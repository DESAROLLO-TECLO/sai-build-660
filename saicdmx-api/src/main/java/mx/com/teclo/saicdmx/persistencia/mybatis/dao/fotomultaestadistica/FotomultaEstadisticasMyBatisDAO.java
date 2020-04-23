package mx.com.teclo.saicdmx.persistencia.mybatis.dao.fotomultaestadistica;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import mx.com.teclo.saicdmx.persistencia.vo.fotomulta.FotoMultaDeteccionesRechazadasReporteVO;
import mx.com.teclo.saicdmx.persistencia.vo.fotomulta.FotoMultaEstPorPersonaVO;
import mx.com.teclo.saicdmx.persistencia.vo.fotomulta.FotoMultaEstPorTurno;
import mx.com.teclo.saicdmx.persistencia.vo.fotomulta.FotoMultaReporteSSPVO;

@Mapper
public interface FotomultaEstadisticasMyBatisDAO {

	String GET_PREVALIDACIONES_POR_PERSONA = "SELECT  FP1.USUARIO_PREVALIDADOR_ID AS prevalidadorId, "+
			"		 FCP.NOMBRE AS nombre, "+
			"        COUNT(FP1.TDSKUID) AS totalPrevalidaciones, "+
			"        COUNT(FP2.TDSKUID) AS prevalidacionesAceptadas, "+
			"        (COUNT(FP1.TDSKUID) - COUNT(FP2.TDSKUID)) AS prevalidacionesRechazadas, "+
			"        (CASE "+
			"          WHEN COUNT(FP1.TDSKUID) > 0 "+
			"            THEN ROUND(((COUNT(FP2.TDSKUID)/COUNT(FP1.TDSKUID))*100),2) || '%' "+
			"          ELSE '0%' "+
			"        END ) AS prevalidacionesPorcentaje, "+
			"        COUNT(FP3.TDSKUID) AS sspTotalValidadas, "+
			"        COUNT(FP4.TDSKUID) AS SSP_VALIDADA_ACEPTADA, "+
			"        (COUNT(FP3.TDSKUID) - COUNT(FP4.TDSKUID)) AS sspValidadaAceptada, "+
			"        (CASE "+
			"          WHEN COUNT(FP3.TDSKUID) > 0 "+
			"            THEN ROUND(((COUNT(FP4.TDSKUID)/COUNT(FP3.TDSKUID))*100),2) || '%' "+
			"          ELSE '0%' "+
			"        END ) AS sspPorcentaje, "+
			"       (COUNT(FP2.TDSKUID) - COUNT(FP3.TDSKUID)) AS sspPendiente "+
			"FROM    FOTOMULTA_CAT_PREVALIDADORES FCP "+
			"        FULL JOIN FOTOMULTA_PREVALIDACIONES FP1 "+
			"          ON FP1.USUARIO_PREVALIDADOR_ID = FCP.PREVALIDADOR_ID "+
			// "          AND FP1.ORIGEN_PLACA = 0 " +
			"        LEFT JOIN FOTOMULTA_PREVALIDACIONES FP2 "+
			"          ON  FP2.PREVALIDACION_ID = FP1.PREVALIDACION_ID "+
			"          AND FP2.ACEPTADA = 1 "+
			"        LEFT JOIN FOTOMULTA_PREVALIDACIONES FP3 "+
			"          ON  FP3.PREVALIDACION_ID = FP1.PREVALIDACION_ID "+
			"          AND FP3.VALIDADA_SSP = 1 "+
			"        LEFT JOIN FOTOMULTA_PREVALIDACIONES FP4 "+
			"          ON  FP4.PREVALIDACION_ID = FP3.PREVALIDACION_ID "+
			"          AND FP4.ACEPTADA_SSP = 1 "+
			"WHERE   FP1.DUPLICADA = 0 "+
			"AND     (CASE  "+
			"          WHEN #{canceladas} = 1   "+
			"            THEN 1 "+
			"          WHEN FP1.CANCELADA = 0   "+
			"            THEN 1 "+
			"        END) = 1 "+
			"AND     (CASE "+
			"          WHEN #{fechaInicio} IS NULL OR #{fechaFin} IS NULL "+
			"            THEN 1 "+
			"          WHEN TRUNC(FP1.FECHA_PREVALIDACION, 'DD') BETWEEN #{fechaInicio} AND #{fechaFin} "+
			"            THEN 1 "+
			"        END) = 1 "+
			"GROUP BY FCP.NOMBRE,FP1.USUARIO_PREVALIDADOR_ID "+
			"ORDER BY FCP.NOMBRE ASC";
	
