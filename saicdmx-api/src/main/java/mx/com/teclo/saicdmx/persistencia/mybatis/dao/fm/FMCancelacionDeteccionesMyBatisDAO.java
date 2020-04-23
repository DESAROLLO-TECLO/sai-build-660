package mx.com.teclo.saicdmx.persistencia.mybatis.dao.fm;


import java.util.List;

import javax.persistence.PersistenceException;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.mapping.StatementType;

import mx.com.teclo.saicdmx.persistencia.vo.fm.FMConsultaDetalleDeteccionesVO;
import mx.com.teclo.saicdmx.persistencia.vo.fm.FMConsultaDeteccionesVO;
import mx.com.teclo.saicdmx.persistencia.vo.fm.FMDeteccionesFechasVO;


@Mapper
public interface FMCancelacionDeteccionesMyBatisDAO { //consultaFechasDetecciones
	
	String FechasDetecciones= "SELECT DISTINCT(TO_CHAR(TRUNC(to_date(TAI005.CD_FECHAHORA,'DD/MM/YYYY HH24:MI:SS')),'MM')) AS mes,"
			                  +"           	   TO_CHAR(TRUNC(to_date(TAI005.CD_FECHAHORA,'DD/MM/YYYY HH24:MI:SS')),'yyyy') AS anio "
			                  +" FROM TAI005D_FM_DETECCIONES TAI005 "
			                  +" WHERE TAI005.ST_PROCESADO=0 AND TAI005.ST_ACTIVO=1 "
			                  +" AND TRUNC(TO_DATE(TAI005.CD_FECHAHORA,'DD-MM-YYYY HH24:MI:SS')) <=#{fechaFin}"
			                  +" ORDER BY TO_CHAR(TRUNC(to_date(TAI005.CD_FECHAHORA,'DD/MM/YYYY HH24:MI:SS')),'MM') ASC ,"
			                  +"          TO_CHAR(TRUNC(to_date(TAI005.CD_FECHAHORA,'DD/MM/YYYY HH24:MI:SS')),'yyyy') ASC";
	@Select(value = FechasDetecciones)
	@Options(statementType = StatementType.CALLABLE)
	public List<FMDeteccionesFechasVO> consultaFechasDetecciones(@Param("fechaFin")String fechaFin) throws PersistenceException;
	
	String consultaDetecciones = "SELECT (SELECT COUNT(*) FROM TAI005D_FM_DETECCIONES TAI005 " 
            +" WHERE TAI005.ID_TIPO_DETECCION = TAI007.ID_TIPO_DETECCION "
            +" AND TAI005.ST_ACTIVO = 1 AND ST_PROCESADO = 0 AND TRUNC(TO_DATE(CD_FECHAHORA,'DD-MM-YYYY HH24:MI:SS')) BETWEEN #{fechaInicio} AND #{fechaFin}"
            +" AND TAI005.ID_ORIGENPLACA = CASE WHEN #{origenPlaca} = 1 OR #{origenPlaca} = 0  THEN #{origenPlaca}"
            +"     								   ELSE TAI005.ID_ORIGENPLACA END) AS TOTAL,"
            +" TAI009.NB_DISPOSITIVO AS RADAR,NB_DISPOSITIVO_DETECCION AS TIPO,TAI007.ID_ARCHIVO_TIPO_FORA AS MARCA,"
            +" TAI009.ID_MARCA_DISPOSITIVO_DET AS ID FROM TAI007C_FM_TIPO_DETECCIONES TAI007 " 
            +" LEFT JOIN TAI009C_FM_MARCA_DISPOSITIVO TAI009 ON (TAI009.ID_MARCA_DISPOSITIVO_DET = TAI007.ID_MARCA_DISPOSITIVO_DET) "
            +" WHERE TAI007.ID_TIPO_DETECCION IN(SELECT ID_TIPO_DETECCION FROM TAI007C_FM_TIPO_DETECCIONES "
            +" WHERE ID_MARCA_DISPOSITIVO_DET = CASE WHEN #{tipoRadar} <> 0 "
            +"                                       THEN #{tipoRadar} ELSE ID_MARCA_DISPOSITIVO_DET END"
            +" AND ID_ARCHIVO_TIPO_FORA =CASE WHEN #{tipoDeteccion} <> 0 THEN #{tipoDeteccion} ELSE  ID_ARCHIVO_TIPO_FORA END)"
            
