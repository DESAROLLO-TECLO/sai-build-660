package mx.com.teclo.saicdmx.persistencia.mybatis.dao.catalogos;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.mapping.StatementType;

import mx.com.teclo.saicdmx.persistencia.vo.catalogos.CrudTipoIngresoVO;

@Mapper
public interface TipoIngresoMyBatisDAO {

	String ABC_TIPO_INGRESO = "{CALL ABC_TIPO_INGRESO ("
			+"#{tIngrId},"
			+"#{tIngrCod},"
			+"#{tIngrNombre},"
			+"#{modificadoPor},"
			+"#{operacion},"
			+"#{resultado, jdbcType=VARCHAR, javaType=java.lang.String, mode=INOUT},"
			+"#{mensaje, jdbcType=VARCHAR, javaType=java.lang.String, mode=INOUT})}";
	
	@Select(value = ABC_TIPO_INGRESO)
	@Options(statementType = StatementType.CALLABLE)
	public void callCrudTipoIngreso(CrudTipoIngresoVO crudTipoIngresoVO) throws PersistenceException;
}
