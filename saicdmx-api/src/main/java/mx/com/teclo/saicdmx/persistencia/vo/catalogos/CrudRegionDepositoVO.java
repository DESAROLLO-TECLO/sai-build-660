package mx.com.teclo.saicdmx.persistencia.vo.catalogos;

import mx.com.teclo.saicdmx.persistencia.hibernate.vo.catalogos.EstadoVO;

public class CrudRegionDepositoVO extends BaseCrudVO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4750609202197873989L;
	
	private Long regionId;
	private String regionCodigo;
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
}
