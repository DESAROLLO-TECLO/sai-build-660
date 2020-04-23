package mx.com.teclo.saicdmx.persistencia.mybatis.dao.controlsuministros;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import mx.com.teclo.saicdmx.persistencia.vo.controlsuministros.DelegadoAuxiliarVO;

@Mapper
public interface BuscarAuxiliarAreaOpeMyBatisDAO {

	String GET_OFICIALES = "  SELECT  reg_id,upc_id,upc_nombre FROM REGIONALES_UPC "
			+ "WHERE upc_id = #{upc_id} and reg_id = #{reg_id}";	

@Select(GET_OFICIALES)
public DelegadoAuxiliarVO buscarAuxiliarAreaRegion(@Param("reg_id") Long reg_id,@Param("upc_id") Long upc_id);

}
