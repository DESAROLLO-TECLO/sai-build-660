package mx.com.teclo.saicdmx.persistencia.mybatis.dao.catalogos;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.mapping.StatementType;

import mx.com.teclo.saicdmx.persistencia.vo.catalogos.CrudCausalesVO;

@Mapper
public interface CausalesMyBatisDAO {

	String ABC_CAUSALES = "{CALL ABC_CAUSALES ("
			+"#{causalId},"
			+"#{causalCodigo},"
			+"#{causalNombre},"
			+"#{modificadoPor},"
			+"#{operacion},"
			+"#{resultado, jdbcType=VARCHAR, javaType=java.lang.String, mode=INOUT},"
			+"#{mensaje, jdbcType=VARCHAR, javaType=java.lang.String, mode=INOUT})}";
	
	@Select(value = ABC_CAUSALES)
	@Options(statementType = StatementType.CALLABLE)
	public void callCrudCausales(CrudCausalesVO crudCausalesVO) throws PersistenceException;
}
