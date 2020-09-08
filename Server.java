import java.net.Socket;
import java.net.ServerSocket;
import java.io.*;
import java.util.Scanner;

public class Server extends Thread {

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
            try{System.out.println(input.readLine());}
            catch(Exception e)
            {
                System.out.println(e);
                break;
            }
        }
    }

    BufferedReader input;

    public static void main(String[] args) {

        try {
            Scanner s = new Scanner(System.in);
            System.out.println("Hello, please enter your username");
            String name = s.nextLine();



            Server server = new Server();
            ServerSocket serverSocket = new ServerSocket(50000);
            System.out.println("Please wait for the client to connect");

            Socket socket = serverSocket.accept();
            System.out.println("Client has connected!");
            server.input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter output = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            server.start();

            String message;
            while(true)
            {
                message = server.inputChat();
                if(message.equals("/stop"))   {
                    System.exit(0);
                }
                else {
                    output.write(name + ": " + message + "\n");
                    output.flush();
                }
            }


        }
        catch(Exception e)  {
            System.out.println(e);
        }


    }
}
