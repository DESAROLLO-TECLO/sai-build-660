package mx.com.teclo.saicdmx.persistencia.mybatis.dao.radarbitacoracambios;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 
 * @author Jesus Gutierrez
 *
 */
@Mapper
public interface RadarBitacoraCambiosMyBatisDAO {
	
	String INSERT_RADAR_BITACORA_CAMBIOS = 
			"INSERT INTO BITACORA_CAMBIOS (COMPONENTE_ID, CONCEPTO_ID, ID_REGISTRO, VALOR_ORIGINAL,VALOR_FINAL,MODIFICADO_POR)"
				+ " VALUES (8, #{conceptoId}, #{idRegistro}, #{valorOriginal}, #{valorFinal}, #{modificadoPor})";
	
	/***
	 * @author Jesus Gutierrez
	 * @param radarArchivoId
	 * @param estatusProcesoId
	 * @param empleadoId
	 * @return Integer
	 */
	@Insert(INSERT_RADAR_BITACORA_CAMBIOS)
	public Integer insertaRadarBitacoraProceso(@Param("conceptoId")Integer conceptoId,  
											   @Param("idRegistro")Long idRegistro,  
											   @Param("valorOriginal")String valorOriginal,
											   @Param("valorFinal")String valorFinal,
											   @Param("modificadoPor")Long modificadoPor);
}
