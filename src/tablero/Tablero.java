/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tablero;

import static Menu.Funciones.loggedIn;
import static Menu.Funciones.loggedIn2;
import Menu.MenuPrincipal;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.Serializable;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import piezas.Caballo;
import piezas.FichaVacia;
import piezas.Peon;
import piezas.Pieza;
import piezas.Reina;
import piezas.Rey;
import piezas.Torre;
import piezas.elefante;

/**
 *
 * @author Boris
 */
public class Tablero extends JPanel implements Serializable{
    public int currentX, currentY, t;
    boolean chosenPiece = false;
    transient Image background = Toolkit.getDefaultToolkit().createImage("src/img/tablero.GIF");
    ImageIcon imgBack = new ImageIcon(background);
    JLabel[][] piezas = new Pieza[8][8];
    GridLayout gridLayout = new GridLayout(8, 8);
    
    public Tablero(){
        System.out.println("Hola");
        initCuadros();
        setBounds(0, 0, 486, 485);
    }
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(imgBack.getImage(), 0, 0, this);
    }
    
    public final void initCuadros(){
        setLayout(gridLayout);
        int contTurnos = 0;
        t = 1;
        for(int y = 0; y<8; y++){
            for(int x = 0; x<8; x++){
                if((y==0 && x==0) || (y==0 && x==7) || (y==7 && x==0) || (y==7 && x==7))
                    piezas[y][x] = new Torre("Torre", t);
                
                else if((y==0 && x==1) || (y==0 && x==6) || (y==7 && x==1) || (y==7 && x==6))
                    piezas[y][x] = new Caballo("Caballo", t);
                
                else if((y==0 && x==2) || (y==0 && x==5) || (y==7 && x==2) || (y==7 && x==5))
                    piezas[y][x] = new elefante("Elefante", t);
                
                else if((y==0 && x==3) || (y==7 && x==4))
                    piezas[y][x] = new Reina("Reina", t);
                
                else if((y==0 && x==4) || (y==7 && x==3))
                    piezas[y][x] = new Rey("Rey", t);
                
                else if((y==1 && x==0) || (y==1 && x==1) || (y==1 && x==2) || (y==1 && x==3)|| 
                        (y==1 && x==4) || (y==1 && x==5) || (y==1 && x==6) || (y==1 && x==7)||
                        (y==6 && x==0) || (y==6 && x==1) || (y==6 && x==2) || (y==6 && x==3)|| 
                        (y==6 && x==4) || (y==6 && x==5) || (y==6 && x==6) || (y==6 && x==7))
                    piezas[y][x] = new Peon("Peon", t);
                
                else
                    piezas[y][x] = new FichaVacia("Ficha Vacia", 0);
                
                contTurnos++;
                piezas[y][x].setIcon(new ImageIcon(((Pieza)piezas[y][x]).icon()));
                piezas[y][x].addMouseListener(new PressedMouse());
                add(piezas[y][x]);
                
            }
            if(contTurnos == 36)
                Turnos();
        }Turnos();
    }

    private void Turnos() {
        if(t==1)
            t = 2;
        else
            t = 1;
    }
    
    class PressedMouse implements MouseListener, Serializable{
        @Override
        public void mousePressed(MouseEvent me) {}
        @Override
        public void mouseReleased(MouseEvent me) {}
        @Override
        public void mouseEntered(MouseEvent me) {}
        @Override
        public void mouseExited(MouseEvent me) {}
        @Override
        public void mouseClicked(MouseEvent me) {
            Object source = me.getSource();
            for(int y = 0; y<10; y++){
                for(int x = 0; x<9; x++){
                    if(source==piezas[y][x] && !(source instanceof FichaVacia) && ((Pieza)piezas[y][x]).turno==t){
                        currentX = x;
                        currentY = y;
                        chosenPiece = true;
                    }
                    else if(source==piezas[y][x] && source instanceof FichaVacia && chosenPiece){
                        moverPieza(x, y);
                    }
                    else if(source==piezas[y][x] && !(source instanceof FichaVacia) && chosenPiece){
                        attackPiece(x, y);
                    }
                }
            }
            String user = (t==1 ? loggedIn : loggedIn2);
            
        }
    }
    
   public void movimientoReyReina(int x, int y){
        setNewFicha(x, y);
    }
    
    public void movimientoEle(int x, int y){
        if(currentX+2==x && currentY+2==y){
            if(piezas[currentY+1][currentX+1] instanceof FichaVacia)
                setNewFicha(x, y);
        }

        else if(currentX+2==x && currentY-2==y){
            if(piezas[currentY-1][currentX+1] instanceof FichaVacia)
                setNewFicha(x, y);
        }

        else if(currentX-2==x && currentY+2==y){
            if(piezas[currentY+1][currentX-1] instanceof FichaVacia)
                setNewFicha(x, y);
        }

        else if(currentX-2==x && currentY-2==y){
            if(piezas[currentY-1][currentX-1] instanceof FichaVacia)
                setNewFicha(x, y);
        }
    }
    
    public void movimientoCaballo(int x, int y){
        if((currentX+1==x && currentY+2==y) ||(currentX-1==x && currentY+2==y)){
            if(piezas[currentY+1][currentX] instanceof FichaVacia)
                setNewFicha(x, y);
        }
        else if((currentX+1==x && currentY-2==y) || (currentX-1==x && currentY-2==y)){
            if(piezas[currentY-1][currentX] instanceof FichaVacia)
                setNewFicha(x, y);
        }
        else if((currentX+2==x && currentY+1==y) || (currentX+2==x && currentY-1==y)){
            if(piezas[currentY][currentX+1] instanceof FichaVacia)
                setNewFicha(x, y);
        }
        else if((currentX-2==x && currentY+1==y) || (currentX-2==x && currentY-1==y)){
            if(piezas[currentY][currentX-1] instanceof FichaVacia)
                setNewFicha(x, y);
        }
    }
    
    public void movimientoTorre(int x, int y, int MOA){
        int intermmediatePiece = 0;
        if(x>currentX && currentY==y){
            for(int x1 = currentX+1; x1<x; x1++)
                if(!(piezas[currentY][x1] instanceof FichaVacia))
                    intermmediatePiece++;
        }
        else if(x<currentX && currentY==y){
            for(int x1 = currentX-1; x1>x; x1--)
                if(!(piezas[currentY][x1] instanceof FichaVacia))
                    intermmediatePiece++;
        }
        else if(x==currentX && y>currentY){
            for(int y1 = currentY+1; y1<y; y1++)
                if(!(piezas[y1][currentX] instanceof FichaVacia))
                    intermmediatePiece++;
        }
        else if(x==currentX && y<currentY){
            for(int y1 = currentY-1; y1>y; y1--)
                if(!(piezas[y1][currentX] instanceof FichaVacia))
                    intermmediatePiece++;
        }
        if(intermmediatePiece==MOA && MOA==1)
            setNewFicha(x, y);
        else if(intermmediatePiece==0 && MOA==0)
            setNewFicha(x, y);
    }
    
    public void movements(int x, int y){
        Pieza temp = (Pieza)piezas[currentY][currentX];
        Pieza tempNuevo = (Pieza)piezas[y][x];
        if(piezas[currentY][currentX] instanceof Reina || piezas[currentY][currentX] instanceof Rey)
            movimientoReyReina(x, y);

        else if(piezas[currentY][currentX] instanceof elefante)
            movimientoEle(x, y);

        else if(piezas[currentY][currentX] instanceof Caballo)
            movimientoCaballo(x, y);

        else if((piezas[currentY][currentX] instanceof Torre))
            movimientoTorre(x, y, 0);

        else
            setNewFicha(x, y);
        
        if(facing()){
            piezas[currentY][currentX] = temp;
            piezas[y][x] = tempNuevo;
            refresh();
            Turnos();
        }
    }
    
    public void moverPieza(int x, int y){
        if(((Pieza)piezas[currentY][currentX]).validarMove(currentX, currentY, x, y))
            movements(x, y);
    }
    
    public void attacks(int x, int y){
        Pieza temp = (Pieza)piezas[currentY][currentX];
        Pieza tempNuevo = (Pieza)piezas[y][x];
        if(piezas[currentY][currentX] instanceof Reina || piezas[currentY][currentX] instanceof Rey)
            movimientoReyReina(x, y);

        else if(piezas[currentY][currentX] instanceof elefante)
            movimientoEle(x, y);

        else if(piezas[currentY][currentX] instanceof Caballo)
            movimientoCaballo(x, y);

        else if(piezas[currentY][currentX] instanceof Torre)
            movimientoTorre(x, y, 0);
        
        else
            setNewFicha(x, y);
        
        if(facing()){
            piezas[currentY][currentX] = temp;
            piezas[y][x] = tempNuevo;
            refresh();
            Turnos();
        }
    }
    
    public void attackPiece(int x, int y){
        if(((Pieza)piezas[currentY][currentX]).validarMove(currentX, currentY, x, y))
            attacks(x, y);
    }
    
    public boolean facing(){
        boolean encontrado = false, f = false;
        for(int y = 0; y<8; y++){
            for(int x = 0; x<8; x++){
                if(piezas[y][x] instanceof Rey){
                    encontrado = true;
                    for(int z = y+1; z<8; z++){
                        if(!(piezas[z][x] instanceof FichaVacia)){
                            if(piezas[z][x] instanceof Rey)
                                f = true;
                            else
                                f = false;
                            break;
                        }
                    }
                }
            }
            if(encontrado) break;
        }
        if(f)
            return true;
        return false;
    }
    
    public void refresh(){
        removeAll();
        for(int y = 0; y<8; y++){
            for(int x = 0; x<8; x++){
                piezas[y][x].addMouseListener(new PressedMouse());
                add(piezas[y][x]);
            }
        }
        revalidate();
    }
    
    public void setNewFicha(int x, int y){
        piezas[y][x] = piezas[currentY][currentX];
        newFichaVacia(currentX, currentY);
        chosenPiece = false;
        Turnos();
        refresh();
        endGame();
    }
    
    public void newFichaVacia(int x, int y){
        piezas[y][x] = new FichaVacia("Ficha Vacia", t);
        piezas[y][x].addMouseListener(new PressedMouse());
    }
    
    public boolean ReyesVivos(){
        int cant= 0;
        for(int y = 0; y<8; y++)
            for(int x = 0; x<8; x++)
                if(piezas[y][x] instanceof Rey)
                    cant++;
        if(cant==2)
            return true;
        return false;
    }
    
    public void endGame(){
        if(!ReyesVivos()){
            String ganador = (t==1 ? loggedIn2 : loggedIn);
            String perdedor = (t==1 ? loggedIn : loggedIn2);
            
            String victoria = ganador+" has won the game against "+perdedor+". "+ganador+" has gained 3 points.";
            
            MenuPrincipal t = new MenuPrincipal();
            t.setVisible(true);
        }
    }
}
