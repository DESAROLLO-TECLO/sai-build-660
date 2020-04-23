package mx.com.teclo.saicdmx.persistencia.mybatis.dao.radarparametrosweb;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface RadarParametrosWebMyBatisDAO {
	
	String BUSCA_RUTA_RADARES = 
			"SELECT DIR_CARGA_ARCHIVOS_FINAL "+
			"FROM RADAR_PARAMETROS_WEB "+
			"WHERE PARAMETRO_ID=1";
	
	
	@Select(BUSCA_RUTA_RADARES)
	public String buscaRutaRadares();
}
