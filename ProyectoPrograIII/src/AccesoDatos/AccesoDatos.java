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
 * Clase que implementa servicios de acceso a datos para realizar operaciones
 * de lectura, escritura, modificación y eliminación en archivos de texto.
 * Esta clase está diseñada para manejar registros en formato delimitado por comas (CSV).
 *
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
     * Bandera que indica si la operación actual es de eliminación.
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
     * Obtiene el contenido del registro actual.
     *
     * @return Contenido del registro actual.
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
    @Override
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
        // Verificar que la línea no esté vacía o nula
        if (linea == null || linea.trim().isEmpty()) {
            System.err.println("Intento de escribir una línea vacía ignorado.");
            return; // Evitar escribir líneas vacías
        }

        // Abrir el archivo en modo de adición (append)
        BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo, true));
        writer.write(linea); // Escribir la línea en el archivo
        writer.newLine(); // Agregar un salto de línea
        writer.close(); // Cerrar el escritor
    }

    /**
     * Lee todos los registros del archivo y los devuelve como una lista de arrays de cadenas.
     * Cada array representa un registro dividido en columnas.
     *
     * @return Una lista de arrays de cadenas, donde cada array representa un registro.
     * @throws IOException Si ocurre un error al leer el archivo.
     */
    @Override
    public ArrayList<String[]> leerRegistros() throws IOException {
        ArrayList<String[]> registros = new ArrayList<>(); // Lista para almacenar los registros
        BufferedReader reader = new BufferedReader(new FileReader(nombreArchivo));
        String linea;

        // Leer cada línea del archivo
        while ((linea = reader.readLine()) != null) {
            linea = linea.trim();
            if (!linea.isEmpty()) { // Evitar procesar líneas vacías
                registros.add(linea.split(",")); // Dividir la línea en columnas y agregar a la lista
            }
        }

        reader.close(); // Cerrar el lector
        return registros;
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
        File archivoActual = new File(this.nombreArchivo); // Archivo original
        File archivoTemp = new File("temp_" + this.nombreArchivo); // Archivo temporal

        try (BufferedReader objBr = new BufferedReader(new FileReader(archivoActual));
             BufferedWriter objBw = new BufferedWriter(new FileWriter(archivoTemp))) {

            String registroActual;
            // Leer cada registro del archivo original
            while ((registroActual = objBr.readLine()) != null) {
                String[] datosRegistro = registroActual.split(",");

                if (this.eliminar) { // Operación de eliminación
                    if (datosRegistro[0].equals(id)) {
                        continue; // Omitir el registro a eliminar
                    }
                    objBw.write(registroActual);
                    objBw.newLine();
                } else { // Operación de modificación
                    if (datosRegistro[0].equals(id)) {
                        objBw.write(this.registro); // Escribir el nuevo registro
                        objBw.newLine();
                    } else {
                        objBw.write(registroActual); // Mantener los registros no afectados
                        objBw.newLine();
                    }
                }
            }
        }

        // Reemplazar el archivo original por el temporal
        if (!archivoActual.delete()) {
            throw new IOException("No se puede borrar el archivo original.");
        }

        if (!archivoTemp.renameTo(archivoActual)) {
            throw new IOException("No se puede renombrar el archivo temporal.");
        }
    }

    /**
     * Sobrescribe el contenido del archivo con los registros proporcionados.
     *
     * @param registros Lista de registros a escribir en el archivo.
     * @throws IOException Si ocurre un error al escribir en el archivo.
     */
    @Override
    public void escribirRegistros(ArrayList<String[]> registros) throws IOException {
        // Verificar que el nombre del archivo esté definido
        if (nombreArchivo == null || nombreArchivo.isEmpty()) {
            throw new IOException("El nombre del archivo no está definido.");
        }

        // Abrir el archivo en modo sobrescritura
        BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo, false)); // 'false' para sobrescribir

        // Escribir cada registro en el archivo
        for (String[] registro : registros) {
            writer.write(String.join(",", registro)); // Convertir el array en una línea CSV
            writer.newLine(); // Agregar salto de línea
        }

        writer.close(); // Cerrar el escritor
    }
}
