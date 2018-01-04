package ir.maktab;

import sun.net.ConnectionResetException;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.SocketException;

/**
 * Created by nader on 12/18/2017.
 */
public class SocketReaderToWriter implements Runnable {
    DataInputStream in;
    DataOutputStream out;
    InetAddress firstIp, secondIp;

    public SocketReaderToWriter(DataInputStream in, DataOutputStream out, InetAddress firstIp, InetAddress secondIp) {
        this.in = in;
        this.out = out;
        this.firstIp = firstIp;
        this.secondIp = secondIp;
    }

    @Override
    public void run() {
        try {
            while (true)
                out.writeUTF(in.readUTF());

        }catch (SocketException se){
            System.err.println(firstIp + " disconnected from " + secondIp);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
