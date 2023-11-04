/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.proyecto2poo;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.util.Collections;

/**
 *
 * @author Pablo
 */
public class RummikubWindow extends javax.swing.JFrame {
    
    private Player player;
    private int cordMazoX;
    private int cordMazoY;
    

    public RummikubWindow(Player player) {
        
        this.cordMazoX = 10;
        this.cordMazoY = 10;
        
        this.player = player;
        
        
        //try {
        
            // Parte de la ventana
            initComponents();
            setLocationRelativeTo(null);
            
            // Crea una cliente que es su coenxion al server
            
            //player = new Player(this);
            //player.getConnected();
            
            setSize(820, 380);
            
            
        //} catch (IOException ex) {
           
        //}
    }
    
    public void mostrar(String mensaje){
        txaChat.append(mensaje + "\n");
        
    }
    
    public void generarFicha(Token token){

        int value = token.getValue();
        TokensTypes.Token color = token.getColor();

        JLabel tokenLabel = new JLabel();
        pnlPlayerTokens.add(tokenLabel);
        
        tokenLabel.setSize(20,30);
        tokenLabel.setText(String.valueOf(value));
        tokenLabel.setHorizontalAlignment(SwingConstants.CENTER);
        tokenLabel.setBackground(Color.gray); // Establece el fondo en negro
        tokenLabel.setOpaque(true);
        Font font = new Font("Lucida Sans", Font.BOLD, 16);
        tokenLabel.setFont(font);

        switch (color) {
            case BLACK:
                tokenLabel.setForeground(Color.BLACK);
                break;
            case BLUE:
                tokenLabel.setForeground(Color.BLUE);
                break;
            case RED:
                tokenLabel.setForeground(Color.RED);
                break;
            case YELLOW:
                tokenLabel.setForeground(Color.YELLOW);
                break;
            default:
                throw new AssertionError();
        }

        
        tokenLabel.setLocation(cordMazoX, cordMazoY);
        setCordMazoX(getCordMazoX()+30);
        pnlPlayerTokens.repaint();
        
        
    }
    
