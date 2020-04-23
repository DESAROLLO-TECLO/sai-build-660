package mx.com.teclo.saicdmx.api.rest.controlsuministros;

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
import org.springframework.web.util.UriComponentsBuilder;

import mx.com.teclo.saicdmx.negocio.service.comun.UsuarioFirmadoService;
import mx.com.teclo.saicdmx.negocio.service.controlsuministros.AuxiliaresService;
import mx.com.teclo.saicdmx.negocio.service.controlsuministros.ControlAlmacenService;
import mx.com.teclo.saicdmx.negocio.service.controlsuministros.DevolucionesService;
import mx.com.teclo.saicdmx.negocio.service.controlsuministros.SuministroAreasService;
import mx.com.teclo.saicdmx.persistencia.vo.controlsuministros.ControlAlmacenVO;
import mx.com.teclo.saicdmx.persistencia.vo.controlsuministros.DelegadoAuxiliarVO;
import mx.com.teclo.saicdmx.persistencia.vo.controlsuministros.DevolucionesVO;
import mx.com.teclo.saicdmx.persistencia.vo.controlsuministros.OficialEliminarVO;
import mx.com.teclo.saicdmx.persistencia.vo.controlsuministros.OficialModificacionVO;
import mx.com.teclo.saicdmx.persistencia.vo.controlsuministros.OficialNuevoVO;
import mx.com.teclo.saicdmx.persistencia.vo.controlsuministros.SuministroAreasVO;
import mx.com.teclomexicana.arquitectura.ortogonales.exception.BusinessException;
import mx.com.teclomexicana.arquitectura.ortogonales.exception.NotFoundException;

@RestController
public class ControlSuministrosRestController {
	
	@Autowired
	private ControlAlmacenService controlAlmacenService;
	
	@Autowired
	private SuministroAreasService suministroAreasService;
	
	@Autowired
	private DevolucionesService devolucionesService;
	
	@Autowired
	private AuxiliaresService auxiliaresService;
	
