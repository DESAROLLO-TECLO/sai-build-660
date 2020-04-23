package mx.com.teclo.saicdmx.persistencia.mybatis.dao.radarArchivoProcesado;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import mx.com.teclo.saicdmx.persistencia.vo.radares.ArchivoBatchFinanzasVO;
import mx.com.teclo.saicdmx.persistencia.vo.radares.ConsultaArchivosProcesadosVO;
import mx.com.teclo.saicdmx.persistencia.vo.radares.DetalleInfraccionesLiberadasVO;
import mx.com.teclo.saicdmx.persistencia.vo.radares.DeteccionesComplementadasVO;
import mx.com.teclo.saicdmx.persistencia.vo.radares.RadarComboTipoArchivoVO;


@Mapper
public interface RadarArchivoProcesadosMyBatisDAO {
	
	
	String GET_COMBO_ARCHIVO_PROCESADO = 
			"SELECT distinct id_archivo_tipo_fora as value," 
			+ "	nb_dispositivo_deteccion as label "
			+ "FROM TAI007C_FM_TIPO_DETECCIONES ";
		
		String GET_COMBO_RADAR_PROCESADO =  
			"SELECT ID_MARCA_DISPOSITIVO_DET AS value, "
			+ "	NB_DISPOSITIVO AS label " 
			+ "FROM TAI009C_FM_MARCA_DISPOSITIVO "
			+ "WHERE "
			+ "	ID_MARCA_DISPOSITIVO_DET IN( " 
			+ "		SELECT ID_MARCA_DISPOSITIVO_DET "
			+ "		FROM TAI007C_FM_TIPO_DETECCIONES "
			+ "		WHERE 1 =( "
			+ "			CASE "
			+ "				WHEN #{tipoArchivo} != -1 AND ID_ARCHIVO_TIPO_FORA = #{tipoArchivo} "
			+ "					THEN 1 "
			+ "				WHEN #{tipoArchivo} = -1 THEN 0 END))";
		
		/*String GET_COMBO_ARCHIVO_PROCESADO = "SELECT archivo_tipo_id as value," 
												+ "nombre as label "
												+ "FROM RADAR_CAT_ARCHIVO_TIPO " + "WHERE activo=1 ";*/
		
