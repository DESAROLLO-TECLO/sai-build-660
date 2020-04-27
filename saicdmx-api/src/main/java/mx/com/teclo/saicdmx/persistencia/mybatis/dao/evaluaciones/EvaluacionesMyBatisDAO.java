package mx.com.teclo.saicdmx.persistencia.mybatis.dao.evaluaciones;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import mx.com.teclo.saicdmx.persistencia.vo.evaluaciones.EvaluacionUsuarioVO;
import mx.com.teclo.saicdmx.persistencia.vo.evaluaciones.EvaluacionVO;
import mx.com.teclo.saicdmx.persistencia.vo.evaluaciones.UsuarioVO;

@Mapper
public interface EvaluacionesMyBatisDAO {

	String CONSULTA_EVALUACIONES = "select ID_EVALUACION as idEvaluacion, CD_EVALUACION as cdEvaluacion, NB_TIPO_EVALUACION as nbTipoEvaluacion, " + 
			"NB_EVALUACION as nbEvaluacion, TX_EVALUACION as txEvaluacion, NU_SECCIONES as nuSecciones, " + 
			"NU_PREGUNTAS as nuPreguntas, to_char(FH_VIG_INI, 'DD/MM/YYYY') as fhVigIni, to_char(FH_VIG_FIN, 'DD/MM/YYYY') as fhVigFin, " + 
			"NU_CALIFICACION_APRO as nuCalificacionApro, NU_EVALUADOS as nuEvaluados, NU_PROMEDIO_CAL as nuPromedioCal, " + 
			"NU_POR_APLICACION as nuPorAplicacion, NU_EFECTIVIDAD as txEfectividad " + 
			"from tai060d_evaluaciones " + 
			"where st_activo = 1 " + 
			"and " + 
			"(case " + 
			"    when #{tipoBusqueda} = 0 then 1 " + 
			"    when #{tipoBusqueda} = 1 and #{valor} = CD_EVALUACION then 1 " + 
			"    when #{tipoBusqueda} = 2 and #{valor} = NB_EVALUACION then 1 " + 
			"end) = 1 " + 
			"and " + 
			"(case " + 
			"    when #{fhIni} is null then 1 " + 
			"    when #{fhIni} is not null " + 
			"        and ((to_date(#{fhIni}, 'dd/mm/yyyy') BETWEEN trunc(FH_VIG_INI) and trunc(FH_VIG_FIN)) or " + 
			"                (to_date(#{fhFin}, 'dd/mm/yyyy') BETWEEN trunc(FH_VIG_INI) and trunc(FH_VIG_FIN))) then 1 end) = 1";
	
	String CONSULTA_EVALUACION_ID = "select ID_EVALUACION as idEvaluacion, CD_EVALUACION as cdEvaluacion, NB_TIPO_EVALUACION as nbTipoEvaluacion, " + 
			"NB_EVALUACION as nbEvaluacion, TX_EVALUACION as txEvaluacion, NU_SECCIONES as nuSecciones, " + 
			"NU_PREGUNTAS as nuPreguntas, to_char(FH_VIG_INI, 'DD/MM/YYYY') as fhVigIni, to_char(FH_VIG_FIN, 'DD/MM/YYYY') as fhVigFin, " + 
			"NU_CALIFICACION_APRO as nuCalificacionApro, NU_EVALUADOS as nuEvaluados, NU_PROMEDIO_CAL as nuPromedioCal, " + 
			"NU_POR_APLICACION as nuPorAplicacion, NU_EFECTIVIDAD as txEfectividad " + 
			"from tai060d_evaluaciones where st_activo = 1 and ID_EVALUACION = #{idEvaluacion}";
	
	String CONSULTA_EVALUACION_USUARIOS = "select tai061.ID_USUARIO_EVALUACION as idUsEvaluacion, emp.EMP_PLACA as empPlaca, " + 
			"emp.EMP_APE_PATERNO as empApePaterno, emp.EMP_APE_MATERNO as empApeMaterno, emp.EMP_NOMBRE as empNombre, " + 
			"to_char(tai061.FH_INICIO, 'DD/MM/YYYY HH24:mi:ss') as fhInicio, " + 
			"to_char(tai061.FH_FIN, 'DD/MM/YYYY HH24:mi:ss') as fhFin, " + 
			"tai061.NU_CALIFICACION as nuCalificacion, tai061.NU_INTENTOS as nuIntentos, " + 
			"tai061.NB_ST_CALIFICACION as nbStCalificacion " +  
			"from tai061d_evaluacion_usuario tai061 " + 
			"inner join empleados emp on emp.emp_id = tai061.id_usuario " +  
			"where tai061.id_evaluacion = #{idEvaluacion}";
	
	@Select(value = CONSULTA_EVALUACIONES)
	public List<EvaluacionVO> getEvaluaciones(
			@Param(value="tipoBusqueda") Integer tipoBusqueda, 
			@Param(value="valor") String valor, 
			@Param(value="fhIni") String fhIni, 
			@Param(value="fhFin") String fhFin);
	
	@Select(value = CONSULTA_EVALUACION_ID)
	public EvaluacionVO getEvaluacionPorId(
			@Param(value="idEvaluacion") Integer idEvaluacion);
	
	@Select(value = CONSULTA_EVALUACION_USUARIOS)
	public List<UsuarioVO> getEvaluacionUsuarios(
			@Param(value="idEvaluacion") Integer idEvaluacion);
}
