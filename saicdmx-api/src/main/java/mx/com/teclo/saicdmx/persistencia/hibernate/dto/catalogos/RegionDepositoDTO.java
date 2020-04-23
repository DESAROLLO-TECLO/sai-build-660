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
@Table(name="REGIONES_DEPOSITOS")
public class RegionDepositoDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3369984786376565456L;
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "REG_ID", unique = true, nullable = false)	
	private Long regionId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "EDO_ID")
	private EstadoDTO estadoDTO;
	
	@Column(name = "REG_COD")
	private String regionCodigo;
	
	@Column(name = "REG_NOMBRE")
	private String regionNombre;
	
	@Column(name = "REG_STATUS")
	private String regionStatus;
	
	@Column(name = "CREADO_POR")
	private Long creadoPor;
	
	@Column(name = "FECHA_CREACION")
	private Date fechaCreacion;
	
	@Column(name = "MODIFICADO_POR")
	private Long modificadoPor;
	
	@Column(name = "ULTIMA_MODIFICACION")
	private Date ultimaModificacion;
	
	public Long getRegionId() {
		return regionId;
	}
	public void setRegionId(Long regionId) {
		this.regionId = regionId;
	}
	public String getRegionCodigo() {
		return regionCodigo;
	}
	public void setRegionCodigo(String regionCodigo) {
		this.regionCodigo = regionCodigo;
	}
	public String getRegionNombre() {
		return regionNombre;
	}
	public void setRegionNombre(String regionNombre) {
		this.regionNombre = regionNombre;
	}
	public String getRegionStatus() {
		return regionStatus;
	}
	public void setRegionStatus(String regionStatus) {
		this.regionStatus = regionStatus;
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
	public EstadoDTO getEstadoDTO() {
		return estadoDTO;
	}
	public void setEstadoDTO(EstadoDTO estadoDTO) {
		this.estadoDTO = estadoDTO;
	}
}
