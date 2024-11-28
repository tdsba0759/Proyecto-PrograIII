
package Servicios;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author dmsda
 */
public interface ServiciosaAccesoDatos {
    String getNombreArchivo();
    void setNombreArchivo(String nombreArchivo);
    String getRegistro();
    void setRegistro(String registro);
    boolean isEliminar();
    void setEliminar(boolean eliminar);
    void agregarRegistro(String linea) throws IOException;
    ArrayList<String[]> leerRegistros() throws FileNotFoundException, IOException;
    void modificarRegistro(String id) throws IOException;
    
}
