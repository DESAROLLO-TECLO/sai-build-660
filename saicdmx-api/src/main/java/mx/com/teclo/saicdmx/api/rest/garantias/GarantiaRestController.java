package mx.com.teclo.saicdmx.api.rest.garantias;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lowagie.text.pdf.PdfCopyFields;
import com.lowagie.text.pdf.PdfReader;

import mx.com.teclo.saicdmx.negocio.service.catalogos.CatalogoService;
import mx.com.teclo.saicdmx.negocio.service.comun.UsuarioFirmadoService;
import mx.com.teclo.saicdmx.negocio.service.empleado.EmpleadoService;
import mx.com.teclo.saicdmx.negocio.service.garantias.GarantiaMasivaService;
import mx.com.teclo.saicdmx.negocio.service.garantias.GarantiaService;
import mx.com.teclo.saicdmx.negocio.service.infracciones.InfraccionService;
import mx.com.teclo.saicdmx.negocio.service.reportes.AcusePagoService;
import mx.com.teclo.saicdmx.negocio.service.semovi.ParametroService;
import mx.com.teclo.saicdmx.persistencia.hibernate.dao.garantias.GarantiasDAO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dao.garantias.GarantiasEstatusProcesoDAO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.TipoFechasDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.empleados.EmpleadosDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.garantias.GarantiaDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.garantias.GarantiaDocumentoDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.garantias.GarantiaEstatusProcesoDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.garantias.GarantiaProcesoDTO;
import mx.com.teclo.saicdmx.persistencia.mybatis.dao.garantias.GarantiasInfraccionesDAO;
import mx.com.teclo.saicdmx.persistencia.vo.catalogos.TipoFechaVO;
import mx.com.teclo.saicdmx.persistencia.vo.comuns.FilterValuesVO;
import mx.com.teclo.saicdmx.persistencia.vo.empleados.EmpleadosVO;
import mx.com.teclo.saicdmx.persistencia.vo.garantias.CatalogoDocumentosGarantiaVO;
import mx.com.teclo.saicdmx.persistencia.vo.garantias.GarantiaDetallePorPagarVO;
import mx.com.teclo.saicdmx.persistencia.vo.garantias.GarantiaIdMasivasVO;
import mx.com.teclo.saicdmx.persistencia.vo.garantias.GarantiaPorPagarVO;
import mx.com.teclo.saicdmx.persistencia.vo.garantias.VSSPGarantiaCatProcesoFVO;
import mx.com.teclo.saicdmx.persistencia.vo.garantias.VSSPGarantiaConsGralFVO;
import mx.com.teclo.saicdmx.persistencia.vo.garantias.VSSPGarantiaConsultaEntregaFVO;
import mx.com.teclo.saicdmx.persistencia.vo.garantias.VSSPGarantiaConsultaFVO;
import mx.com.teclo.saicdmx.persistencia.vo.garantias.VSSPGarantiaRecepcionInfoFVO;
import mx.com.teclo.saicdmx.persistencia.vo.garantias.VSSPGarantiaReporteGeneralFVO;
import mx.com.teclo.saicdmx.persistencia.vo.pagos.DatosPagoVO;
import mx.com.teclo.saicdmx.persistencia.vo.pagos.PagoVO;
import mx.com.teclo.saicdmx.util.comun.ResponseConverter;
import mx.com.teclo.saicdmx.util.comun.RutinasTiempoImpl;
import mx.com.teclo.saicdmx.util.enums.EnumGarantiasProceso;
import mx.com.teclomexicana.arquitectura.ortogonales.exception.NotFoundException;
import mx.com.teclomexicana.arquitectura.ortogonales.seguridad.vo.UsuarioFirmadoVO;

@RestController
public class GarantiaRestController {

	@Autowired
	private GarantiaService garantiaService;
	@Autowired
	private EmpleadoService empleadoService;
	@Autowired
	private GarantiasDAO garantiaDAO;
	@Autowired
	private GarantiasEstatusProcesoDAO garantiaEstatusProcesoDAO;
	@Autowired
	ServletContext context;
	@Autowired
	private UsuarioFirmadoService usuarioFirmadoService;
	@Autowired
	private GarantiasInfraccionesDAO garantiasInfraccionesDAO;
	@Autowired
	@Qualifier("acusePagoReciboService")
	private AcusePagoService acusePagoReciboService;
	@Autowired
	private ParametroService parametroService;
	@Autowired
	private InfraccionService infraccionService;
	@Autowired
	private CatalogoService catalogoService;
	@Autowired
	private GarantiaMasivaService garantiaMasivaService;

	public static final String ACCION_REPORTE_RECIBO = "reporteRecibo";
	public static final String ACCION_REPORTE_VOUCHER = "reporteVoucher";
	public static final String ACCION_REPORTE_VOUCHER_CENTROPAGOS="reporteVoucherCentroPagos";
	public static final String ACCION_REPORTE_VOUCHER_CANCELACION="reporteVoucherCancelacion";
	
