
package Entidades;

import java.util.Date;

/**
 *
 * @author Joshua
 */
public class Transaccion {

    private String tipoTransaccion;
    private double monto;
    private Date fecha;
    private String cuentaId;

    public Transaccion() {
    }

    public Transaccion(String tipoTransaccion, double monto, Date fecha) {
        this.tipoTransaccion = tipoTransaccion;
        this.monto = monto;
        this.fecha = fecha;
    }

    public String getTipoTransaccion() {
        return tipoTransaccion;
    }

    public void setTipoTransaccion(String tipoTransaccion) {
        this.tipoTransaccion = tipoTransaccion;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getCuentaId() {
        return cuentaId;
    }

    public void setCuentaId(String cuentaId) {
        this.cuentaId = cuentaId;
    }

}
