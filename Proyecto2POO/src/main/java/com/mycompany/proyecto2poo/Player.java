/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto2poo;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

/**
 *
 * @author Pablo
 */
public class Player {
    
    // Atributos 
    
    private JuegoRummikub refVentana; // Referencia a la ventana de juego 
    public static String IP_SERVER = "localhost"; //IP del Servidor
    private DataInputStream in = null; //leer comunicacion
    private DataOutputStream out = null; //escribir comunicacion
    private Socket player = null; //para la comunicacion
    private String username;
    
    // Constructor
    
    public Player(JuegoRummikub refVentana){
        
        this.refVentana = refVentana;
        
    }
    
    // METODOS
    
    
    
    // Getters and setters

    public JuegoRummikub getRefVentana() {
        return refVentana;
    }

    public void setRefVentana(JuegoRummikub refVentana) {
        this.refVentana = refVentana;
    }

    public static String getIP_SERVER() {
        return IP_SERVER;
    }

    public static void setIP_SERVER(String IP_SERVER) {
        Player.IP_SERVER = IP_SERVER;
    }

    public DataInputStream getIn() {
        return in;
    }

    public void setIn(DataInputStream in) {
        this.in = in;
    }

    public DataOutputStream getOut() {
        return out;
    }

    public void setOut(DataOutputStream out) {
        this.out = out;
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
