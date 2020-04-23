package mx.com.teclo.saicdmx.persistencia.mybatis.dao.catalogos;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.mapping.StatementType;

import mx.com.teclo.saicdmx.persistencia.vo.catalogos.CrudAreaVO;

@Mapper
public interface AreaMyBatisDAO {

	String ABC_AREAS = "{CALL ABC_AREAS ("
			+"#{areaId},"
			+"#{areaCodigo},"
			+"#{areaNombre},"
			+"#{modificadoPor},"
			+"#{operacion},"
			+"#{resultado, jdbcType=VARCHAR, javaType=java.lang.String, mode=INOUT},"
			+"#{mensaje, jdbcType=VARCHAR, javaType=java.lang.String, mode=INOUT})}";
	
	@Select(value = ABC_AREAS)
	@Options(statementType = StatementType.CALLABLE)
	public void callCrudArea(CrudAreaVO crudAreaVO) throws PersistenceException;
}
