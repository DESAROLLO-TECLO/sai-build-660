package mx.com.teclo.saicdmx.persistencia.vo.remisionadeposito;

import java.io.Serializable;

public class ReciboArrastreVO implements Serializable{

	private static final long serialVersionUID = 7893000053351752299L;

	private String infrac_num;
	private String infrac_num_arrastre;
	private String fecha;
	private String sec_nombre;
	private String conse_nombre;
	private String infrac_tipo_arrastre;
	private String infrac_m_entre_calle;
	private String infrac_m_y_la_calle;
	private Integer grua_id;
	private String emp_placa;
	private String emp_nombre;
	private String infrac_docto;
	
	private String infrac_oper_grua;
	private String grua_cod;
	private String infrac_placa;
	private String vmar_nombre;
	private String vmod_nombre;
	private String vcolor_nombre;
	
	private String ingr_asn;
	private String del_nombre;
	private String infrac_m_colonia;
	private String infrac_observacion;
	private String infrac_m_en_la_calle;
	
	/**
	 * @return the infrac_num_arrastre
	 */
	public String getInfrac_num_arrastre() {
		return infrac_num_arrastre;
	}
	/**
	 * @param infrac_num_arrastre the infrac_num_arrastre to set
	 */
	public void setInfrac_num_arrastre(String infrac_num_arrastre) {
		this.infrac_num_arrastre = infrac_num_arrastre;
	}
	/**
	 * @return the fecha
	 */
	public String getFecha() {
		return fecha;
	}
	/**
	 * @param fecha the fecha to set
	 */
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	/**
	 * @return the sec_nombre
	 */
	public String getSec_nombre() {
		return sec_nombre;
	}
	/**
	 * @param sec_nombre the sec_nombre to set
	 */
	public void setSec_nombre(String sec_nombre) {
		this.sec_nombre = sec_nombre;
	}
	/**
	 * @return the conse_nombre
	 */
	public String getConse_nombre() {
		return conse_nombre;
	}
	/**
	 * @param conse_nombre the conse_nombre to set
	 */
	public void setConse_nombre(String conse_nombre) {
		this.conse_nombre = conse_nombre;
	}
	/**
	 * @return the infrac_tipo_arrastre
	 */
	public String getInfrac_tipo_arrastre() {
		return infrac_tipo_arrastre;
	}
	/**
	 * @param infrac_tipo_arrastre the infrac_tipo_arrastre to set
	 */
	public void setInfrac_tipo_arrastre(String infrac_tipo_arrastre) {
		this.infrac_tipo_arrastre = infrac_tipo_arrastre;
	}
	/**
	 * @return the infrac_m_entre_calle
	 */
	public String getInfrac_m_entre_calle() {
		return infrac_m_entre_calle;
	}
	/**
	 * @param infrac_m_entre_calle the infrac_m_entre_calle to set
	 */
	public void setInfrac_m_entre_calle(String infrac_m_entre_calle) {
		this.infrac_m_entre_calle = infrac_m_entre_calle;
	}
	/**
	 * @return the infrac_m_y_la_calle
	 */
	public String getInfrac_m_y_la_calle() {
		return infrac_m_y_la_calle;
	}
	/**
	 * @param infrac_m_y_la_calle the infrac_m_y_la_calle to set
	 */
	public void setInfrac_m_y_la_calle(String infrac_m_y_la_calle) {
		this.infrac_m_y_la_calle = infrac_m_y_la_calle;
	}
	/**
	 * @return the grua_id
	 */
	public Integer getGrua_id() {
		return grua_id;
	}
	/**
	 * @param grua_id the grua_id to set
	 */
	public void setGrua_id(Integer grua_id) {
		this.grua_id = grua_id;
	}
	/**
	 * @return the emp_nombre
	 */
	public String getEmp_nombre() {
		return emp_nombre;
	}
	/**
	 * @param emp_nombre the emp_nombre to set
	 */
	public void setEmp_nombre(String emp_nombre) {
		this.emp_nombre = emp_nombre;
	}
	/**
	 * @return the infrac_docto
	 */
	public String getInfrac_docto() {
		return infrac_docto;
	}
	/**
	 * @param infrac_docto the infrac_docto to set
	 */
	public void setInfrac_docto(String infrac_docto) {
		this.infrac_docto = infrac_docto;
	}
	/**
	 * @return the grua_cod
	 */
	public String getGrua_cod() {
		return grua_cod;
	}
	/**
	 * @param grua_cod the grua_cod to set
	 */
	public void setGrua_cod(String grua_cod) {
		this.grua_cod = grua_cod;
	}
	/**
	 * @return the vmar_nombre
	 */
	public String getVmar_nombre() {
		return vmar_nombre;
	}
	/**
	 * @param vmar_nombre the vmar_nombre to set
	 */
	public void setVmar_nombre(String vmar_nombre) {
		this.vmar_nombre = vmar_nombre;
	}
	/**
	 * @return the vmod_nombre
	 */
	public String getVmod_nombre() {
		return vmod_nombre;
	}
	/**
	 * @param vmod_nombre the vmod_nombre to set
	 */
	public void setVmod_nombre(String vmod_nombre) {
		this.vmod_nombre = vmod_nombre;
	}
	/**
	 * @return the vcolor_nombre
	 */
	public String getVcolor_nombre() {
		return vcolor_nombre;
	}
	/**
	 * @param vcolor_nombre the vcolor_nombre to set
	 */
	public void setVcolor_nombre(String vcolor_nombre) {
		this.vcolor_nombre = vcolor_nombre;
	}
	/**
	 * @return the ingr_asn
	 */
	public String getIngr_asn() {
		return ingr_asn;
	}
	/**
	 * @param ingr_asn the ingr_asn to set
	 */
	public void setIngr_asn(String ingr_asn) {
		this.ingr_asn = ingr_asn;
	}
	
