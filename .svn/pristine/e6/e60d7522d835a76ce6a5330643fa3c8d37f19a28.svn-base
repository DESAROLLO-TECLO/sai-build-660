package mx.com.teclo.saicdmx.persistencia.hibernate.vo.catalogos;

import java.io.Serializable;

public class VehiculoColorVO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8452359980378003297L;
	
	private Long vColorId;
	private String vColorCod;
	private String vColorNombre;
	private String vColorStatus;
	public Long getvColorId() {
		return vColorId;
	}
	public void setvColorId(Long vColorId) {
		this.vColorId = vColorId;
	}
	public String getvColorCod() {
		return vColorCod;
	}
	public void setvColorCod(String vColorCod) {
		this.vColorCod = vColorCod;
	}
	public String getvColorNombre() {
		return vColorNombre;
	}
	public void setvColorNombre(String vColorNombre) {
		this.vColorNombre = vColorNombre;
	}
	public String getvColorStatus() {
		return vColorStatus;
	}
	public void setvColorStatus(String vColorStatus) {
		this.vColorStatus = vColorStatus;
	}
	public String getStatusDesc() {
		return this.getvColorStatus().equals("A") ? "Activo" : "Cancelado";
	}
}
