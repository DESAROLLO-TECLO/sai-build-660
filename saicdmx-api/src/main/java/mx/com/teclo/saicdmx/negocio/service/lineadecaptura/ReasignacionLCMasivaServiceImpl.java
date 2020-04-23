package mx.com.teclo.saicdmx.negocio.service.lineadecaptura;

import java.io.IOException;
import java.net.ProtocolException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

import mx.com.teclo.saicdmx.bitacora.cambios.infraccionesRadar.BitTrBitUpInfracRadar;
import mx.com.teclo.saicdmx.negocio.service.estadistica.TipoInfraccionService;
import mx.com.teclo.saicdmx.negocio.service.semovi.ParametroService;
import mx.com.teclo.saicdmx.persistencia.hibernate.dao.lineadecaptura.LineaDeCapturaDAO;
import mx.com.teclo.saicdmx.persistencia.mybatis.dao.lineadecaptura.ReasignacionLCMMyBatisDAO;
import mx.com.teclo.saicdmx.persistencia.mybatis.dao.lineadecaptura.ReasignacionLCMyBatisDAO;
import mx.com.teclo.saicdmx.persistencia.vo.infracciones.VSSPInfracConsGralFVO;
import mx.com.teclo.saicdmx.persistencia.vo.lineadecaptura.BitReasignacionLineaCapturaVO;
import mx.com.teclo.saicdmx.persistencia.vo.lineadecaptura.ConsultaInfraccionForReasignacionVO;
import mx.com.teclo.saicdmx.persistencia.vo.lineadecaptura.IncidenciaLCMVO;
import mx.com.teclo.saicdmx.persistencia.vo.lineadecaptura.InfraccionLCMVO;
import mx.com.teclo.saicdmx.persistencia.vo.lineadecaptura.PorcentajeDescuentoLCVO;
import mx.com.teclo.saicdmx.persistencia.vo.lineadecaptura.RespuestaWSReasignaLineaCapturaMasivaVO;
import mx.com.teclo.saicdmx.persistencia.vo.lineadecaptura.RespuestaWSReasignaLineaCapturaVO;
import mx.com.teclo.saicdmx.util.comun.RutinasTiempoImpl;
import mx.com.teclo.saicdmx.util.enumerados.ParametrosBitacoraEnum;
import mx.com.teclo.saicdmx.ws.lineadecaptura.WSReasignaLineaCaptura;
import mx.com.teclomexicana.arquitectura.ortogonales.exception.BusinessException;
import mx.com.teclomexicana.arquitectura.ortogonales.exception.NotFoundException;
import mx.com.teclomexicana.arquitectura.ortogonales.seguridad.vo.BitacoraCambiosVO;
import mx.com.teclomexicana.arquitectura.ortogonales.service.bitacora.BitacoraCambiosService;

@Service
public class ReasignacionLCMasivaServiceImpl implements ReasignacionLCMasivaService {

	@Autowired
	private ReasignacionLCMyBatisDAO reasignacionLCMBDAO;
	
	@Autowired
	private ReasignacionLCMMyBatisDAO reasignacionLCMMBDAO;
	
	@Autowired
	private ParametroService parametros;
	
	@Autowired
	private BitacoraCambiosService bitacoraCambiosService;
	
	@Autowired
	private BitTrBitUpInfracRadar bitTrBitUpInfracRadar;
	
	@Autowired
	private LineaDeCapturaDAO lineaDeCapturaDAO;
	
	@Autowired
	private TipoInfraccionService tipoInfraccionService;
	
	@Override
	public Map<String, Integer> validarLotePendiente() {
		Integer proceso;
		Integer cantidadInfracciones;
		Integer idLote;
		Map<String, Integer> response = new HashMap<String, Integer>();
		
		proceso = reasignacionLCMMBDAO.validarProcesoActual();
		proceso =  proceso == null ? 0 : proceso;
		idLote = reasignacionLCMMBDAO.sqlActualLote();
		
		//cantidadInfracciones = reasignacionLCMMBDAO.validarFoliosPendientes();
		cantidadInfracciones = reasignacionLCMMBDAO.validarFoliosPendientesLote(idLote);
		
		if(cantidadInfracciones > 0){
			response.put("respuesta", 0);
			if(proceso > 0){
				response.put("code", 2);
			}else{
				response.put("code", 1);
			}
			response.put("cantidad",cantidadInfracciones);
		}else{
			response.put("respuesta", 1);
			response.put("code", 0);
		}
		
		return response;
	}

