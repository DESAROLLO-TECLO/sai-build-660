package mx.com.teclo.saicdmx.persistencia.hibernate.dto.usuarios;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="USUARIOS")
public class UsuariosDTO implements Serializable{

	private static final long serialVersionUID = 8210820947982004389L;

	@Id
	@Column(name = "USU_ID", unique = true, nullable = false)
	private Long usuarioId;
	
	@Column(name = "EMP_ID")
	private Long empleadoId;
	
	@Column(name = "USU_LOGIN")
	private String usuarioLogin;
	
	@Column(name = "USU_PASSWORD")
	private String usuarioPassword;
	
	@Column(name = "USU_STATUS")
	private String usuarioStatus;
	
	@Column(name = "CREADO_POR")
	private Long creadoPor;
	
	@Column(name = "FECHA_CREACION")
	private Date fechaCreacion;
	
	@Column(name = "MODIFICADO_POR")
	private Long modificadoPor;
	
	@Column(name = "ULTIMA_MODIFICACION")
	private Date ultimaModificacion;

	/**
	 * @return the usuarioId
	 */
	public Long getUsuarioId() {
		return usuarioId;
	}

	/**
	 * @param usuarioId the usuarioId to set
	 */
	public void setUsuarioId(Long usuarioId) {
		this.usuarioId = usuarioId;
	}

	/**
	 * @return the empleadoId
	 */
	public Long getEmpleadoId() {
		return empleadoId;
	}

	/**
	 * @param empleadoId the empleadoId to set
	 */
	public void setEmpleadoId(Long empleadoId) {
		this.empleadoId = empleadoId;
	}

	/**
	 * @return the usuarioLogin
	 */
	public String getUsuarioLogin() {
		return usuarioLogin;
	}

	/**
	 * @param usuarioLogin the usuarioLogin to set
	 */
	public void setUsuarioLogin(String usuarioLogin) {
		this.usuarioLogin = usuarioLogin;
	}

	/**
	 * @return the usuarioPassword
	 */
	public String getUsuarioPassword() {
		return usuarioPassword;
	}

	/**
	 * @param usuarioPassword the usuarioPassword to set
	 */
	public void setUsuarioPassword(String usuarioPassword) {
		this.usuarioPassword = usuarioPassword;
	}

	/**
	 * @return the usuarioStatus
	 */
	public String getUsuarioStatus() {
		return usuarioStatus;
	}

	/**
	 * @param usuarioStatus the usuarioStatus to set
	 */
	public void setUsuarioStatus(String usuarioStatus) {
		this.usuarioStatus = usuarioStatus;
	}

	/**
	 * @return the creadoPor
	 */
	public Long getCreadoPor() {
		return creadoPor;
	}

	/**
	 * @param creadoPor the creadoPor to set
	 */
	public void setCreadoPor(Long creadoPor) {
		this.creadoPor = creadoPor;
	}

	/**
	 * @return the fechaCreacion
	 */
	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	/**
	 * @param fechaCreacion the fechaCreacion to set
	 */
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	/**
	 * @return the modificadoPor
	 */
	public Long getModificadoPor() {
		return modificadoPor;
	}

	/**
	 * @param modificadoPor the modificadoPor to set
	 */
	public void setModificadoPor(Long modificadoPor) {
		this.modificadoPor = modificadoPor;
	}

	/**
	 * @return the ultimaModificacion
	 */
	public Date getUltimaModificacion() {
		return ultimaModificacion;
	}

	/**
	 * @param ultimaModificacion the ultimaModificacion to set
	 */
	public void setUltimaModificacion(Date ultimaModificacion) {
		this.ultimaModificacion = ultimaModificacion;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
