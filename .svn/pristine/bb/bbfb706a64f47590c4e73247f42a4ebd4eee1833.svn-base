package mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="VEHICULO_MARCA")
public class VehiculoMarcaDTO implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "VMAR_ID", unique = true, nullable = false)
	private Long vMarId;
	@Column(name = "VMAR_COD")
	private String vMarCod;
	@Column(name = "VMAR_NOMBRE")
	private String vMarNombre;
	@Column(name = "VMAR_STATUS")
	private String vMarStatus;
	@Column(name = "CREADO_POR")
	private Long creadoPor;
	@Column(name = "FECHA_CREACION")
	private Date fechaCreacion;
	@Column(name = "MODIFICADO_POR")
	private Long ModPor;
	@Column(name = "ULTIMA_MODIFICACION")
	private Date ultimaMod;
	/*@OneToMany(mappedBy = "vModId.vMarca")
	private Set<VehiculoModeloDTO> vMods;*/
	/**
	 * @return the vMarId
	 */
	public Long getvMarId() {
		return vMarId;
	}
	/**
	 * @param vMarId the vMarId to set
	 */
	public void setvMarId(Long vMarId) {
		this.vMarId = vMarId;
	}
	/**
	 * @return the vMarCod
	 */
	public String getvMarCod() {
		return vMarCod;
	}
	/**
	 * @param vMarCod the vMarCod to set
	 */
	public void setvMarCod(String vMarCod) {
		this.vMarCod = vMarCod;
	}
	/**
	 * @return the vMarNombre
	 */
	public String getvMarNombre() {
		return vMarNombre;
	}
	/**
	 * @param vMarNombre the vMarNombre to set
	 */
	public void setvMarNombre(String vMarNombre) {
		this.vMarNombre = vMarNombre;
	}
	/**
	 * @return the vMarStatus
	 */
	public String getvMarStatus() {
		return vMarStatus;
	}
	/**
	 * @param vMarStatus the vMarStatus to set
	 */
	public void setvMarStatus(String vMarStatus) {
		this.vMarStatus = vMarStatus;
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
	 * @return the modPor
	 */
	public Long getModPor() {
		return ModPor;
	}
	/**
	 * @param modPor the modPor to set
	 */
	public void setModPor(Long modPor) {
		ModPor = modPor;
	}
	/**
	 * @return the ultimaMod
	 */
	public Date getUltimaMod() {
		return ultimaMod;
	}
	/**
	 * @param ultimaMod the ultimaMod to set
	 */
	public void setUltimaMod(Date ultimaMod) {
		this.ultimaMod = ultimaMod;
	}
	/* *
	 * @return the vMods
	 * /
	public Set<VehiculoModeloDTO> getvMods() {
		return vMods;
	}
	/**
	 * @param vMods the vMods to set
	 * /
	public void setvMods(Set<VehiculoModeloDTO> vMods) {
		this.vMods = vMods;
	}*/

}
