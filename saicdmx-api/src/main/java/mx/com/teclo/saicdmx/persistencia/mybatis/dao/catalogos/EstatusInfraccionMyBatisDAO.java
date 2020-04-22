package mx.com.teclo.saicdmx.persistencia.mybatis.dao.catalogos;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.mapping.StatementType;

import mx.com.teclo.saicdmx.persistencia.vo.catalogos.CrudEstatusInfraccionVO;

@Mapper
public interface EstatusInfraccionMyBatisDAO {

	String ABC_ESTATUS_INFRACCION = "{CALL ABC_ESTATUS_INFRACCION ("
			+"#{estatusId},"
			+"#{estatusCodigo},"
			+"#{estatusNombre},"
			+"#{modificadoPor},"
			+"#{operacion},"
			+"#{resultado, jdbcType=VARCHAR, javaType=java.lang.String, mode=INOUT},"
			+"#{mensaje, jdbcType=VARCHAR, javaType=java.lang.String, mode=INOUT})}";
	
	@Select(value = ABC_ESTATUS_INFRACCION)
	@Options(statementType = StatementType.CALLABLE)
	public void callCrudEstatusInfraccion(CrudEstatusInfraccionVO crudEstatusInfraccionVO) throws PersistenceException;
}
