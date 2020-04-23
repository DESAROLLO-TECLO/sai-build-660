package mx.com.teclo.saicdmx.persistencia.mybatis.dao.reportes;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import mx.com.teclo.saicdmx.persistencia.vo.reportes.TotalInfraccionesMensualesVO;

@Mapper
public interface TotalInfraccionesMensualesMyBatisDAO {
	/*en base de datos */
	String consulta_infracciones_Mensuales ="SELECT * FROM (SELECT NOM_MES_FECHA as mes ,TO_CHAR(SUM(TOTAL),'999,999,999,999') as total "
			                               +"     FROM V_REP_SSP_TOTAL_INFRACCION  "
			                               +"         WHERE TRUNC(FECHA) BETWEEN TO_DATE(#{fechaInicio},'dd/MM/YYYY') AND TO_DATE(#{fechaFin},'dd/MM/YYYY')   AND rownum <= 5000 " 
			                               +"GROUP BY NOM_MES_FECHA,NUM_MES_FECHA "
			                               +"ORDER BY NUM_MES_FECHA ASC "
			                               +") UNION ALL "
			                               +"SELECT 'TOTAL',TO_CHAR(SUM(TOTAL),'999,999,999,999') FROM V_REP_SSP_TOTAL_INFRACCION"
			                               +"      WHERE TRUNC(FECHA) BETWEEN TO_DATE(#{fechaInicio},'dd/MM/YYYY') AND TO_DATE(#{fechaFin},'dd/MM/YYYY')  AND rownum <= 5000 "
			                               +"GROUP BY 'TOTAL'";

	@Select(consulta_infracciones_Mensuales)
	List<TotalInfraccionesMensualesVO> obtenerInfraccionesMensuales(@Param("fechaInicio") String fechaInicio,
			                                                        @Param("fechaFin") String fechaFin);
}
