/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import instancias.session;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import serviciosBD.catalogo_servicio;
import serviciosBD.documentos_servicio;
import serviciosBD.operaciones_servicio;
import serviciosBD.usuario_servicio;
import utilidadesbasicas.archivoSerializableParaBD;

/**
 *
 * @author Soxtec Desarrollo
 */
public class centroDeCosto_ifz extends javax.swing.JFrame {

    /**
     * Creates new form centroDeCosto_ifz
     */
    public centroDeCosto_ifz() {
        initComponents();
        actualizaTablaUsuarios();
        actualizaTablaCentrosDeCosto();
        
         
    }
    
    
    public void actualizaTablaUsuarios(){
        usuario_servicio usuario=new usuario_servicio();
        String Usuario[][]=usuario.listaUsuarios();
        String columnas[]=new String[2];
        columnas[0]="ID";
        columnas[1]="Usuario";
        
        usuarioTBL.setModel(new DefaultTableModel(Usuario,columnas));
        redimenciona1();
    }
    
    public void actualizaTablaCentrosDeCosto(){
        catalogo_servicio  catalogo= new catalogo_servicio();
        centrodeCostoTBL.setModel(catalogo.listaPorTipoCatalogoModel(20));
        redimenciona2();
    }
    
    
    public void actualizaTablaRelacionCentroDeCostoUsuario(String idUsuario){
        
        String columnas[]=new String[9];
        columnas[0]="idOperacion";
        columnas[1]="Estado";
        columnas[2]="Fecha Creacion";
        columnas[3]="Fecha Modificacion";
        columnas[4]="Usuario";
        columnas[5]="USUARIO RELACIONADO";
        columnas[6]="CENTRO DE COSTO";
        columnas[7]="estado";
        columnas[8]="Centro de Costo";
       
        operaciones_servicio operacion = new operaciones_servicio();
                                    
        ArrayList<String> tipoOperacionNomina=new ArrayList();
        tipoOperacionNomina.add("24");  //Operacion Relacion Centro de Costo Usuario


        ArrayList<String> estadosOperacionNomina=new ArrayList();
        estadosOperacionNomina.add("19"); //Registrado
        

        ArrayList<String> concepto=new ArrayList();
        concepto.add("137");
        concepto.add("138");
        
        
        
        String  nombre="  estado.EstadoNombre ,  catalogosCentroDeCosto.catalogoNombre ";
        String  condicion=" LEFT JOIN 	(            "
                                            + "   SELECT o.idOperacion,   eo.estado as EstadoNombre "
                                            + "   FROM Operacion o    "
                                            + "    INNER JOIN  estadooperacion eo on o.idEstadoOperacion = eo.idEstadoOperacion  "
                                    + "   ) AS estado  ON o.idOperacion  =  estado.idOperacion   "
                + ""
                +" LEFT JOIN 	(            "
                                    + "   SELECT o.idOperacion,  coA.valorTexto, cat.valor as catalogoNombre "
                                    + "   FROM Operacion o    "
                                    + "    INNER JOIN pertenenciadevalores pvA on (o.idOperacion=pvA.idOperacion)      "
                                    + "    INNER JOIN concepto cA on cA.idConcepto = pvA.idConcepto   AND  (cA.idConcepto=138 )      "
                                    + "    INNER JOIN conceptooperacion coA on coA.idConceptoOperacion = pvA.idConceptoOperacion   "
                                    + "    INNER JOIN catalogo cat on cat.idCatalogo=coA.valorTexto "
                            + "   ) AS catalogosCentroDeCosto  ON o.idOperacion  =  catalogosCentroDeCosto.idOperacion    ";



        String req[][]=operacion.listaOperacionesConcatenadas(concepto,estadosOperacionNomina,tipoOperacionNomina,nombre,condicion,2," AND valor0="+idUsuario+" ");
        usuariosCentroDeCostoTBL.setModel(new DefaultTableModel(req,columnas));
        
        redimenciona3();
        
    }
    
    
     public void redimenciona1(){
        usuarioTBL.getColumnModel().getColumn(0).setMaxWidth(0);
        usuarioTBL.getColumnModel().getColumn(0).setMinWidth(0);
        usuarioTBL.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(0);
        usuarioTBL.getTableHeader().getColumnModel().getColumn(0).setMinWidth(0);
    }
    
