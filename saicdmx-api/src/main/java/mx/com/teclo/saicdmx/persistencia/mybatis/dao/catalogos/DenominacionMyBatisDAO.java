package mx.com.teclo.saicdmx.persistencia.mybatis.dao.catalogos;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.mapping.StatementType;

import mx.com.teclo.saicdmx.persistencia.vo.catalogos.CrudDenominacionVO;

@Mapper
public interface DenominacionMyBatisDAO {

	String ABC_DENOMINACIONES = "{CALL ABC_DENOMINACIONES ("
			+"#{denominacionId},"
			+"#{denominacionCodigo},"
			+"#{denominacionNombre},"
			+"#{denominacionValor},"
			+"#{denominacionTipo},"
			+"#{modificadoPor},"
			+"#{operacion},"
			+"#{resultado, jdbcType=VARCHAR, javaType=java.lang.String, mode=INOUT},"
			+"#{mensaje, jdbcType=VARCHAR, javaType=java.lang.String, mode=INOUT})}";
	
	@Select(value = ABC_DENOMINACIONES)
	@Options(statementType = StatementType.CALLABLE)
	public void callCrudDenominacion(CrudDenominacionVO crudDenominacionVO) throws PersistenceException;
}
