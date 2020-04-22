package mx.com.teclo.saicdmx.persistencia.mybatis.dao.fm;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import mx.com.teclo.saicdmx.persistencia.vo.fm.FMDetalleVO;
import mx.com.teclo.saicdmx.persistencia.vo.fm.FMDeteccionesDisponiblesFCVO;
import mx.com.teclo.saicdmx.persistencia.vo.fm.FMDeteccionesLayoutVO;
import mx.com.teclo.saicdmx.persistencia.vo.fm.FMDeteccionesVO;
import mx.com.teclo.saicdmx.persistencia.vo.fm.FMLotesVO;
import mx.com.teclo.saicdmx.persistencia.vo.fm.FMTipoArchivoFCVO;
import mx.com.teclo.saicdmx.persistencia.vo.fotomulta.ConsultaLotesVO;
import mx.com.teclo.saicdmx.persistencia.vo.fotomulta.FotoMultaLotesVO;
import mx.com.teclo.saicdmx.persistencia.vo.fotomulta.ReporteDeteccionesVO;
import mx.com.teclo.saicdmx.persistencia.vo.lineadecaptura.ConsultaInfraccionForReasignacionVO;

@Mapper
public interface FMLotesMyBatisDAO {
	String NUMERO_DE_DETECCIONES =
		"SELECT "
		+ "	COUNT(*) "
		+ "FROM	TAI005D_FM_DETECCIONES FMD "
		+ "WHERE "
		+ " FMD.ST_ACTIVO = 1 "
		+ "	AND FMD.ST_CANCELADO = 0 "
		+ "	AND FMD.ST_PROCESADO = #{noProcesado} "
		+ "	AND FMD.ID_ORIGENPLACA = #{origenPlaca} "
		+ "	AND FMD.ID_TIPO_FOTOCIVICA = #{idTipoDeteccion} "
		+ "	AND FMD.ID_TIPO_PERSONA = #{idTipoPersona} "
		+ "	AND	TRUNC(TO_DATE(FMD.CD_FECHAHORA ,'dd/mm/yyyy HH24:MI:ss')) BETWEEN #{fechaInicio} AND #{fechaFin} ";
	  //+ "	AND FMD.ST_ACEPTADA_SSP = #{aceptada} "
//		"SELECT "
//		+ "	COUNT(*) "
//		+ "FROM	TAI005D_FM_DETECCIONES FMD "
//		+ "WHERE "
//		+ " FMD.ST_ACTIVO = 1 "
//		+ "	AND	TRUNC(TO_DATE(FMD.CD_FECHAHORA ,'dd/mm/yyyy HH24:MI:ss')) BETWEEN #{fechaInicio} AND #{fechaFin} "
//		+ "	AND FMD.ST_PROCESADO = #{noProcesado} "
//		+ "	AND FMD.ID_TIPO_DETECCION = #{idTipoDeteccion} "
//		+ "	AND FMD.ID_ORIGENPLACA = #{origenPlaca} "
//		+ "	AND FMD.ST_ACEPTADA_SSP = #{aceptada} "
//		+ "	AND FMD.ST_CANCELADO = 0 ";
		
//		+ "	AND FMD.TX_FIRMAELECTRONICA IS NOT NULL"
//		+ "	AND FMD.NU_FOLIO_INFRACCION NOT IN "
//		+ "	("
//		+ "		SELECT "
//		+ "			INFRAC_NUM "
//		+ "		FROM Radar_archivo_detalle RAD "
//		+ "		WHERE "
//		+ "			RAD.INFRAC_NUM = FMD.NU_FOLIO_INFRACCION "
//		+ "			AND RAD.PLACA = FMD.CD_PLACA "
//		+ "			AND RAD.FECHA = SUBSTR(FMD.CD_FECHAHORA, 1, INSTR(FMD.CD_FECHAHORA, ' ')-1) "
//		+ "			AND RAD.HORA = SUBSTR(FMD.CD_FECHAHORA, INSTR(FMD.CD_FECHAHORA, ' ')+1) "
//		+ "			AND RAD.TDSKUID = FMD.CD_TDSKUID"
//		+ "	)";
	
	String ULTIMO_REGISTRO_LOTE = 
		"SELECT "
		+ "	RADAR_ARCHIVO_SEQ.NEXTVAL AS ARCHIVO_ID "
		+ "FROM DUAL";
	
	String BUSCA_ARCHIVO_TIPO_POR_DETECCION_ORIGEN = 
		"SELECT "
		+ "	CASE "
		+ "		WHEN #{origenPlaca} = '0' "
		+ "			THEN ID_ARCHIVO_TIPO_CDMX "
		+ "		WHEN #{origenPlaca} = '1' "
		+ "			THEN ID_ARCHIVO_TIPO_FORA "
		+ "	END AS ARCHIVO_TIPO "
		+ "FROM TAI007C_FM_TIPO_DETECCIONES "
		+ "WHERE "
		+ "	ID_ARCHIVO_TIPO_FORA = #{idDeteccion} "
		+ "	AND ID_MARCA_DISPOSITIVO_DET = #{idMarca}";
	
	String ULTIMO_REGISTRO_DETALLE = 
		"SELECT "
		+ "	NVL(MAX(ID)+1, 1) "
		+ "FROM ( "
		+ "	SELECT ID "
		+ "	FROM RADAR_ARCHIVO_DETALLE "
		+ "	UNION ALL "
		+ "		SELECT ID "
		+ "		FROM RADAR_ARCHIVO_DETALLE_HIST "
		+ ")";
	
	String INSERT_FM_LOTE_FROM_DISP_FIJOS = 
		"INSERT INTO TAI010D_FM_LOTE "
		+ "	(ID, NOMBRE, FECHA_EMISION, ESTATUS_PROCESO_ID, EN_PROCESO, CREADO_POR, MODIFICADO_POR, "
		+ "	ULTIMA_MODIFICACION, ANIO_SALARIO_MINIMO,ARCHIVO_TIPO, ARCHIVO_TIPO_PROCESO,SALARIOS_MINIMOS_ID, "
		+ "	ID_TIPO_PERSONA, ID_TIPO_DETECCION, ST_LCAPTURA, ST_VCP_COMP,FH_INI_PERIODO, FH_FIN_PERIODO) " +
		"VALUES "
		+ "	(#{id}, #{nombre}, #{fechaEmision}, #{estatusProcesoId}, #{enProceso}, #{creadoPor}, "
		+ "	#{modificadoPor}, #{ultimaModificacion}, #{anioSalarioMinimo}, #{archivoTipo}, "
		+ "	#{archivoTipoProceso}, #{salariosMinimosId}, "
		+ "	#{idTipoPersona}, #{idTipoDeteccion}, #{stLCaptura}, #{stVCP},#{fhIniPeriodo},#{fhFinPeriodo})";
	
	String INSERT_RA_FROM_DISP_FIJOS = 
			"INSERT INTO RADAR_ARCHIVO "
			+ "	(ID, NOMBRE, FECHA_EMISION, ESTATUS_PROCESO_ID, EN_PROCESO, CREADO_POR, MODIFICADO_POR, "
			+ "	ULTIMA_MODIFICACION, ANIO_SALARIO_MINIMO,ARCHIVO_TIPO, ARCHIVO_TIPO_PROCESO,SALARIOS_MINIMOS_ID, "
			+ "	ID_TIPO_PERSONA, ID_TIPO_DETECCION, ST_LCAPTURA, ST_VCP_COMP,FH_INI_PERIODO, FH_FIN_PERIODO) "
			+ "VALUES "
			+ "	(#{id}, #{nombre}, #{fechaEmision}, #{estatusProcesoId}, #{enProceso}, #{creadoPor}, "
			+ "	#{modificadoPor}, #{ultimaModificacion}, #{anioSalarioMinimo}, #{archivoTipo}, "
			+ "	#{archivoTipoProceso}, #{salariosMinimosId}, "
			+ "	#{idTipoPersona}, #{idTipoDeteccion}, #{stLCaptura}, #{stVCP},#{fhIniPeriodo},#{fhFinPeriodo})";
	
