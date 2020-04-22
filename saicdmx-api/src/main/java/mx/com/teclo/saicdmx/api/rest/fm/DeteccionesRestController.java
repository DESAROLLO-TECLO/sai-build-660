package mx.com.teclo.saicdmx.api.rest.fm;



import java.io.ByteArrayOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParser;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import mx.com.teclo.saicdmx.negocio.service.comun.UsuarioFirmadoService;
import mx.com.teclo.saicdmx.negocio.service.fm.ConsultaDeteccionesServiceFM;
import mx.com.teclo.saicdmx.negocio.service.fm.DeteccionesService;
import mx.com.teclo.saicdmx.negocio.service.fm.FMReporteDetalleDetecciones;
import mx.com.teclo.saicdmx.negocio.service.fm.ICancelacionDeteccionesFMService;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.empleados.EmpleadosDTO;
import mx.com.teclo.saicdmx.persistencia.vo.fm.DeteccionBatchFinanzasVO;
import mx.com.teclo.saicdmx.persistencia.vo.fm.FCConsultaDeteccionesSinProcVO;
import mx.com.teclo.saicdmx.persistencia.vo.fm.FCConsultaDeteccionesVO;
import mx.com.teclo.saicdmx.persistencia.vo.fm.FMBitProcesoVO;
import mx.com.teclo.saicdmx.persistencia.vo.fm.FMConsultaDetalleDeteccionesVO;
import mx.com.teclo.saicdmx.persistencia.vo.fm.FMConsultaDeteccionesAgrupacionMes;
import mx.com.teclo.saicdmx.persistencia.vo.fm.FMConsultaDeteccionesDetalleHistoricoVO;
import mx.com.teclo.saicdmx.persistencia.vo.fm.FMConsultaDeteccionesSPVO;
import mx.com.teclo.saicdmx.persistencia.vo.fm.FMConsultaDeteccionesVO;
import mx.com.teclo.saicdmx.persistencia.vo.fm.FMDeteccionCP;
import mx.com.teclo.saicdmx.persistencia.vo.fm.FMDeteccionCPV2;
import mx.com.teclo.saicdmx.persistencia.vo.fm.FMDeteccionesFechasVO;
import mx.com.teclo.saicdmx.persistencia.vo.fm.FMLoteProcesoVO;
import mx.com.teclo.saicdmx.persistencia.vo.fm.FMLoteResumenVO;
import mx.com.teclo.saicdmx.persistencia.vo.infracciones.AltaInfraccionSPVO;
import mx.com.teclo.saicdmx.persistencia.vo.infracciones.AltaInfraccionVO;
import mx.com.teclo.saicdmx.util.enumerados.ArchivoBatchEstatusEnum;
import mx.com.teclomexicana.arquitectura.ortogonales.exception.BusinessException;
import mx.com.teclomexicana.arquitectura.ortogonales.exception.NotFoundException;
import mx.com.teclomexicana.arquitectura.ortogonales.seguridad.vo.UsuarioFirmadoVO;



@RestController
public class DeteccionesRestController {
	
	@Autowired
	private ConsultaDeteccionesServiceFM ServiceFM;
	
	@Autowired
	private DeteccionesService deteccionesService;
	
	@Autowired
	private UsuarioFirmadoService usuarioFirmadoService;
	
	@Autowired
	private ICancelacionDeteccionesFMService CancelacionDetecciones ;
	

	/**
	 * @author Sail
	 * @return Map<String, Object>
	 * @throws NotFoundException
	 */
	
