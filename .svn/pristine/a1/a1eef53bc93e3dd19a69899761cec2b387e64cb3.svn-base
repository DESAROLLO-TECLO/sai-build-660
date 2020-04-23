package mx.com.teclo.saicdmx.persistencia.mybatis.dao.administracion;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import mx.com.teclo.saicdmx.persistencia.vo.administracion.AdminCajaVO;

@Mapper
public interface CajaUsuarioAdminMyBatisDAO {

	 String CONSULTA_V_CAJAS = "SELECT EMP_ID, EMP_PLACA, EMP_TIPO,EMP_NOMBRE, CAJA_ID,CAJA_ESTATUS,CAJA_COD,"
             + "        DEP_ID,DEPOSITO,PERFIL_ID,PERFIL_NOMBRE,DERECHO_COBRO,TIENE_OPERACIONES "
             + " from V_CAJAS_CONSULTA WHERE CAJA_COD = #{valor} AND CODIGO_APLICACION = #{cd_aplicacion}";
	
	 
	 
	 @Select(CONSULTA_V_CAJAS)
     List<AdminCajaVO> consultaCaja (@Param("valor") String valor, @Param("cd_aplicacion") String cd_aplicacion); 	 
    
}
