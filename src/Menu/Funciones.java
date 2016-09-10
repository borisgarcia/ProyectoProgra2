/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Menu;

import static Menu.MenuInicio.users;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author Boris
 */
public class Funciones extends javax.swing.JFrame {
    public static final String ROOT = "chaturanga";
    
    public static String loggedIn, loggedIn2; 
    
    public static boolean Verificar(String p) throws IOException{
        users.seek(0);        
        while(users.getFilePointer() < users.length()){
            String username = users.readUTF();
            long pos = users.getFilePointer();
            users.readUTF();
            users.skipBytes(4);
            users.skipBytes(8);
            if(users.readBoolean() && p.equalsIgnoreCase(username)){               
                users.seek(pos);
                return true;
            }         
        }
        return false;
    }
    
    public boolean pass(String pass){
        if(pass.length()==5)          
            return true;       
        JOptionPane.showMessageDialog(rootPane, "Contrasena debe ser de 5 caracteres");
        return false;
    }
    
    public static boolean AgregarPlayer(String u, String p) throws IOException{
        if(!Verificar(u)){
            users.writeUTF(u);
            users.writeUTF(p);
            users.writeInt(0);
            users.writeLong(new Date().getTime());
            users.writeBoolean(true);
            return true;
        }
        return false;
    }
    
    public static boolean LogIn(String u, String p) throws IOException{
        if(Verificar(u)){
            String pass = users.readUTF();
            if(pass.equalsIgnoreCase(p)){
                loggedIn = u;
                return true;
            }
            
        }
        return false;
    }
    
    public static boolean LogIn2(String u) throws IOException{
        if(Verificar(u) && !u.equals(loggedIn)){
            return true;
        }
        return false;
    }
    
    public static boolean CambiarPassword(String a, String b) throws IOException{
        if(Verificar(loggedIn)){
            long pos = users.getFilePointer();
            String pass = users.readUTF();
            if(pass.equals(a)){
                users.seek(pos);
                users.writeUTF(b);
            }
            return true;
        }
        return false;
    }
}
