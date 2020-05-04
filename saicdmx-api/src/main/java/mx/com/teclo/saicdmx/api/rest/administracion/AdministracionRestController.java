package mx.com.teclo.saicdmx.api.rest.administracion;

import java.io.ByteArrayOutputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import mx.com.teclo.saicdmx.negocio.service.administracion.AdministracionService;
import mx.com.teclo.saicdmx.negocio.service.caja.CajaService;
import mx.com.teclo.saicdmx.negocio.service.comun.UsuarioFirmadoService;
import mx.com.teclo.saicdmx.negocio.service.empleado.EmpleadoService;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.administracion.PerfilDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.cajas.VCajaExtDesactivarDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.DepositosDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.RegionalesDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.empleados.EmpleadosDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.vo.catalogos.DepositosVO;
import mx.com.teclo.saicdmx.persistencia.hibernate.vo.catalogos.RegionalesUPCVO;
import mx.com.teclo.saicdmx.persistencia.hibernate.vo.catalogos.RegionalesVO;
import mx.com.teclo.saicdmx.persistencia.vo.administracion.AdminCajaParametrosVO;
import mx.com.teclo.saicdmx.persistencia.vo.administracion.AdminCajaVO;
import mx.com.teclo.saicdmx.persistencia.vo.administracion.AdminDepositoVO;
import mx.com.teclo.saicdmx.persistencia.vo.administracion.AdminPerfilesParamVO;
import mx.com.teclo.saicdmx.persistencia.vo.administracion.AdminUsuarioClaveVO;
import mx.com.teclo.saicdmx.persistencia.vo.administracion.AdminUsuarioEstatusVO;
import mx.com.teclo.saicdmx.persistencia.vo.administracion.AdscripcionVO;
import mx.com.teclo.saicdmx.persistencia.vo.administracion.MenuAdminVO;
import mx.com.teclo.saicdmx.persistencia.vo.administracion.OperacionesExtemporaneasVO;
import mx.com.teclo.saicdmx.persistencia.vo.administracion.PerfilesAdminVO;
import mx.com.teclo.saicdmx.persistencia.vo.administracion.UsuarioAdminVO;
import mx.com.teclo.saicdmx.persistencia.vo.caja.CajaVO;
import mx.com.teclo.saicdmx.persistencia.vo.caja.VCajaConsultaConTieneOperacionesVO;
import mx.com.teclo.saicdmx.persistencia.vo.caja.VCajaExtDesactivarVO;
import mx.com.teclo.saicdmx.persistencia.vo.caja.VFoliosCajaVO;
import mx.com.teclo.saicdmx.persistencia.vo.certificados.ConsultaUsersVO;
import mx.com.teclo.saicdmx.util.comun.ResponseConverter;
import mx.com.teclomexicana.arquitectura.ortogonales.exception.BusinessException;
import mx.com.teclomexicana.arquitectura.ortogonales.exception.NotFoundException;
import mx.com.teclomexicana.arquitectura.ortogonales.seguridad.vo.UsuarioFirmadoVO;

/**
 * @author javier07
 */
@RestController
@RequestMapping("/administracionController")
public class AdministracionRestController {

	@Autowired
	private AdministracionService administracionService;
	@Autowired
	private UsuarioFirmadoService usuarioFirmadoService;
	@Autowired
	private EmpleadoService empleadoService;
	@Autowired
	private CajaService cajaService;

