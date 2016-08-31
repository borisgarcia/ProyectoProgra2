package piezas;

import tablero.Casilla;
import tablero.Posicion;
import tablero.Tablero;
import tablero.TableroControlador;

public class Peon extends Pieza {

    public Peon(boolean b, int y, int x) {
        this.esBlanca = b;
        if (esBlanca)
            this.IMAGEN = "wp.gif";
        else
            this.IMAGEN = "bp.gif";
        pos = new Posicion(x, y);
    }

    public String toString() {
        return "Peon";
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

        int mov = esBlanca ? -1 : 1;

        Casilla casillaTo = tableromodel.getCasilla(xTo, yTo);

        int yFr = pos.getY();
        int xFr = pos.getX();

        if ((xFr == xTo && (yFr + mov) == yTo) && casillaTo.getPieza() == null)
            return true;
        if (Math.abs(xFr - xTo) == 1 && (yTo - yFr) == mov && casillaTo.getPieza() != null)
			return true;

        return false;
    }
}