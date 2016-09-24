package piezas;

public final class FichaVacia extends Pieza{
    public FichaVacia(String name, int turno){
        super(name, turno);
    }
    @Override
    public boolean validarMove(int x, int y, int x1, int y1) {
        return false;
    }

    @Override
    public String icon() {
        return "src/img/SinFondo.png";
    }
    
  
}
