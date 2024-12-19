
package Presentacion;
import Servicios.ServicioLogicaTipoCambio;
import LogicaNegocio.LogicaTipoCambio;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author dmsda
 */
public class Frm_tipo_cambio extends javax.swing.JFrame {

     private final String token = "22OOIA2PA1";
    /**
     * Creates new form Frm_tipo_cambio
     */
    public Frm_tipo_cambio() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        dtinicio = new com.toedter.calendar.JDateChooser();
        jLabel6 = new javax.swing.JLabel();
        dtfinal = new com.toedter.calendar.JDateChooser();
        btnconsultarconversion = new javax.swing.JButton();
        btnlimpiar = new javax.swing.JButton();
        btnregresar = new javax.swing.JButton();
        btndolares = new javax.swing.JRadioButton();
        btncolones = new javax.swing.JRadioButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblresultados = new javax.swing.JTable();
        txtMonto = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lbresultado = new javax.swing.JLabel();
        btnconsultarconveersión = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jPanel1.setBackground(new java.awt.Color(0, 102, 102));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setFont(new java.awt.Font("Comic Sans MS", 3, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Consultar el tipo de cambio por fechas");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(195, 12, -1, -1));

        jLabel4.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Fecha de inicio");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 310, -1, -1));

        dtinicio.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.add(dtinicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 310, 141, -1));

        jLabel6.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Fecha final");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 310, -1, -1));
        jPanel1.add(dtfinal, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 320, 136, -1));

        btnconsultarconversion.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnconsultarconversion.setText("Consultar");
        btnconsultarconversion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnconsultarconversionActionPerformed(evt);
            }
        });
        jPanel1.add(btnconsultarconversion, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 360, -1, -1));

        btnlimpiar.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnlimpiar.setText("Limpiar");
        btnlimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnlimpiarActionPerformed(evt);
            }
        });
        jPanel1.add(btnlimpiar, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 360, -1, -1));

        btnregresar.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        btnregresar.setText("Regresar");
        btnregresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnregresarActionPerformed(evt);
            }
        });
        jPanel1.add(btnregresar, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 20, 160, 30));

        btndolares.setBackground(new java.awt.Color(0, 102, 102));
        buttonGroup1.add(btndolares);
        btndolares.setForeground(new java.awt.Color(255, 255, 255));
        btndolares.setText("Dolares");
        jPanel1.add(btndolares, new org.netbeans.lib.awtextra.AbsoluteConstraints(36, 143, 88, -1));

        btncolones.setBackground(new java.awt.Color(0, 102, 102));
        buttonGroup1.add(btncolones);
        btncolones.setForeground(new java.awt.Color(255, 255, 255));
        btncolones.setText("Colones");
        jPanel1.add(btncolones, new org.netbeans.lib.awtextra.AbsoluteConstraints(36, 184, -1, -1));

        tblresultados.setBackground(new java.awt.Color(204, 204, 204));
        tblresultados.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(204, 204, 204), null));
        tblresultados.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        tblresultados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Fecha ", "Compra", "Venta"
            }
        ));
        jScrollPane1.setViewportView(tblresultados);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 400, 840, 164));

        txtMonto.setFont(new java.awt.Font("Arial", 0, 36)); // NOI18N
        jPanel1.add(txtMonto, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 143, 104, 84));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Digite el monto que desea consultar:");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(38, 100, -1, -1));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("seleccione el tipo de divisa que utilizara para la consulta");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(26, 64, -1, -1));

        lbresultado.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        lbresultado.setForeground(new java.awt.Color(255, 255, 255));
        lbresultado.setText("Monto");
        jPanel1.add(lbresultado, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 250, -1, -1));

        btnconsultarconveersión.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnconsultarconveersión.setText("Consultar");
        btnconsultarconveersión.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnconsultarconveersiónActionPerformed(evt);
            }
        });
        jPanel1.add(btnconsultarconveersión, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 170, -1, -1));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Presentacion/Divisa .jpg"))); // NOI18N
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 80, -1, -1));

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnconsultarconversionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnconsultarconversionActionPerformed

        // Inicializa la lógica de negocio
        ServicioLogicaTipoCambio tipocambio= new LogicaTipoCambio();

         
        
        // Obtiene las fechas de iniciso y final desde los componentes de la interfaz
        Date fechaInicioDate = dtinicio.getDate();
        Date fechaFinalDate = dtfinal.getDate();

        // Verifica si las fechas seleccionadas no son null
        if (fechaInicioDate == null || fechaFinalDate == null) {
            JOptionPane.showMessageDialog(this, "Por favor seleccione ambas fechas.");
            return; // Sale del evento si alguna fecha no está seleccionada
        }

        // Verifica que la fecha de inicio sea anterior o igual a la fecha final
        if (fechaInicioDate.after(fechaFinalDate)) {
            JOptionPane.showMessageDialog(this, "La fecha de inicio debe ser anterior o igual a la fecha final.");
            return; // Sale del evento si la fecha de inicio es después de la fecha final
        }
        

        // Define el formato de fecha que se va a utilizar para mostrar en la tabla
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fechaInicioDate); // Inicializa el calendario con la fecha de inicio

        // Itera sobre el rango de fechas desde la fecha de inicio hasta la fecha final
        while (!calendar.getTime().after(fechaFinalDate)) {
            Date fechaActual = calendar.getTime(); // Obtiene la fecha actual del calendario
            String fechaActualStr = dateFormat.format(fechaActual); // Convierte la fecha a formato String

            try {
                // Llama a la lógica de negocio para obtener el tipo de cambio de compra y venta
                String resultadoCompra = tipocambio.obtenerTipoCambio("317", fechaActualStr, fechaActualStr, "David", "N", "dmsdavidmonterososa@gmail.com", token);
                String resultadoVenta = tipocambio.obtenerTipoCambio("318", fechaActualStr, fechaActualStr, "David", "N", "dmsdavidmonterososa@gmail.com", token);

                // Actualiza la tabla con los resultados de compra y venta
                DefaultTableModel tableModel = (DefaultTableModel) tblresultados.getModel();
                tableModel.addRow(new Object[]{fechaActualStr, resultadoCompra, resultadoVenta});
            } catch (Exception ex) {
                // Muestra un mensaje de error si ocurre un problema al obtener el tipo de cambio
                JOptionPane.showMessageDialog(this, "Error al obtener tipo de cambio para " + fechaActualStr + ": " + ex.getMessage());
            }

            // Avanza al siguiente día
            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }
    }//GEN-LAST:event_btnconsultarconversionActionPerformed

    private void btnlimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnlimpiarActionPerformed
       // Limpia los campos de fecha
    dtinicio.setDate(null); // Limpia la fecha de inicio
    dtfinal.setDate(null);  // Limpia la fecha final

    // Limpia los resultados de la tabla
    DefaultTableModel tableModel = (DefaultTableModel) tblresultados.getModel();
    tableModel.setRowCount(0); // Elimina todas las filas de la tabla
    }//GEN-LAST:event_btnlimpiarActionPerformed

    private void btnregresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnregresarActionPerformed
         try {
             ATMApp atm = new ATMApp();
             
             atm.setVisible(true);      
             this.dispose();
         } catch (IOException ex) {
             Logger.getLogger(Frm_tipo_cambio.class.getName()).log(Level.SEVERE, null, ex);
         }
    }//GEN-LAST:event_btnregresarActionPerformed

    private void btnconsultarconveersiónActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnconsultarconveersiónActionPerformed

      // Definir la fecha directamente como una cadena
