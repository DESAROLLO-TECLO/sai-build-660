package mx.com.teclo.saicdmx.persistencia.mybatis.dao.fotomultadetecciones;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import mx.com.teclo.saicdmx.persistencia.vo.fotomulta.DeteccionesSinProcesarVO;
import mx.com.teclo.saicdmx.persistencia.vo.fotomulta.FotoMultaInfraccionesLiberacionesVO;
import mx.com.teclo.saicdmx.persistencia.vo.fotomulta.FotoMultaUsuarioClasificacionVO;

@Mapper
public interface FotomultaDeteccionesReportesMyBatisDAO {
	
	
	
	String DETECCIONES_PREVALIDADAS_POR_RANGO_TIEMPO = "SELECT FCTR.CODIGO AS CODIGO, " +
			  "COUNT(PRE.PREVALIDACION_ID) AS NUMERODETECIONES, " +
		  	  "(SELECT COUNT(*) " +
		  	  "FROM  ICD.FOTOMULTA_PREVALIDACIONES PRE2 " +
		  	  "WHERE PRE2.ACEPTADA = 1 " +
		  	  "AND PRE2.DUPLICADA = 0 " +
		  	  "AND PRE2.VALIDADA_SSP = 0 " +
		  	  "AND PRE2.CANCELADA = 0 "+
		  	  "AND PRE2.Fecha_Creacion Between to_date(#{fechaInicio},'dd/MM/yy')  AND sysdate) "+
		  	  "AS TOTALDETECCIONES  " +
		  	  "FROM FOTOMULTA_PREVALIDACIONES PRE, " +
		  	  "FOTOMULTA_CAT_TIPO_RADAR FCTR " +
		  	  "WHERE FCTR.CAT_TIPO_RADAR_ID = PRE.RADAR_TIPO_ID " +
		  	  "AND PRE.ACEPTADA = 1 " +
		  	  "AND PRE.DUPLICADA = 0 " +
		  	  "AND PRE.VALIDADA_SSP = 0 " +
		  	  "AND PRE.CANCELADA = 0 "+
		  	  "AND PRE.Fecha_Creacion Between to_date(#{fechaInicio},'dd/MM/yy')  AND sysdate " +
		  	  "GROUP BY FCTR.CODIGO"; 

	String DETECCIONES_VALIDADAS_POR_RANGO_TIEMPO = "SELECT FCTR.CODIGO AS CODIGO, " +
			"COUNT(FD.FOTOMULTA_ID) AS NUMERODETECIONES, " +
			"(SELECT COUNT(*) " +
			"FROM ICD.FOTOMULTA_DETECCIONES FD2 " +
			"WHERE FD2.VALIDO = 1 " +
			"AND FD2.PROCESADO = 0" +
			"AND FD2.AUTORIZADA = 1 "+
			"AND FD2.Fecha_Creacion Between to_date(#{fechaInicio},'dd/MM/yy')  AND sysdate ) "+
			"AS TOTALDETECCIONES  " +
			"FROM ICD.FOTOMULTA_DETECCIONES FD, "+
			"ICD.FOTOMULTA_CAT_TIPO_RADAR FCTR " +
			"WHERE FCTR.CAT_TIPO_RADAR_ID = FD.RADAR_TIPO_ID " +
			"AND FD.VALIDO = 1 " +
			"AND FD.PROCESADO = 0 " +
			"AND FD.AUTORIZADA = 1 " +
			"AND FD.Fecha_Creacion Between to_date(#{fechaInicio},'dd/MM/yy')  AND sysdate "+
			"GROUP BY FCTR.CODIGO";
 
