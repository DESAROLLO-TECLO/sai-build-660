package mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * @author UnitisDes0416
 *
 */
@Entity
@Table(name="RADAR_CAT_ARCHIVO_TIPO")
public class RadarCatArchivoTipoDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "ARCHIVO_TIPO_ID")
	private Long archivoTipoId;
	@Column(name = "NOMBRE")
	private String nombre;
	@Column(name = "ACTIVO")
	private Boolean activo ;
	@Column(name = "ACTIVO_COMPLEMENTACION")
	private Boolean activoComplementacion ;
	@Column(name = "CREADO_POR")
	private Long ceadoPor;
	@Column(name = "FECHA_CREACION")
	private Date fechaCracion;
	@Column(name = "MODIFICADO_POR")
	private Long modificadoPor;
	@Column(name = "ULTIMA_MODIFICACION")
	private Date ultimaModificacion;
	
	/**
	 * @return the archivoTipoId
	 */
	public Long getArchivoTipoId() {
		return archivoTipoId;
	}
	/**
	 * @return the activoComplementacion
	 */
	public Boolean getActivoComplementacion() {
		return activoComplementacion;
	}
	/**
	 * @param activoComplementacion the activoComplementacion to set
	 */
	public void setActivoComplementacion(Boolean activoComplementacion) {
		this.activoComplementacion = activoComplementacion;
	}
	/**
	 * @param archivoTipoId the archivoTipoId to set
	 */
	public void setArchivoTipoId(Long archivoTipoId) {
		this.archivoTipoId = archivoTipoId;
	}
	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}
	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	/**
	 * @return the activo
	 */
	public Boolean getActivo() {
		return activo;
	}
	/**
	 * @param activo the activo to set
	 */
	public void setActivo(Boolean activo) {
		this.activo = activo;
	}
	/**
	 * @return the ceadoPor
	 */
	public Long getCeadoPor() {
		return ceadoPor;
	}
	/**
	 * @param ceadoPor the ceadoPor to set
	 */
	public void setCeadoPor(Long ceadoPor) {
		this.ceadoPor = ceadoPor;
	}
	/**
	 * @return the fechaCracion
	 */
	public Date getFechaCracion() {
		return fechaCracion;
	}
	/**
	 * @param fechaCracion the fechaCracion to set
	 */
	public void setFechaCracion(Date fechaCracion) {
		this.fechaCracion = fechaCracion;
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
	 * @return the ultimaModificacion
	 */
	public Date getUltimaModificacion() {
		return ultimaModificacion;
	}
	/**
	 * @param ultimaModificacion the ultimaModificacion to set
	 */
	public void setUltimaModificacion(Date ultimaModificacion) {
		this.ultimaModificacion = ultimaModificacion;
	}
	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
