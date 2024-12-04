package Entidades;

/**
 * Clase que representa el tipo de cambio, incluyendo los valores de compra y venta.
 * Esta clase permite obtener y establecer los valores asociados al tipo de cambio,
 * así como proporcionar una representación en forma de cadena.
 * 
 * @author dmsda
 */
public class TipoCambio {

    /**
     * Valor del tipo de cambio para la compra.
     */
    private String compra;

    /**
     * Valor del tipo de cambio para la venta.
     */
    private String venta;

    /**
     * Constructor que inicializa los valores de compra y venta del tipo de cambio.
     * 
     * @param compra Valor del tipo de cambio para la compra.
     * @param venta Valor del tipo de cambio para la venta.
     */
    public TipoCambio(String compra, String venta) {
        this.compra = compra;
        this.venta = venta;
    }

    /**
     * Obtiene el valor del tipo de cambio para la compra.
     * 
     * @return El valor del tipo de cambio para la compra.
     */
    public String getCompra() {
        return compra;
    }

    /**
     * Establece el valor del tipo de cambio para la compra.
     * 
     * @param compra El nuevo valor del tipo de cambio para la compra.
     */
    public void setCompra(String compra) {
        this.compra = compra;
    }

    /**
     * Obtiene el valor del tipo de cambio para la venta.
     * 
     * @return El valor del tipo de cambio para la venta.
     */
    public String getVenta() {
        return venta;
    }

    /**
     * Establece el valor del tipo de cambio para la venta.
     * 
     * @param venta El nuevo valor del tipo de cambio para la venta.
     */
    public void setVenta(String venta) {
        this.venta = venta;
    }

    /**
     * Devuelve una representación en forma de cadena del tipo de cambio, mostrando
     * los valores de compra y venta.
     * 
     * @return Una cadena con los valores de compra y venta del tipo de cambio.
     */
    @Override
    public String toString() {
        return "Tipo de cambio - Compra: " + compra + ", Venta: " + venta;
    }
}
