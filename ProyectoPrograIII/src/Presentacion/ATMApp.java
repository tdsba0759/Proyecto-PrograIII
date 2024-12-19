package Presentacion;

import LogicaNegocio.LogicaTransaccion;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author dmsda, Joshua, kendall
 */
public class ATMApp extends javax.swing.JFrame {

    // Variable para almacenar la cuenta autenticada
    private String cuentaAutenticada;

    /**
     * Creates new form MenuPrincipal
     */
    public ATMApp() throws IOException {
        initComponents();
        setLocationRelativeTo(null); // Centrar ventana
        new LogicaTransaccion(); // Inicializar la instancia

    }

    public void setCuentaAutenticada(String cuenta, String nombreUsuario, String pin) {
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

        jPanel2 = new javax.swing.JPanel();
        btnDepositar = new javax.swing.JButton();
        btnConsultar = new javax.swing.JButton();
        btnTransferir1 = new javax.swing.JButton();
        btnRetirar = new javax.swing.JButton();
        btnHisotrial = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblmovimientos = new javax.swing.JTable();
        Btntipodecambio = new javax.swing.JToggleButton();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Principal");

        jPanel2.setBackground(new java.awt.Color(0, 102, 102));
        jPanel2.setToolTipText("");

        btnDepositar.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        btnDepositar.setText("Depositar");
        btnDepositar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDepositarActionPerformed(evt);
            }
        });

        btnConsultar.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        btnConsultar.setText("Consultar Saldo");
        btnConsultar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsultarActionPerformed(evt);
            }
        });

        btnTransferir1.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        btnTransferir1.setText("Transferir");
        btnTransferir1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTransferir1ActionPerformed(evt);
            }
        });

        btnRetirar.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        btnRetirar.setText("Retirar");
        btnRetirar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRetirarActionPerformed(evt);
            }
        });

        btnHisotrial.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        btnHisotrial.setText("Movimientos");
        btnHisotrial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHisotrialActionPerformed(evt);
            }
        });

        tblmovimientos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Fecha", "Monto", "Movimiento", "Monto transacción", "Monto final "
            }
        ));
        jScrollPane1.setViewportView(tblmovimientos);

        Btntipodecambio.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        Btntipodecambio.setText("Consultar tipo de cambio");
        Btntipodecambio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtntipodecambioActionPerformed(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jButton1.setText("Cerrar sesión");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Yu Gothic Light", 3, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(204, 204, 204));
        jLabel1.setText("BIENVENIDO");

        jLabel2.setBackground(new java.awt.Color(204, 204, 204));
        jLabel2.setForeground(new java.awt.Color(204, 204, 204));
        jLabel2.setText("A continuacion se muestran las opciones disponibles que puedes realizar con tu dinero. Seleccione lo necesario:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(66, 66, 66)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnRetirar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnDepositar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(106, 106, 106)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnTransferir1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnHisotrial, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(81, 81, 81)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnConsultar, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Btntipodecambio)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(115, 115, 115)
                        .addComponent(jLabel2))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(269, 269, 269)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 690, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 690, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(43, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnRetirar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTransferir1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnConsultar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Btntipodecambio, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnHisotrial, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(btnDepositar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addContainerGap())
        );

        getContentPane().add(jPanel2, java.awt.BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnDepositarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDepositarActionPerformed
        // Solicitar el monto al usuario
        String montoStr = JOptionPane.showInputDialog(this, "Ingrese el monto a depositar:");

        // Validar que el usuario no cancele o deje vacío el campo
        if (montoStr == null || montoStr.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe ingresar un monto.", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            // Mostrar el monto ingresado para depuración
            System.out.println("Monto ingresado (antes de convertir): " + montoStr);

            // Reemplazar comas por puntos si es necesario (para manejar formatos regionales)
            montoStr = montoStr.replace(",", ".");

            // Validar que el monto sea un número válido
            if (!montoStr.matches("\\d+(\\.\\d+)?")) {
                JOptionPane.showMessageDialog(this, "Por favor, ingrese un monto válido.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Convertir el monto a double
            double monto = Double.parseDouble(montoStr);

            // Validar que el monto sea positivo
            if (monto <= 0) {
                JOptionPane.showMessageDialog(this, "El monto debe ser un número positivo.", "Error", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Crear una instancia de LogicaTransaccion
            LogicaTransaccion logicaTransaccion = new LogicaTransaccion();

            // Obtener el saldo actual de la cuenta
            double saldoAnterior = logicaTransaccion.consultarSaldo(cuentaAutenticada);

            // Calcular el saldo nuevo
            double saldoNuevo = saldoAnterior + monto;

            // Registrar el depósito
            logicaTransaccion.depositar(cuentaAutenticada, monto, saldoAnterior, saldoNuevo);

            // Mostrar mensaje de éxito
            JOptionPane.showMessageDialog(this,
                    "Depósito exitoso.\nSaldo anterior: " + saldoAnterior + "\nSaldo nuevo: " + saldoNuevo,
                    "Éxito", JOptionPane.INFORMATION_MESSAGE);

        } catch (NumberFormatException ex) {
            // Manejar error en la conversión del monto a número
            JOptionPane.showMessageDialog(this, "Por favor, ingrese un monto válido.", "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        } catch (Exception ex) {
            // Manejar cualquier otra excepción
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }

    }//GEN-LAST:event_btnDepositarActionPerformed

    private void btnHisotrialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHisotrialActionPerformed
        // Verificar si la cuenta está autenticada
    if (cuentaAutenticada == null || cuentaAutenticada.isEmpty()) {
        JOptionPane.showMessageDialog(this, "No hay cuenta autenticada.", "Error", JOptionPane.WARNING_MESSAGE);
        return;
    }

    try {
        // Crear una instancia de LogicaTransaccion
        LogicaTransaccion logicaHistorial = new LogicaTransaccion();
        JDialog MovimientosDialog = new JDialog();
        // Obtener los movimientos del historial usando el número de cuenta autenticada
        ArrayList<String[]> movimientos = logicaHistorial.leerBalance(cuentaAutenticada);

        // Si no se encontraron movimientos
        if (movimientos.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No se encontraron movimientos para esta cuenta.", "Error", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        // Crear un modelo de tabla con los movimientos
        DefaultTableModel model = (DefaultTableModel) tblmovimientos.getModel();

        // Limpiar las filas anteriores en la tabla
        model.setRowCount(0);

        // Definir las columnas de la tabla en el orden solicitado: Fecha, Monto Transacción, Monto Anterior, Movimiento, Monto Final
        model.setColumnIdentifiers(new String[]{"Fecha", "Monto Transacción", "Monto Anterior", "Movimiento", "Monto Final"});

        // Agregar las filas a la tabla con los datos de los movimientos
        for (String[] movimiento : movimientos) {
            // El formato de cada línea es: "0.0,123.0,Deposito,2024-12-16 12:00:06"
            // Dividir cada movimiento en sus partes
            String montoAnterior = movimiento[0];
            String montoNuevo = movimiento[1];
            String tipoMovimiento = movimiento[2];
            String montoTransaccion = movimiento[3];
            String fecha = movimiento[4];

            // Agregar la fila a la tabla en el orden deseado: Fecha, Monto Transacción, Monto Anterior, Movimiento, Monto Final
            model.addRow(new Object[]{fecha, montoTransaccion, montoAnterior, tipoMovimiento, montoNuevo});
        }
        MovimientosDialog.setVisible(true);

    } catch (IOException ex) {
        // Mostrar mensaje de error si ocurre alguna excepción
        JOptionPane.showMessageDialog(this, "Error al cargar el historial: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    } catch (Exception ex) {
        Logger.getLogger(ATMApp.class.getName()).log(Level.SEVERE, null, ex);
    }
    }//GEN-LAST:event_btnHisotrialActionPerformed

    private void btnTransferir1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTransferir1ActionPerformed
        // Solicitar la cuenta de destino
        String cuentaDestino = JOptionPane.showInputDialog(this, "Ingrese el número de cuenta de destino:");

        // Validar que el usuario  deje vacío el campo
        if (cuentaDestino == null || cuentaDestino.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe ingresar una cuenta de destino.", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Solicitar el monto a transferir
        String montoStr = JOptionPane.showInputDialog(this, "Ingrese el monto a transferir:");

        // Validar que el usuario  deje vacío el campo
        if (montoStr == null || montoStr.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe ingresar un monto.", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            // Mostrar el monto ingresado para depuración
            System.out.println("Monto ingresado (antes de convertir): " + montoStr);

            // Reemplazar comas por puntos si es necesario (para manejar formatos regionales)
            montoStr = montoStr.replace(",", ".");

            // Validar que el monto sea un número válido
            if (!montoStr.matches("\\d+(\\.\\d+)?")) {
                JOptionPane.showMessageDialog(this, "Por favor, ingrese un monto válido.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Convertir el monto a double
            double monto = Double.parseDouble(montoStr);

            // Validar que el monto sea positivo
            if (monto <= 0) {
                JOptionPane.showMessageDialog(this, "El monto debe ser un número positivo.", "Error", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Crear una instancia de LogicaTransaccion
            LogicaTransaccion logicaTransaccion = new LogicaTransaccion();

            // Obtener el saldo actual de la cuenta de origen
            double saldoOrigen = logicaTransaccion.consultarSaldo(cuentaAutenticada);

            // Verificar si hay suficiente saldo para realizar la transferencia
            if (saldoOrigen < monto) {
                JOptionPane.showMessageDialog(this, "Saldo insuficiente en la cuenta de origen.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Obtener el saldo actual de la cuenta de destino
            double saldoDestino = logicaTransaccion.consultarSaldo(cuentaDestino);

            // Realizar la transferencia (restar del saldo de origen y sumar al saldo de destino)
            logicaTransaccion.transferir(cuentaAutenticada, cuentaDestino, monto, saldoOrigen, saldoDestino);

            // Mostrar mensaje de éxito
            JOptionPane.showMessageDialog(this,
                    "Transferencia exitosa.\nCuenta de origen: " + cuentaAutenticada + "\nCuenta de destino: " + cuentaDestino
                    + "\nMonto transferido: " + monto,
                    "Éxito", JOptionPane.INFORMATION_MESSAGE);

        } catch (NumberFormatException ex) {
            // Manejar error en la conversión del monto a número
            JOptionPane.showMessageDialog(this, "Por favor, ingrese un monto válido.", "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        } catch (Exception ex) {
            // Manejar cualquier otra excepción
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }//GEN-LAST:event_btnTransferir1ActionPerformed

    private void btnRetirarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRetirarActionPerformed
        // Solicitar el monto al usuario
        String montoStr = JOptionPane.showInputDialog(this, "Ingrese el monto a retirar:");

        // Validar que el usuario no cancele o deje vacío el campo
        if (montoStr == null || montoStr.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe ingresar un monto.", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            // Mostrar el monto ingresado para depuración
            System.out.println("Monto ingresado (antes de convertir): " + montoStr);

            // Reemplazar comas por puntos si es necesario (para manejar formatos regionales)
            montoStr = montoStr.replace(",", ".");

            // Validar que el monto sea un número válido
            if (!montoStr.matches("\\d+(\\.\\d+)?")) {
                JOptionPane.showMessageDialog(this, "Por favor, ingrese un monto válido.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Convertir el monto a double
            double monto = Double.parseDouble(montoStr);

            // Validar que el monto sea positivo
            if (monto <= 0) {
                JOptionPane.showMessageDialog(this, "El monto debe ser un número positivo.", "Error", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Crear una instancia de LogicaTransaccion
            LogicaTransaccion logicaTransaccion = new LogicaTransaccion();

            // Obtener el saldo actual de la cuenta
            double saldoAnterior = logicaTransaccion.consultarSaldo(cuentaAutenticada);

            // Verificar si hay suficiente saldo para realizar el retiro
            if (saldoAnterior < monto) {
                JOptionPane.showMessageDialog(this, "Saldo insuficiente para realizar el retiro.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Calcular el saldo nuevo después del retiro
            double saldoNuevo = saldoAnterior - monto;

            // Registrar el retiro
            logicaTransaccion.retirar(cuentaAutenticada, monto, saldoAnterior, saldoNuevo);

            // Mostrar mensaje de éxito
            JOptionPane.showMessageDialog(this,
                    "Retiro exitoso.\nSaldo anterior: " + saldoAnterior + "\nSaldo nuevo: " + saldoNuevo,
                    "Éxito", JOptionPane.INFORMATION_MESSAGE);

        } catch (NumberFormatException ex) {
            // Manejar error en la conversión del monto a número
            JOptionPane.showMessageDialog(this, "Por favor, ingrese un monto válido.", "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        } catch (Exception ex) {
            // Manejar cualquier otra excepción
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }//GEN-LAST:event_btnRetirarActionPerformed

    private void btnConsultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsultarActionPerformed
        // Verificar si la cuenta está autenticada
        if (cuentaAutenticada == null || cuentaAutenticada.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay cuenta autenticada.", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            // Crear una instancia de LogicaTransaccion
            LogicaTransaccion logicaTransaccion = new LogicaTransaccion();

            // Consultar el saldo de la cuenta autenticada
            double saldo = logicaTransaccion.consultarSaldo(cuentaAutenticada);

            // Mostrar el saldo en un JOptionPane
            JOptionPane.showMessageDialog(this,
                    "El saldo de la cuenta  " + cuentaAutenticada + " es: " + saldo,
                    "Consulta de saldo", JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception ex) {
            // Mostrar mensaje de error si ocurre alguna excepción
            JOptionPane.showMessageDialog(this, "Error al consultar el saldo: " + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnConsultarActionPerformed

    private void BtntipodecambioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtntipodecambioActionPerformed
       Frm_tipo_cambio cambio = new Frm_tipo_cambio();
            cambio.setVisible(true);
            this.dispose();  
    }//GEN-LAST:event_BtntipodecambioActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
          VentanaLogin login = new VentanaLogin();
          login.setVisible(true);
          this.dispose();
       
    }//GEN-LAST:event_jButton1ActionPerformed

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
                try {
                    new ATMApp().setVisible(true);
                } catch (IOException ex) {
                    Logger.getLogger(ATMApp.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton Btntipodecambio;
    private javax.swing.JButton btnConsultar;
    private javax.swing.JButton btnDepositar;
    private javax.swing.JButton btnHisotrial;
    private javax.swing.JButton btnRetirar;
    private javax.swing.JButton btnTransferir1;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblmovimientos;
    // End of variables declaration//GEN-END:variables

}
