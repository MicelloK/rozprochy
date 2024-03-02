# Systemy rozproszone - zad 1 - server
# @Michał Kobiera

import socket
import threading
  
def client_thread(id, client_socket):
     init_msg = client_socket.recv(1024)
     client_socket.sendall(init_msg)
     name = str(init_msg, 'utf-8')
     print(f"NEW CONNECTION | id: {id} name: {name}")
     
     while True:
          try:
               buff = client_socket.recv(1024)
               msg = f"Msg from {id} ({name}): " + str(buff, 'utf-8')
               print(msg)
               for connection_id in connections:
                    if connection_id != id:
                         connections[connection_id].send(bytes(msg, 'utf-8'))
               client_socket.sendall(bytes('echo', 'utf-8'))
          except (ConnectionAbortedError, ConnectionError):
               print(f"CLIENT {id} ({name}) CLOSED CONNECTION")
               free_ids.append(id)
               connections.pop(id)
               client_socket.close()
               break
          
          

if __name__ == '__main__':
     server_port = 9009
     MAX_CLIENTS = 5
     server_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
     server_socket.bind(('', server_port))
     server_socket.listen(5)
     
     free_ids = list(range(MAX_CLIENTS))
     connections = {}
     
     print("WAITING FOR CONNECTIONS...")
     while True:
          if free_ids:
               client_socket, address = server_socket.accept()
               id = free_ids.pop()
               connections[id] = client_socket
               ct = threading.Thread(target=client_thread, args=(id, client_socket))
               ct.start()
     

     
     