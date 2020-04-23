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
@Table(name="AGRUPAMIENTOS")
public class AgrupamientosDTO implements Serializable
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4731058831196083124L;
	/**
	 * 
	 */
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "AGRP_ID", unique = true, nullable = false)
	private Long agrupacionId;
	@Column(name = "AGRP_COD")
	private String agrupacionCodigo;
	@Column(name = "AGRP_NOMBRE")
	private String agrupacionNombre;
	@Column(name = "AGRP_STATUS")
	private String agrupacionStatus;
	@Column(name = "CREADO_POR")
	private Long creadoPor;
	@Column(name = "FECHA_CREACION")
	private Date fechaCreacion;
	@Column(name = "MODIFICADO_POR")
	private Long modificadoPor;
	@Column(name = "ULTIMA_MODIFICACION")
	private Date ultimaModificacion;
	
	/*@OneToMany(fetch = FetchType.EAGER, mappedBy = "agrupamiento")
	private Set<EmpleadoLoginDTO> empleadosDTO;*/
		
	public String getAgrupacionCodigo() 
	{
		return agrupacionCodigo;
	}

	public Long getAgrupacionId() 
	{
		return agrupacionId;
	}

	public void setAgrupacionId(Long agrupacionId) 
	{
		this.agrupacionId = agrupacionId;
	}

	public String getAgrupacionNombre() 
	{
		return agrupacionNombre;
	}

	public void setAgrupacionNombre(String agrupacionNombre) 
	{
		this.agrupacionNombre = agrupacionNombre;
	}

	public String getAgrupacionStatus() 
	{
		return agrupacionStatus;
	}

	public void setAgrupacionStatus(String agrupacionStatus) 
	{
		this.agrupacionStatus = agrupacionStatus;
	}

	public Long getCreadoPor() 
	{
		return creadoPor;
	}

	public void setCreadoPor(Long creadoPor) 
	{
		this.creadoPor = creadoPor;
	}

	public Date getFechaCreacion() 
	{
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) 
	{
		this.fechaCreacion = fechaCreacion;
	}

	public Long getModificadoPor() 
	{
		return modificadoPor;
	}

	public void setModificadoPor(Long modificadoPor) 
	{
		this.modificadoPor = modificadoPor;
	}

	public Date getUltimaModificacion() 
	{
		return ultimaModificacion;
	}

	public void setUltimaModificacion(Date ultimaModificacion) 
	{
		this.ultimaModificacion = ultimaModificacion;
	}

	public void setAgrupacionCodigo(String agrupacionCodigo) 
	{
		this.agrupacionCodigo = agrupacionCodigo;
	}

	/*public Set<EmpleadoLoginDTO> getEmpleadosDTO() {
		return empleadosDTO;
	}

	public void setEmpleadosDTO(Set<EmpleadoLoginDTO> empleadosDTO) {
		this.empleadosDTO = empleadosDTO;
	}*/
	
	
	
}