	String GET_PREVALIDACIONES_POR_TURNO = "SELECT  TO_CHAR(FECHA_PREVALIDACION, 'dd/mm/yyyy') AS fechaPrevaliadacion, "+
			"        (CASE  "+
			"          WHEN TO_CHAR(FECHA_PREVALIDACION, 'HH24:mi:ss') < '13:59:59' "+
			"            THEN 'MATUTINO' "+
			"          ELSE 'VESPERTINO' "+
			"        END) AS turno, "+
			"        COUNT(PREVALIDACION_ID) AS total "+
			"FROM    FOTOMULTA_PREVALIDACIONES "+
			"WHERE   DUPLICADA = 0 "+
			//"AND     ORIGEN_PLACA = 0 "+
			"AND     (CASE  "+
			"          WHEN #{canceladas} = 1   "+
			"            THEN 1 "+
			"          WHEN CANCELADA = 0   "+
			"            THEN 1 "+
			"        END) = 1 "+
			"AND     (CASE "+
			"          WHEN #{fechaInicio} IS NULL OR #{fechaFin} IS NULL "+
			"            THEN 1 "+
			"          WHEN TRUNC(FECHA_PREVALIDACION, 'DD') BETWEEN #{fechaInicio} AND #{fechaFin} "+
			"            THEN 1 "+
			"        END) = 1 "+
			"GROUP BY  TO_CHAR(FECHA_PREVALIDACION, 'dd/mm/yyyy'), "+
			"          (CASE  "+
			"          WHEN TO_CHAR(FECHA_PREVALIDACION, 'HH24:mi:ss') < '13:59:59' "+
			"            THEN 'MATUTINO' "+
			"          ELSE 'VESPERTINO' "+
			"        END) "+
			"ORDER BY TO_DATE(fechaPrevaliadacion, 'dd/MM/yyyy'), turno";
	
	String GET_VALIDACIONES_PARA_REPORTE_SSP = "SELECT  FD.OFICIAL_PLACA AS placa, "+
			"		 FD.OFICIAL_NOMBRE AS nombre, "+
			"        COUNT(FD.FOTOMULTA_ID) AS totalValidaciones, "+
			"        COUNT(FD2.FOTOMULTA_ID) AS totalAceptadas, "+
			"        COUNT(FD3.FOTOMULTA_ID) AS totalRechazadas, "+
			"        (CASE "+
			"          WHEN COUNT(FD.FOTOMULTA_ID) > 0 "+
			"            THEN ROUND(((COUNT(FD2.FOTOMULTA_ID)/COUNT(FD.FOTOMULTA_ID))*100),2) || '%' "+
			"          ELSE '0%' "+
			"        END ) AS porcentaje "+
			"FROM    FOTOMULTA_DETECCIONES FD "+
			"        LEFT JOIN FOTOMULTA_DETECCIONES FD2 "+
			"          ON FD.FOTOMULTA_ID = FD2.FOTOMULTA_ID "+
			"          AND     FD2.AUTORIZADA = 1 "+
			"        LEFT JOIN FOTOMULTA_DETECCIONES FD3 "+
			"          ON FD.FOTOMULTA_ID = FD3.FOTOMULTA_ID "+
			"          AND     FD3.AUTORIZADA = 0 "+
			"WHERE   FD.VALIDO = 1 "+
			"AND     (CASE "+
			"          WHEN #{fechaInicio} IS NULL OR #{fechaFin} IS NULL "+
			"            THEN 1 "+
			"          WHEN TRUNC(FD.FECHA_VALIDACION, 'DD') BETWEEN #{fechaInicio} AND #{fechaFin} "+
			"            THEN 1 "+
			"        END) = 1 "+
			"GROUP BY FD.OFICIAL_NOMBRE, FD.OFICIAL_PLACA "+
			"ORDER BY FD.OFICIAL_NOMBRE";
	
	/*************************************/
	
