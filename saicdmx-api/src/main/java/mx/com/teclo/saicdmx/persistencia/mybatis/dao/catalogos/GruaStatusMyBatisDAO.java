package mx.com.teclo.saicdmx.persistencia.mybatis.dao.catalogos;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.mapping.StatementType;

import mx.com.teclo.saicdmx.persistencia.vo.catalogos.CrudGruaStatusVO;


@Mapper
public interface GruaStatusMyBatisDAO {

	String ABC_GRUAS_STATUS = "{CALL ABC_GRUAS_STATUS ("
			+"#{gruaStatusId},"
			+"#{gruaStatusCod},"
			+"#{gruaStatusNombre},"
			+"#{modificadoPor},"
			+"#{operacion},"
			+"#{resultado, jdbcType=VARCHAR, javaType=java.lang.String, mode=INOUT},"
			+"#{mensaje, jdbcType=VARCHAR, javaType=java.lang.String, mode=INOUT})}";
	
	@Select(value = ABC_GRUAS_STATUS)
	@Options(statementType = StatementType.CALLABLE)
	public void callCrudGruaStatus(CrudGruaStatusVO gruaStatusVO) throws PersistenceException;
}
