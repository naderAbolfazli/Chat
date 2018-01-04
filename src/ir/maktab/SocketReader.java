package ir.maktab;

import sun.net.ConnectionResetException;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.SocketException;

/**
 * Created by nader on 12/18/2017.
 */
public class SocketReader implements Runnable {
    DataInputStream in;

    public SocketReader(DataInputStream in) {
        this.in = in;
    }

    @Override
    public void run() {
        try {
            while (true) {
                String message = in.readUTF();
                System.out.println(message);
                if (message.equals("exit"))
                    throw new SocketException();
            }
        }catch (SocketException se){
            System.err.println("disconnected");
            System.exit(0);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

}
