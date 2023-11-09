/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.proyecto2poo;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JOptionPane;

/**
 *
 * @author Pablo
 */
public class RummikubWindow extends javax.swing.JFrame {
    
    private Player player;
    private int cordMazoX;
    private int cordMazoY;
    private int turnoJugador; //Puede ser 1,2,3,4
    private int numeroJugador; //Puede ser 1,2,3,4
    private final ImageIcon avatarImagen1 = new ImageIcon("IconoAvatar1.gif");
    private final ImageIcon avatarImagen2 = new ImageIcon("IconoAvatar2.gif");
    private final ImageIcon avatarImagen3 = new ImageIcon("IconoAvatar3.gif");
    private final ImageIcon avatarImagen4 = new ImageIcon("IconoAvatar4.gif");

    

    public RummikubWindow(Player player) {
        
        this.cordMazoX = 10;
        this.cordMazoY = 10;
        
        this.player = player;
        initComponents();
        
        lblTurnos.setVisible(false);
        
        lblAvatarPlayer1.setVisible(false);
        lblAvatarPlayer2.setVisible(false);
        lblAvatarPlayer3.setVisible(false);
        lblAvatarPlayer4.setVisible(false);
        
        lblAvatarPlayer1.setIcon(avatarImagen1);
        lblAvatarPlayer1.setOpaque(false);
        lblAvatarPlayer1.setBorder(null);
        
        lblAvatarPlayer2.setIcon(avatarImagen2);
        lblAvatarPlayer2.setOpaque(false);
        lblAvatarPlayer2.setBorder(null);
        
        
        
        lblAvatarPlayer3.setIcon(avatarImagen3);
        lblAvatarPlayer3.setOpaque(false);
        lblAvatarPlayer3.setBorder(null);
        
        
        lblAvatarPlayer4.setIcon(avatarImagen4);
        lblAvatarPlayer4.setOpaque(false);
        lblAvatarPlayer4.setBorder(null);
        
        setLocationRelativeTo(null);
            
        setSize(850, 518);
            
    }
    public JLabel getlblAvatar1 (){
        return lblAvatarPlayer1;
    }
    public JLabel getlblAvatar2 (){
        return lblAvatarPlayer2;
    }
    public JLabel getlblAvatar3 (){
        return lblAvatarPlayer3;
    }
    public JLabel getlblAvatar4 (){
        return lblAvatarPlayer4;
    }
    
    public JLabel getlblTurnos (){
        return lblTurnos;
    }
    
    public void errorAlEliminarJugador(int indicador){
        
        if(indicador == 1)
            JOptionPane.showMessageDialog(this, "NO SE HA PODIDO ELIMINAR EL JUGADOR");
        
        else if(indicador == 2)
            JOptionPane.showMessageDialog(this, "SE HA ELIMINADO EL JUGADOR CON EXITO");
    }
    
