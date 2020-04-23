package mx.com.teclo.saicdmx.persistencia.mybatis.dao.controlsuministros;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.mapping.StatementType;

import mx.com.teclo.saicdmx.persistencia.vo.controlsuministros.OficialNuevoVO;

@Mapper
public interface AltaOficialMyBatisDAO {

	static final String SP_INGRESO = "begin SP_SUMINISTRO_AREAS_NEW ("
			+ "#{upc_id}, "
			+ "#{reg_id}, "
			+ "#{oficial_placa}, "
			+ "#{idUser}, " 
			+ "#{resultado, jdbcType=VARCHAR, javaType=java.lang.String, mode=INOUT}, "
			+ "#{mensaje, jdbcType=VARCHAR, javaType=java.lang.String, mode=INOUT}); end;";
	
			
	@Select(value = SP_INGRESO)
	@Options(statementType = StatementType.CALLABLE)	
	public OficialNuevoVO altaAuxiliar(OficialNuevoVO valoresAux) throws PersistenceException;

}
