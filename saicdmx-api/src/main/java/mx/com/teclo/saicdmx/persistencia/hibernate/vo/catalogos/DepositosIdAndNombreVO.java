package mx.com.teclo.saicdmx.persistencia.hibernate.vo.catalogos;

import java.io.Serializable;

public class DepositosIdAndNombreVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1727293923435479791L;
	private Long depId;
	private String depNombre;
	
	public Long getDepId() {
		return depId;
	}
	public void setDepId(Long depId) {
		this.depId = depId;
	}
	public String getDepNombre() {
		return depNombre;
	}
	public void setDepNombre(String depNombre) {
		this.depNombre = depNombre;
	}

}
