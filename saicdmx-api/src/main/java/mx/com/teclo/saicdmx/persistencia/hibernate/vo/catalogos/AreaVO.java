package mx.com.teclo.saicdmx.persistencia.hibernate.vo.catalogos;

import java.io.Serializable;

public class AreaVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6926209017803633857L;
	
	private Long areaId;
	private String areaCodigo;
	private String areaNombre;
	private String areaStatus;
	
	public Long getAreaId() {
		return areaId;
	}
	public void setAreaId(Long areaId) {
		this.areaId = areaId;
	}
	public String getAreaCodigo() {
		return areaCodigo;
	}
	public void setAreaCodigo(String areaCodigo) {
		this.areaCodigo = areaCodigo;
	}
	public String getAreaNombre() {
		return areaNombre;
	}
	public void setAreaNombre(String areaNombre) {
		this.areaNombre = areaNombre;
	}
	public String getAreaStatus() {
		return areaStatus;
	}
	public void setAreaStatus(String areaStatus) {
		this.areaStatus = areaStatus;
	}
	public String getStatusDesc() {
		return this.getAreaStatus().equals("A") ? "Activo" : "Cancelado";
	}
}
