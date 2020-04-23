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
public interface ImpugnacionModificaMyBatisDAO {

	static final String SP_ACTUALIZA_IMPUGNACION = "{CALL SP_IMPUGNACION_MOD("
			+ "#{impugnacionOficioJur}, "
			+ "#{impugnacionJuicio}, "
			+ "#{actorNombre}, "
			+ "#{actorApellidoPaterno}, "
			+ "#{actorApellidoMaterno}, "
			+ "#{fechaRecepcion}, "
			+ "#{impugnacionConstanciaCump}, " 
			+ "#{impugnacionId}, "
			+ "#{modificadorPor}, "
			+ "#{referencia, jdbcType=VARCHAR, javaType=java.lang.String, mode=INOUT},"
			+ "#{resultado, jdbcType=VARCHAR, javaType=java.lang.String, mode=INOUT}, "
			+ "#{mensaje, jdbcType=VARCHAR, javaType=java.lang.String, mode=INOUT})}";
	
	/**
	 * @author javier07
	 * @param impugnacionVO
	 */
	@Select(value = SP_ACTUALIZA_IMPUGNACION)
	@Options(statementType = StatementType.CALLABLE)
    public void actualizaImpugnaInfo(ImpugnacionParametrosVO impugnacionVO);

	
}
