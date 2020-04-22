package mx.com.teclo.saicdmx.persistencia.mybatis.dao.reportes;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import mx.com.teclo.saicdmx.persistencia.vo.reportes.CatalogoDinamicoVO;
import mx.com.teclo.saicdmx.persistencia.vo.reportes.TcaBitacoraCambiosVO;

@Mapper
public interface ReporteBitacoraAnterior {
	
	String catalogo_componentes_anterior="SELECT COMPONENTE_ID AS ID ,COMPONENTE_NOMBRE AS NOMBRE "
			                             +"FROM BITACORA_COMPONENTES WHERE VISIBLE_REPORTE=1"
			                             +"     ORDER BY COMPONENTE_NOMBRE ASC";
	
	String catalogo_conceptos_anterior="SELECT CONCEPTO_ID AS ID ,CONCEPTO_NOMBRE AS NOMBRE "
										+"FROM BITACORA_CONCEPTOS"
										+"   WHERE COMPONENTE_ID=#{componenteId} AND VISIBLE_REPORTE=1"
										+"   ORDER BY CONCEPTO_NOMBRE ASC";
	/*historica */
	String consulta_cambios_bitacora_anterior="SELECT COMPONENTES.COMPONENTE_NOMBRE nombreConponente,CONCEPTOS.CONCEPTO_NOMBRE nombreConcepto,"
										+"NVL(CAMBIOS.VALOR_ORIGINAL,' ') valorOriginal,NVL(CAMBIOS.VALOR_FINAL,'') valorFinal,"
										+"EMP.EMP_NOMBRE ||' '||EMP.EMP_APE_PATERNO||' '||EMP.EMP_APE_MATERNO modificadoPor,CAMBIOS.FECHA_CAMBIO fechaModificacion "
										+"  FROM BITACORA_CAMBIOS_HIST CAMBIOS "
										+" INNER JOIN BITACORA_COMPONENTES COMPONENTES ON(CAMBIOS.COMPONENTE_ID = COMPONENTES.COMPONENTE_ID) "
										+" INNER JOIN BITACORA_CONCEPTOS   CONCEPTOS ON (CAMBIOS.CONCEPTO_ID = CONCEPTOS.CONCEPTO_ID AND COMPONENTES.COMPONENTE_ID = CONCEPTOS.COMPONENTE_ID) "
										+" INNER JOIN EMPLEADOS EMP ON ( CAMBIOS.MODIFICADO_POR = EMP.EMP_ID) "
										+"  WHERE COMPONENTES.COMPONENTE_ID = #{componenteId} AND CONCEPTOS.CONCEPTO_ID IN (${ListaConceptosId}) "
										+"  AND TRUNC(CAMBIOS.FECHA_CAMBIO) BETWEEN TO_DATE(#{fechaInicio},'DD/MM/YYYY') AND TO_DATE(#{fechaFin},'DD/MM/YYYY') AND rownum <= 5000"
										+"ORDER BY CONCEPTOS.CONCEPTO_NOMBRE,CAMBIOS.FECHA_CAMBIO";


	/*Consultas Bitacora Nueva bitacora_Cambios*/
	String componente_Nueva_Bitacora="SELECT COMPONENTE_ID AS ID,COMPONENTE_NOMBRE AS NOMBRE "
			                              +" FROM BITACORA_COMPONENTES"
			                              +"    WHERE VISIBLE_REPORTE=1 ORDER BY COMPONENTE_ID ASC ";
	
	String concepto_nueva_bitacora="SELECT CONCEPTO_ID AS ID,CONCEPTO_NOMBRE AS NOMBRE"
			                       +" FROM BITACORA_CONCEPTOS"
			                       +"     WHERE COMPONENTE_ID =#{componenteId} AND VISIBLE_REPORTE = 1 "
			                       +"ORDER BY CONCEPTO_NOMBRE ASC";
	
	String cambios_nueva_bitacora ="SELECT COMPONENTES.COMPONENTE_NOMBRE nombreConponente,CONCEPTOS.CONCEPTO_NOMBRE nombreConcepto,"
			+"NVL(CAMBIOS.VALOR_ORIGINAL,' ') valorOriginal,NVL(CAMBIOS.VALOR_FINAL,'') valorFinal,"
			+"EMP.EMP_NOMBRE ||' '||EMP.EMP_APE_PATERNO||' '||EMP.EMP_APE_MATERNO modificadoPor,CAMBIOS.FECHA_CAMBIO fechaModificacion "
			+"  FROM BITACORA_CAMBIOS CAMBIOS "
			+" INNER JOIN BITACORA_COMPONENTES COMPONENTES ON(CAMBIOS.COMPONENTE_ID = COMPONENTES.COMPONENTE_ID) "
			+" INNER JOIN BITACORA_CONCEPTOS   CONCEPTOS ON (CAMBIOS.CONCEPTO_ID = CONCEPTOS.CONCEPTO_ID AND COMPONENTES.COMPONENTE_ID = CONCEPTOS.COMPONENTE_ID) "
			+" INNER JOIN EMPLEADOS EMP ON ( CAMBIOS.MODIFICADO_POR = EMP.EMP_ID) "
			+"  WHERE COMPONENTES.COMPONENTE_ID = #{componenteId} AND CONCEPTOS.CONCEPTO_ID IN (${ListaConceptosId}) "
			+"  AND TRUNC(CAMBIOS.FECHA_CAMBIO) BETWEEN TO_DATE(#{fechaInicio},'DD/MM/YYYY') AND TO_DATE(#{fechaFin},'DD/MM/YYYY') AND rownum <= 5000"
			+"ORDER BY CONCEPTOS.CONCEPTO_NOMBRE,CAMBIOS.FECHA_CAMBIO";
	
//	   +"        INNER JOIN TAI_BITACORA_COMPONENTES COMPONENTES ON (COMPONENTES.COMPONENTE_ID = CAMBIOS.COMPONENTE_ID)"
//       +"        INNER JOIN TAI_BITACORA_CONCEPTOS CONCEPTOS ON (CAMBIOS.CONCEPTO_ID = CONCEPTOS.CONCEPTO_ID)"
//       +"        INNER JOIN EMPLEADOS EMP ON ( CAMBIOS.CREADO_POR = EMP.EMP_ID)"
//	
	/*funciones bitacora enterior*/
	@Select (catalogo_componentes_anterior)
	List<CatalogoDinamicoVO> CatalogoComponentesAnterior();
	
	@Select (catalogo_conceptos_anterior)
	List<CatalogoDinamicoVO> CatalogoConceptosAnterior(@Param("componenteId")int componenteId);
	
	@Select (consulta_cambios_bitacora_anterior)
	List<TcaBitacoraCambiosVO> consultaCambiosBitAnterior(@Param("componenteId")long componenteId,
			                                              @Param("ListaConceptosId")String ListaConceptosId,
			                                              @Param("fechaInicio")String fechaInicio,
			                                              @Param("fechaFin")String fechaFin);
	/*Funciones bitacora nueva */
	@Select(componente_Nueva_Bitacora)
	List<CatalogoDinamicoVO> obtenerListaComponentes();
	
	@Select (concepto_nueva_bitacora)
	List<CatalogoDinamicoVO> obtenerListaConceptos(@Param("componenteId")long componenteId);
	
	@Select(cambios_nueva_bitacora)
	List<TcaBitacoraCambiosVO> consultaCambiosBitacora(@Param("componenteId")long componenteId,
            										   @Param("ListaConceptosId")String ListaConceptosId,
                                                       @Param("fechaInicio")String fechaInicio,
                                                       @Param("fechaFin")String fechaFin);
  
}
