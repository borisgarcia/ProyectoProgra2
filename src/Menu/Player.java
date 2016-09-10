/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Menu;


import java.util.Calendar;


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

    public String getPass() {
        return pass;
    }

    public String getUser() {
        return user;
    }

    public int getPuntos() {
        return puntos;
    }

    public Calendar getDia() {
        return dia;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
       
    public int GuardarPuntos(int x){
        return puntos += x; 
    }
    
    private void refreshUltimoUso(){
       dia = Calendar.getInstance(); 
    }
    
    public void isActiva(){
        Calendar hace6 = Calendar.getInstance();
        hace6.add(Calendar.MONTH, -6);
        activo = dia.after(hace6);
    }     
    
}
