package mx.com.teclo.saicdmx.persistencia.mybatis.dao.fm;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.mapping.StatementType;

import mx.com.teclo.saicdmx.persistencia.vo.fm.FMEstadisticasDeteccionesVO;
import mx.com.teclo.saicdmx.persistencia.vo.fm.FMTiposDeteccionesVO;

@Mapper
public interface FMEstadisticasMyBatisDAO {
	String FROM_DET_TIPDET_MARC = 
		"FROM TAI005D_FM_DETECCIONES TAI005 "
		+ "	LEFT JOIN TAI007C_FM_TIPO_DETECCIONES TAI007 ON TAI007.ID_TIPO_DETECCION = TAI005.ID_TIPO_DETECCION "
		+ "	LEFT JOIN TAI009C_FM_MARCA_DISPOSITIVO TAI009 ON TAI007.ID_MARCA_DISPOSITIVO_DET = TAI009.ID_MARCA_DISPOSITIVO_DET ";
	
	String WHERE_DET_TIPDET_MARC = 
		"WHERE "
		+ "	TAI005.ST_ACTIVO = 1 "
		+ "	AND TRUNC(TO_DATE(TAI005.CD_FECHAHORA ,'dd/mm/yyyy HH24:MI:ss')) BETWEEN #{fechaInicio} AND #{fechaFin} "
		+ "	AND TAI007.ID_ARCHIVO_TIPO_FORA = CASE "
		+ "		WHEN #{tipoDeteccion} <> 0 "
		+ "			THEN #{tipoDeteccion} "
		+ "		ELSE TAI007.ID_ARCHIVO_TIPO_FORA "
		+ "	END "
		+ "	AND TAI009.ID_MARCA_DISPOSITIVO_DET = CASE "
		+ "		WHEN #{tipoRadar} <> 0 "
		+ "			THEN #{tipoRadar} "
		+ "		ELSE TAI009.ID_MARCA_DISPOSITIVO_DET "
		+ "	END "
		+ "	AND TAI005.ID_ORIGENPLACA = CASE "
		+ "		WHEN #{origenPlaca} = 1 "
		+ "			THEN 1 "
		+ "		WHEN #{origenPlaca} = 0 "
		+ "			THEN 0 "
		+ "		ELSE TAI005.ID_ORIGENPLACA "
		+ "	END "
//		+ "	AND	"
//		+ "	( "
//		+ "		CASE "
//		+ "			WHEN #{estatusproceso} = 0 "
//		+ "				THEN 1 "
//		+ "			WHEN(#{estatusproceso} = 1) AND TAI005.ST_CANCELADO = 1 "
//		+ "				THEN 1 "
//		+ "			WHEN(#{estatusproceso} = 2) AND TAI005.ST_PROCESADO = 1 "
//		+ "				THEN 1 "
//		+ "			WHEN(#{estatusproceso} = 3) AND TAI005.ST_LIBERADO = 1 "
//		+ "				THEN 1 "
//		+ "			WHEN(#{estatusproceso} = 4) AND (TAI005.ST_CANCELADO = 0 AND TAI005.ST_PROCESADO = 0 AND TAI005.ST_LIBERADO = 0) "
//		+ "				THEN 1 "
//		+ "		END "
//		+ "	) = 1 "
		+ "	AND	"
		+ "	( "
		+ "		( "
		+ "			CASE "
		+ "				WHEN (#{estProCancelado} = 1) "
		+ "						AND TAI005.ST_CANCELADO = 1 " // -- CANCELADO 
		+ "					THEN 1 "
		+ "			END "
		+ "		) = 1 "
		+ "		OR "
		+ "		( "
		+ "			CASE "
		+ "				WHEN (#{estProComplementado} = 1) "
		+ "						AND (TAI005.ST_CANCELADO = 0 AND TAI005.ST_PROCESADO = 1 AND TAI005.ST_LIBERADO = 0) "// -- COMPLEMENTADO 
		+ "					THEN 1 "
		+ "			END "
		+ "		) = 1 "
		+ "		OR "
		+ "		( "
		+ "			CASE "
		+ "				WHEN (#{estProLiberado} = 1) "
		+ "						AND (TAI005.ST_CANCELADO = 0 "
		+ "						AND TAI005.ST_LIBERADO = 1) "// -- LIBERADO 
		+ "					THEN 1 "
		+ "			END "
		+ "		) = 1 "
		+ "		OR "
		+ "		( "
		+ "			CASE "
		+ "				WHEN (#{estProSinProcesar} = 1) "
		+ "						AND (TAI005.ST_CANCELADO = 0 "
		+ "						AND TAI005.ST_PROCESADO = 0 "
		+ "						AND TAI005.ST_LIBERADO = 0) "// --SIN PROCESAR
		+ "					THEN 1 "
		+ "			END "
		+ "		) = 1 "
		+ "	)";
	
