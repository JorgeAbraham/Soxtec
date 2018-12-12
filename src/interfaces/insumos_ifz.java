/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import serviciosBD.catalogo_servicio;

/**
 *
 * @author Soxtec Desarrollo
 */
public class insumos_ifz extends javax.swing.JFrame {

    /**
     * Creates new form insumos_ifz
     */
    public insumos_ifz() {
       
        initComponents();
        iniciarCatalogoDeTipoDeInsumo();
       
    }
    
    
 
    
    
    public void iniciarCatalogoDeTipoDeInsumo(){
        
        catalogo_servicio  catalogo= new catalogo_servicio();
        tiposInsumoTBL.setModel(catalogo.listaPorTipoCatalogoModel(17));
        

        redimenciona1();
        redimenciona2();
        redimenciona3();
        
    }
    
    
    public void redimenciona1(){
        tiposInsumoTBL.getColumnModel().getColumn(0).setMaxWidth(0);
        tiposInsumoTBL.getColumnModel().getColumn(0).setMinWidth(0);
        tiposInsumoTBL.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(0);
        tiposInsumoTBL.getTableHeader().getColumnModel().getColumn(0).setMinWidth(0);
    }
    
    public void redimenciona2(){
        gruposDeInsumoTBL.getColumnModel().getColumn(0).setMaxWidth(0);
        gruposDeInsumoTBL.getColumnModel().getColumn(0).setMinWidth(0);
        gruposDeInsumoTBL.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(0);
        gruposDeInsumoTBL.getTableHeader().getColumnModel().getColumn(0).setMinWidth(0);
    }
    
