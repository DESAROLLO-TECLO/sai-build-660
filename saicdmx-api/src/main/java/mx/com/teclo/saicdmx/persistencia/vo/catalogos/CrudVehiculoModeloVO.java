package mx.com.teclo.saicdmx.persistencia.vo.catalogos;

import mx.com.teclo.saicdmx.persistencia.hibernate.vo.catalogos.VehiculoMarcaVO;
import mx.com.teclo.saicdmx.persistencia.hibernate.vo.catalogos.VehiculoModeloIdVO;

public class CrudVehiculoModeloVO extends BaseCrudVO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4266088473010639137L;
	
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
}
