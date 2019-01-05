/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador.Requisiciones;

import interfaces.requerimientos_ifz;
import javax.swing.JFrame;

/**
 *
 * @author Soxtec Desarrollo
 */
public class Requisiciones {
    
    public void lista(){
        
        requerimientos_ifz V=new requerimientos_ifz();
        V.setVisible(true);
        V.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
    }
    
    
}
