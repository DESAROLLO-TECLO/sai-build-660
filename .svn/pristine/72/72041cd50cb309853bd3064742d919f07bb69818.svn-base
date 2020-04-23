package mx.com.teclo.saicdmx.persistencia.mybatis.dao.certificados;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import mx.com.teclo.saicdmx.persistencia.vo.certificados.ConsultaUsersVO;

@Mapper
public interface ConsultaUsuariosMyBatisDAO {
	
	 String CONSULTA_USERS = "select emp_id, emp_placa, emp_rfc, emp_certificado, emp_ape_paterno, emp_ape_materno, emp_nombre, perfil_web, perfil_hh, upc_nombre, reg_nombre,estatus,"
             + "caja_id,caja_cod,dep_id,dep_nombre,derecho_cobro,emp_tipo_id,emp_tipo_nombre,perfil_id,autorizada_p_cobro,tiene_operaciones"
             + " from V_USUARIOS_EMPLEADO_CONSULTA ";
	 
	 //se quito la condición de  and estatus='A' por comparación con siidf
     String CONSULTA_POR_PLACA = CONSULTA_USERS +" WHERE emp_placa = #{valor} AND Codigo_App_Perfil = 'SA'";
     String CONSULTA_POR_APELLIDO_PATERNO = CONSULTA_USERS +" WHERE emp_ape_paterno = #{valor} AND Codigo_App_Perfil = 'SA'";
     String CONSULTA_POR_NOMBRE = CONSULTA_USERS +" WHERE emp_nombre = #{valor} AND Codigo_App_Perfil = 'SA'";    
     String CONSULTA_POR_PERFIL = CONSULTA_USERS +" WHERE perfil_id = #{valor} AND Codigo_App_Perfil = 'SA'" ;

     String CONSULTA_POR_PLACA_CERTIFICADOS = CONSULTA_USERS +" WHERE emp_placa = #{valor} " + " AND emp_certificado = #{valor2} AND Codigo_App_Perfil = 'SA'";
     String CONSULTA_POR_APELLIDO_PATERNO_CERTIFICADOS = CONSULTA_USERS +" WHERE emp_ape_paterno = #{valor} " + " AND emp_certificado = #{valor2} AND Codigo_App_Perfil = 'SA'";
     String CONSULTA_POR_NOMBRE_CERTIFICADOS = CONSULTA_USERS +" WHERE emp_nombre = #{valor} " + " AND emp_certificado = #{valor2} AND Codigo_App_Perfil = 'SA'";
     String CONSULTA_POR_PERFIL_CERTIFICADOS = CONSULTA_USERS +" WHERE perfil_id = #{valor} " +" AND emp_certificado = #{valor2} AND Codigo_App_Perfil = 'SA'";

     String CONSULTA_USERS_POR_EMPL_ID = CONSULTA_USERS + " WHERE emp_id = #{valor} AND Codigo_App_Perfil = 'SA'";
     
     @Select(CONSULTA_POR_PLACA)
     List<ConsultaUsersVO> obtieneListaUsuariosPlaca (@Param("valor") String valor); 	 
     @Select(CONSULTA_POR_APELLIDO_PATERNO)
     List<ConsultaUsersVO> obtieneListaUsuariosAPaterno (@Param("valor") String valor);
     @Select(CONSULTA_POR_NOMBRE)
     List<ConsultaUsersVO> obtieneListaUsuariosNombre (@Param("valor") String valor);     
     @Select(CONSULTA_POR_PERFIL)
     List<ConsultaUsersVO> obtieneListaUsuariosPerfil (@Param("valor") String valor); 
    
     
     @Select(CONSULTA_POR_PLACA_CERTIFICADOS)
     List<ConsultaUsersVO> obtieneListaUsuarios_Certificado_Placa (@Param("valor") String valor,@Param("valor2") String valor2); 
     @Select(CONSULTA_POR_APELLIDO_PATERNO_CERTIFICADOS)
     List<ConsultaUsersVO> obtieneListaUsuarios_Certificado_APaterno (@Param("valor") String valor,@Param("valor2") String valor2); 
     @Select(CONSULTA_POR_NOMBRE_CERTIFICADOS)
     List<ConsultaUsersVO> obtieneListaUsuarios_Certificado_Nombre (@Param("valor") String valor,@Param("valor2") String valor2); 
     @Select(CONSULTA_POR_PERFIL_CERTIFICADOS)
     List<ConsultaUsersVO> obtieneListaUsuarios_Certificado_Perfil (@Param("valor") String valor,@Param("valor2") String valor2);
     
     
     @Select(CONSULTA_USERS_POR_EMPL_ID)
     List<ConsultaUsersVO> obtieneListaUsuarios_Activos_Empl_id(@Param("valor") String valor);
     

   
}
