package LogicaNegocio;

import AccesoDatos.AccesoDatos;
import Presentacion.ATMApp;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 * La clase LogicaUsuario gestiona el proceso de inicio de sesión de los usuarios,
 * validando las credenciales contra un archivo de usuarios. Si el inicio de sesión es
 * exitoso, abre la interfaz del cajero automático (ATM).
 * 
 * @author dmsda
 * 
 */
public class LogicaUsuario {
    
    private final AccesoDatos accesoDatos;

    /**
     * Constructor de la clase LogicaUsuario.
     * Inicializa el objeto de acceso a datos y establece el archivo de usuarios.
     */
    public LogicaUsuario() {
        this.accesoDatos = new AccesoDatos();
        this.accesoDatos.setNombreArchivo("usuarios.txt"); 
    }
    
    /**
     * Método principal para manejar el inicio de sesión de un usuario.
     * Convierte la contraseña ingresada a String y valida las credenciales.
     * Si las credenciales son correctas, se muestra un mensaje de éxito y se abre la ventana del ATM.
     * Si las credenciales son incorrectas, se muestra un mensaje de error.
     * 
     * @param usuario El nombre de usuario ingresado.
     * @param pdcontraseña La contraseña ingresada como un array de caracteres.
     */
    public void realizarLogin(String usuario, char[] pdcontraseña) {
        // Convierte la contraseña a String
        String contrasena = new String(pdcontraseña);

        // Validación de credenciales
        if (validarCredenciales(usuario, contrasena)) {
            // Si las credenciales son correctas
            JOptionPane.showMessageDialog(null, "¡Login exitoso!");
            // Abre la ventana de mantenimiento de usuarios
            new ATMApp().setVisible(true);
        } else {
            // Si las credenciales son incorrectas
            JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Valida las credenciales de usuario contra un archivo de texto.
     * Lee los registros del archivo de usuarios y compara el nombre de usuario y la contraseña.
     * 
     * @param usuario El nombre de usuario ingresado.
     * @param contrasena La contraseña ingresada.
     * @return true si las credenciales son válidas, false en caso contrario.
     */
    public boolean validarCredenciales(String usuario, String contrasena) {
        try {
            // Llama al método leerRegistros
            ArrayList<String[]> registros = accesoDatos.leerRegistros();
            // Recorre los registros para validar las credenciales
            for (String[] datosUsuario : registros) {
                String usuarioGuardado = datosUsuario[0].trim();
                String contrasenaGuardada = datosUsuario[1].trim();

                if (usuario.equals(usuarioGuardado) && contrasena.equals(contrasenaGuardada)) {
                    return true; // Credenciales válidas
                }
            }
        } catch (IOException e) {
            // Manejar posibles errores de entrada/salida
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al validar credenciales.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return false; // Credenciales no válidas
    }
}
