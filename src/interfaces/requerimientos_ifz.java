/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import instancias.session;
import interfaces.emergentes.requerimientos_detalle_ifz;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.StringTokenizer;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.filechooser.FileSystemView;
import javax.swing.table.DefaultTableModel;
import serviciosBD.ManejadorDeDatos;
import serviciosBD.documentos_servicio;
import serviciosBD.operaciones_servicio;
import serviciosBD.persona_servicio;
import serviciosBD.requisiciones_servicio;
import utilidadesReportesJasper.Reportes;
import utilidadesbasicas.archivoProperties;
import utilidadesbasicas.archivoSerializableParaBD;

/**
 *
 * @author Soxtec Desarrollo
 */
public class requerimientos_ifz extends javax.swing.JFrame {

    String CentrosDeCostoIDS[];
    String EmpleadosIDS[];
    
    
    public requerimientos_ifz() {
        initComponents();
        inicializaListaRequisiciones();
        iniciaCentroDeCosto();
        iniciaPersonas();
        
        
        centroDeCostosTXT.setVisible(false);
        empleadosTXT.setVisible(false);
        
        
        ArrayList<Integer> columnasNumero=new ArrayList();
        columnasNumero.add(0);
        columnasNumero.add(1);
        columnasNumero.add(2);
        columnasNumero.add(3);
        columnasNumero.add(4);
        redimencionaTablasRequisiciones(columnasNumero, requiscisionTBL);
         redimencionaTablasRequisiciones(columnasNumero, aprobadasTBL);
          redimencionaTablasRequisiciones(columnasNumero, canceladasTBL);
           redimencionaTablasRequisiciones(columnasNumero, aplicadoTBL);
            redimencionaTablasRequisiciones(columnasNumero, porComprobarTBL);
            redimencionaTablasRequisiciones(columnasNumero, comprobadoTBL);
            
        
    }
    
    
    public void redimenciona1(){
        conceptosTBL.getColumnModel().getColumn(0).setMaxWidth(0);
        conceptosTBL.getColumnModel().getColumn(0).setMinWidth(0);
        conceptosTBL.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(0);
        conceptosTBL.getTableHeader().getColumnModel().getColumn(0).setMinWidth(0);
    }
    
    
    
    public void redimencionaTablasRequisiciones( ArrayList columnasNumero, JTable tabla ){
        
        for (int i=0;i<columnasNumero.size();i++){
            
            tabla.getColumnModel().getColumn( (int)columnasNumero.get(i)  ).setMaxWidth(0);
            tabla.getColumnModel().getColumn( (int)columnasNumero.get(i) ).setMinWidth(0);
            tabla.getTableHeader().getColumnModel().getColumn( (int)columnasNumero.get(i) ).setMaxWidth(0);
            tabla.getTableHeader().getColumnModel().getColumn( (int)columnasNumero.get(i) ).setMinWidth(0);
           
        
        }
    
    }
    
    
    public void iniciaPersonas(){
        
        
        persona_servicio empleados = new persona_servicio();
                    
      
        String empleado[][]=empleados.LISTAEmpleadosPorStringYSinAsignar(" v.valorTexto='ingresado'  OR  v.valorTexto='alta'     OR   v.valorTexto='sinDefinir'   OR   v.valorTexto='paroTecnico'   ");
       
        DefaultComboBoxModel MODEL = new DefaultComboBoxModel();
        MODEL.addElement("Selecciona...");
       
        
        EmpleadosIDS=new String[empleado.length+1];
        EmpleadosIDS[0]="0";
        if (empleado.length!=0){
            for (int i=0;i<empleado.length;i++){
                
                
                EmpleadosIDS[i+1]=empleado[i][1];
                MODEL.addElement(empleado[i][0]);
            }
        }
        
        empleadosCMB.setModel(MODEL);
        
        
    }

