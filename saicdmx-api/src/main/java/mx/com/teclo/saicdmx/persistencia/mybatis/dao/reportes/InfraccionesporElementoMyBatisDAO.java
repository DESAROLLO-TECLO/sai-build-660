package mx.com.teclo.saicdmx.persistencia.mybatis.dao.reportes;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import mx.com.teclo.saicdmx.persistencia.vo.reportes.InfraccionesporElementoVO;

@Mapper
public interface InfraccionesporElementoMyBatisDAO {
	
	String consulta_Infracciones_Elemento ="SELECT TO_CHAR(FECHA,'dd/MM/YYYY') FECHA_CORTA,PLACA placa,NOMBRE nombre,INFRACCION infraccion,TO_CHAR(FECHA,'DD/MM/YYYY HH24:MI:SS') FECHA,ARTICULO articulo,FRACCION fraccion,"
			                             +"PARRAFO parrafo,INCISO inciso,MOTIVACION motivacion,CALLE calle,ENTRECALLE entreCalle,YLACALLE ycalle,COLONIA colonia,DELEGACION delegacion"
			                             +" FROM VW_TMM_JUN04_83 "
			                             +"     WHERE TRUNC(FECHA_CORTA) BETWEEN TO_DATE(#{fechaInicio},'dd/MM/YYYY') AND TO_DATE(#{fechaFin},'dd/MM/YYYY')"
			                             +" AND  EMP_ID IN (${idPlacasOficiales}) AND rownum <= 5000"
			                             +" ORDER BY FECHA_CORTA ,PLACA ASC";
	
	/**
	 * @author: Fernando Octavio
	 * @param: fechaInicio,fechaFin,idPlacasOficiales
     * @return VO consultaInfracciones 
	 */
	@Select(consulta_Infracciones_Elemento)
	List<InfraccionesporElementoVO> consultaIfraccionesElemento(@Param("fechaInicio")String fechaInicio,
																@Param("fechaFin")String fechaFin,
																@Param("idPlacasOficiales")String idPlacasOficiales);
}
