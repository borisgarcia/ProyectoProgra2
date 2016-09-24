package piezas;

public class Caballo extends Pieza {

    public Caballo(String name, int turno){
        super(name, turno);
    }

    @Override
    public boolean validarMove(int x, int y, int x1, int y1) {
        return ((x+1)==x1 && ((y+2)==y1 || (y-2)==y1)) || ((x-1)==x1 && ((y+2)==y1 || (y-2)==y1)) || 
               ((y+1)==y1 && ((x+2)==x1 || (x-2)==x1)) || ((y-1)==y1 && ((x+2)==x1 || (x-2)==x1));
    }

    @Override
    public String icon() {
        return "src/img/"+getColor()+"n"+".png";
    }


}