	String GET_ARCHIVOS_PROCESADOS = "SELECT "
			+ "DISTINCT A.ID AS ARCHIVO_ID, A.NOMBRE AS ARCHIVO_NOMBRE, A.ARCHIVO_TIPO AS ARCHIVO_TIPO_ID, TA.NOMBRE AS ARCHIVO_TIPO, "
			+ "NVL(TO_CHAR(A.FECHA_EMISION,'DD/MM/YYYY'), ' ') AS FECHA_EMISION, NVL(TO_CHAR(A.FECHA_IMPOSICION,'DD/MM/YYYY'), ' ') AS FECHA_IMPOSICION, "
			+ "NVL(TO_CHAR(TRUNC (A.FECHA_COMPLEMENTADO, 'DD'),'DD/MM/YYYY'),' ') AS FECHA_COMPLEMENTACION, "
			+ "NVL(TO_CHAR(TRUNC (A.FECHA_LIBERACION, 'DD'),'DD/MM/YYYY'),' ') AS FECHA_LIBERACION, "
			+ "("
			+ "	CASE "
			+ "		WHEN (A.FECHA_IMPOSICION IS NOT NULL) THEN "
			+ "			TO_CHAR(TRUNC(A.FECHA_IMPOSICION,'DD') - TRUNC(SYSDATE,'DD')) ELSE 'NA' END) AS DIA_LIBERACION, "
			+ "A.ANIO_SALARIO_MINIMO AS SALARIO_MINIMO, A.INFRAC_NUM_INICIAL AS INFRAC_NUM_INICIAL, A.INFRAC_NUM_FINAL AS INFRAC_NUM_FINAL, "
			+ "("
			+ "	CASE "
			+ "		WHEN (A.ESTATUS_PROCESO_ID = 29 OR A.ESTATUS_PROCESO_ID = 30 OR A.ESTATUS_PROCESO_ID = 31 "
			+ "				OR A.ESTATUS_PROCESO_ID = 32 OR A.ESTATUS_PROCESO_ID = 33) "
			+ "		THEN 0 ELSE 1 END) AS COMPLEMENTADO, "
			+ "A.CANTIDAD_PROCESADOS, A.ESTATUS_PROCESO_ID AS ESTATUS_PROCESO_ID, A.CANTIDAD_CANCELADOS, A.ARCHIVO_COMPLEMENTADAS, A.ARCHIVO_RECHAZADAS, "
			+ "A.EN_PROCESO, A.ORIGEN_LOTE, "
			+ "("
			+ "	CASE "
			+ "		WHEN (SELECT COUNT(RBP.RADAR_ARCHIVO_ID) "
			+ "			FROM RADAR_BITACORA_PROCESO RBP WHERE RBP.ESTATUS_PROCESO_ID = 29 AND RBP.RADAR_ARCHIVO_ID = A.ID) = 0 THEN -1 "
			+ "		ELSE A.ST_ENVIADO END) AS ENVIAR, "
			+ "(CASE " 
			+ "		WHEN A.ID_TIPO_PERSONA = 1 THEN 'Foranea' " 
			+ "		WHEN A.ID_TIPO_PERSONA = 2 THEN 'Física' " 
			+ "		WHEN A.ID_TIPO_PERSONA = 3 THEN 'Moral' " 
			+ "		WHEN A.ID_TIPO_PERSONA is null or A.ID_TIPO_PERSONA = 0 THEN 'N/A' " 
			+ "END) as TIPO_PERSONA, " 
			+ "(CASE WHEN A.ARCHIVO_TIPO IN (SELECT ID_ARCHIVO_TIPO_CDMX FROM TAI036C_FC_TIPO_FOTOCIVICA) THEN 'CMDX' " 
			+ "	 WHEN A.ARCHIVO_TIPO IN (SELECT ID_ARCHIVO_TIPO_FORA FROM TAI036C_FC_TIPO_FOTOCIVICA) THEN 'Foraneo' " 
			+ "END) as origen_placa "
			+ "FROM RADAR_ARCHIVO_TOTAL A INNER JOIN RADAR_ESTATUS_PROCESO EP ON "
			+ "				(EP.ID = A.ESTATUS_PROCESO_ID) "
			+ "			JOIN RADAR_CAT_ARCHIVO_TIPO TA ON A.ARCHIVO_TIPO = TA.ARCHIVO_TIPO_ID "
			+ "WHERE "
			+ "A.ORIGEN_LOTE = ("
			+ "	CASE WHEN #{origenProceso} = -1 THEN A.ORIGEN_LOTE "
			+ "		 else #{origenProceso}"
			+ "	END) "
			+ "AND "
			+ "(CASE WHEN #{tipoProceso} = 0 THEN 1 " 
			+ "		WHEN #{tipoProceso} = 2 AND COMPLEMENTADO = 1 THEN 1 " 
			+ "		WHEN #{tipoProceso} = 3 AND LIBERADO = 1 THEN 1 " 
			+ "		WHEN #{tipoProceso} = 1 AND CANCELADO = 1 THEN 1 "
			+ "		WHEN 4 = 4 AND COMPLEMENTADO = 0 AND LIBERADO = 0 AND CANCELADO = 0 THEN 1" 
			+ "END) = 1 "
			+ "AND "
			+ "(CASE WHEN #{tipoArchivo} = -1 AND A.ARCHIVO_TIPO = A.ARCHIVO_TIPO THEN 1 "
			+ "		 WHEN #{tipoArchivo} = 0 "
			+ "			  AND A.ARCHIVO_TIPO IN ("
			+ "				SELECT ID_ARCHIVO_TIPO_CDMX FROM TAI036C_FC_TIPO_FOTOCIVICA) THEN 1 "
			+ "		 WHEN #{tipoArchivo} = 1 AND A.ARCHIVO_TIPO IN ("
			+ "				SELECT ID_ARCHIVO_TIPO_FORA FROM TAI036C_FC_TIPO_FOTOCIVICA) THEN 1 END) = 1 "
			+ "AND"
			+ " A.ID_TIPO_DETECCION = ("
			+ "	CASE WHEN #{tipoDeteccion} = 0 THEN A.ID_TIPO_DETECCION "
			+ "		 ELSE #{tipoDeteccion} END) "
			+ "AND  "
			+ "A.ID_TIPO_PERSONA = ("
			+ "	CASE WHEN #{tipoPersona} = 0 THEN A.ID_TIPO_PERSONA "
			+ "		 ELSE #{tipoPersona} END) "
			+ "AND "
			+ "(CASE WHEN ( #{tipoDeFecha} = 1 AND #{fechaInicio} IS NOT NULL AND #{fechaFin} IS NOT NULL) "
			+ "		AND TRUNC (A.FECHA_COMPLEMENTADO, 'DD') BETWEEN TO_DATE( #{fechaInicio},'DD/MM/YYYY') "
			+ "		AND TO_DATE( #{fechaFin},'DD/MM/YYYY') THEN 1 "
			+ "		 WHEN ( #{tipoDeFecha} = 1 AND #{fechaFin} IS NULL AND  #{fechaInicio}  IS NOT NULL ) "
			+ "		AND TRUNC (A.FECHA_COMPLEMENTADO, 'DD') >= TO_DATE( #{fechaInicio}) THEN 1 "
			+ "		 WHEN ( #{tipoDeFecha} = 1 AND #{fechaFin} IS NOT NULL "
			+ "		AND  #{fechaInicio}  IS NULL) "
			+ "		AND TRUNC (A.FECHA_COMPLEMENTADO, 'DD') <= TO_DATE( #{fechaFin}) THEN 1 "
			+ "		 WHEN ( #{tipoDeFecha} = 0  "
			+ "		AND #{fechaInicio}  IS NOT NULL "
			+ "		AND #{fechaFin} IS NOT NULL) "
			+ "		AND TRUNC (A.FECHA_EMISION, 'DD') BETWEEN TO_DATE( #{fechaInicio},'DD/MM/YYYY') "
			+ "		AND TO_DATE( #{fechaFin},'DD/MM/YYYY') THEN 1 "
			+ "		WHEN ( #{tipoDeFecha} = 0  AND #{fechaFin} IS NULL AND #{fechaInicio}  IS NOT NULL ) "
			+ "		AND TRUNC (A.FECHA_EMISION, 'DD') >= TO_DATE( #{fechaInicio}) THEN 1 "
			+ "		WHEN ( #{tipoDeFecha} = 0  AND #{fechaFin} IS NOT NULL AND #{fechaInicio} IS NULL) "
			+ "		AND TRUNC (A.FECHA_EMISION, 'DD') <= TO_DATE( #{fechaFin}) THEN 1 "
			+ "		WHEN ( #{tipoDeFecha} = 2  AND #{fechaInicio}  IS NOT NULL AND #{fechaFin} IS NOT NULL) "
			+ "		AND TRUNC (A.FECHA_LIBERACION, 'DD') BETWEEN TO_DATE( #{fechaInicio} ,'DD/MM/YYYY') "
			+ "		AND TO_DATE( #{fechaFin},'DD/MM/YYYY') THEN 1 "
			+ "		WHEN ( #{tipoDeFecha} = 2  AND #{fechaFin} IS NULL AND #{fechaInicio}   IS NOT NULL) "
			+ "		AND TRUNC (A.FECHA_LIBERACION, 'DD') >= TO_DATE( #{fechaInicio} ) THEN 1 "
			+ "		WHEN ( #{tipoDeFecha} = 2  AND #{fechaFin} IS NOT NULL AND #{fechaInicio} IS NULL) "
			+ "		AND TRUNC (A.FECHA_LIBERACION, 'DD') <= TO_DATE( #{fechaFin}) THEN 1 "
			+ "		WHEN ( #{tipoDeFecha} IS NULL) THEN 1 END) = 1 "
			+ "ORDER BY A.ID DESC";
	

