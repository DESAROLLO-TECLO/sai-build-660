package mx.com.teclo.saicdmx.persistencia.mybatis.dao.administracion;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.StatementType;

import mx.com.teclo.saicdmx.persistencia.vo.administracion.AdminUsuarioClaveVO;
import mx.com.teclo.saicdmx.persistencia.vo.administracion.UsuarioAdminVO;
/**
 * @author javier07
 */
@Mapper
public interface ModificaUsuarioMyBatisDAO {
	
	String SP_MODIFICA_USUARIO = "{CALL SP_ADMIN_USUARIO_MODIF ("
															+ "#{modificadoPor},"
															+ "#{emp_id},"
															+ "#{emp_nombre},"
															+ "#{emp_ape_paterno},"
															+ "#{emp_ape_materno},"
															+ "#{emp_rfc},"
															+ "#{perfilWebNuevo},"
															+ "#{tipOficialNuevo},"
															+ "#{caja_id},"
															+ "#{cobroNuevo},"
															+ "#{cd_aplicacion},"
															+ "#{resultado, jdbcType=NUMERIC, javaType=java.lang.Integer, mode=INOUT}, "
															+ "#{mensaje, jdbcType=VARCHAR, javaType=java.lang.String, mode=INOUT})}";
	
	
	@Select(value = SP_MODIFICA_USUARIO)
	@Options(statementType = StatementType.CALLABLE)
	UsuarioAdminVO modficarUsuario(UsuarioAdminVO usuarioAdminVO);
	
	String SP_CAMBIA_CLAVE_USUARIO = "begin SP_ADMIN_PSWCHANGE ("
															+ "#{usuario_id},"
															+ "#{vieja_contra},"
															+ "#{nueva_contra},"
															+ "#{repetir_contra},"
															+ "#{resultado, jdbcType=NUMERIC, javaType=java.lang.Integer, mode=INOUT}, "
															+ "#{mensaje, jdbcType=VARCHAR, javaType=java.lang.String, mode=INOUT}); end;";
	
	@Select(value= SP_CAMBIA_CLAVE_USUARIO)
	@Options(statementType = StatementType.CALLABLE)
	AdminUsuarioClaveVO cambiarClaveAcceso(AdminUsuarioClaveVO adminUsuarioClaveVO);
	
	
	@Select("SELECT COUNT(*) FROM PERFIL_USUARIO PU INNER JOIN TSEG_CAT_APLICACIONES TCA ON PU.ID_APLICACION = TCA.ID_APLICACION WHERE PU.USU_ID = #{usuId} AND TCA.CD_APLICACION = #{codAplicacion}")
	Integer existeRegistroPerfilUsuario(@Param("usuId")Long usuId, @Param("codAplicacion")String codAplicacion );
	
	@Select("SELECT PERFIL_NOMBRE FROM PERFILES WHERE PERFIL_ID =  #{perfilId} ")
	String existeNombrePerfil(@Param("perfilId")String perfilId );
	
	
	
}
