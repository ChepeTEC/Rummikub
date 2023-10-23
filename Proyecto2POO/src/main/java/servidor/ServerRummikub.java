package servidor;

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
    boolean accepting;
    Socket player1;
    Socket player2;

    //Constructores:
    public ServerRummikub (JFrameServer ventanaPadre){
        this.frame = ventanaPadre;
        players = new ArrayList <Socket> ();
        accepting = true;        
    }

    public void runServer (){
        try{
            
            ServerSocket server = new ServerSocket (8081);
            frame.mostrar (".:: Servidor Activo ::.");
            frame.mostrar (".:: Esperando jugadores ::.");

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
            
            while (true)
            {
            
            }
            
        }catch (IOException e){
            e.printStackTrace();
        }
    }


}