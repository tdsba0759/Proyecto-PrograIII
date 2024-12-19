package AccesoDatos;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

/**
 * Clase para acceder a los datos del tipo de cambio a través de un servicio web.
 * Implementa la interfaz {@link Servicios.ServicioAccesoDatosTipoCambio}.
 * Proporciona métodos para construir solicitudes SOAP y parsear las respuestas.
 * 
 */
public class Acceso_Datos_Tipo_Cambio implements Servicios.ServicioAccesoDatosTipoCambio {

    /**
     * Constructor por defecto.
     */
    public Acceso_Datos_Tipo_Cambio() {
    }

    /**
     * Obtiene el tipo de cambio a través de una solicitud SOAP al servicio web.
     * 
     * @param indicador El código del indicador (por ejemplo, 317 para compra, 318 para venta).
     * @param fechaInicio La fecha de inicio de la consulta en formato dd/MM/yyyy.
     * @param fechaFinal La fecha final de la consulta en formato dd/MM/yyyy.
     * @param nombre El nombre del solicitante.
     * @param subniveles Indica si se incluyen subniveles (S/N).
     * @param email El correo electrónico del solicitante.
     * @param token El token de autenticación.
     * @return El tipo de cambio como cadena de texto.
     * @throws Exception Si ocurre un error en la conexión o al parsear la respuesta.
     */
    @Override
    public String obtenerTipoCambio(String indicador, String fechaInicio, String fechaFinal, String nombre, String subniveles, String email, String token) throws Exception {
        String endpoint = "https://gee.bccr.fi.cr/Indicadores/Suscripciones/WS/wsindicadoreseconomicos.asmx";
        URL url = new URL(endpoint);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "text/xml; charset=utf-8");
        connection.setRequestProperty("SOAPAction", "http://ws.sdde.bccr.fi.cr/ObtenerIndicadoresEconomicosXML");
        connection.setDoOutput(true);

        String soapRequest = buildSoapRequest(indicador, fechaInicio, fechaFinal, nombre, subniveles, email, token);

        try (OutputStream os = connection.getOutputStream()) {
            os.write(soapRequest.getBytes("UTF-8"));
        }

        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            return parseResponse(response.toString());
        } else {
            throw new Exception("Error en la conexión: Código de respuesta HTTP: " + responseCode);
        }
    }

    /**
     * Construye la solicitud SOAP para obtener el tipo de cambio.
     * 
     * @param indicador El código del indicador.
     * @param fechaInicio La fecha de inicio de la consulta.
     * @param fechaFinal La fecha final de la consulta.
     * @param nombre El nombre del solicitante.
     * @param subniveles Indica si se incluyen subniveles (S/N).
     * @param email El correo electrónico del solicitante.
     * @param token El token de autenticación.
     * @return La solicitud SOAP como cadena de texto.
     */
    @Override
    public String buildSoapRequest(String indicador, String fechaInicio, String fechaFinal, String nombre, String subniveles, String email, String token) {
        return "<?xml version=\"1.0\" encoding=\"utf-8\"?>"
                + "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" "
                + "xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" "
                + "xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">"
                + "<soap:Body>"
                + "<ObtenerIndicadoresEconomicosXML xmlns=\"http://ws.sdde.bccr.fi.cr\">"
                + "<Indicador>" + indicador + "</Indicador>"
                + "<FechaInicio>" + fechaInicio + "</FechaInicio>"
                + "<FechaFinal>" + fechaFinal + "</FechaFinal>"
                + "<Nombre>" + nombre + "</Nombre>"
                + "<SubNiveles>" + subniveles + "</SubNiveles>"
                + "<CorreoElectronico>" + email + "</CorreoElectronico>"
                + "<Token>" + token + "</Token>"
                + "</ObtenerIndicadoresEconomicosXML>"
                + "</soap:Body>"
                + "</soap:Envelope>";
    }

    /**
     * Parsea la respuesta XML del servicio web para obtener el tipo de cambio.
     * 
     * @param responseXml La respuesta XML como cadena de texto.
     * @return El tipo de cambio como cadena de texto, o un mensaje de error si no se encuentra el valor.
     * @throws Exception Si ocurre un error al parsear la respuesta.
     */
    @Override
    public String parseResponse(String responseXml) throws Exception {
        // Se decodifican las entidades HTML en la respuesta XML
        String decodedXml = responseXml.replace("&lt;", "<").replace("&gt;", ">");
        
        // Se crea un parser XML para parsear la respuesta
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(new ByteArrayInputStream(decodedXml.getBytes(StandardCharsets.UTF_8)));

        // Se obtiene el valor del tipo de cambio desde el XML
        NodeList valueNodeList = document.getElementsByTagName("NUM_VALOR");
        if (valueNodeList.getLength() > 0) {
            return valueNodeList.item(0).getTextContent();
        } else {
            return "No se encontró el valor de tipo de cambio en la respuesta.";
        }
    }
}
