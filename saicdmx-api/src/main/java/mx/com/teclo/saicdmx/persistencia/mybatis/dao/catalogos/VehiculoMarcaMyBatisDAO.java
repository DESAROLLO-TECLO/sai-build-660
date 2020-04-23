package mx.com.teclo.saicdmx.persistencia.mybatis.dao.catalogos;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.mapping.StatementType;

import mx.com.teclo.saicdmx.persistencia.vo.catalogos.CrudVehiculoMarcaVO;

@Mapper
public interface VehiculoMarcaMyBatisDAO {

	String ABC_VEHICULO_MARCA = "{CALL ABC_VEHICULO_MARCA ("
			+"#{vMarId},"
			+"#{vMarCod},"
			+"#{vMarNombre},"
			+"#{modificadoPor},"
			+"#{operacion},"
			+"#{resultado, jdbcType=VARCHAR, javaType=java.lang.String, mode=INOUT},"
			+"#{mensaje, jdbcType=VARCHAR, javaType=java.lang.String, mode=INOUT})}";
	
	@Select(value = ABC_VEHICULO_MARCA)
	@Options(statementType = StatementType.CALLABLE)
	public void callCrudVehiculoMarca(CrudVehiculoMarcaVO crudVehiculoMarcaVO) throws PersistenceException;
}
