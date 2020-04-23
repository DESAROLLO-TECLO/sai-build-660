package mx.com.teclo.saicdmx.persistencia.mybatis.dao.remisionadeposito;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import mx.com.teclo.saicdmx.persistencia.vo.remisionadeposito.IngresoInfraccionVO;

@Mapper
public interface BuscarUpdPorIdMyBatisDAO {

	/// muestra los datos de la infraccion en Con Infraccion
	String GET_DATOS_INFRAC = "select infrac_num, infrac_placa, infrac_num_ctrl,infrac_impresa,infrac_num_arrastre, vorigen, "
			+ " infrac_ley_transporte, infrac_con_placa, infracciones.emp_id , emp_placa, grua_cod, dep_id, conse_prefijo,"
			+ " infrac_m_en_la_calle, infrac_m_entre_calle, infrac_m_y_la_calle, infrac_m_colonia,  infrac_m_fecha_hora, "
			+ " tipo_l_cod, infracciones.tipo_l_id, infrac_m_del_id,infrac_m_edo_id,del_nombre, infrac_tipo_arrastre"
			+ " FROM INFRACCIONES JOIN EMPLEADOS ON infracciones.emp_id = empleados.emp_id"
			+ " LEFT JOIN GRUAS ON gruas.grua_id = infracciones.grua_id"
			+ " LEFT JOIN CONCESIONARIA ON concesionaria.conse_id = gruas.conse_id"
			+ " LEFT JOIN DELEGACIONES ON infracciones.infrac_m_del_id = delegaciones.del_id"
			+ " AND DELEGACIONES.EDO_ID = INFRACCIONES.infrac_m_edo_id"
			+ " LEFT JOIN TIPO_LICENCIA ON tipo_licencia.tipo_l_id = infracciones.tipo_l_id"
			+ " WHERE infrac_num_ctrl = #{infrac_num_ctrl}";
	
	/// muestra los datos de la infraccion en consulta de 
	String GET_DATOS_INFRAC_CONSULTA = " select i.infrac_num, infrac_placa, infrac_num_ctrl,infrac_num_arrastre, vorigen, infrac_ley_transporte, infrac_con_placa, "
									 + " i.emp_id , emp_placa, gruas.grua_cod, i.dep_id, conse_prefijo, infrac_m_en_la_calle,infrac_m_colonia, infrac_m_entre_calle, infrac_m_y_la_calle, "
						             + " infrac_m_fecha_hora,i.tipo_l_id, tipo_l_cod, infrac_m_del_id,infrac_m_edo_id,del_nombre,infrac_tipo_arrastre, "
									 + " ingr_resguardo,ingr_observa as infrac_observacion,causa_id,vtipo_cod,t_ingr_id,ingr_asn as asn,sellado as v_sellado,cajuela as v_cajuela, tipo_grua "
									 + " from infracciones i,empleados,gruas ,concesionaria,tipo_licencia, delegaciones,ingresos "
									 + " where i.emp_id = empleados.emp_id  "
									 + " and gruas.grua_id = i.grua_id  "
									 + " and concesionaria.conse_id = gruas.conse_id "
									 + " and tipo_licencia.tipo_l_id = i.tipo_l_id  "
									 + " and infrac_m_edo_id = edo_id  "
									 + " and infrac_m_del_id = del_id  "
									 + " and infrac_num_ctrl = ingr_num_ctrl  "
									 + " AND infrac_num_ctrl = #{infrac_num_ctrl} ";
	
	
	/// muestra los datos de la infraccion en Con Infraccion
	/*String GET_DATOS_INFRAC_INGRE = "select infrac_num, infrac_placa, infrac_num_ctrl,infrac_impresa,infrac_num_arrastre, vorigen, "
			+ " infrac_ley_transporte, infrac_con_placa, infracciones.emp_id , emp_placa, dep_id,"
			+ " infrac_m_en_la_calle, infrac_m_entre_calle, infrac_m_y_la_calle, infrac_m_colonia,  infrac_m_fecha_hora, "
			+ " tipo_l_cod, infracciones.tipo_l_id, infrac_m_del_id,infrac_m_edo_id,del_nombre, infrac_tipo_arrastre"
			+ " from infracciones,empleados , tipo_licencia, delegaciones "
			+ " where infracciones.emp_id = empleados.emp_id  "
			+ " and tipo_licencia.tipo_l_id = infracciones.tipo_l_id "
			+ " and infrac_m_edo_id = edo_id "
			+ " and infrac_m_del_id = del_id "
			+ " and infrac_num_ctrl = #{infrac_num_ctrl}";	*/
	
	String GET_DATOS_INFRAC_INGRE = "select infrac_num_ctrl, infrac_num_arrastre, infrac_num, "
				+ "infrac_placa, vorigen FROM INFRACCIONES WHERE infrac_num_ctrl = #{infrac_num_ctrl}";
	
	@Select(GET_DATOS_INFRAC)
	public IngresoInfraccionVO buscarUpdPorId(@Param("infrac_num_ctrl") String infrac_num_ctrl);

	@Select(GET_DATOS_INFRAC_CONSULTA)
	public IngresoInfraccionVO mostarDatosPorNCI(@Param("infrac_num_ctrl") String infrac_num_ctrl);

	@Select(GET_DATOS_INFRAC_INGRE)
	public IngresoInfraccionVO buscarPorNCI(@Param("infrac_num_ctrl") String infrac_num_ctrl);

	
}
