/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto2poo;

import servidor.JFrameServer;

/**
 *
 * @author todom
 */
public class ThreadLeerPartidasActivas extends Thread{
    
    private JFrameServer frame;
    private LobbyWindow frameLobby;
    private boolean finish;
    private boolean paused;
    
    /*
    
    Este thread debe de estar leyendo constantemente las partidas que se encuentren en el arraylist
    de partidas que contiene el servidor. Tambien este debe de tener una referencia hacia el server 
    ya que este es el que contiene el arraylist de partidas.
    
    La ventana LobbyWindow es la que tiene este Thread por lo que cada vez que el thread detecta que
    agrego una partida o se creo mejor dicho este se lo indica a la ventana para que haga el display 
    pantalla.
    
    PREGUNTAS PENDIENTES:
    
    - Cual clase o frame es la que contiene el arraylist de partidas activas en ese momento? 
    Si utilizas el JFrameServidor tenes acceso al frame del servidor y al servidor como tal, por lo que es util si queres mandar mensajes al textArea.
    Por lo menos el profe eso es lo que hace de hecho, por eso en este caso lo puse como atributo
    
    - Para una mejor comunicacion es posible mandar comunicar el server y el la pantalla de
    lobbys con sockets?
    Digamos realmente, el threadServidoRummikub como te has dado cuenta, este lo tiene cada jugador, por lo que cada jugador podrá mandar y recibir información
    Ahora si quieres comunicar el Lobby con el Server, creo yo que lo mejor seria utilizar el JFrameServer ya que podriamos utilizarlo de una vez para mandar mensajes y asi
    
    */
    
    public ThreadLeerPartidasActivas(JFrameServer frame, LobbyWindow frameLobby){
        
        this.frame = frame;
        this.frameLobby = frameLobby;
        this.finish = true;
        this.paused = false;
    }
    
    @Override   
    
    public void run(){
        
        while (finish){
            
            while (paused){
                
                try {
                    Thread.sleep(1000); // Pausa de 1 segundo
                } catch (InterruptedException e) {
                    System.out.println("Ocurrió un error al pausar el creador de labels del lobby");
                }
                
            }
            
            if (!frame.getServer().getPartidas().isEmpty()){ //Si hay partidas en el server
                
                Partida partida = frame.getServer().getPartidas().get(0);
                
                // Para partidas que estan en progreso
                
                if (partida.isInProgres()){
                    
                    String mostrar = "Admin: " + partida.getAdmin() + "/t" + "Num. Jugadores: " + partida.getPlayers().size() + "/4" + "/t" + "Estado Partida: " + "Activa";
                    frameLobby.insertarPartida(mostrar);
                    
                }
                
                // Para partidas que no han empezado
                
                else{
        
                    String mostrar = "Admin: " + partida.getAdmin() + "/t" + "Num. Jugadores: " + partida.getPlayers().size() + "/4" + "/t" + "Estado Partida: " + "Inactiva";
                    frameLobby.insertarPartida(mostrar);
                    
                }
                
            }   
        }
    }
        
}
