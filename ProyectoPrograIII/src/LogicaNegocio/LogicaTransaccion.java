package LogicaNegocio;

import Entidades.Transaccion;
import AccesoDatos.AccesoDatos;
import AccesoDatos.Idcontrol;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

/**
 * La clase LogicaTransaccion gestiona las transacciones bancarias, como la consulta de saldo,
 * la adición y eliminación de saldo en cuentas y el registro de las transacciones.
 * Utiliza la clase AccesoDatos para la lectura y escritura de registros y la clase Idcontrol 
 * para la gestión de los identificadores únicos de transacciones.
 * 
 * @author dmsda
 * 
 */
public class LogicaTransaccion {
    
    private final AccesoDatos accesoDatos;
    private final Idcontrol idControl;

    /**
     * Constructor de la clase LogicaTransaccion.
     * Inicializa los objetos de acceso a datos y control de IDs.
     * 
     * @throws IOException Si ocurre un error al inicializar los objetos de acceso a datos.
     */
    public LogicaTransaccion() throws IOException {
        accesoDatos = new AccesoDatos();
        idControl = new Idcontrol("controlIds.txt");
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
                return Double.parseDouble(registro[2]); // Suponiendo que el saldo está en la posición 2
            }
        }
        
        throw new Exception("Cuenta no encontrada.");
    }

    /**
     * Agrega un monto de dinero al saldo de una cuenta bancaria.
     * 
     * @param cuentaId El identificador único de la cuenta.
     * @param monto El monto a agregar a la cuenta.
     * @throws Exception Si no se encuentra la cuenta o si ocurre algún otro error.
     */
    public void agregarSaldo(String cuentaId, double monto) throws Exception {
        ArrayList<String[]> registros = accesoDatos.leerRegistros();
        boolean cuentaEncontrada = false;

        for (String[] registro : registros) {
            if (registro[0].equals(cuentaId)) {
                double saldoActual = Double.parseDouble(registro[2]);
                double nuevoSaldo = saldoActual + monto;
                registro[2] = String.valueOf(nuevoSaldo); // Actualiza el saldo en el registro.
                cuentaEncontrada = true;
                break;
            }
        }

        if (cuentaEncontrada) {
            for (String[] registro : registros) {
                accesoDatos.agregarRegistro(String.join(",", registro));
            }

            registrarTransaccion(cuentaId, monto, "Ingreso");
        } else {
            throw new Exception("Cuenta no encontrada.");
        }
    }

    /**
     * Quita un monto de dinero del saldo de una cuenta bancaria (como un retiro).
     * 
     * @param cuentaId El identificador único de la cuenta.
     * @param monto El monto a retirar de la cuenta.
     * @throws Exception Si no se encuentra la cuenta o si el saldo es insuficiente.
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

            registrarTransaccion(cuentaId, monto, "Retiro");
        } else {
            throw new Exception("Cuenta no encontrada.");
        }
    }

    /**
     * Registra una transacción en el archivo de registros de transacciones.
     * 
     * @param cuentaId El identificador único de la cuenta.
     * @param monto El monto de la transacción.
     * @param tipoTransaccion El tipo de transacción (por ejemplo, "Ingreso" o "Retiro").
     * @throws Exception Si ocurre un error al registrar la transacción.
     */
    private void registrarTransaccion(String cuentaId, double monto, String tipoTransaccion) throws Exception {
        int transaccionId = idControl.getNextId("transacciones");

        Transaccion transaccion = new Transaccion();
        transaccion.setTipoTransaccion(tipoTransaccion);
        transaccion.setMonto(monto);
        transaccion.setFecha(new Date());
        
        String linea = transaccionId + "," + cuentaId + "," + transaccion.getTipoTransaccion() + "," + transaccion.getMonto() + "," + transaccion.getFecha();
        
        accesoDatos.agregarRegistro(linea);
    }
}
