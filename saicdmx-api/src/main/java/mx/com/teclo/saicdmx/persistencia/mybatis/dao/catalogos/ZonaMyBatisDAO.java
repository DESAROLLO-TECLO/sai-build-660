package mx.com.teclo.saicdmx.persistencia.mybatis.dao.catalogos;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.mapping.StatementType;

import mx.com.teclo.saicdmx.persistencia.vo.catalogos.CrudZonaVO;

@Mapper
public interface ZonaMyBatisDAO {

	String ABC_ZONAS = "{CALL ABC_ZONAS ("
			+"#{zonaId},"
			+"#{zonaCodigo},"
			+"#{zonaNombre},"
			+"#{modificadoPor},"
			+"#{operacion},"
			+"#{resultado, jdbcType=VARCHAR, javaType=java.lang.String, mode=INOUT},"
			+"#{mensaje, jdbcType=VARCHAR, javaType=java.lang.String, mode=INOUT})}";
	
	@Select(value = ABC_ZONAS)
	@Options(statementType = StatementType.CALLABLE)
	public void callCrudZona(CrudZonaVO crudZonaVO) throws PersistenceException;
}
