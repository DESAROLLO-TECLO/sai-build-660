package mx.com.teclo.saicdmx.persistencia.hibernate.dto.garantias;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="GARANTIAS_CAT_PROCESOS")
public class GarantiaProcesoDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7097721222578460230L;
	@Id
	@Column(name = "PROCESO_ID", unique = true, nullable = false)
	private Integer procesoId;
	@Column(name="NOMBRE")
	private String nombre;
	@Column(name="FECHA_CREACION")
	private Date fechaCreacion;
	@Column(name="CREADO_POR")
	private Long creadoPor;
	@Column(name="MODIFICADO_POR")
	private Long modificadoPor;
	@Column(name="ULTIMA_MODIFICACION")
	private Date ultimaModificacion;
	
	//@OneToMany(mappedBy = "garantiaProceso", cascade = CascadeType.ALL)
	//public Set<GarantiaEstatusProcesoDTO> garantiasEstatusProcesoDTO = new HashSet<GarantiaEstatusProcesoDTO>();
	
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
	 * @return the procesoId
	 */
	public Integer getProcesoId() {
		return procesoId;
	}
	/**
	 * @param procesoId the procesoId to set
	 */
	public void setProcesoId(Integer procesoId) {
		this.procesoId = procesoId;
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
