package mx.com.teclo.saicdmx.persistencia.hibernate.dto.articulos;

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
@Table(name="ARTICULOS_CAT_OBSERVACION")
public class ArtCatObsDTO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8009081338755336143L;
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "ID_OBSERVACION", unique = true, nullable = false)
	private Integer idObservacion;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ART_ID")
	private ArticuloDTO articulo;
	@Column(name = "CONSECUTIVO")
	private String consecutivo;
	@Column(name = "DESCRIPCION")
	private String descripcion;
	@Column(name = "VALIDO")
	private Integer valido;
	@Column(name = "FECHA_CREACION")
	private Date fechaCreacion;
	@Column(name = "CREADO_POR")
	private Integer creadoPor;
	@Column(name = "FECHA_MODIFICACION")
	private Date fechaMod;
	@Column(name = "MODIFICADO_POR")
	private Integer ModificadoPor;
	
	/**
	 * @return the idObservacion
	 */
	public Integer getIdObservacion() {
		return idObservacion;
	}
	/**
	 * @param idObservacion the idObservacion to set
	 */
	public void setIdObservacion(Integer idObservacion) {
		this.idObservacion = idObservacion;
	}
	/**
	 * @return the articulo
	 */
	public ArticuloDTO getArticulo() {
		return articulo;
	}
	/**
	 * @param articulo the articulo to set
	 */
	public void setArticulo(ArticuloDTO articulo) {
		this.articulo = articulo;
	}
	/**
	 * @return the consecutivo
	 */
	public String getConsecutivo() {
		return consecutivo;
	}
	/**
	 * @param consecutivo the consecutivo to set
	 */
	public void setConsecutivo(String consecutivo) {
		this.consecutivo = consecutivo;
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
	 * @return the valido
	 */
	public Integer getValido() {
		return valido;
	}
	/**
	 * @param valido the valido to set
	 */
	public void setValido(Integer valido) {
		this.valido = valido;
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
	 * @return the creadoPor
	 */
	public Integer getCreadoPor() {
		return creadoPor;
	}
	/**
	 * @param creadoPor the creadoPor to set
	 */
	public void setCreadoPor(Integer creadoPor) {
		this.creadoPor = creadoPor;
	}
	/**
	 * @return the fechaMod
	 */
	public Date getFechaMod() {
		return fechaMod;
	}
	/**
	 * @param fechaMod the fechaMod to set
	 */
	public void setFechaMod(Date fechaMod) {
		this.fechaMod = fechaMod;
	}
	/**
	 * @return the modificadoPor
	 */
	public Integer getModificadoPor() {
		return ModificadoPor;
	}
	/**
	 * @param modificadoPor the modificadoPor to set
	 */
	public void setModificadoPor(Integer modificadoPor) {
		ModificadoPor = modificadoPor;
	}
	
}