	String DETECCIONES_TOTAL_RANGO_TIEMPO = "SELECT  FCTR.CODIGO AS CODIGO,  "+
			"COUNT(FD.FOTOMULTA_ID) AS NUMERODETECIONES,  "+
			"(SELECT COUNT(*)"+
			" FROM ICD.FOTOMULTA_DETECCIONES FD2 "+ 
			" WHERE "+
            " FD2.PROCESADO = 0 "+
            "AND FD2.AUTORIZADA = 1 "+
			"AND FD2.Fecha_Creacion Between to_date(#{fechaInicio},'dd/MM/yy')  AND sysdate ) "+
            "AS TOTALDETECCIONES  " +
			"FROM ICD.FOTOMULTA_DETECCIONES FD, "+
			"ICD.FOTOMULTA_CAT_TIPO_RADAR FCTR "+
			"WHERE FCTR.CAT_TIPO_RADAR_ID = FD.RADAR_TIPO_ID  "+
			"AND FD.PROCESADO = 0"+ 
			"AND FD.AUTORIZADA = 1 "+ 
            "AND FD.Fecha_Creacion Between to_date(#{fechaInicio},'dd/MM/yy')  AND sysdate "+
			"GROUP BY FCTR.CODIGO";
	
	String DETECCIONES_INFRACCIONES = "SELECT FCTR.CODIGO AS CODIGO, " +
			"COUNT(FD.FOTOMULTA_ID) AS NUMERODETECIONES, " +
			"(SELECT COUNT(*) " +
			"FROM ICD.FOTOMULTA_DETECCIONES FD2 " +
			"WHERE FD2.VALIDO = 1 " +
			"AND FD2.PROCESADO = 0" +
			"AND FD2.AUTORIZADA = 1 "+
			"AND FD2.Fecha_Creacion Between to_date(#{fechaInicio},'dd/MM/yy')  AND sysdate ) "+
            "AS TOTALDETECCIONES  " +
			"FROM ICD.FOTOMULTA_DETECCIONES FD, "+
			"ICD.FOTOMULTA_CAT_TIPO_RADAR FCTR " +
			"WHERE FCTR.CAT_TIPO_RADAR_ID = FD.RADAR_TIPO_ID " +
			"AND FD.VALIDO = 1 " +
			"AND FD.PROCESADO = 0 " +
			"AND FD.AUTORIZADA = 1 " +
			"AND FD.Fecha_Cancelacion is null "+
			"AND FD.Fecha_Creacion Between to_date(#{fechaInicio},'dd/MM/yy')  AND sysdate "+
			"GROUP BY FCTR.CODIGO";
	
	String CLASIFICACION_ACEPTADAS = "SELECT  FCTR.CODIGO AS CODIGO,  "+
			"COUNT(FD.FOTOMULTA_ID) AS NUMERODETECIONES,  "+
			"(SELECT COUNT(*) "+
			" FROM ICD.FOTOMULTA_DETECCIONES FD2 "+ 
			"WHERE "+
            "FD2.VALIDO=1 "+
            "AND FD2.PROCESADO = 0 "+
            "AND FD2.AUTORIZADA = 1 "+
			"AND FD2.Fecha_Creacion Between to_date(#{fechaInicio},'dd/MM/yy')  AND sysdate ) "+
			"AS TOTALDETECCIONES  " +
			"FROM ICD.FOTOMULTA_DETECCIONES FD, "+
			"ICD.FOTOMULTA_CAT_TIPO_RADAR FCTR "+
			"WHERE FCTR.CAT_TIPO_RADAR_ID = FD.RADAR_TIPO_ID  "+
			"AND FD.VALIDO = 1 "+
			"AND FD.PROCESADO = 0 "+ 
			"AND FD.AUTORIZADA = 1 "+ 
        	"AND FD.Fecha_Creacion Between to_date(#{fechaInicio},'dd/MM/yy')  AND sysdate "+
			"GROUP BY FCTR.CODIGO";
	
