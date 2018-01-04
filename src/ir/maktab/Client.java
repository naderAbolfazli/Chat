package ir.maktab;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {

        try (Socket s = new Socket("localhost", 4030);
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