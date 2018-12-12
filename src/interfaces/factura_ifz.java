/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import instancias.Instacia;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import serviciosBD.ManejadorDeDatos;
import serviciosBD.catalogo_servicio;
import serviciosBD.detalle_factura_servicio;
import serviciosBD.lugar_servicio;
import serviciosBD.reporte_factura_servicio;
import utilidadesbasicas.EnglishNumberTranslator;
import utilidadesbasicas.utilidadValidacionNulo;

/**
 *
 * @author Abraham
 */
public class factura_ifz extends javax.swing.JFrame {

    public final static int MATUTINO = 1;
    public final static int VESPERTINO = 2;
    
    
    boolean actualizarInformacion;

    DateFormat formatoDiaSemana = new SimpleDateFormat("u");
    DateFormat formatoDiaMes = new SimpleDateFormat("d");
    DateFormat formatoSQL = new SimpleDateFormat("yyyy-MM-dd");
    DateFormat formatoEnglish = new SimpleDateFormat("EEEE dd MMM yyyy", Locale.ENGLISH);
    
    String  listaIdFacturas[][];
    
    
    Calendar calendar;

    int semanasTrabajadas;

    float horasEnSemanaTrabajadaMatutino[][];
    float horasEnSemanaTrabajadaVespertino[][];

    
    DefaultTableModel TBLMDLprimeraJornada;
    DefaultTableModel TBLMDLsegundaJornada;
    DefaultComboBoxModel CBMlugar;
    
    DefaultTableModel TBLMDLconceptosAdicionales;

    int[] semanaTrabajadaMatutino;
    JButton BTN_eliminarMatutino[][];
    JLabel LBL_nombreEmpleadoMatutino[];
    int idEmpleadoMatutino[];
    JLabel LBL_diaMatutino[][];
    String fechasMatutuno[][];
    JFormattedTextField TXThoraEntradaMatutino[][];
    JFormattedTextField TXThoraSalidaMatutino[][];
    JFormattedTextField[][] TXThoraTrabajadaMatutino;

    int[] semanaTrabajadaVespertino;
    JButton BTN_eliminarVespertino[][];
    JLabel LBL_nombreEmpleadoVespertino[];
    int idEmpleadoVespertino[];
    JLabel LBL_diaVespertino[][];
    String fechasVespertino[][];
    JTextField TXThoraEntradaVespertino[][];
    JTextField TXThoraSalidaVespertino[][];
    JTextField[][] TXThoraTrabajadaVespertino;
    
    
    
    JLabel fecha[];
    JTextField sorteados[];
    JTextField rejected[];
    JTextField reworked[];
            

