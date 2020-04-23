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
@Table(name="AREAS")
public class AreaDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6574177464810798715L;
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "AREA_ID", unique = true, nullable = false)
	private Long areaId;
	
	@Column(name = "AREA_COD")
	private String areaCodigo;
	
	@Column(name = "AREA_NOMBRE")
	private String areaNombre;
	
	@Column(name = "AREA_STATUS")
	private String areaStatus;
	
	@Column(name = "CREADO_POR")
	private Long creadoPor;
	
	@Column(name = "FECHA_CREACION")
	private Date fechaCreacion;
	
	@Column(name = "MODIFICADO_POR")
	private Long modificadoPor;
	
	@Column(name = "ULTIMA_MODIFICACION")
	private Date ultimaModificacion;
	
	public Long getAreaId() {
		return areaId;
	}
	public void setAreaId(Long areaId) {
		this.areaId = areaId;
	}
	public String getAreaCodigo() {
		return areaCodigo;
	}
	public void setAreaCodigo(String areaCodigo) {
		this.areaCodigo = areaCodigo;
	}
	public String getAreaNombre() {
		return areaNombre;
	}
	public void setAreaNombre(String areaNombre) {
		this.areaNombre = areaNombre;
	}
	public String getAreaStatus() {
		return areaStatus;
	}
	public void setAreaStatus(String areaStatus) {
		this.areaStatus = areaStatus;
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
}