    public void redimenciona2(){
        centrodeCostoTBL.getColumnModel().getColumn(0).setMaxWidth(0);
        centrodeCostoTBL.getColumnModel().getColumn(0).setMinWidth(0);
        centrodeCostoTBL.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(0);
        centrodeCostoTBL.getTableHeader().getColumnModel().getColumn(0).setMinWidth(0);
    }
    
    
    public void redimenciona3(){
        
        for (int i=0;i<8;i++){
            usuariosCentroDeCostoTBL.getColumnModel().getColumn(i).setMaxWidth(0);
            usuariosCentroDeCostoTBL.getColumnModel().getColumn(i).setMinWidth(0);
            usuariosCentroDeCostoTBL.getTableHeader().getColumnModel().getColumn(i).setMaxWidth(0);
            usuariosCentroDeCostoTBL.getTableHeader().getColumnModel().getColumn(i).setMinWidth(0);

        }
        
        
       
        
    
    }
    
    
    public void actualizaTablaUsuariosCentrosDeCosto(){
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        usuariosCentroDeCostoTBL = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        usuarioTBL = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        centrodeCostoTBL = new javax.swing.JTable();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Centro de Costo");

        usuariosCentroDeCostoTBL.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(usuariosCentroDeCostoTBL);

        jButton1.setText(">");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("<");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        usuarioTBL.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        usuarioTBL.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                usuarioTBLMouseReleased(evt);
            }
        });
        jScrollPane4.setViewportView(usuarioTBL);

        jLabel2.setText("Usuario");

        jLabel3.setText("Centros de Costo");

        jLabel4.setText("Acceso a Centro de Costo");

        centrodeCostoTBL.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane6.setViewportView(centrodeCostoTBL);

        jButton3.setText("+");

        jButton4.setText("x");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel3)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                        .addComponent(jLabel4)
                        .addGap(147, 147, 147))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(188, 188, 188)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane6)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel2))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane4)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton4)
                .addContainerGap(35, Short.MAX_VALUE))
        );

        jScrollPane2.setViewportView(jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(864, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(12, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 895, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(46, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addContainerGap(631, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(59, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(60, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (usuarioTBL.getSelectedRow()!=-1  && centrodeCostoTBL.getSelectedRow()!=-1 ){
            
            
                HashMap<String, String> datosDeInterface = new HashMap();
                datosDeInterface.put("relacionUsuarioCentroDeCosto",usuarioTBL.getValueAt(usuarioTBL.getSelectedRow(), 0)+"");
                datosDeInterface.put("relacionCentroDeCostoUsuario",centrodeCostoTBL.getValueAt(centrodeCostoTBL.getSelectedRow(), 0)+"");


                 HashMap<String, ArrayList> datosDeInterfaceListaInterna = new HashMap();



                operaciones_servicio OP=new operaciones_servicio();
                    // el que formatea
                SimpleDateFormat formateador = new SimpleDateFormat("yyyy-MM-dd");
                Date date=new Date();

                String NumeroDeOperacion=OP.insertaOperacion(
                                       "19",   //Registrado
                                        formateador.format(date),
                                        formateador.format(date),
                                        session.idUsuario,
                                        "24"   //Relacion 
                                    );


                String BaseOperaciones[][] = OP.listaOperacionesBasePorTipo( "24" )   ;


                for(int i=0;i<BaseOperaciones.length;i++){



                    String ValorFormulario= datosDeInterface.get(BaseOperaciones[i][4]); 


                    if (BaseOperaciones[i][2]==null){  //Valores que no son lista


                        String tipoVariable=BaseOperaciones[i][3];
                        String idConcepto=BaseOperaciones[i][5];
                        String nombreHTML=BaseOperaciones[i][4];
                        String conceptoOperacion=null;

                        if (operaciones_servicio.tipoTexto.equals(tipoVariable)){
                            conceptoOperacion=OP.insertaInformacion(operaciones_servicio.tipoTexto, ValorFormulario);
                        }
                        if (operaciones_servicio.tipoArchivo.equals(tipoVariable)){

                          /*
                            Codigo pendiente de archivo
                            */

                        }
                        if (operaciones_servicio.tipoNumero.equals(tipoVariable)){
                            conceptoOperacion=OP.insertaInformacion(operaciones_servicio.tipoNumero, ValorFormulario);
                        }
                        if (operaciones_servicio.tipoFecha.equals(tipoVariable)){
                            conceptoOperacion=OP.insertaInformacion(operaciones_servicio.tipoFecha, ValorFormulario);
                        }
                        if (operaciones_servicio.tipoTextoLargo.equals(tipoVariable)){
                            conceptoOperacion=OP.insertaInformacion(operaciones_servicio.tipoTextoLargo, ValorFormulario);
                        }
                        if (operaciones_servicio.catalogo.equals(tipoVariable)){
                            conceptoOperacion=OP.insertaInformacion(operaciones_servicio.catalogo, ValorFormulario);
                        }

                        if(conceptoOperacion!=null){
                            OP.insertaPertenenciaDeValores(idConcepto, conceptoOperacion, NumeroDeOperacion, null);
                        }

                    }
                    else{   //Valores de la lista

                        String idConcepto=BaseOperaciones[i][5];
                        String tipoVariable=BaseOperaciones[i][3];
                        String conceptoOperacion=null;

                        ArrayList<String> ValorArrayFormulario= datosDeInterfaceListaInterna.get(BaseOperaciones[i][4]);   //utilidadesWeb.utilidadWeb.ArrayDeVariablaDePeticionMultipart(peticion, BaseOperaciones[i][4]);
                        //Se revisa cada elemento de la lista
                        for (int j=0;j<ValorArrayFormulario.size();j++){





                            if (operaciones_servicio.tipoTexto.equals(tipoVariable)){
                                conceptoOperacion=OP.insertaInformacion(operaciones_servicio.tipoTexto, ValorArrayFormulario.get(j));
                            }
                            if (operaciones_servicio.tipoArchivo.equals(tipoVariable)){

                                /**/

                            }
                            if (operaciones_servicio.tipoNumero.equals(tipoVariable)){
                                conceptoOperacion=OP.insertaInformacion(operaciones_servicio.tipoNumero, ValorArrayFormulario.get(j));
                            }
                            if (operaciones_servicio.tipoFecha.equals(tipoVariable)){
                                conceptoOperacion=OP.insertaInformacion(operaciones_servicio.tipoFecha, ValorArrayFormulario.get(j));
                            }
                            if (operaciones_servicio.tipoTextoLargo.equals(tipoVariable)){
                                conceptoOperacion=OP.insertaInformacion(operaciones_servicio.tipoTextoLargo, ValorArrayFormulario.get(j));
                            }
                            if (operaciones_servicio.catalogo.equals(tipoVariable)){
                                conceptoOperacion=OP.insertaInformacion(operaciones_servicio.catalogo, ValorArrayFormulario.get(j));
                            }

                            if (conceptoOperacion!=null){
                                OP.insertaPertenenciaDeValores(idConcepto, conceptoOperacion, NumeroDeOperacion, (j+1)+"");
                            }

                        }

                    }

                }



                actualizaTablaRelacionCentroDeCostoUsuario(  usuarioTBL.getValueAt(usuarioTBL.getSelectedRow(), 0)+""   );
            
                
                
        }else{
            JOptionPane.showMessageDialog(null, "Seleccione un usuario y un centro de costo","Error",JOptionPane.ERROR_MESSAGE);
        }
        
        
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void usuarioTBLMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_usuarioTBLMouseReleased
        if (usuarioTBL.getSelectedRow()!=-1){
            actualizaTablaRelacionCentroDeCostoUsuario(  usuarioTBL.getValueAt(usuarioTBL.getSelectedRow(), 0)+""   );
        }
    }//GEN-LAST:event_usuarioTBLMouseReleased

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if (usuariosCentroDeCostoTBL.getSelectedRow()!=-1){
            operaciones_servicio OP=new operaciones_servicio();
            OP.actualizaEstado(usuariosCentroDeCostoTBL.getValueAt(usuariosCentroDeCostoTBL.getSelectedRow(), 0)+"", "20");
            actualizaTablaRelacionCentroDeCostoUsuario(  usuarioTBL.getValueAt(usuarioTBL.getSelectedRow(), 0)+""   );
        }
    }//GEN-LAST:event_jButton2ActionPerformed

  
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable centrodeCostoTBL;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTable usuarioTBL;
    private javax.swing.JTable usuariosCentroDeCostoTBL;
    // End of variables declaration//GEN-END:variables
}
