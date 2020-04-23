package mx.com.teclo.saicdmx.api.rest.infracciones;

import java.io.ByteArrayOutputStream;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

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
import org.springframework.web.util.UriComponentsBuilder;

import mx.com.teclo.saicdmx.negocio.service.comun.UsuarioFirmadoService;
import mx.com.teclo.saicdmx.negocio.service.empleado.EmpleadoService;
import mx.com.teclo.saicdmx.negocio.service.garantias.GarantiaService;
import mx.com.teclo.saicdmx.negocio.service.infracciones.InfraccionService;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.empleados.EmpleadosDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.infracciones.InfraccionDigitalTodoDiaDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.infracciones.InfraccionesImagenesDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.infracciones.IngresosImagenesDTO;
import mx.com.teclo.saicdmx.persistencia.vo.articulos.ArticuloVO;
import mx.com.teclo.saicdmx.persistencia.vo.comuns.FilterValuesVO;
import mx.com.teclo.saicdmx.persistencia.vo.infracciones.AltaInfraccionSPVO;
import mx.com.teclo.saicdmx.persistencia.vo.infracciones.AltaInfraccionVO;
import mx.com.teclo.saicdmx.persistencia.vo.infracciones.CargaDigitalizacionWebVO;
import mx.com.teclo.saicdmx.persistencia.vo.infracciones.CountVO;
import mx.com.teclo.saicdmx.persistencia.vo.infracciones.InfraccionAllDataVO;
import mx.com.teclo.saicdmx.persistencia.vo.infracciones.InfraccionDigitalTodoDiaFolDuplicadosVO;
import mx.com.teclo.saicdmx.persistencia.vo.infracciones.InfraccionesImagenesVO;
import mx.com.teclo.saicdmx.persistencia.vo.infracciones.IngresosImagenesVO;
import mx.com.teclo.saicdmx.persistencia.vo.infracciones.ModificaEnDepositoSPVO;
import mx.com.teclo.saicdmx.persistencia.vo.infracciones.ModificaEnDepositoVO;
import mx.com.teclo.saicdmx.persistencia.vo.infracciones.SuspensionInfraccionVO;
import mx.com.teclo.saicdmx.persistencia.vo.infracciones.VInfraccionesValidaImagenVO;
import mx.com.teclo.saicdmx.persistencia.vo.infracciones.VSSPInfracConsGralFVO;
import mx.com.teclo.saicdmx.util.comun.ResponseConverter;
import mx.com.teclomexicana.arquitectura.ortogonales.exception.BusinessException;
import mx.com.teclomexicana.arquitectura.ortogonales.exception.NotFoundException;
import mx.com.teclomexicana.arquitectura.ortogonales.seguridad.vo.UsuarioFirmadoVO;

@RestController
public class InfraccionRestController {

	@Autowired
	private InfraccionService infraccionService;
	
	@Autowired
	private UsuarioFirmadoService usuarioFirmadoService;
	
	@Autowired
	private EmpleadoService empleadoService;
	
	@Autowired
	private GarantiaService garantiaService;
	
