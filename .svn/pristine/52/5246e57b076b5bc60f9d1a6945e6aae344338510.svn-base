package mx.com.teclo.saicdmx.persistencia.mybatis.dao.catalogos;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.mapping.StatementType;

import mx.com.teclo.saicdmx.persistencia.vo.catalogos.CrudVehiculoTipoVO;

@Mapper
public interface VehiculoTipoMyBatisDAO {

	String ABC_VEHICULO_TIPO = "{CALL ABC_VEHICULO_TIPO ("
			+"#{vTipoId},"
			+"#{vipoCod},"
			+"#{vSubtipo.vSubTipoId},"
			+"#{vTipoNombre},"
			+"#{modificadoPor},"
			+"#{operacion},"
			+"#{resultado, jdbcType=VARCHAR, javaType=java.lang.String, mode=INOUT},"
			+"#{mensaje, jdbcType=VARCHAR, javaType=java.lang.String, mode=INOUT})}";
	
	@Select(value = ABC_VEHICULO_TIPO)
	@Options(statementType = StatementType.CALLABLE)
	public void callCrudVehiculoTipo(CrudVehiculoTipoVO crudVehiculoTipoVO) throws PersistenceException;
}
