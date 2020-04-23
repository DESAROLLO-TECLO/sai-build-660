package mx.com.teclo.saicdmx.persistencia.vo.catalogos;

public class CrudAgrupamientoVO extends BaseCrudVO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9210748405375718802L;
	
	private Long agrupacionId;
	private String agrupacionCodigo;
	private String agrupacionNombre;
	
	public Long getAgrupacionId() {
		return agrupacionId;
	}
	public void setAgrupacionId(Long agrupacionId) {
		this.agrupacionId = agrupacionId;
	}
	public String getAgrupacionCodigo() {
		return agrupacionCodigo;
	}
	public void setAgrupacionCodigo(String agrupacionCodigo) {
		this.agrupacionCodigo = agrupacionCodigo;
	}
	public String getAgrupacionNombre() {
		return agrupacionNombre;
	}
	public void setAgrupacionNombre(String agrupacionNombre) {
		this.agrupacionNombre = agrupacionNombre;
	}
}
