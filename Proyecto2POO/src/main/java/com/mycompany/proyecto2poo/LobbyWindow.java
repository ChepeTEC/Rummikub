/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.proyecto2poo;

import com.sun.tools.javac.Main;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import servidor.threadServidorRummikub;


public class LobbyWindow extends javax.swing.JFrame {
    
    private threadServidorRummikub playerThreadServidor;
    private int cordX;
    private int cordY;
    
    private Player player;
    
    private ArrayList<JButton> Buttons = new ArrayList<JButton>();
    
    public LobbyWindow(Player player) {
        
        initComponents();
        setLocationRelativeTo(null);
        
        this.cordX = 10;
        this.cordY = 10;
        
        this.player = player;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlBackGround = new javax.swing.JPanel();
        btnRefreshGames = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        pnlLobbys = new javax.swing.JPanel();
        btnVolver = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pnlBackGround.setBackground(new java.awt.Color(76, 112, 255));

        btnRefreshGames.setFont(new java.awt.Font("Segoe UI Historic", 0, 14)); // NOI18N
        btnRefreshGames.setText("Refrescar partidas");
        btnRefreshGames.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshGamesActionPerformed(evt);
            }
        });

        jScrollPane2.setBackground(new java.awt.Color(102, 102, 102));
        jScrollPane2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        jScrollPane2.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        pnlLobbys.setBackground(new java.awt.Color(255, 255, 255));
        pnlLobbys.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        pnlLobbys.setPreferredSize(new java.awt.Dimension(1000, 1000));

        javax.swing.GroupLayout pnlLobbysLayout = new javax.swing.GroupLayout(pnlLobbys);
        pnlLobbys.setLayout(pnlLobbysLayout);
        pnlLobbysLayout.setHorizontalGroup(
            pnlLobbysLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 996, Short.MAX_VALUE)
        );
        pnlLobbysLayout.setVerticalGroup(
            pnlLobbysLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 996, Short.MAX_VALUE)
        );

        jScrollPane2.setViewportView(pnlLobbys);

        btnVolver.setFont(new java.awt.Font("Segoe UI Historic", 0, 14)); // NOI18N
        btnVolver.setText("Volver");
        btnVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVolverActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlBackGroundLayout = new javax.swing.GroupLayout(pnlBackGround);
        pnlBackGround.setLayout(pnlBackGroundLayout);
        pnlBackGroundLayout.setHorizontalGroup(
            pnlBackGroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBackGroundLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlBackGroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlBackGroundLayout.createSequentialGroup()
                        .addComponent(btnVolver, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 359, Short.MAX_VALUE)
                        .addComponent(btnRefreshGames, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        pnlBackGroundLayout.setVerticalGroup(
            pnlBackGroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBackGroundLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 386, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlBackGroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnRefreshGames, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnVolver, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlBackGround, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlBackGround, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void agregarPartidasDisponibles(ArrayList<PartidaSerializable> partidas){
        
        int cantidadDePlayers = 0;
        boolean inProgress = false;
        
        System.out.println("->>" + partidas.size());
        
        for (PartidaSerializable partida: partidas){
            
            cantidadDePlayers = partida.getAmountPlayerWanted();
            inProgress = partida.isInProgres();
            insertarPartida(cantidadDePlayers, inProgress, partida, partidas.indexOf(partida));
            
        }
        
    }
    
    private void btnRefreshGamesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshGamesActionPerformed
            
        pnlLobbys.removeAll();
        pnlLobbys.revalidate();
        pnlLobbys.repaint();
        setCordX(10);
        setCordY(10);
        
        try {
        
            player.getWrite().writeInt(1);
            
        } catch (Exception e) {
            
            System.out.println("ERROR AL UNIRSE A PARTIDA");
        
        }
        
    }//GEN-LAST:event_btnRefreshGamesActionPerformed
    
    private void btnVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVolverActionPerformed
        
        player.getRefMainWindow().setVisible(true);
        pnlLobbys.removeAll();
        pnlLobbys.revalidate();
        pnlLobbys.repaint();
        setCordX(10);
        setCordY(10);
        dispose();
        
    }//GEN-LAST:event_btnVolverActionPerformed
    
    public void insertarPartida(int cantidadDePlayers, boolean inPogress, PartidaSerializable partida, int index){
        
        JPanel panel = new JPanel();
        pnlLobbys.add(panel);
        panel.setLocation(cordX, cordY);
        panel.setSize(617, 50);
        
        JButton btnUnirse = new JButton();
        btnUnirse.setText("UNIRSE " + String.valueOf(index));
        btnUnirse.setFont(new Font ("Century Gothic", 0, 18));
        btnUnirse.setSize(20, 20);
        Buttons.add(btnUnirse);
        
        //Funcionalidad del boton "UNIRSE"
        
        btnUnirse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                final JButton buttonPressed = (JButton) e.getSource();
                
                try {
                    
                    int buttonIndex = findJButtonIndex(buttonPressed);
                    
                    player.getWrite().writeInt(3); //Primero mandamos la opcion que queremos
                    
                    player.getWrite().writeInt(buttonIndex);
                    
                    player.getWrite().writeUTF(player.getUsername()); //Mandamos el nombre del player que hace la accion
                    
                }catch (IOException a){
                    System.out.println("Ocurrió un error al intentar unirse a la partida deseada.");
                }
                
            }
        });
        
        // Crear e instanciar un BevelBorder
        BevelBorder bevelBorder = new BevelBorder(BevelBorder.RAISED); // Puedes usar LOWERED para un bisel hacia adentro

        // Establecer el borde en el JPanel
        panel.setBorder(bevelBorder);
        
        String gameInfo = "HOST: " + partida.getUsernameHost() + "      ";
        gameInfo += partida.getCurrentPlayers() + "/" + String.valueOf(cantidadDePlayers) + " JUGADORES";
        
        if(inPogress == false)
            gameInfo += "      NO INCIADO      ";
        
        else gameInfo += "      INICIADO      ";
        
        JLabel message = new JLabel ();
        message.setText(gameInfo);
        message.setFont (new Font ("Century Gothic", 0, 18));
        
        panel.add (message);
        panel.add (btnUnirse);
        
        panel.setVisible(true);
        pnlLobbys.repaint();
        
        setCordY(getCordY()+60);
    }
    
    public int findJButtonIndex(JButton button){
        
        for (int i = 0; i < Buttons.size(); i++){
            
            if (Buttons.get(i) == button)
                return i;
            
        }
        
        return -1;
    }
    
    public void agregarBoton(){
        
        JButton botonNuevo = new JButton();
        pnlLobbys.add(botonNuevo);
        botonNuevo.setLocation(cordX, cordY);
        botonNuevo.setSize(100, 100);
        botonNuevo.setVisible(true);
        setCordY(getCordY()+100);
        
    }
    
    // GETTERS AND SETTERS
    
    public int getCordX() {
        return cordX;
    }

    public void setCordX(int cordX) {
        this.cordX = cordX;
    }

    public int getCordY() {
        return cordY;
    }

    public void setCordY(int cordY) {
        this.cordY = cordY;
    }
    
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
            java.util.logging.Logger.getLogger(LobbyWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LobbyWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LobbyWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LobbyWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LobbyWindow(new Player()).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnRefreshGames;
    private javax.swing.JButton btnVolver;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel pnlBackGround;
    private javax.swing.JPanel pnlLobbys;
    // End of variables declaration//GEN-END:variables
}
