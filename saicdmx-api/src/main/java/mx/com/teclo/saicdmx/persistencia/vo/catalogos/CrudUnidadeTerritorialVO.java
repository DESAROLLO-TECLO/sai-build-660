package mx.com.teclo.saicdmx.persistencia.vo.catalogos;

import mx.com.teclo.saicdmx.persistencia.hibernate.vo.catalogos.UTIdVO;

public class CrudUnidadeTerritorialVO extends BaseCrudVO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8568098318028370982L;
	
	private UTIdVO utId;
	private String utCod;
	private String utNombre;
	private String utStatus;
	
	public UTIdVO getUtId() {
		return utId;
	}
	public void setUtId(UTIdVO utId) {
		this.utId = utId;
	}
	public String getUtCod() {
		return utCod;
	}
	public void setUtCod(String utCod) {
		this.utCod = utCod;
	}
	public String getUtNombre() {
		return utNombre;
	}
	public void setUtNombre(String utNombre) {
		this.utNombre = utNombre;
	}
	public String getUtStatus() {
		return utStatus;
	}
	public void setUtStatus(String utStatus) {
		this.utStatus = utStatus;
	}
}
