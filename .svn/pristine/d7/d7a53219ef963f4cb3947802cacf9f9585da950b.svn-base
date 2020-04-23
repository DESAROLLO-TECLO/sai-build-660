package mx.com.teclo.saicdmx.persistencia.mybatis.dao.fotomultalotes;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import mx.com.teclo.saicdmx.persistencia.vo.fotomulta.ConsultaLotesVO;
import mx.com.teclo.saicdmx.persistencia.vo.fotomulta.FotoMultaLotesVO;




@Mapper
public interface FotomultaLotesMyBatisDAO {
	
	String CANCELA_LOTE_FOTOMULTA=
			"UPDATE  FOTOMULTA_LOTES "+
			"SET     CANCELADO = 1, CANTIDAD_PROCESADOS = #{cantidadProcesados}, CANTIDAD_CANCELADOS = #{cantidadCancelados} "+
			"WHERE   LOTE_ID = #{loteId}";
	
	String UPDATE_CANCELADOS_LOTE = "UPDATE FOTOMULTA_LOTES SET CANCELADO = 1, CANTIDAD_CANCELADOS = #{cantidadCancelados} "+
			"WHERE LOTE_ID = #{lote}";
	
	String ACTUALIZA_DETECCIONES_TDSKUID_DUPLICADOS = "UPDATE  FOTOMULTA_DETECCIONES "+
			"SET     VALIDO = 0, FECHA_CANCELACION = SYSDATE, MODIFICADO_POR = -2, MOTIVO_CANCELACION = 'TDSKUID DUPLICADO' "+
			"WHERE   FOTOMULTA_ID IN "+
			          "( SELECT  DETEC.FOTOMULTA_ID "+
						" FROM    FOTOMULTA_DETECCIONES DETEC "+
						          "JOIN (  SELECT  TDSKUID, "+ 
						                  "MIN(FECHA_CREACION) FECHA_MENOR "+ 
						                  "FROM    FOTOMULTA_DETECCIONES "+
						                  "WHERE TDSKUID IN( SELECT TDSKUID "+
						                                    "FROM    FOTOMULTA_DETECCIONES "+ 
						                                    "WHERE   TO_DATE(REPLACE(FECHA,'.','/'), 'dd/MM/yy') BETWEEN #{fechaInicio} AND #{fechaFin} "+
						                                    "AND     VALIDO = 1 "+
						                                    "AND 	 PROCESADO = 0 "+
						                                    "AND     AUTORIZADA = 1 "+ 
						                                    "AND     RADAR_TIPO_ID = #{tipoRadar} "+
						                                  ") "+
						                  "AND     VALIDO = 1 "+
						                  "AND     AUTORIZADA = 1 "+
						                  "AND     RADAR_TIPO_ID = #{tipoRadar} " +
						                  "GROUP BY TDSKUID "+
						                  "HAVING COUNT(TDSKUID)>1 "+
						          ") DUPLICADOS "+
						            "ON  RTRIM(LTRIM(DETEC.TDSKUID)) = RTRIM(LTRIM(DUPLICADOS.TDSKUID))  "+
						            "AND DETEC.FECHA_CREACION != DUPLICADOS.FECHA_MENOR AND VALIDO = 1"+
			          ")";
	
