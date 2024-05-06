package server;

import io.grpc.Server;
import io.grpc.ServerBuilder;

import io.grpc.stub.StreamObserver;
import sr.grpc.gen.*;

import java.io.IOException;
import java.util.*;

public class EventNotificationServer {

    private final int port;
    private Server server;

    public EventNotificationServer(int port) {
        this.port = port;
    }

    public void start() throws IOException {
        server = ServerBuilder.forPort(port)
                .addService(new EventNotificationImpl())
                .build()
                .start();
        System.out.println("Server started, listening on " + port);
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.err.println("*** Shutting down gRPC server since JVM is shutting down");
            EventNotificationServer.this.stop();
            System.err.println("*** Server shut down");
        }));
    }

    private void stop() {
        if (server != null) {
            server.shutdown();
        }
    }

    private void blockUntilShutdown() throws InterruptedException {
        if (server != null) {
            server.awaitTermination();
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        EventNotificationServer server = new EventNotificationServer(50051);
        server.start();
        server.blockUntilShutdown();
    }

    private static class EventNotificationImpl extends EventNotificationGrpc.EventNotificationImplBase {
        private final Map<Event.EventType, List<StreamObserver<Event>>> subscribersMap = new LinkedHashMap<>();
        private final Map<StreamObserver<Event>, List<Event>> eventBuffer = new LinkedHashMap<>();
        private final Random random = new Random();
        private int usersCount = 0;

        public EventNotificationImpl() {
            Thread thread = new Thread(() -> {
                try {
                    generateEvents();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
            thread.start();
        }

        @Override
        public void subscribe(SubscriptionRequest request, StreamObserver<Event> responseObserver) {
            int MAX_USERS = 10;
            if (usersCount >= MAX_USERS) {
                responseObserver.onError(new Exception("Server full"));
            } else {
                List<Event.EventType> eventTypes = request.getEventTypesList();
                System.out.println("New client, types=" + eventTypes);
                for (Event.EventType type : eventTypes) {
                    List<StreamObserver<Event>> observers = subscribersMap.computeIfAbsent(type, k -> new ArrayList<>());
                    observers.add(responseObserver);
                    subscribersMap.put(type, observers);
                }
                usersCount++;
            }
        }

        private void generateEvents() throws InterruptedException {
            int BUFF_SIZE = 5;
            System.out.println("Generating events...");
            while (true) {
                int sleepTime = random.nextInt(3001) + 1000;
                Thread.sleep(sleepTime);
                int eventTypeNum = random.nextInt(Event.EventType.values().length-2);
                Event.EventType eventType = Event.EventType.forNumber(eventTypeNum);
                Event newEvent = Event.newBuilder()
                        .setType(eventType)
                        .setName(generateEventName(eventType))
                        .setDescription("Event description bla bla bla")
                        .build();
                System.out.println("New event | name=" + newEvent.getName() + ", type=" + eventType);
                for (Event.EventType type : new ArrayList<>(subscribersMap.keySet())) {
                    if (type == eventType && subscribersMap.containsKey(type)) {
                        List<StreamObserver<Event>> observers = subscribersMap.get(type);
                        Iterator<StreamObserver<Event>> iterator = observers.iterator();
                        while (iterator.hasNext()) {
                            StreamObserver<Event> streamObserver = iterator.next();
                            try {
                                flushBuffer(streamObserver);
                                streamObserver.onNext(newEvent);
                            } catch (Exception e) {
                                List<Event> events = eventBuffer.getOrDefault(streamObserver, new LinkedList<>());
                                events.add(newEvent);
                                eventBuffer.put(streamObserver, events);
                                if (events.size() >= BUFF_SIZE) {
                                    System.out.println("Client lost connection, closing channel");
                                    iterator.remove();
                                    usersCount--;
                                } else {
                                    System.out.println("Client lost connection, buffering data... " + "[" + events.size() + "]");
                                }
                            }
                        }
                    }
                }
            }
        }

        private void flushBuffer(StreamObserver<Event> streamObserver) {
            if (eventBuffer.containsKey(streamObserver)) {
                List<Event> events = eventBuffer.get(streamObserver);
                for (Event event : events) {
                    try {
                        streamObserver.onNext(event);
                        events.remove(event);
                    } catch (Exception ignored) {}
                }
                if (events.isEmpty()) {
                    eventBuffer.remove(streamObserver);
                } else {
                    eventBuffer.put(streamObserver, events);
                }
            }
        }

        private String generateEventName(Event.EventType type) {
            List<String> sportNames = List.of(new String[]{"Final ligii mistrzow", "Puchar Polski", "Mistrzostwa swiata", "Lewandowski strzela 20 goli w 4 minuty"});
            List<String> cinemaNames = List.of(new String[]{"Wychodzi Diuna 3", "Polak dostał oskara", "Czy Tom Holland zagra kolejnego spidermana?", "Zamykają najpopularniejsze polskie kino"});
            List<String> theaterNames = List.of(new String[]{"Chlopi", "Teatr Słowackiego - teatr, czy przystanek autobusowy?", "Chodzze do teatru - kolego", "Dlaczego teatr jest lepszy od kina, 7 powodó"});
            List<String> musicNames = List.of(new String[]{"Sanach wydaje kolejne piosenki, ludzie ile można...", "Przez twe oczy zielone zielone OSZALAŁEM", "Ostatni koncert grupy mozarta", "Harry Potter symfonicznie - tauron arena"});

            int choice = random.nextInt(4);
            switch (type) {
                case SPORT -> {
                    return sportNames.get(choice);
                }
                case MUSIC -> {
                    return musicNames.get(choice);
                }
                case THEATER -> {
                    return theaterNames.get(choice);
                }
                case CINEMA -> {
                    return cinemaNames.get(choice);
                }
                case UNRECOGNIZED -> {
                    return "Unknown name";
                }
            }
            return "Unknown name";
        }
    }
}

