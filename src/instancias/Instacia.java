package instancias;


import serviciosBD.persona_servicio;
import serviciosBD.usuario_servicio;
import serviciosBD.factura_servicio;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Abraham
 */
public class Instacia {
    
    
    public static usuario_servicio usuario_srv;
    public static persona_servicio persona_srv;
    public static factura_servicio factura_srv;

   

    public Instacia() {
        usuario_srv=new usuario_servicio();
        persona_srv=new persona_servicio();
        factura_srv=new factura_servicio();
        
     
       
    }
    
    
    
    
}
