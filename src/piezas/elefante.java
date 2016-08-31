package piezas;

import tablero.Casilla;
import tablero.Posicion;
import tablero.Tablero;
import tablero.TableroControlador;

public class elefante extends Pieza {

	public elefante(boolean b, int y, int x) {
		this.esBlanca = b;
		if (esBlanca)
			this.IMAGEN = "elefante.png";
		else
			this.IMAGEN = "elefanteN.png";
		pos = new Posicion(x, y);
	}

	public String toString() {
		return "elefante";
	}

	@Override
	public boolean esMovimientoValido(Posicion posicion,
			TableroControlador tableromodel, Tablero tablero) {

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

		if (Math.abs(xTo - xFr) == Math.abs(yTo - yFr)) {
			int x1, y1, x2, y2, incX, incY;
			if (xTo > xFr) {
				x1 = xFr + 1;
				x2 = xTo;
				incX = 1;
			} else {
				x1 = xFr - 1;
				x2 = xTo;
				incX = -1;
			}
			if (yTo > yFr) {
				y1 = yFr + 1;
				y2 = yTo;
				incY = 1;
			} else {
				y1 = yFr - 1;
				y2 = yTo;
				incY = -1;
			}
			for (; y2 != y1 && x2 != x1; x1 = x1 + incX, y1 = y1 + incY)
				try {
					if (tableromodel.getCasilla(x1, y1).getPieza() != null)
						return false;
				} catch (Exception e) {
				}

			return true;
		}

		return false;
	}
}