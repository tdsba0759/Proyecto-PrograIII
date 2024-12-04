package Seguridad;

import Servicios.ServicioEncriptacionPIN;
import java.security.MessageDigest;

/**
 * La clase LogicaEncriptacion implementa el servicio de encriptación de PINs
 * utilizando el algoritmo de hash SHA-256 para garantizar la seguridad de las contraseñas.
 * Esta clase permite encriptar un PIN y verificar si un PIN ingresado coincide con uno previamente encriptado.
 */
public class LogicaEncriptacion implements ServicioEncriptacionPIN {
    
    /**
     * Constructor de la clase LogicaEncriptacion.
     * Inicializa una nueva instancia de la clase sin parámetros.
     */
    public LogicaEncriptacion() {}

    /**
     * Encripta un PIN utilizando el algoritmo de hash SHA-256.
     * 
     * @param pin El PIN que se desea encriptar.
     * @return El PIN encriptado en formato hexadecimal.
     * @throws Exception Si ocurre un error durante el proceso de encriptación.
     */
    @Override
    public String encriptarPin(String pin) throws Exception {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] hashedBytes = md.digest(pin.getBytes());
        StringBuilder sb = new StringBuilder();
        for (byte b : hashedBytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    /**
     * Verifica si un PIN ingresado coincide con un PIN previamente encriptado.
     * 
     * @param pin El PIN ingresado por el usuario.
     * @param pinEncriptado El PIN encriptado almacenado para comparar.
     * @return true si el PIN ingresado coincide con el PIN encriptado, false en caso contrario.
     * @throws Exception Si ocurre un error durante la verificación.
     */
    @Override
    public boolean verificarPin(String pin, String pinEncriptado) throws Exception {
        return encriptarPin(pin).equals(pinEncriptado);
    } 
}
