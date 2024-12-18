
package Test;

import LogicaNegocio.LogicaTransaccion;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.io.IOException;
import java.util.ArrayList;

public class LogicaTransaccionTest {

    private LogicaTransaccion logicaTransaccion;

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

    @Test
    public void testConsultarSaldo() throws Exception {
        // Verificar el saldo de la cuenta 11111
        double saldo = logicaTransaccion.consultarSaldo("11111");
        assertEquals(300.0, saldo, 0.0);
    }

    @Test(expected = Exception.class)
    public void testConsultarSaldoCuentaNoEncontrada() throws Exception {
        // Intentar consultar el saldo de una cuenta que no existe
        logicaTransaccion.consultarSaldo("33333");
    }

    @Test
    public void testDepositar() throws Exception {
        // Depositar dinero en la cuenta 11111
        logicaTransaccion.depositar("11111", 100.0, 0.0, 0.0);
        double saldo = logicaTransaccion.consultarSaldo("11111");
        assertEquals(400.0, saldo, 0.0);
    }

    @Test
    public void testRetirar() throws Exception {
        // Retirar dinero de la cuenta 22222
        logicaTransaccion.retirar("22222", 200.0, 0.0, 0.0);
        double saldo = logicaTransaccion.consultarSaldo("22222");
        assertEquals(300.0, saldo, 0.0);
    }

    @Test(expected = Exception.class)
    public void testRetirarSaldoInsuficiente() throws Exception {
        // Intentar retirar más dinero del que hay en la cuenta 11111
        logicaTransaccion.retirar("11111", 400.0, 0.0, 0.0);
    }

    @Test
    public void testTransferir() throws Exception {
        // Transferir dinero de la cuenta 22222 a la cuenta 11111
        logicaTransaccion.transferir("22222", "11111", 200.0, 0.0, 0.0);
        double saldoOrigen = logicaTransaccion.consultarSaldo("22222");
        double saldoDestino = logicaTransaccion.consultarSaldo("11111");
        assertEquals(300.0, saldoOrigen, 0.0);
        assertEquals(500.0, saldoDestino, 0.0);
    }

    @Test(expected = Exception.class)
    public void testTransferirSaldoInsuficiente() throws Exception {
        // Intentar transferir más dinero del que hay en la cuenta de origen
        logicaTransaccion.transferir("22222", "11111", 600.0, 0.0, 0.0);
    }

    @Test
    public void testRegistrarBalance() throws Exception {
        // Registrar un balance para la cuenta 11111
        logicaTransaccion.registrarBalance("11111", 300.0, 400.0, "Deposito", 100.0);
        ArrayList<String[]> balances = logicaTransaccion.leerBalance("11111");
        assertFalse(balances.isEmpty());
    }

    @Test
    public void testLeerBalance() throws Exception {
        // Leer el balance para la cuenta 11111
        logicaTransaccion.registrarBalance("11111", 300.0, 400.0, "Deposito", 100.0);
        ArrayList<String[]> balances = logicaTransaccion.leerBalance("11111");
        assertFalse(balances.isEmpty());
    }

    @Test(expected = IOException.class)
    public void testLeerBalanceCuentaNoEncontrada() throws Exception {
        // Intentar leer el balance de una cuenta que no existe
        logicaTransaccion.leerBalance("33333");
    }
}
