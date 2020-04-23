package mx.com.teclo.saicdmx.persistencia.mybatis.dao.catalogos;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.mapping.StatementType;

import mx.com.teclo.saicdmx.persistencia.vo.catalogos.CrudConcesionariaVO;

@Mapper
public interface ConcesionariaMyBatisDAO {

	String ABC_CONCESIONARIAS = "{CALL ABC_CONCESIONARIAS ("
			+"#{concesionariaId},"
			+"#{concesionariaCodigo},"
			+"#{concesionariaPrefijo},"
			+"#{concesionariaNombre},"
			+"#{modificadoPor},"
			+"#{operacion},"
			+"#{resultado, jdbcType=VARCHAR, javaType=java.lang.String, mode=INOUT},"
			+"#{mensaje, jdbcType=VARCHAR, javaType=java.lang.String, mode=INOUT})}";
	
	@Select(value = ABC_CONCESIONARIAS)
	@Options(statementType = StatementType.CALLABLE)
	public void callCrudConcesionaria(CrudConcesionariaVO crudConcesionariaVO) throws PersistenceException;
}
