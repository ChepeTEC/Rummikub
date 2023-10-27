/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto2poo;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import javax.swing.JOptionPane;
import servidor.JFrameServer;

/**
 *
 * @author Pablo
 */
public class Player {
    
    // Atributos 
    
    private RummikubWindow refVentana; // Referencia a la ventana de juego 
    public static String IP_SERVER = "localhost"; // IP del Servidor
    private DataInputStream read = null; // leer comunicacion
    private DataOutputStream write = null; // escribir comunicacion
    private Socket player = null; // para la comunicacion
    private String username; //
    private boolean isHost; //
    
    // Atributo de prueba; 
    
    private JFrameServer server;
    
    // Constructor
    
    public Player(RummikubWindow refVentana){
        
        this.refVentana = refVentana;
        
    }
    
    // METODOS
    
    public void getConnected() throws IOException{
        
        try{
            
            // Initialize Socket
            player = new Socket(getIP_SERVER(), 8081);
            
            // Initiliaze read and write  
            read = new DataInputStream(player.getInputStream());
            write = new DataOutputStream(player.getOutputStream());
            
            // Request for username
            setUsername(JOptionPane.showInputDialog("Introduzca su nombre de usuario:"));
            
            // Send username to server
            
            write.writeUTF(username);
        }
        catch(IOException e){
            
            System.out.println("\tEl servidor no esta levantado");
            System.out.println("\t=============================");
        }
        
        new ThreadPlayer(read,refVentana).start();
    }
    
    // Getters and setters

    public RummikubWindow getRefVentana() {
        return refVentana;
    }

    public void setRefVentana(RummikubWindow refVentana) {
        this.refVentana = refVentana;
    }

    public static String getIP_SERVER() {
        return IP_SERVER;
    }

    public static void setIP_SERVER(String IP_SERVER) {
        Player.IP_SERVER = IP_SERVER;
    }

    public DataInputStream getRead() {
        return read;
    }

    public void setRead(DataInputStream in) {
        this.read = in;
    }

    public DataOutputStream getWrite() {
        return write;
    }

    public void setWrite(DataOutputStream out) {
        this.write = out;
    }

    public Socket getPlayer() {
        return player;
    }

    public void setPlayer(Socket player) {
        this.player = player;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }    
    
}
