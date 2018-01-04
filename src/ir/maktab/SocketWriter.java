package ir.maktab;

import sun.net.ConnectionResetException;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.SocketException;
import java.sql.Connection;
import java.util.Scanner;

/**
 * Created by nader on 12/18/2017.
 */
public class SocketWriter implements Runnable {
    private DataOutputStream out;
    private Scanner input = new Scanner(System.in);

    public SocketWriter(DataOutputStream out) {
        this.out = out;
    }

    @Override
    public void run() {
        try {
            while (true) {
                String message = input.nextLine();
                out.writeUTF(message);
                if (message.equals("exit"))
                    System.exit(0);
            }
        }catch (SocketException se){
            System.err.println("disconnected");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
