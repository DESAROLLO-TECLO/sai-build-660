package mx.com.teclo.saicdmx.persistencia.mybatis.dao.reportes;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import mx.com.teclo.saicdmx.persistencia.vo.reportes.InfraccionesporTipoVehiculoVO;


@Mapper
public interface InfraccionesporTipoVehiculoMyBatisDAO {
	
	String consulta_infracciones_vehiculo="SELECT TO_CHAR(FECHA,'dd/MM/YYYY') fecha,FOLIO,TO_CHAR(FECHA_HORA,'DD/MM/YYYY HH24:MI:SS') fecha_Hora,"
										+"ARTICULO,FRACCION,TIPO_VEHICULO,DELEGACION "
										+"	FROM VW_TMM_JUN26_84 "
										+"WHERE TRUNC(FECHA) BETWEEN TO_DATE(#{fechaInicio},'dd/MM/YYYY') AND TO_DATE(#{fechaFin},'dd/MM/YYYY')"
										+" AND rownum <= 5000  ORDER BY FECHA ASC";
	
	/** cargado en base de datos
	 * @author Fernando Octavio Sanchez Chavez
	 * @param fechaInicio 
	 * @param FechaFin
	 * @return <InfraccionesporTipoVehiculoVO> listaInfracciones
	 * **/
	 @Select(consulta_infracciones_vehiculo)
	 List<InfraccionesporTipoVehiculoVO> consultaInfraccionesTipoVehiculo(@Param("fechaInicio")String fechaInicio,
			                                                              @Param("fechaFin")String fechaFin);

}
