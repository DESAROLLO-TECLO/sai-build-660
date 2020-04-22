package mx.com.teclo.saicdmx.persistencia.mybatis.dao.vempleadodepautoriza;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import mx.com.teclo.saicdmx.persistencia.vo.empleados.VEmpleadoDepAutorizaVO;

@Mapper
public interface VEmpleadoDepAutorizaMyBatisDAO {
	
	String GET_V_EMPLEADO_DEP_AUTORIZA = "SELECT "
			+ "EMP_ID AS empId, "
			+ "EMP_NOMBRE AS empNombre, "
			+ "EMP_PLACA AS empPlaca "
			+ "FROM V_EMPLEADO_DEP_AUTORIZA "
			+ "WHERE EMP_PLACA = #{v1} AND EMP_PWD = #{v2} AND DEP_ID = #{v3}";
	
	@Select(GET_V_EMPLEADO_DEP_AUTORIZA)
	public List<VEmpleadoDepAutorizaVO> buscarVEmpleadoDepAutorizaVOporEmpIdNombrePlaca(@Param("v1") String v1, @Param("v2") String v2, @Param("v3") String v3);
}
