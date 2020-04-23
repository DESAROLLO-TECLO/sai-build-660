package mx.com.teclo.saicdmx.persistencia.mybatis.dao.garantias;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import mx.com.teclo.saicdmx.persistencia.vo.comuns.FilterValuesVO;
import mx.com.teclo.saicdmx.persistencia.vo.garantias.GarantiaDetallePorPagarVO;
import mx.com.teclo.saicdmx.persistencia.vo.garantias.GarantiaPorPagarVO;
import mx.com.teclo.saicdmx.persistencia.vo.garantias.VSSPGarantiaConsGralFVO;




@Mapper
public interface GarantiaMyBatisDAO {
	
	String GET_DETALLE_GARANTIA_POR_PAGAR = "SELECT T_INFRACCION, "
		      							  + "T_PLACA, " 
		      							  + "T_NCI, " 
		      							  + "T_CANDADO, "
		      							  + "T_MOTIVO, " 
		      							  + "T_REDUCCION, " 
		      							  + "T_ACTUALIZACION, " 
		      							  + "T_RECARGOS, " 
		      							  + "T_DERECHO_PISO, " 
		      							  + "T_ARRASTRE, " 
		      							  + "T_MONTO, " 
		      							  + "T_TOTAL, " 
		      							  + "T_MONTO_ACTUALIZADO " 
		      							  + "FROM TABLE (CAST (ICD.FN_PAGOS_INFRACCION_DETALLE (#{numeroControlInterno}, #{numeroCaja}) AS ICD.T_PAGOS_INFRACCIONES))";
	
	String GET_NUMERO_CONTROL_INTERNO_GARANTIA = "SELECT "
			+ "INFRAC_NUM_CTRL "
			+ "FROM INFRACCIONES "
			+ "WHERE INFRAC_NUM = #{infracNum}";
	
	String GET_GARANTIAS_SIN_PROCEAR = 
			"SELECT  	GAR.GARANTIA_ID as garantiaId, "+
			"			INFRAC.INFRAC_NUM as infraccionFolio, "+
			"			TO_CHAR(INFRAC.INFRAC_M_FECHA_HORA, 'DD/MM/YYYY') as  fechaInfraccion, "+
			"			DOC.DOCUMENTO_ID as documentoTipoId, "+
			"			DOC.NOMBRE as  documentoNombre, "+
			"			GAR.DOCUMENTO_FOLIO as documentoFolio, "+
	        "			CATPROC.PROCESO_ID as procesoId, "+
	        "			CATPROC.NOMBRE as procesoNombre, "+
	        "			EMPl.EMP_ID as empleadoId, "+
	        "			EMPl.EMP_NOMBRE || ' '|| EMPl.EMP_APE_PATERNO || ' ' || EMPl.EMP_APE_MATERNO as empleadoNombre, "+
	        "			EMPL.EMP_PLACA as empleadoPlaca, "+
	        "			ICD.FN_DEPOSITO(INFRAC.INFRAC_NUM) as deposito "+
			"FROM    	ICD.GARANTIAS GAR, "+
			"        	ICD.INFRACCIONES INFRAC, "+
			"        	ICD.GARANTIAS_CAT_DOCUMENTOS DOC, "+
			"        	ICD.GARANTIAS_CAT_PROCESOS CATPROC, "+
			"        	ICD.EMPLEADOS EMPL "+
			"WHERE   	GAR.INFRAC_NUM = INFRAC.INFRAC_NUM "+
			"AND     	GAR.GARANTIA_DOCUMENTO_ID = DOC.DOCUMENTO_ID "+
			"AND     	GAR.GARANTIA_PROCESO_ID = CATPROC.PROCESO_ID "+
			"AND     	GAR.EMP_ID = EMPL.EMP_ID "+
			"AND     	EMPL.EMP_PLACA = (CASE   "+
			"                            	WHEN #{placaOficial} IS NOT NULL "+
			"                                	THEN #{placaOficial} "+
			"                            	ELSE EMPL.EMP_PLACA "+
			"                          	  END) "+
			"AND     	CATPROC.PROCESO_ID = #{idProceso}"+
			" AND ROWNUM <= 20000 ";
	
