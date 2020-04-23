package mx.com.teclo.saicdmx.negocio.service.lineadecaptura;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.ProtocolException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

import mx.com.teclo.saicdmx.bitacora.cambios.infraccionesRadar.BitTrBitUpInfracRadar;
import mx.com.teclo.saicdmx.bitacora.cambios.lineaDeCaptura.BitSpRadarLcReasigna;
import mx.com.teclo.saicdmx.negocio.service.estadistica.TipoInfraccionService;
import mx.com.teclo.saicdmx.negocio.service.semovi.ParametroService;
import mx.com.teclo.saicdmx.pdf.lineadecaptura.PagoFinanzas;
import mx.com.teclo.saicdmx.persistencia.mybatis.dao.lineadecaptura.ReasignacionLCMMyBatisDAO;
import mx.com.teclo.saicdmx.persistencia.mybatis.dao.lineadecaptura.ReasignacionLCMyBatisDAO;
import mx.com.teclo.saicdmx.persistencia.vo.infracciones.VSSPInfracConsGralFVO;
import mx.com.teclo.saicdmx.persistencia.vo.lineadecaptura.BitReasignacionLineaCapturaVO;
import mx.com.teclo.saicdmx.persistencia.vo.lineadecaptura.ConsultaInfraccionForReasignacionVO;
import mx.com.teclo.saicdmx.persistencia.vo.lineadecaptura.DetalleDeReasignacionesInfraccionVO;
import mx.com.teclo.saicdmx.persistencia.vo.lineadecaptura.PorcentajeDescuentoLCVO;
import mx.com.teclo.saicdmx.persistencia.vo.lineadecaptura.RespuestaWSReasignaLineaCapturaVO;
import mx.com.teclo.saicdmx.util.comun.RutinasTiempoImpl;
import mx.com.teclo.saicdmx.ws.lineadecaptura.WSReasignaLineaCaptura;
import mx.com.teclomexicana.arquitectura.ortogonales.seguridad.vo.BitacoraCambiosVO;
import mx.com.teclomexicana.arquitectura.ortogonales.service.bitacora.BitacoraCambiosService;

@Service
public class ReasignacionLCServiceImpl implements ReasignacionLCService{

	@Autowired
	private ReasignacionLCMyBatisDAO reasignacionLCMyBatisDAO;
	
	@Autowired
	private ParametroService parametros;
	
	@Autowired
	private BitacoraCambiosService bitacoraCambiosService;
	
	@Autowired
	private BitTrBitUpInfracRadar bitTrBitUpInfracRadar;

	@Autowired
	private BitSpRadarLcReasigna bitSpRadarLcReasigna;
	
	@Autowired
	private ReasignacionLCMMyBatisDAO reasignacionLCMMBDAO;
	
