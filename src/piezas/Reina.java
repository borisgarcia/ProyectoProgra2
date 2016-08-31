package piezas;

import tablero.Casilla;
import tablero.Posicion;
import tablero.Tablero;
import tablero.TableroControlador;

public class Reina extends Pieza {

    public Reina(boolean b, int y, int x) {
        this.esBlanca = b;
        if (esBlanca)
                this.IMAGEN = "wq.gif";
        else
                this.IMAGEN = "bq.gif";
        pos = new Posicion(x, y);
    }

    public String toString() {
        return "Reina";
    }

    @Override
    public boolean esMovimientoValido(Posicion posicion,TableroControlador tableromodel, Tablero tablero) {
        int xTo = posicion.getX();
        int yTo = posicion.getY();

        Casilla casillaHasta = tableromodel.getCasilla(xTo,yTo);
        Pieza pieza2 = casillaHasta.getPieza();

        if (pieza2 != null) {
            if (esBlanca() == pieza2.esBlanca()) {
                tablero.mensaje("Pieza de tu mismo color...");
                return false;
            }
        }	

        int yFr = pos.getY();
        int xFr = pos.getX();

       if (Math.abs(xFr - xTo) == 1 && Math.abs(yFr - yTo) == 1)
            return true;
        return false;
    }
}
