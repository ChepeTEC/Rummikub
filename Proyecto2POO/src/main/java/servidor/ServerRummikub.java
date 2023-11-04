package servidor;

import com.mycompany.proyecto2poo.LobbyWindow;
import com.mycompany.proyecto2poo.Partida;
import com.mycompany.proyecto2poo.PartidaSerializable;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServerRummikub implements Serializable{
    //Atributos
    
    private JFrameServer frame;
    
    private ArrayList <Socket> players;
    private ArrayList<Partida> partidas;
    private ArrayList<PartidaSerializable> copiaPartidas;
    
    private ArrayList<threadServidorRummikub> jugadoresEnLobby;
    private ArrayList<threadServidorRummikub> jugadoresPrePartida;
    
    private boolean accepting;
    private DataOutputStream output = null;
    
    private int matchID;

    //Constructores:
    
    public ServerRummikub (JFrameServer ventanaPadre){
        
        this.frame = ventanaPadre;
        this.players = new ArrayList <Socket> ();
        this.accepting = true; 
        this.jugadoresEnLobby = new ArrayList <threadServidorRummikub>();
        this.partidas = new ArrayList <Partida> ();
        this.copiaPartidas = new ArrayList <PartidaSerializable> ();
        this.jugadoresPrePartida = new ArrayList <threadServidorRummikub> ();
        this.matchID = 0;
        
    }

    //Metodos
    public void runServer (){
        
        try{
            
            ServerSocket server = new ServerSocket (8081);
            
            frame.mostrar (".:: Servidor Activo ::.");
            frame.mostrar (".:: Esperando jugadores ::.");
            
            int counter = 0;
            
            while (accepting) {
                
                Socket player = server.accept();
                
                frame.mostrar(".:: Cliente #" + ++counter + " Aceptado ::.");

                threadServidorRummikub playerThread = new threadServidorRummikub(player, this, counter);
                //jugadoresEnLobby.add(playerThread);
                
                playerThread.start(); //Empezamos en thread de comunicacion entre el jugador y el servidor                               
                
                // Intento de pasarle el objeto
                
                System.out.println("Intentando");
                
//                ObjectOutputStream out = new ObjectOutputStream(player.getOutputStream()); // Crea un socket para enviar un objeto
//                out.writeObject(this.partidas); // Envia el objeto para que el cliente lo reciba
                
                System.out.println("Se pudo");
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

    public int getMatchID() {
        return matchID;
    }

    public void setMatchID(int matchID) {
        this.matchID = matchID;
    }

    public ArrayList<PartidaSerializable> getCopiaPartidas() {
        return copiaPartidas;
    }

    public void setCopiaPartidas(ArrayList<PartidaSerializable> copiaPartidas) {
        this.copiaPartidas = copiaPartidas;
    }

    
}