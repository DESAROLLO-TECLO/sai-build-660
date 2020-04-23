package mx.com.teclo.saicdmx.persistencia.mybatis.dao.remisionadeposito;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import mx.com.teclo.saicdmx.persistencia.vo.remisionadeposito.ArticuloSancionVO;
import mx.com.teclo.saicdmx.persistencia.vo.remisionadeposito.GruaConceVO;
import mx.com.teclo.saicdmx.persistencia.vo.remisionadeposito.SecAgrupArrasEmpVO;
import mx.com.teclo.saicdmx.persistencia.vo.remisionadeposito.VehiculoTipoColorMarcaVO;

@Mapper
public interface ComplementoInfraccionMyBatisDAO {

	
	String GET_ARTICULO = " select i.art_id, sancion_art_id, vorigen, art_numero, art_fraccion, art_parrafo, art_inciso, i.art_motivacion, art_dias"
						+ " from infracciones i, articulos a"
						+ " where a.art_id = i.art_id and infrac_num = #{infrac_num}";
	
	
	String GET_VEHICULO = " select vtipo_cod,vtipo_nombre, vcolor_cod,vcolor_nombre, vmar_cod, vmar_nombre, vmod_cod, vmod_nombre "
						+ " from vehiculo_tipo vt, vehiculo_color vc, vehiculo_marca  vm, infracciones i , vehiculo_modelo vmd "
						+ " where vt.vtipo_id=i.vtipo_id and "
						+ " vc.vcolor_id = i.vcolor_id and "
					    + " vm.vmar_id=i.vmar_id and "
					    + " vmd.vmod_id =i.vmod_id and "
					    + " vmd.vmar_id=i.vmar_id and "
						+ " i.infrac_num = #{infrac_num}";
				
	
	String GET_SECAGRUP = " select s.sec_cod, sec_nombre, e.agrp_id, agrp_cod, agrp_nombre, infrac_num_arrastre, e.emp_cod,i.emp_id,grua_id, dep_id "
						+ " from sectores s, empleados e, infracciones i , agrupamientos a "
						+ " where i.emp_sector=s.sec_id and "
						+ " i.emp_id=e.emp_id and "
						+ " a.agrp_id = e.agrp_id and "
						+ " i.infrac_num =  #{infrac_num}";
	
	String GET_GRUA = " select infracciones.grua_id, grua_cod, conse_prefijo "
					+ " from infracciones, gruas, concesionaria "
					+ " where gruas.grua_id = infracciones.grua_id "
					+ " and concesionaria.conse_id = gruas.conse_id "
					+ " AND infrac_num = #{infrac_num}";
	
	
	@Select(GET_ARTICULO)
	public ArticuloSancionVO valorArticuloSancion(@Param("infrac_num") String infrac_num);

	@Select(GET_VEHICULO)
	public VehiculoTipoColorMarcaVO valorVehiculo(@Param("infrac_num") String infrac_num);

	@Select(GET_SECAGRUP)
	public SecAgrupArrasEmpVO valorSecAgrupArrasEmp(@Param("infrac_num") String infrac_num);

	@Select(GET_GRUA)
	public GruaConceVO valorGruaConce(@Param("infrac_num") String infrac_num);

	
}
