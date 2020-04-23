package mx.com.teclo.saicdmx.negocio.service.fm;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.com.teclo.saicdmx.persistencia.hibernate.dao.fm.FMLotesDAO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.fm.FMLotesDTO;
import mx.com.teclo.saicdmx.persistencia.mybatis.dao.fm.FMDeteccionesMyBatisDAO;
import mx.com.teclo.saicdmx.persistencia.mybatis.dao.fm.FMLotesMyBatisDAO;
import mx.com.teclo.saicdmx.persistencia.vo.fm.FMDetalleVO;
import mx.com.teclo.saicdmx.persistencia.vo.fm.FMDeteccionesDisponiblesFCVO;
import mx.com.teclo.saicdmx.persistencia.vo.fm.FMDeteccionesVO;
import mx.com.teclo.saicdmx.persistencia.vo.fm.FMLotesVO;
import mx.com.teclo.saicdmx.persistencia.vo.fm.FMTipoArchivoFCVO;
import mx.com.teclo.saicdmx.persistencia.vo.fm.FMTipoFotocivicaVO;
import mx.com.teclo.saicdmx.persistencia.vo.fm.FMTiposDeteccionesVO;
import mx.com.teclo.saicdmx.util.comun.RutinasTiempoImpl;
import mx.com.teclo.saicdmx.util.enumerados.ArchivosNumberEnum;
import mx.com.teclomexicana.arquitectura.ortogonales.exception.BusinessException;
import mx.com.teclomexicana.arquitectura.ortogonales.exception.NotFoundException;
import mx.com.teclomexicana.generaexcel.reporteexcel.PeticionReporteBOImpl;
import mx.com.teclomexicana.generaexcel.vo.PeticioReporteVO;
import mx.com.teclomexicana.generaexcel.vo.PropiedadesReporte;

@Service
public class FMCrearLotesServiceImpl implements FMCrearLotesService{
	
	@Autowired
	private FMDeteccionesMyBatisDAO fmDeteccionesDAO;
	@Autowired
	private FMLotesMyBatisDAO fmLotesMyBatisDAO;
	@Autowired
	private FMLotesDAO fmLotesDAO;
	
	private RutinasTiempoImpl rutina;
	private Map reporte;
	
	@Override
	@Transactional (readOnly =  true)
	public Map<String, String> validarDeteccionesParaLote(String fechaInicio, String fechaFin, 
			Integer tipoPersona, String nombrePersona, 
			Integer tipoDeteccion, String nombreDeteccion, Integer origenPlacaF)
			throws BusinessException {
		Map response = new HashMap();
		Map<String, String> parametros = getParametrosLP();
		Integer paramCantidadRegReporteDet = Integer.parseInt(parametros.get("CANT_REG_REPORTE_DET"));
		String paramMensajeGenReporteDet = parametros.get("MENSAJE_GEN_REPORTE_DET");
		Integer numberDetecciones = 0;
		Integer procesado = 0;
		Integer valido = 1;
		Integer aceptada = 1;
		Integer generarReport = 1;
		List<String>  listMarcas = null;
		Integer origenPlaca = 0;
		origenPlaca = origenPlacaF;
		rutina = new RutinasTiempoImpl();
		Date fechaI = rutina.convertirStringDate(fechaInicio, "dd/MM/yy");
		Date fechaF = rutina.convertirStringDate(fechaFin, "dd/MM/yy");
		numberDetecciones = fmLotesMyBatisDAO.numeroDetecciones(fechaI, fechaF, procesado, Long.valueOf(tipoDeteccion), tipoPersona, origenPlaca, aceptada);
		if(numberDetecciones < 1){
			generarReport = -1;
			paramMensajeGenReporteDet = "No hay detecciones para crear el lote";
		}else {
			if(numberDetecciones > paramCantidadRegReporteDet) {
				generarReport = 0;
			}else {
				generarReport = 1;
			}
		}
		
		response.put("generarReport", generarReport.toString());
		response.put("paramMensajeGenReporteDet", paramMensajeGenReporteDet);
		response.put("numberDetecciones", numberDetecciones.toString());
		//reporte = this.crearReporteDeteccionesPorLote(fechaInicio, fechaFin, tipoDeteccion, tipoPersona, nombrePersona, nombreDeteccion, origenPlaca);
		return response;
	}
	
//	@SuppressWarnings("rawtypes")
//	@Override 
//	@Transactional (readOnly = true)
//	public FMLotesVO guardarLote(String fechaEmision, String nombreLote, String fechaInicio, 
//			String fechaFin, Integer anoSalario, String usuario, Integer tipoPersona, 
//			Integer tipoDeteccion, Integer origenPlaca, Integer stLCaptura, Integer stVCP)throws BusinessException {
//		
//		FMTipoFotocivicaVO tipoFotocivicaVO = fmDeteccionesDAO.consultaIdTipoDeteccion(tipoDeteccion);
//		Integer origenDeteccion = 0; 
//		if(origenPlaca == 0){
//			origenDeteccion = tipoFotocivicaVO.getIdArchivoTipoCDMX();
//		}else{
//			origenDeteccion = tipoFotocivicaVO.getIdArchivoTipoFora();
//		}
//		
//		FMLotesVO consultaLotesVO = null;
////		FMLotesDTO lote = fmLotesDAO.buscarLotesEnCreacion();
////		if(lote == null){
//			Long idLote = guardaLoteArchivoEnCreacion(fechaEmision, nombreLote, fechaInicio, 
//													fechaFin, anoSalario, usuario, 
//													tipoPersona, tipoFotocivicaVO.getIdTipoFotocivica() , origenPlaca, 
//													origenDeteccion, stLCaptura, stVCP);
//			Map response = creaFotomultaLote(fechaEmision, nombreLote, fechaInicio, fechaFin, anoSalario, usuario, 
//					tipoPersona, idLote, origenPlaca, tipoFotocivicaVO, origenDeteccion);
//						
//			Integer guardar = (Integer) response.get("guardar");
//			if(guardar > 0){
//				consultaLotesVO = (FMLotesVO) response.get("FMLotesVO");
//			}else{
//				throw new BusinessException("No hay detecciones para crear el lote");
//			}
//			complementaOficialNombre(idLote);
//			validaFormatoPlaca(idLote);
//			actualizaArchivosOrigen();
////		}else{
////			throw new BusinessException("Existe un lote en proceso de creación\nLote ID: " + lote.getId()
////			+"\nNombre: "+ lote.getNombre()+"\nEspere a que termine el proceso");
////		}
//		return consultaLotesVO;
//	}


