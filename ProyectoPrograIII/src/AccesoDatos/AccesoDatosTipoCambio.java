package AccesoDatos;

import java.io.OutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;

/**
 * Clase que implementa el acceso a los datos del tipo de cambio mediante un servicio SOAP.
 * Proporciona métodos para realizar solicitudes y procesar respuestas relacionadas con
 * indicadores económicos publicados por el Banco Central de Costa Rica.
 *
 * @author dmsda
 */
public class AccesoDatosTipoCambio implements Servicios.ServicioAccesoDatosTipoCambio {

    /**
     * Token de autenticación para el servicio SOAP.
     */
    private String token;

    /**
     * Constructor que inicializa el objeto con el token de autenticación.
     *
     * @param token Token de autenticación proporcionado para el servicio.
     */
    public AccesoDatosTipoCambio(String token) {
        this.token = token;
    }

    /**
     * Obtiene el tipo de cambio utilizando los parámetros especificados.
     *
     * @param indicador Código del indicador económico.
     * @param fechaInicio Fecha de inicio en formato "yyyy-MM-dd".
     * @param fechaFinal Fecha final en formato "yyyy-MM-dd".
     * @param nombre Nombre del solicitante.
     * @param subniveles Indicador de inclusión de subniveles ("S" o "N").
     * @param email Correo electrónico del solicitante.
     * @return Valor del tipo de cambio como una cadena.
     * @throws Exception Si ocurre algún error en la conexión o procesamiento de la respuesta.
     */
    @Override
    public String obtenerTipoCambio(String indicador, String fechaInicio, String fechaFinal, String nombre, String subniveles, String email) throws Exception {
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
     * Construye el cuerpo de la solicitud SOAP utilizando los parámetros proporcionados.
     *
     * @param indicador Código del indicador económico.
     * @param fechaInicio Fecha de inicio en formato "yyyy-MM-dd".
     * @param fechaFinal Fecha final en formato "yyyy-MM-dd".
     * @param nombre Nombre del solicitante.
     * @param subniveles Indicador de inclusión de subniveles ("S" o "N").
     * @param email Correo electrónico del solicitante.
     * @param token Token de autenticación.
     * @return Cadena que representa la solicitud SOAP.
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
     * Analiza la respuesta SOAP y extrae el valor del tipo de cambio.
     *
     * @param responseXml Respuesta SOAP en formato XML como cadena.
     * @return Valor del tipo de cambio como una cadena.
     * @throws Exception Si ocurre un error al analizar la respuesta.
     */
    @Override
    public String parseResponse(String responseXml) throws Exception {
        String decodedXml = responseXml.replace("&lt;", "<").replace("&gt;", ">");
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(new ByteArrayInputStream(decodedXml.getBytes(StandardCharsets.UTF_8)));

        NodeList valueNodeList = document.getElementsByTagName("NUM_VALOR");
        if (valueNodeList.getLength() > 0) {
            return valueNodeList.item(0).getTextContent();
        } else {
            return "No se encontró el valor de tipo de cambio.";
        }
    }
}
