package Servicios;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Interfaz que define los métodos necesarios para realizar operaciones sobre
 * las transacciones de una cuenta, incluyendo consultar el saldo, agregar o
 * quitar saldo y registrar una transacción. Esta interfaz permite manejar las
 * transacciones de manera segura y eficiente.
 *
 *
 */
public interface ServicioLogicaTransaccion {

    /**
     * Consulta el saldo de una cuenta específica.
     *
     * @param cuentaId El identificador de la cuenta cuyo saldo se desea
     * consultar.
     * @return El saldo actual de la cuenta.
     * @throws Exception Si ocurre un error al consultar el saldo.
     */
    double consultarSaldo(String cuentaId) throws Exception;

    /**
     * Agrega un monto al saldo de una cuenta específica.
     *
     * @param cuentaId El identificador de la cuenta a la que se agregará el
     * saldo.
     * @param monto El monto a agregar.
     * @param saldoAnterior
     * @param saldoNuevo
     * @throws Exception Si ocurre un error al agregar el saldo.
     */
    void depositar(String cuentaId, double monto, double saldoAnterior, double saldoNuevo) throws Exception;

    /**
     * Registra una transacción realizada en una cuenta, indicando el tipo de
     * transacción (por ejemplo, ingreso o retiro).
     *
     * @param cuentaId El identificador de la cuenta en la que se realiza la
     * transacción.
     * @param monto El monto de la transacción.
     * @param saldoAnterior
     * @param saldoNuevo
     * @throws Exception Si ocurre un error al registrar la transacción.
     */
    void retirar(String cuentaId, double monto, double saldoAnterior, double saldoNuevo) throws Exception;

    void transferir(String cuentaOrigenId, String cuentaDestinoId, double monto, double saldoOrigen, double saldoDestino) throws Exception;

    /**
     * Registra el balance actual de una cuenta en un archivo específico del
     * usuario.
     *
     * @param cuentaId El identificador de la cuenta.
     * @param saldoAnterior El saldo anterior de la cuenta.
     * @param saldoNuevo El saldo nuevo de la cuenta después de la operación.
     * @throws Exception Si ocurre un error al registrar el balance.
     */
    void registrarBalance(String cuentaId, double saldoAnterior, double saldoNuevo, String movimiento, double monto) throws Exception;

    ArrayList<String[]> leerBalance(String cuentaId) throws IOException;
}
