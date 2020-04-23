package mx.com.teclo.saicdmx.api.rest.atencionCiudadana;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
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

import mx.com.teclo.saicdmx.negocio.service.atencionCiudadana.AtencionCiudadanaService;
import mx.com.teclo.saicdmx.negocio.service.comun.UsuarioFirmadoService;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.CatMedioSolicitudDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.CatTipoDocumentoDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.CatTipoTramiteDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.empleados.EmpleadoFirmaDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.vo.catalogos.CatMedioSolicitudVO;
import mx.com.teclo.saicdmx.persistencia.hibernate.vo.catalogos.CatTipoDocumentoVO;
import mx.com.teclo.saicdmx.persistencia.hibernate.vo.catalogos.CatTipoTramiteVO;
import mx.com.teclo.saicdmx.persistencia.vo.atencionciudadana.ACSegDetTramiteConsultaVO;
import mx.com.teclo.saicdmx.persistencia.vo.atencionciudadana.ACSegTramiteConsultaVO;
import mx.com.teclo.saicdmx.persistencia.vo.atencionciudadana.ACSegTramiteVO;
import mx.com.teclo.saicdmx.persistencia.vo.atencionciudadana.ACTramiteConsultaVO;
import mx.com.teclo.saicdmx.persistencia.vo.atencionciudadana.ACTramiteVO;
import mx.com.teclo.saicdmx.persistencia.vo.atencionciudadana.AtenCiudaCamposRequeridosVO;
import mx.com.teclo.saicdmx.persistencia.vo.atencionciudadana.ExpedienteTramiteVO;
import mx.com.teclo.saicdmx.util.comun.ResponseConverter;
import mx.com.teclomexicana.arquitectura.ortogonales.exception.BusinessException;
import mx.com.teclomexicana.arquitectura.ortogonales.exception.ControllerException;
import mx.com.teclomexicana.arquitectura.ortogonales.exception.NotFoundException;
import mx.com.teclomexicana.arquitectura.ortogonales.seguridad.vo.UsuarioFirmadoVO;




@RestController
@RequestMapping("/atencionCiudadana")
public class AtencionCiudadanaRestController {

	@Autowired
	private AtencionCiudadanaService atencionCiudadanaService;	
	@Autowired
	private UsuarioFirmadoService usuarioFirmadoService;

	
	@RequestMapping(value = "/catTipoTramite", method = RequestMethod.GET)
	public ResponseEntity<List<CatTipoTramiteVO>> buscarTipoTramite() {
		List<CatTipoTramiteDTO> tipoTramiteDTO = new ArrayList<CatTipoTramiteDTO>();
		tipoTramiteDTO = atencionCiudadanaService.getCatalogoTramite();
		List<CatTipoTramiteVO> tramiteVO = ResponseConverter.converterLista(new ArrayList<>(), tipoTramiteDTO,
				CatTipoTramiteVO.class);
		return new ResponseEntity<List<CatTipoTramiteVO>>(tramiteVO, HttpStatus.OK);
	}

