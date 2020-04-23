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
@Table(name="CATALOGOS_WEB")
public class CatalogoWebDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2167708926812431732L;
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "ID_CATALOGO", unique = true, nullable = false)
	private Long catalogoId;
	
	@Column(name = "DESCRIPCION")
	private String catalogoDesc;
	
	@Column(name = "ESTATUS")
	private Long catalogoStatus;
	
	@Column(name = "FECHA_CREACION")
	private Date fechaCreacion;
	
	@Column(name = "CREADO_POR")
	private Long creadoPor;
	
	@Column(name = "FECHA_MODIFICACION")
	private Date fechaModificacion;
	
	@Column(name = "MODIFICADO_POR")
	private Long modificadoPor;
	
	public Long getCatalogoId() {
		return catalogoId;
	}
	public void setCatalogoId(Long catalogoId) {
		this.catalogoId = catalogoId;
	}
	public String getCatalogoDesc() {
		return catalogoDesc;
	}
	public void setCatalogoDesc(String catalogoDesc) {
		this.catalogoDesc = catalogoDesc;
	}
	public Long getCatalogoStatus() {
		return catalogoStatus;
	}
	public void setCatalogoStatus(Long catalogoStatus) {
		this.catalogoStatus = catalogoStatus;
	}
	public Date getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	public Long getCreadoPor() {
		return creadoPor;
	}
	public void setCreadoPor(Long creadoPor) {
		this.creadoPor = creadoPor;
	}
	public Date getFechaModificacion() {
		return fechaModificacion;
	}
	public void setFechaModificacion(Date fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}
	public Long getModificadoPor() {
		return modificadoPor;
	}
	public void setModificadoPor(Long modificadoPor) {
		this.modificadoPor = modificadoPor;
	}
}
