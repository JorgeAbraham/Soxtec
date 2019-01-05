/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador.Nomina;

import interfaces.tarjetas_ifz;
import javax.swing.JFrame;

/**
 *
 * @author Soxtec Desarrollo
 */
public class Tarjetas {
    
    
    public void cuentasNomina(){
        
        tarjetas_ifz V=new tarjetas_ifz();
        V.setVisible(true);
        V.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
    }
}
