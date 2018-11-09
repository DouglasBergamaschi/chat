package client;

import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.*;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.ERROR_MESSAGE;
import jdk.net.Sockets;

public class Chat extends javax.swing.JFrame {

    private String nome;
    private Socket s;
    private BufferedReader br;
    private InputStreamReader isr;
    private boolean executar;

    //construtor
    public Chat(String nome) {

        initComponents();
        executar = true;
        this.nome = nome;

        try {
            s = new Socket("localhost", 2000);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Não conseguiu se conectar ao servidor", "", ERROR_MESSAGE);
            System.exit(0);
        }
        Thread();
    }

    private void Thread() {

        Thread t = new Thread(new Runnable() {
            String mensagem;

            @Override
            public void run() {

                try {
                    isr = new InputStreamReader(s.getInputStream());
                    br = new BufferedReader(isr);

                    while ((mensagem = br.readLine()) != null) {

                        mensagemRetorno.setText(mensagemRetorno.getText() + mensagem + "\n");
                        
                        if(!executar){
                            break;
                        }
                        
                    }

                } catch (IOException e) {
                    JOptionPane.showMessageDialog(null, "Erro na conexão com o Servidor!!", "", ERROR_MESSAGE);
                }
            }
        });
        t.start();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        mensagemRetorno = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        mensagemEnvia = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        mensagemRetorno.setEditable(false);
        mensagemRetorno.setColumns(20);
        mensagemRetorno.setRows(5);
        jScrollPane1.setViewportView(mensagemRetorno);

        mensagemEnvia.setColumns(20);
        mensagemEnvia.setRows(5);
        mensagemEnvia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                mensagemEnviaKeyPressed(evt);
            }
        });
        jScrollPane2.setViewportView(mensagemEnvia);

        jButton1.setText("Enviar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Sair");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton2, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 365, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(23, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton2)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        String mensagem = nome + " disse: ";

        try {
            PrintStream ps = new PrintStream(s.getOutputStream());
            mensagem += mensagemEnvia.getText();

            ps.println(mensagem);
            ps.flush();

            mensagemEnvia.setText("");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Não enviou a mensagem", "", ERROR_MESSAGE);
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void mensagemEnviaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_mensagemEnviaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            String mensagem = nome + " disse: ";

            try {
                PrintStream ps = new PrintStream(s.getOutputStream());
                mensagem += mensagemEnvia.getText();

                ps.println(mensagem);
                ps.flush();

                mensagemEnvia.setText("");
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Não enviou a mensagem", "", ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_mensagemEnviaKeyPressed
//botao sair
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try{
        
        s.close();
        System.exit(0);
            
        }catch(IOException e){
            e.printStackTrace();
            
        }
        
        
    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea mensagemEnvia;
    private javax.swing.JTextArea mensagemRetorno;
    // End of variables declaration//GEN-END:variables

    void visible() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    void visible(boolean b) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
