package com.mycompany.proyecto2poo;

public class Token {
    // Atributos
    private String color;
    private int value;

    // Constructor
    public Token(String color, int value) {
        this.color = color;
        this.value = value;
    }

    // Getters y Setters

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
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
