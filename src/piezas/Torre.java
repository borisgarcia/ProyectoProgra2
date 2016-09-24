package piezas;


public class Torre extends Pieza {
    public Torre(String name, int turno){
        super(name, turno);
    }

    @Override
    public boolean validarMove(int x, int y, int x1, int y1){
         return (y==y1 && (x>x1 || x<x1)) || (x==x1 && (y>y1 || y<y1));
    }

    @Override
    public String icon() {
        return  "src/img/"+getColor()+"r"+".png";
    }
}