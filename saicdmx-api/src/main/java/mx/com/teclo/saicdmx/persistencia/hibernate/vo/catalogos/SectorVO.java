package mx.com.teclo.saicdmx.persistencia.hibernate.vo.catalogos;

import java.io.Serializable;

public class SectorVO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4416900689406026291L;
	
	private Long secId;
	private String secCod;
	private String secNombre;
	private String secStatus;
	//private Set<UnidadTerritorialVO> unidadesT;
	public Long getSecId() {
		return secId;
	}
	public void setSecId(Long secId) {
		this.secId = secId;
	}
	public String getSecCod() {
		return secCod;
	}
	public void setSecCod(String secCod) {
		this.secCod = secCod;
	}
	public String getSecNombre() {
		return secNombre;
	}
	public void setSecNombre(String secNombre) {
		this.secNombre = secNombre;
	}
	public String getSecStatus() {
		return secStatus;
	}
	public void setSecStatus(String secStatus) {
		this.secStatus = secStatus;
	}
	
	public String getStatusDesc() {
		return this.getSecStatus().equals("A") ? "Activo" : "Cancelado";
	}
	
}