	@RequestMapping(value = "/catTipoDocumento", method = RequestMethod.GET)
	public ResponseEntity<List<CatTipoDocumentoVO>> buscarTipoDocumento() {
		List<CatTipoDocumentoDTO> tipoDocumentoDTO = new ArrayList<CatTipoDocumentoDTO>();
		tipoDocumentoDTO = atencionCiudadanaService.getCatalogoDocumento();
		List<CatTipoDocumentoVO> documentoVO = ResponseConverter.converterLista(new ArrayList<>(), tipoDocumentoDTO,
				CatTipoDocumentoVO.class);
		return new ResponseEntity<List<CatTipoDocumentoVO>>(documentoVO, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/catOrigenSolicitud", method = RequestMethod.GET)
	public ResponseEntity<List<CatMedioSolicitudVO>> buscarOrigenSolicitud() {
		List<CatMedioSolicitudDTO> medioSolicitudDTO = new ArrayList<CatMedioSolicitudDTO>();
		medioSolicitudDTO = atencionCiudadanaService.getCatalogoSolicitud();
		List<CatMedioSolicitudVO> solicitudVO = ResponseConverter.converterLista(new ArrayList<>(), medioSolicitudDTO,
				CatMedioSolicitudVO.class);
		return new ResponseEntity<List<CatMedioSolicitudVO>>(solicitudVO, HttpStatus.OK);
	}
	
	

	@RequestMapping(value = "/altaTramite", method = RequestMethod.POST)
	@PreAuthorize("hasAnyAuthority('ALTA_TRAMITE')")
	public ResponseEntity<List<String>> altaTramite(@RequestBody ACTramiteVO altaTramiteVO) throws BusinessException, NotFoundException {
        try
        {
        	UsuarioFirmadoVO usuario = usuarioFirmadoService.getUsuarioFirmadoVO();
        	
        	altaTramiteVO.setIdEmp(usuario.getId().intValue());
			List<String> insertarTramite = atencionCiudadanaService.insertarTramite(altaTramiteVO);
			if(insertarTramite.get(1).equals("true")) {
				if(altaTramiteVO.getFoliosDeInfraccion()!=null) {
					String[] listInfracciones = altaTramiteVO.getFoliosDeInfraccion().split(",");
					ACSegTramiteVO acSegTramite = new ACSegTramiteVO();
					
					acSegTramite.setIdAcTramite(altaTramiteVO.getIdacTramite());
					acSegTramite.setIdTipoTramite(6);
					acSegTramite.setIdTipoValor(1);
					acSegTramite.setStSegTramite(1);
					acSegTramite.setStActivo(1);
					acSegTramite.setIdUsrCreacion(Long.valueOf(usuario.getId()));
					acSegTramite.setIdUsrModifica(Long.valueOf(usuario.getId()));
					atencionCiudadanaService.nuevoSeguimientoTramite(acSegTramite, listInfracciones);
				}
			}
			return new ResponseEntity<List<String>>(insertarTramite, HttpStatus.OK);
        }catch(Exception e)
        {
        	e.printStackTrace();
    		throw new NotFoundException("Ha ocurrido un imprevisto!, por favor contacte al administrador.");
        }
	}
	
	@RequestMapping(value = "/consultaInfraccion", method = RequestMethod.GET)
	public ResponseEntity<List<String>> buscaInfraccionFotocivica(@RequestParam("placa") String placa,
			@RequestParam("infraccion") String infraccion) throws BusinessException, NotFoundException {
        try
        {
        	List<String> seEncontroInfraccion = atencionCiudadanaService.consultaInfraccion(placa,infraccion);
        	return new ResponseEntity<List<String>>(seEncontroInfraccion, HttpStatus.OK);
        }catch(Exception e)
        {
        	e.printStackTrace();
    		throw new NotFoundException("Ha ocurrido un imprevisto!, por favor contacte al administrador.");
        }
	}
	
	@RequestMapping(value = "/vistaPrevia", method = RequestMethod.POST)
	 @PreAuthorize("hasAnyAuthority('VISTA_PREVIA')")
	public ResponseEntity<byte[]> vistaPrevia(@RequestBody ACTramiteVO altaTramiteVO) throws BusinessException, NotFoundException {
		try
		{
		String filename = "Reporte_Tramite_" + altaTramiteVO.getIdacTramite() + ".pdf";
		ByteArrayOutputStream outputStream = atencionCiudadanaService.vistaPrevia(altaTramiteVO);

		byte[] bytes = outputStream.toByteArray();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.parseMediaType("application/pdf"));
		headers.add("Content-Disposition", "attachmnt; filename =" + filename);
		headers.add("filename", filename);
		headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
		headers.setContentLength(bytes.length);
		return new ResponseEntity<byte[]>(bytes, headers, HttpStatus.OK);
		}catch(Exception e)
		{
			e.printStackTrace();
			throw new NotFoundException("Ha ocurrido un imprevisto!, por favor contacte al administrador.");
		}
	}

	@RequestMapping(value = "/reporteAltaTramite", method = RequestMethod.GET)
	 @PreAuthorize("hasAnyAuthority('REPORTE_ALTA_TRAMITE')")
	public ResponseEntity<byte[]> generarReporteAltaTramite(@RequestParam(value = "idTramite") String idTramite

	)throws BusinessException, NotFoundException {
		
        try
        {
		String filename = "Reporte_Tramite_" + idTramite + ".pdf";
		ByteArrayOutputStream outputStream = atencionCiudadanaService.generaReporteTramite(idTramite);

		byte[] bytes = outputStream.toByteArray();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.parseMediaType("application/pdf"));
		headers.add("Content-Disposition", "attachmnt; filename =" + filename);
		headers.add("filename", filename);
		headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
		headers.setContentLength(bytes.length);
		return new ResponseEntity<byte[]>(bytes, headers, HttpStatus.OK);
        }catch(Exception e)
		{
			e.printStackTrace();
			throw new NotFoundException("Ha ocurrido un imprevisto!, por favor contacte al administrador.");
		}

	}
	
