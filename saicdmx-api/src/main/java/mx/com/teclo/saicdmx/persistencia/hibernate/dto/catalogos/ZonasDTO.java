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
@Table(name="ZONAS")
public class ZonasDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8341206157266584913L;
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "ZONA_ID", unique = true, nullable = false)
	private Long zonaId;
	
	@Column(name = "ZONA_COD")
	private String zonaCod;
	
	@Column(name = "ZONA_NOMBRE")
	private String zonaNombre;
	
	@Column(name = "ZONA_STATUS")
	private String zonaEstatus;
	
	@Column(name = "CREADO_POR")
	private Long creadoPor;
	
	@Column(name = "FECHA_CREACION")
	private Date fechaCreacion;
	
	@Column(name = "MODIFICADO_POR")
	private Long modPor;
	
	@Column(name = "ULTIMA_MODIFICACION")
	private Date ultimaMod;

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

	public String getZonaEstatus() {
		return zonaEstatus;
	}

	public void setZonaEstatus(String zonaEstatus) {
		this.zonaEstatus = zonaEstatus;
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

	public Long getModPor() {
		return modPor;
	}

	public void setModPor(Long modPor) {
		this.modPor = modPor;
	}

	public Date getUltimaMod() {
		return ultimaMod;
	}

	public void setUltimaMod(Date ultimaMod) {
		this.ultimaMod = ultimaMod;
	}
	
}
