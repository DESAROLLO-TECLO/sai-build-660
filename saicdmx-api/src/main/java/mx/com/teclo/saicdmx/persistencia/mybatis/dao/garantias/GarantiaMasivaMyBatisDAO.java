package mx.com.teclo.saicdmx.persistencia.mybatis.dao.garantias;


import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import mx.com.teclo.saicdmx.persistencia.vo.garantias.FechasGarantiasVO;
import mx.com.teclo.saicdmx.persistencia.vo.garantias.VSSPGarantiaConsGralFVO;

@Mapper
public interface GarantiaMasivaMyBatisDAO {
	
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
			" FROM    	ICD.GARANTIAS GAR, "+
			"        	ICD.INFRACCIONES INFRAC, "+
			"        	ICD.GARANTIAS_CAT_DOCUMENTOS DOC, "+
			"        	ICD.GARANTIAS_CAT_PROCESOS CATPROC, "+
			"        	ICD.EMPLEADOS EMPL "+
			" WHERE   	GAR.INFRAC_NUM = INFRAC.INFRAC_NUM "+
			" AND     	GAR.GARANTIA_DOCUMENTO_ID = DOC.DOCUMENTO_ID "+
			" AND     	GAR.GARANTIA_PROCESO_ID = CATPROC.PROCESO_ID "+
			" AND     	GAR.EMP_ID = EMPL.EMP_ID "+
			" AND     	EMPL.EMP_PLACA = (CASE   "+
			"                            	WHEN #{placaOficial} IS NOT NULL "+
			"                                	THEN #{placaOficial} "+
			"                            	ELSE EMPL.EMP_PLACA "+
			"                          	  END) "+
			" AND     	CATPROC.PROCESO_ID = #{idProceso}"+
			" AND GAR.GARANTIA_DOCUMENTO_ID NOT IN (7) "+
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
			" FROM    	ICD.GARANTIAS GAR, "+
			"        	ICD.INFRACCIONES INFRAC, "+
			"        	ICD.GARANTIAS_CAT_DOCUMENTOS DOC, "+
			"        	ICD.GARANTIAS_CAT_PROCESOS CATPROC, "+
			"        	ICD.EMPLEADOS EMPL "+
			" WHERE   	GAR.INFRAC_NUM = INFRAC.INFRAC_NUM "+
			" AND     	GAR.GARANTIA_DOCUMENTO_ID = DOC.DOCUMENTO_ID "+
			" AND     	GAR.GARANTIA_PROCESO_ID = CATPROC.PROCESO_ID "+
			" AND     	GAR.EMP_ID = EMPL.EMP_ID "+
			" AND     	EMPL.EMP_PLACA = (CASE   "+
			"                            	WHEN #{placaOficial} IS NOT NULL "+
			"                                	THEN #{placaOficial} "+
			"                            	ELSE EMPL.EMP_PLACA "+
			"                          	  END) "+
			" AND     	CATPROC.PROCESO_ID = #{idProceso} " + 
			" AND GAR.GARANTIA_DOCUMENTO_ID NOT IN (4, 5, 6, 7) "+
			" AND ROWNUM <= 20000 ";
	
	
	String GET_GARANTIAS_SIN_PROCESAR_FECH = 
		"SELECT  	GAR.GARANTIA_ID as garantiaId, "+
					"INFRAC.INFRAC_NUM as infraccionFolio, "+
					"TO_CHAR(INFRAC.INFRAC_M_FECHA_HORA, 'DD/MM/YYYY') as  fechaInfraccion, "+
					"DOC.DOCUMENTO_ID as documentoTipoId, "+
					"DOC.NOMBRE as  documentoNombre, "+
					"GAR.DOCUMENTO_FOLIO as documentoFolio, "+
					"CATPROC.PROCESO_ID as procesoId, "+
					"CATPROC.NOMBRE as procesoNombre, "+
					"EMPl.EMP_ID as empleadoId, "+
					"EMPl.EMP_NOMBRE || ' '|| EMPl.EMP_APE_PATERNO || ' ' || EMPl.EMP_APE_MATERNO as empleadoNombre,"+
					"EMPL.EMP_PLACA as empleadoPlaca, "+
					"ICD.FN_DEPOSITO(INFRAC.INFRAC_NUM) as deposito "+
		" FROM    	ICD.GARANTIAS GAR, "+
		 "      	ICD.INFRACCIONES INFRAC, "+
		 "       	ICD.GARANTIAS_CAT_DOCUMENTOS DOC, "+
		"        	ICD.GARANTIAS_CAT_PROCESOS CATPROC, "+
		"       	ICD.EMPLEADOS EMPL "+
		" WHERE   	GAR.INFRAC_NUM = INFRAC.INFRAC_NUM "+
		" AND     	GAR.GARANTIA_DOCUMENTO_ID = DOC.DOCUMENTO_ID "+
		" AND     	GAR.GARANTIA_PROCESO_ID = CATPROC.PROCESO_ID "+
		" AND     	GAR.EMP_ID = EMPL.EMP_ID "+
		" AND     	EMPL.EMP_PLACA = (CASE   "+
		"                            	WHEN #{placaOficial} IS NOT NULL "+
		"                             		THEN #{placaOficial} "+
		"                          	ELSE EMPL.EMP_PLACA "+
		"                         	  END) "+
		" AND     	CATPROC.PROCESO_ID =0"+
		" AND     	GAR.GARANTIA_DOCUMENTO_ID NOT IN (7) "+
		" AND TRUNC(INFRAC.INFRAC_M_FECHA_HORA) BETWEEN TO_DATE( #{startDate} , 'dd/MM/yyyy') AND TO_DATE( #{endDate} , 'dd/MM/yyyy')"+
		 " AND ROWNUM <= 20000 ";
	