	String GET_GARANTIAS_SIN_PROCEAR_2 = 
			"SELECT  	GAR.GARANTIA_ID as garantiaId, "+
			"			INFRAC.INFRAC_NUM as infraccionFolio, "+
			"			TO_CHAR(INFRAC.INFRAC_M_FECHA_HORA, 'DD/MM/YYYY') as  fechaInfraccion, "+
			"			DOC.DOCUMENTO_ID as documentoTipoId, "+
			"			DOC.NOMBRE as  documentoNombre, "+
			"			GAR.DOCUMENTO_FOLIO as documentoFolio, "+
	        "			CATPROC.PROCESO_ID as procesoId, "+
	        "			CATPROC.NOMBRE as procesoNombre, "+
	        "			EMPl.EMP_ID as empleadoId, "+
	        "			EMPl.EMP_NOMBRE || ' '|| EMPl.EMP_APE_PATERNO || ' ' || EMPl.EMP_APE_MATERNO as empleadoNombre, "+
	        "			EMPL.EMP_PLACA as empleadoPlaca, "+
	        "			ICD.FN_DEPOSITO(INFRAC.INFRAC_NUM) as deposito "+
			"FROM    	ICD.GARANTIAS GAR, "+
			"        	ICD.INFRACCIONES INFRAC, "+
			"        	ICD.GARANTIAS_CAT_DOCUMENTOS DOC, "+
			"        	ICD.GARANTIAS_CAT_PROCESOS CATPROC, "+
			"        	ICD.EMPLEADOS EMPL "+
			"WHERE   	GAR.INFRAC_NUM = INFRAC.INFRAC_NUM "+
			"AND     	GAR.GARANTIA_DOCUMENTO_ID = DOC.DOCUMENTO_ID "+
			"AND     	GAR.GARANTIA_PROCESO_ID = CATPROC.PROCESO_ID "+
			"AND     	GAR.EMP_ID = EMPL.EMP_ID "+
			"AND     	EMPL.EMP_PLACA = (CASE   "+
			"                            	WHEN #{placaOficial} IS NOT NULL "+
			"                                	THEN #{placaOficial} "+
			"                            	ELSE EMPL.EMP_PLACA "+
			"                          	  END) "+
			"AND     	CATPROC.PROCESO_ID = #{idProceso} " + 
			"AND GAR.GARANTIA_DOCUMENTO_ID IN (1, 2, 3) "+
			"AND ROWNUM <= 20000 ";
	
	String GET_GARANTIAS_POR_PAGAR = "SELECT "
			+ "G.GARANTIA_ID AS garantiaId, "
			+ "C.NOMBRE AS garantiaDocumentoId, "
			+ "G.DOCUMENTO_FOLIO AS documentoFolio, " 
			+ "TO_CHAR(P.FECHA_CREACION, 'DD/MM/YYYY') AS fechaCreacionGarantia, "
			+ "(CASE G.RECIBIDA "
			+ "WHEN 1 THEN 'SÍ' "
			+ "ELSE 'NO' "
			+ "END) AS recibida, "
			+ "I.INFRAC_NUM AS infracNum, "
			+ "I.ESTATUS_CANCELACION AS estatusCancelacion, " 
			+ "TO_CHAR(I.FECHA_CREACION, 'DD/MM/YYYY') AS fechaCreacionInfraccion " 
			+ "FROM ICD.GARANTIAS G "
			+ "INNER JOIN ICD.INFRACCIONES I ON G.INFRAC_NUM = I.INFRAC_NUM " 
			+ "INNER JOIN ICD.GARANTIAS_CAT_DOCUMENTOS C ON G.GARANTIA_DOCUMENTO_ID = C.DOCUMENTO_ID "
			+ "INNER JOIN ICD.GARANTIAS_PROCESO_ESTATUS P ON P.GARANTIA_ID = G.GARANTIA_ID "
			+ "AND G.GARANTIA_PROCESO_ID=P.PROCESO_ID " 
			+ "WHERE    (I.INFRAC_NUM = #{infracNum} "
			+ "OR G.DOCUMENTO_FOLIO = #{documentoFolio} " 
			+ "OR I.INFRAC_PLACA = #{infracPlaca}) "
			+ "AND G.GARANTIA_PROCESO_ID = 1 "
			+ "AND 	G.RECIBIDA = 1 ";
	
