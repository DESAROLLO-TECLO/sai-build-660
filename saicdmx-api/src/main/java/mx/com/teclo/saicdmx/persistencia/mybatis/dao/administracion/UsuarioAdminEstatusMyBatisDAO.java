package mx.com.teclo.saicdmx.persistencia.mybatis.dao.administracion;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.StatementType;

import mx.com.teclo.saicdmx.persistencia.vo.administracion.AdminUsuarioEstatusVO;

@Mapper
public interface UsuarioAdminEstatusMyBatisDAO {
	
	String SP_ADMIN_USUARIO_ESTATUS = "{CALL SP_ADMIN_USUARIO_ESTATUS ("
			+ "#{tipo_accion},"
			+ "#{usuario_id},"
			+ "#{modificado_por_id},"
			+ "#{cd_aplicacion},"
			+ "#{result_out, jdbcType=VARCHAR, javaType=java.lang.String, mode=INOUT}, "
			+ "#{message_out, jdbcType=VARCHAR, javaType=java.lang.String, mode=INOUT})}";


	@Select(value = SP_ADMIN_USUARIO_ESTATUS)
	@Options(statementType = StatementType.CALLABLE)
	AdminUsuarioEstatusVO modficarEstatusUsuario(AdminUsuarioEstatusVO adminUsuarioEstatusVO);

	@Select(" select emp_nombre || ' ' || emp_ape_paterno || ' ' || emp_ape_materno " + 
			" from empleados " + 
			" where emp_id = #{usuarioId} ")
	String getNombreOficial(@Param ("usuarioId")Long usuario_id);

	@Select("SELECT caja_id FROM CAJAS WHERE EMP_ID= #{usuarioId} ")
	Long getCajaExiste(@Param("usuarioId")Long usuario_id);
	
	@Select(" SELECT CAJA_COD  FROM CAJAS WHERE CAJA_ID = #{cajaId} ")
	String getCodCaja(@Param("cajaId")Long cajaExiste);

	@Select(" SELECT DEP_ID  FROM CAJAS WHERE CAJA_ID = #{cajaId} ")
	String getDepId(@Param("cajaId")Integer cajaExiste);
	
	@Select(" SELECT EMP_STATUS FROM empleados where emp_id = #{usuarioId} ")
	String getPlaca(@Param ("usuarioId")Long usuario_id);

}
