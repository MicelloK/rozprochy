# Systemy rozproszone - zad 1 - client
# @Michał Kobier

import threading
import socket

def print_msg_from_server(server_socket):
     while True:
          try:
               msg = str(server_socket.recv(1024), 'utf-8')
               if msg and msg != 'echo':
                    print('\n' + msg, end='\n> ')
          except ConnectionError:
               print('SERVER DISCONECTED')
               break

if __name__ == '__main__':
     server_port = 9009
     server_IP = 'localhost'
     
     name = input('Enter your name: ')

     server_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
     
     try:
          server_socket.connect((server_IP, server_port))
          server_socket.send(bytes(name, 'utf-8'))
          echo_msg = server_socket.recv(1024)
     except:
          print('Connection error!')
     else:
          print('Connected')
          pt = threading.Thread(target=print_msg_from_server, args=(server_socket,))
          pt.start()
          
          while True:
               msg = input('> ')
               if msg.upper() == 'C':
                    server_socket.close()
                    exit()
               server_socket.send(bytes(msg, 'utf-8'))

     