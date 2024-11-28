package LogicaNegocio;

import AccesoDatos.AccesoDatos;
import Presentacion.ATMApp;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author dmsda
 */
public class LogicaUsuario {
    
    private final AccesoDatos accesoDatos;

    public LogicaUsuario() {
        this.accesoDatos = new AccesoDatos();
        this.accesoDatos.setNombreArchivo("usuarios.txt"); 
    }
    
     // Método principal para manejar el inicio de sesión
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

    // Método para validar las credenciales contra un archivo de texto
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
