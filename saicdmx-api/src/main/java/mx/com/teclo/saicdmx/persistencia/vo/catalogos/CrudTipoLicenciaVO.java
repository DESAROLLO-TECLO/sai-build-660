package mx.com.teclo.saicdmx.persistencia.vo.catalogos;

public class CrudTipoLicenciaVO extends BaseCrudVO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4563488879348970786L;
	
	private Long tipoLId;
	private String tipoLCod;
	private String tipoLNombre;
	
	public Long getTipoLId() {
		return tipoLId;
	}
	public void setTipoLId(Long tipoLId) {
		this.tipoLId = tipoLId;
	}
	public String getTipoLCod() {
		return tipoLCod;
	}
	public void setTipoLCod(String tipoLCod) {
		this.tipoLCod = tipoLCod;
	}
	public String getTipoLNombre() {
		return tipoLNombre;
	}
	public void setTipoLNombre(String tipoLNombre) {
		this.tipoLNombre = tipoLNombre;
	}
}
