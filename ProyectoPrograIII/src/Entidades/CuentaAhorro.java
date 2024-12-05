/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

/**
 *
 * @author Joshua
 */
public class CuentaAhorro extends Cuenta {

    /**
     * Constructor por defecto.
     */
    public CuentaAhorro() {}

    /**
     * Constructor que inicializa la cuenta de ahorro con un número de cuenta y saldo inicial.
     *
     * @param numeroCuenta Número de la cuenta.
     * @param saldo Saldo inicial.
     */
    public CuentaAhorro(String numeroCuenta, double saldo) {
        super(numeroCuenta, saldo);
    }

    /**
     * Valida si el retiro es posible en una cuenta de ahorro.
     * En este ejemplo, un retiro es válido si el saldo no queda negativo.
     *
     * @param monto Monto a retirar.
     * @return {@code true} si el retiro es válido; {@code false} en caso contrario.
     */
    @Override
    public boolean validarRetiro(double monto) {
        return getSaldo() >= monto; // El saldo debe ser mayor o igual al monto a retirar.
    }
}
