package mx.com.teclo.saicdmx.persistencia.hibernate.vo.catalogos;

import java.io.Serializable;

public class VehiculoMarcaVO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2835333650918863149L;
	
	private Long vMarId;
	private String vMarCod;
	private String vMarNombre;
	private String vMarStatus;
	
	public Long getvMarId() {
		return vMarId;
	}
	public void setvMarId(Long vMarId) {
		this.vMarId = vMarId;
	}
	public String getvMarCod() {
		return vMarCod;
	}
	public void setvMarCod(String vMarCod) {
		this.vMarCod = vMarCod;
	}
	public String getvMarNombre() {
		return vMarNombre;
	}
	public void setvMarNombre(String vMarNombre) {
		this.vMarNombre = vMarNombre;
	}
	public String getvMarStatus() {
		return vMarStatus;
	}
	public void setvMarStatus(String vMarStatus) {
		this.vMarStatus = vMarStatus;
	}
	public String getStatusDesc() {
		if (this.getvMarStatus() != null) {
			return this.getvMarStatus().equals("A") ? "Activo" : "Cancelado";
		}
		return null;
	}
}
