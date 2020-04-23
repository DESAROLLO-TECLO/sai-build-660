package mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos;

import java.io.Serializable;

public class OperativoVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long operativoId;
	private String operativoNombre;
	
	public Long getOperativoId() {
		return operativoId;
	}
	public void setOperativoId(Long operativoId) {
		this.operativoId = operativoId;
	}
	public String getOperativoNombre() {
		return operativoNombre;
	}
	public void setOperativoNombre(String operativoNombre) {
		this.operativoNombre = operativoNombre;
	}
}
