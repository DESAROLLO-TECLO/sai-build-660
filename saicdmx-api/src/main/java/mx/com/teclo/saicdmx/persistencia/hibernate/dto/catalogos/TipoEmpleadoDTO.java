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
@Table(name="EMPLEADO_TIPO")
public class TipoEmpleadoDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1035565413039110359L;
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "EMP_TIP_ID", unique = true, nullable = false)
    private Long empTipId;
	
	@Column(name = "EMP_TIP_COD")
	private String empTipCodigo;
	
	@Column(name = "EMP_TIP_NOMBRE")
	private String empTipNombre;
	
	@Column(name = "EMP_TIP_STATUS")
	private String empTipEstatus;
	
	@Column(name = "CREADO_POR")
	private Long creadoPor;
	
	@Column(name = "FECHA_CREACION")
	private Date fechaCreacion;
	
	@Column(name = "MODIFICADO_POR")
	private Long modificadoPor;
	
	@Column(name = "ULTIMA_MODIFICACION")
	private Date ultimaModificacion;
	
	/*@OneToMany(fetch = FetchType.EAGER, mappedBy = "empTip")
	private Set<EmpleadoLoginDTO> empleadosDTO;*/

	public Long getEmpTipId() {
		return empTipId;
	}

	public void setEmpTipId(Long empTipId) {
		this.empTipId = empTipId;
	}

	public String getEmpTipCodigo() {
		return empTipCodigo;
	}

	public void setEmpTipCodigo(String empTipCodigo) {
		this.empTipCodigo = empTipCodigo;
	}

	public String getEmpTipNombre() {
		return empTipNombre;
	}

	public void setEmpTipNombre(String empTipNombre) {
		this.empTipNombre = empTipNombre;
	}

	public String getEmpTipEstatus() {
		return empTipEstatus;
	}

	public void setEmpTipEstatus(String empTipEstatus) {
		this.empTipEstatus = empTipEstatus;
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

	/*public Set<EmpleadoLoginDTO> getEmpleadosDTO() {
		return empleadosDTO;
	}

	public void setEmpleadosDTO(Set<EmpleadoLoginDTO> empleadosDTO) {
		this.empleadosDTO = empleadosDTO;
	}*/
	
	

}
