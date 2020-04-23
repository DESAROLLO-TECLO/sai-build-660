package mx.com.teclo.saicdmx.persistencia.mybatis.dao.radararchivo;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import mx.com.teclo.saicdmx.persistencia.vo.radares.DeteccionesComplementadasVO;
import mx.com.teclo.saicdmx.persistencia.vo.radares.RadarArchivoEnComplementacionVO;
import mx.com.teclo.saicdmx.persistencia.vo.radares.RadarArchivoEstatusVO;
import mx.com.teclo.saicdmx.persistencia.vo.radares.RadarArchivoVO;

@Mapper
public interface RadarArchivoMyBatisDAO {
	
	String CASTING_CONCESIONADOS = "SELECT VALOR FROM RADAR_CAST_CONCESIONADO " 
						  		 + "WHERE PARAMETRO = #{parametro} " 
						  		 + "AND TIPO = #{tipo}";
	
	String ASIGNAR_ARTICULO = "SELECT ART_ID FROM ARTICULOS_INFRAC_FINANZAS "
							+ "WHERE ARTICULO = #{artNumero} " 
							+ "AND FRACCION = #{artFraccion} " 
							+ "AND PARRAFO = #{artParrafo} " 
							+ "AND INCISO = #{artInciso} " 
							+ "AND SALARIO = #{artUnidadCuenta} "
							+ "AND TO_DATE(#{fecha}, 'dd.MM.YY') BETWEEN FECHA_INICIO AND FECHA_TERMINO";
	
	String BUSCA_ARCHIVO_ENPROCESO = 
			"SELECT	COUNT(*) CANTIDAD "+ 
			"FROM 	RADAR_ARCHIVO "+
			"WHERE 	EN_PROCESO = 1 " + 
			"AND 	CANCELADO = 0";
	
	String BUSCA_ARCHIVO_EN_COMPLEMENTACION = "SELECT  COUNT(EN_PROCESO) "+
		    "FROM RADAR_ARCHIVO "+
		    "WHERE EN_PROCESO = #{enProceso}"; 
	
	
	String INCREMENTA_SECUENCIA_RA = 
			"SELECT RADAR_ARCHIVO_SEQ.NEXTVAL AS ARCHIVO_ID FROM DUAL";
	
	String INSERT_RADAR_ARCHIVO = 
			"INSERT INTO RADAR_ARCHIVO (ID, NOMBRE, FECHA_EMISION, ESTATUS_PROCESO_ID, EN_PROCESO, CREADO_POR, "+
			"	MODIFICADO_POR, ULTIMA_MODIFICACION,ANIO_SALARIO_MINIMO,ARCHIVO_TIPO,ARCHIVO_TIPO_PROCESO,SALARIOS_MINIMOS_ID, RESPONSABLE_PROCESO,FECHA_IMPOSICION) "+
			"VALUES (#{radarArchivoId}, #{archivoNombre}, #{fechaEmision}, #{estatusProcesoId}, #{enProceso}, #{empleadoId}, "+
			"	#{empleadoId}, #{fehcaUltimaModificacion}, #{anioSalario}, #{archivoTipoId}, #{archivoTipoProcesoId}, #{IdSalarioMin}, #{responsableProceso}, #{fechaImposicion})";
	String INSERT_RADAR_ARCHIVO_TOTAL = 
			"INSERT INTO RADAR_ARCHIVO_TOTAL (ID, NOMBRE, FECHA_EMISION, ESTATUS_PROCESO_ID, EN_PROCESO, ORIGEN_LOTE, ID_TIPO_DETECCION, CREADO_POR, "+
			"	MODIFICADO_POR, ULTIMA_MODIFICACION,ANIO_SALARIO_MINIMO,ARCHIVO_TIPO,ARCHIVO_TIPO_PROCESO,SALARIOS_MINIMOS_ID, RESPONSABLE_PROCESO,FECHA_IMPOSICION) "+
			"VALUES (#{radarArchivoId}, #{archivoNombre}, #{fechaEmision}, #{estatusProcesoId}, #{enProceso}, #{origenLote}, #{idTipoDeteccion}, #{empleadoId}, "+
			"	#{empleadoId}, #{fehcaUltimaModificacion}, #{anioSalario}, #{archivoTipoId}, #{archivoTipoProcesoId}, #{IdSalarioMin}, #{responsableProceso}, #{fechaImposicion})";
	
	String INSERT_RADAR_ARCHIVO_FROM_FOTOMULTAS = 
			"INSERT INTO RADAR_ARCHIVO (ID, NOMBRE, FECHA_EMISION, ESTATUS_PROCESO_ID, EN_PROCESO, CREADO_POR, "+
			"	MODIFICADO_POR, ULTIMA_MODIFICACION,ANIO_SALARIO_MINIMO,ARCHIVO_TIPO, ARCHIVO_TIPO_PROCESO,SALARIOS_MINIMOS_ID) "+
			"VALUES (#{radarArchivoId}, #{archivoNombre}, #{fechaEmision}, #{estatusProcesoId}, #{enProceso}, #{empleadoId}, "+
			"	#{empleadoId}, #{fehcaUltimaModificacion}, #{anioSalario}, #{archivoTipoId}, #{archivoTipoProcesoId}, #{IdSalarioMin})";
	
	String UPDATE_RADAR_ARCHIVO = "UPDATE radar_archivo SET "
			+ "estatus_proceso_id = #{archivoListoParaLiberar}, "
			+ "en_proceso = #{enProceso}, "
			+ "modificado_por = #{empId}, "
			+ "ultima_modificacion = systimestamp "
			+ "WHERE id = #{archivoId}";
	
	String UPDATE_RADAR_ARCHIVO_TOTAL = "UPDATE radar_archivo_total SET "
			+ "estatus_proceso_id = #{archivoListoParaLiberar}, "
			+ "en_proceso = #{enProceso}, "
			+ "modificado_por = #{empId}, "
			+ "ultima_modificacion = systimestamp "
			+ "WHERE id = #{archivoId}";
	
	
	String BUSCAR_ARCHIVO_EN_PROCESO ="${query}";
	
