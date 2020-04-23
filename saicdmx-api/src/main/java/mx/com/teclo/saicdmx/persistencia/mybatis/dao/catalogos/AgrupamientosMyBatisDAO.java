package mx.com.teclo.saicdmx.persistencia.mybatis.dao.catalogos;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.mapping.StatementType;

import mx.com.teclo.saicdmx.persistencia.vo.catalogos.CrudAgrupamientoVO;

@Mapper
public interface AgrupamientosMyBatisDAO {

	String Abc_Agrupamientos = "{CALL Abc_Agrupamientos ("
			+"#{agrupacionId},"
			+"#{agrupacionCodigo},"
			+"#{agrupacionNombre},"
			+"#{modificadoPor},"
			+"#{operacion},"
			+"#{resultado, jdbcType=VARCHAR, javaType=java.lang.String, mode=INOUT},"
			+"#{mensaje, jdbcType=VARCHAR, javaType=java.lang.String, mode=INOUT})}";
	
	@Select(value = Abc_Agrupamientos)
	@Options(statementType = StatementType.CALLABLE)
	public void callCrudAgrupamiento(CrudAgrupamientoVO crudAgrupamientoVO) throws PersistenceException;
}
