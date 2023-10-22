package com.mycompany.proyecto2poo;

public class Token {
    // Atributos
    private TokensTypes color;
    private int value;

    // Constructor
    public Token(TokensTypes color, int value) {
        this.color = color;
        this.value = value;
    }

    // Getters y Setters

    public TokensTypes getColor() {
        return color;
    }

    public void setColor(TokensTypes color) {
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
    
    
}