	String BUSCA_TIPO_ARCHIVO = 
			"SELECT "
			+ "	RCAT.NOMBRE AS NOMBRE "
			+ "FROM RADAR_ARCHIVO RA "
			+ "JOIN RADAR_CAT_ARCHIVO_TIPO RCAT ON RA.ARCHIVO_TIPO = RCAT.ARCHIVO_TIPO_ID "
			+ "WHERE "
			+ "	RA.ID = #{radarArchivoId}";
	
	String CANCELA_ARCHIVO = 
			"UPDATE RADAR_ARCHIVO "+
			"SET "
			+ "	ESTATUS_PROCESO_ID = #{estatusProceId}, " 
			+ "	EN_PROCESO = 0, "
			+ "	CANCELADO = 1, " 
			+ "	MODIFICADO_POR = #{empleadoId}, "
			+ "	CANTIDAD_CANCELADOS = #{cantidadCancelados}, "
			+ "	CANTIDAD_PROCESADOS = #{cantidadProcesados}, "
			+ "	ULTIMA_MODIFICACION = SYSDATE, "
			+ "	MOTIVO_CANCELACION= #{motivoCancelacion} "
			+ "WHERE "
			+ "	ID = #{archivoRadarId}";
	
	String CANCELA_ARCHIVO_TOTAL = 
			"UPDATE RADAR_ARCHIVO_TOTAL "+
			"SET 	ESTATUS_PROCESO_ID = #{estatusProceId}, " + "EN_PROCESO = 0, CANCELADO = 1, "+
			"		MODIFICADO_POR = #{empleadoId}, CANTIDAD_CANCELADOS = #{cantidadCancelados}, "+
			"		CANTIDAD_PROCESADOS = #{cantidadProcesados}, ULTIMA_MODIFICACION = SYSDATE, "+
			"		MOTIVO_CANCELACION= #{motivoCancelacion} "+ 
			"WHERE 	ID = #{archivoRadarId}";


	String ACTUALIZA_ARCHIVOS_PROCESADOS = "UPDATE RADAR_ARCHIVO SET CANTIDAD_PROCESADOS = #{cantidadProcesados} "+
			"WHERE ID = #{radarArchivoId}";
	
	String ACTUALIZA_ARCHIVO_PARA_COMPLEMENTACION = "UPDATE  RADAR_ARCHIVO   SET EN_PROCESO = #{enProceso} "+
													"WHERE   ID = #{radarArchivoId}";
	
	String BUSCA_RADAR_ARCHIVO_A_CANCELAR = 
			"SELECT DISTINCT "
			+	"a.id AS ARCHIVOID, "
			+	"a.nombre AS ARCHIVONOMBRE, "
			+	"a.archivo_tipo AS ARCHIVOTIPOID, "
			+	"ta.nombre AS ARCHIVOTIPO, "
			+	"a.archivo_tipo_proceso AS ARCHIVOTIPOPROCESOID, "
			+	"CASE "
			+		"WHEN a.archivo_tipo_proceso = 1 THEN 'Proceso Completo' "
			+		"WHEN a.archivo_tipo_proceso = 2 THEN 'Proceso Foráneo' "
			+		"ELSE 'Desconocido' "
			+	"END AS ARCHIVOTIPOPROCESONOMBRE, "
			+		"a.estatus_proceso_id AS ESTATUSPROCESOID, "
			+		"ep.nombre AS ESTATUSPROCESONOMBRE, "
			+		"a.cancelado, "
			+		"nvl(to_char(TRUNC (a.fecha_complementado, 'dd'),'dd/mm/yyyy'),' ') AS FECHACOMPLEMENTACION, "
			+		"nvl(to_char(TRUNC (a.fecha_liberacion, 'dd'),'dd/mm/yyyy'),' ') AS FECHALIBERACION,  "
			+		"to_char(TRUNC (fecha_emision, 'dd'),'dd/mm/yyyy') AS FECHAEMISION, "
			+		"TRUNC (a.ultima_modificacion, 'dd') AS FECHAMODIFICACION, "
			+		"a.anio_salario_minimo AS SALARIOMINIMO "
			+		"FROM radar_archivo a "
			+		"INNER JOIN radar_estatus_proceso ep "
			+		"ON (ep.id = a.estatus_proceso_id) "
			+		"JOIN RADAR_CAT_ARCHIVO_TIPO ta "
			+		"ON a.archivo_tipo = ta.archivo_tipo_id "
			+		"WHERE a.en_proceso = 0 and a.id = #{archivoId} "
			+		"ORDER BY a.id, FECHAEMISION DESC";
	
