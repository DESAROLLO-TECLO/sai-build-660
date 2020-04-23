package mx.com.teclo.saicdmx.persistencia.vo.catalogos;

public class CrudRegionVO extends BaseCrudVO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3051207350387778473L;
	
	private Long estadoId;
	private Long regionId;
	private String regionCodigo;
	private String regionNombre;
	
	public Long getEstadoId() {
		return estadoId;
	}
	public void setEstadoId(Long estadoId) {
		this.estadoId = estadoId;
	}
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
}
