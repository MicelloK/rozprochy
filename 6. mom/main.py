import subprocess

interpreter = 'python3'
doctor_path = '6. mom/doctor.py'
technician_path = '6. mom/technician.py'
admin_path = '6. mom/admin.py'
num_of_processes = 2

for _ in range(num_of_processes):
    subprocess.Popen(['wt', '-w', '0', interpreter, technician_path])

for _ in range(num_of_processes):
    subprocess.Popen(['wt', '-w', '2', interpreter, doctor_path])
    
subprocess.Popen(['wt', '-w', '-1', 'nt', interpreter, admin_path])