	String ACTUALIZA_DETECCIONES_PLACA_FECHAHORA_DUPLICADOS = "UPDATE  FOTOMULTA_DETECCIONES "+
			"SET     VALIDO = 0, FECHA_CANCELACION = SYSDATE, MODIFICADO_POR = -2, MOTIVO_CANCELACION = 'PLACA, FECHA, HORA Y UT DUPLICADO' "+
			"WHERE   FOTOMULTA_ID IN "+
			          "( SELECT  DETEC.FOTOMULTA_ID "+
						"FROM    FOTOMULTA_DETECCIONES DETEC "+
						          "JOIN (  SELECT  DETEC1.PLACA, "+
						                          "DETEC1.FECHA, "+
						                          "DETEC1.HORA, "+
						                          "DETEC1.UT, "+
						                          "MIN(detec1.FECHA_CREACION) FECHA_MENOR "+
						                  "FROM    FOTOMULTA_DETECCIONES DETEC1 "+
						                          "join (SELECT   PLACA "+
						                                    "PLACA,  "+
						                                    "FECHA, "+
						                                    "HORA, "+
						                                    "UT "+
						                                    "FROM    FOTOMULTA_DETECCIONES "+
						                                    "WHERE   TO_DATE(REPLACE(FECHA,'.','/'), 'dd/MM/yy') BETWEEN #{fechaInicio} AND #{fechaFin} "+
						                                    "AND     VALIDO = 1 "+
						                                    "AND 	 PROCESADO = 0 "+
						                                    "AND     AUTORIZADA = 1 "+
						                                    "AND     RADAR_TIPO_ID = #{tipoRadar} "+
						                                    ") DETEC2 "+
								                              "ON    DETEC1.PLACA = DETEC2.PLACA "+
								                              "AND   DETEC1.FECHA = DETEC2.FECHA "+
								                              "AND   DETEC1.HORA = DETEC2.HORA "+
								                              "AND   DETEC1.UT = DETEC2.UT "+
						                  "WHERE     DETEC1.VALIDO = 1 "+
						                  "AND     DETEC1.AUTORIZADA = 1 "+
						                  "AND     DETEC1.RADAR_TIPO_ID = #{tipoRadar} "+
						                  "GROUP BY DETEC1.PLACA,DETEC1.FECHA,DETEC1.HORA,DETEC1.UT "+
						                  "HAVING COUNT(DETEC1.PLACA)>1 "+
						          ") DUPLICADOS "+
						            "ON  RTRIM(LTRIM(DETEC.PLACA)) = RTRIM(LTRIM(DUPLICADOS.PLACA)) "+
						            "AND RTRIM(LTRIM(DETEC.FECHA)) = RTRIM(LTRIM(DUPLICADOS.FECHA)) "+
						            "AND RTRIM(LTRIM(DETEC.HORA)) = RTRIM(LTRIM(DUPLICADOS.HORA)) "+
						            "AND DETEC.FECHA_CREACION != DUPLICADOS.FECHA_MENOR AND VALIDO = 1"+
			          ")";
	
	String ACTUALIZA_DETECCIONES_EXISTENTES_ENRADARARCHIVODETALLE = "UPDATE  FOTOMULTA_DETECCIONES "+
			"SET     VALIDO = 0, FECHA_CANCELACION = SYSDATE, MODIFICADO_POR = -2, MOTIVO_CANCELACION = 'TDSKUID YA EXISTE EN RADAR_ARCHIVO_DETALLE' "+
			"WHERE   FOTOMULTA_ID IN "+
			          "( SELECT  DETEC.FOTOMULTA_ID "+
						"FROM    FOTOMULTA_DETECCIONES DETEC "+
						          "JOIN (  SELECT  TDSKUID "+
						                  "FROM    FOTOMULTA_DETECCIONES "+
						                  "WHERE   TO_DATE(REPLACE(FECHA,'.','/'), 'dd/MM/yy') BETWEEN #{fechaInicio} AND #{fechaFin} "+
						                  "AND     VALIDO = 1 "+
						                  "AND     PROCESADO = 0 "+
						                  "AND     AUTORIZADA = 1 "+
						                  "AND     RADAR_TIPO_ID = #{tipoRadar} "+
						                  "AND     TDSKUID IN (  SELECT TDSKUID  "+
						                                        "FROM  RADAR_ARCHIVO_DETALLE AECHIVODETALLE "+
						                                              "JOIN RADAR_ARCHIVO ARCHIVO "+
						                                              "ON AECHIVODETALLE.RADAR_ARCHIVO_ID = ARCHIVO.ID "+
						                                        "WHERE ARCHIVO.ARCHIVO_TIPO = #{archivoTipo} "+
						                                        "AND   AECHIVODETALLE.ACTIVO = 1 "+
						                                        "UNION "+
						                                        "SELECT TDSKUID  "+
						                                        "FROM  RADAR_ARCHIVO_DETALLE_HIST AECHIVODETALLE_HIST "+
						                                              "JOIN RADAR_ARCHIVO ARCHIVO "+
						                                              "ON AECHIVODETALLE_HIST.RADAR_ARCHIVO_ID = ARCHIVO.ID "+
						                                        "WHERE ARCHIVO.ARCHIVO_TIPO = #{archivoTipo} "+
						                                        "AND   AECHIVODETALLE_HIST.ACTIVO = 1 "+ 
						                                    ") "+
						                ") DUPLICADOS "+
						                "ON  RTRIM(LTRIM(DETEC.TDSKUID)) = RTRIM(LTRIM(DUPLICADOS.TDSKUID)) "+
						"AND     DETEC.VALIDO = 1 "+
						"AND     DETEC.procesado = 0 "+
						"AND     DETEC.AUTORIZADA = 1 "+
						"AND     DETEC.RADAR_TIPO_ID = #{tipoRadar} "+
			          ")";

