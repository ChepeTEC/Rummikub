/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servidor;

import com.mycompany.proyecto2poo.Partida;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

/**
 *
 * @author Jose
 */
public class threadServidorRummikub extends Thread {
    
    //Atributes
    
    private Socket player = null; //Referencia del socket (cliente)
    
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
                        player.getOutputStream().write(1);
                        
                        ObjectOutputStream out = new ObjectOutputStream(player.getOutputStream()); // Crea un socket para enviar un objeto
                        out.writeObject(gamesToShow); // Envia el objeto para que el cliente lo reciba
                        
                        break;
                    case 2:
                        break;
                    case 3:
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
