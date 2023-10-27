package servidor;

import com.mycompany.proyecto2poo.LobbyWindow;
import com.mycompany.proyecto2poo.Partida;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServerRummikub{
    //Atributos:
    JFrameServer frame;
    LobbyWindow frameLobby;
    
    ArrayList <Socket> players;
    ArrayList<Partida> partidas;
    
    ArrayList<threadServidorRummikub> jugadoresEnLobby;
    ArrayList<threadServidorRummikub> jugadoresPrePartida;
    
    boolean accepting;
    
//    Socket player1;
//    Socket player2;

    //Constructores:
    public ServerRummikub (JFrameServer ventanaPadre, LobbyWindow ventanaMadre){
        
        this.frame = ventanaPadre;
        this.frameLobby = ventanaMadre;
        players = new ArrayList <Socket> ();
        accepting = true; 
        jugadoresEnLobby = new ArrayList <threadServidorRummikub>();
        partidas = new ArrayList <Partida> ();
        jugadoresPrePartida = new ArrayList <threadServidorRummikub> ();
        
    }

    //Metodos
    public void runServer (){
        
        try{
            
            ServerSocket server = new ServerSocket (8081);
            frame.mostrar (".:: Servidor Activo ::.");
            frame.mostrar (".:: Esperando jugadores ::.");

            /*
            
            CODIGO COMENTADO: ACEPTA A SOLAMENTE DOS JUGADORES
            
            player1 = server.accept();
            frame.mostrar(".::Primer Cliente Aceptado");
            threadServidorRummikub user1 = new threadServidorRummikub(player1, this, 1);
            user1.start();
            

            player2 = server.accept();
            frame.mostrar(".::Segundo Cliente Aceptado");
            threadServidorRummikub user2 = new threadServidorRummikub(player2, this, 2);
            user2.start();
            
            
            user1.enemigo = user2;
            user2.enemigo = user1;

            */
            
                        
            //while (true)
            //{
            
            //}
            
            int counter = 0;
            
            while (accepting) {
                
                Socket player = server.accept();
                frame.mostrar(".:: Cliente Aceptado");

                threadServidorRummikub playerThread = new threadServidorRummikub(player, this, ++counter);
                
                playerThread.start(); //Empezamos en thread de comunicacion entre el jugador y el servidor

                // Agregar al jugador al lobby
                jugadoresEnLobby.add(playerThread);
                jugadoresPrePartida.add (playerThread);
                
                for (threadServidorRummikub otherPlayer : jugadoresPrePartida) { //Ponerlos enemigos entre ellos (comunicación entre ellos
                    
                    if (otherPlayer != playerThread) {
                        playerThread.enemies.add(otherPlayer);
                        otherPlayer.enemies.add (playerThread);
                    }
                }
                
                if (jugadoresPrePartida.size() == 4){
                    
                    accepting = false;
                    frame.mostrar("Ya no se estan aceptando más jugadores estos jugadores serán ingresados una partida");
                    Partida partida = new Partida (jugadoresPrePartida, true, 4); //Se crea el objeto de partida
                    partidas.add (partida); //Se agrega al arraylist que consultará el threadLobby
                    String mostrar = "<html>Admin: " + partida.getAdmin() + "\t" + "\t" + "Num. Jugadores: " + partida.getPlayers().size() + "/4 " + "\t" + "\t" + "Estado Partida: " + "Activa";
                    frameLobby.insertarPartida (mostrar);
                    accepting = true;
                    jugadoresPrePartida = new ArrayList <threadServidorRummikub> (); //Limpiamos el arrayList para la partida
                    continue;
                    
                }
            } 
        
            
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    
    
    // GETTERS AND SETTERS 
    
    public JFrameServer getFrame() {
        return frame;
    }

    public void setFrame(JFrameServer frame) {
        this.frame = frame;
    }

    public ArrayList<Partida> getPartidas() {
        return partidas;
    }

    public void setPartidas(ArrayList<Partida> partidas) {
        this.partidas = partidas;
    }

    public ArrayList<threadServidorRummikub> getJugadoresEnLobby() {
        return jugadoresEnLobby;
    }

    public void setJugadoresEnLobby(ArrayList<threadServidorRummikub> jugadoresEnLobby) {
        this.jugadoresEnLobby = jugadoresEnLobby;
    }

    public boolean isAccepting() {
        return accepting;
    }

    public void setAccepting(boolean accepting) {
        this.accepting = accepting;
    }
    
    


}