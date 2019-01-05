package interfaces.emergentes;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import serviciosBD.documentos_servicio;
import serviciosBD.persona_servicio;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Soxtec Desarrollo
 */
public class tarjetas_detalle_ifz extends javax.swing.JDialog {

   
    boolean informacion=false;
    
    String empleado[][];
    
    
    
    
    public tarjetas_detalle_ifz(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        
        
        
           
        persona_servicio empleados = new persona_servicio();
        
        empleado = empleados.LISTAEmpleadosPorStringYSinAsignar(" v.valorTexto='ingresado'  OR  v.valorTexto='alta'     OR   v.valorTexto='sinDefinir'   OR   v.valorTexto='paroTecnico'   ");
                
        DefaultComboBoxModel MODEL=new DefaultComboBoxModel();
        
        for (int i=0;i<empleado.length;i++){
            MODEL.addElement(empleado[i][0]);
        }
        
        nombreCMB.setModel(MODEL);
        
    }

    public boolean isInformacion() {
        return informacion;
    }

    public void setInformacion(boolean informacion) {
        this.informacion = informacion;
    }

    
    
    
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        nombreCMB = new javax.swing.JComboBox<>();
        cuentaTXT = new javax.swing.JTextField();
        tarjetaTXT = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        nombreCMB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel1.setText("Nombre");

        jLabel2.setText("Cuenta");

        jLabel3.setText("Tarjeta");

        jButton1.setText("Guardar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(nombreCMB, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cuentaTXT)
                    .addComponent(tarjetaTXT)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 425, Short.MAX_VALUE))
                .addContainerGap(23, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nombreCMB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cuentaTXT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tarjetaTXT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
           
        
        if (!cuentaTXT.getText().equals("")  && !tarjetaTXT.getText().equals("") ){
        
            documentos_servicio doc=new documentos_servicio();

            String insertaCuenta=doc.insertaInformacion(empleado[nombreCMB.getSelectedIndex()][1],"51","1",cuentaTXT.getText());
            String insertaTarjeta=doc.insertaInformacion(empleado[nombreCMB.getSelectedIndex()][1],"52","1",tarjetaTXT.getText());

            informacion=true;
            this.dispose();
        }
        else{
            JOptionPane.showMessageDialog(null, "Llena todos los datos");
        }
        
    }//GEN-LAST:event_jButton1ActionPerformed

 

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField cuentaTXT;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JComboBox<String> nombreCMB;
    private javax.swing.JTextField tarjetaTXT;
    // End of variables declaration//GEN-END:variables
}