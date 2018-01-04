package ir.maktab;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server implements Runnable{
    private Socket firstClient;
    private Socket secondClient;

    public Server(Socket client1, Socket client2) {
        this.firstClient = client1;
        this.secondClient = client2;
    }

    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        ServerSocket serverSocket = new ServerSocket(4030);
        while (true){
            System.out.println("Listening...");
            Socket client1 = serverSocket.accept();
            Socket client2 = serverSocket.accept();
            System.out.println(client1.getLocalAddress()+" Connected to "+client2.getLocalAddress());
            new Thread(new Server(client1, client2)).start();
        }
    }

    @Override
    public void run() {
        try {
            DataInputStream in1 = new DataInputStream(firstClient.getInputStream());
            DataOutputStream out1 = new DataOutputStream(firstClient.getOutputStream());
            DataInputStream in2 = new DataInputStream(secondClient.getInputStream());
            DataOutputStream  out2 = new DataOutputStream(secondClient.getOutputStream());

            out1.writeUTF("you connected");
            out2.writeUTF("you connected");

            new Thread(new SocketReaderToWriter(in1, out2, firstClient.getLocalAddress(), secondClient.getLocalAddress())).start();
            new Thread(new SocketReaderToWriter(in2, out1, firstClient.getLocalAddress(), secondClient.getLocalAddress())).start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}