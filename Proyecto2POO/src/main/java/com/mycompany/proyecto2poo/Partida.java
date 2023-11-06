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
    private int amountPlayerWanted;
    private int currentPlayers;
    private String usernameHost;
    private ArrayList <Token> tokens;
  
    //Constructores

    public Partida(ArrayList<threadServidorRummikub> players, boolean inProgres) {
        
        this.players = players;
        this.inProgres = inProgres;
        this.currentPlayers = 0;
        this.tokens = new ArrayList <> ();

    }
    
    public Partida (boolean inProgress, String usernameHost, int amountWanted){
        
        this.usernameHost = usernameHost;
        this.inProgres = inProgress;
        this.players = new ArrayList <>();
        this.amountPlayerWanted = amountWanted;
        this.currentPlayers = 0;
        this.tokens = new ArrayList <> ();
        
    }

    // GETTERS AND SETTERS

    public ArrayList<Token> getTokens() {
        return tokens;
    }

    public void setTokens(ArrayList<Token> tokens) {
        this.tokens = tokens;
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
        return amountPlayerWanted;
    }

    public void setAmountPlayer(int amountPlayer) {
        this.amountPlayerWanted = amountPlayer;
    }

    public String getUsernameHost() {
        return usernameHost;
    }

    public void setUsernameHost(String usernameHost) {
        this.usernameHost = usernameHost;
    }

    public int getCurrentPlayers() {
        return currentPlayers;
    }

    public void setCurrentPlayers(int currentPlayers) {
        this.currentPlayers = currentPlayers;
    }
    
}