	String SET_GARANTIA_PAGADA = "UPDATE GARANTIAS "
							   + "SET PAGADA = 1, "
							   + "TIPO_PAGO = #{tipoPago}, "
							   + "GARANTIA_PROCESO_ID = 2, "
							   + "MODIFICADO_POR = #{userId}, "
							   + "ULTIMA_MODIFICACION = SYSDATE  "
							   + "WHERE INFRAC_NUM = #{infracNum}";
	
	String ADD_PROCESO_STATUS_GARANTIA = "INSERT INTO GARANTIAS_PROCESO_ESTATUS "
									   + "VALUES(2, #{garantiaId}, #{observaciones}, #{userId}, SYSDATE,#{userId}, SYSDATE)";
	
	String SET_GARANTIA_NO_PAGADA = "UPDATE GARANTIAS "
			   + "SET PAGADA = 0, "
			   + "ENTREGADA = 0,"
			   + "TIPO_PAGO = null, "
			   + "GARANTIA_PROCESO_ID = 1, "
			   + "MODIFICADO_POR = #{userId}, "
			   + "ULTIMA_MODIFICACION = SYSDATE  "
			   + "WHERE INFRAC_NUM = #{infracNum}";
	
	String ADD_PROCESO_STATUS_GARANTIA_CANCELACION_PAGO = "INSERT INTO GARANTIAS_PROCESO_ESTATUS "
			   + "VALUES(5, #{garantiaId}, #{observaciones}, #{userId}, SYSDATE,SYSDATE,#{userId})";
	
	String GET_GARANTIA_ID_POR_INFRACNUM = "SELECT GARANTIA_ID FROM GARANTIAS WHERE INFRAC_NUM = #{infracNum}";
	
	String GET_ID__PAGADA_GARANTIA_PROCESO_ESTATUS = "SELECT OBSERVACIONES FROM GARANTIAS_PROCESO_ESTATUS WHERE PROCESO_ID = #{procesoId} AND GARANTIA_ID=#{garantiaId}";
	
	String UPDATE_GARANTIA_PROCESO_ESTATUS="UPDATE GARANTIAS_PROCESO_ESTATUS SET ULTIMA_MODIFICACION=sysdate, MODIFICADO_POR=#{empId} where Proceso_Id=#{procesoId} and Garantia_Id=#{garantiaId}";
	
	@Select(value = GET_GARANTIAS_SIN_PROCEAR)
	List<VSSPGarantiaConsGralFVO> buscarGarantiasSinProcesar(
			@Param("placaOficial") String placaOficial,
			@Param("idProceso") Integer idProceso);
	
	@Select(value = GET_GARANTIAS_SIN_PROCEAR_2)
	List<VSSPGarantiaConsGralFVO> buscarGarantiasSinProcesarOp(
			@Param("placaOficial") String placaOficial,
			@Param("idProceso") Integer idProceso);
	
	@Select(value = GET_GARANTIAS_POR_PAGAR)
	List<GarantiaPorPagarVO> buscarGarantiasPorPagar(
			@Param("infracNum") String infracNum,
			@Param("documentoFolio") String documentoFolio,
			@Param("infracPlaca") String infracPlaca);
	
	@Select(value = GET_NUMERO_CONTROL_INTERNO_GARANTIA)
	String buscarNumeroControlInfraccion(@Param("infracNum") String infracNum);
	
	@Select(value = GET_DETALLE_GARANTIA_POR_PAGAR)
	List<GarantiaDetallePorPagarVO> buscarDetalleGarantiaPorPagar(
			@Param("numeroControlInterno") String numeroControlInterno, 
			@Param("numeroCaja") String numeroCaja);
	
