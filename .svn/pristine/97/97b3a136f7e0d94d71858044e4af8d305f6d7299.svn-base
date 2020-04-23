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
@Table(name="TIPO_LICENCIA")
public class TipoLicenciaDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -936077548484000666L;
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "TIPO_L_ID", unique = true, nullable = false)
	private Long tipoLId;
	@Column(name = "TIPO_L_COD")
	private String tipoLCod;
	@Column(name = "TIPO_L_NOMBRE")
	private String tipoLNombre;
	@Column(name = "TIPO_L_STATUS")
	private String tipoLStatus;
	@Column(name = "CREADO_POR")
	private Long creadoPor;
	@Column(name = "FECHA_CREACION")
	private Date fechaCreacion;
	@Column(name = "MODIFICADO_POR")
	private Long modPor;
	@Column(name = "ULTIMA_MODIFICACION")
	private Date ultimaMod;
	/**
	 * @return the tipoLId
	 */
	public Long getTipoLId() {
		return tipoLId;
	}
	/**
	 * @param tipoLId the tipoLId to set
	 */
	public void setTipoLId(Long tipoLId) {
		this.tipoLId = tipoLId;
	}
	/**
	 * @return the tipoLCod
	 */
	public String getTipoLCod() {
		return tipoLCod;
	}
	/**
	 * @param tipoLCod the tipoLCod to set
	 */
	public void setTipoLCod(String tipoLCod) {
		this.tipoLCod = tipoLCod;
	}
	/**
	 * @return the tipoLNombre
	 */
	public String getTipoLNombre() {
		return tipoLNombre;
	}
	/**
	 * @param tipoLNombre the tipoLNombre to set
	 */
	public void setTipoLNombre(String tipoLNombre) {
		this.tipoLNombre = tipoLNombre;
	}
	/**
	 * @return the tipoLStatus
	 */
	public String getTipoLStatus() {
		return tipoLStatus;
	}
	/**
	 * @param tipoLStatus the tipoLStatus to set
	 */
	public void setTipoLStatus(String tipoLStatus) {
		this.tipoLStatus = tipoLStatus;
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
}
