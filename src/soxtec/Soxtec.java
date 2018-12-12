/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soxtec;

import com.nilo.plaf.nimrod.NimRODLookAndFeel;
import com.nilo.plaf.nimrod.NimRODTheme;
import instancias.Instacia;
import interfaces.acceso_ifz;
import javax.swing.UIManager;
import serviciosBD.ManejadorDeDatos;

/**
 *
 * @author Abraham
 */
public class Soxtec {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        
        
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Soxtec.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Soxtec.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Soxtec.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Soxtec.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
        
        Instacia I=new Instacia();
        ManejadorDeDatos M=new ManejadorDeDatos(/*ManejadorDeDatos.IP_LOCAL*/);
        acceso_ifz A=new acceso_ifz();
        A.setVisible(true);
     
    }
    
}
