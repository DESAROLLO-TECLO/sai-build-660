package mx.com.teclo.saicdmx.persistencia.mybatis.dao.fotomultadetecciones;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import mx.com.teclo.saicdmx.persistencia.vo.fotomulta.ConsultaDeteccionesVO;
import mx.com.teclo.saicdmx.persistencia.vo.fotomulta.DeteccionesResultadoVO;
import mx.com.teclo.saicdmx.persistencia.vo.fotomulta.DeteccionesSinProcesarVO;
import mx.com.teclo.saicdmx.persistencia.vo.fotomulta.ReporteDeteccionesVO;


@Mapper
public interface FotomultaDeteccionesMyBatisDAO {
	
	String REACTIVA_DETECCIONES_CANCELA=
			"UPDATE  FOTOMULTA_DETECCIONES "+
			"SET     PROCESADO = 0, LOTE_ID = 0 "+
			"WHERE   LOTE_ID = #{loteId}";
	
	String FOTOMULTAS_BY_FECHA_INFRACCION = "SELECT FM.PLACA AS PLACA, FM.ORIGEN_PLACA AS ORIGENPLACA,"
			+"FM.TDSKUID AS TDSKUID, "
			+"REPLACE(FM.FECHA,'.','/') AS FECHA, "
			+"FM.HORA AS HORA, "
			+"(CASE " 
		    +"WHEN FM.AUTORIZADA = 1 "
		    +"THEN 'SI' "
		    +"WHEN FM.AUTORIZADA = 0 "
		    +"THEN 'NO' "
		    +"ELSE 'N/A' "
		    +"END ) AS AUTORIZADA, "
			+"AR.ART_DIAS AS DIASSANCION, "
			+"AR.ART_NUMERO || AR.ART_FRACCION || AR.ART_PARRAFO || AR.ART_INCISO AS FUNDAMENTACION, "
			+"TO_CHAR(FM.FECHA_CREACION, 'DD/MM/YYYY') AS FECHACREACION, "
			+"(CASE "
            +	"WHEN FCTR.CODIGO = 'LR' "
            +   	"THEN 'LUZ ROJA' "
            +	"WHEN FCTR.CODIGO = 'MI' "
            +   	"THEN 'BOSCH' "
            +	"WHEN FCTR.CODIGO = 'MR' "
            +   	"THEN 'RADAR LASER' "
            +	"ELSE 'N/A' "  
            +"END) AS RADARTIPO "
			+ "FROM FOTOMULTA_DETECCIONES FM "
			+"LEFT JOIN ARTICULOS AR ON FM.ARTICULO_ID = AR.ART_ID "
			+"LEFT JOIN FOTOMULTA_CAT_TIPO_RADAR FCTR ON FM.RADAR_TIPO_ID = FCTR.CAT_TIPO_RADAR_ID "
			+"WHERE TO_DATE(REPLACE(FM.FECHA,'.','/'), 'dd/MM/yy') BETWEEN #{fechaInicio} AND #{fechaFin} "
			+"AND FM.VALIDO = #{valido} "
			+"AND FM.AUTORIZADA =  CASE "
			+"WHEN #{autorizado} = 1 OR #{autorizado} = 0 "
			+"THEN #{autorizado} "
			+"ELSE FM.AUTORIZADA "
			+"END "
			+"AND 	FM.PROCESADO =  CASE "
			+"WHEN #{procesado} = 1 OR #{procesado} = 0 "
			+"THEN #{procesado} "
			+"ELSE FM.PROCESADO "
			+"END "
			+"AND FM.RADAR_TIPO_ID =  CASE "
			+"WHEN #{radarTipo} > 0 "
			+"THEN #{radarTipo} "
			+"ELSE FM.RADAR_TIPO_ID "
			+"END "
			+"AND FM.ORIGEN_PLACA = CASE "
			+"WHEN #{origenPlaca} = 1 OR #{origenPlaca} = 0 "
			+"THEN #{origenPlaca} "
			+"ELSE FM.ORIGEN_PLACA "
			+"END "
			+"AND ROWNUM <= 20000  "
			+"ORDER BY FM.FOTOMULTA_ID DESC";
	
	String FOTOMULTAS_BY_FECHA_VALIDACION = "SELECT FM.PLACA AS PLACA, FM.ORIGEN_PLACA AS ORIGENPLACA, "
			+ 			"FM.TDSKUID AS TDSKUID, "
			+ 			"REPLACE(FM.FECHA,'.','/') AS FECHA, "
			+ 			"FM.HORA AS HORA, "
			+			"(CASE " 
		    +        		"WHEN FM.AUTORIZADA = 1 "
		    +             		"THEN 'SI' "
		    +        		"WHEN FM.AUTORIZADA = 0 "
		    +            		"THEN 'NO' "
		    +        		"ELSE 'N/A' "
		    +    		"END ) AS AUTORIZADA, "
			+ 			"AR.ART_DIAS AS DIASSANCION, "
			+ 			"AR.ART_NUMERO || AR.ART_FRACCION || AR.ART_PARRAFO || AR.ART_INCISO AS FUNDAMENTACION, "
			+ 			"TO_CHAR(FM.FECHA_CREACION, 'DD/MM/YYYY') as FECHACREACION, "
			+ 			"(CASE "
            +				"WHEN FCTR.CODIGO = 'LR' "
            +    				"THEN 'LUZ ROJA' "
            +				"WHEN FCTR.CODIGO = 'MI' "
            +    				"THEN 'BOSCH' "
            +				"WHEN FCTR.CODIGO = 'MR' "
            +    				"THEN 'RADAR LASER' "
            +				"ELSE 'N/A' "  
            +			"END) AS RADARTIPO "
			+ "FROM 	FOTOMULTA_DETECCIONES FM "
			+ "		LEFT JOIN ARTICULOS AR ON FM.ARTICULO_ID = AR.ART_ID "
			+ "		LEFT JOIN FOTOMULTA_CAT_TIPO_RADAR FCTR ON FM.RADAR_TIPO_ID = FCTR.CAT_TIPO_RADAR_ID "
			+ "WHERE 	FM.FECHA_VALIDACION BETWEEN #{fechaInicio} AND #{fechaFin} "
			+ "AND 		FM.VALIDO = #{valido} "
			+ "AND      FM.ORIGEN_PLACA = 0 "
			+ "AND 		FM.AUTORIZADA =  CASE "
			+ 							"WHEN #{autorizado} = 1 OR #{autorizado} = 0 "
			+ 								"THEN #{autorizado} "
			+ 							"ELSE FM.AUTORIZADA "
			+ 						"END "
			+ "AND 		FM.PROCESADO =  CASE "
			+ 							"WHEN #{procesado} = 1 OR #{procesado} = 0 "
			+ 								"THEN #{procesado} "
			+ 							"ELSE FM.PROCESADO "
			+ 						"END "
			+ "AND     FM.RADAR_TIPO_ID =  CASE "
			+ "									WHEN #{radarTipo} > 0 "
			+ "										THEN #{radarTipo} "
			+ "									ELSE FM.RADAR_TIPO_ID "
			+ "								END "
			+"AND FM.ORIGEN_PLACA = CASE "
			+	"WHEN #{origenPlaca} = 1 OR #{origenPlaca} = 0 "
			+		"THEN #{origenPlaca} "
			+	"ELSE FM.ORIGEN_PLACA "
			+"END "
			+"AND ROWNUM <= 20000 "
			+ "ORDER BY FM.FOTOMULTA_ID DESC";
	