    public void comenzarPartida (Partida partida){
        ArrayList <Token> tokensGame = new ArrayList <>();
        for (int i = 0 ; i < 106 ; i++){ //Creacion de las 106 fichas
            if (i == 0 || i == 1){ //Generacion de las 2 fichas comodines
                WildCard wild = new WildCard (TokensTypes.Token.SPECIAL, 0); //Valores para saber si es especial o no
                tokensGame.add(wild);
            }else{
                int number = ((i-2) % 13) + 1; //Para generar el numero
                int colorIndex = (i-2) / 13; //Determinar el color
                TokensTypes.Token color;
                
                switch (colorIndex){
                    case 0:
                    case 1:
                        color = TokensTypes.Token.YELLOW;
                        break;
                    
                    case 2:
                    case 3:
                        color = TokensTypes.Token.RED;
                        break;
                        
                    case 4:
                    case 5:
                        color = TokensTypes.Token.BLUE;
                        break;
                    
                    default:
                        color = TokensTypes.Token.BLACK;
                }
                Token token = new Token (color, number);
                tokensGame.add (token);
            }
        }
        
        Collections.shuffle(tokensGame); //Le hacemos shuffle a los tokens
        
        for (int i = 0 ; i < partida.getCurrentPlayers() ; i++){
            
        }
        
        
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlBackGround = new ImagePanel();
        pnlBoard = new javax.swing.JPanel();
        lblAvatarPlayer1 = new javax.swing.JLabel();
        lblAvatarPlayer2 = new javax.swing.JLabel();
        lblAvatarPlayer3 = new javax.swing.JLabel();
        lblAvatarPlayer4 = new javax.swing.JLabel();
        pnlPlayerTokens = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txaChat = new javax.swing.JTextArea();
        txfPrueba = new javax.swing.JTextField();
        btnEnviar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        pnlBackGround.setBackground(new java.awt.Color(204, 204, 204));

        pnlBoard.setBackground(new java.awt.Color(204, 204, 204));
        pnlBoard.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        javax.swing.GroupLayout pnlBoardLayout = new javax.swing.GroupLayout(pnlBoard);
        pnlBoard.setLayout(pnlBoardLayout);
        pnlBoardLayout.setHorizontalGroup(
            pnlBoardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 364, Short.MAX_VALUE)
        );
        pnlBoardLayout.setVerticalGroup(
            pnlBoardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 222, Short.MAX_VALUE)
        );

        lblAvatarPlayer1.setBackground(new java.awt.Color(153, 153, 153));
        lblAvatarPlayer1.setOpaque(true);

        lblAvatarPlayer2.setBackground(new java.awt.Color(153, 153, 153));
        lblAvatarPlayer2.setOpaque(true);

        lblAvatarPlayer3.setBackground(new java.awt.Color(153, 153, 153));
        lblAvatarPlayer3.setOpaque(true);

        lblAvatarPlayer4.setBackground(new java.awt.Color(153, 153, 153));
        lblAvatarPlayer4.setOpaque(true);

        javax.swing.GroupLayout pnlPlayerTokensLayout = new javax.swing.GroupLayout(pnlPlayerTokens);
        pnlPlayerTokens.setLayout(pnlPlayerTokensLayout);
        pnlPlayerTokensLayout.setHorizontalGroup(
            pnlPlayerTokensLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 368, Short.MAX_VALUE)
        );
        pnlPlayerTokensLayout.setVerticalGroup(
            pnlPlayerTokensLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        txaChat.setEditable(false);
        txaChat.setBackground(new java.awt.Color(255, 255, 255));
        txaChat.setColumns(20);
        txaChat.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        txaChat.setLineWrap(true);
        txaChat.setRows(5);
        jScrollPane1.setViewportView(txaChat);

        txfPrueba.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txfPruebaActionPerformed(evt);
            }
        });

        btnEnviar.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        btnEnviar.setText("->");
        btnEnviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEnviarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlBackGroundLayout = new javax.swing.GroupLayout(pnlBackGround);
        pnlBackGround.setLayout(pnlBackGroundLayout);
        pnlBackGroundLayout.setHorizontalGroup(
            pnlBackGroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBackGroundLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(pnlBackGroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblAvatarPlayer4, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblAvatarPlayer1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblAvatarPlayer2, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblAvatarPlayer3, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addGroup(pnlBackGroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(pnlBoard, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlPlayerTokens, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 138, Short.MAX_VALUE)
                .addGroup(pnlBackGroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlBackGroundLayout.createSequentialGroup()
                        .addComponent(txfPrueba, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEnviar, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(15, 15, 15))
        );
        pnlBackGroundLayout.setVerticalGroup(
            pnlBackGroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBackGroundLayout.createSequentialGroup()
                .addGroup(pnlBackGroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(pnlBackGroundLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(pnlBackGroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(pnlBackGroundLayout.createSequentialGroup()
                                .addComponent(lblAvatarPlayer2, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lblAvatarPlayer3, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lblAvatarPlayer1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(pnlBoard, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, Short.MAX_VALUE)
                        .addGroup(pnlBackGroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(pnlPlayerTokens, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblAvatarPlayer4, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(12, 12, 12))
                    .addGroup(pnlBackGroundLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(pnlBackGroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnEnviar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txfPrueba, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(pnlBackGround);
        pnlBackGround.setBounds(0, 0, 810, 337);

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    public int getCordMazoX() {
        return cordMazoX;
    }

    public void setCordMazoX(int cordMazoX) {
        this.cordMazoX = cordMazoX;
    }

    public int getCordMazoY() {
        return cordMazoY;
    }

    public void setCordMazoY(int cordMazoY) {
        this.cordMazoY = cordMazoY;
    }
    
    private void btnEnviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEnviarActionPerformed
        
//        generarFicha(new Token(TokensTypes.Token.RED, 8));
        
        try {
            
            // se toma lo escrito
            String Mensaje = String.valueOf(txfPrueba.getText());
            
            // se muestra en el text area
            txaChat.append(player.getUsername()+"> "+ Mensaje + "\n");
            
            // se limpia el textfield
            txfPrueba.setText("");

            // envia al server la opcion 4 para que le pase al enemigo lo escrito
            player.getWrite().writeInt(4);
            
            // le envia el mensaje
            player.getWrite().writeUTF(player.getUsername()+ "> " + Mensaje);
            
        } catch (IOException ex) {
            System.out.println("");
        }
        
    }//GEN-LAST:event_btnEnviarActionPerformed

    private void txfPruebaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txfPruebaActionPerformed

    }//GEN-LAST:event_txfPruebaActionPerformed
    
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(RummikubWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RummikubWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RummikubWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RummikubWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RummikubWindow(new Player()).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEnviar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblAvatarPlayer1;
    private javax.swing.JLabel lblAvatarPlayer2;
    private javax.swing.JLabel lblAvatarPlayer3;
    private javax.swing.JLabel lblAvatarPlayer4;
    private javax.swing.JPanel pnlBackGround;
    private javax.swing.JPanel pnlBoard;
    private javax.swing.JPanel pnlPlayerTokens;
    private javax.swing.JTextArea txaChat;
    private javax.swing.JTextField txfPrueba;
    // End of variables declaration//GEN-END:variables
    
    class ImagePanel extends JPanel {
        
        private Image image;

        public ImagePanel() {
            this.image = new ImageIcon("ImagenPantallaJuego.jpg").getImage();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(image, 0, 0, this);
        }
    }
}
