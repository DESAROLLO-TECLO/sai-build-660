package mx.com.teclo.saicdmx.persistencia.mybatis.dao.administracion;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.StatementType;

import mx.com.teclo.saicdmx.persistencia.vo.administracion.UsuarioAdminVO;

import org.apache.ibatis.annotations.Param;
@Mapper
public interface AltaUsuarioMyBatisDAO {

	String SP_ALTA_USUARIO = "{CALL SP_ADMIN_USUARIO_NUEVO ("
			+ "#{modificadoPor},"
			+ "#{emp_nombre},"
			+ "#{emp_ape_paterno},"
			+ "#{emp_ape_materno},"
			+ "#{emp_placa},"
			+ "#{emp_rfc},"
			+ "#{perfilWebNuevo},"
			+ "#{tipOficialNuevo},"
			+ "#{cobroNuevo},"
			+ "#{cd_aplicacion},"
			+ "#{resultado, jdbcType=NUMERIC, javaType=java.lang.Integer, mode=INOUT}, "
			+ "#{mensaje, jdbcType=VARCHAR, javaType=java.lang.String, mode=INOUT})}";


	@Select(value = SP_ALTA_USUARIO)
	@Options(statementType = StatementType.CALLABLE)
	UsuarioAdminVO nuevoUsuario(UsuarioAdminVO usuarioAdminVO);

//	@Select("SELECT EMP_ID FROM EMPLEADOS WHERE EMP_PLACA = #{placaNum} ")
//			+ "and EMP_TIP_ID = #{tipoId}")
//	String getIdRegistro(@Param("placaNum") String placaNum);	
//		
//	@Select("SELECT p.PERFIL_NOMBRE FROM PERFILES p " + 
//			"inner join PERFIL_USUARIO u on " + 
//			"p.PERFIL_ID = u.PERFIL_ID " + 
//			"where u.USU_ID = #{idUsuario} ")
//	String getPerfilUsuario(@Param("idUsuario")String idRegistro);
	
	@Select("SELECT NVL(MAX(EMP_ID),0) + 1 FROM EMPLEADOS")
	String getIdRegistroEmpleadoSiguiente();	
	
	@Select("SELECT PERFIL_NOMBRE FROM PERFILES WHERE PERFIL_ID = #{p_emp_perfil_id}") 
	String getNombrePerfilUsuario(@Param("p_emp_perfil_id") String p_emp_perfil_id);	
}
