package mx.com.teclo.saicdmx.persistencia.hibernate.vo.catalogos;

import java.io.Serializable;

public class VehiculoMarcaDescAndIdVO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2835333650918863149L;
	
	private Long vMarId;
	private String vMarNombre;
	
	public Long getvMarId() {
		return vMarId;
	}
	public void setvMarId(Long vMarId) {
		this.vMarId = vMarId;
	}
	public String getvMarNombre() {
		return vMarNombre;
	}
	public void setvMarNombre(String vMarNombre) {
		this.vMarNombre = vMarNombre;
	}
	
}