	String INSERT_RADAR_TOTAL_FROM_DISP_FIJOS =
		"INSERT INTO RADAR_ARCHIVO_TOTAL "
		+ "	(ID, NOMBRE, FECHA_EMISION, ESTATUS_PROCESO_ID, EN_PROCESO, ORIGEN_LOTE, ID_TIPO_DETECCION, CREADO_POR, MODIFICADO_POR, "
		+ "	ULTIMA_MODIFICACION, ANIO_SALARIO_MINIMO,ARCHIVO_TIPO, ARCHIVO_TIPO_PROCESO,SALARIOS_MINIMOS_ID, "
		+ "	ID_TIPO_PERSONA, ST_LCAPTURA, ST_VCP_COMP,FH_INI_PERIODO, FH_FIN_PERIODO) " +
		"VALUES "
		+ "	(#{id}, #{nombre}, #{fechaEmision}, #{estatusProcesoId}, #{enProceso}, #{origenLote}, #{idTipoDeteccion}, #{creadoPor}, "
		+ "	#{modificadoPor}, #{ultimaModificacion}, #{anioSalarioMinimo}, #{archivoTipo}, "
		+ "	#{archivoTipoProceso}, #{salariosMinimosId}, "
		+ "	#{idTipoPersona}, #{stLCaptura}, #{stVCP},#{fhIniPeriodo},#{fhFinPeriodo})";
	
	String SELECT_CONTEO_TOTAL_DET = 
		"SELECT "
		+ "	count(ID_FC_DETECCION) "
		+ "FROM TAI005D_FM_DETECCIONES FMD "
		+ "WHERE "
		+ "	FMD.ST_ACTIVO = 1 "
		+ " AND FMD.ST_CANCELADO = 0 "
		+ "	AND FMD.ST_PROCESADO =  CASE "
		+ "		WHEN #{procesado} = 1 "
		+ "			THEN 1 "
		+ "		WHEN #{procesado} = 0 "
		+ "			THEN 0 "
		+ "		ELSE FMD.ST_PROCESADO "
		+ "	END "
		+ "	AND FMD.ID_ORIGENPLACA =  CASE "
		+ "		WHEN #{origenPlaca} = 1 "
		+ "			THEN 1 "
		+ "		WHEN #{origenPlaca} = 0 "
		+ "			THEN 0 "
		+ "		ELSE FMD.ID_ORIGENPLACA "
		+ "	END "
		+ "	AND FMD.ID_TIPO_FOTOCIVICA =  CASE "
		+ "		WHEN #{idTipoDeteccion} > 0 "
		+ "			THEN #{idTipoDeteccion} " 
		+ "			ELSE FMD.ID_TIPO_FOTOCIVICA "
		+ "		END "
		+ "	AND FMD.ID_TIPO_PERSONA =  CASE "
		+ "		WHEN #{idTipoPersona} = 1 "
		+ "			THEN '1' "
		+ "		WHEN #{idTipoPersona} = 2 "
		+ "			THEN '2' "
		+ "		WHEN #{idTipoPersona} = 3 "
		+ "			THEN '3' "
		+ "		ELSE FMD.ID_TIPO_PERSONA "
		+ "	END "
		+ "	AND TRUNC(TO_DATE(FMD.CD_FECHAHORA ,'dd/mm/yyyy HH24:MI:ss')) BETWEEN #{fechaInicio} AND #{fechaFin} ";
	
