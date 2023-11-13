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
import com.mycompany.proyecto2poo.TokensTypes;
import java.awt.Color;
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
        this.namePlayer = ""; //Se desconoce hasta la primera corrida del run del thread
        this.tokens = new ArrayList <Token>();
        this.isHost = false;
        
    }
    
    public threadServidorRummikub (){

        this.indexOfCurrentGame = -1;
        this.tokens = new ArrayList <Token>();
        
    }
    
    // METHODS
    
    @Override
    public void run (){
        
        try{
            
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

                    case 1: // Funcionalidad 1: Mostrar las partidas activas en ese momeneto
                        
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
                            output.writeInt(2);
                            output.writeInt(1);
                            break;
                        }
                        
                        if(gameRecieved.isInProgres()){
                            output.writeInt(2);
                            output.writeInt(2);
                            break;
                        }
                            
                        gameRecieved.setCurrentPlayers(gameRecieved.getCurrentPlayers() + 1); // Se unio un jugador, se le suma
                        server.getCopiaPartidas().get(indexOfGame).setCurrentPlayers(server.getCopiaPartidas().get(indexOfGame).getCurrentPlayers() + 1);
                        this.numPlayer = gameRecieved.getCurrentPlayers() + 1;
                        
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
                        output.writeInt(server.getCopiaPartidas().get(indexOfGame).getAmountPlayerWanted());
                        output.writeInt (gameRecieved.getCurrentPlayers());
                        
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
                        
                        String nombreDeJugadorAEliminar = input.readUTF();
                        
                        if(existeEnemigo(nombreDeJugadorAEliminar)){
                            
                            int indice = getIndexOfEnemigo(nombreDeJugadorAEliminar);
                            
                            enemies.get(indice).output.writeInt(8);
                            
                            removePlayerFromEnemyList(nombreDeJugadorAEliminar); // Elimina al jugador de la lista de enemigos de todos los demas
                            
                            enemies.get(indice).enemies.clear(); // Deja al jugador eliminado sin enemigos
                            enemies.remove(indice); // Elimina al jugador de la lista de enemigos del HOST
                            
                            output.writeInt(7);
                            output.writeInt(2);
                            
                            server.getPartidas().get(indexOfCurrentGame).setCurrentPlayers(server.getPartidas().get(indexOfCurrentGame).getCurrentPlayers() - 1);
                            server.getCopiaPartidas().get(indexOfCurrentGame).setCurrentPlayers(server.getCopiaPartidas().get(indexOfCurrentGame).getCurrentPlayers() - 1);
                            
                        }
                                              
                        else{
                            
                            output.writeInt(7);
                            output.writeInt(1);
                            
                        }
                        
                        break;
                        
                    case 6: // Funcionalidad 6: Comenzar la partida
                        
                    {
                        
                        Partida currentGame = server.getPartidas().get(indexOfCurrentGame);
                        currentGame.setInProgres(true);
                        
                        for (int i = 0; i < currentGame.getPlayers().size(); i++){
                            
                            for (int j = 0; j < 14; j++){
                             
                                // Agregar el token al jugador 
                                currentGame.getPlayers().get(i).getTokens().add(currentGame.getTokens().get(i));
                                
                                // Eliminar el token del mazo principal
                                currentGame.getTokens().remove(i);
                                
                            }
                            
                            // Senal para entrar al case
                            currentGame.getPlayers().get(i).getOutput().writeInt(9);
                            
                            // Enviar senal para que se muestre en pantallas esas fichas
                            ObjectOutputStream writeO = new ObjectOutputStream( currentGame.getPlayers().get(i).getPlayer().getOutputStream());
                                
                            // Se le envia todos la lista de tokens
                            writeO.writeObject(currentGame.getPlayers().get(i).getTokens());
                            
                            currentGame.getPlayers().get(i).getOutput().writeInt(12);
                            
                        }
                        
                        for (int i = 0; i < currentGame.getPlayers().size(); i++){
                            
                            currentGame.getPlayers().get(i).getOutput().writeInt(13);
                            currentGame.getPlayers().get(i).getOutput().writeInt(currentGame.getTokens().size());
                            
                            currentGame.getPlayers().get(i).getOutput().writeInt(14);
                            String texto = "Turno del jugador: " + this.namePlayer;
                            currentGame.getPlayers().get(i).getOutput().writeUTF(texto);
                            
                        }
                        
                        break;
                    
                    }
                    
                    
                    case 7: //Funcionalidad 7: Gestion de turnos
                        
                    {
                        
                        int x = input.readInt();
                        int y = input.readInt();
                        
                        int valueOfToken = input.readInt();
                        int colorOfToken = input.readInt();
                        
                        Partida currentGame = server.getPartidas().get(indexOfCurrentGame);
                        
                        currentGame.setTurno((currentGame.getTurno() + 1) % currentGame.getPlayers().size());
                        
                        int posicionPlayerAJugar = currentGame.getTurno();
                        
                        threadServidorRummikub JugadorConTurno = currentGame.getPlayers().get(posicionPlayerAJugar);
                        
                        JugadorConTurno.output.writeInt(3);
                        
                        for (int i = 0; i < enemies.size(); i++){
                            
                            enemies.get(i).output.writeInt(10);
                            
                            enemies.get(i).output.writeInt(x);
                            enemies.get(i).output.writeInt(y);

                            enemies.get(i).output.writeInt(valueOfToken);
                            enemies.get(i).output.writeInt(colorOfToken);
                            
                        }
                        
                        for (int i = 0; i < currentGame.getPlayers().size(); i++){
                            
                            currentGame.getPlayers().get(i).getOutput().writeInt(14);
                            String texto = "Turno del jugador: " + currentGame.getPlayers().get(posicionPlayerAJugar).namePlayer;
                            currentGame.getPlayers().get(i).getOutput().writeUTF(texto);
                                
                        }
                        
                        
                        // Se borra la carta del mazo del jugador
                        
                        if(colorOfToken == 0)
                            removeTokenFromPlayerHand(valueOfToken, TokensTypes.Token.BLACK);
                            
                        if(colorOfToken == 1)
                            removeTokenFromPlayerHand(valueOfToken, TokensTypes.Token.BLUE);
                            
                        if(colorOfToken == 2)
                            removeTokenFromPlayerHand(valueOfToken, TokensTypes.Token.RED);
                            
                        if(colorOfToken == 3)
                            removeTokenFromPlayerHand(valueOfToken, TokensTypes.Token.YELLOW);
                            
                        if(colorOfToken == 4)    
                            removeTokenFromPlayerHand(valueOfToken, TokensTypes.Token.SPECIAL);
                            
                        output.writeInt(11);
                        
                        ObjectOutputStream writeT = new ObjectOutputStream(player.getOutputStream());
                        
                        writeT.writeObject(tokens);
                        
                    }    
                        break;
                    
                    case 8:
                        
                    {
                        Partida currentGame = server.getPartidas().get(indexOfCurrentGame);
                        
                        if (currentGame.getTokens().size() != 0){
                            
                            currentGame.setTurno((currentGame.getTurno() + 1) % currentGame.getPlayers().size());
                        
                            int posicionPlayerAJugar = currentGame.getTurno();

                            threadServidorRummikub JugadorConTurno = currentGame.getPlayers().get(posicionPlayerAJugar);

                            JugadorConTurno.output.writeInt(3);
                            
                            tokens.add(currentGame.getTokens().get(0));
                            currentGame.getTokens().remove(0);

                            output.writeInt(11);

                            ObjectOutputStream writeT = new ObjectOutputStream(player.getOutputStream());

                            writeT.writeObject(tokens);
                            
                            for (int i = 0; i < currentGame.getPlayers().size(); i++){
                            
                                currentGame.getPlayers().get(i).getOutput().writeInt(13);
                                currentGame.getPlayers().get(i).getOutput().writeInt(currentGame.getTokens().size());

                                currentGame.getPlayers().get(i).getOutput().writeInt(14);
                                String texto = "Turno del jugador: " + currentGame.getPlayers().get(posicionPlayerAJugar).namePlayer;
                                currentGame.getPlayers().get(i).getOutput().writeUTF(texto);
                                
                            }
                            
                        }
                        
                        else {
                            output.writeInt(2);
                            output.writeInt(3);
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
    
    // METODOS
    
    private boolean removeTokenFromPlayerHand(int value, TokensTypes.Token color){
        
        System.out.println("LO QUE LLEGA: ");
        System.out.println("->> " + value);
        System.out.println("->> " + color);
        
        for(int i = 0; i < tokens.size(); i++)
         
            if(tokens.get(i).getValue() == value && tokens.get(i).getColor() == color){
                
                System.out.println("===================");
                System.out.println("->> " + tokens.get(i).getValue());
                System.out.println("->> " + tokens.get(i).getColor());
                System.out.println("===================");
                
                tokens.remove(i);
                return true;
            }    
        
        return false;
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
    
    private void eliminarTokenDeMazo(int numero, TokensTypes.Token color){
        
        for (int i = 0; i < tokens.size(); i++){
            
            if (tokens.get(i).getValue() == numero && tokens.get(i).getColor() == color)
                tokens.remove(i);
            
        }
        
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
