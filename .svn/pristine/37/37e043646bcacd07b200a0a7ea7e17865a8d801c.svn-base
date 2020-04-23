package mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name="VEHICULO_TIPO")
public class VehiculoTipoDTO implements Serializable
{		
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "VTIPO_ID", unique = true, nullable = false)
	private Long vTipoId;
	
	@Column(name = "VTIPO_COD")
	private String vipoCod;
	
	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name="VSUBT_ID", referencedColumnName="VSUBT_ID", insertable=false, updatable=false)
	private VehiculoSubTipoDTO vSubtipo;
	
	@Column(name = "VTIPO_NOMBRE")
	private String vTipoNombre;
	
	
	@ManyToOne(optional = true, fetch = FetchType.EAGER)
	@JoinColumn(name="T_ARRAS_ID", referencedColumnName="T_ARRAS_ID", insertable=false, updatable=false)
	private TarifasArrastreDTO tarifaArrastre;
	
	@Column(name = "VTIPO_STATUS")
	private String vTipoStatus;
	
	@Column(name = "MODIFICADO_POR")
	private Long modPor;
	
	@Column(name = "ULTIMA_MODIFICACION")
	private Date ultimaMod;
	
	@Column(name = "CREADO_POR")
	private Long creadoPor;
	
	@Column(name = "FECHA_CREACION")
	private Date fechaCreacion;
	/**
	 * @return the vTipoId
	 */
	public Long getvTipoId() {
		return vTipoId;
	}
	/**
	 * @param vTipoId the vTipoId to set
	 */
	public void setvTipoId(Long vTipoId) {
		this.vTipoId = vTipoId;
	}
	/**
	 * @return the vipoCod
	 */
	public String getVipoCod() {
		return vipoCod;
	}
	/**
	 * @param vipoCod the vipoCod to set
	 */
	public void setVipoCod(String vipoCod) {
		this.vipoCod = vipoCod;
	}
	public VehiculoSubTipoDTO getvSubtipo() {
		return vSubtipo;
	}
	public void setvSubtipo(VehiculoSubTipoDTO vSubtipo) {
		this.vSubtipo = vSubtipo;
	}
	public TarifasArrastreDTO getTarifaArrastre() {
		return tarifaArrastre;
	}
	public void setTarifaArrastre(TarifasArrastreDTO tarifaArrastre) {
		this.tarifaArrastre = tarifaArrastre;
	}
	/**
	 * @return the vTipoNombre
	 */
	public String getvTipoNombre() {
		return vTipoNombre;
	}
	/**
	 * @param vTipoNombre the vTipoNombre to set
	 */
	public void setvTipoNombre(String vTipoNombre) {
		this.vTipoNombre = vTipoNombre;
	}
	/**
	 * @return the vTipoStatus
	 */
	public String getvTipoStatus() {
		return vTipoStatus;
	}
	/**
	 * @param vTipoStatus the vTipoStatus to set
	 */
	public void setvTipoStatus(String vTipoStatus) {
		this.vTipoStatus = vTipoStatus;
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

}
