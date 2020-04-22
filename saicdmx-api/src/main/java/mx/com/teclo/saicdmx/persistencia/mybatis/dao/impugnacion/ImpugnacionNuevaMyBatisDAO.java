package mx.com.teclo.saicdmx.persistencia.mybatis.dao.impugnacion;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.StatementType;

import mx.com.teclo.saicdmx.persistencia.vo.impugnacion.ImpugnacionAltaVO;
/**
 * 
 * @author javier07
 *
 */
@Mapper
public interface ImpugnacionNuevaMyBatisDAO {
	
	String SP_NUEVA_IMPUGNACION ="{CALL SP_IMPUGNACION_NUEVO("
			+ "#{impugnacionOficioJur}, "
			+ "#{impugnacionJuicio},"
			+ "#{actorApellidoPaterno}, "
			+ "#{actorApellidoMaterno}, "
			+ "#{actorNombre},"
			+ "#{fechaRecepcion},"
			+ "#{empleadoId},"
			+ "#{impugnacionConstanciaCump},"
			+ "#{referencia, jdbcType=VARCHAR, javaType=java.lang.String, mode=INOUT},"
			+ "#{resultado, jdbcType=VARCHAR, javaType=java.lang.String, mode=INOUT}, "
			+ "#{mensaje, jdbcType=VARCHAR, javaType=java.lang.String, mode=INOUT})}";
	
	@Select(value = SP_NUEVA_IMPUGNACION)
	@Options(statementType = StatementType.CALLABLE)
	public ImpugnacionAltaVO nuevaImpugnacion(ImpugnacionAltaVO ImpugnacionAltaVO);
	

}
