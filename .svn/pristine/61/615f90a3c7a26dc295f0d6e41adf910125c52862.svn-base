package mx.com.teclo.saicdmx.persistencia.mybatis.dao.reportes;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import mx.com.teclo.saicdmx.persistencia.vo.reportes.infraccionesAnualesVO;

@Mapper
public interface ReporteInfraccionesAnualesMyBatisDAO {

	/*infracciones anuales (Total mensual) */
    String consulta_Infracciones_Anuales ="SELECT * FROM ( "
    		+" SELECT MM mes ,DEP_NOMBRE AS deposito,TO_CHAR(CANTIDAD,'999,999,999,999') total"
    		+"  FROM V_REP_INFRAC_MES_TOTAL WHERE AAAA=#{anio}  AND rownum <= 5000 "
    		+" GROUP BY MM,DEP_NOMBRE,CANTIDAD "
    		+" ORDER BY MM ASC ,CANTIDAD DESC "
    		+")UNION ALL "
    		+"  SELECT AAAA,'TOTAL ',TO_CHAR(SUM(CANTIDAD),'999,999,999,999') TOTAL  FROM V_REP_INFRAC_MES_TOTAL "
    		+"  WHERE AAAA=#{anio}  AND rownum <= 5000   GROUP BY AAAA";
	
	
    @Select(consulta_Infracciones_Anuales)
	 List<infraccionesAnualesVO> obtenerInfraccionesAnuales(@Param("anio") Long anio);
	 
	 
	 String consulta_infracciones_total_anual ="SELECT * FROM ( "
			 +"SELECT DEP_NOMBRE AS deposito ,TO_CHAR(CANTIDAD,'999,999,999,999') total"
			 +"  FROM V_REP_INFRAC_AIO_TOTAL "
			 +"WHERE AAAA=#{anio}  AND rownum <= 5000"
			 +"  GROUP BY DEP_NOMBRE,CANTIDAD "
			 +" ORDER BY CANTIDAD DESC "
			 +")UNION ALL "
			 +"  SELECT 'TOTAL ANUAL' , TO_CHAR(SUM(CANTIDAD),'999,999,999,999') TOTAL "
			 +" FROM V_REP_INFRAC_AIO_TOTAL "
			 +" WHERE AAAA=#{anio}  AND rownum <= 5000";
	 
	 /**
	  * @author FernandoSanchez
	  * @param anio
	  * @return lista infracciones 
	  * */
	 @Select(consulta_infracciones_total_anual)
	 List<infraccionesAnualesVO> consultaInfraccionesAnualesTotal(@Param("anio") Long anio);
	 
	 
	 
}
