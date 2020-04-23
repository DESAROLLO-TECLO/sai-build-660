package mx.com.teclo.saicdmx.persistencia.hibernate.vo.catalogos;

import java.io.Serializable;

public class GruaStatusVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5736743371184087030L;
	
	private Long gruaStatusId;
	private String gruaStatusCod;
	private String gruaStatusNombre;	
	private String status;
	
	public Long getGruaStatusId() {
		return gruaStatusId;
	}
	public void setGruaStatusId(Long gruaStatusId) {
		this.gruaStatusId = gruaStatusId;
	}
	public String getGruaStatusCod() {
		return gruaStatusCod;
	}
	public void setGruaStatusCod(String gruaStatusCod) {
		this.gruaStatusCod = gruaStatusCod;
	}
	public String getGruaStatusNombre() {
		return gruaStatusNombre;
	}
	public void setGruaStatusNombre(String gruaStatusNombre) {
		this.gruaStatusNombre = gruaStatusNombre;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getStatusDesc() {
		return this.getStatus().equals("A") ? "Activo" : "Cancelado";
	}
}
