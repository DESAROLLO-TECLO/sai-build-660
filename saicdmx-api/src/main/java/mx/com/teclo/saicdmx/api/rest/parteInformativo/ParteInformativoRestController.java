package mx.com.teclo.saicdmx.api.rest.parteInformativo;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import mx.com.teclo.saicdmx.negocio.service.catalogos.CatalogoService;
import mx.com.teclo.saicdmx.negocio.service.comun.UsuarioFirmadoService;
import mx.com.teclo.saicdmx.negocio.service.empleado.EmpleadoService;
import mx.com.teclo.saicdmx.negocio.service.parteinformativo.DocumentosService;
import mx.com.teclo.saicdmx.negocio.service.parteinformativo.ParteInformativoBoletaSancionService;
import mx.com.teclo.saicdmx.negocio.service.parteinformativo.ParteInformativoInfracsService;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.empleados.EmpleadosDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.parteinformativo.ParteInformativoBoletaInfracsDTO;
import mx.com.teclo.saicdmx.persistencia.vo.comuns.FilterValuesVO;
import mx.com.teclo.saicdmx.persistencia.vo.empleados.EmpleadosPorPlacaVO;
import mx.com.teclo.saicdmx.persistencia.vo.parteinformativo.ParteInformativoBoletaSancionConsultaVO;
import mx.com.teclo.saicdmx.persistencia.vo.parteinformativo.ParteInformativoBoletaSancionModificacionVO;
import mx.com.teclo.saicdmx.persistencia.vo.parteinformativo.ParteInformativoBoletaSancionNuevaVO;
import mx.com.teclo.saicdmx.persistencia.vo.parteinformativo.ParteInformativoCDocsConsultaVO;
import mx.com.teclo.saicdmx.persistencia.vo.parteinformativo.ParteInformativoCDocsNuevoVO;
import mx.com.teclo.saicdmx.persistencia.vo.parteinformativo.ParteInformativoCDocsVO;
import mx.com.teclo.saicdmx.persistencia.vo.parteinformativo.ParteInformativoInfracsPorBolsVO;
import mx.com.teclo.saicdmx.persistencia.vo.parteinformativo.ParteInformativoInfracsPorDocsVO;
import mx.com.teclo.saicdmx.util.comun.ResponseConverter;
import mx.com.teclomexicana.arquitectura.ortogonales.exception.BusinessException;
import mx.com.teclomexicana.arquitectura.ortogonales.exception.NotFoundException;

@RestController
public class ParteInformativoRestController {

	@Autowired
	private DocumentosService documentosService;
	
	@Autowired
	private ParteInformativoBoletaSancionService parteInformativoBoletaSancionService;
	
	@Autowired
	private ParteInformativoInfracsService parteInformativoInfracsService;
		
	
	@Autowired
	private CatalogoService catalogoService;
	
	@Autowired
	private EmpleadoService empleadoService;
	
	@Autowired
	private UsuarioFirmadoService usuarioFirmadoService;
	