	String GENERA_EXCEL_BY_FECHA_INFRACCION = 
			"SELECT "
			+ "	ID_FC_DETECCION AS idFcDeteccion, "
			+ "	ID_LOTE AS idLote,"
			+ "	ST_PROCESADO AS stProcesado, "
			+ "	NU_FOLIO_INFRACCION AS nuFolioInfraccion, "
			+ "	CD_PLACA AS cdPlaca, "
			+ "	CD_FECHAHORA AS cdFechahora, "
			+ "	CD_TDSKUID AS cdTdskuid, "
			+ "	ID_TIPO_DETECCION AS idTipoDeteccion, "
			+ "	CD_UT AS cdUt, "
			+ "	NU_VELOCIDADDETECTADA AS nuVelocidaddetectada, "
			+ "	CD_OFICIALPLACA AS cdOficialplaca, "
			+ "	ID_ARTID AS idArtid, "
			+ "	ID_ORIGENPLACA AS idOrigenplaca, "
			+ "	NB_NOMBRE AS nbNombre, "
			+ "	NB_APELLIDOPAT AS nbApellidopat, "
			+ "	NB_APELLIDOMAT AS nbApellidomat, "
			+ "	NB_CALLE AS nbCalle, "
			+ "	NU_NUMEXT AS nuNumext, "
			+ "	NU_NUMINT AS nuNumint, "
			+ "	NB_COLONIA AS nbColonia, "
			+ "	NU_CP AS nuCp, "
			+ "	NB_ENTIDAD AS nbEntidad, "
			+ "	(SELECT EDO_NOMBRE FROM ESTADOS WHERE EDO_ID = NB_ENTIDAD) AS nombreEntidad, "
			+ "	NB_DELEGACION AS nbDelegacion, "
			+ "	(Fn_Delegacion(NB_ENTIDAD, NB_DELEGACION)) AS nombreDelegacion, "
			+ "	CD_TELEFONO AS cdTelefono, "
			+ "	NB_MARCA AS nbMarca, "
			+ "	NB_SUBMAR AS nbSubmar, "
			+ "	NB_MODELO AS nbModelo, "
			+ "	NU_NUMSERIE AS nuNumserie, "
			+ "	NU_NUMMOTOR AS nuNummotor, "
			+ "	CD_CREADOPOR AS cdCreadopor, "
			+ "	FH_CREACION AS fhCreacion, "
			+ "	CD_MODIFICADOPOR AS cdModificadopor, "
			+ "	FH_MODIFICACION AS fhModificacion, "
			+ "	ID_TIPO_PERSONA AS idTipoPersona, "
			+ "	ID_TIPO_FOTOCIVICA AS idTipoFotocivica, "
			+ "	NU_PUNTOSDESCNT AS nuPuntosDescnt, "
			+ "	TX_CORREO_ELECT AS txCorreoElect, "
			+ "	NU_LICENCIA AS nuLicencia, "
			+ "	ID_ARCHIVO_FC as idArchivoFC, "
			+ "	NB_UT_CALLE AS nbUTCalle, "
			+ "	NB_UT_ENTRECALLE AS nbUTEntreCalle, "
			+ "	NB_UT_YCALLE AS nbUTYCalle, "
			+ "	9 AS idUTEntidad, "
			+ "	(SELECT EDO_NOMBRE FROM ESTADOS WHERE EDO_ID = 9) AS nbUTEntidad, "
			+ "	ID_UT_DELEGACION AS idUTDelegacion, "
			+ "	(Fn_Delegacion(9, ID_UT_DELEGACION)) AS nbUTDelegacion, "
			+ "	ID_UT_DELEGACION AS idUTDelegacion, "
			+ "	NB_UT_COLONIA AS nbUTColonia, "
			+ "	CD_UT_SEC_COD AS cdUTSecCod, "
			+ "	nvl((select SEC_ID from SECTORES where SEC_COD = CD_UT_SEC_COD), 0) idUTSec, "
			+ "	nvl((select SEC_NOMBRE from SECTORES where SEC_COD = CD_UT_SEC_COD), 'DESCONOCIDO') nbUTSec, "
			+ "	CD_UT_CODIGO AS cdUTCodigo, "
			+ "	TX_UT_SENTIDO AS txUTSentido, "
			+ "	NU_UMAS AS nuUmas,"
			+ "	CD_EXT_SERIE_CAPTURA AS cdExtSerieCaptura "
//			+ "	("
//			+ "		SELECT "
//			+ "			MIN(NU_FOLIO_INFRACCION) "
//			+ "		FROM TAI005D_FM_DETECCIONES FMD1 "
//			+ "		WHERE "
//			+ "			FMD1.ST_ACTIVO = 1 "
//			+ "			AND TRUNC(TO_DATE(FMD1.CD_FECHAHORA ,'dd/mm/yyyy HH24:MI:ss')) BETWEEN #{fechaInicio} AND #{fechaFin} " 
//			+ "			AND FMD1.ST_PROCESADO =  CASE "
//			+ "				WHEN #{procesado} = 1 "
//			+ "					THEN 1 "
//			+ "				WHEN #{procesado} = 0 "
//			+ "					THEN 0 "
//			+ "				ELSE FMD1.ST_PROCESADO "
//			+ "			END "
//			+ "			AND FMD1.ID_TIPO_DETECCION =  CASE "
//			+ "				WHEN #{idTipoDeteccion} > 0 "
//			+ "					THEN #{idTipoDeteccion} " 
//			+ "					ELSE FMD1.ID_TIPO_DETECCION "
//			+ "				END "
//			+ "			AND FMD1.ID_ORIGENPLACA =  CASE "
//			+ "				WHEN #{origenPlaca} = 1 "
//			+ "					THEN 1 "
//			+ "				WHEN #{origenPlaca} = 0 "
//			+ "					THEN 0 "
//			+ "				ELSE FMD1.ID_ORIGENPLACA "
//			+ "			END "
//			+ "			AND FMD1.ST_ACEPTADA_SSP = 1 "
//			+ " 		AND FMD1.ST_CANCELADO = 0 "
//			+ "			AND FMD1.TX_FIRMAELECTRONICA IS NOT NULL "
//			+ "			AND FMD1.NU_FOLIO_INFRACCION NOT IN "
//			+ "			("
//			+ "				SELECT "
//			+ "					INFRAC_NUM "
//			+ "				FROM Radar_archivo_detalle RAD"
//			+ "				WHERE "
//			+ "					RAD.INFRAC_NUM = FMD1.NU_FOLIO_INFRACCION "
//			+ "					AND RAD.PLACA = FMD1.CD_PLACA "
//			+ "					AND RAD.FECHA = SUBSTR(FMD1.CD_FECHAHORA, 1, INSTR(FMD1.CD_FECHAHORA, ' ')-1) "
//			+ "					AND RAD.HORA = SUBSTR(FMD1.CD_FECHAHORA, INSTR(FMD1.CD_FECHAHORA, ' ')+1) "
//			+ "					AND RAD.TDSKUID = FMD1.CD_TDSKUID "
//			+ "			) "
//			+ "	) AS MinNuFolioInfraccion, "
//			+ "	("
//			+ "		SELECT " 
//			+ "			MAX(NU_FOLIO_INFRACCION) "
//			+ "		FROM TAI005D_FM_DETECCIONES FMD2 "
//			+ "		WHERE "
//			+ "			FMD2.ST_ACTIVO = 1 "
//			+ "			AND TRUNC(TO_DATE(FMD2.CD_FECHAHORA ,'dd/mm/yyyy HH24:MI:ss')) BETWEEN #{fechaInicio} AND #{fechaFin} " 
//			+ "			AND FMD2.ST_PROCESADO =  CASE "
//			+ "				WHEN #{procesado} = 1 "
//			+ "					THEN 1"
//			+ "				WHEN #{procesado} = 0 "
//			+ "					THEN 0 "
//			+ "				ELSE FMD2.ST_PROCESADO "
//			+ "			END "
//			+ "			AND FMD2.ID_TIPO_DETECCION =  CASE "
//			+ "				WHEN #{idTipoDeteccion} > 0 "
//			+ "					THEN #{idTipoDeteccion} " 
//			+ "				ELSE FMD2.ID_TIPO_DETECCION "
//			+ "				END "
//			+ "			AND FMD2.ID_ORIGENPLACA =  CASE "
//			+ "				WHEN #{origenPlaca} = 1 "
//			+ "					THEN 1 "
//			+ "				WHEN #{origenPlaca} = 0 "
//			+ "					THEN 0 "
//			+ "				ELSE FMD2.ID_ORIGENPLACA "
//			+ "			END "
//			+ "			AND FMD2.ST_ACEPTADA_SSP = 1 "
//			+ " 		AND FMD2.ST_CANCELADO = 0 "
//			+ "			AND FMD2.TX_FIRMAELECTRONICA IS NOT NULL "
//			+ "			AND FMD2.NU_FOLIO_INFRACCION NOT IN "
//			+ "			("
//			+ "				SELECT "
//			+ "					INFRAC_NUM "
//			+ "				FROM Radar_archivo_detalle RAD"
//			+ "				WHERE "
//			+ "					RAD.INFRAC_NUM = FMD2.NU_FOLIO_INFRACCION "
//			+ "					AND RAD.PLACA = FMD2.CD_PLACA "
//			+ "					AND RAD.FECHA = SUBSTR(FMD2.CD_FECHAHORA, 1, INSTR(FMD2.CD_FECHAHORA, ' ')-1) "
//			+ "					AND RAD.HORA = SUBSTR(FMD2.CD_FECHAHORA, INSTR(FMD2.CD_FECHAHORA, ' ')+1) "
//			+ "					AND RAD.TDSKUID = FMD2.CD_TDSKUID "
//			+ "			) "
//			+ "	) AS MaxNuFolioInfraccion "
			+ "FROM TAI005D_FM_DETECCIONES FMD "
			+ "WHERE "
			+ "	FMD.ST_ACTIVO = 1 "
			+ "	AND TRUNC(TO_DATE(FMD.CD_FECHAHORA ,'dd/mm/yyyy HH24:MI:ss')) BETWEEN #{fechaInicio} AND #{fechaFin} " 
			+ "	AND FMD.ST_PROCESADO =  CASE "
			+ "		WHEN #{procesado} = 1 "
			+ "			THEN 1 "
			+ "		WHEN #{procesado} = 0 "
			+ "			THEN 0 "
			+ "		ELSE FMD.ST_PROCESADO "
			+ "	END "
			+ "	AND FMD.ID_TIPO_FOTOCIVICA =  CASE "
			+ "		WHEN #{idTipoDeteccion} > 0 "
			+ "			THEN #{idTipoDeteccion} " 
			+ "			ELSE FMD.ID_TIPO_FOTOCIVICA "
			+ "		END "
			+ "	AND FMD.ID_ORIGENPLACA =  CASE "
			+ "		WHEN #{origenPlaca} = 1 "
			+ "			THEN 1 "
			+ "		WHEN #{origenPlaca} = 0 "
			+ "			THEN 0 "
			+ "		ELSE FMD.ID_ORIGENPLACA "
			+ "	END "
			+ "	AND FMD.ID_TIPO_PERSONA =  CASE "
			+ "		WHEN #{idTipoPersona} = 1 "
			+ "			THEN '1' "
			+ "		WHEN #{idTipoPersona} = 2 "
			+ "			THEN '2' "
			+ "		WHEN #{idTipoPersona} = 3 "
			+ "			THEN '3' "
			+ "		ELSE FMD.ID_TIPO_PERSONA "
			+ "	END "
			+ "	AND FMD.ST_ACEPTADA_SSP = 1 "
			+ " AND FMD.ST_CANCELADO = 0 "
//			+ "	AND FMD.TX_FIRMAELECTRONICA IS NOT NULL "
//			+ "	AND FMD.NU_FOLIO_INFRACCION NOT IN "
//			+ "	("
//			+ "		SELECT "
//			+ "			INFRAC_NUM "
//			+ "		FROM Radar_archivo_detalle RAD "
//			+ "		WHERE "
//			+ "			RAD.INFRAC_NUM = FMD.NU_FOLIO_INFRACCION "
//			+ "			AND RAD.PLACA = FMD.CD_PLACA "
//			+ "			AND RAD.FECHA = SUBSTR(FMD.CD_FECHAHORA, 1, INSTR(FMD.CD_FECHAHORA, ' ')-1) "
//			+ "			AND RAD.HORA = SUBSTR(FMD.CD_FECHAHORA, INSTR(FMD.CD_FECHAHORA, ' ')+1) "
//			+ "			AND RAD.TDSKUID = FMD.CD_TDSKUID "
//			+ "	) "
			+ "ORDER BY "
			+ "	FMD.NU_CP, FMD.CD_PLACA, FMD.ID_FM_DETECCION ASC";
	
