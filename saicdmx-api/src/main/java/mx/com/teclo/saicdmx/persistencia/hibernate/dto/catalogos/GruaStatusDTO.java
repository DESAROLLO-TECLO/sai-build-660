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
@Table(name="GRUAS_STATUS")
public class GruaStatusDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 904942709022068990L;
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "GSTAT_ID", unique = true, nullable = false)
	private Long gruaStatusId;
	
	@Column(name = "GSTAT_COD")
	private String gruaStatusCod;
	
	@Column(name = "GSTAT_NOMBRE")
	private String gruaStatusNombre;	
	
	@Column(name = "GSTAT_STATUS")
	private String status;
	
	@Column(name = "CREADO_POR")
	private Long creadoPor;
	
	@Column(name = "FECHA_CREACION")
	private Date fechaCreacion;
	
	@Column(name = "MODIFICADO_POR")
	private Long modificadoPor;
	
	@Column(name = "ULTIMA_MODIFICACION")
	private Date ultimaModificacion;
	
	public Long getGruaStatusId() {
		return gruaStatusId;
	}
	public void setGruaStatusId(Long gruaStatusId) {
		this.gruaStatusId = gruaStatusId;
	}
	public String getGruaStatusCod() {
		return gruaStatusCod;
	}
	public void setGruaStatusCod(String gruaStatusCod) {
		this.gruaStatusCod = gruaStatusCod;
	}
	public String getGruaStatusNombre() {
		return gruaStatusNombre;
	}
	public void setGruaStatusNombre(String gruaStatusNombre) {
		this.gruaStatusNombre = gruaStatusNombre;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
