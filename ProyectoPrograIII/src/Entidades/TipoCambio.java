
package Entidades;

/**
 *
 * @author dmsda
 */
public class TipoCambio {

    private String compra;
    private String venta;

    public TipoCambio(String compra, String venta) {
        this.compra = compra;
        this.venta = venta;
    }

    public String getCompra() { return compra; }
    public void setCompra(String compra) { this.compra = compra; }

    public String getVenta() { return venta; }
    public void setVenta(String venta) { this.venta = venta; }

    @Override
    public String toString() {
        return "Tipo de cambio - Compra: " + compra + ", Venta: " + venta;
    }
}
