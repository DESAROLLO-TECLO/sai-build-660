package mx.com.teclo.saicdmx.persistencia.mybatis.dao.reportes;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.StatementType;

import mx.com.teclo.saicdmx.persistencia.vo.reportes.CatalogoDinamicoVO;
import mx.com.teclo.saicdmx.persistencia.vo.reportes.InfraccionesDiariasVO;
import mx.com.teclo.saicdmx.persistencia.vo.reportes.ReporteInfraccionesGralVO;
import mx.com.teclo.saicdmx.persistencia.vo.reportes.RptInfraccionesEmpleado;
import mx.com.teclo.saicdmx.persistencia.vo.reportes.TotalInfraccionesporArticuloVO;


@Mapper
public interface ReporteDinamicoMyBatisDAO {
//	 String catalogo_reportes ="SELECT CABEZA.NB_TIPO_REPORTE AS cabeza,REPORTE.NB_REPORTE as nombreReporte, REPORTE.TX_URL  AS urlReporte, REPORTE.ID_REPORTE AS IdReporte"
//			  				    +"	 FROM TAQ020C_AR_TIPO_REPORTES CABEZA "
//			                    +" 		INNER JOIN TAQ004D_AR_REPORTES REPORTE ON (CABEZA.ID_TIPO_REPORTE = REPORTE.ID_TIPO_REPORTE)"
//			  				    +" 		INNER JOIN TAQ005D_AR_PERFILES_REPORTES PERFIL ON (REPORTE.ID_REPORTE = PERFIL.ID_REPORTE)"
//			  				    +"   WHERE PERFIL.ID_PERFIL=#{id} AND REPORTE.ST_ACTIVO=1 "
//			  				    +"   ORDER BY CABEZA.NB_TIPO_REPORTE ASC,REPORTE.NB_REPORTE ASC";
//	 
//	 String tipos_de_reportes = " SELECT DISTINCT(CABEZA.NB_TIPO_REPORTE) AS cabeza"
//			                   +"       FROM TAQ020C_AR_TIPO_REPORTES CABEZA "
//			                   +"             INNER JOIN TAQ004D_AR_REPORTES REPORTE ON (CABEZA.ID_TIPO_REPORTE = REPORTE.ID_TIPO_REPORTE)"
//			                   +"             INNER JOIN TAQ005D_AR_PERFILES_REPORTES PERFIL ON (REPORTE.ID_REPORTE = PERFIL.ID_REPORTE)"
//			                   +"       WHERE PERFIL.ID_PERFIL=5 AND REPORTE.ST_ACTIVO=1"
//			                   +" ORDER BY CABEZA.NB_TIPO_REPORTE ASC";
	 
	 String catalogo_nombres_depositos="SELECT DEP_ID AS id ,DEP_NOMBRE AS nombre"
			 							+"  FROM DEPOSITOS WHERE DEP_STATUS='A' ORDER BY DEP_NOMBRE ASC";
	 
     String catalogo_articulos="SELECT ART_ID AS id,"
    		                   +"      ART_NUMERO ||' '|| ART_FRACCION ||' '||ART_PARRAFO||' '||ART_INCISO AS nombre "
    		                   +"FROM  ARTICULOS WHERE ART_STATUS='A'"
    		                   +"      ORDER BY ART_NUMERO ASC";
     
     String catalogo_delegaciones = "SELECT DEL_ID AS id,DEL_NOMBRE AS nombre FROM DELEGACIONES WHERE EDO_ID = 9 AND DEL_STATUS = 'A' AND DEL_ID <99 ORDER BY DEL_NOMBRE ASC";
     
	 /*String de qwery para el reporte infracciones diarias **/
	 String consulta_Infracciones_Diarias ="SELECT DEP_NOMBRE AS DEPOSITO,TO_CHAR(CANTIDAD,'999,999,999,999') AS TOTAL "
			 							+"    FROM V_REP_INFRAC_DIA_TOTAL "
			 							+" WHERE TRUNC(FECHA) = TO_DATE(#{fechaInicio},'dd/MM/YYYY') AND rownum <= 5000"
			 							+" UNION ALL "
			 							+" SELECT '0 - TOTAL  ',TO_CHAR(SUM(CANTIDAD),'999,999,999,999') TOTAL FROM V_REP_INFRAC_DIA_TOTAL"
			 							+"  WHERE TRUNC(FECHA) = TO_DATE(#{fechaInicio},'dd/MM/YYYY') AND rownum <= 5000"
			 							+"GROUP BY 'TOTAL' ORDER BY DEPOSITO ASC,TOTAL ";
	 
