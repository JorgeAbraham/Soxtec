/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador.Acceso;

import interfaces.menu_ifz;
import javax.swing.JFrame;

/**
 *
 * @author Soxtec Desarrollo
 */
public class Acceso {
    
    
    
    public void ventanaAcceso(){
         
        menu_ifz I=new  menu_ifz();
        I.setVisible(true);
        I.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    
    
}
