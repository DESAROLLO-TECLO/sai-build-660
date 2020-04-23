package mx.com.teclo.saicdmx.persistencia.vo.infracciones;

import java.io.Serializable;

public class AltaInfraccionSPVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7635616968829730717L;
	
	private String p_infrac_impresa; 	
	private Integer p_sec_id;
	private String p_infrac_con_placa;
	private Integer p_infrac_placa_edo;
	private String p_infrac_placa;
	private String p_infrac_i_paterno;
	private String p_infrac_i_materno;
	private String p_infrac_i_nombre;
	private String p_infrac_i_calle;
	private String p_infrac_i_num_ext;
	private String p_infrac_i_num_int;
	private String p_infrac_i_colonia;
	private Integer p_infrac_i_edo_id;
	private Integer p_infrac_i_del_id;
	private String p_infrac_licencia;
	private Integer p_tipo_l_id;
	private String p_infrac_l_serv_publico;
	private Integer p_infrac_lic_edo;
	private Integer p_vmar_id;	
	private Integer p_vmod_id;
	private Integer p_vcolor_id;
	private String p_vorigen;
	private String p_vtarjeta_circulacion;
	private Integer p_vtipo_id;
	private String p_art_motivacion;	
	private String p_infrac_m_en_la_calle;
	private String p_infrac_m_entre_calle;	
	private String p_infrac_m_y_la_calle;
	private String p_infrac_m_colonia;	
	private Integer p_infrac_m_edo_id;
	private Integer p_infrac_m_del_id;
	private Integer p_art_id;
	private String p_infrac_ley_transporte;
	private Integer p_sancion_art_id;
	private String p_infrac_arrastre;
	private String p_infrac_num_arrastre;
	private String p_infrac_tipo_arrastre;	
	private Integer p_grua_id;
	private Integer p_dep_id; 	
	private Integer p_emp_id;
	private Integer p_emp_sector;  	
	private Integer p_emp_agrup;
	private Integer p_r_veh_id;	
	private String p_oper;
	private String p_infrac_num_ctrl;	
	private Integer p_ut_id;
	private String p_m_fecha;
	private Integer p_modificado_por;
    private String autorizaId; 
	private String p_resultado;
	private String p_mensaje;
	private String P_EMPLEADO_COD;
	private String P_EMP_AGRUPAMIENTO;
	private String P_EMP_PAT;
	private String P_EMP_MAT;
	private String P_EMP_NOMBRE;
	private String P_INFRAC_CAPTURA;
	private Integer P_INFRAC_APOYO_GRUA;
	private String p_infrac_rfc;
	private String p_fecha_emision;
	private String p_infrac_observacion;
	private String p_motivo_cambio;
	private String p_infrac_con_direccion;
	private String p_infrac_frente_al_num;
	private Integer p_observe_que;
	private Integer p_garantia_tipo_id;
	private String p_garantia_folio;
	
	public AltaInfraccionSPVO(){
		
	}
	public AltaInfraccionSPVO(String p_infrac_impresa, Integer p_sec_id, String p_infrac_con_placa,
			Integer p_infrac_placa_edo, String p_infrac_placa, String p_infrac_i_paterno, String p_infrac_i_materno,
			String p_infrac_i_nombre, String p_infrac_i_calle, String p_infrac_i_num_ext, String p_infrac_i_num_int,
			String p_infrac_i_colonia, Integer p_infrac_i_edo_id, Integer p_infrac_i_del_id, String p_infrac_licencia,
			Integer p_tipo_l_id, String p_infrac_l_serv_publico, Integer p_infrac_lic_edo, Integer p_vmar_id,
			Integer p_vmod_id, Integer p_vcolor_id, String p_vorigen, String p_vtarjeta_circulacion, Integer p_vtipo_id,
			String p_art_motivacion, String p_infrac_m_en_la_calle, String p_infrac_m_entre_calle,
			String p_infrac_m_y_la_calle, String p_infrac_m_colonia, Integer p_infrac_m_edo_id,
			Integer p_infrac_m_del_id, Integer p_art_id, String p_infrac_ley_transporte, Integer p_sancion_art_id,
			String p_infrac_arrastre, String p_infrac_num_arrastre, String p_infrac_tipo_arrastre, Integer p_grua_id,
			Integer p_dep_id, Integer p_emp_id, Integer p_emp_sector, Integer p_emp_agrup, Integer p_r_veh_id,
			String p_oper, String p_infrac_num_ctrl, Integer p_ut_id, String p_m_fecha, Integer p_modificado_por,
			String p_resultado, String p_mensaje, String p_EMPLEADO_COD, String p_EMP_AGRUPAMIENTO, String p_EMP_PAT,
			String p_EMP_MAT, String p_EMP_NOMBRE, String p_INFRAC_CAPTURA, Integer p_INFRAC_APOYO_GRUA,
			String p_infrac_rfc, String p_fecha_emision, String p_infrac_observacion, String p_motivo_cambio,
			String p_infrac_con_direccion, String p_infrac_frente_al_num, Integer p_observe_que,
			Integer p_garantia_tipo_id, String p_garantia_folio) {
		super();
		this.p_infrac_impresa = p_infrac_impresa;
		this.p_sec_id = p_sec_id;
		this.p_infrac_con_placa = p_infrac_con_placa;
		this.p_infrac_placa_edo = p_infrac_placa_edo;
		this.p_infrac_placa = p_infrac_placa;
		this.p_infrac_i_paterno = p_infrac_i_paterno;
		this.p_infrac_i_materno = p_infrac_i_materno;
		this.p_infrac_i_nombre = p_infrac_i_nombre;
		this.p_infrac_i_calle = p_infrac_i_calle;
		this.p_infrac_i_num_ext = p_infrac_i_num_ext;
		this.p_infrac_i_num_int = p_infrac_i_num_int;
		this.p_infrac_i_colonia = p_infrac_i_colonia;
		this.p_infrac_i_edo_id = p_infrac_i_edo_id;
		this.p_infrac_i_del_id = p_infrac_i_del_id;
		this.p_infrac_licencia = p_infrac_licencia;
		this.p_tipo_l_id = p_tipo_l_id;
		this.p_infrac_l_serv_publico = p_infrac_l_serv_publico;
		this.p_infrac_lic_edo = p_infrac_lic_edo;
		this.p_vmar_id = p_vmar_id;
		this.p_vmod_id = p_vmod_id;
		this.p_vcolor_id = p_vcolor_id;
		this.p_vorigen = p_vorigen;
		this.p_vtarjeta_circulacion = p_vtarjeta_circulacion;
		this.p_vtipo_id = p_vtipo_id;
		this.p_art_motivacion = p_art_motivacion;
		this.p_infrac_m_en_la_calle = p_infrac_m_en_la_calle;
		this.p_infrac_m_entre_calle = p_infrac_m_entre_calle;
		this.p_infrac_m_y_la_calle = p_infrac_m_y_la_calle;
		this.p_infrac_m_colonia = p_infrac_m_colonia;
		this.p_infrac_m_edo_id = p_infrac_m_edo_id;
		this.p_infrac_m_del_id = p_infrac_m_del_id;
		this.p_art_id = p_art_id;
		this.p_infrac_ley_transporte = p_infrac_ley_transporte;
		this.p_sancion_art_id = p_sancion_art_id;
		this.p_infrac_arrastre = p_infrac_arrastre;
		this.p_infrac_num_arrastre = p_infrac_num_arrastre;
		this.p_infrac_tipo_arrastre = p_infrac_tipo_arrastre;
		this.p_grua_id = p_grua_id;
		this.p_dep_id = p_dep_id;
		this.p_emp_id = p_emp_id;
		this.p_emp_sector = p_emp_sector;
		this.p_emp_agrup = p_emp_agrup;
		this.p_r_veh_id = p_r_veh_id;
		this.p_oper = p_oper;
		this.p_infrac_num_ctrl = p_infrac_num_ctrl;
		this.p_ut_id = p_ut_id;
		this.p_m_fecha = p_m_fecha;
		this.p_modificado_por = p_modificado_por;
		this.p_resultado = p_resultado;
		this.p_mensaje = p_mensaje;
		this.P_EMPLEADO_COD = p_EMPLEADO_COD;
		this.P_EMP_AGRUPAMIENTO = p_EMP_AGRUPAMIENTO;
		this.P_EMP_PAT = p_EMP_PAT;
		this.P_EMP_MAT = p_EMP_MAT;
		this.P_EMP_NOMBRE = p_EMP_NOMBRE;
		this.P_INFRAC_CAPTURA = p_INFRAC_CAPTURA;
		this.P_INFRAC_APOYO_GRUA = p_INFRAC_APOYO_GRUA;
		this.p_infrac_rfc = p_infrac_rfc;
		this.p_fecha_emision = p_fecha_emision;
		this.p_infrac_observacion = p_infrac_observacion;
		this.p_motivo_cambio = p_motivo_cambio;
		this.p_infrac_con_direccion = p_infrac_con_direccion;
		this.p_infrac_frente_al_num = p_infrac_frente_al_num;
		this.p_observe_que = p_observe_que;
		this.p_garantia_tipo_id = p_garantia_tipo_id;
		this.p_garantia_folio = p_garantia_folio;
	}
	public String getP_infrac_impresa() {
		return p_infrac_impresa;
	}
	public void setP_infrac_impresa(String p_infrac_impresa) {
		this.p_infrac_impresa = p_infrac_impresa;
	}
	public Integer getP_sec_id() {
		return p_sec_id;
	}
	public void setP_sec_id(Integer p_sec_id) {
		this.p_sec_id = p_sec_id;
	}
	public String getP_infrac_con_placa() {
		return p_infrac_con_placa;
	}
	public void setP_infrac_con_placa(String p_infrac_con_placa) {
		this.p_infrac_con_placa = p_infrac_con_placa;
	}
	public Integer getP_infrac_placa_edo() {
		return p_infrac_placa_edo;
	}
	public void setP_infrac_placa_edo(Integer p_infrac_placa_edo) {
		this.p_infrac_placa_edo = p_infrac_placa_edo;
	}
	public String getP_infrac_placa() {
		return p_infrac_placa;
	}
	public void setP_infrac_placa(String p_infrac_placa) {
		this.p_infrac_placa = p_infrac_placa;
	}
	public String getP_infrac_i_paterno() {
		return p_infrac_i_paterno;
	}
	public void setP_infrac_i_paterno(String p_infrac_i_paterno) {
		this.p_infrac_i_paterno = p_infrac_i_paterno;
	}
	public String getP_infrac_i_materno() {
		return p_infrac_i_materno;
	}
	public void setP_infrac_i_materno(String p_infrac_i_materno) {
		this.p_infrac_i_materno = p_infrac_i_materno;
	}
	public String getP_infrac_i_nombre() {
		return p_infrac_i_nombre;
	}
	public void setP_infrac_i_nombre(String p_infrac_i_nombre) {
		this.p_infrac_i_nombre = p_infrac_i_nombre;
	}
	public String getP_infrac_i_calle() {
		return p_infrac_i_calle;
	}
	public void setP_infrac_i_calle(String p_infrac_i_calle) {
		this.p_infrac_i_calle = p_infrac_i_calle;
	}
	public String getP_infrac_i_num_ext() {
		return p_infrac_i_num_ext;
	}
	public void setP_infrac_i_num_ext(String p_infrac_i_num_ext) {
		this.p_infrac_i_num_ext = p_infrac_i_num_ext;
	}
	public String getP_infrac_i_num_int() {
		return p_infrac_i_num_int;
	}
	public void setP_infrac_i_num_int(String p_infrac_i_num_int) {
		this.p_infrac_i_num_int = p_infrac_i_num_int;
	}
	public String getP_infrac_i_colonia() {
		return p_infrac_i_colonia;
	}
	public void setP_infrac_i_colonia(String p_infrac_i_colonia) {
		this.p_infrac_i_colonia = p_infrac_i_colonia;
	}
	public Integer getP_infrac_i_edo_id() {
		return p_infrac_i_edo_id;
	}
	public void setP_infrac_i_edo_id(Integer p_infrac_i_edo_id) {
		this.p_infrac_i_edo_id = p_infrac_i_edo_id;
	}
	public Integer getP_infrac_i_del_id() {
		return p_infrac_i_del_id;
	}
	public void setP_infrac_i_del_id(Integer p_infrac_i_del_id) {
		this.p_infrac_i_del_id = p_infrac_i_del_id;
	}
	public String getP_infrac_licencia() {
		return p_infrac_licencia;
	}
	public void setP_infrac_licencia(String p_infrac_licencia) {
		this.p_infrac_licencia = p_infrac_licencia;
	}
	public Integer getP_tipo_l_id() {
		return p_tipo_l_id;
	}
	public void setP_tipo_l_id(Integer p_tipo_l_id) {
		this.p_tipo_l_id = p_tipo_l_id;
	}
	public String getP_infrac_l_serv_publico() {
		return p_infrac_l_serv_publico;
	}
	public void setP_infrac_l_serv_publico(String p_infrac_l_serv_publico) {
		this.p_infrac_l_serv_publico = p_infrac_l_serv_publico;
	}
	public Integer getP_infrac_lic_edo() {
		return p_infrac_lic_edo;
	}
	public void setP_infrac_lic_edo(Integer p_infrac_lic_edo) {
		this.p_infrac_lic_edo = p_infrac_lic_edo;
	}
	public Integer getP_vmar_id() {
		return p_vmar_id;
	}
	public void setP_vmar_id(Integer p_vmar_id) {
		this.p_vmar_id = p_vmar_id;
	}
	public Integer getP_vmod_id() {
		return p_vmod_id;
	}
	public void setP_vmod_id(Integer p_vmod_id) {
		this.p_vmod_id = p_vmod_id;
	}
	public Integer getP_vcolor_id() {
		return p_vcolor_id;
	}
	public void setP_vcolor_id(Integer p_vcolor_id) {
		this.p_vcolor_id = p_vcolor_id;
	}
	public String getP_vorigen() {
		return p_vorigen;
	}
	public void setP_vorigen(String p_vorigen) {
		this.p_vorigen = p_vorigen;
	}
	public String getP_vtarjeta_circulacion() {
		return p_vtarjeta_circulacion;
	}
	public void setP_vtarjeta_circulacion(String p_vtarjeta_circulacion) {
		this.p_vtarjeta_circulacion = p_vtarjeta_circulacion;
	}
	public Integer getP_vtipo_id() {
		return p_vtipo_id;
	}
	public void setP_vtipo_id(Integer p_vtipo_id) {
		this.p_vtipo_id = p_vtipo_id;
	}
	public String getP_art_motivacion() {
		return p_art_motivacion;
	}
	public void setP_art_motivacion(String p_art_motivacion) {
		this.p_art_motivacion = p_art_motivacion;
	}
	public String getP_infrac_m_en_la_calle() {
		return p_infrac_m_en_la_calle;
	}
	public void setP_infrac_m_en_la_calle(String p_infrac_m_en_la_calle) {
		this.p_infrac_m_en_la_calle = p_infrac_m_en_la_calle;
	}
	public String getP_infrac_m_entre_calle() {
		return p_infrac_m_entre_calle;
	}
	public void setP_infrac_m_entre_calle(String p_infrac_m_entre_calle) {
		this.p_infrac_m_entre_calle = p_infrac_m_entre_calle;
	}
	public String getP_infrac_m_y_la_calle() {
		return p_infrac_m_y_la_calle;
	}
	public void setP_infrac_m_y_la_calle(String p_infrac_m_y_la_calle) {
		this.p_infrac_m_y_la_calle = p_infrac_m_y_la_calle;
	}
	public String getP_infrac_m_colonia() {
		return p_infrac_m_colonia;
	}
	public void setP_infrac_m_colonia(String p_infrac_m_colonia) {
		this.p_infrac_m_colonia = p_infrac_m_colonia;
	}
	public Integer getP_infrac_m_edo_id() {
		return p_infrac_m_edo_id;
	}
	public void setP_infrac_m_edo_id(Integer p_infrac_m_edo_id) {
		this.p_infrac_m_edo_id = p_infrac_m_edo_id;
	}
	public Integer getP_infrac_m_del_id() {
		return p_infrac_m_del_id;
	}
	public void setP_infrac_m_del_id(Integer p_infrac_m_del_id) {
		this.p_infrac_m_del_id = p_infrac_m_del_id;
	}
	public Integer getP_art_id() {
		return p_art_id;
	}
	public void setP_art_id(Integer p_art_id) {
		this.p_art_id = p_art_id;
	}
	public String getP_infrac_ley_transporte() {
		return p_infrac_ley_transporte;
	}
	public void setP_infrac_ley_transporte(String p_infrac_ley_transporte) {
		this.p_infrac_ley_transporte = p_infrac_ley_transporte;
	}
	public Integer getP_sancion_art_id() {
		return p_sancion_art_id;
	}
	public void setP_sancion_art_id(Integer p_sancion_art_id) {
		this.p_sancion_art_id = p_sancion_art_id;
	}
	public String getP_infrac_arrastre() {
		return p_infrac_arrastre;
	}
	public void setP_infrac_arrastre(String p_infrac_arrastre) {
		this.p_infrac_arrastre = p_infrac_arrastre;
	}
	public String getP_infrac_num_arrastre() {
		return p_infrac_num_arrastre;
	}
	public void setP_infrac_num_arrastre(String p_infrac_num_arrastre) {
		this.p_infrac_num_arrastre = p_infrac_num_arrastre;
	}
	public String getP_infrac_tipo_arrastre() {
		return p_infrac_tipo_arrastre;
	}
	public void setP_infrac_tipo_arrastre(String p_infrac_tipo_arrastre) {
		this.p_infrac_tipo_arrastre = p_infrac_tipo_arrastre;
	}
	public Integer getP_grua_id() {
		return p_grua_id;
	}
	public void setP_grua_id(Integer p_grua_id) {
		this.p_grua_id = p_grua_id;
	}
	public Integer getP_dep_id() {
		return p_dep_id;
	}
	public void setP_dep_id(Integer p_dep_id) {
		this.p_dep_id = p_dep_id;
	}
	public Integer getP_emp_id() {
		return p_emp_id;
	}
	public void setP_emp_id(Integer p_emp_id) {
		this.p_emp_id = p_emp_id;
	}
	public Integer getP_emp_sector() {
		return p_emp_sector;
	}
	public void setP_emp_sector(Integer p_emp_sector) {
		this.p_emp_sector = p_emp_sector;
	}
	public Integer getP_emp_agrup() {
		return p_emp_agrup;
	}
	public void setP_emp_agrup(Integer p_emp_agrup) {
		this.p_emp_agrup = p_emp_agrup;
	}
	public Integer getP_r_veh_id() {
		return p_r_veh_id;
	}
	public void setP_r_veh_id(Integer p_r_veh_id) {
		this.p_r_veh_id = p_r_veh_id;
	}
	public String getP_oper() {
		return p_oper;
	}
	public void setP_oper(String p_oper) {
		this.p_oper = p_oper;
	}
	public String getP_infrac_num_ctrl() {
		return p_infrac_num_ctrl;
	}
	public void setP_infrac_num_ctrl(String p_infrac_num_ctrl) {
		this.p_infrac_num_ctrl = p_infrac_num_ctrl;
	}
	public Integer getP_ut_id() {
		return p_ut_id;
	}
	public void setP_ut_id(Integer p_ut_id) {
		this.p_ut_id = p_ut_id;
	}
	public String getP_m_fecha() {
		return p_m_fecha;
	}
	public void setP_m_fecha(String p_m_fecha) {
		this.p_m_fecha = p_m_fecha;
	}
	public Integer getP_modificado_por() {
		return p_modificado_por;
	}
	public void setP_modificado_por(Integer p_modificado_por) {
		this.p_modificado_por = p_modificado_por;
	}
	public String getAutorizaId() {
		return autorizaId;
	}
	public void setAutorizaId(String autorizaId) {
		this.autorizaId = autorizaId;
	}
	public String getP_resultado() {
		return p_resultado;
	}
	public void setP_resultado(String p_resultado) {
		this.p_resultado = p_resultado;
	}
	public String getP_mensaje() {
		return p_mensaje;
	}
	public void setP_mensaje(String p_mensaje) {
		this.p_mensaje = p_mensaje;
	}
	public String getP_EMPLEADO_COD() {
		return P_EMPLEADO_COD;
	}
	public void setP_EMPLEADO_COD(String p_EMPLEADO_COD) {
		P_EMPLEADO_COD = p_EMPLEADO_COD;
	}
	public String getP_EMP_AGRUPAMIENTO() {
		return P_EMP_AGRUPAMIENTO;
	}
	public void setP_EMP_AGRUPAMIENTO(String p_EMP_AGRUPAMIENTO) {
		P_EMP_AGRUPAMIENTO = p_EMP_AGRUPAMIENTO;
	}
	public String getP_EMP_PAT() {
		return P_EMP_PAT;
	}
	public void setP_EMP_PAT(String p_EMP_PAT) {
		P_EMP_PAT = p_EMP_PAT;
	}
	public String getP_EMP_MAT() {
		return P_EMP_MAT;
	}
	public void setP_EMP_MAT(String p_EMP_MAT) {
		P_EMP_MAT = p_EMP_MAT;
	}
	public String getP_EMP_NOMBRE() {
		return P_EMP_NOMBRE;
	}
	public void setP_EMP_NOMBRE(String p_EMP_NOMBRE) {
		P_EMP_NOMBRE = p_EMP_NOMBRE;
	}
	public String getP_INFRAC_CAPTURA() {
		return P_INFRAC_CAPTURA;
	}
	public void setP_INFRAC_CAPTURA(String p_INFRAC_CAPTURA) {
		P_INFRAC_CAPTURA = p_INFRAC_CAPTURA;
	}
	public Integer getP_INFRAC_APOYO_GRUA() {
		return P_INFRAC_APOYO_GRUA;
	}
	public void setP_INFRAC_APOYO_GRUA(Integer p_INFRAC_APOYO_GRUA) {
		P_INFRAC_APOYO_GRUA = p_INFRAC_APOYO_GRUA;
	}
	public String getP_infrac_rfc() {
		return p_infrac_rfc;
	}
	public void setP_infrac_rfc(String p_infrac_rfc) {
		this.p_infrac_rfc = p_infrac_rfc;
	}
	public String getP_fecha_emision() {
		return p_fecha_emision;
	}
	public void setP_fecha_emision(String p_fecha_emision) {
		this.p_fecha_emision = p_fecha_emision;
	}
	public String getP_infrac_observacion() {
		return p_infrac_observacion;
	}
	public void setP_infrac_observacion(String p_infrac_observacion) {
		this.p_infrac_observacion = p_infrac_observacion;
	}
	public String getP_motivo_cambio() {
		return p_motivo_cambio;
	}
	public void setP_motivo_cambio(String p_motivo_cambio) {
		this.p_motivo_cambio = p_motivo_cambio;
	}
	public String getP_infrac_con_direccion() {
		return p_infrac_con_direccion;
	}
	public void setP_infrac_con_direccion(String p_infrac_con_direccion) {
		this.p_infrac_con_direccion = p_infrac_con_direccion;
	}
	public String getP_infrac_frente_al_num() {
		return p_infrac_frente_al_num;
	}
	public void setP_infrac_frente_al_num(String p_infrac_frente_al_num) {
		this.p_infrac_frente_al_num = p_infrac_frente_al_num;
	}
	public Integer getP_observe_que() {
		return p_observe_que;
	}
	public void setP_observe_que(Integer p_observe_que) {
		this.p_observe_que = p_observe_que;
	}
	public Integer getP_garantia_tipo_id() {
		return p_garantia_tipo_id;
	}
	public void setP_garantia_tipo_id(Integer p_garantia_tipo_id) {
		this.p_garantia_tipo_id = p_garantia_tipo_id;
	}
	public String getP_garantia_folio() {
		return p_garantia_folio;
	}
	public void setP_garantia_folio(String p_garantia_folio) {
		this.p_garantia_folio = p_garantia_folio;
	}
}
