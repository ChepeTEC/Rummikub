/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.proyecto2poo;

import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 *
 * @author Pablo
 */
public class Proyecto2POO {

    public static void main(String[] args) {

        Partida nueva = new Partida(true, "usernameHost", 3);
        
        int contador = 0;
        
        for (int i = 0; i < nueva.getTokens().size(); i++){
            
            
            if(nueva.getTokens().get(i).getColor() == TokensTypes.Token.BLUE)
                contador++;
            
        }
        
        System.out.println(contador);
        
    }
}
