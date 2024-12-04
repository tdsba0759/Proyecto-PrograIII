package Entidades;

/**
 * Clase abstracta que representa una cuenta bancaria con un número de cuenta
 * y un saldo. Proporciona métodos para obtener y establecer estos valores,
 * así como un método abstracto para validar retiros.
 * 
 * @author Joshua
 */
public abstract class Cuenta {

    /**
     * Número único que identifica la cuenta.
     */
    private String numeroCuenta;

    /**
     * Saldo actual de la cuenta.
     */
    private double saldo;

    /**
     * Constructor por defecto para la clase {@code Cuenta}.
     */
    public Cuenta() {}

    /**
     * Constructor que inicializa la cuenta con un número y un saldo inicial.
     *
     * @param numeroCuenta Número de la cuenta.
     * @param saldo Saldo inicial de la cuenta.
     */
    public Cuenta(String numeroCuenta, double saldo) {
        this.numeroCuenta = numeroCuenta;
        this.saldo = saldo;
    }

    /**
     * Obtiene el número de cuenta.
     *
     * @return El número de la cuenta.
     */
    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    /**
     * Establece el número de cuenta.
     *
     * @param numeroCuenta El número de cuenta a asignar.
     */
    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    /**
     * Obtiene el saldo actual de la cuenta.
     *
     * @return El saldo actual.
     */
    public double getSaldo() {
        return saldo;
    }

    /**
     * Establece el saldo de la cuenta.
     *
     * @param saldo El saldo a asignar a la cuenta.
     */
    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    /**
     * Método abstracto que valida si un retiro es posible con base en el monto especificado.
     * La implementación específica depende de la subclase.
     *
     * @param monto Monto a retirar.
     * @return {@code true} si el retiro es válido; {@code false} en caso contrario.
     */
    public abstract boolean validarRetiro(double monto);
}
