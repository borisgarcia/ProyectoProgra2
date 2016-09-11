package tablero;

import Menu.Player;
import javax.swing.table.AbstractTableModel;



import piezas.elefante;
import piezas.Caballo;
import piezas.Peon;
import piezas.Pieza;
import piezas.Reina;
import piezas.Rey;
import piezas.Torre;

public class TableroControlador extends AbstractTableModel {
    public Rey getReyNegro() {
	return reyNegro;
    }
    public Rey getReyBlanco() {
	return reyBlanco;
    }

    private static final long serialVersionUID = 1L;
    private String[] columnNames = { "A", "B", "C", "D", "E", "F", "G", "H" };
    private Casilla[][] casillas;
    private Rey reyNegro;
    private Rey reyBlanco;
    private Casilla casillaHastaLocal;
    private Pieza piezaDesdeLocal;
    private Pieza piezaHastaLocal;
    private Posicion posicionDesdeLocal;
    private Posicion posicionHastaLocal;

    public TableroControlador() {
        casillas = new Casilla[8][8];
        int i = 0, j = 0;
        casillas[i][j] = new Casilla(new Torre(false, i, j++));
        casillas[i][j] = new Casilla(new Caballo(false, i, j++));
        casillas[i][j] = new Casilla(new elefante(false, i, j++));
        casillas[i][j] = new Casilla(new Reina(false, i, j++));
        casillas[i][j] = new Casilla(new Rey(false, i, j++));
        casillas[i][j] = new Casilla(new elefante(false, i, j++));
        casillas[i][j] = new Casilla(new Caballo(false, i, j++));
        casillas[i][j] = new Casilla(new Torre(false, i, j++));
        i++;
        j = 0;
        casillas[i][j] = new Casilla(new Peon(false, i, j++));
        casillas[i][j] = new Casilla(new Peon(false, i, j++));
        casillas[i][j] = new Casilla(new Peon(false, i, j++));
        casillas[i][j] = new Casilla(new Peon(false, i, j++));
        casillas[i][j] = new Casilla(new Peon(false, i, j++));
        casillas[i][j] = new Casilla(new Peon(false, i, j++));
        casillas[i][j] = new Casilla(new Peon(false, i, j++));
        casillas[i][j] = new Casilla(new Peon(false, i, j++));
        i++;
        j = 0;
        for (; i < 6; i++) {
            casillas[i][0] = new Casilla();
            casillas[i][1] = new Casilla();
            casillas[i][2] = new Casilla();
            casillas[i][3] = new Casilla();
            casillas[i][4] = new Casilla();
            casillas[i][5] = new Casilla();
            casillas[i][6] = new Casilla();
            casillas[i][7] = new Casilla();
        }
        casillas[i][j] = new Casilla(new Peon(true, i, j++));
        casillas[i][j] = new Casilla(new Peon(true, i, j++));
        casillas[i][j] = new Casilla(new Peon(true, i, j++));
        casillas[i][j] = new Casilla(new Peon(true, i, j++));
        casillas[i][j] = new Casilla(new Peon(true, i, j++));
        casillas[i][j] = new Casilla(new Peon(true, i, j++));
        casillas[i][j] = new Casilla(new Peon(true, i, j++));
        casillas[i][j] = new Casilla(new Peon(true, i, j++));
        i++;
        j = 0;
        casillas[i][j] = new Casilla(new Torre(true, i, j++));
        casillas[i][j] = new Casilla(new Caballo(true, i, j++));
        casillas[i][j] = new Casilla(new elefante(true, i, j++));
        casillas[i][j] = new Casilla(new Reina(true, i, j++));
        casillas[i][j] = new Casilla(new Rey(true, i, j++));
        casillas[i][j] = new Casilla(new elefante(true, i, j++));
        casillas[i][j] = new Casilla(new Caballo(true, i, j++));
        casillas[i][j] = new Casilla(new Torre(true, i, j++));

        reyNegro = (Rey) getCasilla(4, 0).getPieza();
        reyBlanco = (Rey) getCasilla(4, 7).getPieza();		

    }

    public int getColumnCount() {
        return 8;
    }

    public int getRowCount() {
        return 8;
    }

    public String getColumnName(int col) {
        return columnNames[col];
    }

    public Object getValueAt(int row, int col) {
        return casillas[row][col];
    }

    public Class getColumnClass(int c) {
        return Casilla.class;
    }

    public Casilla getCasilla(int x, int y) {
        return (Casilla) getValueAt(y, x);

    }

    public boolean intentarMover(Casilla casillaDesde, Posicion posicionHasta, Tablero tablero) {
        Pieza pieza = casillaDesde.getPieza();
        if (!pieza.esMovimientoValido(posicionHasta, this, tablero)) {
                tablero.mensaje("Movimiento invalido...");
                return false;
        }
        Casilla casillaHasta = getCasilla(posicionHasta.getX(),
                        posicionHasta.getY());
        mover(casillaDesde, posicionHasta, casillaHasta);
        Player PlayerActivo = tablero.getPlayerActivo();
        Player PlayerPasivo = tablero.getPlayerPasivo();
        casillaDesde.vaciar();
        fireTableDataChanged();
        return true;
    }

    public void devolverJugada() {
        casillaHastaLocal.setPieza(piezaHastaLocal);		
        piezaDesdeLocal.setPosicion(posicionDesdeLocal);
    }

    public void mover(Casilla casillaDesde, Posicion posicionHasta, Casilla casillaHasta) {
        casillaHastaLocal = casillaHasta;
        piezaDesdeLocal = casillaDesde.getPieza();
        piezaHastaLocal = casillaHasta.getPieza();
        posicionDesdeLocal = casillaDesde.getPieza().getPosicion();
        posicionHastaLocal = posicionHasta;

        piezaDesdeLocal.setPosicion(posicionHastaLocal);
        casillaHastaLocal.setPieza(piezaDesdeLocal);
    }
}