	 String detalle_infracciones_diarias="SELECT * FROM (SELECT DEP_NOMBRE AS DEPOSITO,MEDIO_INGRESO AS MEDIO,TO_CHAR(CANTIDAD,'999,999,999,999') AS TOTAL "
			 				            +"  FROM V_REP_INFRAC_DIA_DET WHERE TRUNC(FECHA) =  TO_DATE(#{fechaInicio},'dd/MM/YYYY') AND rownum <= 5000"
			 				            +"GROUP BY DEP_NOMBRE, MEDIO_INGRESO,TO_CHAR(CANTIDAD,'999,999,999,999') "
			 				            +"UNION  "
			 				            +"  SELECT DEP_NOMBRE,'     TOTAL ',TO_CHAR(SUM(CANTIDAD),'999,999,999,999') AS TOTAL "
			 				            +"FROM V_REP_INFRAC_DIA_DET WHERE TRUNC(FECHA) =  TO_DATE(#{fechaInicio},'dd/MM/YYYY') AND rownum <= 5000"
			 				            +"  GROUP BY DEP_NOMBRE,'TOTAL: '"
			 				            +"UNION  "
			 				            +"SELECT '0','     TOTAL  ',TO_CHAR(SUM(CANTIDAD),'999,999,999,999') FROM V_REP_INFRAC_DIA_DET  "
			 				            +"    WHERE TRUNC(FECHA) = TO_DATE(#{fechaInicio},'dd/MM/YYYY') AND rownum <= 5000"
			 				            +" GROUP BY 'TOTAL ' "
			 				            +")ORDER BY DEPOSITO,MEDIO DESC ,TOTAL DESC";
	 
	 /*String para consulta Infracciones GRAL reporte */
	 String consulta_infracciones_Gral="SELECT TO_CHAR(TRUNC(to_date(INFRAC_FECHA_CORTA,'DD/MM/YYYY HH24:MI:SS'))) AS INFRAC_FECHA_CORTA,"
			 						  +"INFRAC_SECTOR,"
			                          +"INFRACCION,"
			                          +"VEHICULO_PLACA,"
			                          +"PLACA_EXPEDIDA,"
			                          +"NVL(IMPRESA,' '),"
			                          +"LICENCIA_TIPO,"
			                          +"NVL(INFRACTOR_LICENCIA,'n/a'),"
     	                              +"NVL(TARJETA_CIRCULACION,'SIN TARJETA') AS TARJETA_CIRCULACION,"
		                              +"INFRAC_FECHA,"
		                              +"INFRAC_ARTICULO,"
		                              +"INFRAC_FRACCION,"
		                              +"INFRAC_PARRAFO,"
		                              +"INFRAC_INCISO,"
		                              +"VEHICULO_MARCA,"
		                              +"VEHICULO_MODELO,"
		                              +"VEHICULO_COLOR,"
		                              +"VEHICULO_TIPO,"
		                              +"INFRAC_CALLE,"
		                              +"INFRAC_ENTRE_CALLE,"
		                              +"iNFRAC_Y_CALLE,"
		                              +"INFRAC_COLONIA,"
		                              +"INFRAC_DELEGACION,"
		                              +"OFICIAL_PLACA,"
		                              +"OFICIAL_NOMBRE"
			                          +"  FROM V_SSP_REP_INFRAC_CONS_GRAL"
			                          +" WHERE TRUNC(INFRAC_FECHA_CORTA) BETWEEN TO_DATE(#{fechaInicio},'dd/MM/YYYY') AND TO_DATE(#{fechaFin},'dd/MM/YYYY') AND rownum <= 5000"
			                          +" ORDER BY INFRAC_FECHA_CORTA ASC";
	 
	 /* Consulta para infracciones por delegaciones */
	 String consulta_infracciones_delegacion = "SELECT TO_CHAR(TRUNC(to_date(INFRAC_FECHA_CORTA,'DD/MM/YYYY HH24:MI:SS'))) AS INFRAC_FECHA_CORTA,"
			 						  +"INFRAC_DELEGACION,"
			 						  +"INFRAC_SECTOR,"
			                          +"INFRACCION,"
			                          +"VEHICULO_PLACA,"
			                          +"NVL(IMPRESA,' '),"
			                          +"LICENCIA_TIPO,"
			                          +"NVL(INFRACTOR_LICENCIA,'n/a'),"
     	                              +"NVL(TARJETA_CIRCULACION,'SIN TARJETA') AS TARJETA_CIRCULACION,"
		                              +"INFRAC_FECHA,"
		                              +"INFRAC_ARTICULO,"
		                              +"INFRAC_FRACCION,"
		                              +"INFRAC_PARRAFO,"
		                              +"INFRAC_INCISO,"
		                              +"VEHICULO_MARCA,"
		                              +"VEHICULO_MODELO,"
		                              +"VEHICULO_COLOR,"
		                              +"INFRAC_CALLE,"
		                              +"INFRAC_ENTRE_CALLE,"
		                              +"iNFRAC_Y_CALLE,"
		                              +"INFRAC_COLONIA,"		                             
		                              +"OFICIAL_PLACA,"
		                              +"OFICIAL_NOMBRE"
			                          +"  FROM V_SSP_REP_INFRAC_CONS_GRAL"
			                          +" WHERE TRUNC(INFRAC_FECHA_CORTA) BETWEEN TO_DATE(#{fechaInicio},'dd/MM/YYYY') AND TO_DATE(#{fechaFin},'dd/MM/YYYY') AND INFRAC_DELEGACION_ID IN (${id})"
			                          +" AND rownum <= 5000 ORDER BY INFRAC_FECHA_CORTA ASC";
	 
