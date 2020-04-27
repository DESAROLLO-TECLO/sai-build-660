package mx.com.teclo.saicdmx.negocio.service.evaluaciones;

import java.io.ByteArrayOutputStream;
import java.util.List;

import mx.com.teclo.saicdmx.persistencia.vo.evaluaciones.EvaluacionUsuarioVO;
import mx.com.teclo.saicdmx.persistencia.vo.evaluaciones.EvaluacionVO;

public interface EvaluacionesService {

	List<EvaluacionVO> getEvaluaciones(Integer tipoBusqueda, String valor, String fhIni, String fhFin);
	EvaluacionUsuarioVO getEvaluacionUsuarios(Integer idEvaluacion);
	ByteArrayOutputStream generaReporteEvaluaciones(List<EvaluacionVO> evaluaciones);
	ByteArrayOutputStream generaReporteEvaluacionUsuario(EvaluacionUsuarioVO evaluacionUsuario);
}
