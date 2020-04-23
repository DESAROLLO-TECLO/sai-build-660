package mx.com.teclo.saicdmx.persistencia.hibernate.dto.semovi;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "SMV_ARCHIVOS_LICENCIA")
public class SemoviArchivosLicenciaDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6601712153902725379L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ARCHIVO_LICENCIA_ID")
	private Long archivolicenciaId;
	@Column(name = "NOMBRE_ARCHIVO")
	private String nombreArchivo;
	@Column(name = "FECHA_ARCHIVO")
	private Date fechaArchivo;
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "CAT_TIPO_ARCHIVO_ID")
	private SemoviCatTipoArchivoDTO semoviCatTipoArchivoDTO;
	@Column(name = "ACTIVO")
	private Boolean activo;
	@Column(name = "NUMERO_REGISTROS")
	private Integer numeroRegistros;
	@Column(name = "CREADO_POR")
	private Long creadoPor;
	@Column(name = "FECHA_CREACION")
	private Date fechaCreacion;
	@Column(name = "MODIFICADO_POR")
	private Long modificadoPor;
	@Column(name = "ULTIMA_MODIFICACION")
	private Date ultimaModificacion;
	@Column(name = "MOTIVO_CANCELACION")
	private String motivoCancelacion;
	
	/**
	 * @return the numeroRegistros
	 */
	public Integer getNumeroRegistros() {
		return numeroRegistros;
	}
	/**
	 * @param numeroRegistros the numeroRegistros to set
	 */
	public void setNumeroRegistros(Integer numeroRegistros) {
		this.numeroRegistros = numeroRegistros;
	}
	/**
	 * @return the motivoCancelacion
	 */
	public String getMotivoCancelacion() {
		return motivoCancelacion;
	}
	/**
	 * @param motivoCancelacion the motivoCancelacion to set
	 */
	public void setMotivoCancelacion(String motivoCancelacion) {
		this.motivoCancelacion = motivoCancelacion;
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
	 * @return the archivolicenciaId
	 */
	public Long getArchivolicenciaId() {
		return archivolicenciaId;
	}
	/**
	 * @param archivolicenciaId the archivolicenciaId to set
	 */
	public void setArchivolicenciaId(Long archivolicenciaId) {
		this.archivolicenciaId = archivolicenciaId;
	}
	/**
	 * @return the nombreArchivo
	 */
	public String getNombreArchivo() {
		return nombreArchivo;
	}
	/**
	 * @param nombreArchivo the nombreArchivo to set
	 */
	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}
	/**
	 * @return the fechaArchivo
	 */
	public Date getFechaArchivo() {
		return fechaArchivo;
	}
	/**
	 * @param fechaArchivo the fechaArchivo to set
	 */
	public void setFechaArchivo(Date fechaArchivo) {
		this.fechaArchivo = fechaArchivo;
	}
	/**
	 * @return the semoviCatTipoArchivoDTO
	 */
	public SemoviCatTipoArchivoDTO getSemoviCatTipoArchivoDTO() {
		return semoviCatTipoArchivoDTO;
	}
	/**
	 * @param semoviCatTipoArchivoDTO the semoviCatTipoArchivoDTO to set
	 */
	public void setSemoviCatTipoArchivoDTO(SemoviCatTipoArchivoDTO semoviCatTipoArchivoDTO) {
		this.semoviCatTipoArchivoDTO = semoviCatTipoArchivoDTO;
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
	 * @param fechaCreacion the fechaCracion to set
	 */
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	/**
	 * @return the modificadoPor
	 */
	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
