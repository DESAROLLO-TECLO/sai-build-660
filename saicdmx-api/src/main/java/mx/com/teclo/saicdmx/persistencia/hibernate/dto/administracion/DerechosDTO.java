package mx.com.teclo.saicdmx.persistencia.hibernate.dto.administracion;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "DERECHOS")
public class DerechosDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7653085314535553417L;

	@EmbeddedId
	private DerechosIdDTO id;

	@Column(name = "MOD_STATUS")
	private String modStatus;

	@Column(name = "MOD_FECH_MOD")
	private Date modFechMod;

	@Column(name = "CREADO_POR")
	private Long creadoPor;

	@Column(name = "FECHA_CREACION")
	private Date fechaCreacion;

	@Column(name = "MODIFICADO_POR")
	private Long modificadoPor;

	@Column(name = "ULTIMA_MODIFICACION")
	private Date ultimaModificacion;

	public DerechosDTO() {
	}

	public DerechosIdDTO getId() {
		return id;
	}

	public void setId(DerechosIdDTO id) {
		this.id = id;
	}

	public String getModStatus() {
		return modStatus;
	}

	public void setModStatus(String modStatus) {
		this.modStatus = modStatus;
	}

	public Date getModFechMod() {
		return modFechMod;
	}

	public void setModFechMod(Date modFechMod) {
		this.modFechMod = modFechMod;
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
