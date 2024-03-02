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

if __name__ == '__main__':
     server_port = 9009
     server_IP = 'localhost'
     
     name = input('Enter your name: ')

     tcp_client_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
     udp_client_socket = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
     
     try:
          tcp_client_socket.connect((server_IP, server_port))
          tcp_client_socket.send(bytes(name, 'utf-8'))
          echo_client_id = tcp_client_socket.recv(1024)
     except:
          print('Connection error!')
     else:
          print('Connected')
          pt = threading.Thread(target=print_msg_from_server, args=(tcp_client_socket,))
          pt.start()
          
          while True:
               msg = input('> ')
               if msg.upper() == 'C':
                    tcp_client_socket.close()
                    udp_client_socket.close()
                    exit()
               elif msg.upper() == 'U':
                    msg = input('[UDP] > ')
                    if msg:
                         udp_client_socket.sendto(echo_client_id, (server_IP, server_port))
                         udp_client_socket.sendto(bytes(name, 'utf-8'), (server_IP, server_port))
                         udp_client_socket.sendto(bytes(msg, 'utf-8'), (server_IP, server_port))
               else:  
                    tcp_client_socket.send(bytes(msg, 'utf-8'))
                    

     