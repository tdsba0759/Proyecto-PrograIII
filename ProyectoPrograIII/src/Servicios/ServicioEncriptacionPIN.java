/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Servicios;

/**
 *
 * @author Joshua
 */
public interface ServicioEncriptacionPIN {
    
    String encriptarPin(String pin) throws Exception;
    boolean verificarPin(String pin, String pinEncriptado) throws Exception;
    
}
