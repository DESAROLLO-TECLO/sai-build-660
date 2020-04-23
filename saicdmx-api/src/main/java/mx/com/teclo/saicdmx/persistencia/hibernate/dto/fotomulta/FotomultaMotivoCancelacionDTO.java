package mx.com.teclo.saicdmx.persistencia.hibernate.dto.fotomulta;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="FOTOMULTA_CAT_MOT_CANCELACION")
public class FotomultaMotivoCancelacionDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7769817719987533272L;

	@Id
	@Column(name = "MOTIVO_ID")     
	private Integer motivoId;	
	
	@Column(name = "MOTIVO_DESCRIPCION")     
	private String motivoDescripcion;
		
	@Column(name = "ACTIVO")     
	private Integer activo;	

	@Column(name = "CREADOR_POR")
	private Long creadoPor;
	
	@Column(name = "FECHA_CREACION")
	private Date fechaCreacion;
	
	@Column(name = "MODIFICADO_POR")
	private Long modificadoPor;
	
	@Column(name = "ULTIMA_MODIFICACION")
	private Date ultimaModificacion;

	public Integer getMotivoId() {
		return motivoId;
	}

	public void setMotivoId(Integer motivoId) {
		this.motivoId = motivoId;
	}

	public String getMotivoDescripcion() {
		return motivoDescripcion;
	}

	public void setMotivoDescripcion(String motivoDescripcion) {
		this.motivoDescripcion = motivoDescripcion;
	}

	public Integer getActivo() {
		return activo;
	}

	public void setActivo(Integer activo) {
		this.activo = activo;
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
