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
 * La clase LogicaTransaccion gestiona las transacciones bancarias, como la
 * consulta de saldo, la adición y eliminación de saldo en cuentas y el registro
 * de las transacciones. Utiliza la clase AccesoDatos para la lectura y
 * escritura de registros y la clase Idcontrol para la gestión de los
 * identificadores únicos de transacciones.
 *
 * @autor dmsda
 */
public class LogicaTransaccion implements ServicioLogicaTransaccion {

    public  final AccesoDatos accesoDatos;

    public LogicaTransaccion() throws IOException {
        this.accesoDatos = new AccesoDatos(); // Inicializa el acceso a datos
        this.accesoDatos.nombreArchivo = "usuarios.txt";
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

        // Registrar el saldo anterior y el nuevo en el archivo de balance del usuario
        registrarBalance(cuentaId, saldoAnterior, saldoNuevo, "Deposito", monto);
    }

    @Override
    public void retirar(String cuentaId, double monto, double saldoAnterior, double saldoNuevo) throws Exception {
        // Leer los registros del archivo
        ArrayList<String[]> registros = accesoDatos.leerRegistros();
        boolean cuentaEncontrada = false;

        // Buscar la cuenta y actualizar el saldo
        for (String[] registro : registros) {
            if (registro[0].equals(cuentaId)) { // Comparar ID de cuenta
                saldoAnterior = Double.parseDouble(registro[2]); // Obtener el saldo actual
                saldoNuevo = saldoAnterior - monto; // Sumar el monto al saldo
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
        registrarBalance(cuentaId, saldoAnterior, saldoNuevo, "Retiro", monto);
    }

    @Override
    public void transferir(String cuentaOrigenId, String cuentaDestinoId, double monto, double saldoOrigen, double saldoDestino) throws Exception {
        // Leer los registros del archivo
        ArrayList<String[]> registros = accesoDatos.leerRegistros();
        boolean origenEncontrado = false;
        boolean destinoEncontrado = false;

        // Buscar las cuentas origen y destino y actualizar sus saldos
        for (String[] registro : registros) {
            if (registro[0].equals(cuentaOrigenId)) { // Cuenta de origen encontrada
                saldoOrigen = Double.parseDouble(registro[2]);
                if (saldoOrigen < monto) {
                    throw new Exception("Saldo insuficiente en la cuenta de origen.");
                }
                saldoOrigen -= monto; // Restar el monto al saldo de origen
                registro[2] = String.valueOf(saldoOrigen); // Actualizar el saldo
                origenEncontrado = true;
            } else if (registro[0].equals(cuentaDestinoId)) { // Cuenta de destino encontrada
                saldoDestino = Double.parseDouble(registro[2]);
                saldoDestino += monto; // Sumar el monto al saldo de destino
                registro[2] = String.valueOf(saldoDestino); // Actualizar el saldo
                destinoEncontrado = true;
            }

            // Si ambas cuentas ya fueron encontradas, no es necesario seguir buscando
            if (origenEncontrado && destinoEncontrado) {
                break;
            }
        }

        // Validar que ambas cuentas se encontraron
        if (!origenEncontrado) {
            throw new Exception("Cuenta de origen no encontrada.");
        }

        if (!destinoEncontrado) {
            throw new Exception("Cuenta de destino no encontrada.");
        }

        // Sobrescribir el archivo con los registros actualizados
        accesoDatos.escribirRegistros(registros);

        // Registrar balances
        registrarBalance(cuentaOrigenId, saldoOrigen + monto, saldoOrigen, "Transferencia envio", monto); // Saldo de origen
        registrarBalance(cuentaDestinoId, saldoDestino - monto, saldoDestino, "Transferencia recibe", monto); // Saldo de destino

    }

    @Override
    public void registrarBalance(String cuentaId, double saldoAnterior, double saldoNuevo, String movimiento, double monto) throws Exception {
        // Nombre del archivo de balance con el formato especificado
        String archivoBalance = cuentaId + "-Balance.txt";

        // Obtener la fecha actual con el formato deseado
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String fecha = sdf.format(new Date());

        // Formato del balance en una sola línea, agregando la fecha al inicio
        String lineaBalance = saldoAnterior + "," + saldoNuevo + "," + movimiento + ","+ monto + "," + fecha ;

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
                    registros.add(linea.split(",")); // Dividir la línea en columnas (usando coma como delimitador)
                }
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo de balance: " + e.getMessage());
            throw e;
        }

        // Devolver los registros como lista de arreglos
        return registros;
    }

}