	String GENERA_EXCEL_BY_FECHA_INFRACCION = "SELECT LOTE_ID as loteId, "
			+ "			VALIDO as valido, "
			+ "			PROCESADO as procesado, "
			+ "			PLACA as placa, "
			+ "			FECHA as fecha, "
			+ "			HORA as hora, "
			+ "			TDSKUID as tdskuid, "
			+ "			UT as ut, "
			+ "			VELOCIDAD_DETECTADA as velocidadDetectada, "
			+ "			NOMBRE as nombre, "
			+ "			APELLIDO_PATERNO as apellidoPaterno, "
			+ "			APELLIDO_MATERNO as apellidoMaterno, "
			+ "			CALLE as calle, "
			+ "			NUM_EXTERIOR as numExterior, "
			+ "			NUM_INTERIOR as numInterior, "
			+ "			COLONIA as colonia, "
			+ "			MUNICIPIO as municipio, "
			+ "			CODIGO_POSTAL as codigoPostal, "
			+ "			ENTIDAD_FEDERATIVA as entidadFederativa, "
			+ "			MARCA as marca, "
			+ "			SUBMARCA as submarca, "
			+ "			MODELO as modelo, "
			+ "			TELEFONO as telefono, "
			+ "			SERIE as serie, "
			+ "			MOTOR as motor, "
			+ "			ARTICULO_ID as articuloId, "
			+ "			FECHA_CREACION as fechaCreacion, "
			+ "			CREADO_POR as creadoPor, "
			+ "			ULTIMA_MODIFICACION as ultimaModificacion, "
			+ "			MODIFICADO_POR as modificadoPor, "
			+ "			RADAR_TIPO_ID as tipoRadarId, "
			+ "			OFICIAL_PLACA as oficialPlaca, "
			+ "			OFICIAL_NOMBRE as oficialNombre, "
			+ "          ORIGEN_PLACA as origenPlaca "
			+ "FROM 	FOTOMULTA_DETECCIONES "
			+ "WHERE 	TO_DATE(REPLACE(FECHA,'.','/'), 'dd/MM/yy') BETWEEN #{fechaInicio} AND #{fechaFin} "
			+ "AND 		VALIDO = #{valido} "
			+ "AND 		AUTORIZADA =  CASE "
			+ 							"WHEN #{autorizado} = 1 OR #{autorizado} = 0 "
			+ 								"THEN #{autorizado} "
			+ 							"ELSE AUTORIZADA "
			+ "						  END "
			+ "AND 		PROCESADO =  CASE "
			+ "							WHEN #{procesado} = 1 OR #{procesado} = 0 "
			+ "								THEN #{procesado} "
			+ "							ELSE PROCESADO "
			+ "						 END "
			+ "AND     RADAR_TIPO_ID =  CASE "
			+ "									WHEN #{radarTipo} > 0 "
			+ "										THEN #{radarTipo} "
			+ "									ELSE RADAR_TIPO_ID "
			+ "								END "
			+ "AND     ORIGEN_PLACA =  CASE "
			+ "									WHEN #{origenPlaca} = 1 OR #{origenPlaca} = 0 "
			+ "										THEN #{origenPlaca} "
			+ "									ELSE ORIGEN_PLACA "
			+ "								END "
			+ "AND ROWNUM <= 20000 "
			+ "ORDER BY CODIGO_POSTAL, PLACA, FOTOMULTA_ID DESC ";
			//+ "ORDER BY FOTOMULTA_ID DESC";
	
	String GENERA_EXCEL_BY_FECHA_VALIDACION = "SELECT LOTE_ID as loteId, "
			+ "			VALIDO as valido, "
			+ "			PROCESADO as procesado, "
			+ "			PLACA as placa, "
			+ "			FECHA as fecha, "
			+ "			HORA as hora, "
			+ "			TDSKUID as tdskuid, "
			+ "			UT as ut, "
			+ "			VELOCIDAD_DETECTADA as velocidadDetectada, "
			+ "			NOMBRE as nombre, "
			+ "			APELLIDO_PATERNO as apellidoPaterno, "
			+ "			APELLIDO_MATERNO as apellidoMaterno, "
			+ "			CALLE as calle, "
			+ "			NUM_EXTERIOR as numExterior, "
			+ "			NUM_INTERIOR as numInterior, "
			+ "			COLONIA as colonia, "
			+ "			MUNICIPIO as municipio, "
			+ "			CODIGO_POSTAL as codigoPostal, "
			+ "			ENTIDAD_FEDERATIVA as entidadFederativa, "
			+ "			MARCA as marca, "
			+ "			SUBMARCA as submarca, "
			+ "			MODELO as modelo, "
			+ "			TELEFONO as telefono, "
			+ "			SERIE as serie, "
			+ "			MOTOR as motor, "
			+ "			ARTICULO_ID as articuloId, "
			+ "			FECHA_CREACION as fechaCreacion, "
			+ "			CREADO_POR as creadoPor, "
			+ "			ULTIMA_MODIFICACION as ultimaModificacion, "
			+ "			MODIFICADO_POR as modificadoPor, "
			+ "			RADAR_TIPO_ID as tipoRadarId, "
			+ "			OFICIAL_PLACA as oficialPlaca, "
			+ "			OFICIAL_NOMBRE as oficialNombre, "
			+ "         ORIGEN_PLACA as origenPlaca "
			+ "FROM 	FOTOMULTA_DETECCIONES "
			+ "WHERE 	FECHA_VALIDACION BETWEEN #{fechaInicio} AND #{fechaFin} "
			+ "AND 		VALIDO = #{valido} "
			+ "AND 		AUTORIZADA =  CASE "
			+ 							"WHEN #{autorizado} = 1 OR #{autorizado} = 0 "
			+ 								"THEN #{autorizado} "
			+ 							"ELSE AUTORIZADA "
			+ "						  END "
			+ "AND 		PROCESADO =  CASE "
			+ "							WHEN #{procesado} = 1 OR #{procesado} = 0 "
			+ "								THEN #{procesado} "
			+ "							ELSE PROCESADO "
			+ "						 END "
			+ "AND     RADAR_TIPO_ID =  CASE "
			+ "									WHEN #{radarTipo} > 0 "
			+ "										THEN #{radarTipo} "
			+ "									ELSE RADAR_TIPO_ID "
			+ "								END "
			+ "AND     ORIGEN_PLACA =  CASE "
			+ "									WHEN #{origenPlaca} = 1 OR #{origenPlaca} = 0 "
			+ "										THEN #{origenPlaca} "
			+ "									ELSE ORIGEN_PLACA "
			+ "								END "
			+ "AND ROWNUM <= 20000 "
			+ "ORDER BY CODIGO_POSTAL, PLACA, FOTOMULTA_ID DESC";
			//+ "ORDER BY FOTOMULTA_ID DESC";
	
