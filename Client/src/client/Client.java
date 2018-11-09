package client;

import static javax.swing.JOptionPane.*;

public class Client {

    public static void main(String[] args) {

            String nome = showInputDialog(null, "Digite o seu nome: ","",PLAIN_MESSAGE);
        
        
        Chat chat = new Chat(nome);//Instancia a classe chat
        chat.setVisible(true);//Inicializa e mostra a tela do chat
       
    }

}
