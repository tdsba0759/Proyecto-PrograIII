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
 * Clase que implementa los servicios de acceso a datos mediante operaciones 
 * de lectura, escritura, modificación y eliminación en archivos de texto.
 * Permite gestionar registros almacenados en formato delimitado por comas.
 *
 * @author Joshua
 */
public class AccesoDatos implements Servicios.ServiciosaAccesoDatos {

    /**
     * Nombre del archivo donde se almacenan los registros.
     */
    public String nombreArchivo;

    /**
     * Contenido del registro actual que será utilizado en operaciones de escritura o modificación.
     */
    private String registro;

    /**
     * Indica si la operación actual es de eliminación.
     */
    private boolean eliminar;

    /**
     * Constructor por defecto.
     */
    public AccesoDatos() {
    }

    /**
     * Obtiene el nombre del archivo donde se almacenan los registros.
     *
     * @return Nombre del archivo.
     */
    @Override
    public String getNombreArchivo() {
        return nombreArchivo;
    }

    /**
     * Establece el nombre del archivo donde se almacenan los registros.
     *
     * @param nombreArchivo Nombre del archivo.
     */
    @Override
    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    /**
     * Obtiene el registro actual.
     *
     * @return Registro actual.
     */
    @Override
    public String getRegistro() {
        return registro;
    }

    /**
     * Establece el contenido del registro actual.
     *
     * @param registro Contenido del registro.
     */
    @Override
    public void setRegistro(String registro) {
        this.registro = registro;
    }

    /**
     * Indica si la operación actual es de eliminación.
     *
     * @return {@code true} si la operación es de eliminación, de lo contrario {@code false}.
     */
    public boolean isEliminar() {
        return eliminar;
    }

    /**
     * Establece si la operación actual será de eliminación.
     *
     * @param eliminar {@code true} para operación de eliminación, de lo contrario {@code false}.
     */
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
        try (BufferedWriter objBw = new BufferedWriter(new FileWriter(this.getNombreArchivo(), true))) {
            objBw.write(linea);
            objBw.newLine();
        }
    }

    /**
     * Lee todos los registros del archivo y los devuelve como una lista de arrays de cadenas.
     *
     * @return Una lista de arrays de cadenas, donde cada array representa un registro.
     * @throws FileNotFoundException Si el archivo especificado no existe.
     * @throws IOException Si ocurre un error al leer el archivo.
     */
    @Override
    public ArrayList<String[]> leerRegistros() throws FileNotFoundException, IOException {
        ArrayList<String[]> listaRegistros = new ArrayList<>();

        try (BufferedReader objBr = new BufferedReader(new FileReader(this.getNombreArchivo()))) {
            while ((this.registro = objBr.readLine()) != null) {
                String[] datos = this.registro.split(",");
                listaRegistros.add(datos);
            }
            return listaRegistros;
        }
    }

    /**
     * Modifica o elimina un registro en el archivo basado en el ID especificado.
     * Si la operación es de eliminación, omite el registro correspondiente.
     * Si es de modificación, reemplaza el registro existente con los nuevos datos.
     *
     * @param id El ID del registro a modificar o eliminar.
     * @throws IOException Si ocurre un error al leer o escribir en el archivo.
     */
    @Override
    public void modificarRegistro(String id) throws IOException {
        File archivoActual = new File(this.nombreArchivo);
        File archivoTemp = new File("temp_" + this.nombreArchivo);

        try (BufferedReader objBr = new BufferedReader(new FileReader(archivoActual));
             BufferedWriter objBw = new BufferedWriter(new FileWriter(archivoTemp))) {

            String registroActual;
            while ((registroActual = objBr.readLine()) != null) {
                String[] datosRegistro = registroActual.split(",");

                if (this.eliminar) {
                    if (datosRegistro[0].equals(id)) {
                        continue;
                    }
                    objBw.write(registroActual);
                    objBw.newLine();
                } else {
                    if (datosRegistro[0].equals(id)) {
                        objBw.write(this.registro);
                        objBw.newLine();
                    } else {
                        objBw.write(registroActual);
                        objBw.newLine();
                    }
                }
            }
        }

        if (!archivoActual.delete()) {
            throw new IOException("No se puede borrar el archivo original.");
        }

        if (!archivoTemp.renameTo(archivoActual)) {
            throw new IOException("No se puede renombrar el archivo temporal.");
        }
    }
}
