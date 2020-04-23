package mx.com.teclo.saicdmx.api.rest.radares;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import mx.com.teclo.arquitectura.ortogonales.util.ResponseConverter;
import mx.com.teclo.saicdmx.negocio.service.catalogos.CatalogoService;
import mx.com.teclo.saicdmx.negocio.service.certificados.CertificadosService;
import mx.com.teclo.saicdmx.negocio.service.comun.UsuarioFirmadoService;
import mx.com.teclo.saicdmx.negocio.service.fm.FMCrearLotesService;
import mx.com.teclo.saicdmx.negocio.service.radarArchivoProcesado.RadarArchivoProcesadosService;
import mx.com.teclo.saicdmx.negocio.service.radararchivo.RadarArchivoService;
import mx.com.teclo.saicdmx.negocio.service.radarparametrosweb.RadarParametrosWebService;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.RadarCatArchivoTipoDTO;
import mx.com.teclo.saicdmx.persistencia.vo.comuns.ComboValuesVO;
import mx.com.teclo.saicdmx.persistencia.vo.fm.FMMarcaDispositivoVO;
import mx.com.teclo.saicdmx.persistencia.vo.fm.FMOrigenPlacaVO;
import mx.com.teclo.saicdmx.persistencia.vo.fm.FMTiposDeteccionesVO;
import mx.com.teclo.saicdmx.persistencia.vo.radarbitacoraprocesoestatus.RadarBitacoraProcesoComplementacionVO;
import mx.com.teclo.saicdmx.persistencia.vo.radares.ArchivoBatchFinanzasVO;
import mx.com.teclo.saicdmx.persistencia.vo.radares.CargaArchivoComplementracionVO;
import mx.com.teclo.saicdmx.persistencia.vo.radares.RadarArchivoEnComplementacionVO;
import mx.com.teclo.saicdmx.persistencia.vo.radares.RadarArchivoResumenVO;
import mx.com.teclo.saicdmx.persistencia.vo.radares.RadarDeteccionesCentroReparto;
import mx.com.teclo.saicdmx.persistencia.vo.radares.RadarDeteccionesCentroRepartoV2;
import mx.com.teclo.saicdmx.persistencia.vo.radares.RadaresCatArchivoTipoVO;
import mx.com.teclo.saicdmx.util.enumerados.ArchivoBatchEstatusEnum;
import mx.com.teclo.saicdmx.util.enumerados.ArchivosNumberEnum;
import mx.com.teclomexicana.arquitectura.ortogonales.exception.BusinessException;
import mx.com.teclomexicana.arquitectura.ortogonales.seguridad.vo.UsuarioFirmadoVO;


/**
 * 
 * @author UnitisDes0416
 *
 */
@RestController
public class ComplementarRaradesRestController {
	
	@Autowired 
	private RadarArchivoProcesadosService radarArchivoProcesadosService;
	
	@Autowired
	private CatalogoService catalogoService;
	
	@Autowired
	private RadarArchivoService radarArchivoService;
	
	@Autowired
	private RadarParametrosWebService radarParametrosWebService;
	
	@Autowired
	UsuarioFirmadoService usuarioFirmadoService;
	
	@Autowired FMCrearLotesService fMCrearLotesService;