	String ESTADISTICAS_POR_TIPO_DETECCION =
		"SELECT "
		+ "	TAI007.ID_TIPO_DETECCION AS IDTIPODETECCION, "
		+ "	TAI007.ID_ARCHIVO_TIPO_FORA IDTIPOFORA, "
		+ "	TAI007.NB_DISPOSITIVO_DETECCION AS TIPODETECCION, "
		+ "	TAI009.ID_MARCA_DISPOSITIVO_DET AS IDMARCA, "
		+ "	TAI009.NB_DISPOSITIVO AS MARCA, "
		//+ "	TAI005.ID_ORIGENPLACA AS ORIGENPLACA, "
		+ "	COUNT("
		+ "		CASE "
		+ "			WHEN TAI005.ID_ORIGENPLACA = 1 "
		+ "				THEN 1 "
		+ "		END "
		+ "	) AS FORA, "
		+ "	COUNT( "
		+ "		CASE "
		+ "			WHEN TAI005.ID_ORIGENPLACA = 0 "
		+ "				THEN 1 "
		+ "		END "
		+ "	) AS CDMX, "
		+ "	count (*) AS TOTAL " 
		+ FROM_DET_TIPDET_MARC
		+ WHERE_DET_TIPDET_MARC
		+ "GROUP BY "
		+ "	TAI007.ID_TIPO_DETECCION, "
		+ "	TAI007.ID_ARCHIVO_TIPO_FORA, "
		+ "	TAI007.NB_DISPOSITIVO_DETECCION, "
		+ "	TAI009.ID_MARCA_DISPOSITIVO_DET, "
		+ "	TAI009.NB_DISPOSITIVO "
		//+ "	TAI005.ID_ORIGENPLACA "
		+ "ORDER BY "
		+ "	TAI007.ID_TIPO_DETECCION, "
		+ "	TAI007.NB_DISPOSITIVO_DETECCION, "
		+ "	TAI009.NB_DISPOSITIVO";
	
	@Select(ESTADISTICAS_POR_TIPO_DETECCION)
	public List<FMEstadisticasDeteccionesVO> estadisticarByTipoDeteccion(
			@Param("fechaInicio") Date fechaInicio,
			@Param("fechaFin") Date fechaFin,
			@Param("tipoDeteccion") Integer tipoDeteccion,
			@Param("tipoRadar") Integer tipoRadar,
			@Param("origenPlaca") Integer origenPlaca,
			@Param("estProCancelado") Integer estProCancelado,
			@Param("estProComplementado") Integer estProComplementado,
			@Param("estProLiberado") Integer estProLiberado,
			@Param("estProSinProcesar") Integer estProSinProcesar);
	
	String ESTADISTICAS_POR_TIPO_DELEGACION =
		"SELECT "
		+ "	RAUT.TMM_DELEGACIONID AS idDelegacion, "
		+ "	DELE.DEL_NOMBRE AS Delegacion, "
		+ "	COUNT( "
		+ "		CASE "
		+ "			WHEN TAI005.ID_ORIGENPLACA = 1 "
		+ "				THEN 1 "
		+ "			END "
		+ "	) AS FORA, "
		+ "	COUNT( "
		+ "		CASE "
		+ "			WHEN TAI005.ID_ORIGENPLACA = 0 "
		+ "				THEN 1 "
		+ "			END "
		+ "	) AS CDMX, "
		+ "	count (*) AS TOTAL "
		+ FROM_DET_TIPDET_MARC
		+ "	, RADAR_CAT_UT RAUT "
		+ "	LEfT JOIN DELEGACIONES DELE ON DELE.EDO_ID = 9 AND DELE.DEL_ID = RAUT.TMM_DELEGACIONID "
		+ WHERE_DET_TIPDET_MARC
		+ "	AND ( "
		+ "		RAUT.UT = TAI005.CD_UT "
		+ "		AND RAUT.ARCHIVO_TIPO_ID = TAI007.ID_ARCHIVO_TIPO_CDMX "
		+ "		AND RAUT.ESTATUS = 1 "
		+ "	) "
		+ "GROUP BY "
		+ "	RAUT.TMM_DELEGACIONID, "
		+ "	DELE.DEL_NOMBRE "
		+ "ORDER BY "
		+ "	RAUT.TMM_DELEGACIONID ASC";
	
	@Select(ESTADISTICAS_POR_TIPO_DELEGACION)
	public List<FMEstadisticasDeteccionesVO> estadisticarByTipoDelegacion(
			@Param("fechaInicio") Date fechaInicio,
			@Param("fechaFin") Date fechaFin,
			@Param("tipoDeteccion") Integer tipoDeteccion,
			@Param("tipoRadar") Integer tipoRadar,
			@Param("origenPlaca") Integer origenPlaca,
			@Param("estProCancelado") Integer estProCancelado,
			@Param("estProComplementado") Integer estProComplementado,
			@Param("estProLiberado") Integer estProLiberado,
			@Param("estProSinProcesar") Integer estProSinProcesar);
	
	String TIPODETECCION = 
		"SELECT "
		+ "	DISTINCT(ID_ARCHIVO_TIPO_FORA) AS idArchivoTipoFora, "
		+ "	NB_DISPOSITIVO_DETECCION AS nbDispositivoDeteccion "
		+ "FROM TAI007C_FM_TIPO_DETECCIONES";

	@Select(value = TIPODETECCION)
	@Options(statementType = StatementType.CALLABLE)
	public List<FMTiposDeteccionesVO> buscarTipoDeteccion() throws PersistenceException;
}
