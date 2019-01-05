/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador.CentroDeCosto;

import interfaces.centroDeCosto_ifz;
import javax.swing.JFrame;

/**
 *
 * @author Soxtec Desarrollo
 */
public class CentroDeCosto {
    
    
    
    public void menuCentrosDeCosto(){
        centroDeCosto_ifz V=new centroDeCosto_ifz();
        V.setVisible(true);
        V.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    
    
    
    
    
}
