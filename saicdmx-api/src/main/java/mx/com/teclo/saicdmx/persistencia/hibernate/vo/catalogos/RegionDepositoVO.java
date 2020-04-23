package mx.com.teclo.saicdmx.persistencia.hibernate.vo.catalogos;

import java.io.Serializable;

public class RegionDepositoVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5374949559745029870L;
	
	private Long regionId;
	private String regionCodigo;
	private String regionStatus;
	private String regionNombre;
	private EstadoVO estadoDTO;
	
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
	public String getRegionStatus() {
		return regionStatus;
	}
	public void setRegionStatus(String regionStatus) {
		this.regionStatus = regionStatus;
	}
	public String getRegionNombre() {
		return regionNombre;
	}
	public void setRegionNombre(String regionNombre) {
		this.regionNombre = regionNombre;
	}
	public EstadoVO getEstadoDTO() {
		return estadoDTO;
	}
	public void setEstadoDTO(EstadoVO estadoDTO) {
		this.estadoDTO = estadoDTO;
	}
	public String getStatusDesc() {
		return this.getRegionStatus().equals("A") ? "Activo" : "Cancelado";
	}
}
