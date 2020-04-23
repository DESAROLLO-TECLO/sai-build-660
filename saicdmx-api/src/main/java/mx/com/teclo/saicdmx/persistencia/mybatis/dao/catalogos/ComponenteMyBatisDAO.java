package mx.com.teclo.saicdmx.persistencia.mybatis.dao.catalogos;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.mapping.StatementType;

import mx.com.teclo.saicdmx.persistencia.vo.catalogos.CrudComponenteVO;

@Mapper
public interface ComponenteMyBatisDAO {

	String ABC_COMPONENTES_INVENTARIO = "{CALL ABC_COMPONENTES_INVENTARIO ("
			+"#{idComponente},"
			+"#{codigoComponente},"
			+"#{nombreComponente},"
			+"#{ordenComponente},"
			+"#{modificadoPor},"
			+"#{operacion},"
			+"#{resultado, jdbcType=VARCHAR, javaType=java.lang.String, mode=INOUT},"
			+"#{mensaje, jdbcType=VARCHAR, javaType=java.lang.String, mode=INOUT})}";
	
	@Select(value = ABC_COMPONENTES_INVENTARIO)
	@Options(statementType = StatementType.CALLABLE)
	public void callCrudComponente(CrudComponenteVO crudComponenteVO) throws PersistenceException;
}
