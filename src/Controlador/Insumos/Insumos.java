/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador.Insumos;

import interfaces.insumos_ifz;
import javax.swing.JFrame;

/**
 *
 * @author Soxtec Desarrollo
 */
public class Insumos {
    
    
    public void menuInsumos(){
        
        insumos_ifz insumos=new insumos_ifz();
        insumos.setVisible(true);
        insumos.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
    }
    
    
}
