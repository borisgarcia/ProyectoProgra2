package piezas;

import java.util.ArrayList;
import java.util.List;

import tablero.Casilla;
import tablero.Posicion;
import tablero.Tablero;
import tablero.TableroControlador;

public class Rey extends Pieza {
    private List<Pieza> piezasDeJaque = new ArrayList<Pieza>();
    private boolean esJaque;

    public Rey(boolean b, int y, int x) {
        this.esBlanca = b;
        if (esBlanca)
            this.IMAGEN = "wk.gif";
        else
            this.IMAGEN = "bk.gif";
        pos = new Posicion(x, y);
    }

    public String toString() {
        return "Rey";
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

        if (Math.abs(xFr - xTo) == 1 && (yFr - yTo) == 0
                        || Math.abs(xFr - xTo) == 1 && Math.abs(yFr - yTo) == 1
                        || (xFr - xTo) == 0 && Math.abs(yFr - yTo) == 1)
            return true;
        return false;
    }
}