    public void redimenciona3(){
        insumosTBL.getColumnModel().getColumn(0).setMaxWidth(0);
        insumosTBL.getColumnModel().getColumn(0).setMinWidth(0);
        insumosTBL.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(0);
        insumosTBL.getTableHeader().getColumnModel().getColumn(0).setMinWidth(0);
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        gruposDeInsumoTBL = new javax.swing.JTable();
        jScrollPane5 = new javax.swing.JScrollPane();
        tiposInsumoTBL = new javax.swing.JTable();
        jScrollPane6 = new javax.swing.JScrollPane();
        insumosTBL = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("Configuración de Insumos");

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/logoSoxtec.jpg"))); // NOI18N

        jLabel4.setText("Tipos de Insumo");

        jLabel5.setText("Grupos de Insumo");

        jLabel6.setText("Insumos");

        gruposDeInsumoTBL.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        gruposDeInsumoTBL.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                gruposDeInsumoTBLMouseReleased(evt);
            }
        });
        jScrollPane4.setViewportView(gruposDeInsumoTBL);

        tiposInsumoTBL.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tiposInsumoTBL.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tiposInsumoTBLMouseReleased(evt);
            }
        });
        jScrollPane5.setViewportView(tiposInsumoTBL);

        insumosTBL.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane6.setViewportView(insumosTBL);

        jButton1.setText("+");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("+");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("+");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("x");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("x");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setText("x");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel2))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(106, 106, 106))
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(jButton1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
                            .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(jLabel5)
                            .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 184, Short.MAX_VALUE)
                            .addComponent(jButton5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 184, Short.MAX_VALUE)
                                .addComponent(jLabel6)
                                .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(26, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addComponent(jLabel1)
                        .addGap(69, 69, 69)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)))
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 323, Short.MAX_VALUE)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton4)
                    .addComponent(jButton5)
                    .addComponent(jButton6))
                .addGap(29, 29, 29))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tiposInsumoTBLMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tiposInsumoTBLMouseReleased


        if (tiposInsumoTBL.getSelectedRow()!=-1){
            catalogo_servicio  catalogo= new catalogo_servicio();
            gruposDeInsumoTBL.setModel(catalogo.listaPorTipoCatalogoYRelacionModel(tiposInsumoTBL.getValueAt(tiposInsumoTBL.getSelectedRow(), 0)+"",1));
            
            insumosTBL.setModel(new DefaultTableModel());
        }
        
        try{
        
            redimenciona1();
            redimenciona2();
            redimenciona3();
        }catch(Exception e){}
        
    }//GEN-LAST:event_tiposInsumoTBLMouseReleased

    private void gruposDeInsumoTBLMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_gruposDeInsumoTBLMouseReleased
        if (gruposDeInsumoTBL.getSelectedRow()!=-1){
            catalogo_servicio  catalogo= new catalogo_servicio();
            insumosTBL.setModel(catalogo.listaPorTipoCatalogoYRelacionModel(gruposDeInsumoTBL.getValueAt(gruposDeInsumoTBL.getSelectedRow(), 0)+"",2));
        }
        
        try{
        
            redimenciona1();
            redimenciona2();
            redimenciona3();
        }catch(Exception e){}
        
    }//GEN-LAST:event_gruposDeInsumoTBLMouseReleased

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        
        
        if(gruposDeInsumoTBL.getSelectedRow()!=-1){
        
            String respuesta = JOptionPane.showInputDialog(null, "Escribe Insumo");
            if (respuesta!=null ){
                if (!respuesta.equals("") ){
                    catalogo_servicio  catalogo= new catalogo_servicio();
                    String UltimoInsertado=catalogo.insertaCatalogo(respuesta, "19");
                    catalogo.insertaCatalogosConRelacion(gruposDeInsumoTBL.getValueAt(gruposDeInsumoTBL.getSelectedRow(), 0)+"",UltimoInsertado,"2");
                    
                    
                    insumosTBL.setModel(catalogo.listaPorTipoCatalogoYRelacionModel(gruposDeInsumoTBL.getValueAt(gruposDeInsumoTBL.getSelectedRow(), 0)+"",2));
                    
                }
            }
        }
        
        
        try{
        
            redimenciona1();
            redimenciona2();
            redimenciona3();
        }catch(Exception e){}
        
        
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        
        if (insumosTBL.getSelectedRow()!=-1){
            String idInsumo=insumosTBL.getValueAt(insumosTBL.getSelectedRow(), 0)+"";
            catalogo_servicio catalogo=new catalogo_servicio();
            catalogo.borraCatalogoRelacionado(2, idInsumo, "2");
            catalogo.borraCatalogo(idInsumo);
            
            insumosTBL.setModel(catalogo.listaPorTipoCatalogoYRelacionModel(gruposDeInsumoTBL.getValueAt(gruposDeInsumoTBL.getSelectedRow(), 0)+"",2));
        }
        
        try{
        
            redimenciona1();
            redimenciona2();
            redimenciona3();
        }catch(Exception e){}
        
        
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed

        if (gruposDeInsumoTBL.getSelectedRow()!=-1){

            String idInsumo=gruposDeInsumoTBL.getValueAt(gruposDeInsumoTBL.getSelectedRow(), 0)+"";

            catalogo_servicio catalogo=new catalogo_servicio();
            DefaultTableModel DF=catalogo.listaPorTipoCatalogoYRelacionModel(idInsumo,2);

            if(DF.getRowCount()<=0){

                catalogo.borraCatalogoRelacionado(2, idInsumo, "1");
                catalogo.borraCatalogo(idInsumo);

                gruposDeInsumoTBL.setModel(catalogo.listaPorTipoCatalogoYRelacionModel(tiposInsumoTBL.getValueAt(tiposInsumoTBL.getSelectedRow(), 0)+"",1));

            }else{
                JOptionPane.showMessageDialog(null,"El elemento esta relacionado, primero borre los elementos relacionados","Error",JOptionPane.ERROR_MESSAGE);
            }
        }
        
        
        try{
        
            redimenciona1();
            redimenciona2();
            redimenciona3();
        }catch(Exception e){}

    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        if (tiposInsumoTBL.getSelectedRow()!=-1){

            String idInsumo=tiposInsumoTBL.getValueAt(tiposInsumoTBL.getSelectedRow(), 0)+"";

            catalogo_servicio catalogo=new catalogo_servicio();
            DefaultTableModel DF=catalogo.listaPorTipoCatalogoYRelacionModel(idInsumo,1);

            if(DF.getRowCount()<=0){

                catalogo.borraCatalogoRelacionado(1, idInsumo, "1");
                catalogo.borraCatalogo(idInsumo);

                tiposInsumoTBL.setModel(catalogo.listaPorTipoCatalogoModel(17));

            }else{
                JOptionPane.showMessageDialog(null,"El elemento esta relacionado, primero borre los elementos relacionados","Error",JOptionPane.ERROR_MESSAGE);
            }
        }
        
        
        try{
        
            redimenciona1();
            redimenciona2();
            redimenciona3();
        }catch(Exception e){}
        
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        if(tiposInsumoTBL.getSelectedRow()!=-1){

            String respuesta = JOptionPane.showInputDialog(null, "Escribe el Grupo de Insumo");
            if (respuesta!=null ){
                if (!respuesta.equals("") ){
                    catalogo_servicio  catalogo= new catalogo_servicio();
                    String UltimoInsertado=catalogo.insertaCatalogo(respuesta, "18");
                    catalogo.insertaCatalogosConRelacion(tiposInsumoTBL.getValueAt(tiposInsumoTBL.getSelectedRow(), 0)+"",UltimoInsertado,"1");

                    gruposDeInsumoTBL.setModel(catalogo.listaPorTipoCatalogoYRelacionModel(tiposInsumoTBL.getValueAt(tiposInsumoTBL.getSelectedRow(), 0)+"",1));

                }
            }
        }
        
        
        try{
        
            redimenciona1();
            redimenciona2();
            redimenciona3();
        }catch(Exception e){}
        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        catalogo_servicio  catalogo= new catalogo_servicio();

        String respuesta = JOptionPane.showInputDialog(null, "Escribe el Tipo de Insumo");
        if (respuesta!=null ){
            if (!respuesta.equals("") ){

                catalogo.insertaCatalogo(respuesta, "17");

            }
        }

        tiposInsumoTBL.setModel(catalogo.listaPorTipoCatalogoModel(17));
        
        
        try{
        
            redimenciona1();
            redimenciona2();
            redimenciona3();
        }catch(Exception e){}
        
    }//GEN-LAST:event_jButton1ActionPerformed

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable gruposDeInsumoTBL;
    private javax.swing.JTable insumosTBL;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTable tiposInsumoTBL;
    // End of variables declaration//GEN-END:variables
}
