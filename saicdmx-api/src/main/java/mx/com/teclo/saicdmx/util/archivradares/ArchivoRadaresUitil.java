/**
 * 
 */
package mx.com.teclo.saicdmx.util.archivradares;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import mx.com.teclo.saicdmx.persistencia.vo.fm.FMDeteccionesCatLayoutVO;
import mx.com.teclo.saicdmx.persistencia.vo.radares.CargaArchivoComplementracionVO;
import mx.com.teclo.saicdmx.util.enumerados.ArchivosEnum;
import mx.com.teclo.saicdmx.util.enumerados.ArchivosNumberEnum;

/**
 * @author UnitisDes0416
 *
 */
public class ArchivoRadaresUitil {
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Map validacionProcesoCompletoSSP(String fileName, String rutaArchivo, CargaArchivoComplementracionVO radaresCatArchivoTipoVO, FMDeteccionesCatLayoutVO catLayoutVO) {
		Map resultado = new HashMap<>();
		List<Map> duplicados = new ArrayList<Map>();
		List<Map> errores = new ArrayList<Map>();
		List<Map> detecciones = new ArrayList<Map>();
		
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			InputSource is = new InputSource(new InputStreamReader(new FileInputStream(rutaArchivo+ fileName), "utf-8"));
			HashMap contenedorDetecciones = new HashMap();
			String key;
			Document document = db.parse(is);
			Element root = document.getDocumentElement();
			root.normalize();
			NodeList nodes = root.getChildNodes();
			
			for (int i = 0; i < nodes.getLength(); i++) {
				Node node = nodes.item(i);
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					Element deteccion = (Element) node;
					HashMap deteccionesValida = new HashMap();
					
					boolean tipDetect=validaTipoArchivo(extraeElemento(deteccion, "TIPODETECCION", true, false), ArchivosNumberEnum.FM_RADAR_VELOCIDAD_Jenoptik.getConstante(), ArchivosNumberEnum.FM_RADAR_VELOCIDAD_RedFlex.getConstante());
					boolean origenPlaca=validaTipoArchivo(extraeElemento(deteccion, "ORIGENPLACA", true, false), ArchivosNumberEnum.FM_ORIGEN_CDM.getConstante());
					
					deteccionesValida=asignaValores(catLayoutVO.getObligatorios(), deteccionesValida, deteccion, ArchivosNumberEnum.DATO_OBLIGATORIO.getConstante(), false);
					deteccionesValida=asignaValores(catLayoutVO.getOpcionaes(), deteccionesValida, deteccion, ArchivosNumberEnum.DATO_OPCIONAL.getConstante(), false);
					// Llave para validar detecciones duplicadas en e mismo lote
					key = getKeyDeteccion(deteccionesValida.get("PLACA"), deteccionesValida.get("FECHA"), deteccionesValida.get("HORA"), deteccionesValida.get("TDSKUID"));
					
					insertaDatos(contenedorDetecciones, key, detecciones, duplicados,							
					deteccionesValida, errores, tipDetect, origenPlaca, resultado, catLayoutVO.getCatalogosMap(),catLayoutVO.getObligatorios());
					
				}
			}
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
			resultado.put("resultado", false);
		} catch (SAXException e) {
			e.printStackTrace();
			resultado.put("resultado", false);
		} catch (IOException e) {
			e.printStackTrace();
			resultado.put("resultado", false);
		}
		
		return resultado;
	}
	
	/**
	 * Valida si el archivo de detecciones es válido con base al procesamiento
	 * foraneo. Guarda las detecciones en la variable de instancia detecciones.
	 *
	 * @param filename
	 *            : Nombre del archivo.
	 *
	 * @return Regresa un objeto de tipo <b>boolean</b>.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Map validacionProcesoForaneo(String fileName, String rutaArchivo, CargaArchivoComplementracionVO radaresCatArchivoTipoVO, FMDeteccionesCatLayoutVO catLayoutVO) {
		Map resultado = new HashMap<>();
		List<Map> duplicados = new ArrayList<Map>();
		List<Map> errores = new ArrayList<Map>();
		List<Map> detecciones = new ArrayList<Map>();
		
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			InputSource is = new InputSource(new InputStreamReader(new FileInputStream(rutaArchivo+ fileName), "utf-8"));
			HashMap contenedorDetecciones = new HashMap();
			String key=null;
			Document document = db.parse(is);
			Element root = document.getDocumentElement();
			root.normalize();
			NodeList nodes = root.getChildNodes();
			
			for (int i = 0; i < nodes.getLength(); i++) {
				Node node = nodes.item(i);
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					Element deteccion = (Element) node;
					HashMap deteccionesValida = new HashMap();
					
					boolean tipDetect=validaTipoArchivo(extraeElemento(deteccion, "TIPODETECCION", true, false), ArchivosNumberEnum.FM_RADAR_VELOCIDAD_Jenoptik.getConstante(), ArchivosNumberEnum.FM_RADAR_VELOCIDAD_RedFlex.getConstante());
					boolean origenPlaca=validaTipoArchivo(extraeElemento(deteccion, "ORIGENPLACA", true, false), ArchivosNumberEnum.FM_ORIGEN_FORANEO.getConstante());

					deteccionesValida=asignaValores(catLayoutVO.getObligatorios(), deteccionesValida, deteccion, ArchivosNumberEnum.DATO_OBLIGATORIO.getConstante(), false);
					deteccionesValida=asignaValores(catLayoutVO.getOpcionaes(), deteccionesValida, deteccion, ArchivosNumberEnum.DATO_OPCIONAL.getConstante(), false);
					// Llave para validar detecciones duplicadas en e mismo lote
					key = getKeyDeteccion(deteccionesValida.get("PLACA"), deteccionesValida.get("FECHA"), deteccionesValida.get("HORA"), deteccionesValida.get("TDSKUID"));
					
					insertaDatos(contenedorDetecciones, key, detecciones, duplicados,
					deteccionesValida, errores, tipDetect, origenPlaca, resultado, catLayoutVO.getCatalogosMap(),catLayoutVO.getObligatorios());
				}
			}
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}		
		return resultado;
	}
	@SuppressWarnings({ "unchecked", "rawtypes"})
	public Map validacionProcesoCompletoSSPConcesionado(String fileName,String rutaArchivo, CargaArchivoComplementracionVO radaresCatArchivoTipoVO, FMDeteccionesCatLayoutVO catLayoutVO) {
		Map resultado = new HashMap<>();
		List<Map> duplicados = new ArrayList<Map>();
		List<Map> errores = new ArrayList<Map>();
		List<Map> detecciones = new ArrayList<Map>();
		
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			InputSource is = new InputSource(new InputStreamReader(new FileInputStream(rutaArchivo+ fileName), "utf-8"));
			HashMap contenedorDetecciones = new HashMap();
			String key;
			Document document = db.parse(is);
			Element root = document.getDocumentElement();
			root.normalize();
			NodeList nodes = root.getChildNodes();

			for (int i = 0; i < nodes.getLength(); i++) {
				Node node = nodes.item(i);

				if (node.getNodeType() == Node.ELEMENT_NODE) {
					Element deteccion = (Element) node;
					HashMap deteccionesValida = new HashMap();
					
					boolean tipDetect=validaTipoArchivo(extraeElemento(deteccion, "TIPODETECCION", true, false), ArchivosNumberEnum.FM_RADAR_VELOCIDAD_CONSESIONADO_Redflex.getConstante());
					boolean origenPlaca=validaTipoArchivo(extraeElemento(deteccion, "ORIGENPLACA", true, false), ArchivosNumberEnum.FM_ORIGEN_CDM.getConstante());

					deteccionesValida=asignaValores(catLayoutVO.getObligatorios(), deteccionesValida, deteccion, ArchivosNumberEnum.DATO_OBLIGATORIO.getConstante(), false);
					deteccionesValida=asignaValores(catLayoutVO.getOpcionaes(), deteccionesValida, deteccion, ArchivosNumberEnum.DATO_OPCIONAL.getConstante(), false);
					
					// Llave para validar detecciones duplicadas en el mismo lote
					key = getKeyDeteccion(deteccionesValida.get("PLACA"), deteccionesValida.get("FECHA"), deteccionesValida.get("HORA"), deteccionesValida.get("TDSKUID"));

					insertaDatos(contenedorDetecciones, key, detecciones, duplicados,
					deteccionesValida, errores, tipDetect, origenPlaca, resultado, catLayoutVO.getCatalogosMap(),catLayoutVO.getObligatorios());

				}
			}
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return resultado;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Map validacionProcesoCompletoSSPForaneoConcesionado(String fileName, String rutaArchivo, CargaArchivoComplementracionVO radaresCatArchivoTipoVO, FMDeteccionesCatLayoutVO catLayoutVO) {
		Map resultado = new HashMap<>();
		List<Map> duplicados = new ArrayList<Map>();
		List<Map> errores = new ArrayList<Map>();
		List<Map> detecciones = new ArrayList<Map>();
		
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			InputSource is = new InputSource(new InputStreamReader(new FileInputStream(rutaArchivo+ fileName), "utf-8"));
			HashMap contenedorDetecciones = new HashMap();
			String key;
			Document document = db.parse(is);
			Element root = document.getDocumentElement();
			root.normalize();
			NodeList nodes = root.getChildNodes();
			
			for (int i = 0; i < nodes.getLength(); i++) {
				Node node = nodes.item(i);

				if (node.getNodeType() == Node.ELEMENT_NODE) {
					Element deteccion = (Element) node;
					HashMap deteccionesValida = new HashMap();
					
					boolean tipDetect=validaTipoArchivo(extraeElemento(deteccion, "TIPODETECCION", false, false), ArchivosNumberEnum.FM_RADAR_VELOCIDAD_CONSESIONADO_Redflex.getConstante());
					boolean origenPlaca=validaTipoArchivo(extraeElemento(deteccion, "ORIGENPLACA", false, false), ArchivosNumberEnum.FM_ORIGEN_FORANEO.getConstante());
					
					deteccionesValida=asignaValores(catLayoutVO.getObligatorios(), deteccionesValida, deteccion, ArchivosNumberEnum.DATO_OBLIGATORIO.getConstante(), false);
					deteccionesValida=asignaValores(catLayoutVO.getOpcionaes(), deteccionesValida, deteccion, ArchivosNumberEnum.DATO_OPCIONAL.getConstante(), false);
					
					// Llave para validar detecciones duplicadas en el mismo lote
					key = getKeyDeteccion(deteccionesValida.get("PLACA"), deteccionesValida.get("FECHA"), deteccionesValida.get("HORA"), deteccionesValida.get("TDSKUID"));

					insertaDatos(contenedorDetecciones, key, detecciones, duplicados,
					deteccionesValida, errores, tipDetect, origenPlaca, resultado, catLayoutVO.getCatalogosMap(),catLayoutVO.getObligatorios());

				}
			}
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return resultado;
		
	}
	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Map validacionProcesoCarrilConfinado(String fileName, String rutaArchivo, CargaArchivoComplementracionVO radaresCatArchivoTipoVO, FMDeteccionesCatLayoutVO catLayoutVO) {
		Map resultado = new HashMap<>();
		List<Map> duplicados = new ArrayList<Map>();
		List<Map> errores = new ArrayList<Map>();
		List<Map> detecciones = new ArrayList<Map>();
		
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			InputSource is = new InputSource(new InputStreamReader(new FileInputStream(rutaArchivo+ fileName), "utf-8"));
			HashMap contenedorDetecciones = new HashMap();
			String key;
			Document document = db.parse(is);
			Element root = document.getDocumentElement();
			root.normalize();
			NodeList nodes = root.getChildNodes();
			
			for (int i = 0; i < nodes.getLength(); i++) {
				Node node = nodes.item(i);
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					Element deteccion = (Element) node;
					HashMap deteccionesValida = new HashMap();
					
					boolean tipDetect=false;
					boolean origenPlaca=true;
					
					String valTipDet=extraeElemento(deteccion, "TIPODETECCION", false, true).trim();
					if(valTipDet==null||valTipDet.trim()==""||valTipDet.trim().equals("LAYOUT INCORRECTO")) {tipDetect=true;};
					
					deteccionesValida=asignaValores(catLayoutVO.getObligatorios(), deteccionesValida, deteccion, ArchivosNumberEnum.DATO_OBLIGATORIO.getConstante(),true);
					deteccionesValida=asignaValores(catLayoutVO.getOpcionaes(), deteccionesValida, deteccion, ArchivosNumberEnum.DATO_OPCIONAL.getConstante(),true);
					
					deteccionesValida.put("TIPODETECCION",ArchivosNumberEnum.FM_CARRIL_CONFINADO_Jenoptik.getConstante().byteValue()+"");
					deteccionesValida.put("ORIGENPLACA","0");
					String newFech = deteccionesValida.get("FECHA").toString();
					deteccionesValida.put("FECHA",newFech);
					deteccionesValida.put("VELOCIDADDETECTADA","0");

					// Llave para validar detecciones duplicadas en el mismo lote
					key = getKeyDeteccion(deteccionesValida.get("PLACA"), deteccionesValida.get("FECHA"), deteccionesValida.get("HORA"), deteccionesValida.get("TDSKUID"));
					
					insertaDatosCarrilConf(contenedorDetecciones, key, detecciones, duplicados,
							 deteccionesValida, errores, tipDetect, origenPlaca, resultado, catLayoutVO.getCatalogosMap(),catLayoutVO.getObligatorios());
				}
			}
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
				
		return resultado;
	
	}	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Map validacionProcesoCarrilConfinadoForaneo(String fileName, String rutaArchivo, CargaArchivoComplementracionVO radaresCatArchivoTipoVO, FMDeteccionesCatLayoutVO catLayoutVO) {
		Map resultado = new HashMap<>();
		List<Map> duplicados = new ArrayList<Map>();
		List<Map> errores = new ArrayList<Map>();
		List<Map> detecciones = new ArrayList<Map>();
		
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			InputSource is = new InputSource(new InputStreamReader(new FileInputStream(rutaArchivo+ fileName), "utf-8"));
			HashMap contenedorDetecciones = new HashMap();
			String key;
			Document document = db.parse(is);
			Element root = document.getDocumentElement();
			root.normalize();
			NodeList nodes = root.getChildNodes();

			for (int i = 0; i < nodes.getLength(); i++) {
				Node node = nodes.item(i);
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					Element deteccion = (Element) node;
					HashMap deteccionesValida = new HashMap();
					
					boolean tipDetect=false;
					boolean origenPlaca=true;//Cambio a true, ya que es un carril confinado foraneo
							
					String valTipDet=extraeElemento(deteccion, "TIPODETECCION", false, true);
					if(valTipDet==null||valTipDet.trim()==""||valTipDet.trim().equals("LAYOUT INCORRECTO")) {tipDetect=true;};
					
					deteccionesValida=asignaValores(catLayoutVO.getObligatorios(), deteccionesValida, deteccion, ArchivosNumberEnum.DATO_OBLIGATORIO.getConstante(),true);
					deteccionesValida=asignaValores(catLayoutVO.getOpcionaes(), deteccionesValida, deteccion, ArchivosNumberEnum.DATO_OPCIONAL.getConstante(), true);
					
					deteccionesValida.put("TIPODETECCION",ArchivosNumberEnum.FM_CARRIL_CONFINADO_Jenoptik.getConstante().byteValue()+"");
					deteccionesValida.put("ORIGENPLACA","1");
					String newFech = deteccionesValida.get("FECHA").toString();
					deteccionesValida.put("FECHA",newFech);
					deteccionesValida.put("VELOCIDADDETECTADA","0");
					
					// Llave para validar detecciones duplicadas en el mismo lote
					key = getKeyDeteccion(deteccionesValida.get("PLACA"), deteccionesValida.get("FECHA"), deteccionesValida.get("HORA"), deteccionesValida.get("TDSKUID"));
					
					insertaDatosCarrilConf(contenedorDetecciones, key, detecciones, duplicados,
							 deteccionesValida, errores, tipDetect, origenPlaca, resultado, catLayoutVO.getCatalogosMap(),catLayoutVO.getObligatorios());
				}
			}
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return resultado;
	
	}	
	
	@SuppressWarnings({ "unchecked", "rawtypes"})
	public Map validacionProcesoCompletoFotomulta(String fileName,String rutaArchivo, CargaArchivoComplementracionVO radaresCatArchivoTipoVO, FMDeteccionesCatLayoutVO catLayoutVO) {
		Map resultado = new HashMap<>();
		List<Map> duplicados = new ArrayList<Map>();
		List<Map> errores = new ArrayList<Map>();
		List<Map> detecciones = new ArrayList<Map>();
		
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			InputSource is = new InputSource(new InputStreamReader(new FileInputStream(rutaArchivo+ fileName), "utf-8"));
			HashMap contenedorDetecciones = new HashMap();
			String key;
			Document document = db.parse(is);
			Element root = document.getDocumentElement();
			root.normalize();
			NodeList nodes = root.getChildNodes();
			
			for (int i = 0; i < nodes.getLength(); i++) {
				Node node = nodes.item(i);
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					Element deteccion = (Element) node;
					HashMap deteccionesValida = new HashMap();
					
					boolean tipDetect=validaTipoArchivo(extraeElemento(deteccion, "TIPODETECCION", true, false), ArchivosNumberEnum.FM_FOTOMULTA_Bosch.getConstante(), ArchivosNumberEnum.FM_FOTOMULTA_Jenoptik.getConstante(), ArchivosNumberEnum.FM_FOTOMULTA_Laser.getConstante());
					boolean origenPlaca=validaTipoArchivo(extraeElemento(deteccion, "ORIGENPLACA", true, false), ArchivosNumberEnum.FM_ORIGEN_CDM.getConstante());

					deteccionesValida=asignaValores(catLayoutVO.getObligatorios(), deteccionesValida, deteccion, ArchivosNumberEnum.DATO_OBLIGATORIO.getConstante(), false);
					deteccionesValida=asignaValores(catLayoutVO.getOpcionaes(), deteccionesValida, deteccion, ArchivosNumberEnum.DATO_OPCIONAL.getConstante(), false);									
					
					deteccionesValida.put("VELOCIDADDETECTADA",extraeElemento(deteccion, "VELOCIDADDETECTADA", false, false).equals("")?"0":extraeElemento(deteccion, "VELOCIDADDETECTADA", false, false));
					
					// Llave para validar detecciones duplicadas en el mismo lote
					key = getKeyDeteccion(deteccionesValida.get("PLACA"), deteccionesValida.get("FECHA"), deteccionesValida.get("HORA"), deteccionesValida.get("TDSKUID"));
					
					insertaDatos(contenedorDetecciones, key, detecciones, duplicados,
							deteccionesValida, errores, tipDetect, origenPlaca, resultado, catLayoutVO.getCatalogosMap(),catLayoutVO.getObligatorios());
	
				}
			}
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return resultado;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Map validacionProcesoCompletoFotomultaForanea(String fileName, String rutaArchivo, CargaArchivoComplementracionVO radaresCatArchivoTipoVO, FMDeteccionesCatLayoutVO catLayoutVO) {
		Map resultado = new HashMap<>();
		List<Map> duplicados = new ArrayList<Map>();
		List<Map> errores = new ArrayList<Map>();
		List<Map> detecciones = new ArrayList<Map>();
		
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			InputSource is = new InputSource(new InputStreamReader(new FileInputStream(rutaArchivo+ fileName), "utf-8"));
			HashMap contenedorDetecciones = new HashMap();
			String key;
			Document document = db.parse(is);
			
			Element root = document.getDocumentElement();
			root.normalize();
			NodeList nodes = root.getChildNodes();
			
			for (int i = 0; i < nodes.getLength(); i++) {
				Node node = nodes.item(i);
				
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					Element deteccion = (Element) node;
					HashMap deteccionesValida = new HashMap();
					
					boolean tipDetect=validaTipoArchivo(extraeElemento(deteccion, "TIPODETECCION", true, false), ArchivosNumberEnum.FM_FOTOMULTA_Bosch.getConstante(), ArchivosNumberEnum.FM_FOTOMULTA_Jenoptik.getConstante(), ArchivosNumberEnum.FM_FOTOMULTA_Laser.getConstante());
					boolean origenPlaca=validaTipoArchivo(extraeElemento(deteccion, "ORIGENPLACA", true, false), ArchivosNumberEnum.FM_ORIGEN_FORANEO.getConstante());
					
					deteccionesValida=asignaValores(catLayoutVO.getObligatorios(), deteccionesValida, deteccion, ArchivosNumberEnum.DATO_OBLIGATORIO.getConstante(), false);
					deteccionesValida=asignaValores(catLayoutVO.getOpcionaes(), deteccionesValida, deteccion, ArchivosNumberEnum.DATO_OPCIONAL.getConstante(), false);
					
					deteccionesValida.put("VELOCIDADDETECTADA",extraeElemento(deteccion, "VELOCIDADDETECTADA", false, false).equals("")?"0":extraeElemento(deteccion, "VELOCIDADDETECTADA", false, false));
					
					key = getKeyDeteccion(deteccionesValida.get("PLACA"), deteccionesValida.get("FECHA"), deteccionesValida.get("HORA"), deteccionesValida.get("TDSKUID"));
					
					//JLGD
					insertaDatos(contenedorDetecciones, key, detecciones, duplicados,
							deteccionesValida, errores, tipDetect, origenPlaca, resultado, catLayoutVO.getCatalogosMap(),catLayoutVO.getObligatorios());
				}
			}
			
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return resultado;
		
	}
	
	/**
	 * 
	 * @param el
	 * @param nombre
	 * @param oblig
	 * @return IF nodo no existe en el archivo regresa como valor :LAYOUT INCORRECTO
	 * @return IF nodo es obligatorio y valor es NA, regresa NA de lo contrario regresa espacio en blanco
	 */
	private String extraeElemento(Element el, String nombre, boolean oblig,boolean isCarrilConfinado) {
		String text = "";
		NodeList nodeList = el.getElementsByTagName(nombre);
		// Si existe el elemento
		if (nodeList.getLength() > 0) {
			Element nodeElement = (Element) nodeList.item(0);
			NodeList textList = nodeElement.getChildNodes();
			// Si contiene texto
			if (textList.getLength() > 0) {
				text = ((Node) textList.item(0)).getNodeValue().trim();
				if(text.equals("NA")){
					text=oblig?text:"";
				}
			}
		}else {
			//si no existe elemento
			if(isCarrilConfinado && !oblig){
				text = "";
			}else{
				text = "LAYOUT INCORRECTO";
			}
		}
		return text;
	}

	public boolean validaFormatoFechaHora(String cadena, String mascara) {
		boolean isValida = false;
		cadena=cadena==null?"":cadena;
		mascara=mascara==null?"":mascara;
		if (cadena.length() == mascara.length()) {
			SimpleDateFormat formatter = new SimpleDateFormat(mascara);
			formatter.setLenient(false);
			try {
				formatter.parse(cadena);
				isValida = true;
			} catch (Exception e) {
				isValida = false;
			}
		}
		return isValida;
	}

	public String convertFechaFormato(String oldDateString) {
		oldDateString=oldDateString==null?"":oldDateString;
		if(oldDateString.length()==10){
			oldDateString=oldDateString.replace("/", ".");
			oldDateString=oldDateString.substring(0,6)+oldDateString.substring(8,10);
		}
		return oldDateString;
	}
	
	private boolean validaTipoArchivo(String valorEvaluar, int valorReal) {
		try {  
			int value=Integer.parseInt(valorEvaluar);
			if(value==valorReal) {
				return true;
			}else {return false;}  
	      } catch (NumberFormatException e) {  
	         return false;  
	      } 
	}
	private boolean validaTipoArchivo(String valorEvaluar, int valorReal, int valorReal2) {
		try {  
			int value=Integer.parseInt(valorEvaluar);
			if(value==valorReal||value==valorReal2) {
				return true;
			}else {return false;}  
	      } catch (NumberFormatException e) {  
	         return false;  
	      } 
	}
	private boolean validaTipoArchivo(String valorEvaluar, int valorReal, int valorReal2, int valorReal3) {
		try {  
			int value=Integer.parseInt(valorEvaluar);
			if(value==valorReal||value==valorReal2||value==valorReal3) {
				return true;
			}else {return false;}  
	      } catch (NumberFormatException e) {  
	         return false;  
	      } 
	}
	
	
	@SuppressWarnings("unchecked")
	private  Map insertaDatos(HashMap contenedorDetecciones, String key, List<Map> detecciones, List<Map> duplicados,
			HashMap deteccionesValida, List<Map> errores, boolean tipDetect, boolean origenPlaca, Map resultado, Map<String, List<String>> catalogosMap,String[] obligatorios) {
		if (contenedorDetecciones.get(key) == null) {
			contenedorDetecciones.put(key, key);
			boolean isRight=true;
			String valTipoDetec="";
			// Si hay errores, lo agrega a la lista de errores
			Iterator it = deteccionesValida.entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry e = (Map.Entry) it.next();
				if(e.getKey().equals("isLayoutOk")) {
					if(e.getValue() instanceof Boolean){
						if(!(boolean) e.getValue())
							isRight=false;
					}
				}else if(e.getValue().toString().trim().equals("LAYOUT INCORRECTO")) {
					e.setValue(getMsnError(null, 'L'));
					isRight=false;
				} else if (e.getKey().equals("FECHA")) {
					if (!validaFormatoFechaHora(e.getValue().toString(), "dd/MM/yyyy")) {
						e.setValue(getMsnError(e.getValue(), 'F'));
						isRight=false;
					}else{
						e.setValue(convertFechaFormato(e.getValue().toString()));
					}
				}else if (e.getKey().equals("HORA")) {
					if (!validaFormatoFechaHora(e.getValue().toString(), "HH:mm:ss")) {
						e.setValue(getMsnError(e.getValue(), 'H'));
						isRight=false;
					}
				}else if(e.getKey().equals("TIPODETECCION")) {
					valTipoDetec=e.getValue().toString();
					if(!tipDetect) {
						e.setValue(getMsnError(e.getValue(), 'I'));
						isRight=false;
					}
				}else if(e.getKey().equals("ORIGENPLACA")) {
					if(!origenPlaca) {
						e.setValue(getMsnError(e.getValue(), 'I'));
						isRight=false;
					}
				}else if (e.getValue().toString().equals("")
						|| e.getValue().toString().trim().toUpperCase().equals("NA")) {
					if(!permiteVacio(e.getKey().toString(), obligatorios)){
						e.setValue(getMsnError(e.getValue().toString(),'R'));
						isRight=false;	
					}
				}
				else if(e.getKey().equals("TDSKUID")) {
					e.setValue(e.getValue().toString().toUpperCase());
				}
				else if(e.getKey().equals("PLACA")) {					
					e.setValue(e.getValue().toString().toUpperCase());
				}
				else if(e.getKey().equals("LAYOUT INCORRECTO")) {
					e.setValue(getMsnError(null, 'L'));
					isRight=false;
				}else if(e.getKey().equals("ENTIDAD")) {
					if(!catalogosMap.get("CAT_ENTIDADES").subList(0, catalogosMap.get("CAT_ENTIDADES").toArray().length).contains(e.getValue().toString())) {
						e.setValue(getMsnError(e.getValue(), 'C'));
						isRight=false;
					}	
				}
				else if(e.getKey().equals("DELEGACION")) {
					if(!catalogosMap.get("CAT_DELEGACIONES").subList(0, catalogosMap.get("CAT_DELEGACIONES").toArray().length).contains(e.getValue().toString())) {
						e.setValue(getMsnError(e.getValue(), 'C'));
						isRight=false;
					}	
				}else if(e.getKey().equals("ARTID")) {
					if(!catalogosMap.get("CAT_ARTICULOS").subList(0, catalogosMap.get("CAT_ARTICULOS").toArray().length).contains(e.getValue().toString())) {
						e.setValue(getMsnError(e.getValue(), 'C'));
						isRight=false;
					}	
				}
			}
			if(isRight) {
				detecciones.add(deteccionesValida);
			}else {
				errores.add(deteccionesValida);
			}
		} else {
			duplicados.add(deteccionesValida);
		}
		
		resultado.put("detecciones", detecciones);
		resultado.put("duplicados", duplicados);
		resultado.put("errores", errores);
		resultado.put("resultado", errores.isEmpty());
		
		return resultado;
		
	}
	
	
	@SuppressWarnings("unchecked")
	private  Map insertaDatosCarrilConf(HashMap contenedorDetecciones, String key, List<Map> detecciones, List<Map> duplicados,
			HashMap deteccionesValida, List<Map> errores, boolean tipDetect, boolean origenPlaca, Map resultado, Map<String, List<String>> catalogosMap,String[] obligatorios) {

		if (contenedorDetecciones.get(key) == null) {
			contenedorDetecciones.put(key, key);
			boolean isRight=true;
			String valTipoDetec="";
			// Si hay errores, lo agrega a la lista de errores
			Iterator it = deteccionesValida.entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry e = (Map.Entry) it.next();
				
				if(e.getValue().toString().trim().equals("LAYOUT INCORRECTO")) {
					e.setValue(getMsnError(null, 'L'));
					isRight=false;
				} else if(e.getKey().equals("TDSKUID")) {				
					e.setValue(e.getValue().toString().toUpperCase());
				}else if(e.getKey().equals("PLACA")) {					
					e.setValue(e.getValue().toString().toUpperCase());
				}else if(e.getKey().equals("LAYOUT INCORRECTO")) {
					e.setValue(getMsnError(null, 'L'));
					isRight=false;
				}else if (e.getKey().equals("FECHA")) {
					if (!validaFormatoFechaHora(e.getValue().toString(), "dd.MM.yy")) {
						e.setValue(getMsnError(e.getValue(), 'F'));
						isRight=false;
					}
				}else if (e.getKey().equals("HORA")) {
					if (!validaFormatoFechaHora(e.getValue().toString(), "HH:mm:ss")) {
						e.setValue(getMsnError(e.getValue(), 'H'));
						isRight=false;
					}
				}else if(e.getKey().equals("TIPODETECCION")) {
					valTipoDetec=e.getValue().toString();
					if(!tipDetect) {
						e.setValue(getMsnError(e.getValue(), 'I'));
						isRight=false;
					}
				}else if(e.getKey().equals("ORIGENPLACA")) {
					if(!origenPlaca) {
						e.setValue(getMsnError(e.getValue(), 'I'));
						isRight=false;
					}
				}else if (e.getValue().toString().trim().equals("")
						|| e.getValue().toString().trim().toUpperCase().equals("NA")) {
					if(!permiteVacio(e.getKey().toString(), obligatorios)){
						e.setValue(getMsnError(e.getValue().toString(),'R'));
						isRight=false;	
					}
				}else if(e.getKey().equals("isLayoutOk")) {
					if(e.getValue() instanceof Boolean){
						if(!(boolean) e.getValue())
							isRight=false;
					}
				}
			}
			if(isRight) {
				detecciones.add(deteccionesValida);
			}else {
				errores.add(deteccionesValida);
			}
		} else {
			// Se guardan registros duplicados
			duplicados.add(deteccionesValida);
		}
		resultado.put("detecciones", detecciones);
		resultado.put("duplicados", duplicados);
		resultado.put("errores", errores);
		resultado.put("resultado", errores.isEmpty());
		return resultado;
		
	}
	
	private HashMap asignaValores(String[] array, HashMap deteccionesValida, Element deteccion, int isOpOrReq, boolean isCarrilConfinado) {
		if(isOpOrReq==3){//Opcional
			for(int i=0;i<array.length;i++) {
				deteccionesValida.put(array[i],extraeElemento(deteccion, array[i], false,isCarrilConfinado));
				if (deteccionesValida.get(array[i]).toString().trim().isEmpty()) {
					deteccionesValida.put(array[i], ArchivosEnum.NODO_OPCIONAL.getConstante());
				}			
			}
		}if(isOpOrReq==1) {//Si es obligatorio
			deteccionesValida.put("isLayoutOk", true);//Determina si el layout fue complementado en etiquetas
			for(int i=0;i<array.length;i++) {
				if(array[i].equals("FECHAHORA")) {
					String fechHora=extraeElemento(deteccion, "FECHAHORA", true,isCarrilConfinado);
					if(fechHora!=null||!fechHora.trim().equals("")) {
						String[] fechHor = fechHora.split(" ");	
						if(fechHor.length>1) {
							deteccionesValida.put("FECHA",fechHor[0]);
							deteccionesValida.put("HORA",fechHor[1]);
						}else {
							deteccionesValida.put("FECHA",fechHora);
							deteccionesValida.put("HORA","");
						}						
					}else {
						deteccionesValida.put("FECHA","LAYOUT INCORRECTO");
						deteccionesValida.put("HORA","LAYOUT INCORRECTO");
					}
				}else {
					String nodo_str=extraeElemento(deteccion,  array[i], true,isCarrilConfinado);
					deteccionesValida.put(array[i], nodo_str);
					if(nodo_str.equals("LAYOUT INCORRECTO") || nodo_str.equals("NA")) {
						deteccionesValida.put("isLayoutOk", false);//Determina si el layout fue complementado en etiquetas
					}
				}
			}
		}
		return deteccionesValida;
	}
	
	private boolean permiteVacio(String key,String[] obligatorios){
		boolean isRight=true;
		for (int i = 0; i < obligatorios.length; i++) {
			if(key.trim().toUpperCase().equals(obligatorios[i].trim().toUpperCase())){
				isRight=false;
				break;
			}
		}
		return isRight;
	}
	
	/**
	 * 
	 * @param valor : Corresponde al valor que presenta error
	 * @param tipoError :
	 * <br> R : Valor requerido
	 * <br> I : Incorrecto
	 * <br> L : Layout incorrecto
	 * <br> F : Fecha incorrecta
	 * <br> H : Hora incorrecta
	 * @return
	 */
	private String getMsnError(Object valor, char tipoError) {
		String mensajeError=valor==null?"": (valor instanceof String)? valor.toString().trim():"";
		switch (tipoError) {
		case 'R':
			mensajeError=mensajeError+" -> Valor requerido";
			break;
		case 'I':
			mensajeError=mensajeError+" -> Incorrecto";
			break;
		case 'L':
			mensajeError=mensajeError+" -> Layout incorrecto";
			break;
		case 'F':
			mensajeError=mensajeError+" -> Fecha incorrecta";
			break;
		case 'H':
			mensajeError=mensajeError+" -> Hora incorrecta";
			break;
		case 'C':
			mensajeError=mensajeError+" -> Cat incorrecto";
			break;
		default:
			mensajeError=mensajeError+" -> Error desconocido";
			break;
		}
		return mensajeError;
	}
	private String getKeyDeteccion(Object placa,Object fecha, Object hora,Object tdskuid ) {
		String p=placa==null?"": (placa instanceof String)? placa.toString().trim().toUpperCase():"";
		String f=fecha==null?"": (fecha instanceof String)? fecha.toString().trim().toUpperCase():"";
		String h=hora==null?"": (hora instanceof String)? hora.toString().trim().toUpperCase():"";
		String t=tdskuid==null?"": (tdskuid instanceof String)? tdskuid.toString().trim().toUpperCase():"";
		return p.concat(f).concat(h).concat(t);
	}

}
