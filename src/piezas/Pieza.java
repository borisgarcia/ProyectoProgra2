 package piezas;

import javax.swing.JLabel;

public abstract class Pieza extends JLabel{
    public String color;
    public int turno;
    String name;
    
    public Pieza(String name, int turno){
        super();
        this.name = name;
        this.turno = turno;
        color = (turno == 1 ? "w" : "b");
    }
    
    public final String getFicha(){
        return name;
    }
    
    public final int getTurno(){
        return turno;
    }
    
    public final String getColor(){
        return color;
    }
    
    public abstract boolean validarMovimiento(int x, int y, int x1, int y1);
    public abstract String icon();
}