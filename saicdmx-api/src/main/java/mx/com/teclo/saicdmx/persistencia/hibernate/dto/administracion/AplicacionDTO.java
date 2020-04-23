package mx.com.teclo.saicdmx.persistencia.hibernate.dto.administracion;

  
import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="TSEG_CAT_APLICACIONES")
public class AplicacionDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5317484855992578678L;

	@Id
	@Column(name = "ID_APLICACION", unique = true, nullable = false)
	private Integer idAplicacion;
	
	@Column(name = "CD_APLICACION" , nullable = false)
	private String codigo;
	
	@Column(name = "NB_APLICACION",  nullable = false)
	private String nombre;
	
	@Column(name = "TX_APLICACION", nullable = false)
	private String descripcion;
	
 	@Column(name = "ID_CREADO_POR")
	private Long usuarioCreacion;
	
	@Column(name = "FH_CREACION")
	private Date fechaCreacion;
	
	@Column(name = "ID_MODIFICADO_POR")
	private Long usuarioModifica;
	
	@Column(name = "FH_MODIFICACION")
	private Date fechaModificacion;
	
	@Column(name = "NU_EXPIRACION")
 	private Long tiempoExpiracion;
	
	@Column(name = "NU_INACTIVIDAD")
 	private Long tiempoInactividad;
	 
	
	public AplicacionDTO() {
 	}

	public Integer getIdAplicacion() {
		return idAplicacion;
	}

	public void setIdAplicacion(Integer idAplicacion) {
		this.idAplicacion = idAplicacion;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
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

	public Long getUsuarioModifica() {
		return usuarioModifica;
	}

	public void setUsuarioModifica(Long usuarioModifica) {
		this.usuarioModifica = usuarioModifica;
	}

	public Date getFechaModificacion() {
		return fechaModificacion;
	}

	public void setFechaModificacion(Date fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}

	public Long getTiempoExpiracion() {
		return tiempoExpiracion;
	}

	public void setTiempoExpiracion(Long tiempoExpiracion) {
		this.tiempoExpiracion = tiempoExpiracion;
	}

	public Long getTiempoInactividad() {
		return tiempoInactividad;
	}

	public void setTiempoInactividad(Long tiempoInactividad) {
		this.tiempoInactividad = tiempoInactividad;
	}

 
		
}
