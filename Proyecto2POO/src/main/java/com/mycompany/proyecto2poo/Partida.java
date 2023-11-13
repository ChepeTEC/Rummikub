/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto2poo;

import java.io.Serializable;
import java.util.ArrayList;
import servidor.threadServidorRummikub;
import java.util.Collections;

public class Partida implements Serializable{
    
    //Atributos
    
    private ArrayList <threadServidorRummikub> players;
    
    private boolean inProgres;
    private int amountPlayerWanted;
    private int currentPlayers;
    private String usernameHost;
    private ArrayList <Token> tokens;
    private int turno;
  
    //Constructores

    public Partida(ArrayList<threadServidorRummikub> players, boolean inProgres) {
        
        this.players = players;
        this.inProgres = inProgres;
        this.currentPlayers = 0;
        this.tokens = new ArrayList <Token> ();
        this.turno = 0;
        inicializarMazo();

    }
    
    public Partida (boolean inProgress, String usernameHost, int amountWanted){
        
        this.usernameHost = usernameHost;
        this.inProgres = inProgress;
        this.players = new ArrayList <>();
        this.amountPlayerWanted = amountWanted;
        this.currentPlayers = 0;
        this.tokens = new ArrayList <Token> ();
        this.turno = 0;
        inicializarMazo();
        
    }

    // METODOS
    
    public void inicializarMazo(){
        
        // Cuatro veces porque es la cantidad de colores
        for (int i = 0; i < 4; i++){
            
            // Dos veces porque es la cantidad de sets de token por color
            for (int j = 0; j < 2; j++){
                
                // Trece veces porque es la cantidad de fichas que contiene cada set
                for(int k = 1; k < 14; k++){
                    
                    switch (i) {
                        
                        case 0:
                        {
                            // FICHAS ROJAS
                            Token nueva = new Token(TokensTypes.Token.RED, k);
                            tokens.add(nueva);
                            break;
                        }
                        
                        case 1: 
                        {
                            // FICHAS AZULES
                            Token nueva = new Token(TokensTypes.Token.BLUE, k);
                            tokens.add(nueva);
                            break;
                        }
                            
                        case 2: 
                        {    
                            // FICHAS AMARILLAS
                            Token nueva = new Token(TokensTypes.Token.YELLOW, k);
                            tokens.add(nueva);
                            break;
                        }
                        
                        case 3:
                        {    
                            // FICHAS NEGRAS
                            Token nueva = new Token(TokensTypes.Token.BLACK, k);
                            tokens.add(nueva);
                            break;
                        }
                        
                        default:
                            throw new AssertionError();
                            
                    }
                    
                }
                
            }
            
        }
         
        // Se le agregan los dos tokens comodin
        
        Token comodin1 = new Token(TokensTypes.Token.SPECIAL, -1);
        Token comodin2 = new Token(TokensTypes.Token.SPECIAL, -1);
        
//        tokens.add(comodin1);
//        tokens.add(comodin2);
        
        // Se le hace shuffle al mazo
        
        Collections.shuffle(tokens);
        
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

    public int getTurno() {
        return turno;
    }

    public void setTurno(int turno) {
        this.turno = turno;
    }
    
    
    
}
