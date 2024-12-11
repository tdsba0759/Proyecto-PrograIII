package LogicaNegocio;

import AccesoDatos.AccesoDatos;
import Seguridad.LogicaEncriptacion;
import java.io.IOException;
import java.util.ArrayList;

public class LogicaCuenta implements Servicios.ServicioLogicaCuenta {

    private AccesoDatos accesoDatos;

    // Constructor
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
        // Leer los registros
        ArrayList<String[]> registros = accesoDatos.leerRegistros();

        // Buscar la cuenta en los registros
        for (String[] registro : registros) {
            if (registro[0].equals(numeroCuenta)) {
                return true; // Cuenta encontrada
            }
        }

        return false; // Cuenta no encontrada
    }

    /**
     * Crea una nueva cuenta verificando previamente si ya existe y encriptando
     * la información.
     *
     * @param numeroCuenta Número de la cuenta a crear.
     * @param saldo Saldo inicial de la cuenta.
     * @param pin PIN asociado a la cuenta.
     * @throws IOException Si ocurre un error al acceder a los datos.
     */
    @Override   
    public void crearNuevaCuenta(String numeroCuenta,String Nombre, double saldo, String pin) throws IOException {
        // Verifica si el número de cuenta ya existe
        if (existeCuenta(numeroCuenta)) {
            throw new IllegalArgumentException("El número de cuenta ya existe.");
        }

        try {
            // Lógica para encriptar el PIN
            LogicaEncriptacion encriptacion = new LogicaEncriptacion();
            String pinEncriptado = encriptacion.encriptarPin(pin);

            // Formatea la información de la nueva cuenta
            String nuevaCuenta = numeroCuenta + "," + Nombre + "," + saldo + "," + pinEncriptado;

            // Escribe la información de la nueva cuenta en el archivo
            accesoDatos.agregarRegistro(nuevaCuenta);

            // Mensaje de confirmación en consola (para depuración, cambiar por GUI si es necesario)
            System.out.println("Nueva cuenta creada exitosamente: " + numeroCuenta);
        } catch (Exception e) {
            // Lanza la excepción con un mensaje personalizado si ocurre un error
            throw new IOException("Error al crear la nueva cuenta: " + e.getMessage(), e);
        }
    }

 

}
