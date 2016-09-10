package Test;

import Menu.LogIn;
import Menu.MenuInicio;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;


public class Chaturanga {
    public static void main(String[] args) {
	EventQueue.invokeLater(() -> {
            try {
                MenuInicio t = new MenuInicio();
                t.setVisible(true);
            }
            catch (Exception e) {
                System.out.println(e);
            }
        });
    }
}
