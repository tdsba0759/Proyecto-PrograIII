package LogicaNegocio;

import Entidades.Transaccion;
import AccesoDatos.AccesoDatos;
import AccesoDatos.Idcontrol;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

/**
 * La clase LogicaTransaccion gestiona las transacciones bancarias, como la
 * consulta de saldo, la adición y eliminación de saldo en cuentas y el registro
 * de las transacciones. Utiliza la clase AccesoDatos para la lectura y
 * escritura de registros y la clase Idcontrol para la gestión de los
 * identificadores únicos de transacciones.
 *
 * @autor dmsda
 */
public class LogicaTransaccion {

    private final AccesoDatos accesoDatos;
    private final Idcontrol idControl;

    /**
     * Constructor de la clase LogicaTransaccion. Inicializa los objetos de
     * acceso a datos y control de IDs.
     *
     * @throws IOException Si ocurre un error al inicializar los objetos de
     * acceso a datos.
     */
    public LogicaTransaccion() throws IOException {
        accesoDatos = new AccesoDatos();
        idControl = new Idcontrol("cuentas.txt");
    }

    /**
     * Consulta el saldo actual de una cuenta bancaria.
     *
     * @param cuentaId El identificador único de la cuenta.
     * @return El saldo actual de la cuenta.
     * @throws Exception Si no se encuentra la cuenta.
     */
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
     * Agrega un monto de dinero al saldo de una cuenta bancaria.
     *
     * @param cuentaId El identificador único de la cuenta.
     * @param monto El monto a agregar a la cuenta.
     * @throws Exception Si no se encuentra la cuenta o si ocurre algún otro
     * error.
     */
    public void agregarSaldo(String cuentaId, double monto) throws Exception {
        ArrayList<String[]> registros = accesoDatos.leerRegistros();
        boolean cuentaEncontrada = false;

        // Recorremos los registros solo una vez
        for (String[] registro : registros) {
            if (registro[0].equals(cuentaId)) {
                double saldoActual = Double.parseDouble(registro[2]);
                double nuevoSaldo = saldoActual + monto;
                registro[2] = String.valueOf(nuevoSaldo); // Actualiza el saldo en el registro
                cuentaEncontrada = true;
                break;
            }
        }

        if (cuentaEncontrada) {
            // Escribimos solo el registro modificado, sin necesidad de recorrer todos los registros
            // Reescribimos el archivo solo si ha habido un cambio en los registros
            accesoDatos.agregarRegistro(String.join(",", registros.get(registros.indexOf(registros.stream()
                    .filter(reg -> reg[0].equals(cuentaId))
                    .findFirst()
                    .get())))); // Escribimos el nuevo registro modificado

            // Registramos la transacción en el historial
            registrarHistorial(cuentaId, monto, "Ingreso");
        } else {
            throw new Exception("Cuenta no encontrada.");
        }
    }

    /**
     * Quita un monto de dinero del saldo de una cuenta bancaria (como un
     * retiro).
     *
     * @param cuentaId El identificador único de la cuenta.
     * @param monto El monto a retirar de la cuenta.
     * @throws Exception Si no se encuentra la cuenta o si el saldo es
     * insuficiente.
     */
    public void quitarSaldo(String cuentaId, double monto) throws Exception {
        ArrayList<String[]> registros = accesoDatos.leerRegistros();
        boolean cuentaEncontrada = false;

        for (String[] registro : registros) {
            if (registro[0].equals(cuentaId)) {
                double saldoActual = Double.parseDouble(registro[2]);
                if (saldoActual >= monto) {
                    double nuevoSaldo = saldoActual - monto;
                    registro[2] = String.valueOf(nuevoSaldo); // Actualiza el saldo en el registro.
                    cuentaEncontrada = true;
                } else {
                    throw new Exception("Saldo insuficiente.");
                }
                break;
            }
        }

        if (cuentaEncontrada) {
            for (String[] registro : registros) {
                accesoDatos.agregarRegistro(String.join(",", registro));
            }

            registrarHistorial(cuentaId, monto, "Retiro");
        } else {
            throw new Exception("Cuenta no encontrada.");
        }
    }

