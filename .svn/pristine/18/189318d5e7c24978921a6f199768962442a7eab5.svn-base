package mx.com.teclo.saicdmx.persistencia.mybatis.dao.catalogos;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.mapping.StatementType;

import mx.com.teclo.saicdmx.persistencia.vo.catalogos.CrudBancoVO;

@Mapper
public interface BancoMyBatisDAO {

	String ABC_BANCOS = "{CALL ABC_BANCOS ("
			+"#{bancoId},"
			+"#{bancoCodigo},"
			+"#{bancoNombre},"
			+"#{modificadoPor},"
			+"#{operacion},"
			+"#{resultado, jdbcType=VARCHAR, javaType=java.lang.String, mode=INOUT},"
			+"#{mensaje, jdbcType=VARCHAR, javaType=java.lang.String, mode=INOUT})}";
	
	@Select(value = ABC_BANCOS)
	@Options(statementType = StatementType.CALLABLE)
	public void callCrudBanco(CrudBancoVO crudBancoVO) throws PersistenceException;
}
