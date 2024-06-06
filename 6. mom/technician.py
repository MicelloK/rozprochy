import pika
import time

print(' [*] technician.py')
connection = pika.BlockingConnection(pika.ConnectionParameters('localhost'))
channel = connection.channel()

def handle_msg(ch, method, properties, body):
    body_str = body.decode('utf-8')
    print(f'\n [x] {body_str}')
    
# message
msg_exchange_name = 'message_exchange'
channel.exchange_declare(exchange=msg_exchange_name, exchange_type='topic')

technician_name = input(' [>] Enter name: ')

msg_queue = channel.queue_declare(queue='', exclusive=True)
msg_queue_name = msg_queue.method.queue

channel.queue_bind(exchange=msg_exchange_name, queue=msg_queue_name, routing_key='technician.' + technician_name)
channel.queue_bind(exchange=msg_exchange_name, queue=msg_queue_name, routing_key='technician.all')
channel.queue_bind(exchange=msg_exchange_name, queue=msg_queue_name, routing_key='all')
channel.basic_consume(queue=msg_queue_name, on_message_callback=handle_msg, auto_ack=True)

def process_test_request(ch, method, properties, body):
    print(f' [r] {str(body)}')
    body_str = body.decode('utf-8')
    time.sleep(len(body_str)//3)
    doctor_name, task_info = body_str.split(' ')
    print(f' [d] task {task_info} done')
    channel.basic_publish(
        exchange=msg_exchange_name,
        routing_key='doctor.' + doctor_name,
        body=task_info + ' done'
    )
    ch.basic_ack(delivery_tag=method.delivery_tag)

# service
service_exchange_name = 'service_exchange'
channel.exchange_declare(exchange=service_exchange_name, exchange_type='direct')

services = input(' [>] Enter services: ').split(' ')
for service in services:
    if service not in ['knee', 'elbow', 'hip']:
        print(f' [!] unknown service: {service}')
    else:
        channel.queue_declare(queue=service + '_queue')
        channel.queue_bind(exchange=service_exchange_name, queue=service + '_queue', routing_key=service)
        channel.basic_consume(queue=service + '_queue', on_message_callback=process_test_request, auto_ack=False)
    print(' [*] processed service ' + service)
        
print(' [*] start consuming...')
channel.start_consuming()
connection.close()
        
        

    