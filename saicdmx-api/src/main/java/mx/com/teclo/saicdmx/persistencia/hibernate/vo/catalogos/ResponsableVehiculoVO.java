package mx.com.teclo.saicdmx.persistencia.hibernate.vo.catalogos;

import java.io.Serializable;

public class ResponsableVehiculoVO implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1741528390559702996L;
	
	private Long 	rVehId;
	private String	rVehCod;
	private String 	rVehNombre;
	private String 	rVehStatus;
	
	public Long getrVehId() {
		return rVehId;
	}
	public void setrVehId(Long rVehId) {
		this.rVehId = rVehId;
	}
	public String getrVehCod() {
		return rVehCod;
	}
	public void setrVehCod(String rVehCod) {
		this.rVehCod = rVehCod;
	}
	public String getrVehNombre() {
		return rVehNombre;
	}
	public void setrVehNombre(String rVehNombre) {
		this.rVehNombre = rVehNombre;
	}
	public String getrVehStatus() {
		return rVehStatus;
	}
	public void setrVehStatus(String rVehStatus) {
		this.rVehStatus = rVehStatus;
	}
	public String getStatusDesc() {
		return this.getrVehStatus().equals("A") ? "Activo" : "Cancelado";
	}
}
