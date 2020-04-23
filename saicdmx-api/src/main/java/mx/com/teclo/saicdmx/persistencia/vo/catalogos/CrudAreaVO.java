package mx.com.teclo.saicdmx.persistencia.vo.catalogos;

public class CrudAreaVO extends BaseCrudVO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -260903719963109342L;
	
	private Long areaId;
	private String areaCodigo;
	private String areaNombre;
	
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
}
