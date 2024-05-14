import pika
import random
import threading

print(' [*] doctor.py')
doctor_name = input(' [>] Enter name: ')

connection = pika.BlockingConnection(pika.ConnectionParameters('localhost'))
channel = connection.channel()

# message
msg_exchange_name = 'message_exchange'
channel.exchange_declare(exchange=msg_exchange_name, exchange_type='topic')

msg_queue = channel.queue_declare(queue='', exclusive=True)
msg_queue_name = msg_queue.method.queue

channel.queue_bind(exchange=msg_exchange_name, queue=msg_queue_name, routing_key='doctor.' + doctor_name)
channel.queue_bind(exchange=msg_exchange_name, queue=msg_queue_name, routing_key='doctor.all')
channel.queue_bind(exchange=msg_exchange_name, queue=msg_queue_name, routing_key='all')

# service
service_exchange_name = 'service_exchange'
channel.exchange_declare(exchange=service_exchange_name, exchange_type='direct')

def handle_msg(ch, method, properties, body):
    body_str = body.decode('utf-8')
    print(f'\n [x] {body_str}\n [>] enter test name: ', end='')

channel.basic_consume(queue=msg_queue_name, on_message_callback=handle_msg, auto_ack=True)

def start_consuming():
    channel.start_consuming()

consume_thread = threading.Thread(target=start_consuming)
consume_thread.start()

if __name__ == '__main__':
    while True:
        test = input(' [>] enter test name: ')
        test_num = random.randint(1, 1000)
        
        print(f' [*] test: {test}, patient: {test_num}')
        if test == 'hip':
            channel.basic_publish(
                exchange=service_exchange_name,
                routing_key='hip',
                body=doctor_name + ' ' + str(test_num) + '.hip'
            )
        elif test == 'knee':
            channel.basic_publish(
                exchange=service_exchange_name,
                routing_key='knee',
                body=doctor_name + ' ' + str(test_num) + '.knee'
            )
        elif test == 'elbow':
            channel.basic_publish(
                exchange=service_exchange_name,
                routing_key='elbow',
                body=doctor_name + ' ' + str(test_num) + '.elbow'
            )
        elif test == 'exit':
            break
        else:
            print(' [!] unknown test name, try again')
            
channel.stop_consuming()
consume_thread.join()
connection.close()
