import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {
    static ServerSocket serverSocket;
    static List<Socket> sockets = new ArrayList<>();
    static int contador = 0;

    public static void main(String[] args) throws IOException {
        serverSocket = new ServerSocket(5555);
        while (true){
            Socket cliente = serverSocket.accept();
            sockets.add(cliente);
            Thread thread = new Thread(new Execute(cliente, contador));
            contador++;
            thread.start();
        }

    }

}