	String DETECCIONES_PREVALIDADAS = "SELECT FCTR.CODIGO AS CODIGO, " +
			  "COUNT(PRE.PREVALIDACION_ID) AS NUMERODETECIONES, " +
		  	  "(SELECT COUNT(*) " +
		  	  "FROM  ICD.FOTOMULTA_PREVALIDACIONES PRE2 " +
		  	  "WHERE PRE2.ACEPTADA = 1 " +
		  	  "AND PRE2.DUPLICADA = 0 " +
		  	  "AND PRE2.VALIDADA_SSP = 0 " +
		  	  "AND PRE2.ORIGEN_PLACA = CASE "
		  	  +"WHEN #{origenPlaca} = 1 OR #{origenPlaca} = 0 "
		  	  +"THEN #{origenPlaca} "
		  	  +"ELSE PRE2.ORIGEN_PLACA "
		  	  +	"END "+
		  	  "AND PRE2.CANCELADA = 0) AS TOTALDETECCIONES  " +
		  	  "FROM FOTOMULTA_PREVALIDACIONES PRE, " +
		  	  "FOTOMULTA_CAT_TIPO_RADAR FCTR " +
		  	  "WHERE FCTR.CAT_TIPO_RADAR_ID = PRE.RADAR_TIPO_ID " +
		  	  "AND PRE.ACEPTADA = 1 " +
		  	  "AND PRE.DUPLICADA = 0 " +
		  	  "AND PRE.VALIDADA_SSP = 0 " +
		  	  "AND PRE.CANCELADA = 0 " +
		  	  "AND PRE.ORIGEN_PLACA = CASE "
		  	  +"WHEN #{origenPlaca} = 1 OR #{origenPlaca} = 0 "
		  	  +"THEN #{origenPlaca} "
		  	  +"ELSE PRE.ORIGEN_PLACA "
		  	  +"END "+
		  	  "GROUP BY FCTR.CODIGO"; 

	String DETECCIONES_SIN_PROCESAR_HISTORICO_FECHA_INFRACCION = "SELECT FCTR.CODIGO AS CODIGO, " +
			"COUNT(FD.FOTOMULTA_ID) AS NUMERODETECIONES, " +
			"(SELECT COUNT(*) " +
			"FROM ICD.FOTOMULTA_DETECCIONES FD2 " +
			"WHERE FD2.VALIDO = 1 " +
			"AND FD2.PROCESADO = 0 " +
			"AND (to_date(REPLACE(FD2.FECHA,'.','/'),'dd/MM/yy')) < (TO_DATE(#{fechaInicio},'DD/MM/RR')) " +
			"AND FD2.ORIGEN_PLACA = CASE " +
			"WHEN #{origenPlaca} = 1 OR #{origenPlaca} = 0 " +
			"THEN #{origenPlaca} " +
			"ELSE FD2.ORIGEN_PLACA " +
			"END "+
			"AND FD2.AUTORIZADA = 1) AS TOTALDETECCIONES  " +
			"FROM ICD.FOTOMULTA_DETECCIONES FD, "+
			"ICD.FOTOMULTA_CAT_TIPO_RADAR FCTR " +
			"WHERE FCTR.CAT_TIPO_RADAR_ID = FD.RADAR_TIPO_ID " +
			"AND FD.VALIDO = 1 " +
			"AND FD.PROCESADO = 0 " +
			"AND FD.AUTORIZADA = 1 " +
			"AND (to_date(REPLACE(FD.FECHA,'.','/'),'dd/MM/yy')) < (TO_DATE(#{fechaInicio},'DD/MM/RR')) " +
			"AND FD.ORIGEN_PLACA = CASE " +
			"WHEN #{origenPlaca} = 1 OR #{origenPlaca} = 0" +
			"THEN #{origenPlaca} " +
			"ELSE FD.ORIGEN_PLACA " +
			"END "+
			"GROUP BY FCTR.CODIGO";
	
	String DETECCIONES_SIN_PROCESAR_ACTUAL_FECHA_INFRACCION = "SELECT FCTR.CODIGO AS CODIGO, " +
			"COUNT(FD.FOTOMULTA_ID) AS NUMERODETECIONES, " +
			"(SELECT COUNT(*) " +
			"FROM ICD.FOTOMULTA_DETECCIONES FD2 " +
			"WHERE FD2.VALIDO = 1 " +
			"AND FD2.PROCESADO = 0 " +
			"AND TRUNC (to_date(REPLACE(FD2.FECHA,'.','/'),'dd/MM/yy')) >= (TO_DATE(#{fechaInicio},'DD/MM/RR')) " +
			"AND FD2.ORIGEN_PLACA = CASE " +
			"WHEN #{origenPlaca} = 1 OR #{origenPlaca} = 0 " +
			"THEN #{origenPlaca} " +
			"ELSE FD2.ORIGEN_PLACA " +
			"END "+
			"AND FD2.AUTORIZADA = 1) AS TOTALDETECCIONES  " +
			"FROM ICD.FOTOMULTA_DETECCIONES FD, "+
			"ICD.FOTOMULTA_CAT_TIPO_RADAR FCTR " +
			"WHERE FCTR.CAT_TIPO_RADAR_ID = FD.RADAR_TIPO_ID " +
			"AND FD.VALIDO = 1 " +
			"AND FD.PROCESADO = 0 " +
			"AND FD.AUTORIZADA = 1 " +
			"AND TRUNC (to_date(REPLACE(FD.FECHA,'.','/'),'dd/MM/yy')) >= (TO_DATE(#{fechaInicio},'DD/MM/RR')) " +
			"AND FD.ORIGEN_PLACA = CASE " +
			"WHEN #{origenPlaca} = 1 OR #{origenPlaca} = 0" +
			"THEN #{origenPlaca} " +
			"ELSE FD.ORIGEN_PLACA " +
			"END "+
			"GROUP BY FCTR.CODIGO";

	String DETECCIONES_SIN_PROCESAR_ACTUAL_FECHA_VALIDACION = "SELECT	FCTR.CODIGO AS CODIGO, " +
			"		COUNT(FD.FOTOMULTA_ID) AS NUMERODETECIONES, " +
			"		(SELECT COUNT(*) " +
			"		FROM   ICD.FOTOMULTA_DETECCIONES FD2 " +
			"		WHERE  FD2.VALIDO = 1 " +
			"		AND    FD2.PROCESADO = 0" +
			" AND TRUNC (FD2.FECHA_VALIDACION) >= (TO_DATE (#{fechaInicio},'DD/MM/RR'))"+
			" AND FD2.ORIGEN_PLACA = CASE "
			+						" WHEN #{origenPlaca} = 1 OR #{origenPlaca} = 0"
			+							" THEN #{origenPlaca} "
			+							"ELSE FD2.ORIGEN_PLACA "
			+							"END "+
			"		AND    FD2.AUTORIZADA = 1) AS TOTALDETECCIONES  " +
			"FROM   ICD.FOTOMULTA_DETECCIONES FD, "+
			"		ICD.FOTOMULTA_CAT_TIPO_RADAR FCTR " +
			"WHERE  FCTR.CAT_TIPO_RADAR_ID = FD.RADAR_TIPO_ID " +
			"AND    FD.VALIDO = 1 " +
			"AND    FD.PROCESADO = 0 " +
			"AND 	FD.AUTORIZADA = 1 " +
			" AND TRUNC (FD.FECHA_VALIDACION) >= (TO_DATE (#{fechaInicio},'DD/MM/RR'))"+
			" AND FD.ORIGEN_PLACA = CASE "
			+						" WHEN #{origenPlaca} = 1 OR #{origenPlaca} = 0"
			+							" THEN #{origenPlaca} "
			+							"ELSE FD.ORIGEN_PLACA "
			+							"END "+
			"GROUP BY 	FCTR.CODIGO";
	