	String GET_PREVALIDACIONES_RECHAZADAS_PARA_REPORTE_GENERAL = "SELECT  CAT_PREVALIDADORES.NOMBRE AS NOMBRE,  "+
			"        PREVALIDACIONES.TDSKUID AS TDSKUID,  "+
			"        CAT_DESCARTE.MOTIVO_DESCRIPCION AS DESCRIPCION  "+
			"FROM    FOTOMULTA_PREVALIDACIONES PREVALIDACIONES  "+
			"          JOIN  FOTOMULTA_CAT_MOT_DESCARTE CAT_DESCARTE  "+
			"            ON  PREVALIDACIONES.MOTIVO_DESCARTE_ID = CAT_DESCARTE.MOTIVO_ID  "+
			"            AND PREVALIDACIONES.RADAR_TIPO_ID = CAT_DESCARTE.RADAR_TIPO_ID "+
			"          JOIN  FOTOMULTA_CAT_PREVALIDADORES CAT_PREVALIDADORES "+
			"            ON  PREVALIDACIONES.USUARIO_PREVALIDADOR_ID = CAT_PREVALIDADORES.PREVALIDADOR_ID  "+
			"AND     PREVALIDACIONES.ACEPTADA = 0  "+
			"AND     PREVALIDACIONES.DUPLICADA = 0 "+
			"AND     (CASE  "+
			"          WHEN #{canceladas} = 1   "+
			"            THEN 1 "+
			"          WHEN PREVALIDACIONES.CANCELADA = 0   "+
			"            THEN 1 "+
			"        END) = 1 "+
			"AND     (CASE "+
			"          WHEN #{fechaInicio} IS NULL OR #{fechaFin} IS NULL "+
			"            THEN 1 "+
			"          WHEN TRUNC(PREVALIDACIONES.FECHA_PREVALIDACION, 'DD') BETWEEN #{fechaInicio} AND #{fechaFin} "+
			"            THEN 1 "+
			"        END) = 1 "+
			"ORDER BY CAT_PREVALIDADORES.NOMBRE ASC";
	
	String GET_PREVALIDACIONES_RECHAZADAS_POR_PREVALIDADOR = "SELECT  PREVALIDACIONES.TDSKUID AS TDSKUID, "+
			"        CAT_DESCARTE.MOTIVO_DESCRIPCION AS DESCRIPCION "+
			"FROM    FOTOMULTA_PREVALIDACIONES PREVALIDACIONES "+
			"          JOIN  FOTOMULTA_CAT_MOT_DESCARTE CAT_DESCARTE "+
			"            ON  PREVALIDACIONES.MOTIVO_DESCARTE_ID = CAT_DESCARTE.MOTIVO_ID "+
			"            AND PREVALIDACIONES.RADAR_TIPO_ID = CAT_DESCARTE.RADAR_TIPO_ID "+
			"WHERE   PREVALIDACIONES.USUARIO_PREVALIDADOR_ID = #{prevalidadorId} "+
			"AND     PREVALIDACIONES.ACEPTADA = 0 "+
			"AND     PREVALIDACIONES.DUPLICADA = 0 "+
			"AND     (CASE  "+
			"          WHEN #{canceladas} = 1   "+
			"            THEN 1 "+
			"          WHEN CANCELADA = 0   "+
			"            THEN 1 "+
			"        END) = 1 "+
			"AND     (CASE "+
			"          WHEN #{fechaInicio} IS NULL OR #{fechaFin} IS NULL "+
			"            THEN 1 "+
			"          WHEN TRUNC(PREVALIDACIONES.FECHA_PREVALIDACION, 'DD') BETWEEN #{fechaInicio} AND #{fechaFin} "+
			"            THEN 1 "+
			"        END) = 1 "+
			"ORDER BY PREVALIDACIONES.FECHA_PREVALIDACION ASC";
	
	String GET_VALIDACIONES_RECHAZADAS_PARA_REPORTE_GENERAL_SSP = "SELECT  FOTOMULTA.OFICIAL_NOMBRE AS NOMBRE, "+
			"		 FOTOMULTA.TDSKUID AS TDSKUID, "+
			"        CAT_DESCARTE.MOTIVO_DESCRIPCION AS DESCRIPCION "+
			"FROM    FOTOMULTA_DETECCIONES FOTOMULTA "+
			"          JOIN  FOTOMULTA_CAT_MOT_DESCARTE CAT_DESCARTE "+
			"            ON  FOTOMULTA.MOTIVO_DESCARTE_ID = CAT_DESCARTE.MOTIVO_ID "+
			"            AND FOTOMULTA.RADAR_TIPO_ID = CAT_DESCARTE.RADAR_TIPO_ID "+
			"WHERE   FOTOMULTA.VALIDO = 1 "+
			"AND     FOTOMULTA.AUTORIZADA = 0 "+
			"AND     (CASE "+
			"          WHEN #{fechaInicio} IS NULL OR #{fechaFin} IS NULL "+
			"            THEN 1 "+
			"          WHEN TRUNC(FOTOMULTA.FECHA_VALIDACION, 'DD') BETWEEN #{fechaInicio} AND #{fechaFin} "+
			"            THEN 1 "+
			"        END) = 1 "+
			"ORDER BY FOTOMULTA.OFICIAL_NOMBRE";

