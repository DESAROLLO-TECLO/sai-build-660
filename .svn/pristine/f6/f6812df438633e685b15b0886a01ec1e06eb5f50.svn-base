package mx.com.teclo.saicdmx.persistencia.mybatis.dao.garantias;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import mx.com.teclo.saicdmx.persistencia.vo.garantias.VSSPGarantiaReporteGeneralFVO;

@Mapper
public interface GarantiaReporteGralMyBatisDAO {

	String GET_REPORTE_GARANTIAS_GENERAL="SELECT INFRAC_NUM,INFRAC_FECHA,INFRAC_PLACA,EMP_PLACA,"
			+ "EMP_NOMBRE,DOCUMENTO_NOMBRE,DOCUMENTO_FOLIO,PROCESO_RECEPCION,RECIBIDA,"
			+ "DEPOSITO,PAGADA,CANCELADA,OBSERVACIONES FROM V_GARANTIAS_REPORTE_GRAL "
			+ "WHERE TRUNC(FECHA_CONSULTA,'DD')>=to_date(#{fechaInicio},'dd/mm/yyyy') "
			+ "AND TRUNC(FECHA_CONSULTA,'DD')<=to_date(#{fechaFin},'dd/mm/yyyy') "
			+ "AND rownum <=20000";
	
	@Select(GET_REPORTE_GARANTIAS_GENERAL)
	
	List<VSSPGarantiaReporteGeneralFVO> consultaReporteGeneral(
			@Param("fechaInicio") String fechaInicio,
			@Param("fechaFin") String fechaFin);
		
		
	
}
