/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador.Reportes;

import interfaces.reportesGerenciales_ifz;
import interfaces.tarjetas_ifz;
import javax.swing.JFrame;

/**
 *
 * @author Soxtec Desarrollo
 */
public class GraficaBarrasEgresos {
    
    public void ver(){
        
        
        reportesGerenciales_ifz V=new reportesGerenciales_ifz();
        V.setVisible(true);
        V.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      
        
    }
    
    
}
