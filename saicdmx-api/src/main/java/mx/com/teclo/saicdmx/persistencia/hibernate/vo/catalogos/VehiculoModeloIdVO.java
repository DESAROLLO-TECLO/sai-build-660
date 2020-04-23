package mx.com.teclo.saicdmx.persistencia.hibernate.vo.catalogos;

import java.io.Serializable;

public class VehiculoModeloIdVO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5263038208025788086L;
	
	private Long vMarId;
	private Long vModId;
	
	public Long getvMarId() {
		return vMarId;
	}
	public void setvMarId(Long vMarId) {
		this.vMarId = vMarId;
	}
	public Long getvModId() {
		return vModId;
	}
	public void setvModId(Long vModId) {
		this.vModId = vModId;
	}
	
	
}
