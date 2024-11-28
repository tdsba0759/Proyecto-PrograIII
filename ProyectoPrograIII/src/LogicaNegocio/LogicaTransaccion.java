package LogicaNegocio;

import Entidades.Transaccion;
import AccesoDatos.AccesoDatos;
import AccesoDatos.Idcontrol;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class LogicaTransaccion {
    
    private final AccesoDatos accesoDatos;
    private final Idcontrol idControl;

    public LogicaTransaccion() throws IOException {
        // Inicializa los objetos de acceso a datos y control de IDs.
        accesoDatos = new AccesoDatos();
        idControl = new Idcontrol("controlIds.txt"); // El archivo "controlIds.txt" se usará para gestionar los IDs.
    }

    // Consulta el saldo actual de un usuario (este método depende de cómo se maneje el saldo en el sistema).
    public double consultarSaldo(String cuentaId) throws Exception {
        ArrayList<String[]> registros = accesoDatos.leerRegistros();
        
        // Iterar sobre los registros para buscar la cuenta y obtener el saldo
        for (String[] registro : registros) {
            if (registro[0].equals(cuentaId)) {
                return Double.parseDouble(registro[2]); // Suponiendo que el saldo está en la posición 2
            }
        }
        
        throw new Exception("Cuenta no encontrada.");
    }

    // Agrega saldo a una cuenta (suponiendo que cada cuenta tiene un identificador único).
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
            // Escribe los registros actualizados en el archivo.
            for (String[] registro : registros) {
                accesoDatos.agregarRegistro(String.join(",", registro));
            }

            // Registra la transacción.
            registrarTransaccion(cuentaId, monto, "Ingreso");
        } else {
            throw new Exception("Cuenta no encontrada.");
        }
    }

    // Quita saldo de una cuenta (como un retiro).
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
            // Escribe los registros actualizados en el archivo.
            for (String[] registro : registros) {
                accesoDatos.agregarRegistro(String.join(",", registro));
            }

            // Registra la transacción.
            registrarTransaccion(cuentaId, monto, "Retiro");
        } else {
            throw new Exception("Cuenta no encontrada.");
        }
    }

    // Registra una transacción en el archivo de registros (por ejemplo, con tipo de transacción: Ingreso, Retiro).
    private void registrarTransaccion(String cuentaId, double monto, String tipoTransaccion) throws Exception {
        // Obtener el siguiente ID para la transacción utilizando el control de IDs.
        int transaccionId = idControl.getNextId("transacciones");

        // Crear una nueva transacción con un ID único.
        Transaccion transaccion = new Transaccion();
        transaccion.setTipoTransaccion(tipoTransaccion);
        transaccion.setMonto(monto);
        transaccion.setFecha(new Date());
        
        // Formatear la línea para agregarla al archivo de transacciones.
        String linea = transaccionId + "," + cuentaId + "," + transaccion.getTipoTransaccion() + "," + transaccion.getMonto() + "," + transaccion.getFecha();
        
        // Guardar la transacción en el archivo.
        accesoDatos.agregarRegistro(linea);
    }
}
