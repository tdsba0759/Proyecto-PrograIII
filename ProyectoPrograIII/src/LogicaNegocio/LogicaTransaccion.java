package LogicaNegocio;

import java.util.ArrayList;
import AccesoDatos.AccesoDatos;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import Servicios.ServicioLogicaTransaccion;
import java.io.BufferedReader;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * La clase {@code LogicaTransaccion} gestiona las transacciones bancarias, como la
 * consulta de saldo, la adición y eliminación de saldo en cuentas y el registro
 * de las transacciones. Utiliza la clase {@code AccesoDatos} para la lectura y
 * escritura de registros y la clase {@code Idcontrol} para la gestión de los
 * identificadores únicos de transacciones.
 * 
 * <p>Esta clase implementa los métodos necesarios para realizar depósitos, retiros, 
 * consultas de saldo y transferencias entre cuentas. También permite registrar el 
 * historial de las transacciones en archivos específicos de cada cuenta.</p>
 * 
 */
public class LogicaTransaccion implements ServicioLogicaTransaccion {

    /**
     * Objeto para manejar el acceso a los datos de los usuarios.
     */
    public final AccesoDatos accesoDatos;

    /**
     * Constructor de la clase {@code LogicaTransaccion}. Inicializa el acceso a los
     * datos y configura el nombre del archivo donde se almacenan los usuarios.
     *
     * @throws IOException si ocurre un error al inicializar el acceso a datos.
     */
    public LogicaTransaccion() throws IOException {
        this.accesoDatos = new AccesoDatos();
        this.accesoDatos.nombreArchivo = "usuarios.txt";
    }

    /**
     * Consulta el saldo de una cuenta específica.
     *
     * @param cuentaId ID de la cuenta cuyo saldo se desea consultar.
     * @return el saldo actual de la cuenta.
     * @throws Exception si la cuenta no se encuentra.
     */
    @Override
    public double consultarSaldo(String cuentaId) throws Exception {
        ArrayList<String[]> registros = accesoDatos.leerRegistros();
        for (String[] registro : registros) {
            if (registro[0].equals(cuentaId)) {
                return Double.parseDouble(registro[2]);
            }
        }
        throw new Exception("Cuenta no encontrada.");
    }

    /**
     * Realiza un depósito en una cuenta específica.
     *
     * @param cuentaId ID de la cuenta donde se realizará el depósito.
     * @param monto Monto a depositar.
     * @param saldoAnterior Saldo previo antes del depósito.
     * @param saldoNuevo Saldo actualizado después del depósito.
     * @throws Exception si la cuenta no se encuentra.
     */
    @Override
    public void depositar(String cuentaId, double monto, double saldoAnterior, double saldoNuevo) throws Exception {
        ArrayList<String[]> registros = accesoDatos.leerRegistros();
        boolean cuentaEncontrada = false;
        for (String[] registro : registros) {
            if (registro[0].equals(cuentaId)) {
                saldoAnterior = Double.parseDouble(registro[2]);
                saldoNuevo = saldoAnterior + monto;
                registro[2] = String.valueOf(saldoNuevo);
                cuentaEncontrada = true;
                break;
            }
        }
        if (!cuentaEncontrada) {
            throw new Exception("Cuenta no encontrada.");
        }
        accesoDatos.escribirRegistros(registros);
        registrarBalance(cuentaId, saldoAnterior, saldoNuevo, "Deposito", monto);
    }

    /**
     * Realiza un retiro en una cuenta específica.
     *
     * @param cuentaId ID de la cuenta de donde se realizará el retiro.
     * @param monto Monto a retirar.
     * @param saldoAnterior Saldo previo antes del retiro.
     * @param saldoNuevo Saldo actualizado después del retiro.
     * @throws Exception si la cuenta no se encuentra o el saldo es
     * insuficiente.
     */
    @Override
    public void retirar(String cuentaId, double monto, double saldoAnterior, double saldoNuevo) throws Exception {
        ArrayList<String[]> registros = accesoDatos.leerRegistros();
        boolean cuentaEncontrada = false;
        for (String[] registro : registros) {
            if (registro[0].equals(cuentaId)) {
                saldoAnterior = Double.parseDouble(registro[2]);
                saldoNuevo = saldoAnterior - monto;
                registro[2] = String.valueOf(saldoNuevo);
                cuentaEncontrada = true;
                break;
            }
        }
        if (!cuentaEncontrada) {
            throw new Exception("Cuenta no encontrada.");
        }
        accesoDatos.escribirRegistros(registros);
        registrarBalance(cuentaId, saldoAnterior, saldoNuevo, "Retiro", monto);
    }