	@RequestMapping(value = "/buscaLoteEnCurso", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('FM_LOTE_EN_CURSO')")
	public ResponseEntity<Map<String, Object>> buscaLoteEnProceso() throws NotFoundException{
		
		Map<String, Object> response = new HashMap<String, Object>();
		List<FMBitProcesoVO> bitProcesos;
		
		//Se busca sobre la antigua tabla para validar que no haya un proceso
		//FMLoteProcesoVO oldLoteProceso = deteccionesService.buscarAntiguoLoteEnProceso();
		
		//if(oldLoteProceso == null){
			FMLoteProcesoVO loteEnProceso = deteccionesService.buscarLoteEnProceso();
			
			response = new HashMap<String, Object>();
			if(loteEnProceso != null){
				
				//Buscar bitacora de estatus de proceso
				bitProcesos = deteccionesService.buscarBitacoraLoteEnProceso(loteEnProceso.getRadarArchivoId());
				
				response.put("dispFijoLoteVO", loteEnProceso);
				response.put("listaEstatusVO", bitProcesos);
				response.put("antiguoLoteEnProceso", Boolean.FALSE);
			}
		/*}else{
			response.put("antiguoLoteEnProceso", Boolean.TRUE);
		}*/
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	
	/**
	 * @author Sail
	 * @param lote
	 * @param motivo
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping(value = "/cancelacionFmLote", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('FM_CANCELA_LOTE')")
	public ResponseEntity<FMLoteResumenVO> cancelaFmLote(@RequestParam(value = "lote") Long lote,
													     @RequestParam(value = "motivo") String motivo) throws BusinessException{
		Long usuario = usuarioFirmadoService.getUsuarioFirmadoVO().getId();
		
		DeteccionBatchFinanzasVO dbfVO = deteccionesService.cargarLote(lote);
		dbfVO.setModificadoPorId(usuario);
		
		deteccionesService.cancelarArchivo(dbfVO, motivo);
		
		FMLoteResumenVO archivoCancelado = deteccionesService.buscarLoteCancelado(dbfVO.getArchivoId());
		return new ResponseEntity<FMLoteResumenVO>(archivoCancelado, HttpStatus.OK);
	}
	

	/**
	 * @author Sail
	 * @param lote
	 * @return List<FMDeteccionCP>
	 * @throws BusinessException
	 */
	@RequestMapping(value = "/buscarDeteccionesFmValidas", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('FM_LOTE_CP_VALIDAS')")
	public ResponseEntity<List<FMDeteccionCPV2>> buscarDeteccionesFmValidas(@RequestParam(value = "lote") Long lote) throws BusinessException{
	//public ResponseEntity<List<FMDeteccionCP>> buscarDeteccionesFmValidas(@RequestParam(value = "lote") Long lote) throws BusinessException{
			
		List<FMDeteccionCPV2> deteccionesValidas = deteccionesService.buscarDeteccionesFmValidas(lote);
		//List<FMDeteccionCP> deteccionesValidas = deteccionesService.buscarDeteccionesFmValidas(lote);
		//return new ResponseEntity<List<FMDeteccionCP>>(deteccionesValidas, HttpStatus.OK);
		return new ResponseEntity<List<FMDeteccionCPV2>>(deteccionesValidas, HttpStatus.OK);
	}
	
	

	
	/**
	 * @author Sail
	 * @param lote
	 * @return List<FMDeteccionCP>
	 * @throws BusinessException
	 */
	@RequestMapping(value = "/buscarDeteccionesFmInvalidas", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('FM_LOTE_CP_INVALIDAS')")
	public ResponseEntity<List<FMDeteccionCPV2>> buscarDeteccionesFmInvalidas(@RequestParam(value = "lote") Long lote) throws BusinessException{
	//public ResponseEntity<List<FMDeteccionCP>> buscarDeteccionesFmInvalidas(@RequestParam(value = "lote") Long lote) throws BusinessException{
			
		List<FMDeteccionCPV2> deteccionesInvalidas = deteccionesService.buscarDeteccionesFmInvalidas(lote);
		//List<FMDeteccionCP> deteccionesInvalidas = deteccionesService.buscarDeteccionesFmInvalidas(lote);
		
		return new ResponseEntity<List<FMDeteccionCPV2>>(deteccionesInvalidas, HttpStatus.OK);
		//return new ResponseEntity<List<FMDeteccionCP>>(deteccionesInvalidas, HttpStatus.OK);
	}
	
	/**
	 * @author Gibran
	 * @param lote
	 * @return List<FMDeteccionCP>
	 * @throws BusinessException
	 */ 
	@RequestMapping(value = "/buscarAllDeteccionesFm", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('FM_LOTE_CP_VALIDAS')")
	public ResponseEntity<List<FMDeteccionCPV2>> buscarAllDeteccionesFm(@RequestParam(value = "lote") Long lote) throws BusinessException{
		List<FMDeteccionCPV2> deteccionesValidas = deteccionesService.buscarDeteccionesFmValidas(lote);	
		for(int i=0;i<deteccionesValidas.size();i++){
			
			String cp=deteccionesValidas.get(i).getCodigoPostal();
			System.out.println(cp);
			List<FMDeteccionCP> deteccionesValidasByCP = deteccionesService.buscarAllDeteccionesFmByCP(cp,lote);
			deteccionesValidas.get(i).setDetecciones(deteccionesValidasByCP);
		}
//		List<FMDeteccionCP> allDeteccionesValidas = deteccionesService.buscarAllDeteccionesFm(lote);
		//List<FMDeteccionCP> deteccionesValidas = deteccionesService.buscarDeteccionesFmValidas(lote);
		//return new ResponseEntity<List<FMDeteccionCP>>(deteccionesValidas, HttpStatus.OK);
		return new ResponseEntity<List<FMDeteccionCPV2>>(deteccionesValidas, HttpStatus.OK);
	}
	
	/**
	 * @author Gibran
	 * @param lote
	 * @return List<FMDeteccionCP>
	 * @throws BusinessException
	 */ 
	@RequestMapping(value = "/buscarAllDeteccionesRAD", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('FM_LOTE_CP_VALIDAS')")
	public ResponseEntity<List<FMDeteccionCPV2>> buscarAllDeteccionesRAD(@RequestParam(value = "lote") Long lote) throws BusinessException{
		List<FMDeteccionCPV2> deteccionesValidas = deteccionesService.buscarDeteccionesRADValidas(lote);	
//		int total=0;
		for(int i=0;i<deteccionesValidas.size();i++){
			
			String cp=deteccionesValidas.get(i).getCodigoPostal();
//			System.out.println(cp);
			List<FMDeteccionCP> deteccionesValidasByCP = deteccionesService.buscarAllDeteccionesRADByCP(cp,lote);
//			total=total+deteccionesValidasByCP.size();
//			System.out.println("Total: "+total);
			deteccionesValidas.get(i).setDetecciones(deteccionesValidasByCP);
		}
//		List<FMDeteccionCP> allDeteccionesValidas = deteccionesService.buscarAllDeteccionesFm(lote);
		//List<FMDeteccionCP> deteccionesValidas = deteccionesService.buscarDeteccionesFmValidas(lote);
		//return new ResponseEntity<List<FMDeteccionCP>>(deteccionesValidas, HttpStatus.OK);
		return new ResponseEntity<List<FMDeteccionCPV2>>(deteccionesValidas, HttpStatus.OK);
	}
	
	/**
	 * @author Sail
	 * @param lote
	 * @return Boolean
	 * @throws BusinessException
	 */
	@RequestMapping(value = "/actualizaLoteCP", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('FM_CAMBIA_CP_DETECCION')")
	public ResponseEntity<Boolean> actualizarFmLoteReasignadoCP(@RequestParam(value = "lote") Long lote) throws BusinessException{
		UsuarioFirmadoVO usuarioFirmadoVO = usuarioFirmadoService.getUsuarioFirmadoVO();
		
		return new ResponseEntity<Boolean>(deteccionesService.actualizarFmLoteReasignadoCP(lote,ArchivoBatchEstatusEnum.CODIGO_POSTAL_REASIGNADO.getEstatusProceso(),usuarioFirmadoVO.getId(),1), HttpStatus.OK);
	}
	
	/**
	 * @author Sail
	 * @param lote
	 * @return Boolean
	 * @throws BusinessException
	 */
	@RequestMapping(value = "/recomplementaCP", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('FM_RECOMPLEMENTA_PROCESO_CP')")
	public ResponseEntity<Boolean> recomplementarCentroReparto(@RequestParam(value = "lote") Long lote) throws BusinessException{
		UsuarioFirmadoVO usuarioFirmadoVO = usuarioFirmadoService.getUsuarioFirmadoVO();
		
		return new ResponseEntity<Boolean>(deteccionesService.actualizarFmLoteReasignadoCP(lote,ArchivoBatchEstatusEnum.RECOMPLEMENTANDO_CENTRO_REPARTO.getEstatusProceso(),usuarioFirmadoVO.getId(),1), HttpStatus.OK);
	}
	
	/**
	 * @author Sail
	 * @param lote
	 * @return Boolean
//	 * @throws BusinessException
	 */
	@RequestMapping(value = "/habilitaFmCambioCP", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('FM_HABILITA_CP_DETECCION')")
	public ResponseEntity<Boolean> habilitaFmCambioCP(@RequestParam(value = "idCP") Long idCp, @RequestParam(value = "idLote") Long lote, @RequestParam(value = "cp") String cp) throws BusinessException{
		UsuarioFirmadoVO usuarioFirmadoVO = usuarioFirmadoService.getUsuarioFirmadoVO();
		
		return new ResponseEntity<Boolean>(deteccionesService.habilitaFmCambioCP(idCp,lote,usuarioFirmadoVO.getId(),cp), HttpStatus.OK);
	}
	
	/**
	 * @author Sail
	 * @param lote
	 * @return Boolean
	 * @throws BusinessException
	 */
	@RequestMapping(value = "/deshabilitaFmCambioCP", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('FM_DESHABILITA_CP_DETECCION')")
	public ResponseEntity<Boolean> deshabilitaFmCambioCP(@RequestParam(value = "idCP") Long idCp, @RequestParam(value = "idLote") Long lote) throws BusinessException{
		UsuarioFirmadoVO usuarioFirmadoVO = usuarioFirmadoService.getUsuarioFirmadoVO();
		System.out.println("Datos a sehabilitar: "+idCp+" "+lote+" "+usuarioFirmadoVO.getId());
		return new ResponseEntity<Boolean>(deteccionesService.deshabilitaFmCambioCP(idCp,lote,usuarioFirmadoVO.getId()), HttpStatus.OK);
	}
	
	/**
	 * Gibran Garcia
	 * ***/
	@RequestMapping(value = "/complementacionDeCP", method = RequestMethod.GET)
	public ResponseEntity<Boolean>complementacionDeCP
	(@RequestParam(value = "ids" ) int[] ids,
	 @RequestParam(value = "newcp" ) String[]  newcp){
		Long usuario = usuarioFirmadoService.getUsuarioFirmadoVO().getId();
		System.out.println("youare here");
		
		Boolean valor=deteccionesService.actualizarCambiarCP(ids, newcp, usuario);
        return new ResponseEntity<Boolean>(valor, HttpStatus.OK);
	}
	
	/**
	 * Gibran Garcia
	 * ***/
	@RequestMapping(value = "/complementacionRADDeCP", method = RequestMethod.GET)
	public ResponseEntity<Boolean>complementacionRADDeCP
	(@RequestParam(value = "ids" ) int[] ids,
	 @RequestParam(value = "newcp" ) String[]  newcp){
		Long usuario = usuarioFirmadoService.getUsuarioFirmadoVO().getId();
		System.out.println("youare here");
		
		Boolean valor=deteccionesService.actualizarCambiarRADCP(ids, newcp, usuario);
        return new ResponseEntity<Boolean>(valor, HttpStatus.OK);
	}
	
	/**
	 * José Castillo
	 * ***/
	@RequestMapping(value = "/consultaDeteccionesSP", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('FM_CONSULTA_DETECCION_SP')")
	public ResponseEntity<List<FCConsultaDeteccionesSinProcVO>> filtroTipoDeteccion(
//					@RequestParam(value = "TipoDeteccion" ) Integer tipoDeteccion,
//					@RequestParam(value = "TipoRadar" ) Integer tipoRadar,
//					@RequestParam(value = "tipoFecha" ) Integer tipoFecha,
//					@RequestParam(value = "origenPlaca" ) Integer origenPlaca
					@RequestParam(value = "tipoDeteccion" ) Integer tipoDeteccion,
					@RequestParam(value = "tipoFecha" ) Integer tipoFecha,
					@RequestParam("multipleTipoArchivo") List<Integer> multipleTipoArchivo
		) throws NotFoundException {
		List<FCConsultaDeteccionesSinProcVO> filterValues = ServiceFM.consultaDeteccionesSP(tipoDeteccion,tipoFecha,multipleTipoArchivo);//tipoDeteccion,tipoRadar,tipoFecha,origenPlaca
		return new ResponseEntity<List<FCConsultaDeteccionesSinProcVO>>(filterValues, HttpStatus.OK);
	}
	
	/**
	 * José Castillo
	 * ***/
	/**Consulta detalle de detecciones actuales de la fecha del dia menos 3 meses **/	
	@RequestMapping(value = "/consultaDeteccionesSPDetalle", method = RequestMethod.GET)
	//@PreAuthorize("hasAnyAuthority('FM_CONSULTA_DETALLE_ACTUAL')")
	public ResponseEntity<List<FMConsultaDeteccionesSPVO>> consultaDeteccionesSPDetalle(
					@RequestParam(value = "idTipoFotocivica" ) Integer idTipoFotocivica,
					@RequestParam(value = "idTipoArchivo" ) Integer idTipoArchivo,
					@RequestParam(value = "tipoConsulta" ) Integer tipoConsulta,
					@RequestParam(value = "tipoFecha" ) Integer tipoFecha,
					@RequestParam(value = "tipoDetConsulta" ) Integer tipoDetConsulta,
					@RequestParam(value = "mesConsulta" ) String mesConsulta,
					@RequestParam(value = "anioConsulta" ) String anioConsulta
			) throws NotFoundException {
		List<FMConsultaDeteccionesSPVO> filterValues = ServiceFM.consultaDeteccionesSPDetalle(idTipoFotocivica,idTipoArchivo,tipoConsulta,tipoFecha,tipoDetConsulta,mesConsulta,anioConsulta);
		
	    return new ResponseEntity<List<FMConsultaDeteccionesSPVO>>(filterValues, HttpStatus.OK);
	}
	
	/******************* METODO PARA FUNCIONALIDAD CANCELACION DE DETECCIONES ****************************************************************************************************/
	
    @RequestMapping(value = "/TiposFechaDetecciones", method = RequestMethod.GET)
	//@PreAuthorize("hasAnyAuthority('FM_FILTRO_FECHAS')")
	public ResponseEntity<List<FMDeteccionesFechasVO>> TiposFechaDetecciones() throws NotFoundException{
    	
    	List<FMDeteccionesFechasVO> FechasDetecciones = CancelacionDetecciones.filtroTiposFecha();
		return new ResponseEntity<List<FMDeteccionesFechasVO>>(FechasDetecciones, HttpStatus.OK);
	}
    
    @RequestMapping(value = "/consultaDeteccionesParaCancelar", method = RequestMethod.GET)
	//@PreAuthorize("hasAnyAuthority('FM_FILTRO_FECHAS')")
	public ResponseEntity<List<FMConsultaDeteccionesVO>> consultaDeteccionesParaCancelar(
			@RequestParam(value = "fechaInicio") String fechaInicio,
			@RequestParam(value = "fechaFin")String fechaFin,
			@RequestParam(value = "TipoDeteccion")Integer tipoDeteccion,
			@RequestParam(value = "TipoRadar" ) Integer tipoRadar,
			@RequestParam(value = "OrigenPlaca" ) Integer origenPlaca) throws NotFoundException{
    	List<FMConsultaDeteccionesVO> DeteccionesCancelar = CancelacionDetecciones.DeteccionesParaCancelar(fechaInicio,fechaFin,tipoDeteccion,tipoRadar,origenPlaca);
		return new ResponseEntity<List<FMConsultaDeteccionesVO>>(DeteccionesCancelar, HttpStatus.OK);
	}
	
    
    
    @RequestMapping(value = "/consultaDetalles", method = RequestMethod.GET)
    //@PreAuthorize("hasAnyAuthority('FM_FILTRO_FECHAS')")
    public ResponseEntity<List<FMConsultaDetalleDeteccionesVO>> consultaDetalles(@RequestParam(value = "fechaInicio") String fechaInicio,
  																					     @RequestParam(value = "fechaFin")String fechaFin,
  																					     @RequestParam(value = "TipoDeteccion")Integer tipoDeteccion,
  			                                                                             @RequestParam(value = "TipoRadar" ) Integer tipoRadar,
  			                                                                             @RequestParam(value = "OrigenPlaca" ) Integer origenPlaca) throws NotFoundException{
      	
      	List<FMConsultaDetalleDeteccionesVO> DeteccionesDetalle = CancelacionDetecciones.DeteccionesDetalle(fechaInicio, fechaFin, tipoDeteccion, tipoRadar, origenPlaca);
      	
      	if (DeteccionesDetalle.isEmpty()) {
  			throw new NotFoundException("No se encontraron detecciones" );
  		}
  		return new ResponseEntity<List<FMConsultaDetalleDeteccionesVO>>(DeteccionesDetalle, HttpStatus.OK);
  	}
    
    @RequestMapping(value = "/CancelarDeteccionesFM", method = RequestMethod.GET)
  	//@PreAuthorize("hasAnyAuthority('FM_FILTRO_FECHAS')")
  	public ResponseEntity<Long> CancelarDeteccionesFM(@RequestParam(value = "fechaInicio") String fechaInicio,
  																					     @RequestParam(value = "fechaFin")String fechaFin,
  																					     @RequestParam(value = "TipoDeteccion")Integer tipoDeteccion,
  			                                                                             @RequestParam(value = "TipoRadar" ) Integer tipoRadar,
  			                                                                             @RequestParam(value = "OrigenPlaca" ) Integer origenPlaca,
  			                                                                             @RequestParam(value = "motivo")String motivo) throws NotFoundException{
    	 Calendar calendar = Calendar.getInstance();
	     calendar.setTime(new Date()); 
	     DateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
	   
        
	     String fechaCancelacion = formatoFecha.format(calendar.getTime());
    	
    	Long usuario = usuarioFirmadoService.getUsuarioFirmadoVO().getId();
    	
		Long resultado = CancelacionDetecciones.DeteccionesCancelacionFM(fechaCancelacion,usuario,fechaInicio, fechaFin, tipoDeteccion, tipoRadar, origenPlaca,motivo);
		return new ResponseEntity<Long> (resultado, HttpStatus.OK);
  	}
    
    
	@RequestMapping(value = "/descargarExcelDetalleDetecciones", method = RequestMethod.GET)
	//@PreAuthorize("hasAnyAuthority('CANCELACION_DETECCIONES_DESCARGA_EXCEL')")
    public ResponseEntity<byte[]> descargarReporteCanceladas(){
    	
    	String filename = "ReporteDeteccionesCancelar.xls";
    	ByteArrayOutputStream outputStream = CancelacionDetecciones.descargaExcelDeteccMarcado();
    	final byte[] bytes = outputStream.toByteArray();
    	
    	HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/vnd.ms-exce"));
    	headers.add("Content-Disposition", "attachment; filename=" + filename);
    	headers.add("filename",   filename);
        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
        headers.setContentLength(bytes.length);
        
        ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(bytes, headers, HttpStatus.OK);
        
        return response;
	}
  	
    @RequestMapping(value = "/consultaDetecciones", method = RequestMethod.GET)
	//@PreAuthorize("hasAnyAuthority('FM_CONSULTA_DETECCION_SP')")
	public ResponseEntity<Map<String, Object>> consultaDeteccioniones(
					@RequestParam(value = "tipoDeteccion" ) Integer tipoDeteccion,
					@RequestParam("multipleTipoArchivo") List<Integer> multipleTipoArchivo,
					@RequestParam(value = "fechaInicio" ) String fechaInicio,
					@RequestParam(value = "fechaFin" ) String fechaFin,
					@RequestParam(value = "tipoBusqueda" ) Integer tipoBusqueda,
					@RequestParam(value = "valorBusqueda" ) String valorBusqueda,
					@RequestParam(value = "consultaProcesables" ) Integer consultaProcesables
					) throws NotFoundException {
    	Map<String, Object> response = null;
    	response = ServiceFM.consultaDetecciones(tipoDeteccion,multipleTipoArchivo,fechaInicio,fechaFin,tipoBusqueda,valorBusqueda, consultaProcesables);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
    
    @RequestMapping(value = "/generaRepConsultaDetecciones", method = RequestMethod.GET)
    public ResponseEntity<byte[]> generaReporteExcel() {
    	Map data = ServiceFM.getReporteDeteccionesPorLote();
    	final byte[] bytes = ((ByteArrayOutputStream) data.get("reporte")).toByteArray();
    	String filename = (String) data.get("nombre");
    	
    	HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/vnd.ms-exce"));
    	headers.add("Content-Disposition", "attachment; filename=" + filename);
    	headers.add("filename",   filename);
        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
        headers.setContentLength(bytes.length);
        
        ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(bytes, headers, HttpStatus.OK);
        return response; 
    }
}
