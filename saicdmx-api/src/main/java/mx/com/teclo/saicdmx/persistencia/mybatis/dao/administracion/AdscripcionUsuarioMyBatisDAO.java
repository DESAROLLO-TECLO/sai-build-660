package mx.com.teclo.saicdmx.persistencia.mybatis.dao.administracion;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.StatementType;

import mx.com.teclo.saicdmx.persistencia.vo.administracion.AdscripcionVO;
@Mapper
public interface AdscripcionUsuarioMyBatisDAO {

												
	String SP_CAMBIO_ADSCRIPCION_NUEVO = "begin SP_CAMBIO_ADSCRIPCION_NUEVO ("
			+ "#{p_emp_id},"
			+ "#{p_folio},"
			+ "#{p_fecha},"
			+ "#{p_solicita},"
			+ "#{p_area},"
			+ "#{p_regid_solicita},"
			+ "#{p_recibe},"
			+ "#{p_observacion},"
			+ "#{p_regionalid},"
			+ "#{p_areaid},"
			+ "#{p_empid_modif},"
			+ "#{p_resultado, jdbcType=VARCHAR, javaType=java.lang.String, mode=INOUT}, "
			+ "#{p_mensaje , jdbcType=VARCHAR, javaType=java.lang.String, mode=INOUT}); end;";
	
	
	@Select(value = SP_CAMBIO_ADSCRIPCION_NUEVO)
	@Options(statementType = StatementType.CALLABLE)
	AdscripcionVO nuevaAdscripcion(AdscripcionVO adscripcionVO);
			
			
}