	String ListDeteccionesComplementadasVO = "SELECT d.placa AS placa, "
			+ "d.fecha AS fecha, "
			+ "d.hora AS hora, "
			+ "d.tdskuid AS tdskuid, "
			+ "d.ut AS ut, "
			+ "d.velocidad_detectada AS velocidad_detectada, "
			+ "trim(nvl(d.apellido_paterno, ' ') || ' ' || nvl(d.apellido_materno, ' ') || ' ' || nvl(d.nombre, ' ')) AS nombre, "
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
			+ "oficial_nombre ,"
			+ "d.CD_RFC_FIRMANTE, "
			+ "d.TX_CADENA_ORIGINAL, "
			+ "d.TX_FIRMA_ELETRONICA, "
			+ "(select CD_EMP_PLACA_FIRMANTE from TAI031D_FC_PARAMETROS_FIRMA where ID_LOTE = d.radar_archivo_id) as empCodFirmante, "
			+ "aif.DESCRIPCION as art_fundamentacion, "
			+ "aif.ARTICULO, "
			+ "aif.FRACCION, "
			+ "aif.INCISO, "
			+ "aif.PARRAFO "
			+ "FROM radar_archivo a "
			+ "INNER JOIN ("
			+ "				SELECT ID, RADAR_ARCHIVO_ID, PLACA, FECHA, HORA, TDSKUID, UT, VELOCIDAD_DETECTADA, FECHA_CREACION, "
			+ "					PLACA_SF, NOMBRE, APELLIDO_PATERNO, APELLIDO_MATERNO, CALLE, NUM_EXTERIOR, NUM_INTERIOR, COLONIA, "
			+ "					MUNICIPIO, CODIGO_POSTAL, ENTIDAD_FEDERATIVA, MARCA, SUBMARCA, MODELO, TELEFONO, SERIE, MOTOR, "
			+ "					WS_SETRAVI_CONSULTADO, ERROR_WS_SETRAVI, FECHA_CONSULTA_WS_SETRAVI, CENTRO_REPARTO, "
			+ "					CP_MODIFICADO_POR, FECHA_CP_MODIFICADO, INFRAC_NUM, DIAS, PORCENTAJE_DESCUENTO, FECHA_IMPOSICION, "
			+ "					FECHA_VENCIMIENTO, IMPORTE_DESCUENTO, IMPORTE_INFRACCION, IMPORTE_DERECHOS, IMPORTE_ACTUALIZACION, "
			+ "					IMPORTE_RECARGOS, IMPORTE_CEROS, IMPORTE_TOTAL, LINEA_CAPTURA, CLAVE_PAGO, COMPLEMENTADO_LC, "
			+ "					FECHA_COMPLEMENTADO_LC, UT_CALLE, UT_ENTRE_CALLE, UT_Y_CALLE, UT_SECTOR, UT_DELEGACION, "
			+ "					UT_SECTOR_ID, UT_DELEGACION_ID, COMPLEMENTADO_UT, FECHA_COMPLEMENTADO_UT, COMPLEMENTADO, "
			+ "					FECHA_COMPLEMENTADO, LIBERADO, FECHA_LIBERADO, ACTIVO, DUPLICADO, SIN_DATOS_SETRAVI, "
			+ "					SIN_CENTRO_REPARTO, SIN_LC, SIN_UT, PROCESO_CANCELADO, ART_ID, OFICIAL_PLACA, OFICIAL_NOMBRE, "
			+ "					UT_COLONIA, UT_EDO_ID, ART_NUMERO, ART_FRACCION, ART_PARRAFO, ART_INCISO, ART_UNIDAD_CUENTA, "
			+ "					ID_ARCHIVO_FC, CD_RFC_FIRMANTE, TX_CADENA_ORIGINAL, TX_FIRMA_ELETRONICA, NU_PUNTOSDESCNT, "
			+ "					TX_CORREO_ELECT, NU_LICENCIA, CD_SERIE_DISPOSITIVO, NU_VEL_MAX_PERMITIDA, ID_TIPO_DE_VIA, "
			+ "					NU_PORCNTAJ_VEL_EXCDIDO "
			+ "				FROM RADAR_ARCHIVO_DETALLE "
			+ "					UNION "
			+ "				SELECT ID, RADAR_ARCHIVO_ID, PLACA, FECHA, HORA, TDSKUID, UT, VELOCIDAD_DETECTADA, FECHA_CREACION, "
			+ "					PLACA_SF, NOMBRE, APELLIDO_PATERNO, APELLIDO_MATERNO, CALLE, NUM_EXTERIOR, NUM_INTERIOR, COLONIA, "
			+ "					MUNICIPIO, CODIGO_POSTAL, ENTIDAD_FEDERATIVA, MARCA, SUBMARCA, MODELO, TELEFONO, SERIE, MOTOR, "
			+ "					WS_SETRAVI_CONSULTADO, ERROR_WS_SETRAVI, FECHA_CONSULTA_WS_SETRAVI, CENTRO_REPARTO, "
			+ "					CP_MODIFICADO_POR, FECHA_CP_MODIFICADO, INFRAC_NUM, DIAS, PORCENTAJE_DESCUENTO, FECHA_IMPOSICION, "
			+ "					FECHA_VENCIMIENTO, IMPORTE_DESCUENTO, IMPORTE_INFRACCION, IMPORTE_DERECHOS, IMPORTE_ACTUALIZACION, "
			+ "					IMPORTE_RECARGOS, IMPORTE_CEROS, IMPORTE_TOTAL, LINEA_CAPTURA, CLAVE_PAGO, COMPLEMENTADO_LC, "
			+ "					FECHA_COMPLEMENTADO_LC, UT_CALLE, UT_ENTRE_CALLE, UT_Y_CALLE, UT_SECTOR, UT_DELEGACION, "
			+ "					UT_SECTOR_ID, UT_DELEGACION_ID, COMPLEMENTADO_UT, FECHA_COMPLEMENTADO_UT, COMPLEMENTADO, "
			+ "					FECHA_COMPLEMENTADO, LIBERADO, FECHA_LIBERADO, ACTIVO, DUPLICADO, SIN_DATOS_SETRAVI, "
			+ "					SIN_CENTRO_REPARTO, SIN_LC, SIN_UT, PROCESO_CANCELADO, ART_ID, OFICIAL_PLACA, OFICIAL_NOMBRE, "
			+ "					UT_COLONIA, UT_EDO_ID, ART_NUMERO, ART_FRACCION, ART_PARRAFO, ART_INCISO, ART_UNIDAD_CUENTA, "
			+ "					ID_ARCHIVO_FC, CD_RFC_FIRMANTE, TX_CADENA_ORIGINAL, TX_FIRMA_ELETRONICA, NU_PUNTOSDESCNT, "
			+ "					TX_CORREO_ELECT, NU_LICENCIA, CD_SERIE_DISPOSITIVO, NU_VEL_MAX_PERMITIDA, ID_TIPO_DE_VIA, "
			+ "					NU_PORCNTAJ_VEL_EXCDIDO "
			+ "					FROM RADAR_ARCHIVO_DETALLE_HIST"
			+ ") d ON (d.radar_archivo_id = a.id) "
			+ "INNER JOIN ARTICULOS_INFRAC_FINANZAS aif ON (d.ART_ID = aif.ART_ID) "
			+ "WHERE d.activo = 1 " + "AND d.complementado = 1 "
			+ "AND d.radar_archivo_id = #{archivoId} "
			+ "AND trunc(TO_DATE(d.fecha||' '||d.hora,'dd.MM.yy HH24:MI:SS')) " 
			+ "BETWEEN trunc(aif.FECHA_INICIO) AND trunc(AIF.FECHA_TERMINO) "
			+ "ORDER BY d.centro_reparto,d.codigo_postal,d.placa";
	
