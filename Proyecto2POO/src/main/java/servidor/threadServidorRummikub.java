/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servidor;

import com.mycompany.proyecto2poo.Partida;
import com.mycompany.proyecto2poo.Player;
import com.mycompany.proyecto2poo.RummikubWindow;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class threadServidorRummikub extends Thread implements Serializable {
    
    //Atributes
    
    private Socket player = null; //Referencia del socket (cliente)
    private Player playerObject;
    
    private DataInputStream input = null; //Leer
    private DataOutputStream output = null; //Enviar
    
    private int numPlayer;
    private String namePlayer;
    
    private ServerRummikub server; //Referencia al server
    
    private ArrayList <threadServidorRummikub> enemies = new ArrayList <>();
    private ArrayList <Partida> gamesToShow = new ArrayList<> ();
    
    
    // BUILDER
    
    public threadServidorRummikub (Socket player, ServerRummikub server, int num, ArrayList <Partida> gamesToShow){
        
        this.player = player;
        this.server = server;
        this.numPlayer = num;
        //enemies = new ArrayList <threadServidorRummikub> ();
        namePlayer = ""; //Se desconoce hasta la primera corrida del thread
        this.gamesToShow = gamesToShow;
    }
    
    public threadServidorRummikub (){

    }
    
    // METHODS
    
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

                    case 1:
                        
                        // Mostrar las partidas activas en ese momeneto
                        
                        // Actualiza los juegos disponibles en ese momento
                        setGamesToShow(server.getPartidas());
                        
                        // Le pasa la opcion que se desea que se haga en la ventana del jugador
                        output.writeInt(1);
                        
                        ObjectOutputStream out = new ObjectOutputStream(player.getOutputStream()); // Crea un socket para enviar un objeto
                        out.writeObject(gamesToShow); // Envia el objeto para que el cliente lo reciba
                        
                        break;
                        
                    case 2:
                        
                        //Crear las partidas
                        
                        int wantedPlayers = input.readInt();
                        
                        Partida gameToCreate = new Partida(false, namePlayer, wantedPlayers, server.getMatchID());
                        server.setMatchID(server.getMatchID() + 1);
                        server.getPartidas().add(gameToCreate);
                        
                        break;
                        
                    case 3:
                        
                        //Unirse a una partida
                        
                        int indexOfGame = input.readInt();
                        
                        String namePlayer = input.readUTF(); //Leemos el nombre del jugador para asignarle el threadServidorRummikub
                            
                        Partida gameRecieved = server.getPartidas().get(indexOfGame); // Se reconoce cual es el game al que se quiere unir
                            
                        gameRecieved.setCurrentPlayers(gameRecieved.getCurrentPlayers() + 1); // Se unio un jugador, se le suma
                        
                        // ArrayList <threadServidorRummikub> playersActiveThread = server.getJugadoresEnLobby(); //Obtenemos todas los jugadores conectados

                        gameRecieved.getPlayers().add(this); // Agrega al jugador a la partida

                        for (int i = 0; i < gameRecieved.getPlayers().size(); i++){

                            // Bucle para la asginacion de enemgios

                            if (!gameRecieved.getPlayers().get(i).getNamePlayer().equals(this.getNamePlayer())) {

                                this.enemies.add(gameRecieved.getPlayers().get(i));

                            }

                        }
                            
                        output.writeInt(5);    
                        
                        break;

                    case 4:
                        
                        // Enviar un mensaje por el chat
                        
                        String mensaje = input.readUTF();
                        server.getFrame().mostrar(mensaje);
                        
                        // envia un 4 al thradCliente enemigo
                        for (threadServidorRummikub enemy : enemies){
                            try {
                                enemy.output.writeInt(4);
                                enemy.output.writeUTF(mensaje);
                                server.getFrame().mostrar("Mensaje enviado a " + enemy.getNamePlayer());       
                            }catch (IOException e){
                                server.getFrame().mostrar("Error al enviar mensaje a " + enemy.getNamePlayer());
                            }
                        }
                        break;
                }
                
            }catch (IOException e){ 
                break;
            }
        }
        
        server.getFrame().mostrar("Se removio este usuario: " + namePlayer);        
    }
    
    //GETTERS & SETTERS
    
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

    public ArrayList<Partida> getGamesToShow() {
        return gamesToShow;
    }

    public void setGamesToShow(ArrayList<Partida> gamesToShow) {
        this.gamesToShow = gamesToShow;
    }

    public DataInputStream getInput() {
        return input;
    }

    public void setInput(DataInputStream input) {
        this.input = input;
    }

    public DataOutputStream getOutput() {
        return output;
    }

    public void setOutput(DataOutputStream output) {
        this.output = output;
    }
    
}   
