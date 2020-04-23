package mx.com.teclo.saicdmx.persistencia.hibernate.vo.catalogos;

import java.io.Serializable;
import java.util.Date;


public class TipoEmpleadoVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3030432789692711002L;

	private Long empTipId;
	private String empTipCodigo;
	private String empTipNombre;
	private String empTipEstatus;
	private Long creadoPor;
	private Date fechaCreacion;
	private Long modificadoPor;
	private Date ultimaModificacion;
	
	public Long getEmpTipId() {
		return empTipId;
	}
	public void setEmpTipId(Long empTipId) {
		this.empTipId = empTipId;
	}
	public String getEmpTipCodigo() {
		return empTipCodigo;
	}
	public void setEmpTipCodigo(String empTipCodigo) {
		this.empTipCodigo = empTipCodigo;
	}
	public String getEmpTipNombre() {
		return empTipNombre;
	}
	public void setEmpTipNombre(String empTipNombre) {
		this.empTipNombre = empTipNombre;
	}
	public String getEmpTipEstatus() {
		return empTipEstatus;
	}
	public void setEmpTipEstatus(String empTipEstatus) {
		this.empTipEstatus = empTipEstatus;
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
	public String getStatusDesc() {
		return this.getEmpTipEstatus().equals("A") ? "Activo" : "Cancelado";
	}
}
