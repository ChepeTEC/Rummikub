/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.proyecto2poo;

import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 *
 * @author Pablo
 */
public class Proyecto2POO {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Ejemplo de Paneles");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            JPanel panel = new JPanel();

            JLabel jugadorLabel = new JLabel("Jugador1:");
            JLabel anfitrionLabel = new JLabel("Anfitrión");
            JLabel estadoLabel = new JLabel("Activa");

            panel.add(jugadorLabel);
            panel.add(Box.createHorizontalStrut(20)); // Agregar espacio horizontal
            panel.add(anfitrionLabel);
            panel.add(Box.createHorizontalStrut(20)); // Agregar espacio horizontal
            panel.add(estadoLabel);

            frame.add(panel);
            frame.pack();
            frame.setVisible(true);
        });
    }
}
