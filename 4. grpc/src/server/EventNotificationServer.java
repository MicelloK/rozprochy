package server;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

import sr.grpc.gen.EventNotificationGrpc;
import sr.grpc.gen.SubscriptionRequest;
import sr.grpc.gen.Event;
import sr.grpc.gen.EventSubscribersList;
import sr.grpc.gen.User;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventNotificationServer {

    private int port;
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

        private Map<Integer, List<User>> subscribersMap = new HashMap<>();

        @Override
        public void subscribe(SubscriptionRequest request, StreamObserver<Event> responseObserver) {
            // Obsługa subskrypcji
            // Tutaj można zaimplementować logikę subskrypcji i wysyłania zdarzeń do klienta
        }

        @Override
        public void getSubscribers(Event request, StreamObserver<EventSubscribersList> responseObserver) {
            int eventId = request.getId();
            List<User> subscribers = subscribersMap.getOrDefault(eventId, List.of());
            EventSubscribersList subscribersList = EventSubscribersList.newBuilder()
                    .setEventId(eventId)
                    .addAllUsers(subscribers)
                    .build();
            responseObserver.onNext(subscribersList);
            responseObserver.onCompleted();
        }
    }
}

