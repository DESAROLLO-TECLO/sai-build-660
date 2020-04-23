package mx.com.teclo.saicdmx.persistencia.hibernate.dto.empleados;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import mx.com.teclo.saicdmx.persistencia.hibernate.dto.administracion.AplicacionDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.administracion.PerfilUsuario;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.AgrupamientosDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.SectorDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.TipoEmpleadoDTO;

@Entity
@Table(name="EMPLEADOS")
public class EmpleadosDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1488456401273023925L;
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "EMP_ID", unique = true, nullable = false)
    private Long empId;
	@Column(name = "EMP_COD")
	private String empCod;
	@Column(name = "EMP_PLACA")
	private String empPlaca;
	
	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name="EMP_TIP_ID", referencedColumnName="EMP_TIP_ID", insertable=false, updatable=false)
	private TipoEmpleadoDTO empTip;
	
	@Column(name = "EMP_RFC")
	private String empRFC;
	
	
	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name="AGRP_ID", referencedColumnName="AGRP_ID", insertable=false, updatable=false)
	private AgrupamientosDTO agrupamiento;
	
	
	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name="SEC_ID", referencedColumnName="SEC_ID", insertable=false, updatable=false)
	private SectorDTO sector;
	
	@Column(name = "EMP_APE_PATERNO")
	private String empApePaterno;
	@Column(name = "EMP_APE_MATERNO")
	private String empApeMaterno;
	@Column(name = "EMP_NOMBRE")
	private String empNombre;
	@Column(name = "EMP_PWD")
	private String empPwd;
	@Column(name = "EMP_STATUS")
	private String empStatus;
	@Column(name = "CREADO_POR")
	private Long creadoPor;
	@Column(name = "FECHA_CREACION")
	private Date fechaCreacion;
	@Column(name = "MODIFICADO_POR")
	private Long modificadoPor;
	@Column(name = "ULTIMA_MODIFICACION")
	private Date ultimaModificacion;
	
	/*@OneToOne(cascade=CascadeType.ALL)
    @JoinTable(name="PERFIL_USUARIO",
        joinColumns = {@JoinColumn(name="USU_ID", referencedColumnName="EMP_ID")},
        inverseJoinColumns = {@JoinColumn(name="PERFIL_ID", referencedColumnName="PERFIL_ID")}
    )
	private PerfilDTO profileDTO;*/
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "TSEG_USUARIO_APLICACION", schema = "ICD", joinColumns = {
			@JoinColumn(name = "USU_ID", nullable = false, updatable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "ID_APLICACION", nullable = false, updatable = false) })
	private List<AplicacionDTO> tsegCatAplicacioneses = new ArrayList<AplicacionDTO>(0);
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "empleadoDTO")
	private List<PerfilUsuario> perfilUsuarios = new ArrayList<PerfilUsuario>(0);
	
	
	/**
	 * @return the empId
	 */
	public Long getEmpId() {
		return empId;
	}
	/**
	 * @param empId the empId to set
	 */
	public void setEmpId(Long empId) {
		this.empId = empId;
	}
	/**
	 * @return the empCod
	 */
	public String getEmpCod() {
		return empCod;
	}
	/**
	 * @param empCod the empCod to set
	 */
	public void setEmpCod(String empCod) {
		this.empCod = empCod;
	}
	/**
	 * @return the empPlaca
	 */
	public String getEmpPlaca() {
		return empPlaca;
	}
	/**
	 * @param empPlaca the empPlaca to set
	 */
	public void setEmpPlaca(String empPlaca) {
		this.empPlaca = empPlaca;
	}
	public EmpleadosDTO() {
	}
	
	 
	public String getEmpRFC() {
		return empRFC;
	}
	/**
	 * @param empRFC the empRFC to set
	 */
	public void setEmpRFC(String empRFC) {
		this.empRFC = empRFC;
	}
	public TipoEmpleadoDTO getEmpTip() {
		return empTip;
	}
	public void setEmpTip(TipoEmpleadoDTO empTip) {
		this.empTip = empTip;
	}
	public AgrupamientosDTO getAgrupamiento() {
		return agrupamiento;
	}
	public void setAgrupamiento(AgrupamientosDTO agrupamiento) {
		this.agrupamiento = agrupamiento;
	}
	public SectorDTO getSector() {
		return sector;
	}
	public void setSector(SectorDTO sector) {
		this.sector = sector;
	}
	/**
	 * @return the empApePaterno
	 */
	public String getEmpApePaterno() {
		return empApePaterno;
	}
	/**
	 * @param empApePaterno the empApePaterno to set
	 */
	public void setEmpApePaterno(String empApePaterno) {
		this.empApePaterno = empApePaterno;
	}
	/**
	 * @return the empApeMaterno
	 */
	public String getEmpApeMaterno() {
		return empApeMaterno;
	}
	/**
	 * @param empApeMaterno the empApeMaterno to set
	 */
	public void setEmpApeMaterno(String empApeMaterno) {
		this.empApeMaterno = empApeMaterno;
	}
	/**
	 * @return the empNombre
	 */
	public String getEmpNombre() {
		return empNombre;
	}
	/**
	 * @param empNombre the empNombre to set
	 */
	public void setEmpNombre(String empNombre) {
		this.empNombre = empNombre;
	}
	/**
	 * @return the empPwd
	 */
	public String getEmpPwd() {
		return empPwd;
	}
	/**
	 * @param empPwd the empPwd to set
	 */
	public void setEmpPwd(String empPwd) {
		this.empPwd = empPwd;
	}
	/**
	 * @return the empStatus
	 */
	public String getEmpStatus() {
		return empStatus;
	}
	/**
	 * @param empStatus the empStatus to set
	 */
	public void setEmpStatus(String empStatus) {
		this.empStatus = empStatus;
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
	public List<AplicacionDTO> getTsegCatAplicacioneses() {
		return tsegCatAplicacioneses;
	}
	public void setTsegCatAplicacioneses(List<AplicacionDTO> tsegCatAplicacioneses) {
		this.tsegCatAplicacioneses = tsegCatAplicacioneses;
	}
	public List<PerfilUsuario> getPerfilUsuarios() {
		return perfilUsuarios;
	}
	public void setPerfilUsuarios(List<PerfilUsuario> perfilUsuarios) {
		this.perfilUsuarios = perfilUsuarios;
	}
	
	/*public PerfilDTO getProfileDTO() {
		return profileDTO;
	}
	public void setProfileDTO(PerfilDTO profileDTO) {
		this.profileDTO = profileDTO;
	}*/
}
