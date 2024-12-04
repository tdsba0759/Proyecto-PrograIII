package Entidades;

import java.util.Date;

/**
 * Clase que representa una transacción bancaria, incluyendo información
 * sobre el tipo de transacción, monto, fecha y la cuenta asociada.
 * 
 * Esta clase permite modelar operaciones financieras realizadas en una cuenta.
 * 
 * @author Joshua
 */
public class Transaccion {

    /**
     * Tipo de transacción (e.g., depósito, retiro).
     */
    private String tipoTransaccion;

    /**
     * Monto de la transacción.
     */
    private double monto;

    /**
     * Fecha en la que se realizó la transacción.
     */
    private Date fecha;

    /**
     * Identificador de la cuenta asociada a la transacción.
     */
    private String cuentaId;

    /**
     * Constructor por defecto.
     */
    public Transaccion() {
    }

    /**
     * Constructor que inicializa los valores de tipo de transacción, monto y fecha.
     * 
     * @param tipoTransaccion Tipo de la transacción (e.g., depósito, retiro).
     * @param monto Monto de la transacción.
     * @param fecha Fecha de la transacción.
     */
    public Transaccion(String tipoTransaccion, double monto, Date fecha) {
        this.tipoTransaccion = tipoTransaccion;
        this.monto = monto;
        this.fecha = fecha;
    }

    /**
     * Obtiene el tipo de transacción.
     * 
     * @return El tipo de transacción (e.g., depósito, retiro).
     */
    public String getTipoTransaccion() {
        return tipoTransaccion;
    }

    /**
     * Establece el tipo de transacción.
     * 
     * @param tipoTransaccion El nuevo tipo de transacción.
     */
    public void setTipoTransaccion(String tipoTransaccion) {
        this.tipoTransaccion = tipoTransaccion;
    }

    /**
     * Obtiene el monto de la transacción.
     * 
     * @return El monto de la transacción.
     */
    public double getMonto() {
        return monto;
    }

    /**
     * Establece el monto de la transacción.
     * 
     * @param monto El nuevo monto de la transacción.
     */
    public void setMonto(double monto) {
        this.monto = monto;
    }

    /**
     * Obtiene la fecha de la transacción.
     * 
     * @return La fecha de la transacción.
     */
    public Date getFecha() {
        return fecha;
    }

    /**
     * Establece la fecha de la transacción.
     * 
     * @param fecha La nueva fecha de la transacción.
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    /**
     * Obtiene el identificador de la cuenta asociada a la transacción.
     * 
     * @return El ID de la cuenta asociada.
     */
    public String getCuentaId() {
        return cuentaId;
    }

    /**
     * Establece el identificador de la cuenta asociada a la transacción.
     * 
     * @param cuentaId El nuevo ID de la cuenta asociada.
     */
    public void setCuentaId(String cuentaId) {
        this.cuentaId = cuentaId;
    }
}
