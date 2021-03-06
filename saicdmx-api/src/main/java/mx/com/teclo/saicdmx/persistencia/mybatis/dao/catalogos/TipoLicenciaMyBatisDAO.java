package mx.com.teclo.saicdmx.persistencia.mybatis.dao.catalogos;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.mapping.StatementType;

import mx.com.teclo.saicdmx.persistencia.vo.catalogos.CrudTipoLicenciaVO;

@Mapper
public interface TipoLicenciaMyBatisDAO {

	String ABC_TIPO_LICENCIA = "{CALL ABC_TIPO_LICENCIA ("
			+"#{tipoLId},"
			+"#{tipoLCod},"
			+"#{tipoLNombre},"
			+"#{modificadoPor},"
			+"#{operacion},"
			+"#{resultado, jdbcType=VARCHAR, javaType=java.lang.String, mode=INOUT},"
			+"#{mensaje, jdbcType=VARCHAR, javaType=java.lang.String, mode=INOUT})}";
	
	@Select(value = ABC_TIPO_LICENCIA)
	@Options(statementType = StatementType.CALLABLE)
	public void callCrudTipoLicencia(CrudTipoLicenciaVO crudTipoLicenciaVO) throws PersistenceException;
}
