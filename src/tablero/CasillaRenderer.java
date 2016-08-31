package tablero;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class CasillaRenderer implements TableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object casilla, boolean isSelectect, boolean hasFocus,int row,int column) {
        Casilla casilla2 = (Casilla) casilla;
        casilla2.setOpaque(true);
        if((row + column) % 2 == 1 )
                casilla2.setBackground(Color.LIGHT_GRAY);
        return casilla2;
    }

}
