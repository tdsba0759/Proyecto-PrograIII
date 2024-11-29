
package Entidades;

/**
 *
 * @author Joshua
 */
public class Usuario {
    
    
    private String numeroCuenta;
    private String nombre;
    private String pinEncriptado;
    private double saldo;

    public Usuario() {}

    public Usuario(String numeroCuenta, String nombre, String pinEncriptado, double saldo) {
        this.numeroCuenta = numeroCuenta;
        this.nombre = nombre;
        this.pinEncriptado = pinEncriptado;
        this.saldo = saldo;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPinEncriptado() {
        return pinEncriptado;
    }

    public void setPinEncriptado(String pinEncriptado) {
        this.pinEncriptado = pinEncriptado;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
}
