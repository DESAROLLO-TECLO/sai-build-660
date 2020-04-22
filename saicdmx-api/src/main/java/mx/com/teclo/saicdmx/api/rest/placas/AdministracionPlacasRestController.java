package mx.com.teclo.saicdmx.api.rest.placas;

import java.io.ByteArrayOutputStream;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import mx.com.teclo.saicdmx.negocio.service.catalogos.CatalogoService;
import mx.com.teclo.saicdmx.negocio.service.comun.UsuarioFirmadoService;
import mx.com.teclo.saicdmx.negocio.service.empleado.EmpleadoService;
import mx.com.teclo.saicdmx.negocio.service.placas.BitPlacasService;
import mx.com.teclo.saicdmx.negocio.service.placas.PlacasService;
import mx.com.teclo.saicdmx.persistencia.comun.assemblerPlaca.PlacaAssembler;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.empleados.EmpleadosDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.placas.PlacasDTO;
import mx.com.teclo.saicdmx.persistencia.vo.placas.BitPlacasVO;
import mx.com.teclo.saicdmx.persistencia.vo.placas.PlacasVO;
import mx.com.teclo.saicdmx.util.comun.ComparaUtils;
import mx.com.teclomexicana.arquitectura.ortogonales.exception.NotFoundException;
import mx.com.teclomexicana.arquitectura.ortogonales.seguridad.vo.UsuarioFirmadoVO;
import utilerias.Utilerias;

@RestController
@RequestMapping("/administracionPlacasController")
public class AdministracionPlacasRestController {

	@Autowired
	private PlacasService placasService;
	@Autowired
	private BitPlacasService bitPlacasService;

	@Autowired
	private EmpleadoService empleadoService;
	
	@Autowired
	private CatalogoService catalogoService;

	@Autowired
	private UsuarioFirmadoService usuarioFirmadoService;