	String GET_VALIDACIONES_RECHAZADAS_POR_PERSONA_SSP = "SELECT  FOTOMULTA.TDSKUID AS tdskuid, "+
			"        CAT_DESCARTE.MOTIVO_DESCRIPCION AS descripcion "+
			"FROM    FOTOMULTA_DETECCIONES FOTOMULTA "+
			"          JOIN  FOTOMULTA_CAT_MOT_DESCARTE CAT_DESCARTE "+
			"            ON  FOTOMULTA.MOTIVO_DESCARTE_ID = CAT_DESCARTE.MOTIVO_ID "+
			"            AND FOTOMULTA.RADAR_TIPO_ID = CAT_DESCARTE.RADAR_TIPO_ID "+
			"WHERE   FOTOMULTA.VALIDO = 1 "+
			"AND     FOTOMULTA.AUTORIZADA = 0 "+
			"AND     FOTOMULTA.ORIGEN_PLACA = 0 "+
			"AND     OFICIAL_PLACA = #{placa} "+
			"AND     (CASE "+
			"          WHEN #{fechaInicio} IS NULL OR #{fechaFin} IS NULL "+
			"            THEN 1 "+
			"          WHEN TRUNC(FOTOMULTA.FECHA_VALIDACION, 'DD') BETWEEN #{fechaInicio} AND #{fechaFin} "+
			"            THEN 1 "+
			"        END) = 1 "+
			"ORDER BY FOTOMULTA.FOTOMULTA_ID";
	
	@Select(GET_PREVALIDACIONES_POR_PERSONA)
	public List<FotoMultaEstPorPersonaVO> buscaPrevalidacionesParaReportePorPersona(
			@Param("fechaInicio") Date fechaInicio, @Param("fechaFin") Date fechaFin, @Param("canceladas") Integer canceladas);
	
	@Select(GET_PREVALIDACIONES_POR_TURNO)
	public List<FotoMultaEstPorTurno> buscaPrevalidacionesParaReportePorTurno(
			@Param("fechaInicio") Date fechaInicio, @Param("fechaFin") Date fechaFin, @Param("canceladas") Integer canceladas);

	@Select(GET_VALIDACIONES_PARA_REPORTE_SSP)
	List<FotoMultaReporteSSPVO> buscaValidacionesParaReporteSSP(
			@Param("fechaInicio") Date fechaInicio, @Param("fechaFin") Date fechaFin);
	
	@Select(GET_PREVALIDACIONES_RECHAZADAS_PARA_REPORTE_GENERAL)
	public List<FotoMultaDeteccionesRechazadasReporteVO> buscaPrevalidacionesRechazadasReporteGeneral(
			@Param("fechaInicio") Date fechaInicio, @Param("fechaFin") Date fechaFin, @Param("canceladas") Integer canceladas);

	@Select(GET_PREVALIDACIONES_RECHAZADAS_POR_PREVALIDADOR)
	public List<FotoMultaDeteccionesRechazadasReporteVO> buscaPrevalidacionesRechazadasReportePorPrevalidador(
			@Param("prevalidadorId") Long prevalidadorId,
			@Param("fechaInicio") Date fechaInicio, @Param("fechaFin") Date fechaFin, @Param("canceladas") Integer canceladas);
	
	@Select(GET_VALIDACIONES_RECHAZADAS_PARA_REPORTE_GENERAL_SSP)
	public List<FotoMultaDeteccionesRechazadasReporteVO> buscaDeteccionesRechazadasParaReporteGeneralSSP(
			@Param("fechaInicio") Date fechaInicio, @Param("fechaFin") Date fechaFin);
	
	@Select(GET_VALIDACIONES_RECHAZADAS_POR_PERSONA_SSP)
	public List<FotoMultaDeteccionesRechazadasReporteVO> buscaDeteccionesRechazadasParaReportePorPersonaSSP(
													@Param("placa") String placa, 
													@Param("fechaInicio") Date fechaInicio, 
													@Param("fechaFin") Date fechaFin);
}
