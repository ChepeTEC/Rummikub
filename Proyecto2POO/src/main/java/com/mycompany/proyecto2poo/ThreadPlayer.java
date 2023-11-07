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

public class ThreadPlayer extends Thread{
    
    private DataInputStream read; // Para hacer lectura de lo que comunica el server
    private boolean isRunning;
    private Player player;
    
    // CONSTRUCTOR
    
    public ThreadPlayer(DataInputStream read, Player player){
        
        this.read = read;
        this.player = player;
        this.isRunning = true;
        
    }

    // METODO RUN();
    
    public void run(){
        
        // Hacer algo antes del while true
        
        int opcion = 0;
        
        while(isRunning){
           
            try{
                
                String mensaje = "";
                
                ArrayList <PartidaSerializable> gamesToShow = new ArrayList <PartidaSerializable> ();
                threadServidorRummikub playerThreadServidor = new threadServidorRummikub ();
                
                opcion = read.readInt();
                
                switch(opcion){
                    
                    case 1: // Funcionalidad 1: Mostrar partidas disponibles
                        {
                        ObjectInputStream in = new ObjectInputStream(read);
                        gamesToShow = ((ArrayList<PartidaSerializable>) in.readObject());
                        
                        player.getRefLobby().agregarPartidasDisponibles(gamesToShow);
                        player.getRefLobby().setVisible(true);
                        player.getRefMainWindow().setVisible(false);
                        
                        // Inicio del thread 
                        
                        //player.getRefLobby().getRefreshInfo().start();
                        }
                        break;
                        
                    case 2: // Funcionalidad 2: Crear partida
                        
                        break;
                    
                    case 3: // Funcionalidad 3 : ???
                        
                        
                    case 4: // Funcionalidad 4: Interaccion del chat
                        
                        mensaje = read.readUTF();
                        
                        player.getRefVentana().mostrar(mensaje);
                        
                        break;
                        
                    case 5: // Funcionalidad 5: Unirse a una partida
                        
                        player.setRefVentana(new RummikubWindow(player));
                        player.getRefVentana().setVisible(true);
                        player.getRefLobby().setVisible(false);
                        
                        if(player.isIsHost()){
                            
                            player.getRefVentana().agregarBotonEliminarJugador();
                            
                        }
                        
                        break;
                        
                    case 6: // Funcionalidad 6: Desginar al player como host de la partida
                        
                        this.player.setIsHost(true);
                        
                        break;
                        
                    case 7: // Funcionalidad 7: Manejar la eliminacion de jugadores y sus posibles errores
                        
                        int situacion = player.getRead().readInt();
                        
                        switch (situacion) {
                            
                            case 1:
                                
                                // No se encontro el jugador que se deseaba eliminar
                                System.out.println("NO SE PUDO");
                                player.getRefVentana().errorAlEliminarJugador(1);
                                
                                break;
                                
                            case 2:
                                
                                // Se elimino el jugador con exito
                                System.out.println("SE PUDO");
                                player.getRefVentana().errorAlEliminarJugador(2);
                                
                                break;
                                
                            default:
                                throw new AssertionError();
                                
                        }
                        
                        break;
                        
                    case 8: // Funcionalidad: Has sido eliminado de la partida
                        
                        player.getRefVentana().setVisible(false);
                        player.getRefMainWindow().setVisible(true);
                        
                        player.getRefLobby().getButtons().clear();
                        player.getRefLobby().getPnlLobbys().removeAll();
                        player.getRefLobby().getPnlLobbys().revalidate();
                        player.getRefLobby().getPnlLobbys().repaint();
                        player.getRefLobby().setCordX(10);
                        player.getRefLobby().setCordY(10);
                        
                        break;
                        
                    case 9: 
                    {
                        System.out.println("1");
                        
                        ObjectInputStream a = new ObjectInputStream(read);
                        
                        System.out.println("2");
                        
                        ArrayList<Token> playerTokens = ((ArrayList<Token>) a.readObject());
                        
                        System.out.println("3");
                        
                        for(int i = 0; i < playerTokens.size(); i++){
                            
                            player.getRefVentana().generarFicha(playerTokens.get(i));
                            
                        }
                    }    
                        break;
                }
            }
            catch (ClassNotFoundException ex) {
                Logger.getLogger(ThreadPlayer.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("ERROR 1");
            }
            catch(IOException e){
                  Logger.getLogger(ThreadPlayer.class.getName()).log(Level.SEVERE, null, e);
                  System.out.println("ERROR 2");   
            } 
            
        }
    }
    
    // GETTER & SETTERS
    
    
}