            +" GROUP BY TAI007.ID_TIPO_DETECCION, TAI009.NB_DISPOSITIVO, NB_DISPOSITIVO_DETECCION,TAI007.ID_ARCHIVO_TIPO_FORA ,TAI009.ID_MARCA_DISPOSITIVO_DET "
            +" ORDER BY NB_DISPOSITIVO_DETECCION ASC";
	
	@Select(value = consultaDetecciones)
	@Options(statementType = StatementType.CALLABLE)
	public List<FMConsultaDeteccionesVO> consultaDeteccionesCancelar(@Param("fechaInicio") String fechaInicio,
																	 @Param("fechaFin")String fechaFin,
																	 @Param("tipoDeteccion") int tipoDeteccion,
														             @Param("tipoRadar") int tipoRadar, 
														             @Param("origenPlaca") int origenPlaca
														             )throws PersistenceException;
	
	
	String ObtenerFechaTop ="SELECT MIN(TO_CHAR(TRUNC(to_date(TAI005.CD_FECHAHORA,'DD/MM/YYYY HH24:MI:SS')),'YYYY')) AS fechaInicio "
			              +"      FROM TAI005D_FM_DETECCIONES TAI005 "
			              +"          WHERE TAI005.ST_PROCESADO=0 AND TAI005.ST_ACTIVO=1 "
			              +"      AND TRUNC(TO_DATE(TAI005.CD_FECHAHORA,'DD-MM-YYYY HH24:MI:SS')) <#{fechaFin}";
	@Select(value = ObtenerFechaTop)
	@Options(statementType = StatementType.CALLABLE)
	public String FechaMasViejaDeteccion(@Param("fechaFin") String fechaFin)throws PersistenceException;
	
	
	
	String ConsultaDetalleDetecciones ="SELECT TAI005.CD_PLACA AS placa,"
			                         +"        TAI005.CD_TDSKUID AS tdskuid,"
			                         +"        TO_CHAR(TRUNC(to_date(TAI005.CD_FECHAHORA,'DD/MM/YYYY HH24:MI:SS')),'DD/MM/YYYY') AS fecha,"
			                         +"        TO_CHAR(TO_DATE(CD_FECHAHORA, 'dd/MM/yyyy HH24:MI:SS'),'HH24:MI:SS' ) AS hora,"
			                         +"        TAI005.NB_NOMBRE ||' ' || TAI005.NB_APELLIDOPAT ||' ' || TAI005.NB_APELLIDOMAT AS nombre ,"
			                         +"        CD_OFICIALPLACA AS usuario,"
			                         +"        TO_CHAR(TRUNC(to_date(FH_MODIFICACION ,'DD/MM/YYYY HH24:MI:SS'))) AS fechavalidacion, "
                                     +"        TO_CHAR(TRUNC(to_date(FH_CREACION ,'DD/MM/YYYY HH24:MI:SS'))) AS fechacreacion, "
			                         +"        TAI007.NB_DISPOSITIVO_DETECCION AS tipoDeteccion,"
			                         +"        TAI009.NB_DISPOSITIVO  AS radar ,  "
			                         +"        TAI005.ID_ORIGENPLACA AS origenPlaca "
			                         +"FROM TAI005D_FM_DETECCIONES TAI005  "
			                         +"    JOIN TAI007C_FM_TIPO_DETECCIONES TAI007 ON (TAI005.ID_TIPO_DETECCION = TAI007.ID_TIPO_DETECCION)   "
			                         +"    JOIN TAI009C_FM_MARCA_DISPOSITIVO TAI009 ON (TAI009.ID_MARCA_DISPOSITIVO_DET = TAI007.ID_MARCA_DISPOSITIVO_DET)   "
			                         +"         WHERE TAI007.ID_TIPO_DETECCION IN (SELECT ID_TIPO_DETECCION FROM TAI007C_FM_TIPO_DETECCIONES "
			                         +"         WHERE ID_MARCA_DISPOSITIVO_DET = CASE WHEN #{tipoRadar} <> 0   "
			                         +"         THEN #{tipoRadar} ELSE ID_MARCA_DISPOSITIVO_DET END   "
			                         +"      AND ID_ARCHIVO_TIPO_FORA = CASE WHEN #{tipoDeteccion} <> 0     "
			                         +"                                      THEN #{tipoDeteccion} ELSE ID_ARCHIVO_TIPO_FORA END )     "
			                         +"     AND TAI005.ID_ORIGENPLACA = CASE WHEN #{origenPlaca} = 1 OR #{origenPlaca} = 0    "
			                         +"                                     THEN #{origenPlaca} ELSE TAI005.ID_ORIGENPLACA END  "
			                         +"     AND TAI005.ST_ACTIVO = 1 AND ST_PROCESADO = 0 "
			                         +"     AND TRUNC(TO_DATE(CD_FECHAHORA,'DD-MM-YYYY HH24:MI:SS')) BETWEEN #{fechaInicio} AND #{fechaFin} "
			                         +" ORDER BY TAI005.CD_TDSKUID  ASC";
			                       