	@Autowired
	private UsuarioFirmadoService usuarioService;
	
	
	@RequestMapping(value = "/controlAlmacen", method = RequestMethod.POST)	
	@PreAuthorize("hasAnyAuthority('ALTA_CONTROL_ALMACEN')")
	public ResponseEntity<ControlAlmacenVO> altaAlmacen(@RequestBody ControlAlmacenVO almacenVO, UriComponentsBuilder ucBuilder) throws BusinessException {
		Long user = usuarioService.getUsuarioFirmadoVO().getId();
		almacenVO.setUserID(user);
		ControlAlmacenVO controlAlmacen = controlAlmacenService.altaAlmacen(almacenVO);
		
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/controlAlmacen/{id}").buildAndExpand(almacenVO.getId()).toUri()); // Verificar si se usa ID en BD
		
		return new ResponseEntity<ControlAlmacenVO>(controlAlmacen,headers, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/suministroAreas", method = RequestMethod.POST)	
	@PreAuthorize("hasAnyAuthority('ALTA_SUMINISTRO')")
	public ResponseEntity<SuministroAreasVO> altaSuministro (@RequestBody SuministroAreasVO suministroVO, UriComponentsBuilder ucBuilder) throws BusinessException {
		Long user = usuarioService.getUsuarioFirmadoVO().getId();
		suministroVO.setIdUser(user);
		SuministroAreasVO suministroAreas = suministroAreasService.altaSuministro(suministroVO);
		
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/suministroAreas/{id}").buildAndExpand(suministroVO.getId()).toUri()); // Verificar si se usa ID en BD
		
		return new ResponseEntity<SuministroAreasVO>(suministroAreas,headers, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/devoluciones", method = RequestMethod.POST)	
	@PreAuthorize("hasAnyAuthority('ALTA_DEVOLUCION')")
	public ResponseEntity<DevolucionesVO> altaDevolucion (@RequestBody DevolucionesVO devolucionVO, UriComponentsBuilder ucBuilder) throws BusinessException {

		Long user = usuarioService.getUsuarioFirmadoVO().getId();
		devolucionVO.setIdUser(Long.valueOf(user).intValue());
		DevolucionesVO devoluciones = devolucionesService.altaDevolucion(devolucionVO);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/devoluciones/{id}").buildAndExpand(devolucionVO.getId()).toUri()); // Verificar si se usa ID en BD
		
		return new ResponseEntity<DevolucionesVO>(devoluciones,headers, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/modificarAuxiliar/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//	@PreAuthorize("hasAnyAuthority('BUSCAR_AUXILIAR_POR_ID')")
	public ResponseEntity<DelegadoAuxiliarVO> buscarAuxiliarPorId(@PathVariable("id") Long id_registro) throws NotFoundException{
		DelegadoAuxiliarVO auxiliarVO = auxiliaresService.buscarAuxiliarPorId(id_registro);
		if (auxiliarVO == null) {
			throw new NotFoundException("No se encontraron Auxiliares");
		}	
		return new ResponseEntity<DelegadoAuxiliarVO>(auxiliarVO, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/consultarPlaca", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('BUSCAR_EMPLEADO_POR_PLACA')")
	public ResponseEntity<DelegadoAuxiliarVO> buscarAuxiliarPorPlaca(@RequestParam("oficial_placa") String oficial_placa)throws NotFoundException {
		DelegadoAuxiliarVO auxiliarVO = auxiliaresService.buscarAuxiliarPorPlaca(oficial_placa);
		if (auxiliarVO == null) {
			throw new NotFoundException("No se encontro la placa");
		}	
		return new ResponseEntity<DelegadoAuxiliarVO>(auxiliarVO, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/cambiarAuxiliar", method = RequestMethod.POST)
	@PreAuthorize("hasAnyAuthority('CAMBIAR_AUXILIAR')")
	public ResponseEntity<OficialModificacionVO> cambiarAuxiliar(@RequestBody OficialModificacionVO valores, UriComponentsBuilder ucBuilder) throws BusinessException {
		valores.setIdUser(usuarioService.getUsuarioFirmadoVO().getId());;
		OficialModificacionVO oficialAcciones = auxiliaresService.cambiarAuxiliar(valores);
	
		return new ResponseEntity<OficialModificacionVO>(oficialAcciones, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/altaAuxiliar/{id}/{id2}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasAnyAuthority('BUSCAR_AUXILIAR_AREA_REGION')")
	public ResponseEntity<DelegadoAuxiliarVO> buscarAuxiliarAreaRegion(@PathVariable("id") Long reg_id,@PathVariable("id2") Long upc_id) throws NotFoundException{
		DelegadoAuxiliarVO auxiliarVO = auxiliaresService.buscarAuxiliarAreaRegion(reg_id,upc_id);
		if (auxiliarVO == null) {
			throw new NotFoundException("No se encontraron regiones");
		}	
		return new ResponseEntity<DelegadoAuxiliarVO>(auxiliarVO, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/nuevoAuxiliar", method = RequestMethod.POST)	
	@PreAuthorize("hasAnyAuthority('CREAR_AUXILIAR')")
	public ResponseEntity<OficialNuevoVO> altaAuxiliar(@RequestBody OficialNuevoVO valoresAux, UriComponentsBuilder ucBuilder) throws BusinessException {
		Long user = usuarioService.getUsuarioFirmadoVO().getId();
		valoresAux.setIdUser(Long.valueOf(user).intValue());
		OficialNuevoVO oficialAcciones = auxiliaresService.altaAuxiliar(valoresAux);	
		return new ResponseEntity<OficialNuevoVO>(oficialAcciones, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/eliminarAuxiliar", method = RequestMethod.POST)
	@PreAuthorize("hasAnyAuthority('ELIMINAR_AUXILIAR')")
	public ResponseEntity<OficialEliminarVO> eliminarAuxiliar(@RequestBody OficialEliminarVO valoresAuxBaja, UriComponentsBuilder ucBuilder) throws BusinessException {
		valoresAuxBaja.setIdUser(usuarioService.getUsuarioFirmadoVO().getId());
		OficialEliminarVO auxiliarBajaVO = auxiliaresService.eliminarAuxiliar(valoresAuxBaja);
		 auxiliaresService.altaBitacoraCambios(valoresAuxBaja);
		return new ResponseEntity<OficialEliminarVO>(auxiliarBajaVO, HttpStatus.CREATED);
	}
	
}