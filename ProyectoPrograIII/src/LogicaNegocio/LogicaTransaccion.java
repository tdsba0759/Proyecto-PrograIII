package LogicaNegocio;


import Servicios.OperacionesBancarias;
import java.util.ArrayList;
import AccesoDatos.AccesoDatos;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * La clase LogicaTransaccion gestiona las transacciones bancarias, como la
 * consulta de saldo, la adición y eliminación de saldo en cuentas y el registro
 * de las transacciones. Utiliza la clase AccesoDatos para la lectura y
 * escritura de registros y la clase Idcontrol para la gestión de los
 * identificadores únicos de transacciones.
 *
 * @autor Joshua
 */
public class LogicaTransaccion implements OperacionesBancarias {
    
    private final AccesoDatos accesoDatos;
    
    public LogicaTransaccion() throws IOException {
    this.accesoDatos = new AccesoDatos(); // Inicializa el acceso a datos
        }


    @Override
    public double consultarSaldo(String cuentaId) throws Exception {
        // Leer los registros del archivo
        ArrayList<String[]> registros = accesoDatos.leerRegistros();

        // Buscar la cuenta por su ID
        for (String[] registro : registros) {
            if (registro[0].equals(cuentaId)) {
                // Si la cuenta existe, devolver el saldo como un valor double
                return Double.parseDouble(registro[2]);
            }
        }

        // Si la cuenta no se encuentra, lanzar una excepción
        throw new Exception("Cuenta no encontrada.");
    }


@Override
public void depositar(String cuentaId, double monto, double saldoAnterior, double saldoNuevo) throws Exception {
    // Leer los registros del archivo
    ArrayList<String[]> registros = accesoDatos.leerRegistros();
    boolean cuentaEncontrada = false;

    // Buscar la cuenta y actualizar el saldo
    for (String[] registro : registros) {
        if (registro[0].equals(cuentaId)) { // Comparar ID de cuenta
            saldoAnterior = Double.parseDouble(registro[2]); // Obtener el saldo actual
            saldoNuevo = saldoAnterior + monto; // Sumar el monto al saldo
            registro[2] = String.valueOf(saldoNuevo); // Actualizar el saldo en el registro
            cuentaEncontrada = true;
            break;
        }
    }

    if (!cuentaEncontrada) {
        throw new Exception("Cuenta no encontrada.");
    }

    // Sobrescribir el archivo con los registros actualizados
    accesoDatos.escribirRegistros(registros);

    // Registrar la transacción en el historial
    registrarTransaccion(cuentaId, monto, "Depósito");

    // Registrar el saldo anterior y el nuevo en el archivo de balance del usuario
    registrarBalance(cuentaId, saldoAnterior, saldoNuevo);
}


    @Override
    public void retirar(String cuentaId, double monto) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void transferir(String cuentaId, double monto, String tipoTransaccion) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void registrarTransaccion(String cuentaId, double monto, String tipoTransaccion) throws Exception {
            // Ruta del archivo donde se almacenan las transacciones
    String archivoHistorial = "Historial.txt";

    // Crear el formato de la línea de transacción
    String lineaTransaccion = cuentaId + "," + monto + "," + tipoTransaccion;

    // Utilizar la clase AccesoDatos para registrar la transacción
    AccesoDatos accesoDatos = new AccesoDatos();
    accesoDatos.setNombreArchivo(archivoHistorial);

    // Registrar la transacción en el archivo
    accesoDatos.agregarRegistro(lineaTransaccion);
    }

@Override
public void registrarBalance(String cuentaId, double saldoAnterior, double saldoNuevo) throws Exception {
    // Nombre del archivo de balance con el formato especificado
    String archivoBalance = cuentaId + "-Balance.txt";

    // Formato del balance que se agregará al archivo
    String lineaBalance = "Saldo anterior: " + saldoAnterior + "\nSaldo nuevo: " + saldoNuevo + "\n";

    // Crear el archivo de balance (en la ubicación por defecto del proyecto)
    File archivo = new File(archivoBalance);

    // Escribir la línea de balance en el archivo
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo, true))) {
        writer.write(lineaBalance);
        writer.newLine();
        System.out.println("Archivo de balance creado/actualizado: " + archivo.getAbsolutePath());
    } catch (IOException e) {
        System.err.println("Error al escribir el archivo de balance: " + e.getMessage());
        throw e;
    }
}


}
