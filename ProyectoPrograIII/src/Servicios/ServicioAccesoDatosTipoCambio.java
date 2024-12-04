package Servicios;

/**
 * Interfaz que define los métodos necesarios para acceder a datos relacionados con el tipo de cambio.
 * Esta interfaz incluye la obtención del tipo de cambio, la construcción de una solicitud SOAP
 * y el análisis de la respuesta XML.
 * 
 * @author dmsda
 */
public interface ServicioAccesoDatosTipoCambio {
    
    /**
     * Obtiene el tipo de cambio en función de los parámetros proporcionados.
     * 
     * @param indicador El indicador de tipo de cambio.
     * @param fechaInicio La fecha de inicio del rango de tipo de cambio.
     * @param fechaFinal La fecha final del rango de tipo de cambio.
     * @param nombre El nombre asociado al tipo de cambio.
     * @param subniveles Los subniveles relacionados con el tipo de cambio.
     * @param email El correo electrónico asociado a la solicitud.
     * @return El tipo de cambio obtenido.
     * @throws Exception Si ocurre un error al obtener el tipo de cambio.
     */
    String obtenerTipoCambio(String indicador, String fechaInicio, String fechaFinal, String nombre, String subniveles, String email) throws Exception;

    /**
     * Construye una solicitud SOAP para obtener datos del tipo de cambio.
     * 
     * @param indicador El indicador de tipo de cambio.
     * @param fechaInicio La fecha de inicio del rango de tipo de cambio.
     * @param fechaFinal La fecha final del rango de tipo de cambio.
     * @param nombre El nombre asociado al tipo de cambio.
     * @param subniveles Los subniveles relacionados con el tipo de cambio.
     * @param email El correo electrónico asociado a la solicitud.
     * @param token El token de autenticación para la solicitud.
     * @return La solicitud SOAP construida.
     */
    String buildSoapRequest(String indicador, String fechaInicio, String fechaFinal, String nombre, String subniveles, String email, String token);
    
    /**
     * Analiza la respuesta XML recibida de la solicitud SOAP.
     * 
     * @param responseXml La respuesta XML a analizar.
     * @return El resultado del análisis de la respuesta.
     * @throws Exception Si ocurre un error al analizar la respuesta XML.
     */
    String parseResponse(String responseXml) throws Exception;
}
