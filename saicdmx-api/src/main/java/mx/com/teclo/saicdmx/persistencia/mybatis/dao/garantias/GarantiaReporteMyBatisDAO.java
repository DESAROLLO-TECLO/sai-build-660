package mx.com.teclo.saicdmx.persistencia.mybatis.dao.garantias;



import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import mx.com.teclo.saicdmx.persistencia.vo.garantias.VSSPGarantiaReporteRecibirFVO;

import mx.com.teclo.saicdmx.persistencia.vo.garantias.VSSPGarantiaReporteRecibirFVO;
import mx.com.teclo.saicdmx.persistencia.vo.garantias.VSSPGarantiaReporteRecibirMasivaFVO;

@Mapper
public interface GarantiaReporteMyBatisDAO {
	
	
	String CONSULTA_GARANTIA_REPORTE = 
			"SELECT "
			+ " (CASE  WHEN (GARDOC.DOCUMENTO_ID = 1 OR GARDOC.DOCUMENTO_ID = 4) THEN 'Placa Vehicular' WHEN (GARDOC.DOCUMENTO_ID = 2 OR GARDOC.DOCUMENTO_ID = 5)"
			+ " THEN 'Licencia' WHEN (GARDOC.DOCUMENTO_ID = 3 OR GARDOC.DOCUMENTO_ID = 6)"
			+ " THEN 'Tarjeta de Circulación' END) AS NOMBRE,"
			+ " GAR.DOCUMENTO_FOLIO AS DOCUMENTO_FOLIO, "
			+ " INF.INFRAC_NUM AS INFRAC_NUM,"
			+ " TRIM(TO_CHAR(INF.INFRAC_M_FECHA_HORA, 'DD')) || ' de ' || "
			+ " LOWER(TRIM(TO_CHAR(INF.INFRAC_M_FECHA_HORA, 'MONTH', 'nls_date_language=spanish'))) ||"
			+ " ' de ' || TRIM(TO_CHAR(INF.INFRAC_M_FECHA_HORA, 'YYYY')) || ' a las ' ||"
			+ " TRIM(TO_CHAR(INF.INFRAC_M_FECHA_HORA,'HH12:MI:SS')) || ' Hrs.' AS INFRAC_FECHA,"
			+ "  EMP.EMP_NOMBRE || ' ' || EMP.EMP_APE_PATERNO || ' ' || EMP.EMP_APE_MATERNO AS NOMBRE_OFICIAL,"
			+ " EST.FECHA_CREACION, 'México, CDMX., a '||to_char(EST.FECHA_CREACION,'DD')||' de ' || REPLACE(INITCAP(TO_CHAR (EST.FECHA_CREACION,"
			+ " 'MONTH', 'nls_date_language=spanish')), ' ', '') ||' de '||TO_CHAR (EST.FECHA_CREACION, 'YYYY') as fecha_entrega,"
			+ " GAR.GARANTIA_ID FROM ICD.GARANTIAS "
			+ " GAR JOIN ICD.GARANTIAS_CAT_DOCUMENTOS "
			+ " GARDOC ON GAR.GARANTIA_DOCUMENTO_ID = GARDOC.DOCUMENTO_ID "
			+ " JOIN ICD.INFRACCIONES INF ON GAR.INFRAC_NUM = INF.INFRAC_NUM "
			+ " JOIN ICD.GARANTIAS_PROCESO_ESTATUS EST"
			+ " ON EST.GARANTIA_ID = GAR.GARANTIA_ID AND EST.PROCESO_ID = 1 JOIN ICD.EMPLEADOS EMP ON INF.EMP_ID = EMP.EMP_ID"
			+ " WHERE GAR.GARANTIA_ID = #{garantiaId}";
	
	
//	String CONSULTA_GARANTIA_REPORTE_MASIVA = 
//			"SELECT "
//			+ " (CASE  WHEN (GARDOC.DOCUMENTO_ID = 1 OR GARDOC.DOCUMENTO_ID = 4) THEN 'Placa Vehicular' WHEN (GARDOC.DOCUMENTO_ID = 2 OR GARDOC.DOCUMENTO_ID = 5)"
//			+ " THEN 'Licencia' WHEN (GARDOC.DOCUMENTO_ID = 3 OR GARDOC.DOCUMENTO_ID = 6)"
//			+ " THEN 'Tarjeta de Circulación' END) AS NOMBRE,"
//			+ " GAR.DOCUMENTO_FOLIO AS DOCUMENTO_FOLIO, "
//			+ " INF.INFRAC_NUM AS INFRAC_NUM,"
//			+ " TRIM(TO_CHAR(INF.INFRAC_M_FECHA_HORA, 'DD')) || ' de ' || "
//			+ " TRIM(TO_CHAR(INF.INFRAC_M_FECHA_HORA, 'MONTH', 'nls_date_language=spanish')) ||"
//			+ " ' de ' || TRIM(TO_CHAR(INF.INFRAC_M_FECHA_HORA, 'YYYY')) || ' a las ' ||"
//			+ " TRIM(TO_CHAR(INF.INFRAC_M_FECHA_HORA,'HH12:MI:SS')) || ' Hrs.' AS INFRAC_FECHA,"
//			+ "  EMP.EMP_NOMBRE || ' ' || EMP.EMP_APE_PATERNO || ' ' || EMP.EMP_APE_MATERNO AS NOMBRE_OFICIAL,"
//			+ " EST.FECHA_CREACION, 'México, a '||to_char(EST.FECHA_CREACION,'DD')||' de ' || INITCAP(TO_CHAR (EST.FECHA_CREACION,"
//			+ " 'MONTH', 'nls_date_language=spanish')) ||' de '||TO_CHAR (EST.FECHA_CREACION, 'YYYY') as fecha_entrega,"
//			+ " GAR.GARANTIA_ID FROM ICD.GARANTIAS "
//			+ " GAR JOIN ICD.GARANTIAS_CAT_DOCUMENTOS "
//			+ " GARDOC ON GAR.GARANTIA_DOCUMENTO_ID = GARDOC.DOCUMENTO_ID "
//			+ " JOIN ICD.INFRACCIONES INF ON GAR.INFRAC_NUM = INF.INFRAC_NUM "
//			+ " JOIN ICD.GARANTIAS_PROCESO_ESTATUS EST"
//			+ " ON EST.GARANTIA_ID = GAR.GARANTIA_ID AND EST.PROCESO_ID = 1 JOIN ICD.EMPLEADOS EMP ON INF.EMP_ID = EMP.EMP_ID"
//			+ " WHERE GAR.GARANTIA_ID in(${myCadenaIds})";
	String CONSULTA_GARANTIA_REPORTE_MASIVA = 
			"SELECT "
			+ "ROWNUM AS numero,"
			+ " (CASE  WHEN (GARDOC.DOCUMENTO_ID = 1 OR GARDOC.DOCUMENTO_ID = 4) THEN 'Placa Vehicular' WHEN (GARDOC.DOCUMENTO_ID = 2 OR GARDOC.DOCUMENTO_ID = 5)"
			+ " THEN 'Licencia' WHEN (GARDOC.DOCUMENTO_ID = 3 OR GARDOC.DOCUMENTO_ID = 6)"
			+ " THEN 'Tarjeta de Circulación' END) AS NOMBRE,"
			+ " GAR.DOCUMENTO_FOLIO AS DOCUMENTO_FOLIO, "
			+ " INF.INFRAC_NUM AS INFRAC_NUM,"
			+ " TRIM(TO_CHAR(INF.INFRAC_M_FECHA_HORA, 'DD')) || ' de ' || "
			+ " TRIM(TO_CHAR(INF.INFRAC_M_FECHA_HORA, 'MONTH', 'nls_date_language=spanish')) ||"
			+ " ' de ' || TRIM(TO_CHAR(INF.INFRAC_M_FECHA_HORA, 'YYYY')) || ' a las ' ||"
			+ " TRIM(TO_CHAR(INF.INFRAC_M_FECHA_HORA,'HH12:MI:SS')) || ' Hrs.' AS INFRAC_FECHA,"
			+ "  EMP.EMP_NOMBRE || ' ' || EMP.EMP_APE_PATERNO || ' ' || EMP.EMP_APE_MATERNO AS NOMBRE_OFICIAL,"
			+ " EST.FECHA_CREACION, 'México, CDMX., a '||to_char(EST.FECHA_CREACION,'DD')||' de ' || REPLACE(INITCAP(TO_CHAR (EST.FECHA_CREACION,"
			+ " 'MONTH', 'nls_date_language=spanish')), ' ', '') ||' de '||TO_CHAR (EST.FECHA_CREACION, 'YYYY') as fecha_entrega,"
			+ " GAR.GARANTIA_ID FROM ICD.GARANTIAS "
			+ " GAR JOIN ICD.GARANTIAS_CAT_DOCUMENTOS "
			+ " GARDOC ON GAR.GARANTIA_DOCUMENTO_ID = GARDOC.DOCUMENTO_ID "
			+ " JOIN ICD.INFRACCIONES INF ON GAR.INFRAC_NUM = INF.INFRAC_NUM "
			+ " JOIN ICD.GARANTIAS_PROCESO_ESTATUS EST"
			+ " ON EST.GARANTIA_ID = GAR.GARANTIA_ID AND EST.PROCESO_ID = 1 JOIN ICD.EMPLEADOS EMP ON INF.EMP_ID = EMP.EMP_ID"
			+ " WHERE GAR.ID_LOTE = #{idLote}";
	
	
	String CANTIDAD_LOTE ="Select count(1) from garantias where id_lote=#{idLote}";
	
	@Select(value = CONSULTA_GARANTIA_REPORTE)
	VSSPGarantiaReporteRecibirFVO buscarGarantiasReporteRecibir(
			@Param("garantiaId") Long garantiaId);
	
//	@Select(value = CONSULTA_GARANTIA_REPORTE_MASIVA)
//	List<VSSPGarantiaReporteRecibirMasivaFVO> buscarGarantiasMasivaReporteRecibir(
//			@Param("myCadenaIds") String myCadenaIds);
	
	@Select(value = CONSULTA_GARANTIA_REPORTE_MASIVA)
	List<VSSPGarantiaReporteRecibirMasivaFVO> buscarGarantiasMasivaReporteRecibir(
			@Param("idLote") Integer idLote);
	
	@Select(value=CANTIDAD_LOTE)
	Integer cantidadLote(@Param("idLote") Integer idLote);
	
	@Select(PARAMETROS_QUERYS_CONDONACIONES)
	public List<Map<String, String>> buscarQuerys();
	String PARAMETROS_QUERYS_CONDONACIONES="SELECT CD_LLAVE_P_CONFIG,CD_VALOR_P_CONFIG FROM TAI041P_CONFIGURACION";
	

			

}
