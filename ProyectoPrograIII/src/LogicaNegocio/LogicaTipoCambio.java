

/**
 *
 * @author dmsda
 */

package LogicaNegocio;


import Servicios.ServicioAccesoDatosTipoCambio;


public class LogicaTipoCambio implements Servicios.ServicioLogicaTipoCambio{

    private final ServicioAccesoDatosTipoCambio servicioTipoCambio;

    public LogicaTipoCambio(ServicioAccesoDatosTipoCambio servicioTipoCambio) {
        this.servicioTipoCambio = servicioTipoCambio;
    }

    /**
     * Obtiene el tipo de cambio para una operación específica y valida el resultado.
     *
     * @param indicador Código del indicador (317 para compra, 318 para venta).
     * @param fechaInicio Fecha de inicio en formato dd/MM/yyyy.
     * @param fechaFinal Fecha final en formato dd/MM/yyyy.
     * @param nombre Nombre del solicitante.
     * @param subniveles Indica si desea subniveles (S/N).
     * @param email Correo electrónico del solicitante.
     * @return El tipo de cambio como String, o null si no es válido.
     * @throws Exception Si hay un error en el servicio de tipo de cambio.
     */
    public String obtenerTipoCambio(String indicador, String fechaInicio, String fechaFinal, String nombre, String subniveles, String email) throws Exception {
        // Obtiene el tipo de cambio desde la capa de servicios
        String tipoCambio = servicioTipoCambio.obtenerTipoCambio(indicador, fechaInicio, fechaFinal, nombre, subniveles, email);
        
        // Verifica que el tipo de cambio no sea nulo o vacío
        if (tipoCambio == null || tipoCambio.isEmpty()) {
            return null; // Retorna null para indicar un valor inválido
        }
        
        return tipoCambio;
    }
}

