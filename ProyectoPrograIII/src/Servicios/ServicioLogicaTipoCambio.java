package Servicios;

/**
 * Interfaz que define el método necesario para obtener el tipo de cambio en función de los parámetros proporcionados.
 * Esta interfaz permite acceder a los datos del tipo de cambio utilizando los parámetros relevantes como indicador,
 * fechas y otros detalles asociados.
 * 
 */
public interface ServicioLogicaTipoCambio {
    
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
   public String obtenerTipoCambio(String indicador, String fechaInicio, String fechaFinal, String nombre, String subniveles, String email, String token) throws Exception;
}
