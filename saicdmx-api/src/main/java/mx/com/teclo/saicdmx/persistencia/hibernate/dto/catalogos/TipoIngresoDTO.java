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
@Table(name="TIPO_INGRESO")
public class TipoIngresoDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -762360172622608730L;
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "T_INGR_ID", unique = true, nullable = false)
	private Long tIngrId;
	@Column(name = "T_INGR_COD")
	private String tIngrCod;
	@Column(name = "T_INGR_NOMBRE")
	private String tIngrNombre;
	@Column(name = "T_INGR_STATUS")
	private String tIngrStatus;
	@Column(name = "CREADO_POR")
	private Long creadoPor;
	@Column(name = "FECHA_CREACION")
	private Date fechaCreacion;
	@Column(name = "MODIFICADO_POR")
	private Long modPor;
	@Column(name = "ULTIMA_MODIFICACION")
	private Date ultimaMod;
	/**
	 * @return the tIngrId
	 */
	public Long gettIngrId() {
		return tIngrId;
	}
	/**
	 * @param tIngrId the tIngrId to set
	 */
	public void settIngrId(Long tIngrId) {
		this.tIngrId = tIngrId;
	}
	/**
	 * @return the tIngrCod
	 */
	public String gettIngrCod() {
		return tIngrCod;
	}
	/**
	 * @param tIngrCod the tIngrCod to set
	 */
	public void settIngrCod(String tIngrCod) {
		this.tIngrCod = tIngrCod;
	}
	/**
	 * @return the tIngrNombre
	 */
	public String gettIngrNombre() {
		return tIngrNombre;
	}
	/**
	 * @param tIngrNombre the tIngrNombre to set
	 */
	public void settIngrNombre(String tIngrNombre) {
		this.tIngrNombre = tIngrNombre;
	}
	/**
	 * @return the tIngrStatus
	 */
	public String gettIngrStatus() {
		return tIngrStatus;
	}
	/**
	 * @param tIngrStatus the tIngrStatus to set
	 */
	public void settIngrStatus(String tIngrStatus) {
		this.tIngrStatus = tIngrStatus;
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
