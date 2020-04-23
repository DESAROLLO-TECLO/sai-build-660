package mx.com.teclo.saicdmx.persistencia.vo.catalogos;

public class CrudSectorVO extends BaseCrudVO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6361259491313044690L;
	
	private Long sectorId;
	private String sectorCodigo;
	private String sectorNombre;
	private Long estadoId;
	private Long delegacionId;
	
	public Long getSectorId() {
		return sectorId;
	}
	public void setSectorId(Long sectorId) {
		this.sectorId = sectorId;
	}
	public String getSectorCodigo() {
		return sectorCodigo;
	}
	public void setSectorCodigo(String sectorCodigo) {
		this.sectorCodigo = sectorCodigo;
	}
	public String getSectorNombre() {
		return sectorNombre;
	}
	public void setSectorNombre(String sectorNombre) {
		this.sectorNombre = sectorNombre;
	}
	public Long getEstadoId() {
		return estadoId;
	}
	public void setEstadoId(Long estadoId) {
		this.estadoId = estadoId;
	}
	public Long getDelegacionId() {
		return delegacionId;
	}
	public void setDelegacionId(Long delegacionId) {
		this.delegacionId = delegacionId;
	}
}