	/**
	 * @author UnitisDes0416
	 * @return List(RadaresCargaArchivoVO)
	 * @throws NotFoundException 
	 */
	@RequestMapping(value = "/buscaCatArchivoTipoActivos", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('BUSCAR_CAT_ARCHIVO_TIPO_ACTIVOS')")
	public ResponseEntity<List<RadaresCatArchivoTipoVO>> buscaCatArchivoTipoActivos() throws NotFoundException{
		List<RadaresCatArchivoTipoVO> listaRadaresCargaArchivoVO = new ArrayList<RadaresCatArchivoTipoVO>();
		List<RadarCatArchivoTipoDTO> listaRadarCatArchivoTipo = catalogoService.buscaCatArchivoTipoActivos();
		listaRadaresCargaArchivoVO = ResponseConverter.converterLista(new ArrayList<>(), listaRadarCatArchivoTipo, RadaresCatArchivoTipoVO.class);
		if (listaRadaresCargaArchivoVO.isEmpty()) {
			throw new NotFoundException("No hay informacion para el combo archivos tipo de radar");
		}
		
		return new ResponseEntity<List<RadaresCatArchivoTipoVO>>(listaRadaresCargaArchivoVO, HttpStatus.OK);
	}
	
	/**
	 * @author UnitisDes0416
	 * @return List(RadaresCargaArchivoVO)
	 * @throws NotFoundException 
	 */
	@RequestMapping(value = "/buscaCatTipoMarca", method = RequestMethod.GET)
	//@PreAuthorize("hasAnyAuthority('BUSCAR_CAT_ARCHIVO_TIPO_ACTIVOS')")
	public ResponseEntity<List<FMMarcaDispositivoVO>> buscaCatTipoMarca(@RequestParam("idMarca")Integer idMarca) throws NotFoundException{
	
		List<FMMarcaDispositivoVO> listaTipoMarca = catalogoService.fmTiposRadaresByMarca(false, idMarca);
		
		if (listaTipoMarca.isEmpty()) {
			throw new NotFoundException("No hay informacion para el combo archivos tipo de radar");
		}
		
		return new ResponseEntity<List<FMMarcaDispositivoVO>>(listaTipoMarca, HttpStatus.OK);
	}
	
	/**
	 * @author UnitisDes0416
	 * @return List(ComboValuesVO)
	 */
	@RequestMapping(value = "/obtenerAnioSalarioMinimo", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('OBTENER_ANIO_SALARIO_MINIMO')")
	public ResponseEntity<List<ComboValuesVO>> obtenerAnioSalarioMinimo(){
		//return new ResponseEntity <List<ComboValuesVO>> (radarArchivoService.obtenerAniosSalarioMinimo(), HttpStatus.OK);
		return new ResponseEntity<List<ComboValuesVO>>(catalogoService.obtenerAnioSalarioMinimo(), HttpStatus.OK);
	}
	
	/**
	 * @author UnitisDes0416
	 * @param file MultipartFile
	 * @param archivoVO String
	 * @return ResponseEntity(Boolean)
	 * @throws BusinessException
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/cargaArchivoComplementacion", method = RequestMethod.POST)
	@PreAuthorize("hasAnyAuthority('CARGAR_ARCHIVO_COMPLEMENTACION')")
	public ResponseEntity<Map> cargaArchivoComplementacion(
			@RequestParam(required = false, name = "file") MultipartFile file, @RequestParam("archivoVO")String archivoVO) throws BusinessException, IOException{
		Map resultadoArchivo = new HashMap<>();
		CargaArchivoComplementracionVO cargaArchivoComplementracionVO = null;
		UsuarioFirmadoVO usuarioFirmadoVO = usuarioFirmadoService.getUsuarioFirmadoVO(); 
		String rutaRadares = radarParametrosWebService.obtenerRutaRadares();
		
		if(radarArchivoService.verificarArchivosEnCurso() == false){
			cargaArchivoComplementracionVO = this.armaOrdenEnvioVO(archivoVO);
			radarArchivoService.cargaArchivoComplementacionRadares(file, cargaArchivoComplementracionVO, rutaRadares);
			/*Aqui agregue la modificación sobre el tipo de archivo*/
			resultadoArchivo = radarArchivoService.validaArchivoRadar(file.getOriginalFilename(), rutaRadares, cargaArchivoComplementracionVO);
			System.out.println("Booleano"+resultadoArchivo.get("resultado"));
			if((Boolean)resultadoArchivo.get("resultado")){
				System.out.println("Booleano resultado 1");
				int origenProceso=getOrigenPlaca(cargaArchivoComplementracionVO.getRadaresCatArchivoTipoVO().getArchivoTipoId());
				System.out.println("Este es el origen de la placa: "+origenProceso);
				radarArchivoService.cargaArchivoRadar(file.getOriginalFilename(), 
						origenProceso, 
						(List<Map>)resultadoArchivo.get("detecciones"), 
						(List<Map>)resultadoArchivo.get("duplicados"),
						usuarioFirmadoVO.getId(), 
						cargaArchivoComplementracionVO, 
						(Integer)resultadoArchivo.get("origenPlaca"),
						(boolean) resultadoArchivo.get("esCarrilConfinado"));
				resultadoArchivo.put("resultado", 1);
				resultadoArchivo.put("message", "¡El archivo se cargó exitosamente!");
			}else{
				System.out.println("Booleano resultado 2");
				resultadoArchivo.put("resultado", 2);
				resultadoArchivo.put("message", "Archivo con registros incorrectos");
			}
		}else{
			System.out.println("Booleano resultado 3");
			resultadoArchivo.put("resultado", 3);
			resultadoArchivo.put("message", "Hay un archivo en proceso de Complementacion");
		}
		
		return new ResponseEntity<Map>(resultadoArchivo, HttpStatus.OK);
	}
	