	String CLASIFICACION_RECHAZADAS = "SELECT  FCTR.CODIGO AS CODIGO,  "+
			"COUNT(FD.FOTOMULTA_ID) AS NUMERODETECIONES,  "+
			"(SELECT COUNT(*) "+
			" FROM ICD.FOTOMULTA_DETECCIONES FD2 "+ 
			" WHERE "+
            "FD2.VALIDO=0 "+
            "AND FD2.PROCESADO = 0 "+
            "AND FD2.AUTORIZADA = 1 "+
			"AND FD2.Fecha_Creacion Between to_date(#{fechaInicio},'dd/MM/yy')  AND sysdate ) "+
			"AS TOTALDETECCIONES  " +
			"FROM ICD.FOTOMULTA_DETECCIONES FD, "+
			"ICD.FOTOMULTA_CAT_TIPO_RADAR FCTR "+
			"WHERE FCTR.CAT_TIPO_RADAR_ID = FD.RADAR_TIPO_ID  "+
			"AND FD.VALIDO = 0 "+
			"AND FD.PROCESADO = 0 "+ 
			"AND FD.AUTORIZADA = 1 "+ 
        	"AND FD.Fecha_Creacion Between to_date(#{fechaInicio},'dd/MM/yy')  AND sysdate "+
			"GROUP BY FCTR.CODIGO";
	
	
	String PREVALIDADAS_ACEPTADAS = "SELECT FCTR.CODIGO AS CODIGO, " +
			  "COUNT(PRE.PREVALIDACION_ID) AS NUMERODETECIONES, " +
		  	  "(SELECT COUNT(*) " +
		  	  "FROM  ICD.FOTOMULTA_PREVALIDACIONES PRE2 " +
		  	  "WHERE PRE2.ACEPTADA = 1 " +
		  	  "AND PRE2.DUPLICADA = 0 " +
		  	  "AND PRE2.VALIDADA_SSP = 0 " +
		  	  "AND PRE2.CANCELADA = 0 "+
		  	  "AND PRE2.Fecha_Creacion Between to_date(#{fechaInicio},'dd/MM/yy')  AND sysdate) "+
		  	  "AS TOTALDETECCIONES  " +
		  	  "FROM FOTOMULTA_PREVALIDACIONES PRE, " +
		  	  "FOTOMULTA_CAT_TIPO_RADAR FCTR " +
		  	  "WHERE FCTR.CAT_TIPO_RADAR_ID = PRE.RADAR_TIPO_ID " +
		  	  "AND PRE.ACEPTADA = 1 " +
		  	  "AND PRE.DUPLICADA = 0 " +
		  	  "AND PRE.VALIDADA_SSP = 0 " +
		  	  "AND PRE.CANCELADA = 0 "+
		  	  "AND PRE.Fecha_Creacion Between to_date(#{fechaInicio},'dd/MM/yy')  AND sysdate " +
		  	  "GROUP BY FCTR.CODIGO"; 
	
	String PREVALIDADAS_RECHAZADAS = "SELECT FCTR.CODIGO AS CODIGO, " +
			  "COUNT(PRE.PREVALIDACION_ID) AS NUMERODETECIONES, " +
		  	  "(SELECT COUNT(*) " +
		  	  "FROM  ICD.FOTOMULTA_PREVALIDACIONES PRE2 " +
		  	  "WHERE PRE2.ACEPTADA = 0 " +
		  	  "AND PRE2.DUPLICADA = 0 " +
		  	  "AND PRE2.VALIDADA_SSP = 0 " +
		  	  "AND PRE2.CANCELADA = 0 "+
		  	  "AND PRE2.Fecha_Creacion Between to_date(#{fechaInicio},'dd/MM/yy')  AND sysdate) "+
		  	  "AS TOTALDETECCIONES  " +
		  	  "FROM FOTOMULTA_PREVALIDACIONES PRE, " +
		  	  "FOTOMULTA_CAT_TIPO_RADAR FCTR " +
		  	  "WHERE FCTR.CAT_TIPO_RADAR_ID = PRE.RADAR_TIPO_ID " +
		  	  "AND PRE.ACEPTADA = 0 " +
		  	  "AND PRE.DUPLICADA = 0 " +
		  	  "AND PRE.VALIDADA_SSP = 0 " +
		  	  "AND PRE.CANCELADA = 0 "+
		  	  "AND PRE.Fecha_Creacion Between to_date(#{fechaInicio},'dd/MM/yy')  AND sysdate " +
		  	  "GROUP BY FCTR.CODIGO"; 