	@Update(value = SET_GARANTIA_PAGADA)
	Integer guardarGarantiaPagada(
			@Param("tipoPago") Integer tipoPago, 
			@Param("userId") Long userId, 
			@Param("infracNum") String infracNum);
	
	@Insert(value = ADD_PROCESO_STATUS_GARANTIA)
	Integer guardarProcesoEstatusGarantiaPagada(
			@Param("garantiaId") String garantiaId, 
			@Param("userId") Long userId, 
			@Param("observaciones") String observaciones);
	
	@Select(value = GET_GARANTIA_ID_POR_INFRACNUM)
	List<String> obtenerGarantiaIdPorInfraccion(
			@Param("infracNum") String infracNum);
	
	@Update(value = SET_GARANTIA_NO_PAGADA)
	Integer guardarGarantiaPagadaNoPagada(
			@Param("userId") Long userId, 
			@Param("infracNum") String infracNum);
	
	@Insert(value = ADD_PROCESO_STATUS_GARANTIA_CANCELACION_PAGO)
	Integer guardarProcesoEstatusGarantiaNoPagada(
			@Param("garantiaId") String garantiaId, 
			@Param("userId") Long userId, 
			@Param("observaciones") String observaciones);
	
	@Select(value = GET_ID__PAGADA_GARANTIA_PROCESO_ESTATUS)
	List<String> obtenerObservacionGarantiaProcesoEstatus(
			@Param("garantiaId") String garantiaId,@Param("procesoId") Integer procesoId);
	
	@Update(value = UPDATE_GARANTIA_PROCESO_ESTATUS)
	Integer updateGarantiaProcesoEstatus(
			@Param("garantiaId") String garantiaId,@Param("procesoId") Integer procesoId,
			@Param("empId") Long empId);
	
	String GET_INFRAC_DE_GAR = "SELECT "
			+ "NVL(I.INFRAC_GAR_ASOCIADA, I.INFRAC_NUM) AS infrac "
			+ "FROM "
			+ "GARANTIAS G "
			+ "INNER JOIN INFRACCIONES I ON G.INFRAC_NUM = I.INFRAC_NUM "
			+ "WHERE ROWNUM = 1 "
			+ "AND ( "
			+ "	I.INFRAC_PLACA = #{infracPlaca} "
			+ "	OR I.INFRAC_NUM = #{infracNum} "
			+ "	OR G.DOCUMENTO_FOLIO = #{documentoFolio} "
			+ "	OR G.GARANTIA_ID = #{documentoId})";
		
	@Select(value = GET_INFRAC_DE_GAR)
	String buscarInfracGarantia(
			@Param("infracNum") String infracNum,
			@Param("documentoFolio") String documentoFolio,
			@Param("infracPlaca") String infracPlaca,
			@Param("documentoId") String documentoId);
	
	String GET_INFRAC_DE_GAR_2 = "SELECT "
			+ "INFRAC_GAR_ASOCIADA AS infrac "
			+ "FROM "
			+ "INFRACCIONES "
			+ "WHERE ROWNUM = 1 "
			+ "AND INFRAC_NUM = #{infracNum}";
		
	@Select(value = GET_INFRAC_DE_GAR_2)
	String buscarInfracGarantia_2(
			@Param("infracNum") String infracNum);
	
