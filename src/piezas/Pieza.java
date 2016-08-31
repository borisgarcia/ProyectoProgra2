 package piezas;

import tablero.Casilla;
import tablero.Posicion;
import tablero.Tablero;
import tablero.TableroControlador;

public abstract class Pieza {
    protected String IMAGEN;
    protected boolean esBlanca;
    protected Posicion pos;

    public String getRutaImagen() {
        return IMAGEN;
    }

    public String toString() {
        return "";
    }

    public Posicion getPosicion() {
        return this.pos;
    }

    abstract public boolean esMovimientoValido(Posicion posicion,TableroControlador tableromodel, Tablero tablero);

    public boolean esBlanca() {
        return esBlanca;
    }

    public boolean isBlanca() {
        return esBlanca;
    }

    public void setPosicion(Posicion posicionHasta) {
        this.pos = posicionHasta;
    }
}
