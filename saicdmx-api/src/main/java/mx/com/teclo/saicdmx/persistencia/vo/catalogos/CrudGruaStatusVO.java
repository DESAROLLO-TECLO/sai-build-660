package mx.com.teclo.saicdmx.persistencia.vo.catalogos;

public class CrudGruaStatusVO extends BaseCrudVO {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -200492514352837509L;
	
	private Long gruaStatusId;
	private String gruaStatusCod;
	private String gruaStatusNombre;
	
	public Long getGruaStatusId() {
		return gruaStatusId;
	}
	public void setGruaStatusId(Long gruaStatusId) {
		this.gruaStatusId = gruaStatusId;
	}
	public String getGruaStatusCod() {
		return gruaStatusCod;
	}
	public void setGruaStatusCod(String gruaStatusCod) {
		this.gruaStatusCod = gruaStatusCod;
	}
	public String getGruaStatusNombre() {
		return gruaStatusNombre;
	}
	public void setGruaStatusNombre(String gruaStatusNombre) {
		this.gruaStatusNombre = gruaStatusNombre;
	}
}