	 /*Consulta para infracciones por articulo */
	 String conuslta_Infracciones_Articulo="  SELECT TO_CHAR(TRUNC(to_date(INFRAC_FECHA_CORTA,'DD/MM/YYYY HH24:MI:SS'))) AS INFRAC_FECHA_CORTA,"
		                                      	  +"INFRAC_SECTOR,"
		                                      	  +"INFRACCION,"
		                                      	  +"VEHICULO_PLACA,"
		                                      	  +"PLACA_EXPEDIDA,"
		                                      	  +"NVL(IMPRESA,' '),"
		                                      	  +"LICENCIA_TIPO,"
		                                      	  +"NVL(INFRACTOR_LICENCIA,'n/a'),"
		                                      	  +"NVL(TARJETA_CIRCULACION,'SIN TARJETA') AS TARJETA_CIRCULACION,"
		                                      	  +"INFRAC_FECHA,"
		                                      	  +"INFRAC_ARTICULO,"
		                                      	  +"INFRAC_FRACCION,"
		                                      	  +"INFRAC_PARRAFO,"
		                                      	  +"INFRAC_INCISO,"
		                                      	  +"VEHICULO_MARCA,"
		                                      	  +"VEHICULO_MODELO,"
		                                      	  +"VEHICULO_COLOR,"
		                                      	  //+"VEHICULO_TIPO,"
		                                      	  +"INFRAC_CALLE,"
		                                      	  +"INFRAC_ENTRE_CALLE,"
		                                      	  +"iNFRAC_Y_CALLE,"
		                                      	  +"INFRAC_COLONIA,"
		                                      	  +"INFRAC_DELEGACION,"
		                                      	  +"OFICIAL_PLACA,"
		  		                                  +"OFICIAL_NOMBRE"
		                                      	  +"  FROM V_SSP_REP_INFRAC_CONS_GRAL"
		                                      	  +" WHERE TRUNC(INFRAC_FECHA_CORTA) BETWEEN TO_DATE(#{fechaInicio},'dd/MM/YYYY') AND TO_DATE(#{fechaFin},'dd/MM/YYYY')"
		                                      	  +"  AND INFRAC_ART_ID IN(${id}) "
		                                      	  +"AND rownum <= 5000 ORDER BY INFRAC_FECHA_CORTA ASC ";
	 
	 /*consulta Infracciones por articulo TOTAL Detalle */
	 String conuslta_Infracciones_Articulo_Total ="SELECT TO_CHAR(INFRAC_FECHA_CORTA,'dd/MM/YYYY')INFRAC_FECHA_CORTA,INFRAC_ARTICULO,INFRAC_FRACCION,INFRAC_PARRAFO,INFRAC_INCISO,TO_CHAR(SUM(TOTAL_INFRACS),'999,999,999,999') as total"
			                              +" FROM V_SSP_REP_INFRAC_CONS_TOT  "
			                              +" WHERE TRUNC(INFRAC_FECHA_CORTA) BETWEEN TO_DATE(#{fechaInicio},'dd/MM/YYYY') AND TO_DATE(#{fechaFin},'dd/MM/YYYY') AND INFRAC_ART_ID IN(${id}) AND rownum <= 5000"
			                              +"GROUP BY TO_CHAR(INFRAC_FECHA_CORTA,'dd/MM/YYYY'),INFRAC_ARTICULO, INFRAC_FRACCION, INFRAC_PARRAFO, INFRAC_INCISO   "
			                              +"UNION ALL  "
			                              +"  SELECT TO_CHAR(INFRAC_FECHA_CORTA,'dd/MM/YYYY')INFRAC_FECHA_CORTA,INFRAC_ARTICULO,'  ','TOTAL ','   ',TO_CHAR(SUM(TOTAL_INFRACS),'999,999,999,999') "
			                              +"    FROM V_SSP_REP_INFRAC_CONS_TOT  "
			                              +" WHERE TRUNC(INFRAC_FECHA_CORTA) BETWEEN TO_DATE(#{fechaInicio},'dd/MM/YYYY') AND TO_DATE(#{fechaFin},'dd/MM/YYYY') AND INFRAC_ART_ID IN(${id}) AND rownum <= 5000 "
			                              +" GROUP BY INFRAC_FECHA_CORTA,INFRAC_ARTICULO,'  ','TOTAL '  "
			                              +" ORDER BY INFRAC_FECHA_CORTA,INFRAC_ARTICULO ASC,INFRAC_PARRAFO,INFRAC_FRACCION DESC ";
	 
