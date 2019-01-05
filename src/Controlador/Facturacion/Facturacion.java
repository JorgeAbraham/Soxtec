/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador.Facturacion;


import interfaces.estado_facturacion_ifz;
import interfaces.factura_ifz;
import interfaces.gqrs_ifz;
import javax.swing.JFrame;

/**
 *
 * @author Soxtec Desarrollo
 */
public class Facturacion {

    
    
    public void facturacion(){
        factura_ifz V=new factura_ifz();
        V.setVisible(true);
        V.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    
     
     public void facturacionEstatus(){
        estado_facturacion_ifz V=new estado_facturacion_ifz();
        V.setVisible(true);
        V.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    
     
      public void facturacionGQRS(){
        gqrs_ifz V=new gqrs_ifz();
        V.setVisible(true);
        V.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    
     
}
