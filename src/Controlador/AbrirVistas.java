/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import instancias.session;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import serviciosBD.peticiones_web_servicio;

/**
 *
 * @author Soxtec Desarrollo
 */
public class AbrirVistas {
    
    
    public void controlador(String nombreControlador){
        peticiones_web_servicio peticion=new peticiones_web_servicio();
        peticion.setNombreVista(nombreControlador);
        peticion.setIdUsuario(session.idUsuario);
        peticion.getVistasPorNombre();
        AbrirVistas abrirVistas = new AbrirVistas(peticion);
    }
    
    public AbrirVistas(){
    }
    
    public AbrirVistas(peticiones_web_servicio peticion){
        
        try {
            try {
                 try {
                     try {
                            //Class userClass = Class.forName("Controlador.Acceso.Acceso");
                            Class userClass = Class.forName(peticion.getControlador());

                            //Si fuera una peticion web y quissieramos inciar el codigo dejariamos esto

                            /*
                                Method metodo = userClass.getMethod("init",HttpServlet.class ,HttpServletRequest.class ,HttpServletResponse.class ,String.class );
                            */
                                Object instancia=userClass.newInstance();
                            /*
                                metodo.invoke(instancia, this , request, response, Base);
                            */


                            Method metodo2 = userClass.getMethod( peticion.getMetodo() );
                            metodo2.invoke(   instancia    );

                         } 
                     catch (InstantiationException | IllegalAccessException ex) {
                         ex.printStackTrace();
                     }
                 } 
                 catch (IllegalArgumentException | InvocationTargetException ex) {
                     ex.printStackTrace();
                 }
             } 
             catch (NoSuchMethodException | SecurityException ex) {
                 ex.printStackTrace();
             }
         } 
         catch (ClassNotFoundException ex) {
             ex.printStackTrace();
         }
        
        
    }
    
    
}