	@SuppressWarnings("rawtypes")
	@Override 
	@Transactional (readOnly = true)
	public Long creacionLote(String fechaEmision, String nombreLote, String fechaInicio, 
			String fechaFin, Integer anoSalario, String usuario, Integer tipoPersona, 
			Integer tipoDeteccion, Integer origenPlaca, Integer stLCaptura, Integer stVCP)throws BusinessException {
		
		FMTipoFotocivicaVO tipoFotocivicaVO = fmDeteccionesDAO.consultaIdTipoDeteccion(tipoDeteccion);
		Integer origenDeteccion = 0; 
		if(origenPlaca == 0){
			origenDeteccion = tipoFotocivicaVO.getIdArchivoTipoCDMX();
		}else{
			origenDeteccion = tipoFotocivicaVO.getIdArchivoTipoFora();
		}
		
		Long idLote = guardaLoteArchivoEnCreacion(fechaEmision, nombreLote, fechaInicio, fechaFin, 
													anoSalario, usuario, tipoPersona, 
													tipoFotocivicaVO.getIdTipoFotocivica() , 
													origenPlaca, origenDeteccion, stLCaptura, stVCP);
		return idLote;
	}
	

	@SuppressWarnings("rawtypes")
	@Override 
	@Transactional (readOnly = true)
	public FMLotesVO crearLoteDetalle(Long idLote, String fechaEmision, String nombreLote, String fechaInicio, 
			String fechaFin, Integer anoSalario, String usuario, Integer tipoPersona, 
			Integer tipoDeteccion, Integer origenPlaca, Integer stLCaptura, Integer stVCP, String mensajeErr
			)throws BusinessException, NotFoundException {
		
		FMTipoFotocivicaVO tipoFotocivicaVO = fmDeteccionesDAO.consultaIdTipoDeteccion(tipoDeteccion);
		Integer origenDeteccion = 0; 
		if(origenPlaca == 0){
			origenDeteccion = tipoFotocivicaVO.getIdArchivoTipoCDMX();
		}else{
			origenDeteccion = tipoFotocivicaVO.getIdArchivoTipoFora();
		}
		
		FMLotesVO consultaLotesVO = null;
		Map response = creaFotomultaLote(fechaEmision, nombreLote, fechaInicio, fechaFin, anoSalario, usuario, 
					tipoPersona, idLote, origenPlaca, tipoFotocivicaVO, origenDeteccion);
		Integer guardar = (Integer) response.get("guardar");
		if(guardar > 0){
			consultaLotesVO = (FMLotesVO) response.get("FMLotesVO");
		}else{
			mensajeErr = "No hay detecciones para crear el lote";
			throw new NotFoundException("");
		}
		return consultaLotesVO;
	}
	
	@SuppressWarnings("rawtypes")
	@Override 
	@Transactional (readOnly = true)
	public void crearLoteValidaciones(Long idLote)throws BusinessException {
		Map<String, String> parametros = getParametrosLP();
		
		complementaOficialNombre(idLote);
		validaFormatoPlaca(idLote, parametros);
		actualizaArchivosOrigen(idLote);
	}
	
