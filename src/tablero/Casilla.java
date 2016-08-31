package tablero;

import java.awt.Color;
import img.FabricaImagenes;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import piezas.Pieza;

public class Casilla extends JLabel{
    private Pieza pieza;
    
    public Casilla(Pieza p) {
        this.pieza = p;
        setIcon(FabricaImagenes.getImageIcon(pieza.getRutaImagen()));
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }
    
    public Casilla() {
        setBorder(BorderFactory.createLineBorder(Color.lightGray));
    }
    public Pieza getPieza(){
        return pieza;
    }
    public void setSeleccionada() {
        setBorder(BorderFactory.createLineBorder(Color.MAGENTA));
    }
    public void setPieza(Pieza pieza2) {
        this.pieza = pieza2;	
        if(pieza2 != null)
            setIcon(FabricaImagenes.getImageIcon(pieza.getRutaImagen()));
        else
            setIcon(null);
    }
    public void deSeleccionar() {
        setBorder(BorderFactory.createLineBorder(Color.lightGray));
    }
    public void vaciar() {
        deSeleccionar();
        this.pieza = null;
        setIcon(null);
    }
}