    utilidadesbasicas.utilidadValidacionNulo validacionNulos;
    utilidadesbasicas.utilidadValidacionNulo validacionNulosReporte;
        
    
    public factura_ifz() {
        
        
        actualizarInformacion=false;
        Instacia.factura_srv.setIdFactura(0);
        
        String columnNames[] = {"Empleado","Num"};
        TBLMDLprimeraJornada = new DefaultTableModel(columnNames, 0);
        TBLMDLsegundaJornada = new DefaultTableModel(columnNames, 0);
        
        CBMlugar = new DefaultComboBoxModel();
        
        columnNames =new String[]{"Cantidad","Descripcion","Precio"};
        TBLMDLconceptosAdicionales = new DefaultTableModel(columnNames, 0);
        
        
        
        initComponents();
        TBLtrabajaores.setModel(Instacia.persona_srv.LISTAempleados());
        
        TBLfolios.setModel(Instacia.factura_srv.LISTAfacturas());
        
        listaIdFacturas = Instacia.factura_srv.LISTAIdsFacturas();
        
        
        lugar_servicio lugares=new lugar_servicio();
        CMBlugar.setModel(lugares.LISTAlugares());
        
        
        catalogo_servicio estados= new catalogo_servicio();
        CMBestatus.setModel(estados.listaComboPorTipoCatalogo(5));  // 5 estatus
        
        
        LBLdisponible.setVisible(false);
        LBL_noDisponible.setVisible(false);
        
        LBLorderNotAvalible.setVisible(false);
                LBLorderAvalible.setVisible(false);
        
        
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();       
        if ( calendar.get(Calendar.DAY_OF_WEEK) == 1 ){
            calendar.add(Calendar.DAY_OF_YEAR, 1);  
        } 
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        DTE_fecha.setText( format1.format(date) ); 
      
        
        
        validacionNulos=new utilidadValidacionNulo();
        validacionNulos.add(TXT_folio);
        
        validacionNulos.add(TXT_nombre); 
        validacionNulos.add(TXT_descripcion); 
        validacionNulos.add(TXT_direccion);
        validacionNulos.add(txt_solicitante);
        validacionNulos.add(TXT_po);
        validacionNulos.add(TXTvendedor);
        validacionNulos.add(TXTprecioHorasRegulares);
        validacionNulos.add(TXTprecioHorasTiempoExtra); 
        
        
    
        validacionNulosReporte=new utilidadValidacionNulo();
        validacionNulosReporte.add(TXTsorteados); 
        validacionNulosReporte.add(TXTrechazados); 
        validacionNulosReporte.add(TXTretrabajados); 
        
        
        
        DTE_fechaFinPeriodo.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            @Override
            public void propertyChange(java.beans.PropertyChangeEvent evt){
                Calendar calendar = Calendar.getInstance();
                Calendar calendar2 = Calendar.getInstance();
                
                calendar.setTime(DTE_fechaFinPeriodo.getDate()); 
                calendar.add(Calendar.DAY_OF_YEAR, 2);  
                
                calendar2.setTime(DTE_fechaFinPeriodo.getDate()); 
                calendar2.add(Calendar.DAY_OF_YEAR, 1);  
                
                
                
                if ( calendar.get(Calendar.DAY_OF_WEEK) == 1 ){
                    calendar.add(Calendar.DAY_OF_YEAR, 1);  
                } 
                if ( calendar2.get(Calendar.DAY_OF_WEEK) == 1 ){
                    calendar2.add(Calendar.DAY_OF_YEAR, 1);  
                } 
                
                if ( calendar2.get(Calendar.DAY_OF_YEAR) ==  calendar.get(Calendar.DAY_OF_YEAR) ){
                    calendar.add(Calendar.DAY_OF_YEAR, 1);  
                
                }
                
                
                Date date;   
                SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
                
                
                date = calendar.getTime();
                DTE_fecha.setText( format1.format(date) ); 
                
                date = calendar2.getTime();             
                TXTfechaReport.setText( format1.format(date) ); 
                
                
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

        jButton5 = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        TXTsubtotal = new javax.swing.JTextField();
        TXT_impuestos = new javax.swing.JTextField();
        TXTtotal = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        TXTtotalHorasRegulares = new javax.swing.JTextField();
        TXTtotalHorasExtra = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel30 = new javax.swing.JLabel();
        TXT_buscarFolio = new javax.swing.JTextField();
        jScrollPane8 = new javax.swing.JScrollPane();
        TBLfolios = new javax.swing.JTable();
        jButton3 = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jScrollPane9 = new javax.swing.JScrollPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        LBLdisponible = new javax.swing.JLabel();
        LBL_noDisponible = new javax.swing.JLabel();
        TXT_folio = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TXT_descripcion = new javax.swing.JTextArea();
        CMBlugar = new javax.swing.JComboBox();
        jLabel31 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        TXT_nombre = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        TXT_direccion = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txt_solicitante = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        TXT_po = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        TXTvendedor = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        TXTprecioHorasRegulares = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        TXTprecioHorasTiempoExtra = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        DTE_fechaFinPeriodo = new com.toedter.calendar.JCalendar();
        jLabel10 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        DTE_fechaInicioPeriodo = new com.toedter.calendar.JCalendar();
        CMBestatus = new javax.swing.JComboBox<>();
        jLabel32 = new javax.swing.JLabel();
        DTE_fecha = new javax.swing.JTextField();
        LBLorderNotAvalible = new javax.swing.JLabel();
        LBLorderAvalible = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        TXT_conceptoTexto = new javax.swing.JTextArea();
        jScrollPane7 = new javax.swing.JScrollPane();
        TBLconceptosAdicionales = new javax.swing.JTable();
        TXTcantidad = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        TXTdescripcion = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        TXTprecioUnitario = new javax.swing.JTextField();
        BTNadicionalAgregar = new javax.swing.JButton();
        BTNadicionalBorrar = new javax.swing.JButton();
        TXT_totalAdicionales = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        TXTsorteados = new javax.swing.JTextField();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        TXTrechazados = new javax.swing.JTextField();
        jLabel35 = new javax.swing.JLabel();
        TXTretrabajados = new javax.swing.JTextField();
        jLabel36 = new javax.swing.JLabel();
        jScrollPane10 = new javax.swing.JScrollPane();
        TXT_descripcion_problema = new javax.swing.JTextArea();
        jScrollPane11 = new javax.swing.JScrollPane();
        jPanel4 = new javax.swing.JPanel();
        TXTfechaReport = new javax.swing.JTextField();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        TXTcontactoCliente = new javax.swing.JTextField();
        TXTnumeroParte = new javax.swing.JTextField();
        jLabel40 = new javax.swing.JLabel();
        TXTtipoDeParte = new javax.swing.JTextField();
        TXT1 = new javax.swing.JTextField();
        TXT2 = new javax.swing.JTextField();
        jButton6 = new javax.swing.JButton();
        promedioSorteadoMIN = new javax.swing.JTextField();
        promedioSorteadoMAX = new javax.swing.JTextField();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        promedioRechazadoMIN = new javax.swing.JTextField();
        promedioRechazadoMAX = new javax.swing.JTextField();
        promedioRetrabajadoMIN = new javax.swing.JTextField();
        promedioRetrabajadoMAX = new javax.swing.JTextField();
        jLabel43 = new javax.swing.JLabel();
        scrollPaneReporte = new javax.swing.JScrollPane();
        PNL_reportes_fechas = new javax.swing.JPanel();
        promedioSorteado = new javax.swing.JTextField();
        promedioRechazado = new javax.swing.JTextField();
        promedioRetrabajado = new javax.swing.JTextField();
        jLabel45 = new javax.swing.JLabel();
        TXTsorteados2 = new javax.swing.JTextField();
        totalSorteados = new javax.swing.JTextField();
        TXTrechazados2 = new javax.swing.JTextField();
        totalRechazados = new javax.swing.JTextField();
        TXTretrabajados2 = new javax.swing.JTextField();
        totalRetrabajados = new javax.swing.JTextField();
        diferenciaSorteados = new javax.swing.JTextField();
        diferenciaRechazados = new javax.swing.JTextField();
        diferenciaRetrabajados = new javax.swing.JTextField();
        jLabel46 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        TXTresidente = new javax.swing.JTextField();
        TXTaprueba = new javax.swing.JTextField();
        scrollPaneWorkHours = new javax.swing.JScrollPane();
        jPanel3 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        TXT_buscarTrabajador = new javax.swing.JTextField();
        jScrollPane5 = new javax.swing.JScrollPane();
        TBLtrabajaores = new javax.swing.JTable();
        jScrollPane6 = new javax.swing.JScrollPane();
        TBL_primeraJornada = new javax.swing.JTable();
        jLabel14 = new javax.swing.JLabel();
        BTNagregarPrimeraJornada = new javax.swing.JButton();
        BTNprimeraJornada = new javax.swing.JButton();
        BTNagregarSegundaJornada = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        TBL_segundaJornada = new javax.swing.JTable();
        BTNsegundaJornada = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        scrollPane = new javax.swing.JScrollPane();
        PNL_horas = new javax.swing.JPanel();
        jButton4 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton5.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jButton5.setText("Back <<");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel16.setText("SubTotal");

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel17.setText("Tax");

        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel18.setText("GRAND TOTAL");

        TXTsubtotal.setEditable(false);
        TXTsubtotal.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N

        TXT_impuestos.setEditable(false);
        TXT_impuestos.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        TXT_impuestos.setText("0.0");

        TXTtotal.setEditable(false);
        TXTtotal.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        TXTtotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TXTtotalActionPerformed(evt);
            }
        });

        jLabel23.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel23.setText("Globlal Regular Hours");

        jLabel24.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel24.setText("Global Premium Hours");

        TXTtotalHorasRegulares.setEditable(false);
        TXTtotalHorasRegulares.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N

        TXTtotalHorasExtra.setEditable(false);
        TXTtotalHorasExtra.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jButton1.setText("Print Report / Save");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel30.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel30.setText("Folio");

        TXT_buscarFolio.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        TXT_buscarFolio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TXT_buscarFolioKeyReleased(evt);
            }
        });

        TBLfolios.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        TBLfolios.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane8.setViewportView(TBLfolios);

        jButton3.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jButton3.setText("Open");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jTabbedPane1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setText("Folio");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel2.setText("Date");

        LBLdisponible.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        LBLdisponible.setForeground(new java.awt.Color(0, 153, 0));
        LBLdisponible.setText("Available");

        LBL_noDisponible.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        LBL_noDisponible.setForeground(new java.awt.Color(255, 0, 51));
        LBL_noDisponible.setText("Not Available");

        TXT_folio.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        TXT_folio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TXT_folioKeyReleased(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel5.setText("Internal Notes");

        TXT_descripcion.setColumns(20);
        TXT_descripcion.setFont(new java.awt.Font("Monospaced", 0, 24)); // NOI18N
        TXT_descripcion.setRows(5);
        TXT_descripcion.setText("Comentarios Internos Soxtec\n*******");
        jScrollPane1.setViewportView(TXT_descripcion);

        CMBlugar.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        CMBlugar.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        CMBlugar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CMBlugarActionPerformed(evt);
            }
        });

        jLabel31.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel31.setText("Locations");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel3.setText("Name");

        TXT_nombre.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        TXT_nombre.setText("Yazaki North America, Inc.");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel4.setText("Address");

        TXT_direccion.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        TXT_direccion.setText("6801 Haggerty Road Canton, Michigan Zp 48187");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel6.setText("Requestor");

        txt_solicitante.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        txt_solicitante.setText("Mancinas Ricardo");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel7.setText("Order Purchase");

        TXT_po.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        TXT_po.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TXT_poKeyReleased(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel8.setText("Vendor");

        TXTvendedor.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        TXTvendedor.setText("11122");

        jLabel19.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel19.setText("Regular Hours Cost");

        TXTprecioHorasRegulares.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        TXTprecioHorasRegulares.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TXTprecioHorasRegularesKeyReleased(evt);
            }
        });

        jLabel22.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel22.setText("$");

        jLabel20.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel20.setText("Premium Hours Cost");

        TXTprecioHorasTiempoExtra.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        TXTprecioHorasTiempoExtra.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TXTprecioHorasTiempoExtraKeyReleased(evt);
            }
        });

        jLabel21.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel21.setText("$");

        DTE_fechaFinPeriodo.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        DTE_fechaFinPeriodo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                DTE_fechaFinPeriodoMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                DTE_fechaFinPeriodoMouseReleased(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel10.setText("End");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel9.setText("Start");

        DTE_fechaInicioPeriodo.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        CMBestatus.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        CMBestatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel32.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel32.setText("Status");

        DTE_fecha.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        DTE_fecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DTE_fechaActionPerformed(evt);
            }
        });

        LBLorderNotAvalible.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        LBLorderNotAvalible.setForeground(new java.awt.Color(255, 0, 51));
        LBLorderNotAvalible.setText("Not Available");

        LBLorderAvalible.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        LBLorderAvalible.setForeground(new java.awt.Color(51, 153, 0));
        LBLorderAvalible.setText("Available");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(237, 237, 237)
                                .addComponent(jLabel8))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel19)
                                .addGap(139, 139, 139)
                                .addComponent(jLabel20))
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(DTE_fechaInicioPeriodo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(65, 65, 65)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(DTE_fechaFinPeriodo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel21)
                                        .addGap(18, 18, 18)
                                        .addComponent(TXTprecioHorasTiempoExtra, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel10)))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(TXT_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel31)
                                    .addComponent(CMBlugar, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addComponent(TXT_po, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(LBLorderNotAvalible)
                                .addGap(18, 18, 18)
                                .addComponent(LBLorderAvalible)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(TXTvendedor, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(TXT_direccion, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txt_solicitante, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel32)
                                    .addComponent(CMBestatus, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(108, 108, 108))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(TXT_folio, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel1))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(LBL_noDisponible)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(LBLdisponible)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(140, 140, 140)
                                        .addComponent(jLabel2))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(59, 59, 59)
                                        .addComponent(DTE_fecha, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel22)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(TXTprecioHorasRegulares, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addGap(11, 11, 11)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TXT_folio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LBL_noDisponible)
                    .addComponent(LBLdisponible)
                    .addComponent(DTE_fecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel31)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TXT_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CMBlugar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TXT_direccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel32))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_solicitante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CMBestatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TXT_po, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TXTvendedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LBLorderNotAvalible)
                    .addComponent(LBLorderAvalible))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(jLabel20))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TXTprecioHorasRegulares, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel22)
                    .addComponent(jLabel21)
                    .addComponent(TXTprecioHorasTiempoExtra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(DTE_fechaInicioPeriodo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(DTE_fechaFinPeriodo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jScrollPane9.setViewportView(jPanel2);

        jTabbedPane1.addTab("INVOICE", jScrollPane9);

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel11.setText("Description");

        TXT_conceptoTexto.setEditable(false);
        TXT_conceptoTexto.setColumns(20);
        TXT_conceptoTexto.setFont(new java.awt.Font("Monospaced", 0, 24)); // NOI18N
        TXT_conceptoTexto.setRows(5);
        TXT_conceptoTexto.setText("-----\t\t\t\n\t\t\t\nLocations of sorting /reworking:\t\t\t\n\t\t\n\t\t\t\nSEVERAL PART NUMBERS\t\t\t\nHarnesses sorted: \t\t\t\nHarnesses rejected: \t\nHarnesses reworked: ");
        jScrollPane3.setViewportView(TXT_conceptoTexto);

        TBLconceptosAdicionales.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        TBLconceptosAdicionales.setModel(TBLMDLconceptosAdicionales);
        jScrollPane7.setViewportView(TBLconceptosAdicionales);

        TXTcantidad.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N

        jLabel25.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel25.setText("Aditionals");

        jLabel26.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel26.setText("Quantity");

        jLabel27.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel27.setText("Description");

        TXTdescripcion.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N

        jLabel28.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel28.setText("Unity Price");

        TXTprecioUnitario.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N

        BTNadicionalAgregar.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        BTNadicionalAgregar.setText("Add");
        BTNadicionalAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNadicionalAgregarActionPerformed(evt);
            }
        });

        BTNadicionalBorrar.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        BTNadicionalBorrar.setText("X");
        BTNadicionalBorrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNadicionalBorrarActionPerformed(evt);
            }
        });

        TXT_totalAdicionales.setEditable(false);
        TXT_totalAdicionales.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N

        jLabel29.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel29.setText("Total");

        TXTsorteados.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        TXTsorteados.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TXTsorteadosKeyReleased(evt);
            }
        });

        jLabel33.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel33.setText("Harnesses Soted:");

        jLabel34.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel34.setText("Harnesses rejected");

        TXTrechazados.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        TXTrechazados.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TXTrechazadosKeyReleased(evt);
            }
        });

        jLabel35.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel35.setText("Harnesses reworked");

        TXTretrabajados.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        TXTretrabajados.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TXTretrabajadosKeyReleased(evt);
            }
        });

        jLabel36.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel36.setText("Description of the problem");

        TXT_descripcion_problema.setColumns(20);
        TXT_descripcion_problema.setFont(new java.awt.Font("Monospaced", 0, 24)); // NOI18N
        TXT_descripcion_problema.setRows(5);
        TXT_descripcion_problema.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TXT_descripcion_problemaKeyReleased(evt);
            }
        });
        jScrollPane10.setViewportView(TXT_descripcion_problema);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel11)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel29)
                                    .addGap(27, 27, 27)
                                    .addComponent(TXT_totalAdicionales, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 487, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(BTNadicionalBorrar))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(TXTcantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(TXTdescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(TXTprecioUnitario, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(BTNadicionalAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGap(87, 87, 87))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel26)
                            .addGap(20, 20, 20)
                            .addComponent(jLabel27)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel28)))
                    .addComponent(jLabel25)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel33)
                            .addComponent(jLabel34))
                        .addGap(22, 22, 22)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(TXTsorteados, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TXTrechazados, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel35)
                        .addGap(18, 18, 18)
                        .addComponent(TXTretrabajados, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel36)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 617, Short.MAX_VALUE)
                        .addComponent(jScrollPane10, javax.swing.GroupLayout.Alignment.LEADING)))
                .addContainerGap(109, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TXTsorteados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel33))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel34)
                    .addComponent(TXTrechazados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel35)
                    .addComponent(TXTretrabajados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel36)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 66, Short.MAX_VALUE)
                .addComponent(jLabel25)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28)
                    .addComponent(jLabel27)
                    .addComponent(jLabel26))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TXTcantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TXTdescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TXTprecioUnitario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BTNadicionalAgregar))
                .addGap(40, 40, 40)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(44, 44, 44))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(BTNadicionalBorrar)
                        .addGap(131, 131, 131)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel29)
                    .addComponent(TXT_totalAdicionales, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jScrollPane2.setViewportView(jPanel1);

        jTabbedPane1.addTab("CONCEPTS", jScrollPane2);

        TXTfechaReport.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N

        jLabel37.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel37.setText("Date report");

        jLabel38.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel38.setText("Part Number");

        jLabel39.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel39.setText("Customer Contact");

        TXTcontactoCliente.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N

        TXTnumeroParte.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N

        jLabel40.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel40.setText("Part Type");

        TXTtipoDeParte.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N

        TXT1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N

        TXT2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N

        jButton6.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jButton6.setText("Submit");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        promedioSorteadoMIN.setEditable(false);
        promedioSorteadoMIN.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N

        promedioSorteadoMAX.setEditable(false);
        promedioSorteadoMAX.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N

        jLabel41.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel41.setText("Sorted");

        jLabel42.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel42.setText("Rejected");

        promedioRechazadoMIN.setEditable(false);
        promedioRechazadoMIN.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N

        promedioRechazadoMAX.setEditable(false);
        promedioRechazadoMAX.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N

        promedioRetrabajadoMIN.setEditable(false);
        promedioRetrabajadoMIN.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N

        promedioRetrabajadoMAX.setEditable(false);
        promedioRetrabajadoMAX.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N

        jLabel43.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel43.setText("Reworked");

        PNL_reportes_fechas.setLayout(new javax.swing.BoxLayout(PNL_reportes_fechas, javax.swing.BoxLayout.Y_AXIS));
        scrollPaneReporte.setViewportView(PNL_reportes_fechas);

        promedioSorteado.setEditable(false);
        promedioSorteado.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N

        promedioRechazado.setEditable(false);
        promedioRechazado.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N

        promedioRetrabajado.setEditable(false);
        promedioRetrabajado.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N

        jLabel45.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel45.setText("Average");

        TXTsorteados2.setEditable(false);
        TXTsorteados2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N

        totalSorteados.setEditable(false);
        totalSorteados.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N

        TXTrechazados2.setEditable(false);
        TXTrechazados2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N

        totalRechazados.setEditable(false);
        totalRechazados.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N

        TXTretrabajados2.setEditable(false);
        TXTretrabajados2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N

        totalRetrabajados.setEditable(false);
        totalRetrabajados.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N

        diferenciaSorteados.setEditable(false);
        diferenciaSorteados.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N

        diferenciaRechazados.setEditable(false);
        diferenciaRechazados.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N

        diferenciaRetrabajados.setEditable(false);
        diferenciaRetrabajados.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N

        jLabel46.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel46.setText("Invoice");

        jLabel47.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel47.setText("Total");

        jLabel48.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel48.setText("Diference");

        jLabel44.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel44.setText("Resident Engineer");

        jLabel49.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel49.setText("Approved by");

        TXTresidente.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N

        TXTaprueba.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(jLabel45))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel47, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(11, 11, 11)
                                .addComponent(jLabel46))
                            .addComponent(jLabel48, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(79, 79, 79)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(diferenciaSorteados, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(totalSorteados, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TXTsorteados2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(promedioSorteado, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                                .addComponent(promedioSorteadoMIN, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(promedioSorteadoMAX, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(TXTrechazados2, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(totalRechazados, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(diferenciaRechazados, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(TXTretrabajados2, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(totalRetrabajados, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(diferenciaRetrabajados, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(promedioRechazado, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(promedioRetrabajado, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel42)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(promedioRechazadoMIN, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(promedioRechazadoMAX, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(promedioRetrabajadoMIN, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(promedioRetrabajadoMAX, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel43)))))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel37)
                                    .addComponent(jLabel38)
                                    .addComponent(jLabel40))
                                .addGap(25, 25, 25)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(TXTfechaReport)
                                    .addComponent(TXTnumeroParte)
                                    .addComponent(TXTtipoDeParte, javax.swing.GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel44)
                                .addGap(10, 10, 10)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel41)
                                    .addComponent(TXTresidente, javax.swing.GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE))))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel49)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(TXTaprueba))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel39)
                                .addGap(18, 18, 18)
                                .addComponent(TXTcontactoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(TXT1)
                            .addComponent(TXT2)))
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jButton6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 716, Short.MAX_VALUE)
                        .addComponent(scrollPaneReporte, javax.swing.GroupLayout.Alignment.LEADING)))
                .addContainerGap(26, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TXTfechaReport, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel37)
                    .addComponent(jLabel39)
                    .addComponent(TXTcontactoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel38)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(TXTnumeroParte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(TXT1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel40)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(TXTtipoDeParte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(TXT2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel44)
                        .addComponent(TXTresidente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel49)
                    .addComponent(TXTaprueba, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel42, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel43))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(promedioRechazadoMAX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(promedioRechazadoMIN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(promedioRetrabajadoMAX, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(promedioRetrabajadoMIN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(promedioRechazado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(promedioRetrabajado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jLabel41)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(promedioSorteadoMAX, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(promedioSorteadoMIN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(promedioSorteado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel45))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TXTsorteados2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TXTrechazados2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TXTretrabajados2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel46))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(totalSorteados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(totalRechazados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(totalRetrabajados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel47))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(diferenciaSorteados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(diferenciaRechazados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(diferenciaRetrabajados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel48))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollPaneReporte, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jScrollPane11.setViewportView(jPanel4);

        jTabbedPane1.addTab("REPORT", jScrollPane11);

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel13.setText("Inspectors");

        TXT_buscarTrabajador.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        TXT_buscarTrabajador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TXT_buscarTrabajadorActionPerformed(evt);
            }
        });
        TXT_buscarTrabajador.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TXT_buscarTrabajadorKeyReleased(evt);
            }
        });

        TBLtrabajaores.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        TBLtrabajaores.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane5.setViewportView(TBLtrabajaores);

        TBL_primeraJornada.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        TBL_primeraJornada.setModel(TBLMDLprimeraJornada);
        jScrollPane6.setViewportView(TBL_primeraJornada);

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel14.setText("First Shift");

        BTNagregarPrimeraJornada.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        BTNagregarPrimeraJornada.setText("Add First Shift");
        BTNagregarPrimeraJornada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNagregarPrimeraJornadaActionPerformed(evt);
            }
        });

        BTNprimeraJornada.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        BTNprimeraJornada.setText("X");
        BTNprimeraJornada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNprimeraJornadaActionPerformed(evt);
            }
        });

        BTNagregarSegundaJornada.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        BTNagregarSegundaJornada.setText("Add Second Shift");
        BTNagregarSegundaJornada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNagregarSegundaJornadaActionPerformed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel15.setText("Second Shift");

        TBL_segundaJornada.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        TBL_segundaJornada.setModel(TBLMDLsegundaJornada);
        jScrollPane4.setViewportView(TBL_segundaJornada);

        BTNsegundaJornada.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        BTNsegundaJornada.setText("X");
        BTNsegundaJornada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNsegundaJornadaActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jButton2.setText("Submit");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel12.setText("Worked Hours ");

        PNL_horas.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        PNL_horas.setLayout(new javax.swing.BoxLayout(PNL_horas, javax.swing.BoxLayout.Y_AXIS));
        scrollPane.setViewportView(PNL_horas);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(TXT_buscarTrabajador)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13)
                            .addComponent(jLabel12)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 638, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel14)
                                        .addGap(226, 226, 226))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addComponent(BTNagregarPrimeraJornada, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(60, 60, 60)
                                                .addComponent(BTNprimeraJornada))
                                            .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(BTNagregarSegundaJornada)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(BTNsegundaJornada))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel15)
                                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(scrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 626, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 18, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel13)
                .addGap(7, 7, 7)
                .addComponent(TXT_buscarTrabajador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(jLabel14))
                .addGap(7, 7, 7)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(BTNagregarPrimeraJornada)
                        .addGap(18, 18, 18)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(BTNagregarSegundaJornada)
                        .addComponent(BTNprimeraJornada)
                        .addComponent(BTNsegundaJornada)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        scrollPaneWorkHours.setViewportView(jPanel3);

        jTabbedPane1.addTab("WORK HOURS ", scrollPaneWorkHours);

        jButton4.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jButton4.setText("New");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 685, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(TXTtotalHorasExtra, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TXTtotalHorasRegulares, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel24)
                                    .addComponent(jLabel23))))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel17)
                            .addComponent(jLabel16)
                            .addComponent(jLabel18)
                            .addComponent(TXT_impuestos)
                            .addComponent(TXTsubtotal)
                            .addComponent(TXTtotal, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jLabel30)
                        .addGap(18, 18, 18)
                        .addComponent(TXT_buscarFolio))
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jButton3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton5)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 583, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel23)
                        .addGap(6, 6, 6)
                        .addComponent(TXTtotalHorasRegulares, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel24)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TXTtotalHorasExtra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TXTsubtotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TXT_impuestos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TXTtotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel30)
                    .addComponent(TXT_buscarFolio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton5)
                    .addComponent(jButton4))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        PNL_horas = new javax.swing.JPanel();
        PNL_horas.setLayout(new javax.swing.BoxLayout(PNL_horas, javax.swing.BoxLayout.Y_AXIS));
        scrollPane.setViewportView(PNL_horas);

        Date fechaInicial = DTE_fechaInicioPeriodo.getDate();
        Date fechaFinal = DTE_fechaFinPeriodo.getDate();

        calendar = Calendar.getInstance();

        semanasTrabajadas = diferenciaSemanas(fechaInicial, fechaFinal) + 1;

        int diasTranscurridos = numeroDiasEntreDosFechas(fechaInicial,fechaFinal);
        
       
        
        if (diasTranscurridos >= 0) {  //Si hay numero de dias disponibles

            JPanel panelEmpleado = new JPanel();   //Fila Nueva
            panelEmpleado.setLayout(new BoxLayout(panelEmpleado, BoxLayout.X_AXIS));

            JLabel Titulo = new JLabel("1st Shift");
            Titulo.setFont(new java.awt.Font("Tahoma", 1, 20));
            
            panelEmpleado.add(Titulo);
            PNL_horas.add(panelEmpleado); //Se agrega la Fila

            llenaTrabajadoresConHorasMatutino(//TABLA VESPERTINO
                    diasTranscurridos,
                    fechaInicial,
                    MATUTINO
            );

            panelEmpleado = new JPanel();   //Fila Nueva
            panelEmpleado.setLayout(new BoxLayout(panelEmpleado, BoxLayout.X_AXIS));

            Titulo = new JLabel("2nd Shift");
            Titulo.setFont(new java.awt.Font("Tahoma", 1, 20));
            panelEmpleado.add(Titulo);
            PNL_horas.add(panelEmpleado); //Se agrega la Fila

            llenaTrabajadoresConHorasVespertino(//TABLA VESPERTINO
                    diasTranscurridos,
                    fechaInicial,
                    VESPERTINO
            );
            
            
           
                sumaTotales();
            


        } else {
            javax.swing.JOptionPane.showMessageDialog(null, "La fecha de periodo Final es menor que la fecha de periodo Inicial");
        }

       
    }//GEN-LAST:event_jButton2ActionPerformed

    public static int numeroDiasEntreDosFechas(Date fecha1, Date fecha2){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        
        
        String strDate1 = sdf.format(fecha1);
        String strDate2 = sdf.format(fecha2);
 
        long diffDays=0;
        
        try {
            Date fechaParse1=new SimpleDateFormat("dd/MM/yyyy").parse(strDate1);
            Date fechaParse2=new SimpleDateFormat("dd/MM/yyyy").parse(strDate2);
            long startTime = fechaParse1.getTime();
            long endTime = fechaParse2.getTime();
            long diffTime = endTime - startTime;
            diffDays = diffTime / (1000 * 60 * 60 * 24);
        
        } catch (ParseException ex) {
            Logger.getLogger(factura_ifz.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
       
        
        
     
        return (int)diffDays;
    }
    
    public int diferenciaSemanas(Date fechaInicial, Date fechaFinal) {
        int diferenciaSemana;

        calendar.setTime(fechaInicial); // Configuramos la fecha que se recibe
        int SemanaInicial = calendar.get(Calendar.WEEK_OF_YEAR);
        calendar.setTime(fechaFinal); // Configuramos la fecha que se recibe
        int SemanaFinal = calendar.get(Calendar.WEEK_OF_YEAR);

        int diferencia = SemanaFinal - SemanaInicial;

        if (diferencia >= 0) {
            diferenciaSemana = diferencia;
        } else {

            diferenciaSemana = (SemanaFinal + 53) - SemanaInicial;

        }

        return diferenciaSemana;
    }

    public void llenaTrabajadoresConHorasMatutino(
            int diasTranscurridos,
            Date fechaInicial,
            int turno
    ) {

        horasEnSemanaTrabajadaMatutino = new float[semanasTrabajadas][TBLMDLprimeraJornada.getRowCount()];

        LBL_nombreEmpleadoMatutino = new JLabel[TBLMDLprimeraJornada.getRowCount()];
        
        idEmpleadoMatutino = new int[TBLMDLprimeraJornada.getRowCount()];
        BTN_eliminarMatutino = new JButton[TBLMDLprimeraJornada.getRowCount()][diasTranscurridos + 1];

        semanaTrabajadaMatutino = new int[diasTranscurridos + 1];
        LBL_diaMatutino = new JLabel[TBLMDLprimeraJornada.getRowCount()][diasTranscurridos + 1];
        fechasMatutuno = new String[TBLMDLprimeraJornada.getRowCount()][diasTranscurridos + 1];
        TXThoraEntradaMatutino = new JFormattedTextField[TBLMDLprimeraJornada.getRowCount()][diasTranscurridos + 1];
        TXThoraSalidaMatutino = new JFormattedTextField[TBLMDLprimeraJornada.getRowCount()][diasTranscurridos + 1];
        TXThoraTrabajadaMatutino = new JFormattedTextField[TBLMDLprimeraJornada.getRowCount()][diasTranscurridos + 1];

        for (int i = 0; i < TBLMDLprimeraJornada.getRowCount(); i++) {  // Lista Primera jornada

            JPanel panelEmpleado = new JPanel();   //Fila Nueva
            panelEmpleado.setLayout(new BoxLayout(panelEmpleado, BoxLayout.X_AXIS));

            String Name=TBLMDLprimeraJornada.getValueAt(i, 0)+"";
            LBL_nombreEmpleadoMatutino[i] = new JLabel("    " +  rellenaEspaciosEnBlanco(   Name.toUpperCase()+""   )   + "");  //
            LBL_nombreEmpleadoMatutino[i].setFont(new java.awt.Font("Lucida Console", 1, 24));
            LBL_nombreEmpleadoMatutino[i].setForeground(Color.RED);
            panelEmpleado.add(LBL_nombreEmpleadoMatutino[i]);
            
            idEmpleadoMatutino[i]=Integer.parseInt(TBLMDLprimeraJornada.getValueAt(i, 1) + "");

            for (int j = 0; j <= diasTranscurridos; j++) {

                calendar.setTime(fechaInicial); // Configuramos la fecha que se recibe
                calendar.add(Calendar.DATE, j);  // numero de horas a añadir, o restar en caso de horas<0
                String DiaMes = formatoDiaMes.format(calendar.getTime());
                String DiaSemana = formatoDiaSemana.format(calendar.getTime());
                String DiaCompleto = formatoSQL.format(calendar.getTime());
                semanaTrabajadaMatutino[j] = diferenciaSemanas(fechaInicial, calendar.getTime()) + 1;  //Se saca la semana y se agrega  los dias

                String nombreDia = "";//LMMiJVSD
                if (DiaSemana.equals("1")) {
                    nombreDia = "L";
                }
                if (DiaSemana.equals("2")) {
                    nombreDia = "M";
                }
                if (DiaSemana.equals("3")) {
                    nombreDia = "Mi";
                }
                if (DiaSemana.equals("4")) {
                    nombreDia = "J";
                }
                if (DiaSemana.equals("5")) {
                    nombreDia = "V";
                }
                if (DiaSemana.equals("6")) {
                    nombreDia = "S";
                }
                if (DiaSemana.equals("7")) {
                    nombreDia = "D";
                }

                LBL_diaMatutino[i][j] = new JLabel("       " + nombreDia + "-" + DiaMes);
                LBL_diaMatutino[i][j].setFont(new java.awt.Font("Tahoma", Font.BOLD, 24));
                panelEmpleado.add(LBL_diaMatutino[i][j]);
                
                fechasMatutuno[i][j]=DiaCompleto;
                

              // 6 Y 8
                // 4 Y 2.30  6
                try {
                    MaskFormatter mascaraHoraForma = new MaskFormatter("##:##");

                    TXThoraEntradaMatutino[i][j] = new JFormattedTextField(mascaraHoraForma);
                    TXThoraEntradaMatutino[i][j].setText("06:00");
                    TXThoraEntradaMatutino[i][j].setFont(new java.awt.Font("Tahoma", Font.BOLD, 24));
                    EventoTeclas Eventotecla = new EventoTeclas(i, j, EventoTeclas.Entrada, EventoTeclas.Matutino);
                    TXThoraEntradaMatutino[i][j].addKeyListener(Eventotecla);
                    panelEmpleado.add(TXThoraEntradaMatutino[i][j]);

                    TXThoraSalidaMatutino[i][j] = new JFormattedTextField(mascaraHoraForma);
                    TXThoraSalidaMatutino[i][j].setText("18:00");
                    TXThoraSalidaMatutino[i][j].setFont(new java.awt.Font("Tahoma", Font.BOLD, 24));
                    Eventotecla = new EventoTeclas(i, j, EventoTeclas.Salida, EventoTeclas.Matutino);
                    TXThoraSalidaMatutino[i][j].addKeyListener(Eventotecla);
                    panelEmpleado.add(TXThoraSalidaMatutino[i][j]);

                    
                    TXThoraTrabajadaMatutino[i][j] = new JFormattedTextField();  //8  ,  9   , 12
                    TXThoraTrabajadaMatutino[i][j].setFont(new java.awt.Font("Tahoma", Font.BOLD, 24));
                    TXThoraTrabajadaMatutino[i][j].setText("12.00");
                    TXThoraTrabajadaMatutino[i][j].setEnabled(false);
                    panelEmpleado.add(TXThoraTrabajadaMatutino[i][j]);
                    
                    BTN_eliminarMatutino[i][j] = new JButton("X");  //Boton de Cerrar
                    EventoBorrar eventoBorrar=new EventoBorrar(i,j,EventoBorrar.Matutino);
                    BTN_eliminarMatutino[i][j].addActionListener(eventoBorrar);
                    panelEmpleado.add(BTN_eliminarMatutino[i][j]);
                    
                    
                   

                } catch (ParseException ex) {
                    Logger.getLogger(factura_ifz.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
            PNL_horas.add(panelEmpleado); //Se agrega la Fila
        }

    }

    
    public class EventoBorrar implements ActionListener{
        int i;
        int j;
        int turno;
        public static final int Matutino=1;
        public static final int Vespertino=2;

        public EventoBorrar(int i, int j,int turno) {
            this.i = i;
            this.j = j;
            this.turno = turno;
        }
        
        
        
        
        @Override
        public void actionPerformed(ActionEvent e) {
            
            

            int resp = JOptionPane.showConfirmDialog(null, "¿Esta seguro de eliminar este dia?", "Alerta!", JOptionPane.YES_NO_OPTION);
            
            if (resp==0){
                if (turno==Matutino){

                    LBL_diaMatutino[i][j].setVisible(false);
                    BTN_eliminarMatutino[i][j].setVisible(false);
                    TXThoraEntradaMatutino[i][j].setText("00:00");
                    TXThoraEntradaMatutino[i][j].setVisible(false);
                    TXThoraSalidaMatutino[i][j].setText("00:00");
                    TXThoraSalidaMatutino[i][j].setVisible(false);
                    TXThoraTrabajadaMatutino[i][j].setText("0.0");
                    TXThoraTrabajadaMatutino[i][j].setVisible(false);
                }
                if (turno==Vespertino){

                    LBL_diaVespertino[i][j].setVisible(false);
                    BTN_eliminarVespertino[i][j].setVisible(false);
                    TXThoraEntradaVespertino[i][j].setText("00:00");
                    TXThoraEntradaVespertino[i][j].setVisible(false);
                    TXThoraSalidaVespertino[i][j].setText("00:00");
                    TXThoraSalidaVespertino[i][j].setVisible(false);
                    TXThoraTrabajadaVespertino[i][j].setText("0.0");
                    TXThoraTrabajadaVespertino[i][j].setVisible(false);


                }
               
                    sumaTotales();
             
            }
        }
        
    }
    
    public class EventoTeclas extends KeyAdapter {

        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
        int i;
        int j;
        int opcion;
        int horario;

        public static final int Matutino = 1;
        public static final int Vespertino = 2;
        public static final int Entrada = 3;
        public static final int Salida = 4;

        public EventoTeclas(int i, int j, int opcion, int horario) {
            this.i = i;
            this.j = j;
            this.opcion = opcion;
            this.horario = horario;
        }

        @Override
        public void keyReleased(KeyEvent e) {
            try {
                
               
                if (e.getKeyCode() == 38) {

                    if (horario == Matutino) {
                        if (opcion == Entrada) {

                            try {

                                Date date = formatter.parse(TXThoraEntradaMatutino[i][j].getText());
                                calendar.setTime(date); // Configuramos la fecha que se recibe
                                calendar.add(Calendar.MINUTE, +30);
                                TXThoraEntradaMatutino[i][j].setText(formatter.format(calendar.getTime()));
                            } catch (ParseException ex) {
                            }

                        }
                        if (opcion == Salida) {

                            try {

                                Date date = formatter.parse(TXThoraSalidaMatutino[i][j].getText());
                                calendar.setTime(date); // Configuramos la fecha que se recibe
                                calendar.add(Calendar.MINUTE, +30);
                                TXThoraSalidaMatutino[i][j].setText(formatter.format(calendar.getTime()));
                            } catch (ParseException ex) {
                            }

                        }
                        
                        
                        
                        
                        
                    }
                    if (horario == Vespertino) {
                        if (opcion == Entrada) {

                            try {

                                Date date = formatter.parse(TXThoraEntradaVespertino[i][j].getText());
                                calendar.setTime(date); // Configuramos la fecha que se recibe
                                calendar.add(Calendar.MINUTE, +30);
                                TXThoraEntradaVespertino[i][j].setText(formatter.format(calendar.getTime()));
                            } catch (ParseException ex) {
                            }

                        }
                        if (opcion == Salida) {
                            try {

                                Date date = formatter.parse(TXThoraSalidaVespertino[i][j].getText());
                                calendar.setTime(date); // Configuramos la fecha que se recibe
                                calendar.add(Calendar.MINUTE, +30);
                                TXThoraSalidaVespertino[i][j].setText(formatter.format(calendar.getTime()));
                            } catch (ParseException ex) {
                            }

                        }
                        
                    }
                    

                }
                if (e.getKeyCode() == 40) {
                    if (horario == Matutino) {
                        if (opcion == Entrada) {

                            try {

                                Date date = formatter.parse(TXThoraEntradaMatutino[i][j].getText());
                                calendar.setTime(date); // Configuramos la fecha que se recibe
                                calendar.add(Calendar.MINUTE, -30);
                                TXThoraEntradaMatutino[i][j].setText(formatter.format(calendar.getTime()));
                            } catch (ParseException ex) {
                            }

                        }
                        if (opcion == Salida) {

                            try {

                                Date date = formatter.parse(TXThoraSalidaMatutino[i][j].getText());
                                calendar.setTime(date); // Configuramos la fecha que se recibe
                                calendar.add(Calendar.MINUTE, -30);
                                TXThoraSalidaMatutino[i][j].setText(formatter.format(calendar.getTime()));
                            } catch (ParseException ex) {
                            }

                        }
                        

                    }
                    if (horario == Vespertino) {
                        if (opcion == Entrada) {

                            try {

                                Date date = formatter.parse(TXThoraEntradaVespertino[i][j].getText());
                                calendar.setTime(date); // Configuramos la fecha que se recibe
                                calendar.add(Calendar.MINUTE, -30);
                                TXThoraEntradaVespertino[i][j].setText(formatter.format(calendar.getTime()));
                            } catch (ParseException ex) {
                            }

                        }
                        if (opcion == Salida) {
                            try {

                                Date date = formatter.parse(TXThoraSalidaVespertino[i][j].getText());
                                calendar.setTime(date); // Configuramos la fecha que se recibe
                                calendar.add(Calendar.MINUTE, -30);
                                TXThoraSalidaVespertino[i][j].setText(formatter.format(calendar.getTime()));
                            } catch (ParseException ex) {
                            }

                        }
                        
                    }
                    
                }
                TXThoraTrabajadaMatutino[i][j].setText(String.format("%.2f", calculaHoras(TXThoraEntradaMatutino[i][j].getText(),TXThoraSalidaMatutino[i][j].getText())));
                        TXThoraTrabajadaVespertino[i][j].setText(String.format("%.2f", calculaHoras(TXThoraEntradaVespertino[i][j].getText(),TXThoraSalidaVespertino[i][j].getText())));
               
            } catch (Exception ex) {

            }
            
             sumaTotales();
        }

        @Override
        public void keyTyped(KeyEvent e) {
        }

        @Override
        public void keyPressed(KeyEvent e) {
        }
    }
    
    
    
    
    public class EventoTeclasReporte extends KeyAdapter {

        public static final int  SORTEADOS=1;
        public static final int  RECHAZADOS=2;
        public static final int  RETRABAJADOS=3;
        
        
        int type;
        int i;
        

        public EventoTeclasReporte(int type, int i) {
            this.type = type;
            this.i = i;
            
        }

        @Override
        public void keyReleased(KeyEvent e) {
            try {
                
               
                if (e.getKeyCode() == 38) {
                    
                    if (type==SORTEADOS){
                        sorteados[i].setText(   (Integer.parseInt(sorteados[i].getText())+1)+""    );
                    }
                    if (type==RECHAZADOS){
                        rejected[i].setText(   (Integer.parseInt(rejected[i].getText())+1)+""    );
                    }
                    if (type==RETRABAJADOS){
                        reworked[i].setText(   (Integer.parseInt(reworked[i].getText())+1)+""    );
                    }
                    
                    
                }
                if (e.getKeyCode() == 40) {
                    
                    if (type==SORTEADOS){
                        sorteados[i].setText(   (Integer.parseInt(sorteados[i].getText())-1)+""    );
                    }
                    if (type==RECHAZADOS){
                        rejected[i].setText(   (Integer.parseInt(rejected[i].getText())-1)+""    );
                    }
                    if (type==RETRABAJADOS){
                        reworked[i].setText(   (Integer.parseInt(reworked[i].getText())-1)+""    );
                    }
                    
                }
               
                sumaReporte();
                   
               
            } catch (Exception ex) {

            }
            
             
        }

        @Override
        public void keyTyped(KeyEvent e) {
        }

        @Override
        public void keyPressed(KeyEvent e) {
        }
        
    }

    public float calculaHoras(String horaInicial, String horaFinal) {
        
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
        Date HoraInicial=new Date();
        Date HoraFinal=new Date();
        try {
            HoraInicial = formatter.parse(horaInicial);
            HoraFinal = formatter.parse(horaFinal);
        } catch (ParseException ex) {
            Logger.getLogger(factura_ifz.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        
       
        long tiempoInicial=HoraInicial.getTime();
        long tiempoFinal=HoraFinal.getTime(); 
        long resta=tiempoFinal - tiempoInicial;
        //el metodo getTime te devuelve en mili segundos para saberlo en mins debes hacer
        float hora = resta/3600000;
        float restohora = resta%3600000;
        float minuto = restohora/60000;
        float minutoEnHora=minuto/60;
        
        float total=hora+minutoEnHora;
        if (total<0){
           total=24+total;
        }
        
        
        return total;
        
    }

    public void llenaTrabajadoresConHorasVespertino(
            int diasTranscurridos,
            Date fechaInicial,
            int turno
    ) {

        horasEnSemanaTrabajadaVespertino = new float[semanasTrabajadas][TBLMDLsegundaJornada.getRowCount()];

        
        LBL_nombreEmpleadoVespertino = new JLabel[TBLMDLsegundaJornada.getRowCount()];
        
        idEmpleadoVespertino = new int[TBLMDLsegundaJornada.getRowCount()];
        BTN_eliminarVespertino = new JButton[TBLMDLsegundaJornada.getRowCount()][diasTranscurridos + 1];

        semanaTrabajadaVespertino = new int[diasTranscurridos + 1];
        LBL_diaVespertino = new JLabel[TBLMDLsegundaJornada.getRowCount()][diasTranscurridos + 1];
        fechasVespertino = new String[TBLMDLsegundaJornada.getRowCount()][diasTranscurridos + 1];
        TXThoraEntradaVespertino = new JFormattedTextField[TBLMDLsegundaJornada.getRowCount()][diasTranscurridos + 1];
        TXThoraSalidaVespertino = new JFormattedTextField[TBLMDLsegundaJornada.getRowCount()][diasTranscurridos + 1];
        TXThoraTrabajadaVespertino = new JFormattedTextField[TBLMDLsegundaJornada.getRowCount()][diasTranscurridos + 1];

        for (int i = 0; i < TBLMDLsegundaJornada.getRowCount(); i++) {  // Lista Primera jornada

            JPanel panelEmpleado = new JPanel();   //Fila Nueva
            panelEmpleado.setLayout(new BoxLayout(panelEmpleado, BoxLayout.X_AXIS));

            
            String Name=TBLMDLsegundaJornada.getValueAt(i, 0)+"";
            

            LBL_nombreEmpleadoVespertino[i] = new JLabel("    " +  rellenaEspaciosEnBlanco(   Name.toUpperCase()+""   )   + "");  //
            LBL_nombreEmpleadoVespertino[i].setFont(new java.awt.Font("Lucida Console", 1, 24));
            LBL_nombreEmpleadoVespertino[i].setForeground(Color.BLUE);
            panelEmpleado.add(LBL_nombreEmpleadoVespertino[i]);
            
            idEmpleadoVespertino[i]=Integer.parseInt(TBLMDLsegundaJornada.getValueAt(i, 1) + "");
            

            for (int j = 0; j <= diasTranscurridos; j++) {

                calendar.setTime(fechaInicial); // Configuramos la fecha que se recibe
                calendar.add(Calendar.DATE, j);  // numero de horas a añadir, o restar en caso de horas<0
                String DiaMes = formatoDiaMes.format(calendar.getTime());
                String DiaSemana = formatoDiaSemana.format(calendar.getTime());
                String DiaCompleto = formatoSQL.format(calendar.getTime());
                semanaTrabajadaVespertino[j] = diferenciaSemanas(fechaInicial, calendar.getTime()) + 1;  //Se saca la semana y se agrega  los dias

                String nombreDia = "";//LMMiJVSD
                if (DiaSemana.equals("1")) {
                    nombreDia = "L";
                }
                if (DiaSemana.equals("2")) {
                    nombreDia = "M";
                }
                if (DiaSemana.equals("3")) {
                    nombreDia = "Mi";
                }
                if (DiaSemana.equals("4")) {
                    nombreDia = "J";
                }
                if (DiaSemana.equals("5")) {
                    nombreDia = "V";
                }
                if (DiaSemana.equals("6")) {
                    nombreDia = "S";
                }
                if (DiaSemana.equals("7")) {
                    nombreDia = "D";
                }

                LBL_diaVespertino[i][j] = new JLabel("       " + nombreDia + "-" + DiaMes);
                LBL_diaVespertino[i][j].setFont(new java.awt.Font("Tahoma", Font.BOLD, 24));
                panelEmpleado.add(LBL_diaVespertino[i][j]);
                
                fechasVespertino[i][j]=DiaCompleto;
                

              // 6 Y 8
                // 4 Y 2.30  6
                try {
                    MaskFormatter mascaraHoraForma = new MaskFormatter("##:##");

                    TXThoraEntradaVespertino[i][j] = new JFormattedTextField(mascaraHoraForma);
                    TXThoraEntradaVespertino[i][j].setText("18:00");
                    TXThoraEntradaVespertino[i][j].setFont(new java.awt.Font("Tahoma", Font.BOLD, 24));
                    EventoTeclas EventoTecla = new EventoTeclas(i, j, EventoTeclas.Entrada, EventoTeclas.Vespertino);
                    TXThoraEntradaVespertino[i][j].addKeyListener(EventoTecla);
                    panelEmpleado.add(TXThoraEntradaVespertino[i][j]);

                    TXThoraSalidaVespertino[i][j] = new JFormattedTextField(mascaraHoraForma);
                    TXThoraSalidaVespertino[i][j].setText("06:00");
                    TXThoraSalidaVespertino[i][j].setFont(new java.awt.Font("Tahoma", Font.BOLD, 24));
                    EventoTecla = new EventoTeclas(i, j, EventoTeclas.Salida, EventoTeclas.Vespertino);
                    TXThoraSalidaVespertino[i][j].addKeyListener(EventoTecla);
                    panelEmpleado.add(TXThoraSalidaVespertino[i][j]);

                    
                    TXThoraTrabajadaVespertino[i][j] = new JFormattedTextField();  //8  ,  9   , 12
                    TXThoraTrabajadaVespertino[i][j].setFont(new java.awt.Font("Tahoma", Font.BOLD, 24));
                    TXThoraTrabajadaVespertino[i][j].setText("12.00");
                    TXThoraTrabajadaVespertino[i][j].setEnabled(false);
                    panelEmpleado.add(TXThoraTrabajadaVespertino[i][j]);
                    
                    BTN_eliminarVespertino[i][j] = new JButton("X");  //Boton de Cerrar
                    EventoBorrar eventoBorrar=new EventoBorrar(i,j,EventoBorrar.Vespertino);
                    BTN_eliminarVespertino[i][j].addActionListener(eventoBorrar);
                    panelEmpleado.add(BTN_eliminarVespertino[i][j]);
                    
                   
                    

                } catch (ParseException ex) {
                    Logger.getLogger(factura_ifz.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
            PNL_horas.add(panelEmpleado); //Se agrega la Fila
        }

    }

    public void sumaTotales() {

        float horasRegulares = 0;
        float horasExtras = 0;

        horasEnSemanaTrabajadaMatutino = new float[semanasTrabajadas][TBLMDLprimeraJornada.getRowCount()];
        horasEnSemanaTrabajadaVespertino = new float[semanasTrabajadas][TBLMDLsegundaJornada.getRowCount()];

        for (int semana = 0; semana < semanasTrabajadas; semana++) {

            for (int trabajador = 0; trabajador < TXThoraTrabajadaMatutino.length; trabajador++) {
                for (int dia = 0; dia < TXThoraTrabajadaMatutino[0].length; dia++) {
                    if (semanaTrabajadaMatutino[dia] == semana + 1) {  //Si es la misma semana
                        horasEnSemanaTrabajadaMatutino[semana][trabajador] = horasEnSemanaTrabajadaMatutino[semana][trabajador] + Float.parseFloat(TXThoraTrabajadaMatutino[trabajador][dia].getText());
                    }
                }
            }

            for (int trabajador = 0; trabajador < TXThoraTrabajadaVespertino.length; trabajador++) {
                for (int dia = 0; dia < TXThoraTrabajadaVespertino[0].length; dia++) {
                    if (semanaTrabajadaVespertino[dia] == semana + 1) {  //Si es la misma semana
                        horasEnSemanaTrabajadaVespertino[semana][trabajador] = horasEnSemanaTrabajadaVespertino[semana][trabajador] + Float.parseFloat(TXThoraTrabajadaVespertino[trabajador][dia].getText());
                    }
                }
            }

        }

        for (int semana = 0; semana < semanasTrabajadas; semana++) {
            for (int trabajador = 0; trabajador < TXThoraTrabajadaMatutino.length; trabajador++) {

                if (horasEnSemanaTrabajadaMatutino[semana][trabajador] <= 48) {
                    horasRegulares = horasRegulares + horasEnSemanaTrabajadaMatutino[semana][trabajador];
                } else {
                    horasRegulares = horasRegulares + 48;
                    horasExtras = horasExtras + horasEnSemanaTrabajadaMatutino[semana][trabajador] - 48;
                }
            }
        }

        for (int semana = 0; semana < semanasTrabajadas; semana++) {
            for (int trabajador = 0; trabajador < TXThoraTrabajadaVespertino.length; trabajador++) {
                if (horasEnSemanaTrabajadaVespertino[semana][trabajador] <= 48) {
                    horasRegulares = horasRegulares + horasEnSemanaTrabajadaVespertino[semana][trabajador];
                } else {
                    horasRegulares = horasRegulares + 48;
                    horasExtras = horasExtras + horasEnSemanaTrabajadaVespertino[semana][trabajador] - 48;
                }
            }
        }

        TXTtotalHorasRegulares.setText(horasRegulares + "");
        TXTtotalHorasExtra.setText(horasExtras + "");
        sumaAdicionales();
        if (
                !TXTtotalHorasRegulares.getText().equals("") &&
                !TXTtotalHorasExtra.getText().equals("") &&
                !TXT_totalAdicionales.getText().equals("") 
                
            ){
            Float totalCostoHorasRegulares;
            Float totalCostoHorasExtra;
            if (isNumeric(TXTprecioHorasRegulares.getText()) && isNumeric(TXTprecioHorasTiempoExtra.getText())){
                totalCostoHorasRegulares = Float.parseFloat(TXTprecioHorasRegulares.getText())*Float.parseFloat(TXTtotalHorasRegulares.getText());
                totalCostoHorasExtra = Float.parseFloat(TXTprecioHorasTiempoExtra.getText())*Float.parseFloat(TXTtotalHorasExtra.getText());
                float subTotal=totalCostoHorasRegulares+totalCostoHorasExtra+Float.parseFloat(TXT_totalAdicionales.getText());
                TXTsubtotal.setText(subTotal+"");
                float Total=subTotal+Float.parseFloat(TXT_impuestos.getText());
                TXTtotal.setText(Total+"");
            }else{
                TXTsubtotal.setText("?? Llena Costos");
                TXTtotal.setText("?? Llena Costos");
            }
            
            
            
            
        }
    }

    
    private static boolean isNumeric(String cadena){
	try {
		Float.parseFloat(cadena);
		return true;
	} catch (NumberFormatException nfe){
		return false;
	}
    }

    private void TXT_buscarTrabajadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TXT_buscarTrabajadorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TXT_buscarTrabajadorActionPerformed

    private void TXT_buscarTrabajadorKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TXT_buscarTrabajadorKeyReleased
        TBLtrabajaores.setModel(Instacia.persona_srv.LISTAempleados(TXT_buscarTrabajador.getText()));
    }//GEN-LAST:event_TXT_buscarTrabajadorKeyReleased

    private void BTNagregarPrimeraJornadaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNagregarPrimeraJornadaActionPerformed

        if (TBLtrabajaores.getSelectedRow() != -1) {

            String valorSeleccionadoDeTabla = TBLtrabajaores.getValueAt(TBLtrabajaores.getSelectedRow(), 0) + "";
            String valorestablaJornada;
            boolean valorNoEncontradoEntabla = true;
            for (int i = 0; i < TBL_primeraJornada.getRowCount(); i++) {
                valorestablaJornada = TBL_primeraJornada.getValueAt(i, 0) + "";
                if (valorSeleccionadoDeTabla.equals(valorestablaJornada)) {
                    valorNoEncontradoEntabla = false;
                }
            }

            if (valorNoEncontradoEntabla) {
                TBLMDLprimeraJornada.addRow(
                                                new String[]{
                                                                TBLtrabajaores.getValueAt(TBLtrabajaores.getSelectedRow(), 0) + "",
                                                                TBLtrabajaores.getValueAt(TBLtrabajaores.getSelectedRow(), 1) + "",
                                                                
                                                            }
                                            );
                
                
            }

        }


    }//GEN-LAST:event_BTNagregarPrimeraJornadaActionPerformed

    private void BTNagregarSegundaJornadaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNagregarSegundaJornadaActionPerformed
        if (TBLtrabajaores.getSelectedRow() != -1) {

            String valorSeleccionadoDeTabla = TBLtrabajaores.getValueAt(TBLtrabajaores.getSelectedRow(), 0) + "";
            String valorestablaJornada;
            boolean valorNoEncontradoEntabla = true;
            for (int i = 0; i < TBL_segundaJornada.getRowCount(); i++) {

                valorestablaJornada = TBL_segundaJornada.getValueAt(i, 0) + "";
                if (valorSeleccionadoDeTabla.equals(valorestablaJornada)) {
                    valorNoEncontradoEntabla = false;
                }

            }

            if (valorNoEncontradoEntabla) {
                TBLMDLsegundaJornada.addRow(
                                            new String[]
                                                        {
                                                        TBLtrabajaores.getValueAt(TBLtrabajaores.getSelectedRow(), 0) + "",
                                                        TBLtrabajaores.getValueAt(TBLtrabajaores.getSelectedRow(), 1) + ""
                                                        }
                                            );

            }

        }


    }//GEN-LAST:event_BTNagregarSegundaJornadaActionPerformed

  
    
    public boolean guardaEnBDInfoFactura( boolean actualizarInformacion){
        
        
        
        
        
        Instacia.factura_srv.setClaveFactura(TXT_folio.getText());
        Instacia.factura_srv.setDescripcion(TXT_descripcion.getText());
        Instacia.factura_srv.setFecha(DTE_fecha.getText() );
        Instacia.factura_srv.setNombre(TXT_nombre.getText());
        Instacia.factura_srv.setDireccion(TXT_direccion.getText());
        Instacia.factura_srv.setSolicitante(txt_solicitante.getText());
        Instacia.factura_srv.setPo(TXT_po.getText());
        Instacia.factura_srv.setVendedor(TXTvendedor.getText());
        Instacia.factura_srv.setPeriodoInicio(formatoSQL.format(DTE_fechaInicioPeriodo.getDate()));
        Instacia.factura_srv.setPeriodoFin(formatoSQL.format(DTE_fechaFinPeriodo.getDate()));
        Instacia.factura_srv.setSubTotal(Float.parseFloat(TXTsubtotal.getText()));
        Instacia.factura_srv.setImpuestos(Float.parseFloat(TXT_impuestos.getText()));
        Instacia.factura_srv.setExpTram(Float.parseFloat("0.0"));
        Instacia.factura_srv.setTotal(Float.parseFloat(TXTtotal.getText()));
        Instacia.factura_srv.setPrecioHorasRegulares(Float.parseFloat(TXTprecioHorasRegulares.getText()));
        Instacia.factura_srv.setPrecioHorasExtra(Float.parseFloat(TXTprecioHorasTiempoExtra.getText()));
        
        lugar_servicio LS=new lugar_servicio();
        Instacia.factura_srv.setLugar(LS.idLugarPorNombre(CMBlugar.getSelectedItem()+"")   );
        
        catalogo_servicio CS=new catalogo_servicio();
        Instacia.factura_srv.setEstatus(CS.idCatalogoPorValorYTipo(CMBestatus.getSelectedItem()+"","5"));
        
        System.out.println(Instacia.factura_srv.getLugar());
        System.out.println(Instacia.factura_srv.getEstatus());
        
        
        boolean resultado=Instacia.factura_srv.insertaFactura(
                
                                        actualizarInformacion,
                
                                        TBLMDLconceptosAdicionales,
                                        TXT_conceptoTexto.getText(),
                                        TXTprecioHorasRegulares.getText(),
                                        TXTprecioHorasTiempoExtra.getText(),
                                        TXTtotalHorasRegulares.getText(),
                                        
                                        TXTtotalHorasExtra.getText(),
                                        idEmpleadoMatutino,
                                        fechasMatutuno,
                                        TXThoraEntradaMatutino,
                                        TXThoraTrabajadaMatutino,
                
                                        idEmpleadoVespertino,
                                        fechasVespertino,
                                        TXThoraEntradaVespertino,
                                        TXThoraTrabajadaVespertino,
                                       
                                        Instacia.factura_srv.getIdFactura()
                
                
                            );
        
        
        
        
        
        reporte_factura_servicio rfs=new reporte_factura_servicio();
        
        
        String ReporteID[][]=rfs.consultaReporteFactura(Instacia.factura_srv.getIdFactura()+"");
        
        if (ReporteID!=null){
            
            for (int i=0;i<ReporteID.length;i++){
                
                rfs.borraReporteDetalle(ReporteID[i][0]);
                rfs.borraReporte(ReporteID[i][1]);
            }
            
        }
        
        
        String idReporte=rfs.agregaReporte(
                            Instacia.factura_srv.getIdFactura()+"",
                                TXTfechaReport.getText(), 
                                    TXTnumeroParte.getText(), 
                                        TXTtipoDeParte.getText(), 
                                            TXT1.getText(), 
                                                TXT2.getText(), 
                                                    TXTcontactoCliente.getText(), 
                                                        "", 
                                                            TXTresidente.getText(), 
                                                                TXTaprueba.getText(), 
                                                                    TXT_descripcion_problema.getText()
                        );
        
    
            
        for (int i=0;i<sorteados.length;i++){
            rfs.agregaDetalleReporte(idReporte,
                                        fecha[i].getText().trim(), 
                                            sorteados[i].getText(), 
                                                rejected[i].getText(), 
                                                    reworked[i].getText());
        }
     
        
        return resultado;
    }


    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        
        

        if (diferenciaSorteados.getText().equals("0") &&  diferenciaRechazados.getText().equals("0") &&   diferenciaRetrabajados.getText().equals("0")  ){
        
            if (!validacionNulos.existenNulos() && DTE_fecha.getText()!=null &&  !validacionNulosReporte.existenNulos()     ){

                if(guardaEnBDInfoFactura(actualizarInformacion)){

                    try {
                        File F=new File("reportes\\facturacion\\facturacionSoxtec.jasper");
                        JasperReport reporte = (JasperReport) JRLoader.loadObject(F);

                        Map parametros;
                        parametros = new HashMap();

                        parametros.put("PRMidFactura",   Instacia.factura_srv.getIdFactura()+""     );
                        parametros.put("PRMfolio",   Instacia.factura_srv.getClaveFactura());
                        parametros.put("PRMdireccion",   Instacia.factura_srv.getDireccion());
                        parametros.put("PRMfecha",   Instacia.factura_srv.getFecha());
                        parametros.put("PRMnombre",   Instacia.factura_srv.getNombre());
                        parametros.put("PRMsolicitante",   Instacia.factura_srv.getSolicitante());
                        parametros.put("PRMpo",   Instacia.factura_srv.getPo()     );
                        parametros.put("PRMvendedor",   Instacia.factura_srv.getVendedor());

                        calendar = Calendar.getInstance();
                        calendar.setTime(DTE_fechaInicioPeriodo.getDate()); // Configuramos la fecha que se recibe
                        parametros.put("PRMperiodoInicial",   formatoEnglish.format(calendar.getTime())     );
                        calendar.setTime(DTE_fechaFinPeriodo.getDate()); // Configuramos la fecha que se recibe
                        parametros.put("PRMperiodoFinal",    formatoEnglish.format(calendar.getTime())   );

                        parametros.put("PRMexpTram",   "0.00");
                        parametros.put("PRMsubtotal",   TXTsubtotal.getText());
                        
                       
                        parametros.put("PRMletraTotal",    EnglishNumberTranslator.convert(Double.parseDouble(TXTtotal.getText())).toUpperCase()      );
                        parametros.put("PRMtotal",   TXTtotal.getText());
                        parametros.put("PRMtax",   TXT_impuestos.getText());

                        parametros.put("PRMlugar",   CMBlugar.getSelectedItem()+"");
                        parametros.put("PRMestatus",   CMBestatus.getSelectedItem()+"");

                        JasperPrint jasperPrint = JasperFillManager.fillReport(  reporte, parametros, ManejadorDeDatos.BD.getCon());
                        JasperViewer vista = new JasperViewer(jasperPrint, false);
                        vista.setVisible(true);
                        
                        // Reporte  //////////////////////////////////////////////////////////////////////////////////////////////////////////////
                        
                        F=new File("reportes\\facturacion\\ReporteFacturacion.jasper");
                        reporte = (JasperReport) JRLoader.loadObject(F);
                        
                        parametros.put("idFactura",   Instacia.factura_srv.getIdFactura()+""     );
                        
                        
                        jasperPrint = JasperFillManager.fillReport(  reporte, parametros, ManejadorDeDatos.BD.getCon());
                        vista = new JasperViewer(jasperPrint, false);
                        vista.setVisible(true);
                        
                    } catch (JRException ex) {
                        Logger.getLogger(factura_ifz.class.getName()).log(Level.SEVERE, null, ex);
                    }


                }
                else{
                    javax.swing.JOptionPane.showMessageDialog(null, "Error al guardar la Factura");
                }


            }
        }
        else{
             javax.swing.JOptionPane.showMessageDialog(null, "Los valores del reporte deben coincidir");
        }
        


    }//GEN-LAST:event_jButton1ActionPerformed

    private void TXTtotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TXTtotalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TXTtotalActionPerformed

    private void BTNprimeraJornadaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNprimeraJornadaActionPerformed
        if (TBL_primeraJornada.getSelectedRow() != -1) {
            TBLMDLprimeraJornada.removeRow(TBL_primeraJornada.getSelectedRow());
        }
    }//GEN-LAST:event_BTNprimeraJornadaActionPerformed

    private void BTNsegundaJornadaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNsegundaJornadaActionPerformed
        if (TBL_segundaJornada.getSelectedRow() != -1) {
            TBLMDLsegundaJornada.removeRow(TBL_segundaJornada.getSelectedRow());
        }
    }//GEN-LAST:event_BTNsegundaJornadaActionPerformed

    private void BTNadicionalAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNadicionalAgregarActionPerformed

        if (  isNumeric(TXTcantidad.getText()) && isNumeric(TXTprecioUnitario.getText())){
            
            if ( !TXTcantidad.getText().equals("") &&
                    !TXTdescripcion.getText().equals("") && 
                        !TXTprecioUnitario.getText().equals("")
                ){ //Valores
            
                String nuevoConcepto[]=new String[]{ TXTcantidad.getText(),TXTdescripcion.getText(),TXTprecioUnitario.getText() };
            
                TBLMDLconceptosAdicionales.addRow(nuevoConcepto);
                
                sumaAdicionales();
                sumaTotales();
              }
        }
        else{
            JOptionPane.showMessageDialog(null, "Campo Cantidad y Precio Unitario tienen que ser numericos.");
        }
    }//GEN-LAST:event_BTNadicionalAgregarActionPerformed

    
    public void sumaAdicionales(){
        
        float sumaAdicionales=0;
        for (int i=0;i<TBLMDLconceptosAdicionales.getRowCount();i++){
            
            float cantidad=Float.parseFloat(TBLMDLconceptosAdicionales.getValueAt(i, 0)+"");
            float precioUnitario=Float.parseFloat(TBLMDLconceptosAdicionales.getValueAt(i, 2)+"");
            
            sumaAdicionales=sumaAdicionales+(cantidad*precioUnitario);
        
        
        }
        
        TXT_totalAdicionales.setText(sumaAdicionales+"");
        
    }
            
    
    private void BTNadicionalBorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNadicionalBorrarActionPerformed
        if (TBLconceptosAdicionales.getSelectedRow()!=-1){
            TBLMDLconceptosAdicionales.removeRow(TBLconceptosAdicionales.getSelectedRow());
            sumaAdicionales();
            sumaTotales();
        }
        
    }//GEN-LAST:event_BTNadicionalBorrarActionPerformed

    private void TXT_buscarFolioKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TXT_buscarFolioKeyReleased
        TBLfolios.setModel(Instacia.factura_srv.LISTAfacturas(TXT_buscarFolio.getText()));
        listaIdFacturas = Instacia.factura_srv.LISTAIdsFacturas(TXT_buscarFolio.getText());   
    }//GEN-LAST:event_TXT_buscarFolioKeyReleased

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed

        
        if (TBLfolios.getSelectedRow()!=-1){
        
        
            actualizarInformacion=true;



            String informacionDeFacturas[][]=Instacia.factura_srv.informacionFactura(   listaIdFacturas[TBLfolios.getSelectedRow()][0]   );

            for (int i=0;i<informacionDeFacturas.length;i++){

                Instacia.factura_srv.setIdFactura(Integer.parseInt(informacionDeFacturas[i][0]));
                TXT_folio.setText(informacionDeFacturas[i][1]);
                TXT_descripcion.setText(informacionDeFacturas[i][2]);
                
                DTE_fecha.setText(informacionDeFacturas[i][3]);
                
                TXT_nombre.setText(informacionDeFacturas[i][4]);
                TXT_direccion.setText(informacionDeFacturas[i][5]);
                txt_solicitante.setText(informacionDeFacturas[i][6]);
                TXT_po.setText(informacionDeFacturas[i][7]);
                TXTvendedor.setText(informacionDeFacturas[i][8]);
                try {
                    DTE_fechaInicioPeriodo.setDate(formatoSQL.parse(informacionDeFacturas[i][9]));
                } catch (ParseException ex) {
                    Logger.getLogger(factura_ifz.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    DTE_fechaFinPeriodo.setDate(formatoSQL.parse(informacionDeFacturas[i][10]));
                } catch (ParseException ex) {
                    Logger.getLogger(factura_ifz.class.getName()).log(Level.SEVERE, null, ex);
                }
                TXTsubtotal.setText(informacionDeFacturas[i][11]);
                TXT_impuestos.setText(informacionDeFacturas[i][12]);
                TXTtotal.setText(informacionDeFacturas[i][14]);
                TXTprecioHorasRegulares.setText(informacionDeFacturas[i][15]);
                TXTprecioHorasTiempoExtra.setText(informacionDeFacturas[i][16]);
                CMBlugar.setSelectedItem(informacionDeFacturas[i][17]);
                CMBestatus.setSelectedItem(informacionDeFacturas[i][18]);
                TXT_conceptoTexto.setText(informacionDeFacturas[i][19]);

            }

            TBLMDLprimeraJornada=Instacia.factura_srv.informacionTrabajadores(listaIdFacturas[TBLfolios.getSelectedRow()][0] ,true,false );
            TBLMDLsegundaJornada=Instacia.factura_srv.informacionTrabajadores(listaIdFacturas[TBLfolios.getSelectedRow()][0] ,false,true );
            TBL_primeraJornada.setModel(TBLMDLprimeraJornada);
            TBL_segundaJornada.setModel(TBLMDLsegundaJornada);

            String informacionDeHorasTrabajadasMatutino[][]=Instacia.factura_srv.informacionHorasTrabajadas(   listaIdFacturas[TBLfolios.getSelectedRow()][0] ,true,false  );


            String informacionDeHorasTrabajadasVespertino[][]=Instacia.factura_srv.informacionHorasTrabajadas(   listaIdFacturas[TBLfolios.getSelectedRow()][0] ,false,true  );




            PNL_horas = new javax.swing.JPanel();
            PNL_horas.setLayout(new javax.swing.BoxLayout(PNL_horas, javax.swing.BoxLayout.Y_AXIS));
            scrollPane.setViewportView(PNL_horas);

            Date fechaInicial = DTE_fechaInicioPeriodo.getDate();
            Date fechaFinal = DTE_fechaFinPeriodo.getDate();
            calendar = Calendar.getInstance();

            semanasTrabajadas = diferenciaSemanas(fechaInicial, fechaFinal) + 1;

            int diasTranscurridos = (int) ((fechaFinal.getTime() - fechaInicial.getTime()) / 86400000);

            JPanel panelEmpleado = new JPanel();   //Fila Nueva
            panelEmpleado.setLayout(new BoxLayout(panelEmpleado, BoxLayout.X_AXIS));

            JLabel Titulo = new JLabel("1st Shift");
            Titulo.setFont(new java.awt.Font("Tahoma", 1, 20));
            panelEmpleado.add(Titulo);
            PNL_horas.add(panelEmpleado); //Se agrega la Fila

            llenaTrabajadoresConHorasMatutinoBD(//TABLA VESPERTINO
                    diasTranscurridos,
                    fechaInicial,
                    MATUTINO,
                    informacionDeHorasTrabajadasMatutino
            );

            panelEmpleado = new JPanel();   //Fila Nueva
            panelEmpleado.setLayout(new BoxLayout(panelEmpleado, BoxLayout.X_AXIS));

            Titulo = new JLabel("2nd Shift");
            Titulo.setFont(new java.awt.Font("Tahoma", 1, 20));
            panelEmpleado.add(Titulo);
            PNL_horas.add(panelEmpleado); //Se agrega la Fila

            llenaTrabajadoresConHorasVespertinoBD(//TABLA VESPERTINO
                    diasTranscurridos,
                    fechaInicial,
                    VESPERTINO,
                    informacionDeHorasTrabajadasVespertino
            );


            detalle_factura_servicio detalleFactura=new detalle_factura_servicio();
            detalleFactura.informacionConceptoPrincipalDescripcion(listaIdFacturas[TBLfolios.getSelectedRow()][0]);

            TBLMDLconceptosAdicionales =  detalleFactura.informacionConceptoDescripcion(listaIdFacturas[TBLfolios.getSelectedRow()][0]);

            TBLconceptosAdicionales.setModel(TBLMDLconceptosAdicionales);

            reporte_factura_servicio rfs=new reporte_factura_servicio();
            String reporte[][]=rfs.consultaReporteFactura( listaIdFacturas[TBLfolios.getSelectedRow()][0] );
            
            
            for (int i=0;i<reporte.length;i++){
               
                TXTfechaReport.setText(  reporte[i][2]  );
                TXTnumeroParte.setText(  reporte[i][3]  );
                TXTcontactoCliente.setText( reporte[i][7] );
                TXTtipoDeParte.setText(   reporte[i][4]  );
                TXT1.setText(reporte[i][5]);
                TXT2.setText(reporte[i][6]);
                TXTresidente.setText(reporte[i][9]);
                TXTaprueba.setText(reporte[i][10]);
                
                TXT_descripcion_problema.setText(reporte[i][11]);
               
                
                PNL_reportes_fechas = new javax.swing.JPanel();
                PNL_reportes_fechas.setLayout(new javax.swing.BoxLayout(PNL_reportes_fechas, javax.swing.BoxLayout.Y_AXIS));
                scrollPaneReporte.setViewportView(PNL_reportes_fechas);
        
                Date fechaIni = DTE_fechaInicioPeriodo.getDate();
                Date fechaFin= DTE_fechaFinPeriodo.getDate();
                calendar = Calendar.getInstance();

                int diasTranscurridosReporte;
                diasTranscurridosReporte = numeroDiasEntreDosFechas(fechaIni, fechaFin);
                JPanel PNL_reportes_fila[] = new JPanel[diasTranscurridosReporte+1];
                fecha = new JLabel[diasTranscurridosReporte+1];

                sorteados = new JTextField[diasTranscurridosReporte+1];
                rejected = new JTextField[diasTranscurridosReporte+1];
                reworked = new JTextField[diasTranscurridosReporte+1];

               
                
                String detalleReporte[][]= rfs.consultaReporteDetalleFactura(reporte[i][0]);
                
                
                int sortedINT=0;
                int rejectedINT=0;
                int reworkedINT=0;
                
                for (int j=0;j<detalleReporte.length;j++){
                
                    PNL_reportes_fila[j] = new JPanel();
                    PNL_reportes_fila[j].setLayout(new BoxLayout(PNL_reportes_fila[j], BoxLayout.X_AXIS));

                    
                    fecha[j] = new JLabel(detalleReporte[j][2]);

                    sorteados[j] = new JTextField(detalleReporte[j][3]);  
                    EventoTeclasReporte EventoTecla = new EventoTeclasReporte( EventoTeclasReporte.SORTEADOS,i );
                    sorteados[j].addKeyListener(EventoTecla);


                    rejected[j] = new JTextField(detalleReporte[j][4]);
                    EventoTeclasReporte EventoTecla2 = new EventoTeclasReporte( EventoTeclasReporte.RECHAZADOS,i );
                    rejected[j].addKeyListener(EventoTecla2);

                    reworked[j] = new JTextField(detalleReporte[j][5]);
                    EventoTeclasReporte EventoTecla3 = new EventoTeclasReporte( EventoTeclasReporte.RETRABAJADOS,i );
                    reworked[j].addKeyListener(EventoTecla3);


                    PNL_reportes_fila[j].add(fecha[j]); //Se agrega la Fila
                    PNL_reportes_fila[j].add(sorteados[j]); //Se agrega la Fila
                    PNL_reportes_fila[j].add(rejected[j]); //Se agrega la Fila
                    PNL_reportes_fila[j].add(reworked[j]); //Se agrega la Fila

                    sortedINT=sortedINT+Integer.parseInt(detalleReporte[j][3]);
                    rejectedINT=rejectedINT+Integer.parseInt(detalleReporte[j][4]);
                    reworkedINT=reworkedINT+Integer.parseInt(detalleReporte[j][5]);
                    
                    
                    
                    PNL_reportes_fechas.add(PNL_reportes_fila[j]);


                }
                
                TXTsorteados.setText(sortedINT+"");
                TXTrechazados.setText(rejectedINT+"");
                TXTretrabajados.setText(reworkedINT+"");
                
                
                
                              
            }

            diferenciaSorteados.setText("0");
            diferenciaRechazados.setText("0");
            diferenciaRetrabajados.setText("0");

            sumaTotales();


        }

        
        
        
         
        
        //listaIdFacturas[TBLfolios.getSelectedRow()]
    }//GEN-LAST:event_jButton3ActionPerformed

    private void TXTprecioHorasTiempoExtraKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TXTprecioHorasTiempoExtraKeyReleased
        if (isNumeric(TXTprecioHorasTiempoExtra.getText())){
            sumaTotales();
        }
    }//GEN-LAST:event_TXTprecioHorasTiempoExtraKeyReleased

    private void TXTprecioHorasRegularesKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TXTprecioHorasRegularesKeyReleased
        if (isNumeric(TXTprecioHorasRegulares.getText())){
            sumaTotales();
        }
    }//GEN-LAST:event_TXTprecioHorasRegularesKeyReleased

    private void TXT_folioKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TXT_folioKeyReleased
 
        LBLdisponible.setVisible(false);

        if (!TXT_folio.getText().equals("")){
            
            TXT_folio.setText( ( TXT_folio.getText() ).replace(" ","")  );
            
            if (Instacia.factura_srv.claveFacturaRepetida(TXT_folio.getText())){

                LBL_noDisponible.setVisible(true);
                LBLdisponible.setVisible(false);
            }else{
                LBLdisponible.setVisible(true);
                LBL_noDisponible.setVisible(false);
            }

        }
    }//GEN-LAST:event_TXT_folioKeyReleased

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        this.dispose();
        menu_ifz F=new menu_ifz();
        F.setVisible(true);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        this.dispose();
        factura_ifz V=new factura_ifz();
        V.setVisible(true);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void DTE_fechaFinPeriodoMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DTE_fechaFinPeriodoMouseReleased
        
       
        
        
    }//GEN-LAST:event_DTE_fechaFinPeriodoMouseReleased

    private void DTE_fechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DTE_fechaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DTE_fechaActionPerformed

    private void DTE_fechaFinPeriodoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DTE_fechaFinPeriodoMouseClicked
       
        
    }//GEN-LAST:event_DTE_fechaFinPeriodoMouseClicked

    private void TXTsorteadosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TXTsorteadosKeyReleased
        llenaDescripcion();
    }//GEN-LAST:event_TXTsorteadosKeyReleased

    private void TXT_descripcion_problemaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TXT_descripcion_problemaKeyReleased
         llenaDescripcion();
    }//GEN-LAST:event_TXT_descripcion_problemaKeyReleased

    private void TXTrechazadosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TXTrechazadosKeyReleased
        llenaDescripcion();
    }//GEN-LAST:event_TXTrechazadosKeyReleased

    private void TXTretrabajadosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TXTretrabajadosKeyReleased
        llenaDescripcion();
    }//GEN-LAST:event_TXTretrabajadosKeyReleased

    private void CMBlugarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CMBlugarActionPerformed
        llenaDescripcion();
    }//GEN-LAST:event_CMBlugarActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
          
        PNL_reportes_fechas = new javax.swing.JPanel();
        PNL_reportes_fechas.setLayout(new javax.swing.BoxLayout(PNL_reportes_fechas, javax.swing.BoxLayout.Y_AXIS));
        scrollPaneReporte.setViewportView(PNL_reportes_fechas);
        
        if (!validacionNulosReporte.existenNulos()){
            
            TXTsorteados2.setText(TXTsorteados.getText());
            TXTrechazados2.setText(TXTrechazados.getText());
            TXTretrabajados2.setText(TXTretrabajados.getText());
     
            Date fechaInicial = DTE_fechaInicioPeriodo.getDate();
            Date fechaFinal = DTE_fechaFinPeriodo.getDate();
            calendar = Calendar.getInstance();

            int diasTranscurridos = numeroDiasEntreDosFechas(fechaInicial, fechaFinal);
       
            JPanel PNL_reportes_fila[] = new JPanel[diasTranscurridos+1];
            
            fecha = new JLabel[diasTranscurridos+1];
            
            sorteados = new JTextField[diasTranscurridos+1];
            rejected = new JTextField[diasTranscurridos+1];
            reworked = new JTextField[diasTranscurridos+1];
            
            Date Fecha=fechaInicial;
            
            int promedioSorteadoINT=Integer.parseInt(TXTsorteados.getText())/(diasTranscurridos+1);
            int promedioRechazadoINT=Integer.parseInt(TXTrechazados.getText())/(diasTranscurridos+1);
            int promedioRetrabajadoINT=Integer.parseInt(TXTretrabajados.getText())/(diasTranscurridos+1);
            
            promedioSorteado.setText(promedioSorteadoINT+"");
            promedioRechazado.setText(promedioRechazadoINT+"");
            promedioRetrabajado.setText(promedioRetrabajadoINT+"");
            
            promedioSorteadoMIN.setText(((promedioSorteadoINT*0.90)<0?0:(int)(promedioSorteadoINT*0.90))+"");
            promedioSorteadoMAX.setText(((promedioSorteadoINT*1.10)<0?0:(int)(promedioSorteadoINT*1.10))+"");
                    
            promedioRechazadoMIN.setText(((promedioRechazadoINT*0.90)<0?0:(int)(promedioRechazadoINT*0.90))+"");
            promedioRechazadoMAX.setText(((promedioRechazadoINT*1.10)<0?0:(int)(promedioRechazadoINT*1.10))+"");
    
            promedioRetrabajadoMIN.setText(((promedioRetrabajadoINT*0.90)<0?0:(int)(promedioRetrabajadoINT*0.90))+"");
            promedioRetrabajadoMAX.setText(((promedioRetrabajadoINT*1.10)<0?0:(int)(promedioRetrabajadoINT*1.10))+"");
            
            for (int i=0;i<diasTranscurridos+1;i++){
                
                PNL_reportes_fila[i] = new JPanel();
                PNL_reportes_fila[i].setLayout(new BoxLayout(PNL_reportes_fila[i], BoxLayout.X_AXIS));
                
                String fechaString=utilidadesFecha.sumaFecha.date_aaaa_mm_dd(Fecha);
                fecha[i] = new JLabel("    "+fechaString+"    ");
                
                int sorteado = (int) Math.floor(Math.random()*   (Integer.parseInt(promedioSorteadoMAX.getText())-Integer.parseInt(promedioSorteadoMIN.getText())+1)   +   Integer.parseInt(promedioSorteadoMIN.getText())   );
                int rechazado = (int) Math.floor(Math.random()*   (Integer.parseInt(promedioRechazadoMAX.getText())-Integer.parseInt(promedioRechazadoMIN.getText())+1)   +   Integer.parseInt(promedioRechazadoMIN.getText())   );
                int retrabajado = (int) Math.floor(Math.random()*   (Integer.parseInt(promedioRetrabajadoMAX.getText())-Integer.parseInt(promedioRetrabajadoMIN.getText())+1)   +   Integer.parseInt(promedioRetrabajadoMIN.getText())   );
    
                sorteados[i] = new JTextField(sorteado+"");  
                EventoTeclasReporte EventoTecla = new EventoTeclasReporte( EventoTeclasReporte.SORTEADOS,i );
                sorteados[i].addKeyListener(EventoTecla);
                
                
                rejected[i] = new JTextField(rechazado+"");
                EventoTeclasReporte EventoTecla2 = new EventoTeclasReporte( EventoTeclasReporte.RECHAZADOS,i );
                rejected[i].addKeyListener(EventoTecla2);
                
                reworked[i] = new JTextField(retrabajado+"");
                EventoTeclasReporte EventoTecla3 = new EventoTeclasReporte( EventoTeclasReporte.RETRABAJADOS,i );
                reworked[i].addKeyListener(EventoTecla3);
                
                
                PNL_reportes_fila[i].add(fecha[i]); //Se agrega la Fila
                PNL_reportes_fila[i].add(sorteados[i]); //Se agrega la Fila
                PNL_reportes_fila[i].add(rejected[i]); //Se agrega la Fila
                PNL_reportes_fila[i].add(reworked[i]); //Se agrega la Fila
              
                PNL_reportes_fechas.add(PNL_reportes_fila[i]);
                
                Fecha=utilidadesFecha.sumaFecha.sumarDiasAFecha(Fecha,1);
                
            }
            
            PNL_reportes_fechas.updateUI();
            sumaReporte();
        }
        else{
            JOptionPane.showMessageDialog(null, "Llene los datos: Sorted, Rejected, Reworked");
        }
        
    }//GEN-LAST:event_jButton6ActionPerformed

    private void TXT_poKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TXT_poKeyReleased
        LBLorderAvalible.setVisible(false);

        if (!TXT_po.getText().equals("")){
            if (Instacia.factura_srv.claveOrdenRepetida(TXT_po.getText())){

                LBLorderNotAvalible.setVisible(true);
                LBLorderAvalible.setVisible(false);
            }else{
                LBLorderAvalible.setVisible(true);
                LBLorderNotAvalible.setVisible(false);
            }

        }
    }//GEN-LAST:event_TXT_poKeyReleased

    
    public void sumaReporte(){
        
        int sorteadosTotal=0;
        int rechazadosTotal=0;
        int retrabajadosTotal=0;
        
        
        for (int i=0;i<sorteados.length;i++){
            
            sorteadosTotal = sorteadosTotal +  Integer.parseInt(sorteados[i].getText());
            rechazadosTotal = rechazadosTotal +  Integer.parseInt(rejected[i].getText());
            retrabajadosTotal = retrabajadosTotal + Integer.parseInt(reworked[i].getText());
            
        }
        
        
        totalSorteados.setText(sorteadosTotal+"");
        totalRechazados.setText(rechazadosTotal+"");
        totalRetrabajados.setText(retrabajadosTotal+"");
        
        
        diferenciaSorteados.setText(  (Integer.parseInt(  TXTsorteados.getText()  )  -  sorteadosTotal)+""  );
        diferenciaRechazados.setText(  (Integer.parseInt(  TXTrechazados.getText()  )  -  rechazadosTotal)+""  );
        diferenciaRetrabajados.setText(  (Integer.parseInt(  TXTretrabajados.getText()  )  -  retrabajadosTotal)+""  );
        
        
        
        
        
        
    }
    
    
    public void llenaDescripcion(){
        
        
        
        
        TXT_conceptoTexto.setText( 
                                    "\n"+
                                    "\n"+
                                    "\n"+
                                    TXT_descripcion_problema.getText()+"\n"+
                                    "\n"+
                                    "Locations of sorting / reworking:"+"\n"+
                                    CMBlugar.getSelectedItem()+"\n"+
                                    "\n"+
                                    "SEVERAL PART NUMBERS"+"\n"+
                                    "Harnesses sorted: "+TXTsorteados.getText()+"\n"+
                                    "Harnesses rejected: "+TXTrechazados.getText()+"\n"+                                                    
                                    "Harnesses reworked: "+TXTretrabajados.getText()
                                );
    }
    
    
    
    public String rellenaEspaciosEnBlanco(String nombre){
        
        String R=nombre;
        
        
        
        for (int i=0;i<(45-nombre.length());i++){
            R=R+"*";
        }
        
        
        
        return R;
        
    }
    
    
    public void llenaTrabajadoresConHorasMatutinoBD(
            int diasTranscurridos,
            Date fechaInicial,
            int turno,
            String informacionDeHorasTrabajadasMatutino[][]
    ) {

        horasEnSemanaTrabajadaMatutino = new float[semanasTrabajadas][TBLMDLprimeraJornada.getRowCount()];

        LBL_nombreEmpleadoMatutino = new JLabel[TBLMDLprimeraJornada.getRowCount()];
        
        idEmpleadoMatutino = new int[TBLMDLprimeraJornada.getRowCount()];
        BTN_eliminarMatutino = new JButton[TBLMDLprimeraJornada.getRowCount()][diasTranscurridos + 1];

        semanaTrabajadaMatutino = new int[diasTranscurridos + 1];
        LBL_diaMatutino = new JLabel[TBLMDLprimeraJornada.getRowCount()][diasTranscurridos + 1];
        fechasMatutuno = new String[TBLMDLprimeraJornada.getRowCount()][diasTranscurridos + 1];
        TXThoraEntradaMatutino = new JFormattedTextField[TBLMDLprimeraJornada.getRowCount()][diasTranscurridos + 1];
        TXThoraSalidaMatutino = new JFormattedTextField[TBLMDLprimeraJornada.getRowCount()][diasTranscurridos + 1];
        TXThoraTrabajadaMatutino = new JFormattedTextField[TBLMDLprimeraJornada.getRowCount()][diasTranscurridos + 1];


        
        for (int i = 0; i < TBLMDLprimeraJornada.getRowCount(); i++) {  // Lista Primera jornada

            JPanel panelEmpleado = new JPanel();   //Fila Nueva
            panelEmpleado.setLayout(new BoxLayout(panelEmpleado, BoxLayout.X_AXIS));

           
            LBL_nombreEmpleadoMatutino[i] = new JLabel("    " + rellenaEspaciosEnBlanco( TBLMDLprimeraJornada.getValueAt(i, 0)+"" ) + "");  //
            LBL_nombreEmpleadoMatutino[i].setFont(new java.awt.Font("Lucida Console", 1, 24));
            LBL_nombreEmpleadoMatutino[i].setForeground(Color.RED);
            panelEmpleado.add(LBL_nombreEmpleadoMatutino[i]);
            
            idEmpleadoMatutino[i]=Integer.parseInt(TBLMDLprimeraJornada.getValueAt(i, 1) + "");

            for (int j = 0; j <= diasTranscurridos; j++) {

                calendar.setTime(fechaInicial); // Configuramos la fecha que se recibe
                calendar.add(Calendar.DATE, j);  // numero de horas a añadir, o restar en caso de horas<0
                String DiaMes = formatoDiaMes.format(calendar.getTime());
                String DiaSemana = formatoDiaSemana.format(calendar.getTime());
                String DiaCompleto = formatoSQL.format(calendar.getTime());
                semanaTrabajadaMatutino[j] = diferenciaSemanas(fechaInicial, calendar.getTime()) + 1;  //Se saca la semana y se agrega  los dias

                String nombreDia = "";//LMMiJVSD
                if (DiaSemana.equals("1")) {
                    nombreDia = "L";
                }
                if (DiaSemana.equals("2")) {
                    nombreDia = "M";
                }
                if (DiaSemana.equals("3")) {
                    nombreDia = "Mi";
                }
                if (DiaSemana.equals("4")) {
                    nombreDia = "J";
                }
                if (DiaSemana.equals("5")) {
                    nombreDia = "V";
                }
                if (DiaSemana.equals("6")) {
                    nombreDia = "S";
                }
                if (DiaSemana.equals("7")) {
                    nombreDia = "D";
                }

                
                
                String fecha="";
                String horaEntrada="";
                String horasTrabajadas="";
                String horaSalida="";
                
                for (int k=0;k<informacionDeHorasTrabajadasMatutino.length;k++){
                
                    if (  DiaCompleto.equals(informacionDeHorasTrabajadasMatutino[k][1]) && idEmpleadoMatutino[i]==Integer.parseInt(informacionDeHorasTrabajadasMatutino[k][2])   ){

                        
                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                        SimpleDateFormat u = new SimpleDateFormat("u");
                        SimpleDateFormat d = new SimpleDateFormat("d");
                        String dateInString = informacionDeHorasTrabajadasMatutino[k][1];
                        

                        try {

                            Date date = formatter.parse(dateInString);
                            String diaSemana="";
                            if (u.format(date).equals("1")) {
                                diaSemana = "L";
                            }
                            if (u.format(date).equals("2")) {
                                diaSemana = "M";
                            }
                            if (u.format(date).equals("3")) {
                                diaSemana = "Mi";
                            }
                            if (u.format(date).equals("4")) {
                                diaSemana = "J";
                            }
                            if (u.format(date).equals("5")) {
                                diaSemana = "V";
                            }
                            if (u.format(date).equals("6")) {
                                diaSemana = "S";
                            }
                            if (u.format(date).equals("7")) {
                                diaSemana = "D";
                            }
                            fecha="       " +diaSemana+"-"+d.format(date);
                            
                            formatter = new SimpleDateFormat("HH:mm");
                            date = formatter.parse(informacionDeHorasTrabajadasMatutino[k][10]);
                            
                            Calendar calendar = Calendar.getInstance();
                            calendar.setTime(date); // Configuramos la fecha que se recibe
                            calendar.add(  Calendar.MINUTE, (int)(Float.parseFloat( informacionDeHorasTrabajadasMatutino[k][3])*60)    ) ;  // numero de días a añadir, o restar en caso de días<0
                                                
                            DateFormat df = new SimpleDateFormat("HH:mm");
                            horaSalida = df.format(calendar.getTime());
                            

                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        
                        horaEntrada=informacionDeHorasTrabajadasMatutino[k][10];
                        DecimalFormat formateador = new DecimalFormat("00.00");
                        horasTrabajadas=formateador.format(Float.parseFloat( informacionDeHorasTrabajadasMatutino[k][3]));
                        
                        
                        break; //Si encontro coincidencia se sale
                
                    }
                    else{
                        fecha="       " + nombreDia + "-" + DiaMes;
                        horaEntrada="00:00";
                        horasTrabajadas="0.0";
                        horaSalida="00:00";
                    }

                }

                
                        LBL_diaMatutino[i][j] = new JLabel(fecha);
                        LBL_diaMatutino[i][j].setFont(new java.awt.Font("Tahoma", Font.BOLD, 24));
                        panelEmpleado.add(LBL_diaMatutino[i][j]);

                        fechasMatutuno[i][j]=DiaCompleto;
                
                
                        // 6 Y 8
                        // 4 Y 2.30  6
                        try {
                            MaskFormatter mascaraHoraForma = new MaskFormatter("##:##");



                            TXThoraEntradaMatutino[i][j] = new JFormattedTextField(mascaraHoraForma);
                            TXThoraEntradaMatutino[i][j].setText(horaEntrada);
                            TXThoraEntradaMatutino[i][j].setFont(new java.awt.Font("Tahoma", Font.BOLD, 24));
                            EventoTeclas Eventotecla = new EventoTeclas(i, j, EventoTeclas.Entrada, EventoTeclas.Matutino);
                            TXThoraEntradaMatutino[i][j].addKeyListener(Eventotecla);
                            panelEmpleado.add(TXThoraEntradaMatutino[i][j]);

                            TXThoraSalidaMatutino[i][j] = new JFormattedTextField(mascaraHoraForma);
                            TXThoraSalidaMatutino[i][j].setText(horaSalida);
                            TXThoraSalidaMatutino[i][j].setFont(new java.awt.Font("Tahoma", Font.BOLD, 24));
                            Eventotecla = new EventoTeclas(i, j, EventoTeclas.Salida, EventoTeclas.Matutino);
                            TXThoraSalidaMatutino[i][j].addKeyListener(Eventotecla);
                            panelEmpleado.add(TXThoraSalidaMatutino[i][j]);


                            TXThoraTrabajadaMatutino[i][j] = new JFormattedTextField();  //8  ,  9   , 12
                            TXThoraTrabajadaMatutino[i][j].setFont(new java.awt.Font("Tahoma", Font.BOLD, 24));
                            TXThoraTrabajadaMatutino[i][j].setText(horasTrabajadas);
                            TXThoraTrabajadaMatutino[i][j].setEnabled(false);
                            panelEmpleado.add(TXThoraTrabajadaMatutino[i][j]);

                            BTN_eliminarMatutino[i][j] = new JButton("X");  //Boton de Cerrar
                            EventoBorrar eventoBorrar=new EventoBorrar(i,j,EventoBorrar.Matutino);
                            BTN_eliminarMatutino[i][j].addActionListener(eventoBorrar);
                            panelEmpleado.add(BTN_eliminarMatutino[i][j]);




                        } catch (ParseException ex) {
                            Logger.getLogger(factura_ifz.class.getName()).log(Level.SEVERE, null, ex);
                        }

                   
                      
                      
                      

            }
            PNL_horas.add(panelEmpleado); //Se agrega la Fila
        }

    }

    
    
     public void llenaTrabajadoresConHorasVespertinoBD(
            int diasTranscurridos,
            Date fechaInicial,
            int turno,
            String informacionDeHorasTrabajadasVespertino[][]
    ) {

        horasEnSemanaTrabajadaVespertino = new float[semanasTrabajadas][TBLMDLsegundaJornada.getRowCount()];

        
        LBL_nombreEmpleadoVespertino = new JLabel[TBLMDLsegundaJornada.getRowCount()];
        
        idEmpleadoVespertino = new int[TBLMDLsegundaJornada.getRowCount()];
        BTN_eliminarVespertino = new JButton[TBLMDLsegundaJornada.getRowCount()][diasTranscurridos + 1];

        semanaTrabajadaVespertino = new int[diasTranscurridos + 1];
        LBL_diaVespertino = new JLabel[TBLMDLsegundaJornada.getRowCount()][diasTranscurridos + 1];
        fechasVespertino = new String[TBLMDLsegundaJornada.getRowCount()][diasTranscurridos + 1];
        TXThoraEntradaVespertino = new JFormattedTextField[TBLMDLsegundaJornada.getRowCount()][diasTranscurridos + 1];
        TXThoraSalidaVespertino = new JFormattedTextField[TBLMDLsegundaJornada.getRowCount()][diasTranscurridos + 1];
        TXThoraTrabajadaVespertino = new JFormattedTextField[TBLMDLsegundaJornada.getRowCount()][diasTranscurridos + 1];

        for (int i = 0; i < TBLMDLsegundaJornada.getRowCount(); i++) {  // Lista Primera jornada

            JPanel panelEmpleado = new JPanel();   //Fila Nueva
            panelEmpleado.setLayout(new BoxLayout(panelEmpleado, BoxLayout.X_AXIS));

            

            LBL_nombreEmpleadoVespertino[i] = new JLabel("    " + rellenaEspaciosEnBlanco(TBLMDLsegundaJornada.getValueAt(i, 0)+"") + "");  //
            LBL_nombreEmpleadoVespertino[i].setFont(new java.awt.Font("Lucida Console", 1, 24));
            LBL_nombreEmpleadoVespertino[i].setForeground(Color.BLUE);
            panelEmpleado.add(LBL_nombreEmpleadoVespertino[i]);
            
            idEmpleadoVespertino[i]=Integer.parseInt(TBLMDLsegundaJornada.getValueAt(i, 1) + "");
            

            for (int j = 0; j <= diasTranscurridos; j++) {

                calendar.setTime(fechaInicial); // Configuramos la fecha que se recibe
                calendar.add(Calendar.DATE, j);  // numero de horas a añadir, o restar en caso de horas<0
                String DiaMes = formatoDiaMes.format(calendar.getTime());
                String DiaSemana = formatoDiaSemana.format(calendar.getTime());
                String DiaCompleto = formatoSQL.format(calendar.getTime());
                semanaTrabajadaVespertino[j] = diferenciaSemanas(fechaInicial, calendar.getTime()) + 1;  //Se saca la semana y se agrega  los dias

                String nombreDia = "";//LMMiJVSD
                if (DiaSemana.equals("1")) {
                    nombreDia = "L";
                }
                if (DiaSemana.equals("2")) {
                    nombreDia = "M";
                }
                if (DiaSemana.equals("3")) {
                    nombreDia = "Mi";
                }
                if (DiaSemana.equals("4")) {
                    nombreDia = "J";
                }
                if (DiaSemana.equals("5")) {
                    nombreDia = "V";
                }
                if (DiaSemana.equals("6")) {
                    nombreDia = "S";
                }
                if (DiaSemana.equals("7")) {
                    nombreDia = "D";
                }
                
                
                String fecha="";
                String horaEntrada="";
                String horasTrabajadas="";
                String horaSalida="";
                
                
                  for (int k=0;k<informacionDeHorasTrabajadasVespertino.length;k++){
                
                    if (  DiaCompleto.equals(informacionDeHorasTrabajadasVespertino[k][1]) && idEmpleadoVespertino[i]==Integer.parseInt(informacionDeHorasTrabajadasVespertino[k][2])   ){

                        
                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                        SimpleDateFormat u = new SimpleDateFormat("u");
                        SimpleDateFormat d = new SimpleDateFormat("d");
                        String dateInString = informacionDeHorasTrabajadasVespertino[k][1];
                        

                        try {

                            Date date = formatter.parse(dateInString);
                            String diaSemana="";
                            if (u.format(date).equals("1")) {
                                diaSemana = "L";
                            }
                            if (u.format(date).equals("2")) {
                                diaSemana = "M";
                            }
                            if (u.format(date).equals("3")) {
                                diaSemana = "Mi";
                            }
                            if (u.format(date).equals("4")) {
                                diaSemana = "J";
                            }
                            if (u.format(date).equals("5")) {
                                diaSemana = "V";
                            }
                            if (u.format(date).equals("6")) {
                                diaSemana = "S";
                            }
                            if (u.format(date).equals("7")) {
                                diaSemana = "D";
                            }
                            fecha="       " +diaSemana+"-"+d.format(date);
                            
                            formatter = new SimpleDateFormat("HH:mm");
                            date = formatter.parse(informacionDeHorasTrabajadasVespertino[k][10]);
                            
                            Calendar calendar = Calendar.getInstance();
                            calendar.setTime(date); // Configuramos la fecha que se recibe
                            calendar.add(  Calendar.MINUTE, (int)(Float.parseFloat( informacionDeHorasTrabajadasVespertino[k][3])*60)    ) ;  // numero de días a añadir, o restar en caso de días<0
                                                
                            DateFormat df = new SimpleDateFormat("HH:mm");
                            horaSalida = df.format(calendar.getTime());
                            

                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        
                        horaEntrada=informacionDeHorasTrabajadasVespertino[k][10];
                        DecimalFormat formateador = new DecimalFormat("00.00");
                        horasTrabajadas=formateador.format(Float.parseFloat( informacionDeHorasTrabajadasVespertino[k][3]));
                        
                        
                        break; //Si encontro coincidencia se sale
                
                    }
                    else{
                        fecha="       " + nombreDia + "-" + DiaMes;
                        horaEntrada="00:00";
                        horasTrabajadas="0.0";
                        horaSalida="00:00";
                    }

                }

                LBL_diaVespertino[i][j] = new JLabel(fecha);
                LBL_diaVespertino[i][j].setFont(new java.awt.Font("Tahoma", Font.BOLD, 24));
                panelEmpleado.add(LBL_diaVespertino[i][j]);
                
                fechasVespertino[i][j]=DiaCompleto;
                

              // 6 Y 8
                // 4 Y 2.30  6
                try {
                    MaskFormatter mascaraHoraForma = new MaskFormatter("##:##");

                    TXThoraEntradaVespertino[i][j] = new JFormattedTextField(mascaraHoraForma);
                    TXThoraEntradaVespertino[i][j].setText(horaEntrada);
                    TXThoraEntradaVespertino[i][j].setFont(new java.awt.Font("Tahoma", Font.BOLD, 24));
                    EventoTeclas EventoTecla = new EventoTeclas(i, j, EventoTeclas.Entrada, EventoTeclas.Vespertino);
                    TXThoraEntradaVespertino[i][j].addKeyListener(EventoTecla);
                    panelEmpleado.add(TXThoraEntradaVespertino[i][j]);

                    TXThoraSalidaVespertino[i][j] = new JFormattedTextField(mascaraHoraForma);
                    TXThoraSalidaVespertino[i][j].setText(horaSalida);
                    TXThoraSalidaVespertino[i][j].setFont(new java.awt.Font("Tahoma", Font.BOLD, 24));
                    EventoTecla = new EventoTeclas(i, j, EventoTeclas.Salida, EventoTeclas.Vespertino);
                    TXThoraSalidaVespertino[i][j].addKeyListener(EventoTecla);
                    panelEmpleado.add(TXThoraSalidaVespertino[i][j]);

                    
                    TXThoraTrabajadaVespertino[i][j] = new JFormattedTextField();  //8  ,  9   , 12
                    TXThoraTrabajadaVespertino[i][j].setFont(new java.awt.Font("Tahoma", Font.BOLD, 24));
                    TXThoraTrabajadaVespertino[i][j].setText(horasTrabajadas);
                    TXThoraTrabajadaVespertino[i][j].setEnabled(false);
                    panelEmpleado.add(TXThoraTrabajadaVespertino[i][j]);
                    
                    BTN_eliminarVespertino[i][j] = new JButton("X");  //Boton de Cerrar
                    EventoBorrar eventoBorrar=new EventoBorrar(i,j,EventoBorrar.Vespertino);
                    BTN_eliminarVespertino[i][j].addActionListener(eventoBorrar);
                    panelEmpleado.add(BTN_eliminarVespertino[i][j]);
                    
                   
                    

                } catch (ParseException ex) {
                    Logger.getLogger(factura_ifz.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
            PNL_horas.add(panelEmpleado); //Se agrega la Fila
            
        
            
        }

    }
    
    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BTNadicionalAgregar;
    private javax.swing.JButton BTNadicionalBorrar;
    private javax.swing.JButton BTNagregarPrimeraJornada;
    private javax.swing.JButton BTNagregarSegundaJornada;
    private javax.swing.JButton BTNprimeraJornada;
    private javax.swing.JButton BTNsegundaJornada;
    private javax.swing.JComboBox<String> CMBestatus;
    private javax.swing.JComboBox CMBlugar;
    private javax.swing.JTextField DTE_fecha;
    private com.toedter.calendar.JCalendar DTE_fechaFinPeriodo;
    private com.toedter.calendar.JCalendar DTE_fechaInicioPeriodo;
    private javax.swing.JLabel LBL_noDisponible;
    private javax.swing.JLabel LBLdisponible;
    private javax.swing.JLabel LBLorderAvalible;
    private javax.swing.JLabel LBLorderNotAvalible;
    private javax.swing.JPanel PNL_horas;
    private javax.swing.JPanel PNL_reportes_fechas;
    private javax.swing.JTable TBL_primeraJornada;
    private javax.swing.JTable TBL_segundaJornada;
    private javax.swing.JTable TBLconceptosAdicionales;
    private javax.swing.JTable TBLfolios;
    private javax.swing.JTable TBLtrabajaores;
    private javax.swing.JTextField TXT1;
    private javax.swing.JTextField TXT2;
    private javax.swing.JTextField TXT_buscarFolio;
    private javax.swing.JTextField TXT_buscarTrabajador;
    private javax.swing.JTextArea TXT_conceptoTexto;
    private javax.swing.JTextArea TXT_descripcion;
    private javax.swing.JTextArea TXT_descripcion_problema;
    private javax.swing.JTextField TXT_direccion;
    private javax.swing.JTextField TXT_folio;
    private javax.swing.JTextField TXT_impuestos;
    private javax.swing.JTextField TXT_nombre;
    private javax.swing.JTextField TXT_po;
    private javax.swing.JTextField TXT_totalAdicionales;
    private javax.swing.JTextField TXTaprueba;
    private javax.swing.JTextField TXTcantidad;
    private javax.swing.JTextField TXTcontactoCliente;
    private javax.swing.JTextField TXTdescripcion;
    private javax.swing.JTextField TXTfechaReport;
    private javax.swing.JTextField TXTnumeroParte;
    private javax.swing.JTextField TXTprecioHorasRegulares;
    private javax.swing.JTextField TXTprecioHorasTiempoExtra;
    private javax.swing.JTextField TXTprecioUnitario;
    private javax.swing.JTextField TXTrechazados;
    private javax.swing.JTextField TXTrechazados2;
    private javax.swing.JTextField TXTresidente;
    private javax.swing.JTextField TXTretrabajados;
    private javax.swing.JTextField TXTretrabajados2;
    private javax.swing.JTextField TXTsorteados;
    private javax.swing.JTextField TXTsorteados2;
    private javax.swing.JTextField TXTsubtotal;
    private javax.swing.JTextField TXTtipoDeParte;
    private javax.swing.JTextField TXTtotal;
    private javax.swing.JTextField TXTtotalHorasExtra;
    private javax.swing.JTextField TXTtotalHorasRegulares;
    private javax.swing.JTextField TXTvendedor;
    private javax.swing.JTextField diferenciaRechazados;
    private javax.swing.JTextField diferenciaRetrabajados;
    private javax.swing.JTextField diferenciaSorteados;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField promedioRechazado;
    private javax.swing.JTextField promedioRechazadoMAX;
    private javax.swing.JTextField promedioRechazadoMIN;
    private javax.swing.JTextField promedioRetrabajado;
    private javax.swing.JTextField promedioRetrabajadoMAX;
    private javax.swing.JTextField promedioRetrabajadoMIN;
    private javax.swing.JTextField promedioSorteado;
    private javax.swing.JTextField promedioSorteadoMAX;
    private javax.swing.JTextField promedioSorteadoMIN;
    private javax.swing.JScrollPane scrollPane;
    private javax.swing.JScrollPane scrollPaneReporte;
    private javax.swing.JScrollPane scrollPaneWorkHours;
    private javax.swing.JTextField totalRechazados;
    private javax.swing.JTextField totalRetrabajados;
    private javax.swing.JTextField totalSorteados;
    private javax.swing.JTextField txt_solicitante;
    // End of variables declaration//GEN-END:variables
}
