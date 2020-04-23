package mx.com.teclo.saicdmx.persistencia.hibernate.vo.catalogos;

import java.io.Serializable;

public class VehiculoTipoVO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8374995175739082375L;
	
	private Long vTipoId;
	private String vipoCod;
	private VehiculoSubTipoVO vSubtipo;
	private String vTipoNombre;
	private TarifasArrastreVO tarifaArrastre;
	private String vTipoStatus;
	
	public Long getvTipoId() {
		return vTipoId;
	}
	public void setvTipoId(Long vTipoId) {
		this.vTipoId = vTipoId;
	}
	public String getVipoCod() {
		return vipoCod;
	}
	public void setVipoCod(String vipoCod) {
		this.vipoCod = vipoCod;
	}
	public VehiculoSubTipoVO getvSubtipo() {
		return vSubtipo;
	}
	public void setvSubtipo(VehiculoSubTipoVO vSubtipo) {
		this.vSubtipo = vSubtipo;
	}
	public String getvTipoNombre() {
		return vTipoNombre;
	}
	public void setvTipoNombre(String vTipoNombre) {
		this.vTipoNombre = vTipoNombre;
	}
	public TarifasArrastreVO getTarifaArrastre() {
		return tarifaArrastre;
	}
	public void setTarifaArrastre(TarifasArrastreVO tarifaArrastre) {
		this.tarifaArrastre = tarifaArrastre;
	}
	public String getvTipoStatus() {
		return vTipoStatus;
	}
	public void setvTipoStatus(String vTipoStatus) {
		this.vTipoStatus = vTipoStatus;
	}
	public String getStatusDesc() {
		if (this.getvTipoStatus() != null) {
			return this.getvTipoStatus().equals("A") ? "Activo" : "Cancelado";
		}
		return null;
	}
}