	String SELECT_MIN_MAX = 
		"SELECT "
		+ "	MIN(NU_FOLIO_INFRACCION) AS MaxNuFolioInfraccion,"
		+ "	MAX(NU_FOLIO_INFRACCION) AS MinNuFolioInfraccion "
		+ "FROM TAI005D_FM_DETECCIONES "
		+ "WHERE"
		+ "	ST_ACTIVO = 1 "
		+ "	AND TRUNC(TO_DATE(CD_FECHAHORA ,'dd/mm/yyyy HH24:MI:ss')) BETWEEN #{fechaInicio} AND #{fechaFin} " 
		+ "	AND ST_PROCESADO =  CASE "
		+ "		WHEN #{procesado} = 1 OR #{procesado} = 0 " 
		+ "			THEN #{procesado} "
		+ "			ELSE ST_PROCESADO "
		+ "		END "
		+ "	AND ID_TIPO_DETECCION =  CASE "
		+ "		WHEN #{idTipoDeteccion} > 0 "
		+ "			THEN #{idTipoDeteccion}" 
		+ "			ELSE ID_TIPO_DETECCION "
		+ "		END "
		+ "	AND ID_ORIGENPLACA =  CASE" 
		+ "		WHEN #{origenPlaca} = 1 OR #{origenPlaca} = 0 "
		+ "			THEN #{origenPlaca} "
		+ "			ELSE ID_ORIGENPLACA "
		+ "		END "
		+ "ORDER BY "
		+ "	NU_CP, CD_PLACA, ID_FM_DETECCION ASC";
	
	String INSERTAR_FM_DETALLE = 
		"INSERT INTO TAI011D_FM_DETALLE "
		+ "	(ID, RADAR_ARCHIVO_ID, PLACA, FECHA, HORA, TDSKUID, UT, VELOCIDAD_DETECTADA, FECHA_CREACION, "
		+ "	PLACA_SF, NOMBRE, APELLIDO_PATERNO, APELLIDO_MATERNO, CALLE, NUM_EXTERIOR, NUM_INTERIOR, "
		+ "	COLONIA, MUNICIPIO, CODIGO_POSTAL, ENTIDAD_FEDERATIVA, MARCA, SUBMARCA, MODELO, TELEFONO, "
		+ "	SERIE, MOTOR, INFRAC_NUM, ART_ID, OFICIAL_PLACA, UT_MODIFICADO_POR) " +
		"VALUES "
		+ "	(#{id}, #{radarArchivoId}, #{placa}, #{fecha}, #{hora}, #{tdskuid}, #{ut}, #{velocidadDetectada}, "
		+ "	SYSDATE, #{placa}, #{nombre}, #{apellidoPaterno}, #{apellidoMaterno}, #{calle}, "
		+ "	#{numExterior}, #{numInterior}, #{colonia}, #{municipio}, #{codigoPostal}, #{entidadFederativa}, "
		+ "	#{marca}, #{submarca}, #{modelo}, #{telefono}, #{serie}, #{motor}, #{infracNum}, #{artId}, "
		+ "	#{oficialPlaca}, #{utModificadoPor})";
	
	String INSERTAR_RADAR_ARCHIVO = 
			"INSERT INTO  RADAR_ARCHIVO_DETALLE "
			+ "( "
			+ "	ID, RADAR_ARCHIVO_ID, PLACA, FECHA, HORA, TDSKUID, UT, VELOCIDAD_DETECTADA, FECHA_CREACION, "
			+ "	PLACA_SF, NOMBRE, APELLIDO_PATERNO, APELLIDO_MATERNO, CALLE, NUM_EXTERIOR, NUM_INTERIOR, "
			+ "	COLONIA, MUNICIPIO, CODIGO_POSTAL, ENTIDAD_FEDERATIVA, MARCA, SUBMARCA, MODELO, TELEFONO, "
			+ "	SERIE, MOTOR, INFRAC_NUM, ART_ID, OFICIAL_PLACA, NU_PUNTOSDESCNT, TX_CORREO_ELECT, NU_LICENCIA, "
			+ "	ID_ARCHIVO_FC, "
			+ "	UT_CALLE, UT_ENTRE_CALLE, UT_Y_CALLE, UT_COLONIA, UT_DELEGACION_ID, UT_DELEGACION, UT_EDO_ID, "
			+ "	UT_SECTOR, UT_SECTOR_ID, DIAS, CD_EXT_SERIE_CAPTURA, ID_FC_DETECCION "
			+ ") VALUES ("
			+ "	RADAR_ARCHIVO_DETALLE_SEQ.NEXTVAL, #{radarArchivoId}, #{placa}, #{fecha}, #{hora}, #{tdskuid}, #{ut}, #{velocidadDetectada}, "
			+ "	SYSDATE, #{placa}, #{nombre}, #{apellidoPaterno}, #{apellidoMaterno}, #{calle}, "
			+ "	#{numExterior}, #{numInterior}, #{colonia}, #{municipio}, #{codigoPostal}, #{entidadFederativa}, "
			+ "	#{marca}, #{submarca}, #{modelo}, #{telefono}, #{serie}, #{motor}, #{infracNum}, #{artId}, "
			+ "	#{oficialPlaca}, #{nuPuntosDescnt}, #{txCorreoElect}, #{nuLicencia}, #{idArchivoFC}, "
			+ "	#{utCalle}, #{utEntreCalle}, #{utYCalle}, #{utColonia}, #{utDelegacionId}, #{utDelegacion}, #{utEdoId}, "
			+ "	#{utSector}, #{utSectorId}, #{dias}, #{cdExtSerieCaptura}, #{idFcDeteccion} "
			+ ")";
	
	String ACTUALIZA_FM_DETECCIONES_POR_LOTE = 
			"${queryUpdate}";
	
	String ACTUALIZA_FC_DETECCIONES_POR_LOTE = 
			"${queryUpdate}";
	
	String ACTUALIZA_LOTE_CREACION = 
		"UPDATE TAI010D_FM_LOTE "
		+ "SET "
		+ "	CANTIDAD_PROCESADOS = 0, "
		+ "	EN_PROCESO = 0,"
//		+ "	INFRAC_NUM_INICIAL = #{minNuFolioInfraccion}, "
//		+ "	INFRAC_NUM_FINAL = #{maxNuFolioInfraccion},"
		+ "	CANTIDAD_CANCELADOS = 0"
		+ "WHERE "
		+ "	ID = #{idLote}";
	
