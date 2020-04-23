package mx.com.teclo.saicdmx.persistencia.hibernate.dto.bloquehohh;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "BLOQUEOHH_CAT_TIPO_BLOQUEO")
public class BloqueohhCatTipoBloqueoDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8520336677781814724L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "CAT_TIPO_BLOQUEO_ID")
	private Long tipoBloqueoId;
	@Column(name = "CODIGO")
	private Integer codigo;
	@Column(name = "DESCRIPCION")
	private String descripcion;
	@Column(name = "ACTIVO")
	private Integer activo;
	@Column(name = "USUARIO_CREA")
	private Long usuarioCreacion;
	@Column(name = "FECHA_CREACION")
	private Date fechaCreacion;
	@Column(name = "USUARIO_MODIFICA")
	private Long usuarioModificacion;
	@Column(name = "ULTIMA_MODIFICACION")
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
