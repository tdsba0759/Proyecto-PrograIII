package Test;

import LogicaNegocio.LogicaTransaccion;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.io.IOException;
import java.util.ArrayList;

/**
 * La clase {@code LogicaTransaccionTest} contiene pruebas unitarias para la clase
 * {@code LogicaTransaccion}. Utiliza JUnit para probar las operaciones de transacciones
 * bancarias, como la consulta de saldo, depósitos, retiros, transferencias y la gestión
 * de balances. Estas pruebas verifican que el comportamiento de la clase sea correcto
 * en diversas situaciones.
 * 
 * <p>Las pruebas cubren los siguientes aspectos del sistema:</p>
 * <ul>
 *   <li>{@link LogicaTransaccion#consultarSaldo(String)}: Verifica que se puede consultar
 *       el saldo de una cuenta existente.</li>
 *   <li>{@link LogicaTransaccion#depositar(String, double, double, double)}: Verifica que
 *       se puede realizar un depósito exitoso en una cuenta.</li>
 *   <li>{@link LogicaTransaccion#retirar(String, double, double, double)}: Verifica que
 *       se puede realizar un retiro exitoso de una cuenta.</li>
 *   <li>{@link LogicaTransaccion#transferir(String, String, double, double, double)}: Verifica
 *       que se puede realizar una transferencia exitosa entre dos cuentas.</li>
 *   <li>{@link LogicaTransaccion#registrarBalance(String, double, double, String, double)}: 
 *       Verifica que se pueden registrar balances para una cuenta.</li>
 *   <li>{@link LogicaTransaccion#leerBalance(String)}: Verifica que se puede leer el balance
 *       de una cuenta específica.</li>
 * </ul>
 * 
 * <p>Los métodos están diseñados para manejar casos exitosos así como excepciones,
 * como el intento de realizar operaciones con cuentas no existentes o saldo insuficiente.</p>
 * 
 * @autor dmsda
 */
public class LogicaTransaccionTest {

    private LogicaTransaccion logicaTransaccion;

    /**
     * Configura el entorno de prueba inicializando una instancia de
     * {@code LogicaTransaccion} y creando datos iniciales para las pruebas.
     * 
     * @throws IOException Si ocurre un error al escribir los datos en el archivo.
     */
    @Before
    public void setUp() throws IOException {
        logicaTransaccion = new LogicaTransaccion();
        logicaTransaccion.accesoDatos.setNombreArchivo("usuariostest.txt");

        // Crear datos iniciales para las pruebas
        ArrayList<String[]> registros = new ArrayList<>();
        registros.add(new String[]{"11111", "Usuario1", "300.0", "1234"});
        registros.add(new String[]{"22222", "Usuario2", "500.0", "1234"});

        // Escribir datos en el archivo
        logicaTransaccion.accesoDatos.escribirRegistros(registros);
    }

    /**
     * Prueba que verifica la consulta del saldo de una cuenta existente.
     * 
     * @throws Exception Si ocurre un error al consultar el saldo.
     */
    @Test
    public void testConsultarSaldo() throws Exception {
        // Verificar el saldo de la cuenta 11111
        double saldo = logicaTransaccion.consultarSaldo("11111");
        assertEquals(300.0, saldo, 0.0);
    }

    /**
     * Prueba que verifica el intento de consultar el saldo de una cuenta no existente.
     * 
     * @throws Exception Si ocurre un error al intentar consultar el saldo de una cuenta no encontrada.
     */
    @Test(expected = Exception.class)
    public void testConsultarSaldoCuentaNoEncontrada() throws Exception {
        // Intentar consultar el saldo de una cuenta que no existe
        logicaTransaccion.consultarSaldo("33333");
    }

    /**
     * Prueba que verifica el depósito de dinero en una cuenta.
     * 
     * @throws Exception Si ocurre un error al realizar el depósito.
     */
    @Test
    public void testDepositar() throws Exception {
        // Depositar dinero en la cuenta 11111
        logicaTransaccion.depositar("11111", 100.0, 0.0, 0.0);
        double saldo = logicaTransaccion.consultarSaldo("11111");
        assertEquals(400.0, saldo, 0.0);
    }

    /**
     * Prueba que verifica el retiro de dinero de una cuenta.
     * 
     * @throws Exception Si ocurre un error al realizar el retiro.
     */
    @Test
    public void testRetirar() throws Exception {
        // Retirar dinero de la cuenta 22222
        logicaTransaccion.retirar("22222", 200.0, 0.0, 0.0);
        double saldo = logicaTransaccion.consultarSaldo("22222");
        assertEquals(300.0, saldo, 0.0);
    }

    /**
     * Prueba que verifica el intento de retirar más dinero del disponible en una cuenta.
     * 
     * @throws Exception Si ocurre un error al intentar retirar más dinero del disponible.
     */
    @Test(expected = Exception.class)
    public void testRetirarSaldoInsuficiente() throws Exception {
        // Intentar retirar más dinero del que hay en la cuenta 11111
        logicaTransaccion.retirar("11111", 400.0, 0.0, 0.0);
    }

    /**
     * Prueba que verifica la transferencia de dinero entre dos cuentas.
     * 
     * @throws Exception Si ocurre un error al realizar la transferencia.
     */
    @Test
    public void testTransferir() throws Exception {
        // Transferir dinero de la cuenta 22222 a la cuenta 11111
        logicaTransaccion.transferir("22222", "11111", 200.0, 0.0, 0.0);
        double saldoOrigen = logicaTransaccion.consultarSaldo("22222");
        double saldoDestino = logicaTransaccion.consultarSaldo("11111");
        assertEquals(300.0, saldoOrigen, 0.0);
        assertEquals(500.0, saldoDestino, 0.0);
    }

    /**
     * Prueba que verifica el intento de transferir más dinero del disponible en la cuenta de origen.
     * 
     * @throws Exception Si ocurre un error al intentar realizar una transferencia con saldo insuficiente.
     */
    @Test(expected = Exception.class)
    public void testTransferirSaldoInsuficiente() throws Exception {
        // Intentar transferir más dinero del que hay en la cuenta de origen
        logicaTransaccion.transferir("22222", "11111", 600.0, 0.0, 0.0);
    }

    /**
     * Prueba que verifica el registro de un balance para una cuenta.
     * 
     * @throws Exception Si ocurre un error al registrar el balance.
     */
    @Test
    public void testRegistrarBalance() throws Exception {
        // Registrar un balance para la cuenta 11111
        logicaTransaccion.registrarBalance("11111", 300.0, 400.0, "Deposito", 100.0);
        ArrayList<String[]> balances = logicaTransaccion.leerBalance("11111");
        assertFalse(balances.isEmpty());
    }

    /**
     * Prueba que verifica la lectura del balance de una cuenta.
     * 
     * @throws Exception Si ocurre un error al leer el balance.
     */
    @Test
    public void testLeerBalance() throws Exception {
        // Leer el balance para la cuenta 11111
        logicaTransaccion.registrarBalance("11111", 300.0, 400.0, "Deposito", 100.0);
        ArrayList<String[]> balances = logicaTransaccion.leerBalance("11111");
        assertFalse(balances.isEmpty());
    }

    /**
     * Prueba que verifica el intento de leer el balance de una cuenta no existente.
     * 
     * @throws IOException Si ocurre un error al intentar leer el balance de una cuenta no encontrada.
     */
    @Test(expected = IOException.class)
    public void testLeerBalanceCuentaNoEncontrada() throws Exception {
        // Intentar leer el balance de una cuenta que no existe
        logicaTransaccion.leerBalance("33333");
    }
}
