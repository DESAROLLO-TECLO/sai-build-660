package mx.com.teclo.saicdmx.api.rest.certificados;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import mx.com.teclo.saicdmx.negocio.service.certificados.CertificadosService;
import mx.com.teclo.saicdmx.negocio.service.comun.UsuarioFirmadoService;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.administracion.PerfilDTO;
import mx.com.teclo.saicdmx.persistencia.vo.administracion.PerfilesAdminVO;
import mx.com.teclo.saicdmx.persistencia.vo.certificados.CertificadoVO;
import mx.com.teclo.saicdmx.persistencia.vo.certificados.ConsultaUsersVO;
import mx.com.teclo.saicdmx.util.comun.ResponseConverter;
import mx.com.teclomexicana.arquitectura.ortogonales.exception.NotFoundException;
import mx.com.teclomexicana.arquitectura.ortogonales.seguridad.vo.UsuarioFirmadoVO;

/**
 * 
 * @author javier07
 *
 */
@RestController
@RequestMapping("/certificadosController")
public class CertificadosRestController {

	@Autowired
	private UsuarioFirmadoService usuarioFirmadoService;

	@Autowired
	private CertificadosService certificadosService;

	/**
	 * Servicio para obtener los perfiles
	 * 
	 * @author Jesus Gutierrez
	 * @return ResponseEntity<List<PerfilVO>>
	 */
	@RequestMapping(value = "/certificados/buscarPerfiles", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('CERTIFICADOS_BUSCAR_PERFILES')")
	public ResponseEntity<List<PerfilesAdminVO>> buscarPerfiles(@RequestParam("codigo") String codigo)
			throws NotFoundException {
		List<PerfilDTO> listaDTO = certificadosService.listaPerfilesByPerfilUsuario(codigo);
		List<PerfilesAdminVO> listaVO = ResponseConverter.converterLista(new ArrayList<>(), listaDTO,
				PerfilesAdminVO.class);
		return new ResponseEntity<List<PerfilesAdminVO>>(listaVO, HttpStatus.OK);
	}

	/**
	 * @author javier07 Servicio guardar certificado.
	 * @param file
	 *            Un solo archivo
	 * @param ucBuilder
	 * @return Map<String, Object>
	 */

	@RequestMapping(value = "/certificados/save", method = RequestMethod.POST)
	@PreAuthorize("hasAnyAuthority('CERTIFICADOS_GUARDAR')")
	public @ResponseBody Map<String, Object> saveCertificado(@RequestParam("files") List<MultipartFile> files,
			@RequestParam("placa") String placaOficial, @RequestParam("pwd") String pwdLlavePrivate) {

		pwdLlavePrivate = pwdLlavePrivate.replaceAll("\"", "");
		placaOficial = placaOficial.replaceAll("\"", "");
		UsuarioFirmadoVO usuario = usuarioFirmadoService.getUsuarioFirmadoVO();
		Long idUsuario = usuario.getId();

		Map<String, Object> respuestaValidacionParidad = null;

		if (!pwdLlavePrivate.equals("null")) {

			respuestaValidacionParidad = certificadosService.validarParidad(files, pwdLlavePrivate);
			if (respuestaValidacionParidad.get("respuesta").equals(true)) {
				return certificadosService.saveCertKeySAT(placaOficial, idUsuario, files, new Integer(1));
			}
		} else {
			return certificadosService.saveCertKeySAT(placaOficial, idUsuario, files, new Integer(0));

		}

		return respuestaValidacionParidad;
	}

	/**
	 * @author javier07
	 * @param tipoBusqueda
	 * @param tipoBusqueda2
	 * @param valor
	 * @return ResponseEntity<List<ConsultaUsersVO>>
	 * @throws NotFoundException
	 */
	@RequestMapping(value = "/certificados/buscarUsuarios", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('CERTIFICADOS_BUSCAR_USUARIOS_CRITERIOS')")
	public ResponseEntity<List<ConsultaUsersVO>> buscarUsuarios(
			@RequestParam(value = "tipoBusqueda") String tipoBusqueda,
			@RequestParam(value = "tipoBusqueda2") String tipoBusqueda2, @RequestParam(value = "valor") String valor)
			throws NotFoundException {

		List<ConsultaUsersVO> listaConsulta = certificadosService.obtenerListaUsuarios(tipoBusqueda, tipoBusqueda2, valor);

		if (listaConsulta.isEmpty()) {
			throw new NotFoundException("No se encontraron registros");
		}
		return new ResponseEntity<List<ConsultaUsersVO>>(listaConsulta, HttpStatus.OK);
	}

	/**
	 * @author javier07
	 * @param placaOficial
	 * @param rfc
	 * @return Map<String, Object>
	 */
	@RequestMapping(value = "/certificados/rfc", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('CERTIFICADOS_ACTUALIZAR_RFC')")
	public @ResponseBody Map<String, Object> actualizaRFCEmpleado(@RequestParam("placaOficial") String placaOficial,
			@RequestParam(value = "rfc") String rfc) {
		Map<String, Object> response = new HashMap<String, Object>();

		response = certificadosService.actualizaRFCEmpleado(placaOficial, rfc.toUpperCase());
		return response;
	}

	/**
	 * @author javier07
	 * @param placaOficial
	 * @return Map<String, Object>
	 */
	@RequestMapping(value = "/certificados/placaOficial", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('CERTIFICADOS_OBTIENE_CERTIFICADO_PLACA')")
	public @ResponseBody Map<String, Object> validaArchivoExiste(@RequestParam("placaOficial") String placaOficial) {

		Map<String, Object> response = new HashMap<String, Object>();
		response.put("respuesta", Boolean.FALSE);
		CertificadoVO certificadoVO = certificadosService.obtieneCertificadoPorPlaca(placaOficial);
		if (certificadoVO != null)
			response.put("respuesta", Boolean.TRUE);

		response.put("certificado", certificadoVO);
		return response;
	}

	/**
	 * @author javier07 Actualiza la entidad Certificado existente, de acuerdo a
	 *         la placa de Empleado.
	 * @param request,
	 *            contiene los archivos del certificado y parametro de busqueda
	 *            placaOficial.
	 * @return Map<String, Object>, Entidades encontradas e informacion
	 *         adicional.
	 * @throws IOException
	 */
	@Transactional
	@RequestMapping(value = "/certificados/update", method = RequestMethod.POST, consumes = { "multipart/form-data",
			MediaType.APPLICATION_JSON_VALUE })
	@PreAuthorize("hasAnyAuthority('CERTIFICADOS_ACTUALIZAR')")
	public @ResponseBody Map<String, Object> updateCertificado(@RequestParam("files") List<MultipartFile> files,
			@RequestParam("placa") String placaOficial, @RequestParam("pwd") String pwdLlavePrivate)
			throws IOException {

		pwdLlavePrivate = pwdLlavePrivate.replaceAll("\"", "");
		placaOficial = placaOficial.replaceAll("\"", "");

		UsuarioFirmadoVO usuario = usuarioFirmadoService.getUsuarioFirmadoVO();
		Long idUsuario = usuario.getId();
		Map<String, Object> respuestaValidacionParidad = null;

		if (!pwdLlavePrivate.equals("null")) {
			respuestaValidacionParidad = certificadosService.validarParidad(files, pwdLlavePrivate);
			if (respuestaValidacionParidad.get("respuesta").equals(true)) {
				return certificadosService.updateCertificado(placaOficial, idUsuario, files, new Integer(1));
			}
		} else {
			return certificadosService.updateCertificado(placaOficial, idUsuario, files, new Integer(0));

		}

		return respuestaValidacionParidad;
	}

	/**
	 * @author javier07 Busca la entidad Certificado por parámetros de Empleados
	 *         y Rango de fechas.
	 * @param tipoBusqueda,
	 *            parámetro de búsqueda.
	 * @param paramBusqueda,
	 *            valor del parámetros a buscar.
	 * @param fechaInicio,
	 *            fecha inicio de búsqueda de certificados.
	 * @param fechaFin,
	 *            fecha fin de búsqueda de certificados.
	 * @returnMap List<CertificadoVO>, Entidades encontradas e informacion
	 *            adicional.
	 */
	@RequestMapping(value = "/certificados/buscarPorFechas", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('CERTIFICADOS_CONSULTAR')")
	public @ResponseBody List<CertificadoVO> obtieneCertificados(
			@RequestParam(value = "tipoBusqueda") String tipoBusqueda,
			@RequestParam(value = "validado") String validado,
			@RequestParam(value = "paramBusqueda") String paramBusqueda,
			@RequestParam(value = "fechaInicio") String fechaInicio, @RequestParam(value = "fechaFin") String fechaFin)
			throws NotFoundException {

		List<CertificadoVO> listaCertificados = null;

		Integer paramValidado = validado.equals("") ? 3 : Integer.parseInt(validado);
		listaCertificados = certificadosService.obtieneCertificados(tipoBusqueda.toString(), paramBusqueda.toString(),
				fechaInicio, fechaFin, paramValidado);

		if (listaCertificados.isEmpty()) {
			throw new NotFoundException("No se encontraron registros");

		}

		return listaCertificados;
	}

	/**
	 * @author javier07 Valida que la llavePrivada pertenezca al certificado.
	 * @param placaOficial,
	 *            parámetro de búsqueda.
	 * @return Map<String, Object>, Entidades encontradas e informacion
	 *         adicional.
	 */
	@RequestMapping(value = "/certificados/valida", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('CERTIFICADOS_VALIDA')")
	public @ResponseBody Map<String, Object> validaParidad(@RequestParam("placaOficial") String placaOficial,
			@RequestParam(value = "pwd") String pwd) {

		Map<String, Object> response = new HashMap<String, Object>();

		response = certificadosService.validarParidad(placaOficial, pwd);

		return response;
	}

}
