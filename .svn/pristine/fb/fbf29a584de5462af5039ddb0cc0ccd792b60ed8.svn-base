package mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="SECTORES")
public class SectorDTO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4684723245404019054L;
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "SEC_ID", unique = true, nullable = false)
	private Long secId;
	@Column(name = "SEC_COD")
	private String secCod;
	@Column(name = "SEC_NOMBRE")
	private String secNombre;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumns({
        @JoinColumn(name="EDO_ID", referencedColumnName="EDO_ID"),
        @JoinColumn(name="DEL_ID", referencedColumnName="DEL_ID")
    })
	private DelegacionDTO del;
	@Column(name = "SEC_STATUS")
	private String secStatus;
	@Column(name = "CREADO_POR")
	private Long creadoPor;
	@Column(name = "FECHA_CREACION")
	private Date fechaCreacion;
	@Column(name = "MODIFICADO_POR")
	private Long modPor;
	@Column(name = "ULTIMA_MODIFICACION")
	private Date ultimaMod;
	/*@OneToMany(fetch = FetchType.EAGER, mappedBy = "sectorDTO")
	private Set<UnidadTerritorialDTO> unidadesT;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "sector")
	private Set<EmpleadoLoginDTO> empleadosDTO;*/
	/**
	 * @return the secId
	 */
	public Long getSecId() {
		return secId;
	}
	/**
	 * @param secId the secId to set
	 */
	public void setSecId(Long secId) {
		this.secId = secId;
	}
	/**
	 * @return the secCod
	 */
	public String getSecCod() {
		return secCod;
	}
	/**
	 * @param secCod the secCod to set
	 */
	public void setSecCod(String secCod) {
		this.secCod = secCod;
	}
	/**
	 * @return the secNombre
	 */
	public String getSecNombre() {
		return secNombre;
	}
	/**
	 * @param secNombre the secNombre to set
	 */
	public void setSecNombre(String secNombre) {
		this.secNombre = secNombre;
	}
	/**
	 * @return the del
	 */
	public DelegacionDTO getDel() {
		return del;
	}
	/**
	 * @param del the del to set
	 */
	public void setDel(DelegacionDTO del) {
		this.del = del;
	}
	/**
	 * @return the secStatus
	 */
	public String getSecStatus() {
		return secStatus;
	}
	/**
	 * @param secStatus the secStatus to set
	 */
	public void setSecStatus(String secStatus) {
		this.secStatus = secStatus;
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
	 * @return the unidadesT
	 * /
	public Set<UnidadTerritorialDTO> getUnidadesT() {
		return unidadesT;
	}
	/**
	 * @param unidadesT the unidadesT to set
	 * /
	public void setUnidadesT(Set<UnidadTerritorialDTO> unidadesT) {
		this.unidadesT = unidadesT;
	}*/
}
