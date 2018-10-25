package servidor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.*;

public class Servidor {

    public static void main(String[] args) {

        String mensagemRetorno;

        try {
            ServerSocket server = new ServerSocket(2000);
            Socket socket;

            while (true) {
                socket = server.accept();

                InputStreamReader isr = new InputStreamReader(socket.getInputStream());
                BufferedReader br = new BufferedReader(isr);

                while ((mensagemRetorno = br.readLine()) != null) {

                    System.out.println(mensagemRetorno);

                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
