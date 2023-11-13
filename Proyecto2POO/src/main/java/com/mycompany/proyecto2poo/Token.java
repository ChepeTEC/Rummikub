package com.mycompany.proyecto2poo;

import java.awt.Color;
import java.io.Serializable;

public class Token implements Serializable{
    // Atributos
    
    private TokensTypes.Token color;
    private int value;
    private boolean isWildCard;

    // Constructor
    
    public Token(TokensTypes.Token color, int value) {
        this.color = color;
        this.value = value;
    }

    // Getters y Setters

    public TokensTypes.Token getColor() {
        return color;
    }

    public void setColor(TokensTypes.Token color) {
        this.color = color;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Token:" + " color -> " + color + " value -> " + value;
    }

    public boolean isIsWildCard() {
        return isWildCard;
    }

    public void setIsWildCard(boolean isWildCard) {
        this.isWildCard = isWildCard;
    }
    
    
      
}
