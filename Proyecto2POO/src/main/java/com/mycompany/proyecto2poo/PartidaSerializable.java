/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto2poo;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Pablo
 */
public class PartidaSerializable implements Serializable{
    
    private boolean inProgres;
    private int amountPlayerWanted;
    private int currentPlayers;
    private String usernameHost;  
  
    public PartidaSerializable (boolean inProgress, String usernameHost, int amountWanted){
        
        this.usernameHost = usernameHost;
        this.inProgres = inProgress;
        this.amountPlayerWanted = amountWanted;
        this.currentPlayers = 0;
        
    }

    // GETTERS AND SETTERS
    
    public boolean isInProgres() {
        return inProgres;
    }

    public void setInProgres(boolean inProgres) {
        this.inProgres = inProgres;
    }

    public int getAmountPlayerWanted() {
        return amountPlayerWanted;
    }

    public void setAmountPlayerWanted(int amountPlayerWanted) {
        this.amountPlayerWanted = amountPlayerWanted;
    }

    public int getCurrentPlayers() {
        return currentPlayers;
    }

    public void setCurrentPlayers(int currentPlayers) {
        this.currentPlayers = currentPlayers;
    }

    public String getUsernameHost() {
        return usernameHost;
    }

    public void setUsernameHost(String usernameHost) {
        this.usernameHost = usernameHost;
    }
    
}