	@Override
	public Map<String, Object> cargarArchivoReasignar(String[] infracs, Long userId, String name, String fEmision, Integer tipoDescuento) {
		//List<IncidenciaLCMVO>
//		String[] cleanInfracs = null;
//		List<IncidenciaLCMVO> listaIncidencias = new ArrayList<IncidenciaLCMVO>();
//			
//		cleanInfracs = limpiarEntradas(infracs, listaIncidencias);
//
//		List<String> originalListInfracs = new ArrayList<String>(Arrays.asList(cleanInfracs));
//		List<String> listInfracs = new ArrayList<String>(Arrays.asList(cleanInfracs));
//		List<String> cleanListInfracs = new ArrayList<String>();
//		
//		if(!listaIncidencias.isEmpty()){
//			return listaIncidencias;
//		}
//		
//		for(int s = 0; s<originalListInfracs.size(); s++){
//			//Valida que la infracción cumpla
//			regex(originalListInfracs.get(s), listaIncidencias);
//			
//			listInfracs.remove(originalListInfracs.get(s));
//			if (!listInfracs.contains(originalListInfracs.get(s))){
//				cleanListInfracs.add(originalListInfracs.get(s).substring(0, 11));
//			}else{
//				/*Proceso de Limpiado de Listas*/
//				filtrarInfraccionRepetida(originalListInfracs.get(s), listInfracs, originalListInfracs, listaIncidencias);
//				cleanListInfracs.add(originalListInfracs.get(s).substring(0, 11));
//			}
//		}
//		
//		if(listInfracs.isEmpty()){
//			if(!cleanListInfracs.isEmpty() && listaIncidencias.isEmpty()){
//				int c = 0;
//				//Se procede a iterar para agregar
//				for(String data:cleanListInfracs){
//					//c += radarReasignarLCMIBDAO.insertarArchivoReasignacion(data, userId);
//					c += reasignacionLCMMBDAO.insertarFolio(data, userId);
//				}
//				if(c == cleanListInfracs.size()){
//					//radarReasignarLCMIBDAO.insertarLoteFolios(nombre, userId);
//					RutinasTiempoImpl fEmision3 = new RutinasTiempoImpl();
//					Date fEmision2 = fEmision3.convertirStringDate(fEmision,"DD/MM/YYYY");
//					reasignacionLCMMBDAO.crearLoteFolios(name, userId, fEmision2);
//				}
//			}else{
//				//Si hay incidencias se manda una lista de incidencias
//			}
//		}
//		return listaIncidencias;
		String infraccion = "";
		String[] cleanInfracs = null;
		Map<String, Object> response = new HashMap<String, Object>();
		List<IncidenciaLCMVO> listaIncidencias = new ArrayList<IncidenciaLCMVO>();
		Integer idLote = 0;
		if(infracs.length == 0){
			response.put("vacio", Boolean.TRUE);
			return response;
		}
		
		cleanInfracs = limpiarEntradas(infracs, listaIncidencias);
		List<String> originalListInfracs = new ArrayList<String>(Arrays.asList(cleanInfracs));
		List<String> listInfracs = new ArrayList<String>(Arrays.asList(cleanInfracs));
		List<String> cleanListInfracs = new ArrayList<String>(); 			//Correctas
		List<String> repetidasListInfracs = new ArrayList<String>(); 		//Repetidas
		
		for(int s = 0; s < originalListInfracs.size(); s++){
			infraccion = originalListInfracs.get(s);
			if(infraccion != null){	
				//Valida que la infracción cumpla
				regex(infraccion, listaIncidencias);
			
				listInfracs.remove(infraccion);
				if (!listInfracs.contains(infraccion)){
					cleanListInfracs.add(infraccion.substring(0, 11));
				}else{
					if (!cleanListInfracs.contains(infraccion)){
						cleanListInfracs.add(infraccion.substring(0, 11));
					}
					/*Proceso de Limpiado de Listas*/
					filtrarInfraccionRepetida(infraccion, listInfracs, originalListInfracs, listaIncidencias);
					repetidasListInfracs.add(infraccion.substring(0, 11));
				}
			}else{
				listInfracs.remove(infraccion);
			}
		}
		
		if(listaIncidencias.isEmpty()){
			response.put("insidencias", Boolean.FALSE);
			response.put("listaIncidencias", null);
		}else{
			response.put("insidencias", Boolean.TRUE);
			response.put("listaIncidencias", listaIncidencias);
		}
		
		if(listInfracs.isEmpty()){
			if(!cleanListInfracs.isEmpty()){
				int c = 0;
				//if(c == cleanListInfracs.size()){
					//radarReasignarLCMIBDAO.insertarLoteFolios(nombre, userId);
					RutinasTiempoImpl fEmision3 = new RutinasTiempoImpl();
					Date fEmision2 = fEmision3.convertirStringDate(fEmision,"dd/MM/yyyy");
					reasignacionLCMMBDAO.crearLoteFolios(name, userId, fEmision2, tipoDescuento);
					idLote = reasignacionLCMMBDAO.consultaLoteCreado(name, userId, fEmision2, tipoDescuento);
				//}
				//Se procede a iterar para agregar
				for(String data:cleanListInfracs){
					//c += radarReasignarLCMIBDAO.insertarArchivoReasignacion(data, userId);
					c += reasignacionLCMMBDAO.insertarFolio(data, userId, idLote);
				}
				response.put("loteCreado", Boolean.TRUE);
				response.put("infraccionesAgregadas", c);
			}else{
				response.put("loteCreado", Boolean.FALSE);
				response.put("infraccionesAgregadas", 0);
			}
		}else{
			response.put("loteCreado", Boolean.FALSE);
			response.put("infraccionesAgregadas", 0);
		}
		return response;
	}