	String NUMERO_DE_DETECCIONES = "SELECT	COUNT(*) " +
			"FROM 	FOTOMULTA_DETECCIONES " +
			"WHERE 	TO_DATE(REPLACE(FECHA,'.','/'), 'dd/MM/yy') BETWEEN #{fechaInicio} AND #{fechaFin} "+
			"AND  	VALIDO = #{valido} " +
			"AND 	PROCESADO = #{noProcesado} " +
			"AND 	RADAR_TIPO_ID = #{tipoRadar} " +
			"AND 	AUTORIZADA = #{autorizado} " +
			"AND ORIGEN_PLACA = #{origenPlaca}";
	
	String INSERTA_FOTOMULTA_LOTE = "INSERT INTO FOTOMULTA_LOTES "
	+ "(LOTE_ID, NOMBRE, FECHA_EMISION, FECHA_PROC_INICIAL, FECHA_PROC_FINAL, CREADO_POR, MODIFICADO_POR, "
	+ "ULTIMA_MODIFICACION, CANTIDAD_PROCESADOS, RADAR_TIPO_ID, EN_CREACION, ARCHIVO_TIPO) "
	+ "VALUES "
	+ "(#{loteID}, #{nombre}, #{fechaEmision}, #{fechaProcInicio}, #{fechaProcFin}, "
	+ "	#{empleadoId}, #{empleadoId}, SYSDATE, #{cantidadProcesados}, #{tipoRadar}, #{enCreacion}, #{archivoTipo})";
	
	String ACTUALIZA_LOTE_CREACION = "UPDATE FOTOMULTA_LOTES "
			+ "SET CANTIDAD_PROCESADOS = #{cantidadProcesados}, EN_CREACION = 0 "
			+ "WHERE LOTE_ID = #{loteId}";
	
