package mx.com.teclo.saicdmx.persistencia.mybatis.dao.controlsuministros;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import mx.com.teclo.saicdmx.persistencia.vo.controlsuministros.DelegadoAuxiliarVO;

@Mapper
public interface BuscarAuxiliarPorPlacaMyBatisDAO {

	String GET_OFICIALES = " select emp_placa as oficial_placa, emp_nombre || ' ' || emp_ape_paterno || ' ' || emp_ape_materno as oficial_nombre "
			+ " from EMPLEADOS where EMP_STATUS='A' and EMP_PLACA = #{oficial_placa} ";
			
		@Select(GET_OFICIALES)
		public DelegadoAuxiliarVO buscarAuxiliarPorPlaca(@Param("oficial_placa") String oficial_placa);

}