	@Override
	public Map<String, Object> reasignarLoteFolio(Long userId)throws ProtocolException, IOException, ParserConfigurationException, SAXException, BusinessException, ParseException, NotFoundException{
		/**
		* Estatus general de proceso e Infracciones(Contiene el estatus general de 
		* todo el proceso y tambien contiene el estatus de cada infraccion procesada)
		**/
		String[][] strReturn = (new String[][]{{"0"}, null});
		
		//Obtiene el Lote actual
		Integer lote = reasignacionLCMMBDAO.sqlActualLote();
		String fEmision = reasignacionLCMMBDAO.sqlActualLoteFEmision(lote);
		Integer tipoDescuento = reasignacionLCMMBDAO.sqlActualLoteTipoDescuento(lote);
		
		//Obtiene las infracciones que faltan por reasignar(Estatus = 0)
		//List<InfraccionLCMVO> listaInfracs = reasignacionLCMMBDAO.sqlObtenerFolios();
		List<InfraccionLCMVO> listaInfracs = reasignacionLCMMBDAO.sqlObtenerFoliosLote(lote);
		
		//Obtiene el detalle de cada infraccion(descuento, condonacion, etc...)
		List<ConsultaInfraccionForReasignacionVO> listaBean = obtenerInfoCambioFecha(listaInfracs, userId, lote, fEmision, tipoDescuento);
		Map<String, Object> respuesta = new HashMap<String, Object>();
		
		strReturn[1] = new String[listaBean.size()];
		
		reasignacionLCMMBDAO.sqlProceso(Integer.parseInt("1"), lote);
		
		WSReasignaLineaCaptura servicioWS = new WSReasignaLineaCaptura();
		
		for(int i = 0; i<listaBean.size(); i++){
			RespuestaWSReasignaLineaCapturaVO resp = new RespuestaWSReasignaLineaCapturaVO();
			RespuestaWSReasignaLineaCapturaMasivaVO cast;
			String proxyURL = parametros.getURLProxyLc();
			
			resp = servicioWS.servicio(listaBean.get(i), parametros.getUrlWebServiceReasignarLineaCaptura(),proxyURL);
			
			resp.setUsuario(String.valueOf(userId));
			
			/** Guarda en bitacora los parametros enviados al servicio de finanzas**/
			BitReasignacionLineaCapturaVO peticionWS = new BitReasignacionLineaCapturaVO();
			peticionWS = resp.getPeticionWS();
			peticionWS.setIdUsuario(userId);
			peticionWS.setIdOrigen(0);
			reasignacionLCMMBDAO.guardaBitacoraLcReasignaWS(peticionWS);
			
			if(resp.getError().equals("500")){
				reasignacionLCMMBDAO.sqlProceso(Integer.parseInt("0"), lote);
				throw new BusinessException("Secretaria de Finanzas no respondió a la reasignación, por favor inténtelo más tarde");
			}
			
			if(resp.getError().equals("100")){
				resp.setEstatusConsumo(resp.getError());
				resp.setEstatusResultado(resp.getError_descripcion());
				strReturn[1][i] = "0";
			}
			else
			{
				if(resp.getError().equals("0"))
				{
					resp.setEstatusConsumo("0");
					strReturn[1][i] = "1";
				}
				else
				{
					resp.setEstatusConsumo(resp.getError());
					resp.setEstatusResultado(resp.getError_descripcion());
					strReturn[1][i] = "0";
				}
			}
			
			cast = convertirLCToLCM(resp, lote.toString());
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy/MM/dd");
			RutinasTiempoImpl objetotiempo = new RutinasTiempoImpl();
			Date datef = objetotiempo.convertirStringDate(fEmision, "yy/MM/dd");
			String fEmisionSP = simpleDateFormat.format(datef);
			cast.setFecha_emision(fEmisionSP);
			
			String isFolioRadar = tipoInfraccionService.getVistaPorTipo(cast.getFolio());
			isFolioRadar = isFolioRadar != null? isFolioRadar.toUpperCase().trim(): "";
			
			RespuestaWSReasignaLineaCapturaVO newRespuesta = resp;
			RespuestaWSReasignaLineaCapturaVO oldRespuesta = new RespuestaWSReasignaLineaCapturaVO();
			if(isFolioRadar.equals("V_WS_SF_LINEAC_REASIGNA")){
				if(cast.getFolio()!=null && !cast.getFolio().equals(""))
					oldRespuesta = reasignacionLCMBDAO.OldReasignarLineaDeCaptura(cast.getFolio());
				VSSPInfracConsGralFVO newInfracRadar = new VSSPInfracConsGralFVO();
				VSSPInfracConsGralFVO oldInfracRadar = new VSSPInfracConsGralFVO();
				newInfracRadar.setNumeroControlInterno(cast.getFolio());
				newRespuesta.setInfracRadarVO(newInfracRadar);
				oldRespuesta.setInfracRadarVO(oldInfracRadar);
			}
			ArrayList<BitacoraCambiosVO> bitacoraCambiosVOs = new ArrayList<BitacoraCambiosVO>();
			
			cast.setDescuento(cast.getDescuento() != null ? cast.getDescuento().length() >= 10 ? cast.getDescuento().substring(0, 9):cast.getDescuento() :null);
			
			
			//reasignacionLCMMBDAO.sqlSpRadarReasignaMasiva(cast);
			
			if(isFolioRadar.equals("V_WS_SF_LINEAC_REASIGNA")){
				//---------------------------------------------
				// 			INICIA INFRACCIONES_RADAR
				//---------------------------------------------
				reasignacionLCMMBDAO.sqlSpRadarReasignaMasiva(cast);
			}else if(isFolioRadar.equals("V_WS_SF_LINEAC_REASIGNA_ALL")){
				//---------------------------------------------
				// 			INICIA INFRACCIONES
				//---------------------------------------------
				reasignacionLCMMBDAO.sqlSpRadarReasignaMasivaAll(cast);
			}else if(isFolioRadar.equals("V_WS_SF_LINEAC_REASIGNA_DIG")){
				//---------------------------------------------
				// 		INICIA INFRACCIONES_DIGITALIZACION
				//---------------------------------------------
				reasignacionLCMMBDAO.sqlSpRadarReasignaMasivaAll(cast);
			}
			
			if(resp.getError().equals("100") || resp.getError().equals("500")){
				bitacoraCambiosService.guardarBitacoraCambiosParametros(
					ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro(), 8L, 4L,
					resp.getFolio() != null && !resp.getFolio().equals("") ? resp.getFolio() : "", 
					resp.getError_descripcion() != null && !resp.getError_descripcion().equals("") ? resp.getError_descripcion() : "", 
					resp.getUsuario() != null && !resp.getUsuario().equals("") ? Long.parseLong(resp.getUsuario()) : 0L, 
					"0", 
					"", ParametrosBitacoraEnum.ORIGEN_W.getParametro()
				);
			}else{
				if(isFolioRadar.equals("V_WS_SF_LINEAC_REASIGNA")){
					try{
						bitacoraCambiosService.guardarBitacoraCambiosParametros(
								ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro(), 8L, 3L,
								resp.getFolio() != null && !resp.getFolio().equals("") ? resp.getFolio() : "", 
								resp.getLineacaptura() != null && !resp.getLineacaptura().equals("") ? resp.getLineacaptura() : "", 
								resp.getUsuario() != null && !resp.getUsuario().equals("") ? Long.parseLong(resp.getUsuario()) : 0L, 
								"0", 
								"", ParametrosBitacoraEnum.ORIGEN_W.getParametro()
							);
						//Realiza insercion a bitacora desde el trigger al modificar infrac_linea_cap,infrc_linea_cap_vigencia,infrac_linea_cap_monto,autorizado_por
						if(cast.getVigencia() != null && !cast.getVigencia().equals("")) {
							bitacoraCambiosVOs = bitTrBitUpInfracRadar.guardarCambiosBitacora(newRespuesta, oldRespuesta);
							bitacoraCambiosService.guardarListaBitacoraCambios(bitacoraCambiosVOs);
						}
					}catch (Exception e){
						System.err.print("Ha ocurrido un imprevisto! , por favor contacte al administrador " + e);
					}
				}
			}
		}
		
		respuesta = cleanTableReasignado(strReturn, lote);
		//respuesta.put("respuesta", cleanTableReasignado(strReturn, lote, listaInfracCancel));
		System.out.println("Codigo general: "+ strReturn[0][0]);
		return respuesta;
	}
	