	String GET_ARCHIVOS_PROCESADOS_ALL = "SELECT  DISTINCT"
			+ "	A.ID AS ARCHIVO_ID, "
			+ "	A.NOMBRE AS ARCHIVO_NOMBRE,"
			+ "	A.ARCHIVO_TIPO AS ARCHIVO_TIPO_ID, "
			+ "	TA.NOMBRE AS ARCHIVO_TIPO, "
			+ "	NVL(TO_CHAR(A.FECHA_EMISION,'DD/MM/YYYY'), ' ') AS FECHA_EMISION, "
			+ "	NVL(TO_CHAR(A.FECHA_IMPOSICION,'DD/MM/YYYY'), ' ') AS FECHA_IMPOSICION, "
			+ "	NVL(TO_CHAR(TRUNC (A.FECHA_COMPLEMENTADO, 'dd'),'dd/mm/yyyy'),' ') AS FECHA_COMPLEMENTACION, "
			+ "	NVL(TO_CHAR(TRUNC (A.FECHA_LIBERACION, 'dd'),'dd/mm/yyyy'),' ') AS FECHA_LIBERACION, "
		    + "	(CASE " 
			+ "		WHEN (A.FECHA_IMPOSICION IS NOT NULL) "
			+ "			THEN to_char(trunc(A.FECHA_IMPOSICION,'DD') - TRUNC(SYSDATE,'DD')) "
			+ "		ELSE 'NA' " 
			+ "	END) AS DIA_LIBERACION, "
			+ "	A.ANIO_SALARIO_MINIMO AS SALARIO_MINIMO, "
			+ "	A.INFRAC_NUM_INICIAL AS INFRAC_NUM_INICIAL, "
			+ "	A.INFRAC_NUM_FINAL AS INFRAC_NUM_FINAL,"
			+ "	(CASE"
			+ "		WHEN (A.ESTATUS_PROCESO_ID = 29 OR A.ESTATUS_PROCESO_ID = 30 OR A.ESTATUS_PROCESO_ID = 31"
			+ "		OR A.ESTATUS_PROCESO_ID = 32 OR A.ESTATUS_PROCESO_ID = 33)"
			+ "			THEN 0 "
			+ "		ELSE 1 "
			+ "	END) AS COMPLEMENTADO, "
			+ "	A.CANTIDAD_PROCESADOS, "
			+ "	A.ESTATUS_PROCESO_ID AS ESTATUS_PROCESO_ID,"
			+ "	A.CANTIDAD_CANCELADOS, "
			+ "	A.ARCHIVO_COMPLEMENTADAS, "
			+ "	A.ARCHIVO_RECHAZADAS, "
			+ "	A.EN_PROCESO, "
			+ "	A.ORIGEN_LOTE, "
			+ " (case when (SELECT COUNT(RBP.RADAR_ARCHIVO_ID) FROM RADAR_BITACORA_PROCESO RBP WHERE RBP.ESTATUS_PROCESO_ID = 29 AND RBP.RADAR_ARCHIVO_ID = A.ID) = 0 " 
			+ "		then -1 " 
			+ "		else A.ST_ENVIADO " 
			+ "	end) AS ENVIAR "
			+ "FROM radar_archivo_total a "
			+ "	INNER JOIN radar_estatus_proceso ep ON (ep.id = a.estatus_proceso_id)"
			+ "	JOIN RADAR_CAT_ARCHIVO_TIPO ta ON a.archivo_tipo = ta.archivo_tipo_id "
			+ " LEFT JOIN FOTOMULTA_LOTES FT ON FT.LOTE_ID = A.ID "
			+ "WHERE "
//			+ "	1 = (CASE "
//			+ "		WHEN a.archivo_tipo != 3 "
//			+ "			THEN 1 "
//			+ "		WHEN ft.en_creacion = 0 "
//			+ "			THEN 1 "
//			+ "	END) "
//			+ "	AND "
			+ "	(CASE " 
			+ "		WHEN (#{tipoArchivo} != 0) AND  a.archivo_tipo= #{tipoArchivo} " 
			+ "			THEN  1 "
			+ "		WHEN (#{tipoArchivo} = 0 ) AND a.archivo_tipo = a.archivo_tipo "
			+ "			THEN 1 "
			+ "	END) = 1 " 
			+ "	AND "
			+ "	(CASE "
			+ "		WHEN ( #{origenProceso} = 0) AND a.origen_lote = 0 "
			+ "			THEN 1 "
			+ "		WHEN ( #{origenProceso} = 1 ) AND a.origen_lote = 1 "
			+ "			THEN 1 "
			+ "		WHEN ( #{origenProceso} = -1 )"
			+ "			THEN 1 "
			+ "	END) = 1 "
			+ " AND a.CANCELADO = 0 "
			+ "ORDER BY a.id desc";
	

//	String GET_ARCHIVO_BY_ID = "SELECT a.id AS archivoId, "
//
//			+ "a.nombre AS fileName, "
//			+ "a.fecha_emision AS fechaEmision, "
//			+ "a.estatus_proceso_id AS estatusProcesoId, "
//			+ "ep.nombre AS estatusProceso, "
//			+ "a.en_proceso AS enProceso, "
//			+ "a.modificado_por AS modificadoPorId, "
//			+ "a.ultima_modificacion AS ultimaModificacion, "
//			+ "a.anio_salario_minimo as anioEjercicio, "
//			+ "a.archivo_tipo as archivotipo, "
//			+ "a.archivo_tipo_proceso AS archivoTipoProceso "
//			+ "FROM radar_archivo a "
//			+ "INNER JOIN radar_estatus_proceso ep ON (ep.id = a.estatus_proceso_id) "
//			+ "WHERE a.id= #{archivoId}";
	
