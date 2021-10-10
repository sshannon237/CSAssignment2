package UploadServer;

import java.net.*;
import java.io.*;
public class UploadServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = null;

        // Instantiates socket
        try {
            serverSocket = new ServerSocket(8999);
        } catch (IOException e) {
            System.err.println("Could not listen on port: 8999.");
            System.exit(-1);
        }

        // Multi thread call
        while (true) {
	        new UploadServerThread(serverSocket.accept()).start();
        }
    }
}
