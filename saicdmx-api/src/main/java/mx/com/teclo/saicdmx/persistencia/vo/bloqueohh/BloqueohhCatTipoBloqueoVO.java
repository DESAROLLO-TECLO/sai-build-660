package mx.com.teclo.saicdmx.persistencia.vo.bloqueohh;

import java.io.Serializable;
import java.util.Date;

public class BloqueohhCatTipoBloqueoVO implements Serializable {
	
 	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	 
	
	private Long tipoBloqueoId;
 	private Integer codigo;
 	private String descripcion;
 	private Integer activo;
 	private Long usuarioCreacion;
 	private Date fechaCreacion;
 	private Long usuarioModificacion;
 	private Date ultimaModificacion;
 	
	public Long getTipoBloqueoId() {
		return tipoBloqueoId;
	}
	public void setTipoBloqueoId(Long tipoBloqueoId) {
		this.tipoBloqueoId = tipoBloqueoId;
	}
	public Integer getCodigo() {
		return codigo;
	}
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Integer getActivo() {
		return activo;
	}
	public void setActivo(Integer activo) {
		this.activo = activo;
	}
	public Long getUsuarioCreacion() {
		return usuarioCreacion;
	}
	public void setUsuarioCreacion(Long usuarioCreacion) {
		this.usuarioCreacion = usuarioCreacion;
	}
	public Date getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	public Long getUsuarioModificacion() {
		return usuarioModificacion;
	}
	public void setUsuarioModificacion(Long usuarioModificacion) {
		this.usuarioModificacion = usuarioModificacion;
	}
	public Date getUltimaModificacion() {
		return ultimaModificacion;
	}
	public void setUltimaModificacion(Date ultimaModificacion) {
		this.ultimaModificacion = ultimaModificacion;
	}
 	
 	

}
