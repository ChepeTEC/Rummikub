/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package servidor;

import com.mycompany.proyecto2poo.LobbyWindow;
import com.mycompany.proyecto2poo.ThreadLeerPartidasActivas;
import javax.swing.JTextArea;

/**
 *
 * @author Jose
 */
public class JFrameServer extends javax.swing.JFrame {
    
    private ServerRummikub servidor;
    //private ThreadLeerPartidasActivas threadLeerPartidas;
    //private LobbyWindow lobby;

    public JFrameServer() {
        
        initComponents();
        setLocationRelativeTo(null);
        
        // CREACION DE LOS ATRIBUTOS 
        
        //this.lobby = new LobbyWindow();
        this.servidor = new ServerRummikub(this);
        
        // Este puede quedar inutilizado
        //this.threadLeerPartidas = new ThreadLeerPartidasActivas(this, this.lobby);
        
        // PONER A CORRER AL THREAD
        //this.threadLeerPartidas.start();

    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        txtAreaMensajes = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        txtAreaMensajes.setEditable(false);
        txtAreaMensajes.setBackground(new java.awt.Color(242, 255, 255));
        txtAreaMensajes.setColumns(20);
        txtAreaMensajes.setFont(new java.awt.Font("Segoe UI Historic", 0, 14)); // NOI18N
        txtAreaMensajes.setRows(5);
        txtAreaMensajes.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        jScrollPane1.setViewportView(txtAreaMensajes);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 317, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(JFrameServer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFrameServer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFrameServer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFrameServer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                
                JFrameServer serv = new JFrameServer();
                serv.setVisible(true);
                Thread serverThread = new Thread(() -> {
                    serv.servidor.runServer();
                });
                serverThread.start();
                    
            }
        });
    }
    
    public void mostrar (String texto){
        txtAreaMensajes.append (texto+"\n");
        this.repaint();
    }

    public JTextArea getTxtAreaMensajes() {
        return txtAreaMensajes;
    }

    public void setTxtAreaMensajes(JTextArea txtAreaMensajes) {
        this.txtAreaMensajes = txtAreaMensajes;
    }
    
    public ServerRummikub getServer (){
        return servidor;
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea txtAreaMensajes;
    // End of variables declaration//GEN-END:variables
}
