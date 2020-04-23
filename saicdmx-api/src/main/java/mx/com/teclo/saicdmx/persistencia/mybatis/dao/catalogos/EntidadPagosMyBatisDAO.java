package mx.com.teclo.saicdmx.persistencia.mybatis.dao.catalogos;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.mapping.StatementType;

import mx.com.teclo.saicdmx.persistencia.vo.catalogos.CrudEntidadPagosVO;

@Mapper
public interface EntidadPagosMyBatisDAO {

	String ABC_ENTIDADES_PAGO = "{CALL ABC_ENTIDADES_PAGO ("
			+"#{entidadId},"
			+"#{entidadCodigo},"
			+"#{entidadNombre},"
			+"#{modificadoPor},"
			+"#{operacion},"
			+"#{resultado, jdbcType=VARCHAR, javaType=java.lang.String, mode=INOUT},"
			+"#{mensaje, jdbcType=VARCHAR, javaType=java.lang.String, mode=INOUT})}";
	
	@Select(value = ABC_ENTIDADES_PAGO)
	@Options(statementType = StatementType.CALLABLE)
	public void callCrudEntidadPagos(CrudEntidadPagosVO crudEntidadPagosVO) throws PersistenceException;
}
