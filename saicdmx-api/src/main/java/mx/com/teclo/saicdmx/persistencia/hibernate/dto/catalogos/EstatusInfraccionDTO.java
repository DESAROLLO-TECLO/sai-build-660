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
@Table(name="ESTATUS_INFRACCION")
public class EstatusInfraccionDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4632311672253431737L;
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "ESTAT_ID", unique = true, nullable = false)
	private Long estatusId;
	
	@Column(name = "ESTAT_COD")
	private String estatusCodigo;
	
	@Column(name = "ESTAT_NOMBRE")
	private String estatusNombre;
	
	@Column(name = "ESTAT_STATUS")
	private String status;
	
	@Column(name = "CREADO_POR")
	private Long creadoPor;
	
	@Column(name = "FECHA_CREACION")
	private Date fechaCreacion;
	
	@Column(name = "MODIFICADO_POR")
	private Long modificadoPor;
	
	@Column(name = "ULTIMA_MODIFICACION")
	private Date fechaUltimaModificacion;
	
	public Long getEstatusId() {
		return estatusId;
	}
	public void setEstatusId(Long estatusId) {
		this.estatusId = estatusId;
	}
	public String getEstatusCodigo() {
		return estatusCodigo;
	}
	public void setEstatusCodigo(String estatusCodigo) {
		this.estatusCodigo = estatusCodigo;
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
	public Date getFechaUltimaModificacion() {
		return fechaUltimaModificacion;
	}
	public void setFechaUltimaModificacion(Date fechaUltimaModificacion) {
		this.fechaUltimaModificacion = fechaUltimaModificacion;
	}
	public String getEstatusNombre() {
		return estatusNombre;
	}
	public void setEstatusNombre(String estatusNombre) {
		this.estatusNombre = estatusNombre;
	}
}
