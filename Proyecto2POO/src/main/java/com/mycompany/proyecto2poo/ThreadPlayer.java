/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto2poo;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import servidor.threadServidorRummikub;

/**
 *
 * @author Pablo
 */
public class ThreadPlayer extends Thread{
    
    private DataInputStream read; // Para hacer lectura de lo que comunica el server
    private boolean isRunning;
    private Player player;
    
    public ThreadPlayer(DataInputStream read, Player player){
        
        this.read = read;
        this.player = player;
        this.isRunning = true;
        
    }

    public void run(){
        
        // Hacer algo antes del while true
        
        System.out.println("START");
        
        int opcion = 0;
        
        while(isRunning){
           
            try{
                
                String mensaje = "";
                ArrayList <Partida> gamesToShow = new ArrayList <Partida> ();
                
                threadServidorRummikub playerThreadServidor = new threadServidorRummikub ();
                
                System.out.println("ANTES DE LEER OPCION");
                
                opcion = read.readInt();
                
                System.out.println("LEE OPCION");
                
                switch(opcion){
                    case 1: // Funcionalidad 1: Mostrar partidas disponibles
                        
                        ObjectInputStream in = new ObjectInputStream(read);
                        gamesToShow = ((ArrayList<Partida>) in.readObject());
                        
                        player.getRefLobby().agregarPartidasDisponibles(gamesToShow);
                        player.getRefLobby().setVisible(true);
                        player.getRefMainWindow().setVisible(false);
                        
                        break;
                        
                    case 2: // Funcionalidad 2: Crear partida
                        
                        break;
                    
                    case 3: // Funcionalidad 3
                        ObjectInputStream in3 = new ObjectInputStream (read);
                        playerThreadServidor = ((threadServidorRummikub) in3.readObject());
                        player.setThreadServidorPlayer(playerThreadServidor);            
                        
                    case 4: // Funcionalidad 3: Interaccion del chat
                        
                        mensaje = read.readUTF();
                        
                        player.getRefVentana().mostrar(mensaje);
                        
                        break;
                }
            }
            catch (ClassNotFoundException ex) {
                Logger.getLogger(ThreadPlayer.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("ERROR 1");
            }
            catch(IOException e){
                  System.out.println("ERROR 2");   
            } 
            
        }
    }
    
    // GETTER & SETTERS
    
    
}
