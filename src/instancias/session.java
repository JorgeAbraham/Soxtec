/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package instancias;

/**
 *
 * @author Soxtec Desarrollo
 */
public class session {
    
    
    public static String idUsuario="";

    
    public static Object getAttribute(String Attribute){
        
        Object R=null;
        
        if (Attribute.equals("idUsuario")){
            R=idUsuario;
        }
        
        return R;
        
    }
    
}
