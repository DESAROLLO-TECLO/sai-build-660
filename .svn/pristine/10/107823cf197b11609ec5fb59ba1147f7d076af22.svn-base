package mx.com.teclo.saicdmx.persistencia.hibernate.vo.catalogos;

import java.io.Serializable;

public class ZonaServicioVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4111311205536889977L;

	private Long zonaId;
	private String zonaCod;
	private String zonaNombre;
	private String zonaStatus;
	
	public Long getZonaId() {
		return zonaId;
	}
	public void setZonaId(Long zonaId) {
		this.zonaId = zonaId;
	}
	public String getZonaCod() {
		return zonaCod;
	}
	public void setZonaCod(String zonaCod) {
		this.zonaCod = zonaCod;
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
