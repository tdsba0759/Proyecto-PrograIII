package Servicios;

/**
 * Interfaz que define los métodos necesarios para realizar operaciones sobre las transacciones de una cuenta,
 * incluyendo consultar el saldo, agregar o quitar saldo y registrar una transacción.
 * Esta interfaz permite manejar las transacciones de manera segura y eficiente.
 * 
 * @author dmsda
 * 
 */
public interface ServicioLogicaTransaccion {
    
    /**
     * Consulta el saldo de una cuenta específica.
     * 
     * @param cuentaId El identificador de la cuenta cuyo saldo se desea consultar.
     * @return El saldo actual de la cuenta.
     * @throws Exception Si ocurre un error al consultar el saldo.
     */
    double consultarSaldo(String cuentaId) throws Exception;

    /**
     * Agrega un monto al saldo de una cuenta específica.
     * 
     * @param cuentaId El identificador de la cuenta a la que se agregará el saldo.
     * @param monto El monto a agregar.
     * @throws Exception Si ocurre un error al agregar el saldo.
     */
    void agregarSaldo(String cuentaId, double monto) throws Exception;

    /**
     * Quita un monto del saldo de una cuenta específica.
     * 
     * @param cuentaId El identificador de la cuenta de la cual se quitará el saldo.
     * @param monto El monto a retirar.
     * @throws Exception Si ocurre un error al quitar el saldo.
     */
    void quitarSaldo(String cuentaId, double monto) throws Exception;

    /**
     * Registra una transacción realizada en una cuenta, indicando el tipo de transacción (por ejemplo, ingreso o retiro).
     * 
     * @param cuentaId El identificador de la cuenta en la que se realiza la transacción.
     * @param monto El monto de la transacción.
     * @param tipoTransaccion El tipo de transacción (por ejemplo, "Ingreso" o "Retiro").
     * @throws Exception Si ocurre un error al registrar la transacción.
     */
    void registrarTransaccion(String cuentaId, double monto, String tipoTransaccion) throws Exception;
}
