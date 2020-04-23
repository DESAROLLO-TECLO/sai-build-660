package mx.com.teclo.saicdmx.persistencia.mybatis.dao.reportes;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import mx.com.teclo.saicdmx.persistencia.vo.reportes.CatalogoDinamicoVO;
import mx.com.teclo.saicdmx.persistencia.vo.reportes.rptInfraccionesMensuales;

@Mapper
public interface ReporteTotalesInfraccionMyBatisDAO {
	
	/*Infracciones mensuales totales consultas */
	//sSELECT DISTINCT(AAAA) as nombre FROM V_REP_INFRAC_MES_DET ORDER BY AAAA ASC
	String infracciones_mensuales_anios="select unique anio as id from SALARIOS_MINIMOS 	ORDER BY ANIO ASC";
	
	String infracciones_mensuales_totales ="SELECT * FROM (SELECT MEDIO_INGRESO AS medioIngreso,to_char(CANTIDAD,'999,999,999,999') AS cantidad "
                                          +"FROM V_REP_INFRAC_MES_DET WHERE DEP_NOMBRE =#{deposito} AND AAAA=#{anio} AND MM= #{mes}  AND rownum <= 5000"
			                              +"UNION ALL" 
			                              +" SELECT '0 - TOTAL',TO_CHAR(SUM(CANTIDAD),'999,999,999,999')" 
			                              +" FROM V_REP_INFRAC_MES_DET WHERE DEP_NOMBRE = #{deposito} AND AAAA= #{anio} AND MM=#{mes}  AND rownum <= 5000"
			                              +" GROUP BY 'TOTAL') order by medioIngreso ASC";
	
	/*Infracciones mensuales totales metodos */
	 @Select (infracciones_mensuales_anios)
	 List<CatalogoDinamicoVO> listaAniosDisponibles();
	 
	 @Select(infracciones_mensuales_totales)
	 List<rptInfraccionesMensuales> totalInfraccionesMensuales(@Param("deposito") String deposito,
			 												   @Param("anio") String anio,
			 												   @Param("mes") String mes);
	
	
}
