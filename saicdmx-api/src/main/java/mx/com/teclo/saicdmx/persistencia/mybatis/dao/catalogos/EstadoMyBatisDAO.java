package mx.com.teclo.saicdmx.persistencia.mybatis.dao.catalogos;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.mapping.StatementType;

import mx.com.teclo.saicdmx.persistencia.vo.catalogos.CrudEstadoVO;

@Mapper
public interface EstadoMyBatisDAO {

	String ABC_ESTADOS = "{CALL ABC_ESTADOS ("
			+"#{edoId},"
			+"#{edoCod},"
			+"#{edoNombre},"
			+"#{modificadoPor},"
			+"#{operacion},"
			+"#{resultado, jdbcType=VARCHAR, javaType=java.lang.String, mode=INOUT},"
			+"#{mensaje, jdbcType=VARCHAR, javaType=java.lang.String, mode=INOUT})}";
	
	@Select(value = ABC_ESTADOS)
	@Options(statementType = StatementType.CALLABLE)
	public void callCrudEstado(CrudEstadoVO altaGruaVO) throws PersistenceException;
}
