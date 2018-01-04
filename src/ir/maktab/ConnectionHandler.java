package ir.maktab;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Created by Nader on 1/4/2018.
 */
public class ConnectionHandler implements Runnable {
    private Socket firstClient;
    private Socket secondClient;

    public ConnectionHandler(Socket firstClient, Socket secondClient) {
        this.firstClient = firstClient;
        this.secondClient = secondClient;
    }

    @Override
    public void run() {
        try {
                DataInputStream in1 = new DataInputStream(firstClient.getInputStream());
                DataOutputStream out1 = new DataOutputStream(firstClient.getOutputStream());
                DataInputStream in2 = new DataInputStream(secondClient.getInputStream());
                DataOutputStream out2 = new DataOutputStream(secondClient.getOutputStream());

            out1.writeUTF("you connected.");
            out2.writeUTF("you connected.");

            new Thread(new SocketReaderToWriter(in1, out2, firstClient.getLocalAddress(), secondClient.getLocalAddress())).start();
            new Thread(new SocketReaderToWriter(in2, out1, firstClient.getLocalAddress(), secondClient.getLocalAddress())).start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
