package com.mycompany.proyecto2poo;

public class Ficha {
    //Atributos
    private String color;
    private int valor;

    //Constructor
    public Ficha(String color, int valor) {
        this.color = color;
        this.valor = valor;
    }

    //Getters y Setters

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "Ficha:" + " de color: " + color + " con valor de " + valor;
    }
    
    
}