	String DETECCIONES_SIN_PROCESAR_HISTORICO_FECHA_VALIDACION = "SELECT	FCTR.CODIGO AS CODIGO, " +
			"		COUNT(FD.FOTOMULTA_ID) AS NUMERODETECIONES, " +
			"		(SELECT COUNT(*) " +
			"		FROM   ICD.FOTOMULTA_DETECCIONES FD2 " +
			"		WHERE  FD2.VALIDO = 1 " +
			"		AND    FD2.PROCESADO = 0" +
			"  AND  TRUNC (FD2.FECHA_VALIDACION) < (TO_DATE(#{fechaInicio},'DD/MM/RR')) " +
			" AND FD2.ORIGEN_PLACA = CASE "
			+						" WHEN #{origenPlaca} = 1 OR #{origenPlaca} = 0"
			+							" THEN #{origenPlaca} "
			+							"ELSE FD2.ORIGEN_PLACA "
			+							"END "+
			"		AND    FD2.AUTORIZADA = 1) AS TOTALDETECCIONES  " +
			"FROM   ICD.FOTOMULTA_DETECCIONES FD, "+
			"		ICD.FOTOMULTA_CAT_TIPO_RADAR FCTR " +
			"WHERE  FCTR.CAT_TIPO_RADAR_ID = FD.RADAR_TIPO_ID " +
			"AND    FD.VALIDO = 1 " +
			"AND    FD.PROCESADO = 0 " +
			"AND 	FD.AUTORIZADA = 1 " +
			"  AND  TRUNC (FD.FECHA_VALIDACION) < (TO_DATE(#{fechaInicio},'DD/MM/RR')) " +
			" AND FD.ORIGEN_PLACA = CASE "
			+						" WHEN #{origenPlaca} = 1 OR #{origenPlaca} = 0"
			+							" THEN #{origenPlaca} "
			+							"ELSE FD.ORIGEN_PLACA "
			+							"END "+
			"GROUP BY 	FCTR.CODIGO";
	
	
	
	String DETECCIONES_BY_FECHA_INFRACCION_ACTUAL = "SELECT " +
					"TO_CHAR(TRUNC(TO_DATE(REPLACE(FD.FECHA,'.','/'),'DD/MM/YY'), 'MM'), 'DD/MM/YYYY') AS FECHACREACION, " +
			        "EXTRACT(MONTH FROM TO_DATE(REPLACE(FD.FECHA,'.','/'),'DD/MM/YY')) AS MES, " +
					"EXTRACT(YEAR FROM TO_DATE(REPLACE(FD.FECHA,'.','/'),'DD/MM/YY')) AS ANIO, " +
					"COUNT(FDLR.FOTOMULTA_ID) as LUZROJA, " +
		            "COUNT(FDMI.FOTOMULTA_ID) as BOSCH, " +
		            "COUNT(FDMR.FOTOMULTA_ID) as LASER " +
					"FROM ICD.FOTOMULTA_DETECCIONES FD " +
					"LEFT JOIN FOTOMULTA_DETECCIONES FDLR " +
		            "ON FD.FOTOMULTA_ID = FDLR.FOTOMULTA_ID " +
		            "AND FD.RADAR_TIPO_ID = 1 " +
		            "LEFT JOIN FOTOMULTA_DETECCIONES FDMI " +
		            "ON FD.FOTOMULTA_ID = FDMI.FOTOMULTA_ID " +
		            "AND FD.RADAR_TIPO_ID = 2 " +
		            "LEFT JOIN FOTOMULTA_DETECCIONES FDMR " +
		            "ON FD.FOTOMULTA_ID = FDMR.FOTOMULTA_ID " +
		            "AND FD.RADAR_TIPO_ID = 3, " +
					"ICD.FOTOMULTA_CAT_TIPO_RADAR FCTR " +
		        	"WHERE  FCTR.CAT_TIPO_RADAR_ID = FD.RADAR_TIPO_ID " +
					"AND FD.VALIDO = 1 " +
					"AND FD.PROCESADO = 0 " +
					"AND FD.AUTORIZADA = 1 " +
					"AND TRUNC (to_date(REPLACE(FD.FECHA,'.','/'),'dd/MM/yy')) BETWEEN #{fechaInicio} AND #{fechaHoy} "+
					"AND FCTR.CODIGO =   (CASE " +
					"WHEN #{codigoRadar} = 'LR' " +
					"THEN  #{codigoRadar} " +
					"WHEN #{codigoRadar} = 'MI' " +
					"THEN  #{codigoRadar} " +
					"WHEN #{codigoRadar} = 'MR' " +
					"THEN  #{codigoRadar} " +
					"ELSE FCTR.CODIGO " +
					"END) " +
					"AND FD.ORIGEN_PLACA = CASE "+
					"WHEN #{origenPlaca} = 1 OR #{origenPlaca} = 0 " +
					"THEN #{origenPlaca} " +
					"ELSE FD.ORIGEN_PLACA " +
					"END "+
					"GROUP BY TO_CHAR(TRUNC(TO_DATE(REPLACE(FD.FECHA,'.','/'),'DD/MM/YY'), 'MM'), 'DD/MM/YYYY'), EXTRACT(MONTH FROM TO_DATE(REPLACE(FD.FECHA,'.','/'),'DD/MM/YY')), EXTRACT(YEAR FROM TO_DATE(REPLACE(FD.FECHA,'.','/'),'DD/MM/YY')) " +
					"ORDER BY ANIO,MES,FECHACREACION DESC";
	
