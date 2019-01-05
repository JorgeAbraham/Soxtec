/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.util.StringTokenizer;
import javax.swing.JOptionPane;
import javax.swing.JTable;
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
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tiposInsumoTBL = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        gruposDeInsumoTBL = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        insumosTBL = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("Configuración de Insumos");

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/logoSoxtec.jpg"))); // NOI18N

        jLabel4.setText("Tipos de Insumo");

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

        jButton1.setText("+");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton4.setText("x");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel5.setText("Grupos de Insumo");

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

        jButton2.setText("+");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton5.setText("x");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

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

        jLabel6.setText("Insumos");

        jButton3.setText("+");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton6.setText("x");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6))
                .addContainerGap(855, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addGap(839, 839, 839)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jScrollPane5)
                                .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jScrollPane4)
                                .addComponent(jButton6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jScrollPane6, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addContainerGap()))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addGap(187, 187, 187)
                .addComponent(jLabel5)
                .addGap(172, 172, 172)
                .addComponent(jLabel6)
                .addContainerGap(177, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(31, 31, 31)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jButton1)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jButton4)
                    .addGap(27, 27, 27)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jButton2)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jButton5)
                    .addGap(26, 26, 26)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jButton3)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jButton6)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        jScrollPane1.setViewportView(jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2))
            .addComponent(jScrollPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addComponent(jLabel1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    public String IndicePadre(JTable tabla){
        
        
        StringTokenizer tok;
        tok=new StringTokenizer(tabla.getValueAt(tabla.getSelectedRow(), 1)+"","[]",false);
        return tok.nextToken();
    }
    
    public String siguienteIndice(JTable tabla,String tipoCatalogo,int formato){
        
        StringTokenizer tok;
        String indiceEntreCorchetes="1";
        
        String indice="1";
        
        if (tabla.getRowCount()>0){
            String ultimoValor=tabla.getValueAt(tabla.getRowCount()-1, 1)+"";
            tok=new StringTokenizer(ultimoValor,"[]-",false);
            
            
            
            indiceEntreCorchetes = tok.nextToken();
            indice= (Integer.parseInt(indiceEntreCorchetes)+1)+"";
            if (formato==3){
                tok.nextToken();
                indiceEntreCorchetes=tok.nextToken();
                indiceEntreCorchetes=(Integer.parseInt(indiceEntreCorchetes)+1)+"";
            }
           
            
        }
        
        if (formato==3){
            
            indice=String.format("%04d", (Integer.parseInt(indiceEntreCorchetes)));
        }
        
        if (formato==2){
            indice=String.format("%02d", Integer.parseInt(indice));
        }  
        if (formato==1){
            indice=String.format("%01d", Integer.parseInt(indice));
        }  
      
        
        
        return indice;
    }
    
    
    
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
                    String UltimoInsertado=catalogo.insertaCatalogo("["+IndicePadre(tiposInsumoTBL)+"-"+IndicePadre(gruposDeInsumoTBL)+"-"+siguienteIndice(insumosTBL,"19",3)+"]"+respuesta, "19");
                    
                    
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
                    String UltimoInsertado=catalogo.insertaCatalogo("["+siguienteIndice(gruposDeInsumoTBL,"18",2)+"]"+respuesta, "18");
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

        String respuesta = JOptionPane.showInputDialog(null, "Escribe el Tipo de Insumo  ");
        if (respuesta!=null ){
            if (!respuesta.equals("") ){

                catalogo.insertaCatalogo("["+siguienteIndice(tiposInsumoTBL,"17",1)+"]"+respuesta, "17");

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
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTable tiposInsumoTBL;
    // End of variables declaration//GEN-END:variables
}
