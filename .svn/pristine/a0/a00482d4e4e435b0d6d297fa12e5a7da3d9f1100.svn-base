package mx.com.teclo.saicdmx.persistencia.hibernate.dto.garantias;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="GARANTIAS_CAT_DOCUMENTOS")
public class GarantiaDocumentoDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3773686545801319305L;
	@Id
	@Column(name = "DOCUMENTO_ID", unique = true, nullable = false)
	private Integer documentoId;
	@Column(name="NOMBRE")
	private String nombre;
	@Column(name="ACTIVO")
	private Boolean activo;
	@Column(name="FECHA_CREACION")
	private Date fechaCreacion;
	@Column(name="CREADO_POR")
	private Long creadoPor;
	@Column(name="MODIFICADO_POR")
	private Long modificadoPor;
	@Column(name="ULTIMA_MODIFICACION")
	private Date ultimaModificacion;
	/**
	 * @return the documentoId
	 */
	public Integer getDocumentoId() {
		return documentoId;
	}
	/**
	 * @param documentoId the documentoId to set
	 */
	public void setDocumentoId(Integer documentoId) {
		this.documentoId = documentoId;
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
	public Date getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	public Long getCreadoPor() {
		return creadoPor;
	}
	public void setCreadoPor(Long creadoPor) {
		this.creadoPor = creadoPor;
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
