package Servicios;

/**
 * Interfaz que define los métodos necesarios para encriptar y verificar un PIN.
 * Esta interfaz incluye la encriptación del PIN utilizando un algoritmo seguro y
 * la verificación de un PIN encriptado.
 * 
 * @author dmsda
 * 
 */
public interface ServicioEncriptacionPIN {
    
    /**
     * Encripta un PIN utilizando un algoritmo de encriptación seguro.
     * 
     * @param pin El PIN que se desea encriptar.
     * @return El PIN encriptado.
     * @throws Exception Si ocurre un error durante el proceso de encriptación.
     */
    String encriptarPin(String pin) throws Exception;

    /**
     * Verifica si un PIN ingresado coincide con un PIN previamente encriptado.
     * 
     * @param pin El PIN ingresado por el usuario.
     * @param pinEncriptado El PIN encriptado almacenado para comparar.
     * @return true si el PIN ingresado coincide con el PIN encriptado, false en caso contrario.
     * @throws Exception Si ocurre un error durante la verificación.
     */
    boolean verificarPin(String pin, String pinEncriptado) throws Exception;
}
