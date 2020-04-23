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
@Table(name="SOPORTE_COMPONENTES")
public class ComponentesSoporteDTO implements Serializable{
		
	/**
	 * 
	 */
	private static final long serialVersionUID = 7195965270304735822L;
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "COMPONENTE_ID", unique = true, nullable = false)
	private Long componenteId;
	
	@Column(name = "NOMBRE")
	private String nombre;
	
	@Column(name="FECHA_CREACION")
	private Date fechaCreacion;
	
	@Column(name="CREADO_POR")
	private Long creadoPor;
	
	@Column(name="ULTIMA_MODIFICACION")
	private Date ultimaModificacion;
	
	@Column(name="MODIFICADO_POR")
	private Long modificadoPor;
	
	@Column(name="VALIDO")
	private Integer	valido;

	/**
	 * @return the componenteId
	 */
	public Long getComponenteId() {
		return componenteId;
	}

	/**
	 * @param componenteId the componenteId to set
	 */
	public void setComponenteId(Long componenteId) {
		this.componenteId = componenteId;
	}

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
	 * @return the fechaCreacion
	 */
	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	/**
	 * @param fechaCreacion the fechaCreacion to set
	 */
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	/**
	 * @return the creadoPor
	 */
	public Long getCreadoPor() {
		return creadoPor;
	}

	/**
	 * @param creadoPor the creadoPor to set
	 */
	public void setCreadoPor(Long creadoPor) {
		this.creadoPor = creadoPor;
	}

	/**
	 * @return the ultimaModificacion
	 */
	public Date getUltimaModificacion() {
		return ultimaModificacion;
	}

	/**
	 * @param ultimaModificacion the ultimaModificacion to set
	 */
	public void setUltimaModificacion(Date ultimaModificacion) {
		this.ultimaModificacion = ultimaModificacion;
	}

	/**
	 * @return the modificadoPor
	 */
	public Long getModificadoPor() {
		return modificadoPor;
	}

	/**
	 * @param modificadoPor the modificadoPor to set
	 */
	public void setModificadoPor(Long modificadoPor) {
		this.modificadoPor = modificadoPor;
	}

	/**
	 * @return the valido
	 */
	public Integer getValido() {
		return valido;
	}

	/**
	 * @param valido the valido to set
	 */
	public void setValido(Integer valido) {
		this.valido = valido;
	}
}
