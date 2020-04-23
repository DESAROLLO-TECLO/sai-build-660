package mx.com.teclo.saicdmx.persistencia.mybatis.dao.catalogos;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.mapping.StatementType;

import mx.com.teclo.saicdmx.persistencia.vo.catalogos.CrudProgramaVO;

@Mapper
public interface ProgramaMyBatisDAO {

	String ABC_PROGRAMAS = "{CALL ABC_PROGRAMAS ("
			+"#{programaId},"
			+"#{programaCodigo},"
			+"#{programaNombre},"
			+"#{modificadoPor},"
			+"#{operacion},"
			+"#{resultado, jdbcType=VARCHAR, javaType=java.lang.String, mode=INOUT},"
			+"#{mensaje, jdbcType=VARCHAR, javaType=java.lang.String, mode=INOUT})}";
	
	@Select(value = ABC_PROGRAMAS)
	@Options(statementType = StatementType.CALLABLE)
	public void callCrudPrograma(CrudProgramaVO crudProgramaVO) throws PersistenceException;
}
