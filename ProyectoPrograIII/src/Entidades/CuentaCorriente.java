/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

/**
 *
 * @author Joshua
 */
public class CuentaCorriente extends Cuenta {

    /**
     * Constructor por defecto.
     */
    public CuentaCorriente() {}

    /**
     * Constructor que inicializa la cuenta corriente con un número de cuenta y saldo inicial.
     *
     * @param numeroCuenta Número de la cuenta.
     * @param saldo Saldo inicial.
     */
    public CuentaCorriente(String numeroCuenta, double saldo) {
        super(numeroCuenta, saldo);
    }

    /**
     * Valida si el retiro es posible en una cuenta corriente.
     * En este ejemplo, se permite que el saldo quede negativo hasta un límite (sobregiro permitido).
     *
     * @param monto Monto a retirar.
     * @return {@code true} si el retiro es válido; {@code false} en caso contrario.
     */
    @Override
    public boolean validarRetiro(double monto) {
        double limiteSobregiro = -5000.0; // Sobregiro permitido hasta -5000
        return (getSaldo() - monto) >= limiteSobregiro;
    }
}
