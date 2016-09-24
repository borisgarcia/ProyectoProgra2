/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tablero;

import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import piezas.Caballo;
import piezas.Peon;
import piezas.Pieza;
import piezas.PiezaVacia;
import piezas.Reina;
import piezas.Rey;
import piezas.Torre;
import piezas.elefante;

public final class Tablero extends JPanel implements Serializable{
    public int currentX, currentY, t;
    boolean chosenPiece = false;
    transient Image background = Toolkit.getDefaultToolkit().createImage("src/Imagenes/tablero.png");
    ImageIcon imgBack = new ImageIcon(background);
    JLabel[][] piezas = new Pieza[8][8];
    GridLayout gridLayout = new GridLayout(8, 8);
    
    public Tablero(){
        initCuadros();
        setBounds(0, 0, 540, 600);
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
               
                else if((y==1 && x==0) ||(y==1 && x==1) || (y==1 && x==2) || (y==1 && x==3) || 
                        (y==1 && x==4) ||(y==1 && x==5) || (y==1 && x==6) || (y==1 && x==7) ||
                        (y==6 && x==0) ||(y==6 && x==1) || (y==6 && x==2) || (y==6 && x==3) || 
                        (y==6 && x==4) ||(y==6 && x==5) || (y==6 && x==6) || (y==6 && x==7))
                    piezas[y][x] = new Peon("Peon", t);
                
                else
                    piezas[y][x] = new PiezaVacia("Ficha Vacia", 0);
                
                contTurnos++;
                piezas[y][x].setIcon(new ImageIcon(((Pieza)piezas[y][x]).icon()));
                piezas[y][x].addMouseListener(new PressedMouse());
                add(piezas[y][x]);
            }
            if(contTurnos == 36)
                changeTurn();
        }changeTurn();
    }
    
    public void changeTurn(){
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
                    if(source==piezas[y][x] && !(source instanceof PiezaVacia) && ((Pieza)piezas[y][x]).turno==t){
                        currentX = x;
                        currentY = y;
                        chosenPiece = true;
                    }
                    else if(source==piezas[y][x] && source instanceof PiezaVacia && chosenPiece){
                        moverPieza(x, y);
                    }
                    else if(source==piezas[y][x] && !(source instanceof PiezaVacia) && chosenPiece){
                        attackPiece(x, y);
                    }
                }
            }
            String user = (t==1 ? Menu.userLogged : Menu.userLogged2);
            Game.setTurnoText(user);
        }
    }
    
   
    public void movementEle(int x, int y){
        if(currentX+2==x && currentY+2==y){
            if(piezas[currentY+1][currentX+1] instanceof PiezaVacia)
                setNewFicha(x, y);
        }

        else if(currentX+2==x && currentY-2==y){
            if(piezas[currentY-1][currentX+1] instanceof PiezaVacia)
                setNewFicha(x, y);
        }

        else if(currentX-2==x && currentY+2==y){
            if(piezas[currentY+1][currentX-1] instanceof PiezaVacia)
                setNewFicha(x, y);
        }

        else if(currentX-2==x && currentY-2==y){
            if(piezas[currentY-1][currentX-1] instanceof PiezaVacia)
                setNewFicha(x, y);
        }
    }
    
    public void movementHorse(int x, int y){
        if((currentX+1==x && currentY+2==y) ||(currentX-1==x && currentY+2==y)){
            if(piezas[currentY+1][currentX] instanceof PiezaVacia)
                setNewFicha(x, y);
        }
        else if((currentX+1==x && currentY-2==y) || (currentX-1==x && currentY-2==y)){
            if(piezas[currentY-1][currentX] instanceof PiezaVacia)
                setNewFicha(x, y);
        }
        else if((currentX+2==x && currentY+1==y) || (currentX+2==x && currentY-1==y)){
            if(piezas[currentY][currentX+1] instanceof PiezaVacia)
                setNewFicha(x, y);
        }
        else if((currentX-2==x && currentY+1==y) || (currentX-2==x && currentY-1==y)){
            if(piezas[currentY][currentX-1] instanceof PiezaVacia)
                setNewFicha(x, y);
        }
    }
    
    public void movementGenAdv(int x, int y){
        setNewFicha(x, y);
    }
    
    public void movementCharCan(int x, int y, int MOA){
        int intermmediatePiece = 0;
        if(x>currentX && currentY==y){
            for(int x1 = currentX+1; x1<x; x1++)
                if(!(piezas[currentY][x1] instanceof PiezaVacia))
                    intermmediatePiece++;
        }
        else if(x<currentX && currentY==y){
            for(int x1 = currentX-1; x1>x; x1--)
                if(!(piezas[currentY][x1] instanceof PiezaVacia))
                    intermmediatePiece++;
        }
        else if(x==currentX && y>currentY){
            for(int y1 = currentY+1; y1<y; y1++)
                if(!(piezas[y1][currentX] instanceof PiezaVacia))
                    intermmediatePiece++;
        }
        else if(x==currentX && y<currentY){
            for(int y1 = currentY-1; y1>y; y1--)
                if(!(piezas[y1][currentX] instanceof PiezaVacia))
                    intermmediatePiece++;
        }
        if(intermmediatePiece==MOA && MOA==1)
            setNewFicha(x, y);
        else if(intermmediatePiece==0 && MOA==0)
            setNewFicha(x, y);
    }
    
    public void movements(int x, int y){
        Pieza tempCurrent = (Pieza)piezas[currentY][currentX];
        Pieza tempNew = (Pieza)piezas[y][x];
        if(piezas[currentY][currentX] instanceof Reina || piezas[currentY][currentX] instanceof Rey)
            movementGenAdv(x, y);

        else if(piezas[currentY][currentX] instanceof elefante)
            movementEle(x, y);

        else if(piezas[currentY][currentX] instanceof Caballo)
            movementHorse(x, y);

        else if((piezas[currentY][currentX] instanceof Torre))
            movementCharCan(x, y, 0);

        else
            setNewFicha(x, y);
        
        if(facingGenerals()){
            piezas[currentY][currentX] = tempCurrent;
            piezas[y][x] = tempNew;
            refresh();
            changeTurn();
        }
    }
    
    public void moverPieza(int x, int y){
        if(((Pieza)piezas[currentY][currentX]).validarMovimiento(currentX, currentY, x, y))
            movements(x, y);
    }
    
    public void attacks(int x, int y){
        Pieza tempCurrent = (Pieza)piezas[currentY][currentX];
        Pieza tempNew = (Pieza)piezas[y][x];
        if(piezas[currentY][currentX] instanceof Reina || piezas[currentY][currentX] instanceof Rey)
            movementGenAdv(x, y);

        else if(piezas[currentY][currentX] instanceof elefante)
            movementEle(x, y);

        else if(piezas[currentY][currentX] instanceof Caballo)
            movementHorse(x, y);

        else if(piezas[currentY][currentX] instanceof Torre)
            movementCharCan(x, y, 0);
        
       else
            setNewFicha(x, y);
        
        if(facingGenerals()){
            piezas[currentY][currentX] = tempCurrent;
            piezas[y][x] = tempNew;
            refresh();
            changeTurn();
        }
    }
    
    public void attackPiece(int x, int y){
        if(((Pieza)piezas[currentY][currentX]).validarMovimiento(currentX, currentY, x, y))
            attacks(x, y);
    }
    
    public boolean facingGenerals(){
        boolean encontrado = false, facing = false;
        for(int y = 0; y<8; y++){
            for(int x = 3; x<8; x++){
                if(piezas[y][x] instanceof Rey){
                    encontrado = true;
                    for(int z = y+1; z<8; z++){
                        if(!(piezas[z][x] instanceof PiezaVacia)){
                            if(piezas[z][x] instanceof Rey)
                                facing = true;
                            else
                                facing = false;
                            break;
                        }
                    }
                }
            }
            if(encontrado) break;
        }
        if(facing)
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
        changeTurn();
        refresh();
        endGame();
    }
    
    public void newFichaVacia(int x, int y){
        piezas[y][x] = new PiezaVacia("Ficha Vacia", t);
        piezas[y][x].addMouseListener(new PressedMouse());
    }
    
    public boolean generalsAlive(){
        int cantGeneral = 0;
        for(int y = 0; y<8; y++)
            for(int x = 0; x<8; x++)
                if(piezas[y][x] instanceof Rey)
                    cantGeneral++;
        if(cantGeneral==2)
            return true;
        return false;
    }
    
    public void endGame(){
        if(!generalsAlive()){
            String winner = (t==1 ? Menu.userLogged2 : Menu.userLogged);
            String loser = (t==1 ? Menu.userLogged : Menu.userLogged2);
            
            String victory = winner+" has won the game against "+loser+". "+winner+" has gained 3 points.";
            
            new File(Menu.path).delete();
            Menu.xia.addPoints(winner, victory);
            Menu.xia.saveLogs(victory);
            Menu.menu.setPanel(new MenuPrincipal());
        }
    }
    
    public static void newGame(String user) throws IOException{
        Menu.userLogged2 = user;
        Menu.path = "Players/"+Menu.userLogged+"/"+Menu.xia.getCode()+"-"+user;
        FileOutputStream file = new FileOutputStream(Menu.path);
        ObjectOutputStream data = new ObjectOutputStream(file);
        data.writeObject(new Tablero());
        Menu.tablero = new Tablero();
        data.close();
        file.close();
    }
    
    public void saveGame() throws IOException{
        FileOutputStream file = new FileOutputStream(Menu.path); 
        ObjectOutputStream data = new ObjectOutputStream(file);
        data.reset();
        data.writeObject(this);
        file.close();
        data.close();
    }
    
    public static Tablero loadGame(String path) throws IOException, ClassNotFoundException{
        String[] game = path.split("-");
        Menu.userLogged2 = game[1];
        String fileName = Menu.xia.getFileName(game);
        Menu.path = "Players/"+Menu.userLogged+"/"+fileName;
        FileInputStream file = new FileInputStream(Menu.path);
        ObjectInputStream data = new ObjectInputStream(file);
        Tablero tab = (Tablero)data.readObject();        
        return tab;
    }
}