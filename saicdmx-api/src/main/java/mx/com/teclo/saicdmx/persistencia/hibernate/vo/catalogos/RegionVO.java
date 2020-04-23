package mx.com.teclo.saicdmx.persistencia.hibernate.vo.catalogos;

import java.io.Serializable;

public class RegionVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8386890705152923809L;

	private Long regionId;
	private String regionCodigo;
	private String regionNombre;
	private String regionStatus;
	private Long estadoId;
	
	public Long getRegionId() {
		return regionId;
	}
	public void setRegionId(Long regionId) {
		this.regionId = regionId;
	}
	public String getRegionCodigo() {
		return regionCodigo;
	}
	public void setRegionCodigo(String regionCodigo) {
		this.regionCodigo = regionCodigo;
	}
	public String getRegionNombre() {
		return regionNombre;
	}
	public void setRegionNombre(String regionNombre) {
		this.regionNombre = regionNombre;
	}
	public String getRegionStatus() {
		return regionStatus;
	}
	public void setRegionStatus(String regionStatus) {
		this.regionStatus = regionStatus;
	}
	
	public Long getEstadoId() {
		return estadoId;
	}
	public void setEstadoId(Long estadoId) {
		this.estadoId = estadoId;
	}
	public String getStatusDesc() {
		return this.getRegionStatus().equals("A") ? "Activo" : "Cancelado";
	}
}
