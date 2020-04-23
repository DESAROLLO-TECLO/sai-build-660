package mx.com.teclo.saicdmx.persistencia.vo.infracciones;

public class InfraccionValidaImagenSPVO {
	private Long p_emp_id;
	private String p_infrac_num;
	private String p_estatus;
	private String p_resultado;   
	private String p_mensaje;
	public Long getP_emp_id() {
		return p_emp_id;
	}
	public void setP_emp_id(Long p_emp_id) {
		this.p_emp_id = p_emp_id;
	}
	public String getP_infrac_num() {
		return p_infrac_num;
	}
	public void setP_infrac_num(String p_infrac_num) {
		this.p_infrac_num = p_infrac_num;
	}
	public String getP_estatus() {
		return p_estatus;
	}
	public void setP_estatus(String p_estatus) {
		this.p_estatus = p_estatus;
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
	
	@Override
	public String toString() {
		return "InfraccionValidaImagenSPVO [p_emp_id=" + p_emp_id + ", p_infrac_num=" + p_infrac_num + ", p_estatus="
				+ p_estatus + ", p_resultado=" + p_resultado + ", p_mensaje=" + p_mensaje + "]";
	}
	
	public InfraccionValidaImagenSPVO(Long p_emp_id, String p_infrac_num, String p_estatus, String p_resultado,
			String p_mensaje) {
		super();
		this.p_emp_id = p_emp_id;
		this.p_infrac_num = p_infrac_num;
		this.p_estatus = p_estatus;
		this.p_resultado = p_resultado;
		this.p_mensaje = p_mensaje;
	}
	
}
