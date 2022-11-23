import java.io.*;
import java.net.Socket;

public class Execute implements Runnable {

    private Socket cliente;
    private int contador;

    public Execute(Socket cliente, int contador) {
        this.cliente = cliente;
        this.contador = contador;

    }

    @Override
    public void run() {
        try {
            InputStream inputStream = cliente.getInputStream();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = bufferedReader.readLine()) != null){
                String finalLine = "Cliente" +contador +": "+line;
                System.out.println(finalLine);
                Server.sockets.forEach(socket -> {
                    OutputStream outputStream = null;
                    try {
                        outputStream = socket.getOutputStream();
                        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
                        bufferedWriter.write(finalLine);
                        bufferedWriter.newLine();
                        bufferedWriter.flush();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
