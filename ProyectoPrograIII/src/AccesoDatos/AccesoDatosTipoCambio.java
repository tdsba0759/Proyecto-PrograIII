

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
 *
 * @author dmsda
 */
public class AccesoDatosTipoCambio implements Servicios.ServicioAccesoDatosTipoCambio {

    private String token;  // Ahora es un String

    public AccesoDatosTipoCambio(String token) {
        this.token = token;  // El token sigue siendo un String
    }

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
                + "<Token>" + token + "</Token>"  // Aquí sigue siendo un String
                + "</ObtenerIndicadoresEconomicosXML>"
                + "</soap:Body>"
                + "</soap:Envelope>";
    }

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
            return "No se encontró el valor de tipo de cambio en la respuesta.";
        }
    }
}