	@Override
	public Boolean  cancelarLoteFolio() {
		//Cancela la reasignacion del lote actual del archivo
		Integer result = 0;
		result +=reasignacionLCMMBDAO.cancelarLoteFolios();
		//Agregar datos cancelados a INFRACCIONES_LC_REASIGNA (NO ES NECESARIO PORQUE EL ESTATUS ES EL WS)
		result +=reasignacionLCMMBDAO.vaciarFolios();
		return result > 0 ? true : false;
	}
	
	private String[] limpiarEntradas(String[] infracs, List<IncidenciaLCMVO> incidencias){
		String[] cleanInfracs = new String[infracs.length];
		Pattern pattern = Pattern.compile("[0-9]{11}");
		
		for(int i = 0; i < infracs.length; i++){
			Matcher matcher = pattern.matcher(infracs[i]);
			if(matcher.find()){
				cleanInfracs[i] = matcher.group(0);
			}else{
				//Se anota a la lista de incidencias
				IncidenciaLCMVO temp = new IncidenciaLCMVO();
				temp.setInfracNum(infracs[i]);
				temp.setLinea(i+1);
				incidencias.add(temp);
			}
		}
		return cleanInfracs;
	}
	
	private void regex(String word, List<IncidenciaLCMVO> incidencias){
		Pattern pattern = Pattern.compile("^[0-9]{11}$");
		Matcher matcher = pattern.matcher(word);
		if(matcher.find()){
			return;
		}else{
			//Se anota a la lista de incidencias
			IncidenciaLCMVO temp = new IncidenciaLCMVO();
			temp.setInfracNum(word);
			incidencias.add(temp);
		}
	}
	
	private void filtrarInfraccionRepetida(String incident, List<String> cleanList, List<String> iterableList, List<IncidenciaLCMVO> incidentList){
		int firstOneLine = 0;
		int line = iterableList.indexOf(incident);
		firstOneLine = line;
		
		while(line > -1){
			//iterableList.remove(line);
			iterableList.set(line, " ");
			IncidenciaLCMVO ilcmVO = new IncidenciaLCMVO();
			ilcmVO.setInfracNum(incident);
			ilcmVO.setDuplicated(true);
			ilcmVO.setLinea(line+1);
			incidentList.add(ilcmVO);
			line = iterableList.indexOf(incident);
		}
		
		/*Limpiamos los espacios vacios*/
		line = iterableList.indexOf(" ");
		while(line > -1){
			iterableList.remove(line);
			line = iterableList.indexOf(" ");
		}
		
		/*Agregamos la incidencia como UNICO valor*/
		iterableList.add(firstOneLine, incident);
		
		line = cleanList.indexOf(incident);
		while(line > -1){
			cleanList.remove(line);
			line = cleanList.indexOf(incident);
		}
	}
	
