package servidor;

public class JFrameServidor extends javax.swing.JFrame {
 
     ServidorRummikub servidor;
     /** Creates new form JFrameServidor */
     public JFrameServidor() {
         initComponents();
         //servidor = new ServidorRummikub(this);
     }
 
     @SuppressWarnings("unchecked")
     // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
     private void initComponents() {
 
         jScrollPane1 = new javax.swing.JScrollPane();
         txaMensajesServidor = new javax.swing.JTextArea();
 
         setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
 
         txaMensajesServidor.setColumns(20);
         txaMensajesServidor.setRows(5);
         txaMensajesServidor.setEnabled(false);
         jScrollPane1.setViewportView(txaMensajesServidor);
 
         javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
         getContentPane().setLayout(layout);
         layout.setHorizontalGroup(
             layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
             .addGroup(layout.createSequentialGroup()
                 .addContainerGap()
                 .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
                 .addContainerGap())
         );
         layout.setVerticalGroup(
             layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
             .addGroup(layout.createSequentialGroup()
                 .addContainerGap()
                 .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 362, Short.MAX_VALUE)
                 .addContainerGap())
         );
 
         pack();
     }// </editor-fold>//GEN-END:initComponents
 
     public static void main(String args[]) {
         JFrameServidor serv =new JFrameServidor();
         serv.setVisible(true);
         //serv.servidor.runServer();
         
             
     }
     
     public void mostrar (String texto)
     {
         txaMensajesServidor.append(texto+"\n");
     }
 
     // Variables declaration - do not modify//GEN-BEGIN:variables
     private javax.swing.JScrollPane jScrollPane1;
     private javax.swing.JTextArea txaMensajesServidor;
     // End of variables declaration//GEN-END:variables
 
 }