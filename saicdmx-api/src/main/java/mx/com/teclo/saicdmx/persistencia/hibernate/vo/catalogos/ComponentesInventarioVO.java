package mx.com.teclo.saicdmx.persistencia.hibernate.vo.catalogos;

import java.io.Serializable;

public class ComponentesInventarioVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6514449887810787900L;
	private Long idComponente;
	private String codigoComponente;
	private String nombreComponente;
	private int	ordenComponente;
	private String statusComponente;
	/**
	 * @return the idComponente
	 */
	public Long getIdComponente() {
		return idComponente;
	}
	/**
	 * @param idComponente the idComponente to set
	 */
	public void setIdComponente(Long idComponente) {
		this.idComponente = idComponente;
	}
	/**
	 * @return the codigoComponente
	 */
	public String getCodigoComponente() {
		return codigoComponente;
	}
	/**
	 * @param codigoComponente the codigoComponente to set
	 */
	public void setCodigoComponente(String codigoComponente) {
		this.codigoComponente = codigoComponente;
	}
	/**
	 * @return the nombreComponente
	 */
	public String getNombreComponente() {
		return nombreComponente;
	}
	/**
	 * @param nombreComponente the nombreComponente to set
	 */
	public void setNombreComponente(String nombreComponente) {
		this.nombreComponente = nombreComponente;
	}
	/**
	 * @return the ordenComponente
	 */
	public int getOrdenComponente() {
		return ordenComponente;
	}
	/**
	 * @param ordenComponente the ordenComponente to set
	 */
	public void setOrdenComponente(int ordenComponente) {
		this.ordenComponente = ordenComponente;
	}
	/**
	 * @return the statusComponente
	 */
	public String getStatusComponente() {
		return statusComponente;
	}
	/**
	 * @param statusComponente the statusComponente to set
	 */
	public void setStatusComponente(String statusComponente) {
		this.statusComponente = statusComponente;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ComponentesInventarioVO [idComponente=" + idComponente + ", codigoComponente=" + codigoComponente
				+ ", nombreComponente=" + nombreComponente + ", ordenComponente=" + ordenComponente
				+ ", statusComponente=" + statusComponente + "]";
	}
	
	public String getStatusDesc() {
		return this.getStatusComponente().equals("A") ? "Activo" : "Cancelado";
	}
	
}