	String GET_ARCHIVO_BY_ID = 
			"SELECT "
			+ "RAT.id AS archivoId, "
			+ "RAT.nombre AS fileName, "
			+ "RAT.fecha_emision AS fechaEmision, "
			+ "RAT.estatus_proceso_id AS estatusProcesoId, "
			+ "ep.nombre AS estatusProceso, "
			+ "RAT.en_proceso AS enProceso, "
			+ "RAT.modificado_por AS modificadoPorId, "
			+ "RAT.ultima_modificacion AS ultimaModificacion, "
			+ "RAT.anio_salario_minimo as anioEjercicio, "
			+ "RAT.archivo_tipo as archivotipo, "
			+ "RAT.archivo_tipo_proceso AS archivoTipoProceso "
			//+ "FROM radar_archivo a "
			+ "FROM RADAR_ARCHIVO_TOTAL RAT "
			+ "INNER JOIN radar_estatus_proceso ep ON (ep.id = RAT.estatus_proceso_id) "
			+ "WHERE RAT.id= #{archivoId}";
	

	String insertRadarBitacoraProceso = "INSERT INTO radar_bitacora_proceso (radar_archivo_id, estatus_proceso_id, fecha, creado_por)"
			+ " VALUES (#{archivoId}, #{archivoListoParaLiberar}, systimestamp, #{empId})";
	
	String cargarArchivoById = "SELECT a.id AS archivoId, "
					+ "a.nombre AS fileName, "
					+ "a.fecha_emision AS fechaEmision, "
					+ "a.estatus_proceso_id AS estatusProcesoId, "
					+ "ep.nombre AS estatusProceso, "
					+ "a.en_proceso AS enProceso, "
					+ "a.modificado_por AS modificadoPorId, "
					+ "a.ultima_modificacion AS ultimaModificacion, "
					+ "a.anio_salario_minimo as anioEjercicio, "
					+ "a.archivo_tipo as archivoTipo,"
					+ "a.archivo_tipo_proceso as archivoTipoProceso"
					+ "FROM radar_archivo a "
					+ "INNER JOIN radar_estatus_proceso ep ON (ep.id = a.estatus_proceso_id) "
					+ "WHERE a.id = #{archivoId}";

//	String insertRadarBitacoraProceso = "INSERT INTO radar_bitacora_proceso (radar_archivo_id, estatus_proceso_id, fecha, creado_por)"
//			+ " VALUES (#{archivoId}, #{archivoListoParaLiberar}, systimestamp, #{empId})";
//	
//	String updateRadarArchivo = "UPDATE radar_archivo SET "
//			+ "estatus_proceso_id = #{archivoListoParaLiberar}, "
//			+ "en_proceso = #{enProceso}, "
//			+ "modificado_por = #{empId}, "
//			+ "ultima_modificacion = systimestamp "
//			+ "WHERE id = #{archivoId}";



	
	String UPDATE_RADAR_ARCHIVO_EXC = "UPDATE radar_archivo SET "
					+ " archivo_complementadas = #{pGenerar}, ultima_modificacion = SYSDATE "
					+ " WHERE id = #{archivoId}";
	
	String ListDeteccionesComplementadasVO = "SELECT d.placa AS placa, "
			+ "d.fecha AS fecha, "
			+ "d.hora AS hora, "
			+ "d.tdskuid AS tdskuid, "
			+ "d.ut AS ut, "
			+ "d.velocidad_detectada AS velocidad_detectada, "
			+ "nvl(d.apellido_paterno, ' ') || ' ' || nvl(d.apellido_materno, ' ') || ' ' || nvl(d.nombre, ' ') AS nombre, "
			+ "nvl(d.calle,' ')  || ' ' || nvl(d.num_exterior,' ') || ' ' || nvl(d.num_interior,' ') || ' ' || nvl(d.colonia,' ') AS domicilio, "
			+ "nvl(d.codigo_postal, ' ') as codigo_postal, "
			+ "nvl(d.municipio, ' ') as municipio, "
			+ "nvl(d.entidad_federativa, ' ') as entidad_federativa, "
			+ "nvl(d.telefono, ' ') as telefono, "
			+ "nvl(d.marca, ' ') as marca, "
			+ "nvl(d.submarca, ' ') as submarca, "
			+ "nvl(d.modelo, ' ') as modelo, "
			+ "nvl(d.serie, ' ') as serie, "
			+ "nvl(d.motor, ' ') as motor, "
			+ "d.infrac_num AS infrac_num, "
			+ "d.dias AS dias, "
			+ "d.porcentaje_descuento AS porcentaje_descuento, "
			+ "to_char(a.fecha_emision, 'dd/mm/yyyy') AS fecha_emision, "
			+ "to_char(d.fecha_imposicion, 'dd/mm/yyyy') AS fecha_imposicion, "
			+ "to_char(d.fecha_vencimiento, 'dd/mm/yyyy') AS fecha_vencimiento, "
			+ "d.importe_infraccion AS importe_infraccion, "
			+ "d.importe_descuento AS importe_descuento, "
			+ "d.importe_total AS importe_total, "
			+ "d.linea_captura AS linea_captura, "
			+ "trim(to_char(nvl(centro_reparto, 0), '00009')) AS centro_reparto, "
			+ "nvl(d.clave_pago, ' ') AS clave_pago, "
			+ "nvl(d.ut_calle, ' ') AS calle, "
			+ "nvl(d.ut_entre_calle, ' ') AS entre_calle, "
			+ "nvl(d.ut_y_calle, ' ') AS y_calle, "
			+ "nvl(d.ut_sector, ' ') AS sector, "
			+ "nvl(d.ut_delegacion, ' ') AS delegacion, "
			+ "oficial_placa, "
			+ "oficial_nombre "
			+ "FROM radar_archivo a "
			+ "INNER JOIN (SELECT * FROM RADAR_ARCHIVO_DETALLE "
			+ "	           UNION "
			+ "	           SELECT * FROM RADAR_ARCHIVO_DETALLE_HIST) d ON (d.radar_archivo_id = a.id) "
			+ "WHERE d.activo = 1 " + "AND d.complementado = 1 "
			+ "AND d.radar_archivo_id = #{archivoId} "
			+ "ORDER BY d.centro_reparto,d.codigo_postal,d.placa";
	