	@Transactional
	public Long guardaLoteArchivoEnCreacion(String fechaEmision, String nombreLote,	String fechaInicio, 
											String fechaFin, Integer anoSalario, String usuario, 
											Integer tipoPersona, Integer idTipoDeteccion, Integer origenPlaca,
											Integer origenDeteccion, Integer stLCaptura, Integer stVCP){
		rutina = new RutinasTiempoImpl();
		
		final Integer estatusID = 1;
		//final Integer enProceso = 0;
		final Boolean enProceso = false;
		final Integer enCreacion = 1;
		final Integer numerDetecciones = -1;
		Integer tipoProceso = 1; //Proceso CDMX
		if (origenDeteccion == 4 || origenDeteccion == 5 || origenDeteccion == 6){
			tipoProceso = 2; //Proceso Foraneo
		}
		
		Date fechaEmi = rutina.convertirStringDate(fechaEmision);
		Date fhIniPeriodo = rutina.convertirStringDate(fechaInicio);
		Date fhFinPeriodo = rutina.convertirStringDate(fechaFin);
		final Integer archivoTipo = origenPlaca; //RadarTipoArchivo.FOTOMULTA.getProcesoId();
		
		Long idLote = fmLotesMyBatisDAO.obtenerSiguienteLote();//id del lote
		String SalarioMin = fmLotesMyBatisDAO.getAnioSalarioMin(String.valueOf(anoSalario));
		
		FMLotesVO objectInsert = new FMLotesVO();
		objectInsert.setId(idLote);
		objectInsert.setNombre(nombreLote);
		objectInsert.setFechaEmision(fechaEmi);
		objectInsert.setEstatusProcesoId(estatusID);
		objectInsert.setEnProceso(enProceso);
		objectInsert.setOrigenLote(1);
		objectInsert.setIdTipoDeteccion(idTipoDeteccion);
		objectInsert.setCreadoPor(new Long(usuario));
		objectInsert.setModificadoPor(new Long(usuario));
		objectInsert.setUltimaModificacion(new Date());
		objectInsert.setAnioSalarioMinimo(SalarioMin);
		objectInsert.setArchivoTipo(origenDeteccion);
		objectInsert.setArchivoTipoProceso(tipoProceso);
		objectInsert.setCantidadProcesados(numerDetecciones);
		objectInsert.setSalariosMinimosId(anoSalario);
		objectInsert.setIdTipoPersona(tipoPersona);
		objectInsert.setStLCaptura(stLCaptura);
		objectInsert.setStVCP(stVCP);
		objectInsert.setFhIniPeriodo(fhIniPeriodo);
		objectInsert.setFhFinPeriodo(fhFinPeriodo);
		
		fmLotesMyBatisDAO.insertFMLoteFromFotomultas(objectInsert);
		fmLotesMyBatisDAO.insertRadarArchivoFromFotomultas(objectInsert);
		fmLotesMyBatisDAO.insertRadarArchivoTotalFromFotomultas(objectInsert);
		
		//fmLotesMyBatisDAO.insertarFMLoteBitProceso(idLote, new Long(usuario));
		fmLotesMyBatisDAO.insertarRadarArchivoBitProceso(idLote, new Long(usuario));
		//fmLotesMyBatisDAO.insertBitacoraCambiosRA(idLote, nombreLote, new Long(usuario));
		//fmLotesMyBatisDAO.insertBitacoraCambiosFM(idLote, nombreLote, new Long(usuario));
		return idLote;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Transactional
	public Map creaFotomultaLote(String fechaEmision, String nombreLote, String fechaInicio, 
								String fechaFin, Integer anoSalario, String usuario, 
								Integer tipoPersona, Long idLote, Integer origenPlaca, 
								FMTipoFotocivicaVO tipoFotocivicaVO, Integer origenDeteccion) {
	
		Map<String, String> parametros = getParametrosLP();
		Map response = new HashMap();
		final Integer procesado = 1;
		final Integer noProcesado = 0;
		rutina = new RutinasTiempoImpl();
		String queryInsertDT = parametros.get("INSERT_DET_TAI005_TO_RAD");
		String queryactualizaFotomultaDeteccionesPorLote = parametros.get("UPD_PROCESADO_RAD_TO_TAI005");
		String queryactualizaFotoCivicasDeteccionesPorLote = parametros.get("UPD_PROCESADO_RAD_TO_TAI027");
		
		FMLotesVO fmConsultaLotesVO = null;
		Date fechaI = rutina.convertirStringDate(fechaInicio, "dd/MM/yy");
		Date fechaF = rutina.convertirStringDate(fechaFin, "dd/MM/yy");
		
		Integer totalDet = fmLotesMyBatisDAO.buscaFotomultasDetecionesRangoFechasByFechaInfraccionSelect
				(fechaI, fechaF, noProcesado, tipoFotocivicaVO.getIdTipoFotocivica(), tipoPersona, origenPlaca);
		
		Integer r = fmLotesMyBatisDAO.insertarFMDetalleSelect(
				queryInsertDT, 
				idLote,
				noProcesado,
				tipoFotocivicaVO.getIdTipoFotocivica(),
				origenPlaca,
				tipoPersona,
				fechaI, 
				fechaF);
		
		if (r > 0) {
			fmLotesMyBatisDAO.actualizaFotomultaDeteccionesPorLote(
					queryactualizaFotomultaDeteccionesPorLote,
					procesado, idLote);
			
			fmLotesMyBatisDAO.actualizaFotoCivicasDeteccionesPorLote(
					queryactualizaFotoCivicasDeteccionesPorLote,
					procesado, idLote);
			
//			System.out.println(tipoFotocivicaVO.getIdTipoFotocivica());
//			System.out.println("total de detecciones en el lote: " + totalDet);		
			Date fechaE = rutina.convertirStringDate(fechaEmision, "dd/MM/yy");
			
			fmConsultaLotesVO = new FMLotesVO();
			fmConsultaLotesVO.setCantidadProcesados(totalDet);
			fmConsultaLotesVO.setComplementado(false);
			fmConsultaLotesVO.setFechaEmision(fechaE);
			fmConsultaLotesVO.setId(idLote);
			fmConsultaLotesVO.setNombre(nombreLote);
			
			response.put("guardar", 1);
			response.put("FMLotesVO", fmConsultaLotesVO);
		} else {
			response.put("guardar", 0);
		}
		return response;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Transactional
	public Map creaFotomultaLoteOLD(String fechaEmision, String nombreLote, String fechaInicio, 
								String fechaFin, Integer anoSalario, String usuario, 
								Integer tipoPersona, Long idLote, Integer origenPlaca, 
								FMTipoFotocivicaVO tipoFotocivicaVO, Integer origenDeteccion) {
	
		Map response = new HashMap();
		final Integer procesado = 1;
		final Integer noProcesado = 0;
		final Integer valido = 1;
		final Integer autorizado = 1;
		final Integer empleadoLogeado = Integer.parseInt(usuario);
		Integer cantidadProcesados = 0;
		rutina = new RutinasTiempoImpl();
		
		FMLotesVO fmConsultaLotesVO = null;
		Date fechaI = rutina.convertirStringDate(fechaInicio, "dd/MM/yy");
		Date fechaF = rutina.convertirStringDate(fechaFin, "dd/MM/yy");
		//modificar aqui
		List<FMDeteccionesVO> listaDetecciones = new ArrayList<FMDeteccionesVO>();
		//List<Long> listTiposDetecccion = new ArrayList<Long>();
		//System.out.println("total de ids tipo detecciones: "+tipoDeteccionVO.size());
//		for(int i=0;i<tipoDeteccionVO;i++) {
//			
//			List<FMDeteccionesVO> listresultemp = null;
//			listresultemp = fmLotesMyBatisDAO.buscaFotomultasDetecionesRangoFechasByFechaInfraccion
//					(fechaI, fechaF, noProcesado, tipoDeteccionVO.get(i).getIdTipoDeteccion(), origenPlaca);
//			listaDetecciones.addAll(listresultemp);
//			System.out.println(tipoDeteccionVO.get(i).getIdTipoDeteccion());
//			listTiposDetecccion.add(i, tipoDeteccionVO.get(i).getIdTipoDeteccion());
//		}
		
		List<FMDeteccionesVO> listresultemp = null;
//		listresultemp = fmLotesMyBatisDAO.buscaFotomultasDetecionesRangoFechasByFechaInfraccion
//				(fechaI, fechaF, noProcesado, tipoFotocivicaVO.getIdTipoFotocivica(), tipoPersona, origenPlaca);
		listaDetecciones.addAll(listresultemp);
		//System.out.println(tipoFotocivicaVO.getIdTipoFotocivica());
		//listTiposDetecccion.add(0, tipoDeteccionVO.getIdTipoDeteccion());
		
		
		//System.out.println("total de detecciones en el lote: "+listaDetecciones.size());
//		List<FMDeteccionesVO> listaDetecciones = fmLotesMyBatisDAO.buscaFotomultasDetecionesRangoFechasByFechaInfraccion
//				(fechaI, fechaF, noProcesado, tipoDeteccion, origenPlaca);
		
		Integer min = 0;
		String minInfrac = "";
		Integer max = 0;
		String maxInfrac = "";
		String minNuFolioInfraccion = "";
		String maxNuFolioInfraccion = "";
		if (!listaDetecciones.isEmpty()) {
			String velocidadDetectada;
			Long loteId = idLote;
			
			for (FMDeteccionesVO fmDeteccionesVO : listaDetecciones) {
				FMDetalleVO fmDetalleVO = new FMDetalleVO();
				//String nombreEntidad = fmLotesMyBatisDAO.getNombreEntidad(fmDetecctionesVO.getNbEntidad());
				//String nombreDelegacion = fmLotesMyBatisDAO.getNombreDelegacion(fmDetecctionesVO.getNbEntidad(), fmDetecctionesVO.getNbDelegacion());
				
				//Long idDetalle = fmLotesMyBatisDAO.obtenerSiguienteDetalle();
				String[] CdFechahora = fmDeteccionesVO.getCdFechahora().split(" ");
				String[] CdFechaArray = CdFechahora[0].split("/");
				String CdFecha = CdFechaArray[0]+"."+CdFechaArray[1]+"."+CdFechaArray[2].substring(2, CdFechaArray[2].length());
				velocidadDetectada = fmDeteccionesVO.getNuVelocidaddetectada() == null ? "0" : fmDeteccionesVO.getNuVelocidaddetectada();
				
				//fmDetalleVO.setId(idDetalle);
				fmDetalleVO.setRadarArchivoId(idLote);
				fmDetalleVO.setPlaca(fmDeteccionesVO.getCdPlaca());
				fmDetalleVO.setFecha(CdFecha);
				fmDetalleVO.setHora(CdFechahora[1]);
				fmDetalleVO.setTdskuid(fmDeteccionesVO.getCdTdskuid());
				fmDetalleVO.setUt(fmDeteccionesVO.getCdUt());
				fmDetalleVO.setVelocidadDetectada(velocidadDetectada);
				fmDetalleVO.setInfracNum(fmDeteccionesVO.getNuFolioInfraccion());
				//fecha de creacio
				if(origenPlaca == 0){
					fmDetalleVO.setNombre(fmDeteccionesVO.getNbNombre());
					fmDetalleVO.setApellidoPaterno(fmDeteccionesVO.getNbApellidopat());
					fmDetalleVO.setApellidoMaterno(fmDeteccionesVO.getNbApellidomat());
					fmDetalleVO.setCalle(fmDeteccionesVO.getNbCalle());
					fmDetalleVO.setNumExterior(fmDeteccionesVO.getNuNumext());
					fmDetalleVO.setNumInterior(fmDeteccionesVO.getNuNumint());
					fmDetalleVO.setColonia(fmDeteccionesVO.getNbColonia());
					fmDetalleVO.setMunicipio(fmDeteccionesVO.getNombreDelegacion());
					fmDetalleVO.setCodigoPostal(fmDeteccionesVO.getNuCp());
					fmDetalleVO.setEntidadFederativa(fmDeteccionesVO.getNombreEntidad());
					fmDetalleVO.setMarca(fmDeteccionesVO.getNbMarca());
					fmDetalleVO.setSubmarca(fmDeteccionesVO.getNbSubmar());
					fmDetalleVO.setModelo(fmDeteccionesVO.getNbModelo());
					fmDetalleVO.setTelefono(fmDeteccionesVO.getCdTelefono());
					fmDetalleVO.setSerie(fmDeteccionesVO.getNuNumserie());
					fmDetalleVO.setMotor(fmDeteccionesVO.getNuNummotor());
					fmDetalleVO.setTxCorreoElect(fmDeteccionesVO.getTxCorreoElect());
					fmDetalleVO.setNuLicencia(fmDeteccionesVO.getNuLicencia());
				}
				
				fmDetalleVO.setArtId(Integer.parseInt(fmDeteccionesVO.getIdArtid()));
				fmDetalleVO.setOficialPlaca(fmDeteccionesVO.getCdOficialplaca());
				fmDetalleVO.setUtModificadoPor(fmDeteccionesVO.getCdModificadopor());
				fmDetalleVO.setNuPuntosDescnt(fmDeteccionesVO.getNuPuntosDescnt());
				fmDetalleVO.setIdArchivoFC(fmDeteccionesVO.getIdArchivoFC());
				fmDetalleVO.setIdFcDeteccion(fmDeteccionesVO.getIdFcDeteccion());
				
				fmDetalleVO.setUtCalle(fmDeteccionesVO.getNbUTCalle());
				fmDetalleVO.setUtEntreCalle(fmDeteccionesVO.getNbUTEntreCalle());
				fmDetalleVO.setUtYCalle(fmDeteccionesVO.getNbUTYCalle());
				fmDetalleVO.setUtColonia(fmDeteccionesVO.getNbUTColonia());
				fmDetalleVO.setUtDelegacionId(fmDeteccionesVO.getIdUTDelegacion());
				fmDetalleVO.setUtDelegacion(fmDeteccionesVO.getNbUTDelegacion());
				fmDetalleVO.setUtEdoId(fmDeteccionesVO.getIdUTEntidad());
				fmDetalleVO.setUtSector(fmDeteccionesVO.getNbUTSec());
				fmDetalleVO.setUtSectorId(fmDeteccionesVO.getIdUTSec());
				fmDetalleVO.setDias(fmDeteccionesVO.getNuUmas());
				fmDetalleVO.setCdExtSerieCaptura(fmDeteccionesVO.getCdExtSerieCaptura());
				//fmDetalleVO.setMinNuFolioInfraccion(fmDeteccionesVO.getMinNuFolioInfraccion());
				//fmDetalleVO.setMaxNuFolioInfraccion(fmDeteccionesVO.getMaxNuFolioInfraccion());
//				if(minNuFolioInfraccion == "" && maxNuFolioInfraccion == ""){
//					minNuFolioInfraccion = fmDeteccionesVO.getMinNuFolioInfraccion();
//					maxNuFolioInfraccion = fmDeteccionesVO.getMaxNuFolioInfraccion();
//				}
				
				fmLotesMyBatisDAO.insertarRADDetalle(fmDetalleVO);
				//fmLotesMyBatisDAO.insertarFMDetalle(fmDetalleVO);
				cantidadProcesados++;
//				for(int x=0;x<tipoDeteccionVO.size();x++) {
//					fmLotesMyBatisDAO.actualizaFotomultaDeteccionesPorLote
//					(fmDeteccionesVO.getCdTdskuid(),procesado, idLote, noProcesado, tipoDeteccionVO.get(x).getIdTipoDeteccion());
//				}
				
//				fmLotesMyBatisDAO.actualizaFotomultaDeteccionesPorLote(
//						fmDeteccionesVO.getCdTdskuid(),procesado, idLote, noProcesado, 
//						Long.parseLong(fmDeteccionesVO.getIdTipoDeteccion().toString()));
//				
//				fmLotesMyBatisDAO.actualizaFotoCivicasDeteccionesPorLote(
//						procesado, fmDeteccionesVO.getIdFcDeteccion());
				
//				if(min > Integer.parseInt(fmDetecctionesVO.getNuFolioInfraccion())){
//					min = Integer.parseInt(fmDetecctionesVO.getNuFolioInfraccion());
//					minInfrac = fmDetecctionesVO.getNuFolioInfraccion();
//				}
//				if(max < Integer.parseInt(fmDetecctionesVO.getNuFolioInfraccion())){
//					max = Integer.parseInt(fmDetecctionesVO.getNuFolioInfraccion());
//					maxInfrac = fmDetecctionesVO.getNuFolioInfraccion();
//				}
			}
//			fmLotesMyBatisDAO.actualizaLoteCreacion(idLote, minNuFolioInfraccion, maxNuFolioInfraccion);
			
			Date fechaE = rutina.convertirStringDate(fechaEmision, "dd/MM/yy");
			
			fmConsultaLotesVO = new FMLotesVO();
			fmConsultaLotesVO.setCantidadProcesados(cantidadProcesados);
			fmConsultaLotesVO.setComplementado(false);
			fmConsultaLotesVO.setFechaEmision(fechaE);
			fmConsultaLotesVO.setId(idLote);
			fmConsultaLotesVO.setNombre(nombreLote);
			
			response.put("guardar", 1);
			response.put("FMLotesVO", fmConsultaLotesVO);
		} else {
			response.put("guardar", 0);
		}
		return response;
	}
	
	@Override
	public Integer realizarCompletacion(String mensajeErr, Long idLote, Long usuario) 
		throws Exception, BusinessException, NotFoundException{
		final Integer enProceso = 1;
		try {
			Integer ArchivoEnompletacion = fmLotesMyBatisDAO.buscarArchivoEnComplementacion(enProceso);
			
			if (ArchivoEnompletacion == 0) {
				//Integer res = fmLotesMyBatisDAO.actualizaFMLoteParaComplementacion(idLote, enProceso);
				Integer res = fmLotesMyBatisDAO.actualizaRadarArchivoParaComplementacion(idLote, enProceso);
				//fmLotesMyBatisDAO.actualizaRadarArchivoTotalParaComplementacion(idLote, enProceso);
				//fmLotesMyBatisDAO.insertarBitProceso(idLote, usuario);
				return res;
			}else{
				mensajeErr = "Existe un lote en proceso de complementacion. Espere a que el proceso termine.";
				throw new NotFoundException("");
			}
		} catch (Exception e) {
			if(mensajeErr != null && !mensajeErr.isEmpty() && !mensajeErr.equals(null)) {
				throw new NotFoundException(mensajeErr);
			} else {
				e.printStackTrace();
				throw new NotFoundException("¡Ha ocurrido un imprevisto! , porfavor contacte al administrador");
			}
		}
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public Map getReporteDeteccionesPorLote(String fechaInicio, String fechaFin, 
			Integer tipoDeteccion,Integer tipoPersona, String nombrePersona, 
			String nombreDeteccion, Integer origenPlacaF) {
		reporte = this.crearReporteDeteccionesPorLote(fechaInicio, fechaFin, tipoDeteccion, tipoPersona, 
					nombrePersona, nombreDeteccion, origenPlacaF);
		return reporte;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Map crearReporteDeteccionesPorLote(String fechaInicio, String fechaFin, Integer tipoDeteccion, 
											Integer tipoPersona, String nombrePersona, String nombreDeteccion, 
											Integer origenPlaca) {

		Map response = new HashMap();
		ByteArrayOutputStream reporte = new ByteArrayOutputStream();

		PeticioReporteVO peticionReporteVO = new PeticioReporteVO();
		PropiedadesReporte propiedadesReporte = new PropiedadesReporte();
		PeticionReporteBOImpl peticionReporteBOImpl = new PeticionReporteBOImpl();
		rutina = new RutinasTiempoImpl();
		
		Integer valido = 1;
		Integer autorizado = 1;
		Integer procesado = 0;
		
		//Resultados de la tabla
		List<Object> contenido = new ArrayList<Object>();
		List<Object> contenido1 = new ArrayList<Object>();
		
		//Leyendas de las columnas de las tablas
		List<Object> encabezadoTitulo = new ArrayList<Object>();
		List<String> titulos = new ArrayList<String>();
		List<String> subtitulos = new ArrayList<String>();
		
		//titulos.add("Folio Infraccion");
		titulos.add("Placa");
		titulos.add("Fecha");
		titulos.add("Hora");
		titulos.add("TDSKUID");
		titulos.add("UT");
		titulos.add("Velocidad Detectada");
		titulos.add("Nombre");
		titulos.add("Apellido Paterno");
		titulos.add("Apellido Materno");
		titulos.add("Calle");
		titulos.add("Num. Exterior");
		titulos.add("Num. Interior");
		titulos.add("Colonia");
		titulos.add("Municipio");
		titulos.add("Codigo Postal");
		titulos.add("Entidad Federativa");
		titulos.add("Marca");
		titulos.add("Submarca");
		titulos.add("Modelo");
		titulos.add("Telefono");
		titulos.add("Serie");
		titulos.add("Motor");
		titulos.add("Oficial Placa");
		//titulos.add("Oficial Nombre");
		encabezadoTitulo.add(titulos);
		
		String origenPlacaString = "Foránea";
		if(origenPlaca==0){origenPlacaString="CDMX";};
		
		subtitulos.add("Tipo de Deteccion: " + nombreDeteccion);
		subtitulos.add("Tipo de Archivo: " + nombrePersona);
		subtitulos.add("Origen de Placa: " + origenPlacaString);
		
		propiedadesReporte.setFechaI(fechaInicio);
		propiedadesReporte.setFechaF(fechaFin);
		String fechaReporte = fechaInicio + "-" + fechaFin;
		String fechaNReporte = fechaInicio.replace("/", "") + fechaFin.replace("/", "");
		
		propiedadesReporte.setNombreReporte("DETECCIONES"+fechaNReporte);
		propiedadesReporte.setTituloExcel("DETECCIONES " + fechaReporte);
		propiedadesReporte.setExtencionArchvio(".xls");
		
		//cuerpo del reporte
		List<String> listaContenido1;
		List<FMDeteccionesVO> listresult = new ArrayList<FMDeteccionesVO>();
		Date fechaI = rutina.convertirStringDate(fechaInicio, "dd/MM/yyyy");
		Date fechaF = rutina.convertirStringDate(fechaFin, "dd/MM/yyyy");
//		for(int i=0;i<tipoDeteccionVO.size();i++) {
//			List<FMDeteccionesVO> listresultemp = null;
//			listresultemp = fmLotesMyBatisDAO.buscaFotomultasDetecionesRangoFechasByFechaInfraccion(fechaI, fechaF, procesado, Long.valueOf(tipoDeteccionVO.get(i).getIdTipoDeteccion()), origenPlaca);
//			listresult.addAll(listresultemp);
//		}
		List<FMDeteccionesVO> listresultemp = null;
		listresultemp = fmLotesMyBatisDAO.buscaFotomultasDetecionesRangoFechasByFechaInfraccion(fechaI, fechaF, procesado, tipoDeteccion, tipoPersona, origenPlaca);
		listresult.addAll(listresultemp);
		
		if(!listresult.isEmpty()){	
			for(FMDeteccionesVO deteccionVO : listresult){
				listaContenido1 = new ArrayList<String>();
				
				//Long idDetalle = fmLotesMyBatisDAO.obtenerSiguienteDetalle();
				String[] CdFechahora = deteccionVO.getCdFechahora().split(" ");
				String[] CdFechaArray = CdFechahora[0].split("/");
				String CdFecha = CdFechaArray[0]+"/"+CdFechaArray[1]+"/"+CdFechaArray[2];
				//String CdFecha = CdFechaArray[0]+"/"+CdFechaArray[1]+"/"+CdFechaArray[2].substring(2, CdFechaArray[2].length());
				String velocidadDetectada = deteccionVO.getNuVelocidaddetectada() == null ? "0" : deteccionVO.getNuVelocidaddetectada();
				
				//listaContenido1.add(deteccionVO.getNuFolioInfraccion());
				listaContenido1.add(deteccionVO.getCdPlaca());
				listaContenido1.add(CdFecha);
				listaContenido1.add(CdFechahora[1]);
				listaContenido1.add(deteccionVO.getCdTdskuid());
				listaContenido1.add(deteccionVO.getCdUt());
				listaContenido1.add(velocidadDetectada);
				listaContenido1.add(deteccionVO.getNbNombre());
				listaContenido1.add(deteccionVO.getNbApellidopat());
				listaContenido1.add(deteccionVO.getNbApellidomat());
				listaContenido1.add(deteccionVO.getNbCalle());
				listaContenido1.add(deteccionVO.getNuNumext());
				listaContenido1.add(deteccionVO.getNuNumint());
				listaContenido1.add(deteccionVO.getNbColonia());
				listaContenido1.add(deteccionVO.getNombreDelegacion());
				listaContenido1.add(deteccionVO.getNuCp());
				listaContenido1.add(deteccionVO.getNombreEntidad());
				listaContenido1.add(deteccionVO.getNbMarca());
				listaContenido1.add(deteccionVO.getNbSubmar());
				listaContenido1.add(deteccionVO.getNbModelo());
				listaContenido1.add(deteccionVO.getCdTelefono());
				listaContenido1.add(deteccionVO.getNuNumserie());
				listaContenido1.add(deteccionVO.getNuNummotor());
				listaContenido1.add(deteccionVO.getCdOficialplaca());
				//listaContenido1.add(deteccionVO.getOficialNombre());
				
				contenido1.add(listaContenido1);
			}
					
			contenido.add(contenido1);
			
			propiedadesReporte.setSubtitulos(subtitulos);
			peticionReporteVO.setPropiedadesReporte(propiedadesReporte);
			peticionReporteVO.setEncabezado(encabezadoTitulo);
			peticionReporteVO.setContenido(contenido);
		
			try {
				reporte = peticionReporteBOImpl.generaReporteExcel(peticionReporteVO);
				response.put("reporte", reporte);
				response.put("nombre", propiedadesReporte.getNombreReporte());
			} catch (IOException e) {
	 			e.printStackTrace();
			}
		}
		
		return response;
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public List<FMLotesVO> consultaLotesCreados(){
		List<FMLotesVO> consultaLotesVO = null;
		consultaLotesVO = fmLotesMyBatisDAO.buscarLotesCreados();
		return consultaLotesVO;
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public List<FMDeteccionesDisponiblesFCVO> consultaDetDisponibles(){
		List<FMDeteccionesDisponiblesFCVO> listaFMDeteccionesDisponiblesFCVO = new ArrayList<FMDeteccionesDisponiblesFCVO>();
		List<FMTipoArchivoFCVO> listaFMTipoArchivoFCVO = fmLotesMyBatisDAO.buscarArchivoFC();
		List<FMTipoFotocivicaVO> listaFMTipoFotocivicaVO = fmDeteccionesDAO.buscarTipoDeteccionFc(0);
		DecimalFormatSymbols simbolo = new DecimalFormatSymbols();
		simbolo.setDecimalSeparator('.');
		simbolo.setGroupingSeparator(',');
		DecimalFormat formateador = new DecimalFormat("###,###,###.##", simbolo); 
		
		if(!listaFMTipoArchivoFCVO.isEmpty()){
			for(FMTipoArchivoFCVO fMTipoArchivoFCVO : listaFMTipoArchivoFCVO){
				Integer totalDet = 0;
				Integer cantInfrac03 = 0;
				Integer cantInfrac08 = 0;
				String meses03 = "";
				String meses08 = "";
				
				for(FMTipoFotocivicaVO fMTipoFotocivicaVO : listaFMTipoFotocivicaVO){
					Integer totalDetXTFC = 0;
					String mesesDetXTFC = "";
					Integer contadorTipoFC = 0;
					List<FMDeteccionesDisponiblesFCVO> listaDeteccionesDisponiblesPorTipoFC = fmLotesMyBatisDAO.buscarDetDisponibles(
						fMTipoArchivoFCVO.getIdTipoArchivoFCivica(), fMTipoFotocivicaVO.getIdTipoFotocivica());
					for(FMDeteccionesDisponiblesFCVO fMDeteccionesDisponiblesFCVO : listaDeteccionesDisponiblesPorTipoFC){
						totalDetXTFC += fMDeteccionesDisponiblesFCVO.getCantInfrac();
						mesesDetXTFC +="<tr>"
										+ "<td class='td1DetDisp'>" + formateador.format(fMDeteccionesDisponiblesFCVO.getCantInfrac()) + "</td>"
										+ "<td class='td2DetDisp'>" + fMDeteccionesDisponiblesFCVO.getMes() + "</td>"
										+ "<td class='td3DetDisp'>" + fMDeteccionesDisponiblesFCVO.getNuAnio() + "</td>"
									+ "</tr>";
						contadorTipoFC++;
					}
					
					if(fMTipoFotocivicaVO.getCdTipoFolioInfraccion().equals("03") ) {
						cantInfrac03 = totalDetXTFC;
						meses03 = mesesDetXTFC;
					}if(fMTipoFotocivicaVO.getCdTipoFolioInfraccion().equals("08") ) {
						cantInfrac08 = totalDetXTFC;
						meses08 = mesesDetXTFC;
					}
				}
				
				FMDeteccionesDisponiblesFCVO fMDeteccionesDisponiblesFCVO = new FMDeteccionesDisponiblesFCVO();
				
				fMDeteccionesDisponiblesFCVO.setNbTipoArchivoFCivica(fMTipoArchivoFCVO.getNbTipoArchivoFCivica());
				fMDeteccionesDisponiblesFCVO.setCantInfrac03(cantInfrac03);
				fMDeteccionesDisponiblesFCVO.setMeses03(meses03);
				fMDeteccionesDisponiblesFCVO.setCantInfrac08(cantInfrac08);
				fMDeteccionesDisponiblesFCVO.setMeses08(meses08);
				totalDet = cantInfrac03 + cantInfrac08;
				fMDeteccionesDisponiblesFCVO.setInfracTotal(totalDet);
				
				listaFMDeteccionesDisponiblesFCVO.add(fMDeteccionesDisponiblesFCVO);
			}
		}
		
		return listaFMDeteccionesDisponiblesFCVO;
	}
	
	public void actualizaArchivosOrigen(Long idLote) {
		fmLotesMyBatisDAO.actualizarCantProcs(idLote);
		fmLotesMyBatisDAO.actualizarStComp(idLote);
	}
	
	public void complementaOficialNombre(Long idLote){
		fmLotesMyBatisDAO.complementaOficialNombre(idLote);
	}
	
	public void validaFormatoPlaca(Long idLote, Map<String, String> parametros){
		String queryConsultaPlacasLote = parametros.get("CONS_PLACAS_RA");
		String queryConsultaPlacasLB = parametros.get("CONS_PLACAS_TAI006");
		String queryUpdateFormatoPlacas = parametros.get("UPD_RAD_FORMATO_PLACAS");
		
		queryConsultaPlacasLote = StringUtils.replace(queryConsultaPlacasLote, "#{idLote}", idLote.toString());
		
		List<FMDetalleVO> placasLote = fmLotesMyBatisDAO.consPlacasLote(queryConsultaPlacasLote);
		List<String> placasLB = fmLotesMyBatisDAO.consPlacasLB(queryConsultaPlacasLB);
		List<FMDetalleVO> deteccionesADesactivar = new ArrayList();
		
		for(FMDetalleVO registro : placasLote) {
			if (placasLB.contains(registro.getPlaca())){
				deteccionesADesactivar.add(registro);
			}
		}
		placasLote = null;
		placasLB = null;
		
		for(FMDetalleVO detecADesactivar : deteccionesADesactivar) {
			String updateArmado = queryUpdateFormatoPlacas; 
			updateArmado = StringUtils.replace(updateArmado, "#{idLote}", idLote.toString());
			updateArmado = StringUtils.replace(updateArmado, "#{id}", detecADesactivar.getId().toString());
			fmLotesMyBatisDAO.UpdateFormatoPlacas(updateArmado);
		}
	}
	
	public String consultaNombreSugLote(String fechaInicio, String fechaFin, Integer tipoPersona, 
									Integer tipoDeteccion){
		Map<String, String> parametros = getParametrosLP(); 
		String layotuNombreLote = parametros.get("LAYOUT_NOMBRE_LOTE");
		String consLayotuNombreMes = parametros.get("CONS_LAYOUT_NOMBRE_MES");
		String consLayotuNombreAnio = parametros.get("CONS_LAYOUT_NOMBRE_ANIO");
		String consLayotuNombreTipoArchFC = parametros.get("CONS_LAYOUT_NOMBRE_TIPO_ARCH_FC");
		String consLayotuNombreConsecutivo = parametros.get("CONS_LAYOUT_NOMBRE_CONSECUTIVO");
		String consLayotuNombreTipoFC = parametros.get("CONS_LAYOUT_NOMBRE_TIPO_FC");
		
		//  LAYOUT_NOMBRE_LOTE
		//  #{MES}_#{CONS}_#{ANIO}_#{CD_TIPO_ARCH_FC}_#{CD_TIPO_FC}
		
		consLayotuNombreMes = StringUtils.replace(consLayotuNombreMes, "#{fechaInicio}", "'"+fechaInicio+"'");
		String nombreMes = fmLotesMyBatisDAO.consLayotuNombreMes(consLayotuNombreMes);
		
		consLayotuNombreAnio = StringUtils.replace(consLayotuNombreAnio, "#{fechaInicio}", "'"+fechaInicio+"'");
		String nombreAnio = fmLotesMyBatisDAO.consLayotuNombreAnio(consLayotuNombreAnio);
		
		consLayotuNombreTipoArchFC = StringUtils.replace(consLayotuNombreTipoArchFC, "#{tipoPersona}", tipoPersona.toString());
		String nombreTipoArchFC = fmLotesMyBatisDAO.consLayotuNombreTipoArchFC(consLayotuNombreTipoArchFC);
		
		consLayotuNombreTipoFC = StringUtils.replace(consLayotuNombreTipoFC, "#{tipoDeteccion}", tipoDeteccion.toString());
		String nombreTipoFC = fmLotesMyBatisDAO.consLayotuNombreTipoFC(consLayotuNombreTipoFC);
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/yyyy");
		RutinasTiempoImpl objetotiempo = new RutinasTiempoImpl();
		Date datef = objetotiempo.convertirStringDate(fechaInicio, "dd/MM/yyyy");
		String fConsultConsec = simpleDateFormat.format(datef);
		
		//
		//SELECT MAX(TRIM(REPLACE(REPLACE(REPLACE(RA.NOMBRE, #{MES}, ''), #{ANIO}, ''), #{CD_TIPO_ARCH_FC}, ''))) AS CONSECUTIVO FROM RADAR_ARCHIVO RA WHERE RA.NOMBRE LIKE '% #{CD_TIPO_ARCH_FC}' AND RA.NOMBRE LIKE '%#{MES}%' AND RA.NOMBRE LIKE '%#{ANIO}%'
		//
		
		//consLayotuNombreConsecutivo = StringUtils.replace(consLayotuNombreConsecutivo, "#{MES}", nombreMes);
		//consLayotuNombreConsecutivo = StringUtils.replace(consLayotuNombreConsecutivo, "#{ANIO}", nombreAnio);
		//consLayotuNombreConsecutivo = StringUtils.replace(consLayotuNombreConsecutivo, "#{CD_TIPO_ARCH_FC}", nombreTipoArchFC);
		//consLayotuNombreConsecutivo = StringUtils.replace(consLayotuNombreConsecutivo, "#{CD_TIPO_FC}", nombreTipoFC);
		//String nombreConsecutivo = fmLotesMyBatisDAO.consLayotuNombreTipoFC(consLayotuNombreConsecutivo);
		
		consLayotuNombreConsecutivo = StringUtils.replace(consLayotuNombreConsecutivo, "#{tipoPersona}", tipoPersona.toString());
		consLayotuNombreConsecutivo = StringUtils.replace(consLayotuNombreConsecutivo, "#{tipoDeteccion}", tipoDeteccion.toString());
		consLayotuNombreConsecutivo = StringUtils.replace(consLayotuNombreConsecutivo, "#{fConsultConsec}", fConsultConsec);
		String nombreConsecutivo = fmLotesMyBatisDAO.consLayotuNombreTipoFC(consLayotuNombreConsecutivo);
		
		layotuNombreLote = StringUtils.replace(layotuNombreLote, "#{MES}", nombreMes);
		layotuNombreLote = StringUtils.replace(layotuNombreLote, "#{CONS}", nombreConsecutivo);
		layotuNombreLote = StringUtils.replace(layotuNombreLote, "#{ANIO}", nombreAnio);
		layotuNombreLote = StringUtils.replace(layotuNombreLote, "#{CD_TIPO_ARCH_FC}", nombreTipoArchFC);
		layotuNombreLote = StringUtils.replace(layotuNombreLote, "#{CD_TIPO_FC}", nombreTipoFC);
		
		return layotuNombreLote;
	};

	@Transactional (readOnly =  true)
	@Override
	public Map<String, String> getParametrosLP() {
		List<Map<String, String>> listaParametros = fmLotesMyBatisDAO.buscarQuerys();
		Map<String, String> parametros = new HashMap<String, String>();
		for(Map<String, String> registro : listaParametros) {
			parametros.put(registro.get("CD_LLAVE_P_CONFIG"), registro.get("CD_VALOR_P_CONFIG"));
		}
		return parametros;
	}

	@Transactional (readOnly =  true)
	@Override
	public Map<String, String> getParametrosLPPorNBConfig(String nbConfig) {
		List<Map<String, String>> listaParametros  = fmLotesMyBatisDAO.buscarParametrosPorNbConfig(nbConfig);
		Map<String, String> parametros = new HashMap<String, String>();
		for(Map<String, String> registro : listaParametros) {
			parametros.put(registro.get("CD_LLAVE_P_CONFIG"), registro.get("CD_VALOR_P_CONFIG"));
		}
		return parametros;
	}
	
	@Override
	@Transactional (readOnly =  true)
	public FMLotesDTO consultaLoteEnProceso() {
		return fmLotesDAO.buscarLotesEnCreacion();
	}
}
