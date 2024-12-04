package Entidades;

/**
 * Clase que representa un usuario del sistema bancario. Contiene información
 * básica como el número de cuenta, nombre del usuario, PIN encriptado y saldo
 * de la cuenta.
 * 
 * @author Joshua
 */
public class Usuario {

    /**
     * Número de cuenta del usuario.
     */
    private String numeroCuenta;

    /**
     * Nombre del usuario.
     */
    private String nombre;

    /**
     * PIN encriptado asociado a la cuenta del usuario.
     */
    private String pinEncriptado;

    /**
     * Saldo disponible en la cuenta del usuario.
     */
    private double saldo;

    /**
     * Constructor por defecto.
     */
    public Usuario() {}

    /**
     * Constructor que inicializa los valores del número de cuenta, nombre,
     * PIN encriptado y saldo.
     * 
     * @param numeroCuenta El número de cuenta del usuario.
     * @param nombre El nombre del usuario.
     * @param pinEncriptado El PIN encriptado del usuario.
     * @param saldo El saldo inicial de la cuenta.
     */
    public Usuario(String numeroCuenta, String nombre, String pinEncriptado, double saldo) {
        this.numeroCuenta = numeroCuenta;
        this.nombre = nombre;
        this.pinEncriptado = pinEncriptado;
        this.saldo = saldo;
    }

    /**
     * Obtiene el número de cuenta del usuario.
     * 
     * @return El número de cuenta.
     */
    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    /**
     * Establece el número de cuenta del usuario.
     * 
     * @param numeroCuenta El nuevo número de cuenta.
     */
    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    /**
     * Obtiene el nombre del usuario.
     * 
     * @return El nombre del usuario.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del usuario.
     * 
     * @param nombre El nuevo nombre del usuario.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene el PIN encriptado del usuario.
     * 
     * @return El PIN encriptado.
     */
    public String getPinEncriptado() {
        return pinEncriptado;
    }

    /**
     * Establece el PIN encriptado del usuario.
     * 
     * @param pinEncriptado El nuevo PIN encriptado.
     */
    public void setPinEncriptado(String pinEncriptado) {
        this.pinEncriptado = pinEncriptado;
    }

    /**
     * Obtiene el saldo actual de la cuenta del usuario.
     * 
     * @return El saldo de la cuenta.
     */
    public double getSaldo() {
        return saldo;
    }

    /**
     * Establece el saldo de la cuenta del usuario.
     * 
     * @param saldo El nuevo saldo de la cuenta.
     */
    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
}
