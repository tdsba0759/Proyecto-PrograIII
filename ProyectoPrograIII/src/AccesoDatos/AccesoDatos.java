package AccesoDatos;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Joshua
 */


public class AccesoDatos implements Servicios.ServiciosaAccesoDatos{

    public String nombreArchivo;  // Nombre del archivo donde se almacenan los registros
    private String registro;  // Contenido del registro actual a escribir o modificar
    private boolean eliminar;  // Indica si la operación actual es de eliminación

    public AccesoDatos() {

    }

    //Aqui se hace el get de del Nombre del Archivo
    @Override
    public String getNombreArchivo() {
        return nombreArchivo;
    }

    //Aqui se hace el set de del Nombre del Archivo
    @Override
    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    //Aqui se hace el get de del Registro
    @Override
    public String getRegistro() {
        return registro;
    }

    //Aqui se hace el set de del Resgistro
    @Override
    public void setRegistro(String registro) { 
        this.registro = registro;
    }

    // Aqui se establece el eliminar 
    public boolean isEliminar() {
        return eliminar;
    }

    // Aqui se hace el set del eliminar 
    @Override
    public void setEliminar(boolean eliminar) {
        this.eliminar = eliminar;
    }

    /**
     * Agrega un nuevo registro al final del archivo especificado.
     *
     * @param linea La línea que representa el nuevo registro a agregar.
     * @throws IOException Si ocurre un error al escribir en el archivo.
     */
    @Override
    public void agregarRegistro(String linea) throws IOException {
        // El BufferedWriter permite escribir en el archivo de manera eficiente.
        try (BufferedWriter objBw = new BufferedWriter(new FileWriter(this.getNombreArchivo(), true))) {
            objBw.write(linea);  // Escribe la línea en el archivo.
            objBw.newLine();  // Añade una nueva línea para separar los registros.
        }
    }

    /**
     * Lee todos los registros del archivo y los devuelve como una lista de
     * arrays de String.
     *
     * @return Una lista de arrays de String, donde cada array representa un
     * registro.
     * @throws FileNotFoundException Si el archivo especificado no se encuentra.
     * @throws IOException Si ocurre un error al leer el archivo.
     */
    @Override
    public ArrayList<String[]> leerRegistros() throws FileNotFoundException, IOException {
        ArrayList<String[]> listaRegistros = new ArrayList<>();

        // BufferedReader se utiliza para leer el archivo de manera eficiente.
        try (BufferedReader objBr = new BufferedReader(new FileReader(this.getNombreArchivo()))) {
            while ((this.registro = objBr.readLine()) != null) {  // Lee cada línea del archivo.
                String[] datos = this.registro.split(",");  // Divide la línea en campos separados por comas.
                listaRegistros.add(datos);  // Añade el registro a la lista.
            }
            return listaRegistros;  // Devuelve la lista de registros.
        }
    }

    /**
     * Modifica o elimina un registro en el archivo basado en el ID
     * especificado. Si la operación es de eliminación, se omitirá el registro
     * correspondiente. Si la operación es de modificación, se reemplazará el
     * registro existente con los nuevos datos.
     *
     * @param id El ID del registro a modificar o eliminar.
     * @throws IOException Si ocurre un error al leer o escribir en el archivo.
     */
    @Override
    public void modificarRegistro(String id) throws IOException {
        File archivoActual = new File(this.nombreArchivo);  // Archivo original donde se encuentran los registros.
        File archivoTemp = new File("temp_" + this.nombreArchivo);  // Archivo temporal para almacenar los cambios.

        // BufferedReader para leer el archivo original y BufferedWriter para escribir en el archivo temporal.
        try (BufferedReader objBr = new BufferedReader(new FileReader(archivoActual)); BufferedWriter objBw = new BufferedWriter(new FileWriter(archivoTemp))) {

            String registroActual;
            while ((registroActual = objBr.readLine()) != null) {  // Lee cada línea del archivo original.
                String[] datosRegistro = registroActual.split(",");  // Divide la línea en campos separados por comas.

                if (this.eliminar) {  // Si la operación es de eliminación
                    if (datosRegistro[0].equals(id)) {
                        continue;  // Omitir la escritura de este registro en el archivo temporal.
                    }
                    objBw.write(registroActual);  // Escribe el registro no eliminado en el archivo temporal.
                    objBw.newLine();
                } else {  // Si la operación es de modificación
                    if (datosRegistro[0].equals(id)) {
                        objBw.write(this.registro);  // Escribe el nuevo registro en lugar del original.
                        objBw.newLine();
                    } else {
                        objBw.write(registroActual);  // Escribe los registros no modificados tal como están.
                        objBw.newLine();
                    }
                }
            }
        }

        // Eliminar el archivo original.
        if (!archivoActual.delete()) {
            throw new IOException("No se puede borrar el archivo original.");
        }

        // Renombrar el archivo temporal para reemplazar al archivo original.
        if (!archivoTemp.renameTo(archivoActual)) {
            throw new IOException("No se puede renombrar el archivo temporal.");
        }
    }
}
