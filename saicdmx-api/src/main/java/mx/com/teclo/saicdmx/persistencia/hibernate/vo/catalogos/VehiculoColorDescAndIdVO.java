package mx.com.teclo.saicdmx.persistencia.hibernate.vo.catalogos;

import java.io.Serializable;

public class VehiculoColorDescAndIdVO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8452359980378003297L;
	
	private Long vColorId;
	private String vColorNombre;
	public Long getvColorId() {
		return vColorId;
	}
	public void setvColorId(Long vColorId) {
		this.vColorId = vColorId;
	}
	public String getvColorNombre() {
		return vColorNombre;
	}
	public void setvColorNombre(String vColorNombre) {
		this.vColorNombre = vColorNombre;
	}
}
