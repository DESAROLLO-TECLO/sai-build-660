package mx.com.teclo.saicdmx.persistencia.hibernate.vo.catalogos;

import java.io.Serializable;

public class ZonaVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4113005712517051099L;
	
	private Long zonaId;
	private String zonaCodigo;
	private String zonaNombre;
	private String zonaStatus;
	
	public Long getZonaId() {
		return zonaId;
	}
	public void setZonaId(Long zonaId) {
		this.zonaId = zonaId;
	}
	public String getZonaCodigo() {
		return zonaCodigo;
	}
	public void setZonaCodigo(String zonaCodigo) {
		this.zonaCodigo = zonaCodigo;
	}
	public String getZonaNombre() {
		return zonaNombre;
	}
	public void setZonaNombre(String zonaNombre) {
		this.zonaNombre = zonaNombre;
	}
	public String getZonaStatus() {
		return zonaStatus;
	}
	public void setZonaStatus(String zonaStatus) {
		this.zonaStatus = zonaStatus;
	}
	public String getStatusDesc() {
		return this.getZonaStatus().equals("A") ? "Activo" : "Cancelado";
	}
}
