
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    final static int PORT_NUMBER = 4495;

    public static void main(String[] args) {
        String command;
        Server obj = new Server();




        try (
                ServerSocket serverSocket = new ServerSocket(PORT_NUMBER);
                Socket clientSocket = serverSocket.accept();
                PrintWriter out =
                        new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(clientSocket.getInputStream()))

        ) {
            System.out.println("Verbindung2");
            String inputLine, outputLine;

            // Initiate conversation with client
            ServerProtocol serverProtocol = new ServerProtocol();
            outputLine = serverProtocol.processInput(null);
            out.println(outputLine);

            while ((inputLine = in.readLine()) != null) {
                outputLine = serverProtocol.processInput(inputLine);
                out.println(outputLine);
                if (outputLine.equals("Bye."))
                    break;
            }
        } catch (IOException e) {
            System.out.println("Exception caught when trying to listen on port "
                    + PORT_NUMBER + " or listening for a connection");
            System.out.println(e.getMessage());
        }

        //	String output = kobj.executeCommand(command);

        //	System.out.println(output);

    }


}