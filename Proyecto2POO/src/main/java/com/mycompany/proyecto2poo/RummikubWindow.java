/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.proyecto2poo;

import static com.mycompany.proyecto2poo.TokensTypes.Token.BLACK;
import static com.mycompany.proyecto2poo.TokensTypes.Token.BLUE;
import static com.mycompany.proyecto2poo.TokensTypes.Token.RED;
import static com.mycompany.proyecto2poo.TokensTypes.Token.SPECIAL;
import static com.mycompany.proyecto2poo.TokensTypes.Token.YELLOW;
import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.Component;
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
import javax.swing.border.BevelBorder;

public class RummikubWindow extends javax.swing.JFrame {
    
    private Player player;
    private int cordMazoX;
    private int cordMazoY;
    
    private int cordYToken;
    private int cordXToken;
    private int numCartaAJugar;
    private int color1; // 0: negro, 1: azul, 2: Rojo, 3: Amarillo, 4: Especial
    
    private int turnoJugador; //Puede ser 1,2,3,4
    private int numeroJugador; //Puede ser 1,2,3,4
    private int cantidadJugadores; //2,3,4
    
    private final ImageIcon avatarImagen1 = new ImageIcon("IconoAvatar1.gif");
    private final ImageIcon avatarImagen2 = new ImageIcon("IconoAvatar2.gif");
    private final ImageIcon avatarImagen3 = new ImageIcon("IconoAvatar3.gif");
    private final ImageIcon avatarImagen4 = new ImageIcon("IconoAvatar4.gif");    