	String CONSULTA_LOTES_POR_FECHA = "SELECT FL.LOTE_ID AS LOTEID, RCAT.NOMBRE AS NOMBREARCHIVOTIPO, "+
			"FL.NOMBRE AS NOMBRE, FL.ARCHIVO_TIPO AS ARCHIVOTIPO, "+
			"FCTR.CAT_TIPO_RADAR_ID AS CATTIPORADARID, "+
			"FCTR.NOMBRE AS NOMBRETIPORADAR, "+
			"TO_CHAR(FL.FECHA_PROC_INICIAL,'DD/MM/YYYY') as FECHAPROCINICIAL, "+ 
	        "TO_CHAR(FL.FECHA_PROC_FINAL,'DD/MM/YYYY') AS FECHAPROCFINAL, "+
			"NVL(TO_CHAR(FL.FECHA_EMISION,'DD/MM/YYYY'), ' ') AS FECHAEMISION, "+
			"NVL(TO_CHAR(RA.FECHA_IMPOSICION,'DD/MM/YYYY'), ' ') AS FECHAIMPOSICION, "+
			"NVL(TO_CHAR(RA.FECHA_COMPLEMENTADO,'DD/MM/YYYY'), ' ') AS FECHACOMPLEMENTADO, "+
			"NVL(TO_CHAR(RA.FECHA_LIBERACION,'DD/MM/YYYY'), ' ') AS FECHALIBERACION, "+
			"(CASE "+
			"	WHEN (RA.FECHA_IMPOSICION IS NOT NULL) "+
			"   	THEN to_char(trunc(RA.FECHA_IMPOSICION,'DD') - TRUNC(SYSDATE,'DD')) "+
			"   ELSE 'NA' "+
			"END) AS DIALIBERACION, "+
			"RA.INFRAC_NUM_INICIAL AS INFRACNUMINICIAL, "+
	        "RA.INFRAC_NUM_FINAL AS INFRACNUMFINAL, "+
			"RA.ARCHIVO_COMPLEMENTADAS AS ARCHIVOCOMPLEMENTADAS, "+
	        "RA.ARCHIVO_RECHAZADAS AS ARCHIVORECHAZADAS, "+
			"(CASE "+
			"	WHEN RA.ESTATUS_PROCESO_ID = 29 OR RA.ESTATUS_PROCESO_ID = 30 OR RA.ESTATUS_PROCESO_ID = 31 "+
			"	       OR RA.ESTATUS_PROCESO_ID = 32 OR RA.ESTATUS_PROCESO_ID = 33 "+
			"	   	THEN 0 "+
			"	ELSE 1 "+
			"END) AS COMPLEMENTADO, "+ 
			"FL.CANTIDAD_PROCESADOS AS CANTIDADPROCESADOS, "+
			"FL.CANTIDAD_CANCELADOS AS CANTIDADCANCELADOS, "+
			"(CASE WHEN FECHA_ENVIADO_CR IS NULL THEN ' ' ELSE TO_CHAR(FECHA_ENVIADO_CR,'DD/MM/YYYY') END) AS FECHAENVIADOCR, "+
			"	RA.ESTATUS_PROCESO_ID AS ESTATUSPROCESOID, "+
			"(SELECT FBWS.ESTATUS_EJECUCION "+
			" FROM FOTOMULTA_BITACORA_WS FBWS "+
			" 	WHERE FL.LOTE_ID  = FBWS.REQUEST_LOTE_ID "+
			" 		AND  FBWS.FOTOMULTA_CAT_PROCESO_WS_ID = 1 "+
			" 			 AND  FBWS.FOTOMULTA_BITACORA_WS_ID = (	SELECT MAX(FBWS3.FOTOMULTA_BITACORA_WS_ID) "+
		    "                              						FROM FOTOMULTA_BITACORA_WS FBWS3 "+
	        "                          							WHERE FBWS.REQUEST_LOTE_ID  = FBWS3.REQUEST_LOTE_ID "+
	        "                          							AND  FBWS3.FOTOMULTA_CAT_PROCESO_WS_ID = 1)) AS ESTATUSWSASIGNACION, "+
        	"(SELECT FBWS.EN_PROCESO "+
			"	FROM FOTOMULTA_BITACORA_WS FBWS "+
			"		WHERE FL.LOTE_ID  = FBWS.REQUEST_LOTE_ID "+
			"			AND  FBWS.FOTOMULTA_CAT_PROCESO_WS_ID = 1 "+
			"			 AND  FBWS.FOTOMULTA_BITACORA_WS_ID = (	SELECT MAX(FBWS3.FOTOMULTA_BITACORA_WS_ID) "+
		    "                              						FROM FOTOMULTA_BITACORA_WS FBWS3 "+
	        "                          							WHERE FBWS.REQUEST_LOTE_ID  = FBWS3.REQUEST_LOTE_ID "+
	        "                          							AND  FBWS3.FOTOMULTA_CAT_PROCESO_WS_ID = 1)) AS ENPROCESOWSASIGNACION,"+
		    "(CASE WHEN FL.FECHA_LIBERACION IS NULL THEN ' ' ELSE TO_CHAR(FL.FECHA_LIBERACION,'DD/MM/YYYY') END) AS FECHALIBERACION, "+
		    "	(SELECT FBWS2.ESTATUS_EJECUCION "+
  			" 		FROM FOTOMULTA_BITACORA_WS FBWS2 "+
 			"			WHERE FL.LOTE_ID  = FBWS2.REQUEST_LOTE_ID "+
 			"			 	AND  FBWS2.FOTOMULTA_CAT_PROCESO_WS_ID = 2 "+
 			"			 		AND  FBWS2.FOTOMULTA_BITACORA_WS_ID = (	SELECT MAX(FBWS4.FOTOMULTA_BITACORA_WS_ID) "+
		    "                              							FROM FOTOMULTA_BITACORA_WS FBWS4 "+
		    "                              							WHERE FBWS2.REQUEST_LOTE_ID  = FBWS4.REQUEST_LOTE_ID "+
		    "                              							AND  FBWS4.FOTOMULTA_CAT_PROCESO_WS_ID = 2)) AS ESTATUSWSLIBERACION, "+
		 	"(SELECT FBWS2.EN_PROCESO "+
 			"	FROM FOTOMULTA_BITACORA_WS FBWS2 "+
 			"		WHERE FL.LOTE_ID  = FBWS2.REQUEST_LOTE_ID  "+
 			"			AND  FBWS2.FOTOMULTA_CAT_PROCESO_WS_ID = 2 "+
 			"			 	AND  FBWS2.FOTOMULTA_BITACORA_WS_ID = (	SELECT MAX(FBWS4.FOTOMULTA_BITACORA_WS_ID) "+
		    "                              							FROM FOTOMULTA_BITACORA_WS FBWS4 "+
	        "                          								WHERE FBWS2.REQUEST_LOTE_ID  = FBWS4.REQUEST_LOTE_ID "+
	        "                          								AND  FBWS4.FOTOMULTA_CAT_PROCESO_WS_ID = 2)) AS ENPROCESOWSLIBERACION "+
			"FROM FOTOMULTA_LOTES FL "+
			"	JOIN FOTOMULTA_CAT_TIPO_RADAR FCTR "+
			"   	ON FCTR.CAT_TIPO_RADAR_ID = FL.RADAR_TIPO_ID "+
			"  			JOIN RADAR_CAT_ARCHIVO_TIPO RCAT ON FL.ARCHIVO_TIPO = RCAT.ARCHIVO_TIPO_ID, "+
			"				RADAR_ARCHIVO RA "+
			"WHERE   FL.LOTE_ID = RA.ID "+
			"AND 	FL.EN_CREACION = 0 "+
			"AND (CASE "+
			"		WHEN (#{tipoFecha} =1 AND #{fechaInicio} IS NOT NULL AND #{fechaFin} IS NOT NULL) AND TRUNC(RA.FECHA_EMISION, 'DD') BETWEEN TO_DATE(#{fechaInicio},'DD/MM/YYYY') AND TO_DATE(#{fechaFin},'DD/MM/YYYY') "+
			"	    	THEN 1  "+
			"	    WHEN (#{tipoFecha} = 1 AND #{fechaInicio} IS NOT NULL AND #{fechaFin} IS NULL) AND TRUNC(RA.FECHA_EMISION, 'DD') > TO_DATE(#{fechaInicio},'DD/MM/YYYY') "+
			"	        THEN 1 "+
			"	    WHEN (#{tipoFecha} = 1 AND #{fechaInicio} IS NULL AND #{fechaFin} IS NOT NULL) AND TRUNC(RA.FECHA_EMISION, 'DD') < TO_DATE(#{fechaFin},'DD/MM/YYYY') "+
			"	        THEN 1 "+
			"	    WHEN (#{tipoFecha} = 1 AND #{fechaInicio} IS NULL AND #{fechaFin} IS NULL) "+
			"	        THEN 1 "+
			"	    WHEN (#{tipoFecha} <> 1) "+
			"	        THEN 1 "+
			"END) = 1 "+
			"	AND (CASE "+
			"	      	WHEN (#{tipoFecha} =2 AND #{fechaInicio} IS NOT NULL AND #{fechaFin} IS NOT NULL) AND TRUNC(RA.FECHA_COMPLEMENTADO, 'DD') BETWEEN TO_DATE(#{fechaInicio},'DD/MM/YYYY') AND TO_DATE(#{fechaFin},'DD/MM/YYYY') "+
			"	        	THEN 1 "+
			"	        WHEN (#{tipoFecha} = 2 AND #{fechaInicio} IS NOT NULL AND #{fechaFin} IS NULL) AND TRUNC(RA.FECHA_COMPLEMENTADO, 'DD') > TO_DATE(#{fechaInicio},'DD/MM/YYYY') "+
			"	            THEN 1 "+
			"	        WHEN (#{tipoFecha} = 2 AND #{fechaInicio} IS NULL AND #{fechaFin} IS NOT NULL) AND TRUNC(RA.FECHA_COMPLEMENTADO, 'DD') < TO_DATE(#{fechaFin},'DD/MM/YYYY') "+
			"	            THEN 1 "+
			"	        WHEN (#{tipoFecha} = 2 AND #{fechaInicio} IS NULL AND #{fechaFin} IS NULL) "+
			"	            THEN 1 "+
			"	        WHEN (#{tipoFecha} <> 2) "+
			"	            THEN 1 "+
			"END) = 1 "+
			"	AND (CASE  "+
			"	     	WHEN (#{tipoFecha} = 3 AND #{fechaInicio} IS NOT NULL AND #{fechaFin} IS NOT NULL) AND TRUNC(RA.FECHA_LIBERACION, 'DD') BETWEEN TO_DATE(#{fechaInicio},'DD/MM/YYYY') AND TO_DATE(#{fechaFin},'DD/MM/YYYY') "+
			"	        	THEN 1 "+
			"	        WHEN (#{tipoFecha} = 3 AND #{fechaInicio} IS NOT NULL AND #{fechaFin} IS NULL) AND TRUNC(RA.FECHA_LIBERACION, 'DD') > TO_DATE(#{fechaInicio},'DD/MM/YYYY') "+
			"	            THEN 1 "+
			"	        WHEN (#{tipoFecha} = 3 AND #{fechaInicio} IS NULL AND #{fechaFin} IS NOT NULL) AND TRUNC(RA.FECHA_LIBERACION, 'DD') < TO_DATE(#{fechaFin},'DD/MM/YYYY') "+
			"	            THEN 1 "+
			"	        WHEN (#{tipoFecha} = 3 AND #{fechaInicio} IS NULL AND #{fechaFin} IS NULL) "+
			"	            THEN 1 "+
			"	        WHEN (#{tipoFecha} <> 3) "+
			"	            THEN 1 "+
			"END) = 1"+
			"	AND (CASE "+
			"			WHEN #{tipoRadar} = 0 "+
			"				THEN 1 "+
			"			WHEN FL.RADAR_TIPO_ID = #{tipoRadar} "+
			"				THEN 1 "+
			"END) = 1 "+
			"	AND	(CASE "+
			"		 	WHEN #{estatusProceso} = 0 "+
			"		    	THEN 1 "+
			"		    WHEN RA.ESTATUS_PROCESO_ID = #{estatusProceso} "+
			"		        THEN 1 "+
			"END) = 1 "+
			"	AND FL.ARCHIVO_TIPO = CASE " +
			"		WHEN #{archivoTipo} = 6 OR #{archivoTipo} = 3 " +
			"			THEN #{archivoTipo} " +
			"		ELSE FL.ARCHIVO_TIPO " +
			"END "+
			"ORDER BY FL.LOTE_ID DESC";
	