	String ListDeteccionesRechazadasVO = "SELECT d.placa AS placa, "
			+ "d.fecha AS fecha, "
			+ "d.hora AS hora, "
			+ "d.tdskuid AS tdskuid, "
			+ "d.ut AS ut, "
			+ "d.velocidad_detectada AS velocidad_detectada, "
			+ "nvl(d.apellido_paterno, ' ') || ' ' || nvl(d.apellido_materno, ' ') || ' ' || nvl(d.nombre, ' ') AS nombre, "
			+ "nvl(d.calle,' ')  || ' ' || nvl(d.num_exterior,' ') || ' ' || nvl(d.num_interior,' ') || ' ' || nvl(d.colonia,' ') AS domicilio, "
			+ "nvl(d.codigo_postal, ' ') as codigo_postal, "
			+ "nvl(d.municipio, ' ') as municipio, "
			+ "nvl(d.entidad_federativa, ' ') as entidad_federativa, "
			+ "nvl(d.telefono, ' ') as telefono, "
			+ "nvl(d.marca, ' ') as marca, "
			+ "nvl(d.submarca, ' ') as submarca, "
			+ "nvl(d.modelo, ' ') as modelo, "
			+ "nvl(d.serie, ' ') as serie, "
			+ "nvl(d.motor, ' ') as motor, "
			+ "nvl(d.infrac_num, ' ') AS infrac_num, "
			+ "d.dias AS dias, "
			+ "d.porcentaje_descuento AS porcentaje_descuento, "
			+ "nvl(to_char(a.fecha_emision, 'dd/mm/yyyy'), ' ') AS fecha_emision, "
			+ "nvl(to_char(d.fecha_imposicion, 'dd/mm/yyyy'), ' ') AS fecha_imposicion, "
			+ "nvl(to_char(d.fecha_vencimiento, 'dd/mm/yyyy'), ' ') AS fecha_vencimiento, "
			+ "d.importe_infraccion AS importe_infraccion, "
			+ "d.importe_descuento AS importe_descuento, "
			+ "d.importe_total AS importe_total, "
			+ "nvl(d.linea_captura, ' ') AS linea_captura, "
			+ "trim(to_char(nvl(centro_reparto, 0), '00009')) AS centro_reparto, "
			+ "nvl(d.clave_pago, ' ') AS clave_pago, "
			+ "nvl(d.ut_calle, ' ') AS calle, "
			+ "nvl(d.ut_entre_calle, ' ') AS entre_calle, "
			+ "nvl(d.ut_y_calle, ' ') AS y_calle, "
			+ "nvl(d.ut_sector, ' ') AS sector, "
			+ "nvl(d.ut_delegacion, ' ') AS delegacion, "
			+ "CASE "
			+ "WHEN d.duplicado = 1 THEN 'Registro duplicado' "
			+ "WHEN d.sin_datos_setravi = 1 THEN 'No existe información en SETRAVI' "
			+ "WHEN d.sin_centro_reparto = 1 THEN 'No tiene centro de reparto' "
			+ "WHEN d.sin_lc = 1 THEN 'No se asignó Línea de Captura' "
			+ "WHEN d.sin_ut = 1 THEN 'No tiene UT' "
			+ "WHEN d.proceso_cancelado = 1 THEN 'Proceso cancelado por el usuario' "
			+ "ELSE ' ' "
			+ "END AS motivo, "
			+ "oficial_placa, "
			+ "oficial_nombre "
			+ "FROM radar_archivo a "
			+ "INNER JOIN (SELECT * FROM RADAR_ARCHIVO_DETALLE "
			+ "	           UNION "
			+ "	           SELECT * FROM RADAR_ARCHIVO_DETALLE_HIST) d ON (d.radar_archivo_id = a.id) "
			+ "WHERE d.activo = 0 " + "AND d.radar_archivo_id = #{archivoId} "
			+ "ORDER BY d.id";

	/*String CANCELAR_FOTOMULTA_DETECCIONES = "UPDATE FOTOMULTA_DETECCIONES SET PROCESADO = 0, LOTE_ID = 0 "+
												"WHERE LOTE_ID = #{lote}";*/
	
	/*String RECICLAR_FOLIOS_INFRACCIONES = "INSERT INTO RADAR_ASIGNAFOLIO_TEMP (LOTE_ID, ARCHIVO_TIPO_ID, PLACA, TDSKUID,INFRACCION) "+
											"SELECT #{archivoId}, #{archivoTipo}, PLACA, TDSKUID, INFRAC_NUM "+
												"FROM RADAR_ARCHIVO_DETALLE "+
													"WHERE RADAR_ARCHIVO_ID = #{archivoId} "+
														"AND INFRAC_NUM IS NOT NULL";*/
	
	/*String UPDATE_TO_CANCEL_RADAR_ARCHIVO = "UPDATE RADAR_ARCHIVO SET ESTATUS_PROCESO_ID = #{estatusProcesoId}, EN_PROCESO = 0, CANCELADO = 1, "
									+ "MODIFICADO_POR = #{modificadoPor}, CANTIDAD_CANCELADOS = #{cantidadCancelados}, CANTIDAD_PROCESADOS = #{cantidadProcesados}, "
										+ "ULTIMA_MODIFICACION = SYSDATE, MOTIVO_CANCELACION = #{motivo}" + " WHERE ID = #{archivoId}";*/
	
	/*String UPDATE_TO_CANCEL_RADAR_ARCHIVO_DETALLE = "UPDATE radar_archivo_detalle SET activo = 0, proceso_cancelado = 1, "
														+ "complementado = 0 WHERE radar_archivo_id = #{archivoId}";*/
	
	//Insertar en Bitacora esta en RadarBitacoraProcesoMyBatisDAO
	
