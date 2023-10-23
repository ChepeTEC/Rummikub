/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto2poo;

import java.io.DataInputStream;
import java.io.IOException;

/**
 *
 * @author Pablo
 */
public class ThreadPlayer extends Thread{
    
    private DataInputStream read; // Para hacer lectura de lo que comunica el server
    private RummikubWindow playerWindow; // Referencia a la ventana y al jugador de esa ventana
    private boolean isRunning;
    
    public ThreadPlayer(DataInputStream read, RummikubWindow playerWindow){
        
        this.read = read;
        this.playerWindow = playerWindow;
        this.isRunning = true;
    }

    public void run(){
        
        // Hacer algo antes del while true
        
        int opcion = 0;
        
        while(isRunning){
           
            try{
                
                System.out.println("ENTRA AL TRY");
                String mensaje = "";
                opcion = read.readInt();
                
                switch(opcion){
                    case 1: // Funcionalidad 1
                        
                        // ENVIAR MENSAJE
                        
                        break;
                        
                    case 2: // Funcionalidad 2
                        
                        break;
                    
                    case 3: // Funcionalidad 3
                        
                        break;
                        
                    case 4:
                        
                        System.out.println("ENTRA AL CASE xD");
                        
                        mensaje = read.readUTF();
                        
                        playerWindow.mostrar(mensaje);
                        
                        System.out.println("VA SALIENDO");
                        
                        break;
                }
            }
            catch(IOException e){
                  
                
            }
            
        }
    }    
}
