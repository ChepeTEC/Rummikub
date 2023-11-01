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
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jose
 */
public class threadServidorRummikub extends Thread {
    
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
            
            System.out.println("ERROR");
            e.printStackTrace();
        }
        
        int opcion = 0; 
        
        while (true){
            
            try{
                
                opcion = input.readInt();
                switch (opcion){
                    //TODO: Faltan hacer los cases para las diferentes funcionalidades
                    case 1:
                        
                        // Mostrar las partidas activas en ese momeneto
                        
                        // Actualiza los juegos disponibles en ese momento
                        setGamesToShow(server.getPartidas());
                        
                        // Le pasa la opcion que se desea que se haga en la ventana del jugador
                        output.writeInt(1);
                        
                        ObjectOutputStream out = new ObjectOutputStream(player.getOutputStream()); // Crea un socket para enviar un objeto
                        out.writeObject(gamesToShow); // Envia el objeto para que el cliente lo reciba
                        
                        System.out.println("Despues de recibir el mensaje");
                        
                        break;
                        
                    case 2:
                        
                        //Crear las partidas
                        
                        int wantedPlayers = input.readInt();
                        
                        Partida gameToCreate = new Partida(false, namePlayer, wantedPlayers);
                        server.getPartidas().add(gameToCreate);
                        
                        break;
                    case 3:
                        //Unirse a una partida
                        
                        String namePlayer = input.readUTF(); //Leemos el nombre del jugador para asignarle el threadServidorRummikub
                        
                        ObjectInputStream in3 = new ObjectInputStream (player.getInputStream());
                        
                    {
                        try {
                            Partida gameRecieved = ((Partida) in3.readObject()); //Recibimos la partida que se quiere
                            
                            ArrayList <threadServidorRummikub> playersActiveThread = server.getJugadoresEnLobby(); //Obtenemos todas los jugadores conectados
                        
                        for (int i = 0; i < playersActiveThread.size(); i++) { //Recorremos hasta buscar el que concuerde con el nombre
                            threadServidorRummikub get = playersActiveThread.get(i);
                            if (get.getNamePlayer() == namePlayer){
                                ObjectOutputStream out3 = new ObjectOutputStream (player.getOutputStream());
                                out3.writeObject(get);
                                gameRecieved.getPlayers().add(get); //A la partida le agregamos el jugador que solicito la entrada
                                
                                for (threadServidorRummikub otherPlayer : playersActiveThread) { //Se relacionen siendo enemigos con los que estan en la partida
                                    if (otherPlayer != get) {
                                        get.enemies.add(otherPlayer);
                                        otherPlayer.enemies.add (get);
                                    }
                                }
                            
                            }
                        }
                        
                        } catch (ClassNotFoundException ex) {
                            Logger.getLogger(threadServidorRummikub.class.getName()).log(Level.SEVERE, null, ex);
                            System.out.println("Ocurrio un error durante el recibir del la partida deseada");
                        }
                    }   
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
                System.out.println("El cliente termino la conexion"); 
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
