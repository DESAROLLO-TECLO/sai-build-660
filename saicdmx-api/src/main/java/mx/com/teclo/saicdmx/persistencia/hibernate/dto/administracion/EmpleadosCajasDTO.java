package mx.com.teclo.saicdmx.persistencia.hibernate.dto.administracion;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "EMPLEADOS_CAJAS")
public class EmpleadosCajasDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1587926325870717454L;

	@EmbeddedId
	private EmpleadosCajasIdDTO id;

	@Column(name = "CAJA_STATUS")
	private String stCaja;

	@Column(name = "CREADO_POR")
	private Long creadoPor;

	@Column(name = "FECHA_CREACION")
	private Date fechaCreacion;

	@Column(name = "MODIFICADO_POR")
	private Long modificadoPor;

	@Column(name = "ULTIMA_MODIFICACION")
	private Date ultimaModificacion;

	public EmpleadosCajasDTO(){}
	
	public EmpleadosCajasDTO(EmpleadosCajasIdDTO id, String stCaja, Long creadoPor, Date fechaCreacion,
			Long modificadoPor, Date ultimaModificacion){
		this.id = id;
		this.stCaja = stCaja;
		this.creadoPor = creadoPor;
		this.fechaCreacion = fechaCreacion;
		this.modificadoPor = modificadoPor;
		this.ultimaModificacion = ultimaModificacion;
	}
	
	public EmpleadosCajasIdDTO getId() {
		return id;
	}

	public void setId(EmpleadosCajasIdDTO id) {
		this.id = id;
	}

	public String getStCaja() {
		return stCaja;
	}

	public void setStCaja(String stCaja) {
		this.stCaja = stCaja;
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
