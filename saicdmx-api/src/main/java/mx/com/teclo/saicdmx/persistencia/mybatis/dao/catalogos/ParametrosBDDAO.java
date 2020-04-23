package mx.com.teclo.saicdmx.persistencia.mybatis.dao.catalogos;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ParametrosBDDAO {

	String PARAMETROS_TODOS = "SELECT CD_LLAVE_P_CONFIG, CD_VALOR_P_CONFIG FROM TAI041P_CONFIGURACION WHERE ST_ACTIVO = 1";
	String PARAMETROS_QUERYS = "SELECT CD_LLAVE_P_CONFIG, CD_VALOR_P_CONFIG FROM TAI041P_CONFIGURACION WHERE ST_ACTIVO = 1 AND NB_P_CONFIG = 'QUERY'";
	String PARAMETROS_PARAMETRO = "SELECT CD_LLAVE_P_CONFIG, CD_VALOR_P_CONFIG FROM TAI041P_CONFIGURACION WHERE ST_ACTIVO = 1 AND NB_P_CONFIG = 'PARAMETRO'";
	String PARAMETROS_PARAMETRO_POR_NBCONFIG = "SELECT CD_LLAVE_P_CONFIG, CD_VALOR_P_CONFIG FROM TAI041P_CONFIGURACION WHERE ST_ACTIVO = 1 AND NB_P_CONFIG =#{valor}";
	String PARAMETROS_PARAMETRO_POR_CDCONFIG = "SELECT CD_LLAVE_P_CONFIG, CD_VALOR_P_CONFIG FROM TAI041P_CONFIGURACION WHERE ST_ACTIVO = 1 AND CD_LLAVE_P_CONFIG =#{valor}";
		
	@Select(PARAMETROS_TODOS)
	public List<Map<String, String>> getParametrosTodos();
	
	@Select(PARAMETROS_QUERYS)
	public List<Map<String, String>> getParametrosSoloQuerys();
	
	@Select(PARAMETROS_PARAMETRO)
	public List<Map<String, String>> getParametrosSoloParametros();
	
	@Select(PARAMETROS_PARAMETRO_POR_NBCONFIG)
	public List<Map<String, String>> getParametrosPorNbConfig(
			@Param("valor") String valor);
	
	@Select(PARAMETROS_PARAMETRO_POR_CDCONFIG)
	public List<Map<String, String>> getParametrosPorCdConfig(
			@Param("valor") String valor);
}