	@RequestMapping(value = "/cargarExpedienteTramite", method = RequestMethod.POST)
	 @PreAuthorize("hasAnyAuthority('CARGAR_EXPEDIENTE_TRAMITE')")
	public ResponseEntity<ExpedienteTramiteVO> cargarExpedienteTramite(
			@RequestBody ExpedienteTramiteVO expedienteTramiteVO) throws NotFoundException {
		UsuarioFirmadoVO usuario = usuarioFirmadoService.getUsuarioFirmadoVO();
		try {
			ExpedienteTramiteVO expTramiteVO = atencionCiudadanaService.guardarExpedineteTramite(
					expedienteTramiteVO,usuario.getId());
			return new ResponseEntity<ExpedienteTramiteVO>(expTramiteVO, HttpStatus.OK);
		}catch (Exception e) {
			System.err.println(e);
			throw new NotFoundException("No se pudo guardar el expediente, contacte con su administrador");
		}
	}

	/**
	 * RETORNA LA CONSULTA DE TODOS LOS TRAMITES ENCONTRADOS DE ACUERDO A LOS
	 * PARAMTROS DE BUSQUEDA
	 * 
	 * @author David.Guerra
	 * @return List<ACTramiteConsultaVO>
	 */
	/* COMIENZA CONSULTA */
	@RequestMapping(value = "/consultaTramitesParametros", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('CONSULTA_TRAMITE')")
	public ResponseEntity<List<ACTramiteConsultaVO>> buscarTramitesHistorico(
			@RequestParam("fhInicio") String fechaInicio,
			@RequestParam("fhFin") String fechaFin, 
			@RequestParam("paramBusqueda") Integer paramBusqueda,
			@RequestParam(name="valor", required = false) String valor, 
			@RequestParam("opcionAtendido") Integer atendido,
			@RequestParam(name="avanzadoNombre", required = false) String avanzadoNombre,
			@RequestParam(name="avanzadoAP", required = false) String avanzadoAP, 
			@RequestParam(name="avanzadoAM", required = false) String avanzadoAM,
			@RequestParam(name="avanzadoTel", required = false) String avanzadoTel, 
			@RequestParam(name="avanzadoCorreo", required = false) String avanzadoCorreo,
			@RequestParam(name="avanzadoEmpresa", required = false) String avanzadoEmpresa, 
			@RequestParam("busquedaAvanzada") Boolean busquedaAvanzada)
			throws NotFoundException {

		try
		{
	List<ACTramiteConsultaVO> consultadefault = atencionCiudadanaService.buscarTramitesHistorico(fechaInicio,fechaFin, paramBusqueda, valor, atendido,
			avanzadoNombre,avanzadoAP,avanzadoAM,avanzadoTel,avanzadoCorreo,avanzadoEmpresa,busquedaAvanzada);
	return new ResponseEntity<List<ACTramiteConsultaVO>>(consultadefault, HttpStatus.OK);
	}catch(Exception e)
		{
		e.printStackTrace();
		throw new NotFoundException("Ha ocurrido un imprevisto!, por favor contacte al administrador.");
	
		}

	}

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/tramitesExcel", method = RequestMethod.GET)
    @PreAuthorize("hasAnyAuthority('GENERAR_REPORTE_TRAMITES_AC')")
	public ResponseEntity<byte[]> generarReporteExcelHistoricos() {

		Map reporte = atencionCiudadanaService.getReporteExcelConsulta();
		String filename = (String) reporte.get("nombre");
		ByteArrayOutputStream outputStream = (ByteArrayOutputStream) reporte.get("reporte");
		final byte[] bytes = outputStream.toByteArray();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.parseMediaType("application/vnd.ms-exce"));
		headers.add("Content-Disposition", "attachment; filename=" + filename);
		headers.add("filename", filename);
		headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
		headers.setContentLength(bytes.length);

		ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(bytes, headers, HttpStatus.OK);

		return response;
	}