	String DETECCIONES_BY_FECHA_INFRACCION_HISTORICO = "SELECT " +
			"TO_CHAR(TRUNC(TO_DATE(REPLACE(FD.FECHA,'.','/'),'DD/MM/YY'), 'MM'), 'DD/MM/YYYY') AS FECHACREACION, " +
	        "EXTRACT(MONTH FROM TO_DATE(REPLACE(FD.FECHA,'.','/'),'DD/MM/YY')) AS MES, " +
			"EXTRACT(YEAR FROM TO_DATE(REPLACE(FD.FECHA,'.','/'),'DD/MM/YY')) AS ANIO, " +
	        "COUNT(FDLR.FOTOMULTA_ID) as LUZROJA, " +
            "COUNT(FDMI.FOTOMULTA_ID) as BOSCH, " +
            "COUNT(FDMR.FOTOMULTA_ID) as LASER " +
			"FROM   ICD.FOTOMULTA_DETECCIONES FD " +
            "LEFT JOIN FOTOMULTA_DETECCIONES FDLR " +
            "ON FD.FOTOMULTA_ID = FDLR.FOTOMULTA_ID " +
            "AND FD.RADAR_TIPO_ID = 1 " +
            "LEFT JOIN FOTOMULTA_DETECCIONES FDMI " +
            "ON FD.FOTOMULTA_ID = FDMI.FOTOMULTA_ID " +
            "AND FD.RADAR_TIPO_ID = 2 " +
            "LEFT JOIN FOTOMULTA_DETECCIONES FDMR " +
            "ON FD.FOTOMULTA_ID = FDMR.FOTOMULTA_ID " +
            "AND FD.RADAR_TIPO_ID = 3, " +
			"ICD.FOTOMULTA_CAT_TIPO_RADAR FCTR " +
        	"WHERE  FCTR.CAT_TIPO_RADAR_ID = FD.RADAR_TIPO_ID " +
			"AND    FD.VALIDO = 1 " +
			"AND    FD.PROCESADO = 0 " +
			"AND 	FD.AUTORIZADA = 1 " +
			"AND (to_date(REPLACE(FD.FECHA,'.','/'),'dd/MM/yy')) < (TO_DATE(#{fechaInicio},'DD/MM/RR')) " +
			"AND    FCTR.CODIGO =   (CASE " +
			"WHEN #{codigoRadar} = 'LR' " +
			"	THEN  #{codigoRadar} " +
			"WHEN #{codigoRadar} = 'MI' " +
			"	THEN  #{codigoRadar} " +
			"WHEN #{codigoRadar} = 'MR' " +
			"	THEN  #{codigoRadar} " +
			"ELSE FCTR.CODIGO " +
			"END) " +
			"AND FD.ORIGEN_PLACA = CASE "
			+ " WHEN #{origenPlaca} = 1 OR #{origenPlaca} = 0 "
			+							" THEN #{origenPlaca} "
			+							"ELSE FD.ORIGEN_PLACA "
			+							"END "+
			"GROUP BY TO_CHAR(TRUNC(TO_DATE(REPLACE(FD.FECHA,'.','/'),'DD/MM/YY'), 'MM'), 'DD/MM/YYYY'), EXTRACT(MONTH FROM TO_DATE(REPLACE(FD.FECHA,'.','/'),'DD/MM/YY')), EXTRACT(YEAR FROM TO_DATE(REPLACE(FD.FECHA,'.','/'),'DD/MM/YY')) " +
			"ORDER BY ANIO,MES,FECHACREACION DESC";

	String DETECCIONES_BY_FECHA_VALIDACION_ACTUAL = "SELECT " +
					"TO_CHAR(TRUNC(FD.FECHA_VALIDACION, 'MM'),'DD/MM/YYYY') AS FECHACREACION, " +
					"EXTRACT(MONTH FROM FD.FECHA_VALIDACION) AS MES, " +
		            "EXTRACT(YEAR FROM FD.FECHA_VALIDACION) AS ANIO, " +
		            "COUNT(FDLR.FOTOMULTA_ID) as LUZROJA, " +
		            "COUNT(FDMI.FOTOMULTA_ID) as BOSCH, " +
		            "COUNT(FDMR.FOTOMULTA_ID) as LASER " +
					"FROM ICD.FOTOMULTA_DETECCIONES FD " +
					"LEFT JOIN FOTOMULTA_DETECCIONES FDLR " +
		            "ON FD.FOTOMULTA_ID = FDLR.FOTOMULTA_ID " +
		            "AND FD.RADAR_TIPO_ID = 1 " +
		            "LEFT JOIN FOTOMULTA_DETECCIONES FDMI " +
		            "ON FD.FOTOMULTA_ID = FDMI.FOTOMULTA_ID " +
		            "AND FD.RADAR_TIPO_ID = 2 " +
		            "LEFT JOIN FOTOMULTA_DETECCIONES FDMR " +
		            "ON FD.FOTOMULTA_ID = FDMR.FOTOMULTA_ID " +
		            "AND FD.RADAR_TIPO_ID = 3, " +
					"ICD.FOTOMULTA_CAT_TIPO_RADAR FCTR " +
		        	"WHERE FCTR.CAT_TIPO_RADAR_ID = FD.RADAR_TIPO_ID " +
					"AND FD.VALIDO = 1 " +
					"AND FD.PROCESADO = 0 " +
					"AND FD.AUTORIZADA = 1 " +
					"AND  TRUNC (FD.FECHA_VALIDACION) BETWEEN #{fechaInicio} AND #{fechaHoy} " +
					"AND FCTR.CODIGO =   (CASE " +
					"WHEN #{codigoRadar} = 'LR' " +
					"THEN  #{codigoRadar} " +
					"WHEN #{codigoRadar} = 'MI' " +
					"THEN  #{codigoRadar} " +
					"WHEN #{codigoRadar} = 'MR' " +
					"THEN  #{codigoRadar} " +
					"ELSE FCTR.CODIGO " +
					"END) " +
					"AND FD.ORIGEN_PLACA = CASE " +
					"WHEN #{origenPlaca} = 1 OR #{origenPlaca} = 0 "+ 
					"THEN #{origenPlaca} " +
					"ELSE FD.ORIGEN_PLACA " +
					"END "+
					"GROUP BY TRUNC(FD.FECHA_VALIDACION, 'MM'), EXTRACT(MONTH FROM FD.FECHA_VALIDACION), EXTRACT(YEAR FROM FD.FECHA_VALIDACION) " +
					"ORDER BY ANIO,MES,FECHACREACION DESC";

