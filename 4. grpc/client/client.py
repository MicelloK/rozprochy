import grpc
import subscribeEvent_pb2
import subscribeEvent_pb2_grpc
import signal
import time

def signal_handler(sig, frame):
    print('\nCtrl+C detected. Interrupting...')
    exit(0)
    
signal.signal(signal.SIGINT, signal_handler)

def run():
    channel = grpc.insecure_channel('localhost:50051')
    client = subscribeEvent_pb2_grpc.EventNotificationStub(channel)
    
    user_events = input('events [0|1|2|3] >> ')
    event_types = []
    for event in user_events:
        if event in '0123':
            event_types.append(int(event))
            
    request = subscribeEvent_pb2.SubscriptionRequest(
        eventTypes=event_types
    )
    
    try:
        response = client.Subscribe(request)
        for event in response:
            print(f'TYPE: {event.type}')
            print(f'Name: {event.name}')
            print(f'Desc: {event.description}')
            print('---------------------')
            
            # Simulate 3 seconds of connection loss
            # print("Simulating temporary connection loss...")
            # channel.close()
            # time.sleep(6)
            # channel = grpc.insecure_channel('localhost:50051')
            # client = subscribeEvent_pb2_grpc.EventNotificationStub(channel)
            # print("Connection re-established.")
            
    except grpc.RpcError as e:
        print('connection error')

if __name__ == '__main__':
    print('--- client start ---')
    run()
