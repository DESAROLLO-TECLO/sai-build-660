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
@Table(name="UNIDAD_TERRITORIAL")
public class UnidadTerritorialDTO implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EmbeddedId
	private UTId utId;
	@Column(name = "UT_COD")
	private String utCod;
	@Column(name = "UT_NOMBRE")
	private String utNombre;
	@Column(name = "UT_STATUS")
	private String utStatus;
	@Column(name = "CREADO_POR")
	private Long creadoPor;
	@Column(name = "FECHA_CREACION")
	private Date fechaCreacion;
	@Column(name = "MODIFICADO_POR")
	private Long modPor;
	@Column(name = "ULTIMA_MODIFICACION")
	private Date ultimaMod;
	
	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name="SEC_ID", referencedColumnName="SEC_ID", insertable=false, updatable=false)
	private SectorDTO sectorDTO;
	/**
	 * @return the utId
	 */
	public UTId getUtId() {
		return utId;
	}
	/**
	 * @param utId the utId to set
	 */
	public void setUtId(UTId utId) {
		this.utId = utId;
	}
	/**
	 * @return the utCod
	 */
	public String getUtCod() {
		return utCod;
	}
	/**
	 * @param utCod the utCod to set
	 */
	public void setUtCod(String utCod) {
		this.utCod = utCod;
	}
	/**
	 * @return the utNombre
	 */
	public String getUtNombre() {
		return utNombre;
	}
	/**
	 * @param utNombre the utNombre to set
	 */
	public void setUtNombre(String utNombre) {
		this.utNombre = utNombre;
	}
	/**
	 * @return the utStatus
	 */
	public String getUtStatus() {
		return utStatus;
	}
	/**
	 * @param utStatus the utStatus to set
	 */
	public void setUtStatus(String utStatus) {
		this.utStatus = utStatus;
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
	public SectorDTO getSectorDTO() {
		return sectorDTO;
	}
	public void setSectorDTO(SectorDTO sectorDTO) {
		this.sectorDTO = sectorDTO;
	}
	
}
