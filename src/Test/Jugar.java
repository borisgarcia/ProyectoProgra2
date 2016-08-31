/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import java.awt.Dimension;
import javax.swing.JFrame;
import tablero.Tablero;

/**
 *
 * @author dell
 */
public class Jugar {
    public static void main(String[] args) {
        Tablero frame = new Tablero();
        frame.setAlwaysOnTop(true);
        frame.setSize( new Dimension(1000, 520) );
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationByPlatform(true);      
        frame.setVisible(true);
    }
}
