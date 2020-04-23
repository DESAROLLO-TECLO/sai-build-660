package mx.com.teclo.saicdmx.persistencia.hibernate.dto.infracciones;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "INVENTARIO")
public class InventarioDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private InventarioKey inventarioKey;
	
	@Column(name = "DEP_ID")
	private Long depId;
	
	@Column(name = "INGR_RESGUARDO")
	private String ingrResguardo;
	
	@Column(name = "INV_STATUS")
	private String invStatus;
	
	@Column(name = "CREADO_POR")
	private Long creadoPor;
	
	@Column(name = "FECHA_CREACION")
	private Date fechaCreacion;
	
	@Column(name = "MODIFICADO_POR")
	private Long modificadoPor;
	
	@Column(name = "ULTIMA_MODIFICACION")
	private Date ultimaModificacion;

	public InventarioKey getInventarioKey() {
		return inventarioKey;
	}

	public void setInventarioKey(InventarioKey inventarioKey) {
		this.inventarioKey = inventarioKey;
	}

	public Long getDepId() {
		return depId;
	}

	public void setDepId(Long depId) {
		this.depId = depId;
	}

	public String getIngrResguardo() {
		return ingrResguardo;
	}

	public void setIngrResguardo(String ingrResguardo) {
		this.ingrResguardo = ingrResguardo;
	}

	public String getInvStatus() {
		return invStatus;
	}

	public void setInvStatus(String invStatus) {
		this.invStatus = invStatus;
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
