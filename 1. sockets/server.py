# Systemy rozproszone - zad 1 - server
# @Michał Kobiera

import socket
import threading
  
def client_thread(id, client_socket):
     init_msg = client_socket.recv(1024)
     client_socket.sendall(int.to_bytes(id))
     name = str(init_msg, 'utf-8')
     print(f"NEW CONNECTION | id: {id} name: {name}")
     
     while True:
          try:
               buff = client_socket.recv(1024)
               print(f"[TCP] Msg from (addr: {client_socket.getpeername()} id: {id}) {name}: " + str(buff, 'utf-8'))
               for connection_id in connections:
                    if connection_id != id:
                         connections[connection_id].send(bytes(f"Msg from {name}: " + str(buff, 'utf-8'), 'utf-8'))
               client_socket.sendall(bytes('echo', 'utf-8'))
          except (ConnectionAbortedError, ConnectionError):
               print(f"CLIENT {id} ({name}) CLOSED CONNECTION")
               free_ids.append(id)
               connections.pop(id)
               client_socket.close()
               break
          
def udp_listener(client_socket):
     print('[UDP] START LISTENING...')
     while True:
          id_buff, address = client_socket.recvfrom(1024)
          name_buff, address = client_socket.recvfrom(1024)
          buff, address = client_socket.recvfrom(1024)
          client_id = int.from_bytes(id_buff)
          name = str(name_buff, 'utf-8')
          msg = str(buff, 'utf-8')
          
          print(f"[UDP] Msg from (addr: {address} id: {id}) {name}: " + str(buff, 'utf-8'))
          for connection_id in connections:
               if connection_id != client_id:
                    connections[connection_id].send(bytes(f'Msg from {name}: {msg}', 'utf-8'))

     
          

if __name__ == '__main__':
     server_port = 9009
     MAX_CLIENTS = 5
     
     tcp_server_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
     udp_server_socket = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
     tcp_server_socket.bind(('', server_port))
     udp_server_socket.bind(('', server_port))
     tcp_server_socket.listen(5)
     
     free_ids = list(range(MAX_CLIENTS))
     connections = {}
     
     udp_t = threading.Thread(target=udp_listener, args=(udp_server_socket,))
     udp_t.start()
     print("[TCP] WAITING FOR CONNECTIONS...")
     while True:
          if free_ids:
               client_socket, address = tcp_server_socket.accept()
               id = free_ids.pop()
               connections[id] = client_socket
               ct = threading.Thread(target=client_thread, args=(id, client_socket))
               ct.start()
     

     
     