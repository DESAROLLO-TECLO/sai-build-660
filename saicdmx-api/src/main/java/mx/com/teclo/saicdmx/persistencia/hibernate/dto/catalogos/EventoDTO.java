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
@Table(name="EVENTOS")
public class EventoDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6070182176048452611L;
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "EVEN_ID", unique = true, nullable = false)
	private Long eventoId;
	
	@Column(name = "EVEN_COD")
	private String eventoCodigo;
	
	@Column(name = "EVEN_NOMBRE")
	private String eventoNombre;
	
	@Column(name = "EVEN_STATUS")
	private String eventoStatus;
	
	@Column(name = "CREADO_POR")
	private Long creadoPor;
	
	@Column(name = "FECHA_CREACION")
	private Date fechaCreacion;
	
	@Column(name = "MODIFICADO_POR")
	private Long modificadoPor;
	
	@Column(name = "ULTIMA_MODIFICACION")
	private Date ultimaModificacion;
	
	public Long getEventoId() {
		return eventoId;
	}
	public void setEventoId(Long eventoId) {
		this.eventoId = eventoId;
	}
	public String getEventoCodigo() {
		return eventoCodigo;
	}
	public void setEventoCodigo(String eventoCodigo) {
		this.eventoCodigo = eventoCodigo;
	}
	public String getEventoNombre() {
		return eventoNombre;
	}
	public void setEventoNombre(String eventoNombre) {
		this.eventoNombre = eventoNombre;
	}
	public String getEventoStatus() {
		return eventoStatus;
	}
	public void setEventoStatus(String eventoStatus) {
		this.eventoStatus = eventoStatus;
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
