package mx.com.teclo.saicdmx.persistencia.hibernate.vo.catalogos;

import java.io.Serializable;

public class VehiculoTipoDescAndIdVO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8374995175739082375L;
	
	private Long vTipoId;
	private String vTipoNombre;
	
	public Long getvTipoId() {
		return vTipoId;
	}
	public void setvTipoId(Long vTipoId) {
		this.vTipoId = vTipoId;
	}
	public String getvTipoNombre() {
		return vTipoNombre;
	}
	public void setvTipoNombre(String vTipoNombre) {
		this.vTipoNombre = vTipoNombre;
	}

}
