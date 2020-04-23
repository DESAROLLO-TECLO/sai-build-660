package mx.com.teclo.saicdmx.persistencia.mybatis.dao.catalogos;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.mapping.StatementType;

import mx.com.teclo.saicdmx.persistencia.vo.catalogos.CrudVehiculoResponsableVO;

@Mapper
public interface VehiculoResponsableMyBatisDAO {

	String ABC_RESPONSABLE_VEHICULO = "{CALL ABC_RESPONSABLE_VEHICULO ("
			+"#{rVehId},"
			+"#{rVehCod},"
			+"#{rVehNombre},"
			+"#{modificadoPor},"
			+"#{operacion},"
			+"#{resultado, jdbcType=VARCHAR, javaType=java.lang.String, mode=INOUT},"
			+"#{mensaje, jdbcType=VARCHAR, javaType=java.lang.String, mode=INOUT})}";
	
	@Select(value = ABC_RESPONSABLE_VEHICULO)
	@Options(statementType = StatementType.CALLABLE)
	public void callCrudVehiculoResponsable(CrudVehiculoResponsableVO crudVehiculoResponsableVO) throws PersistenceException;
}
