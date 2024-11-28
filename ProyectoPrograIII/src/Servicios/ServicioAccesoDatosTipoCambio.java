/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Servicios;

/**
 *
 * @author dmsda
 */
public interface ServicioAccesoDatosTipoCambio {
    
  String obtenerTipoCambio(String indicador, String fechaInicio, String fechaFinal, String nombre, String subniveles, String email) throws Exception;
  
  String buildSoapRequest(String indicador, String fechaInicio, String fechaFinal, String nombre, String subniveles, String email, String token);
      
  String parseResponse(String responseXml) throws Exception;
  
  
}