	 //// infracciones por Empleado 
	 String consulta_empleados = " SELECT EMP_ID AS id,EMP_PLACA AS placa ,EMP_NOMBRE||' '||EMP_APE_PATERNO||' '||EMP_APE_MATERNO AS nombre"
			                     +"      FROM EMPLEADOS WHERE EMP_PLACA IN (${PlacasOficial})"
			                     +"      ORDER BY EMP_NOMBRE ASC";
	 
	 String conuslta_infracciones_empleados ="SELECT TO_CHAR(FECHA,'dd/MM/YYYY') fechaCorta,INFRACCION as infraccion,TO_CHAR(FECHA_CORTA,'dd/MM/YYYY hh:mm:ss') fecha,"
			                                +"OFICIAL_NOMBRE as nombre,PLACA as placa"
			 								+"     FROM V_INFRACS_TOTAL_POR_OFICIAL"
			                                +" WHERE TRUNC(FECHA) BETWEEN TO_DATE(#{fechaInicio},'dd/MM/YYYY') AND TO_DATE(#{fechaFin},'dd/MM/YYYY')"
			 								+"      AND EMP_ID IN (${PlacasOficial}) AND rownum <= 5000  ORDER BY fechaCorta";
	 
//	 @Select(catalogo_reportes)
//	 List<ReporteDinamicoVO> obtenerListaReportes(@Param("id") Long idEmpleado);
//	 
//	 @Select (tipos_de_reportes)
//	 List<ReporteDinamicoVO> obtenerTitulosReportes(@Param("id") Long idEmpleado);
//	 
	 
	 @Select (catalogo_nombres_depositos)
	 List<CatalogoDinamicoVO> CatalogoNombreDepositos();
	 
	 @Select(catalogo_articulos)
	 List<CatalogoDinamicoVO> CatalogoArticulos();
	 
	 
	 /*Reporte Infracciones Diarias*/
	 @Select(consulta_Infracciones_Diarias)
	 List<InfraccionesDiariasVO> ConsultaInfraccionesDiarias(@Param("fechaInicio") String fechaInicio);
	 
	 @Select(detalle_infracciones_diarias)
	 List<InfraccionesDiariasVO> ConsultaInfraccionesDiariasDetalle(@Param("fechaInicio") String fechaInicio);
	 
	 /*Reporte Infracciones GRAL*/
	 @Select(consulta_infracciones_Gral)
	 List<ReporteInfraccionesGralVO> consultaDeteccionesGral(@Param("fechaInicio")String fechaInicio,
			                                                 @Param("fechaFin")String fechaFin);
	 /*Reporte Infracciones por Articulo*/
	 @Select(conuslta_Infracciones_Articulo)
	 @Options(statementType = StatementType.CALLABLE)
	 List<ReporteInfraccionesGralVO> consultaDeteccionesporArticulo(@Param("fechaInicio")String fechaInicio,
                                                                         @Param("fechaFin")String fechaFin,
                                                                         @Param("id")String id);
	 /*Reporte InfraccionesTOtalesporArticulo ,
	  * fechaInicio
	  * FechaFin
	  * List id 
	  * return */
	 @Select(conuslta_Infracciones_Articulo_Total)
	 @Options(statementType = StatementType.CALLABLE)
	 List<TotalInfraccionesporArticuloVO> consultaDeteccionesporArticuloDetalle(@Param("fechaInicio")String fechaInicio,
                                                                                @Param("fechaFin")String fechaFin,
                                                                                @Param("id")String id);
	 
	 
	 /*Reporte Infracciones por Delegacion */
	 @Select(catalogo_delegaciones)
	 List<CatalogoDinamicoVO> catalogoDelegaciones();
	 
	 @Select(consulta_infracciones_delegacion)
	 List<ReporteInfraccionesGralVO> conusltaInfraccionesDelegacion(@Param("fechaInicio")String fechaInicio,
			 														@Param("fechaFin")String fechaFin,
			 														@Param("id")String id);
	 
	 /*Reporte Infracciones por Empleado */
	 @Select(consulta_empleados)
	 List<CatalogoDinamicoVO> consultaEmpleados(@Param("PlacasOficial")String PlacasOficial);
	 
	 @Select(conuslta_infracciones_empleados)
	 List<RptInfraccionesEmpleado> consultaInfraccionesEmpleado(@Param("fechaInicio")String fechaInicio,
				                                                @Param("fechaFin")String fechaFin,
				                                                @Param("PlacasOficial")String PlacasOficial);

	
	 
	 
}
