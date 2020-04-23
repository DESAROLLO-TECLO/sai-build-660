package mx.com.teclo.saicdmx.persistencia.hibernate.dto.logs;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="WEB_LOGS")
public class WebLogsDTO implements Serializable{	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6977734763697688343L;

	/**
	 * 
	 */
	

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "LOG_ID", unique = true, nullable = false)
	private Long logId;
	
	@Column(name="LOG_NOMBRE")
	private String logNombre;
	
	@Column(name="LOG_DESCRIPCION")
	private String logDescripcion;
	
	@Column(name="LOG_ESTATUS")
	private String logEstatus;
	
	@Column(name="RUTA_ARCHIVOS")
	private String rutaArchivos;
	
	@Column(name="TIPO_EXTENSIONES")
	private String tipoExtensiones;
	
	@Column(name="FECHA_CREACION")
	private Date fechaCreacion;
	
	@Column(name="CREADO_POR")
	private Long creadoPor;
	
	@Column(name="FECHA_MODIFICACION")
	private Date fechaModificacion;
	
	@Column(name="MODIFICADO_POR")
	private Long modificadoPor;

	public Long getLogId() {
		return logId;
	}

	public void setLogId(Long logId) {
		this.logId = logId;
	}

	public String getLogNombre() {
		return logNombre;
	}

	public void setLogNombre(String logNombre) {
		this.logNombre = logNombre;
	}

	public String getLogDescripcion() {
		return logDescripcion;
	}

	public void setLogDescripcion(String logDescripcion) {
		this.logDescripcion = logDescripcion;
	}

	public String getLogEstatus() {
		return logEstatus;
	}

	public void setLogEstatus(String logEstatus) {
		this.logEstatus = logEstatus;
	}

	public String getRutaArchivos() {
		return rutaArchivos;
	}

	public void setRutaArchivos(String rutaArchivos) {
		this.rutaArchivos = rutaArchivos;
	}

	public String getTipoExtensiones() {
		return tipoExtensiones;
	}

	public void setTipoExtensiones(String tipoExtensiones) {
		this.tipoExtensiones = tipoExtensiones;
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

	public Date getFechaModificacion() {
		return fechaModificacion;
	}

	public void setFechaModificacion(Date fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}

	public Long getModificadoPor() {
		return modificadoPor;
	}

	public void setModificadoPor(Long modificadoPor) {
		this.modificadoPor = modificadoPor;
	}
	
	
	
	

}
