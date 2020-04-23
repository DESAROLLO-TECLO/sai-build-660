package mx.com.teclo.saicdmx.api.rest.remisionadeposito;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import mx.com.teclo.saicdmx.negocio.service.comun.UsuarioFirmadoService;
import mx.com.teclo.saicdmx.negocio.service.pagos.ConsultaInfraccionService;
import mx.com.teclo.saicdmx.negocio.service.remisionadeposito.RemisionaDepositoService;
import mx.com.teclo.saicdmx.negocio.service.salidas.SalidaVehiculoService;
import mx.com.teclo.saicdmx.negocio.service.usuarios.UsuariosService;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.ingresos.IngresosDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.usuarios.UsuariosDTO;
import mx.com.teclo.saicdmx.persistencia.vo.comuns.FilterValuesVO;
import mx.com.teclo.saicdmx.persistencia.vo.remisionadeposito.ArticuloSancionVO;
import mx.com.teclo.saicdmx.persistencia.vo.remisionadeposito.ConInfraccionVO;
import mx.com.teclo.saicdmx.persistencia.vo.remisionadeposito.GruaConceVO;
import mx.com.teclo.saicdmx.persistencia.vo.remisionadeposito.IngresoInfraccionVO;
import mx.com.teclo.saicdmx.persistencia.vo.remisionadeposito.OficialIngresoVO;
import mx.com.teclo.saicdmx.persistencia.vo.remisionadeposito.SecAgrupArrasEmpVO;
import mx.com.teclo.saicdmx.persistencia.vo.remisionadeposito.VehiculoTipoColorMarcaVO;
import mx.com.teclo.saicdmx.persistencia.vo.salidas.ConsultaTrasladoVO;
import mx.com.teclo.saicdmx.persistencia.vo.salidas.FilesSalidaVO;
import mx.com.teclo.saicdmx.persistencia.vo.salidas.GuardarTrasladoVO;
import mx.com.teclo.saicdmx.util.comun.ResponseConverter;
import mx.com.teclomexicana.arquitectura.ortogonales.exception.BusinessException;
import mx.com.teclomexicana.arquitectura.ortogonales.exception.NotFoundException;

@RestController
public class RemisionaDepositoRestController {

	@Autowired
	private RemisionaDepositoService remisionaDepositoService;

	
	@Autowired
	private UsuarioFirmadoService usuarioFirmadoService;
	
	@Autowired
	private UsuariosService usuariosService;
	
	@Autowired
	ServletContext context;
	
	@Autowired
	private SalidaVehiculoService salidaVehiculoService;

	@Autowired
	private ConsultaInfraccionService consultaInfraccionService;
	
