package mx.com.teclo.saicdmx.persistencia.mybatis.dao.remisionadeposito;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.mapping.StatementType;

import mx.com.teclo.saicdmx.persistencia.vo.remisionadeposito.GruaConceVO;
import mx.com.teclo.saicdmx.persistencia.vo.remisionadeposito.IngresoInfraccionVO;

@Mapper
public interface AltaIngresoInfraccionMyBatisDAO {
    
	static final String SP_INGRESO_ADMIN = "begin Proc_Ingreso_Exceed_Admin ("
			+ "#{dep_usuario}, "
			+ "#{infrac_num_ctrl}, "
			+ "#{t_ingr_id}, "
			//+ "#{tipo_l_id}, "
			+ "#{causa_ingreso}, "
			+ "#{conse_prefijo}, "
			+ "#{deposito}, "
			+ "#{depo_origen}, "
			+ "#{grua_cod}, "
			+ "#{infrac_num_arrastre}, "
			+ "#{infrac_num}, "
			+ "#{infrac_placa_empl}, "
			+ "#{sec_cod}, "
			+ "#{placa_pre}, "
			+ "#{articulos}, "
			+ "#{usu_login}, "
			+ "#{emp_cod}, "
			+ "#{vtipo_cod}, "
			+ "#{infrac_placa}, "
			+ "#{vcolor_cod}, "
			+ "#{vmod_cod}, "
			+ "#{vmar_cod}, "
			+ "#{vorigen}, "
			+ "'2', "
			+ "#{num_serie_vehiculo}, "
			+ "#{resultado, jdbcType=VARCHAR, javaType=java.lang.String, mode=INOUT}," 
			+ "#{mensaje, jdbcType=VARCHAR, javaType=java.lang.String, mode=INOUT},"
			+ "#{resguardo, jdbcType=VARCHAR, javaType=java.lang.String, mode=INOUT}," 
			+ "#{asn, jdbcType=VARCHAR, javaType=java.lang.String, mode=INOUT}); end;";
	
//	------------------------		
	
	static final String SP_INGRESO_INFRAC = "begin Proc_Ingreso ("
			+ "#{dep_id}, "
			+ "#{infrac_num_ctrl}, "
			+ "#{t_ingr_id}, "
			+ "#{causa_id}, "
			+ "#{infrac_num}, "
			+ "#{resguardo}, "
			+ "#{asn}, "
			+ "#{v_sellado}, "
			+ "#{v_cajuela}, "
			+ "#{infrac_parametros_inv}, "
			+ "#{usu_id}, "
			+ "#{vtipo_cod}, "
			+ "#{num_serie_vehiculo}, "
			+ "#{infrac_num_arrastre, jdbcType=VARCHAR, javaType=java.lang.String, mode=INOUT}, "
			+ "#{resultado, jdbcType=VARCHAR, javaType=java.lang.String, mode=INOUT}, "
			+ "#{mensaje, jdbcType=VARCHAR, javaType=java.lang.String, mode=INOUT}, "
			+ "#{infrac_observacion},"
			+ "#{documento}, "
			+ "#{p_captura}); end;";
	
 //  ----------- SP_INGRESO_EMBARGO
	
