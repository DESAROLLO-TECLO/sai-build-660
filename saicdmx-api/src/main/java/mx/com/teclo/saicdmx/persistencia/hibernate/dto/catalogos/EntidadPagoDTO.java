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
@Table(name="ENTIDADES_PAGO")
public class EntidadPagoDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2455109298147999598L;
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "ENT_ID", unique = true, nullable = false)
	private Long entidadId;
	
	@Column(name = "ENT_COD")
	private String entidadCodigo;
	
	@Column(name = "ENT_NOMBRE")
	private String entidadNombre;
	
	@Column(name = "ENT_STATUS")
	private String entidadStatus;
	
	@Column(name = "CREADO_POR")
	private Long creadoPor;
	
	@Column(name = "FECHA_CREACION")
	private Date fechaCreacion;
	
	@Column(name = "MODIFICADO_POR")
	private Long modificadoPor;
	
	@Column(name = "ULTIMA_MODIFICACION")
	private Date ultimaModificacion;
	
	public Long getEntidadId() {
		return entidadId;
	}
	public void setEntidadId(Long entidadId) {
		this.entidadId = entidadId;
	}
	public String getEntidadCodigo() {
		return entidadCodigo;
	}
	public void setEntidadCodigo(String entidadCodigo) {
		this.entidadCodigo = entidadCodigo;
	}
	public String getEntidadNombre() {
		return entidadNombre;
	}
	public void setEntidadNombre(String entidadNombre) {
		this.entidadNombre = entidadNombre;
	}
	public String getEntidadStatus() {
		return entidadStatus;
	}
	public void setEntidadStatus(String entidadStatus) {
		this.entidadStatus = entidadStatus;
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