	String BUSCA_ARCHIVO_EN_COMPLEMENTACION = 
		"SELECT COUNT(EN_PROCESO) "
		+ "FROM TAI010D_FM_LOTE "
		+ "WHERE "
		+ "	EN_PROCESO = #{enProceso}"; 
	
	String ACTUALIZA_FM_LOTE_PARA_COMPLEMENTACION = 
		"UPDATE  TAI010D_FM_LOTE "
		+ "SET "
		+ "	EN_PROCESO = #{enProceso} "
		+ "WHERE "
		+ "	ID = #{idLote}";
	String ACTUALIZA_RADAR_ARCHIVO_PARA_COMPLEMENTACION = 
		"UPDATE  RADAR_ARCHIVO "
		+ "SET "
		+ "	EN_PROCESO = #{enProceso} "
		+ "WHERE "
		+ "	ID = #{idLote}";
	
	String ACTUALIZA_RADAR_ARCHIVO_TOTAL_PARA_COMPLEMENTACION = 
			"UPDATE  RADAR_ARCHIVO_TOTAL "
			+ "SET "
			+ "	EN_PROCESO = #{enProceso} "
			+ "WHERE "
			+ "	ID = #{idLote}";
	
	String INSERTAR_BIT_PROCESO = 
			"INSERT INTO RADAR_BITACORA_PROCESO "
			+ "	(ID_FM_LOTE, ID_ESTATUS_PROCESO, FH_CREACION, CD_CREADO_POR) " +
			"VALUES "
			+ "	(#{idLote}, 1, SYSDATE, #{usuario})";
	
	String INSERTAR_FM_LOTE_BIT_PROCESO = 
			"INSERT INTO TAI013B_FM_BIT_PROCESO "
			+ "	(ID_FM_LOTE, ID_ESTATUS_PROCESO, FH_CREACION, CD_CREADO_POR) " +
			"VALUES "
			+ "	(#{idLote}, 1, SYSDATE, #{usuario})";
	
	String INSERTAR_RADAR_ARCHIVO_BIT_PROCESO = 
			"INSERT INTO RADAR_BITACORA_PROCESO "
			+ "	(RADAR_ARCHIVO_ID, ESTATUS_PROCESO_ID, FECHA, CREADO_POR) " +
			"VALUES "
			+ "	(#{idLote}, 1, SYSDATE, #{usuario})";
	
	String ID_DETECCION_POR_ARCHIVO_TIPO = 
		"SELECT "
		+ "	ID_TIPO_DETECCION "
		+ "FROM TAI007C_FM_TIPO_DETECCIONES "
		+ "WHERE "
		+ "	ID_ARCHIVO_TIPO_FORA = #{idDeteccion} "
		+ "	AND ID_MARCA_DISPOSITIVO_DET = #{idMarca}";
	
	String BUSCA_DELEGACION_POR_ID = 
		"SELECT "
		+ "	DEL_NOMBRE "
		+ "FROM DELEGACIONES "
		+ "WHERE "
		+ "	DEL_ID = #{idDelegacion} "
		+ "	AND EDO_ID = #{idEstado}";
	
	String BUSCA_ESTADO_POR_ID = 
		"SELECT "
		+ "	EDO_NOMBRE "
		+ "FROM ESTADOS "
		+ "WHERE "
		+ "	EDO_ID = #{idEstado}";
	
	String INSERT_BITACORA_CAMBIOS_RA = 
		"INSERT INTO RADAR_BITACORA_PROCESO "
		+ "	(COMPONENTE_ID, CONCEPTO_ID, VALOR_ORIGINAL, VALOR_FINAL, FECHA_CREACION, CREADO_POR, ID_REGISTRO) " +
		"VALUES "
		+ "	(8, 1, NULL, #{nombreLote}, SYSDATE, #{creadoPor}, #{idLote})";
	
	String INSERT_BITACORA_CAMBIOS_FM = 
		"INSERT INTO TAI_BITACORA_CAMBIOS "
		+ "	(COMPONENTE_ID, CONCEPTO_ID, VALOR_ORIGINAL, VALOR_FINAL, FECHA_CREACION, CREADO_POR, ID_REGISTRO) " +
		"VALUES "
		+ "	(8, 1, NULL, #{nombreLote}, SYSDATE, #{creadoPor}, #{idLote})";
	
	String BUSCA_LOTES_CREADOS = 
		"SELECT "
		+ "	RAT.ID, "
		+ "	RAT.NOMBRE, "
		+ "	RAT.FECHA_EMISION FECHAEMISION, "
		+ "	FCTF.NB_TIPO_FOTOCIVICA TIPODETECCION, " 
		+ "	FCTP.NB_TIPO_ARCHIVO_FCIVICA TIPOARCHIVO, " 
		+ "	FMOP.ORIGEN_PLACA ORIGENPLACA, " 
		+ "	( "
		+ "		SELECT "
		+ "			COUNT(ID) "
		+ "		FROM RADAR_ARCHIVO_DETALLE "
		+ "		WHERE "
		+ "			RADAR_ARCHIVO_ID = RAT.ID " 
		+ "	) CANTIDADDETECCIONES "
		+ "FROM RADAR_ARCHIVO_TOTAL RAT "
		+ "	LEFT JOIN TAI007C_FM_TIPO_DETECCIONES FMTD ON RAT.ID_TIPO_DETECCION = FMTD.ID_TIPO_DETECCION "
		+ "	LEFT JOIN TAI009C_FM_MARCA_DISPOSITIVO FMMD ON FMTD.ID_MARCA_DISPOSITIVO_DET = FMMD.ID_MARCA_DISPOSITIVO_DET " 
		+ "	LEFT JOIN ( "
		+ "		SELECT "
		+ "			DISTINCT(ID_ARCHIVO_TIPO_CDMX) ARCHIVO_TIPO, " 
		+ "			'CDMX' ORIGEN_PLACA "
		+ "		FROM TAI007C_FM_TIPO_DETECCIONES " 
		+ "		UNION "
		+ "		SELECT "
		+ "			DISTINCT(ID_ARCHIVO_TIPO_FORA) ARCHIVO_TIPO, " 
		+ "			'FORANEA' ORIGEN_PLACA "
		+ "		FROM TAI007C_FM_TIPO_DETECCIONES "
		+ "	) FMOP ON RAT.ARCHIVO_TIPO = FMOP.ARCHIVO_TIPO "
		+ "	LEFT JOIN TAI033C_FC_TIPO_ARCH_FCIVICA FCTP on FCTP.ID_TIPO_ARCHIVO_FCIVICA = RAT.ID_TIPO_PERSONA "
		+ "	LEFT JOIN TAI036C_FC_TIPO_FOTOCIVICA FCTF ON FCTF.ID_TIPO_FOTOCIVICA = RAT.ID_TIPO_DETECCION "
		+ "WHERE "
		+ "	RAT.ORIGEN_LOTE = 1 "
		+ "	AND RAT.ESTATUS_PROCESO_ID = 1 " 
		+ "	AND RAT.EN_PROCESO = 0 "
		+ "	AND RAT.CANCELADO = 0 " 
		+ "	AND ( "
		+ "		RAT.CANTIDAD_CANCELADOS = 0 "
		+ "		OR RAT.CANTIDAD_CANCELADOS IS NULL "
		+ "	) "
		+ "ORDER BY RAT.ID DESC";
//		"SELECT "
//		+ "	RAT.ID, "
//		+ "	RAT.NOMBRE, "
//		+ "	RAT.FECHA_EMISION FECHAEMISION, "
//		+ "	FMTD.NB_DISPOSITIVO_DETECCION TIPODETECCION, "
//		+ "	FMMD.NB_DISPOSITIVO MARCA, "
//		+ "	FMOP.ORIGEN_PLACA ORIGENPLACA, "
//		+ "	( "
//		+ "		SELECT "
//		+ "			COUNT(ID) "
//		+ "		FROM TAI011D_FM_DETALLE "
//		+ "		WHERE "
//		+ "			RADAR_ARCHIVO_ID = RAT.ID "
//		+ "	) CANTIDADDETECCIONES "
//		+ "FROM RADAR_ARCHIVO_TOTAL RAT "
//		+ "	LEFT JOIN TAI007C_FM_TIPO_DETECCIONES FMTD ON RAT.ID_TIPO_DETECCION = FMTD.ID_TIPO_DETECCION "
//		+ "	LEFT JOIN TAI009C_FM_MARCA_DISPOSITIVO FMMD ON FMTD.ID_MARCA_DISPOSITIVO_DET = FMMD.ID_MARCA_DISPOSITIVO_DET "
//		+ "	LEFT JOIN ("
//		+ "		SELECT "
//		+ "			DISTINCT(ID_ARCHIVO_TIPO_CDMX) ARCHIVO_TIPO, "
//		+ "			'CDMX' ORIGEN_PLACA "
//		+ "		FROM TAI007C_FM_TIPO_DETECCIONES "
//		+ "		UNION "
//		+ "		SELECT "
//		+ "			DISTINCT(ID_ARCHIVO_TIPO_FORA) ARCHIVO_TIPO, "
//		+ "			'FORANEA' ORIGEN_PLACA "
//		+ "		FROM TAI007C_FM_TIPO_DETECCIONES "
//		+ "	) FMOP ON RAT.ARCHIVO_TIPO = FMOP.ARCHIVO_TIPO "
//		+ "WHERE "
//		+ "	RAT.ORIGEN_LOTE = 1 "
//		+ "	AND RAT.ESTATUS_PROCESO_ID = 1 "
//		+ "	AND RAT.EN_PROCESO = 0 "
//		+ "	AND RAT.CANCELADO = 0 "
//		+ "	AND ("
//		+ "		RAT.CANTIDAD_CANCELADOS = 0 "
//		+ "		OR RAT.CANTIDAD_CANCELADOS IS NULL"
//		+ "	) "
//		+ "ORDER BY RAT.ID DESC";
	
