# Systemy rozproszone - zad 1 - client
# @Michał Kobier

import threading
import socket

def print_msg_from_server(client_socket):
     while True:
          try:
               msg = str(client_socket.recv(1024), 'utf-8')
               if msg and msg != 'echo':
                    print('\n' + msg, end='\n> ')
          except ConnectionError:
               print('SERVER DISCONECTED')
               break
          
def print_multicast_msg(client_id):
     multicast_group = '224.1.1.1'
     multicast_port = 5007
     
     multicast_socket = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
     multicast_socket.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)
     multicast_socket.bind(('', multicast_port))
     
     mreq = socket.inet_aton(multicast_group) + socket.inet_aton('0.0.0.0')
     multicast_socket.setsockopt(socket.IPPROTO_IP, socket.IP_ADD_MEMBERSHIP, mreq)
     
     while True:
          try:
               msg = str(multicast_socket.recv(1024), 'utf-8')
               msg_id, name, msg = msg.split(maxsplit=2)
               msg_id = int(msg_id)
               if msg and msg != 'echo' and msg_id != client_id:
                    print(f'\nMULTICAST | Msg from {name}: {msg}', end='\n> ')
          except ConnectionError:
               print('SERVER DISCONECTED')
               break

if __name__ == '__main__':
     server_port = 9009
     server_IP = 'localhost'
     
     name = input('Enter your name: ')

     tcp_client_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
     udp_client_socket = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
     multicast_client_socket = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
     
     try:
          tcp_client_socket.connect((server_IP, server_port))
          tcp_client_socket.send(bytes(name, 'utf-8'))
          echo_client_id = tcp_client_socket.recv(1024)
          id = int.from_bytes(echo_client_id)
     except:
          print('Connection error!')
     else:
          print('Connected')
          pt = threading.Thread(target=print_msg_from_server, args=(tcp_client_socket,), daemon=True)
          mt = threading.Thread(target=print_multicast_msg, args=(id,), daemon=True)
          pt.start()
          mt.start()
          
          while True:
               msg = input('> ')
               if msg.upper() == 'C':               
                    tcp_client_socket.close()
                    udp_client_socket.close()
                    multicast_client_socket.close()
                    exit()
               elif msg.upper() == 'U':
                    msg = input('[UDP] > ')
                    if msg == 'ascii':
                         with open('lab1/ascii_art.txt') as file:
                              msg = file.read()
                    if msg:
                         udp_client_socket.sendto(echo_client_id, (server_IP, server_port))
                         udp_client_socket.sendto(bytes(name, 'utf-8'), (server_IP, server_port))
                         udp_client_socket.sendto(bytes(msg, 'utf-8'), (server_IP, server_port))
               elif msg.upper() == 'M':
                    msg = input('[MULTICAST] > ')
                    if msg:
                         msg = f'{id} {name} {msg}'
                         multicast_client_socket.sendto(bytes(msg, 'utf-8'), ('224.1.1.1', 5007))
               else:
                    tcp_client_socket.send(bytes(msg, 'utf-8'))
                    

     