	/***
	 * {@inheritDoc}
	 */
	@Override
	public ConsultaInfraccionForReasignacionVO buscaInfraccionRadarByFolio(String folio, String isFolioRadar) {
		ConsultaInfraccionForReasignacionVO consultaInfraccionForReasignacionVO = null;
		
		Map<String, String> parametros = getParametrosLP(); 
		String consCondonacion = parametros.get("CONS_CONDONACION");
		
		if(isFolioRadar.equals("V_WS_SF_LINEAC_REASIGNA")){
			//---------------------------------------------
			// 			INICIA INFRACCIONES_RADAR
			//---------------------------------------------
			
			consultaInfraccionForReasignacionVO = reasignacionLCMyBatisDAO.buscaInfraccionRadarByFolio(folio);
			String consPorcentajeDescRad = parametros.get("CONS_PORCENTAJE_DESCUENTO_RAD");
			consPorcentajeDescRad = StringUtils.replace(consPorcentajeDescRad, "#{infraccion}", "'"+folio+"'");
			consPorcentajeDescRad = StringUtils.replace(consPorcentajeDescRad, "#{estatus}", "0");
			
			if(consultaInfraccionForReasignacionVO != null){
				PorcentajeDescuentoLCVO porcentajeDescuentoLCVO = reasignacionLCMyBatisDAO.consPorcentajeDescRad(consPorcentajeDescRad);
				if(porcentajeDescuentoLCVO != null){
					consultaInfraccionForReasignacionVO.setTotalReasignaciones(porcentajeDescuentoLCVO.getTotalReasignaciones());
					consultaInfraccionForReasignacionVO.setPorcentajeCondonacion(porcentajeDescuentoLCVO.getPorcentajeCorresponde());
					consultaInfraccionForReasignacionVO.setPorcentajeDescuento1(porcentajeDescuentoLCVO.getPorcenDescuento1());
					consultaInfraccionForReasignacionVO.setPorcentajeDescuento2(porcentajeDescuentoLCVO.getPorcenDescuento2());
				}
				//VALIDA SI LA REASIGNACION DE LINEA DE CAPTURA SE HA REALIZADO ANTERIORMENTE
				if(consultaInfraccionForReasignacionVO.getTotalReasignaciones() >= 1){
					consultaInfraccionForReasignacionVO.setPorcentajeCondonacion(consultaInfraccionForReasignacionVO.getPorcentajeDescuento2());
					//A partir de la segunda vez aplica solo 50% de descuento (bandera = 0)
				}
				//VALIDA SI A LA INFRACCION LE CORRESPONDE LA CONDONACION DEL 80% (bandera = 1)
				consCondonacion = StringUtils.replace(consCondonacion, "#{PorcentajeCondonacion}", consultaInfraccionForReasignacionVO.getPorcentajeCondonacion().toString());
				Integer Condonacion = reasignacionLCMyBatisDAO.consCondonacion(consCondonacion);
				consultaInfraccionForReasignacionVO.setAplicaCondonacion(Condonacion);
				
				//VALIDA SI A LA INFRACCION LE CORRESPONDE LA CONDONACION DEL 80% (bandera = 1)
//				consultaInfraccionForReasignacionVO.setAplicaCondonacion(reasignacionLCMyBatisDAO.ConsultaMotivoCondonacion(folio, 80));
//				consultaInfraccionForReasignacionVO.setTotalReasignaciones(0);
//				consultaInfraccionForReasignacionVO.setPorcentajeCondonacion(0);
//				consultaInfraccionForReasignacionVO.setPorcentajeDescuento1(0);
//				consultaInfraccionForReasignacionVO.setPorcentajeDescuento2(0);
				
				//CONSULTA EL TOTAL DE REASIGNACIONES HISTORICAS DE LA INFRACCION
//				PorcentajeDescuentoLCVO porcentajeDescuentoLCVO = reasignacionLCMyBatisDAO.ConsultarPorcentajeDescuento(folio, 0);
//				if(porcentajeDescuentoLCVO != null){
//					consultaInfraccionForReasignacionVO.setTotalReasignaciones(porcentajeDescuentoLCVO.getTotalReasignaciones());
//					consultaInfraccionForReasignacionVO.setPorcentajeCondonacion(porcentajeDescuentoLCVO.getPorcentajeCorresponde());
//					consultaInfraccionForReasignacionVO.setPorcentajeDescuento1(porcentajeDescuentoLCVO.getPorcenDescuento1());
//					consultaInfraccionForReasignacionVO.setPorcentajeDescuento2(porcentajeDescuentoLCVO.getPorcenDescuento2());
//				}
//				
//				//VALIDA SI LA REASIGNACION DE LINEA DE CAPTURA SE HA REALIZADO ANTERIORMENTE
//				if((consultaInfraccionForReasignacionVO.getAplicaCondonacion() == 1) && (consultaInfraccionForReasignacionVO.getTotalReasignaciones() >= 1)){			
//					consultaInfraccionForReasignacionVO.setAplicaCondonacion(0);  //A partir de la segunda vez aplica solo 50% de descuento (bandera = 0)
//				}
			}
			//---------------------------------------------
			// 			TERMINA INFRACCIONES_RADAR
			//---------------------------------------------
		}else if(isFolioRadar.equals("V_WS_SF_LINEAC_REASIGNA_ALL")){
			//---------------------------------------------
			// 			INICIA INFRACCIONES
			//---------------------------------------------
			consultaInfraccionForReasignacionVO = reasignacionLCMyBatisDAO.buscaInfraccionByFolio(folio);
			String consPorcentajeDescInfrac = parametros.get("CONS_PORCENTAJE_DESCUENTO_INFRAC");
			consPorcentajeDescInfrac = StringUtils.replace(consPorcentajeDescInfrac, "#{infraccion}", "'"+folio+"'");
			consPorcentajeDescInfrac = StringUtils.replace(consPorcentajeDescInfrac, "#{estatus}", "0");
			
			if(consultaInfraccionForReasignacionVO != null){
				
				PorcentajeDescuentoLCVO porcentajeDescuentoLCVO = reasignacionLCMyBatisDAO.consPorcentajeDescInfrac(consPorcentajeDescInfrac);
				if(porcentajeDescuentoLCVO != null){
					consultaInfraccionForReasignacionVO.setTotalReasignaciones(porcentajeDescuentoLCVO.getTotalReasignaciones());
					consultaInfraccionForReasignacionVO.setPorcentajeCondonacion(porcentajeDescuentoLCVO.getPorcentajeCorresponde());
					consultaInfraccionForReasignacionVO.setPorcentajeDescuento1(porcentajeDescuentoLCVO.getPorcenDescuento1());
					consultaInfraccionForReasignacionVO.setPorcentajeDescuento2(porcentajeDescuentoLCVO.getPorcenDescuento2());
				}
				//VALIDA SI LA REASIGNACION DE LINEA DE CAPTURA SE HA REALIZADO ANTERIORMENTE
				if(consultaInfraccionForReasignacionVO.getTotalReasignaciones() >= 1){
					consultaInfraccionForReasignacionVO.setPorcentajeCondonacion(consultaInfraccionForReasignacionVO.getPorcentajeDescuento2());
					//A partir de la segunda vez aplica solo 50% de descuento (bandera = 0)
				}
				//VALIDA SI A LA INFRACCION LE CORRESPONDE LA CONDONACION DEL 80% (bandera = 1)
				consCondonacion = StringUtils.replace(consCondonacion, "#{PorcentajeCondonacion}", consultaInfraccionForReasignacionVO.getPorcentajeCondonacion().toString());
				Integer Condonacion = reasignacionLCMyBatisDAO.consCondonacion(consCondonacion);
				consultaInfraccionForReasignacionVO.setAplicaCondonacion(Condonacion);
//				//VALIDA SI A LA INFRACCION LE CORRESPONDE LA CONDONACION DEL 80% (bandera = 1)
//				consultaInfraccionForReasignacionVO.setAplicaCondonacion(reasignacionLCMyBatisDAO.ConsultaMotivoCondonacionInfrac(folio, 80));
//				consultaInfraccionForReasignacionVO.setTotalReasignaciones(0);
//				consultaInfraccionForReasignacionVO.setPorcentajeCondonacion(0);
//				consultaInfraccionForReasignacionVO.setPorcentajeDescuento1(0);
//				consultaInfraccionForReasignacionVO.setPorcentajeDescuento2(0);
//				
//				//CONSULTA EL TOTAL DE REASIGNACIONES HISTORICAS DE LA INFRACCION
//				PorcentajeDescuentoLCVO porcentajeDescuentoLCVO = reasignacionLCMyBatisDAO.ConsultarPorcentajeDescuentoInfrac(folio, 0);
//				if(porcentajeDescuentoLCVO != null){
//					consultaInfraccionForReasignacionVO.setTotalReasignaciones(porcentajeDescuentoLCVO.getTotalReasignaciones());
//					consultaInfraccionForReasignacionVO.setPorcentajeCondonacion(porcentajeDescuentoLCVO.getPorcentajeCorresponde());
//					consultaInfraccionForReasignacionVO.setPorcentajeDescuento1(porcentajeDescuentoLCVO.getPorcenDescuento1());
//					consultaInfraccionForReasignacionVO.setPorcentajeDescuento2(porcentajeDescuentoLCVO.getPorcenDescuento2());
//				}
//				
//				//VALIDA SI LA REASIGNACION DE LINEA DE CAPTURA SE HA REALIZADO ANTERIORMENTE
//				if((consultaInfraccionForReasignacionVO.getAplicaCondonacion() == 1) && (consultaInfraccionForReasignacionVO.getTotalReasignaciones() >= 1)){			
//					consultaInfraccionForReasignacionVO.setAplicaCondonacion(0);  //A partir de la segunda vez aplica solo 50% de descuento (bandera = 0)
//				}
			}
			//---------------------------------------------
			// 			TERMINA INFRACCIONES
			//---------------------------------------------
		}else if(isFolioRadar.equals("V_WS_SF_LINEAC_REASIGNA_DIG")){
			//---------------------------------------------
			// 		INICIA INFRACCIONES_DIGITALIZACION
			//---------------------------------------------
			
			consultaInfraccionForReasignacionVO = reasignacionLCMyBatisDAO.buscaInfraccionDigByFolio(folio);
			String consPorcentajeDescDig = parametros.get("CONS_PORCENTAJE_DESCUENTO_DIG");
			consPorcentajeDescDig = StringUtils.replace(consPorcentajeDescDig, "#{infraccion}", "'"+folio+"'");
			consPorcentajeDescDig = StringUtils.replace(consPorcentajeDescDig, "#{estatus}", "0");
			
			
			if(consultaInfraccionForReasignacionVO != null){
				PorcentajeDescuentoLCVO porcentajeDescuentoLCVO = reasignacionLCMyBatisDAO.consPorcentajeDescDig(consPorcentajeDescDig);
				if(porcentajeDescuentoLCVO != null){
					consultaInfraccionForReasignacionVO.setTotalReasignaciones(porcentajeDescuentoLCVO.getTotalReasignaciones());
					consultaInfraccionForReasignacionVO.setPorcentajeCondonacion(porcentajeDescuentoLCVO.getPorcentajeCorresponde());
					consultaInfraccionForReasignacionVO.setPorcentajeDescuento1(porcentajeDescuentoLCVO.getPorcenDescuento1());
					consultaInfraccionForReasignacionVO.setPorcentajeDescuento2(porcentajeDescuentoLCVO.getPorcenDescuento2());
				}
				//VALIDA SI LA REASIGNACION DE LINEA DE CAPTURA SE HA REALIZADO ANTERIORMENTE
				if(consultaInfraccionForReasignacionVO.getTotalReasignaciones() >= 1){
					consultaInfraccionForReasignacionVO.setPorcentajeCondonacion(consultaInfraccionForReasignacionVO.getPorcentajeDescuento2());
					//A partir de la segunda vez aplica solo 50% de descuento (bandera = 0)
				}
				//VALIDA SI A LA INFRACCION LE CORRESPONDE LA CONDONACION DEL 80% (bandera = 1)
				consCondonacion = StringUtils.replace(consCondonacion, "#{PorcentajeCondonacion}", consultaInfraccionForReasignacionVO.getPorcentajeCondonacion().toString());
				Integer Condonacion = reasignacionLCMyBatisDAO.consCondonacion(consCondonacion);
				consultaInfraccionForReasignacionVO.setAplicaCondonacion(Condonacion);
				
//				//VALIDA SI A LA INFRACCION LE CORRESPONDE LA CONDONACION DEL 80% (bandera = 1)
//				consultaInfraccionForReasignacionVO.setAplicaCondonacion(reasignacionLCMyBatisDAO.ConsultaMotivoCondonacionInfracDig(folio, 80));
//				consultaInfraccionForReasignacionVO.setTotalReasignaciones(0);
//				consultaInfraccionForReasignacionVO.setPorcentajeCondonacion(0);
//				consultaInfraccionForReasignacionVO.setPorcentajeDescuento1(0);
//				consultaInfraccionForReasignacionVO.setPorcentajeDescuento2(0);
//				
//				//CONSULTA EL TOTAL DE REASIGNACIONES HISTORICAS DE LA INFRACCION
//				PorcentajeDescuentoLCVO porcentajeDescuentoLCVO = reasignacionLCMyBatisDAO.ConsultarPorcentajeDescuentoInfracDig(folio, 0);
//				if(porcentajeDescuentoLCVO != null){
//					consultaInfraccionForReasignacionVO.setTotalReasignaciones(porcentajeDescuentoLCVO.getTotalReasignaciones());
//					consultaInfraccionForReasignacionVO.setPorcentajeCondonacion(porcentajeDescuentoLCVO.getPorcentajeCorresponde());
//					consultaInfraccionForReasignacionVO.setPorcentajeDescuento1(porcentajeDescuentoLCVO.getPorcenDescuento1());
//					consultaInfraccionForReasignacionVO.setPorcentajeDescuento2(porcentajeDescuentoLCVO.getPorcenDescuento2());
//				}
//				
//				//VALIDA SI LA REASIGNACION DE LINEA DE CAPTURA SE HA REALIZADO ANTERIORMENTE
//				if((consultaInfraccionForReasignacionVO.getAplicaCondonacion() == 1) && (consultaInfraccionForReasignacionVO.getTotalReasignaciones() >= 1)){			
//					consultaInfraccionForReasignacionVO.setAplicaCondonacion(0);  //A partir de la segunda vez aplica solo 50% de descuento (bandera = 0)
//				}
			}
			//---------------------------------------------
			// 		TERMINA INFRACCIONES_DIGITALIZACION
			//---------------------------------------------
		}
		return consultaInfraccionForReasignacionVO;
	}
	