	String GET_GARANTIAS_POR_PAGAR_INF = "SELECT "
			+ "G.GARANTIA_ID AS GARANTIAID, "
			+ "C.NOMBRE AS GARANTIADOCUMENTOID, "
			+ "G.DOCUMENTO_FOLIO AS DOCUMENTOFOLIO, "
			+ "TO_CHAR(P.FECHA_CREACION) AS "
			+ "FECHACREACIONGARANTIA, "
			+ "(CASE G.RECIBIDA WHEN 1 THEN 'SÍ' ELSE 'NO' END) AS RECIBIDA, "
			+ "I.INFRAC_NUM AS INFRACNUM, TO_CHAR(I.FECHA_CREACION) AS FECHACREACIONINFRACCION, "
			+ "G.PAGADA AS estatus "
			+ "FROM GARANTIAS G "
			+ "INNER JOIN INFRACCIONES I ON G.INFRAC_NUM = I.INFRAC_NUM "
			+ "INNER JOIN ICD.GARANTIAS_CAT_DOCUMENTOS C ON G.GARANTIA_DOCUMENTO_ID = C.DOCUMENTO_ID "
			+ "INNER JOIN ICD.GARANTIAS_PROCESO_ESTATUS P ON P.GARANTIA_ID = G.GARANTIA_ID "
			+ "WHERE "
			+ "G.GARANTIA_PROCESO_ID = 1 "
			+ "AND G.RECIBIDA = 1 "
			+ "AND P.PROCESO_ID = 1 "
			+ "AND ("
			+ "		I.INFRAC_NUM = #{infrac} "
			+ "	OR "
			+ "		I.INFRAC_GAR_ASOCIADA = #{infrac}) "
			+ "union "
			+ GET_GARANTIAS_POR_PAGAR;
			
	@Select(value = GET_GARANTIAS_POR_PAGAR_INF)
	List<GarantiaPorPagarVO> buscarGarantiasPorPagarInf(
			@Param("infrac") String infrac,
			@Param("infracNum") String infracNum,
			@Param("documentoFolio") String documentoFolio,
			@Param("infracPlaca") String infracPlaca);
	
	String GET_GARANTIAS_PAGADAS_INF = "SELECT "
			+ "G.GARANTIA_ID AS GARANTIAID, "
			+ "C.NOMBRE AS GARANTIADOCUMENTOID, "
			+ "G.DOCUMENTO_FOLIO AS DOCUMENTOFOLIO, "
			+ "TO_CHAR(P.FECHA_CREACION) AS "
			+ "FECHACREACIONGARANTIA, "
			+ "(CASE G.RECIBIDA WHEN 1 THEN 'SÍ' ELSE 'NO' END) AS RECIBIDA, "
			+ "I.INFRAC_NUM AS INFRACNUM, TO_CHAR(I.FECHA_CREACION) AS FECHACREACIONINFRACCION, "
			+ "G.PAGADA AS estatus "
			+ "FROM GARANTIAS G "
			+ "INNER JOIN INFRACCIONES I ON G.INFRAC_NUM = I.INFRAC_NUM "
			+ "INNER JOIN ICD.GARANTIAS_CAT_DOCUMENTOS C ON G.GARANTIA_DOCUMENTO_ID = C.DOCUMENTO_ID "
			+ "INNER JOIN ICD.GARANTIAS_PROCESO_ESTATUS P ON P.GARANTIA_ID = G.GARANTIA_ID "
			+ "WHERE "
			+ "G.GARANTIA_PROCESO_ID = 2 "
			+ "AND G.PAGADA = 1 "
			+ "AND P.PROCESO_ID = 2 "
			+ "AND I.INFRAC_GAR_ASOCIADA = #{infrac} "
			+ "AND (SELECT PAGADA FROM GARANTIAS WHERE INFRAC_NUM = #{infrac}) = 0";
			
	@Select(value = GET_GARANTIAS_PAGADAS_INF)
	List<GarantiaPorPagarVO> buscarGarantiasPagadasInf(
			@Param("infrac") String infrac);
	
	String GET_CONT_GARANTIAS_AS_PAGADAS = "SELECT COUNT(G.INFRAC_NUM) FROM GARANTIAS G "
			+ "INNER JOIN INFRACCIONES I ON G.INFRAC_NUM = I.INFRAC_NUM "
			+ "WHERE G.PAGADA = 0 AND G.GARANTIA_PROCESO_ID = 1 AND I.INFRAC_GAR_ASOCIADA = #{infraNum}";
		
		@Select(value = GET_CONT_GARANTIAS_AS_PAGADAS)
		Integer buscarGarantiasAsociadaPagadas(
				@Param("infraNum") String infraNum);
}

