/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto2poo;

import java.awt.Color;
import java.io.Serializable;
import java.util.ArrayList;
import servidor.threadServidorRummikub;
import java.util.Collections;
import javax.swing.JLabel;

public class Partida implements Serializable{
    
    //Atributos
    
    private ArrayList <threadServidorRummikub> players;
    
    private boolean inProgres;
    private int amountPlayerWanted;
    private int currentPlayers;
    private String usernameHost;
    private ArrayList <Token> tokens;
    private ArrayList <String> playerNames;
    private ArrayList<JLabel> Tablero;
    
    private int turno;
  
    //Constructores

    public Partida(ArrayList<threadServidorRummikub> players, boolean inProgres) {
        
        this.players = players;
        this.inProgres = inProgres;
        this.currentPlayers = 0;
        this.tokens = new ArrayList <Token> ();
        this.playerNames = new ArrayList <String> ();
        this.Tablero = new ArrayList <JLabel> ();
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
        this.playerNames = new ArrayList <String> ();
        this.Tablero = new ArrayList <JLabel> ();
        this.turno = 0;
        inicializarMazo();
        
    }

    // METODOS
    
    public void inicializarMazo(){
        
        // Se le agregan los dos tokens comodin
        
        Token comodin1 = new Token(TokensTypes.Token.SPECIAL, -2);
        Token comodin2 = new Token(TokensTypes.Token.SPECIAL, -2);
        
        tokens.add(comodin1);
        tokens.add(comodin2);
        
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
        
        // Se le hace shuffle al mazo
        
        Collections.shuffle(tokens);
        
    }

    public boolean existeToken(int x, int y){
        
        for(int i = 0; i < Tablero.size(); i++){
            
            int x1 = (int)Tablero.get(i).getLocation().getX();
            int y1 = (int)Tablero.get(i).getLocation().getY();
            
            if(x1 == x && y1 == y){
                
                return true;
                
            }
            
        }
        
        return false;
    }
    
    public boolean verificarJugadaMismoNumero(int x, int y, int value){
        
        boolean flag = false;
        
        boolean derecha = true;
        boolean izquierda = true;
        
        for (int i = 0; i < Tablero.size(); i++){
            
            if ((int)Tablero.get(i).getLocation().getX() == (x+20) && (int)Tablero.get(i).getLocation().getY() == y){
                
                int value1 = Integer.parseInt(Tablero.get(i).getText());
                
                if(value1 == value){
                    
                    
                    
                }
                
                else {
                    
                    derecha = false;
                    
                }
            }
                
            if(Tablero.get(i).getLocation().getX() == (x-20) && Tablero.get(i).getLocation().getY() == y){
                
                int value1 = Integer.parseInt(Tablero.get(i).getText());
                
                if(value1 == value){
                    
                    
                }
                
                else{
                    
                    izquierda = false;
                }
            }    
                
        }
        
        if (derecha && izquierda)
            flag = true;
        
        return flag;
    }
    
    public boolean verificarJugadaMismoColor(int x, int y, int value, Color color){
        
        boolean flag = false;
        
        boolean derecha = true;
        boolean izquierda = true;
        
        for (int i = 0; i < Tablero.size(); i++){
            
            
            if ((int)Tablero.get(i).getLocation().getX() == (x+20) && (int)Tablero.get(i).getLocation().getY() == y){
                
                int value1 = Integer.parseInt(Tablero.get(i).getText());
                Color color1 = Tablero.get(i).getForeground();
                
                if(value1 == value+1 && color1.equals(color)){
                    
                    
                    
                }
                
                else {
                    
                    derecha = false;
                    
                }
            }
                
            if((int)Tablero.get(i).getLocation().getX() == (x-20) && (int)Tablero.get(i).getLocation().getY() == y){
                
                int value1 = Integer.parseInt(Tablero.get(i).getText());
                Color color1 = Tablero.get(i).getForeground();
                
                if(value1 == value-1 && color1.equals(color)){
                    
                    
                }
                
                else{
                    
                    izquierda = false;
                }
            }    
                
        }
        
        if (derecha && izquierda)
            flag = true;
        
        return flag;
    }
    
    public int ganador(){
        
        for (int i = 0; i < players.size(); i++){
            
            if(players.get(i).getTokens().size() == 0)
                return i;
        }
        
        return -1;
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

    public ArrayList<String> getPlayerNames() {
        return playerNames;
    }

    public void setPlayerNames(ArrayList<String> playerNames) {
        this.playerNames = playerNames;
    }

    public ArrayList<JLabel> getTablero() {
        return Tablero;
    }

    public void setTablero(ArrayList<JLabel> Tablero) {
        this.Tablero = Tablero;
    }
    
}