	String ListDeteccionesRechazadasVO = "SELECT d.placa AS placa, "
			+ "d.fecha AS fecha, "
			+ "d.hora AS hora, "
			+ "d.tdskuid AS tdskuid, "
			+ "d.ut AS ut, "
			+ "d.velocidad_detectada AS velocidad_detectada, "
			+ "trim(nvl(d.apellido_paterno, ' ') || ' ' || nvl(d.apellido_materno, ' ') || ' ' || nvl(d.nombre, ' ')) AS nombre, "
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
			+ "WHEN trim(NVL(d.OFICIAL_PLACA,'')) is null THEN 'Oficial placa vacio' "
			+ "WHEN d.sin_datos_setravi = 1 THEN 'No existe información en SETRAVI' "
			+ "WHEN d.sin_centro_reparto = 1 THEN 'No tiene centro de reparto' "
			+ "WHEN d.sin_lc = 1 THEN 'No se asigno Linea de Captura' "
			+ "WHEN d.sin_ut = 1 THEN 'No tiene UT' "
			+ "WHEN d.proceso_cancelado = 1 THEN 'Proceso cancelado por el usuario' "
			+ "WHEN d.OFICIAL_NOMBRE = '-3' THEN 'Placa vehicular incorrecta' "
			+ "ELSE ' ' "
			+ "END AS motivo, "
			+ "oficial_placa, "
			+ "DECODE(trim(d.OFICIAL_NOMBRE), '-3', '',trim(d.OFICIAL_NOMBRE)) AS oficial_nombre "
			+ "FROM radar_archivo a "
			+ "INNER JOIN ("
			+"				SELECT ID, RADAR_ARCHIVO_ID, PLACA, FECHA, HORA, TDSKUID, UT, VELOCIDAD_DETECTADA, FECHA_CREACION, "
			+ "					PLACA_SF, NOMBRE, APELLIDO_PATERNO, APELLIDO_MATERNO, CALLE, NUM_EXTERIOR, NUM_INTERIOR, COLONIA, "
			+ "					MUNICIPIO, CODIGO_POSTAL, ENTIDAD_FEDERATIVA, MARCA, SUBMARCA, MODELO, TELEFONO, SERIE, MOTOR, "
			+ "					WS_SETRAVI_CONSULTADO, ERROR_WS_SETRAVI, FECHA_CONSULTA_WS_SETRAVI, CENTRO_REPARTO, "
			+ "					CP_MODIFICADO_POR, FECHA_CP_MODIFICADO, INFRAC_NUM, DIAS, PORCENTAJE_DESCUENTO, FECHA_IMPOSICION, "
			+ "					FECHA_VENCIMIENTO, IMPORTE_DESCUENTO, IMPORTE_INFRACCION, IMPORTE_DERECHOS, IMPORTE_ACTUALIZACION, "
			+ "					IMPORTE_RECARGOS, IMPORTE_CEROS, IMPORTE_TOTAL, LINEA_CAPTURA, CLAVE_PAGO, COMPLEMENTADO_LC, "
			+ "					FECHA_COMPLEMENTADO_LC, UT_CALLE, UT_ENTRE_CALLE, UT_Y_CALLE, UT_SECTOR, UT_DELEGACION, "
			+ "					UT_SECTOR_ID, UT_DELEGACION_ID, COMPLEMENTADO_UT, FECHA_COMPLEMENTADO_UT, COMPLEMENTADO, "
			+ "					FECHA_COMPLEMENTADO, LIBERADO, FECHA_LIBERADO, ACTIVO, DUPLICADO, SIN_DATOS_SETRAVI, "
			+ "					SIN_CENTRO_REPARTO, SIN_LC, SIN_UT, PROCESO_CANCELADO, ART_ID, OFICIAL_PLACA, OFICIAL_NOMBRE, "
			+ "					UT_COLONIA, UT_EDO_ID, ART_NUMERO, ART_FRACCION, ART_PARRAFO, ART_INCISO, ART_UNIDAD_CUENTA, "
			+ "					ID_ARCHIVO_FC, CD_RFC_FIRMANTE, TX_CADENA_ORIGINAL, TX_FIRMA_ELETRONICA, NU_PUNTOSDESCNT, "
			+ "					TX_CORREO_ELECT, NU_LICENCIA, CD_SERIE_DISPOSITIVO, NU_VEL_MAX_PERMITIDA, ID_TIPO_DE_VIA, "
			+ "					NU_PORCNTAJ_VEL_EXCDIDO "
			+ "				FROM RADAR_ARCHIVO_DETALLE "
			+ "					UNION "
			+ "				SELECT ID, RADAR_ARCHIVO_ID, PLACA, FECHA, HORA, TDSKUID, UT, VELOCIDAD_DETECTADA, FECHA_CREACION, "
			+ "					PLACA_SF, NOMBRE, APELLIDO_PATERNO, APELLIDO_MATERNO, CALLE, NUM_EXTERIOR, NUM_INTERIOR, COLONIA, "
			+ "					MUNICIPIO, CODIGO_POSTAL, ENTIDAD_FEDERATIVA, MARCA, SUBMARCA, MODELO, TELEFONO, SERIE, MOTOR, "
			+ "					WS_SETRAVI_CONSULTADO, ERROR_WS_SETRAVI, FECHA_CONSULTA_WS_SETRAVI, CENTRO_REPARTO, "
			+ "					CP_MODIFICADO_POR, FECHA_CP_MODIFICADO, INFRAC_NUM, DIAS, PORCENTAJE_DESCUENTO, FECHA_IMPOSICION, "
			+ "					FECHA_VENCIMIENTO, IMPORTE_DESCUENTO, IMPORTE_INFRACCION, IMPORTE_DERECHOS, IMPORTE_ACTUALIZACION, "
			+ "					IMPORTE_RECARGOS, IMPORTE_CEROS, IMPORTE_TOTAL, LINEA_CAPTURA, CLAVE_PAGO, COMPLEMENTADO_LC, "
			+ "					FECHA_COMPLEMENTADO_LC, UT_CALLE, UT_ENTRE_CALLE, UT_Y_CALLE, UT_SECTOR, UT_DELEGACION, "
			+ "					UT_SECTOR_ID, UT_DELEGACION_ID, COMPLEMENTADO_UT, FECHA_COMPLEMENTADO_UT, COMPLEMENTADO, "
			+ "					FECHA_COMPLEMENTADO, LIBERADO, FECHA_LIBERADO, ACTIVO, DUPLICADO, SIN_DATOS_SETRAVI, "
			+ "					SIN_CENTRO_REPARTO, SIN_LC, SIN_UT, PROCESO_CANCELADO, ART_ID, OFICIAL_PLACA, OFICIAL_NOMBRE, "
			+ "					UT_COLONIA, UT_EDO_ID, ART_NUMERO, ART_FRACCION, ART_PARRAFO, ART_INCISO, ART_UNIDAD_CUENTA, "
			+ "					ID_ARCHIVO_FC, CD_RFC_FIRMANTE, TX_CADENA_ORIGINAL, TX_FIRMA_ELETRONICA, NU_PUNTOSDESCNT, "
			+ "					TX_CORREO_ELECT, NU_LICENCIA, CD_SERIE_DISPOSITIVO, NU_VEL_MAX_PERMITIDA, ID_TIPO_DE_VIA, "
			+ "					NU_PORCNTAJ_VEL_EXCDIDO "
			+ "					FROM RADAR_ARCHIVO_DETALLE_HIST"
			+ ") d ON (d.radar_archivo_id = a.id) "
			+ "WHERE d.activo = 0 " + "AND d.radar_archivo_id = #{archivoId} "
			+ "ORDER BY d.id";
	
