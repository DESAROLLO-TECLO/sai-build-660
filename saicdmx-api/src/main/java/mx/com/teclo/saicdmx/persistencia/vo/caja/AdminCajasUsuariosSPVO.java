package mx.com.teclo.saicdmx.persistencia.vo.caja;

public class AdminCajasUsuariosSPVO {

	private String p_operacion;
	private Long p_caja_id;
	private Long p_caja_emp_id;
	private Long p_caja_emp_perfil_id;
	private Long p_emp_id;
	private Long p_emp_caja_id;
	private Long p_emp_perfil_id;
	private String p_emp_puede_cobrar;
	private Long p_modificado_por;
	private Long p_resultado;
	private String p_mensaje;
	
	public AdminCajasUsuariosSPVO(String p_operacion, Long p_caja_id, Long p_caja_emp_id, Long p_caja_emp_perfil_id,
			Long p_emp_id, Long p_emp_caja_id, Long p_emp_perfil_id, String p_emp_puede_cobrar, Long p_modificado_por,
			Long p_resultado, String p_mensaje) {
		super();
		this.p_operacion = p_operacion;
		this.p_caja_id = p_caja_id;
		this.p_caja_emp_id = p_caja_emp_id;
		this.p_caja_emp_perfil_id = p_caja_emp_perfil_id;
		this.p_emp_id = p_emp_id;
		this.p_emp_caja_id = p_emp_caja_id;
		this.p_emp_perfil_id = p_emp_perfil_id;
		this.p_emp_puede_cobrar = p_emp_puede_cobrar;
		this.p_modificado_por = p_modificado_por;
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
	public Long getP_caja_emp_perfil_id() {
		return p_caja_emp_perfil_id;
	}
	public void setP_caja_emp_perfil_id(Long p_caja_emp_perfil_id) {
		this.p_caja_emp_perfil_id = p_caja_emp_perfil_id;
	}
	public Long getP_emp_id() {
		return p_emp_id;
	}
	public void setP_emp_id(Long p_emp_id) {
		this.p_emp_id = p_emp_id;
	}
	public Long getP_emp_caja_id() {
		return p_emp_caja_id;
	}
	public void setP_emp_caja_id(Long p_emp_caja_id) {
		this.p_emp_caja_id = p_emp_caja_id;
	}
	public Long getP_emp_perfil_id() {
		return p_emp_perfil_id;
	}
	public void setP_emp_perfil_id(Long p_emp_perfil_id) {
		this.p_emp_perfil_id = p_emp_perfil_id;
	}
	public String getP_emp_puede_cobrar() {
		return p_emp_puede_cobrar;
	}
	public void setP_emp_puede_cobrar(String p_emp_puede_cobrar) {
		this.p_emp_puede_cobrar = p_emp_puede_cobrar;
	}
	public Long getP_modificado_por() {
		return p_modificado_por;
	}
	public void setP_modificado_por(Long p_modificado_por) {
		this.p_modificado_por = p_modificado_por;
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