	/***
	 * {@inheritDoc}
	 */
	@Override
	public List<DetalleDeReasignacionesInfraccionVO> consultaDetalleReasignacionesByInfraccion(String folio, Integer estatus) {
		List<DetalleDeReasignacionesInfraccionVO> listaReasignaciones = null;
		listaReasignaciones = reasignacionLCMyBatisDAO.ConsultaDetalleReasignacionesByInfraccion(folio, estatus);
		return listaReasignaciones;
	}
	
	/***
	 * {@inheritDoc}
	 * @throws ParseException 
	 */
	@Override
	public void UpdateFechaEmParaLC(String folio, String fechareasignacion, Long usuario) throws ParseException {
		RutinasTiempoImpl objetotiempo = new RutinasTiempoImpl();
		Date fecha = objetotiempo.convertirStringDate(fechareasignacion, "dd/MM/yyyy");
		String usuarioOld = reasignacionLCMyBatisDAO.sqlUsuarioOld(folio);
		String autorizadoPorOld = reasignacionLCMyBatisDAO.sqlAutoriadoPorOld(folio);
		String fechaOld = "";
		if(usuarioOld!=null){
			//fechaOld = objetotiempo.getStringDateFromFormta("dd/MM/yyyy", reasignacionLCMyBatisDAO.sqlFechaOld(folio));
			try{
				fechaOld = objetotiempo.getStringDateFromFormta("dd/MM/yyyy", reasignacionLCMyBatisDAO.sqlFechaOld(folio));
			}catch (Exception e){
				//throw new NotFoundException("Ha ocurrido un imprevisto! , por favor contacte al administrador");
				System.err.print("Ha ocurrido un imprevisto! , por favor contacte al administrador Fecha de emision no encontrada para el folio "+ folio + " - " + e);
			}
		}
		RespuestaWSReasignaLineaCapturaVO newReasignaLineaCaptura = new RespuestaWSReasignaLineaCapturaVO();
		RespuestaWSReasignaLineaCapturaVO oldReasignaLineaCaptura = new RespuestaWSReasignaLineaCapturaVO();
		VSSPInfracConsGralFVO oldInfracRadar = new VSSPInfracConsGralFVO();
		VSSPInfracConsGralFVO newInfracRadar = new VSSPInfracConsGralFVO();
		newInfracRadar.setFechaEmision(fechareasignacion);
		newInfracRadar.setNumeroControlInterno(folio);
		newReasignaLineaCaptura.setInfracRadarVO(newInfracRadar);
		newReasignaLineaCaptura.setUsuario(usuario.toString());
		newReasignaLineaCaptura.setAutorizaId(usuario.toString());
		
		oldInfracRadar.setFechaEmision(fechaOld);
		oldReasignaLineaCaptura.setInfracRadarVO(oldInfracRadar);
		oldReasignaLineaCaptura.setUsuario(usuarioOld);
		oldReasignaLineaCaptura.setAutorizaId(autorizadoPorOld);
		
		ArrayList<BitacoraCambiosVO> bitacoraCambiosVOs = new ArrayList<BitacoraCambiosVO>();
		
		reasignacionLCMyBatisDAO.UpdateFechaEmForLC(folio, fecha, usuario);
		if(usuarioOld!=null){
			try{
				bitacoraCambiosVOs = bitTrBitUpInfracRadar.guardarCambiosBitacora(newReasignaLineaCaptura, oldReasignaLineaCaptura);
				bitacoraCambiosService.guardarListaBitacoraCambios(bitacoraCambiosVOs);
			}catch (Exception e){
				System.err.print("Ha ocurrido un imprevisto! , por favor contacte al administrador " + e);
			}
		}
	}
	