    public void agregarBotonEliminarJugador(){
        
        JButton eliminarJugadores = new JButton();
        eliminarJugadores.setText("Eliminar");
        eliminarJugadores.setSize(101, 24);
        
        JButton iniciarPartida = new JButton();
        iniciarPartida.setText("Iniciar");
        iniciarPartida.setSize(101, 24);
        
        pnlBackGround.add(eliminarJugadores);
        pnlBackGround.add(iniciarPartida);
        
        eliminarJugadores.setLocation(480, 400);
        eliminarJugadores.setVisible(true);
        
        iniciarPartida.setLocation(480,430);
        iniciarPartida.setVisible(true);
        
        eliminarJugadores.addActionListener(new ActionListener(){
            
            public void actionPerformed(ActionEvent e){
                
                String respuesta = JOptionPane.showInputDialog ("Nombre del jugador a eliminar: ");
                
                try {
                    
                    System.out.println("ENTRA AL BOTON");
                    player.getWrite().writeInt(5);
                    player.getWrite().writeUTF(respuesta);
                    
                } catch (IOException ex) {
                    Logger.getLogger(RummikubWindow.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                
            }
            
        });
        
        iniciarPartida.addActionListener(new ActionListener(){
            
            public void actionPerformed(ActionEvent e){
                
                // Aqui el codigo para que la partida inicie
                // Se busca la referencia a la partida
                try{
                    System.out.println("Entra al boton de Iniciar partida");
                    
                    player.getWrite().writeInt(6);
                    
                } catch (IOException event){
                    System.out.println("Error al iniciar la partida");
                }
            }
            
        });
        
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
            case SPECIAL:
                tokenLabel.setForeground(Color.ORANGE);
            default:
                throw new AssertionError();
        }

        
        tokenLabel.setLocation(cordMazoX, cordMazoY);
        setCordMazoX(getCordMazoX()+30);
        pnlPlayerTokens.repaint();
        
        if (getCordMazoX() >= 370){
            
            setCordMazoX(10);
            setCordMazoY(getCordMazoY()+ 50);
        }
          
    }
    
    public void comenzarPartida (Partida partida){
        
        // Me parece que esta funcion queda inutilizada
        
        partida.setInProgres(true); //Cambiamos el estado de la partida
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
        
        partida.setTokens(tokensGame); //Los tokens se le referencian a la partida
        
        Collections.shuffle(tokensGame); //Le hacemos shuffle a los tokens
        
        for (int i = 0 ; i < partida.getCurrentPlayers() ; i++) { //Hacemos un for con la cantidad de jugadores de la partida
            for (int j = 0; j < 14; j++) {
                // Asegurarse de que hay suficientes fichas
                if (tokensGame.isEmpty()) {
                    throw new IllegalStateException("No hay suficientes fichas para todos los jugadores");
                }

                // "Gastar" una ficha y dársela al jugador
                Token ficha = tokensGame.remove(tokensGame.size() - 1);
                partida.getPlayers().get(i).getTokens().add(ficha); //Se le da al jugador "actual"
                //generarFicha(ficha); //Mejor esto se hara cada jugadors
            }
        }
        
        
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlBoard = new javax.swing.JPanel();
        pnlBackGround = new ImagePanel();
        lblAvatarPlayer1 = new javax.swing.JLabel();
        lblAvatarPlayer2 = new javax.swing.JLabel();
        lblAvatarPlayer3 = new javax.swing.JLabel();
        lblAvatarPlayer4 = new javax.swing.JLabel();
        pnlPlayerTokens = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txaChat = new javax.swing.JTextArea();
        txfChat = new javax.swing.JTextField();
        btnEnviar = new javax.swing.JButton();
        jPanel1 = new ImagePanel1();
        lblTurnos = new javax.swing.JLabel();

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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        pnlBackGround.setBackground(new java.awt.Color(204, 204, 204));

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
            .addGap(0, 110, Short.MAX_VALUE)
        );

        txaChat.setEditable(false);
        txaChat.setBackground(new java.awt.Color(255, 255, 255));
        txaChat.setColumns(20);
        txaChat.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        txaChat.setLineWrap(true);
        txaChat.setRows(5);
        jScrollPane1.setViewportView(txaChat);

        txfChat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txfChatActionPerformed(evt);
            }
        });

        btnEnviar.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        btnEnviar.setText("->");
        btnEnviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEnviarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 500, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        lblTurnos.setBackground(new java.awt.Color(255, 0, 51));
        lblTurnos.setFont(new java.awt.Font("MS PGothic", 1, 18)); // NOI18N
        lblTurnos.setForeground(new java.awt.Color(255, 0, 51));
        lblTurnos.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTurnos.setText("Turno del jugador #1");

        javax.swing.GroupLayout pnlBackGroundLayout = new javax.swing.GroupLayout(pnlBackGround);
        pnlBackGround.setLayout(pnlBackGroundLayout);
        pnlBackGroundLayout.setHorizontalGroup(
            pnlBackGroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBackGroundLayout.createSequentialGroup()
                .addGroup(pnlBackGroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlBackGroundLayout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(pnlBackGroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblAvatarPlayer3, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblAvatarPlayer2, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblAvatarPlayer1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblAvatarPlayer4, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26)
                        .addGroup(pnlBackGroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pnlPlayerTokens, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(50, 50, 50)
                        .addGroup(pnlBackGroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(pnlBackGroundLayout.createSequentialGroup()
                                .addComponent(txfChat, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnEnviar, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pnlBackGroundLayout.createSequentialGroup()
                        .addGap(268, 268, 268)
                        .addComponent(lblTurnos)))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        pnlBackGroundLayout.setVerticalGroup(
            pnlBackGroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlBackGroundLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTurnos)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlBackGroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlBackGroundLayout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnlBackGroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnEnviar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txfChat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(22, 22, 22))
                    .addGroup(pnlBackGroundLayout.createSequentialGroup()
                        .addGroup(pnlBackGroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlBackGroundLayout.createSequentialGroup()
                                .addComponent(lblAvatarPlayer2, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lblAvatarPlayer3, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(23, 23, 23)
                                .addComponent(lblAvatarPlayer1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lblAvatarPlayer4, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                                .addComponent(pnlPlayerTokens, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlBackGroundLayout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(14, 14, 14))))
        );

        getContentPane().add(pnlBackGround);
        pnlBackGround.setBounds(0, 0, 840, 480);

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
        
        try {
            
            // se toma lo escrito
            String Mensaje = String.valueOf(txfChat.getText());
            
            // se muestra en el text area
            txaChat.append(player.getUsername()+"> "+ Mensaje + "\n");
            
            // se limpia el textfield
            txfChat.setText("");

            // envia al server la opcion 4 para que le pase al enemigo lo escrito
            player.getWrite().writeInt(4);
            
            // le envia el mensaje
            player.getWrite().writeUTF(player.getUsername()+ "> " + Mensaje);
            
        } catch (IOException ex) {
            System.out.println("");
        }
        
    }//GEN-LAST:event_btnEnviarActionPerformed

    private void txfChatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txfChatActionPerformed

    }//GEN-LAST:event_txfChatActionPerformed
    
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
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblAvatarPlayer1;
    private javax.swing.JLabel lblAvatarPlayer2;
    private javax.swing.JLabel lblAvatarPlayer3;
    private javax.swing.JLabel lblAvatarPlayer4;
    private javax.swing.JLabel lblTurnos;
    private javax.swing.JPanel pnlBackGround;
    private javax.swing.JPanel pnlBoard;
    private javax.swing.JPanel pnlPlayerTokens;
    private javax.swing.JTextArea txaChat;
    private javax.swing.JTextField txfChat;
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
    
    class ImagePanel1 extends JPanel {
        
        private Image image;

        public ImagePanel1() {
            this.image = new ImageIcon("Tablero.jpg").getImage();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(image, 0, 0, this);
        }
    }
}
