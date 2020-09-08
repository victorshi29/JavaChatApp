import java.net.InetAddress;
import java.net.Socket;
import java.io.*;
import java.util.Scanner;


public class Client extends Thread{

    public String inputChat()
    {
        String chatInput;
        Scanner scanner = new Scanner(System.in);
        chatInput = scanner.nextLine();
        return chatInput;
    }


    public void run()
    {
        while(true)
        {
            try {
                System.out.println(input.readLine());
            }

            catch(Exception e)
            {
                System.out.println(e.toString());
                break;
            }
        }

    }

    Socket s;
    BufferedReader input;
    BufferedWriter output;

    public static void main(String[] args) {
        try {

            InetAddress address = InetAddress.getByName("68.134.231.133");

            Scanner s = new Scanner(System.in);
            System.out.println("Hello, please enter your username");
            String name = s.nextLine();

            Client client = new Client();
            client.s = new Socket(address, 50000);
            System.out.println("You have successfully connected to the server!");

            client.output = new BufferedWriter(new OutputStreamWriter(client.s.getOutputStream()));
            client.input = new BufferedReader(new InputStreamReader(client.s.getInputStream()));

            client.start();


            String message;
            while(true) {
                message = client.inputChat();
                if(message.equals("/stop"))   {
                                        System.exit(0);
                }
                else {
                    client.output.write(name + ": " + message + "\n");
                    client.output.flush();
                }
            }


        }
        catch(Exception e)  {
            System.out.println(e);
        }

    }
}