	@RequestMapping(value = "/buscarPlacas", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('CONSULTA_BUSCAR_PLACAS')")
	public ResponseEntity<List<PlacasVO>> buscarPlacas(@RequestParam(value = "tipoBusqueda") String tipoBusqueda,
			@RequestParam(value = "valor") String valor, @RequestParam(value = "isAdmin") Boolean isAdmin)

			throws NotFoundException {

		List<PlacasVO> listaConsulta = placasService.obtenerListaPlacas(tipoBusqueda, valor, isAdmin);
		if (listaConsulta.isEmpty()) {
			throw new NotFoundException("No se encontraron registros");
		}
		return new ResponseEntity<List<PlacasVO>>(listaConsulta, HttpStatus.OK);
	}

	@RequestMapping(value = "/buscaExistencia", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('ADMINISTRA_BUSCA_EXISTENCIA')")
	public ResponseEntity<List<PlacasVO>> buscarExistencia(@RequestParam(value = "tipoBusqueda") String tipoBusqueda,
			@RequestParam(value = "valor") String valor, @RequestParam(value = "isAdmin") Boolean isAdmin)

			throws NotFoundException {

		List<PlacasVO> listaConsulta = placasService.obtenerListaPlacas(tipoBusqueda, valor, isAdmin);
		return new ResponseEntity<List<PlacasVO>>(listaConsulta, HttpStatus.OK);
	}

	@RequestMapping(value = "/buscarPlacasFecha", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('CONSULTA_BUSCAR_PLACAS_FECHA')")
	public ResponseEntity<List<PlacasVO>> buscarPlacasFecha(@RequestParam(value = "tipoBusqueda") String tipoBusqueda,
			@RequestParam(value = "fInicio") String fInicio, @RequestParam(value = "fFin") String fFin,
			@RequestParam(value = "isAdmin") Boolean isAdmin)

			throws NotFoundException {

		List<PlacasVO> listaConsulta = placasService.obtenerListaPlacasFecha(tipoBusqueda, fInicio, fFin, isAdmin);
		if (listaConsulta.isEmpty()) {
			throw new NotFoundException("No se encontraron registros");

		}
		return new ResponseEntity<List<PlacasVO>>(listaConsulta, HttpStatus.OK);
	}

	@RequestMapping(value = "/buscarPlacasFechaRango", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('CONSULTA_BUSCAR_PLACAS_FECHA_RANGO')")
	public ResponseEntity<List<PlacasVO>> buscarPlacasFechaRango(
			@RequestParam(value = "tipoBusqueda") String tipoBusqueda,
			@RequestParam(value = "periodoFecha") Integer periodoFecha,
			@RequestParam(value = "isAdmin") Boolean isAdmin)

			throws NotFoundException {

		List<PlacasVO> listaConsulta = placasService.obtenerListaPlacasFechaRango(tipoBusqueda, periodoFecha, isAdmin);
		if (listaConsulta.isEmpty()) {
			throw new NotFoundException("No se encontraron registros");

		}
		return new ResponseEntity<List<PlacasVO>>(listaConsulta, HttpStatus.OK);
	}

	@RequestMapping(value = "/actualizaPlaca", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('ADMINISTRA_ACTUALIZA_PLACA')")
	public ResponseEntity<String> updatePlaca(@RequestParam(value = "placaId") Long placaId,
			@RequestParam(value = "placaCodigo") String placaCodigo,
			@RequestParam(value = "observaciones") String observaciones)
			throws NotFoundException {
		ResponseEntity<String> respuesta=null;
		List<PlacasVO> listaConsulta =null;
		try {
			listaConsulta = placasService.obtenerListaPlacas("placa", placaCodigo, true);
			if (listaConsulta == null || listaConsulta.size() == 0) {
				PlacasVO originalPlacasVO = null, finalPlacasVO = null;
				originalPlacasVO = this.obtenerOriginalPlacasVO(placaId.intValue());
				finalPlacasVO = new PlacasVO();
				finalPlacasVO.setPlacaCodigo(placaCodigo.toUpperCase().trim());
				finalPlacasVO.setPlacaStatus(originalPlacasVO.getPlacaStatus());
				finalPlacasVO.setObservaciones(observaciones);
				placasService.encriptarPlaca(finalPlacasVO);

				if (!originalPlacasVO.equals(finalPlacasVO)) {
					PlacasDTO placasDTO = placasService.updatePlaca(placaId, placaCodigo, observaciones);
					this.guardarBitacoraPlaca(originalPlacasVO, finalPlacasVO, "updatePlaca");
					respuesta= new ResponseEntity<String>("true", HttpStatus.OK);
				}
			}else{
				respuesta=new ResponseEntity<String>("false", HttpStatus.OK);
			}	
		} catch (Exception e) {
			respuesta=new ResponseEntity<String>("false", HttpStatus.CONFLICT);
		}
		return respuesta;
	}

	@RequestMapping(value = "/guardaPlaca", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('ADMINISTRA_GUARDA_PLACA')")
	public ResponseEntity<String> savePlaca(@RequestParam(value = "placaCodigo") String placaCodigo,
			@RequestParam(value = "observaciones") String observaciones)
			throws NotFoundException {
		PlacasDTO guardaPlaca = new PlacasDTO();
		PlacasVO originalPlacasVO = null, finalPlacasVO = null;

		guardaPlaca = placasService.savePlaca(placaCodigo, observaciones);

		finalPlacasVO = this.obtenerOriginalPlacasVO(guardaPlaca.getPlacaId().intValue());
		originalPlacasVO = new PlacasVO();
		originalPlacasVO.setPlacaId(finalPlacasVO.getPlacaId());
		originalPlacasVO.setObservaciones(finalPlacasVO.getObservaciones());
		this.guardarBitacoraPlaca(originalPlacasVO, finalPlacasVO, "guardaPlaca");
		return new ResponseEntity<String>("true", HttpStatus.OK);

	}

	@RequestMapping(value = "/cambiarEstatusPlaca", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('ADMINISTRA_CAMBIAR_ESTATUS_PLACA')")
	public Boolean cambiarEstatus(@RequestParam(value = "accion") String accion,
			@RequestParam(value = "placaId") int placaId) {
		Boolean confirmacion = false;
		PlacasVO originalPlacasVO = null, finalPlacasVO = null;
		try {
			originalPlacasVO = this.obtenerOriginalPlacasVO(placaId);
			finalPlacasVO = new PlacasVO();
			finalPlacasVO.setPlacaCodigo(originalPlacasVO.getPlacaCodigo());
			finalPlacasVO.setPlacaStatus(accion.equals("Deshabilitado") ? "1" : "0");
			finalPlacasVO.setObservaciones(originalPlacasVO.getObservaciones());
			placasService.encriptarPlaca(finalPlacasVO);

			if (!originalPlacasVO.equals(finalPlacasVO)) {
				confirmacion = placasService.cambiarEstatus(accion, placaId, this.obtenerUsuarioConectado());
				this.guardarBitacoraPlaca(originalPlacasVO, finalPlacasVO, "cambiarEstatus");
			}

		} catch (Exception e) {
			System.err.println(e.getMessage());
		}

		return confirmacion;
	}

	@RequestMapping(value = "/reportePlacas", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('CONSULTA_REPORTE_PLACAS')")

	public ResponseEntity<byte[]> generarReportExcel(@RequestParam(value = "tipoBusqueda") String tipoBusqueda,
			@RequestParam(value = "valor") String valor, @RequestParam(value = "periodoFecha") Integer periodoFecha,
			@RequestParam(value = "fInicio") String fInicio, @RequestParam(value = "fFin") String fFin,
			@RequestParam(value = "isAdmin") Boolean isAdmin) {

		String filename = "Placas.xls";
		ByteArrayOutputStream outputStream = placasService.generarReportExcel(tipoBusqueda, valor, periodoFecha,
				fInicio, fFin, isAdmin);
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

	private int obtenerUsuarioConectado() {
		int usuarioConectado = -1;
		UsuarioFirmadoVO usuario = usuarioFirmadoService.getUsuarioFirmadoVO();
		EmpleadosDTO empleado = empleadoService.getEmpleadoById(usuario.getId());
		usuarioConectado = empleado.getEmpId().intValue();
		return usuarioConectado;
	}

	private PlacasVO obtenerOriginalPlacasVO(int placaId) {
		PlacasVO originalPlacasVO = null;
		PlacasDTO originalPlacasDTO = placasService.getPlacaById((long) placaId);
		PlacaAssembler placaAssembler = new PlacaAssembler();
		originalPlacasVO = placaAssembler.obtenerPlacasVO(originalPlacasDTO);
		return originalPlacasVO;
	}

	private void guardarBitacoraPlaca(PlacasVO originalPlacasVO, PlacasVO finalPlacasVO, String metodoJava) {

		BitPlacasVO bitPlacasVO = new BitPlacasVO();
		bitPlacasVO.setIdBitValidaMatri(-1);
		bitPlacasVO.setIdRegistro(originalPlacasVO.getPlacaId().toString());
		bitPlacasVO.setNbTabla("TAI006_FM_VALIDA_MATRICULA");
		bitPlacasVO.setCreadoPor(this.obtenerUsuarioConectado());

		if (metodoJava.toUpperCase().trim().equals("cambiarEstatus".toUpperCase())) {
			bitPlacasVO.setCdValorOriginal(originalPlacasVO.getPlacaStatus());
			bitPlacasVO.setCdValorFinal(finalPlacasVO.getPlacaStatus());
			bitPlacasVO.setNbColumna("ACTIVO");
			bitPlacasService.guardarBitPlacas(bitPlacasVO);
		} else {
			bitPlacasVO.setCdValorOriginal(originalPlacasVO.getPlacaCodigo());
			bitPlacasVO.setCdValorFinal(finalPlacasVO.getPlacaCodigo());
			bitPlacasVO.setNbColumna("PLACA");
			bitPlacasService.guardarBitPlacas(bitPlacasVO);
		}

		if (!ComparaUtils.comparaCadenas(originalPlacasVO.getObservaciones(), finalPlacasVO.getObservaciones())) {
			bitPlacasVO.setCdValorOriginal(originalPlacasVO.getObservaciones());
			bitPlacasVO.setCdValorFinal(finalPlacasVO.getObservaciones());
			bitPlacasVO.setNbColumna("OBSERVACIONES");
			bitPlacasService.guardarBitPlacas(bitPlacasVO);
		}
	}
	
	
	@RequestMapping(value = "/getOcultarActualizar", method = RequestMethod.GET)
	public ResponseEntity<Boolean> getOcultarActualizar()
			throws NotFoundException {
		ResponseEntity<Boolean> respuesta=null;
		Boolean ocultarActualizarPlaca=true;
		try {
		//placasService.encriptarPlaca(finalPlacasVO);
			Map<String, String>  valor=catalogoService.getParametrosBD(4,"OCULTAR_ACTUALIZAR_PLACA");
			ocultarActualizarPlaca=valor==null?ocultarActualizarPlaca: 
				valor.get("OCULTAR_ACTUALIZAR_PLACA").toString().toUpperCase().equals("TRUE") ;
				respuesta=new ResponseEntity<Boolean>(ocultarActualizarPlaca, HttpStatus.OK);
		} catch (Exception e) {
			respuesta=new ResponseEntity<Boolean>(ocultarActualizarPlaca, HttpStatus.OK);
		}
		return respuesta;
	}

}
