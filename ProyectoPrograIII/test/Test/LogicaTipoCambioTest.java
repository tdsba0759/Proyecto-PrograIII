package Test;

import LogicaNegocio.LogicaTipoCambio;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class LogicaTipoCambioTest {

    private LogicaTipoCambio logicaTipoCambio;

    @Before
    public void setUp() {
// Usar una implementación real del servicio de acceso a datos de tipo de cambio
        logicaTipoCambio = new LogicaTipoCambio();
    }

    @Test
    public void testObtenerTipoCambio() throws Exception {
        // Configurar parámetros de prueba
        String indicador = "317";
        String fechaInicio = "17/12/2024";
        String fechaFinal = "17/12/2024";
        String nombre = "David";
        String subniveles = "N";
        String email = "dmsdavidmonterososa@gmail.com";
        String token = "22OOIA2PA1";

        // Llamar al método que se está probando
        String result = logicaTipoCambio.obtenerTipoCambio(indicador, fechaInicio, fechaFinal, nombre, subniveles, email, token);

        // Verificar que el resultado no es nulo (puedes ajustar esto según los valores esperados)
        assertNotNull(result);
    }

}
