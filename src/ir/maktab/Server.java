package ir.maktab;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

public class Server implements Runnable {
    private Socket client;
    private static Map<Socket, String> clients = new HashMap<>();

    public Server(Socket client) {
        this.client = client;
    }

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(4030);
        while (true) {
            System.out.println("Listening...");
            Socket client = serverSocket.accept();
            new DataOutputStream(client.getOutputStream()).writeUTF("Enter your Name:");
            String name = new DataInputStream(client.getInputStream()).readUTF();
            clients.put(client, name);
            System.out.println(client.getLocalAddress() + " " + name + " Added to List");
            System.out.println(client1.getLocalAddress() + " Connected to " + client2.getLocalAddress());
            new Thread(new ConnectionHandler(client1, client2)).start();
        }
    }

    @Override
    public void run() {

    }
}