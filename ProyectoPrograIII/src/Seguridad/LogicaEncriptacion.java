
package Seguridad;

import Servicios.ServicioEncriptacionPIN;
import java.security.MessageDigest;
/**
 *
 * @author Joshua
 */
public class LogicaEncriptacion implements ServicioEncriptacionPIN{
    
    public LogicaEncriptacion() {}

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

    @Override
    public boolean verificarPin(String pin, String pinEncriptado) throws Exception {
        return encriptarPin(pin).equals(pinEncriptado);
    } 
    
}
