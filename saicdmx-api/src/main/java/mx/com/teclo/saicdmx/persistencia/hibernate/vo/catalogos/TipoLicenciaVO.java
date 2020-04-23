package mx.com.teclo.saicdmx.persistencia.hibernate.vo.catalogos;

import java.io.Serializable;

public class TipoLicenciaVO implements Serializable
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5548855136954072316L;
	
	private Long tipoLId;
	private String tipoLCod;
	private String tipoLNombre;
	private String tipoLStatus;
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
	public String getTipoLStatus() {
		return tipoLStatus;
	}
	public void setTipoLStatus(String tipoLStatus) {
		this.tipoLStatus = tipoLStatus;
	}
	public String getStatusDesc() {
		return this.getTipoLStatus().equals("A") ? "Activo" : "Cancelado";
	}
}