	/*String INSERT_RADAR_ARCHIVO_DETALLE_HISTORIAL = "INSERT INTO RADAR_ARCHIVO_DETALLE_HIST"
					+ "("
					+ "ID,RADAR_ARCHIVO_ID,PLACA,FECHA,HORA,TDSKUID,UT,VELOCIDAD_DETECTADA,FECHA_CREACION,PLACA_SF,NOMBRE,APELLIDO_PATERNO,APELLIDO_MATERNO,"
					+ "CALLE,NUM_EXTERIOR,NUM_INTERIOR,COLONIA,MUNICIPIO,CODIGO_POSTAL,ENTIDAD_FEDERATIVA,MARCA,SUBMARCA,MODELO,TELEFONO,SERIE,MOTOR,"
					+ "WS_SETRAVI_CONSULTADO,ERROR_WS_SETRAVI,FECHA_CONSULTA_WS_SETRAVI,CENTRO_REPARTO,CP_MODIFICADO_POR,FECHA_CP_MODIFICADO,INFRAC_NUM,"
					+ "DIAS,PORCENTAJE_DESCUENTO,FECHA_IMPOSICION,FECHA_VENCIMIENTO,IMPORTE_DESCUENTO,IMPORTE_INFRACCION,IMPORTE_DERECHOS,IMPORTE_ACTUALIZACION,"
					+ "IMPORTE_RECARGOS,IMPORTE_CEROS,IMPORTE_TOTAL,LINEA_CAPTURA,CLAVE_PAGO,COMPLEMENTADO_LC,FECHA_COMPLEMENTADO_LC,UT_CALLE,UT_ENTRE_CALLE,"
					+ "UT_Y_CALLE,UT_SECTOR,UT_DELEGACION,UT_COLONIA,UT_SECTOR_ID,UT_DELEGACION_ID,COMPLEMENTADO_UT,FECHA_COMPLEMENTADO_UT,COMPLEMENTADO,FECHA_COMPLEMENTADO,"
					+ "ACTIVO,DUPLICADO,SIN_DATOS_SETRAVI,SIN_CENTRO_REPARTO,SIN_LC,SIN_UT,PROCESO_CANCELADO,ART_ID,OFICIAL_PLACA,OFICIAL_NOMBRE,LIBERADO,FECHA_LIBERADO"
					+ ") "
					+ "SELECT " 
					+ "ID,RADAR_ARCHIVO_ID,PLACA,FECHA,HORA,TDSKUID,UT,VELOCIDAD_DETECTADA,FECHA_CREACION,PLACA_SF,NOMBRE,APELLIDO_PATERNO,APELLIDO_MATERNO,"
					+ "CALLE,NUM_EXTERIOR,NUM_INTERIOR,COLONIA,MUNICIPIO,CODIGO_POSTAL,ENTIDAD_FEDERATIVA,MARCA,SUBMARCA,MODELO,TELEFONO,SERIE,MOTOR,"
					+ "WS_SETRAVI_CONSULTADO,ERROR_WS_SETRAVI,FECHA_CONSULTA_WS_SETRAVI,CENTRO_REPARTO,CP_MODIFICADO_POR,FECHA_CP_MODIFICADO,INFRAC_NUM,"
					+ "DIAS,PORCENTAJE_DESCUENTO,FECHA_IMPOSICION,FECHA_VENCIMIENTO,IMPORTE_DESCUENTO,IMPORTE_INFRACCION,IMPORTE_DERECHOS,IMPORTE_ACTUALIZACION,"
					+ "IMPORTE_RECARGOS,IMPORTE_CEROS,IMPORTE_TOTAL,LINEA_CAPTURA,CLAVE_PAGO,COMPLEMENTADO_LC,FECHA_COMPLEMENTADO_LC,UT_CALLE,UT_ENTRE_CALLE,"
					+ "UT_Y_CALLE,UT_SECTOR,UT_DELEGACION, UT_COLONIA,UT_SECTOR_ID,UT_DELEGACION_ID,COMPLEMENTADO_UT,FECHA_COMPLEMENTADO_UT,COMPLEMENTADO,FECHA_COMPLEMENTADO,"
					+ "ACTIVO,DUPLICADO,SIN_DATOS_SETRAVI,SIN_CENTRO_REPARTO,SIN_LC,SIN_UT,PROCESO_CANCELADO,ART_ID,OFICIAL_PLACA,OFICIAL_NOMBRE,LIBERADO,FECHA_LIBERADO "
					+ "FROM RADAR_ARCHIVO_DETALLE "
					+ "WHERE RADAR_ARCHIVO_ID = #{archivoId}";*/
	
	
	/*String GET_TOTAL_DETECCIONES_CANCELADAS = "SELECT COUNT(*) as cantidad_canceladas FROM radar_archivo_detalle "
												+ "WHERE radar_archivo_id = #{archivoId} AND activo = 0";
	
	String GET_TOTAL_DETECCIONES_PROCESADAS = "SELECT COUNT(*) as cantidad_procesadas FROM radar_archivo_detalle "
												+ "WHERE   radar_archivo_id = #{archivoId} AND activo = 1 ";*/
	
	/*String DELETE_LOTE_CANCELADO = "DELETE FROM RADAR_ARCHIVO_DETALLE WHERE RADAR_ARCHIVO_ID = #{archivoId}";*/
	/*Termina Querys para Cancelar Archivo*/
	
	
	
	String BUSCA_DETALLE_INFRACCIONES_LIBERADAS = 
			"SELECT "
			+ "	min(infrac_num) folioInicial, "
			+ "	max(infrac_num) folioFinal, "
			+ "	count(1) total FROM radar_archivo_detalle_hist "
			+ "WHERE "
			+ "	activo = 1 "
			+ "	AND liberado = 1 "
			+ "	AND radar_archivo_id= #{archivoId}";
	
	
	/**/
	
