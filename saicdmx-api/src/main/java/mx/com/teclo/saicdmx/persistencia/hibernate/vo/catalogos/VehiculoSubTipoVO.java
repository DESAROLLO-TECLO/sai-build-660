package mx.com.teclo.saicdmx.persistencia.hibernate.vo.catalogos;

import java.io.Serializable;

public class VehiculoSubTipoVO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2256853958609561059L;

	private Long vSubTipoId;
	private String vSubTipoCodigo;
	private String vSubTipoNombre;
	private String vSubTipoStatus;

	public Long getvSubTipoId() {
		return vSubTipoId;
	}

	public void setvSubTipoId(Long vSubTipoId) {
		this.vSubTipoId = vSubTipoId;
	}

	public String getvSubTipoCodigo() {
		return vSubTipoCodigo;
	}

	public void setvSubTipoCodigo(String vSubTipoCodigo) {
		this.vSubTipoCodigo = vSubTipoCodigo;
	}

	public String getvSubTipoNombre() {
		return vSubTipoNombre;
	}

	public void setvSubTipoNombre(String vSubTipoNombre) {
		this.vSubTipoNombre = vSubTipoNombre;
	}

	public String getvSubTipoStatus() {
		return vSubTipoStatus;
	}

	public void setvSubTipoStatus(String vSubTipoStatus) {
		this.vSubTipoStatus = vSubTipoStatus;
	}
	public String getStatusDesc() {
		if (this.getvSubTipoStatus() != null) {
			return this.getvSubTipoStatus().equals("A") ? "Activo" : "Cancelado";
		}
		return null;
	}
}