	String CAMPOS_OBLIGATORIOS_OPCIONALES = 
			"SELECT "
			+ "	DL.CD_CAMPOS_OBLIGATORIOS AS cdCamposObligatorios, "
			+ "	DL.CD_CAMPOS_OPCIONALES AS cdCamposOpcionales, "
			+ "	DL.CD_CAT_ENTIDADES AS cdCatEntidades, "
			+ "	DL.CD_CAT_DELEGACIONES AS cdCatDelegaciones, "
			+ "	DL.CD_CAT_ARTICULOS AS cdCatArticulos "
			+ "FROM TAI018C_FM_DETECCIONES_LAYOUT DL "
			+ "WHERE DL.ST_ACTIVO=1 "
			+ "	AND DL.ID_TIPO_DETECCION = #{idDeteccion} "
			+ "	AND DL.ID_ORIGENPLACA = #{idOrigenPlaca}";
	
	String BUSCA_ARCHIVO_FOTOCIVICAS = 
		"SELECT "
		+ "	TAI033.ID_TIPO_ARCHIVO_FCIVICA AS idTipoArchivoFCivica, "
		+ "	TAI033.NB_TIPO_ARCHIVO_FCIVICA AS nbTipoArchivoFCivica "
		+ "FROM TAI033C_FC_TIPO_ARCH_FCIVICA TAI033 "
		+ "WHERE "
		+ "	TAI033.ST_ACTIVO = 1";
	
	String BUSCA_DETECCIONES_DISPONIBLES =
		"SELECT "
		+ "	TAI005.ID_TIPO_PERSONA AS idTipoPersona, "
		+ "	TAI036.CD_TIPO_FOLIO_INFRACCION as folio, "
		+ "	to_char(TRUNC(TO_DATE(TAI005.CD_FECHAHORA,'DD-MM-YYYY HH24:MI:SS')),'MM') nuMes, "
		+ "	TRIM(UPPER(to_char(TRUNC(TO_DATE(TAI005.CD_FECHAHORA,'DD-MM-YYYY HH24:MI:SS')),'month','nls_date_language=spanish'))) mes, "
		+ "	to_char(TRUNC(TO_DATE(TAI005.CD_FECHAHORA,'DD-MM-YYYY HH24:MI:SS')),'YYYY') nuAnio, "
		+ "	NVL(COUNT(TAI005.ID_TIPO_FOTOCIVICA),0) AS cantInfrac "
		+ "FROM TAI005D_FM_DETECCIONES TAI005 "
		+ "		LEFT JOIN TAI036C_FC_TIPO_FOTOCIVICA TAI036 ON TAI036.ID_TIPO_FOTOCIVICA = TAI005.ID_TIPO_FOTOCIVICA "
		+ "WHERE "
		+ "	TAI005.ID_LOTE = 0 "
		+ "	AND TAI005.ST_ACTIVO = 1 "
		+ "	AND TAI005.ST_PROCESADO = 0 "
		//+ "	AND TAI005.ST_ACEPTADA_SSP = 1 "
		+ "	AND TAI005.ST_CANCELADO = 0 "
		+ "	AND TAI005.ID_TIPO_PERSONA = #{idTipoArchivoFCivica} "
		+ "	AND TAI005.ID_TIPO_FOTOCIVICA = #{idTipoFCivica} "
		+ "GROUP BY "
		+ "	TAI005.ID_TIPO_PERSONA, "
		+ "	TAI036.CD_TIPO_FOLIO_INFRACCION, "
		+ "	to_char(TRUNC(TO_DATE(TAI005.CD_FECHAHORA,'DD-MM-YYYY HH24:MI:SS')),'MM'), "
		+ "	TRIM(UPPER(to_char(TRUNC(TO_DATE(TAI005.CD_FECHAHORA,'DD-MM-YYYY HH24:MI:SS')),'month','nls_date_language=spanish'))), "
		+ "	to_char(TRUNC(TO_DATE(TAI005.CD_FECHAHORA,'DD-MM-YYYY HH24:MI:SS')),'YYYY') "
		+ "ORDER BY "
		+ "	to_char(TRUNC(TO_DATE(TAI005.CD_FECHAHORA,'DD-MM-YYYY HH24:MI:SS')),'YYYY') ASC, "
		+ "	TRIM(UPPER(to_char(TRUNC(TO_DATE(TAI005.CD_FECHAHORA,'DD-MM-YYYY HH24:MI:SS')),'MM'))) ASC";
		
//		"SELECT "
//		+ "	NVL(COUNT(CASE"
//		+ "		WHEN TAI005.ID_TIPO_FOTOCIVICA = 1"
//		+ "			THEN 1 "
//		+ "		END),0) AS infrac03, "
//		+ "	NVL(COUNT(CASE "
//		+ "		WHEN TAI005.ID_TIPO_FOTOCIVICA = 2 "
//		+ "			THEN 1 "
//		+ "		END)"
//		+ " ,0) AS infrac08 "
//		+ "FROM TAI005D_FM_DETECCIONES TAI005 "
//		+ "WHERE "
//		+ "	TAI005.ID_LOTE = 0"
//		+ "	AND TAI005.ST_ACTIVO = 1"
//		+ "	AND TAI005.ST_PROCESADO = 0"
//		+ "	AND TAI005.ST_ACEPTADA_SSP = 1"
//		+ "	AND TAI005.ST_CANCELADO = 0"
//		+ "	AND TAI005.ID_TIPO_PERSONA = #{idTipoArchivoFCivica}";
			
	
	String ACTUALIZA_CANT_PROCESADOS = 
		"UPDATE TAI028D_FC_ARCHIVO TAI028 "
		+ "SET CANTIDAD_PROCESADOS = ( "
		+ "		SELECT COUNT(*)"
		+ "		FROM TAI027D_FC_DETECCIONES "
		+ "		WHERE "
		+ "		ID_ARCHIVO = TAI028.ID_ARCHIVO "
		+ "		AND ST_PROCESADO = 1 "
		+ "	) "
		+ "WHERE "
		+ "	TAI028.ST_COMPLEMENTADO = 0 "
		+ " AND TAI028.ID_ARCHIVO in( "
		+ "		SELECT DISTINCT ID_ARCHIVO_FC "
		+ "		FROM RADAR_ARCHIVO_DETALLE "
		+ "		WHERE RADAR_ARCHIVO_ID = #{radarArchivoId}"
		+ "	)";
	
