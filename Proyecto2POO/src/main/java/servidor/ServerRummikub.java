package servidor;

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
    ArrayList <Socket> players;
    ArrayList<Partida> partidas;
    ArrayList<threadServidorRummikub> jugadoresEnLobby;
    boolean accepting;
    Socket player1;
    Socket player2;

    //Constructores:
    public ServerRummikub (JFrameServer ventanaPadre){
        this.frame = ventanaPadre;
        players = new ArrayList <Socket> ();
        accepting = true; 
        jugadoresEnLobby = new ArrayList <threadServidorRummikub>();
    }

    
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
                System.out.println(counter);
                playerThread.start();

                // Agregar al jugador al lobby
                jugadoresEnLobby.add(playerThread);
                
                for (threadServidorRummikub otherPlayer : jugadoresEnLobby) {
                    if (otherPlayer != playerThread) {
                        playerThread.enemies.add(otherPlayer);
                        otherPlayer.enemies.add (playerThread);
                    }
                }
                
                if (jugadoresEnLobby.size() == 4){
                    accepting = false;
                    frame.mostrar("Ya no se estan aceptando más jugadores.");
                    break;
                }
            } 
            
        }catch (IOException e){
            e.printStackTrace();
        }
    }


}