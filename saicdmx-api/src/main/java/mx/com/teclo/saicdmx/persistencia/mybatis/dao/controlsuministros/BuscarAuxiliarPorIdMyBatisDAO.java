package mx.com.teclo.saicdmx.persistencia.mybatis.dao.controlsuministros;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import mx.com.teclo.saicdmx.persistencia.vo.controlsuministros.DelegadoAuxiliarVO;

@Mapper
public interface BuscarAuxiliarPorIdMyBatisDAO {

	String GET_OFICIALES = " SELECT re.id_registro, d.descripcion as tipo, r.upc_nombre, "
			+ " e.emp_placa oficial_placa, e.emp_nombre || ' ' || e.emp_ape_paterno || ' ' || e.emp_ape_materno as oficial_nombre "
			+ " FROM REGIONALES_UPC_DELEGADOS re, EMPLEADOS E, DELEGADOS_TIPO d, REGIONALES_UPC r  "
			+ " WHERE e.emp_id = re.emp_id  "
			+ " AND re.id_tipo_delegado = d.id_tipo "
			+ " AND re.upc_id = r.upc_id "
			+ " AND re.reg_id = r.reg_id "
			+ " AND re.id_registro = #{id_registro} ";	
			
@Select(GET_OFICIALES)
public DelegadoAuxiliarVO buscarAuxiliarPorId(@Param("id_registro") Long id_registro);



}
