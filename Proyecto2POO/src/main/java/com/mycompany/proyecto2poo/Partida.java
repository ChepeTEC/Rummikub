/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto2poo;

import java.io.Serializable;
import java.util.ArrayList;
import servidor.threadServidorRummikub;

public class Partida implements Serializable{
    
    //Atributos
    
    private ArrayList <threadServidorRummikub> players;
    private boolean inProgres;
    private int amountPlayer;
  
    //Constructores

    public Partida(ArrayList<threadServidorRummikub> players, boolean inProgres) {
        
        this.players = players;
        this.inProgres = inProgres;

    }

    // GETTERS AND SETTERS
    
    public ArrayList<threadServidorRummikub> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<threadServidorRummikub> players) {
        this.players = players;
    }

    public boolean isInProgres() {
        return inProgres;
    }

    public void setInProgres(boolean inProgres) {
        this.inProgres = inProgres;
    }

    public int getAmountPlayer() {
        return amountPlayer;
    }

    public void setAmountPlayer(int amountPlayer) {
        this.amountPlayer = amountPlayer;
    }
     
}
