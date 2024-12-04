package AccesoDatos;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Clase que gestiona el control de IDs únicos para diferentes archivos.
 * Se asegura de que cada archivo tenga un ID único incremental.
 * Los IDs se almacenan y persisten en un archivo de control.
 *
 * @author dmsda
 */
public class Idcontrol {

    /**
     * Nombre del archivo de control donde se almacenan los IDs.
     */
    private String controlFile;

    /**
     * Mapa que asocia el nombre de cada archivo con su último ID utilizado.
     */
    private Map<String, Integer> idMap;

    /**
     * Constructor que inicializa el gestor de IDs.
     * Verifica o crea el archivo de control y carga los IDs existentes.
     *
     * @param controlFile Nombre del archivo de control.
     * @throws IOException Si ocurre un error al acceder o crear el archivo de control.
     */
    public Idcontrol(String controlFile) throws IOException {
        this.controlFile = controlFile;
        this.idMap = new HashMap<>();
        initializeFile(); // Asegura que el archivo existe o lo crea si no es así
        loadIds();
    }

    /**
     * Verifica si el archivo de control existe, y lo crea si no es así.
     *
     * @throws IOException Si ocurre un error al crear el archivo.
     */
    private void initializeFile() throws IOException {
        File file = new File(controlFile);
        if (!file.exists()) {
            // Crea el archivo vacío si no existe
            if (!file.createNewFile()) {
                throw new IOException("No se pudo crear el archivo de control: " + controlFile);
            }
        }
    }

    /**
     * Carga los IDs almacenados en el archivo de control.
     * Los datos se almacenan en el mapa {@code idMap}.
     *
     * @throws IOException Si ocurre un error al leer el archivo de control o el formato es incorrecto.
     */
    private void loadIds() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(controlFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("=");
                if (parts.length != 2) {
                    throw new IOException("Formato de línea incorrecto en el archivo de control: " + line);
                }
                try {
                    idMap.put(parts[0], Integer.parseInt(parts[1]));
                } catch (NumberFormatException e) {
                    throw new IOException("ID inválido en el archivo de control: " + parts[1], e);
                }
            }
        }
    }

    /**
     * Guarda los IDs actuales en el archivo de control.
     *
     * @throws IOException Si ocurre un error al escribir en el archivo de control.
     */
    private void saveIds() throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(controlFile))) {
            for (Map.Entry<String, Integer> entry : idMap.entrySet()) {
                writer.write(entry.getKey() + "=" + entry.getValue());
                writer.newLine();
            }
        }
    }

    /**
     * Obtiene el siguiente ID único para un archivo específico y lo actualiza en el archivo de control.
     *
     * @param fileName Nombre del archivo para el cual se requiere el siguiente ID.
     * @return El siguiente ID único para el archivo especificado.
     * @throws IOException Si ocurre un error al actualizar el archivo de control.
     * @throws IllegalArgumentException Si el nombre del archivo es nulo o vacío.
     */
    public synchronized int getNextId(String fileName) throws IOException {
        if (fileName == null || fileName.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del archivo no puede ser nulo o vacío.");
        }

        int nextId = idMap.getOrDefault(fileName, 1);
        idMap.put(fileName, nextId + 1);
        saveIds();
        return nextId;
    }
}
