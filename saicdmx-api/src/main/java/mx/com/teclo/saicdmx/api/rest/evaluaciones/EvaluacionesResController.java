package mx.com.teclo.saicdmx.api.rest.evaluaciones;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import mx.com.teclo.saicdmx.negocio.service.evaluaciones.EvaluacionesService;
import mx.com.teclo.saicdmx.persistencia.vo.evaluaciones.EvaluacionUsuarioVO;
import mx.com.teclo.saicdmx.persistencia.vo.evaluaciones.EvaluacionVO;
import mx.com.teclo.saicdmx.persistencia.vo.garantias.VSSPGarantiaReporteGeneralFVO;
import mx.com.teclomexicana.arquitectura.ortogonales.exception.NotFoundException;

@RestController
public class EvaluacionesResController {
	
	@Autowired
	private EvaluacionesService evaluacionesService;
	
	@RequestMapping(value="/getEvaluaciones", method=RequestMethod.GET)
	public ResponseEntity<List<EvaluacionVO>> consultaEvaluaciones(
			@RequestParam(value="tipoBusqueda") Integer tipoBusqueda, 
			@RequestParam(value="valor", required = false) String valor, 
			@RequestParam(value="fhIni", required = false) String fhIni,
			@RequestParam(value="fhFin", required = false) String fhFin) throws NotFoundException{
		List<EvaluacionVO> listEvaluaciones = new ArrayList<EvaluacionVO>();
		listEvaluaciones = evaluacionesService.getEvaluaciones(tipoBusqueda, valor, fhIni, fhFin);
		if(listEvaluaciones.isEmpty())
			throw new NotFoundException("No se encontraron registros");
		return new ResponseEntity<List<EvaluacionVO>>(listEvaluaciones, HttpStatus.OK);
	}
	
	@RequestMapping(value="/evaluacionUsuarios", method=RequestMethod.GET)
	public ResponseEntity<EvaluacionUsuarioVO> consultaEvaluacionesUsuarios(
		@RequestParam(value="idEvaluacion") Integer idEvaluacion) throws NotFoundException{
		
		EvaluacionUsuarioVO listEvaluacionUsuario = new EvaluacionUsuarioVO();
		listEvaluacionUsuario = evaluacionesService.getEvaluacionUsuarios(idEvaluacion);
		if(listEvaluacionUsuario==null)
			throw new NotFoundException("No se encontraron registros");
		
		return new ResponseEntity<EvaluacionUsuarioVO>(listEvaluacionUsuario, HttpStatus.OK);
	}
	
	@RequestMapping(value="/evaluacionesReporte")
	public ResponseEntity<byte[]> evaluacionesReporteExcel(
			@RequestParam(value="tipoBusqueda") Integer tipoBusqueda, 
			@RequestParam(value="valor", required = false) String valor, 
			@RequestParam(value="fhIni", required = false) String fhIni,
			@RequestParam(value="fhFin", required = false) String fhFin) throws NotFoundException{
		
		String NombreReporte = "Evaluaciones";
		List<EvaluacionVO> listEvaluaciones = evaluacionesService.getEvaluaciones(tipoBusqueda, valor, fhIni, fhFin);
		ByteArrayOutputStream reporteExcel = evaluacionesService.generaReporteEvaluaciones(listEvaluaciones);
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
	
	@RequestMapping(value="/evaluacionUsuariosReporte")
	public ResponseEntity<byte[]> evaluacionUsuarioExcel(
			@RequestParam(value="idEvaluacion") Integer idEvaluacion) throws NotFoundException{
		EvaluacionUsuarioVO evaluacionUsuario = evaluacionesService.getEvaluacionUsuarios(idEvaluacion);
		String NombreReporte = "Evaluaciones Usuarios Evaluación " + evaluacionUsuario.getEvaluacion().getNbEvaluacion();
		ByteArrayOutputStream reporteExcel = evaluacionesService.generaReporteEvaluacionUsuario(evaluacionUsuario);
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
}