	String UPDATE_RADAR_ARCHIVO_EXC = "UPDATE radar_archivo SET "
			+ " ARCHIVO_COMPLEMENTADAS = #{pGenerar}, ultima_modificacion = SYSDATE "
			+ " WHERE id = #{archivoId}";

	
	String BUSCA_ESTATUS_ARCHIVO_ZIP = "SELECT #{tipoBusqueda} AS RESULTADO"
										+ " FROM RADAR_ARCHIVO " + " WHERE ID = #{archivoId}";
	
	String IS_ARCHIVO_COMPLEMENTADO = "SELECT complementado from radar_archivo where id = #{archivoId}";
	
	String GET_ARCHIVO_ID = "select id from RADAR_ARCHIVO where rownum = 1 order by id desc";
	
	String IF_EXISTE_ID = "SELECT ID_REGISTRO from TAI_BITACORA_CAMBIOS where rownum = 1 and ID_REGISTRO = #{archivoId}";
	
	String GET_NOMBRE_ARCHIVO = "select nombre from RADAR_ARCHIVO where rownum = 1 order by id desc";
	
	String IS_ARCHIVO_CANCELADO = "SELECT cancelado from radar_archivo where id = #{archivoId}";
	
	String CANCELA_ARCHIVO_FM = 
			"UPDATE TAI010D_FM_LOTE "
			+ "SET "
			+ "	ESTATUS_PROCESO_ID = #{estatusProceId}, EN_PROCESO = 0, CANCELADO = 1, "
			+ "	MODIFICADO_POR = #{empleadoId}, CANTIDAD_CANCELADOS = #{cantidadCancelados}, "
			+ "	CANTIDAD_PROCESADOS = #{cantidadProcesados}, ULTIMA_MODIFICACION = SYSDATE, "
			+ "	MOTIVO_CANCELACION= #{motivoCancelacion} "
			+ "WHERE "
			+ "	ID = #{archivoRadarId}";
		
		String BUSCA_TIPO_ARCHIVO_FM = 
			"SELECT "
			+ "	RCAT.NOMBRE AS NOMBRE "
			+ "FROM TAI010D_FM_LOTE FML "
			+ "	JOIN RADAR_CAT_ARCHIVO_TIPO RCAT ON FML.ARCHIVO_TIPO = RCAT.ARCHIVO_TIPO_ID "
			+ "WHERE "
			+ "	FML.ID = #{radarArchivoId}";
		
		String BUSCA_RADAR_ARCHIVO_A_CANCELAR_FM = "SELECT DISTINCT "
				+	"a.id AS ARCHIVOID, "
				+	"a.nombre AS ARCHIVONOMBRE, "
				+	"a.archivo_tipo AS ARCHIVOTIPOID, "
				+	"ta.nombre AS ARCHIVOTIPO, "
				+	"a.archivo_tipo_proceso AS ARCHIVOTIPOPROCESOID, "
				+	"CASE "
				+		"WHEN a.archivo_tipo_proceso = 1 THEN 'Proceso Completo' "
				+		"WHEN a.archivo_tipo_proceso = 2 THEN 'Proceso Foráneo' "
				+		"ELSE 'Desconocido' "
				+	"END AS ARCHIVOTIPOPROCESONOMBRE, "
				+		"a.estatus_proceso_id AS ESTATUSPROCESOID, "
				+		"ep.nombre AS ESTATUSPROCESONOMBRE, "
				+		"a.cancelado, "
				+		"nvl(to_char(TRUNC (a.fecha_complementado, 'dd'),'dd/mm/yyyy'),' ') AS FECHACOMPLEMENTACION, "
				+		"nvl(to_char(TRUNC (a.fecha_liberacion, 'dd'),'dd/mm/yyyy'),' ') AS FECHALIBERACION,  "
				+		"to_char(TRUNC (fecha_emision, 'dd'),'dd/mm/yyyy') AS FECHAEMISION, "
				+		"TRUNC (a.ultima_modificacion, 'dd') AS FECHAMODIFICACION, "
				+		"a.anio_salario_minimo AS SALARIOMINIMO "
				+		"FROM TAI010D_FM_LOTE a "
				+		"INNER JOIN radar_estatus_proceso ep "
				+		"ON (ep.id = a.estatus_proceso_id) "
				+		"JOIN RADAR_CAT_ARCHIVO_TIPO ta "
				+		"ON a.archivo_tipo = ta.archivo_tipo_id "
				+		"WHERE a.en_proceso = 0 and a.id = #{archivoId} "
				+		"ORDER BY a.id, FECHAEMISION DESC";
		
