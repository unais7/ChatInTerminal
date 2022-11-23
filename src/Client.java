import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    static Socket socket;

    public static void main(String[] args) throws IOException {
        socket = new Socket("localhost", 5555);
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Scanner scanner = new Scanner(System.in);
                        String message = scanner.nextLine();
                        OutputStream outputStream = socket.getOutputStream();
                        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream));
                        writer.write(message);
                        writer.newLine();
                        writer.flush();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                }
            }
        });
        thread.start();
        InputStream inputStream = socket.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        while ((line = reader.readLine()) != null){
            System.out.println(line);
        }

    }
}