	String ACTUALIZA_ST_COMP = 
		"UPDATE TAI028D_FC_ARCHIVO TAI028 "
		+ "SET TAI028.ST_COMPLEMENTADO = ( "
		+ "		SELECT "
		+ "			DECODE(COUNT(*), 0, 1, 0) "
		+ "		FROM TAI027D_FC_DETECCIONES TAI027 "
		+ "		WHERE "
		+ "			TAI027.ST_PROCESADO = 0 "
		+ "			AND TAI027.ST_CANCELADO = 0 "
		+ "			AND TAI027.ID_ARCHIVO = TAI028.ID_ARCHIVO"
		+ "	) "
		+ "WHERE "
		+ "	TAI028.ST_COMPLEMENTADO = 0 "
		+ "	AND ST_PROCESO != 5 "
		+ "	AND TAI028.ID_ARCHIVO in("
		+ "		SELECT DISTINCT ID_ARCHIVO_FC "
		+ "		FROM RADAR_ARCHIVO_DETALLE "
		+ "		WHERE RADAR_ARCHIVO_ID = #{radarArchivoId} "
		+ "	)";
	
	String UPDATE_COMPLEMENTA_OFICIAL_NOMBRE =
		"UPDATE RADAR_ARCHIVO_DETALLE "
		+ "	SET "
		+ "		OFICIAL_NOMBRE = ( "
		+ "			SELECT NVL(trim(EMP_APE_PATERNO ||' '|| EMP_APE_MATERNO ||' '|| EMP_NOMBRE),null) AS OFICIAL_NOMBRE "
		+ "			FROM EMPLEADOS "
		+ "			WHERE EMP_PLACA = RADAR_ARCHIVO_DETALLE.OFICIAL_PLACA "
		+ "		) "
		+ "	WHERE NVL(OFICIAL_NOMBRE,null) is null AND RADAR_ARCHIVO_ID = #{radarArchivoId}";
	
	String UPDATE_VALIDA_FORMATO_PLACA = 
		"UPDATE RADAR_ARCHIVO_DETALLE "
		+ "	SET "
		+ "		ACTIVO = 0, "
		+ "		OFICIAL_NOMBRE = -3 "
		+ "	WHERE "
		+ "		EXISTS ( "
		+ "			SELECT * "
		+ "			FROM TAI006_FM_VALIDA_MATRICULA "
		+ "			WHERE "
		+ "				ACTIVO = 1 "
		//+ "				AND PKG_ENCRIPCION.DESENCRIPTA(PLACA) = RADAR_ARCHIVO_DETALLE.PLACA "
		+ "				AND PLACA = PKG_ENCRIPCION.ENCRIPTA(RADAR_ARCHIVO_DETALLE.PLACA) "
		+ "		) "
		+ "		AND RADAR_ARCHIVO_ID = #{radarArchivoId}";
	
	String PARAMETROS_QUERYS_CONDONACIONES = 
		"SELECT "
		+ "	CD_LLAVE_P_CONFIG, "
		+ "	CD_VALOR_P_CONFIG "
		+ "FROM TAI041P_CONFIGURACION "
		+ "WHERE "
		+ "	ST_ACTIVO = 1";
	
	String CONS_PERSONALIZADA = "${consPersonalizada}";
	
	@Select(NUMERO_DE_DETECCIONES)
	public Integer numeroDetecciones(
			@Param("fechaInicio")	Date fechaInicio, 
			@Param("fechaFin")		Date fechaFin, 
			@Param("noProcesado")	Integer procesado, 
			@Param("idTipoDeteccion")Long idTipoDeteccion,
			@Param("idTipoPersona")	Integer idTipoPersona, 
			@Param("origenPlaca")	Integer origenPlaca,
			@Param("aceptada")		Integer aceptada);
	
	@Select(ULTIMO_REGISTRO_LOTE)
	public Long obtenerSiguienteLote();
	
	@Select(BUSCA_ARCHIVO_TIPO_POR_DETECCION_ORIGEN)
	public Integer obtenerArchivoTipoPorDeteccionYOrigen(@Param("origenPlaca")Long origenPlaca, @Param("idDeteccion")Integer idDeteccion, @Param("idMarca")Integer idMarca);
	
	@Select(ULTIMO_REGISTRO_DETALLE)
	public Long obtenerSiguienteDetalle();
	
	@Select("select anio from SALARIOS_MINIMOS where SALARIOS_MINIMOS_ID = #{anio} ")
	public String getAnioSalarioMin(
			@Param("anio") String valor);
	
	@Insert(INSERT_FM_LOTE_FROM_DISP_FIJOS)
	public Integer insertFMLoteFromFotomultas(FMLotesVO radarArchivoVO);
	
	@Insert(INSERT_RA_FROM_DISP_FIJOS)
	public Integer insertRadarArchivoFromFotomultas(FMLotesVO radarArchivoVO);
	
	@Insert(INSERT_RADAR_TOTAL_FROM_DISP_FIJOS)
	public Integer insertRadarArchivoTotalFromFotomultas(FMLotesVO radarArchivoVO);
	
	@Insert(INSERT_BITACORA_CAMBIOS_RA)
	public Integer insertBitacoraCambiosRA(
			@Param("idLote") Long idLote, 
			@Param("nombreLote") String nombreLote,
			@Param("creadoPor") Long creadoPor);
	
	@Insert(INSERT_BITACORA_CAMBIOS_FM)
	public Integer insertBitacoraCambiosFM(
			@Param("idLote") Long idLote, 
			@Param("nombreLote") String nombreLote,
			@Param("creadoPor") Long creadoPor);
	
	
	@Select(GENERA_EXCEL_BY_FECHA_INFRACCION)
	List<FMDeteccionesVO> buscaFotomultasDetecionesRangoFechasByFechaInfraccion(
			@Param("fechaInicio") Date fechaInicio, 
			@Param("fechaFin") Date fechaFin, 
			@Param("procesado") Integer procesado, 
			@Param("idTipoDeteccion") Integer idTipoDeteccion, 
			@Param("idTipoPersona") Integer idTipoPersona,
			@Param("origenPlaca") Integer origenPlaca);
	
	@Select(SELECT_CONTEO_TOTAL_DET)
	Integer buscaFotomultasDetecionesRangoFechasByFechaInfraccionSelect(
			@Param("fechaInicio") Date fechaInicio, 
			@Param("fechaFin") Date fechaFin, 
			@Param("procesado") Integer procesado, 
			@Param("idTipoDeteccion") Integer idTipoDeteccion, 
			@Param("idTipoPersona") Integer idTipoPersona,
			@Param("origenPlaca") Integer origenPlaca);
	