    public RummikubWindow(Player player) {
        
        this.cordMazoX = 10;
        this.cordMazoY = 10;
        
        this.player = player;
        initComponents();
        
        turnoJugador = 1;
        
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
        
        this.numCartaAJugar = -1;
        this.color1 = -1;
        this.cordXToken = -1;
        this.cordMazoY = -1;
        
        btnComerFicha.setEnabled(false);
        
        setLocationRelativeTo(null);
            
        setSize(830, 518);
            
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
        pnlGame = new ImagePanel1();
        lblTurnos = new javax.swing.JLabel();
        btnComerFicha = new javax.swing.JButton();

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

        pnlGame.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlGameMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout pnlGameLayout = new javax.swing.GroupLayout(pnlGame);
        pnlGame.setLayout(pnlGameLayout);
        pnlGameLayout.setHorizontalGroup(
            pnlGameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 500, Short.MAX_VALUE)
        );
        pnlGameLayout.setVerticalGroup(
            pnlGameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        lblTurnos.setBackground(new java.awt.Color(255, 0, 51));
        lblTurnos.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        lblTurnos.setForeground(new java.awt.Color(255, 0, 51));
        lblTurnos.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTurnos.setText("Turno del Jugador : ");

        btnComerFicha.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        btnComerFicha.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(41, 43, 45)), "FICHAS", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));
        btnComerFicha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnComerFichaActionPerformed(evt);
            }
        });

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
                            .addComponent(pnlGame, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(pnlBackGroundLayout.createSequentialGroup()
                                .addComponent(pnlPlayerTokens, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnComerFicha, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(28, 28, 28)
                        .addGroup(pnlBackGroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(pnlBackGroundLayout.createSequentialGroup()
                                .addComponent(txfChat, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnEnviar, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pnlBackGroundLayout.createSequentialGroup()
                        .addGap(268, 268, 268)
                        .addComponent(lblTurnos)))
                .addContainerGap(41, Short.MAX_VALUE))
        );
        pnlBackGroundLayout.setVerticalGroup(
            pnlBackGroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlBackGroundLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTurnos)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlBackGroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlBackGroundLayout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addGap(16, 16, 16)
                        .addGroup(pnlBackGroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txfChat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnEnviar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(4, 4, 4))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlBackGroundLayout.createSequentialGroup()
                        .addGroup(pnlBackGroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(pnlBackGroundLayout.createSequentialGroup()
                                .addComponent(pnlGame, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE))
                            .addGroup(pnlBackGroundLayout.createSequentialGroup()
                                .addComponent(lblAvatarPlayer2, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(60, 60, 60)
                                .addComponent(lblAvatarPlayer3, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblAvatarPlayer1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(5, 5, 5)))
                        .addGroup(pnlBackGroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnComerFicha, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(pnlPlayerTokens, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblAvatarPlayer4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(14, 14, 14))
        );

        getContentPane().add(pnlBackGround);
        pnlBackGround.setBounds(0, 0, 840, 480);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void errorAlEliminarJugador(int indicador){
        
        if(indicador == 1)
            JOptionPane.showMessageDialog(this, "NO SE HA PODIDO ELIMINAR EL JUGADOR");
        
        else if(indicador == 2)
            JOptionPane.showMessageDialog(this, "SE HA ELIMINADO EL JUGADOR CON EXITO");
    }
    
    public void agregarBotonEliminarJugador(){
        
        JButton eliminarJugadores = new JButton();
        eliminarJugadores.setText("Eliminar");
        eliminarJugadores.setSize(75, 24);
        
        JButton iniciarPartida = new JButton();
        iniciarPartida.setText("Iniciar");
        iniciarPartida.setSize(75, 24);
        
        pnlBackGround.add(eliminarJugadores);
        pnlBackGround.add(iniciarPartida);
        
        eliminarJugadores.setLocation(535, 410);
        eliminarJugadores.setVisible(true);
        
        iniciarPartida.setLocation(535,440);
        iniciarPartida.setVisible(true);
        
        eliminarJugadores.addActionListener(new ActionListener(){
            
            public void actionPerformed(ActionEvent e){
                
                String respuesta = JOptionPane.showInputDialog ("Nombre del jugador a eliminar: ");
                
                try {
                   
                    player.getWrite().writeInt(5);
                    player.getWrite().writeUTF(respuesta);
                    
                } catch (IOException ex) {
                    Logger.getLogger(RummikubWindow.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                
            }
            
        });
        
        iniciarPartida.addActionListener(new ActionListener(){
            
            public void actionPerformed(ActionEvent e){
                
                JButton botonPresionado = (JButton) e.getSource();

                try{
                    
                    player.getWrite().writeInt(6);
                    
                    botonPresionado.setEnabled(false);
                    
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
        
        tokenLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                
                JLabel clickedLabel = (JLabel) e.getSource();
                
                setNumCartaAJugar(Integer.parseInt(clickedLabel.getText()));
                
                if(clickedLabel.getForeground() == Color.BLACK)
                    setColor(0);
                
                if(clickedLabel.getForeground() == Color.BLUE)
                    setColor(1);
                
                if(clickedLabel.getForeground() == Color.RED)
                    setColor(2);
                
                if(clickedLabel.getForeground() == Color.YELLOW)
                    setColor(3);
                
                if(clickedLabel.getForeground() == Color.ORANGE)
                    setColor(4);
                    
            }
        });
        
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
    
    public void marcar(){ //Recibe las corrdenadas
        //Se le cambia el turno del jugador
        turnoJugador = (turnoJugador % numeroJugador) + 1;
        lblTurnos.setText("Turno del Jugador #" + turnoJugador);
    }
    
    public void cambiarTurno (){
        turnoJugador = (turnoJugador % numeroJugador) + 1;
        lblTurnos.setText("Turno del Jugador #" + turnoJugador); //Se cambia el texto del indicador del turno
    }
    
    // ACTIONS 
    
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

    private void pnlGameMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlGameMouseClicked
        
        int x = evt.getX()/10;
        int y = evt.getY()/15;

        cordXToken = x*10;
        cordYToken = y*15;
        
        if (cordXToken != -1 && cordYToken != -1 && numCartaAJugar != -1 && color1 != -1){

            if(player.isMyTurn()){
                
                System.out.println("Entra al if");

                JLabel labelToken = new JLabel();

                labelToken.setSize(10,15);
                BevelBorder bevelBorder = new BevelBorder(BevelBorder.RAISED, Color.BLACK, Color.BLACK);
                labelToken.setBorder(bevelBorder);
                labelToken.setText(String.valueOf(numCartaAJugar));
                labelToken.setHorizontalAlignment(SwingConstants.CENTER);
                labelToken.setBackground(Color.gray); // Establece el fondo en negro
                labelToken.setOpaque(true);
                Font font = new Font("Lucida Sans", Font.BOLD, 8);
                labelToken.setFont(font);

                switch (color1) {
                    case 0:
                        labelToken.setForeground(Color.BLACK);
                        break;
                    case 1:
                        labelToken.setForeground(Color.BLUE);
                        break;
                    case 2:
                        labelToken.setForeground(Color.RED);
                        break;
                    case 3:
                        labelToken.setForeground(Color.YELLOW);
                        break;
                    case 4:
                        labelToken.setForeground(Color.ORANGE);
                        break;
                    default:
                        throw new AssertionError();
                }

                labelToken.setLocation(cordXToken, cordYToken);

                pnlGame.add(labelToken);
                pnlGame.repaint();

                try {

                        player.setMyTurn(false);
                        
                        player.getWrite().writeInt(7);
                        
                        // Mandar las coordenadas

                        player.getWrite().writeInt(cordXToken);
                        player.getWrite().writeInt(cordYToken);

                        // Mandar informacion de del token
                        
                        player.getWrite().writeInt(numCartaAJugar);
                        player.getWrite().writeInt(color1);


                    } catch (IOException ex) {
                        Logger.getLogger(RummikubWindow.class.getName()).log(Level.SEVERE, null, ex);
                    }
            }
            
            else JOptionPane.showMessageDialog(this, "ESPERA A TU TURNO","ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_pnlGameMouseClicked

    private void btnComerFichaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnComerFichaActionPerformed
        
        if(player.isMyTurn()){
        
            player.setMyTurn(false);

            try {
                player.getWrite().writeInt(8);
            } catch (IOException ex) {
                Logger.getLogger(RummikubWindow.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        else JOptionPane.showMessageDialog(this, "ESPERA A TU TURNO","ERROR", JOptionPane.ERROR_MESSAGE);
    }//GEN-LAST:event_btnComerFichaActionPerformed

    // GETTERS AND SETTERS
    
    public int getNumCartaAJugar() {
        return numCartaAJugar;
    }

    public void setNumCartaAJugar(int numCartaAJugar) {
        this.numCartaAJugar = numCartaAJugar;
    }

    public int getColor() {
        return color1;
    }

    public void setColor(int color) {
        this.color1 = color;
    }  

    public JPanel getPnlBoard() {
        return pnlBoard;
    }

    public void setPnlBoard(JPanel pnlBoard) {
        this.pnlBoard = pnlBoard;
    }
    
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

    public int getNumeroJugador() {
        return numeroJugador;
    }

    public void setNumeroJugador(int numeroJugador) {
        this.numeroJugador = numeroJugador;
    }

    public JPanel getPnlGame() {
        return pnlGame;
    }

    public void setPnlGame(JPanel pnlGame) {
        this.pnlGame = pnlGame;
    }

    public JPanel getPnlPlayerTokens() {
        return pnlPlayerTokens;
    }

    public void setPnlPlayerTokens(JPanel pnlPlayerTokens) {
        this.pnlPlayerTokens = pnlPlayerTokens;
    }

    public JButton getBtnComerFicha() {
        return btnComerFicha;
    }

    public void setBtnComerFicha(JButton btnComerFicha) {
        this.btnComerFicha = btnComerFicha;
    }

    public JLabel getLblTurnos() {
        return lblTurnos;
    }

    public void setLblTurnos(JLabel lblTurnos) {
        this.lblTurnos = lblTurnos;
    }
    
    // MAIN
    
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
    private javax.swing.JButton btnComerFicha;
    private javax.swing.JButton btnEnviar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblAvatarPlayer1;
    private javax.swing.JLabel lblAvatarPlayer2;
    private javax.swing.JLabel lblAvatarPlayer3;
    private javax.swing.JLabel lblAvatarPlayer4;
    private javax.swing.JLabel lblTurnos;
    private javax.swing.JPanel pnlBackGround;
    private javax.swing.JPanel pnlBoard;
    private javax.swing.JPanel pnlGame;
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