    public void iniciaCentroDeCosto(){
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



        String req[][]=operacion.listaOperacionesConcatenadas(concepto,estadosOperacionNomina,tipoOperacionNomina,nombre,condicion,2," AND valor0="+session.idUsuario+" ");
        
        DefaultComboBoxModel centroCostosMODEL = new DefaultComboBoxModel();
        centroCostosMODEL.addElement("Selecciona...");
       
        
        CentrosDeCostoIDS=new String[req.length+1];
        CentrosDeCostoIDS[0]="0";
        if (req.length!=0){
            for (int i=0;i<req.length;i++){
                
                
                CentrosDeCostoIDS[i+1]=req[i][6];
                centroCostosMODEL.addElement(req[i][8]);
            }
        }
        
        centroDeCostoCMB.setModel(centroCostosMODEL);
        
        //usuariosCentroDeCostoTBL.setModel(new DefaultTableModel(req,columnas));
        
        
        
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



        String req[][]=operacionNomina.listaOperacionesConcatenadas(concepto,estadosOperacionNomina,tipoOperacionNomina,nombre,condicion,1," AND  o.idusuarios='"+session.idUsuario+"' ");
        requiscisionTBL.setModel(new DefaultTableModel(req,columnas){

                                        @Override
                                        public boolean isCellEditable(int row, int column) {
                                           //all cells false
                                           return false;
                                        }
                                    }   );
        
        estadosOperacionNomina=new ArrayList();
        estadosOperacionNomina.add("3"); //Aprobado
     
        
        req=operacionNomina.listaOperacionesConcatenadas(concepto,estadosOperacionNomina,tipoOperacionNomina,nombre,condicion,1," AND  o.idusuarios='"+session.idUsuario+"' ");
        aprobadasTBL.setModel(new DefaultTableModel(req,columnas){

                                        @Override
                                        public boolean isCellEditable(int row, int column) {
                                           //all cells false
                                           return false;
                                        }
                                    });
        
        estadosOperacionNomina=new ArrayList();
        estadosOperacionNomina.add("4"); //Rechada
        
        
        req=operacionNomina.listaOperacionesConcatenadas(concepto,estadosOperacionNomina,tipoOperacionNomina,nombre,condicion,1," AND  o.idusuarios='"+session.idUsuario+"' ");
        canceladasTBL.setModel(new DefaultTableModel(req,columnas){

                                        @Override
                                        public boolean isCellEditable(int row, int column) {
                                           //all cells false
                                           return false;
                                        }
                                    });
        
        estadosOperacionNomina=new ArrayList();
        estadosOperacionNomina.add("5"); //Aplicado
      
        
        req=operacionNomina.listaOperacionesConcatenadas(concepto,estadosOperacionNomina,tipoOperacionNomina,nombre,condicion,1," AND  o.idusuarios='"+session.idUsuario+"' ");
        aplicadoTBL.setModel(new DefaultTableModel(req,columnas){

                                        @Override
                                        public boolean isCellEditable(int row, int column) {
                                           //all cells false
                                           return false;
                                        }
                                    });
        
        estadosOperacionNomina=new ArrayList();
        estadosOperacionNomina.add("6"); //Por comprobar
        
        
        req=operacionNomina.listaOperacionesConcatenadas(concepto,estadosOperacionNomina,tipoOperacionNomina,nombre,condicion,1," AND  o.idusuarios='"+session.idUsuario+"' ");
        porComprobarTBL.setModel(new DefaultTableModel(req,columnas){

                                        @Override
                                        public boolean isCellEditable(int row, int column) {
                                           //all cells false
                                           return false;
                                        }
                                    });
        
        estadosOperacionNomina=new ArrayList();
        estadosOperacionNomina.add("7"); //Comprobado
        
        
        req=operacionNomina.listaOperacionesConcatenadas(concepto,estadosOperacionNomina,tipoOperacionNomina,nombre,condicion,1," AND  o.idusuarios='"+session.idUsuario+"' ");
        comprobadoTBL.setModel(new DefaultTableModel(req,columnas){

                                        @Override
                                        public boolean isCellEditable(int row, int column) {
                                           //all cells false
                                           return false;
                                        }
                                    });
        
    
    
    }
    
    
    public void calculaTotales(){
        
        float total=0;
        
        for (int i=0;i<conceptosTBL.getRowCount();i++){
            
           total=total+Float.parseFloat(conceptosTBL.getValueAt(i, 5)+"");
            
        }
        
        
        
        totalTXT.setText(total+"");
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
        jLabel2 = new javax.swing.JLabel();
        centroDeCostoCMB = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        fechaRequisicionDTE = new com.toedter.calendar.JDateChooser();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        descripcionTXT = new javax.swing.JTextArea();
        jScrollPane5 = new javax.swing.JScrollPane();
        conceptosTBL = new javax.swing.JTable();
        jLabel14 = new javax.swing.JLabel();
        fechaEntregaDTE = new com.toedter.calendar.JDateChooser();
        jButton1 = new javax.swing.JButton();
        archivoNombreLBL = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        totalTXT = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        centroDeCostosTXT = new javax.swing.JTextField();
        empleadosCMB = new javax.swing.JComboBox<>();
        empleadosTXT = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        claveTXT = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        fechaCorteDTE = new com.toedter.calendar.JDateChooser();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jScrollPane3 = new javax.swing.JScrollPane();
        requiscisionTBL = new javax.swing.JTable();
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

        jLabel1.setText("Requisicion");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setText("Centro de Costo");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 140, -1, 20));

        centroDeCostoCMB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        centroDeCostoCMB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                centroDeCostoCMBActionPerformed(evt);
            }
        });
        jPanel1.add(centroDeCostoCMB, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 140, 250, -1));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/logoSoxtec.jpg"))); // NOI18N
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 350, 90));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("Soxtec SA. de CV");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 20, -1, -1));

        jLabel6.setText("Fecha");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, -1, 20));
        jPanel1.add(fechaRequisicionDTE, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 120, 210, -1));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setText("Calle Tenochtitlan  Numero 143   ");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 40, -1, -1));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel8.setText(" Colonia Valle de Huinala. Apodaca N.L.");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 60, -1, -1));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel9.setText("Codigo Postal 66634 ");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 80, -1, -1));

        jLabel10.setText("SOX0908277X3");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 100, -1, -1));

        jLabel11.setText("DATOS GENERALES");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 120, -1, -1));

        jLabel12.setText("Responsable de la compra");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 180, -1, 20));

        jLabel13.setText("Descripción");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, -1, -1));

        descripcionTXT.setColumns(20);
        descripcionTXT.setRows(5);
        jScrollPane4.setViewportView(descripcionTXT);

        jPanel1.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 150, 430, 130));

        conceptosTBL.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "idInsumo", "Cantidad", "Insumo", "Observaciones", "Precio Unitario", "Importe"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, true, true, true, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane5.setViewportView(conceptosTBL);
        if (conceptosTBL.getColumnModel().getColumnCount() > 0) {
            conceptosTBL.getColumnModel().getColumn(2).setResizable(false);
        }

        jPanel1.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 330, 1060, 140));

        jLabel14.setText("Fecha de Entrega");
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 220, -1, 20));
        jPanel1.add(fechaEntregaDTE, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 220, 250, -1));

        jButton1.setText("Adjuntar Archivo");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 510, -1, -1));
        jPanel1.add(archivoNombreLBL, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 510, -1, 20));

        jLabel16.setText("Total");
        jPanel1.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 520, -1, 20));

        totalTXT.setEditable(false);
        totalTXT.setText("0");
        jPanel1.add(totalTXT, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 520, 180, -1));

        jButton2.setText("+");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 480, 880, -1));

        jButton3.setText("x");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 480, 170, -1));

        centroDeCostosTXT.setText("0");
        jPanel1.add(centroDeCostosTXT, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 140, 50, -1));

        empleadosCMB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        empleadosCMB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                empleadosCMBActionPerformed(evt);
            }
        });
        jPanel1.add(empleadosCMB, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 180, 250, -1));

        empleadosTXT.setText("0");
        jPanel1.add(empleadosTXT, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 180, 50, -1));

        jButton4.setText("GUARDAR");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 550, 1060, 40));

        claveTXT.setEditable(false);
        jPanel1.add(claveTXT, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 30, 120, -1));

        jLabel3.setText("Fecha de Corte");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 260, -1, -1));
        jPanel1.add(fechaCorteDTE, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 260, 250, -1));

        jScrollPane2.setViewportView(jPanel1);

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

        jTabbedPane1.addTab("Generadas", jScrollPane3);

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
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(1094, Short.MAX_VALUE))
            .addComponent(jTabbedPane1)
            .addComponent(jScrollPane2)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 422, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void centroDeCostoCMBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_centroDeCostoCMBActionPerformed
               
        
        centroDeCostosTXT.setText(  CentrosDeCostoIDS[centroDeCostoCMB.getSelectedIndex()] );
        
        
        if (!centroDeCostosTXT.getText().equals("0")){
            requisiciones_servicio r=new requisiciones_servicio(); 
            String ultimaClave[][]=r.ultimaClaveCentrodeCosto(centroDeCostosTXT.getText());
            
            
            if (ultimaClave!=null){
                if (ultimaClave.length!=0){
                    StringTokenizer TOK=new StringTokenizer(ultimaClave[0][0],"-" ,false);
                    TOK.nextToken();
                    String numero=TOK.nextToken();
                     
                    StringTokenizer TOK2=new StringTokenizer(centroDeCostoCMB.getSelectedItem()+"","[]" ,false);
                    claveTXT.setText(  TOK2.nextToken()+"-"+String.format("%06d", (Integer.parseInt(numero)+1)     )  );   
                     
                }
                else{
                    StringTokenizer TOK=new StringTokenizer(centroDeCostoCMB.getSelectedItem()+"","[]" ,false);
                    claveTXT.setText(  TOK.nextToken()+"-000001"  );  
                }
                
            }
            else{
                 StringTokenizer TOK=new StringTokenizer(centroDeCostoCMB.getSelectedItem()+"","[]" ,false);
                    claveTXT.setText(  TOK.nextToken()+"-000001"  );  
            }
            
            
        }else{
            claveTXT.setText("");
        }
        
       
    }//GEN-LAST:event_centroDeCostoCMBActionPerformed

    private void empleadosCMBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_empleadosCMBActionPerformed
         
        empleadosTXT.setText(  EmpleadosIDS[empleadosCMB.getSelectedIndex()] );
    }//GEN-LAST:event_empleadosCMBActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        requerimientos_detalle_ifz V=new requerimientos_detalle_ifz(this, true);
        V.setVisible(true);
        
        if (V.isInformacion()){
            
            DefaultTableModel MODEL=(DefaultTableModel) (conceptosTBL.getModel());
            MODEL.addRow(new Object[]{
                                        V.getInsumoIdTXT().getText(),
                                        V.getCantidadSPN().getValue(),
                                        V.getInsumoTXT().getText(),
                                        V.getObservacionesTXT().getText(),
                                        V.getPrecioTXT().getText(),
                                        V.getImporteTXT().getText()
                                    }
                        );
            
            
        }
        redimenciona1();
        calculaTotales();
        

    }//GEN-LAST:event_jButton2ActionPerformed

    
    String MSG="";
    
    public boolean validacion(){
        boolean valoracion=true;
        MSG="";
        
        if (fechaRequisicionDTE.getDate()==null){
            valoracion=false;
            MSG=MSG+"Por favor llene Fecha de Requisición.\n";
        }
        if (fechaEntregaDTE.getDate()==null){
            valoracion=false;
            MSG=MSG+"Por favor llene Fecha de Entrega.\n";
        }
        if (fechaCorteDTE.getDate()==null){
            valoracion=false;
            MSG=MSG+"Por favor llene Fecha de Corte.\n";
        }
        
        if (descripcionTXT.getText().equals("")){
            valoracion=false;
            MSG=MSG+"Por favor llene Descripción.\n";
        }
        if (empleadosTXT.getText().equals("0")){
            valoracion=false;
            MSG=MSG+"Por favor Seleccione un Responsable.\n";
        }
        if (totalTXT.getText().equals("0")){
            valoracion=false;
            MSG=MSG+"El total tiene que ser mayor que 0.\n";
        }
        if (centroDeCostosTXT.getText().equals("0")){
            valoracion=false;
            MSG=MSG+"Por favor seleccione un centro de costo.\n";
        }
        if (claveTXT.getText().equals("0")){
            valoracion=false;
            MSG=MSG+"Por favor seleccione un centro de costo para poder generar un folio.\n";
        }
        return valoracion;
    }
    
    
    
    
    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        if ( validacion() ){
            
            
                HashMap<String, String> datosDeInterface = new HashMap();
                datosDeInterface.put("UsuarioP",instancias.session.idUsuario);
                datosDeInterface.put("FechaP",utilidadesFecha.sumaFecha.date_aaaa_mm_dd(fechaRequisicionDTE.getDate()));
                datosDeInterface.put("FechaEntrega",utilidadesFecha.sumaFecha.date_aaaa_mm_dd(fechaEntregaDTE.getDate()));
               
                datosDeInterface.put("DescRazon",descripcionTXT.getText());
                datosDeInterface.put("PersonaReceptora",empleadosTXT.getText());
                datosDeInterface.put("Total",totalTXT.getText());
                datosDeInterface.put("Archivo",archivoNombreLBL.getText());
                datosDeInterface.put("centroDeCostoRequisicion",centroDeCostosTXT.getText());
                datosDeInterface.put("claveRequisicion",claveTXT.getText());
                datosDeInterface.put("fechaCorte",utilidadesFecha.sumaFecha.date_aaaa_mm_dd(fechaCorteDTE.getDate()));
                
                
                HashMap<String, ArrayList> datosDeInterfaceListaInterna = new HashMap();
                
                
                ArrayList<String> lista1=new ArrayList();
                ArrayList<String> lista2=new ArrayList();
                ArrayList<String> lista3=new ArrayList();
                ArrayList<String> lista4=new ArrayList();
                ArrayList<String> lista5=new ArrayList();
             
                
                
                for (int i=0;i<conceptosTBL.getRowCount();i++){
                    lista1.add(conceptosTBL.getValueAt(i, 1)+"");
                    lista2.add(conceptosTBL.getValueAt(i, 3)+"");
                    lista3.add(conceptosTBL.getValueAt(i, 0)+"");
                    lista4.add(conceptosTBL.getValueAt(i, 4)+"");
                    lista5.add(conceptosTBL.getValueAt(i, 5)+"");
                }
                
                
                
                
                datosDeInterfaceListaInterna.put("Cantidad",lista1);
                datosDeInterfaceListaInterna.put("DescripcioDeAriculo",lista2);
                datosDeInterfaceListaInterna.put("insumosRequisicionConcepto",lista3);
                datosDeInterfaceListaInterna.put("PrecioUnitario",lista4);
                datosDeInterfaceListaInterna.put("Importe",lista5);
               
                
               


                operaciones_servicio OP=new operaciones_servicio();
                    // el que formatea
                SimpleDateFormat formateador = new SimpleDateFormat("yyyy-MM-dd");
                Date date=new Date();

                String NumeroDeOperacion=OP.insertaOperacion(
                                       "1",   //Solicitud
                                        formateador.format(date),
                                        formateador.format(date),
                                        session.idUsuario,
                                        "1"   //Requicision 
                                    );


                String BaseOperaciones[][] = OP.listaOperacionesBasePorTipo( "1" )   ;


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
            requerimientos_ifz V=new requerimientos_ifz();
            V.setVisible(true);
                
                
        }else{
            JOptionPane.showMessageDialog(null, MSG,"Error",JOptionPane.ERROR_MESSAGE);
        }  
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       
         
            /**llamamos el metodo que permite cargar la ventana*/
            JFileChooser file=new JFileChooser();
            file.showOpenDialog(this);
            /**abrimos el archivo seleccionado*/
            File abre=file.getSelectedFile();
            /**recorremos el archivo, lo leemos para plasmarlo
             *en el area de texto*/
            if(abre!=null) {
                archivoNombreLBL.setText(abre.getAbsolutePath());
            }
            else{
                archivoNombreLBL.setText("");
            }
            
           
            
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        
        if (conceptosTBL.getRowCount()!=-1){
            DefaultTableModel model =  (DefaultTableModel)conceptosTBL.getModel();
            model.removeRow(model.getRowCount() - 1);
        }
        calculaTotales();
        
    }//GEN-LAST:event_jButton3ActionPerformed

    private void requiscisionTBLMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_requiscisionTBLMouseClicked
        if (evt.getClickCount() == 2)
        {
          
            Reportes R=new Reportes();
            
            R.addParametro("idOperacion", requiscisionTBL.getValueAt(requiscisionTBL.getSelectedRow(), 0).toString());
            archivoAdjunto( requiscisionTBL.getValueAt(requiscisionTBL.getSelectedRow(), 0).toString() );
            archivoProperties A=new archivoProperties("reportParams.properties");
            R.setSubreportDir(A.getProperties("SUBREPORTDIR")+"\\requisiciones\\");
            R.setImageReportDir(A.getProperties("IMAGEREPORTDIR"));
            
            R.verReporte("reportes\\requisiciones\\requisicionesGeneral.jasper",ManejadorDeDatos.BD.getCon());
            
        }
    }//GEN-LAST:event_requiscisionTBLMouseClicked

    
     public void archivoAdjunto(String idOperacion ){
        
        operaciones_servicio OS=new operaciones_servicio();
        String archivos[][]=OS.getArchivoDeOperacion(idOperacion,"11");
         if( archivos != null ){
                if( archivos.length>0 ){
                    
                    JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
                    jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                    // int returnValue = jfc.showOpenDialog(null);
                    jfc.setDialogTitle("Existen Archivos Adjuntos. ¿Descargar?  ==>            "+archivos[0][1]);
                    int returnValue = jfc.showSaveDialog(null);
                    
                    if (returnValue == JFileChooser.APPROVE_OPTION) {
                        File selectedFile = jfc.getSelectedFile();
                        
                        
                            String ruta = selectedFile.getAbsolutePath()+"/"+archivos[0][1];
                            File archivo = new File(ruta);
                            if(archivo.exists()) {

                                if (JOptionPane.showConfirmDialog(null, "YA EXISTE UN ARCHIVO CON EL MISMO NOMBRE, ¿REEMPLAZARLO?", "REEMPLAZAR", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                                     ManejadorDeDatos.BD.ConsultaArchivo(" SELECT archivo FROM archivo WHERE idArchivo="+ archivos[0][0]  +";  ", "archivo",  selectedFile.getAbsolutePath()+"/"+archivos[0][1]   );
                                } 

                            } else {
                                ManejadorDeDatos.BD.ConsultaArchivo(" SELECT archivo FROM archivo WHERE idArchivo="+ archivos[0][0]  +";  ", "archivo",  selectedFile.getAbsolutePath()+"/"+archivos[0][1]   );

                            }

         
                        
                    }
            
                }
            }
    }
    
    private void aprobadasTBLMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_aprobadasTBLMouseClicked
        if (evt.getClickCount() == 2)
        {
          
            Reportes R=new Reportes();
            
            R.addParametro("idOperacion", aprobadasTBL.getValueAt(aprobadasTBL.getSelectedRow(), 0).toString());
            archivoAdjunto( aprobadasTBL.getValueAt(aprobadasTBL.getSelectedRow(), 0).toString() );
            archivoProperties A=new archivoProperties("reportParams.properties");
            R.setSubreportDir(A.getProperties("SUBREPORTDIR")+"\\requisiciones\\");
            R.setImageReportDir(A.getProperties("IMAGEREPORTDIR"));
            
            R.verReporte("reportes\\requisiciones\\requisicionesGeneral.jasper",ManejadorDeDatos.BD.getCon());
            
        }
    }//GEN-LAST:event_aprobadasTBLMouseClicked

    private void canceladasTBLMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_canceladasTBLMouseClicked
           // TODO add your handling code here:
           
           if (evt.getClickCount() == 2)
        {
          
            Reportes R=new Reportes();
            
            R.addParametro("idOperacion", canceladasTBL.getValueAt(canceladasTBL.getSelectedRow(), 0).toString());
            
            archivoProperties A=new archivoProperties("reportParams.properties");
            R.setSubreportDir(A.getProperties("SUBREPORTDIR")+"\\requisiciones\\");
            R.setImageReportDir(A.getProperties("IMAGEREPORTDIR"));
            
            R.verReporte("reportes\\requisiciones\\requisicionesGeneral.jasper",ManejadorDeDatos.BD.getCon());
            
        }
    }//GEN-LAST:event_canceladasTBLMouseClicked
    
    
    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable aplicadoTBL;
    private javax.swing.JTable aprobadasTBL;
    private javax.swing.JLabel archivoNombreLBL;
    private javax.swing.JTable canceladasTBL;
    private javax.swing.JComboBox<String> centroDeCostoCMB;
    private javax.swing.JTextField centroDeCostosTXT;
    private javax.swing.JTextField claveTXT;
    private javax.swing.JTable comprobadoTBL;
    private javax.swing.JTable conceptosTBL;
    private javax.swing.JTextArea descripcionTXT;
    private javax.swing.JComboBox<String> empleadosCMB;
    private javax.swing.JTextField empleadosTXT;
    private com.toedter.calendar.JDateChooser fechaCorteDTE;
    private com.toedter.calendar.JDateChooser fechaEntregaDTE;
    private com.toedter.calendar.JDateChooser fechaRequisicionDTE;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable porComprobarTBL;
    private javax.swing.JTable requiscisionTBL;
    private javax.swing.JTextField totalTXT;
    // End of variables declaration//GEN-END:variables
}
