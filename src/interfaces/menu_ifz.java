/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import Controlador.AbrirVistas;
import instancias.Instacia;
import instancias.session;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.StringTokenizer;
import javax.swing.JButton;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import serviciosBD.peticiones_web_servicio;

/**
 *
 * @author Abraham
 */
public class menu_ifz extends javax.swing.JFrame {

    
    JButton botonesDeMenu[];
    String menu[][];
   
    
    public menu_ifz() {
        initComponents();
        
        
        
        lbl_nombre.setText(Instacia.persona_srv.getNombre()+" "+Instacia.persona_srv.getApellidoPaterno()+" "+Instacia.persona_srv.getApellidoMaterno());
        
        
        DefaultMutableTreeNode SoxtecRoot = new DefaultMutableTreeNode("Soxtec");
        DefaultTreeModel modelo = new DefaultTreeModel(SoxtecRoot);
        
        jTree1.setModel(modelo);
      
        
        
        
        serviciosBD.peticiones_web_servicio vista=new peticiones_web_servicio();
        vista.setIdTipoVista(peticiones_web_servicio.menuPrincipal);
        vista.setIdUsuario(  (String)session.getAttribute("idUsuario")    );
        vista.setIdTipoVista(peticiones_web_servicio.menuPrincipal);
        
        String idUsuario=(String)session.getAttribute("idUsuario");
        
        String MenusPadre[][]=vista.getVistasPoridTipoVista(peticiones_web_servicio.menuSecundario+"",idUsuario,"1");
        
        String RelacionesMenu[][]=vista.getVistasPorRelacion("1");
        
        
        for (int i=0;i<MenusPadre.length;i++){
            
            DefaultMutableTreeNode nodoPadre = new DefaultMutableTreeNode(MenusPadre[i][5]);
            modelo.insertNodeInto(nodoPadre,SoxtecRoot,i);
            
            
            for (int j=0 ;j<RelacionesMenu.length;j++){
                if (RelacionesMenu[j][0].equals(MenusPadre[i][0])){
                    
                    
                    DefaultMutableTreeNode nodoHijo = new DefaultMutableTreeNode(RelacionesMenu[j][3]);
                    modelo.insertNodeInto(nodoHijo,nodoPadre,nodoPadre.getChildCount());
                   
                   
                    
                }
            }
            
        } 
        
        
        
        
        
        
     
        
        EventoControlador eventoEntrar=new EventoControlador();
        jTree1.addMouseListener(eventoEntrar);
        
        
       
    }

   
    
    
    public class EventoControlador implements MouseListener{

        String nombreControlador;

        public EventoControlador( String nombreControlador) {
            this.nombreControlador=nombreControlador;
        }

        private EventoControlador() {
            
        }

     
        @Override
        public void mouseClicked(MouseEvent e) {
            
            
          
         
            
            if(e.getClickCount()==2){
                    TreePath tp;
                    tp = jTree1.getPathForLocation(e.getX(), e.getY());

                    if (tp != null){
                       
                        String nodoSeleccionado=jTree1.getSelectionPath()+"";
                        
                        StringTokenizer tok=new StringTokenizer( nodoSeleccionado,",]",false   );

                        
                        
                        if (tok.countTokens()==3){
                        
                            tok.nextToken();
                            tok.nextToken();
                            String aliasVista=tok.nextToken();
                            aliasVista=aliasVista.substring(1, aliasVista.length());
                            peticiones_web_servicio peticion=new peticiones_web_servicio();
                            String Vista[][]=peticion.getVistasPorAliasVisible(aliasVista);
                            
                           
                            
                            AbrirVistas vista=new AbrirVistas();
                            vista.controlador(Vista[0][1]);
                        
                        }
                        
                        
                        
                    }
                    
            }
            
            
        }

        @Override
        public void mousePressed(MouseEvent e) {
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {
        }

        @Override
        public void mouseExited(MouseEvent e) {
        }

       
        
    }
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane3 = new javax.swing.JScrollPane();
        jTree1 = new javax.swing.JTree();
        jLabel1 = new javax.swing.JLabel();
        lbl_nombre = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jScrollPane3.setViewportView(jTree1);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Welcome");

        lbl_nombre.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbl_nombre.setText("jLabel2");

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/mundo.jpg"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lbl_nombre, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 401, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(51, 51, 51))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 723, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lbl_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3))
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 639, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 11, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTree jTree1;
    private javax.swing.JLabel lbl_nombre;
    // End of variables declaration//GEN-END:variables
}