String fecha = "12/12/2024";

// Verifica cuál JRadioButton está seleccionado para determinar el tipo de moneda
String tipoMoneda;
if (btndolares.isSelected()) {
    tipoMoneda = "Dólares";
} else if (btncolones.isSelected()) {
    tipoMoneda = "Colones";
} else {
    JOptionPane.showMessageDialog(this, "Por favor seleccione un tipo de moneda.");
    return; // Sale del método si no se ha seleccionado un tipo de moneda
}

double monto;
try {
    // Obtiene el monto desde el campo de texto y lo convierte a tipo double
    monto = Double.parseDouble(txtMonto.getText());
} catch (NumberFormatException e) {
    // Si el monto no es un número válido, muestra un mensaje de error
    JOptionPane.showMessageDialog(this, "Por favor ingrese un monto válido.");
    return; // Termina la ejecución si el monto es inválido
}

// Inicializa la lógica de negocio
LogicaTipoCambio logicaNegocio = new LogicaTipoCambio();

try {
    // Llama a la lógica de negocio para obtener el tipo de cambio
    String tipoCambio = logicaNegocio.obtenerTipoCambio("317", fecha, fecha, "David", "N", "dmsdavidmonterososa@gmail.com", token);

    // Convierte el tipo de cambio a un valor numérico (double)
    double tipoCambioValor = Double.parseDouble(tipoCambio);

    // Realiza la conversión según el tipo de moneda seleccionado
    double resultadoConversion = 0;
    if (tipoMoneda.equals("Dólares")) {
        // Si el tipo de moneda es Dólares, multiplicamos el monto por el tipo de cambio
        resultadoConversion = monto * tipoCambioValor;
    } else if (tipoMoneda.equals("Colones")) {
        // Si el tipo de moneda es Colones, dividimos el monto por el tipo de cambio
        resultadoConversion = monto / tipoCambioValor;
    }

    // Muestra el resultado en el JLabel
    lbresultado.setText("La conversión de " + monto + " es: " + resultadoConversion + " en " + tipoMoneda);

} catch (Exception ex) {
    // Si ocurre algún error en la obtención del tipo de cambio o en la conversión, se muestra el mensaje de error
    lbresultado.setText("Error: " + ex.getMessage());
}
    }//GEN-LAST:event_btnconsultarconveersiónActionPerformed

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
            java.util.logging.Logger.getLogger(Frm_tipo_cambio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Frm_tipo_cambio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Frm_tipo_cambio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Frm_tipo_cambio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Frm_tipo_cambio().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton btncolones;
    private javax.swing.JButton btnconsultarconveersión;
    private javax.swing.JButton btnconsultarconversion;
    private javax.swing.JRadioButton btndolares;
    private javax.swing.JButton btnlimpiar;
    private javax.swing.JButton btnregresar;
    private javax.swing.ButtonGroup buttonGroup1;
    private com.toedter.calendar.JDateChooser dtfinal;
    private com.toedter.calendar.JDateChooser dtinicio;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbresultado;
    private javax.swing.JTable tblresultados;
    private javax.swing.JTextField txtMonto;
    // End of variables declaration//GEN-END:variables
}