	static final String SP_INGRESO_EMBARGO = "begin Proc_Ingreso_Embargo ("
			+ "#{dep_id}, "
			+ "#{deposito}, "
			+ "#{dep_usuario}, "
			+ "#{num_serie_vehiculo}, "
			+ "#{infrac_con_placa}, " 
			+ "#{infrac_placa}, "
			+ "#{vorigen}, "
			+ "#{vtipo_cod}, "
			+ "#{vmar_cod}, "
			+ "#{vmod_cod}, "
			+ "#{vcolor_cod}, "
			+ "#{documento}, "
			+ "#{causa_id}, "
			+ "#{t_ingr_id}, "
			+ "#{grua_cod}, "
			+ "#{infrac_placa_empl}, "
			+ "#{articulos}, " //infrac_ley_transporte
			+ "#{usu_login}, "
			+ "#{v_sellado}, "
			+ "#{v_cajuela}, "
			+ "#{infrac_parametros_inv}, " 
			+ "#{resultado, jdbcType=VARCHAR, javaType=java.lang.String, mode=INOUT}, " 
			+ "#{mensaje, jdbcType=VARCHAR, javaType=java.lang.String, mode=INOUT}, "
			+ "#{resguardo, jdbcType=VARCHAR, javaType=java.lang.String, mode=INOUT}, "
			+ "#{asn, jdbcType=VARCHAR, javaType=java.lang.String, mode=INOUT}, " 
			+ "#{infrac_num, jdbcType=VARCHAR, javaType=java.lang.String, mode=INOUT}, " 
			+ "#{infrac_observacion}, "
			+ "#{infrac_placa_empl}, "
			+ "#{agrp_nombre}, "
			+ "#{emp_ape_paterno}, "
			+ "#{emp_ape_materno}, "
			+ "#{emp_nombre}, "
			+ "#{p_captura}); end;";
	
//  ----------- SP_ALTA_INFRACCION 
	
	static final String SP_ALTA_INFRACCION = "begin Proc_Ingreso_Embargo ("
			+ "#{p_captura}); end;";
	
	static final String UPDATE_INVENTARIO = "update INVENTARIO set INVENTARIO.INGR_RESGUARDO = " 
			+ "nvl((select INGRESOS.INGR_RESGUARDO from INGRESOS where INGRESOS.INFRAC_NUM=INVENTARIO.INFRAC_NUM ),'0') " 
			+ "where INVENTARIO.INFRAC_NUM = #{infrac_num}";
	
	@Select(value = SP_INGRESO_INFRAC)
	@Options(statementType = StatementType.CALLABLE)	
	public IngresoInfraccionVO altaIngresoInfraccion(IngresoInfraccionVO ingresoInfracVO)throws PersistenceException;

	@Select(value = SP_INGRESO_ADMIN)
	@Options(statementType = StatementType.CALLABLE)	
	public IngresoInfraccionVO altaIngresoAdmin(IngresoInfraccionVO ingresoInfracVO)throws PersistenceException;

	@Select(value = SP_INGRESO_EMBARGO)
	@Options(statementType = StatementType.CALLABLE)	
	public IngresoInfraccionVO altaIngresoEmbargo(IngresoInfraccionVO ingresoInfracVO)throws PersistenceException;
	
	@Select(value = UPDATE_INVENTARIO)
	@Options(statementType = StatementType.CALLABLE)	
	public IngresoInfraccionVO updateInventario(IngresoInfraccionVO ingresoInfracVO)throws PersistenceException;
	
	@Select(value = SP_ALTA_INFRACCION)
	@Options(statementType = StatementType.CALLABLE)	
	public IngresoInfraccionVO altaInfraccion(IngresoInfraccionVO ingresoInfracVO)throws PersistenceException;

	@Select("SELECT INGR_RESGUARDO FROM INGRESOS WHERE INFRAC_NUM = #{infracNum}")
	public String getIngrResguardo(@Param(value="infracNum") String infracNum);

	@Select("SELECT "
			+ "c.CONSE_ID conseId,"
			+ "c.CONSE_COD  conseCod, " + 
			"  c.CONSE_PREFIJO  conse_prefijo, " + 
			"  c.CONSE_ARRASTRE  conseArrastre, " + 
			"  c.CONSE_NOMBRE  conseNombre, " + 
			"  c.CONSE_STATUS  conseStatus, "
			+" c.MODIFICADO_POR modificadoPor" + 
			"    FROM CONCESIONARIA c " + 
			" INNER JOIN GRUAS g "+
			"  ON (c.CONSE_ID = g.CONSE_ID) "+
			"  WHERE g.GRUA_COD = #{gruaCod} "+
			"  ORDER BY c.CONSE_ID DESC ")
	public GruaConceVO getAltaIngresoVO(@Param(value="gruaCod") String gruaCod);
	
}