	private List<ConsultaInfraccionForReasignacionVO> obtenerInfoCambioFecha(List<InfraccionLCMVO>lista, Long userId, Integer lote, String fEmision, Integer tipoDescuento) throws ParseException, NotFoundException{
		//SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");
		SimpleDateFormat sdf = new SimpleDateFormat("hh:mm");
		String fecha = sdf.format(new Date());
		//String fecha = sdf.format(fEmision);

        String[] arrFecha;
        String loteFechaEmision = "";
        
		arrFecha = fEmision.split(" ");
		arrFecha = arrFecha[0].split("-");
        loteFechaEmision = arrFecha[2]+"/"+arrFecha[1]+"/"+arrFecha[0];
        fecha = loteFechaEmision + " " + fecha;
        
		List<ConsultaInfraccionForReasignacionVO>listaDetalleInfracs = new ArrayList<ConsultaInfraccionForReasignacionVO>();
		Map<String, String> parametrosCons = getParametrosLP();
		
		for(InfraccionLCMVO infrac:lista){
			String consCondonacion = parametrosCons.get("CONS_CONDONACION");
			RutinasTiempoImpl objetotiempo = new RutinasTiempoImpl();
			String usuarioOld = reasignacionLCMBDAO.sqlUsuarioOld(infrac.getInfracNum());
			String fechaOld = "";
			
			String isFolioRadar = tipoInfraccionService.getVistaPorTipo(infrac.getInfracNum());
			isFolioRadar = isFolioRadar != null? isFolioRadar.toUpperCase().trim(): "";
			
			ConsultaInfraccionForReasignacionVO cifrVO;
			RespuestaWSReasignaLineaCapturaVO newReasignaLineaCaptura = new RespuestaWSReasignaLineaCapturaVO();
			RespuestaWSReasignaLineaCapturaVO oldReasignaLineaCaptura = new RespuestaWSReasignaLineaCapturaVO();
			VSSPInfracConsGralFVO oldInfracRadar = new VSSPInfracConsGralFVO();
			VSSPInfracConsGralFVO newInfracRadar = new VSSPInfracConsGralFVO();
			
			if(isFolioRadar.equals("V_WS_SF_LINEAC_REASIGNA")){
				//---------------------------------------------
				// 			INICIA INFRACCIONES_RADAR
				//---------------------------------------------
				if(usuarioOld!=null){
					try{
						fechaOld = objetotiempo.getStringDateFromFormta("dd/MM/yyyy", reasignacionLCMBDAO.sqlFechaOld(infrac.getInfracNum()));
					}catch (Exception e){
						//throw new NotFoundException("Ha ocurrido un imprevisto! , por favor contacte al administrador");
						System.err.print("No se encontro la fecha de emision de infraccion " + infrac.getInfracNum() + "--" + e);
					}
				}
				
				newInfracRadar.setFechaEmision(loteFechaEmision);
				newInfracRadar.setNumeroControlInterno(infrac.getInfracNum());
				newReasignaLineaCaptura.setInfracRadarVO(newInfracRadar);
				newReasignaLineaCaptura.setUsuario(userId.toString());
				oldInfracRadar.setFechaEmision(fechaOld);
				oldReasignaLineaCaptura.setInfracRadarVO(oldInfracRadar);
				oldReasignaLineaCaptura.setUsuario(usuarioOld);
				ArrayList<BitacoraCambiosVO> bitacoraCambiosVOs = new ArrayList<BitacoraCambiosVO>();
				
				reasignacionLCMMBDAO.sqlSpRadarLcReasignaFechaEm(fecha, userId, infrac.getInfracNum());
				if(usuarioOld!=null){
					try{
						bitacoraCambiosVOs = bitTrBitUpInfracRadar.guardarCambiosBitacora(newReasignaLineaCaptura, oldReasignaLineaCaptura);
						bitacoraCambiosService.guardarListaBitacoraCambios(bitacoraCambiosVOs);
					}catch (Exception e){
						//throw new NotFoundException("Ha ocurrido un imprevisto! , por favor contacte al administrador");
						System.err.print("Ha ocurrido un imprevisto!, por favor contacte al administrador " + e);
					}
				}
				
				cifrVO = reasignacionLCMBDAO.buscaInfraccionRadarByFolio(infrac.getInfracNum());
				
				if(cifrVO != null){
					String consPorcentajeDescRad = parametrosCons.get("CONS_PORCENTAJE_DESCUENTO_RAD");
					consPorcentajeDescRad = StringUtils.replace(consPorcentajeDescRad, "#{infraccion}", "'"+infrac.getInfracNum()+"'");
					consPorcentajeDescRad = StringUtils.replace(consPorcentajeDescRad, "#{estatus}", "0");
					
					cifrVO.setTotalReasignaciones(0);
					cifrVO.setPorcentajeCondonacion(0);
					cifrVO.setPorcentajeDescuento1(0);
					cifrVO.setPorcentajeDescuento2(0);

					//CONSULTA EL TOTAL DE REASIGNACIONES HISTORICAS DE LA INFRACCION
					PorcentajeDescuentoLCVO porcentajeDescuentoLCVO = reasignacionLCMBDAO.consPorcentajeDescRad(consPorcentajeDescRad);
					if(porcentajeDescuentoLCVO != null){
						cifrVO.setTotalReasignaciones(porcentajeDescuentoLCVO.getTotalReasignaciones());
						cifrVO.setPorcentajeCondonacion(porcentajeDescuentoLCVO.getPorcentajeCorresponde());
						cifrVO.setPorcentajeDescuento1(porcentajeDescuentoLCVO.getPorcenDescuento1());
						cifrVO.setPorcentajeDescuento2(porcentajeDescuentoLCVO.getPorcenDescuento2());
					}
					
					//SE ASIGNA DESCUENTO SEGUN CONFIGURACION ESTABLECIDA
					if(tipoDescuento == 1){//Se le aplica el descuento maximo
						cifrVO.setPorcentajeCondonacion(porcentajeDescuentoLCVO.getPorcenDescuento1()); //Se le aplica el descuento maximo
					}else if(tipoDescuento == 2){//Se le aplica el descuento minimo
						cifrVO.setPorcentajeCondonacion(porcentajeDescuentoLCVO.getPorcenDescuento2()); //Se le aplica el descuento minimo
					}else if(tipoDescuento == 3){//A partir de la segunda vez aplica solo 50% de descuento (bandera = 0)
						//VALIDA SI LA REASIGNACION DE LINEA DE CAPTURA SE HA REALIZADO ANTERIORMENTE
						if(cifrVO.getTotalReasignaciones() >= 1){
							//A partir de la segunda vez aplica solo 50% de descuento (bandera = 0)
							cifrVO.setPorcentajeCondonacion(cifrVO.getPorcentajeDescuento2());
						}
					}
					
					//CONSULTA DE ID DE CONDONACION 1 = 80% DESC, 0 = 50% DESC, 2 = 0% DESC
					consCondonacion = StringUtils.replace(consCondonacion, "#{PorcentajeCondonacion}", cifrVO.getPorcentajeCondonacion().toString());
					Integer Condonacion = reasignacionLCMBDAO.consCondonacion(consCondonacion);
					cifrVO.setAplicaCondonacion(Condonacion);
					
					listaDetalleInfracs.add(cifrVO);
				}else{
					registrarFoliosInexistentes(infrac.getInfracNum(), userId, lote);
				}
				//---------------------------------------------
				// 			TERMINA INFRACCIONES_RADAR
				//---------------------------------------------
			}else if(isFolioRadar.equals("V_WS_SF_LINEAC_REASIGNA_ALL")){
				//---------------------------------------------
				// 			INICIA INFRACCIONES
				//---------------------------------------------
				
				if(usuarioOld!=null){
					try{
						fechaOld = objetotiempo.getStringDateFromFormta("dd/MM/yyyy", reasignacionLCMBDAO.sqlFechaOldInfrac(infrac.getInfracNum()));
					}catch (Exception e){
						//throw new NotFoundException("Ha ocurrido un imprevisto! , por favor contacte al administrador");
						System.err.print("No se encontro la fecha de emision de infraccion " + infrac.getInfracNum() + "--" + e);
					}
				}
				
				newInfracRadar.setFechaEmision(loteFechaEmision);
				newInfracRadar.setNumeroControlInterno(infrac.getInfracNum());
				newReasignaLineaCaptura.setInfracRadarVO(newInfracRadar);
				newReasignaLineaCaptura.setUsuario(userId.toString());
				oldInfracRadar.setFechaEmision(fechaOld);
				oldReasignaLineaCaptura.setInfracRadarVO(oldInfracRadar);
				oldReasignaLineaCaptura.setUsuario(usuarioOld);
				ArrayList<BitacoraCambiosVO> bitacoraCambiosVOs = new ArrayList<BitacoraCambiosVO>();
				
				reasignacionLCMMBDAO.sqlSpRadarLcReasignaFechaEm(fecha, userId, infrac.getInfracNum());
				if(usuarioOld!=null){
					try{
						bitacoraCambiosVOs = bitTrBitUpInfracRadar.guardarCambiosBitacora(newReasignaLineaCaptura, oldReasignaLineaCaptura);
						bitacoraCambiosService.guardarListaBitacoraCambios(bitacoraCambiosVOs);
					}catch (Exception e){
						//throw new NotFoundException("Ha ocurrido un imprevisto! , por favor contacte al administrador");
						System.err.print("Ha ocurrido un imprevisto!, por favor contacte al administrador " + e);
					}
				}
				
				cifrVO = reasignacionLCMBDAO.buscaInfraccionByFolio(infrac.getInfracNum());
				if(cifrVO != null){
					String consPorcentajeDescInfrac = parametrosCons.get("CONS_PORCENTAJE_DESCUENTO_INFRAC");
					consPorcentajeDescInfrac = StringUtils.replace(consPorcentajeDescInfrac, "#{infraccion}", "'"+infrac.getInfracNum()+"'");
					consPorcentajeDescInfrac = StringUtils.replace(consPorcentajeDescInfrac, "#{estatus}", "0");
					
					cifrVO.setTotalReasignaciones(0);
					cifrVO.setPorcentajeCondonacion(0);
					cifrVO.setPorcentajeDescuento1(0);
					cifrVO.setPorcentajeDescuento2(0);
					PorcentajeDescuentoLCVO porcentajeDescuentoLCVO = reasignacionLCMBDAO.consPorcentajeDescInfrac(consPorcentajeDescInfrac);
					if(porcentajeDescuentoLCVO != null){
						cifrVO.setTotalReasignaciones(porcentajeDescuentoLCVO.getTotalReasignaciones());
						cifrVO.setPorcentajeCondonacion(porcentajeDescuentoLCVO.getPorcentajeCorresponde());
						cifrVO.setPorcentajeDescuento1(porcentajeDescuentoLCVO.getPorcenDescuento1());
						cifrVO.setPorcentajeDescuento2(porcentajeDescuentoLCVO.getPorcenDescuento2());
					}
					
					//SE ASIGNA DESCUENTO SEGUN CONFIGURACION ESTABLECIDA
					if(tipoDescuento == 1){//Se le aplica el descuento maximo
						cifrVO.setPorcentajeCondonacion(porcentajeDescuentoLCVO.getPorcenDescuento1()); //Se le aplica el descuento maximo
					}else if(tipoDescuento == 2){//Se le aplica el descuento minimo
						cifrVO.setPorcentajeCondonacion(porcentajeDescuentoLCVO.getPorcenDescuento2()); //Se le aplica el descuento minimo
					}else if(tipoDescuento == 3){//A partir de la segunda vez aplica solo 50% de descuento (bandera = 0)
						//VALIDA SI LA REASIGNACION DE LINEA DE CAPTURA SE HA REALIZADO ANTERIORMENTE
						if(cifrVO.getTotalReasignaciones() >= 1){
							//A partir de la segunda vez aplica solo 50% de descuento (bandera = 0)
							cifrVO.setPorcentajeCondonacion(cifrVO.getPorcentajeDescuento2());
						}
					}
					
					//CONSULTA DE ID DE CONDONACION 1 = 80% DESC, 0 = 50% DESC, 2 = 0% DESC
					consCondonacion = StringUtils.replace(consCondonacion, "#{PorcentajeCondonacion}", cifrVO.getPorcentajeCondonacion().toString());
					Integer Condonacion = reasignacionLCMBDAO.consCondonacion(consCondonacion);
					cifrVO.setAplicaCondonacion(Condonacion);
					listaDetalleInfracs.add(cifrVO);
				}else{
					registrarFoliosInexistentes(infrac.getInfracNum(), userId, lote);
				}
				//---------------------------------------------
				// 			TERMINA INFRACCIONES
				//---------------------------------------------
			}else if(isFolioRadar.equals("V_WS_SF_LINEAC_REASIGNA_DIG")){
				//---------------------------------------------
				// 		INICIA INFRACCIONES_DIGITALIZACION
				//---------------------------------------------
				
				cifrVO = reasignacionLCMBDAO.buscaInfraccionDigByFolio(infrac.getInfracNum());
				if(cifrVO != null){
					String consPorcentajeDescDig = parametrosCons.get("CONS_PORCENTAJE_DESCUENTO_DIG");
					consPorcentajeDescDig = StringUtils.replace(consPorcentajeDescDig, "#{infraccion}", "'"+infrac.getInfracNum()+"'");
					consPorcentajeDescDig = StringUtils.replace(consPorcentajeDescDig, "#{estatus}", "0");
					
					PorcentajeDescuentoLCVO porcentajeDescuentoLCVO = reasignacionLCMBDAO.consPorcentajeDescDig(consPorcentajeDescDig);
					if(porcentajeDescuentoLCVO != null){
						cifrVO.setTotalReasignaciones(porcentajeDescuentoLCVO.getTotalReasignaciones());
						cifrVO.setPorcentajeCondonacion(porcentajeDescuentoLCVO.getPorcentajeCorresponde());
						cifrVO.setPorcentajeDescuento1(porcentajeDescuentoLCVO.getPorcenDescuento1());
						cifrVO.setPorcentajeDescuento2(porcentajeDescuentoLCVO.getPorcenDescuento2());
					}
					
					//SE ASIGNA DESCUENTO SEGUN CONFIGURACION ESTABLECIDA
					if(tipoDescuento == 1){//Se le aplica el descuento maximo
						cifrVO.setPorcentajeCondonacion(porcentajeDescuentoLCVO.getPorcenDescuento1()); //Se le aplica el descuento maximo
					}else if(tipoDescuento == 2){//Se le aplica el descuento minimo
						cifrVO.setPorcentajeCondonacion(porcentajeDescuentoLCVO.getPorcenDescuento2()); //Se le aplica el descuento minimo
					}else if(tipoDescuento == 3){//A partir de la segunda vez aplica solo 50% de descuento (bandera = 0)
						//VALIDA SI LA REASIGNACION DE LINEA DE CAPTURA SE HA REALIZADO ANTERIORMENTE
						if(cifrVO.getTotalReasignaciones() >= 1){
							//A partir de la segunda vez aplica solo 50% de descuento (bandera = 0)
							cifrVO.setPorcentajeCondonacion(cifrVO.getPorcentajeDescuento2());
						}
					}
					
					//CONSULTA DE ID DE CONDONACION 1 = 80% DESC, 0 = 50% DESC, 2 = 0% DESC
					consCondonacion = StringUtils.replace(consCondonacion, "#{PorcentajeCondonacion}", cifrVO.getPorcentajeCondonacion().toString());
					Integer Condonacion = reasignacionLCMBDAO.consCondonacion(consCondonacion);
					cifrVO.setAplicaCondonacion(Condonacion);
					listaDetalleInfracs.add(cifrVO);
				}else{
					registrarFoliosInexistentes(infrac.getInfracNum(), userId, lote);
				}
				//---------------------------------------------
				// 		TERMINA INFRACCIONES_DIGITALIZACION
				//---------------------------------------------
			}
		}
		return listaDetalleInfracs;
	}
	
