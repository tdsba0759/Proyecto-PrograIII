
package Entidades;

/**
 *
 * @author Joshua
 */
public abstract class Cuenta {
    
    private String numeroCuenta;
    private double saldo;

    public Cuenta() {}

    public Cuenta(String numeroCuenta, double saldo) {
        this.numeroCuenta = numeroCuenta;
        this.saldo = saldo;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public abstract boolean validarRetiro(double monto);
    
}