	@Select("SELECT EDO_NOMBRE FROM ESTADOS WHERE EDO_ID=#{nb_entidad}")
	public String getNombreEntidad(
			@Param("nb_entidad") String nb_entidad);
	
	@Select("Fn_Delegacion(#{nb_entidad}, #{nb_delegacion})")
	public String getNombreDelegacion(
			@Param("nb_entidad") String nb_entidad, 
			@Param("nb_delegacion") String nb_delegacion);
	
	@Insert(INSERTAR_FM_DETALLE)
	public Integer insertarFMDetalle(FMDetalleVO reporteDeteccionesVO);
	
	String INSERTAR_FM_DETALLE_SELECT = "${queryInsertDT}";
	
	@Insert(INSERTAR_FM_DETALLE_SELECT)
	public Integer insertarFMDetalleSelect(
			@Param("queryInsertDT") String queryInsertDT,
			@Param("radarArchivoId") Long radarArchivoId,
			@Param("procesado") Integer procesado,
			@Param("idTipoDeteccion") Integer idTipoDeteccion,
			@Param("origenPlaca") Integer origenPlaca,
			@Param("idTipoPersona") Integer idTipoPersona,
			@Param("fechaInicio") Date fechaInicio, 
			@Param("fechaFin") Date fechaFin);
	
	@Insert(INSERTAR_RADAR_ARCHIVO)
	public Integer insertarRADDetalle(FMDetalleVO reporteDeteccionesVO);
	
	@Update(ACTUALIZA_FM_DETECCIONES_POR_LOTE)
	Integer actualizaFotomultaDeteccionesPorLote(
			@Param("queryUpdate") String queryUpdate,
			@Param("procesado") Integer procesado, 
			@Param("idLote") Long idLote);
	
	@Update(ACTUALIZA_FC_DETECCIONES_POR_LOTE)
	Integer actualizaFotoCivicasDeteccionesPorLote(
			@Param("queryUpdate") String queryUpdate,
			@Param("procesado") Integer procesado,
			@Param("idLote") Long idLote);
	
	@Update(ACTUALIZA_LOTE_CREACION)
	public void actualizaLoteCreacion( 
			@Param("idLote") Long idLote,
			@Param("minNuFolioInfraccion") String minNuFolioInfraccion,
			@Param("maxNuFolioInfraccion") String maxNuFolioInfraccion);
	
	@Select(BUSCA_ARCHIVO_EN_COMPLEMENTACION)
	public Integer buscarArchivoEnComplementacion(
			@Param("enProceso") Integer enProceso);
	
	@Update(ACTUALIZA_FM_LOTE_PARA_COMPLEMENTACION)
	Integer actualizaFMLoteParaComplementacion(
			@Param("idLote") Long idLote, 
			@Param("enProceso") Integer enProceso);
	
	@Update(ACTUALIZA_RADAR_ARCHIVO_PARA_COMPLEMENTACION)
	Integer actualizaRadarArchivoParaComplementacion(
			@Param("idLote") Long idLote, 
			@Param("enProceso") Integer enProceso);
	
	@Update(ACTUALIZA_RADAR_ARCHIVO_TOTAL_PARA_COMPLEMENTACION)
	Integer actualizaRadarArchivoTotalParaComplementacion(
			@Param("idLote") Long idLote, 
			@Param("enProceso") Integer enProceso);
	
	@Insert(INSERTAR_BIT_PROCESO)
	public void insertarBitProceso(
			@Param("idLote") Long idLote, 
			@Param("usuario") Long usuario);
	
	@Insert(INSERTAR_FM_LOTE_BIT_PROCESO)
	public void insertarFMLoteBitProceso(
			@Param("idLote") Long idLote, 
			@Param("usuario") Long usuario);
	
	@Insert(INSERTAR_RADAR_ARCHIVO_BIT_PROCESO)
	public void insertarRadarArchivoBitProceso(
			@Param("idLote") Long idLote, 
			@Param("usuario") Long usuario);
	
	@Select(BUSCA_DELEGACION_POR_ID)
	public String buscarDelegacionPorId(
			@Param("idDelegacion") Long idDelegacion,
			@Param("idEstado") Long idEstado);
	
	@Select(BUSCA_ESTADO_POR_ID)
	public String buscarEstadoPorId(
			@Param("idEstado") Long idEstado);
	
	@Select(ID_DETECCION_POR_ARCHIVO_TIPO)
	public Long buscarIdDeteccionPorArchivoTipo(
			@Param("idDeteccion") Integer idDeteccion,
			@Param("idMarca") Integer idMarca);
	
	@Select(BUSCA_LOTES_CREADOS)
	public List<FMLotesVO> buscarLotesCreados();
	
	@Select(CAMPOS_OBLIGATORIOS_OPCIONALES)
	public FMDeteccionesLayoutVO consultaFMDeteccionesLayout(
			@Param("idDeteccion") Long idDeteccion,
			@Param("idOrigenPlaca") Long idOrigenPlaca);
	
	@Select(BUSCA_DETECCIONES_DISPONIBLES)
	public List<FMDeteccionesDisponiblesFCVO> buscarDetDisponibles(
			@Param("idTipoArchivoFCivica") Integer idTipoArchivoFCivica,
			@Param("idTipoFCivica") Integer idTipoFCivica);
	
	@Select(BUSCA_ARCHIVO_FOTOCIVICAS)
	public List<FMTipoArchivoFCVO> buscarArchivoFC();
	
	@Select(ACTUALIZA_CANT_PROCESADOS)
	public void actualizarCantProcs(
			@Param("radarArchivoId") Long radarArchivoId);
	
	@Select(ACTUALIZA_ST_COMP)
	public void actualizarStComp(
			@Param("radarArchivoId") Long radarArchivoId);
	
	@Update(UPDATE_COMPLEMENTA_OFICIAL_NOMBRE)
	public Integer complementaOficialNombre(
			@Param("radarArchivoId") Long radarArchivoId);
	
	@Update(UPDATE_VALIDA_FORMATO_PLACA)
	public Integer validaFormatoPlaca(
			@Param("radarArchivoId") Long radarArchivoId);
	
	@Select(PARAMETROS_QUERYS_CONDONACIONES)
	public List<Map<String, String>> buscarQuerys();
	
	@Select("SELECT CD_LLAVE_P_CONFIG, CD_VALOR_P_CONFIG FROM TAI041P_CONFIGURACION WHERE ST_ACTIVO = 1 AND NB_P_CONFIG = #{nbConfig}")
	public List<Map<String, String>> buscarParametrosPorNbConfig(
			@Param("nbConfig") String nbConfig);
	
	@Select(CONS_PERSONALIZADA)
	public String consLayotuNombreMes(
			@Param("consPersonalizada") String consPersonalizada);
	
	@Select(CONS_PERSONALIZADA)
	public String consLayotuNombreAnio(
			@Param("consPersonalizada") String consPersonalizada);
	
	@Select(CONS_PERSONALIZADA)
	public String consLayotuNombreTipoArchFC(
			@Param("consPersonalizada") String consPersonalizada);
	
	@Select(CONS_PERSONALIZADA)
	public String consLayotuNombreTipoFC(
			@Param("consPersonalizada") String consPersonalizada);
	
	@Select(CONS_PERSONALIZADA)
	public List<FMDetalleVO> consPlacasLote(
			@Param("consPersonalizada") String consPersonalizada);
	
	@Select(CONS_PERSONALIZADA)
	public List<String> consPlacasLB(
			@Param("consPersonalizada") String consPersonalizada);
	
	@Select(CONS_PERSONALIZADA)
	public void UpdateFormatoPlacas(
			@Param("consPersonalizada") String consPersonalizada);
}