	/*@RequestMapping(value = "/descargarExpediente", method = RequestMethod.GET)
	// @PreAuthorize("hasAnyAuthority('EXPEDIENTE_TRAMITE')")
	public ResponseEntity<byte[]> generarDescargaExpediente(@RequestParam("idTramite") String folioTramite)
			throws NotFoundException {
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.add("filename", "Expediente_" + folioTramite);
			byte[] blobAsBytes = atencionCiudadanaService.obtenerBytesExpediente(folioTramite);
			return new ResponseEntity<byte[]>(blobAsBytes, headers, HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
			throw new NotFoundException("Ha ocurrido un imprevisto! , porfavor contacte al administrador");
		}

	}*/
	
	@RequestMapping(value = "/consultaDefaultModificacion", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('CONSULTA_DEFAULT_MODIFICACION')")
	public ResponseEntity<List<ACTramiteConsultaVO>> busquedaDefaultModficacion(
			@RequestParam(value = "opcion") Boolean op) throws NotFoundException {
		List<ACTramiteConsultaVO> consultadefault = atencionCiudadanaService.consultaDefaultModificacion(op);
		if (consultadefault.isEmpty())
			throw new NotFoundException("No se encontraron resultados");
		return new ResponseEntity<List<ACTramiteConsultaVO>>(consultadefault, HttpStatus.OK);
	}
	
	@RequestMapping(value="/consultaTramiteDetalle", method = RequestMethod.GET)
	 @PreAuthorize("hasAnyAuthority('DETALLE_TRAMITE')")
	public ResponseEntity<ACTramiteConsultaVO> busquedaTramitesAModificar(
			@RequestParam(value="folio") String folio, 
			@RequestParam(value="paramBusqueda") Integer paramBusqueda) throws NotFoundException{
		List<ACTramiteConsultaVO> consultadefault = atencionCiudadanaService.buscarTramitesParaModificar("", "", paramBusqueda, folio, 0);
		if (consultadefault.isEmpty())
			throw new NotFoundException("No se encontraron coincidencias con los criterios de busqueda");
		return new ResponseEntity<ACTramiteConsultaVO>(consultadefault.get(0), HttpStatus.OK);
	}
	
	@RequestMapping(value="/informacionTooltipAyuda", method = RequestMethod.GET)
	public ResponseEntity<Map<String, String>> busquedaInformacionAyuda() throws NotFoundException{
		Map<String, String> ParametrosTooltipAyuda=atencionCiudadanaService.obtenerParametrosAyuda();
		return new ResponseEntity<Map<String, String>>(ParametrosTooltipAyuda, HttpStatus.OK);
	}
	
	@RequestMapping(value="/consultaCamposRequeridos", method = RequestMethod.GET)
	public ResponseEntity<AtenCiudaCamposRequeridosVO> busquedaCamposRequeridos() throws NotFoundException{
   		AtenCiudaCamposRequeridosVO atenCiudaCamposRequeridosVO=atencionCiudadanaService.obtenerJsonCamposRequeridos();
		return new ResponseEntity<AtenCiudaCamposRequeridosVO>(atenCiudaCamposRequeridosVO, HttpStatus.OK);
	}
	
	
	
	
	@RequestMapping(value = "/modificaACTramite", method = RequestMethod.POST)
	@PreAuthorize("hasAnyAuthority('MODIFICA_TRAMITE')")
	public ResponseEntity<ACTramiteConsultaVO> motificaTramite
	(@RequestBody ACTramiteConsultaVO motificaTramiteVO)throws BusinessException {
		UsuarioFirmadoVO usuario = usuarioFirmadoService.getUsuarioFirmadoVO();
		motificaTramiteVO = atencionCiudadanaService.modificaTramite(motificaTramiteVO, usuario.getId());
		
		ACSegTramiteVO acSegTramite = new ACSegTramiteVO();
		String[] listAgregadas = motificaTramiteVO.getTxInfraccionAgregadasModi() != null ? 
				motificaTramiteVO.getTxInfraccionAgregadasModi().split(",") : null;
		String[] listEliminadas = motificaTramiteVO.getTxInfraccionEliminadasModi() != null ? 
				motificaTramiteVO.getTxInfraccionEliminadasModi().split(",") : null;
		
			
		if(listAgregadas != null) {
			acSegTramite.setIdAcTramite(motificaTramiteVO.getIdACTramite());
			acSegTramite.setIdTramiteDetalle(motificaTramiteVO.getIdTramiteDetalle());
			acSegTramite.setIdTipoTramite(6);
			acSegTramite.setIdTipoValor(1);
			acSegTramite.setStSegTramite(1);
			acSegTramite.setStActivo(1);
			acSegTramite.setIdUsrCreacion(usuario.getId());
			acSegTramite.setIdUsrModifica(usuario.getId());
		}
		
		atencionCiudadanaService.modificarSeguimientoTramite(
				acSegTramite, motificaTramiteVO.getIdACTramite(), motificaTramiteVO.getTotalDeInfraccionesModificacion(), 
				usuario.getId(), listAgregadas, listEliminadas);
		
		return new ResponseEntity<ACTramiteConsultaVO>(motificaTramiteVO, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/descargarExpedienteTramite", method = RequestMethod.POST)
	 @PreAuthorize("hasAnyAuthority('EXPEDIENTE_TRAMITE_TODO')")
	public ResponseEntity<ExpedienteTramiteVO> descargarExpedienteTramite(
			@RequestParam(value="folioTramite") String folioTramite,
			@RequestParam(value="tipoExp") String tipoExp) throws NotFoundException {
		try {
			ExpedienteTramiteVO expTramiteVO = atencionCiudadanaService.descargarExpedineteTramite(folioTramite, tipoExp);
			return new ResponseEntity<ExpedienteTramiteVO>(expTramiteVO, HttpStatus.OK);
		}catch (Exception e) {
			System.err.println(e);
			throw new NotFoundException("No se pudo descargar el expediente, contacte con su administrador");
		}
	}
	
	@RequestMapping(value = "/remplazarExpedienteTramite", method = RequestMethod.POST)
	 @PreAuthorize("hasAnyAuthority('REMPLAZAR_EXPEDIENTE')")
	public ResponseEntity<ExpedienteTramiteVO> remplazarExpedienteTramite(
			@RequestBody ExpedienteTramiteVO expedienteTramiteVO) throws NotFoundException {
		try {
			UsuarioFirmadoVO usuario = usuarioFirmadoService.getUsuarioFirmadoVO();
			ExpedienteTramiteVO expTramiteVO = atencionCiudadanaService.remplazarExpedineteTramite(expedienteTramiteVO, usuario.getId());
			return new ResponseEntity<ExpedienteTramiteVO>(expTramiteVO, HttpStatus.OK);
		}catch (Exception e) {
			System.err.println(e);
			throw new NotFoundException("No se pudo remplazar el expediente, contacte con su administrador");
		}
	}

	@RequestMapping(value = "/consultaSeguimiento", method = RequestMethod.GET)
	public ResponseEntity<List<ACSegTramiteConsultaVO>> consultaSeguimiento(
			@RequestParam(value = "tipoBusqueda", required = true) int tipoBusqueda, 
			@RequestParam(value= "valor", required = false) String valor, 
			@RequestParam(value = "idTipoTramite", required = true) Integer idTipoTramite,
			@RequestParam(value = "stSegTramite", required = true) Integer stSegTramite, 
			@RequestParam(value = "tipoFecha", required = true) int tipoFecha, 
			@RequestParam(value = "fhInicio", required = false) String fhInicio, 
			@RequestParam(value = "fhFin", required = false) String fhFin) throws NotFoundException{
		List<ACSegTramiteConsultaVO> listSegTramite = new ArrayList<ACSegTramiteConsultaVO>();
//		try {		
			listSegTramite = atencionCiudadanaService.getListaSeguimientosTramite(
				tipoBusqueda, valor, idTipoTramite, stSegTramite, tipoFecha, fhInicio, fhFin);
//		}catch (Exception e) {
//			throw new NotFoundException("Ocurrio un error al momento de consultar");
//		}
		 
		return new ResponseEntity<List<ACSegTramiteConsultaVO>>(listSegTramite, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/realizarCambioDePersona", method = RequestMethod.GET)
	public ResponseEntity<Boolean> realizarCambioDePersona(
			@RequestParam(value = "idAcTramite", required = true) String idAcTramite,
			@RequestParam(value = "txComentario", required = true) String txComentario) throws NotFoundException{
		UsuarioFirmadoVO usuario = usuarioFirmadoService.getUsuarioFirmadoVO();
		Boolean respuesta;
		try {
			respuesta = atencionCiudadanaService.realizarCambioDePersona(idAcTramite, usuario.getId());
			if(respuesta) {
				atencionCiudadanaService.actualizarACTramiteSeguimientoFhMod(idAcTramite, usuario.getId());
				atencionCiudadanaService.actualizarACTramiteDetalleFhMod(idAcTramite, usuario.getId(), txComentario);
				atencionCiudadanaService.actualizarACTramiteFhMod(idAcTramite, usuario.getId());
				return new ResponseEntity<Boolean>(respuesta, HttpStatus.OK);
			}else {
				return new ResponseEntity<Boolean>(respuesta, HttpStatus.NOT_FOUND);
			}
		}catch (Exception e) {
			throw new NotFoundException("Ocurrio un error al momento de realizar el cambio de tipo de persona");
		}
	}
	
	@RequestMapping(value = "/getInfCambioDePersona", method = RequestMethod.GET)
	public ResponseEntity<List<ACSegDetTramiteConsultaVO>> getInfCambioDePersona(
			@RequestParam(value = "placaVehicular", required = false) String placaVehicular,
			@RequestParam(value = "infracNum", required = false) String infracNum,
			@RequestParam(value = "listInfracciones", required = false) String listInfracciones)
					 throws NotFoundException{
		try {
			List<ACSegDetTramiteConsultaVO> listACSegDetTramiteConsulta = new ArrayList<ACSegDetTramiteConsultaVO>();
			if(listInfracciones != null) {
				listACSegDetTramiteConsulta = atencionCiudadanaService.getInfCambioDePersona(listInfracciones);
			}else {
				listACSegDetTramiteConsulta = atencionCiudadanaService.getListInfCambioDePersona(placaVehicular, infracNum);
			}
			return new ResponseEntity<List<ACSegDetTramiteConsultaVO>>(listACSegDetTramiteConsulta, HttpStatus.OK);
		}
		catch (Exception e) {
			System.err.println("getInfCambioDePersona " + e);
			throw new NotFoundException("Ocurrio un problema, consulte con su administrador");
		}	
	}
	
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/seguimeintoTramitesTPExcel", method = RequestMethod.GET)
    @PreAuthorize("hasAnyAuthority('GENERAR_REPORTE_TRAMITES_AC')")
	public ResponseEntity<byte[]> generaReporteExcelSeguimientoTramite(
			@RequestParam(value = "tipoBusqueda", required = true) int tipoBusqueda, 
			@RequestParam(value= "valor", required = false) String valor, 
			@RequestParam(value = "idTipoTramite", required = true) Integer idTipoTramite,
			@RequestParam(value = "stSegTramite", required = true) Integer stSegTramite, 
			@RequestParam(value = "tipoFecha", required = true) int tipoFecha, 
			@RequestParam(value = "fhInicio", required = false) String fhInicio, 
			@RequestParam(value = "fhFin", required = false) String fhFin) {

		String NombreReporte = "Consulta Seguimiento Cambio Tipo de Persona";
		List<ACSegTramiteConsultaVO> listSeguimiento = atencionCiudadanaService.getListaSeguimientosTramite(
				tipoBusqueda, valor, idTipoTramite, stSegTramite, tipoFecha, fhInicio, fhFin);
		
		ByteArrayOutputStream reporteExcel = atencionCiudadanaService.generaReporteExcelSeguimientoTramite(
				tipoBusqueda, valor, idTipoTramite, stSegTramite, tipoFecha, fhInicio, fhFin, listSeguimiento);
		
		final byte[] bytes = reporteExcel.toByteArray();

		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.parseMediaType("application/vnd.ms-exce"));
		headers.add("Content-Disposition", "attachment; filename=" + NombreReporte);
		headers.add("filename", NombreReporte);
		headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
		headers.setContentLength(bytes.length);

		ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(bytes, headers, HttpStatus.OK);

		return response;
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/seguimeintoTramitesTPInfExcel", method = RequestMethod.GET)
    @PreAuthorize("hasAnyAuthority('GENERAR_REPORTE_TRAMITES_AC')")
	public ResponseEntity<byte[]> generaReporteExcelInfraccionesTramite(
			@RequestParam(value = "placaVehicular", required = false) String placaVehicular,
			@RequestParam(value = "infracNum", required = false) String infracNum,
			@RequestParam(value = "listInfracciones", required = false) String listInfracciones,
			@RequestParam(value = "idAcTramite", required = false) String idAcTramite)
					 throws NotFoundException{
		
		List<ACSegDetTramiteConsultaVO> listACSegDetTramiteConsulta = new ArrayList<ACSegDetTramiteConsultaVO>();
		if(listInfracciones != null) {
			listACSegDetTramiteConsulta = atencionCiudadanaService.getInfCambioDePersona(listInfracciones);
		}else {
			listACSegDetTramiteConsulta = atencionCiudadanaService.getListInfCambioDePersona(placaVehicular, infracNum);
		}

		String NombreReporte = "Consulta Seguimiento Infracciones Cambio Tipo de Persona";
		
		ByteArrayOutputStream reporteExcel = atencionCiudadanaService.generaReporteExcelSegTramiteInfracciones(
				placaVehicular, infracNum, listInfracciones, listACSegDetTramiteConsulta, idAcTramite);
		
		final byte[] bytes = reporteExcel.toByteArray();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.parseMediaType("application/vnd.ms-exce"));
		headers.add("Content-Disposition", "attachment; filename=" + NombreReporte);
		headers.add("filename", NombreReporte);
		headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
		headers.setContentLength(bytes.length);

		ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(bytes, headers, HttpStatus.OK);

		return response;
	}
}
