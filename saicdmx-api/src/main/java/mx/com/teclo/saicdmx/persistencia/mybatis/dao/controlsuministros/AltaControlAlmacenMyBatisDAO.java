package mx.com.teclo.saicdmx.persistencia.mybatis.dao.controlsuministros;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.exceptions.PersistenceException;

import org.apache.ibatis.mapping.StatementType;

import mx.com.teclo.saicdmx.persistencia.vo.controlsuministros.ControlAlmacenVO;

@Mapper
public interface AltaControlAlmacenMyBatisDAO {

	static final String SP_INGRESO = "BEGIN SP_SUMINISTRO_ALMACEN ("
			+ "#{txt_num_remision}, "
			+ "#{txt_recibe}, "
			+ "#{txt_del_folio}, "
			+ "#{txt_al_folio}, "
			+ "#{txt_total}, "
			+ "#{txt_fecha}, "
			+ "#{userID}, "
			+ "#{ltipo_sum}, " 
			+ "#{resultado, jdbcType=VARCHAR, javaType=java.lang.String, mode=INOUT}, "
			+ "#{mensaje, jdbcType=VARCHAR, javaType=java.lang.String, mode=INOUT}); end;";
	
			
	@Select(value = SP_INGRESO)
	@Options(statementType = StatementType.CALLABLE)	
	public ControlAlmacenVO altaAlmacen(ControlAlmacenVO almacenVO) throws PersistenceException;	
}