	String DETECCIONES_BY_FECHA_VALIDACION_HISTORICA = "SELECT " +
			"TO_CHAR(TRUNC(FD.FECHA_VALIDACION, 'MM'),'DD/MM/YYYY') AS FECHACREACION, " +
			"EXTRACT(MONTH FROM FD.FECHA_VALIDACION) AS MES, " +
            "EXTRACT(YEAR FROM FD.FECHA_VALIDACION) AS ANIO, " +
            "COUNT(FDLR.FOTOMULTA_ID) as LUZROJA, " +
            "COUNT(FDMI.FOTOMULTA_ID) as BOSCH, " +
            "COUNT(FDMR.FOTOMULTA_ID) as LASER " +
			"FROM	ICD.FOTOMULTA_DETECCIONES FD " +
			"LEFT JOIN FOTOMULTA_DETECCIONES FDLR " +
            "ON FD.FOTOMULTA_ID = FDLR.FOTOMULTA_ID " +
            "AND FD.RADAR_TIPO_ID = 1 " +
            "LEFT JOIN FOTOMULTA_DETECCIONES FDMI " +
            "ON FD.FOTOMULTA_ID = FDMI.FOTOMULTA_ID " +
            "AND FD.RADAR_TIPO_ID = 2 " +
            "LEFT JOIN FOTOMULTA_DETECCIONES FDMR " +
            "ON FD.FOTOMULTA_ID = FDMR.FOTOMULTA_ID " +
            "AND FD.RADAR_TIPO_ID = 3, " +
			"ICD.FOTOMULTA_CAT_TIPO_RADAR FCTR " +
        	"WHERE  FCTR.CAT_TIPO_RADAR_ID = FD.RADAR_TIPO_ID " +
			"AND    FD.VALIDO = 1 " +
			"AND    FD.PROCESADO = 0 " +
			"AND 	FD.AUTORIZADA = 1 " +
			"  AND  TRUNC (FD.FECHA_VALIDACION) < (TO_DATE(#{fechaInicio},'DD/MM/RR'))"+
			"AND    FCTR.CODIGO =   (CASE " +
			"                    		WHEN #{codigoRadar} = 'LR' " +
			"                             THEN  #{codigoRadar} " +
			"                           WHEN #{codigoRadar} = 'MI' " +
			"                             THEN  #{codigoRadar} " +
			"                           WHEN #{codigoRadar} = 'MR' " +
			"                             THEN  #{codigoRadar} " +
			"                           ELSE FCTR.CODIGO " +
			"                       END) " +
			"AND FD.ORIGEN_PLACA = CASE "
			+						" WHEN #{origenPlaca} = 1 OR #{origenPlaca} = 0"
			+							" THEN #{origenPlaca} "
			+							"ELSE FD.ORIGEN_PLACA "
			+							"END "+
			"GROUP BY TRUNC(FD.FECHA_VALIDACION, 'MM'), EXTRACT(MONTH FROM FD.FECHA_VALIDACION), EXTRACT(YEAR FROM FD.FECHA_VALIDACION) " +
			"ORDER BY ANIO,MES,FECHACREACION DESC";
	
	String DETECCIONES_BY_FECHA_INFRACCION_MES = "SELECT " +
			"TO_CHAR(TO_DATE(REPLACE(FD.FECHA,'.','/'),'dd/MM/yy'), 'dd/MM/yyyy') AS FECHACREACION, " +  
	        "COUNT(FDLR.FOTOMULTA_ID) as LUZROJA, " +
            "COUNT(FDMI.FOTOMULTA_ID) as BOSCH, " +
            "COUNT(FDMR.FOTOMULTA_ID) as LASER " +
			"FROM   ICD.FOTOMULTA_DETECCIONES FD " +
            "LEFT JOIN FOTOMULTA_DETECCIONES FDLR " +
            "ON FD.FOTOMULTA_ID = FDLR.FOTOMULTA_ID " +
            "AND FD.RADAR_TIPO_ID = 1 " +
            "LEFT JOIN FOTOMULTA_DETECCIONES FDMI " + 
            "ON FD.FOTOMULTA_ID = FDMI.FOTOMULTA_ID " +
            "AND FD.RADAR_TIPO_ID = 2 " +
            "LEFT JOIN FOTOMULTA_DETECCIONES FDMR "+ 
            "ON FD.FOTOMULTA_ID = FDMR.FOTOMULTA_ID "+
            "AND FD.RADAR_TIPO_ID = 3, " +
			"ICD.FOTOMULTA_CAT_TIPO_RADAR FCTR " + 
        	"WHERE  FCTR.CAT_TIPO_RADAR_ID = FD.RADAR_TIPO_ID " + 
			"AND    FD.VALIDO = 1 " +
			"AND    FD.PROCESADO = 0 " +
			"AND 	FD.AUTORIZADA = 1 " +
			"AND (TO_DATE(REPLACE(FD.FECHA,'.','/'),'dd/MM/yy')) between TO_DATE(#{fechaInicio},'dd/MM/yyyy') AND " + 
			"Last_Day(TO_DATE(#{fechaInicio},'dd/MM/yyyy')) " +
			"AND    FCTR.CODIGO =   (CASE " +
			"                    		WHEN #{codigoRadar} = 'LR' " +
			"                             THEN  #{codigoRadar} " +
			"                           WHEN #{codigoRadar} = 'MI' " +
			"                             THEN  #{codigoRadar} " +
			"                           WHEN #{codigoRadar} = 'MR' " +
			"                             THEN  #{codigoRadar} " +
			"                           ELSE FCTR.CODIGO " +
			"                       END) " +
			"AND FD.ORIGEN_PLACA = CASE " +
			"WHEN #{origenPlaca} = 1 OR #{origenPlaca} = 0 " + 
			"	 THEN #{origenPlaca} " + 
			"ELSE FD.ORIGEN_PLACA " + 
			"END " + 
			"GROUP BY TO_CHAR(TO_DATE(REPLACE(FD.FECHA,'.','/'),'dd/MM/yy'), 'dd/MM/yyyy') " +
			"ORDER BY FECHACREACION DESC";
	
	String DETECCIONES_BY_FECHA_VALIDACION_MES = "SELECT " +
			"TO_CHAR(FD.FECHA_VALIDACION, 'dd/MM/yyyy') AS FECHACREACION, " +  
	        "COUNT(FDLR.FOTOMULTA_ID) as LUZROJA, " +
            "COUNT(FDMI.FOTOMULTA_ID) as BOSCH, " +
            "COUNT(FDMR.FOTOMULTA_ID) as LASER " +
			"FROM   ICD.FOTOMULTA_DETECCIONES FD " +
            "LEFT JOIN FOTOMULTA_DETECCIONES FDLR " +
            "ON FD.FOTOMULTA_ID = FDLR.FOTOMULTA_ID " +
            "AND FD.RADAR_TIPO_ID = 1 " +
            "LEFT JOIN FOTOMULTA_DETECCIONES FDMI " + 
            "ON FD.FOTOMULTA_ID = FDMI.FOTOMULTA_ID " +
            "AND FD.RADAR_TIPO_ID = 2 " +
            "LEFT JOIN FOTOMULTA_DETECCIONES FDMR "+ 
            "ON FD.FOTOMULTA_ID = FDMR.FOTOMULTA_ID "+
            "AND FD.RADAR_TIPO_ID = 3, " +
			"ICD.FOTOMULTA_CAT_TIPO_RADAR FCTR " + 
        	"WHERE  FCTR.CAT_TIPO_RADAR_ID = FD.RADAR_TIPO_ID " + 
			"AND    FD.VALIDO = 1 " +
			"AND    FD.PROCESADO = 0 " +
			"AND 	FD.AUTORIZADA = 1 " +
			"AND TO_DATE(FD.FECHA_VALIDACION,'dd/MM/yy') between TO_DATE(#{fechaInicio},'dd/MM/yyyy') AND " + 
			"Last_Day(TO_DATE(#{fechaInicio},'dd/MM/yyyy')) " +
			"AND    FCTR.CODIGO =   (CASE " +
			"                    		WHEN #{codigoRadar} = 'LR' " +
			"                             THEN  #{codigoRadar} " +
			"                           WHEN #{codigoRadar} = 'MI' " +
			"                             THEN  #{codigoRadar} " +
			"                           WHEN #{codigoRadar} = 'MR' " +
			"                             THEN  #{codigoRadar} " +
			"                           ELSE FCTR.CODIGO " +
			"                       END) " +
			"AND FD.ORIGEN_PLACA = CASE " +
			"WHEN #{origenPlaca} = 1 OR #{origenPlaca} = 0 " + 
			"	 THEN #{origenPlaca} " + 
			"ELSE FD.ORIGEN_PLACA " + 
			"END " + 
			"GROUP BY TO_CHAR(FD.FECHA_VALIDACION, 'dd/MM/yyyy') " +
			"ORDER BY FECHACREACION DESC";
	
	
	String ACTUALIZA_FOTOMULTA_DETECCIONES_POR_LOTE = "UPDATE FOTOMULTA_DETECCIONES SET PROCESADO = #{procesado}, LOTE_ID = #{idRadarArchivo} "
			+ "WHERE 	TDSKUID = #{tdskuid} "
			+ "AND 		VALIDO = #{valido} "
			+ "AND 		PROCESADO = #{noProcesado} "
			+ "AND 		RADAR_TIPO_ID = #{tipoRadar} "
			+ "AND 		AUTORIZADA = #{autorizado}";
	
