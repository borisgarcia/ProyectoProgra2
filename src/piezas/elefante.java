package piezas;

public class elefante extends Pieza {

    public elefante(String name, int turno) {
        super(name,turno);
    }

    @Override
    public boolean validarMovimiento(int x, int y, int x1, int y1){
        return Math.abs(x - x1) == 1 && Math.abs(y - y1) == 1;
    }

    @Override
    public String icon() {
        return "src/img/"+getColor()+"e"+".png";
    }
}