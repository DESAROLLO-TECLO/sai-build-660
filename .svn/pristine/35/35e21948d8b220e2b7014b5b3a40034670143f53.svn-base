package mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="OPERATIVO")
public class OperativoDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "OPER_ID")
	private Long operativoId;
	@Column(name = "OPER_COD")
	private String operativoCod;
	@Column(name = "OPER_NOMBRE")
	private String operativoNombre;
	@Column(name = "OPER_STATUS")
	private String operativoStatus;
	@Column(name = "CREADO_POR")
	private Long creadoPor;
	@Column(name = "FECHA_CREACION")
	private Date fechaCreacion;
	@Column(name = "MODIFICADO_POR")
	private Long modificadoPor;
	@Column(name = "ULTIMA_MODIFICACION")
	private Date ultimaModificacion;
	
	public Long getOperativoId() {
		return operativoId;
	}
	public void setOperativoId(Long operativoId) {
		this.operativoId = operativoId;
	}
	public String getOperativoCod() {
		return operativoCod;
	}
	public void setOperativoCod(String operativoCod) {
		this.operativoCod = operativoCod;
	}
	public String getOperativoNombre() {
		return operativoNombre;
	}
	public void setOperativoNombre(String operativoNombre) {
		this.operativoNombre = operativoNombre;
	}
	public String getOperativoStatus() {
		return operativoStatus;
	}
	public void setOperativoStatus(String operativoStatus) {
		this.operativoStatus = operativoStatus;
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
