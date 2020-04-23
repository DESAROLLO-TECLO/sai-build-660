package mx.com.teclo.saicdmx.persistencia.mybatis.dao.catalogos;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.mapping.StatementType;

import mx.com.teclo.saicdmx.persistencia.vo.catalogos.CrudConceptoPagoVO;

@Mapper
public interface ConceptoMyBatisDAO {
	
	String ABC_CONCEPTO_PAGOS = "{CALL ABC_CONCEPTO_PAGOS ("
			+"#{conceptoId},"
			+"#{conceptoCodigo},"
			+"#{conceptoNombre},"
			+"#{conceptoValor},"
			+"#{conceptoDias},"
			+"#{conceptoDescuento},"
			+"#{modificadoPor},"
			+"#{operacion},"
			+"#{resultado, jdbcType=VARCHAR, javaType=java.lang.String, mode=INOUT},"
			+"#{mensaje, jdbcType=VARCHAR, javaType=java.lang.String, mode=INOUT})}";
	
	@Select(value = ABC_CONCEPTO_PAGOS)
	@Options(statementType = StatementType.CALLABLE)
	public void callCrudConceptoPago(CrudConceptoPagoVO crudConceptoPagoVO) throws PersistenceException;
}
