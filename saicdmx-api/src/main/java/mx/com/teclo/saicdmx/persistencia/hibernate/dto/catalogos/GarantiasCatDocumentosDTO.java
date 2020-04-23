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
@Table(name="GARANTIAS_CAT_DOCUMENTOS")
public class GarantiasCatDocumentosDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1300263885672585888L;
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "DOCUMENTO_ID", unique = true, nullable = false)
	private Long garantiaCatDocumentoId;
	
	@Column(name = "NOMBRE")
	private String garantiaCatNombre;
	
	@Column(name = "ACTIVO")
	private Integer garantiaCatActivo;
	
	@Column(name = "CREADO_POR")
	private Long creadoPor;
	
	@Column(name = "FECHA_CREACION")
	private Date fechaCreacion;
	
	@Column(name = "MODIFICADO_POR")
	private Long modificadoPor;
	
	@Column(name = "ULTIMA_MODIFICACION")
	private Date ultimaModificacion;

	public Long getGarantiaCatDocumentoId() {
		return garantiaCatDocumentoId;
	}

	public void setGarantiaCatDocumentoId(Long garantiaCatDocumentoId) {
		this.garantiaCatDocumentoId = garantiaCatDocumentoId;
	}

	public String getGarantiaCatNombre() {
		return garantiaCatNombre;
	}

	public void setGarantiaCatNombre(String garantiaCatNombre) {
		this.garantiaCatNombre = garantiaCatNombre;
	}

	public Integer getGarantiaCatActivo() {
		return garantiaCatActivo;
	}

	public void setGarantiaCatActivo(Integer garantiaCatActivo) {
		this.garantiaCatActivo = garantiaCatActivo;
	}

	public Long getCreadoPor() {
		return creadoPor;
	}

	public void setCreadoPor(Long creadoPor) {
		this.creadoPor = creadoPor;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
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
