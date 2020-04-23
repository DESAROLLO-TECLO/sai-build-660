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
@Table(name = "SMV_CAT_TIPO_ARCHIVO")
public class SemoviCatTipoArchivoDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3333653326452569451L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "CAT_TIPO_ARCHIVO_ID")
	private Long catTipoArchivoId;
	@Column(name = "DESCRIPCION")
	private String descripcion;
	@Column(name = "ACTIVO")
	private Boolean activo;
	@Column(name = "CODIGO")
	private Integer codigo;
	@Column(name = "TIPO")
	private String tipo;
	@Column(name = "CREADO_POR")
	private Long cradoPor;
	@Column(name = "FECHA_CREACION")
	private Date fechaCracion;
	
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
	 * @return the tipo
	 */
	public String getTipo() {
		return tipo;
	}
	/**
	 * @param tipo the tipo to set
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	/**
	 * @return the catTipoArchivoId
	 */
	public Long getCatTipoArchivoId() {
		return catTipoArchivoId;
	}
	/**
	 * @param catTipoArchivoId the catTipoArchivoId to set
	 */
	public void setCatTipoArchivoId(Long catTipoArchivoId) {
		this.catTipoArchivoId = catTipoArchivoId;
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
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
