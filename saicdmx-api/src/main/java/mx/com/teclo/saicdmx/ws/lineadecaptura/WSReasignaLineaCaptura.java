package mx.com.teclo.saicdmx.ws.lineadecaptura;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import mx.com.teclo.saicdmx.persistencia.vo.lineadecaptura.BitReasignacionLineaCapturaVO;
import mx.com.teclo.saicdmx.persistencia.vo.lineadecaptura.ConsultaInfraccionForReasignacionVO;
import mx.com.teclo.saicdmx.persistencia.vo.lineadecaptura.RespuestaWSReasignaLineaCapturaVO;

public class WSReasignaLineaCaptura {

	public WSReasignaLineaCaptura(){}
	
	public RespuestaWSReasignaLineaCapturaVO servicio(ConsultaInfraccionForReasignacionVO objectVO, String urlws,String urlProxyLc) throws ProtocolException, IOException, ParserConfigurationException, SAXException {
		RespuestaWSReasignaLineaCapturaVO response = new RespuestaWSReasignaLineaCapturaVO();
		BitReasignacionLineaCapturaVO peticionWS = new BitReasignacionLineaCapturaVO();
		//try {
            URL url = new URL(urlws);
            String xmlInput =
                    "<SOAP-ENV:Envelope SOAP-ENV:encodingStyle=\"http://schemas.xmlsoap.org/soap/encoding/\" " +
                    "xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\" " +
                    "xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" " +
                    "xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" " +
                    "xmlns:SOAP-ENC=\"http://schemas.xmlsoap.org/soap/encoding/\" " +
                    "xmlns:tns=\""+ urlws+"\">"
                    + "<SOAP-ENV:Body>"
                    + "<tns:solicitar_lc_autodeterminada xmlns:tns=\""+urlws+"\">"
                    + "<pregunta xsi:type=\"SOAP-ENC:Array\" SOAP-ENC:arrayType=\"tns:tipo_pregunta_lc_determinada[1]\">"
                    + "<item xsi:type=\"tns:tipo_pregunta_lc_determinada\">"
                    + "<folio xsi:type=\"xsd:string\">" + objectVO.getFolio() + "</folio>"
                    + "<fechaInfraccion xsi:type=\"xsd:string\">" + objectVO.getFechaInfraccion() + "</fechaInfraccion>"
                    + "<fechaReasigna xsi:type=\"xsd:string\">" + objectVO.getFechaEmision() + "</fechaReasigna>"
                    + "<dias xsi:type=\"xsd:decimal\">" + objectVO.getDias() + "</dias>"
                    + "<salarioMinimo xsi:type=\"xsd:decimal\">" + objectVO.getSalarioMinimo() + "</salarioMinimo>"
                    + "<importe xsi:type=\"xsd:decimal\">" + objectVO.getImporte() + "</importe>"
                    + "<derechos xsi:type=\"xsd:decimal\">" + objectVO.getDerechos() + "</derechos>"
                    + "<clave xsi:type=\"xsd:decimal\">" + "03" + "</clave>"
                    + "<condonacion xsi:type=\"xsd:decimal\">" + objectVO.getAplicaCondonacion() + "</condonacion>"
                    + "<usuario xsi:type=\"xsd:string\">" + "ssp_infraccion" + "</usuario>"
                    + "<password xsi:type=\"xsd:string\">" + "c91f6bc53f25218e34c7c48020b2bcaf" + "</password>"
                    + "</item>"
                    + "</pregunta>"
                    + "</tns:solicitar_lc_autodeterminada>"
                    +"</SOAP-ENV:Body>"
                    +"</SOAP-ENV:Envelope>";
            
            String[] proxy = urlProxyLc.split(":");
            
            String proxyLC  = proxy[0];
            String puertoLC = proxy[1];

            response = consultaWebService(url, xmlInput,proxyLC,puertoLC);
            response.getPeticionWS().setUrlWebservice(urlws);
            response.getPeticionWS().setFolio(objectVO.getFolio());
            response.getPeticionWS().setFechaInfraccion(objectVO.getFechaInfraccion());
            response.getPeticionWS().setFechaEmision(objectVO.getFechaEmision());
            response.getPeticionWS().setDias((Long.valueOf(objectVO.getDias())));
            response.getPeticionWS().setSalarioMinimo(objectVO.getSalarioMinimo());
            response.getPeticionWS().setImporte(objectVO.getImporte());
            response.getPeticionWS().setDerechos((Long.valueOf(objectVO.getDerechos())));
            response.getPeticionWS().setAplicaCondonacion(objectVO.getAplicaCondonacion());
            response.getPeticionWS().setCadenaXML(xmlInput);
            
            //response.setCadenaxml(xmlInput);
        //} catch (Exception e) {
        	//throw new BusinessException(e.getMessage());
//        	  RespuestaWSReasignaLineaCapturaVO resp = new RespuestaWSReasignaLineaCapturaVO();
//            resp.setError("100");
//            resp.setError_descripcion(e.getMessage());
//            e.printStackTrace();
//            return resp;
        //}
        return response;
    }

