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
@Table(name="OPERACIONES_EXTEMPORANEAS")
public class OperacionesExtemporaneasDTO implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "CAJA_ID", unique = true, nullable = false)
	private Long cajaId;
	@Column(name = "USU_ID")
	private Long usuId;
	@Column(name = "FECHA_HABILITADA")
	private Date fechaHab;
	@Column(name = "CAP_STATUS")
	private String capStatus;
	@Column(name = "CREADO_POR")
	private Long creadoPor;
	@Column(name = "FECHA_CREACION")
	private Date fechaCreacion;
	@Column(name = "MODIFICADO_POR")
	private Long modificadoPor;
	@Column(name = "FECHA_MODIFICACION")
	private Date fechaModificada;
	/**
	 * @return the cajaId
	 */
	public Long getCajaId() {
		return cajaId;
	}
	/**
	 * @param cajaId the cajaId to set
	 */
	public void setCajaId(Long cajaId) {
		this.cajaId = cajaId;
	}
	/**
	 * @return the usuId
	 */
	public Long getUsuId() {
		return usuId;
	}
	/**
	 * @param usuId the usuId to set
	 */
	public void setUsuId(Long usuId) {
		this.usuId = usuId;
	}
	/**
	 * @return the fechaHab
	 */
	public Date getFechaHab() {
		return fechaHab;
	}
	/**
	 * @param fechaHab the fechaHab to set
	 */
	public void setFechaHab(Date fechaHab) {
		this.fechaHab = fechaHab;
	}
	/**
	 * @return the capStatus
	 */
	public String getCapStatus() {
		return capStatus;
	}
	/**
	 * @param capStatus the capStatus to set
	 */
	public void setCapStatus(String capStatus) {
		this.capStatus = capStatus;
	}
	/**
	 * @return the creadoPor
	 */
	public Long getCreadoPor() {
		return creadoPor;
	}
	/**
	 * @param creadoPor the creadoPor to set
	 */
	public void setCreadoPor(Long creadoPor) {
		this.creadoPor = creadoPor;
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
	 * @return the fechaModificada
	 */
	public Date getFechaModificada() {
		return fechaModificada;
	}
	/**
	 * @param fechaModificada the fechaModificada to set
	 */
	public void setFechaModificada(Date fechaModificada) {
		this.fechaModificada = fechaModificada;
	}
}
