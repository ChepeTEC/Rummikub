/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto2poo;

import java.io.Serializable;

public class ClassicToken extends Token implements Serializable{
    
    public ClassicToken(TokensTypes.Token color, int value) {
        super(color, value);
        super.setIsWildCard(false);
    }    
}
