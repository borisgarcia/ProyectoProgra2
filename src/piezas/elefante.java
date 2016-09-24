package piezas;


public class elefante extends Pieza {

    public elefante(String name, int turno){
        super(name, turno);
    }

    @Override
    public boolean validarMove(int x, int y, int x1, int y1) {
        return (Math.abs(x - x1) == 2 && Math.abs(y - y1) == 2);
    }

    @Override
    public String icon() {
        return "src/img/"+getColor()+"e"+".png";
    }
}