/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servidor;

import com.mycompany.proyecto2poo.Partida;
import com.mycompany.proyecto2poo.PartidaSerializable;
import com.mycompany.proyecto2poo.Player;
import com.mycompany.proyecto2poo.RummikubWindow;
import com.mycompany.proyecto2poo.Token;
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
import javax.print.attribute.standard.OutputDeviceAssigned;
import javax.swing.JOptionPane;


public class threadServidorRummikub extends Thread implements Serializable {
    
    //Atributes
    
    private Socket player = null; //Referencia del socket (cliente)
    private Player playerObject;
    
    private DataInputStream input = null; //Leer
    private DataOutputStream output = null; //Enviar
    
    private int numPlayer;
    private int indexOfCurrentGame;
    private String namePlayer;
    private boolean isHost; // True --> Es el host : False --> No es el host
    
    private ServerRummikub server; //Referencia al server
    
    private ArrayList <threadServidorRummikub> enemies = new ArrayList <>();
    private ArrayList <PartidaSerializable> gamesToShow = new ArrayList<> ();
    private ArrayList <Token> tokens;
    
    
    // BUILDER
    
    public threadServidorRummikub (Socket player, ServerRummikub server, int num){
        
        this.player = player;
        this.server = server;
        this.numPlayer = num;
        this.indexOfCurrentGame = -1;
        this.namePlayer = ""; //Se desconoce hasta la primera corrida del thread
        this.tokens = new ArrayList <>();
        this.isHost = false;
        
    }
    
    public threadServidorRummikub (){

        this.indexOfCurrentGame = -1;
        
    }
    
    // METHODS
    
    @Override
    public void run (){
        
        try{
            
            //Inicializamos para lectura y escritura con el player
            input = new DataInputStream (player.getInputStream());
            output = new DataOutputStream (player.getOutputStream());
            
            //String posibleNombreDeJugador = input.readUTF(); No se porque no esta funcionando se queda pegado al unirse partida (boton)
            
            
            //for (int i = 0 ; i <= server.getJugadoresEnLobby().size() ; i++){
                //if (server.getJugadoresEnLobby().get(i).getNamePlayer().equals(posibleNombreDeJugador)){ //Si se repite el nombre de jugador
                    //server.getJugadoresEnLobby().get(i).getPlayer().close(); //Se cierra el socket
                    //server.getFrame().mostrar("Se cerró el socket con el jugador con el nombre repetido.");
                //}else{
                    this.setNamePlayer(input.readUTF()); //Conseguimos el nombre del jugador
                    
                //}
            //}

            
        }catch (IOException e){
            
            e.printStackTrace();
            
        }
        
        int opcion = 0;
        
        while (true){
            
            try{
                
                opcion = input.readInt();
                switch (opcion){

                    case 1: // Funcionalidad 1: Mostrar las partidas activas en ese momeneto
                        
                        System.out.println("ENTRO AL CASE 1 DEL SERVER");
                        
                        // Actualiza los juegos disponibles en ese momento
                        setGamesToShow(server.getCopiaPartidas());
                        
                        // Le pasa la opcion que se desea que se haga en la ventana del jugador
                        output.writeInt(1);
                        
                        ObjectOutputStream out = new ObjectOutputStream(player.getOutputStream()); // Crea un socket para enviar un objeto
                        out.writeObject(gamesToShow); // Envia el objeto para que el cliente lo reciba
                        
                        break;
                        
                    case 2: // Funcionalidad 2: Creacion de partidas
                        
                        int wantedPlayers = input.readInt();
                        
                        Partida gameToCreate = new Partida(false, namePlayer, wantedPlayers);
                       
                        PartidaSerializable copyOfGameToCreate = new PartidaSerializable(false, namePlayer, wantedPlayers);
                       
                        server.getPartidas().add(gameToCreate);
                        server.getCopiaPartidas().add(copyOfGameToCreate);
                        
                        break;
                        
                    case 3: // Funcionalidad 3: Unirse a partida
                        
                        int indexOfGame = input.readInt();
                        
                        setIndexOfCurrentGame(indexOfGame);
                        
                        String namePlayer = input.readUTF(); //Leemos el nombre del jugador para asignarle el threadServidorRummikub
                            
                        Partida gameRecieved = server.getPartidas().get(indexOfGame); // Se reconoce cual es el game al que se quiere unir   
                        
                        if (gameRecieved.getCurrentPlayers() + 1 > gameRecieved.getAmountPlayer()){ //Si se pasa del limite de jugadores
                            JOptionPane.showMessageDialog(server.getFrame(), "No se puede unir a la partida porque supera el limite de jugadores");
                            break;
                        }
                            
                        gameRecieved.setCurrentPlayers(gameRecieved.getCurrentPlayers() + 1); // Se unio un jugador, se le suma
                        server.getCopiaPartidas().get(indexOfGame).setCurrentPlayers(server.getCopiaPartidas().get(indexOfGame).getCurrentPlayers() + 1);
                            
                        gameRecieved.getPlayers().add(this); // Agrega al jugador a la partida
                         
                        for (int i = 0; i < gameRecieved.getPlayers().size(); i++){ //Limpia los enemigos cada vez que se une uno
                              threadServidorRummikub playerPartida = gameRecieved.getPlayers().get (i);
                              playerPartida.enemies.clear(); //Limpiamos para hacer el refresh
                              
                              for (int j = 0; j < gameRecieved.getPlayers().size(); j++){ //Asegura que 
                                  threadServidorRummikub otroJugadorPartida = gameRecieved.getPlayers().get(j);
                                  if (!otroJugadorPartida.getNamePlayer().equals(playerPartida.getNamePlayer())){
                                      playerPartida.enemies.add(otroJugadorPartida);
                                  }
                              }
                        }
                        
                        if (namePlayer.equals(gameRecieved.getUsernameHost())) {

                            this.isHost = true;
                            output.writeInt(6);
                            
                        }
                        
                        output.writeInt(5);    
                        
                        break;

                    case 4: // Funcionalidad 4: Enviar un mensaje por el chat
                        
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
                    
                    case 5: // Funcionalidad 5: Eliminar jugador de una partida
                        
                        System.out.println("ENTRA AL 5");
                        
                        String nombreDeJugadorAEliminar = input.readUTF();
                        
                        System.out.println("111111111");
                        
                        if(existeEnemigo(nombreDeJugadorAEliminar)){
                            
                            System.out.println("ENTRA AL IF");
                            
                            int indice = getIndexOfEnemigo(nombreDeJugadorAEliminar);
                            
                            System.out.println("CONSIGUIÓ INDEX");
                            
                            enemies.get(indice).output.writeInt(8);
                            
                            System.out.println("MANDO EL MENSAJE");
                            
                            removePlayerFromEnemyList(nombreDeJugadorAEliminar); // Elimina al jugador de la lista de enemigos de todos los demas
                            
                            enemies.get(indice).enemies.clear(); // Deja al jugador eliminado sin enemigos
                            enemies.remove(indice); // Elimina al jugador de la lista de enemigos del HOST
                            
                            System.out.println("ANTES DEL OUTPUT");
                            
                            output.writeInt(7);
                            output.writeInt(2);
                            
                            server.getPartidas().get(indexOfCurrentGame).setCurrentPlayers(server.getPartidas().get(indexOfCurrentGame).getCurrentPlayers() - 1);
                            server.getCopiaPartidas().get(indexOfCurrentGame).setCurrentPlayers(server.getCopiaPartidas().get(indexOfCurrentGame).getCurrentPlayers() - 1);
                            
                            System.out.println("DESPUES DEL IF");
                            
                        }
                        
//                        
                        else{
                            
                            
                            System.out.println("ENTRA AL ELSE");
                            output.writeInt(7);
                            output.writeInt(1);
                            
                        }
                        
                        break;
                        
                    case 6: //Buscar la referencia de la partida para asi obtenerla y comenzar la partida
//                        String nombrePlayerHost = input.readUTF();
//                        for (int i = 0 ; i < server.getPartidas().size() ; i++){
//                            if (server.getPartidas().get(i).getUsernameHost().equals(nombrePlayerHost)){
//                                Partida partidaPorComenzar = server.getPartidas().get(i);
//                                for (int j = 0 ; j < partidaPorComenzar.getPlayers().size() ; j++){
//                                    server. 
//                                }
//                            }
//                        }
                        
                }
                
            }catch (IOException e){ 
                break;
            }
        }
        
        server.getFrame().mostrar("Se removio este usuario: " + namePlayer);        
    }
    
