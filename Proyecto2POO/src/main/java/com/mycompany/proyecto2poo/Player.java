/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto2poo;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import servidor.JFrameServer;
import servidor.ServerRummikub;
import servidor.threadServidorRummikub;

public class Player implements Serializable{
    
    // Atributos 
    
    private RummikubWindow refVentana; // Referencia a la ventana de juego 
    private LobbyWindow refLobby;
    private MainWindow refMainWindow;
    
    public static String IP_SERVER = "localhost"; // IP del Servidor
    
    private Socket player = null; // para la comunicacion
    private boolean isHost; // True ->> es host : False ->> no es host
    private boolean myTurn;
    
    private DataInputStream read = null; // leer comunicacion
    private DataOutputStream write = null; // escribir comunicacion
    
    private String username; // Username del jugador
    private threadServidorRummikub threadServidorPlayer;
    private ArrayList <Token> tokensPlayer;
    
    int cantidadDePartidasJugadas;
    int cantidadDePartidasGanadas;
   
    // Atributo de prueba; 
    
    private ArrayList<Partida> partidas;
    
    // Builders
    
    public Player(RummikubWindow refVentana) throws ClassNotFoundException{
        
        this.refVentana = refVentana;
        this.refLobby = new LobbyWindow(this);
        this.refMainWindow = new MainWindow(true);
        this.threadServidorPlayer = null;
        this.tokensPlayer = new ArrayList <> ();
        this.isHost = false;
        this.myTurn = false;
        
    }
    
    public Player (MainWindow refMainWindow) throws ClassNotFoundException{
        
        this.refLobby = new LobbyWindow(this);
        this.refMainWindow = refMainWindow;
        this.threadServidorPlayer = null;
        this.tokensPlayer = new ArrayList <> ();
        this.isHost = false;
        this.myTurn = false;
    }
    
    public Player(){
        
    }
    
    // METODOS
    
    public void getConnected() {
        try {
            // Inicializa el socket
            player = new Socket(getIP_SERVER(), 8081);

            // Inicializa las corrientes de entrada y salida
            read = new DataInputStream(player.getInputStream());
            write = new DataOutputStream(player.getOutputStream());

            // Solicita el nombre de usuario           
            setUsername(JOptionPane.showInputDialog("Introduzca su nombre de usuario:"));

            // Envia el nombre de usuario al servidor
            write.writeUTF(username);
            
            // Inicia un hilo para manejar la comunicación con el servidor
            new ThreadPlayer(read, this).start();
            
        } catch (IOException e) {
            System.out.println("Error de E/S: " + e.getMessage());
            System.out.println("El servidor no está levantado o hay un problema de conexión.");
        }
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

    public LobbyWindow getRefLobby() {
        return refLobby;
    }

    public void setRefLobby(LobbyWindow refLobby) {
        this.refLobby = refLobby;
    }
    
    public ArrayList<Partida> getPartidas() {
        return partidas;
    }

    public void setPartidas(ArrayList<Partida> partidas) {
        this.partidas = partidas;
    }

    public MainWindow getRefMainWindow() {
        return refMainWindow;
    }

    public void setRefMainWindow(MainWindow refMainWindow) {
        this.refMainWindow = refMainWindow;
    }

    public threadServidorRummikub getThreadServidorPlayer() {
        return threadServidorPlayer;
    }

    public void setThreadServidorPlayer(threadServidorRummikub threadServidorPlayer) {
        this.threadServidorPlayer = threadServidorPlayer;
    }

    public boolean isIsHost() {
        return isHost;
    }

    public void setIsHost(boolean isHost) {
        this.isHost = isHost;
    }

    public boolean isMyTurn() {
        return myTurn;
    }

    public void setMyTurn(boolean myTurn) {
        this.myTurn = myTurn;
    }
    
}
