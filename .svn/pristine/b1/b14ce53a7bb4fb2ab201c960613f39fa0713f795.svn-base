package mx.com.teclo.saicdmx.persistencia.hibernate.dto.administracion;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.CajaDTO;

/**
 * 
 * @author Javier Flores
 *
 */
@Entity
@Table(name="OPERACIONES_EXTEMPORANEAS")
public class OperacionExtDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2602586432767390900L;


	@Id
	@Column(name = "CAJA_ID", unique = true, nullable = false)
	private Long cajaId;

	@Column(name="USU_ID")
	private Long usuarioId;
	
	@Column(name="FECHA_HABILITADA")
	private Date fechaHabilitada;

	@Column(name="CAP_STATUS")	
	private String capStatus;

	@Column(name="CREADO_POR")	
	private Long creadoPor;
	
	@Column(name="FECHA_CREACION")		
	private Date fechaCreacion;
	
	@Column(name="MODIFICADO_POR")	
	private Long modificadoPor;   
	  
	@Column(name="FECHA_MODIFICACION")	
	private Date fechaModificacion;

	public Long getCajaId() {
		return cajaId;
	}

	public void setCajaId(Long cajaId) {
		this.cajaId = cajaId;
	}

	public Long getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(Long usuarioId) {
		this.usuarioId = usuarioId;
	}

	public Date getFechaHabilitada() {
		return fechaHabilitada;
	}

	public void setFechaHabilitada(Date fechaHabilitada) {
		this.fechaHabilitada = fechaHabilitada;
	}

	public String getCapStatus() {
		return capStatus;
	}

	public void setCapStatus(String capStatus) {
		this.capStatus = capStatus;
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

	public Date getFechaModificacion() {
		return fechaModificacion;
	}

	public void setFechaModificacion(Date fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}
	
	
	
}
