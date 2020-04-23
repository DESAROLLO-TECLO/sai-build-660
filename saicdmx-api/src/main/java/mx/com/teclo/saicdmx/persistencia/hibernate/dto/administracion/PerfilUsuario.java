package mx.com.teclo.saicdmx.persistencia.hibernate.dto.administracion;
 
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import mx.com.teclo.saicdmx.persistencia.hibernate.dto.empleados.EmpleadosDTO;

  
@Entity
@Table(name = "PERFIL_USUARIO")
public class PerfilUsuario implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private PerfilUsuarioId id;
	private EmpleadosDTO empleadoDTO;
	private PerfilDTO profileDTO;
	private AplicacionDTO aplicacionDTO;

	public PerfilUsuario() {
	}

	public PerfilUsuario(PerfilUsuarioId id, EmpleadosDTO empleadoDTO, PerfilDTO perfiles,
			AplicacionDTO aplicacionDTO) {
		this.id = id;
		this.empleadoDTO = empleadoDTO;
		this.profileDTO = perfiles;
		this.aplicacionDTO = aplicacionDTO;
	}

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "perfilId", column = @Column(name = "PERFIL_ID", nullable = false, precision = 22, scale = 0)),
			@AttributeOverride(name = "usuId", column = @Column(name = "USU_ID", nullable = false, precision = 11, scale = 0)),
			@AttributeOverride(name = "idAplicacion", column = @Column(name = "ID_APLICACION", nullable = false, precision = 11, scale = 0)) })
	public PerfilUsuarioId getId() {
		return this.id;
	}

	public void setId(PerfilUsuarioId id) {
		this.id = id;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USU_ID", nullable = false, insertable = false, updatable = false)
	public EmpleadosDTO getEmpleadoDTO() {
		return empleadoDTO;
	}

	public void setEmpleadoDTO(EmpleadosDTO empleadoDTO) {
		this.empleadoDTO = empleadoDTO;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PERFIL_ID", nullable = false, insertable = false, updatable = false)
	public PerfilDTO getProfileDTO() {
		return profileDTO;
	}

	public void setProfileDTO(PerfilDTO profileDTO) {
		this.profileDTO = profileDTO;
	}
 
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_APLICACION", nullable = false, insertable = false, updatable = false)
	public AplicacionDTO getAplicacionDTO() {
		return aplicacionDTO;
	}

	public void setAplicacionDTO(AplicacionDTO aplicacionDTO) {
		this.aplicacionDTO = aplicacionDTO;
	}

	
}
