package mx.com.teclo.saicdmx.persistencia.mybatis.dao.catalogos;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.mapping.StatementType;

import mx.com.teclo.saicdmx.persistencia.vo.catalogos.CrudRegionDepositoVO;

@Mapper
public interface RegionDepositoMyBatisDAO {

	String ABC_REGIONES_DEPOSITOS = "{CALL ABC_REGIONES_DEPOSITOS ("
			+"#{estadoDTO.edoId},"
			+"#{regionId},"
			+"#{regionCodigo},"
			+"#{regionNombre},"
			+"#{modificadoPor},"
			+"#{operacion},"
			+"#{resultado, jdbcType=VARCHAR, javaType=java.lang.String, mode=INOUT},"
			+"#{mensaje, jdbcType=VARCHAR, javaType=java.lang.String, mode=INOUT})}";
	
	@Select(value = ABC_REGIONES_DEPOSITOS)
	@Options(statementType = StatementType.CALLABLE)
	public void callCrudRegionDeposito(CrudRegionDepositoVO crudRegionDepositoVO) throws PersistenceException;
}
