package mx.com.teclo.saicdmx.persistencia.mybatis.dao.catalogos;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.mapping.StatementType;

import mx.com.teclo.saicdmx.persistencia.vo.catalogos.CrudVehiculoModeloVO;

@Mapper
public interface VehiculoModeloMyBatisDAO {

	String ABC_VEHICULO_MODELO = "{CALL ABC_VEHICULO_MODELO ("
			+"#{vehiculoMarca.vMarId},"
			+"#{vModId.vModId},"
			+"#{vModCod},"
			+"#{vModNombre},"
			+"#{modificadoPor},"
			+"#{operacion},"
			+"#{resultado, jdbcType=VARCHAR, javaType=java.lang.String, mode=INOUT},"
			+"#{mensaje, jdbcType=VARCHAR, javaType=java.lang.String, mode=INOUT})}";
	
	@Select(value = ABC_VEHICULO_MODELO)
	@Options(statementType = StatementType.CALLABLE)
	public void callCrudGrua(CrudVehiculoModeloVO crudVehiculoModeloVO) throws PersistenceException;
}
