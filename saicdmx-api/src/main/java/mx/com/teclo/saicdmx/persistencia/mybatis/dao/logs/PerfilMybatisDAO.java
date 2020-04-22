package mx.com.teclo.saicdmx.persistencia.mybatis.dao.logs;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import mx.com.teclo.saicdmx.persistencia.vo.logs.ComboVO;

@Mapper
public interface PerfilMybatisDAO {
	
	 String CONSULTA_NO_ASIGNADOS = "SELECT PERFIL_ID as comboValor, PERFIL_NOMBRE as comboEtiqueta "
             + "FROM PERFILES WHERE PERFIL_ID "
             + "NOT IN(SELECT PERFIL_ID FROM V_PERFIL_LOGS_ACTIVOS "
             + "WHERE LOG_ID= #{logId})";
	
	  String CONSULTA_ASIGNADOS_POR_LOG = "SELECT PERFIL_ID as comboValor, PERFIL_NOMBRE as comboEtiqueta "
              + "FROM V_PERFIL_LOGS_ACTIVOS "
              + "WHERE LOG_ID= #{logId}"; 
	 
	 
	 
	 @Select(CONSULTA_NO_ASIGNADOS)
	 List<ComboVO> consultaPerfilesNoAsignados(@Param("logId") Long id);

	 @Select(CONSULTA_ASIGNADOS_POR_LOG)
	 List<ComboVO>  perfilesAsignadosPorLog(@Param("logId") Long id);

	
}
