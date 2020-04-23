package mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity 
@Table(name="SOPORTE_CONCEPTOS")
public class ConceptosSoporteDTO implements Serializable{
		
	/**
	 * 
	 */
	private static final long serialVersionUID = 7195965270304735822L;
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "CONCEPTO_ID", unique = true, nullable = false)
	private Long conceptoId;
	
	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name="COMPONENTE_ID", referencedColumnName="COMPONENTE_ID", insertable=false, updatable=false)
	private ComponentesSoporteDTO componenteId;
	
	@Column(name = "CONCEPTO_NOMBRE")
	private String conceptoNombre;
	
	@Column(name = "DESCRIPCION")
	private String descripcion;
	
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
	 * @return the conceptoId
	 */
	public Long getConceptoId() {
		return conceptoId;
	}

	/**
	 * @param conceptoId the conceptoId to set
	 */
	public void setConceptoId(Long conceptoId) {
		this.conceptoId = conceptoId;
	}

	/**
	 * @return the componenteId
	 */
	public ComponentesSoporteDTO getComponenteId() {
		return componenteId;
	}

	/**
	 * @param componenteId the componenteId to set
	 */
	public void setComponenteId(ComponentesSoporteDTO componenteId) {
		this.componenteId = componenteId;
	}

	/**
	 * @return the conceptoNombre
	 */
	public String getConceptoNombre() {
		return conceptoNombre;
	}

	/**
	 * @param conceptoNombre the conceptoNombre to set
	 */
	public void setConceptoNombre(String conceptoNombre) {
		this.conceptoNombre = conceptoNombre;
	}

	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
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
