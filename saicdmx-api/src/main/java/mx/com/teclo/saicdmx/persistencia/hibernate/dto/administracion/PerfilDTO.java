package mx.com.teclo.saicdmx.persistencia.hibernate.dto.administracion;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Immutable;

import mx.com.teclomexicana.arquitectura.ortogonales.seguridad.dto.AplicacionDTO;

@Entity(name="PerfilDTO")
@Table(name="PERFILES")
@Immutable
public class PerfilDTO implements Serializable {
 
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2073543093107067611L;

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "PERFIL_ID", unique = true, nullable = false)
	private Long perfilId;
	
	@Column(name = "PERFIL_NOMBRE")
	private String perfilNombre;
	
	@Column(name = "ST_VISIBLE")
	private String stVisible;
	
	@Column(name = "CD_PERFIL", length = 15)
	private String perfilCodigo;
	
	@Column(name = "ST_ACTIVO", nullable = false, precision = 1, scale = 0)
	private boolean stActivo;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_APLICACION")
	private AplicacionDTO aplicacionDTO;

	public Long getPerfilId() {
		return perfilId;
	}
	public void setPerfilId(Long perfilId) {
		this.perfilId = perfilId;
	}
	public String getPerfilNombre() {
		return perfilNombre;
	}
	public void setPerfilNombre(String perfilNombre) {
		this.perfilNombre = perfilNombre;
	}
	public String getStVisible() {
		return stVisible;
	}
	public void setStVisible(String stVisible) {
		this.stVisible = stVisible;
	}
	public String getPerfilCodigo() {
		return perfilCodigo;
	}
	public void setPerfilCodigo(String perfilCodigo) {
		this.perfilCodigo = perfilCodigo;
	}
	public AplicacionDTO getAplicacionDTO() {
		return aplicacionDTO;
	}
	public void setAplicacionDTO(AplicacionDTO aplicacionDTO) {
		this.aplicacionDTO = aplicacionDTO;
	}
}
