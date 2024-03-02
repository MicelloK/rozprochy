import threading
from time import sleep
import random


def test():
     print("asdf")
     sleep(random.randint(1, 3))
     print("helrkjh")
     
     
conntections = []    
for i in range(3):
     ct = threading.Thread(target=test)
     conntections.append(ct)
     ct.start()
print(conntections)