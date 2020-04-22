package mx.com.teclo.saicdmx.persistencia.mybatis.dao.garantias;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import mx.com.teclo.saicdmx.persistencia.vo.garantias.VSSPGarantiaReporteEntregarFVO;

@Mapper
public interface GarantiaReporteEntregaMyBatisDAO {
	
	String CONSULTA_GARANTIA_REPORTE_ENTREGA = 
			 "SELECT (CASE WHEN (GARDOC.DOCUMENTO_ID = 1 OR GARDOC.DOCUMENTO_ID = 4)"
			 + " THEN 'Placa Vehicular'"
			 + " WHEN (GARDOC.DOCUMENTO_ID = 2 OR GARDOC.DOCUMENTO_ID = 5)"
			 + " THEN 'Licencia' "
			 + " WHEN (GARDOC.DOCUMENTO_ID = 3 OR GARDOC.DOCUMENTO_ID = 6)"
			 + " THEN 'Tarjeta de Circulación'"
			 + " END)"
			 + " AS NOMBRE, GAR.DOCUMENTO_FOLIO AS DOCUMENTO_FOLIO, INF.INFRAC_NUM AS INFRAC_NUM,"
			 + " TRIM (TO_CHAR (INF.INFRAC_M_FECHA_HORA, 'DD'))"
			 + " || ' de ' || TRIM (TO_CHAR (INF.INFRAC_M_FECHA_HORA, 'MONTH', 'nls_date_language=spanish'))"
			 + " || ' de ' || TRIM (TO_CHAR (INF.INFRAC_M_FECHA_HORA, 'YYYY')) || ' a las '"
			 + " || TRIM (TO_CHAR (INF.INFRAC_M_FECHA_HORA, 'HH12:MI:SS')) || ' Hrs.' AS INFRAC_FECHA,"
			 + " EST.FECHA_CREACION, 'México, a '||to_char(EST.FECHA_CREACION,'DD')||' de '"
			 + " || INITCAP(TO_CHAR (EST.FECHA_CREACION, 'MONTH', 'nls_date_language=spanish'))"
			 + " ||' de '||TO_CHAR (EST.FECHA_CREACION, 'YYYY') as fecha_recibimiento, GAR.GARANTIA_ID FROM ICD.GARANTIAS GAR"
			 + " JOIN ICD.GARANTIAS_CAT_DOCUMENTOS GARDOC"
			 + " ON GAR.GARANTIA_DOCUMENTO_ID = GARDOC.DOCUMENTO_ID"
			 + " JOIN ICD.INFRACCIONES INF"
			 + " ON GAR.INFRAC_NUM = INF.INFRAC_NUM"
			 + " JOIN ICD.GARANTIAS_PROCESO_ESTATUS EST"
			 + " ON EST.GARANTIA_ID = GAR.GARANTIA_ID AND EST.PROCESO_ID = 3"
			 + " AND     GAR.GARANTIA_ID = #{garantiaId}";
	          
	
	
	
	@Select(value = CONSULTA_GARANTIA_REPORTE_ENTREGA)
	      	VSSPGarantiaReporteEntregarFVO buscarGarantiasReporteEntregar(
	      			@Param("garantiaId") Long garantiaId);

}