	/*CONSULTAS*/
	@RequestMapping(value = "/documentosPI", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('BUSCAR_DOCUMENTOS')")
	public ResponseEntity<List<ParteInformativoCDocsConsultaVO>> buscarPIDocumentos(@RequestParam(value = "tipoBusqueda" ) Integer tipoBusqueda, @RequestParam(value = "valor" ) String valor) throws NotFoundException {
		List<ParteInformativoCDocsConsultaVO> documentos = null;
		documentos = documentosService.buscarPIDocumentos(tipoBusqueda, valor);
		if (documentos.isEmpty()) {
			throw new NotFoundException("No se encontraron registros");
		}	
		return new ResponseEntity<List<ParteInformativoCDocsConsultaVO>>(documentos, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/boletasPI", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('BUSCAR_BOLETAS')")
	public ResponseEntity<List<ParteInformativoBoletaSancionConsultaVO>> buscarPIBoletas(@RequestParam(value = "tipoBusqueda" ) Integer tipoBusqueda, @RequestParam(value = "valor" ) String valor) throws NotFoundException {
		List<ParteInformativoBoletaSancionConsultaVO> boletas = null;
		boletas = parteInformativoBoletaSancionService.buscarPIBoletas(tipoBusqueda, valor);
		if (boletas.isEmpty()) {
			throw new NotFoundException("No se encontraron registros");
		}	
		return new ResponseEntity<List<ParteInformativoBoletaSancionConsultaVO>>(boletas, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/consultarEmpleadoPorPlaca", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('BUSCAR_EMPLEADO_POR_PLACA')")
	public ResponseEntity<EmpleadosPorPlacaVO> consultarEmpleadoPorPlaca(@RequestParam("placa") String placa) throws NotFoundException{
		EmpleadosDTO empleadoDTO = empleadoService.getEmpleadoByPlaca(placa);
		if (empleadoDTO == null) {
			throw new NotFoundException("El oficial no existe en el sistema");
		}
		EmpleadosPorPlacaVO empleadoVO = ResponseConverter.copiarPropiedadesFull(empleadoDTO, EmpleadosPorPlacaVO.class);
		//ResponseConverter.copiarPropriedades(empleadoVO, empleadoDTO);
		return new ResponseEntity<EmpleadosPorPlacaVO>(empleadoVO, HttpStatus.OK);
	}
	
	/*FILTROS*/
	@RequestMapping(value = "/filterValuesDocumentos", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('FILTRO_BUSQUEDA_DOCUMENTOS')")
	public ResponseEntity<List<FilterValuesVO>> filtroPIDocumentos() throws NotFoundException {
		List<FilterValuesVO> filterValues = catalogoService.filtroPIDocumentos();
		return new ResponseEntity<List<FilterValuesVO>>(filterValues, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/filterValuesBoletas", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('FILTRO_BUSQUEDA_BOLETAS')")
	public ResponseEntity<List<FilterValuesVO>> filtroPIBoletas() throws NotFoundException {
		List<FilterValuesVO> filterValues = catalogoService.filtroPIBoletas();
		return new ResponseEntity<List<FilterValuesVO>>(filterValues, HttpStatus.OK);
	}
	
	/*OPCIONES*/
	@RequestMapping(value = "/opcionesBoletas", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('SITUACIONES_BOLETA')")
	public ResponseEntity<List<FilterValuesVO>> opcionesBoletas() throws NotFoundException {
		List<FilterValuesVO> optionsValues = catalogoService.opcionesSituacionesBoletas();
		return new ResponseEntity<List<FilterValuesVO>>(optionsValues, HttpStatus.OK);
	}

	/*CONSULTAS POR ID*/
	@RequestMapping(value = "/consultarDocumento/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasAnyAuthority('BUSCAR_DOCUMENTO_POR_ID')")
	public ResponseEntity<ParteInformativoCDocsVO> consultarDocumento(@PathVariable("id") long documentoId) {
		ParteInformativoCDocsVO documentoVO = documentosService.buscarDocumentoPorId(documentoId);
		if (documentoVO == null) {
			return new ResponseEntity<ParteInformativoCDocsVO>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<ParteInformativoCDocsVO>(documentoVO, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/consultarBoleta/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasAnyAuthority('BUSCAR_BOLETA_POR_ID')")
	public ResponseEntity<ParteInformativoBoletaSancionModificacionVO> consultarBoleta(@PathVariable("id") long boletaId) {
		ParteInformativoBoletaSancionModificacionVO boletaVO = parteInformativoBoletaSancionService.buscarBoletaPorId(boletaId);
		if (boletaVO == null) {
			return new ResponseEntity<ParteInformativoBoletaSancionModificacionVO>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<ParteInformativoBoletaSancionModificacionVO>(boletaVO, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/consultarInfraccionesPorDocumento", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('BUSCAR_INFRACCIONES_POR_DOCUMENTO')")
	public ResponseEntity<List<ParteInformativoInfracsPorDocsVO>> consultarInfraccionesPorDocumento(@RequestParam("documentoId") long documentoId) throws NotFoundException {
		//List<ParteInformativoCDocsInfracsDTO> listaInfraccionesDTO = null;
		List<ParteInformativoInfracsPorDocsVO> listaInfraccionesVO = parteInformativoInfracsService.buscarInfraccionesPorDocumento(documentoId);
		if (listaInfraccionesVO.isEmpty()) {
			throw new NotFoundException("No se encontraron infracciones");
		}
		//List<ParteInformativoInfracsPorDocsVO> listaInfraccionesVO = ResponseConverter.converterLista(new ArrayList<>(), listaInfraccionesDTO, ParteInformativoInfracsPorDocsVO.class);
		return new ResponseEntity<List<ParteInformativoInfracsPorDocsVO>>(listaInfraccionesVO, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/consultarInfraccionesPorBoleta", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('BUSCAR_INFRACCIONES_POR_BOLETA')")
	public ResponseEntity<List<ParteInformativoInfracsPorBolsVO>> consultarInfraccionesPorBoleta(@RequestParam("boletaId") long boletaId) throws NotFoundException {
		List<ParteInformativoInfracsPorBolsVO> listaInfraccionesVO = null;
		listaInfraccionesVO = parteInformativoInfracsService.buscarInfraccionesPorBoleta(boletaId);
		if (listaInfraccionesVO.isEmpty()) {
			throw new NotFoundException("No se encontraron registros");
		}
	//	List<ParteInformativoInfracsPorBolsVO> listaInfraccionesVO = ResponseConverter.converterLista(new ArrayList<>(), listaInfraccionesDTO, ParteInformativoInfracsPorBolsVO.class);
		return new ResponseEntity<List<ParteInformativoInfracsPorBolsVO>>(listaInfraccionesVO, HttpStatus.OK);
	}
	
	/*CRUD*/
	/***
	 * @author Jesus Gutierrez
	 * @param documentoVO
	 * @param addInfracciones
	 * @param deleteInfracciones
	 * @param ucBuilder
	 * @return
	 * @throws BusinessException
	 * @throws ParseException 
	 */	
	@RequestMapping(value = "/actualizaDocumento", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasAnyAuthority('ACTUALIZAR_DOCUMENTO')")
	public ResponseEntity<ParteInformativoCDocsVO> actualizaDocumento(@RequestParam(value="documentoVO") String documentoVO, @RequestParam(value="addInfracciones") String addInfracciones, @RequestParam(value="deleteInfracciones") String deleteInfracciones, UriComponentsBuilder ucBuilder) throws BusinessException, ParseException {
		ParteInformativoCDocsVO documento = crearParteInformativoCDocsVO(documentoVO);
		List<ParteInformativoInfracsPorDocsVO> infraccionesAdd = crearParteInformativoInfracsPorDocsVO(addInfracciones);
		List<ParteInformativoInfracsPorDocsVO> infraccionesDelete = crearParteInformativoInfracsPorDocsVO(deleteInfracciones);
		
		documento.setModificadoPor(usuarioFirmadoService.getUsuarioFirmadoVO().getId());
		
		ParteInformativoCDocsVO documentomodificado = documentosService.modificacionDocumento(documento,infraccionesAdd,infraccionesDelete);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/actualizaDocumento/{id}").buildAndExpand(documento.getIdPi()).toUri());
		return new ResponseEntity<ParteInformativoCDocsVO>(documentomodificado,headers, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/actualizaBoleta", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasAnyAuthority('ACTUALIZAR_BOLETA')")
	public ResponseEntity<ParteInformativoBoletaSancionModificacionVO> actualizaBoleta(@RequestParam(value="boletaVO") String boletaVO, @RequestParam(value="addInfracciones") String addInfracciones, @RequestParam(value="deleteInfracciones") String deleteInfracciones, UriComponentsBuilder ucBuilder) throws BusinessException {
		ParteInformativoBoletaSancionModificacionVO boleta = crearParteInformativoBoletaSancionModificacionVO(boletaVO);
		
		List<ParteInformativoInfracsPorBolsVO> infraccionesAdd = crearParteInformativoInfracsPorBolsVO(addInfracciones);
		List<ParteInformativoInfracsPorBolsVO> infraccionesDelete = crearParteInformativoInfracsPorBolsVO(deleteInfracciones);
		
		boleta.setModificadoPor(usuarioFirmadoService.getUsuarioFirmadoVO().getId());
		
		ParteInformativoBoletaSancionModificacionVO boletamodificado = parteInformativoBoletaSancionService.modificacionBoleta(boleta,infraccionesAdd,infraccionesDelete);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/actualizaBoleta/{id}").buildAndExpand(boleta.getId()).toUri());
		return new ResponseEntity<ParteInformativoBoletaSancionModificacionVO>(boletamodificado,headers, HttpStatus.CREATED);
	}
	
	/***
	 * @author Jesus Gutierrez
	 * @param documentoVO
	 * @param addInfracciones
	 * @param ucBuilder
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping(value = "/creaDocumento", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasAnyAuthority('CREAR_DOCUMENTO')")
	public ResponseEntity<ParteInformativoCDocsNuevoVO> creaDocumento(@RequestParam(value="documentoVO") String documentoVO, @RequestParam(value="addInfracciones") String addInfracciones, UriComponentsBuilder ucBuilder) throws BusinessException {
		ParteInformativoCDocsNuevoVO documento = crearParteInformativoCDocsNuevoVO(documentoVO);
		List<ParteInformativoInfracsPorDocsVO> infraccionesAdd = crearParteInformativoInfracsPorDocsVO(addInfracciones);
		documento.setCreadoPor(usuarioFirmadoService.getUsuarioFirmadoVO().getId());
		
		ParteInformativoCDocsNuevoVO documentonuevo = documentosService.crearDocumento(documento,infraccionesAdd);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/creaDocumento/{id}").buildAndExpand(documentonuevo.getId()).toUri());
		return new ResponseEntity<ParteInformativoCDocsNuevoVO>(documentonuevo,headers, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/creaBoleta", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasAnyAuthority('CREAR_BOLETA')")
	public ResponseEntity<ParteInformativoBoletaSancionNuevaVO> creaBoleta(@RequestParam(value="boletaVO") String boletaVO, @RequestParam(value="addInfracciones") String addInfracciones, UriComponentsBuilder ucBuilder) throws BusinessException {
		ParteInformativoBoletaSancionNuevaVO boleta = crearParteInformativoBoletaSancionNuevaVO(boletaVO);
		List<ParteInformativoInfracsPorBolsVO> infraccionesAdd = crearParteInformativoInfracsPorBolsVO(addInfracciones);
		boleta.setCreadoPor(usuarioFirmadoService.getUsuarioFirmadoVO().getId());
		
		ParteInformativoBoletaSancionNuevaVO boletanueva = parteInformativoBoletaSancionService.crearBoleta(boleta, infraccionesAdd);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/creaBoleta/{id}").buildAndExpand(boletanueva.getId()).toUri());
		return new ResponseEntity<ParteInformativoBoletaSancionNuevaVO>(boletanueva,headers, HttpStatus.CREATED);
	}
	
	/***
	 * @author Jesus Gutierrez
	 * @param jsonDocumentoVO
	 * @return Un objeto de tipo ParteInformativoCDocsNuevoVO (Creacion de documento)
	 */
	private ParteInformativoCDocsNuevoVO crearParteInformativoCDocsNuevoVO(String jsonDocumentoVO){
		ParteInformativoCDocsNuevoVO parteInformativoCDocsNuevoVO = null;
        try {
        	ObjectMapper mapper = new ObjectMapper();
           	mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
           	parteInformativoCDocsNuevoVO = mapper.readValue(jsonDocumentoVO.toString(), ParteInformativoCDocsNuevoVO.class);
        } catch (IOException e) {
        	e.printStackTrace();
        }
        return parteInformativoCDocsNuevoVO;
	}
	
	/***
	 * @author Jesus Gutierrez
	 * @param jsonDocumentoVO
	 * @return Un objeto de tipo ParteInformativoCDocsVO (Modificacion de documento) 
	 */
	private ParteInformativoCDocsVO crearParteInformativoCDocsVO(String jsonDocumentoVO){
		ParteInformativoCDocsVO parteInformativoCDocsVO = null;
        try {
        	ObjectMapper mapper = new ObjectMapper();
           	mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
           	parteInformativoCDocsVO = mapper.readValue(jsonDocumentoVO.toString(), ParteInformativoCDocsVO.class);
        } catch (IOException e) {
        	e.printStackTrace();
        }
        return parteInformativoCDocsVO;
	}

	/***
	 * @author Jesus Gutierrez
	 * @param jsonBoletaVO
	 * @return Un objeto de tipo ParteInformativoBoletaSancionModificacionVO (Modificacion de boleta)
	 */
	private ParteInformativoBoletaSancionModificacionVO crearParteInformativoBoletaSancionModificacionVO(String jsonBoletaVO){
		ParteInformativoBoletaSancionModificacionVO parteInformativoBoletaSancionModificacionVO = null;
        try {
        	ObjectMapper mapper = new ObjectMapper();
           	mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
           	parteInformativoBoletaSancionModificacionVO = mapper.readValue(jsonBoletaVO.toString(), ParteInformativoBoletaSancionModificacionVO.class);
        } catch (IOException e) {
        	e.printStackTrace();
        }
        return parteInformativoBoletaSancionModificacionVO;
	}
	
	/***
	 * @author Jesus Gutierrez
	 * @param jsonDocumentoVO
	 * @return Un objeto de tipo ParteInformativoBoletaSancionNuevaVO (Creacion de boleta)
	 */
	private ParteInformativoBoletaSancionNuevaVO crearParteInformativoBoletaSancionNuevaVO(String jsonDocumentoVO){
		ParteInformativoBoletaSancionNuevaVO parteInformativoBoletaSancionNuevaVO = null;
        try {
        	ObjectMapper mapper = new ObjectMapper();
           	mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
           	parteInformativoBoletaSancionNuevaVO = mapper.readValue(jsonDocumentoVO.toString(), ParteInformativoBoletaSancionNuevaVO.class);
        } catch (IOException e) {
        	e.printStackTrace();
        }
        return parteInformativoBoletaSancionNuevaVO;
	}
	
	/***
	 * @author Jesus Gutierrez
	 * @param jsonInfraccionesVO
	 * @return Una lista de tipo ParteInformativoInfracPorDocsVO
	 */
	private List<ParteInformativoInfracsPorDocsVO> crearParteInformativoInfracsPorDocsVO(String jsonInfraccionesVO){
		List<ParteInformativoInfracsPorDocsVO> parteInformativoInfracsPorDocsVO = null;
        try {
        	ObjectMapper mapper = new ObjectMapper();
           	mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
           	parteInformativoInfracsPorDocsVO = mapper.readValue(jsonInfraccionesVO, new TypeReference<List<ParteInformativoInfracsPorDocsVO>>(){});
        } catch (IOException e) {
        	e.printStackTrace();
        }
        return parteInformativoInfracsPorDocsVO;
	}
	
	/***
	 * @author Jesus Gutierrez
	 * @param jsonInfraccionesVO
	 * @return Una lista de tipo ParteInformativoInfracsPorBolsVO
	 */
	private List<ParteInformativoInfracsPorBolsVO> crearParteInformativoInfracsPorBolsVO(String jsonInfraccionesVO){
		List<ParteInformativoInfracsPorBolsVO> parteInformativoInfracsPorBolsVO = null;
        try {
        	ObjectMapper mapper = new ObjectMapper();
           	mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
           	parteInformativoInfracsPorBolsVO = mapper.readValue(jsonInfraccionesVO, new TypeReference<List<ParteInformativoInfracsPorBolsVO>>(){});
        } catch (IOException e) {
        	e.printStackTrace();
        }
        return parteInformativoInfracsPorBolsVO;
	}
}
