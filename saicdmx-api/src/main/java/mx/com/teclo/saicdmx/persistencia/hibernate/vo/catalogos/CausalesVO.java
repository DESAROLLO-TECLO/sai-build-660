package mx.com.teclo.saicdmx.persistencia.hibernate.vo.catalogos;

import java.io.Serializable;

public class CausalesVO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4597835820364662922L;
	
	private Long causalId;	
	private String causalCodigo;
	private String causalNombre;
	private String causalStatus;
	
	public Long getCausalId() {
		return causalId;
	}
	public void setCausalId(Long causalId) {
		this.causalId = causalId;
	}
	public String getCausalCodigo() {
		return causalCodigo;
	}
	public void setCausalCodigo(String causalCodigo) {
		this.causalCodigo = causalCodigo;
	}
	public String getCausalNombre() {
		return causalNombre;
	}
	public void setCausalNombre(String causalNombre) {
		this.causalNombre = causalNombre;
	}
	public String getCausalStatus() {
		return causalStatus;
	}
	public void setCausalStatus(String causalStatus) {
		this.causalStatus = causalStatus;
	}
	public String getStatusDesc() {
		return this.getCausalStatus().equals("A") ? "Activo" : "Cancelado";
	}
}