	/**
	 * @return the emp_placa
	 */
	public String getEmp_placa() {
		return emp_placa;
	}
	/**
	 * @param emp_placa the emp_placa to set
	 */
	public void setEmp_placa(String emp_placa) {
		this.emp_placa = emp_placa;
	}
	/**
	 * @return the infrac_placa
	 */
	public String getInfrac_placa() {
		return infrac_placa;
	}
	/**
	 * @param infrac_placa the infrac_placa to set
	 */
	public void setInfrac_placa(String infrac_placa) {
		this.infrac_placa = infrac_placa;
	}
	/**
	 * @return the del_nombre
	 */
	public String getDel_nombre() {
		return del_nombre;
	}
	/**
	 * @param del_nombre the del_nombre to set
	 */
	public void setDel_nombre(String del_nombre) {
		this.del_nombre = del_nombre;
	}
	/**
	 * @return the infrac_m_colonia
	 */
	public String getInfrac_m_colonia() {
		return infrac_m_colonia;
	}
	/**
	 * @param infrac_m_colonia the infrac_m_colonia to set
	 */
	public void setInfrac_m_colonia(String infrac_m_colonia) {
		this.infrac_m_colonia = infrac_m_colonia;
	}
	/**
	 * @return the infrac_observacion
	 */
	public String getInfrac_observacion() {
		return infrac_observacion;
	}
	/**
	 * @param infrac_observacion the infrac_observacion to set
	 */
	public void setInfrac_observacion(String infrac_observacion) {
		this.infrac_observacion = infrac_observacion;
	}
	/**
	 * @return the infrac_num
	 */
	public String getInfrac_num() {
		return infrac_num;
	}
	/**
	 * @param infrac_num the infrac_num to set
	 */
	public void setInfrac_num(String infrac_num) {
		this.infrac_num = infrac_num;
	}
	/**
	 * @return the infrac_oper_grua
	 */
	public String getInfrac_oper_grua() {
		return infrac_oper_grua;
	}
	/**
	 * @param infrac_oper_grua the infrac_oper_grua to set
	 */
	public void setInfrac_oper_grua(String infrac_oper_grua) {
		this.infrac_oper_grua = infrac_oper_grua;
	}
	/**
	 * @return the infrac_m_en_la_calle
	 */
	public String getInfrac_m_en_la_calle() {
		return infrac_m_en_la_calle;
	}
	/**
	 * @param infrac_m_en_la_calle the infrac_m_en_la_calle to set
	 */
	public void setInfrac_m_en_la_calle(String infrac_m_en_la_calle) {
		this.infrac_m_en_la_calle = infrac_m_en_la_calle;
	}
}