package mx.com.teclo.saicdmx.persistencia.mybatis.dao.administracion;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.StatementType;

import mx.com.teclo.saicdmx.persistencia.vo.administracion.AdminCajaParametrosVO;
import mx.com.teclo.saicdmx.persistencia.vo.caja.VCajaConsultaVO;

@Mapper
public interface ModificaCajaUsuarioMyBatisDAO {


	String SP_ADMIN_USUARIO_CAJA = "{CALL SP_ADMIN_USUARIO_CAJA ("
			+ "#{p_operacion},"
			+ "#{p_emp_id},"
			+ "#{p_emp_caja_id},"
			+ "#{p_perfil_id},"
			+ "#{p_emp_puede_cobrar},"
			+ "#{p_caja_id},"
			+ "#{p_caja_emp_id},"
			+ "#{p_caja_emp_perfil_id},"
			+ "#{p_modificado_por},"
			+ "#{p_resultado, jdbcType=NUMERIC, javaType=java.lang.Integer, mode=INOUT}, "
			+ "#{p_mensaje, jdbcType=VARCHAR, javaType=java.lang.String, mode=INOUT})}";


	@Select(value = SP_ADMIN_USUARIO_CAJA)
	@Options(statementType = StatementType.CALLABLE)
	AdminCajaParametrosVO modficarCaja(AdminCajaParametrosVO adminCajaParametrosVO);
	
	@Select("SELECT EMP_ID FROM CAJAS WHERE CAJA_ID = #{caja_id} ")
	String getUsuarioIdFromCajaId(@Param ("caja_id")Long caja_id);
	
	@Select("SELECT AUTORIZADA_P_COBRO FROM CAJAS WHERE CAJA_ID = #{caja_id} ")
	String getEstatusCajaAutCobro(@Param ("caja_id")Long caja_id);
	
	@Select("SELECT COUNT(*) FROM DERECHOS WHERE USU_ID = #{usu_id} AND OPER_ID=6")
	Integer getDerechoCobro(@Param ("usu_id")Long usu_id);
	
	@Select("SELECT "
			+ "DEP_ID as depId, "
			+ "CAJA_COD as cajaCod, "
			+ "CAJA_NOMBRE as cajaNombre, "
			+ "CAJA_NUM_PAGO as cajaNumPago, "
			+ "CAJA_NUM_TRAN as cajaNumTran, "
			+ "CAJA_NUM_CORTE as cajaNumCorte, "
			+ "CAJA_STATUS as cajaEstatus, "
			+ "EMP_ID as empId "
			+ "FROM CAJAS WHERE CAJA_ID = #{caja_id} ")
	public VCajaConsultaVO getDatosCaja(@Param("caja_id") Long caja_id);

	@Select("SELECT 'FI'||TO_CHAR(10000 + MAX(param_cajas_ficticias)+1) AS cajaCodCajaFicticia FROM PARAMETROS")
	public String getCajaCodCajaFicticia();

	@Select("SELECT NVL(MAX(CAJA_ID),0) + 1 AS cajaId FROM CAJAS")
	public String getCajaIdCajaFicticia();
	
	@Select("SELECT NVL (MAX (CAJA_ID), 0) FROM EMPLEADOS_CAJAS WHERE CAJA_STATUS = 'A' AND EMP_ID = #{emp_id} ")
	public Long getCajaIdFromEmpId(@Param("emp_id") Long emp_id);
}
