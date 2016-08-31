package Test;

import Menu.LogIn;
import Menu.MenuInicio;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;

import tablero.Tablero;


public class Chaturanga {
    public static void main(String[] args) {
	EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MenuInicio t = new MenuInicio();
                    t.setVisible(true);
                    } 
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
