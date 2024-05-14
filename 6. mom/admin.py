import pika
import threading

print(' [*] admin.py')
connection = pika.BlockingConnection(pika.ConnectionParameters('localhost'))
channel = connection.channel()

msg_exchange_name = 'message_exchange'
channel.exchange_declare(exchange=msg_exchange_name, exchange_type='topic')

msg_queue = channel.queue_declare(queue='', exclusive=True)
msg_queue_name = msg_queue.method.queue

channel.queue_bind(exchange=msg_exchange_name, queue=msg_queue_name, routing_key='#')

def handle_msg(ch, method, properties, body):
    body_str = body.decode('utf-8')
    print(f'\n [x] {body_str}\n [>] Enter command: ', end='')
    
channel.basic_consume(queue=msg_queue_name, on_message_callback=handle_msg, auto_ack=True)

def start_consuming():
    channel.start_consuming()

consume_thread = threading.Thread(target=start_consuming)
consume_thread.start()

while True:
    command = input(' [>] Enter command: ') # "topic message"
    if command == 'exit':
        break
    
    topic, message = command.split(' ')
    channel.basic_publish(
        exchange=msg_exchange_name,
        routing_key=topic,
        body=message
    )
    