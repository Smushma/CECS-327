## To compile the java files 
Via CLI and assuming you have set up your JDK path:

```
javac UDPServer.java
javac UDPClient.java
```

To run the server, enter a port number as follows:

```
java UDPServer <port>
```

Example:

```
java UDPServer 6789
```

To kill the server, Ctrl + C in the command line

To run the client, the command format is:
```
java UDPClient <ip-address> <port> <message>
```
Example:

```
java UDPClient 192.168.111.111 9999 "CECS 327"
```