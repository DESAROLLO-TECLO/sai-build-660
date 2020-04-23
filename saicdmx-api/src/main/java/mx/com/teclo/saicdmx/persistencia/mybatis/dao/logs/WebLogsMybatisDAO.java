package mx.com.teclo.saicdmx.persistencia.mybatis.dao.logs;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import mx.com.teclo.saicdmx.persistencia.vo.logs.LogsConsultaComboVO;

@Mapper
public interface WebLogsMybatisDAO {
	
	  String CONSULTA_PERFIL = "SELECT L.LOG_ID as logId,"
	  		  + "L.LOG_NOMBRE as logNombre,"
	  		  + "LOG_DESCRIPCION as logDescripcion, "
              + "LOG_ESTATUS as logEstatus,"
              + "RUTA_ARCHIVOS as rutaArchivos "
              + " FROM WEB_LOGS L,V_PERFIL_LOGS_ACTIVOS P "
              + " WHERE L.LOG_ID=P.LOG_ID "
              + " AND P.PERFIL_ID= #{perfilId}"
              + " AND L.LOG_ESTATUS='A'";
	  
	  
	  @Select(CONSULTA_PERFIL)
      List<LogsConsultaComboVO> busquedaPorPerfil(@Param("perfilId")Long perfilId);
}
