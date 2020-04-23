package mx.com.teclo.saicdmx.persistencia.vo.administracion;

public class AdminDepositoVO {

	private String p_operacion;
	private Long p_emp_id;
	private int p_emp_caja_id;
	private int p_dep_id;
	private Long p_modificado_por;
	private Integer p_resultado;
	private String p_mensaje;
	private String activaDeposito;
	private String asignaDeposito;
	private String cd_aplicacion_emp;
	private String nombreDep;
	
	public String getP_operacion() {
		return p_operacion;
	}
	public void setP_operacion(String p_operacion) {
		this.p_operacion = p_operacion;
	}
	public Long getP_emp_id() {
		return p_emp_id;
	}
	public void setP_emp_id(Long p_emp_id) {
		this.p_emp_id = p_emp_id;
	}
	public int getP_emp_caja_id() {
		return p_emp_caja_id;
	}
	public void setP_emp_caja_id(int p_emp_caja_id) {
		this.p_emp_caja_id = p_emp_caja_id;
	}
	public int getP_dep_id() {
		return p_dep_id;
	}
	public void setP_dep_id(int p_dep_id) {
		this.p_dep_id = p_dep_id;
	}
	public Long getP_modificado_por() {
		return p_modificado_por;
	}
	public void setP_modificado_por(Long p_modificado_por) {
		this.p_modificado_por = p_modificado_por;
	}
	public Integer getP_resultado() {
		return p_resultado;
	}
	public void setP_resultado(Integer p_resultado) {
		this.p_resultado = p_resultado;
	}
	public String getP_mensaje() {
		return p_mensaje;
	}
	public void setP_mensaje(String p_mensaje) {
		this.p_mensaje = p_mensaje;
	}
	public String getActivaDeposito() {
		return activaDeposito;
	}
	public void setActivaDeposito(String activaDeposito) {
		this.activaDeposito = activaDeposito;
	}
	public String getAsignaDeposito() {
		return asignaDeposito;
	}
	public void setAsignaDeposito(String asignaDeposito) {
		this.asignaDeposito = asignaDeposito;
	}
	public String getCd_aplicacion_emp() {
		return cd_aplicacion_emp;
	}
	public void setCd_aplicacion_emp(String cd_aplicacion_emp) {
		this.cd_aplicacion_emp = cd_aplicacion_emp;
	}
	public String getNombreDep() {
		return nombreDep;
	}
	public void setNombreDep(String nombreDep) {
		this.nombreDep = nombreDep;
	}
	
	
}
