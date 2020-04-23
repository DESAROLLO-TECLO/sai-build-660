package mx.com.teclo.saicdmx.persistencia.mybatis.dao.logs;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.StatementType;

import mx.com.teclo.saicdmx.persistencia.vo.logs.LogsVO;
import mx.com.teclo.saicdmx.persistencia.vo.logs.ResultadoProcedureVO;

@Mapper
public interface LogsMyBatisDAO {
	
  String SP_ADMIN_LOGS = "CALL SP_ADMIN_LOGS("
				+ "#{tipoOperacion},"
				+ "#{logId},"
				+ "#{perfilId},"
				+ "#{logNombre},"
				+ "#{logDescripcion},"
				+ "#{rutaArchivo},"
				+ "#{logTipoArchivos},"
				+ "#{creadoPor},"
				+ "#{resultadoPrincipal, jdbcType=VARCHAR, javaType=java.lang.String, mode=INOUT},"
				+ "#{mensaje, jdbcType=VARCHAR, javaType=java.lang.String, mode=INOUT})";

   @Select(SP_ADMIN_LOGS)
   @Options(statementType = StatementType.CALLABLE)
   public LogsVO ejecutarSPAdminLogs(LogsVO logVO); 

}
