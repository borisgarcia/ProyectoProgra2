package Test;

import java.util.ArrayList;
import java.util.List;

import piezas.Pieza;
import piezas.Rey;
import tablero.Casilla;
import tablero.Tablero;
import tablero.TableroControlador;

public class Jugador {

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Jugador))
                return false;
        return ((Jugador) obj).esBlanco() == this.esBlanco();
    }
    private boolean esBlanco;	
    private Rey miRey;

    public Jugador(boolean b) {
        this.esBlanco = b;
    }

    public Jugador() {

    }

    public boolean esBlanco(){
        return esBlanco;
    }

    public String toString(){
        return esBlanco ? "Jugador Blanco" : "Jugador Negro";
    }
    public void setMiRey(Rey miRey) {
        this.miRey = miRey;
    }

    public Rey getMiRey() {
        return miRey;
    }

}
