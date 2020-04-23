package mx.com.teclo.saicdmx.persistencia.mybatis.dao.catalogos;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.mapping.StatementType;

import mx.com.teclo.saicdmx.persistencia.vo.catalogos.CrudVehiculoSubTipoVO;

@Mapper
public interface VehiculoSubTipoMyBatisDAO {

	String ABC_VEHICULO_SUBTIPO = "{CALL ABC_VEHICULO_SUBTIPO ("
			+"#{vSubTipoId},"
			+"#{vSubTipoCodigo},"
			+"#{vSubTipoNombre},"
			+"#{modificadoPor},"
			+"#{operacion},"
			+"#{resultado, jdbcType=VARCHAR, javaType=java.lang.String, mode=INOUT},"
			+"#{mensaje, jdbcType=VARCHAR, javaType=java.lang.String, mode=INOUT})}";
	
	@Select(value = ABC_VEHICULO_SUBTIPO)
	@Options(statementType = StatementType.CALLABLE)
	public void callCrudVehiculoSubTipo(CrudVehiculoSubTipoVO crudVehiculoSubTipoVO) throws PersistenceException;
}
