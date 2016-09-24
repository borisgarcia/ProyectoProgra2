/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package piezas;

/**
 *
 * @author Boris
 */
public final class PiezaVacia extends Pieza{
    public PiezaVacia(String name, int turno){
        super(name, turno);
    }
    @Override
    public boolean validarMovimiento(int x, int y, int x1, int y1) {
        return false;
    }

    @Override
    public String icon() {
        return "src/img/SinFondo.png";
    }
    
}
