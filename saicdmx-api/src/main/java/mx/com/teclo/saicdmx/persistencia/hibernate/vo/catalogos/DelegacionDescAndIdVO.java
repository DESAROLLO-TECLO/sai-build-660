package mx.com.teclo.saicdmx.persistencia.hibernate.vo.catalogos;

import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.DelId;

public class DelegacionDescAndIdVO {

	private DelId delId;
	
	private String delNombre;
	/**
	 * @return the delId
	 */
	public DelId getDelId() {
		return delId;
	}

	/**
	 * @param delId the delId to set
	 */
	public void setDelId(DelId delId) {
		this.delId = delId;
	}

	/**
	 * @return the delNombre
	 */
	public String getDelNombre() {
		return delNombre;
	}

	/**
	 * @param delNombre the delNombre to set
	 */
	public void setDelNombre(String delNombre) {
		this.delNombre = delNombre;
	}

}
