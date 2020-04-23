package mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity 
@Table(name="COMPONENTES_INVENTARIO")
public class ComponentesInventarioDTO implements Serializable{
		
	/**
	 * 
	 */
	private static final long serialVersionUID = 7195965270304735822L;
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "COMP_ID", unique = true, nullable = false)
	private Long idComponente;
	@Column(name = "COMP_COD")
	private String codigoComponente;
	@Column(name="COMP_NOMBRE")
	private String nombreComponente;
	@Column(name="COMP_ORDEN")
	private int	ordenComponente;
	@Column(name="COMP_STATUS")
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
	
}