	String UPDATE_CANTIDAD_DETECCIONES_POR_LOTE = 
		"UPDATE FOTOMULTA_LOTES "
		+ "	SET "
		+ "	CANTIDAD_CANCELADOS = #{cantidadCanceladas}, "
		+ "	CANTIDAD_PROCESADOS = #{cantidadProcesados} "
		+ "WHERE LOTE_ID = #{lote}";
	
	String BUSCA_TIPO_LOTE_FOTOMULTA = 
		"SELECT "
		+ "	codigo "
		+ "FROM fotomulta_lotes l "
		+ "JOIN radar_archivo r ON l.lote_id = r.id "
		+ "JOIN fotomulta_cat_tipo_radar c ON c.cat_tipo_radar_id = radar_tipo_id "
		+ "WHERE id = #{archivoId}";
	
	@Update(CANCELA_LOTE_FOTOMULTA)
	public Integer updateFotoMultaLotesCancelado(@Param("loteId")Long loteId, @Param("cantidadProcesados")Integer cantidadProcesados, @Param("cantidadCancelados") Integer cantidadCancelados);
	
	@Select(NUMERO_DE_DETECCIONES)
	public Integer numeroDetecciones(@Param("fechaInicio") Date fechaInicio, @Param("fechaFin") Date fechaFin, @Param("valido") Integer valido, 
			@Param("noProcesado") Integer procesado, @Param("tipoRadar") Integer tipoRadar, 
			@Param("autorizado") Integer autorizado, @Param("origenPlaca") Integer origenPlaca);
	
