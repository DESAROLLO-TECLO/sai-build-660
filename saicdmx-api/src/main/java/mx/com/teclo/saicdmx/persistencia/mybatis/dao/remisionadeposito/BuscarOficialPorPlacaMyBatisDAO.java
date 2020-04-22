package mx.com.teclo.saicdmx.persistencia.mybatis.dao.remisionadeposito;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import mx.com.teclo.saicdmx.persistencia.vo.controlsuministros.DelegadoAuxiliarVO;
import mx.com.teclo.saicdmx.persistencia.vo.empleados.EmpleadosPorPlacaVO;
import mx.com.teclo.saicdmx.persistencia.vo.remisionadeposito.OficialIngresoVO;

@Mapper
public interface  BuscarOficialPorPlacaMyBatisDAO {

	String GET_OFICIALES = " select emp_id, emp_placa, concat(EMP_APE_PATERNO,concat(' ', concat(EMP_APE_MATERNO, concat(' ', EMP_NOMBRE)))) "
	+ " nombre_comp,EMP_APE_PATERNO,EMP_APE_MATERNO,EMP_NOMBRE,a.agrp_id, agrp_nombre, s.sec_id, sec_nombre  "
	+ " from EMPLEADOS, agrupamientos a, sectores s  "
	+ " where EMP_STATUS='A' and (EMP_TIP_ID = 1 or EMP_TIP_ID = 7 or EMP_TIP_ID = 10) and  "
	+ " a.agrp_id = EMPLEADOS.agrp_id and s.sec_id = EMPLEADOS.sec_id and EMP_COD =  #{infrac_placa_empl} ";
	
			
		@Select(GET_OFICIALES)
		public OficialIngresoVO buscarOficialPorPlaca(@Param("infrac_placa_empl") String infrac_placa_empl);

		@Select(" select emp_id, emp_placa, concat(EMP_APE_PATERNO,concat(' ', concat(EMP_APE_MATERNO, concat(' ', EMP_NOMBRE)))) "
				+"	nombre_comp,EMP_APE_PATERNO,EMP_APE_MATERNO,EMP_NOMBRE,a.agrp_id, agrp_nombre, s.sec_id, sec_nombre  , EMP_COD "
				+" from EMPLEADOS, agrupamientos a, sectores s "
				+" where EMP_STATUS='A' AND emp_id = #{infrac_placa_empl} and "
 	 			+" a.agrp_id = EMPLEADOS.agrp_id and s.sec_id = EMPLEADOS.sec_id ")
		public OficialIngresoVO buscarOficialPorPlacaId(
				@Param("infrac_placa_empl")String infrac_placa_empl);
		
}
