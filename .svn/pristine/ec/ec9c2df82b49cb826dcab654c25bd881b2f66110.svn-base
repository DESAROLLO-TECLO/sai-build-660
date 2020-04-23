package mx.com.teclo.saicdmx.persistencia.hibernate.vo.catalogos;

import java.io.Serializable;

public class CausaIngresoVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long idCausaIngreso;
	private String codigoCausaIngreso;
	private String nombreCausaIngreso;
	private String statusCausaIngreso;
	/**
	 * @return the idCausaIngreso
	 */
	public Long getIdCausaIngreso() {
		return idCausaIngreso;
	}
	/**
	 * @param idCausaIngreso the idCausaIngreso to set
	 */
	public void setIdCausaIngreso(Long idCausaIngreso) {
		this.idCausaIngreso = idCausaIngreso;
	}
	/**
	 * @return the codigoCausaIngreso
	 */
	public String getCodigoCausaIngreso() {
		return codigoCausaIngreso;
	}
	/**
	 * @param codigoCausaIngreso the codigoCausaIngreso to set
	 */
	public void setCodigoCausaIngreso(String codigoCausaIngreso) {
		this.codigoCausaIngreso = codigoCausaIngreso;
	}
	/**
	 * @return the nombreCausaIngreso
	 */
	public String getNombreCausaIngreso() {
		return nombreCausaIngreso;
	}
	/**
	 * @param nombreCausaIngreso the nombreCausaIngreso to set
	 */
	public void setNombreCausaIngreso(String nombreCausaIngreso) {
		this.nombreCausaIngreso = nombreCausaIngreso;
	}
	/**
	 * @return the statusCausaIngreso
	 */
	public String getStatusCausaIngreso() {
		return statusCausaIngreso;
	}
	/**
	 * @param statusCausaIngreso the statusCausaIngreso to set
	 */
	public void setStatusCausaIngreso(String statusCausaIngreso) {
		this.statusCausaIngreso = statusCausaIngreso;
	}
	
	public String getStatusDesc() {
		return this.getStatusCausaIngreso().equals("A") ? "Activo" : "Cancelado";
	}
}
