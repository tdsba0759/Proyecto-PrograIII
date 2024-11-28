/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Servicios;

/**
 *
 * @author dmsda
 */
public interface ServicioLogicaUsuario {
    void realizarLogin(String usuario, char[] pdcontraseña) ;
    boolean validarCredenciales(String usuario, String contrasena);
    
}
