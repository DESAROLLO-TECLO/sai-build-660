package mx.com.teclo.saicdmx.persistencia.mybatis.dao.catalogos;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.mapping.StatementType;

import mx.com.teclo.saicdmx.persistencia.vo.catalogos.CrudTurnoVO;

@Mapper
public interface TurnoMyBatisDAO {

	String ABC_TURNOS = "{CALL ABC_TURNOS ("
			+"#{turnoId},"
			+"#{turnoCod},"
			+"#{turnoNombre},"
			+"#{fechaInicio},"
			+"#{fechaFin},"
			+"#{modificadoPor},"
			+"#{operacion},"
			+"#{resultado, jdbcType=VARCHAR, javaType=java.lang.String, mode=INOUT},"
			+"#{mensaje, jdbcType=VARCHAR, javaType=java.lang.String, mode=INOUT})}";
	
	@Select(value = ABC_TURNOS)
	@Options(statementType = StatementType.CALLABLE)
	public void callCrudTurno(CrudTurnoVO turnoVO) throws PersistenceException;
}
