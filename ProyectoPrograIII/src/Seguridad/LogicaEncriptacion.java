package Seguridad;

import Servicios.ServicioEncriptacionPIN;
import java.security.MessageDigest;

/**
 * La clase {@code LogicaEncriptacion} implementa el servicio de encriptación de PINs
 * utilizando el algoritmo de hash SHA-256 para garantizar la seguridad de las
 * contraseñas. Esta clase permite encriptar un PIN y verificar si un PIN
 * ingresado coincide con uno previamente encriptado.
 * 
 * El algoritmo SHA-256 produce un hash de 256 bits que se presenta en formato
 * hexadecimal, proporcionando un nivel alto de seguridad para las contraseñas.
 * 
 */
public class LogicaEncriptacion implements ServicioEncriptacionPIN {

    /**
     * Constructor de la clase {@code LogicaEncriptacion}. Inicializa una nueva
     * instancia de la clase sin parámetros.
     */
    public LogicaEncriptacion() {
    }

    /**
     * Encripta un PIN utilizando el algoritmo de hash SHA-256.
     *
     * @param pin El PIN que se desea encriptar.
     * @return El PIN encriptado en formato hexadecimal.
     * @throws Exception Si ocurre un error durante el proceso de encriptación.
     */
    @Override
    public String encriptarPin(String pin) throws Exception {
        // Se crea una instancia de MessageDigest con el algoritmo SHA-256
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        
        // Se obtiene el hash del PIN en forma de bytes
        byte[] hashedBytes = md.digest(pin.getBytes());
        
        // Se construye la representación hexadecimal del hash
        StringBuilder sb = new StringBuilder();
        for (byte b : hashedBytes) {
            sb.append(String.format("%02x", b));  // Convertir cada byte a su representación hexadecimal
        }
        
        // Devolver el PIN encriptado como una cadena hexadecimal
        return sb.toString();
    }

    /**
     * Verifica si un PIN ingresado coincide con un PIN previamente encriptado.
     *
     * Este método encripta el PIN ingresado y lo compara con el PIN previamente
     * encriptado para determinar si son iguales.
     *
     * @param pin El PIN ingresado por el usuario.
     * @param pinEncriptado El PIN encriptado almacenado para comparar.
     * @return {@code true} si el PIN ingresado coincide con el PIN encriptado,
     *         {@code false} en caso contrario.
     * @throws Exception Si ocurre un error durante la verificación.
     */
    @Override
    public boolean verificarPin(String pin, String pinEncriptado) throws Exception {
        // Encriptar el PIN ingresado
        String pinEncriptadoIngresado = encriptarPin(pin);
        
        // Comparar el PIN ingresado encriptado con el PIN almacenado
        return pinEncriptadoIngresado.equals(pinEncriptado);
    }
}
