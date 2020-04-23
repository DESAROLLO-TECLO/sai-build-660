package mx.com.teclo.saicdmx.persistencia.hibernate.dto.semovi;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "SMV_CAT_MOTIVO_CANCELACION")
public class SemoviCatMotivoCancelacionDTO implements Serializable {
	/**
	 * @serial
	 */
	private static final long serialVersionUID = 519214081641069553L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "SMV_CAT_MOTIVO_CANCELACION_ID")
	private Long catMotivoCancelacionId;
	@Column(name = "DESCRIPCION")
	private String descripcion;
	@Column(name = "ACTIVO")
	private Boolean activo;
	@Column(name = "CODIGO")
	private Integer codigo;
	@Column(name = "CREADO_POR")
	private Long cradoPor;
	@Column(name = "FECHA_CREACION")
	private Date fechaCracion;
	@Column(name = "MODIFICADO_POR")
	private Long modificadoPor;
	@Column(name = "ULTIMA_MODIFICACION")
	private Date ultimaModificacion;
	
	/**
	 * @return the codigo
	 */
	public Integer getCodigo() {
		return codigo;
	}
	/**
	 * @param codigo the codigo to set
	 */
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	/**
	 * @return the catMotivoCancelacionId
	 */
	public Long getCatMotivoCancelacionId() {
		return catMotivoCancelacionId;
	}
	/**
	 * @param catMotivoCancelacionId the catMotivoCancelacionId to set
	 */
	public void setCatMotivoCancelacionId(Long catMotivoCancelacionId) {
		this.catMotivoCancelacionId = catMotivoCancelacionId;
	}
	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}
	/**
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	/**
	 * @return the activo
	 */
	public Boolean getActivo() {
		return activo;
	}
	/**
	 * @param activo the activo to set
	 */
	public void setActivo(Boolean activo) {
		this.activo = activo;
	}
	/**
	 * @return the cradoPor
	 */
	public Long getCradoPor() {
		return cradoPor;
	}
	/**
	 * @param cradoPor the cradoPor to set
	 */
	public void setCradoPor(Long cradoPor) {
		this.cradoPor = cradoPor;
	}
	/**
	 * @return the fechaCracion
	 */
	public Date getFechaCracion() {
		return fechaCracion;
	}
	/**
	 * @param fechaCracion the fechaCracion to set
	 */
	public void setFechaCracion(Date fechaCracion) {
		this.fechaCracion = fechaCracion;
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
