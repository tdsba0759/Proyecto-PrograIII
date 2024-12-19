package LogicaNegocio;

import AccesoDatos.AccesoDatos;
import Seguridad.LogicaEncriptacion;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * La clase {@code LogicaCuenta} gestiona tanto el proceso de inicio de sesión
 * como la creación y validación de cuentas de usuario. Proporciona métodos
 * para verificar si una cuenta existe, crear nuevas cuentas y validar las
 * credenciales de usuario.
 * 
 * <p>Esta clase interactúa con los archivos de almacenamiento de usuarios y
 * utiliza la clase {@code LogicaEncriptacion} para encriptar los PINs de las
 * cuentas de usuario.</p>
 * 
 */
public class LogicaCuenta implements Servicios.ServicioLogicaCuenta {

    public final AccesoDatos accesoDatos;

    /**
     * Constructor de la clase {@code LogicaCuenta}. Inicializa el objeto de
     * acceso a datos y establece el archivo de usuarios.
     */
    public LogicaCuenta() {
        this.accesoDatos = new AccesoDatos();
        this.accesoDatos.setNombreArchivo("usuarios.txt");
    }

    /**
     * Verifica si la cuenta existe en el archivo de datos.
     * 
     * @param numeroCuenta Número de la cuenta a verificar.
     * @return {@code true} si la cuenta existe, {@code false} en caso
     * contrario.
     * @throws IOException Si ocurre un error al acceder a los datos.
     */
    @Override
    public boolean existeCuenta(String numeroCuenta) throws IOException {
        ArrayList<String[]> registros = accesoDatos.leerRegistros();
        for (String[] registro : registros) {
            if (registro[0].equals(numeroCuenta)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Crea una nueva cuenta verificando previamente si ya existe y encriptando
     * la información. Si la cuenta ya existe, lanza una excepción
     * {@code IllegalArgumentException}.
     * 
     * @param numeroCuenta Número de la cuenta a crear.
     * @param nombreUsuario Nombre del usuario.
     * @param Saldo Saldo inicial de la cuenta.
     * @param pin PIN asociado a la cuenta.
     * @throws IOException Si ocurre un error al acceder a los datos.
     * @throws IllegalArgumentException Si el número de cuenta ya existe.
     * @throws Exception Si ocurre cualquier otro error durante la creación.
     */
    @Override
    public void crearNuevaCuenta(String numeroCuenta, String nombreUsuario, double Saldo, String pin) throws IOException, IllegalArgumentException, Exception {
        if (existeCuenta(numeroCuenta)) {
            throw new IllegalArgumentException("El número de cuenta ya existe.");
        }

        LogicaEncriptacion encriptacion = new LogicaEncriptacion();
        String pinEncriptado = encriptacion.encriptarPin(pin);

        // Crear el registro para la nueva cuenta
        String nuevaCuenta = numeroCuenta + "," + nombreUsuario + "," + Saldo + "," + pinEncriptado;

        // Guardar el registro en el archivo en modo anexado
        try (FileWriter writer = new FileWriter("usuarios.txt", true); 
             BufferedWriter bufferedWriter = new BufferedWriter(writer);
             PrintWriter printWriter = new PrintWriter(bufferedWriter)) {

            printWriter.println(nuevaCuenta);
        } catch (IOException e) {
            throw new IOException("Error al escribir en el archivo usuarios.txt: " + e.getMessage());
        }
    }

    /**
     * Valida las credenciales de usuario contra el archivo de datos. El PIN
     * ingresado por el usuario se encripta antes de realizar la comparación.
     * 
     * @param numeroCuenta Número de la cuenta a validar.
     * @param pin PIN ingresado por el usuario.
     * @return {@code true} si las credenciales son válidas, {@code false} en
     * caso contrario.
     * @throws IOException Si ocurre un error al acceder a los datos.
     * @throws Exception Si ocurre un error durante la validación de credenciales.
     */
    @Override
    public boolean validarCredenciales(String numeroCuenta, String pin) throws IOException, Exception {
        // Encriptar el PIN ingresado por el usuario utilizando LogicaEncriptacion
        LogicaEncriptacion logicaEncriptacion = new LogicaEncriptacion();
        String hashPin = logicaEncriptacion.encriptarPin(pin);

        // Leer usuarios y verificar credenciales
        ArrayList<String[]> usuarios = accesoDatos.leerRegistros();
        for (String[] usuario : usuarios) {
            if (usuario[0].equals(numeroCuenta) && usuario[3].equals(hashPin)) {
                return true; // Autenticación exitosa
            }
        }
        return false; // Credenciales incorrectas
    }
}
