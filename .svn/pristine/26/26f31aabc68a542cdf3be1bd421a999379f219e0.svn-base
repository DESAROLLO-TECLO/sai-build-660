package mx.com.teclo.saicdmx.persistencia.mybatis.dao.administracion;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import mx.com.teclo.saicdmx.persistencia.vo.certificados.ConsultaUsersVO;

@Mapper
public interface ConsultaUsuarios_AdminMyBatisDAO {

	String CONSULTA_USERS = "select codigo_aplicacion as cd_aplicacion, codigo_perfil as cd_perfil, emp_id, emp_placa, emp_rfc, emp_ape_paterno, emp_ape_materno, emp_nombre, perfil_web, perfil_hh, upc_nombre, reg_nombre,estatus,"
             + "caja_id,caja_cod,dep_id,dep_nombre,derecho_cobro,emp_tipo_id,emp_tipo_nombre,perfil_id,autorizada_p_cobro,tiene_operaciones"
             + " from V_USUARIOS_CONSULTA";
   
  
	 String CONSULTA_POR_PLACA = CONSULTA_USERS +" WHERE emp_placa = #{valor} AND codigo_aplicacion = #{cd_aplicacion}" ;
     String CONSULTA_POR_APELLIDO_PATERNO = CONSULTA_USERS +" WHERE emp_ape_paterno = #{valor} AND codigo_aplicacion = #{cd_aplicacion}" ;
     String CONSULTA_POR_NOMBRE = CONSULTA_USERS +" WHERE emp_nombre = #{valor} AND codigo_aplicacion = #{cd_aplicacion}" ;    
     String CONSULTA_POR_PERFIL = CONSULTA_USERS +"  WHERE perfil_id = #{valor} AND codigo_aplicacion = #{cd_aplicacion}";
     String CONSULTA_POR_EMPLADO_ID = CONSULTA_USERS +"  WHERE emp_id = #{valor} AND codigo_aplicacion = #{cd_aplicacion}";
 
     String CONSULTA_POR_PLACA_ACTIVOS = CONSULTA_POR_PLACA+ " AND estatus='A'";
     String CONSULTA_POR_APELLIDO_PATERNO_ACTIVOS = CONSULTA_POR_APELLIDO_PATERNO+ " and estatus='A'";
     String CONSULTA_POR_NOMBRE_ACTIVOS = CONSULTA_POR_NOMBRE+" AND estatus='A'";
     String CONSULTA_POR_PERFIL_ACTIVOS = CONSULTA_POR_PERFIL+" AND estatus='A'";
     String CONSULTA_POR_EMPLADO_ID_ACTIVOS = CONSULTA_POR_EMPLADO_ID+" AND estatus='A'"; 

     @Select(CONSULTA_POR_PLACA)
     List<ConsultaUsersVO> obtieneListaUsuariosPlaca (@Param("valor") String valor, @Param("cd_aplicacion") String cd_aplicacion); 	 
     @Select(CONSULTA_POR_APELLIDO_PATERNO)
     List<ConsultaUsersVO> obtieneListaUsuariosAPaterno (@Param("valor") String valor, @Param("cd_aplicacion") String cd_aplicacion);
     @Select(CONSULTA_POR_NOMBRE)
     List<ConsultaUsersVO> obtieneListaUsuariosNombre (@Param("valor") String valor, @Param("cd_aplicacion") String cd_aplicacion);     
     @Select(CONSULTA_POR_PERFIL)
     List<ConsultaUsersVO> obtieneListaUsuariosPerfil (@Param("valor") String valor, @Param("cd_aplicacion") String cd_aplicacion); 
     @Select(CONSULTA_POR_EMPLADO_ID)
     List<ConsultaUsersVO> obtieneListaUsuariosEmpId (@Param("valor") String valor, @Param("cd_aplicacion") String cd_aplicacion); 
     /*Usuarios ACTIVOS*/

     @Select(CONSULTA_POR_PLACA_ACTIVOS)
     List<ConsultaUsersVO> obtieneListaUsuariosPlaca_Activos (@Param("valor") String valor, @Param("cd_aplicacion") String cd_aplicacion); 	 
     @Select(CONSULTA_POR_APELLIDO_PATERNO_ACTIVOS)
     List<ConsultaUsersVO> obtieneListaUsuariosAPaterno_Activos (@Param("valor") String valor, @Param("cd_aplicacion") String cd_aplicacion);
     @Select(CONSULTA_POR_NOMBRE_ACTIVOS)
     List<ConsultaUsersVO> obtieneListaUsuariosNombre_Activos (@Param("valor") String valor, @Param("cd_aplicacion") String cd_aplicacion);     
     @Select(CONSULTA_POR_PERFIL_ACTIVOS)
     List<ConsultaUsersVO> obtieneListaUsuariosPerfil_Activos (@Param("valor") String valor, @Param("cd_aplicacion") String cd_aplicacion);
     @Select(CONSULTA_POR_EMPLADO_ID_ACTIVOS)
     List<ConsultaUsersVO> obtieneListaUsuariosEmpId_Activos (@Param("valor") String valor, @Param("cd_aplicacion") String cd_aplicacion); 
     
}
