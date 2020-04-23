package mx.com.teclo.saicdmx.persistencia.mybatis.dao.administracion;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.StatementType;

import mx.com.teclo.saicdmx.persistencia.vo.administracion.AdminPerfilesParamVO;
import mx.com.teclo.saicdmx.persistencia.vo.administracion.PerfilUsuarioVO;

@Mapper
public interface AdminPerfilesAdminMyBatisDAO {

   String SP_ADMIN_PERFIL_WEB = "{CALL SP_ADMIN_PERFIL_WEB ("
			+ "#{operacionTipo},"
			+ "#{perfilId},"
			+ "#{perfilNombre},"
			+ "#{menuId},"
			+ "#{empleadoId},"
			+ "#{cdAplicacion},"
//			+ "#{jerarquiaPerfil},"
			+ "#{resultado, jdbcType=NUMERIC, javaType=java.lang.Integer, mode=INOUT}, "
			+ "#{mensaje, jdbcType=VARCHAR, javaType=java.lang.String, mode=INOUT})}";


	@Select(value = SP_ADMIN_PERFIL_WEB)
	@Options(statementType = StatementType.CALLABLE)
	AdminPerfilesParamVO crudPerfilesWeb(AdminPerfilesParamVO parametros);
   
	String CONSULTA_PERFIL_USUARIO = "SELECT PU.PERFIL_ID AS perfilId, "
			+"PU.USU_ID AS usuId, "
			+"PU.ID_APLICACION AS IdAplicacion "
			+"FROM PERFIL_USUARIO PU "
			+"JOIN TSEG_CAT_APLICACIONES TCA ON "
			+"PU.ID_APLICACION = TCA.ID_APLICACION WHERE "
			+"PU.USU_ID = #{usuId} AND TCA.CD_APLICACION = #{cdAplicacion}";

	@Select(value = CONSULTA_PERFIL_USUARIO)
	PerfilUsuarioVO consultaPerfilUsuario(@Param("usuId")Long usuId,@Param("cdAplicacion") String cdAplicacion);

}
