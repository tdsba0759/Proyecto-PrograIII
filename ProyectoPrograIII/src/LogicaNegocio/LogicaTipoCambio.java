/**
 * Clase que representa la lógica de negocio para manejar operaciones relacionadas
 * con el tipo de cambio. Se comunica con la capa de servicios para obtener
 * la información del tipo de cambio y realizar validaciones adicionales.
 * 
 */
package LogicaNegocio;

import AccesoDatos.Acceso_Datos_Tipo_Cambio;
import Servicios.ServicioAccesoDatosTipoCambio;

public class LogicaTipoCambio implements Servicios.ServicioLogicaTipoCambio {

    /**
     * Servicio para acceder a los datos del tipo de cambio.
     */
    private final ServicioAccesoDatosTipoCambio servicioAcceso;

    /**
     * Constructor que inicializa la clase con un servicio de acceso a datos de tipo de cambio.
     *
     */
     public LogicaTipoCambio() {
        this.servicioAcceso = new Acceso_Datos_Tipo_Cambio();
    }

    /**
     * Obtiene el tipo de cambio para una operación específica y valida el resultado.
     *
     * @param indicador Código del indicador (por ejemplo, 317 para compra, 318 para venta).
     * @param fechaInicio Fecha de inicio de la consulta en formato dd/MM/yyyy.
     * @param fechaFinal Fecha final de la consulta en formato dd/MM/yyyy.
     * @param nombre Nombre del solicitante.
     * @param subniveles Indica si se incluyen subniveles (S/N).
     * @param email Correo electrónico del solicitante.
     * @param token el token para realizar las consultas
     * @return El tipo de cambio como String, o null si no es válido.
     * @throws Exception Si ocurre un error al obtener el tipo de cambio desde el servicio.
     */
    @Override
    public String obtenerTipoCambio(String indicador, String fechaInicio, String fechaFinal, String nombre, String subniveles, String email, String token) throws Exception {
        return servicioAcceso.obtenerTipoCambio(indicador, fechaInicio, fechaFinal, nombre, subniveles, email, token);
    }
}
