/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.proyecto2poo;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class MainWindow extends javax.swing.JFrame {
    
    private Player player;

    public MainWindow(boolean flag) throws ClassNotFoundException {
        
        initComponents();
        setLocationRelativeTo(null);
        
        //MainWindow refMainWindow = new MainWindow(); Si le pasas este al player pasa lo de ayer
        //Si le pasas el this. no se peta
        
        if(flag == true){
            player = new Player(this); //No se le pasa la referencia a la ventana aun
            player.getConnected();
        }
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlBackground = new ImagePanel();
        btnCreate = new javax.swing.JButton();
        BtnUnirseAPartida = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnCreate.setBackground(new java.awt.Color(0, 0, 102));
        btnCreate.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        btnCreate.setForeground(new java.awt.Color(204, 0, 0));
        btnCreate.setText("CREAR PARTIDA");
        btnCreate.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnCreate.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnCreateMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnCreateMouseExited(evt);
            }
        });
        btnCreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateActionPerformed(evt);
            }
        });

        BtnUnirseAPartida.setBackground(new java.awt.Color(0, 0, 102));
        BtnUnirseAPartida.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        BtnUnirseAPartida.setForeground(new java.awt.Color(204, 0, 0));
        BtnUnirseAPartida.setText("UNIRSE A PARTIDA");
        BtnUnirseAPartida.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        BtnUnirseAPartida.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                BtnUnirseAPartidaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                BtnUnirseAPartidaMouseExited(evt);
            }
        });
        BtnUnirseAPartida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnUnirseAPartidaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlBackgroundLayout = new javax.swing.GroupLayout(pnlBackground);
        pnlBackground.setLayout(pnlBackgroundLayout);
        pnlBackgroundLayout.setHorizontalGroup(
            pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBackgroundLayout.createSequentialGroup()
                .addContainerGap(144, Short.MAX_VALUE)
                .addComponent(btnCreate, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(BtnUnirseAPartida, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(141, 141, 141))
        );
        pnlBackgroundLayout.setVerticalGroup(
            pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlBackgroundLayout.createSequentialGroup()
                .addContainerGap(281, Short.MAX_VALUE)
                .addGroup(pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BtnUnirseAPartida, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCreate, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(54, 54, 54))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlBackground, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlBackground, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    private void BtnUnirseAPartidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnUnirseAPartidaActionPerformed
        
        try {
        
            player.getWrite().writeInt(1);
            
        } catch (Exception e) {
            
            // Por que entra a este error?
            System.out.println("ERROR AL UNIRSE A PARTIDA");
        
        }
    }//GEN-LAST:event_BtnUnirseAPartidaActionPerformed

    private void btnCreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateActionPerformed
       
        String respuesta = JOptionPane.showInputDialog ("Por favor, introduce un número");
        
        try{
            int numero = Integer.parseInt(respuesta);
            if (numero > 4 || numero < 2){ //Validacion de datos
                JOptionPane.showMessageDialog(rootPane, "Los datos ingresados no son válidos", "ERROR", JOptionPane.ERROR_MESSAGE);
            }else{ //Union de la partida
                try{
                    player.getWrite().writeInt(2);
                    player.getWrite().writeInt (numero);
                } catch (Exception e){
                    System.out.println("ERROR AL CREAR LA PARTIDA");
                }
            }
        }catch (NumberFormatException e){ //Validacion de tipos de datos
            JOptionPane.showMessageDialog(rootPane, "Se ingreso un valor no númerico.", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnCreateActionPerformed

    private void btnCreateMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCreateMouseEntered
        btnCreate.setBackground(new Color(0,0,255));
    }//GEN-LAST:event_btnCreateMouseEntered

    private void BtnUnirseAPartidaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnUnirseAPartidaMouseEntered
        BtnUnirseAPartida.setBackground(new Color(0,0,255));
    }//GEN-LAST:event_BtnUnirseAPartidaMouseEntered

    private void btnCreateMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCreateMouseExited
        btnCreate.setBackground(new Color(0,0,102));
    }//GEN-LAST:event_btnCreateMouseExited

    private void BtnUnirseAPartidaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnUnirseAPartidaMouseExited
        BtnUnirseAPartida.setBackground(new Color(0,0,102));
    }//GEN-LAST:event_BtnUnirseAPartidaMouseExited

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
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MainWindow ventana = new MainWindow(true);
                    ventana.setVisible(true);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnUnirseAPartida;
    private javax.swing.JButton btnCreate;
    private javax.swing.JPanel pnlBackground;
    // End of variables declaration//GEN-END:variables
    
    class ImagePanel extends JPanel {
        
        private Image image;

        public ImagePanel() {
            this.image = new ImageIcon("ImagenPantallaInicio1.jpg").getImage();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(image, 0, 0, this);
        }
    }

}