	String REACTIVA_DETECCIONES_CANCELA_FM =
		"UPDATE  TAI005D_FM_DETECCIONES "+
		"SET     ST_PROCESADO = 0, ID_LOTE = 0 "+
		"WHERE   ID_LOTE = #{loteId}";
	
	String RESETEA_DETECCIONES_DESCARTADAS_TAI005 = 
		"UPDATE TAI005D_FM_DETECCIONES "
		+ "	SET ST_CANCELADO = 0 "
		+ "WHERE ST_CANCELADO >= 0 "
		+ "	AND ST_CANCELADO IN ( "
		+ "		SELECT ID_MOTIVO "
		+ "		FROM TAI015C_MOT_DESCARTE "
		+ "		WHERE ST_RESETEABLE = 1 "
		+ "	) "
		+ "	AND ID_LOTE = #{loteId}";
	
	String RESETEA_DETECCIONES_DESCARTADAS_TAI027 =
		"UPDATE TAI027D_FC_DETECCIONES "
		+ "	SET ST_CANCELADO = 0 "
		+ "WHERE "
		+ "	ID_FC_DETECCION IN ( "
		+ "		SELECT "
		+ "			ID_FC_DETECCION "
		+ "		FROM TAI005D_FM_DETECCIONES "
		+ "		WHERE "
		+ "			ID_LOTE = 930 "
		+ "			AND ST_CANCELADO >= 0 "
		+ "			AND ST_CANCELADO IN ( "
		+ "				SELECT ID_MOTIVO "
		+ "				FROM TAI015C_MOT_DESCARTE "
		+ "				WHERE ST_RESETEABLE = 1 "
		+ "			) "
		+ "	)";
	
	String REACTIVA_FC_DETECCIONES_CANCELA =
		"UPDATE  TAI027D_FC_DETECCIONES "
		+ "SET "
		+ "	ST_PROCESADO = 0 "
		+ "WHERE "
		+ " ID_FC_DETECCION IN ( "
		+ "		SELECT "
		+ "			ID_FC_DETECCION "
		+ "		FROM TAI005D_FM_DETECCIONES "
		+ "		WHERE "
		+ "			ID_LOTE = #{loteId} "
		+ "	)";
	
	String ACTUALIZA_CANT_PROCESADOS = 
		"UPDATE TAI028D_FC_ARCHIVO TAI028 "
		+ "SET "
		+ "	CANTIDAD_PROCESADOS = ( "
		+ "		SELECT "
		+ "			COUNT(*) "
		+ "		FROM TAI027D_FC_DETECCIONES TAI027 "
		+ "		WHERE "
		+ "			TAI027.ST_PROCESADO = 1 "
		+ "			AND TAI027.ID_ARCHIVO = TAI028.ID_ARCHIVO " 
		+ "	) "
		+ "WHERE "
		+ "	TAI028.ID_ARCHIVO IN ( "
		+ "	SELECT "
		+ "		DISTINCT ID_ARCHIVO "
		+ "	FROM TAI027D_FC_DETECCIONES "
		+ "	WHERE "
		+ "		ID_FC_DETECCION IN ( "
		+ "			SELECT "
		+ "				ID_FC_DETECCION "
		+ "			FROM RADAR_ARCHIVO_DETALLE "
		+ "			WHERE "
		+ "				RADAR_ARCHIVO_ID = #{loteId} "
		+ "		) "
		+ "	)";
	
	String ACTUALIZA_ST_COMP = 
		"UPDATE TAI028D_FC_ARCHIVO TAI028 "
		+ "SET TAI028.ST_COMPLEMENTADO = ( "
		+ "		SELECT "
		+ "			DECODE(COUNT(*), 0, 1, 0) "
		+ "		FROM TAI027D_FC_DETECCIONES TAI027 "
		+ "		WHERE "
		+ "			TAI027.ST_PROCESADO = 0 "
		+ "			AND TAI027.ID_ARCHIVO = TAI028.ID_ARCHIVO " 
		+ "	) "
		+ "WHERE "
		+ "	TAI028.ID_ARCHIVO IN ( "
		+ "		SELECT "
		+ "			DISTINCT ID_ARCHIVO "
		+ "		FROM TAI027D_FC_DETECCIONES "
		+ "		WHERE "
		+ "			ID_FC_DETECCION IN ( "
		+ "				SELECT "
		+ "					ID_FC_DETECCION "
		+ "				FROM RADAR_ARCHIVO_DETALLE "
		+ "				WHERE "
		+ "					RADAR_ARCHIVO_ID = #{loteId} "
		+ "			) "
		+ "	)";
	/***
	 * @author Jesus Gutierrez
	 * @param fechaInicio
	 * @param fechaFin
	 * @param autorizado
	 * @param procesado
	 * @param radarTipo
	 * @param valido
	 * @return
	 */
	@Select(FOTOMULTAS_BY_FECHA_INFRACCION)
	List<ConsultaDeteccionesVO> buscaFotomultasByFechaInfraccion(@Param("fechaInicio") Date fechaInicio, @Param("fechaFin") Date fechaFin, 
			@Param("autorizado") Long autorizado, @Param("procesado") Integer procesado, @Param("radarTipo") Integer radarTipo,  
			@Param("valido") Integer valido, @Param("origenPlaca") Integer origenPlaca);

	/***
	 * @author Jesus Gutierrez
	 * @param fechaInicio
	 * @param fechaFin
	 * @param autorizado
	 * @param procesado
	 * @param radarTipo
	 * @param valido
	 * @return
	 */
	@Select(FOTOMULTAS_BY_FECHA_VALIDACION)
	List<ConsultaDeteccionesVO> buscaFotomultasByFechaValidacion(@Param("fechaInicio") Date fechaInicio, @Param("fechaFin") Date fechaFin, 
			@Param("autorizado") Long autorizado, @Param("procesado") Integer procesado, @Param("radarTipo") Integer radarTipo,
			@Param("valido") Integer valido, @Param("origenPlaca") Integer origenPlaca);