	String VALIDADAS_ACEPTADAS = "SELECT FCTR.CODIGO AS CODIGO, " +
			"COUNT(FD.FOTOMULTA_ID) AS NUMERODETECIONES, " +
			"(SELECT COUNT(*) " +
			"FROM ICD.FOTOMULTA_DETECCIONES FD2 " +
			"WHERE FD2.VALIDO = 1 " +
			"AND FD2.PROCESADO = 0" +
			"AND FD2.AUTORIZADA = 1 "+
			"AND FD2.Fecha_Creacion Between to_date(#{fechaInicio},'dd/MM/yy')  AND sysdate ) "+
			"AS TOTALDETECCIONES  " +
			"FROM ICD.FOTOMULTA_DETECCIONES FD, "+
			"ICD.FOTOMULTA_CAT_TIPO_RADAR FCTR " +
			"WHERE FCTR.CAT_TIPO_RADAR_ID = FD.RADAR_TIPO_ID " +
			"AND FD.VALIDO = 1 " +
			"AND FD.PROCESADO = 0 " +
			"AND FD.AUTORIZADA = 1 " +
			"AND FD.Fecha_Creacion Between to_date(#{fechaInicio},'dd/MM/yy')  AND sysdate "+
			"GROUP BY FCTR.CODIGO";
	
	String VALIDADAS_RECHAZADAS = "SELECT FCTR.CODIGO AS CODIGO, " +
			"COUNT(FD.FOTOMULTA_ID) AS NUMERODETECIONES, " +
			"(SELECT COUNT(*) " +
			"FROM ICD.FOTOMULTA_DETECCIONES FD2 " +
			"WHERE FD2.VALIDO = 0 " +
			"AND FD2.PROCESADO = 0" +
			"AND FD2.AUTORIZADA = 1 "+
			"AND FD2.Fecha_Creacion Between to_date(#{fechaInicio},'dd/MM/yy')  AND sysdate ) "+
			"AS TOTALDETECCIONES  " +
			"FROM ICD.FOTOMULTA_DETECCIONES FD, "+
			"ICD.FOTOMULTA_CAT_TIPO_RADAR FCTR " +
			"WHERE FCTR.CAT_TIPO_RADAR_ID = FD.RADAR_TIPO_ID " +
			"AND FD.VALIDO = 0 " +
			"AND FD.PROCESADO = 0 " +
			"AND FD.AUTORIZADA = 1 " +
			"AND FD.Fecha_Creacion Between to_date(#{fechaInicio},'dd/MM/yy')  AND sysdate "+
			"GROUP BY FCTR.CODIGO";
	

	String LIBERACIONES = "SELECT FD.LOTE_ID AS ID, FL.FECHA_LIBERACION AS fecha "
			+ "FROM FOTOMULTA_DETECCIONES FD , FOTOMULTA_LOTES FL "
			+ "WHERE FD.LOTE_ID = FL.LOTE_ID AND "
			+ "FECHA_LIBERACION BETWEEN TO_DATE(#{fechaInicio},'DD/MM/YY')  AND SYSDATE  ";
	
	String INFRACCIONES = "SELECT FD.FOTOMULTA_ID AS ID,FD.FECHA_CREACION AS FECHA FROM FOTOMULTA_DETECCIONES FD WHERE "
			+ " FD.VALIDO = 1  "
			+ "AND FD.PROCESADO = 0  "
			+ "AND FD.AUTORIZADA = 1  "
			+ "AND FD.FECHA_CANCELACION IS NULL "
			+ "AND FD.FECHA_CREACION BETWEEN TO_DATE(#{fechaInicio},'DD/MM/YY')  AND SYSDATE ";
	
