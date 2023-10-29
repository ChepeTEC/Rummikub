/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.proyecto2poo;

import java.awt.Font;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.border.BevelBorder;
import servidor.threadServidorRummikub;

/**
 *
 * @author todom
 */
public class LobbyWindow extends javax.swing.JFrame {
    
    private threadServidorRummikub playerThreadServidor;
    private int cordX;
    private int cordY;
    
    public LobbyWindow() {
        initComponents();
        setLocationRelativeTo(null);
        this.cordX = 10;
        this.cordY = 10; 
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlBackGround = new javax.swing.JPanel();
        btnPrueba = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        pnlLobbys = new javax.swing.JPanel();
        btnVolver = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pnlBackGround.setBackground(new java.awt.Color(76, 112, 255));

        btnPrueba.setFont(new java.awt.Font("Segoe UI Historic", 0, 14)); // NOI18N
        btnPrueba.setText("Refrescar partidas");
        btnPrueba.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPruebaActionPerformed(evt);
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
                        .addComponent(btnPrueba, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        pnlBackGroundLayout.setVerticalGroup(
            pnlBackGroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBackGroundLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 386, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlBackGroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnPrueba, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
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

    public void agregarPartidasDisponibles(ArrayList<Partida> partidas){
        
        int cantidadDePlayers = 0;
        boolean inProgress = false;
        
        for (Partida partida: partidas){
            
            cantidadDePlayers = partida.getAmountPlayer();
            inProgress = partida.isInProgres();
            insertarPartida(cantidadDePlayers, inProgress);
            
        }
        
    }
    
    private void btnPruebaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPruebaActionPerformed
        
//        ArrayList <Partida> currentGames = playerThreadServidor.getGamesToShow();
//        
//        for (int i = 0; i < currentGames.size(); i++) {
//            
//            Partida gameRecibido = currentGames.get(i);
//            agregarBoton(); // Aqui me parece que el método a llamar es insertarPartida()
//            
//        }

          insertarPartida(2, true);
    }//GEN-LAST:event_btnPruebaActionPerformed
    
    private void btnVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVolverActionPerformed
        
        MainWindow ventana = null;
        try {
            ventana = new MainWindow();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(LobbyWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
        ventana.setVisible(true);
        dispose();
    }//GEN-LAST:event_btnVolverActionPerformed
    
    public void insertarPartida(int cantidadDePlayers, boolean inPogress){
        
        JPanel panel = new JPanel();
        pnlLobbys.add(panel);
        panel.setLocation(cordX, cordY);
        panel.setSize(617, 50);
        
        // Falta crear el boton para unirse a la partida
        
        // Crear e instanciar un BevelBorder
        BevelBorder bevelBorder = new BevelBorder(BevelBorder.RAISED); // Puedes usar LOWERED para un bisel hacia adentro

        // Establecer el borde en el JPanel
        panel.setBorder(bevelBorder);
        
        String gameInfo = String.valueOf(cantidadDePlayers) + "/4\t\t\t\t";
        
        if(inPogress == false)
            gameInfo += "No iniciado";
        
        else gameInfo += "Iniciado";
        
        JLabel message = new JLabel ();
        message.setText(gameInfo);
        message.setFont (new Font ("Segoe UI Historic", 0, 20));
        
        panel.add (message);
        
        panel.setVisible(true);
        pnlLobbys.repaint();
        
        setCordY(getCordY()+60);
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
                new LobbyWindow().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnPrueba;
    private javax.swing.JButton btnVolver;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel pnlBackGround;
    private javax.swing.JPanel pnlLobbys;
    // End of variables declaration//GEN-END:variables
}
