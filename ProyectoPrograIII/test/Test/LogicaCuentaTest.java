package Test;

import LogicaNegocio.LogicaCuenta;
import Seguridad.LogicaEncriptacion;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.io.IOException;

/**
 * La clase {@code LogicaCuentaTest} contiene pruebas unitarias para la clase
 * {@code LogicaCuenta}. Utiliza JUnit para probar las funcionalidades de la
 * creación de cuentas, validación de credenciales y verificación de existencia
 * de cuentas en el sistema.
 * 
 * <p>Las pruebas cubren los siguientes métodos de la clase {@code LogicaCuenta}:</p>
 * <ul>
 *   <li>{@link LogicaCuenta#existeCuenta(String)}: Verifica si una cuenta
 *       existe en el archivo de datos.</li>
 *   <li>{@link LogicaCuenta#crearNuevaCuenta(String, String, double, String)}:
 *       Crea una nueva cuenta y la guarda en el archivo de datos.</li>
 *   <li>{@link LogicaCuenta#validarCredenciales(String, String)}: Valida las
 *       credenciales de un usuario comparando el número de cuenta y el PIN
 *       encriptado.</li>
 * </ul>
 * 
 * <p>La clase utiliza un archivo de prueba {@code usuariostest.txt} para simular
 * la creación y validación de cuentas.</p>
 * 
 * @autor dmsda
 */
public class LogicaCuentaTest {

    private LogicaCuenta logicaCuenta;

    /**
     * Configura el entorno de prueba inicializando una instancia de
     * {@code LogicaCuenta} y configurando el archivo de usuarios de prueba.
     * 
     * @throws IOException Si ocurre un error al configurar el entorno de prueba.
     */
    @Before
    public void setUp() throws IOException {
        logicaCuenta = new LogicaCuenta();
        logicaCuenta.accesoDatos.setNombreArchivo("usuariostest.txt");
    }

    /**
     * Prueba que verifica si una cuenta no existe en el archivo de datos.
     * 
     * @throws IOException Si ocurre un error al verificar la existencia de la cuenta.
     */
    @Test
    public void testExisteCuenta() throws IOException {
        // Verificar que la cuenta no existe
        assertFalse(logicaCuenta.existeCuenta("11111"));
    }

    /**
     * Prueba que verifica la creación de una nueva cuenta y su almacenamiento
     * en el archivo de datos.
     * 
     * @throws Exception Si ocurre un error durante la creación de la cuenta.
     */
    @Test
    public void testCrearNuevaCuenta() throws Exception {
        // Crear una nueva cuenta
        logicaCuenta.crearNuevaCuenta("11111", "Usuario1", 300.0, "1234");

        // Verificar que la nueva cuenta fue creada
        assertTrue(logicaCuenta.existeCuenta("11111"));
    }

    /**
     * Prueba que valida las credenciales de un usuario, tanto para credenciales
     * correctas como incorrectas.
     * 
     * @throws Exception Si ocurre un error durante la validación de credenciales.
     */
    @Test
    public void testValidarCredenciales() throws Exception {
        LogicaEncriptacion encriptacion = new LogicaEncriptacion();

        // Encriptamos el pin para la comparación
        String hashedPin1 = encriptacion.encriptarPin("1234");

        // Simulamos el hash en el archivo usuarios para las pruebas
        logicaCuenta.accesoDatos.modificarRegistro("11111,Usuario1,300.0," + hashedPin1);

        // Validar credenciales correctas
        assertTrue(logicaCuenta.validarCredenciales("11111", "1234"));

        // Validar credenciales incorrectas
        assertFalse(logicaCuenta.validarCredenciales("11111", "4321"));
    }
}