	private static int getOrigenPlaca(int archivoTipo) {
		if(archivoTipo == ArchivosNumberEnum.RADAR_SSP_FORANEO.getConstante()||
				archivoTipo == ArchivosNumberEnum.RADAR_CONCESIONADO_FORANEO.getConstante()||
						archivoTipo == ArchivosNumberEnum.FOTOMULTA_FORANEO.getConstante()||
						archivoTipo	== ArchivosNumberEnum.CARRIL_CONFINADO_FORANEO.getConstante()
				){
			return ArchivosNumberEnum.PROCESO_FORANEO.getConstante();
		}else {
			return ArchivosNumberEnum.PROCESO_COMPLETO.getConstante();
		}
	}
	
	/**
	 * @author UnitisDes0416
	 * @param jsonCargaArchivoComplementracionVO String
	 * @return CargaArchivoComplementracionVO
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	private CargaArchivoComplementracionVO armaOrdenEnvioVO(String jsonCargaArchivoComplementracionVO){
		
		CargaArchivoComplementracionVO cargaArchivoComplementracionVO = null;
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		try {
			cargaArchivoComplementracionVO = mapper.readValue(jsonCargaArchivoComplementracionVO, CargaArchivoComplementracionVO.class);
		} catch (IOException e) {
			new BusinessException("Validar parametros para el archivo");
			cargaArchivoComplementracionVO = null;
		}	
		
		return cargaArchivoComplementracionVO;
	}
	
	/**
	 * @author UnitisDes0416
	 * @return List(ComboValuesVO)
	 * @throws BusinessException 
	 */
	@RequestMapping(value = "/buscaArchivoEnProceso", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('BUSCAR_ARCHIVO_ENPROCESO')")
	public ResponseEntity<Boolean> buscaArchivoEnProceso() throws BusinessException{
		return new ResponseEntity<Boolean>(radarArchivoService.verificarArchivosEnCurso(), HttpStatus.OK);
	}
	
	/**
	 * @author UnitisDes0416
	 * @return List(ComboValuesVO)
	 * @throws BusinessException 
	 */
	@RequestMapping(value = "/buscarRadarArchivoEnProceso", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('BUSCAR_RADAR_ARCHIVO_ENPROCESO')")
	public ResponseEntity<RadarArchivoEnComplementacionVO> buscarRadarArchivoEnProceso() {
		Map<String, String> parametros = fMCrearLotesService.getParametrosLP();		
		return new ResponseEntity<RadarArchivoEnComplementacionVO>(radarArchivoService.buscarRadarArchivoEnProceso(parametros.get("BUSCAR_ARCHIVO_EN_PROCESO")), HttpStatus.OK);
	}
		
	/**
	 * @author UnitisDes0416
	 * @return List(ComboValuesVO)
	 * @throws BusinessException 
	 */
	@RequestMapping(value = "/buscaEststusRadarArchivoEnProceso", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('BUSCAR_ESTATUS_RADAR_ARCHIVO_ENPROCESO')")
	public ResponseEntity<List<RadarBitacoraProcesoComplementacionVO>> buscaEststusRadarArchivoEnProceso(@RequestParam("radarArchivoId") Long radarArchivoid) {
		return new ResponseEntity<List<RadarBitacoraProcesoComplementacionVO>>(radarArchivoService.buscaEststusRadarArchivoEnProceso(radarArchivoid), HttpStatus.OK);
	}

	/**
	 * @author UnitisDes0416
	 * @param radarArchivoid Long
	 */
	@RequestMapping(value = "/cancelaLoteEnProceso", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('CANCELAR_LOTE_ENPROCESO')")
	public void cancelaLoteEnProceso(@RequestParam("radarArchivoId") Long radarArchivoId, @RequestParam("motivoCancelacion") String motivoCancelacion) {
		
		
		//radarArchivoService
		///.cancelaLoteEnProceso(radarArchivoId, motivoCancelacion);
	}
	
	@RequestMapping(value = "/listaDeteccionValidas", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('LISTA_DETECCION_VALIDAS')")
	public ResponseEntity<List<RadarDeteccionesCentroRepartoV2>>  buscarDeteccionesValidas(@RequestParam("radarArchivoId") Long radarArchivoId) {
		
		return new ResponseEntity<List<RadarDeteccionesCentroRepartoV2>>(radarArchivoService.buscarListaDeteccionesValidas(radarArchivoId), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/listaDeteccionInvalidas", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('LISTA_DETECCION_INVALIDAS')")
	public ResponseEntity<List<RadarDeteccionesCentroRepartoV2>>  buscarDeteccionesInvalidas(@RequestParam("radarArchivoId") Long radarArchivoId) {
		
		return new ResponseEntity<List<RadarDeteccionesCentroRepartoV2>>(radarArchivoService.buscarListaDeteccionesInvalidas(radarArchivoId), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/recomplementCentroRepart", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('RECOMPLEMENT_CENTRO_REPART')")
	public ResponseEntity<Boolean>  recomplementarCentroReparto(@RequestParam("radarArchivoId") Long radarArchivoId) {
		UsuarioFirmadoVO usuarioFirmadoVO = usuarioFirmadoService.getUsuarioFirmadoVO(); 
		//setEstatusProcesoId(RECOMPLEMENTANDO_CENTRO_REPARTO, empleadoId, 1);
		System.out.println("Estas en /recomplementCentroRepart");
		
		return new ResponseEntity<Boolean>(radarArchivoService.actualizarArchivoProcesoId(radarArchivoId,ArchivoBatchEstatusEnum.RECOMPLEMENTANDO_CENTRO_REPARTO.getEstatusProceso(),usuarioFirmadoVO.getId(),1), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/codigoPostalReasignado", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('CODIGO_POSTAL_REASIGNADO')")
	public ResponseEntity<Boolean>  cancelaProceso(@RequestParam("radarArchivoId") Long radarArchivoId) {
		UsuarioFirmadoVO usuarioFirmadoVO = usuarioFirmadoService.getUsuarioFirmadoVO(); 
		System.out.println("Estas en /codigoPostalReasignado");
		radarArchivoService.updateAllCR(radarArchivoId);
		return new ResponseEntity<Boolean>(radarArchivoService.actualizarArchivoProcesoId(radarArchivoId,ArchivoBatchEstatusEnum.RECOMPLEMENTANDO_CENTRO_REPARTO.getEstatusProceso(),usuarioFirmadoVO.getId(),1), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/codigoPostalReasignado2", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('CODIGO_POSTAL_REASIGNADO')")
	public ResponseEntity<Boolean>  cancelaProceso2(@RequestParam("radarArchivoId") Long radarArchivoId) {
		UsuarioFirmadoVO usuarioFirmadoVO = usuarioFirmadoService.getUsuarioFirmadoVO(); 
		System.out.println("Estas en /codigoPostalReasignado2");
		
		return new ResponseEntity<Boolean>(radarArchivoService.actualizarArchivoProcesoId(radarArchivoId,ArchivoBatchEstatusEnum.CODIGO_POSTAL_REASIGNADO.getEstatusProceso(),usuarioFirmadoVO.getId(),1), HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/cancelarProceso", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('CANCELACION_ARCHIVO_RADARES')")
	public ResponseEntity<Boolean> consultaLotes(@RequestParam(value = "lote") Long idArchivo,
															    @RequestParam(value = "motivo") String motivo) throws BusinessException{
		Long usuario = usuarioFirmadoService.getUsuarioFirmadoVO().getId();
		
		ArchivoBatchFinanzasVO objectVO = radarArchivoProcesadosService.cargarArchivo(idArchivo.toString());
		objectVO.setModificadoPorId(usuario);
		motivo =  "";
		radarArchivoProcesadosService.cancelarArchivo(objectVO, motivo);
		return new ResponseEntity<Boolean>(true, HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/radarCambiaCpDesh", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('RADAR_CAMBIA_CP_DESH')")
	public ResponseEntity<Boolean>  radarCambiaCpDesh(@RequestParam("idCP") Long idCP, @RequestParam("radarArchivoId") Long radarArchivoId ) {
		UsuarioFirmadoVO usuarioFirmadoVO = usuarioFirmadoService.getUsuarioFirmadoVO(); 
		System.out.println("Datos a sehabilitar: "+idCP+" "+radarArchivoId+" "+usuarioFirmadoVO.getId());
		return new ResponseEntity<Boolean>(radarArchivoService.updateDetecciones(idCP, radarArchivoId, usuarioFirmadoVO.getId()), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/cambiaThisCP", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('RADAR_CAMBIA_CP_DESH')")
	public ResponseEntity<Boolean>  cambiaThisCP(
			@RequestParam("radarArchivoId") Long radarArchivoId,
			@RequestParam("Cp") String Cp, 
			@RequestParam("ids") List<Integer> ids ) {
		UsuarioFirmadoVO usuarioFirmadoVO = usuarioFirmadoService.getUsuarioFirmadoVO(); 
		boolean flag=false;
		for(int i=0;i<ids.size();i++) {
			flag=radarArchivoService.updateThisCP(radarArchivoId,Cp, ids.get(i).longValue(), usuarioFirmadoVO.getId());
		}
		return new ResponseEntity<Boolean>(flag, HttpStatus.OK);
	}
	
	
	
	
	@RequestMapping(value = "/radarCambiaCpHabil", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('RADAR_CAMBIA_CP_HABIL')")
	public ResponseEntity<Boolean>  radarCambiaCpHabil(@RequestParam("idCP") Long idCP, @RequestParam("CP") String CP,  @RequestParam("radarArchivoId") Long radarArchivoId ) {
		UsuarioFirmadoVO usuarioFirmadoVO = usuarioFirmadoService.getUsuarioFirmadoVO(); 
		
		return new ResponseEntity<Boolean>(radarArchivoService.cambiaCPDeteccion(idCP,CP, usuarioFirmadoVO.getId(), radarArchivoId), HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/asignarLineasCapt", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('ASIGNAR_LINEAS_DE_CAPTURA')")
	public ResponseEntity<Boolean>  asignarLineasCapt(@RequestParam("radarArchivoId") Long radarArchivoId ) {
	UsuarioFirmadoVO usuarioFirmadoVO = usuarioFirmadoService.getUsuarioFirmadoVO(); 
		
		return new ResponseEntity<Boolean>(radarArchivoService.actualizarArchivoProcesoId(radarArchivoId,ArchivoBatchEstatusEnum.ASIGNANDO_LC_ARCHIVO_INFRACCIONES.getEstatusProceso(),usuarioFirmadoVO.getId(),1), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/actualizaCertificadoValido", method = RequestMethod.GET)
//	//@PreAuthorize("hasAnyAuthority('ASIGNAR_LINEAS_DE_CAPTURA')")
	public ResponseEntity<Boolean> actualizaCertificadoValido(
			@RequestParam("radarArchivoId") Long radarArchivoId, 
			@RequestParam("empId") Long empId, 
			@RequestParam("placaOficial") String placaOficial, 
			@RequestParam("empRFC") String empRFC, 
			@RequestParam("pwd") String pwd ){
		UsuarioFirmadoVO usuarioFirmadoVO = usuarioFirmadoService.getUsuarioFirmadoVO();
		
		radarArchivoService.insertaParametrosFirma(radarArchivoId, empId, placaOficial, empRFC, pwd);
		return new ResponseEntity<Boolean>(radarArchivoService.actualizarArchivoProcesoId(radarArchivoId,46,usuarioFirmadoVO.getId(),1), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/validarUsuarioActivo", method = RequestMethod.GET)
	public ResponseEntity<Boolean> validarUsuarioActivo(@RequestParam("placaOficial") String placaOficial){
		return new ResponseEntity<Boolean>(radarArchivoService.validarUsuarioActivo(placaOficial), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/radarValidaPassCert", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('CERTIFICADOS_VALIDA')")
	public @ResponseBody Map<String, Object> validaParidad(
			@RequestParam("placaOficial") String placaOficial,
			@RequestParam(value = "pwd") String pwd) {

		Map<String, Object> response = new HashMap<String, Object>();

		response = radarArchivoService.validarPassCert(placaOficial, pwd);

		return response;
	}
}
