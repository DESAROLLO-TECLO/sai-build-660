package mx.com.teclo.saicdmx.persistencia.vo.catalogos;

import java.io.Serializable;

public class RespuestaBusquedaDelegacionesVO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3713512316155062173L;
	
	private Long delId;
	private Long edoId;
	private Long regId;
	private String delCod;
	private String regNombre;
	private String delNombre;
	private String delStatus;
	
	public Long getDelId() {
		return delId;
	}
	public void setDelId(Long delId) {
		this.delId = delId;
	}
	public Long getEdoId() {
		return edoId;
	}
	public void setEdoId(Long edoId) {
		this.edoId = edoId;
	}
	public Long getRegId() {
		return regId;
	}
	public void setRegId(Long regId) {
		this.regId = regId;
	}
	public String getDelCod() {
		return delCod;
	}
	public void setDelCod(String delCod) {
		this.delCod = delCod;
	}
	public String getRegNombre() {
		return regNombre;
	}
	public void setRegNombre(String regNombre) {
		this.regNombre = regNombre;
	}
	public String getDelNombre() {
		return delNombre;
	}
	public void setDelNombre(String delNombre) {
		this.delNombre = delNombre;
	}
	public String getDelStatus() {
		return delStatus;
	}
	public void setDelStatus(String delStatus) {
		this.delStatus = delStatus;
	}
	public String getStatusDesc() {
		return this.getDelStatus().equals("A") ? "Activo" : "Cancelado";
	}
}
