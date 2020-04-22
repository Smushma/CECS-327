import java.net.*;
import java.io.*;

public class UDPClient {
    public static void main(String args[]) {
        if (args.length != 3) {
            System.out.println("ERROR: Invalid number of arguments.");
            System.out.println("Usage: java UDPClient <ip-address> <port> <message>");
            return;
        }

        DatagramSocket datagramSocket = null;
        try {
            datagramSocket = new DatagramSocket();
            
            // Initialize with args
            InetAddress host = InetAddress.getByName(args[0]);
            int serverPort = Integer.parseInt(args[1]);
            byte[] message = args[2].getBytes();

            // Send request packet to server
            DatagramPacket request = new DatagramPacket(message, message.length, host, serverPort);
            datagramSocket.send(request);

            // Receive the response and display to user
            byte[] buffer = new byte[100];
            DatagramPacket response = new DatagramPacket(buffer, buffer.length);
            datagramSocket.receive(response);
            System.out.println("Response from server: " + new String(response.getData()));
        }
        catch (SocketException e) {
            System.out.println("SocketException: " + e.getMessage());
        }
        catch (UnknownHostException e) {
            System.out.println("UnknownHostException: " + e.getMessage());
        }
        catch(IOException e) {
            System.out.println("IOException: " + e.getMessage());
        }
        finally {
            datagramSocket.close();
        }
    }
}