	private void registrarFoliosInexistentes(String infracNum, Long userId, Integer lote){
		//Marca la infraccion actual como Inexistente.
		reasignacionLCMMBDAO.sqlMarcarInexistente(infracNum);
		
		//Registra en el historial el estatus de la infraccion.
		Integer count = reasignacionLCMMBDAO.sqlValdarRegInex(infracNum, lote);
		
		if(count <= 0){
			reasignacionLCMMBDAO.sqlRegistrarInexistente(infracNum, userId, lote);
		}
	}
	
	private RespuestaWSReasignaLineaCapturaMasivaVO convertirLCToLCM(RespuestaWSReasignaLineaCapturaVO rwsrlcVO, String lote){
		RespuestaWSReasignaLineaCapturaMasivaVO rwsrlcmVO = new RespuestaWSReasignaLineaCapturaMasivaVO();
		
		//WS
		rwsrlcmVO.setLineacapturaCB(rwsrlcVO.getLineacapturaCB());
		rwsrlcmVO.setError(rwsrlcVO.getError());
		rwsrlcmVO.setError_descripcion(rwsrlcVO.getError_descripcion());
		
		//DB
		rwsrlcmVO.setFolio(rwsrlcVO.getFolio());
		rwsrlcmVO.setFecha_infraccion(rwsrlcVO.getFecha_infraccion());
		rwsrlcmVO.setSalario_minimo(rwsrlcVO.getSalario_minimo());
		rwsrlcmVO.setNum_dias(rwsrlcVO.getNum_dias());
		rwsrlcmVO.setImporte(rwsrlcVO.getImporte());
		rwsrlcmVO.setDescuento(rwsrlcVO.getDescuento());
		rwsrlcmVO.setRecargos(rwsrlcVO.getRecargos());
		rwsrlcmVO.setTotal(rwsrlcVO.getTotal());
		rwsrlcmVO.setVigencia(rwsrlcVO.getVigencia());
		rwsrlcmVO.setLineacaptura(rwsrlcVO.getLineacaptura());
		rwsrlcmVO.setUsuario(rwsrlcVO.getUsuario());
		rwsrlcmVO.setEstatusConsumo(rwsrlcVO.getEstatusConsumo());
		rwsrlcmVO.setEstatusResultado(rwsrlcVO.getEstatusResultado());
		rwsrlcmVO.setLote(lote);
		
		return rwsrlcmVO;
	}
	