    @SuppressWarnings("finally")
	private RespuestaWSReasignaLineaCapturaVO consultaWebService(URL url, String xmlRequest,String proxyLC,String puertoProxyLC) throws IOException, ProtocolException, ParserConfigurationException, SAXException {
    	RespuestaWSReasignaLineaCapturaVO responseWs = new RespuestaWSReasignaLineaCapturaVO();
    	BitReasignacionLineaCapturaVO peticionWS = new BitReasignacionLineaCapturaVO();
        try {
            // Code to make a webservice HTTP request
            String responseString = "";
            StringBuffer outputString = new StringBuffer();
            //System.setProperty("https.proxyHost", "172.18.44.116");//LOCAL
            //System.setProperty("https.proxyHost", "172.40.27.20");//CORRECTO
            //System.setProperty("https.proxyPort", "3128");
            System.setProperty("https.proxyHost", proxyLC);//CORRECTO
            System.setProperty("https.proxyPort", puertoProxyLC);
            System.setProperty("http.proxyHost", proxyLC);//CORRECTO
            System.setProperty("http.proxyPort", puertoProxyLC);
            URLConnection connection = url.openConnection();
            HttpURLConnection httpConn = (HttpURLConnection) connection;
            ByteArrayOutputStream bout = new ByteArrayOutputStream();
            byte[] buffer = new byte[xmlRequest.length()];
            buffer = xmlRequest.getBytes();
            bout.write(buffer);
            byte[] b = bout.toByteArray();
            
            // Set the appropriate HTTP parameters
            httpConn.setRequestProperty("Content-Length", String.valueOf(b.length));
            httpConn.setRequestProperty("Content-Type", "text/xml; charset=ISO-8859-1");
            httpConn.setRequestProperty("SOAPAction", "rpc");
            httpConn.setRequestMethod("POST");
            httpConn.setDoOutput(true);
            httpConn.setDoInput(true);
            OutputStream out = httpConn.getOutputStream();
            //Write the content of the request to the outputstream of the HTTP Connection.
            out.write(b);
            out.close();
            if (httpConn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                //Ready with sending the request.
                //Read the response.
                InputStreamReader isr = new InputStreamReader(httpConn.getInputStream());
                BufferedReader in = new BufferedReader(isr);

                //Write the SOAP message response to a String.
                while ((responseString = in.readLine()) != null) {
                    outputString.append(responseString);
                }
        		//System.out.println(outputString);
                //Parse the String output to a org.w3c.dom.Document and be able to reach every node with the org.w3c.dom API.
                Document document = parseXmlFile(outputString.toString());
                responseWs.setFolio(extraeInfo(document, "folio"));
                responseWs.setFecha_infraccion(extraeInfo(document, "fecha_infraccion"));
                responseWs.setSalario_minimo(extraeInfo(document, "salario_minimo"));
                responseWs.setNum_dias(extraeInfo(document, "num_dias"));
                responseWs.setImporte(extraeInfo(document, "importe"));
                responseWs.setDescuento(extraeInfo(document, "descuento"));
                responseWs.setRecargos(extraeInfo(document, "recargos"));
                responseWs.setTotal(extraeInfo(document, "total"));
                responseWs.setVigencia(extraeInfo(document, "vigencia"));
                responseWs.setLineacaptura(extraeInfo(document, "lineacaptura"));
                responseWs.setLineacapturaCB(extraeInfo(document, "lineacapturaCB"));
                responseWs.setError(extraeInfo(document, "error"));
                responseWs.setError_descripcion(extraeInfo(document, "error_descripcion"));
                
                peticionWS.setFolioRes(extraeInfo(document, "folio"));
                peticionWS.setFechaInfraccionRes(extraeInfo(document, "fecha_infraccion"));
                peticionWS.setSalarioMinimoRes((extraeInfo(document, "salario_minimo") != null) ? Double.valueOf(extraeInfo(document, "salario_minimo")) : null);
                peticionWS.setDiasRes((extraeInfo(document, "num_dias") != null) ? (Long.valueOf(extraeInfo(document, "num_dias"))) : null);
                peticionWS.setImporteRes((extraeInfo(document, "importe") != null) ? (Double.valueOf(extraeInfo(document, "importe"))) : null);
                peticionWS.setDescuentoRes((extraeInfo(document, "descuento") != null) ? (Double.valueOf(extraeInfo(document, "descuento"))) : null);
                peticionWS.setRecargosRes((extraeInfo(document, "recargos") != null) ? (Double.valueOf(extraeInfo(document, "recargos"))) : null);
                peticionWS.setTotalRes((extraeInfo(document, "total") != null) ? (Double.valueOf(extraeInfo(document, "total"))) : null);
                peticionWS.setVigenciaRes(extraeInfo(document, "vigencia"));
                peticionWS.setLineacapturaRes(extraeInfo(document, "lineacaptura"));
                peticionWS.setLineacapturaCBRes(extraeInfo(document, "lineacapturaCB"));
                peticionWS.setErrorRes((extraeInfo(document, "error") != null) ? (Long.valueOf(extraeInfo(document, "error"))) : null);
                peticionWS.setErrorDescripcionRes(extraeInfo(document, "error_descripcion"));
                peticionWS.setCadenaXMLRes(outputString.toString());
                responseWs.setPeticionWS(peticionWS);
                //responseWs.setCadenaxml(outputString.toString());
                // Write the SOAP message formatted to the console.
                // String formattedSOAPResponse = formatXML(outputString);
                // System.out.println(formattedSOAPResponse);
            } else {
            	InputStreamReader isr = new InputStreamReader(httpConn.getInputStream());
                BufferedReader in = new BufferedReader(isr);

                //Write the SOAP message response to a String.
                while ((responseString = in.readLine()) != null) {
                    outputString.append(responseString);
                }
        		//System.out.println(outputString);
                //Parse the String output to a org.w3c.dom.Document and be able to reach every node with the org.w3c.dom API.
                Document document = parseXmlFile(outputString.toString());
            	
            	
            	responseWs.setError("100");
                responseWs.setError_descripcion("Fallo de conexión");
                
                peticionWS.setErrorRes(Long.valueOf("100"));
                peticionWS.setErrorDescripcionRes("Fallo de conexión");
                responseWs.setPeticionWS(peticionWS);
            }
          } catch (IOException e) {
            e.printStackTrace();
            responseWs.setError("100");
            responseWs.setError_descripcion("Fallo de conexión");
            
            peticionWS.setErrorRes(Long.valueOf("100"));
            peticionWS.setErrorDescripcionRes("Fallo de conexión");
            responseWs.setPeticionWS(peticionWS);
          }finally{
        	  return responseWs;  
          }
    }

    private String extraeInfo(Document doc, String nombre) {
        String text = "";
        NodeList nodeList = doc.getElementsByTagName(nombre);
        // Si encontr el elemento
        if (nodeList.getLength() > 0) {
            Element nodeElement = (Element) nodeList.item(0);

            NodeList textList = nodeElement.getChildNodes();
            // Si contiene texto
            if (textList.getLength() > 0) {
                text = ((Node) textList.item(0)).getNodeValue().trim();
            }
        }

        return text;
    }

    private Document parseXmlFile(String in) throws ParserConfigurationException, SAXException, IOException {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            InputSource is = new InputSource(new StringReader(in));
            return db.parse(is);
        //} catch (ParserConfigurationException e) {
            //throw new RuntimeException(e);
        //} catch (SAXException e) {
            //throw new RuntimeException(e);
        //} catch (IOException e) {
            //throw new RuntimeException(e);
        //}
    }
}