	//La terminacion SP significa sin promesas de pago
	String GET_GARANTIAS_SIN_PROCESAR_FECH_SP = 
			"SELECT  	GAR.GARANTIA_ID as garantiaId, "+
						"INFRAC.INFRAC_NUM as infraccionFolio, "+
						"TO_CHAR(INFRAC.INFRAC_M_FECHA_HORA, 'DD/MM/YYYY') as  fechaInfraccion, "+
						"DOC.DOCUMENTO_ID as documentoTipoId, "+
						"DOC.NOMBRE as  documentoNombre, "+
						"GAR.DOCUMENTO_FOLIO as documentoFolio, "+
						"CATPROC.PROCESO_ID as procesoId, "+
						"CATPROC.NOMBRE as procesoNombre, "+
						"EMPl.EMP_ID as empleadoId, "+
						"EMPl.EMP_NOMBRE || ' '|| EMPl.EMP_APE_PATERNO || ' ' || EMPl.EMP_APE_MATERNO as empleadoNombre,"+
						"EMPL.EMP_PLACA as empleadoPlaca, "+
						"ICD.FN_DEPOSITO(INFRAC.INFRAC_NUM) as deposito "+
			" FROM    	ICD.GARANTIAS GAR, "+
			 "      	ICD.INFRACCIONES INFRAC, "+
			 "       	ICD.GARANTIAS_CAT_DOCUMENTOS DOC, "+
			"        	ICD.GARANTIAS_CAT_PROCESOS CATPROC, "+
			"       	ICD.EMPLEADOS EMPL "+
			" WHERE   	GAR.INFRAC_NUM = INFRAC.INFRAC_NUM "+
			" AND     	GAR.GARANTIA_DOCUMENTO_ID = DOC.DOCUMENTO_ID "+
			" AND     	GAR.GARANTIA_PROCESO_ID = CATPROC.PROCESO_ID "+
			" AND     	GAR.EMP_ID = EMPL.EMP_ID "+
			" AND     	EMPL.EMP_PLACA = (CASE   "+
			"                            	WHEN #{placaOficial} IS NOT NULL "+
			"                             		THEN #{placaOficial} "+
			"                          	ELSE EMPL.EMP_PLACA "+
			"                         	  END) "+
			" AND     	CATPROC.PROCESO_ID =0"+
			" AND     	GAR.GARANTIA_DOCUMENTO_ID NOT IN (4,5,6,7) "+
			" AND TRUNC(INFRAC.INFRAC_M_FECHA_HORA) BETWEEN TO_DATE( #{startDate} , 'dd/MM/yyyy') AND TO_DATE( #{endDate} , 'dd/MM/yyyy')"+
			 " AND ROWNUM <= 20000 ";
	
	String GET_FECHA_INICIAL_FINAL =
			"SELECT FECHAINICIAL as fechaInicial, FECHAFINAL as fechaFinal FROM "+
			"( ${txtQuery} ) TABLE2 ";
	
	String GET_FECHAS_CAT =
			"SELECT  ROWNUM AS numero, 	GAR.GARANTIA_ID as garantiaId, "+
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
					" FROM    	ICD.GARANTIAS GAR, "+
					"        	ICD.INFRACCIONES INFRAC, "+
					"        	ICD.GARANTIAS_CAT_DOCUMENTOS DOC, "+
					"        	ICD.GARANTIAS_CAT_PROCESOS CATPROC, "+
					"        	ICD.EMPLEADOS EMPL "+
					" WHERE   	GAR.INFRAC_NUM = INFRAC.INFRAC_NUM "+
					" AND     	GAR.GARANTIA_DOCUMENTO_ID = DOC.DOCUMENTO_ID "+
					" AND     	GAR.GARANTIA_PROCESO_ID = CATPROC.PROCESO_ID "+
					" AND     	GAR.EMP_ID = EMPL.EMP_ID "+
					" AND     	EMPL.EMP_PLACA = (CASE   "+
					"                            	WHEN #{placaOficial} IS NOT NULL "+
					"                                	THEN #{placaOficial} "+
					"                            	ELSE EMPL.EMP_PLACA "+
					"                          	  END) "+
					" AND     	CATPROC.PROCESO_ID = #{idProceso}"+
					" AND     	GAR.GARANTIA_DOCUMENTO_ID not in (7) "+
					" AND TRUNC(INFRAC.INFRAC_M_FECHA_HORA) BETWEEN TO_DATE( #{fech1} , 'dd/MM/yyyy') AND TO_DATE( #{fech2} , 'dd/MM/yyyy') "+
					" AND ROWNUM <= 20000 ";
	
