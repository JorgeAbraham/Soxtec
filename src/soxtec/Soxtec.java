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
        
        
        try 
        {
                NimRODTheme nt = new NimRODTheme( "lookAndFeel/Night.theme");

                NimRODLookAndFeel nf = new NimRODLookAndFeel();
                nf.setCurrentTheme( nt);
                UIManager.setLookAndFeel( nf);
        }
        catch (Exception e)
        {

        }
        
        
        Instacia I=new Instacia();
        ManejadorDeDatos M=new ManejadorDeDatos();
        acceso_ifz A=new acceso_ifz();
        A.setVisible(true);
    }
    
}