	/**
	 * @author Sail
	 * @param clave
	 * @param nueva_clave
	 * @param repetir_clave
	 * @return List<String>
	 * @throws BusinessException
	 */
	@RequestMapping(value = "/cambiarClave", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('ADMINISTRACION_CAMBIAR_CLAVE')")
	public ResponseEntity<List<String>> cambiarClaveAcceso(@RequestParam(value = "clave") String clave,
			@RequestParam(value = "nueva") String nueva, @RequestParam(value = "repetir") String repetir)
			throws BusinessException {
		UsuarioFirmadoVO usuario = usuarioFirmadoService.getUsuarioFirmadoVO();
		List<String> message = new ArrayList<String>();
		if (!usuario.getPassword().equals(clave)) {
			throw new BusinessException("La contraseña actual es incorrecta");
		} else {
			if (empleadoService.validarPerfilEmpleado(usuario.getPerfilId(), 3)) {
				// Aplicar regex por ser de Teclo
				Pattern patron = Pattern
						.compile("(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[$@$!%*?&])[a-zA-Z0-9$@$!%*?&]{8,}");
				Matcher muestra = patron.matcher(nueva);
				if (muestra.find() && muestra.group(0) != null) {
					// GUARDAR EN BD
					AdminUsuarioClaveVO aucVO = administracionService.cambiarClaveAcceso(usuario.getId(), clave, nueva,
							repetir);
					message.add(String.valueOf(aucVO.getResultado()));
					message.add(aucVO.getMensaje());
				} else {
					// mandar error
					throw new BusinessException(
							"La contraseña no cumple los requisitos. Minimo 8 carácteres, mayúscula, minúscula, un numero y carácter especial");
				}
			} else {
				// No aplicar regex
				// GUARDAR EN BD
				AdminUsuarioClaveVO aucVO = administracionService.cambiarClaveAcceso(usuario.getId(), clave, nueva,
						repetir);
				message.add(String.valueOf(aucVO.getResultado()));
				message.add(aucVO.getMensaje());
			}
		}
		return new ResponseEntity<List<String>>(message, HttpStatus.OK);
	}

	/**
	 * @author javier07
	 * @param tipoBusqueda
	 * @param valor
	 * @return
	 * @throws NotFoundException
	 */
	@RequestMapping(value = "/buscarUsuarios", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('ADMINISTRACION_BUSCAR_USUARIOS')")
	public ResponseEntity<List<ConsultaUsersVO>> buscarUsuarios(@RequestParam(value = "tipoBusqueda") String tipoBusqueda, 
			@RequestParam(value = "valor") String valor, @RequestParam(value = "isAdmin") Boolean isAdmin,
			@RequestParam(value = "codigo") String codigoPerfil, @RequestParam(value = "isConsulta") Boolean isConsulta) throws NotFoundException {

		List<ConsultaUsersVO> listaConsulta = administracionService.obtenerListaUsuarios(tipoBusqueda, valor, isAdmin, codigoPerfil, isConsulta);
		if (listaConsulta.isEmpty()) {
			throw new NotFoundException("No se encontraron registros");

		}
		return new ResponseEntity<List<ConsultaUsersVO>>(listaConsulta, HttpStatus.OK);
	}

	/**
	 * @author javier07
	 * @param usuarioVO
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping(value = "/actualizaInformacion", method = RequestMethod.PUT, produces = "text/plain")
	@PreAuthorize("hasAnyAuthority('ADMINISTRACION_ACTUALIZAR_USUARIO')")
	public ResponseEntity<String> actualizarInformacion(@RequestBody UsuarioAdminVO usuarioVO)
			throws BusinessException {

		UsuarioFirmadoVO usuario = usuarioFirmadoService.getUsuarioFirmadoVO();
		EmpleadosDTO empleado = empleadoService.getEmpleadoById(usuario.getId());
		usuarioVO.setModificadoPor(empleado.getEmpId());
		String mensaje = administracionService.actualizarUsuario(usuarioVO);
		return new ResponseEntity<String>(mensaje, HttpStatus.OK);

	}

	/**
	 * @author javier07
	 * @param usuarioVO
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping(value = "/altaUsuario", method = RequestMethod.PUT, produces = "text/plain")
	@PreAuthorize("hasAnyAuthority('ADMINISTRACION_ALTA_USUARIO')")
	public ResponseEntity<String> AltUsuario(@RequestBody UsuarioAdminVO usuarioVO) throws BusinessException {

		UsuarioFirmadoVO usuario = usuarioFirmadoService.getUsuarioFirmadoVO();
		EmpleadosDTO empleado = empleadoService.getEmpleadoById(usuario.getId());
		usuarioVO.setModificadoPor(empleado.getEmpId());
		String mensaje = administracionService.nuevoUsuario(usuarioVO);
		return new ResponseEntity<String>(mensaje, HttpStatus.OK);

	}

	/**
	 * @author javier07
	 * @return
	 * @throws NotFoundException
	 */
	@RequestMapping(value = "/obtenerRegiones", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('ADMINISTRACION_OBTENER_REGIONES')")
	public ResponseEntity<List<RegionalesVO>> getRegiones() throws NotFoundException {

		List<RegionalesDTO> listaDTO = administracionService.obtenerRegionales();
		if (listaDTO.isEmpty()) {
			throw new NotFoundException("No se encontraron registros");
		}

		List<RegionalesVO> listaRegiones = ResponseConverter.converterLista(new ArrayList<>(), listaDTO,
				RegionalesVO.class);
		return new ResponseEntity<List<RegionalesVO>>(listaRegiones, HttpStatus.OK);
	}

	/**
	 * @author javier07
	 * @return
	 * @throws NotFoundException
	 */
	@RequestMapping(value = "/obtenerRegionesUPC", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('ADMINISTRACION_OBTENER_REGIONES_UPC')")
	public ResponseEntity<List<RegionalesUPCVO>> getRegiones_UPC() throws NotFoundException {

		List<RegionalesUPCVO> listaRegUPC = administracionService.obtenerRegionales_UPC();
		if (listaRegUPC.isEmpty()) {
			throw new NotFoundException("No se encontraron registros");
		}

		return new ResponseEntity<List<RegionalesUPCVO>>(listaRegUPC, HttpStatus.OK);
	}

	/**
	 * @author javier07
	 * @param adscripcionVO
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping(value = "/adscripcionAlta", method = RequestMethod.POST, produces = "text/plain")
	@PreAuthorize("hasAnyAuthority('ADMINISTRACION_ALTA_ADSCRIPCION')")
	public ResponseEntity<String> adscripcionAlta(@RequestBody AdscripcionVO adscripcionVO) throws BusinessException {

		UsuarioFirmadoVO usuario = usuarioFirmadoService.getUsuarioFirmadoVO();
		EmpleadosDTO empleado = empleadoService.getEmpleadoById(usuario.getId());
		adscripcionVO.setP_empid_modif((empleado.getEmpId()));
		String mensaje = administracionService.nuevaAdscripcion(adscripcionVO);
		return new ResponseEntity<String>(mensaje, HttpStatus.OK);

	}

	/**
	 * @author javier07
	 * @return
	 * @throws NotFoundException
	 */
	@RequestMapping(value = "/obtenerDepositos", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('ADMINISTRACION_OBTENER_DEPOSITOS')")
	public ResponseEntity<List<DepositosVO>> getDepositos() throws NotFoundException {

		List<DepositosDTO> listaDTO = administracionService.obtieneDepositosActivos();

		List<DepositosVO> listaDepositos = ResponseConverter.converterLista(new ArrayList<>(), listaDTO,
				DepositosVO.class);

		return new ResponseEntity<List<DepositosVO>>(listaDepositos, HttpStatus.OK);
	}

	/**
	 * @author javier07
	 * @param deposito
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping(value = "/modificaDeposito", method = RequestMethod.POST, produces = "text/plain")
	@PreAuthorize("hasAnyAuthority('ADMINISTRACION_MODIFICA_DEPOSITO')")
	public ResponseEntity<String> modificaDeposito(@RequestBody AdminDepositoVO deposito) throws BusinessException {

		UsuarioFirmadoVO usuario = usuarioFirmadoService.getUsuarioFirmadoVO();
		EmpleadosDTO empleado = empleadoService.getEmpleadoById(usuario.getId());
		deposito.setP_modificado_por(empleado.getEmpId());
		String mensaje = administracionService.modificaDeposito(deposito);
		return new ResponseEntity<String>(mensaje, HttpStatus.OK);

	}

	/**
	 * @author javier07
	 * @param adminUsuarioEstatusVO
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping(value = "/actualizarEstatus", method = RequestMethod.POST, produces = "text/plain")
	@PreAuthorize("hasAnyAuthority('ADMINISTRACION_ACTUALIZAR_ESTATUS_USUARIO')")
	public ResponseEntity<String> actualizarEstatus(@RequestBody AdminUsuarioEstatusVO adminUsuarioEstatusVO)
			throws BusinessException {

		UsuarioFirmadoVO usuario = usuarioFirmadoService.getUsuarioFirmadoVO();
		EmpleadosDTO empleado = empleadoService.getEmpleadoById(usuario.getId());
		adminUsuarioEstatusVO.setModificado_por_id(empleado.getEmpId());
		String mensaje = administracionService.actualizaEstatus(adminUsuarioEstatusVO);
		return new ResponseEntity<String>(mensaje, HttpStatus.OK);

	}

	/**
	 * @author javier07
	 * @param numCaja
	 * @return
	 * @throws NotFoundException
	 */
	@RequestMapping(value = "/consultaCaja", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('ADMINISTRACION_CONSULTA_CAJA')")
	public ResponseEntity<List<AdminCajaVO>> consultaCaja(@RequestParam(value = "numCaja") String numCaja)
			throws NotFoundException {

		List<AdminCajaVO> listaConsultaCaja = administracionService.consultaCaja(numCaja);

		if (listaConsultaCaja.isEmpty()) {
			throw new NotFoundException("No se encontraron registros");
		}

		return new ResponseEntity<List<AdminCajaVO>>(listaConsultaCaja, HttpStatus.OK);
	}

	/**
	 * @author javier07
	 * @param adminCajaParametrosVO
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping(value = "/actualizaCaja", method = RequestMethod.POST, produces = "text/plain")
	@PreAuthorize("hasAnyAuthority('ADMINISTRACION_ACTUALIZA_CAJA')")
	public ResponseEntity<String> actualizaCaja(@RequestBody AdminCajaParametrosVO adminCajaParametrosVO)
			throws BusinessException {

		UsuarioFirmadoVO usuario = usuarioFirmadoService.getUsuarioFirmadoVO();
		EmpleadosDTO empleado = empleadoService.getEmpleadoById(usuario.getId());
		adminCajaParametrosVO.setP_modificado_por(empleado.getEmpId());
		String mensaje = administracionService.modificaCaja(adminCajaParametrosVO);
		return new ResponseEntity<String>(mensaje, HttpStatus.OK);

	}

	/**
	 * @author Jesus Gutierrez
	 * @return <List<PerfilesAdminVO>
	 * @throws NotFoundException
	 */
	@RequestMapping(value = "/obtenerAllPerfiles", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('ADMINISTRACION_OBTENER_PERFILES')") //Perfiles web
	public ResponseEntity<List<PerfilesAdminVO>> getListaPerfiles() throws NotFoundException {
		List<PerfilDTO> listaDTO = administracionService.obtieneListaPerfiles();
		List<PerfilesAdminVO> listaPerfiles = ResponseConverter.converterLista(new ArrayList<>(), listaDTO,
				PerfilesAdminVO.class);
		return new ResponseEntity<List<PerfilesAdminVO>>(listaPerfiles, HttpStatus.OK);
	}

	/***
	 * @author Jesus Gutierrez
	 * @return
	 * @throws NotFoundException
	 */
	@RequestMapping(value = "/obtenerPerfilesPorUsuarioPerfil", method = RequestMethod.GET)
	// @PreAuthorize("hasAnyAuthority('ADMINISTRACION_OBTENER_PERFILES_POR_USUARIO_PERFIL')")
	public ResponseEntity<List<PerfilesAdminVO>> getListaPerfilesByUsuarioPerfil(@RequestParam("codigo") String codigo)
			throws NotFoundException { //Usuarios
		List<PerfilDTO> listaDTO = administracionService.listaPerfilesByPerfilUsuario(codigo);
		List<PerfilesAdminVO> listaPerfiles = ResponseConverter.converterLista(new ArrayList<>(), listaDTO,
				PerfilesAdminVO.class);
		return new ResponseEntity<List<PerfilesAdminVO>>(listaPerfiles, HttpStatus.OK);
	}

	/**
	 * @author javier07
	 * @param tipoBusqueda
	 * @param id
	 * @return
	 * @throws NotFoundException
	 */
	@RequestMapping(value = "/obtenerListaMenus", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('ADMINISTRACION_OBTENER_MENUS')")
	public ResponseEntity<List<MenuAdminVO>> getListaMenus(@RequestParam(value = "tipoBusqueda") String tipoBusqueda,
			@RequestParam(value = "valor") Long id) throws NotFoundException {

		List<MenuAdminVO> listaMenus = administracionService.obtieneListaMenus(tipoBusqueda, id);
		return new ResponseEntity<List<MenuAdminVO>>(listaMenus, HttpStatus.OK);
	}

	/**
	 * @author javier07
	 * @param parametros
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping(value = "/crudPerfiles", method = RequestMethod.POST, produces = "text/plain")
	@PreAuthorize("hasAnyAuthority('ADMINISTRACION_CRUD_PERFILES')")
	public ResponseEntity<String> crudPerfiles(@RequestBody AdminPerfilesParamVO parametros) throws BusinessException {

		UsuarioFirmadoVO usuario = usuarioFirmadoService.getUsuarioFirmadoVO();
		EmpleadosDTO empleado = empleadoService.getEmpleadoById(usuario.getId());
		parametros.setEmpleadoId(empleado.getEmpId().toString());
		String mensaje = administracionService.crudPerfiles(parametros);
		return new ResponseEntity<String>(mensaje, HttpStatus.OK);

	}

	@RequestMapping(value = "/buscarCajaExtemporaneas", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('BUSCAR_CAJA_EXTEMPORANEA')")
	public ResponseEntity<CajaVO> buscarCajaExtemporaneas(@RequestParam(value = "valor") String valor,
			@RequestParam(value = "tipoBusqueda") String tipoBusqueda) throws NotFoundException {
		CajaVO cajaVO = administracionService.bucarCajaOperacionesExtemporaneas(valor, tipoBusqueda);
		if (cajaVO == null) {
			throw new NotFoundException("No se encontraron registros");
		}
		//CajaVO cajaVO = new CajaVO();
//		ResponseConverter.copiarPropriedades(cajaVO, cajaDTO);
//		cajaVO.setNombreEmpleado(cajaDTO.getEmpleado().getEmpNombre() + " " + cajaDTO.getEmpleado().getEmpApePaterno()
//				+ " " + cajaDTO.getEmpleado().getEmpApeMaterno());
//		cajaVO.setPlacaEmpleado(cajaDTO.getEmpleado().getEmpPlaca());
		return new ResponseEntity<CajaVO>(cajaVO, HttpStatus.OK);
	}

	@RequestMapping(value = "/habilitarExtemporanea", method = RequestMethod.POST)
	@PreAuthorize("hasAnyAuthority('HABILITAR_EXTEMPORANEA')")
	public ResponseEntity<OperacionesExtemporaneasVO> habilitarExtemporanea(
			@RequestBody OperacionesExtemporaneasVO operacionesExtemporaneasVO, UriComponentsBuilder ucBuilder) throws ParseException {
		administracionService.habilitarExtemporanea(operacionesExtemporaneasVO);
		return new ResponseEntity<OperacionesExtemporaneasVO>(operacionesExtemporaneasVO, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/buscarCajaExtemporaneasDesactivar", method = RequestMethod.GET)
	@Transactional
	@PreAuthorize("hasAnyAuthority('BUSCAR_CAJAS_EXTEMPORANEAS_PARA_DESACTIVAR')")
	public ResponseEntity<List<VCajaExtDesactivarVO>> buscarCajaExtemporaneas() throws NotFoundException {
		List<VCajaExtDesactivarDTO> cajas = administracionService.buscarCajaExtDesactivar();
		List<VCajaExtDesactivarVO> cajasVO = ResponseConverter.converterLista(new ArrayList<>(), cajas,
				VCajaExtDesactivarVO.class);
		return new ResponseEntity<List<VCajaExtDesactivarVO>>(cajasVO, HttpStatus.OK);
	}

	@RequestMapping(value = "/desabilitarExtemporanea", method = RequestMethod.POST)
	@PreAuthorize("hasAnyAuthority('DESHABILITAR_EXTEMPORANEA')")
	public ResponseEntity<VCajaExtDesactivarVO> desabilitarExtemporanea(
			@RequestBody VCajaExtDesactivarVO vCajaExtDesactivarVO, UriComponentsBuilder ucBuilder) throws ParseException {
		administracionService.desabilitarExtemporanea(vCajaExtDesactivarVO);
		return new ResponseEntity<VCajaExtDesactivarVO>(vCajaExtDesactivarVO, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/cajaByCajaCodEmpPlacaDepId", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('CAJA_BY_CAJACOD_EMPPLACA_DEPID')")
	public ResponseEntity<List<VCajaConsultaConTieneOperacionesVO>> buscarCajasPorCajaCodEmpPlacaAndDepId(
			@RequestParam(value = "cajaCod") String cajaCod, @RequestParam(value = "empPlaca") String empPlaca,
			@RequestParam(value = "depId") String depId) {
		List<VCajaConsultaConTieneOperacionesVO> listVCajaConsultaConTieneOperacionesVO = cajaService
				.buscarCajasPorCajaCodEmpPlacaAndDepId(cajaCod.equals("") ? null : cajaCod,
						empPlaca.equals("") ? null : empPlaca, depId);
		if (listVCajaConsultaConTieneOperacionesVO == null)
			return new ResponseEntity<List<VCajaConsultaConTieneOperacionesVO>>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<List<VCajaConsultaConTieneOperacionesVO>>(listVCajaConsultaConTieneOperacionesVO,
				HttpStatus.OK);
	}

	@RequestMapping(value = "/foliosByCaja", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('BUSCA_FOLIOS_POR_CAJA')")
	public ResponseEntity<List<VFoliosCajaVO>> buscarFoliosByCajaId(@RequestParam(value = "caja") String cajaId) {
		List<VFoliosCajaVO> listvFoliosCajaVO = cajaService.buscarFoliosByCajaId(cajaId);
		if (listvFoliosCajaVO == null)
			return new ResponseEntity<List<VFoliosCajaVO>>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<List<VFoliosCajaVO>>(listvFoliosCajaVO, HttpStatus.OK);
	}

	@RequestMapping(value = "/reporteAdminCajas", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('CAJA_ADMIN_REPORTE')")
	public ResponseEntity<byte[]> generarReporteAdminExcel() {

		String filename = "Cajas.xls";
		ByteArrayOutputStream outputStream = cajaService.generarReporteAdminExcel();
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

}
