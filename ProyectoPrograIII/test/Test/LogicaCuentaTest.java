package Test;

import LogicaNegocio.LogicaCuenta;
import Seguridad.LogicaEncriptacion;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.IOException;

public class LogicaCuentaTest {

    private LogicaCuenta logicaCuenta;

    @BeforeEach
    public void setUp() throws IOException {
        logicaCuenta = new LogicaCuenta();
        logicaCuenta.accesoDatos.setNombreArchivo("usuarios.txt");

        // Simulamos algunos datos de prueba en el archivo de usuarios
        logicaCuenta.accesoDatos.agregarRegistro("12345,Usuario1,100.0,hashedpin1");
        logicaCuenta.accesoDatos.agregarRegistro("67890,Usuario2,200.0,hashedpin2");
    }

    @Test
    public void testExisteCuenta() throws IOException {
        // Verificar que la cuenta existe
        assertTrue(logicaCuenta.existeCuenta("12345"));

        // Verificar que la cuenta no existe
        assertFalse(logicaCuenta.existeCuenta("99999"));
    }

    @Test
    public void testCrearNuevaCuenta() throws Exception {
        // Crear una nueva cuenta
        logicaCuenta.crearNuevaCuenta("11111", "Usuario3", 300.0, "newpin");

        // Verificar que la nueva cuenta fue creada
        assertTrue(logicaCuenta.existeCuenta("11111"));
    }

    @Test
    public void testCrearNuevaCuentaYaExiste() {
        // Intentar crear una cuenta con un número de cuenta que ya existe
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            logicaCuenta.crearNuevaCuenta("12345", "Usuario1", 100.0, "pin");
        });

        String expectedMessage = "El número de cuenta ya existe.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testValidarCredenciales() throws Exception {
        LogicaEncriptacion encriptacion = new LogicaEncriptacion();

        // Encriptamos el pin para la comparación
        String hashedPin1 = encriptacion.encriptarPin("pin1");
        String hashedPin2 = encriptacion.encriptarPin("pin2");

        // Simulamos el hash en el archivo usuarios para las pruebas
        logicaCuenta.accesoDatos.modificarRegistro("12345,Usuario1,100.0," + hashedPin1);
        logicaCuenta.accesoDatos.modificarRegistro("67890,Usuario2,200.0," + hashedPin2);

        // Validar credenciales correctas
        assertTrue(logicaCuenta.validarCredenciales("12345", "pin1"));
        assertTrue(logicaCuenta.validarCredenciales("67890", "pin2"));

        // Validar credenciales incorrectas
        assertFalse(logicaCuenta.validarCredenciales("12345", "wrongpin"));
        assertFalse(logicaCuenta.validarCredenciales("67890", "wrongpin"));
    }
}
