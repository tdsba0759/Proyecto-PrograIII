package Servicios;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Interfaz que define los métodos necesarios para acceder y gestionar datos desde un archivo,
 * incluyendo operaciones como agregar, leer, modificar registros y gestionar la eliminación de datos.
 * 
 * @author dmsda
 * 
 */
public interface ServiciosaAccesoDatos {
    
    /**
     * Obtiene el nombre del archivo que contiene los datos.
     * 
     * @return El nombre del archivo.
     */
    String getNombreArchivo();

    /**
     * Establece el nombre del archivo que contiene los datos.
     * 
     * @param nombreArchivo El nombre del archivo a establecer.
     */
    void setNombreArchivo(String nombreArchivo);

    /**
     * Obtiene el registro actual que se está manejando.
     * 
     * @return El registro actual.
     */
    String getRegistro();

    /**
     * Establece el registro que se va a manejar.
     * 
     * @param registro El registro a establecer.
     */
    void setRegistro(String registro);

    /**
     * Verifica si el registro debe ser eliminado.
     * 
     * @return true si el registro debe ser eliminado, false en caso contrario.
     */
    boolean isEliminar();

    /**
     * Establece si el registro debe ser eliminado.
     * 
     * @param eliminar true si el registro debe ser eliminado, false en caso contrario.
     */
    void setEliminar(boolean eliminar);

    /**
     * Agrega una línea de texto como un nuevo registro en el archivo de datos.
     * 
     * @param linea La línea a agregar.
     * @throws IOException Si ocurre un error al escribir en el archivo.
     */
    void agregarRegistro(String linea) throws IOException;

    /**
     * Lee los registros del archivo de datos y los devuelve como una lista de arreglos de cadenas.
     * 
     * @return Una lista de arreglos de cadenas que representan los registros leídos.
     * @throws FileNotFoundException Si el archivo no se encuentra.
     * @throws IOException Si ocurre un error al leer el archivo.
     */
    ArrayList<String[]> leerRegistros() throws FileNotFoundException, IOException;

    /**
     * Modifica un registro específico en el archivo de datos, identificado por su ID.
     * 
     * @param id El ID del registro que se desea modificar.
     * @throws IOException Si ocurre un error al modificar el archivo.
     */
    void modificarRegistro(String id) throws IOException;
}
