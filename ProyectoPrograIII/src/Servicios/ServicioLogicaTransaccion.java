/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Servicios;

/**
 *
 * @author dmsda
 */
public interface ServicioLogicaTransaccion {
    double consultarSaldo(String cuentaId) throws Exception;
    void agregarSaldo(String cuentaId, double monto) throws Exception;
    void quitarSaldo(String cuentaId, double monto) throws Exception;
    void registrarTransaccion(String cuentaId, double monto, String tipoTransaccion) throws Exception;
}
