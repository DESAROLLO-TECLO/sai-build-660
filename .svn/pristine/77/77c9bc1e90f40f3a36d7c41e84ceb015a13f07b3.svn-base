package mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="DELEGACIONES")
public class DelegacionDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8373854344218613383L;
	@EmbeddedId
	private DelId delId;
	@Column(name = "REG_ID")
	private Long regId;
	@Column(name = "DEL_COD")
	private String delCod;
	@Column(name = "DEL_NOMBRE")
	private String delNombre;
	@Column(name = "DEL_STATUS")
	private String delStatus;
	@Column(name = "CREADO_POR")
	private Long creadoPor;
	@Column(name = "FECHA_CREACION")
	private Date fechaCreacion;
	@Column(name = "MODIFICADO_POR")
	private Long modificadoPor;
	@Column(name = "ULTIMA_MODIFICACION")
	private Date ultimaMod;
	
	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name="EDO_ID", referencedColumnName="EDO_ID", insertable=false, updatable=false)
	private EstadoDTO estadoDTO;
	
	
	/*@OneToMany(mappedBy = "del", fetch = FetchType.EAGER)
	private Set<SectorDTO> sectores;*/
	/**
	 * @return the delId
	 */
	public DelId getDelId() {
		return delId;
	}
	/**
	 * @param delId the delId to set
	 */
	public void setDelId(DelId delId) {
		this.delId = delId;
	}
	/**
	 * @return the regId
	 */
	public Long getRegId() {
		return regId;
	}
	/**
	 * @param regId the regId to set
	 */
	public void setRegId(Long regId) {
		this.regId = regId;
	}
	/**
	 * @return the delCod
	 */
	public String getDelCod() {
		return delCod;
	}
	/**
	 * @param delCod the delCod to set
	 */
	public void setDelCod(String delCod) {
		this.delCod = delCod;
	}
	/**
	 * @return the delNombre
	 */
	public String getDelNombre() {
		return delNombre;
	}
	/**
	 * @param delNombre the delNombre to set
	 */
	public void setDelNombre(String delNombre) {
		this.delNombre = delNombre;
	}
	/**
	 * @return the delStatus
	 */
	public String getDelStatus() {
		return delStatus;
	}
	/**
	 * @param delStatus the delStatus to set
	 */
	public void setDelStatus(String delStatus) {
		this.delStatus = delStatus;
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
	 * @return the sectores
	 * /
	public Set<SectorDTO> getSectores() {
		return sectores;
	}
	/**
	 * @param sectores the sectores to set
	 * /
	public void setSectores(Set<SectorDTO> sectores) {
		this.sectores = sectores;
	}*/
	public EstadoDTO getEstadoDTO() {
		return estadoDTO;
	}
	public void setEstadoDTO(EstadoDTO estadoDTO) {
		this.estadoDTO = estadoDTO;
	}
}