		String BUSCA_ESTATUS_ARCHIVO_ZIP_FM = 
			"SELECT "
			+ "	#{tipoBusqueda} AS RESULTADO"
			+ "FROM TAI010D_FM_LOTE "
			+ "WHERE "
			+ "	ID = #{archivoId}";
		
		String IS_ARCHIVO_COMPLEMENTADO_FM = 
			"SELECT "
			+ "	complementado "
			+ "FROM TAI010D_FM_LOTE "
			+ "WHERE "
			+ "	id = #{archivoId}";
		
		String UPDATE_RADAR_ARCHIVO_EXC_FM = 
			"UPDATE "
			+ "	TAI010D_FM_LOTE "
			+ "SET "
			+ "	ARCHIVO_COMPLEMENTADAS = #{pGenerar}, "
			+ "	ultima_modificacion = SYSDATE "
			+ "WHERE id = #{archivoId}";
		
		String ListDeteccionesComplementadasVO_FM = 
			"SELECT "
			+ "	d.placa AS placa, "
			+ "	d.fecha AS fecha, "
			+ "	d.hora AS hora, "
			+ "	d.tdskuid AS tdskuid, "
			+ "	d.ut AS ut, "
			+ "	d.velocidad_detectada AS velocidad_detectada, "
			+ "	trim(nvl(d.apellido_paterno, ' ') || ' ' || nvl(d.apellido_materno, ' ') || ' ' || nvl(d.nombre, ' ')) AS nombre, "
			+ "	nvl(d.calle,' ')  || ' ' || nvl(d.num_exterior,' ') || ' ' || nvl(d.num_interior,' ') || ' ' || nvl(d.colonia,' ') AS domicilio, "
			+ "	nvl(d.codigo_postal, ' ') as codigo_postal, "
			+ "	nvl(d.municipio, ' ') as municipio, "
			+ "	nvl(d.entidad_federativa, ' ') as entidad_federativa, "
			+ "	nvl(d.telefono, ' ') as telefono, "
			+ "	nvl(d.marca, ' ') as marca, "
			+ "	nvl(d.submarca, ' ') as submarca, "
			+ "	nvl(d.modelo, ' ') as modelo, "
			+ "	nvl(d.serie, ' ') as serie, "
			+ "	nvl(d.motor, ' ') as motor, "
			+ "	d.infrac_num AS infrac_num, "
			+ "	d.dias AS dias, "
			+ "	d.porcentaje_descuento AS porcentaje_descuento, "
			+ "	to_char(a.fecha_emision, 'dd/mm/yyyy') AS fecha_emision, "
			+ "	to_char(d.fecha_imposicion, 'dd/mm/yyyy') AS fecha_imposicion, "
			+ "	to_char(d.fecha_vencimiento, 'dd/mm/yyyy') AS fecha_vencimiento, "
			+ "	d.importe_infraccion AS importe_infraccion, "
			+ "	d.importe_descuento AS importe_descuento, "
			+ "	d.importe_total AS importe_total, "
			+ "	d.linea_captura AS linea_captura, "
			+ "	trim(to_char(nvl(centro_reparto, 0), '00009')) AS centro_reparto, "
			+ "	nvl(d.clave_pago, ' ') AS clave_pago, "
			+ "	nvl(d.ut_calle, ' ') AS calle, "
			+ "	nvl(d.ut_entre_calle, ' ') AS entre_calle, "
			+ "	nvl(d.ut_y_calle, ' ') AS y_calle, "
			+ "	nvl(d.ut_sector, ' ') AS sector, "
			+ "	nvl(d.ut_delegacion, ' ') AS delegacion, "
			+ "	oficial_placa, "
			+ "	oficial_nombre "
			+ "FROM TAI010D_FM_LOTE a "
			+ "	INNER JOIN	(SELECT * FROM TAI011D_FM_DETALLE "
			+ "				UNION "
			+ "				SELECT * FROM TAI012D_FM_DETALLE_HIST"
			+ "				) d ON (d.radar_archivo_id = a.id) "
			+ "WHERE "
			+ "	d.activo = 1 "
			+ "	AND d.complementado = 1 "
			+ "	AND d.radar_archivo_id = #{archivoId} "
			+ "ORDER BY d.centro_reparto,d.codigo_postal,d.placa";
		
		String ListDeteccionesRechazadasVO_FM = "SELECT d.placa AS placa, "
				+ "d.fecha AS fecha, "
				+ "d.hora AS hora, "
				+ "d.tdskuid AS tdskuid, "
				+ "d.ut AS ut, "
				+ "d.velocidad_detectada AS velocidad_detectada, "
				+ "trim(nvl(d.apellido_paterno, ' ') || ' ' || nvl(d.apellido_materno, ' ') || ' ' || nvl(d.nombre, ' ')) AS nombre, "
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
				+ "WHEN trim(NVL(d.OFICIAL_PLACA,'')) is null THEN 'Oficial placa vacio' "
				+ "WHEN d.sin_datos_setravi = 1 THEN 'No existe información en SETRAVI' "
				+ "WHEN d.sin_centro_reparto = 1 THEN 'No tiene centro de reparto' "
				+ "WHEN d.sin_lc = 1 THEN 'No se asignó linea de Captura' "
				+ "WHEN d.sin_ut = 1 THEN 'No tiene UT' "
				+ "WHEN d.proceso_cancelado = 1 THEN 'Proceso cancelado por el usuario' "
				+ "WHEN d.OFICIAL_NOMBRE = '-3' THEN 'Placa vehicular incorrecta' "
				+ "ELSE ' ' "
				+ "END AS motivo, "
				+ "oficial_placa, "
				+ "DECODE(trim(d.OFICIAL_NOMBRE), '-3', '',trim(d.OFICIAL_NOMBRE)) AS oficial_nombre "
				+ "FROM TAI010D_FM_LOTE a "
				+ "INNER JOIN (SELECT * FROM TAI011D_FM_DETALLE "
				+ "	           UNION "
				+ "	           SELECT * FROM TAI012D_FM_DETALLE_HIST) d ON (d.radar_archivo_id = a.id) "
				+ "WHERE d.activo = 0 " + "AND d.radar_archivo_id = #{archivoId} "
				+ "ORDER BY d.id";
		