    private void removePlayerFromEnemyList(String nameOfPlayer){
        
        for (int i = 0; i < enemies.size(); i++){
            
            if(enemies.get(i).namePlayer.equals(nameOfPlayer) == false){
            
                for (int j = 0; j < enemies.get(i).enemies.size(); j++){
                    
                    if(enemies.get(i).enemies.get(j).namePlayer.equals(nameOfPlayer)){
                        
                        enemies.get(i).enemies.remove(j);
                        
                    }
                    
                }
                
            }
        }
        
    }
    
    private int getIndexOfEnemigo(String nameOfPlayer){
        
        for(int i = 0; i < enemies.size(); i++){
        
            if(enemies.get(i).getNamePlayer().equals(nameOfPlayer)){
                return i;
            }
            
        }
        return -1;
    }
    
    private boolean existeEnemigo(String nameOfPlayer){
        
        for(threadServidorRummikub player : enemies){
            
            System.out.println("->>" + player.getNamePlayer());
            
            if(player.getNamePlayer().equals(nameOfPlayer)){
                return true;
            }
            
        }
        
        return false;
    }
    //GETTERS & SETTERS
    public Player getPlayerObject() {
        return playerObject;
    }

    public void setPlayerObject(Player playerObject) {
        this.playerObject = playerObject;
    }

    public int getNumPlayer() {
        return numPlayer;
    }

    public void setNumPlayer(int numPlayer) {
        this.numPlayer = numPlayer;
    }

    public ArrayList<threadServidorRummikub> getEnemies() {
        return enemies;
    }

    public void setEnemies(ArrayList<threadServidorRummikub> enemies) {
        this.enemies = enemies;
    }

    public ArrayList<Token> getTokens() {
        return tokens;
    }

    
    public void setTokens(ArrayList<Token> tokens) {    
        this.tokens = tokens;
    }

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

    public ArrayList<PartidaSerializable> getGamesToShow() {
        return gamesToShow;
    }

    public void setGamesToShow(ArrayList<PartidaSerializable> gamesToShow) {
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

    public boolean isIsHost() {
        return isHost;
    }

    public void setIsHost(boolean isHost) {
        this.isHost = isHost;
    }

    public int getIndexOfCurrentGame() {
        return indexOfCurrentGame;
    }

    public void setIndexOfCurrentGame(int indexOfGame) {
        this.indexOfCurrentGame = indexOfGame;
    }
    
}   