	@Update(ACTUALIZA_DETECCIONES_TDSKUID_DUPLICADOS)
	public void actualizaDeteccionesTdskuidDuplicados(@Param("tipoRadar") Integer tipoRadar, 
							@Param("fechaInicio") Date fechaInicio, @Param("fechaFin") Date fechaFin);

	@Update(ACTUALIZA_DETECCIONES_PLACA_FECHAHORA_DUPLICADOS)
	public void actualizaDeteccionesPlacaFechaHoraDuplicados(@Param("tipoRadar") Integer tipoRadar, 
							@Param("fechaInicio") Date fechaInicio, @Param("fechaFin") Date fechaFin);
	
	@Update(ACTUALIZA_DETECCIONES_EXISTENTES_ENRADARARCHIVODETALLE)
	public void actualizaDeteccionesExistentesEnRadarArchiviDetalle(@Param("tipoRadar") Integer tipoRadar, 
								@Param("fechaInicio") Date fechaInicio, @Param("fechaFin") Date fechaFin,
								@Param("archivoTipo") Integer archivoTipo);

	@Insert(INSERTA_FOTOMULTA_LOTE)
	public Integer InsertarFotoMultaLote(FotoMultaLotesVO fotoMultaLotesVO);
	
	@Update(ACTUALIZA_LOTE_CREACION)
	public void actualizaLoteCreacion(@Param("cantidadProcesados") Integer cantidadProcesados, @Param("loteId") Long loteId);

	@Select(CONSULTA_LOTES_POR_FECHA)
	public List<ConsultaLotesVO> consultaLotesPorFechas(@Param("fechaInicio") String fechaInicio, @Param("fechaFin") String fechaFin, @Param("tipoRadar")Long tipoRadar,
								@Param("estatusProceso")Integer estatusProceso, @Param("tipoFecha")Integer tipoFecha, @Param("archivoTipo") Integer archivoTipo);

	@Update(UPDATE_CANTIDAD_DETECCIONES_POR_LOTE)
	public Integer actualizarCantidadDeteccionesPorLote(@Param("cantidadCanceladas") Integer cantidadCanceladas,
														 @Param("cantidadProcesados") Integer cantidadProcesados,
														@Param("lote") Long archivoId);
	
	@Update(UPDATE_CANCELADOS_LOTE)
	public Integer actualizarCantidadCanceladosLote(@Param("lote") Long archivoId, @Param("cantidadCancelados") Integer cantidadCancelados);
	
	@Select(BUSCA_TIPO_LOTE_FOTOMULTA)
	public String buscaTipoLoteFotoMulta(@Param("archivoId") Long archivoId);
}
