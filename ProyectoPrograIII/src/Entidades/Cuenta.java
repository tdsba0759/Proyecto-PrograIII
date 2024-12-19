package Entidades;

/**
 * Clase que representa una cuenta bancaria. Contiene un número de cuenta,
 * saldo, nombre del usuario, PIN encriptado y un método para validar retiros.
 * Esta clase se puede usar para gestionar las operaciones básicas relacionadas
 * con una cuenta bancaria.
 * 
 */
public class Cuenta {

    /**
     * Número único que identifica la cuenta.
     */
    private String numeroCuenta;

    /**
     * Saldo actual de la cuenta.
     */
    private double saldo;

    /**
     * Nombre del usuario asociado a la cuenta.
     */
    private String nombre;

    /**
     * PIN encriptado asociado a la cuenta.
     */
    private String pinEncriptado;

    /**
     * Constructor por defecto para la clase {@code Cuenta}.
     * Este constructor crea una cuenta con valores predeterminados.
     */
    public Cuenta() {}

    /**
     * Constructor que inicializa la cuenta con un número de cuenta, saldo,
     * nombre de usuario y PIN encriptado.
     *
     * @param numeroCuenta Número de la cuenta.
     * @param saldo Saldo inicial de la cuenta.
     * @param nombre Nombre del usuario.
     * @param pinEncriptado PIN encriptado del usuario.
     */
    public Cuenta(String numeroCuenta, double saldo, String nombre, String pinEncriptado) {
        this.numeroCuenta = numeroCuenta;
        this.saldo = saldo;
        this.nombre = nombre;
        this.pinEncriptado = pinEncriptado;
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
     * Obtiene el nombre del usuario asociado a la cuenta.
     *
     * @return El nombre del usuario.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del usuario.
     *
     * @param nombre El nombre del usuario a asignar.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene el PIN encriptado asociado a la cuenta.
     *
     * @return El PIN encriptado.
     */
    public String getPinEncriptado() {
        return pinEncriptado;
    }

    /**
     * Establece el PIN encriptado de la cuenta.
     *
     * @param pinEncriptado El PIN encriptado a asignar.
     */
    public void setPinEncriptado(String pinEncriptado) {
        this.pinEncriptado = pinEncriptado;
    }

    /**
     * Método para validar si un retiro es posible con base en el monto especificado.
     * Se verifica que el monto solicitado no exceda el saldo disponible en la cuenta.
     * 
     * @param monto Monto a retirar.
     * @return {@code true} si el retiro es válido (es decir, si el saldo es suficiente); 
     *         {@code false} en caso contrario.
     */
    public boolean validarRetiro(double monto) {
        // Lógica para validar el retiro, por ejemplo, verificando el saldo disponible
        if (monto <= saldo) {
            return true;
        }
        return false;
    }
}
