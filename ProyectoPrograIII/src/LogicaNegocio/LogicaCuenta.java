package LogicaNegocio;

import AccesoDatos.AccesoDatos;
import java.io.IOException;
import java.util.ArrayList;

public class LogicaCuenta {

    private AccesoDatos accesoDatos;

    // Constructor
    public LogicaCuenta() {
        this.accesoDatos = new AccesoDatos();
    }

    /**
     * Verifica si la cuenta existe en el archivo de datos.
     * 
     * @param numeroCuenta Número de la cuenta a verificar.
     * @return {@code true} si la cuenta existe, {@code false} en caso contrario.
     * @throws IOException Si ocurre un error al acceder a los datos.
     */
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
     * Método privado para crear una nueva cuenta.
     * Este método debe delegar la creación a la clase correspondiente.
     * 
     * @param numeroCuenta Número de la cuenta.
     * @param saldo Saldo inicial de la cuenta.
     * @throws IOException Si ocurre un error al acceder a los datos.
     */
    private void crearNuevaCuenta(String numeroCuenta, double saldo) throws IOException {
        // Lógica para crear una nueva cuenta (delegada a otra clase o servicio)
        System.out.println("Creando nueva cuenta con número: " + numeroCuenta + " y saldo inicial: " + saldo);
        // Este código puede invocar el servicio correspondiente para crear la cuenta en otra parte.
    }
}
