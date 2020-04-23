package mx.com.teclo.saicdmx.persistencia.mybatis.dao.catalogos;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.mapping.StatementType;

import mx.com.teclo.saicdmx.persistencia.vo.catalogos.CrudTipoEmpleadoVO;

@Mapper
public interface TipoEmpleadoMyBatisDAO {
	
	String ABC_EMPLEADO_TIPO = "{CALL ABC_EMPLEADO_TIPO ("
			+"#{empTipId},"
			+"#{empTipCodigo},"
			+"#{empTipNombre},"
			+"#{modificadoPor},"
			+"#{operacion},"
			+"#{resultado, jdbcType=VARCHAR, javaType=java.lang.String, mode=INOUT},"
			+"#{mensaje, jdbcType=VARCHAR, javaType=java.lang.String, mode=INOUT})}";
	
	@Select(value = ABC_EMPLEADO_TIPO)
	@Options(statementType = StatementType.CALLABLE)
	public void callCrudTipoEmpleado(CrudTipoEmpleadoVO crudTipoEmpleadoVO) throws PersistenceException;
}
