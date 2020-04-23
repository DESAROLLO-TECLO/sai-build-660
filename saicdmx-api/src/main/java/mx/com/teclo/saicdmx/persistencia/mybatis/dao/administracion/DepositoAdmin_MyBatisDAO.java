package mx.com.teclo.saicdmx.persistencia.mybatis.dao.administracion;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.StatementType;

import mx.com.teclo.saicdmx.persistencia.vo.administracion.AdminDepositoVO;


@Mapper
public interface DepositoAdmin_MyBatisDAO {

	String SP_ADMIN_USUARIO_DEPOSITO = "{CALL SP_ADMIN_USUARIO_DEPOSITO ("
			+ "#{p_operacion},"
			+ "#{p_emp_id},"
			+ "#{p_emp_caja_id},"
			+ "#{p_dep_id},"
			+ "#{p_modificado_por},"
			+ "#{cd_aplicacion_emp},"
			+ "#{p_resultado, jdbcType=NUMERIC, javaType=java.lang.Integer, mode=INOUT}, "
			+ "#{p_mensaje , jdbcType=VARCHAR, javaType=java.lang.String, mode=INOUT})}";
	
	@Select(value = SP_ADMIN_USUARIO_DEPOSITO)
	@Options(statementType = StatementType.CALLABLE)
	//UsuarioAdminVO 
	AdminDepositoVO modificaDeposito(AdminDepositoVO adminDepositoVO);

	@Select(" SELECT DEP_NOMBRE FROM DEPOSITOS WHERE DEP_ID = #{depId}  ")
	String getDepNombre(@Param("depId")int p_dep_id);
	
	@Select(" SELECT DEP_NOMBRE FROM DEPOSITOS D,DEPOSITOS_EMPLEADOS E " + 
			"           WHERE D.DEP_ID=E.DEP_ID " + 
			"           AND E.EMP_ID= #{empId} ")
	String getDepNombreOld(@Param("empId")Long empId);
	
	@Select(" SELECT DEP_NOMBRE FROM DEPOSITOS D,CAJAS C " + 
			"           WHERE D.DEP_ID=C.DEP_ID " + 
			"           AND C.CAJA_ID= #{cajaId} ")
	String getDepNombreByCajaId(@Param("cajaId")Long cajaId);

	@Select(" SELECT "
			+ "PERFIL_ID FROM V_USUARIOS_CONSULTA "
			+ "WHERE EMP_ID = #{empId} ")
	Long getPerfilUsuarioByIdEmp(@Param("empId")Long empId);
	
}
