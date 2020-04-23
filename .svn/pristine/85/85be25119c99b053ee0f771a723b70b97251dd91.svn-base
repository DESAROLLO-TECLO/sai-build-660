package mx.com.teclo.saicdmx.persistencia.mybatis.dao.catalogos;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.mapping.StatementType;

import mx.com.teclo.saicdmx.persistencia.vo.catalogos.CrudUnidadeTerritorialVO;

@Mapper
public interface UnidadeTerritorialMyBatisDAO {
	
	String ABC_UNIDADES = "{CALL ABC_UNIDADES ("
			+"#{utId.utId},"
			+"#{utCod},"
			+"#{utId.secId},"
			+"#{utNombre},"
			+"#{modificadoPor},"
			+"#{operacion},"
			+"#{resultado, jdbcType=VARCHAR, javaType=java.lang.String, mode=INOUT},"
			+"#{mensaje, jdbcType=VARCHAR, javaType=java.lang.String, mode=INOUT})}";
	
	@Select(value = ABC_UNIDADES)
	@Options(statementType = StatementType.CALLABLE)
	public void callCrudUnidadeTerritorial(CrudUnidadeTerritorialVO crudUnidadeTerritorialVO) throws PersistenceException;
}
