package mx.com.teclo.saicdmx.persistencia.vo.caja;

public class AdminCajasDepositosSPVO {
	
	private String p_operacion;
	private Long p_caja_id;
	private Long p_caja_emp_id;
	private Long p_dep_id;
	private Long p_modificado_por;
	private String cd_aplicacion;
	private Long p_resultado;
	private String p_mensaje;
	
	public AdminCajasDepositosSPVO(String p_operacion, Long p_caja_id, Long p_caja_emp_id, Long p_dep_id,
			Long p_modificado_por, String cd_aplicacion, Long p_resultado, String p_mensaje) {
		super();
		this.p_operacion = p_operacion;
		this.p_caja_id = p_caja_id;
		this.p_caja_emp_id = p_caja_emp_id;
		this.p_dep_id = p_dep_id;
		this.p_modificado_por = p_modificado_por;
		this.cd_aplicacion = cd_aplicacion;
		this.p_resultado = p_resultado;
		this.p_mensaje = p_mensaje;
	}
	
	public String getP_operacion() {
		return p_operacion;
	}
	public void setP_operacion(String p_operacion) {
		this.p_operacion = p_operacion;
	}
	public Long getP_caja_id() {
		return p_caja_id;
	}
	public void setP_caja_id(Long p_caja_id) {
		this.p_caja_id = p_caja_id;
	}
	public Long getP_caja_emp_id() {
		return p_caja_emp_id;
	}
	public void setP_caja_emp_id(Long p_caja_emp_id) {
		this.p_caja_emp_id = p_caja_emp_id;
	}
	public Long getP_dep_id() {
		return p_dep_id;
	}
	public void setP_dep_id(Long p_dep_id) {
		this.p_dep_id = p_dep_id;
	}
	public Long getP_modificado_por() {
		return p_modificado_por;
	}
	public void setP_modificado_por(Long p_modificado_por) {
		this.p_modificado_por = p_modificado_por;
	}
	public String getCd_aplicacion() {
		return cd_aplicacion;
	}

	public void setCd_aplicacion(String cd_aplicacion) {
		this.cd_aplicacion = cd_aplicacion;
	}

	public Long getP_resultado() {
		return p_resultado;
	}
	public void setP_resultado(Long p_resultado) {
		this.p_resultado = p_resultado;
	}
	public String getP_mensaje() {
		return p_mensaje;
	}
	public void setP_mensaje(String p_mensaje) {
		this.p_mensaje = p_mensaje;
	}
	
}