	String BUSCA_ID_TIPO_DETECCION = "SELECT ID_TIPO_DETECCION FROM TAI007C_FM_TIPO_DETECCIONES WHERE ID_ARCHIVO_TIPO_FORA = #{idDeteccion} AND  ID_MARCA_DISPOSITIVO_DET = #{idMarca} ";
	/**/
	
	String BUSCA_ARCHIVO_EN_COMPLEMENTACION = 
		"SELECT "
		+ "	COUNT(EN_PROCESO) "
		//+ "FROM RADAR_ARCHIVO "
		+ "FROM radar_archivo_total a "
		+ "WHERE "
		+ "	EN_PROCESO = #{enProceso}"; 
	
	String GET_ORIGEN_BY_ID = 
	"SELECT "
	+ "	ORIGEN_LOTE "
	//+ "FROM RADAR_ARCHIVO "
	+ "FROM radar_archivo_total a "
	+ "WHERE "
	+ "	ID = #{idArchivo}";
	
	String ACTUALIZA_ARCHIVO_TOTAL_PARA_COMPLEMENTACION = 
		"UPDATE RADAR_ARCHIVO_TOTAL "
		+ "SET "
		+ "	EN_PROCESO = #{enProceso} "
		+ "WHERE "
		+ "	ID = #{radarArchivoId}";

	String ACTUALIZA_RADAR_ARCHIVO_PARA_COMPLEMENTACION = 
		"UPDATE RADAR_ARCHIVO "
		+ "SET "
		+ "	EN_PROCESO = #{enProceso} "
		+ "WHERE "
		+ "	ID = #{radarArchivoId}";

	String ACTUALIZA_FM_LOTE_PARA_COMPLEMENTACION = 
		"UPDATE TAI010D_FM_LOTE "
		+ "SET "
		+ "	EN_PROCESO = #{enProceso} "
		+ "WHERE "
		+ "	ID = #{radarArchivoId}";
	
	String BUSCA_DETALLE_INFRACCIONES_LIBERADAS_FM = 
		"SELECT "
		+ "	min(INFRAC_NUM) folioInicial, "
		+ "	max(INFRAC_NUM) folioFinal, "
		+ "	count(1) total "
		+ "FROM TAI012D_FM_DETALLE_HIST "
		+ "WHERE "
		+ "	activo = 1 "
		+ "	AND liberado = 1 "
		+ "	AND radar_archivo_id= #{archivoId}";
	
	

	@Select(GET_COMBO_ARCHIVO_PROCESADO)
	public List<RadarComboTipoArchivoVO> getArchivoProcesado();

	@Select(GET_COMBO_RADAR_PROCESADO)
	public List<RadarComboTipoArchivoVO> getRadarProcesado(
		@Param("tipoArchivo")Integer tipoArchivo);

	@Select(value = GET_ARCHIVOS_PROCESADOS)
	public List<ConsultaArchivosProcesadosVO> getArchivosProcesados(
		@Param("origenProceso") Integer origenProceso,
		@Param("tipoProceso") Integer tipoProceso,
		@Param("tipoDeteccion") Integer tipoDeteccion,
		@Param("tipoArchivo") Integer tipoArchivo,
		@Param("tipoPersona") Integer tipoPersona,
		@Param("tipoDeFecha") Integer  tipoFecha,
		@Param("fechaInicio") String fechaInicio,
		@Param("fechaFin") String fechaFin
	);
	
	
	@Select(value = GET_ARCHIVOS_PROCESADOS_ALL)
	public List<ConsultaArchivosProcesadosVO> getArchivosProcesadosAll(
		@Param("tipoArchivo") String tipoArchivo, 
		@Param("origenProceso") Integer origenProceso
	);
	
//	@Select(value = GET_ARCHIVOS_PROCESADOS)
//	public List<ConsultaArchivosProcesadosVO> getArchivosProcesados(
//			@Param("tipoArchivo") String tipoArchivo,
//			@Param("tipoDeFecha") Integer  tipoFecha,
//			@Param("fechaInicio") String fechaInicio,
//			@Param("fechaFin") String fechaFin,
//			@Param("tipoProces") String tipoProces
//			);
//	
//	
//	@Select(value = GET_ARCHIVOS_PROCESADOS_ALL)
//	public List<ConsultaArchivosProcesadosVO> getArchivosProcesadosAll(
//			@Param("tipoArchivo") String tipoArchivo			
//			);


	@Select(value =  GET_ARCHIVO_BY_ID)
	public ArchivoBatchFinanzasVO cargarArchivo(
			@Param("archivoId") String archivoId);

	/*@Select(value = insertRadarBitacoraProceso)
	public void setArchivoListoParaLiberarInsert(
			@Param("archivoListoParaLiberar") int archivoListoParaLiberar, 
			@Param("empId") Long empId, 			
			@Param("archivoId") String archivoId);*/

	
	/***/
	/*@Select(GET_TIPO_ARCHIVO)
	public String buscarTipoArchivo(@Param("lote") Long archivoId);*/
	

	/*@Update(CANCELAR_FOTOMULTA_DETECCIONES)
	public Integer cancelarFotomultaDetecciones(@Param("lote") Long archivoId);*/
	
	/*@Insert(RECICLAR_FOLIOS_INFRACCIONES)
	public Integer reciclarFoliosInfracciones(@Param("archivoId") Long archivoId, @Param("archivoTipo") Integer arhivoTipo);*/
	
	/*@Update(UPDATE_TO_CANCEL_RADAR_ARCHIVO)
	public Integer actualizarParaCancelarRadarArchivo(@Param("estatusProcesoId") Integer estatusProcesoId, @Param("modificadoPor") Long modificadoPor,
													  @Param("cantidadCancelados") Integer cantidadCancelados, @Param("cantidadProcesados") Integer cantidadProcesados,
													  @Param("motivo") String motivo, @Param("archivoId") Long archivoId);*/
	/*@Update(UPDATE_TO_CANCEL_RADAR_ARCHIVO_DETALLE)
	public Integer actualizarParaCancelarRadarArchivoDetalle(@Param("archivoId") Long archivoId);*/
	
