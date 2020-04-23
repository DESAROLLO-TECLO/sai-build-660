package mx.com.teclo.saicdmx.persistencia.vo.catalogos;

public class CrudZonaServicioVO extends BaseCrudVO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4111311205536889977L;
	
	private Long zonaId;
	private String zonaCod;
	private String zonaNombre;
	
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
}
