package Servicios;

import java.io.IOException;

/**
 * Interfaz que define los servicios relacionados con la lógica de las cuentas bancarias.
 * Incluye métodos para verificar la existencia de una cuenta y crear una nueva cuenta.
 * 
 */
public interface ServicioLogicaCuenta {

    /**
     * Verifica si una cuenta con el número especificado existe.
     * 
     * @param numeroCuenta El número de la cuenta a verificar.
     * @return {@code true} si la cuenta existe, {@code false} si no existe.
     * @throws IOException Si ocurre un error al acceder a los datos.
     */
    boolean existeCuenta(String numeroCuenta) throws IOException;

    /**
     * Crea una nueva cuenta con el número de cuenta y saldo proporcionados.
     * 
     * @param numeroCuenta El número de la nueva cuenta.
     * @param nombreUsuario
     * @param Saldo
     * @param pin
     * @throws IOException Si ocurre un error al crear la cuenta o acceder a los datos.
     */
    void crearNuevaCuenta(String numeroCuenta,String nombreUsuario,double Saldo, String pin) throws IOException, IllegalArgumentException, Exception;

    /**
     * Valida las credenciales de un usuario comparando el nombre de usuario y la contraseña proporcionada.
     * 
     * @param numeroCuenta
     * @param pin
     * @return true si las credenciales son válidas, false en caso contrario.
     * @throws java.io.IOException
     */
     boolean validarCredenciales(String numeroCuenta, String pin) throws IOException, Exception;
}
