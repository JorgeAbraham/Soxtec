/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.util.ArrayList;
import java.util.StringTokenizer;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import serviciosBD.catalogo_servicio;
import serviciosBD.gqrs_servicio;
import serviciosBD.lugar_servicio;
import serviciosBD.persona_servicio;
import utilidadesbasicas.utilidadValidacionNulo;

/**
 *
 * @author Soxtec Desarrollo
 */
public class gqrs_ifz extends javax.swing.JFrame {

    DefaultTableModel TBLMDLgqrs;
    utilidadesbasicas.utilidadValidacionNulo validacionNulos;  
    
    
    public gqrs_ifz() {
        
        gqrs_servicio gqrs=new gqrs_servicio();
        
        TBLMDLgqrs = gqrs.LISTAgqrs(null,null,null);
        initComponents();
        
        validacionNulos=new utilidadValidacionNulo();
        
        validacionNulos.add(TXTgqrs);
        validacionNulos.add(TXTestados);
        validacionNulos.add(CMBestado);
        validacionNulos.add(CMBfamilia);
        validacionNulos.add(CMBlugar);
        validacionNulos.add(TXTsorteo);
        validacionNulos.add(TXTsordeado);
        validacionNulos.add(TXTrechazado);
        validacionNulos.add(TXTretrabajo);
        validacionNulos.add(TXTresponsable);
        
        
        
        
        
        catalogo_servicio estados= new catalogo_servicio();
        CMBestado.setModel(estados.listaComboPorTipoCatalogoEspacionEnBlanco(10));  
        CMBestadoBuscar.setModel(estados.listaComboPorTipoCatalogoEspacionEnBlanco(10)); 
      
        catalogo_servicio familias= new catalogo_servicio();
        CMBfamilia.setModel(familias.listaComboPorTipoCatalogoEspacionEnBlanco(11));  
        
        lugar_servicio lugar=new lugar_servicio();
        CMBlugar.setModel(lugar.LISTAlugaresEspacioEnBlanco());
        
        CMBlugarBuscar.setModel(lugar.LISTAlugaresEspacioEnBlanco());
        
        
        persona_servicio persona=new persona_servicio();
        
        ArrayList<String> personasInvolucradas=new ArrayList();
        personasInvolucradas.add("26");
        personasInvolucradas.add("78");
        personasInvolucradas.add("77");
        personasInvolucradas.add("30");
        personasInvolucradas.add("208");
        personasInvolucradas.add("194");
        
        
        
        CMBresponsable.setModel(   persona.listaComboPorPersonaEspacionEnBlanco(personasInvolucradas)   );
        
        
        TXTestados.setVisible(false);
        TXTfamilia.setVisible(false);
        TXTlugar.setVisible(false);
        TXTresponsable.setVisible(false);
        
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        TXTgqrs = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        DTEabierto = new com.toedter.calendar.JDateChooser();
        jLabel3 = new javax.swing.JLabel();
        DTEcerrado = new com.toedter.calendar.JDateChooser();
        jLabel4 = new javax.swing.JLabel();
        CMBestado = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        TXTid = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        TXTestados = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        CMBfamilia = new javax.swing.JComboBox<>();
        TXTfamilia = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        TXTsorteo = new javax.swing.JTextArea();
        jLabel8 = new javax.swing.JLabel();
        TXTcs = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        CMBlugar = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        TXTsordeado = new javax.swing.JTextField();
        TXTrechazado = new javax.swing.JTextField();
        TXTretrabajo = new javax.swing.JTextField();
        TXTlugar = new javax.swing.JTextField();
        CMBresponsable = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        TXTresponsable = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        TXTgqrsBuscar = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        CMBlugarBuscar = new javax.swing.JComboBox<>();
        jLabel17 = new javax.swing.JLabel();
        CMBestadoBuscar = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTable1.setModel(TBLMDLgqrs);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTable1MouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jButton2.setText("SAVE");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel1.setText("GQRS");

        jLabel2.setText("Date Open");

        jLabel3.setText("Date Close");

        jLabel4.setText("State");

        CMBestado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        CMBestado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CMBestadoActionPerformed(evt);
            }
        });

        jButton1.setText("NEW");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        TXTid.setEditable(false);

        jLabel5.setText("ID");

        TXTestados.setText("jTextField1");

        jLabel6.setText("Family");

        CMBfamilia.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        CMBfamilia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CMBfamiliaActionPerformed(evt);
            }
        });

        TXTfamilia.setText("jTextField1");

        jLabel7.setText("Sort");

        TXTsorteo.setColumns(20);
        TXTsorteo.setRows(5);
        jScrollPane2.setViewportView(TXTsorteo);

        jLabel8.setText("CS / CCR");

        jLabel9.setText("Lugar");

        CMBlugar.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        CMBlugar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CMBlugarActionPerformed(evt);
            }
        });

        jLabel10.setText("Sorted");

        jLabel11.setText("Rejected");

        jLabel12.setText("Reworked");

        TXTsordeado.setText("0");

        TXTrechazado.setText("0");

        TXTretrabajo.setText("0");

        TXTlugar.setText("jTextField1");

        CMBresponsable.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        CMBresponsable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CMBresponsableActionPerformed(evt);
            }
        });

        jLabel13.setText("Responsable");

        TXTresponsable.setText("jTextField1");

        jButton3.setText("BACK");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel14.setText("Buscar");

        jLabel15.setText("GQRS");

        TXTgqrsBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TXTgqrsBuscarKeyReleased(evt);
            }
        });

        jLabel16.setText("Lugar");

        CMBlugarBuscar.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        CMBlugarBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CMBlugarBuscarActionPerformed(evt);
            }
        });

        jLabel17.setText("State");

        CMBestadoBuscar.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        CMBestadoBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CMBestadoBuscarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(TXTestados, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(29, 29, 29)
                                .addComponent(TXTfamilia, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(53, 53, 53)
                                .addComponent(TXTlugar, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(TXTresponsable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(TXTid, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel5)
                                    .addComponent(TXTgqrs, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel7))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(DTEabierto, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3)
                                    .addComponent(DTEcerrado, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(TXTcs, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel9)
                                    .addComponent(CMBlugar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(CMBestado, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel6)
                                    .addComponent(CMBfamilia, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(TXTsordeado, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel10)
                                    .addComponent(jLabel11)
                                    .addComponent(TXTrechazado, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(CMBresponsable, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel12)
                                        .addComponent(TXTretrabajo, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(jScrollPane1)
                            .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 1088, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(35, 35, 35)
                                .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(TXTgqrsBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel15))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel16)
                                    .addComponent(CMBlugarBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(22, 22, 22)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel17)
                                    .addComponent(CMBestadoBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap(43, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(jLabel16)
                    .addComponent(jLabel17))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TXTgqrsBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CMBlugarBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CMBestadoBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 329, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel5)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel4)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel10)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(TXTid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(TXTgqrs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(DTEabierto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(TXTcs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(CMBestado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(TXTsordeado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(TXTretrabajo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel6)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel11)
                                        .addComponent(jLabel13)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(DTEcerrado, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(CMBlugar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(CMBfamilia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(TXTrechazado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(CMBresponsable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TXTestados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TXTfamilia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TXTlugar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TXTresponsable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        jTable1.clearSelection();
        TXTid.setText("");
        TXTgqrs.setText("");
        DTEabierto.setDate(null);
        DTEcerrado.setDate(null);
        TXTestados.setText("");
        CMBestado.setSelectedIndex(0);
        CMBfamilia.setSelectedIndex(0);
        CMBlugar.setSelectedIndex(0);
        CMBresponsable.setSelectedIndex(0);
        TXTfamilia.setText("");
        TXTlugar.setText("");
        TXTsordeado.setText("0");
        TXTrechazado.setText("0");
        TXTretrabajo.setText("0");
        TXTresponsable.setText("0");
        TXTsorteo.setText("");
        
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
          
        
        if (validacionNulos.existenNulos() || DTEabierto.getDate()==null  ){
            JOptionPane.showMessageDialog(null, "Llena todos los Campos" );
        }else{
            
            gqrs_servicio gqrs=new gqrs_servicio();
            
            
            if (TXTid.getText().equals("") ||  TXTid.getText()==null  ){
                JOptionPane.showMessageDialog(null, "Inserted");
                gqrs.insertaGQRS(
                                TXTgqrs.getText(),
                                utilidadesFecha.sumaFecha.date_aaaa_mm_dd(DTEabierto.getDate()),
                                DTEcerrado.getDate()!=null?"'"+utilidadesFecha.sumaFecha.date_aaaa_mm_dd(DTEcerrado.getDate())+"'":null,
                                TXTestados.getText(),
                                null,
                                TXTsorteo.getText(),
                                TXTfamilia.getText(),
                                TXTcs.getText(),
                                TXTlugar.getText(),
                                TXTsordeado.getText(),
                                TXTrechazado.getText(),
                                TXTretrabajo.getText(),
                                TXTresponsable.getText()
                            );
            }else{
                JOptionPane.showMessageDialog(null, "Updated");
                gqrs.actualizaGQRS(
                        TXTid.getText(),
                        TXTgqrs.getText(),
                        utilidadesFecha.sumaFecha.date_aaaa_mm_dd(DTEabierto.getDate()),
                        DTEcerrado.getDate()!=null?"'"+utilidadesFecha.sumaFecha.date_aaaa_mm_dd(DTEcerrado.getDate())+"'":null,
                        TXTestados.getText(),
                        null,
                        TXTsorteo.getText(),
                        TXTfamilia.getText(),
                        TXTcs.getText(),
                        TXTlugar.getText(),
                        TXTsordeado.getText(),
                        TXTrechazado.getText(),
                        TXTretrabajo.getText(),
                        TXTresponsable.getText()
                
                );
            }
            
        }
             
        
        
        gqrs_servicio gqrs=new gqrs_servicio();
        TBLMDLgqrs = gqrs.LISTAgqrs(TXTgqrsBuscar.getText(),CMBlugarBuscar.getSelectedItem()+"",CMBestadoBuscar.getSelectedItem()+"");
        jTable1.setModel(TBLMDLgqrs);
        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void CMBestadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CMBestadoActionPerformed
        
        if (CMBestado.getSelectedIndex()!=0){
            catalogo_servicio catalogoValor=new catalogo_servicio();
            TXTestados.setText(catalogoValor.idCatalogoPorValorYTipo(CMBestado.getSelectedItem()+"", "10"));
        }
        else{
           TXTestados.setText("");
        }
    }//GEN-LAST:event_CMBestadoActionPerformed

    private void CMBfamiliaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CMBfamiliaActionPerformed
        
        if (CMBfamilia.getSelectedIndex()!=0){
            catalogo_servicio catalogoValor=new catalogo_servicio();
            TXTfamilia.setText(catalogoValor.idCatalogoPorValorYTipo(CMBfamilia.getSelectedItem()+"", "11"));
        }
        else{
           TXTfamilia.setText("");
        }
    }//GEN-LAST:event_CMBfamiliaActionPerformed

    private void CMBlugarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CMBlugarActionPerformed
        
        if (CMBlugar.getSelectedIndex()!=0){
            lugar_servicio lugar=new lugar_servicio();
            TXTlugar.setText(lugar.idLugarPorNombre(CMBlugar.getSelectedItem()+""));
        }
        else{
           TXTlugar.setText("");
       }
        
    }//GEN-LAST:event_CMBlugarActionPerformed

    private void CMBresponsableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CMBresponsableActionPerformed
       if (CMBresponsable.getSelectedIndex()!=0){
            StringTokenizer TOK=new StringTokenizer(CMBresponsable.getSelectedItem()+"", "[]", false);
            TXTresponsable.setText(TOK.nextToken());
        }
       else{
           TXTresponsable.setText("");
       }
    }//GEN-LAST:event_CMBresponsableActionPerformed

    private void jTable1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseReleased
        if(jTable1.getSelectedRow()!=-1){
            gqrs_servicio gqrs=new gqrs_servicio();
            String GQRS[][]=gqrs.gqrsPorID(jTable1.getValueAt(jTable1.getSelectedRow(),0 )+"");
            
            TXTid.setText(GQRS[0][0]);
            TXTgqrs.setText(GQRS[0][1]);
            DTEabierto.setDate(utilidadesFecha.sumaFecha.StringTOdate_aaaa_mm_dd( GQRS[0][2] ,utilidadesFecha.sumaFecha.GUION ));
            DTEcerrado.setDate(utilidadesFecha.sumaFecha.StringTOdate_aaaa_mm_dd( GQRS[0][3] ,utilidadesFecha.sumaFecha.GUION ));
            
            TXTestados.setText(GQRS[0][4]);
            CMBestado.setSelectedItem(GQRS[0][5]);
            
            TXTsorteo.setText(GQRS[0][7]);
            
            TXTfamilia.setText(GQRS[0][8]);
            CMBfamilia.setSelectedItem(GQRS[0][9]);
            
            TXTcs.setText(GQRS[0][10]);
            
            TXTlugar.setText(GQRS[0][11]);
            CMBlugar.setSelectedItem(GQRS[0][12]);
            
            TXTsordeado.setText(GQRS[0][13]);
            TXTrechazado.setText(GQRS[0][14]);
            TXTretrabajo.setText(GQRS[0][15]);
            
            TXTresponsable.setText(GQRS[0][16]);
            CMBresponsable.setSelectedItem(   "[" +GQRS[0][16]+"]-"+ GQRS[0][17]  );
            
            
        }
    }//GEN-LAST:event_jTable1MouseReleased

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        this.dispose();
        menu_ifz V= new menu_ifz();
        V.setVisible(true);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void TXTgqrsBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TXTgqrsBuscarKeyReleased
        gqrs_servicio gqrs=new gqrs_servicio();
        TBLMDLgqrs = gqrs.LISTAgqrs(TXTgqrsBuscar.getText(),CMBlugarBuscar.getSelectedItem()+"",CMBestadoBuscar.getSelectedItem()+"");
        jTable1.setModel(TBLMDLgqrs);
    }//GEN-LAST:event_TXTgqrsBuscarKeyReleased

    private void CMBlugarBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CMBlugarBuscarActionPerformed
        gqrs_servicio gqrs=new gqrs_servicio();
        TBLMDLgqrs = gqrs.LISTAgqrs(TXTgqrsBuscar.getText(),CMBlugarBuscar.getSelectedItem()+"",CMBestadoBuscar.getSelectedItem()+"");
        jTable1.setModel(TBLMDLgqrs);
    }//GEN-LAST:event_CMBlugarBuscarActionPerformed

    private void CMBestadoBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CMBestadoBuscarActionPerformed
        gqrs_servicio gqrs=new gqrs_servicio();
        TBLMDLgqrs = gqrs.LISTAgqrs(TXTgqrsBuscar.getText(),CMBlugarBuscar.getSelectedItem()+"",CMBestadoBuscar.getSelectedItem()+"");
        jTable1.setModel(TBLMDLgqrs);
    }//GEN-LAST:event_CMBestadoBuscarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(gqrs_ifz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(gqrs_ifz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(gqrs_ifz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(gqrs_ifz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new gqrs_ifz().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> CMBestado;
    private javax.swing.JComboBox<String> CMBestadoBuscar;
    private javax.swing.JComboBox<String> CMBfamilia;
    private javax.swing.JComboBox<String> CMBlugar;
    private javax.swing.JComboBox<String> CMBlugarBuscar;
    private javax.swing.JComboBox<String> CMBresponsable;
    private com.toedter.calendar.JDateChooser DTEabierto;
    private com.toedter.calendar.JDateChooser DTEcerrado;
    private javax.swing.JTextField TXTcs;
    private javax.swing.JTextField TXTestados;
    private javax.swing.JTextField TXTfamilia;
    private javax.swing.JTextField TXTgqrs;
    private javax.swing.JTextField TXTgqrsBuscar;
    private javax.swing.JTextField TXTid;
    private javax.swing.JTextField TXTlugar;
    private javax.swing.JTextField TXTrechazado;
    private javax.swing.JTextField TXTresponsable;
    private javax.swing.JTextField TXTretrabajo;
    private javax.swing.JTextField TXTsordeado;
    private javax.swing.JTextArea TXTsorteo;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
