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
import serviciosBD.ManejadorDeDatos;
import serviciosBD.documentos_servicio;
import serviciosBD.operaciones_servicio;
import utilidadesReportesJasper.Reportes;
import utilidadesbasicas.archivoProperties;
import utilidadesbasicas.archivoSerializableParaBD;

/**
 *
 * @author Soxtec Desarrollo
 */
public class aprobacion_ifz extends javax.swing.JFrame {

    /**
     * Creates new form aprovacion_ifz
     */
    public aprobacion_ifz() {
        initComponents();
        inicializaListaRequisiciones();
    }

    public void inicializaListaRequisiciones(){
        operaciones_servicio operacion = new operaciones_servicio();
        
        
       
        String columnas[]=new String[10];
        columnas[0]="idOperacion";
        columnas[1]="Estado";
        columnas[2]="fechaCreacion";
        columnas[3]="fechaModificacion";
        columnas[4]="idusuarios";
        columnas[5]="Clave";
        columnas[6]="Fecha de Petición";
        columnas[7]="Descipción";
        columnas[8]="Total";
        columnas[9]="Estado";
       
         operaciones_servicio operacionNomina = new operaciones_servicio();
                                    
        ArrayList<String> tipoOperacionNomina=new ArrayList();
        tipoOperacionNomina.add("1");


        ArrayList<String> estadosOperacionNomina=new ArrayList();
        estadosOperacionNomina.add("1"); //Solicitud
        estadosOperacionNomina.add("2"); //En Proceso
     


        ArrayList<String> concepto=new ArrayList();
        concepto.add("143"); //CLAVE
        concepto.add("2");//Fecha de Peticio  
        concepto.add("4"); //Descipcion
        concepto.add("10"); //Total

        
        
        String  nombre="  estado.EstadoNombre  ";
        String  condicion=" LEFT JOIN 	(            "
                                            + "   SELECT o.idOperacion,   eo.estado as EstadoNombre "
                                            + "   FROM Operacion o    "
                                            + "    INNER JOIN  estadooperacion eo on o.idEstadoOperacion = eo.idEstadoOperacion  "
                                    + "   ) AS estado  ON o.idOperacion  =  estado.idOperacion    ";



        String req[][]=operacionNomina.listaOperacionesConcatenadas(concepto,estadosOperacionNomina,tipoOperacionNomina,nombre,condicion,1,"  ");
        requiscisionTBL.setModel(new DefaultTableModel(req,columnas){

                                        @Override
                                        public boolean isCellEditable(int row, int column) {
                                           //all cells false
                                           return false;
                                        }
                                    }   );
        
        estadosOperacionNomina=new ArrayList();
        estadosOperacionNomina.add("3"); //Aprobado
     
        
        req=operacionNomina.listaOperacionesConcatenadas(concepto,estadosOperacionNomina,tipoOperacionNomina,nombre,condicion,1,"  ");
        aprobadasTBL.setModel(new DefaultTableModel(req,columnas){

                                        @Override
                                        public boolean isCellEditable(int row, int column) {
                                           //all cells false
                                           return false;
                                        }
                                    });
        
        estadosOperacionNomina=new ArrayList();
        estadosOperacionNomina.add("4"); //Rechada
        
        
        req=operacionNomina.listaOperacionesConcatenadas(concepto,estadosOperacionNomina,tipoOperacionNomina,nombre,condicion,1,"  ");
        canceladasTBL.setModel(new DefaultTableModel(req,columnas){

                                        @Override
                                        public boolean isCellEditable(int row, int column) {
                                           //all cells false
                                           return false;
                                        }
                                    });
        
        estadosOperacionNomina=new ArrayList();
        estadosOperacionNomina.add("5"); //Aplicado
      
        
        req=operacionNomina.listaOperacionesConcatenadas(concepto,estadosOperacionNomina,tipoOperacionNomina,nombre,condicion,1,"  ");
        aplicadoTBL.setModel(new DefaultTableModel(req,columnas){

                                        @Override
                                        public boolean isCellEditable(int row, int column) {
                                           //all cells false
                                           return false;
                                        }
                                    });
        
        estadosOperacionNomina=new ArrayList();
        estadosOperacionNomina.add("6"); //Por comprobar
        
        
        req=operacionNomina.listaOperacionesConcatenadas(concepto,estadosOperacionNomina,tipoOperacionNomina,nombre,condicion,1,"  ");
        porComprobarTBL.setModel(new DefaultTableModel(req,columnas){

                                        @Override
                                        public boolean isCellEditable(int row, int column) {
                                           //all cells false
                                           return false;
                                        }
                                    });
        
        estadosOperacionNomina=new ArrayList();
        estadosOperacionNomina.add("7"); //Comprobado
        
        
        req=operacionNomina.listaOperacionesConcatenadas(concepto,estadosOperacionNomina,tipoOperacionNomina,nombre,condicion,1,"  ");
        comprobadoTBL.setModel(new DefaultTableModel(req,columnas){

                                        @Override
                                        public boolean isCellEditable(int row, int column) {
                                           //all cells false
                                           return false;
                                        }
                                    });
        
    
    
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        requiscisionTBL = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        aprobadasTBL = new javax.swing.JTable();
        jScrollPane7 = new javax.swing.JScrollPane();
        canceladasTBL = new javax.swing.JTable();
        jScrollPane1 = new javax.swing.JScrollPane();
        aplicadoTBL = new javax.swing.JTable();
        jScrollPane8 = new javax.swing.JScrollPane();
        porComprobarTBL = new javax.swing.JTable();
        jScrollPane9 = new javax.swing.JScrollPane();
        comprobadoTBL = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        requiscisionTBL.setModel(new javax.swing.table.DefaultTableModel(
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
        requiscisionTBL.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                requiscisionTBLMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(requiscisionTBL);

        jButton1.setBackground(new java.awt.Color(0, 102, 0));
        jButton1.setText("Aprobar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(204, 0, 0));
        jButton2.setText("Cancelar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 469, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 378, Short.MAX_VALUE))
            .addComponent(jScrollPane3)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 465, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jTabbedPane1.addTab("Requisición", jPanel1);

        aprobadasTBL.setModel(new javax.swing.table.DefaultTableModel(
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
        aprobadasTBL.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                aprobadasTBLMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(aprobadasTBL);

        jTabbedPane1.addTab("Aprobadas", jScrollPane6);

        canceladasTBL.setModel(new javax.swing.table.DefaultTableModel(
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
        canceladasTBL.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                canceladasTBLMouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(canceladasTBL);

        jTabbedPane1.addTab("Cancelada", jScrollPane7);

        aplicadoTBL.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(aplicadoTBL);

        jTabbedPane1.addTab("Aplicado", jScrollPane1);

        porComprobarTBL.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane8.setViewportView(porComprobarTBL);

        jTabbedPane1.addTab("Por Comprobar", jScrollPane8);

        comprobadoTBL.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane9.setViewportView(comprobadoTBL);

        jTabbedPane1.addTab("Comprobado", jScrollPane9);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 534, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void requiscisionTBLMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_requiscisionTBLMouseClicked
        if (evt.getClickCount() == 2)
        {

            Reportes R=new Reportes();
            String idOperacion=requiscisionTBL.getValueAt(requiscisionTBL.getSelectedRow(), 0).toString();
            operaciones_servicio OS=new operaciones_servicio();
            OS.actualizaEstado(idOperacion, "2");
            R.addParametro("idOperacion", idOperacion );
            
            archivoProperties A=new archivoProperties("reportParams.properties");
            R.setSubreportDir(A.getProperties("SUBREPORTDIR")+"\\requisiciones\\");
            R.setImageReportDir(A.getProperties("IMAGEREPORTDIR"));
            
            R.verReporte("reportes\\requisiciones\\requisicionesGeneral.jasper",ManejadorDeDatos.BD.getCon());
            

        } 
    }//GEN-LAST:event_requiscisionTBLMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        
        
        //APROBACIÓN 
        
        if (requiscisionTBL.getSelectedRow()!=-1)
        {
        
            int n = JOptionPane.showConfirmDialog(null,"¿Aprobar la requisición?","",JOptionPane.YES_NO_OPTION);
        
            
            if(n==0){
                
                
          
                            HashMap<String, String> datosDeInterface = new HashMap();
                            datosDeInterface.put("UsuarioApruebaRechaza",instancias.session.idUsuario);
                            datosDeInterface.put("MotivoApruebaRechaza","APROBADA");


                             HashMap<String, ArrayList> datosDeInterfaceListaInterna = new HashMap();

                            operaciones_servicio OP=new operaciones_servicio();


                            String idOperacion=requiscisionTBL.getValueAt(requiscisionTBL.getSelectedRow(), 0).toString();

                            //Inserta Operaciones y actualiza el estado
                            OP.actualizaEstado(idOperacion, "3");


                                // el que formatea
                            SimpleDateFormat formateador = new SimpleDateFormat("yyyy-MM-dd");
                            Date date=new Date();

                            String NumeroDeOperacion=OP.insertaOperacion(
                                                   "3",   //Solicitud
                                                    formateador.format(date),
                                                    formateador.format(date),
                                                    session.idUsuario,
                                                    "2"   //Aprovacion 
                                                );
                            // Se inserta la relacion entre las operaciones 
                            OP.insertaRelacionOperaciones(idOperacion, NumeroDeOperacion, "1");  

                            String BaseOperaciones[][] = OP.listaOperacionesBasePorTipo( "2" )   ;


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



                                        if (!ValorFormulario.equals("")){


                                            ArrayList<String> Archivos=new ArrayList();
                                            Archivos.add(ValorFormulario);

                                            for (int j=0;j<Archivos.size();j++){

                                                archivoSerializableParaBD archivo=new  archivoSerializableParaBD(Archivos.get(j));
                                                documentos_servicio documentos=new documentos_servicio();
                                                String idArchivoInsertado=documentos.insertDocument(archivo);
                                                conceptoOperacion=OP.insertaInformacion(operaciones_servicio.tipoArchivo, idArchivoInsertado);                    

                                            }


                                        }
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

                        this.dispose();
                        aprobacion_ifz V=new aprobacion_ifz();
                        V.setVisible(true);
                        V.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                
            }
        }
        

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if (requiscisionTBL.getSelectedRow()!=-1)
        {
        
            int n = JOptionPane.showConfirmDialog(  null,"¿Cancelar la requisición?","",JOptionPane.YES_NO_OPTION);

            if(n==0){
                
                String m=null;  
                
                m = JOptionPane.showInputDialog(null,"Indique el por que del rechazo de la requisición","",JOptionPane.INFORMATION_MESSAGE);

               

                if (m != null){
                    if ( !m.trim().equals("")){
                       
                                    HashMap<String, String> datosDeInterface = new HashMap();
                                    datosDeInterface.put("UsuarioApruebaRechaza",instancias.session.idUsuario);
                                    datosDeInterface.put("MotivoApruebaRechaza",m);


                                     HashMap<String, ArrayList> datosDeInterfaceListaInterna = new HashMap();

                                    operaciones_servicio OP=new operaciones_servicio();


                                    String idOperacion=requiscisionTBL.getValueAt(requiscisionTBL.getSelectedRow(), 0).toString();

                                    //Inserta Operaciones y actualiza el estado
                                    OP.actualizaEstado(idOperacion, "4");


                                        // el que formatea
                                    SimpleDateFormat formateador = new SimpleDateFormat("yyyy-MM-dd");
                                    Date date=new Date();

                                    String NumeroDeOperacion=OP.insertaOperacion(
                                                           "4",   //Solicitud
                                                            formateador.format(date),
                                                            formateador.format(date),
                                                            session.idUsuario,
                                                            "2"   //Aprovacion 
                                                        );
                                    // Se inserta la relacion entre las operaciones 
                                    OP.insertaRelacionOperaciones(idOperacion, NumeroDeOperacion, "1");  

                                    String BaseOperaciones[][] = OP.listaOperacionesBasePorTipo( "2" )   ;


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



                                                if (!ValorFormulario.equals("")){


                                                    ArrayList<String> Archivos=new ArrayList();
                                                    Archivos.add(ValorFormulario);

                                                    for (int j=0;j<Archivos.size();j++){

                                                        archivoSerializableParaBD archivo=new  archivoSerializableParaBD(Archivos.get(j));
                                                        documentos_servicio documentos=new documentos_servicio();
                                                        String idArchivoInsertado=documentos.insertDocument(archivo);
                                                        conceptoOperacion=OP.insertaInformacion(operaciones_servicio.tipoArchivo, idArchivoInsertado);                    

                                                    }


                                                }
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
                                    
                                    
                                    this.dispose();
                                    aprobacion_ifz V=new aprobacion_ifz();
                                    V.setVisible(true);
                                    V.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                                    
                                    
                    }
                    
                }
                        
                    
                    
                    
                    
                       
                
                
                
                
            }
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void aprobadasTBLMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_aprobadasTBLMouseClicked
        if (evt.getClickCount() == 2)
        {

            Reportes R=new Reportes();
            String idOperacion=aprobadasTBL.getValueAt(aprobadasTBL.getSelectedRow(), 0).toString();
            operaciones_servicio OS=new operaciones_servicio();
            R.addParametro("idOperacion", idOperacion );
            
            archivoProperties A=new archivoProperties("reportParams.properties");
            R.setSubreportDir(A.getProperties("SUBREPORTDIR")+"\\requisiciones\\");
            R.setImageReportDir(A.getProperties("IMAGEREPORTDIR"));
            
            R.verReporte("reportes\\requisiciones\\requisicionesGeneral.jasper",ManejadorDeDatos.BD.getCon());
            

        } 
    }//GEN-LAST:event_aprobadasTBLMouseClicked

    private void canceladasTBLMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_canceladasTBLMouseClicked
         if (evt.getClickCount() == 2)
        {

            Reportes R=new Reportes();
            String idOperacion=canceladasTBL.getValueAt(canceladasTBL.getSelectedRow(), 0).toString();
            operaciones_servicio OS=new operaciones_servicio();
            R.addParametro("idOperacion", idOperacion );
            
            archivoProperties A=new archivoProperties("reportParams.properties");
            R.setSubreportDir(A.getProperties("SUBREPORTDIR")+"\\requisiciones\\");
            R.setImageReportDir(A.getProperties("IMAGEREPORTDIR"));
            
            R.verReporte("reportes\\requisiciones\\requisicionesGeneral.jasper",ManejadorDeDatos.BD.getCon());
            

        } 
    }//GEN-LAST:event_canceladasTBLMouseClicked

   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable aplicadoTBL;
    private javax.swing.JTable aprobadasTBL;
    private javax.swing.JTable canceladasTBL;
    private javax.swing.JTable comprobadoTBL;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable porComprobarTBL;
    private javax.swing.JTable requiscisionTBL;
    // End of variables declaration//GEN-END:variables
}