	/*@Insert(INSERT_RADAR_ARCHIVO_DETALLE_HISTORIAL)
	public Integer insertarEnHistorialArchivoDetalle(@Param("archivoId") Long archivoId);*/

	@Select(value = GET_ARCHIVO_BY_ID)
	public ArchivoBatchFinanzasVO cargarArchivoById(
			@Param("archivoId") String archivoId);

//	@Select(value = updateRadarArchivo)
//	public void setArchivoListoParaLiberarUpdate(
//			@Param("archivoListoParaLiberar") int archivoListoParaLiberar, 
//			@Param("enProceso") int enProceso,
//			@Param("empId") Long empId, 			
//			@Param("archivoId") String archivoId);

	/*@Update(CANCELAR_FOTOMULTA_DETECCIONES)
	public Integer cancelarFotomultaDetecciones(@Param("lote") Long archivoId);*/
	
	/*@Insert(RECICLAR_FOLIOS_INFRACCIONES)
	public Integer reciclarFoliosInfracciones(@Param("archivoId") Long archivoId, @Param("archivoTipo") Integer arhivoTipo);*/
	
	/*@Update(UPDATE_TO_CANCEL_RADAR_ARCHIVO)
	public Integer actualizarParaCancelarRadarArchivo(@Param("estatusProcesoId") Integer estatusProcesoId, @Param("modificadoPor") Long modificadoPor,
													  @Param("cantidadCancelados") Integer cantidadCancelados, @Param("cantidadProcesados") Integer cantidadProcesados,
													  @Param("motivo") String motivo, @Param("archivoId") Long archivoId);*/
	/*@Update(UPDATE_TO_CANCEL_RADAR_ARCHIVO_DETALLE)
	public Integer actualizarParaCancelarRadarArchivoDetalle(@Param("archivoId") Long archivoId);*/
	
	/*@Insert(INSERT_RADAR_ARCHIVO_DETALLE_HISTORIAL)
	public Integer insertarEnHistorialArchivoDetalle(@Param("archivoId") Long archivoId);*/

	@Select(value = UPDATE_RADAR_ARCHIVO_EXC)
	public void updateRadarArchivo(
			@Param("tipoColumna") String tipoColumna, 
			@Param("pGenerar") int pGenerar, 
			@Param("archivoId") String archivoId);

//	@Select(GET_TOTAL_DETECCIONES_CANCELADAS)
//	public Integer buscarCantidadDeteccionesCanceladas(@Param("archivoId") Long archivoId);
//	
//	@Select(GET_TOTAL_DETECCIONES_PROCESADAS)
//	public Integer buscarCantidadDeteccionesProcesadas(@Param("archivoId") Long archivoId);*/
//	
//	/*@Delete(DELETE_LOTE_CANCELADO)
//	public Integer eliminarLoteCancelado(@Param("archivoId") Long archivoId);*/


	@Select(value = ListDeteccionesComplementadasVO)
	public List<DeteccionesComplementadasVO> listaDeteccionesComplementadas(
			@Param("archivoId") String archivoId);

	@Select(value = ListDeteccionesRechazadasVO)
	public List<DeteccionesComplementadasVO> listaDeteccionesRechazadas(
			@Param("archivoId") String archivoId);

//	@Select(GET_TOTAL_DETECCIONES_CANCELADAS)
//	public Integer buscarCantidadDeteccionesCanceladas(@Param("archivoId") Long archivoId);

//	@Select(GET_TOTAL_DETECCIONES_PROCESADAS)
//	public Integer buscarCantidadDeteccionesProcesadas(@Param("archivoId") Long archivoId);
	

	/*@Delete(DELETE_LOTE_CANCELADO)
	public Integer eliminarLoteCancelado(@Param("archivoId") Long archivoId);*/
	/***/

//	@Select(BUSCA_DETALLE_INFRACCIONES_LIBERADAS)
//	public DetalleInfraccionesLiberadasVO buscarDetalleInfraccionesLiberadas(@Param("archivoId") Long archivoId);
//	
	@Select(BUSCA_DETALLE_INFRACCIONES_LIBERADAS)
	public DetalleInfraccionesLiberadasVO buscarDetalleInfraccionesLiberadas(
		@Param("archivoId") Long archivoId
	);
	
	@Select(BUSCA_ID_TIPO_DETECCION)
	public Integer buscarIdTipoDeteccion(
		@Param("idDeteccion")String idDeteccion, 
		@Param("idMarca")String idMarca
	);
	
	@Select(BUSCA_ARCHIVO_EN_COMPLEMENTACION)
	public Integer buscarArchivoEnComplementacion(
		@Param("enProceso") Integer enProceso
	);
	
	@Select(GET_ORIGEN_BY_ID)
	public Integer buscarOrigenById(
		@Param("idArchivo") Long idArchivo
	);
	
	@Update(ACTUALIZA_ARCHIVO_TOTAL_PARA_COMPLEMENTACION)
	Integer actualizaRadarTotalParaComplementacion(
		@Param("radarArchivoId") Long radarArchivoId, 
		@Param("enProceso") Integer enProceso
	);
	
	@Update(ACTUALIZA_RADAR_ARCHIVO_PARA_COMPLEMENTACION)
	Integer actualizaRadarArchivoParaComplementacion(
		@Param("radarArchivoId") Long radarArchivoId, 
		@Param("enProceso") Integer enProceso
	);
	
	@Update(ACTUALIZA_FM_LOTE_PARA_COMPLEMENTACION)
	Integer actualizaFMLoteParaComplementacion(
		@Param("radarArchivoId") Long radarArchivoId, 
		@Param("enProceso") Integer enProceso
	);
	
	@Select(BUSCA_DETALLE_INFRACCIONES_LIBERADAS_FM)
	public DetalleInfraccionesLiberadasVO buscarDetalleInfraccionesLiberadasFM(
		@Param("archivoId") Long archivoId
	);
	
}
