/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Menu;



import static Menu.Funciones.loggedIn;
import static Menu.Funciones.loggedIn2;
import java.util.Calendar;
import piezas.Rey;


/**
 *
 * @author jrmartinez
 */
public class Player {
    protected String pass, user;
    protected int puntos;
    protected Calendar dia;
    protected boolean activo;
   
    public Player(String u, String p){
        user = u;
        pass = p;
        puntos=0;
        dia = Calendar.getInstance();
        activo=true;
    }
    
    public void isActiva(){
        Calendar hace6 = Calendar.getInstance();
        hace6.add(Calendar.MONTH, -6);
        activo = dia.after(hace6);
    }    
    
    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Player))
                return false;
        return ((Player) obj).esBlanco() == this.esBlanco();
    }
    private boolean esBlanco;	
    private Rey miRey;

    public Player(boolean b) {
        user = loggedIn;
        puntos=0;
        dia = Calendar.getInstance();
        activo=true;
        this.esBlanco = b;
    }

    public Player() {
        user = loggedIn2;
        puntos=0;
        dia = Calendar.getInstance();
        activo=true;
    }

    public boolean esBlanco(){
        
        return esBlanco;
    }

    public String toString(){
        return esBlanco ? "Jugador Blanco" : "Jugador Negro";
    }
    public void setMiRey(Rey miRey) {
        this.miRey = miRey;
    }

    public Rey getMiRey() {
        return miRey;
    }

    public String getUser() {
        return user;
    }
    
    
    
}
