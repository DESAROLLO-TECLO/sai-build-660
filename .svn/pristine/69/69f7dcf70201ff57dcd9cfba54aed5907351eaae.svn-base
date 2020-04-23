package mx.com.teclo.saicdmx.persistencia.hibernate.vo.catalogos;

import java.io.Serializable;
import java.util.Date;

public class AgrupamientosVO implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7962740750376458941L;
	/**
	 * 
	 */
	
	private Long agrupacionId;
	private String agrupacionCodigo;
	private String agrupacionNombre;
	private String agrupacionStatus;
	private Long creadoPor;
	private Date fechaCreacion;
	private Long modificadoPor;
	private Date ultimaModificacion;
	
	public Long getAgrupacionId() {
		return agrupacionId;
	}
	public void setAgrupacionId(Long agrupacionId) {
		this.agrupacionId = agrupacionId;
	}
	public String getAgrupacionCodigo() {
		return agrupacionCodigo;
	}
	public void setAgrupacionCodigo(String agrupacionCodigo) {
		this.agrupacionCodigo = agrupacionCodigo;
	}
	public String getAgrupacionStatus() {
		return agrupacionStatus;
	}
	public void setAgrupacionStatus(String agrupacionStatus) {
		this.agrupacionStatus = agrupacionStatus;
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
	public String getAgrupacionNombre() {
		return agrupacionNombre;
	}
	public void setAgrupacionNombre(String agrupacionNombre) {
		this.agrupacionNombre = agrupacionNombre;
	}
	
	public String getStatusDesc() {
		return this.getAgrupacionStatus().equals("A") ? "Activo" : "Cancelado";
	}
}
