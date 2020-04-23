package mx.com.teclo.saicdmx.persistencia.hibernate.vo.catalogos;

import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.DelId;

public class DelegacionVO {
	
	private Long edoId;

	private DelId delId;
	
	private String delCod;
	
	private String delNombre;
	
	private String delStatus;

	/**
	 * @return the edoId
	 */
	public Long getEdoId() {
		return edoId;
	}

	/**
	 * @param edoId the edoId to set
	 */
	public void setEdoId(Long edoId) {
		this.edoId = edoId;
	}

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
	 * @return the delCod
	 */
	public String getDelCod() {
		return delCod;
	}

	/**
	 * @param delCod the delCod to set
	 */
	public void setDelCod(String delCod) {
		this.delCod = delCod;
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

	/**
	 * @return the delStatus
	 */
	public String getDelStatus() {
		return delStatus;
	}

	/**
	 * @param delStatus the delStatus to set
	 */
	public void setDelStatus(String delStatus) {
		this.delStatus = delStatus;
	}
	
	public String getStatusDesc() {
		return this.getDelStatus().equals("A") ? "Activo" : "Cancelado";
	}
}
