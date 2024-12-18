package Test;

import LogicaNegocio.LogicaCuenta;
import Seguridad.LogicaEncriptacion;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.io.IOException;

public class LogicaCuentaTest {

    private LogicaCuenta logicaCuenta;

    @Before
    public void setUp() throws IOException {
        logicaCuenta = new LogicaCuenta();
        logicaCuenta.accesoDatos.setNombreArchivo("usuariostest.txt");

    }

    @Test
    public void testExisteCuenta() throws IOException {
        // Verificar que la cuenta no existe
         assertFalse(logicaCuenta.existeCuenta("11111"));
    }

    @Test
    public void testCrearNuevaCuenta() throws Exception {
        // Crear una nueva cuenta
        logicaCuenta.crearNuevaCuenta("11111", "Usuario1", 300.0, "1234");

        // Verificar que la nueva cuenta fue creada
        assertTrue(logicaCuenta.existeCuenta("11111"));
    }

 

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
