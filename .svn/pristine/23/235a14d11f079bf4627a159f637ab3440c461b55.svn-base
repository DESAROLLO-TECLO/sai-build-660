package mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="ESTADOS")
public class EstadoDTO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4188107893880069771L;
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "EDO_ID", unique = true, nullable = false)
	private Long edoId;
	@Column(name = "EDO_COD")
	private String edoCod;
	@Column(name = "EDO_NOMBRE")
	private String edoNombre;
	@Column(name = "EDO_STATUS")
	private String edoStatus;
	@Column(name = "CREADO_POR")
	private Long creadoPor;
	@Column(name = "FECHA_CREACION")
	private Date fechaCreacion;
	@Column(name = "MODIFICADO_POR")
	private Long modPor;
	@Column(name = "ULTIMA_MODIFICACION")
	private Date ultimaMod;
	
	/*@OneToMany(fetch = FetchType.EAGER, mappedBy = "estadoDTO")
	private Set<DelegacionDTO> delegaciones;*/
	
	/**
	 * @return the edoId
	 */
	public Long getEdoId() {
		return edoId;
	}
	/**
	 * @param edoId the edoId to set
	 */
	public void setEdoId(Long edoId) {
		this.edoId = edoId;
	}
	/**
	 * @return the edoCod
	 */
	public String getEdoCod() {
		return edoCod;
	}
	/**
	 * @param edoCod the edoCod to set
	 */
	public void setEdoCod(String edoCod) {
		this.edoCod = edoCod;
	}
	/**
	 * @return the edoNombre
	 */
	public String getEdoNombre() {
		return edoNombre;
	}
	/**
	 * @param edoNombre the edoNombre to set
	 */
	public void setEdoNombre(String edoNombre) {
		this.edoNombre = edoNombre;
	}
	/**
	 * @return the edoStatus
	 */
	public String getEdoStatus() {
		return edoStatus;
	}
	/**
	 * @param edoStatus the edoStatus to set
	 */
	public void setEdoStatus(String edoStatus) {
		this.edoStatus = edoStatus;
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
	 * @return the modPor
	 */
	public Long getModPor() {
		return modPor;
	}
	/**
	 * @param modPor the modPor to set
	 */
	public void setModPor(Long modPor) {
		this.modPor = modPor;
	}
	/**
	 * @return the ultimaMod
	 */
	public Date getUltimaMod() {
		return ultimaMod;
	}
	/**
	 * @param ultimaMod the ultimaMod to set
	 */
	public void setUltimaMod(Date ultimaMod) {
		this.ultimaMod = ultimaMod;
	}
	/* *
	 * @return the delegaciones
	 * /
	public Set<DelegacionDTO> getDelegaciones() {
		return delegaciones;
	}
	/**
	 * @param delegaciones the delegaciones to set
	 * /
	public void setDelegaciones(Set<DelegacionDTO> delegaciones) {
		this.delegaciones = delegaciones;
	}*/
	

}