    /**
     * Realiza una transferencia entre dos cuentas.
     *
     * @param cuentaOrigenId ID de la cuenta desde donde se realizará la transferencia.
     * @param cuentaDestinoId ID de la cuenta que recibirá la transferencia.
     * @param monto Monto a transferir.
     * @param saldoOrigen Saldo actualizado de la cuenta de origen.
     * @param saldoDestino Saldo actualizado de la cuenta de destino.
     * @throws Exception si alguna de las cuentas no se encuentra o el saldo es
     * insuficiente.
     */
    @Override
    public void transferir(String cuentaOrigenId, String cuentaDestinoId, double monto, double saldoOrigen, double saldoDestino) throws Exception {
        ArrayList<String[]> registros = accesoDatos.leerRegistros();
        boolean origenEncontrado = false;
        boolean destinoEncontrado = false;
        for (String[] registro : registros) {
            if (registro[0].equals(cuentaOrigenId)) {
                saldoOrigen = Double.parseDouble(registro[2]);
                if (saldoOrigen < monto) {
                    throw new Exception("Saldo insuficiente en la cuenta de origen.");
                }
                saldoOrigen -= monto;
                registro[2] = String.valueOf(saldoOrigen);
                origenEncontrado = true;
            } else if (registro[0].equals(cuentaDestinoId)) {
                saldoDestino = Double.parseDouble(registro[2]);
                saldoDestino += monto;
                registro[2] = String.valueOf(saldoDestino);
                destinoEncontrado = true;
            }
            if (origenEncontrado && destinoEncontrado) {
                break;
            }
        }
        if (!origenEncontrado) {
            throw new Exception("Cuenta de origen no encontrada.");
        }
        if (!destinoEncontrado) {
            throw new Exception("Cuenta de destino no encontrada.");
        }
        accesoDatos.escribirRegistros(registros);
        registrarBalance(cuentaOrigenId, saldoOrigen + monto, saldoOrigen, "Transferencia envio", monto);
        registrarBalance(cuentaDestinoId, saldoDestino - monto, saldoDestino, "Transferencia recibe", monto);
    }

    /**
     * Registra un balance en el archivo asociado a una cuenta.
     *
     * @param cuentaId ID de la cuenta a la que se asocia el balance.
     * @param saldoAnterior Saldo antes de la operación.
     * @param saldoNuevo Saldo después de la operación.
     * @param movimiento Tipo de movimiento realizado (ej. Deposito, Retiro, etc.).
     * @param monto Monto de la operación.
     * @throws Exception si ocurre un error al escribir en el archivo.
     */
    @Override
    public void registrarBalance(String cuentaId, double saldoAnterior, double saldoNuevo, String movimiento, double monto) throws Exception {
        String archivoBalance = cuentaId + "-Balance.txt";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String fecha = sdf.format(new Date());
        String lineaBalance = saldoAnterior + "," + saldoNuevo + "," + movimiento + "," + monto + "," + fecha;
        File archivo = new File(archivoBalance);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo, true))) {
            writer.write(lineaBalance);
            writer.newLine();
            System.out.println("Archivo de balance creado/actualizado: " + archivo.getAbsolutePath());
        } catch (IOException e) {
            System.err.println("Error al escribir el archivo de balance: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Lee los balances registrados en el archivo asociado a una cuenta.
     *
     * @param cuentaId ID de la cuenta cuyo balance se desea leer.
     * @return una lista de arreglos con los registros del balance.
     * @throws IOException si ocurre un error al leer el archivo o el archivo no
     * existe.
     */
    @Override
    public ArrayList<String[]> leerBalance(String cuentaId) throws IOException {
        // Nombre del archivo de balance con el formato especificado
        String archivoBalance = cuentaId + "-Balance.txt";

        // Lista para almacenar los registros leídos
        ArrayList<String[]> registros = new ArrayList<>();

        // Crear el archivo para verificar su existencia
        File archivo = new File(archivoBalance);
        if (!archivo.exists()) {
            throw new IOException("No se encontraron movimientos para esta cuenta.");
        }

        // Leer el archivo línea por línea
        try (BufferedReader reader = new BufferedReader(new FileReader(archivoBalance))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                linea = linea.trim();
                if (!linea.isEmpty()) { // Evitar procesar líneas vacías
                    registros.add(linea.split(","));
                }
            }
        }

        return registros;
    }
}
