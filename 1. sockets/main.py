# Script to open client processes
# @Michał Kobiera

import subprocess

interpreter = 'python3'
server_path = '1. sockets/server.py'
client_path = '1. sockets/client.py'
num_of_processes = 3

for _ in range(num_of_processes):
    subprocess.Popen(['wt', '-w', '0', interpreter, client_path])
    
subprocess.Popen(['wt', '-w', '-1', 'nt', interpreter, server_path])
