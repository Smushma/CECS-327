import java.net.*;
import java.io.*;
import java.util.Scanner;

public class UDPClient {
	private static String serverIp, portInput, userMessage;
	
	private static void userPrompt() {
		System.out.println("Enter a UDP server IP address to connect to:");
		Scanner scanner = new Scanner(System.in);
		serverIp = scanner.nextLine();
		
		System.out.println("Enter a port number for the UDP server:");
		portInput = scanner.nextLine();
		
		System.out.println("Enter a message to send to the UDP server:");
		userMessage = scanner.nextLine();
	}
	
    public static void main(String args[]) {
		// For CLI version
		/*
        if (args.length != 3) {
            System.out.println("ERROR: Invalid number of arguments.");
            System.out.println("Usage: java UDPClient <ip-address> <port> <message>");
            return;
        }
		*/
		
		// Prom
		userPrompt();

        DatagramSocket datagramSocket = null;
        try {
			boolean flag = true;
			while (flag) {
				// Establish a new datagram socket
				datagramSocket = new DatagramSocket();
				
				// Initialize with args
				//InetAddress host = InetAddress.getByName(args[0]);
				//int serverPort = Integer.parseInt(args[1]);
				//byte[] message = args[2].getBytes();
				
				InetAddress host = InetAddress.getByName(serverIp);
				int serverPort = Integer.parseInt(portInput);
				byte[] message = userMessage.getBytes();

				// Send creates and sends request packet to server
				DatagramPacket request = new DatagramPacket(message, message.length, host, serverPort);
				datagramSocket.send(request);

				// Receive the response and display to user
				byte[] buffer = new byte[100];
				DatagramPacket response = new DatagramPacket(buffer, buffer.length);

				// Use the same socket that you did for sending
				datagramSocket.receive(response);
				System.out.println("Response from server: " + new String(response.getData()));
				
				// Continue?
				System.out.println("Continue the program? (y/n)");
				Scanner scanner = new Scanner(System.in);
				String continueProgram = scanner.nextLine().toLowerCase();
				
				if (continueProgram.equals("y")) 
					userPrompt();
				else 
					flag = false;
			}
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