	/***
	 * @author Jesus Gutierrez
	 * @param fechaInicio
	 * @param fechaFin
	 * @param valido
	 * @param noProcesado
	 * @param tipoRadar
	 * @param autorizado
	 * @return
	 */
	@Select(GENERA_EXCEL_BY_FECHA_INFRACCION)
	List<ReporteDeteccionesVO> buscaFotomultasDetecionesRangoFechasByFechaInfraccion(@Param("fechaInicio") Date fechaInicio, @Param("fechaFin") Date fechaFin, @Param("valido") Integer valido, 
			@Param("procesado") Integer noprocesado, @Param("radarTipo") Integer tipoRadar, @Param("autorizado") Long autorizado, @Param("origenPlaca") Integer origenPlaca);
	
	/***
	 * @author Jesus Gutierrez
	 * @param fechaInicio
	 * @param fechaFin
	 * @param valido
	 * @param noProcesado
	 * @param tipoRadar
	 * @param autorizado
	 * @return
	 */
	@Select(GENERA_EXCEL_BY_FECHA_VALIDACION)
	List<ReporteDeteccionesVO> buscaFotomultasDetecionesRangoFechasByFechaValidacion(@Param("fechaInicio") Date fechaInicio, @Param("fechaFin") Date fechaFin, @Param("valido") Integer valido, 
			@Param("procesado") Integer noProcesado, @Param("radarTipo") Integer tipoRadar, @Param("autorizado") Long autorizado, @Param("origenPlaca") Integer origenPlaca);
	
	@Update(REACTIVA_DETECCIONES_CANCELA)
	public Integer updateDeteccionesLoteCancelado(@Param("loteId")Long loteId);
	
	/************************************************************/
	@Select(value = DETECCIONES_PREVALIDADAS)
	List<DeteccionesSinProcesarVO> buscarDeteccionesPrevalidadas(@Param("origenPlaca") Integer origenPlaca);
	
	@Select(value = DETECCIONES_SIN_PROCESAR_ACTUAL_FECHA_INFRACCION)
	List<DeteccionesSinProcesarVO> buscarDeteccionesSinProcesarActual(@Param("fechaInicio") String fechaInicio, @Param("origenPlaca") Integer origenPlaca);
	
	@Select(value = DETECCIONES_SIN_PROCESAR_HISTORICO_FECHA_INFRACCION)
	List<DeteccionesSinProcesarVO> buscarDeteccionesSinProcesarHistorico(@Param("fechaInicio") String fechaInicio, @Param("origenPlaca") Integer origenPlaca);

	@Select(value = DETECCIONES_SIN_PROCESAR_ACTUAL_FECHA_VALIDACION)
	List<DeteccionesSinProcesarVO> buscarDeteccionesSinProcesarActualFechaValidacion(@Param("fechaInicio") String fechaInicio, @Param("origenPlaca") Integer origenPlaca);
	
	@Select(value = DETECCIONES_SIN_PROCESAR_HISTORICO_FECHA_VALIDACION)
	List<DeteccionesSinProcesarVO> buscarDeteccionesSinProcesarHistoricoFechaValidacion(@Param("fechaInicio") String fechaInicio, @Param("origenPlaca") Integer origenPlaca);
	
	
	
	
	@Select(value = DETECCIONES_BY_FECHA_INFRACCION_ACTUAL)
	List<DeteccionesResultadoVO> buscarDeteccionesByFechaInfraccionActual(@Param("codigoRadar") String codigoRadar, @Param("fechaInicio") String fechaInicio,
			@Param("fechaHoy") String fechaHoy, @Param("origenPlaca") Integer origenPlaca);
	
	@Select(value = DETECCIONES_BY_FECHA_INFRACCION_HISTORICO)
	List<DeteccionesResultadoVO> buscarDeteccionesByFechaInfraccionHistorica(@Param("codigoRadar") String codigoRadar, @Param("fechaInicio") String fechaInicio,
			@Param("origenPlaca") Integer origenPlaca);
	
	@Select(value = DETECCIONES_BY_FECHA_VALIDACION_ACTUAL)
	List<DeteccionesResultadoVO> buscarDeteccionesByFechaValidacionActual(@Param("codigoRadar") String codigoRadar, @Param("fechaInicio") String fechaInicio,
			@Param("fechaHoy") String fechaHoy, @Param("origenPlaca") Integer origenPlaca);
	
	@Select(value = DETECCIONES_BY_FECHA_VALIDACION_HISTORICA)
	List<DeteccionesResultadoVO> buscarDeteccionesByFechaValidacionHistorica(@Param("codigoRadar") String codigoRadar, @Param("fechaInicio") String fechaInicio,
			@Param("origenPlaca") Integer origenPlaca);
	
	@Select(value = DETECCIONES_BY_FECHA_INFRACCION_MES)
	List<DeteccionesResultadoVO> buscarDeteccionesFechaInfraccionPorMes(@Param("fechaInicio") String fechaInicio, 
			@Param("origenPlaca") Integer origenPlaca, @Param("codigoRadar") String codigoRadar);
	
	@Select(value = DETECCIONES_BY_FECHA_VALIDACION_MES)
	List<DeteccionesResultadoVO> buscarDeteccionesFechaValidacionPorMes(@Param("fechaInicio") String fechaInicio, 
			@Param("origenPlaca") Integer origenPlaca, @Param("codigoRadar") String codigoRadar);
	
	/**************************************************************/
	
	@Update(ACTUALIZA_FOTOMULTA_DETECCIONES_POR_LOTE)
	Integer actualizaFotomultaDeteccionesPorLote(@Param("tdskuid") String tdskuid, @Param("procesado") Integer procesado, 
		@Param("idRadarArchivo") Long idRadarArchivo, @Param("valido") Integer valido, @Param("noProcesado") Integer noProcesado, 
		@Param("tipoRadar") Integer tipoRadar, @Param("autorizado") Integer autorizado );

	
	@Update(REACTIVA_DETECCIONES_CANCELA_FM)
	public Integer updateDeteccionesLoteCanceladoFM(
		@Param("loteId")Long loteId);
	
	@Update(RESETEA_DETECCIONES_DESCARTADAS_TAI005)
	public Integer updateResetDeteccionesFCLoteCanceladoTAI005(
		@Param("loteId")Long loteId);
	
	@Update(RESETEA_DETECCIONES_DESCARTADAS_TAI027)
	public Integer updateResetDeteccionesFCLoteCanceladoTAI027(
		@Param("loteId")Long loteId);
	
	@Update(REACTIVA_FC_DETECCIONES_CANCELA)
	public Integer updateDeteccionesFCLoteCancelado(
		@Param("loteId")Long loteId);
	
	@Select(ACTUALIZA_CANT_PROCESADOS)
	public void actualizarCantProcs(
		@Param("loteId")Long loteId);
	
	@Select(ACTUALIZA_ST_COMP)
	public void actualizarStComp(
		@Param("loteId")Long loteId);
}