	@Select(value = ConsultaDetalleDetecciones)
	@Options(statementType = StatementType.CALLABLE)
	public List<FMConsultaDetalleDeteccionesVO> consultaDetallesDetecciones(@Param("fechaInicio") String fechaInicio,
																	 @Param("fechaFin")String fechaFin,
																	 @Param("tipoDeteccion") int tipoDeteccion,
														             @Param("tipoRadar") int tipoRadar, 
														             @Param("origenPlaca") int origenPlaca
														             )throws PersistenceException;
/*caNCCELAR DETECCIONES */
	String DeteccionesParaCancelar=" update TAI005D_FM_DETECCIONES TAI005 "
			+" SET FH_CANCELACION=#{fechaCancelacion} ,"
			+"     TAI005.ST_ACTIVO='0' ,"
			+"     TAI005.ST_CANCELADO='1', "
			+"     TAI005.ID_MOT_CANCELACION='2',"
			+"     TAI005.CD_CANCELADOPOR =#{usuario} ,"
			+"     TAI005.TX_OBS_CANCELACION=#{motivo}"
			+"WHERE TAI005.ID_TIPO_DETECCION IN (SELECT ID_TIPO_DETECCION FROM TAI007C_FM_TIPO_DETECCIONES "
            +"         WHERE ID_MARCA_DISPOSITIVO_DET = CASE WHEN #{tipoRadar} <> 0   "
            +"         THEN #{tipoRadar} ELSE ID_MARCA_DISPOSITIVO_DET END   "
            +"      AND ID_ARCHIVO_TIPO_FORA = CASE WHEN #{tipoDeteccion} <> 0     "
            +"                                      THEN #{tipoDeteccion} ELSE ID_ARCHIVO_TIPO_FORA END )     "
            +"     AND TAI005.ID_ORIGENPLACA = CASE WHEN #{origenPlaca} = 1 OR #{origenPlaca} = 0    "
            +"                                     THEN #{origenPlaca} ELSE TAI005.ID_ORIGENPLACA END  "
            +"     AND TAI005.ST_ACTIVO = 1 AND ST_PROCESADO = 0 "
            +"     AND TRUNC(TO_DATE(CD_FECHAHORA,'DD-MM-YYYY HH24:MI:SS')) BETWEEN #{fechaInicio} AND #{fechaFin} ";
	@Update(value = DeteccionesParaCancelar)
	@Options(statementType = StatementType.CALLABLE)
	public long DeteccionesCancelarFM (@Param("fechaCancelacion")String fechaCancelacion,
									 @Param("usuario")long usuario,
									 @Param("fechaInicio") String fechaInicio,
									 @Param("fechaFin")String fechaFin,
									 @Param("tipoDeteccion") int tipoDeteccion,
									 @Param("tipoRadar") int tipoRadar, 
									 @Param("origenPlaca") int origenPlaca,
									 @Param("motivo")String motivo)throws PersistenceException;
	
}
