package piezas;

public class Peon extends Pieza {

    public Peon(String name, int turno){
        super(name, turno);
    }

    @Override
    public boolean validarMove(int x, int y, int x1, int y1){
        if(turno == 1){//Jugador 1 se mueve
             if(y>4)//Jugador 1 ha cruzado el rio
                return (y==y1 && (((x+1)==x1) || ((x-1)==x1))) || (x==x1 && (y+1)==y1);
            return (x==x1 && (y+1)==y1);
        }
        if(y<5)//Jugador 2 se mueve hacia arriba y ya ha cruzado el rio
            return ((y==y1 && ((x+1)==x1 || (x-1)==x1)) || (x==x1 && (y-1)==y1));
        return (x==x1 && y-1==y1);
    }

    @Override
    public String icon() {
        return "src/img/"+getColor()+"p"+".png";
    }
}