	String USUARIOS_CLASIFICACION = "  SELECT FD.NOMBRE ||' '||FD.APELLIDO_PATERNO || ' '||FD.APELLIDO_MATERNO AS NOMBRE , "
			+ "COUNT(*) AS TOTAL "
			+ "FROM FOTOMULTA_DETECCIONES FD "
			+ "WHERE FD.PROCESADO = 0 "
			+ "AND FD.AUTORIZADA = 1  "
			+ "AND FD.FECHA_CREACION BETWEEN TO_DATE('01/01/2015','DD/MM/YY') "
			+ "AND SYSDATE GROUP BY FD.NOMBRE, FD.APELLIDO_PATERNO, FD.APELLIDO_MATERNO";
	
	
	
	@Select(value = DETECCIONES_PREVALIDADAS_POR_RANGO_TIEMPO)
	List<DeteccionesSinProcesarVO> buscarDeteccionesPrevalidadasPorRangoTiempo(@Param("fechaInicio") String fechaInicio);
	
	@Select(value = DETECCIONES_VALIDADAS_POR_RANGO_TIEMPO)
	List<DeteccionesSinProcesarVO> buscarDeteccionesValidadasPorRangoTiempo(@Param("fechaInicio") String fechaInicio);
	
	@Select(value = DETECCIONES_INFRACCIONES)
	List<DeteccionesSinProcesarVO> buscarDeteccionesInfracciones(@Param("fechaInicio") String fechaInicio);

	@Select(value = DETECCIONES_TOTAL_RANGO_TIEMPO)
	List<DeteccionesSinProcesarVO> buscarDeteccionesTotalPorRangoTiempo(@Param("fechaInicio") String fechaInicio);

	/******** ACEPTADAS - CANCELADAS *******/
	
	@Select(value = CLASIFICACION_ACEPTADAS)
	List<DeteccionesSinProcesarVO> buscarClasificacionAceptadas(@Param("fechaInicio") String fechaInicio);
	
	@Select(value = PREVALIDADAS_ACEPTADAS)
	List<DeteccionesSinProcesarVO> buscarPrevalidadasAceptadas(@Param("fechaInicio") String fechaInicio);

	@Select(value = VALIDADAS_ACEPTADAS)
	List<DeteccionesSinProcesarVO> buscarValidadasAceptadas(@Param("fechaInicio") String fechaInicio);
	
	@Select(value = CLASIFICACION_RECHAZADAS)
	List<DeteccionesSinProcesarVO> buscarClasificacionRechazadas(@Param("fechaInicio") String fechaInicio);
	
	@Select(value = PREVALIDADAS_RECHAZADAS)
	List<DeteccionesSinProcesarVO> buscarPrevalidadasRechazadas(@Param("fechaInicio") String fechaInicio);

	@Select(value = VALIDADAS_RECHAZADAS)
	List<DeteccionesSinProcesarVO> buscarValidadasRechazadas(@Param("fechaInicio") String fechaInicio);

	/******** LIBERADAS *******/
	@Select(value = LIBERACIONES)
	List<FotoMultaInfraccionesLiberacionesVO> buscarLiberaciones(@Param("fechaInicio") String fechaInicio);

	@Select(value = INFRACCIONES)
	List<FotoMultaInfraccionesLiberacionesVO> buscarInfracciones(@Param("fechaInicio") String fechaInicio);

	/******* CLASIFICACIÓN USUARIOS *****/
	@Select(value = USUARIOS_CLASIFICACION)
	List<FotoMultaUsuarioClasificacionVO> buscarUsuariosClasificacion(@Param("fechaInicio") String fechaInicio);
	
	
}
