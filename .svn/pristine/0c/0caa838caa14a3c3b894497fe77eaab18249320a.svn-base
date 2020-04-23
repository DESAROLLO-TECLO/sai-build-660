package mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="TIPO_GRUA")
public class TipoGruaDTO  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1583520513851116134L;

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "TIP_G_ID", unique = true, nullable = false)
	private Long tipGId;
	@Column(name = "TIP_G_COD")
	private String tipGCod;
	@Column(name = "TIP_G_NOMBRE")
	private String tipGNombre;
	@Column(name = "TIP_G_STATUS")
	private String tipGStatus;
	@Column(name = "CREADO_POR")
	private Long creadoPor;
	@Column(name = "FECHA_CREACION")
	private Date fechaCreacion;
	@Column(name = "MODIFICADO_POR")
	private Long modPor;
	@Column(name = "ULTIMA_MODIFICACION")
	private Date ultimaMod;	
	@ManyToOne(cascade = CascadeType.ALL)
	//@JoinColumn(name = "TIPO_INGR_ID", nullable = false)
	//private TipoIngresoDTO tipoIngreso;
	/**
	 * @return the tipGId
	 */
	public Long getTipGId() {
		return tipGId;
	}
	/**
	 * @param tipGId the tipGId to set
	 */
	public void setTipGId(Long tipGId) {
		this.tipGId = tipGId;
	}
	/**
	 * @return the tipGCod
	 */
	public String getTipGCod() {
		return tipGCod;
	}
	/**
	 * @param tipGCod the tipGCod to set
	 */
	public void setTipGCod(String tipGCod) {
		this.tipGCod = tipGCod;
	}
	/**
	 * @return the tipGNombre
	 */
	public String getTipGNombre() {
		return tipGNombre;
	}
	/**
	 * @param tipGNombre the tipGNombre to set
	 */
	public void setTipGNombre(String tipGNombre) {
		this.tipGNombre = tipGNombre;
	}
	/**
	 * @return the tipGStatus
	 */
	public String getTipGStatus() {
		return tipGStatus;
	}
	/**
	 * @param tipGStatus the tipGStatus to set
	 */
	public void setTipGStatus(String tipGStatus) {
		this.tipGStatus = tipGStatus;
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
	/**
	 * @return the tipoIngreso
	 */
//	public TipoIngresoDTO getTipoIngreso() {
//		return tipoIngreso;
//	}
	/**
	 * @param tipoIngreso the tipoIngreso to set
	 */
//	public void setTipoIngreso(TipoIngresoDTO tipoIngreso) {
//		this.tipoIngreso = tipoIngreso;
//	}
	
}
