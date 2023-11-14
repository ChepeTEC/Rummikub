/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto2poo;

import java.awt.Color;
import java.awt.Font;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import servidor.threadServidorRummikub;

public class ThreadPlayer extends Thread{
    
    private DataInputStream read; // Para hacer lectura de lo que comunica el server
    private boolean isRunning;
    private Player player;
    
    // CONSTRUCTOR
    
    public ThreadPlayer(DataInputStream read, Player player){
        
        this.read = read;
        this.player = player;
        this.isRunning = true;
        
    }

    // METODO RUN()
    
    public void run(){
        
        // Hacer algo antes del while true
        
        int opcion = 0;
        
        while(isRunning){
           
            try{
                
                String mensaje = "";
                
                int cantidadAvatares;
                
                int numeroJugadorRecibido;
                
                ArrayList <PartidaSerializable> gamesToShow = new ArrayList <PartidaSerializable> ();
                threadServidorRummikub playerThreadServidor = new threadServidorRummikub ();
                
                opcion = read.readInt();
                
                switch(opcion){
                    
                    case 1: // Funcionalidad 1: Mostrar partidas disponibles
                        {
                        
                        ObjectInputStream in = new ObjectInputStream(read);
                        gamesToShow = ((ArrayList<PartidaSerializable>) in.readObject());
                        
                        player.getRefLobby().agregarPartidasDisponibles(gamesToShow);
                        player.getRefLobby().setVisible(true);
                        player.getRefMainWindow().setVisible(false);
                        
                        }
                        break;
                        
                    case 2: // Funcionalidad 2: Manejar no poder unirse a partida 
                        
                        int opcionError = read.readInt();
                        
                        switch (opcionError) {
                            case 1:
                                JOptionPane.showMessageDialog(player.getRefLobby(), "No se puede unir a la partida, partida llena");
                                break;
                                
                            case 2: 
                                JOptionPane.showMessageDialog(player.getRefLobby(), "No se puede unir a la partida, ya esta en progreso");
                                break;
                                
                            case 3: 
                                JOptionPane.showMessageDialog(player.getRefVentana(), "No quedan mas cartas en el mazo");
                                player.setMyTurn(true);
                                
                                break;
                                
                            case 4: 
                                JOptionPane.showMessageDialog(player.getRefVentana(), "No puede colocar dos fichas en el mismo lugar");
                                player.setMyTurn(true);
                                break;
                                
                            case 5:
                                JOptionPane.showMessageDialog(player.getRefVentana(), "JUGADA INVALIDA");
                                player.setMyTurn(true);
                                break;
                                
                            case 6:
                                JOptionPane.showMessageDialog(player.getRefVentana(), "ERES EL GANADOR DE LA PARTIDA");
                                player.getRefVentana().setVisible(false);
                                player.getRefMainWindow().setVisible(true);
                                break;
                                
                            case 7: 
                                JOptionPane.showMessageDialog(player.getRefVentana(), "HAS PERDIDO");
                                player.getRefVentana().setVisible(false);
                                player.getRefMainWindow().setVisible(true);
                                break;
                            default:
                                throw new AssertionError();
                        }
                        
                        break;
                    
                    case 3: // Funcionalidad 3 : Cambio de turno
                        
                        player.setMyTurn(true);
                        
                        break;
        
                    case 4: // Funcionalidad 4: Interaccion del chat
                        
                        mensaje = read.readUTF();
                        
                        player.getRefVentana().mostrar(mensaje);
                        
                        break;
                        
                    case 5: // Funcionalidad 5: Unirse a una partida
                        
                        
                        player.setRefVentana(new RummikubWindow(player));
                        player.getRefVentana().setVisible(true);
                        
                        player.getRefVentana().getlblTurnos().setVisible(true);
                        
                        player.getRefLobby().setVisible(false);
                        
                        if(player.isIsHost()){
                            
                            player.getRefVentana().agregarBotonEliminarJugador();
                            
                        }
                        
                        break;
                        
                    case 6: // Funcionalidad 6: Desginar al player como host de la partida
                        
                        this.player.setIsHost(true);
                        this.player.setMyTurn(true);
                        
                        break;
                        
                    case 7: // Funcionalidad 7: Manejar la eliminacion de jugadores y sus posibles errores
                        
                        int situacion = player.getRead().readInt();
                        
                        switch (situacion) {
                            
                            case 1:
                                
                                // No se encontro el jugador que se deseaba eliminar
                                System.out.println("NO SE PUDO");
                                player.getRefVentana().errorAlEliminarJugador(1);
                                
                                break;
                                
                            case 2:
                                
                                // Se elimino el jugador con exito
                                System.out.println("SE PUDO");
                                player.getRefVentana().errorAlEliminarJugador(2);
                                
                                break;
                                
                            default:
                                throw new AssertionError();
                                
                        }
                        
                        break;
                        
                    case 8: // Funcionalidad: Has sido eliminado de la partida
                        
                        player.getRefVentana().setVisible(false);
                        player.getRefMainWindow().setVisible(true);
                        
                        player.getRefLobby().getButtons().clear();
                        player.getRefLobby().getPnlLobbys().removeAll();
                        player.getRefLobby().getPnlLobbys().revalidate();
                        player.getRefLobby().getPnlLobbys().repaint();
                        player.getRefLobby().setCordX(10);
                        player.getRefLobby().setCordY(10);
                        
                        break;
                        
                    case 9: // Funcionalidad 9: Recibir fichas al principio
                    {
                        
                        ObjectInputStream a = new ObjectInputStream(read);
                        
                        ArrayList<Token> playerTokens = ((ArrayList<Token>) a.readObject());
                        
                        for(int i = 0; i < playerTokens.size(); i++){
                            
                            player.getRefVentana().generarFicha(playerTokens.get(i));
                            
                        }
                    }    
                        break;
                        
                    case 10: // Funcionalidad 10: Marcar jugada en tablero
                        
                        int x = read.readInt();
                        int y = read.readInt();
                        
                        int valueOfToken = read.readInt();
                        int colorOfToken = read.readInt();
                        
                        JLabel labelToken = new JLabel();

                        labelToken.setSize(20,30);
                        BevelBorder bevelBorder = new BevelBorder(BevelBorder.RAISED, Color.BLACK, Color.BLACK);
                        labelToken.setBorder(bevelBorder);
                        labelToken.setText(String.valueOf(valueOfToken));
                        labelToken.setHorizontalAlignment(SwingConstants.CENTER);
                        labelToken.setBackground(Color.gray); // Establece el fondo en negro
                        labelToken.setOpaque(true);
                        Font font = new Font("Lucida Sans", Font.BOLD, 10);
                        labelToken.setFont(font);

                        switch (colorOfToken) {
                            case 0:
                                labelToken.setForeground(Color.BLACK);
                                break;
                            case 1:
                                labelToken.setForeground(Color.BLUE);
                                break;
                            case 2:
                                labelToken.setForeground(Color.RED);
                                break;
                            case 3:
                                labelToken.setForeground(Color.YELLOW);
                                break;
                            case 4:
                                labelToken.setForeground(Color.ORANGE);
                                break;
                            default:
                                throw new AssertionError();
                        }
                        
                        labelToken.setLocation(x, y);
                        
                        player.getRefVentana().getPnlGame().add(labelToken);
                        player.getRefVentana().getPnlGame().repaint();
                        
                        break;
                        
                    case 11: // Funcionalidad 11: Actulizar mazo del jugador
                        
                        player.getRefVentana().getPnlPlayerTokens().removeAll();
                        player.getRefVentana().getPnlPlayerTokens().validate();
                        player.getRefVentana().getPnlPlayerTokens().repaint();
                        
                        player.getRefVentana().setCordMazoX(10);
                        player.getRefVentana().setCordMazoY(10);
                        
                        ObjectInputStream a = new ObjectInputStream(read);
                        
                        ArrayList<Token> playerTokens = ((ArrayList<Token>) a.readObject());
                        
                        for(int i = 0; i < playerTokens.size(); i++){
                            
                            player.getRefVentana().generarFicha(playerTokens.get(i));
                            
                        }
                        
                        break;
                    
                    case 12: // Funcionalidad 12: Activar los botones de comer ficha
                        
                            player.getRefVentana().getBtnComerFicha().setEnabled(true);
                        
                        break;
                    
                    case 13: // Funcionalidad 13: Setear cantidad de fichas en el mazo
                        
                        int cantidadDeFichas = read.readInt();
                        
                        player.getRefVentana().getBtnComerFicha().setText(String.valueOf(cantidadDeFichas));
                        
                        break;
                        
                    case 14: // Funcionalidad 14: Setea el titulo dle turno
                        
                        String turnoText = read.readUTF();
                        
                        player.getRefVentana().getlblTurnos().setText(turnoText);
                        
                        break;
                        
                    case 15:
                        
                        int indiceDeAvatar = read.readInt();
                        
                        ObjectInputStream ObjectIn = new ObjectInputStream(read);
                        ArrayList <String> names = (ArrayList <String>)ObjectIn.readObject();
                        
                        for (int i = 0; i < indiceDeAvatar; i++){
                            
                            // Setear avatar
                            
                            player.getRefVentana().getAvatarIcons().get(i).setVisible(true);
                            
                            // Setear nombre
                            
                            player.getRefVentana().getPlayerNames().get(i).setVisible(true);
                            player.getRefVentana().getPlayerNames().get(i).setOpaque(true);
                            player.getRefVentana().getPlayerNames().get(i).setText(names.get(i));
                            
                        }
                        
                        break;
                }
            }
            
            catch (ClassNotFoundException ex) {
                Logger.getLogger(ThreadPlayer.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("ERROR 1");
            }
            catch(IOException e){
                  Logger.getLogger(ThreadPlayer.class.getName()).log(Level.SEVERE, null, e);
                  System.out.println("ERROR 2");   
            } 
            
        }
    }
    
    // GETTER & SETTERS
    
}
