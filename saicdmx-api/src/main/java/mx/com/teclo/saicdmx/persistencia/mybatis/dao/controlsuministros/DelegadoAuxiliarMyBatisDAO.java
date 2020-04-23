package mx.com.teclo.saicdmx.persistencia.mybatis.dao.controlsuministros;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import mx.com.teclo.saicdmx.persistencia.vo.controlsuministros.DelegadoAuxiliarVO;

@Mapper
public interface DelegadoAuxiliarMyBatisDAO {

	String GET_OFICIALES = " SELECT re.id_registro, d.descripcion as tipo, "
							 + " e.emp_placa as oficial_placa, e.emp_nombre || ' ' || e.emp_ape_paterno || ' ' || e.emp_ape_materno as oficial_nombre " 
							 + " FROM REGIONALES_UPC_DELEGADOS re, EMPLEADOS E, DELEGADOS_TIPO d "
							 + " WHERE e.emp_id = re.emp_id "
							 + " AND re.id_tipo_delegado = d.id_tipo "
							 + " AND re.upc_id = #{upc_id} and re.reg_id = #{reg_id} ";		
							
		@Select(GET_OFICIALES)
		public List<DelegadoAuxiliarVO> buscarAuxiliar(@Param("upc_id") Long upc_id, @Param("reg_id") Long reg_id);


			
}
