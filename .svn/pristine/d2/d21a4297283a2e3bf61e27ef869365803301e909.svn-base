package mx.com.teclo.saicdmx.persistencia.hibernate.dto.garantias;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;



@Entity
@Table(name="GARANTIAS_PROCESO_ESTATUS")
@IdClass(GarantiasEstatusProcesoPK.class)
public class GarantiaEstatusProcesoDTO implements Serializable{

	/**
	 * 
	 */
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -8297433946285390817L;
	/**
	 * 
	 */

	/**
	 * 
	 */
	
	
	@Id
	@Column(name = "PROCESO_ID")
	private Integer procesoId;
	@Id
	@Column(name = "GARANTIA_ID")
	private Long garantiaId;
	@Column(name="OBSERVACIONES")
	private String observaciones;
	@Column(name="FECHA_CREACION")
	private Date fechaCreacion;
	@Column(name="CREADO_POR")
	private Long creadoPor;
	@Column(name="MODIFICADO_POR")
	private Long modificadoPor;
	@Column(name="ULTIMA_MODIFICACION")
	private Date ultimaModificacion;
	
	@ManyToOne
	@JoinColumn(name = "GARANTIA_ID",insertable=false,updatable=false)
    private GarantiaDTO garantia;
	
	@ManyToOne
	@JoinColumn(name = "PROCESO_ID",insertable=false,updatable=false)
    private GarantiaProcesoDTO garantiaProcesoDTO;
	
	
	
	
	/**
	 * @return the observaciones
	 */
	public String getObservaciones() {
		return observaciones;
	}
	/**
	 * @param observaciones the observaciones to set
	 */
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
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
	 * @return the procesoId
	 */
	public Integer getProcesoId() {
		return procesoId;
	}
	/**
	 * @param procesoId the procesoId to set
	 */
	public void setProcesoId(Integer procesoId) {
		this.procesoId = procesoId;
	}
	/**
	 * @return the garantiaId
	 */
	public Long getGarantiaId() {
		return garantiaId;
	}
	/**
	 * @param garantiaId the garantiaId to set
	 */
	public void setGarantiaId(Long garantiaId) {
		this.garantiaId = garantiaId;
	}
	
	/**
	 * @author genunt
	 * @return GarantiaProcesoDTO
	 */
	public GarantiaProcesoDTO getGarantiaProcesoDTO() {
		return garantiaProcesoDTO;
	}
	
	/**
	 * @author genunt
	 * @returnGarantiaDTO
	 */
	public GarantiaDTO getGarantia() {
		return garantia;
	}
	public Long getModificadoPor() {
		return modificadoPor;
	}
	public void setModificadoPor(Long modificadoPor) {
		this.modificadoPor = modificadoPor;
	}
	public Date getUltimaModificacion() {
		return ultimaModificacion;
	}
	public void setUltimaModificacion(Date ultimaModificacion) {
		this.ultimaModificacion = ultimaModificacion;
	}
}