	@RequestMapping(value = "/buscarInfraccion", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('BUSCAR_INFRACCION')")
	public ResponseEntity<List<ConInfraccionVO>> buscarConInfraccion(@RequestParam(value = "opcion") String opcion,
			@RequestParam(value = "valor") String valor) throws NotFoundException {
		
		List<ConInfraccionVO> conInfraccionVO = new ArrayList<ConInfraccionVO>();
//		IngresosDTO traslado = consultaInfraccionService.validarTraslado(opcion,valor, false);
//		if(traslado != null){
//			throw new NotFoundException("El vehículo se encuentra en traslado al depósito " +traslado.getMovimientos().get(0).getDepNomDestino() );
//		}else{}
		conInfraccionVO = remisionaDepositoService.buscarConInfraccion(opcion, valor);
		if (conInfraccionVO.isEmpty()) {
			throw new NotFoundException("No se encontraron registros");
		
		}
		return new ResponseEntity<List<ConInfraccionVO>>(conInfraccionVO, HttpStatus.OK);
	}

	//
	@RequestMapping(value = "/updIngresoDeposito/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasAnyAuthority('BUSCA_INGRESO_DEPOSITO_POR_INFRACCION')")
	public ResponseEntity<IngresoInfraccionVO> buscarUpdPorId(@PathVariable("id") String infrac_num_ctrl)
			throws NotFoundException {
		IngresoInfraccionVO updingresoVO = remisionaDepositoService.buscarUpdPorId(infrac_num_ctrl);
		if (updingresoVO == null) {
			throw new NotFoundException("No se encontraron registros");
		}
		return new ResponseEntity<IngresoInfraccionVO>(updingresoVO, HttpStatus.OK);
	}

	/// Con infraccion
	@SuppressWarnings("unused")
	@RequestMapping(value = "/registroConInfraccion", method = RequestMethod.POST)
	@PreAuthorize("hasAnyAuthority('REGISTRO_CON_INFRACCION')")
	public ResponseEntity<IngresoInfraccionVO> altaIngresoInfraccion(@RequestBody IngresoInfraccionVO ingresoInfracVO)
			throws BusinessException, ParseException {
		
		UsuariosDTO usuario = usuariosService.buscaUsuarioById(usuarioFirmadoService.getUsuarioFirmadoVO().getId());
		ingresoInfracVO.setUsu_id(usuarioFirmadoService.getUsuarioFirmadoVO().getId());
		if(usuario!=null){
			ingresoInfracVO.setUsu_login(usuario.getUsuarioLogin());
		}
		if (ingresoInfracVO.getCausa_id() == "03" || ingresoInfracVO.getCausa_id() == "04"
				|| ingresoInfracVO.getCausa_id() == "07") {
			IngresoInfraccionVO registroEmbargoVO = remisionaDepositoService.altaIngresoEmbargo(ingresoInfracVO);
			return new ResponseEntity<IngresoInfraccionVO>(registroEmbargoVO, HttpStatus.OK);
		} else {
			IngresoInfraccionVO registoAdminVO = remisionaDepositoService.altaIngresoAdmin(ingresoInfracVO);
			IngresoInfraccionVO registroVO = remisionaDepositoService.altaIngresoInfraccion(ingresoInfracVO);
			return new ResponseEntity<IngresoInfraccionVO>(registroVO, HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/consultarOficialPlaca", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('BUSCAR_EMPLEADO_POR_PLACA')")
	public ResponseEntity<OficialIngresoVO> buscarOficialPorPlaca(
			@RequestParam("infrac_placa_empl") String infrac_placa_empl) throws NotFoundException {
		OficialIngresoVO oficialVO = remisionaDepositoService.buscarOficialPorPlaca(infrac_placa_empl);

		if (oficialVO == null) {
			throw new NotFoundException("No se encontro la placa");
		}
		return new ResponseEntity<OficialIngresoVO>(oficialVO, HttpStatus.OK);
	}

	// Imprimir Recibo Resguardo
	@RequestMapping(value = "/imprimirResguardo", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('IMPRIMIR_RESGUARDO')")
	public ResponseEntity<byte[]> generarPDFResguardo(@RequestParam(value = "infraccionNum") String infraccionNum)
			throws NotFoundException, FileNotFoundException {

		String filename = "reporteResguardo.pdf";
		String rutaArchivo = context.getRealPath("/WEB-INF/jasper/remisionadeposito/repo_resguardo.jasper");
		ByteArrayOutputStream reporte = remisionaDepositoService.generaReporteResguardo(infraccionNum, rutaArchivo);
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

	// Imprimir Arrastre
	@RequestMapping(value = "/imprimirArrastre", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('IMPRIMIR_ARRASTRE')")
	public ResponseEntity<byte[]> generarPDFArrastre(@RequestParam(value = "infraccionNum") String infraccionNum)
			throws NotFoundException, FileNotFoundException {

		String filename = "reporteArrastre.pdf";
		String rutaArchivo = context.getRealPath("/WEB-INF/jasper/remisionadeposito/repo_lev_arr.jasper");
		// String rutaImagen =
		// context.getRealPath("/WEB-INF/imagenes/logo_SSP_bn.png");
		ByteArrayOutputStream reporte = remisionaDepositoService.generaReporteArrastre(infraccionNum, rutaArchivo);
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

	@RequestMapping(value = "/articuloSancion", method = RequestMethod.GET)
	public ResponseEntity<ArticuloSancionVO> valorArticuloSancion(@RequestParam(value = "infrac_num") String infrac_num)
			throws NotFoundException {
		ArticuloSancionVO articulo = null;
		articulo = remisionaDepositoService.valorArticuloSancion(infrac_num);
		// if (articulo.isEmpty()) {
		// throw new NotFoundException("No se encontro el articulo");
		// }
		return new ResponseEntity<ArticuloSancionVO>(articulo, HttpStatus.OK);
	}

	@RequestMapping(value = "/vehiculo", method = RequestMethod.GET)
	public ResponseEntity<VehiculoTipoColorMarcaVO> valorVehiculo(@RequestParam(value = "infrac_num") String infrac_num)
			throws NotFoundException {
		VehiculoTipoColorMarcaVO vehiculo = null;
		vehiculo = remisionaDepositoService.valorVehiculo(infrac_num);
		return new ResponseEntity<VehiculoTipoColorMarcaVO>(vehiculo, HttpStatus.OK);
	}

	@RequestMapping(value = "/SecAgrupArrasEmp", method = RequestMethod.GET)
	public ResponseEntity<SecAgrupArrasEmpVO> valorSecAgrupArrasEmp(
			@RequestParam(value = "infrac_num") String infrac_num) throws NotFoundException {
		SecAgrupArrasEmpVO secAgrup = null;
		secAgrup = remisionaDepositoService.valorSecAgrupArrasEmp(infrac_num);
		return new ResponseEntity<SecAgrupArrasEmpVO>(secAgrup, HttpStatus.OK);
	}

	@RequestMapping(value = "/gruaConce", method = RequestMethod.GET)
	public ResponseEntity<GruaConceVO> valorGruaConce(@RequestParam(value = "infrac_num") String infrac_num)
			throws NotFoundException {
		GruaConceVO grua = null;
		grua = remisionaDepositoService.valorGruaConce(infrac_num);
		return new ResponseEntity<GruaConceVO>(grua, HttpStatus.OK);
	}

	@RequestMapping(value = "/registroSinInfraccion", method = RequestMethod.POST)
	@PreAuthorize("hasAnyAuthority('REGISTRO_SIN_INFRACCION')")
	public ResponseEntity<IngresoInfraccionVO> altaIngresoSinInfraccion(
			@RequestBody IngresoInfraccionVO ingresoInfracVO) throws BusinessException, ParseException {
		ingresoInfracVO.setUsu_login("");
		UsuariosDTO usuario = usuariosService.buscaUsuarioById(usuarioFirmadoService.getUsuarioFirmadoVO().getId());
		if(usuario != null){
			ingresoInfracVO.setUsu_login(usuario.getUsuarioLogin());
		}
		if (ingresoInfracVO.getCausa_id().equals("03") || ingresoInfracVO.getCausa_id().equals("04") || ingresoInfracVO.getCausa_id().equals( "07") ){
			IngresoInfraccionVO registroEmbargoVO = remisionaDepositoService.altaIngresoEmbargo(ingresoInfracVO);
			return new ResponseEntity<IngresoInfraccionVO>(registroEmbargoVO, HttpStatus.OK);
		} else {
			IngresoInfraccionVO adminResult = remisionaDepositoService.altaIngresoAdmin(ingresoInfracVO);
			ingresoInfracVO.setAsn(adminResult.getAsn());
			ingresoInfracVO.setResguardo(adminResult.getResguardo());
			ingresoInfracVO.setUsu_id(usuarioFirmadoService.getUsuarioFirmadoVO().getId());
			IngresoInfraccionVO registroVO = remisionaDepositoService.altaIngresoInfraccion(ingresoInfracVO);
			return new ResponseEntity<IngresoInfraccionVO>(registroVO, HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/buscarRemisiones", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('BUSCAR_REMISIONES')")
	public ResponseEntity<List<ConInfraccionVO>> buscarRemisiones(@RequestParam(value = "opcion") String opcion,
			@RequestParam(value = "valor") String valor) throws NotFoundException {
		List<ConInfraccionVO> conInfraccionVO = remisionaDepositoService.buscarRemisiones(opcion, valor, 
				usuarioFirmadoService.getUsuarioFirmadoVO().getId());
		
		if (conInfraccionVO.isEmpty()) {
			throw new NotFoundException("No se encontraron registros");
		}
		return new ResponseEntity<List<ConInfraccionVO>>(conInfraccionVO, HttpStatus.OK);
	}

	@RequestMapping(value = "/mostarDatosNCI", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	// @PreAuthorize("hasAnyAuthority('BUSCAR_AUXILIAR_POR_ID')")
	public ResponseEntity<IngresoInfraccionVO> mostarDatosPorNCI(
			@RequestParam("infrac_num_ctrl") String infrac_num_ctrl) throws NotFoundException {
		IngresoInfraccionVO updingresoVO = remisionaDepositoService.mostarDatosPorNCI(infrac_num_ctrl);
		if (updingresoVO == null) {
			throw new NotFoundException("No se encontraron valores");
		}
		return new ResponseEntity<IngresoInfraccionVO>(updingresoVO, HttpStatus.OK);
	}

	@RequestMapping(value = "/infracIngresoNCI", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<IngresoInfraccionVO> buscarInfraporNCI(
			@RequestParam("infrac_num_ctrl") String infrac_num_ctrl) throws NotFoundException {
		IngresoInfraccionVO NCIingresoVO = remisionaDepositoService.buscarPorNCI(infrac_num_ctrl);
		if (NCIingresoVO == null) {
			throw new NotFoundException("No se encontraron registros");
		}
		return new ResponseEntity<IngresoInfraccionVO>(NCIingresoVO, HttpStatus.OK);
	}
	
	
	
	/* INICIA BUSQUEDA TRASLADO EN CURSO DE VEHICULOS EN SALIDA 
	 * ***********************************
	 * ***********************************
	 * */
	
	@RequestMapping(value = "/tipoBusquedaCombo", method = RequestMethod.GET)
	public  ResponseEntity<List<FilterValuesVO>>  tipoBusquedaCombo () throws NotFoundException{
		List<FilterValuesVO> tipoBusquedaComb = salidaVehiculoService.tipoBusqCombo();	
		if(tipoBusquedaComb.isEmpty()){
			throw new NotFoundException("No se encontraron registros");
		}
		 return new ResponseEntity<List<FilterValuesVO>>(tipoBusquedaComb,HttpStatus.OK);
	}
	
	@RequestMapping(value = "/depOrgCombo", method = RequestMethod.GET)
	public  ResponseEntity<List<FilterValuesVO>>  depOrgCombo () throws NotFoundException{
		List<FilterValuesVO> depOrgComb = salidaVehiculoService.depOrigenCombo();
		if(depOrgComb.isEmpty()){
			throw new NotFoundException("No se encontraron registros");
		}
		 return new ResponseEntity<List<FilterValuesVO>>(depOrgComb,HttpStatus.OK);
	}
	
	
	
	@RequestMapping(value = "/buscarTrasladoEnCurso", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('BUSCAR_TRASLADO_EN_CURSO')")
	public  ResponseEntity<List<ConsultaTrasladoVO>>  buscarTrasladoEnCurso (@RequestParam(value = "tipoBusq" ) String tipoBusq,
			@RequestParam(value = "datoBusq" ) String datoBusq) throws NotFoundException{
		List<ConsultaTrasladoVO> resultsTrasladoVO = new ArrayList<ConsultaTrasladoVO>();
		ConsultaTrasladoVO traslado = consultaInfraccionService.buscaTrasladoParaIngreso(tipoBusq,datoBusq, true);
		if(traslado != null){
			resultsTrasladoVO.add(traslado);
			return new ResponseEntity<List<ConsultaTrasladoVO>>(resultsTrasladoVO,HttpStatus.OK);
		}else{
			throw new NotFoundException("No se encontraron registros");
		}
	}
	
	/* FIN DE  BUSQUEDA TRASLADO EN CURSO DE VEHICULOS EN SALIDA 
	 * ***********************************
	 * ***********************************
	 * */
	
	/* INICIO DE  ALTA TRASLADO EN CURSO DE VEHICULOS EN SALIDA 
	 * ***********************************
	 * ***********************************
	 * */
	
	@RequestMapping(value = "/buscarInfoTrasladoVehiculo", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('BUSCAR_TRASLADO_EN_CURSO_TIPO')")
	public  ResponseEntity<ConsultaTrasladoVO>  buscarInfoTrasladoVehiculo (@RequestParam(value = "numInfraccion" ) String numInfraccion
			) throws NotFoundException, ParseException{
		String tipo = "INFRACCION";
		ConsultaTrasladoVO traslado = consultaInfraccionService.buscaTrasladoParaIngreso(tipo,numInfraccion, true);
		return new ResponseEntity<ConsultaTrasladoVO>(traslado,HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getInfracPlaca", method = RequestMethod.GET)
	public ResponseEntity<OficialIngresoVO> getInfracPlaca(
			@RequestParam("infrac_placa_empl") String infrac_placa_empl) throws NotFoundException {
		OficialIngresoVO oficialVO = remisionaDepositoService.buscarOficialPorPlacaId(infrac_placa_empl);

		if (oficialVO == null) {
			throw new NotFoundException("No se encontro la placa");
		}
		return new ResponseEntity<OficialIngresoVO>(oficialVO, HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/guardarVOIngresoTraslado", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('GUARDAR_INGRESO_TRASLADO')")
	public  ResponseEntity<GuardarTrasladoVO>  guardarVOIngresoTraslado (@RequestParam(value = "parametroVO")String parametroVO, 
			@RequestParam(value = "files") String files,  @RequestParam(value = "tmno") int tmno) throws NotFoundException{
		GuardarTrasladoVO convertVO = this.conversionAngularJavaTraslado(parametroVO);
		
		salidaVehiculoService.updateIngresos(convertVO);
		Long idMovimiento = salidaVehiculoService.getIdMovVeh(convertVO.getInfracNum());
		if(convertVO.getMensaje() == null){
			throw new NotFoundException("Ingreso no registrado correctamente");
		}else {
			String oldStatus = salidaVehiculoService.getOldStatus(convertVO.getInfracNum());
			salidaVehiculoService.insertToBit(convertVO.getInfracNum(), oldStatus);
		}
		if(tmno > 0){
			List<FilesSalidaVO> filesVO = this.conversionAngularJavaFiles(files);
			salidaVehiculoService.insertToImgSalidaIngreso(filesVO,convertVO, idMovimiento);
		}
		 return new ResponseEntity<GuardarTrasladoVO>(convertVO,HttpStatus.OK);
	}
	
	private GuardarTrasladoVO conversionAngularJavaTraslado (String jsonDocumentoVO){
		GuardarTrasladoVO newObject = null;
        try {
        	ObjectMapper mapper = new ObjectMapper();
           	mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
           	newObject = mapper.readValue(jsonDocumentoVO.toString(), GuardarTrasladoVO.class);
        } catch (IOException e) {
        	e.printStackTrace();
        }
        return newObject;
	}
	private List<FilesSalidaVO> conversionAngularJavaFiles (String files){
		List<FilesSalidaVO> newObject = null;
        try {
        	ObjectMapper mapper = new ObjectMapper();
           	mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
           	newObject = mapper.readValue(files.toString(),  new TypeReference<List<FilesSalidaVO>>(){});
        } catch (IOException e) {
        	e.printStackTrace();
        }
        return newObject;
	}
	
}
