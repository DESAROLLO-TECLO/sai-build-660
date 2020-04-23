package mx.com.teclo.saicdmx.persistencia.hibernate.vo.catalogos;

import java.io.Serializable;

public class VehiculoModeloVO implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2107324610389057403L;
	
	private VehiculoModeloIdVO vModId;
	private String vModCod;
	private String vModNombre;
	private String vModStatus;
	private VehiculoMarcaVO vehiculoMarca;
	
	public VehiculoModeloIdVO getvModId() {
		return vModId;
	}
	public void setvModId(VehiculoModeloIdVO vModId) {
		this.vModId = vModId;
	}
	public String getvModCod() {
		return vModCod;
	}
	public void setvModCod(String vModCod) {
		this.vModCod = vModCod;
	}
	public String getvModNombre() {
		return vModNombre;
	}
	public void setvModNombre(String vModNombre) {
		this.vModNombre = vModNombre;
	}
	public String getvModStatus() {
		return vModStatus;
	}
	public void setvModStatus(String vModStatus) {
		this.vModStatus = vModStatus;
	}
	public VehiculoMarcaVO getVehiculoMarca() {
		return vehiculoMarca;
	}
	public void setVehiculoMarca(VehiculoMarcaVO vehiculoMarca) {
		this.vehiculoMarca = vehiculoMarca;
	}
	public String getStatusDesc() {
		return this.getvModStatus().equals("A") ? "Activo" : "Cancelado";
	}
}
