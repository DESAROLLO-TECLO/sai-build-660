package mx.com.teclo.saicdmx.persistencia.hibernate.vo.catalogos;

import java.io.Serializable;

public class ZonasVO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7121137821556046455L;
	
	private Long zonaId;
	private String zonaCod;
	private String zonaNombre;
	private String zonaEstatus;
	
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
	public String getZonaEstatus() {
		return zonaEstatus;
	}
	public void setZonaEstatus(String zonaEstatus) {
		this.zonaEstatus = zonaEstatus;
	}
	
}
