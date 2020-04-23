package mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="VEHICULO_MODELO")
public class VehiculoModeloDTO implements Serializable
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8514886143421451866L;

	@EmbeddedId
	private VehiculoModeloId vModId;
	
	@Column(name = "VMOD_COD")
	private String vModCod;
	
	@Column(name = "VMOD_NOMBRE")
	private String vModNombre;
	
	@Column(name = "VMOD_STATUS")
	private String vModStatus;
	
	@Column(name = "CREADO_POR")
	private Long creadoPor;
	
	@Column(name = "FECHA_CREACION")
	private Date fechaCreacion;
	
	@Column(name = "MODIFICADO_POR")
	private Long modPor;
	
	@Column(name = "ULTIMA_MODIFICACION")
	private Date ultimaMod;
	
	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name="VMAR_ID", referencedColumnName="VMAR_ID", insertable=false, updatable=false)
	private VehiculoMarcaDTO vehiculoMarca;
	
	/**
	 * @return the vModId
	 */
	public VehiculoModeloId getvModId() {
		return vModId;
	}
	/**
	 * @param vModId the vModId to set
	 */
	public void setvModId(VehiculoModeloId vModId) {
		this.vModId = vModId;
	}
	/**
	 * @return the vModCod
	 */
	public String getvModCod() {
		return vModCod;
	}
	/**
	 * @param vModCod the vModCod to set
	 */
	public void setvModCod(String vModCod) {
		this.vModCod = vModCod;
	}
	/**
	 * @return the vModNombre
	 */
	public String getvModNombre() {
		return vModNombre;
	}
	/**
	 * @param vModNombre the vModNombre to set
	 */
	public void setvModNombre(String vModNombre) {
		this.vModNombre = vModNombre;
	}
	/**
	 * @return the vModStatus
	 */
	public String getvModStatus() {
		return vModStatus;
	}
	/**
	 * @param vModStatus the vModStatus to set
	 */
	public void setvModStatus(String vModStatus) {
		this.vModStatus = vModStatus;
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
		return modPor;
	}
	/**
	 * @param modPor the modPor to set
	 */
	public void setModPor(Long modPor) {
		this.modPor = modPor;
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
	
	public VehiculoMarcaDTO getVehiculoMarca() {
		return vehiculoMarca;
	}
	
	public void setVehiculoMarca(VehiculoMarcaDTO vehiculoMarca) {
		this.vehiculoMarca = vehiculoMarca;
	}
	

}