	/***
	 * {@inheritDoc}
	 */
	@Override
	public ByteArrayOutputStream generaReporteFUTPDF(DetalleDeReasignacionesInfraccionVO parametrosVO, String rutaTotalArchivo,
			String rutaTotalImagen) throws FileNotFoundException {
		PagoFinanzas pdf = new PagoFinanzas();
		return pdf.generaReporteFUTPDF(parametrosVO, rutaTotalArchivo, rutaTotalImagen);
	}

	/***
	 * {@inheritDoc}
	 * @throws ParseException 
	 */
	@Override
	public RespuestaWSReasignaLineaCapturaVO ReasignarLineaDeCaptura(ConsultaInfraccionForReasignacionVO infraccionRadar,
			Integer descuento, Long usuario, String isFolioRadar) throws ProtocolException, IOException, ParserConfigurationException, SAXException, ParseException {
		
		//Valores para consumo de webservices de SF
		//infraccionRadar.setUsuario("ssp_infraccion");
		//infraccionRadar.setPassword("c91f6bc53f25218e34c7c48020b2bcaf");
		//infraccionRadar.setClave(new BigDecimal("03"));
		
		Map<String, String> parametrosCons = getParametrosLP();
		String consCondonacion = parametrosCons.get("CONS_CONDONACION");
		String consCondonacionSeleccionada = "";
		Integer Condonacion = 0;
		
		if(descuento != null){
			if(descuento != infraccionRadar.getPorcentajeCondonacion()) {
				//Funcion solo para usuarios supervisores que pueden modificar el porcentaje de condonacion (80/50) en panatalla        
				infraccionRadar.setPorcentajeCondonacion(descuento);
				//if(infraccionRadar.getPorcentajeCondonacion() == 80){
				//	infraccionRadar.setAplicaCondonacion(1);
				//}else{        			
				//	infraccionRadar.setAplicaCondonacion(0);
				//}
				
				consCondonacionSeleccionada = StringUtils.replace(consCondonacion, "#{PorcentajeCondonacion}", infraccionRadar.getPorcentajeCondonacion().toString());
				Condonacion = reasignacionLCMyBatisDAO.consCondonacion(consCondonacionSeleccionada);
				infraccionRadar.setAplicaCondonacion(Condonacion);
			}
        }
		
		RespuestaWSReasignaLineaCapturaVO respuesta = new RespuestaWSReasignaLineaCapturaVO();	
		WSReasignaLineaCaptura servicioWS = new WSReasignaLineaCaptura();
		String proxyURL = parametros.getURLProxyLc();
		respuesta = servicioWS.servicio(infraccionRadar, parametros.getUrlWebServiceReasignarLineaCaptura(),proxyURL);
		
		//infraccionRadar.setCadenaXML(respuesta.getCadenaxml());
		//infraccionRadar.setIdUsuario(usuario);
		//infraccionRadar.setProxiURL(proxyURL);
		/** Guarda en bitacora los parametros enviados al servicio de finanzas**/
		BitReasignacionLineaCapturaVO peticionWS = new BitReasignacionLineaCapturaVO();
		peticionWS = respuesta.getPeticionWS();
		peticionWS.setIdUsuario(usuario);
		peticionWS.setIdOrigen(0);
		reasignacionLCMMBDAO.guardaBitacoraLcReasignaWS(peticionWS);
		
		/*Seteo pruebas
		respuesta.setFolio(infraccionRadar.getFolio());
		respuesta.setFecha_infraccion("16/10/15");
		respuesta.setSalario_minimo(String.valueOf(infraccionRadar.getSalarioMinimo()));
		respuesta.setNum_dias(String.valueOf(infraccionRadar.getDias()));
		respuesta.setImporte(String.valueOf(infraccionRadar.getImporte()));
		respuesta.setVigencia("16/10/17");
		respuesta.setDescuento("0");
		respuesta.setRecargos("0");
		respuesta.setLineacaptura("123");
		respuesta.setTotal(String.valueOf(infraccionRadar.getImporte()));
		respuesta.setError("0");
		respuesta.setError_descripcion("");*/
		
		if(respuesta.getError().equals("0")){ //respuesta correcta
			respuesta.setUsuario(String.valueOf(usuario));
			respuesta.setEstatusConsumo("0");
			respuesta.setEstatusResultado("OK");
		}else{
			respuesta.setUsuario(String.valueOf(usuario));
			respuesta.setFolio(infraccionRadar.getFolio());
		}
		
		RespuestaWSReasignaLineaCapturaVO newRespuesta = respuesta;
		RespuestaWSReasignaLineaCapturaVO oldRespuesta = null;
		ArrayList<BitacoraCambiosVO> bitacoraCambiosVOs = null;
		
		respuesta.setDescuento(respuesta.getDescuento() != null ? respuesta.getDescuento().length() >= 10 ? respuesta.getDescuento().substring(0, 9):respuesta.getDescuento() :null);
		
		if(isFolioRadar.equals("V_WS_SF_LINEAC_REASIGNA")){
			//---------------------------------------------
			// 			INICIA INFRACCIONES_RADAR
			//---------------------------------------------
			reasignacionLCMyBatisDAO.ReasignarLineaDeCaptura(respuesta);
			
			oldRespuesta = reasignacionLCMyBatisDAO.OldReasignarLineaDeCaptura(respuesta.getFolio());
			VSSPInfracConsGralFVO newInfracRadar = new VSSPInfracConsGralFVO();
			VSSPInfracConsGralFVO oldInfracRadar = new VSSPInfracConsGralFVO();
			newInfracRadar.setNumeroControlInterno(respuesta.getFolio());
			newRespuesta.setInfracRadarVO(newInfracRadar);
			oldRespuesta.setInfracRadarVO(oldInfracRadar);
			bitacoraCambiosVOs = new ArrayList<BitacoraCambiosVO>();
		}else if(isFolioRadar.equals("V_WS_SF_LINEAC_REASIGNA_ALL")){
			//---------------------------------------------
			// 			INICIA INFRACCIONES
			//---------------------------------------------
			reasignacionLCMyBatisDAO.ReasignarLineaDeCapturaInfrac(respuesta);
			
			oldRespuesta = reasignacionLCMyBatisDAO.OldReasignarLineaDeCapturaInfrac(respuesta.getFolio());
			VSSPInfracConsGralFVO newInfracRadar = new VSSPInfracConsGralFVO();
			VSSPInfracConsGralFVO oldInfracRadar = new VSSPInfracConsGralFVO();
			newInfracRadar.setNumeroControlInterno(respuesta.getFolio());
			newRespuesta.setInfracRadarVO(newInfracRadar);
			oldRespuesta.setInfracRadarVO(oldInfracRadar);
			bitacoraCambiosVOs = new ArrayList<BitacoraCambiosVO>();
			
		}else if(isFolioRadar.equals("V_WS_SF_LINEAC_REASIGNA_DIG")){
			//---------------------------------------------
			// 		INICIA INFRACCIONES_DIGITALIZACION
			//---------------------------------------------
			reasignacionLCMyBatisDAO.ReasignarLineaDeCapturaInfrac(respuesta);
			
			oldRespuesta = reasignacionLCMyBatisDAO.OldReasignarLineaDeCapturaInfracDig(respuesta.getFolio());
			VSSPInfracConsGralFVO newInfracRadar = new VSSPInfracConsGralFVO();
			VSSPInfracConsGralFVO oldInfracRadar = new VSSPInfracConsGralFVO();
			newInfracRadar.setNumeroControlInterno(respuesta.getFolio());
			newRespuesta.setInfracRadarVO(newInfracRadar);
			oldRespuesta.setInfracRadarVO(oldInfracRadar);
			bitacoraCambiosVOs = new ArrayList<BitacoraCambiosVO>();
		}
		
		/*Bitacota de Cambios */
		if(respuesta.getResultado() == 0){
			try{
				bitacoraCambiosVOs.add(bitSpRadarLcReasigna.bitacoraCambioReasignarLineaCaptura(respuesta));
				//Realiza insercion a bitacora desde el trigger al modificar infrac_linea_cap,infrc_linea_cap_vigencia,infrac_linea_cap_monto,autorizado_por
				bitacoraCambiosVOs.addAll(bitTrBitUpInfracRadar.guardarCambiosBitacora(newRespuesta, oldRespuesta));
				bitacoraCambiosService.guardarListaBitacoraCambios(bitacoraCambiosVOs);
			}catch(Exception e){System.err.println(e.getMessage());}
		}
		
		return respuesta;
	}
	
	public Map<String, String> getParametrosLP() {
		List<Map<String, String>> listaParametros = reasignacionLCMyBatisDAO.buscarQuerys();
		Map<String, String> parametros = new HashMap<String, String>();
		for(Map<String, String> registro : listaParametros) {
			parametros.put(registro.get("CD_LLAVE_P_CONFIG"), registro.get("CD_VALOR_P_CONFIG"));
		}
		return parametros;
	}
}
