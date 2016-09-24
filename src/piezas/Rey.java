package piezas;


public class Rey extends Pieza {
   
    public Rey(String name, int turno){
        super(name, turno);
    }

    @Override
    public boolean validarMovimiento(int x, int y, int x1, int y1) {
        return ((x==x1 && (y+1==y1) || y-1==y1) || (y==y1 && (x+1==x1 || x-1==x1)));
    }

    @Override
    public String icon() {
        return "src/img/"+getColor()+"k"+".png";
    }
}
