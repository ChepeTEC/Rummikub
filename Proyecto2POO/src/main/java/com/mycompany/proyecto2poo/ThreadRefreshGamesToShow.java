/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto2poo;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Pablo
 */

// CLASE TOTALMENTE SIN USO POR EL MOMENTO

public class ThreadRefreshGamesToShow extends Thread{
    
    private LobbyWindow refLobby;
    private boolean isRunning;
    private boolean isPaused;
    
    public ThreadRefreshGamesToShow (LobbyWindow refLobby){ 
     
        this.refLobby = refLobby;
        this.isRunning = true;
        this.isPaused = false;
        
    }
    
    public void run() {
        
        while (isRunning) {
            
            while(isPaused){
                try {
                    
                    Thread.sleep(100);
                
                } catch (InterruptedException ex) {
                    Logger.getLogger(ThreadRefreshGamesToShow.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            try {
             
                refLobby.refreshGamesToShow();
                Thread.sleep(2000);
                
            } catch (InterruptedException ex) {
                Logger.getLogger(ThreadRefreshGamesToShow.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public boolean isIsRunning() {
        return isRunning;
    }

    public void setIsRunning(boolean isRunning) {
        this.isRunning = isRunning;
    }

    public boolean isIsPaused() {
        return isPaused;
    }

    public void setIsPaused(boolean isPaused) {
        this.isPaused = isPaused;
    }

}
