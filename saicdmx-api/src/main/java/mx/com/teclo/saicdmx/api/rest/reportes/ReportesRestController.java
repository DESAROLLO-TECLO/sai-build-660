package mx.com.teclo.saicdmx.api.rest.reportes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import mx.com.teclo.saicdmx.negocio.service.comun.UsuarioFirmadoService;
import mx.com.teclo.saicdmx.negocio.service.empleado.EmpleadoService;
import mx.com.teclo.saicdmx.negocio.service.reportes.ReporteService;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.empleados.EmpleadosDTO;
import mx.com.teclo.saicdmx.persistencia.vo.reportes.ReporteVO;
import mx.com.teclomexicana.arquitectura.ortogonales.exception.NotFoundException;
import mx.com.teclomexicana.arquitectura.ortogonales.seguridad.vo.UsuarioFirmadoVO;

/**
 * @author javier07
 */
@RestController	
@RequestMapping("/reportes")
public class ReportesRestController {
	
	@Autowired
	private UsuarioFirmadoService usuarioFirmadoService;
	
	@Autowired
	private EmpleadoService empleadoService;
	
	@Autowired
	private ReporteService reporteService;
	
	/**
	 * @author javier07
	 * @return
	 * @throws NotFoundException
	 */
	@RequestMapping(value = "/getReportesLista", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('REPORTES_CONSULTA')")
	public ResponseEntity<List<ReporteVO>> getReportes()throws NotFoundException{

		UsuarioFirmadoVO usuario = usuarioFirmadoService.getUsuarioFirmadoVO();
		EmpleadosDTO empleado = empleadoService.getEmpleadoById(usuario.getId());
		
		List<ReporteVO> listaReportes = reporteService.obtenerListaLinks(empleado.getEmpId());
		if (listaReportes.isEmpty()) {
			throw new NotFoundException("No se encontraron registros");
		}

		return new  ResponseEntity<List<ReporteVO>>(listaReportes, HttpStatus.OK);
	}
	
}