	String GET_FECHAS_CAT_SIN_PROMESAS =
			"SELECT  ROWNUM AS numero, 	GAR.GARANTIA_ID as garantiaId, "+
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
					" FROM    	ICD.GARANTIAS GAR, "+
					"        	ICD.INFRACCIONES INFRAC, "+
					"        	ICD.GARANTIAS_CAT_DOCUMENTOS DOC, "+
					"        	ICD.GARANTIAS_CAT_PROCESOS CATPROC, "+
					"        	ICD.EMPLEADOS EMPL "+
					" WHERE   	GAR.INFRAC_NUM = INFRAC.INFRAC_NUM "+
					" AND     	GAR.GARANTIA_DOCUMENTO_ID = DOC.DOCUMENTO_ID "+
					" AND     	GAR.GARANTIA_PROCESO_ID = CATPROC.PROCESO_ID "+
					" AND     	GAR.EMP_ID = EMPL.EMP_ID "+
					" AND     	EMPL.EMP_PLACA = (CASE   "+
					"                            	WHEN #{placaOficial} IS NOT NULL "+
					"                                	THEN #{placaOficial} "+
					"                            	ELSE EMPL.EMP_PLACA "+
					"                          	  END) "+
					" AND     	CATPROC.PROCESO_ID = #{idProceso}"+
					" AND     	GAR.GARANTIA_DOCUMENTO_ID not in (4,5,6,7) "+
					" AND TRUNC(INFRAC.INFRAC_M_FECHA_HORA) BETWEEN TO_DATE( #{fech1} , 'dd/MM/yyyy') AND TO_DATE( #{fech2} , 'dd/MM/yyyy') "+
					" AND ROWNUM <= 20000 ";
	
	
	String NEXT_VAL_SEQUENCE="select SQAIGARANTIAS_LOTE.nextval from dual";
	
	
	
	@Select(value = GET_GARANTIAS_SIN_PROCEAR)
	List<VSSPGarantiaConsGralFVO> buscarGarantiasSinProcesar(
			@Param("placaOficial") String placaOficial,
			@Param("idProceso") Integer idProceso//,
			//@Param("fech1") String fech1,
			//@Param("fech2") String fech2
			);
	
	
	@Select(value = GET_GARANTIAS_SIN_PROCEAR_2)
	List<VSSPGarantiaConsGralFVO> buscarGarantiasSinProcesarOp(
			@Param("placaOficial") String placaOficial,
			@Param("idProceso") Integer idProceso);
	
	
	@Select(value=GET_GARANTIAS_SIN_PROCESAR_FECH)
	List<VSSPGarantiaConsGralFVO> buscarGarantiaSinProcesarFech(
			@Param("placaOficial") String placaOficial,
			@Param("idProceso") Integer idProceso,
			@Param("startDate") String startDate,
			@Param("endDate") String endDate);
	
	@Select(value=GET_GARANTIAS_SIN_PROCESAR_FECH_SP)
	List<VSSPGarantiaConsGralFVO> buscarGarantiaSinProcesarFechSP(
			@Param("placaOficial") String placaOficial,
			@Param("idProceso") Integer idProceso,
			@Param("startDate") String startDate,
			@Param("endDate") String endDate);
	
	@Select(value=GET_FECHA_INICIAL_FINAL)
	FechasGarantiasVO fechasInicialFinal(
			@Param("txtQuery") String txtQuery);
	
	@Select(value=GET_FECHAS_CAT)
	List<VSSPGarantiaConsGralFVO>buscarGarantiasSinProcesarWithFech(
			@Param("placaOficial") String placaOficial,
			@Param("idProceso") Integer idProceso,
			@Param("fech1") String date,
			@Param("fech2") String date2
			);
	
	@Select(value=GET_FECHAS_CAT_SIN_PROMESAS)
	List<VSSPGarantiaConsGralFVO>buscarGarantiasSinProcesarWithFechSP(
			@Param("placaOficial") String placaOficial,
			@Param("idProceso") Integer idProceso,
			@Param("fech1") String date,
			@Param("fech2") String date2
			);
	
	@Select(value = NEXT_VAL_SEQUENCE)
	Long buscarInfracGarantia();
	
	@Select("SELECT TX_TIPO_FECHA FROM TAI044C_TIPO_FECHAS WHERE ID_TIPO_FECHA = #{idTipoFecha}")
	String getQueryCalcularFechas(@Param("idTipoFecha") Integer idTipoFecha);

}
