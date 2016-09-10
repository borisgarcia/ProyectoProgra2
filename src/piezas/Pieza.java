 package piezas;

import tablero.Casilla;
import tablero.Posicion;
import tablero.Tablero;
import tablero.TableroControlador;

public abstract class Pieza {
    protected String IMAGEN;
    protected boolean esBlanca;
    protected Posicion pos;

    public final String getRutaImagen() {
        return IMAGEN;
    }

    public String toString() {
        return "";
    }

    public Posicion getPosicion() {
        return this.pos;
    }

    public abstract boolean esMovimientoValido(Posicion posicion,TableroControlador tableromodel, Tablero tablero);

    public final boolean esBlanca() {
        return esBlanca;
    }

    public void setPosicion(Posicion posicionHasta) {
        this.pos = posicionHasta;
    }
}
