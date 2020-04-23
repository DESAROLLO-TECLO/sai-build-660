package mx.com.teclo.saicdmx.persistencia.mybatis.dao.administracion;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.mapping.StatementType;

import mx.com.teclo.saicdmx.persistencia.vo.administracion.OperacionesExtemporaneasVO;
import mx.com.teclo.saicdmx.persistencia.vo.caja.CajaVO;
import mx.com.teclo.saicdmx.persistencia.vo.caja.VCajaExtDesactivarVO;

@Mapper
public interface OperacionesExtemporaneasMyBatisDAO {

	String PROC_HABILITAR_EXTEMPORANEA = "{CALL PROC_HABILITAR_EXTEMPORANEA ("
			+"#{cajaId},"
			+"#{supervisorId},"
			+"#{fecha},"			
			+"#{resultado, jdbcType=VARCHAR, javaType=java.lang.String, mode=INOUT},"
			+"#{mensaje, jdbcType=VARCHAR, javaType=java.lang.String, mode=INOUT})}";
	
	String PROC_DESHABILITAR_EXTEMPORANEA = "{CALL PROC_DESHABILITAR_EXTEMPORANEA ("
			+"#{cajaId},"
			+"#{supervisorId},"				
			+"#{resultado, jdbcType=VARCHAR, javaType=java.lang.String, mode=INOUT},"
			+"#{mensaje, jdbcType=VARCHAR, javaType=java.lang.String, mode=INOUT})}";
	
	String CONSULTA_CAJAS ="SELECT CAJA_ID AS CAJAID, CAJA AS CAJACOD, EMP_PLACA AS PLACAEMPLEADO,EMPLEADO_NOMBRE AS NOMBREEMPLEADO FROM ICD.V_CAJA_EXT_ACTIVAR ";
	
	String CRITERIO_CADA_COD =CONSULTA_CAJAS+" WHERE CAJA = #{cajaCod}";
	
	String CRITERIO_EMP_PLACA =CONSULTA_CAJAS+" WHERE EMP_PLACA = #{empPlaca}";
	
	@Select(value = PROC_HABILITAR_EXTEMPORANEA)
	@Options(statementType = StatementType.CALLABLE)	
	public void habilitarExtemporanea(OperacionesExtemporaneasVO operacionesExtemporaneasVO) throws PersistenceException;
	
	@Select(value = PROC_DESHABILITAR_EXTEMPORANEA)
	@Options(statementType = StatementType.CALLABLE)	
	public void desabilitarExtemporanea(VCajaExtDesactivarVO vCajaExtDesactivarVO) throws PersistenceException;

	@Select(CRITERIO_CADA_COD)
	public CajaVO buscarCajasByCodCaja(@Param("cajaCod")String cajaCod);

	@Select(CRITERIO_EMP_PLACA)
	public CajaVO buscarCajasByEmpPlaca(@Param("empPlaca")String empPlaca);

	@Select("SELECT EMP_ID FROM EMPLEADOS_CAJAS WHERE CAJA_ID=#{idCaja}")
	public Long getIdEmpfromIdCaja(@Param("idCaja")Long idCaja);

}
