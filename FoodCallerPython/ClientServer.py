import socket
import os
import tkinter
import winsound


socketToClient = socket.socket(socket.AF_INET, socket.SOCK_STREAM)

s = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
s.connect(("8.8.8.8", 80))

socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
server_address = (s.getsockname()[0], 50002)
s.close()
print("Starting the Bind to port " + str(server_address[1]))
socket.bind(server_address)

socket.listen(10)

while True:
    print("Waiting for connection")
    connection, client_address = socket.accept()
    try:
        print("connection made from" + str(client_address))
        data = connection.recv(32)
        print(data)
        #os.startfile(r"C:\Program Files (x86)\FoodCaller\Eat.txt")
        #os.startfile(r"C:\Program Files (x86)\FoodCaller\WhatsApp Ptt 2019-04-26 at 2.34.39 PM.ogg")
        connection.sendall(data)
        connection.close()


    finally:
        connection.close()

