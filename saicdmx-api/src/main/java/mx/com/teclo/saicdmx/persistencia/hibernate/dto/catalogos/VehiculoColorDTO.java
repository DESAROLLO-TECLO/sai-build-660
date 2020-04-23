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
@Table(name="VEHICULO_COLOR")
public class VehiculoColorDTO implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "VCOLOR_ID", unique = true, nullable = false)
	private Long vColorId;
	@Column(name ="VCOLOR_COD")
	private String vColorCod;
	@Column(name ="VCOLOR_NOMBRE")
	private String vColorNombre;
	@Column(name ="VCOLOR_STATUS")
	private String vColorStatus;
	@Column(name ="CREADO_POR")
	private Long creadoPor;
	@Column(name ="FECHA_CREACION")
	private Date fechaCreacion;
	@Column(name ="MODIFICADO_POR")
	private Long modPor;
	@Column(name ="ULTIMA_MODIFICACION")
	private Date ultimaMod;
	/**
	 * @return the vColorId
	 */
	public Long getvColorId() {
		return vColorId;
	}
	/**
	 * @param vColorId the vColorId to set
	 */
	public void setvColorId(Long vColorId) {
		this.vColorId = vColorId;
	}
	/**
	 * @return the vColorCod
	 */
	public String getvColorCod() {
		return vColorCod;
	}
	/**
	 * @param vColorCod the vColorCod to set
	 */
	public void setvColorCod(String vColorCod) {
		this.vColorCod = vColorCod;
	}
	/**
	 * @return the vColorNombre
	 */
	public String getvColorNombre() {
		return vColorNombre;
	}
	/**
	 * @param vColorNombre the vColorNombre to set
	 */
	public void setvColorNombre(String vColorNombre) {
		this.vColorNombre = vColorNombre;
	}
	/**
	 * @return the vColorStatus
	 */
	public String getvColorStatus() {
		return vColorStatus;
	}
	/**
	 * @param vColorStatus the vColorStatus to set
	 */
	public void setvColorStatus(String vColorStatus) {
		this.vColorStatus = vColorStatus;
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