		String UPDATE_RADAR_ARCHIVO_FM = 
			"UPDATE TAI010D_FM_LOTE SET "
			+ "ESTATUS_PROCESO_ID = #{archivoListoParaLiberar}, "
			+ "EN_PROCESO = #{enProceso}, "
			+ "MODIFICADO_POR = #{empId}, "
			+ "ULTIMA_MODIFICACION = systimestamp "
			+ "WHERE id = #{archivoId}";
		
	String UPDATE_RADAR_ARCHIVO_EN_PROCESO = 
		"UPDATE RADAR_ARCHIVO SET "
		+ "EN_PROCESO = #{enProceso} "
		+ "WHERE id = #{archivoId}";
	
	String UPDATE_RADAR_ARCHIVO_TOTAL_EN_PROCESO = 
		"UPDATE RADAR_ARCHIVO SET "
		+ "EN_PROCESO = #{enProceso} "
		+ "WHERE id = #{archivoId}";
	

	String VALIDA_USUARIO_ACTIVO = 
		"SELECT COUNT(1) "
		+ "FROM EMPLEADOS "
		+ "WHERE "
		+ "	EMP_STATUS = 'A' "
		+ "	AND UPPER(EMP_PLACA) = UPPER(#{placaOficial})";
	

	String INSERT_PARAMETROS_FIRMA_RADARES = 
			"INSERT INTO TAI031D_FC_PARAMETROS_FIRMA "
			+ "(ID_LOTE, ID_EMP_FIRMANTE, CD_EMP_PLACA_FIRMANTE, CD_RFC_FIRMANTE, CD_LLAVE_PRIVADA) "
			+ "VALUES "
			+ "(#{radarArchivoId}, #{empId}, #{placaOficial}, #{empRFC}, PKG_ENCRIPCION.ENCRIPTA(#{pwd}))";
	/**
	 * @author Sail
	 * @return Integer
	 */
	@Select(CASTING_CONCESIONADOS)
	public Integer castingConcesionado(@Param("parametro") String parametro, @Param("tipo") String tipo);
	
	/**
	 * @author Sail
	 * @return Integer
	 */
	@Select(ASIGNAR_ARTICULO)
	public Integer asignarArticulo(@Param("artNumero") String artNumero, @Param("artFraccion") String artFraccion, 
			@Param("artParrafo") String artParrafo, @Param("artInciso") String artInciso, @Param("artUnidadCuenta") String artUnidadCuenta, 
			@Param("fecha") String fecha);
	
	/**
	 * @author UnitisDes0416
	 * @return Integer
	 */
	@Select(BUSCA_ARCHIVO_ENPROCESO)
	public Integer buscaArchivoEnProceso();
	
	/**
	 * @author UnitisDes0416
	 * @return Long
	 */
	@Select(INCREMENTA_SECUENCIA_RA)
	public Long obtenerSiguienteSecuencia();
	
	/**
	 * @author UnitisDes0416
	 * @param radarArchivoVO RadarArchivoVO
	 * @return Integer
	 */
	@Insert(INSERT_RADAR_ARCHIVO)
	public Integer insertaRadarArchivo(RadarArchivoVO radarArchivoVO);
	
	/**
	 * @author UnitisDes0416
	 * @param radarArchivoVO RadarArchivoVO
	 * @return Integer
	 */
	@Insert(INSERT_RADAR_ARCHIVO_TOTAL)
	public Integer insertaRadarArchivoTotal(RadarArchivoVO radarArchivoVO);
	
	
	/***
	 * @author Jesus Gutierrez
	 * @param radarArchivoVO
	 * @return Integer
	 */
	@Insert(INSERT_RADAR_ARCHIVO_FROM_FOTOMULTAS)
	public Integer insertRadarArchivoFromFotomultas(RadarArchivoVO radarArchivoVO);
	
	/**
	 * @author UnitisDes0416
	 * @return RadarArchivoEnComplementacionVO
	 */
	@Select(BUSCAR_ARCHIVO_EN_PROCESO)
	public RadarArchivoEnComplementacionVO buscarRadarArchivoEnProceso(
			@Param("query") String query);
	
	/**
	 * @author UnitisDes0416
	 * @param adarArchivoId Long
	 * @return String  
	 */
	@Select(BUSCA_TIPO_ARCHIVO)
	public String buscaArchivoTipo(@Param("radarArchivoId") Long radarArchivoId);
	
	@Insert(CANCELA_ARCHIVO)
	public Integer cancelaRadarArchivo(@Param("archivoRadarId") Long radarArchivoId, @Param("estatusProceId") Long estatusProceId,
			@Param("empleadoId") Long empleadoId, @Param("cantidadCancelados") Integer cantidadCancelados,
			@Param("cantidadProcesados") Integer cantidadProcesados, @Param("motivoCancelacion") String motivoCancelacion);
	
	@Update(CANCELA_ARCHIVO_TOTAL)
	public Integer cancelaRadarArchivoTotal(@Param("archivoRadarId") Long radarArchivoId, @Param("estatusProceId") Long estatusProceId,
			@Param("empleadoId") Long empleadoId, @Param("cantidadCancelados") Integer cantidadCancelados,
			@Param("cantidadProcesados") Integer cantidadProcesados, @Param("motivoCancelacion") String motivoCancelacion);
	
	
	@Select(value = UPDATE_RADAR_ARCHIVO_EXC)
	public void updateRadarArchivo(
			@Param("tipoColumna") String tipoColumna, 
			@Param("pGenerar") int pGenerar, 
			@Param("archivoId") String archivoId);
	
	@Select(value = UPDATE_RADAR_ARCHIVO)
	public void setArchivoListoParaLiberarUpdate(
			@Param("archivoListoParaLiberar") int archivoListoParaLiberar, 
			@Param("enProceso") int enProceso,
			@Param("empId") Long empId, 			
			@Param("archivoId") String archivoId);
	
	@Select(value = UPDATE_RADAR_ARCHIVO_TOTAL)
	public void setArchivoListoParaLiberarEnTotal(
			@Param("archivoListoParaLiberar") int archivoListoParaLiberar, 
			@Param("enProceso") int enProceso,
			@Param("empId") Long empId, 			
			@Param("archivoId") String archivoId);

	
	@Update(ACTUALIZA_ARCHIVOS_PROCESADOS)
	public Integer actualizaArchivosProcesados (@Param("radarArchivoId") Long radarArchivoId, @Param("cantidadProcesados") Integer cantidadProcesados);

