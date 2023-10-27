/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto2poo;

import java.util.ArrayList;
import servidor.threadServidorRummikub;

/**
 *
 * @author Jose
 */
public class Partida {
    
    //Atributos
    
    private Player admin;
    private ArrayList <threadServidorRummikub> players;
    private boolean inProgres;
    private int amountPlayer;
  
    //Constructores

    public Partida(Player admin, ArrayList<threadServidorRummikub> players, boolean inProgres, int amountPlayer) {
        this.admin = admin;
        this.players = players;
        this.inProgres = inProgres;
        this.amountPlayer = amountPlayer;
    }

    public Partida(ArrayList<threadServidorRummikub> players, boolean inProgres, int amountPlayer) {
        this.players = players;
        this.inProgres = inProgres;
        this.amountPlayer = amountPlayer;
    }

    public Player getAdmin() {
        return admin;
    }

    public void setAdmin(Player admin) {
        this.admin = admin;
    }

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