	/**
	 * RETORNA LA LISTA DE LOS TIPOS DE FILTRO PARA BUSQUEDA DE INFRACCIONES
	 * @author Kevin
	 * @return List<FilterValuesVO>
	 */
	@RequestMapping(value="/infraccionesTipoBusqueda", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('TIPO_BUSQUEDA_INFRACCION')")//R
	public ResponseEntity<List<FilterValuesVO>> buscarTiposFitroParaBusquedaInfracciones(){
		return new ResponseEntity<List<FilterValuesVO>>
			(infraccionService.buscarTiposFitroParaBusquedaInfracciones(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/infracciones", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('BUSCAR_INFRACCIONES')")//R
	public ResponseEntity<List<VSSPInfracConsGralFVO>> buscarInfracciones(@RequestParam(value = "valor" ) String valor, @RequestParam(value = "tipoBusqueda" ) String tipoBusqueda) throws NotFoundException {
		List<VSSPInfracConsGralFVO> infracciones = null;
		infracciones = infraccionService.buscarInfracciones(valor, tipoBusqueda);
		if (infracciones.isEmpty()) {
			throw new NotFoundException("No se encontraron registros");
		}
		infraccionService.consultaExisteExpedienteInfraccion(infracciones);
		return new ResponseEntity<List<VSSPInfracConsGralFVO>>(infracciones, HttpStatus.OK);
	}
	
	/**
	 * RETORNA DETALLE COMPLETO DE UNA INFRACCION, BUSCADA POR EL CAMPO INFRAC_NUM
	 * @author Kevin
	 * @param String Valor
	 * @return VSSPInfracConsGralFVO
	 */
	@RequestMapping(value = "/infraccionAllData", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('CONSULTA DETALLE INFRACCION')")//R
	public ResponseEntity<VSSPInfracConsGralFVO> buscarInfraccionesAllDataByInfracNum( @RequestParam(value = "id") String valor) 
			throws NotFoundException {
		VSSPInfracConsGralFVO vSSPInfracConsGralFVO = infraccionService.buscarInfraccionesAllDataByInfracNum(valor);
		if (vSSPInfracConsGralFVO == null) {
			throw new NotFoundException("No se encontró la infracción.");
		}else { //Emplementación de creación de garantia al momento de consultar la infracción
			Boolean flagGralGarantias;
			UsuarioFirmadoVO usuarioFirmadoVO = usuarioFirmadoService.getUsuarioFirmadoVO();
			if (usuarioFirmadoVO != null) {
				flagGralGarantias = garantiaService.generarGarantiasSinProcesarPorInf(vSSPInfracConsGralFVO.getInfraccionNumero(), usuarioFirmadoVO.getId());
				if(flagGralGarantias)
					vSSPInfracConsGralFVO = infraccionService.buscarInfraccionesAllDataByInfracNum(valor);
			}
		}
		return new ResponseEntity<VSSPInfracConsGralFVO>(vSSPInfracConsGralFVO, HttpStatus.OK);
		
	}
	
	@RequestMapping(value = "/altaInfraccion", method = RequestMethod.POST)
	@PreAuthorize("hasAnyAuthority('ALTA INFRACCION')")//R
	public ResponseEntity<AltaInfraccionSPVO>altaInfraccion
	(@RequestBody AltaInfraccionVO altaInfraccionVO, UriComponentsBuilder ucBuilder)throws BusinessException {
		altaInfraccionVO.generaParametrosParaSP();
		UsuarioFirmadoVO usuario = usuarioFirmadoService.getUsuarioFirmadoVO();
		EmpleadosDTO empleado = empleadoService.getEmpleadoById(usuario.getId());
		
		altaInfraccionVO.getAltaInfraccionSPVO().setP_modificado_por((int)(long)empleado.getEmpId());
		
		AltaInfraccionSPVO altaInfraccionSPVO = infraccionService.crearInfraccion(altaInfraccionVO.getAltaInfraccionSPVO());
		
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/altaInfraccion/{id}").buildAndExpand(altaInfraccionSPVO.getP_art_id()).toUri());
		return new ResponseEntity<AltaInfraccionSPVO>(altaInfraccionSPVO,headers, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/modificaInfraccion", method = RequestMethod.POST)
	@PreAuthorize("hasAnyAuthority('MODIFICA_INFRACCION')")
	public ResponseEntity<AltaInfraccionSPVO>modificaInfraccion
	(@RequestBody AltaInfraccionVO modificaInfraccionVO, UriComponentsBuilder ucBuilder)throws BusinessException, ParseException{
		modificaInfraccionVO.generaParametrosParaSP();
		UsuarioFirmadoVO usuario = usuarioFirmadoService.getUsuarioFirmadoVO();
		EmpleadosDTO empleado = empleadoService.getEmpleadoById(usuario.getId());
		modificaInfraccionVO.getAltaInfraccionSPVO().setP_modificado_por((int)(long)empleado.getEmpId());
		
		AltaInfraccionSPVO modificaInfraccionSPVO = infraccionService.modificaInfraccion(modificaInfraccionVO.getAltaInfraccionSPVO());
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/modificaInfraccion/{id}").buildAndExpand(modificaInfraccionSPVO.getP_art_id()).toUri());
		return new ResponseEntity<AltaInfraccionSPVO>(modificaInfraccionSPVO,headers, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/modificaInfraccionEnDeposito", method = RequestMethod.POST)
	@PreAuthorize("hasAnyAuthority('MODIFICA_INFRACCION_EN_DEPOSITO')")
	public ResponseEntity<ModificaEnDepositoVO>modificaInfraccionEnDeposito
	(@RequestBody ModificaEnDepositoVO modificaInfraccionVO, UriComponentsBuilder ucBuilder)throws BusinessException,ParseException{
		UsuarioFirmadoVO usuario = usuarioFirmadoService.getUsuarioFirmadoVO();
		EmpleadosDTO empleado = empleadoService.getEmpleadoById(usuario.getId());
		
		ModificaEnDepositoSPVO modificaInfraccionSPVO = infraccionService.modificaInfraccionEnDeposito(modificaInfraccionVO, (long)empleado.getEmpId());
		modificaInfraccionVO.setpMensaje(modificaInfraccionSPVO.getP_mensaje());
		modificaInfraccionVO.setpResultado(modificaInfraccionSPVO.getP_resultado());
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/modificaInfraccion/{id}").buildAndExpand(modificaInfraccionSPVO.getP_infrac_num_ctrl()).toUri());
		return new ResponseEntity<ModificaEnDepositoVO>(modificaInfraccionVO,headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/suspInfraccion", method = RequestMethod.POST)
	@PreAuthorize("hasAnyAuthority('MARCADO_INFRACCION')")
	public ResponseEntity<SuspensionInfraccionVO>modificaInfraccionEnDeposito
	(@RequestBody SuspensionInfraccionVO suspensionInfraccionVO, UriComponentsBuilder ucBuilder)throws BusinessException, ParseException{
		UsuarioFirmadoVO usuario = usuarioFirmadoService.getUsuarioFirmadoVO();
		EmpleadosDTO empleado = empleadoService.getEmpleadoById(usuario.getId());
		
		suspensionInfraccionVO = infraccionService.suspensionInfraccion(suspensionInfraccionVO, (long)empleado.getEmpId());

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/modificaInfraccion/{id}").buildAndExpand(suspensionInfraccionVO.getpInfracNum()).toUri());
		return new ResponseEntity<SuspensionInfraccionVO>(suspensionInfraccionVO,headers, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/foto", method = RequestMethod.GET)
	//@PreAuthorize("hasAnyAuthority('EXPEDIENTE_FOTO')")
	public ResponseEntity<InfraccionesImagenesVO>buscaFotoPorNombreArchivo
	(@RequestParam(value = "nombre") String nombre) throws NotFoundException, SQLException {
		InfraccionesImagenesDTO infraccionesImagenesDTO = new InfraccionesImagenesDTO();
		infraccionesImagenesDTO = infraccionService.buscaFotoPorNombreArchivo(nombre);
		if (infraccionesImagenesDTO == null){
			return new ResponseEntity<InfraccionesImagenesVO>(HttpStatus.NOT_FOUND);
		}
		InfraccionesImagenesVO infraccionesImagenesVO = ResponseConverter.copiarPropiedadesFull(infraccionesImagenesDTO, InfraccionesImagenesVO.class);
		//infraccionesImagenesVO.setImage(infraccionesImagenesDTO.getFoto());
		
		int blobLength = (int) infraccionesImagenesDTO.getFoto().length();  
		byte[] blobAsBytes = infraccionesImagenesDTO.getFoto().getBytes(1, blobLength);
		
		infraccionesImagenesVO.setImage(blobAsBytes);
		
		return new ResponseEntity<InfraccionesImagenesVO>(infraccionesImagenesVO , HttpStatus.OK);
	}

	@RequestMapping(value = "/imagenIngreso", method = RequestMethod.GET)
	//@PreAuthorize("hasAnyAuthority('EXPEDIENTE_INGRESO_FOTO')")
	public ResponseEntity<IngresosImagenesVO>buscaFotoPorNombreArchivoIngreso
	(@RequestParam(value = "nombre") String nombre) throws NotFoundException, SQLException {
		IngresosImagenesDTO ingresosImagenesDTO = new IngresosImagenesDTO();
		ingresosImagenesDTO = infraccionService.buscaFotoPorNombreArchivoIngreso(nombre);
		if (ingresosImagenesDTO == null)
			return new ResponseEntity<IngresosImagenesVO>(HttpStatus.NOT_FOUND);
		IngresosImagenesVO ingresosImagenesVO = ResponseConverter.copiarPropiedadesFull(ingresosImagenesDTO, IngresosImagenesVO.class);
		int blobLength = (int) ingresosImagenesDTO.getFoto().length();  
		byte[] blobAsBytes = ingresosImagenesDTO.getFoto().getBytes(1, blobLength);
		ingresosImagenesVO.setImage(blobAsBytes);
		return new ResponseEntity<IngresosImagenesVO>(ingresosImagenesVO , HttpStatus.OK);
	}
	
	@RequestMapping(value = "/countInfracDigTodDiaPorStatus", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('CUENTA_INFRAC_DIG_TOD_DIA_POR_STATUS')")
	public ResponseEntity<CountVO>countInfracDigTodDiaByStatus
		(@RequestParam(value = "status") String status) throws NotFoundException, SQLException{
		CountVO countVO = infraccionService.countByStatus(status);
		return new ResponseEntity<CountVO>(countVO, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/cargaDigWeb", method = RequestMethod.POST)
	//@PreAuthorize("hasAnyAuthority('MARCADO_INFRACCION')")
	public ResponseEntity<CargaDigitalizacionWebVO>CargaDigitalizacionWeb
	(@RequestBody CargaDigitalizacionWebVO cargaDigitalizacionWebVO, UriComponentsBuilder ucBuilder)throws BusinessException{
		UsuarioFirmadoVO usuario = usuarioFirmadoService.getUsuarioFirmadoVO();
		EmpleadosDTO empleado = empleadoService.getEmpleadoById(usuario.getId());
		
		cargaDigitalizacionWebVO = infraccionService.CargaDigitalizacionWeb(cargaDigitalizacionWebVO, (long)empleado.getEmpId());

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/modificaInfraccion/{id}").buildAndExpand(cargaDigitalizacionWebVO.getFoliosProcesados()).toUri());
		return new ResponseEntity<CargaDigitalizacionWebVO>(cargaDigitalizacionWebVO,headers, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/infraccionesDigitalesTodoDiaPorStatus", method = RequestMethod.GET)
	public ResponseEntity<List<InfraccionDigitalTodoDiaFolDuplicadosVO>> infraccionesDigitalesTodoDiaPorStatus(@RequestParam(value = "status") String status) throws NotFoundException {
		List<InfraccionDigitalTodoDiaDTO> instanciaDTO = new ArrayList<InfraccionDigitalTodoDiaDTO>();
		instanciaDTO = infraccionService.buscarInfraccionDigitalTodoDiaDTOPorEstatus(status);
		if (instanciaDTO == null){
			return new ResponseEntity<List<InfraccionDigitalTodoDiaFolDuplicadosVO>>(HttpStatus.NOT_FOUND);
		}
		List<InfraccionDigitalTodoDiaFolDuplicadosVO> infraccionDigitalTodoDiaFolDuplicadosVO = new ArrayList<InfraccionDigitalTodoDiaFolDuplicadosVO>();
		infraccionDigitalTodoDiaFolDuplicadosVO =  ResponseConverter.converterLista(new ArrayList<>(), 
				instanciaDTO, InfraccionDigitalTodoDiaFolDuplicadosVO.class);
		return new ResponseEntity<List<InfraccionDigitalTodoDiaFolDuplicadosVO>>(infraccionDigitalTodoDiaFolDuplicadosVO , HttpStatus.OK);
	}
	
	@RequestMapping(value="getInfraccionAltaCatalogo", method = RequestMethod.GET)
	public ResponseEntity<InfraccionAllDataVO> infraccionAllData() throws NotFoundException {
		return new ResponseEntity<InfraccionAllDataVO>(infraccionService.gatherAllData() , HttpStatus.OK);
	}
	
	@RequestMapping(value="validaImagen", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('GET_STATUS_VALIDACION_EXP_IMAGENES')")
	public ResponseEntity<VInfraccionesValidaImagenVO> vInfraccionesValidaImagen
		(@RequestParam(value="infracNum")String infracNum){
		VInfraccionesValidaImagenVO vInfraccionesValidaImagenVO = infraccionService.vInfraccionesValidaImagen(infracNum);
		if(vInfraccionesValidaImagenVO == null)
			return new ResponseEntity<VInfraccionesValidaImagenVO>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<VInfraccionesValidaImagenVO>(vInfraccionesValidaImagenVO, HttpStatus.OK);
	}	
	
	@RequestMapping(value = "/reporteConsultaInfraccion", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('REPORTE_CONSULTA_INFRACCION')")
    public ResponseEntity<byte[]> generarReporteConsultaInfraccion(@RequestParam(value="nci") String nci)  {
    	String filename 				   = "Reporte_Infraccion_Consulta.pdf";
    	
    	ByteArrayOutputStream outputStream = infraccionService.generaReporteConsulta(nci);
    	byte[] bytes = outputStream.toByteArray();
    	HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.parseMediaType("application/pdf"));
		headers.add("Content-Disposition", "attachmnt; filename =" + filename);
		headers.add("filename", filename);
		headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
		headers.setContentLength(bytes.length);
		return new ResponseEntity<byte[]>(bytes, headers, HttpStatus.OK);
    }
    
	@RequestMapping(value="/buscarExcepcionesEnArticulos", method = RequestMethod.GET)
	public ResponseEntity<List<ArticuloVO>> buscarExcepcionesEnArticulos(@RequestParam(value = "articulo") String articulo) throws NotFoundException {
		return new ResponseEntity<List<ArticuloVO>>(infraccionService.buscarExcepcionesEnArticulos(articulo) , HttpStatus.OK);
	}
}