	@RequestMapping(value = "/garantias", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('BUSCAR_GARANTIAS')")
	public ResponseEntity<List<VSSPGarantiaConsGralFVO>> buscarGarantias(@RequestParam(value = "valor") String valor)
			throws NotFoundException {
		List<VSSPGarantiaConsGralFVO> garantias = null;
		garantias = garantiaService.buscarGarantiasSinProcesar(valor, false);
		if(garantias == null){
			throw new NotFoundException("Ha ocurrido un imprevisto! , por favor contacte al administrador");
		}
		if (!garantias.isEmpty()) {
			return new ResponseEntity<List<VSSPGarantiaConsGralFVO>>(garantias, HttpStatus.OK);

		} else {
			throw new NotFoundException("No se encontraron registros");

		}

	}

	@RequestMapping(value = "/garantiasRecibir", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('BUSCAR_GARANTIAS_RECEPCION')")
	public ResponseEntity<List<VSSPGarantiaConsGralFVO>> buscarGarantiasRecepcion(
			@RequestParam(value = "valor") String valor,
			@RequestParam(value = "op") Boolean op) throws NotFoundException {
		
		
		crearGarantia(valor);
		List<VSSPGarantiaConsGralFVO> garantias = null  ;
		garantias = garantiaService.buscarGarantiasSinProcesar(valor, op);
		
		if(garantias == null){
			throw new NotFoundException("No se encontraron resultados");
			
		}
		
		return new ResponseEntity<List<VSSPGarantiaConsGralFVO>>(garantias, HttpStatus.OK);
	}
	
	@SuppressWarnings("unused")
	@Transactional
	private void crearGarantia (String valor) throws NotFoundException{
		String mensajeErr="";
//		try{
			EmpleadosDTO empleadoDTO = empleadoService.getEmpleadoByPlaca(valor);
			EmpleadosVO empleadovo = new EmpleadosVO();
	//		Boolean flagGralGarantias = new Boolean(true);
			if (empleadoDTO != null) {
				ResponseConverter.copiarPropriedades(empleadovo, empleadoDTO);
				UsuarioFirmadoVO usuarioFirmadoVO = usuarioFirmadoService.getUsuarioFirmadoVO();
	//			EmpleadosDTO empFirmado = empleadoService.getEmpleadoById(usuarioFirmadoVO.getId());
				Boolean flagGralGarantias = garantiaService.generarGarantiasSinProcesar(empleadovo.getEmpPlaca(),usuarioFirmadoVO.getId());
		
			}else{
				mensajeErr="No se encontró el oficial";
				
			}
//		}catch(Exception e){
//			
//			if(mensajeErr != null && !mensajeErr.isEmpty() && !mensajeErr.equals(null)) {
//				throw new NotFoundException(mensajeErr);
//			} else {
//				
//				throw new NotFoundException("Ha ocurrido un imprevisto! , porfavor contacte al administrador");
//			}
//			
//		}
		
	}
	
	@RequestMapping(value = "/verificarPerfilCaja", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('FIND_PERFIL_PAGO_ACTUAL')")
	public @ResponseBody boolean  verificarPerfilCajero() throws NotFoundException {
		return garantiaService.obtenerPerfilCajero();
	}
	
	@RequestMapping(value = "/cargarParamGarantiasPorPagar", method = RequestMethod.GET)
	public ResponseEntity<Map<String, List<FilterValuesVO>>> obtenerGarantiasPorPagar(){
		List<FilterValuesVO> listaParametros1 = new ArrayList<FilterValuesVO>();
		List<FilterValuesVO> listaParametros2 = new ArrayList<FilterValuesVO>();
		List<FilterValuesVO> listaParametros3 = new ArrayList<FilterValuesVO>();
		Map<String, List<FilterValuesVO>> respuesta = new HashMap<String, List<FilterValuesVO>>();
		FilterValuesVO fvVO1 = new FilterValuesVO();
		FilterValuesVO fvVO2 = new FilterValuesVO();
		FilterValuesVO fvVO3 = new FilterValuesVO();
		FilterValuesVO fvVO4 = new FilterValuesVO();
		FilterValuesVO fvVO5 = new FilterValuesVO();
		FilterValuesVO fvVO6 = new FilterValuesVO();
		FilterValuesVO fvVO7 = new FilterValuesVO();
		FilterValuesVO fvVO8 = new FilterValuesVO();
		FilterValuesVO fvVO9 = new FilterValuesVO();
		FilterValuesVO fvVO10 = new FilterValuesVO();
		fvVO1.setCodigo(0);fvVO1.setCodigoString("N°. Infracción");
		listaParametros1.add(fvVO1);
		fvVO2.setCodigo(1);fvVO2.setCodigoString("Folio Documento");
		listaParametros1.add(fvVO2);
		fvVO3.setCodigo(2);fvVO3.setCodigoString("Placa Vehículo");
		listaParametros1.add(fvVO3);
		fvVO4.setCodigo(0);fvVO4.setCodigoString("Seleccione una Opción");
		listaParametros2.add(fvVO4);
		fvVO5.setCodigo(1);fvVO5.setCodigoString("Pago en Otras Entidades");
		listaParametros2.add(fvVO5);
		fvVO6.setCodigo(2);fvVO6.setCodigoString("Tarjeta de Crédito/Débito");
		listaParametros2.add(fvVO6);
		fvVO7.setCodigo(7);fvVO7.setCodigoString("TESORERIA");
		listaParametros3.add(fvVO7);
		fvVO8.setCodigo(6);fvVO8.setCodigoString("SETRAVI");
		listaParametros3.add(fvVO8);
		fvVO9.setCodigo(4);fvVO9.setCodigoString("INTERNET");
		listaParametros3.add(fvVO9);
		fvVO10.setCodigo(5);fvVO10.setCodigoString("BANCO");
		listaParametros3.add(fvVO10);
		
		respuesta.put("param1", listaParametros1);
		respuesta.put("param2", listaParametros2);
		respuesta.put("param3", listaParametros3);
		
		return new ResponseEntity<Map<String, List<FilterValuesVO>>>(respuesta, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/buscarGarantiasPorPagar", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('BUSCAR_GARANTIAS_POR_PAGAR')")
	public ResponseEntity<List<GarantiaPorPagarVO>> obtenerGarantiasPorPagar(
			@RequestParam(value = "param")String valor,
			@RequestParam(value = "tipo")  String tipo)throws NotFoundException{
		List<GarantiaPorPagarVO> listaGarantias = garantiaService.buscarGarantiasPorPagar(valor, tipo);
		if (listaGarantias == null || listaGarantias.isEmpty())
		{
			throw new NotFoundException("No se encontraron registros");
		}
		else
		{
			return new ResponseEntity<List<GarantiaPorPagarVO>>(listaGarantias, HttpStatus.OK);
		}
	}
	
	@RequestMapping(value = "/buscarDetalleGarantiaPorPagar", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('BUSCAR_DETALLE_GARANTIA_POR_PAGAR')")
	public ResponseEntity<List<GarantiaDetallePorPagarVO>> obtenerDetalleGarantiasPorPagar(
			@RequestParam(value = "param")String valor)throws NotFoundException{
		List<GarantiaDetallePorPagarVO> listaGarantias = garantiaService.buscarDetalleGarantiaPorPagar(valor);
		if (listaGarantias == null || listaGarantias.isEmpty())
		{
			throw new NotFoundException("No se encontraron registros");
		}
		else
		{
			return new ResponseEntity<List<GarantiaDetallePorPagarVO>>(listaGarantias, HttpStatus.OK);
		}
	}
	
	@RequestMapping(value = "/tokenMit", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('OBTENER_TOKEN_MIT_GARANTIA')")
	public String obtenerTokenMit() {
		return garantiaService.obtenerTokenMit();
	}
	
	
	@RequestMapping(value = "/pagarGarantiaTarjeta", method = RequestMethod.PUT)
	@PreAuthorize("hasAnyAuthority('PAGAR_GARANTIA_POR_TARJETA')")
	public ResponseEntity<PagoVO> pagarGarantiaPorTarjeta(@RequestBody DatosPagoVO datosPagoVO ) throws NotFoundException{
		PagoVO pagoVO = garantiaService.pagarGarantiaPorTarjeta(datosPagoVO);

 		if (  pagoVO .getResultado() ==null || pagoVO.getResultado().equals("-1") )
			throw new NotFoundException(pagoVO.getMensaje());
	
		return new ResponseEntity<PagoVO>(pagoVO, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/pagarGarantiaDocumento", method = RequestMethod.PUT)
	@PreAuthorize("hasAnyAuthority('PAGAR_GARANTIA_POR_DOCUMENTO')")
	public ResponseEntity<PagoVO> pagarGarantiaPorDocumento(@RequestBody DatosPagoVO datosPagoVO ) throws NotFoundException{
		PagoVO pagoVO = garantiaService.pagarGarantiaPorDocumento(datosPagoVO);

 		if (  pagoVO .getResultado() ==null || pagoVO.getResultado().equals("-1") )
			throw new NotFoundException(pagoVO.getMensaje());
	
		return new ResponseEntity<PagoVO>(pagoVO, HttpStatus.OK);
	}
	
	@Transactional
	@RequestMapping(value = "/garantiaInfo", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('BUSCAR_GARANTIAS_INFO')")
	public ResponseEntity<VSSPGarantiaRecepcionInfoFVO> getGarantia(@RequestParam(value = "garantiaId") Long garantiaId)
			throws NotFoundException {

		VSSPGarantiaRecepcionInfoFVO garantiaRecepcionInfo = new VSSPGarantiaRecepcionInfoFVO();
		garantiaRecepcionInfo = garantiaService.getGarantiaByIdInfo(garantiaId);
//		GarantiaDTO garantiaDTO = garantiaService.getGarantiaById(garantiaId);
//		ResponseConverter.copiarPropriedades(garantiaRecepcionInfo, garantiaDTO);

//		EmpleadosDTO empleadoDTO = empleadoService.getEmpleadoById(garantiaDTO.getEmpleadoDTO().getEmpId());
//		ResponseConverter.copiarPropriedades(garantiaRecepcionInfo, empleadoDTO);

		return new ResponseEntity<VSSPGarantiaRecepcionInfoFVO>(garantiaRecepcionInfo, HttpStatus.OK);

	}

	@Transactional
	@RequestMapping(value = "/recibirProcesar", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('GARANTIAS_RECEPCION')")
	public ResponseEntity<String> SaveGarantiaObserv(@RequestParam(value = "garantiaId") Long garantiaId,
			@RequestParam(value = "recibir") Boolean recibida,
			@RequestParam(value = "observaciones") String observaciones) throws NotFoundException {

		UsuarioFirmadoVO usuarioFirmadoVO = usuarioFirmadoService.getUsuarioFirmadoVO();
		EmpleadosDTO empFirmado = empleadoService.getEmpleadoById(usuarioFirmadoVO.getId());

		GarantiaDTO garantiaDTO = garantiaService.getGarantiaById(garantiaId);
		GarantiaProcesoDTO proceso = garantiaService.busquedaProceso();

		GarantiaEstatusProcesoDTO procesoEstatus = new GarantiaEstatusProcesoDTO();

		procesoEstatus.setCreadoPor(empFirmado.getEmpId());
		procesoEstatus.setFechaCreacion(new Date());
		procesoEstatus.setGarantiaId(garantiaDTO.getGarantiaId());
		procesoEstatus.setProcesoId(EnumGarantiasProceso.REVISADA.getProcesoID());
		procesoEstatus.setObservaciones(observaciones);
		procesoEstatus.setModificadoPor(empFirmado.getEmpId());
		procesoEstatus.setUltimaModificacion(new Date());
		garantiaEstatusProcesoDAO.save(procesoEstatus);

		garantiaDTO.setModificadoPor(empFirmado.getEmpId());
		garantiaDTO.setGarantiaProcesoDTO(proceso);
		garantiaDTO.setRecibida(recibida);
		garantiaDTO.setUltimaModificacion(new Date());
		garantiaDAO.update(garantiaDTO);

		return new ResponseEntity<String>("true", HttpStatus.OK);

	}

	@RequestMapping(value = "/generarReporteGarantiaRecepcion", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('GENERAR_GARANTIAS_RECEPCION_PDF')")
	public ResponseEntity<byte[]> generarPDF(@RequestParam(value = "garantiaID") Long garantiaId)
			throws NotFoundException, IOException {
		
		UsuarioFirmadoVO usuarioFirmadoVO = usuarioFirmadoService.getUsuarioFirmadoVO();
		EmpleadosDTO empFirmado = empleadoService.getEmpleadoById(usuarioFirmadoVO.getId());

		String filename = "reporteGarantiaRecibe.pdf";
		String rutaArchivo = "WEB-INF/jasper/garantias/reporteGarantiaRecibe.jasper";
		String rutaImagen = "WEB-INF/imagenes/nuevo_logo_SSC.jpg";
		
		
       		
		ByteArrayOutputStream reporte = garantiaService.generaReporteGarantiaRecibe(garantiaId, empFirmado, rutaArchivo,
				rutaImagen);
		final byte[] bytes = reporte.toByteArray();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.parseMediaType("application/pdf"));
		headers.add("Content-Disposition", "attachmnt; filename =" + filename);
		headers.add("filename", filename);
		headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
		headers.setContentLength(bytes.length);

		ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(bytes, headers, HttpStatus.OK);
		
		
		return response;

	}

	@Transactional(readOnly = true)
	@RequestMapping(value = "/buscarPorOpcionGarantiaEntrega", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('GARANTIAS_ENTREGA_CONSULTA')")
	public ResponseEntity<List<VSSPGarantiaConsultaEntregaFVO>> buscarPorOpcionGarantiaEntrega(
			@RequestParam(value = "dato") String dato, @RequestParam(value = "opcionBusqueda") Integer opcionBusqueda) {

		List<VSSPGarantiaConsultaEntregaFVO> garantiaEntregaConsulta = new ArrayList<VSSPGarantiaConsultaEntregaFVO>();

		garantiaEntregaConsulta = garantiaService.buscarGarantiaEntrega(dato, opcionBusqueda);

		return new ResponseEntity<List<VSSPGarantiaConsultaEntregaFVO>>(garantiaEntregaConsulta, HttpStatus.OK);

	}

	@Transactional
	@RequestMapping(value = "/guardarGarantiasEntrega", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('GUARDAR_GARANTIAS_ENTREGA')")

	public ResponseEntity<String> guardarGarantiasEntrega(@RequestParam(value = "garantiaId") Long garantiaId,
			@RequestParam(value = "observacion") String observacion) {

		UsuarioFirmadoVO usuarioFirmadoVO = usuarioFirmadoService.getUsuarioFirmadoVO();
		EmpleadosDTO empFirmado = empleadoService.getEmpleadoById(usuarioFirmadoVO.getId());

		return garantiaService.guardarGarantiaEntrega(garantiaId, empFirmado, observacion);

	}

	@RequestMapping(value = "/generarReporteGarantiaEntrega", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('GENERAR_GARANTIAS_ENTREGA_PDF')")
	public ResponseEntity<byte[]> generarPDFEntrega(@RequestParam(value = "garantiaID") Long garantiaId)
			throws NotFoundException, FileNotFoundException {

		String filename = "reporteGarantiasEntrega.pdf";
		String rutaArchivo = context.getRealPath("/WEB-INF/jasper/garantias/reporteGarantiaEntrega.jasper");
		String rutaImagen = context.getRealPath("/WEB-INF/imagenes/nuevo_logo_SSC.jpg");

		ByteArrayOutputStream reporte = garantiaService.generaReporteGarantiaEntrega(garantiaId, rutaArchivo,
				rutaImagen);
		final byte[] bytes = reporte.toByteArray();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.parseMediaType("application/pdf"));
		headers.add("Content-Disposition", "attachmnt; filename =" + filename);
		headers.add("filename", filename);
		headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
		headers.setContentLength(bytes.length);

		ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(bytes, headers, HttpStatus.OK);

		return response;

	}

	@Transactional(readOnly = true)
	@RequestMapping(value = "/consultaDeGarantias", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('CONSULTA_GARANTIAS_PARAMETRO')")
	public ResponseEntity<List<VSSPGarantiaConsultaFVO>> consultaDeGarantias(
			@RequestParam(value = "estatusParametro") Integer estatusParametro,
			@RequestParam(value = "txtParametro") String txtParametro) {

		List<VSSPGarantiaConsultaFVO> listaConsultaGarantia = new ArrayList<VSSPGarantiaConsultaFVO>();

		if (estatusParametro != 0) {
			List<GarantiaDTO> garantiaDTO = garantiaDAO.busquedaGarantiasGenerales(estatusParametro, txtParametro);
//			listaConsultaGarantia = ResponseConverter.converterLista(new ArrayList<>(), garantiaDTO,
//					VSSPGarantiaConsultaFVO.class);
			listaConsultaGarantia = getGarantiaConsulta(garantiaDTO);

			for (int i = 0; i < listaConsultaGarantia.size(); i++) {
				listaConsultaGarantia.get(i).setDeposito(garantiasInfraccionesDAO.buscaDepositoProInfraccionGarantia(listaConsultaGarantia.get(i).getInfraccNum()));
			}

			return new ResponseEntity<List<VSSPGarantiaConsultaFVO>>(listaConsultaGarantia, HttpStatus.OK);
		}

		return new ResponseEntity<List<VSSPGarantiaConsultaFVO>>(listaConsultaGarantia, HttpStatus.OK);

	}

	@Transactional(readOnly = true)
	@RequestMapping(value = "/consultaDeGarantiasOp", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('CONSULTA_GARANTIAS_FECHA')")
	public ResponseEntity<List<VSSPGarantiaConsultaFVO>> consultaDeGarantiasop(
			@RequestParam(value = "tipoFecha") Integer tipoFecha,
			@RequestParam(value = "tipoProceso") Integer tipoProceso,
			@RequestParam(value = "fechaInicio") String fechaInicio,
			@RequestParam(value = "fechaFin") String fechaFin) {

		List<VSSPGarantiaConsultaFVO> listaConsultaGarantia = new ArrayList<VSSPGarantiaConsultaFVO>();
		Date fInicio = garantiaService.RutinaInicio(fechaInicio);
		Date fFin = garantiaService.RutinaFin(fechaFin);

		if (tipoFecha != 0) {

			List<GarantiaEstatusProcesoDTO> garantiaEstatusProcesoDTO = garantiaEstatusProcesoDAO
					.buscaGarantiaPorEstatusYFechaCreacion(tipoFecha, tipoProceso, fInicio, fFin);
//			listaConsultaGarantia = ResponseConverter.converterLista(new ArrayList<>(), garantiaEstatusProcesoDTO,
//					VSSPGarantiaConsultaFVO.class);
			
			listaConsultaGarantia = convertirDTOtoVO(garantiaEstatusProcesoDTO);

			for (int x = 0; x < listaConsultaGarantia.size(); x++) {
				listaConsultaGarantia.get(x).setDeposito(garantiasInfraccionesDAO.buscaDepositoProInfraccionGarantia(listaConsultaGarantia.get(x).getInfraccNum()));
				

			}
			

			return new ResponseEntity<List<VSSPGarantiaConsultaFVO>>(listaConsultaGarantia, HttpStatus.OK);
		}

		return new ResponseEntity<List<VSSPGarantiaConsultaFVO>>(listaConsultaGarantia, HttpStatus.OK);

	}

	private List<VSSPGarantiaConsultaFVO> convertirDTOtoVO(List<GarantiaEstatusProcesoDTO> garantiaEstatusProcesoDTO) {
		List<VSSPGarantiaConsultaFVO> listaGarantiasVO = new ArrayList<VSSPGarantiaConsultaFVO>();
//		RutinasTiempoImpl rutinasTiempoImpl = new RutinasTiempoImpl();
		RutinasTiempoImpl rutinasTiempoImpl = new RutinasTiempoImpl();
		for(GarantiaEstatusProcesoDTO GarantiaEstatusProcesoDTO : garantiaEstatusProcesoDTO){
			VSSPGarantiaConsultaFVO garantiaConsultaVO = new VSSPGarantiaConsultaFVO();
			
			if(GarantiaEstatusProcesoDTO.getGarantia() != null){
				if(GarantiaEstatusProcesoDTO.getGarantiaId() != null){
					garantiaConsultaVO.setGarantiaId(GarantiaEstatusProcesoDTO.getGarantiaId());
				}
			
				if(GarantiaEstatusProcesoDTO.getGarantia().getInfraccionDTO() != null) {
					garantiaConsultaVO.setInfraccNum(GarantiaEstatusProcesoDTO.getGarantia().getInfraccionDTO().getInfraccNum());
					garantiaConsultaVO.setFechaInfraccion(rutinasTiempoImpl.getStringDateFromFormta("dd/MM/yyyy", GarantiaEstatusProcesoDTO.getGarantia().getInfraccionDTO().getInfracMFechaHora()));
				}
				if(GarantiaEstatusProcesoDTO.getGarantia().getGarantiaDocumentoDTO() != null) {
					garantiaConsultaVO.setNombreDocumento(GarantiaEstatusProcesoDTO.getGarantia().getGarantiaDocumentoDTO().getNombre());
				}
				if(GarantiaEstatusProcesoDTO.getGarantia().getDocumentoFolio() != null) {
					garantiaConsultaVO.setDocumentoFolio(GarantiaEstatusProcesoDTO.getGarantia().getDocumentoFolio());
				}
				if(GarantiaEstatusProcesoDTO.getGarantia().getGarantiaProcesoDTO() != null) {
					garantiaConsultaVO.setNombreProceso(GarantiaEstatusProcesoDTO.getGarantia().getGarantiaProcesoDTO().getNombre());
				}
				if(GarantiaEstatusProcesoDTO.getGarantia().getRecibida() != null) {
					garantiaConsultaVO.setRecibida(GarantiaEstatusProcesoDTO.getGarantia().getRecibida() ? "SI" : "NO");
				}
				if(GarantiaEstatusProcesoDTO.getGarantia().getEntregada() != null) {
					garantiaConsultaVO.setEntregada(GarantiaEstatusProcesoDTO.getGarantia().getEntregada() ? "SI" : "NO");
				}
				if(GarantiaEstatusProcesoDTO.getGarantia().getPagada() != null) {
					garantiaConsultaVO.setPagada(GarantiaEstatusProcesoDTO.getGarantia().getPagada() ? "SI" : "NO");
				}
				if(GarantiaEstatusProcesoDTO.getGarantia().getTipoPago() != null){
					garantiaConsultaVO.setTipoPago(GarantiaEstatusProcesoDTO.getGarantia().getTipoPago());
				}
				
				if(GarantiaEstatusProcesoDTO.getGarantia().getIdLote() != null){
					garantiaConsultaVO.setIdLote(GarantiaEstatusProcesoDTO.getGarantia().getIdLote());
					
				}
			}
			
			listaGarantiasVO.add(garantiaConsultaVO);
		}
		
		return listaGarantiasVO;
		
	}
	
	public List<VSSPGarantiaConsultaFVO> getGarantiaConsulta(List<GarantiaDTO> listaGarantiasDTO){
		List<VSSPGarantiaConsultaFVO> listaGarantiasVO = new ArrayList<VSSPGarantiaConsultaFVO>();
		RutinasTiempoImpl rutinasTiempoImpl = new RutinasTiempoImpl();
		for(GarantiaDTO garantiaDTO : listaGarantiasDTO){
			VSSPGarantiaConsultaFVO garantiaConsultaVO = new VSSPGarantiaConsultaFVO();
			
			if(garantiaDTO.getGarantiaId() != null){
				garantiaConsultaVO.setGarantiaId(garantiaDTO.getGarantiaId());
			}
			if(garantiaDTO.getInfraccionDTO() != null) {
				garantiaConsultaVO.setInfraccNum(garantiaDTO.getInfraccionDTO().getInfraccNum());
				garantiaConsultaVO.setFechaInfraccion(rutinasTiempoImpl.getStringDateFromFormta("dd/MM/yyyy", garantiaDTO.getInfraccionDTO().getInfracMFechaHora()));
			}
			if(garantiaDTO.getGarantiaDocumentoDTO() != null) {
				garantiaConsultaVO.setNombreDocumento(garantiaDTO.getGarantiaDocumentoDTO().getNombre());
			}
			if(garantiaDTO.getDocumentoFolio() != null) {
				garantiaConsultaVO.setDocumentoFolio(garantiaDTO.getDocumentoFolio());
			}
			if(garantiaDTO.getGarantiaProcesoDTO() != null) {
				garantiaConsultaVO.setNombreProceso(garantiaDTO.getGarantiaProcesoDTO().getNombre());
			}
			if(garantiaDTO.getRecibida() != null) {
				garantiaConsultaVO.setRecibida(garantiaDTO.getRecibida() ? "SI" : "NO");
			}
			if(garantiaDTO.getEntregada() != null) {
				garantiaConsultaVO.setEntregada(garantiaDTO.getEntregada() ? "SI" : "NO");
			}
			if(garantiaDTO.getPagada() != null) {
				garantiaConsultaVO.setPagada(garantiaDTO.getPagada() ? "SI" : "NO");
			}
			if(garantiaDTO.getTipoPago() != null){
				garantiaConsultaVO.setTipoPago(garantiaDTO.getTipoPago());
			}
			if(garantiaDTO.getIdLote() != null){
				garantiaConsultaVO.setIdLote(garantiaDTO.getIdLote());
				
			}
						
			listaGarantiasVO.add(garantiaConsultaVO);
		}
		
		return listaGarantiasVO;
	}
	
	@Transactional(readOnly = true)
	@RequestMapping(value = "/buscarEstatusProceso", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('BUSCAR_GARANTIA_PROCESO_ESTATUS')")
	public ResponseEntity<List<VSSPGarantiaCatProcesoFVO>> buscarEstatusProceso() {

		List<GarantiaProcesoDTO> garantiaProcesoDTO = garantiaService.obtenerGarantiaProceso();
		List<VSSPGarantiaCatProcesoFVO> listaGarantiaCatProceso = new ArrayList<VSSPGarantiaCatProcesoFVO>();
		listaGarantiaCatProceso = ResponseConverter.converterLista(new ArrayList<>(), garantiaProcesoDTO,
				VSSPGarantiaCatProcesoFVO.class);

		return new ResponseEntity<List<VSSPGarantiaCatProcesoFVO>>(listaGarantiaCatProceso, HttpStatus.OK);

	}

	
	@RequestMapping(value = "/generarReporteVaucher", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('GENERAR_GARANTIAS_VOUCHER_PDF')")
	public ResponseEntity<byte[]> generarPDFVaucher(@RequestParam(value = "infracNum") String infracNum,
			@RequestParam(value = "tipoReporte") String tipoReporte) throws NotFoundException, FileNotFoundException {
		ResponseEntity<byte[]> response = null;
		ByteArrayOutputStream reporte = null;
		byte[] bytes = null;
		HttpHeaders headers = new HttpHeaders();
		String nombreReporte = "";

		if (tipoReporte.equals(ACCION_REPORTE_VOUCHER)) {

			reporte = garantiaService.imprimeReporteVoucher(infracNum);
			if (reporte != null) {
				if (reporte.size() > 0) {
				bytes = reporte.toByteArray();
				nombreReporte = "VoucherPago-" + infracNum + ".pdf";
					headers.setContentType(MediaType.parseMediaType("application/pdf"));
					headers.add("Content-Disposition", "attachmnt; filename =" + nombreReporte);
					headers.add("filename", nombreReporte);
					headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
					headers.setContentLength(bytes.length);
					response = new ResponseEntity<byte[]>(bytes, headers, HttpStatus.OK);
				}
			} else {
				throw new NotFoundException("No se pudo generar reporte");
			}
		} else {
			if (tipoReporte.equals(ACCION_REPORTE_RECIBO)) {

				reporte = acusePagoReciboService.generarAcusePago(infracNum);
				nombreReporte = "ReciboPago-" + infracNum + ".pdf";
				bytes = reporte.toByteArray();

			}
			if(tipoReporte.equals(ACCION_REPORTE_VOUCHER_CANCELACION)){
				String[] parametros=infracNum.split("\\|");
				String tranId=parametros[0];
				String numOperacion=parametros[1];
				reporte = garantiaService.imprimirVoucherCancelacion(tranId,numOperacion);//enrealidad recibe el numero de operacion y no numero infraccion
				nombreReporte = "VoucherPago-" + numOperacion + ".pdf";
				bytes = reporte.toByteArray();
			}
			if(tipoReporte.equals(ACCION_REPORTE_VOUCHER_CENTROPAGOS)){
				reporte = garantiaService.imprimirVoucherCentroPagos(infracNum);//enrealidad recibe el numero de operacion y no numero infraccion
				nombreReporte = "VoucherPago-" + infracNum + ".pdf";
				bytes = reporte.toByteArray();
			}
			if (reporte != null) {
				if (reporte.size() > 0) {

					headers.setContentType(MediaType.parseMediaType("application/pdf"));
					headers.add("Content-Disposition", "attachmnt; filename =" + nombreReporte);
					headers.add("filename", nombreReporte);
					headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
					headers.setContentLength(bytes.length);
					response = new ResponseEntity<byte[]>(bytes, headers, HttpStatus.OK);

				}

			} else {
				throw new NotFoundException("El reporte esta vacío");
			}
		}
		return response;

	}

	@RequestMapping(value = "/consultarReporteGeneral", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('CONSULTAR_GARANTIAS_REPORTES')")
		public ResponseEntity<List<VSSPGarantiaReporteGeneralFVO>> consultaGarantiaReportes(
			@RequestParam(value = "fechaInicio") String fechaInicio, @RequestParam(value = "fechaFin") String fechaFin)
			throws NotFoundException {
		List<VSSPGarantiaReporteGeneralFVO> garantiaReporteGeneralVO = null;
		try {	
			garantiaReporteGeneralVO = garantiaService.consultaReporteGral(fechaInicio,
				fechaFin);
		}catch (Exception e) {
			throw new NotFoundException("Ha ocurrido un imprevisto! , porfavor contacte al administrador");
		}
		if (garantiaReporteGeneralVO.isEmpty()) {
				throw new NotFoundException("No se encontraron registros");
		}
		return new ResponseEntity<List<VSSPGarantiaReporteGeneralFVO>>(garantiaReporteGeneralVO, HttpStatus.OK);
	}

	@RequestMapping(value = "/recuperarArchivo", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('GENERAR_GARANTIAS_REPORTES_EXCEL')")
	public ResponseEntity<byte[]> generarExcelReporte(
			@RequestParam(value = "fechaInicio") String fechaInicio, @RequestParam(value = "fechaFin") String fechaFin)
			throws NotFoundException, IOException {
			String NombreReporte = "GarantiasRep" + fechaInicio.replaceAll("/", "") + "_" + fechaFin.replaceAll("/", "");
			List<VSSPGarantiaReporteGeneralFVO> garantiaReporteGeneralVO = garantiaService.consultaReporteGral(fechaInicio,
				fechaFin);
			ByteArrayOutputStream reporteExcel = garantiaService.generaReporteExcel(garantiaReporteGeneralVO, fechaInicio,
				fechaFin);
			final byte[] bytes = reporteExcel.toByteArray();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.parseMediaType("application/vnd.ms-exce"));
		headers.add("Content-Disposition", "attachmnt; filename =" + NombreReporte);
		headers.add("filename", NombreReporte);
		headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
		headers.setContentLength(bytes.length);

		ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(bytes, headers, HttpStatus.OK);

		return response;

	}

	@RequestMapping(value = "/garantias/generarExcel", method = RequestMethod.GET)	
    public ResponseEntity<byte[]> generarReporteExcelGarantiasRecepcion(
    		@RequestParam(value= "empPlaca") String empPlaca,
    		@RequestParam(value = "op") Boolean op) 
    		throws NotFoundException, IOException {
    	List<VSSPGarantiaConsGralFVO> garantias = null;
    	String NombreReporte = "Garantias Recepción " + empPlaca;
		garantias = garantiaService.buscarGarantiasSinProcesar(empPlaca, op);
		
    	ByteArrayOutputStream excelGarRecepcion =  garantiaService.generarReporteExcelGarantiasRecepcion(garantias);
    	final byte[] bytes = excelGarRecepcion.toByteArray();
    	
    	HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.parseMediaType("application/vnd.ms-exce"));
		headers.add("Content-Disposition", "attachmnt; filename =" + NombreReporte);
		headers.add("filename", NombreReporte);
		headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
		headers.setContentLength(bytes.length);

		ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(bytes, headers, HttpStatus.OK);
    	
    	return response;
    }
	
    @RequestMapping(value = "/garantias/generarExcelEntrega", method = RequestMethod.GET)
    @PreAuthorize("hasAnyAuthority('GARANTIAS_ENTREGA_CONSULTA')")
    public ResponseEntity<byte[]> generarReporteExcelGarantiasEntrega(
    		@RequestParam(value = "dato") String dato, @RequestParam(value = "opcionBusqueda") Integer opcionBusqueda)  
    		throws NotFoundException, IOException {
    	List<VSSPGarantiaConsultaEntregaFVO> garantiaEntregaConsulta = new ArrayList<VSSPGarantiaConsultaEntregaFVO>();
    	RutinasTiempoImpl rutinasTiempoImpl = new RutinasTiempoImpl();
		garantiaEntregaConsulta = garantiaService.buscarGarantiaEntrega(dato, opcionBusqueda);

    	ByteArrayOutputStream excelGarRecepcion =  garantiaService.generarReporteExcelGarantiasEntrega(garantiaEntregaConsulta, true);
    	final byte[] bytes = excelGarRecepcion.toByteArray();
    	
    	String NombreReporte = "Garantias Recepción " + rutinasTiempoImpl.getStringDateFromFormta("dd/MM/yyyy HH:mm", new Date());
    	HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.parseMediaType("application/vnd.ms-exce"));
		headers.add("Content-Disposition", "attachmnt; filename =" + NombreReporte);
		headers.add("filename", NombreReporte);
		headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
		headers.setContentLength(bytes.length);

		ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(bytes, headers, HttpStatus.OK);
    	
    	return response;
    }
    
    @Transactional(readOnly = true)
	@RequestMapping(value = "garantias/generarExcelConsultaB", method = RequestMethod.GET)
    @PreAuthorize("hasAnyAuthority('CONSULTA_GARANTIAS_FECHA')")
    public ResponseEntity<byte[]>  generarExcelConsultaB(
			@RequestParam(value = "tipoFecha") Integer tipoFecha,
			@RequestParam(value = "tipoProceso") Integer tipoProceso,
			@RequestParam(value = "fechaInicio") String fechaInicio,
			@RequestParam(value = "fechaFin") String fechaFin) {
		List<VSSPGarantiaConsultaFVO> listaConsultaGarantia = new ArrayList<VSSPGarantiaConsultaFVO>();
		Date fInicio = garantiaService.RutinaInicio(fechaInicio);
		Date fFin = garantiaService.RutinaFin(fechaFin);
		RutinasTiempoImpl rutinasTiempoImpl = new RutinasTiempoImpl();
	
		List<GarantiaEstatusProcesoDTO> garantiaEstatusProcesoDTO = garantiaEstatusProcesoDAO
				.buscaGarantiaPorEstatusYFechaCreacion(tipoFecha, tipoProceso, fInicio, fFin);
		listaConsultaGarantia = convertirDTOtoVO(garantiaEstatusProcesoDTO);
		for (int x = 0; x < listaConsultaGarantia.size(); x++) {
			listaConsultaGarantia.get(x).setDeposito(garantiasInfraccionesDAO.buscaDepositoProInfraccionGarantia(listaConsultaGarantia.get(x).getInfraccNum()));

		}
    	ByteArrayOutputStream excelConsultaB =  garantiaService.generarReporteExcelConsulta(listaConsultaGarantia);
    	final byte[] bytes = excelConsultaB.toByteArray();
    	String NombreReporte = "Garantias Consulta " + rutinasTiempoImpl.getStringDateFromFormta("dd/MM/yyyy HH:mm", new Date());
    	HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.parseMediaType("application/vnd.ms-exce"));
		headers.add("Content-Disposition", "attachmnt; filename =" + NombreReporte);
		headers.add("filename", NombreReporte);
		headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
		headers.setContentLength(bytes.length);

		ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(bytes, headers, HttpStatus.OK);
    	
    	return response;
	}
    
    @Transactional(readOnly = true)
	@RequestMapping(value = "/garantias/generarExcelConsultaA", method = RequestMethod.GET)
    @PreAuthorize("hasAnyAuthority('CONSULTA_GARANTIAS_PARAMETRO')")
    public ResponseEntity<byte[]>  generarExcelConsultaA(
    		@RequestParam(value = "estatusParametro") Integer estatusParametro,
			@RequestParam(value = "txtParametro") String txtParametro) {
    	List<VSSPGarantiaConsultaFVO> listaConsultaGarantia = new ArrayList<VSSPGarantiaConsultaFVO>();
    	RutinasTiempoImpl rutinasTiempoImpl = new RutinasTiempoImpl();
		List<GarantiaDTO> garantiaDTO = garantiaDAO.busquedaGarantiasGenerales(estatusParametro, txtParametro);
		listaConsultaGarantia = getGarantiaConsulta(garantiaDTO);

		for (int i = 0; i < listaConsultaGarantia.size(); i++) {
			listaConsultaGarantia.get(i).setDeposito(garantiasInfraccionesDAO.buscaDepositoProInfraccionGarantia(listaConsultaGarantia.get(i).getInfraccNum()));
		}
		
    	ByteArrayOutputStream excelConsultaB =  garantiaService.generarReporteExcelConsulta(listaConsultaGarantia);
    	final byte[] bytes = excelConsultaB.toByteArray();
    	String NombreReporte = "Garantias Consulta " + rutinasTiempoImpl.getStringDateFromFormta("dd/MM/yyyy HH:mm", new Date());
    	HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.parseMediaType("application/vnd.ms-exce"));
		headers.add("Content-Disposition", "attachmnt; filename =" + NombreReporte);
		headers.add("filename", NombreReporte);
		headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
		headers.setContentLength(bytes.length);

		ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(bytes, headers, HttpStatus.OK);
    	
    	return response;
	}
    /*
     * 
     * 
     */

	@RequestMapping(value = "/tipoFechasForGarantiasMasivas", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('RECIBIR_GARANTIA_MASIVA_FECH')")
	public ResponseEntity<List<TipoFechaVO>> buscarTipoFechas() throws NotFoundException {

		List<TipoFechasDTO> listTipoFechasDTO = catalogoService.tipoFechas();
		List<TipoFechaVO> listaTiposFechasVO = ResponseConverter.converterLista(new ArrayList<>(), listTipoFechasDTO,
				TipoFechaVO.class);
		return new ResponseEntity<List<TipoFechaVO>>(listaTiposFechasVO, HttpStatus.OK);

	}

	@SuppressWarnings("unused")
	@RequestMapping(value = "/garantiasRecibirMasiva", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('GARANTIAS_RECEPCION_MASIVA')")
	public ResponseEntity<List<VSSPGarantiaConsGralFVO>> buscarGarantiasRecepcionMasiva(
			@RequestParam(value = "valor") String valor, @RequestParam(value = "op") Boolean op,
			@RequestParam(value = "idTipoFecha", required=false) Integer idTipoFecha) throws NotFoundException {

		crearGarantia(valor);
			
			List<VSSPGarantiaConsGralFVO> garantiasMasiva = new ArrayList<VSSPGarantiaConsGralFVO>();
				garantiasMasiva = garantiaMasivaService.buscarGarantiasMasivaSinProcesar(valor, op, idTipoFecha);
				if (garantiasMasiva == null) {
					
					throw new NotFoundException("No se encontraron registros");
				}
				
				return new ResponseEntity<List<VSSPGarantiaConsGralFVO>>(garantiasMasiva, HttpStatus.OK);

	}
	
	/**
	 * @param placaOficial
	 * @param op 
	 * @param startDate fecha inicial
	 * @param endDate fecha final
	 * @return List<VSSPGarantiaConsGralFVO>
	 * @throws NotFoundException
	 * @author VAPD1226
	 */
	@RequestMapping(value="/garantiaMasivaFech", method=RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('RECIBIR_GARANTIA_MASIVA_FECH')")
	public ResponseEntity<List<VSSPGarantiaConsGralFVO>> buscarGarantiasRecepFechMasiva(
			@RequestParam(value="placaOficial") String placaOficial,
			@RequestParam(value="op") Boolean op,
			@RequestParam(value="startDate") String startDate,
			@RequestParam(value="endDate") String endDate) throws NotFoundException{
		
		crearGarantia(placaOficial);
		List<VSSPGarantiaConsGralFVO> garantiaMasivaFech = new ArrayList<VSSPGarantiaConsGralFVO>();
		
		garantiaMasivaFech = garantiaMasivaService.buscarGarantiasMasivaFech(placaOficial, op, startDate, endDate);
			if(garantiaMasivaFech ==null){
					throw new NotFoundException("No se encontraron Registros");
			}
		return new ResponseEntity<List<VSSPGarantiaConsGralFVO>>(garantiaMasivaFech, HttpStatus.OK);
	}

	/***
	 * 
	 * @param garantiaIdMasivasVO Objeto que trae la lista de garantias a recibir con sus respectivas observaciones
	 * @return GarantiaIdMasivasVO
	 * @throws NotFoundException
	 * @author VAPD1226
	 */
	@Transactional(readOnly = true)
	@RequestMapping(value = "/recibirProcesarMasivas", method = RequestMethod.POST)
	@PreAuthorize("hasAnyAuthority('GARANTIAS_RECEPCION')")
	public ResponseEntity<GarantiaIdMasivasVO> SaveGarantiaMasiva(@RequestBody GarantiaIdMasivasVO garantiaIdMasivasVO) throws NotFoundException {
	
		/*coloca el proceso 1*/
		GarantiaProcesoDTO proceso = garantiaService.busquedaProceso();
		
		GarantiaIdMasivasVO garantiaIdMasivasVO1 = garantiaMasivaService.recepcionMasivaGarantias(garantiaIdMasivasVO, proceso);
		return new ResponseEntity<GarantiaIdMasivasVO>(garantiaIdMasivasVO1, HttpStatus.OK);
	}
	
	/***
	 * 
	 * @param idGarantias identificador de las garantias recibidas
	 * @param idLote id del lote que se genero al recibir de manera masiva las garantias
	 * @return ResponseEntity<byte[]>
	 * @throws NotFoundException
	 * @throws IOException
	 * @author VAPD1226
	 */
	
	@RequestMapping(value = "/generarReporteGarantiaRecepcionMasiva", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('GENERAR_GARANTIAS_RECEPCION_PDF')")
	public ResponseEntity<byte[]> generarPDFMAsivass(@RequestParam("idGarantiaInd") long idGarantiaInd, @RequestParam("totalGarMasiva")int totalGarMasiva, @RequestParam("iDLote") Integer idLote)
			throws NotFoundException, IOException {
		
		
		int banderaJasper;
		String rutaArchivo="";
		String rutaArchivo2="";
		UsuarioFirmadoVO usuarioFirmadoVO = usuarioFirmadoService.getUsuarioFirmadoVO();
		EmpleadosDTO empFirmado = empleadoService.getEmpleadoById(usuarioFirmadoVO.getId());

		String filename = "reporteGarantiaMasivaRecibe.pdf";
		
		if(totalGarMasiva==1){
			rutaArchivo = "WEB-INF/jasper/garantias/reporteGarantiaRecibe.jasper";
			banderaJasper = 0;
		}
		else if(totalGarMasiva<=10 && totalGarMasiva>1){
			rutaArchivo = "WEB-INF/jasper/garantias/reportRecepcionMenor.jasper";
			banderaJasper = 1;
		}
		else if(totalGarMasiva>10 && totalGarMasiva<=20){
			rutaArchivo = "WEB-INF/jasper/garantias/reportRecepcionMG2.jasper";
			banderaJasper = 2;
		}
		else if (totalGarMasiva>20 && totalGarMasiva<=55){
			rutaArchivo = "WEB-INF/jasper/garantias/reportRecepcionTopMax.jasper";
			rutaArchivo2="WEB-INF/jasper/garantias/reportRecepcionTopMaxSub.jasper";
			banderaJasper = 4;
		}
		else{
			rutaArchivo="WEB-INF/jasper/garantias/reportRecepcionMayor.jasper";
			rutaArchivo2="WEB-INF/jasper/garantias/reportRecepcionMayorSub.jasper";
			banderaJasper=3;
		}

		String rutaImagen = "WEB-INF/imagenes/nuevo_logo_SSC.jpg";
		
		if(banderaJasper==3 || banderaJasper==4){
			
			Map<String, String> parametros = garantiaService.getParametrosLP();
			
			String ruta =parametros.get("RUTAR_GUARDAR_ARCH_PDF_TEMP");
			File directorio = new File(ruta);
			
			if(!directorio.exists()){
				directorio.mkdir();
			}

			ByteArrayOutputStream reporte = garantiaService.generaReporteGarantiaRecibeMasiva(idGarantiaInd, empFirmado, rutaArchivo,
					rutaImagen, banderaJasper, idLote);
			
			ByteArrayOutputStream reporte2 = garantiaService.generaReporteGarantiaRecibeMasiva2(idGarantiaInd, empFirmado, rutaArchivo2,
					rutaImagen, banderaJasper, idLote);
			
			
			byte[] one =  reporte.toByteArray();
			byte[] two = reporte2.toByteArray();
			byte[] combined = unirDosPdfsByte(one, two, ruta);
			
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.parseMediaType("application/pdf"));
			headers.add("Content-Disposition", "attachmnt; filename =" + filename);
			headers.add("filename", filename);
			headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
			headers.setContentLength(combined.length);
			
			ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(combined, headers, HttpStatus.OK);
			return response;
			
		}
		else{
			ByteArrayOutputStream reporte = garantiaService.generaReporteGarantiaRecibeMasiva(idGarantiaInd, empFirmado, rutaArchivo,
					rutaImagen, banderaJasper, idLote);

			byte[] one =  reporte.toByteArray();
			
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.parseMediaType("application/pdf"));
			headers.add("Content-Disposition", "attachmnt; filename =" + filename);
			headers.add("filename", filename);
			headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
			headers.setContentLength(one.length);
			
			ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(one, headers, HttpStatus.OK);
			
			return response;
		}
	}
	
	/***
	 * 
	 * @param idLote
	 * @return List<VSSPGarantiaConsultaFVO>
	 * @author VAPD1226
	 */
	@Transactional
	@RequestMapping(value = "/consultaListaGarantiaLotes", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('CONSULTA_GARANTIAS_PARAMETRO')")
	public ResponseEntity<List<VSSPGarantiaConsultaFVO>> consultaListaGarantiaLotes(
			@RequestParam(value = "idlote") Long idLote) {

		List<VSSPGarantiaConsultaFVO> listaConsultaGarantia = new ArrayList<VSSPGarantiaConsultaFVO>();

			List<GarantiaDTO> garantiaDTO = garantiaDAO.buscarListGarantLote(idLote);
			listaConsultaGarantia = getGarantiaConsulta(garantiaDTO);
		return new ResponseEntity<List<VSSPGarantiaConsultaFVO>>(listaConsultaGarantia, HttpStatus.OK);

	}
	
	/***
	 * 
	 * @param archivo1 primer PDF
	 * @param archivo2 segundo PDF
	 * @param ruta
	 * @return byteArray
	 * @author VAPD1226
	 */
	
	public byte[] unirDosPdfsByte(byte[] archivo1, byte[] archivo2, String ruta){
		String archivoTemporal=ruta+"concatenatedPDFByte.pdf";
		byte[] archivoPDFconcat=null;
		try {
		PdfReader reader1 = new PdfReader(archivo1);
		PdfReader reader2 = new PdfReader(archivo2);
		PdfCopyFields copy = 
		new PdfCopyFields(new FileOutputStream(archivoTemporal));
		copy.addDocument(reader1);
		copy.addDocument(reader2);
		copy.close();
		archivoPDFconcat=convertDocToByteArray(archivoTemporal);
		new File(archivoTemporal).delete();
		} catch (Exception e) {
		System.out.println(e);
		}
		return archivoPDFconcat;
		}

		public byte[] convertDocToByteArray(String sourcePath) {
		byte[] byteArray = null;
		 
		File file = null;
		try {
		file = new File(sourcePath);
		byteArray = new byte[(int) file.length()];
		FileInputStream fis=new FileInputStream(file);
		fis.read(byteArray);
		fis.close();
		} catch (FileNotFoundException e) {
		System.out.println("File Not found" + e);
		} catch (IOException e) {
		System.out.println("IO Ex" + e);
		}
		return byteArray;
		}
		
		//JLGD
		@RequestMapping(value = "/garantias/generarExcelMasiva", method = RequestMethod.GET)	
	    public ResponseEntity<byte[]> generarReporteExcelGarantiasRecepcionM(
	    		@RequestParam(value= "reportePlacaOficial") String empPlaca,
	    		@RequestParam(value = "reporteConPromesaPago") Boolean op,
	    		@RequestParam(value = "reporteTipoFecha") Integer idTipoFecha,
				@RequestParam(value = "reporteFechaIni") String fechaInicio,
				@RequestParam(value = "reporteFechaFin") String fechaFin
	    		) 
	    		throws NotFoundException, IOException {
	    	List<VSSPGarantiaConsGralFVO> garantias = null;
	    	
	    	String NombreReporte = "Garantias Recepción " + empPlaca;
	    
	    	if(idTipoFecha!=null ){
	    			garantias= garantiaMasivaService.buscarGarantiasMasivaSinProcesar(empPlaca, op, idTipoFecha);
	    		
	    	}else{
	    		if(fechaInicio==null || fechaInicio.isEmpty()){
	    			garantias= garantiaMasivaService.buscarGarantiasMasivaSinProcesar(empPlaca, op, null);
	    		}else{
	    			garantias= garantiaMasivaService.buscarGarantiasMasivaFech(empPlaca, op, fechaInicio, fechaFin);	
	    		}
	    	}
	    	
	    	ByteArrayOutputStream excelGarRecepcion =  garantiaService.generarReporteExcelGarantiasRecepcion(garantias);
	    	final byte[] bytes = excelGarRecepcion.toByteArray();
	    	
	    	HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.parseMediaType("application/vnd.ms-exce"));
			headers.add("Content-Disposition", "attachmnt; filename =" + NombreReporte);
			headers.add("filename", NombreReporte);
			headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
			headers.setContentLength(bytes.length);

			ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(bytes, headers, HttpStatus.OK);
	    	
	    	return response;
	    }
	    
	    @RequestMapping(value = "/aplicaConsultaPromesas", method = RequestMethod.GET)
		//@PreAuthorize("hasAnyAuthority('APLICA_CONSULTA_PROMESAS')")
		public ResponseEntity<Boolean> aplicaConsultaPromesas() throws NotFoundException {

	    	Map<String, String> parametros = garantiaService.getParametrosLP();
	    	String valor= parametros.get("APLICA_CONSULTA_PROMESAS");
	    	Boolean aplicaPromesaPago= valor ==null? false : valor.toUpperCase().equals("TRUE") ? true : false ;
			return new ResponseEntity<Boolean>(aplicaPromesaPago, HttpStatus.OK);

		}
	    
	    @RequestMapping(value = "/catalogoDocGarantias", method = RequestMethod.GET)
//		@PreAuthorize("hasAnyAuthority('RECIBIR_GARANTIA_MASIVA_FECH')")
		public ResponseEntity<List<CatalogoDocumentosGarantiaVO>> buscarCatDocGarantias() throws NotFoundException {

			List<GarantiaDocumentoDTO> garantiaDocumentoDTO = catalogoService.catalogoDocGarantias();
			List<CatalogoDocumentosGarantiaVO> catalogoDocumentosGarantiaVO = ResponseConverter.converterLista(new ArrayList<>(), garantiaDocumentoDTO, CatalogoDocumentosGarantiaVO.class);
			return new ResponseEntity<List<CatalogoDocumentosGarantiaVO>>(catalogoDocumentosGarantiaVO, HttpStatus.OK);

		}  
}
