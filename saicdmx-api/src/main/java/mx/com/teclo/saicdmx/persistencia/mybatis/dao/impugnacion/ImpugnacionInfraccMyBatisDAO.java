package mx.com.teclo.saicdmx.persistencia.mybatis.dao.impugnacion;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.StatementType;

import mx.com.teclo.saicdmx.persistencia.vo.impugnacion.ImpugnacionParametrosVO;
/**
 * 
 * @author javier07
 *
 */
@Mapper
public interface ImpugnacionInfraccMyBatisDAO {
	
	String SP_IMPUGNACION_INFRACS= "{CALL SP_IMPUGNACION_INFRACS("
	+ "#{tipo}, "
	+ "#{impugnacionId},"
	+ "#{infraccNum}, "
	+ "#{estatus}, "
	+ "#{empleadoId},"
	+ "#{referencia, jdbcType=VARCHAR, javaType=java.lang.String, mode=INOUT},"
	+ "#{resultado, jdbcType=VARCHAR, javaType=java.lang.String, mode=INOUT}, "
	+ "#{mensaje, jdbcType=VARCHAR, javaType=java.lang.String, mode=INOUT})}";
	
	@Select(value = SP_IMPUGNACION_INFRACS)
	@Options(statementType = StatementType.CALLABLE)
    public void callSPImpugnaInfracc(ImpugnacionParametrosVO impugnacionVO);
	

}
