package mx.com.teclo.saicdmx.persistencia.mybatis.dao.controlsuministros;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.mapping.StatementType;

import mx.com.teclo.saicdmx.persistencia.vo.controlsuministros.SuministroAreasVO;

@Mapper
public interface AltaSuministroAreasMyBatisDAO {
	
	static final String SP_INGRESO =  "BEGIN SP_SUMINISTRO_NUEVO ("
			+ "#{ltipo_sum}, "
			+ "#{lstAreaOpe}, "
			+ "#{lstRegional}, "
			+ "#{lstRecibe}, "
			+ "#{txt_num_recibo}, "
			+ "#{txt_fecha}, "
			+ "#{txt_folio_ini}, "
			+ "#{txt_folio_fin}, " 
			+ "#{txt_tot_folios}, " 
			+ "#{idUser}, " 
			+ "#{resultado, jdbcType=VARCHAR, javaType=java.lang.String, mode=INOUT}, "
			+ "#{mensaje, jdbcType=VARCHAR, javaType=java.lang.String, mode=INOUT}); end;";
	
			
	@Select(value = SP_INGRESO)
	@Options(statementType = StatementType.CALLABLE)	
	public SuministroAreasVO altaSuministro(SuministroAreasVO suministroVO) throws PersistenceException;	
}

