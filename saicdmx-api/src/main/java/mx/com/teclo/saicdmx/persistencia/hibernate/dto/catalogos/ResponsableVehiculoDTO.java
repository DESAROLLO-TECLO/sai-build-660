package mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="RESPONSABLE_VEHICULO")
public class ResponsableVehiculoDTO implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "R_VEH_ID", unique = true, nullable = false)
	private Long rVehId;
	@Column(name = "R_VEH_COD")
	private String rVehCod;
	@Column(name = "R_VEH_NOMBRE")
	private String rVehNombre;
	@Column(name = "R_VEH_STATUS")
	private String rVehStatus;
	@Column(name = "CREADO_POR")
	private Long creadorPor;
	@Column(name = "FECHA_CREACION")
	private Date fechaCreacion;
	@Column(name = "MODIFICADO_POR")
	private Long modificadoPor;
	@Column(name = "ULTIMA_MODIFICACION")
	private Date ultimaMod;
	/**
	 * @return the rVehId
	 */
	public Long getrVehId() {
		return rVehId;
	}
	/**
	 * @param rVehId the rVehId to set
	 */
	public void setrVehId(Long rVehId) {
		this.rVehId = rVehId;
	}
	/**
	 * @return the rVehCod
	 */
	public String getrVehCod() {
		return rVehCod;
	}
	/**
	 * @param rVehCod the rVehCod to set
	 */
	public void setrVehCod(String rVehCod) {
		this.rVehCod = rVehCod;
	}
	/**
	 * @return the rVehNombre
	 */
	public String getrVehNombre() {
		return rVehNombre;
	}
	/**
	 * @param rVehNombre the rVehNombre to set
	 */
	public void setrVehNombre(String rVehNombre) {
		this.rVehNombre = rVehNombre;
	}
	/**
	 * @return the rVehStatus
	 */
	public String getrVehStatus() {
		return rVehStatus;
	}
	/**
	 * @param rVehStatus the rVehStatus to set
	 */
	public void setrVehStatus(String rVehStatus) {
		this.rVehStatus = rVehStatus;
	}
	/**
	 * @return the creadorPor
	 */
	public Long getCreadorPor() {
		return creadorPor;
	}
	/**
	 * @param creadorPor the creadorPor to set
	 */
	public void setCreadorPor(Long creadorPor) {
		this.creadorPor = creadorPor;
	}
	/**
	 * @return the fechaCreacion
	 */
	public Date getFechaCreacion() {
		return fechaCreacion;
	}
	/**
	 * @param fechaCreacion the fechaCreacion to set
	 */
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	/**
	 * @return the modificadoPor
	 */
	public Long getModificadoPor() {
		return modificadoPor;
	}
	/**
	 * @param modificadoPor the modificadoPor to set
	 */
	public void setModificadoPor(Long modificadoPor) {
		this.modificadoPor = modificadoPor;
	}
	/**
	 * @return the ultimaMod
	 */
	public Date getUltimaMod() {
		return ultimaMod;
	}
	/**
	 * @param ultimaMod the ultimaMod to set
	 */
	public void setUltimaMod(Date ultimaMod) {
		this.ultimaMod = ultimaMod;
	}
}
