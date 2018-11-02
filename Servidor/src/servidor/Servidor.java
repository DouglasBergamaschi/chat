package servidor;

import java.io.IOException;
import java.io.PrintStream;
import java.net.*;
import java.util.ArrayList;

public class Servidor {

    public static void main(String[] args) {

        ArrayList<PrintStream> clientes = new ArrayList<>();//Array para guardar os clientes

        try {
            ServerSocket server = new ServerSocket(2000);//Inicia o servidor instanciando o server socket
            Socket socket;

            while (true) {
                socket = server.accept();
                //salva o endereco do cliente
                clientes.add(new PrintStream(socket.getOutputStream()));
                
                   Mensagem mensagem = new Mensagem(socket.clientes);
                   
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
