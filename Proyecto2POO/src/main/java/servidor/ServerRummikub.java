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
            while (accepting){
              Socket player = server.accept();
              players.add(player);
              frame.mostrar("Ingreso un jugador");
              if (players.size() == 4){
                  accepting = false;
              }
            }  
            
        }catch (IOException e){
            e.printStackTrace();
        }
    }


}