package mx.com.teclo.saicdmx.persistencia.mybatis.dao.catalogos;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.mapping.StatementType;

import mx.com.teclo.saicdmx.persistencia.vo.catalogos.CrudCausaIngresoVO;

@Mapper
public interface CausaIngresoMyBatisDAO {

	String ABC_CAUSA_INGRESO = "{CALL ABC_CAUSA_INGRESO ("
			+"#{idCausaIngreso},"
			+"#{codigoCausaIngreso},"
			+"#{nombreCausaIngreso},"
			+"#{modificadoPor},"
			+"#{operacion},"
			+"#{resultado, jdbcType=VARCHAR, javaType=java.lang.String, mode=INOUT},"
			+"#{mensaje, jdbcType=VARCHAR, javaType=java.lang.String, mode=INOUT})}";
	
	@Select(value = ABC_CAUSA_INGRESO)
	@Options(statementType = StatementType.CALLABLE)
	public void callCrudCausaIngreso(CrudCausaIngresoVO crudCausaIngresoVO) throws PersistenceException;
}
