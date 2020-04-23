package mx.com.teclo.saicdmx.persistencia.mybatis.dao.controlsuministros;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.mapping.StatementType;

import mx.com.teclo.saicdmx.persistencia.vo.controlsuministros.OficialModificacionVO;

@Mapper
public interface  ModificarOficialMyBatisDAO {

	static final String SP_INGRESO = "begin SP_SUMINISTRO_AREAS_UPD ("
			+ "#{id_registro}, "
			+ "#{oficial_placa}, "
			+ "#{idUser}, " 
			+ "#{resultado, jdbcType=VARCHAR, javaType=java.lang.String, mode=INOUT}, "
			+ "#{mensaje, jdbcType=VARCHAR, javaType=java.lang.String, mode=INOUT}); end;";
	
			
	@Select(value = SP_INGRESO)
	@Options(statementType = StatementType.CALLABLE)	
	public OficialModificacionVO cambiarAuxiliar(OficialModificacionVO valores) throws PersistenceException;
//	public OficialAccionesVO cambiarAuxiliar(@Param("id_registro") long id_registro, @Param("oficial_placa") String oficial_placa);


//	public OficialAccionesVO cambiarAuxiliar(OficialAccionesVO valoresVO);

}
