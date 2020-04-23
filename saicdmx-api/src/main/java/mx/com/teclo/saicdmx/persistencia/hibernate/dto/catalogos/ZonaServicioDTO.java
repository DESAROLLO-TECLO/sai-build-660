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
@Table(name="ZONA_SERVICIO")
public class ZonaServicioDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 140280241561829360L;

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "ZONA_S_ID", unique = true, nullable = false)
	private Long zonaId;
	
	@Column(name = "ZONA_S_COD")
	private String zonaCod;
	
	@Column(name = "ZONA_S_NOMBRE")
	private String zonaNombre;
	
	@Column(name = "ZONA_S_STATUS")
	private String zonaStatus;
	
	@Column(name = "CREADO_POR")
	private Long creadoPor;
	
	@Column(name = "FECHA_CREACION")
	private Date fechaCreacion;
	
	@Column(name = "MODIFICADO_POR")
	private Long modificadoPor;
	
	@Column(name = "ULTIMA_MODIFICACION")
	private Date ultimaModificacion;
	
	public Long getZonaId() {
		return zonaId;
	}
	public void setZonaId(Long zonaId) {
		this.zonaId = zonaId;
	}
	public String getZonaCod() {
		return zonaCod;
	}
	public void setZonaCod(String zonaCod) {
		this.zonaCod = zonaCod;
	}
	public String getZonaNombre() {
		return zonaNombre;
	}
	public void setZonaNombre(String zonaNombre) {
		this.zonaNombre = zonaNombre;
	}
	public String getZonaStatus() {
		return zonaStatus;
	}
	public void setZonaStatus(String zonaStatus) {
		this.zonaStatus = zonaStatus;
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
