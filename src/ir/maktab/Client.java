package ir.maktab;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        System.out.print("Enter Server ip: ");
        String serverIp = new Scanner(System.in).next();

        try (Socket s = new Socket(serverIp, 4030);
             DataOutputStream out = new DataOutputStream(s.getOutputStream());
             DataInputStream in = new DataInputStream(s.getInputStream())) {

            new Thread(new SocketReader(in)).start();
            new Thread(new SocketWriter(out)).start();
            while (true);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}