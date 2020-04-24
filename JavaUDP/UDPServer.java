import java.net.*;
import java.io.*;

public class UDPServer {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("ERROR: Invalid number of arguments.");
            System.out.println("Usage: java UDPServer <port>");
            return;
        }

        DatagramSocket datagramSocket = null;
        try {
            int serverPort = Integer.parseInt(args[0]);
            datagramSocket = new DatagramSocket(serverPort);

            // Continuously listen on the port and echo data back to client
            while (true) {
                byte[] buffer = new byte[100];
                DatagramPacket request = new DatagramPacket(buffer, buffer.length);
                datagramSocket.receive(request);
                System.out.println("Received message: " + new String(request.getData()));

                // Echo the data
                DatagramPacket response = new DatagramPacket(
                    request.getData(), 
                    request.getLength(), 
                    request.getAddress(), 
                    request.getPort() 
                );

                datagramSocket.send(response);
            } // Ctrl + C in CLI to kill the program
        }
        catch (SocketException e) {
            System.out.println("SocketException: " + e.getMessage());
        }
        catch(IOException e) {
            System.out.println("IOException: " + e.getMessage());
        }
        finally {
            datagramSocket.close();
        }
    }
}