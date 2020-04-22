package mx.com.teclo.saicdmx.persistencia.mybatis.dao.controlsuministros;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import mx.com.teclo.saicdmx.persistencia.vo.controlsuministros.OficialRecibeVO;

@Mapper
public interface CatalogoOficialRecibeMyBatisDAO {
	
	String GET_OFICIALES = " SELECT e.emp_id, d.descripcion || ' - ' || e.emp_nombre || ' ' || e.emp_ape_paterno || ' ' || e.emp_ape_materno as descripcion "
	+ " FROM REGIONALES_UPC_DELEGADOS re, EMPLEADOS E, DELEGADOS_TIPO d "
	+ " WHERE e.emp_id = re.emp_id "
	+ " AND re.id_tipo_delegado = d.id_tipo "
	+ " AND re.REG_ID =  #{reg_id} and re.upc_id =  #{upc_id} ";
	
	
	@Select(GET_OFICIALES)
	public List<OficialRecibeVO> buscarOficiales(@Param("reg_id") Long reg_id, @Param("upc_id") Long upc_id);


	
}