	@Update(ACTUALIZA_ARCHIVO_PARA_COMPLEMENTACION)
	Integer actualizaArchivoParaComplementacion(@Param("radarArchivoId") Long radarArchivoId, @Param("enProceso") Integer enProceso);
	
	@Select(BUSCA_ARCHIVO_EN_COMPLEMENTACION)
	public Integer buscarArchivoEnComplementacion(@Param("enProceso") Integer enProceso);
	
	@Select(BUSCA_RADAR_ARCHIVO_A_CANCELAR)
	public RadarArchivoEstatusVO buscaRadarArchivoACancelar(@Param("archivoId") Long archivoId);
	
	@Select(value = ListDeteccionesComplementadasVO)
	public List<DeteccionesComplementadasVO> listaDeteccionesComplementadas(
			@Param("archivoId") String archivoId);

	@Select(value = ListDeteccionesRechazadasVO)
	public List<DeteccionesComplementadasVO> listaDeteccionesRechazadas(
			@Param("archivoId") String archivoId);
	

	
	@Select(IS_ARCHIVO_COMPLEMENTADO)
	public Integer isArchivoComplementado(@Param("archivoId") Long archivoId);
	
	@Select(GET_ARCHIVO_ID)
	public Integer getArchivoId();
	
	@Select(IF_EXISTE_ID)
	public String taiBitacoraCambios(@Param("archivoId") String archivoId);
	
	@Select(GET_NOMBRE_ARCHIVO)
	public String getNombreArchivo();
	
	@Select(IS_ARCHIVO_CANCELADO)
	public Integer isArchivoCancelado(@Param("archivoId") Long archivoId);

	@Select(BUSCA_ESTATUS_ARCHIVO_ZIP)
	public String buscarEstatusArchivoZIP(@Param("archivoId") Long archivoId, @Param("tipoBusqueda") String tipoBusqueda);
	
	@Select("select anio from SALARIOS_MINIMOS where SALARIOS_MINIMOS_ID = #{anio} ")
	public String getAnioSalarioMin(@Param("anio") String valor);
	
	@Update("UPDATE radar_archivo SET "
			+ " ARCHIVO_RECHAZADAS = #{pGenerar}, ultima_modificacion = SYSDATE "
			+ " WHERE id = #{archivoId}")
	public void updateRadarArchivoRechazadas(@Param("pGenerar") int pGenerar, @Param("archivoId") String archivoId);
	
	/**
	 * @author José Carmen Castillo Navarrete
	 * @return Integer
	 */
	@Insert(CANCELA_ARCHIVO_FM)
	public Integer cancelaRadarArchivoFM(
		@Param("archivoRadarId") Long radarArchivoId, 
		@Param("estatusProceId") Long estatusProceId, 
		@Param("empleadoId") Long empleadoId, 
		@Param("cantidadCancelados") Integer cantidadCancelados, 
		@Param("cantidadProcesados") Integer cantidadProcesados, 
		@Param("motivoCancelacion") String motivoCancelacion
	);
	
	/**
	 * @author José Carmen Catillo Navarrete
	 * @param adarArchivoId Long
	 * @return String  
	 */
	@Select(BUSCA_TIPO_ARCHIVO_FM)
	public String buscaArchivoTipoFM(
		@Param("radarArchivoId") Long radarArchivoId
	);
	
	@Select(BUSCA_RADAR_ARCHIVO_A_CANCELAR_FM)
	public RadarArchivoEstatusVO buscaRadarArchivoACancelarFM(
		@Param("archivoId") Long archivoId
	);
	
	@Select(BUSCA_ESTATUS_ARCHIVO_ZIP_FM)
	public String buscarEstatusArchivoZIPFM(
		@Param("archivoId") Long archivoId, 
		@Param("tipoBusqueda") String tipoBusqueda
	);
	
	@Select(IS_ARCHIVO_COMPLEMENTADO_FM)
	public Integer isArchivoComplementadoFM(@Param("archivoId") Long archivoId);
	
	@Select(value = UPDATE_RADAR_ARCHIVO_EXC)
	public void updateRadarArchivoFM(
			@Param("tipoColumna") String tipoColumna, 
			@Param("pGenerar") int pGenerar, 
			@Param("archivoId") String archivoId);
	
	@Select(value = ListDeteccionesComplementadasVO_FM)
	public List<DeteccionesComplementadasVO> listaDeteccionesComplementadasFM(
			@Param("archivoId") String archivoId);
	
	@Select(value = ListDeteccionesRechazadasVO_FM)
	public List<DeteccionesComplementadasVO> listaDeteccionesRechazadasFM(
			@Param("archivoId") String archivoId);
	
	@Select(value = UPDATE_RADAR_ARCHIVO_FM)
	public void setArchivoListoParaLiberarUpdateFM(
			@Param("archivoListoParaLiberar") int archivoListoParaLiberar, 
			@Param("enProceso") int enProceso,
			@Param("empId") Long empId, 			
			@Param("archivoId") String archivoId);
	
	@Select(value = UPDATE_RADAR_ARCHIVO_EN_PROCESO)
	public void setArchivoEnProceso(
			@Param("enProceso") int enProceso,
			@Param("archivoId") Long archivoId);

	@Select(value = UPDATE_RADAR_ARCHIVO_TOTAL_EN_PROCESO)
	public void setArchivoTotalEnProceso(
			@Param("enProceso") int enProceso,
			@Param("archivoId") Long archivoId);
	

	@Select(VALIDA_USUARIO_ACTIVO)
	public Integer validarUsuarioActivo(
			@Param("placaOficial") String placaOficial);
	
	@Select(INSERT_PARAMETROS_FIRMA_RADARES)
	public Integer insertaParametrosFirmaRadar(
			@Param("radarArchivoId") Long radarArchivoId, 
			@Param("empId") Long empId, 
			@Param("placaOficial") String placaOficial, 
			@Param("empRFC") String empRFC, 
			@Param("pwd") String pwd);
}
