package mx.com.teclo.saicdmx.persistencia.mybatis.dao.catalogos;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.mapping.StatementType;

import mx.com.teclo.saicdmx.persistencia.vo.catalogos.CrudEventoVO;

@Mapper
public interface EventoMyBatisDAO {

	String ABC_EVENTO = "{CALL ABC_EVENTO ("
			+"#{eventoId},"
			+"#{eventoCodigo},"
			+"#{eventoNombre},"
			+"#{modificadoPor},"
			+"#{operacion},"
			+"#{resultado, jdbcType=VARCHAR, javaType=java.lang.String, mode=INOUT},"
			+"#{mensaje, jdbcType=VARCHAR, javaType=java.lang.String, mode=INOUT})}";
	
	@Select(value = ABC_EVENTO)
	@Options(statementType = StatementType.CALLABLE)
	public void callCrudEvento(CrudEventoVO crudEventoVO) throws PersistenceException;
}
