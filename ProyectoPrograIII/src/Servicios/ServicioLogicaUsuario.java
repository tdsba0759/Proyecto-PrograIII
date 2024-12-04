package Servicios;

/**
 * Interfaz que define los métodos necesarios para realizar operaciones relacionadas con el manejo de usuarios,
 * como realizar el login y validar las credenciales de un usuario.
 * 
 * @author dmsda
 * 
 */
public interface ServicioLogicaUsuario {
    
    /**
     * Realiza el proceso de login validando el usuario y la contraseña.
     * 
     * @param usuario El nombre de usuario.
     * @param pdcontraseña La contraseña del usuario, representada como un array de caracteres.
     */
    void realizarLogin(String usuario, char[] pdcontraseña);

    /**
     * Valida las credenciales de un usuario comparando el nombre de usuario y la contraseña proporcionada.
     * 
     * @param usuario El nombre de usuario.
     * @param contrasena La contraseña proporcionada para validar.
     * @return true si las credenciales son válidas, false en caso contrario.
     */
    boolean validarCredenciales(String usuario, String contrasena);
}
