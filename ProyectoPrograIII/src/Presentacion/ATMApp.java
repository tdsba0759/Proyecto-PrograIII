/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Presentacion;

import LogicaNegocio.LogicaCuenta;
import LogicaNegocio.LogicaTransaccion;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author contr
 */
public class ATMApp extends javax.swing.JFrame {

    // Variable para almacenar la cuenta autenticada
    private String cuentaAutenticada;

    /**
     * Creates new form MenuPrincipal
     */
    public ATMApp() {
        initComponents();
    }

    public void setCuentaAutenticada(String cuenta) {
        this.cuentaAutenticada = cuenta;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        MovimientosDialog = new javax.swing.JDialog();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblmovimientos = new javax.swing.JTable();
        btnHisotrial = new javax.swing.JButton();
        btnRegistro = new javax.swing.JButton();
        btnCerrar = new javax.swing.JButton();
        btnOperaciones = new javax.swing.JButton();
        btnIngreso = new javax.swing.JButton();
        btnTransferir1 = new javax.swing.JButton();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        tblmovimientos.setBackground(new java.awt.Color(0, 102, 102));
        tblmovimientos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Fecha", "Monto", "Movimiento"
            }
        ));
        jScrollPane1.setViewportView(tblmovimientos);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 453, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        MovimientosDialog.getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Principal");

        btnHisotrial.setText("Movimientos");
        btnHisotrial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHisotrialActionPerformed(evt);
            }
        });

        btnRegistro.setText("Depositar");
        btnRegistro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistroActionPerformed(evt);
            }
        });

        btnCerrar.setText("Cerrar sesion");
        btnCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarActionPerformed(evt);
            }
        });

        btnOperaciones.setText("Consultar");

        btnIngreso.setText("Retirar");
        btnIngreso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIngresoActionPerformed(evt);
            }
        });

        btnTransferir1.setText("Transferir");
        btnTransferir1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTransferir1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(btnOperaciones, javax.swing.GroupLayout.DEFAULT_SIZE, 216, Short.MAX_VALUE)
                                .addGap(28, 28, 28)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnIngreso, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnTransferir1, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnHisotrial, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnIngreso, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnOperaciones, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTransferir1, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnHisotrial, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(44, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnRegistroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistroActionPerformed

        // Solicitar al usuario que ingrese el saldo
        String saldoStr = JOptionPane.showInputDialog("Ingresa el saldo:");

        try {
            // Intentar convertir el texto a un valor double
            double saldoInicial = Double.parseDouble(saldoStr);

            // Crear instancia de LogicaTransaccion
            LogicaTransaccion logicaTransaccion = new LogicaTransaccion();

            // Llamar al método de agregar saldo (se asume que 'cuentaAutenticada' es el ID de la cuenta autenticada)
            logicaTransaccion.agregarSaldo(cuentaAutenticada, saldoInicial); // Verifica que la cuenta exista

            // Si todo es exitoso, mostrar mensaje de éxito
            JOptionPane.showMessageDialog(null, "Saldo agregado con éxito.");

        } catch (NumberFormatException ex) {
            // Si el formato del saldo es incorrecto (no es un número válido)
            JOptionPane.showMessageDialog(null, "Por favor ingrese un monto válido para el saldo inicial.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            // Captura cualquier otra excepción que ocurra durante el proceso
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_btnRegistroActionPerformed

    private void btnCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarActionPerformed
        VentanaLogin login = new VentanaLogin();
        login.setVisible(true);
        this.dispose();

    }//GEN-LAST:event_btnCerrarActionPerformed

    private void btnIngresoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIngresoActionPerformed

    }//GEN-LAST:event_btnIngresoActionPerformed

    private void btnHisotrialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHisotrialActionPerformed
        // Verificar si la cuenta está autenticada
        if (cuentaAutenticada == null || cuentaAutenticada.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay cuenta autenticada.", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            // Crear una instancia de LogicaTransaccion
            LogicaTransaccion logicaHistorial = new LogicaTransaccion();

            // Obtener los movimientos del historial usando el número de cuenta autenticada
            ArrayList<String[]> movimientos = logicaHistorial.consultarHistorial(cuentaAutenticada);

            // Si no se encontraron movimientos
            if (movimientos.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No se encontraron movimientos para esta cuenta.", "Error", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            // Crear un modelo de tabla con los movimientos
            DefaultTableModel model = (DefaultTableModel) tblmovimientos.getModel();

            // Limpiar las filas anteriores en la tabla
            model.setRowCount(0);

            // Agregar las filas a la tabla con los datos de los movimientos
            for (String[] movimiento : movimientos) {
                model.addRow(movimiento);  // Cada movimiento debe ser un arreglo de String
            }

        } catch (IOException ex) {
            // Mostrar mensaje de error si ocurre alguna excepción
            JOptionPane.showMessageDialog(this, "Error al cargar el historial: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            Logger.getLogger(ATMApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnHisotrialActionPerformed

    private void btnTransferir1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTransferir1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnTransferir1ActionPerformed

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
            java.util.logging.Logger.getLogger(ATMApp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ATMApp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ATMApp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ATMApp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ATMApp().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDialog MovimientosDialog;
    private javax.swing.JButton btnCerrar;
    private javax.swing.JButton btnHisotrial;
    private javax.swing.JButton btnIngreso;
    private javax.swing.JButton btnOperaciones;
    private javax.swing.JButton btnRegistro;
    private javax.swing.JButton btnTransferir1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblmovimientos;
    // End of variables declaration//GEN-END:variables

}