	private Map<String, Object> cleanTableReasignado(String[][] stats, Integer lote){
		Map<String, Object> respuesta = new HashMap<String, Object>();
		ArrayList<String> listaInfracCancel = new ArrayList<String>();
		String dataInfCan = null;
		
		int codigo = 0;
    	for (String s:stats[1])
    	{
    		if(Integer.parseInt(s) != 0){
    			codigo = 1;
    		}
    		else
    		{
    			codigo = 0; break;
    		}
    	}
    	
    	if(codigo != 0){
    		Integer cantInexistentes = reasignacionLCMMBDAO.sqlInexistente();
    		
    		if(cantInexistentes > 0){
    			reasignacionLCMMBDAO.sqlActualizarLoteConInexistentes(cantInexistentes, lote);
    			listaInfracCancel = reasignacionLCMMBDAO.infracCanceladas();
    			reasignacionLCMMBDAO.sqlQuitarInex();
    		}else{
    			reasignacionLCMMBDAO.sqlActualizarLoteSinInexistentes(lote);
    		}
    	}else{
    		Integer cantInexistentes = reasignacionLCMMBDAO.sqlInexistente();
    		reasignacionLCMMBDAO.sqlActualizarLoteConInexistentes(cantInexistentes, lote);
			listaInfracCancel = reasignacionLCMMBDAO.infracCanceladas();
			reasignacionLCMMBDAO.sqlQuitarInex();
    	}
    	
    	reasignacionLCMMBDAO.sqlProceso(Integer.parseInt("0"), lote);
		
		reasignacionLCMMBDAO.sqlClear();
		
		
		dataInfCan = "";
        int sizeCan = listaInfracCancel.size();
        for(int xCan = 0; xCan < sizeCan; xCan++){
        	if((xCan + 1) == sizeCan){
        		dataInfCan += listaInfracCancel.get(xCan);
        	}else{
        		dataInfCan += listaInfracCancel.get(xCan) + ",";
        	}
        }
		if(codigo == 0 && listaInfracCancel.isEmpty()){
			respuesta.put("respuesta", Boolean.FALSE);
		}else if(codigo == 0 && !listaInfracCancel.isEmpty()){
			respuesta.put("respuesta", Boolean.TRUE);
		}else if(codigo != 0){
			respuesta.put("respuesta", Boolean.TRUE);
		}
		//respuesta.put("respuesta", codigo == 0 ? Boolean.FALSE:Boolean.TRUE);
		respuesta.put("respuesta2", dataInfCan);
		return respuesta;
	}

	public Map<String, String> getParametrosLP() {
		List<Map<String, String>> listaParametros = reasignacionLCMBDAO.buscarQuerys();
		Map<String, String> parametros = new HashMap<String, String>();
		for(Map<String, String> registro : listaParametros) {
			parametros.put(registro.get("CD_LLAVE_P_CONFIG"), registro.get("CD_VALOR_P_CONFIG"));
		}
		return parametros;
	}
}
