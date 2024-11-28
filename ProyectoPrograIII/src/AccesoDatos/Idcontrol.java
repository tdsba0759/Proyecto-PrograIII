
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
 *
 * @author dmsda
 */
public class Idcontrol {
     //Variables 
    private String controlFile;
    private Map<String, Integer> idMap;

    public Idcontrol(String controlFile) throws IOException {
        this.controlFile = controlFile;
        this.idMap = new HashMap<>();
        initializeFile(); // Asegura que el archivo existe o lo crea si no es así
        loadIds();
    }

    // Verifica si el archivo existe y lo crea si no existe
    private void initializeFile() throws IOException {
        File file = new File(controlFile);
        if (!file.exists()) {
            // Crea el archivo vacío si no existe
            if (!file.createNewFile()) {
                throw new IOException("No se pudo crear el archivo de control: " + controlFile);
            }
        }
    }

    // Carga los IDs del archivo
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

    // Guarda los IDs en el archivo
    private void saveIds() throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(controlFile))) {
            for (Map.Entry<String, Integer> entry : idMap.entrySet()) {
                writer.write(entry.getKey() + "=" + entry.getValue());
                writer.newLine();
            }
        }
    }

    // Obtiene el siguiente ID y actualiza el archivo
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
