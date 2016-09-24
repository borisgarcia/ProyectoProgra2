package piezas;

public class Rey extends Pieza {
   
    public Rey(String name, int turno){
        super(name, turno);
    }

    @Override
    public boolean validarMove(int x, int y, int x1, int y1) {
        if (Math.abs(x - x1) == 1 && (y - y1) == 0
        || Math.abs(x - x1) == 1 && Math.abs(y - y1) == 1
            || (x - x1) == 0 && Math.abs(y - y1) == 1)
                return true;
        return false;
    }
    

    @Override
    public String icon(){
        return "src/img/"+getColor()+"k"+".png";
    }
    

    
}
