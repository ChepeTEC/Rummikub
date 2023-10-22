/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author Jose
 */
public class threadServidorRummikub extends Thread {
    //Atributes
    Socket player = null; //Referencia del socket (cliente)
    DataInputStream input = null; //Leer
    DataOutputStream output = null; //Enviar
    String namePlayer;
    ServerRummikub server; //Referencia al server
    
    int numPlayer;
    
    //Constructor
    public threadServidorRummikub (Socket player, ServerRummikub server, int num){
        this.player = player;
        this.server = server;
        this.numPlayer = num;
        namePlayer = ""; //Se desconoce hasta la primera corrida del thread
    }

    //Getter y setters
    public Socket getPlayer() {
        return player;
    }

    public void setPlayer(Socket player) {
        this.player = player;
    }

    public String getNamePlayer() {
        return namePlayer;
    }

    public void setNamePlayer(String namePlayer) {
        this.namePlayer = namePlayer;
    }

    public ServerRummikub getServer() {
        return server;
    }

    public void setServer(ServerRummikub server) {
        this.server = server;
    }
    
    //Methods
    
    @Override
    public void run (){
        try{
            //Inicializamos para lectura y escritura con el player
            input = new DataInputStream (player.getInputStream());
            output = new DataOutputStream (player.getOutputStream());
            
            this.setNamePlayer(input.readUTF()); //Conseguimos el nombre del jugador
            
        }catch (IOException e){
            e.printStackTrace();
        }
        
        int opcion = 0; 
        
        while (true){
            try{
                opcion = input.readInt();
                switch (opcion){
                    //TODO: Faltan hacer los cases para las diferentes funcionalidades
                }
            }catch (IOException e){
                System.out.println("El cliente termino la conexion"); break;}
        }
        server.frame.mostrar("Se removio este usuario: " + namePlayer);
             
    }
    
    


}   