    /**
     * Transfiere un monto de una cuenta a otra.
     *
     * @param cuentaOrigenId El identificador único de la cuenta de origen.
     * @param cuentaDestinoId El identificador único de la cuenta de destino.
     * @param monto El monto a transferir.
     * @throws Exception Si no se encuentran las cuentas, el saldo es
     * insuficiente, o si ocurre algún otro error.
     */
    public void transferirSaldo(String cuentaOrigenId, String cuentaDestinoId, double monto) throws Exception {
        ArrayList<String[]> registros = accesoDatos.leerRegistros();
        boolean cuentaOrigenEncontrada = false;
        boolean cuentaDestinoEncontrada = false;

        // Verificamos que ambas cuentas existan.
        for (String[] registro : registros) {
            if (registro[0].equals(cuentaOrigenId)) {
                cuentaOrigenEncontrada = true;
                double saldoActual = Double.parseDouble(registro[2]);
                if (saldoActual < monto) {
                    throw new Exception("Saldo insuficiente en la cuenta de origen.");
                }
            }
            if (registro[0].equals(cuentaDestinoId)) {
                cuentaDestinoEncontrada = true;
            }
        }

        // Lanzamos excepciones si alguna de las cuentas no existe.
        if (!cuentaOrigenEncontrada) {
            throw new Exception("Cuenta de origen no encontrada.");
        }
        if (!cuentaDestinoEncontrada) {
            throw new Exception("Cuenta de destino no encontrada.");
        }

        // Realizamos las actualizaciones de saldo.
        for (String[] registro : registros) {
            if (registro[0].equals(cuentaOrigenId)) {
                double saldoActual = Double.parseDouble(registro[2]);
                double nuevoSaldo = saldoActual - monto;
                registro[2] = String.valueOf(nuevoSaldo); // Actualiza el saldo en la cuenta de origen.
            }
            if (registro[0].equals(cuentaDestinoId)) {
                double saldoActual = Double.parseDouble(registro[2]);
                double nuevoSaldo = saldoActual + monto;
                registro[2] = String.valueOf(nuevoSaldo); // Actualiza el saldo en la cuenta de destino.
            }
        }

        // Guardamos los registros actualizados.
        for (String[] registro : registros) {
            accesoDatos.agregarRegistro(String.join(",", registro));
        }

        // Registramos las transacciones correspondientes.
        registrarHistorial(cuentaOrigenId, monto, "Transferencia Enviada");
        registrarHistorial(cuentaDestinoId, monto, "Transferencia Recibida");
    }

    /**
     * Registra una transacción en el archivo de transacciones.
     *
     * @param cuentaId El ID de la cuenta relacionada con la transacción.
     * @param monto El monto de la transacción.
     * @param tipoTransaccion El tipo de transacción (por ejemplo, "depósito" o
     * "retiro").
     * @throws Exception Si ocurre algún error al registrar la transacción.
     */
    private void registrarHistorial(String cuentaId, double monto, String tipoTransaccion) throws Exception {
        // Generamos un ID único para la transacción utilizando el control de IDs.
        int HistorialId = idControl.getNextId("transacciones");

        // Creamos la transacción con los datos proporcionados.
        Transaccion transaccion = new Transaccion();
        transaccion.setTipoTransaccion(tipoTransaccion);
        transaccion.setMonto(monto);
        transaccion.setFecha(new Date());

        // Creamos la línea que representa la transacción a ser guardada en el archivo.
        String linea = HistorialId + "," + cuentaId + "," + transaccion.getTipoTransaccion() + "," + transaccion.getMonto() + "," + transaccion.getFecha();

        // Definimos el nombre del archivo de transacciones.
        String archivoHistorial = "Historial.txt";

        // Creamos la instancia de AccesoDatos y le pasamos el archivo para realizar la operación de escritura.
        AccesoDatos accesoDatosTransacciones = new AccesoDatos();
        accesoDatosTransacciones.setNombreArchivo(archivoHistorial); // Establecemos el archivo donde se guardará la transacción.
        accesoDatosTransacciones.agregarRegistro(linea); // Registramos la transacción en el archivo.

    }

    /**
     * Consulta el historial de transacciones de una cuenta bancaria.
     *
     * @param cuentaId El identificador único de la cuenta.
     * @return Una lista de transacciones asociadas a la cuenta.
     * @throws Exception Si no se encuentra el historial de transacciones o si
     * ocurre un error de lectura.
     */
    public ArrayList<String[]> consultarHistorial(String cuentaId) throws Exception {
        // Crear instancia de AccesoDatos y establecer el archivo de historial
        AccesoDatos accesoDatos = new AccesoDatos();
        accesoDatos.setNombreArchivo("Historial.txt");  // Establecer el archivo de historial

        // Leer los registros del archivo
        ArrayList<String[]> registros = accesoDatos.leerRegistros();

        // Filtrar las transacciones asociadas a la cuentaId proporcionada
        ArrayList<String[]> historialCuenta = new ArrayList<>();
        for (String[] registro : registros) {
            if (registro[0].equals(cuentaId)) {
                historialCuenta.add(registro);  // Agregar la transacción al historial de la cuenta
            }
        }

        // Si no se encontraron transacciones para la cuenta
        if (historialCuenta.isEmpty()) {
            throw new Exception("No se encontraron transacciones para esta cuenta.");
        }

        // Retornar el historial de transacciones de la cuenta
        return historialCuenta;
    }

}
