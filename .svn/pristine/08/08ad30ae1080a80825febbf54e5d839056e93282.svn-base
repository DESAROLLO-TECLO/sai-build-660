package mx.com.teclo.saicdmx.persistencia.hibernate.dto.articulos;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="ARTICULOS_INFRAC_FINANZAS")
public class ArticulosInfraccionesFinanzasDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3011485015978242297L;
	
	@Column(name = "ARTICULO")
	private Long artInfrFinArticulo;
	
	@Column(name = "FRACCION")
	private Long artInfrFinFraccion;
	
	@Column(name = "INCISO")
	private String artInfrFinInciso;
	
	@Column(name = "PARRAFO")
	private String artInfrFinParrafo;
	
	@Column(name = "DESCRIPCION")
	private String artInfrFinDescripcion;
	
	@Column(name = "SALARIO")
	private Long artInfrFinSalario;
	
	@Column(name = "APLICADOVEH")
	private Long artInfrFinAplicadoVeh;
	
	@Column(name = "ARRESTO")
	private String artInfrFinArrastro;

	@Column(name = "PUNTOS")
	private Long artInfrFinPuntos;
	
	@Id
	@Column(name = "CONS")
	private Long artInfrFinCons;
	
	@Column(name = "FECHA_INICIO")
	private Date artInfrFinFechaInicio;
	
	@Column(name = "FECHA_TERMINO")
	private Date artInfrFinFechaTermino;
	
	@Column(name = "CREADO_POR")
	private Long creadoPor;
	
	@Column(name = "FECHA_CREACION")
	private Date fechaCreacion;
	
	@Column(name = "MODIFICADO_POR")
	private Long modificadoPor;
	
	@Column(name = "ULTIMA_MODIFICACION")
	private Date ultimaModificacion;
	
	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name="ART_ID", referencedColumnName="ART_ID", insertable=false, updatable=false)
	private ArticuloDTO articulo;

	public Long getArtInfrFinArticulo() {
		return artInfrFinArticulo;
	}

	public void setArtInfrFinArticulo(Long artInfrFinArticulo) {
		this.artInfrFinArticulo = artInfrFinArticulo;
	}

	public Long getArtInfrFinFraccion() {
		return artInfrFinFraccion;
	}

	public void setArtInfrFinFraccion(Long artInfrFinFraccion) {
		this.artInfrFinFraccion = artInfrFinFraccion;
	}

	public String getArtInfrFinInciso() {
		return artInfrFinInciso;
	}

	public void setArtInfrFinInciso(String artInfrFinInciso) {
		this.artInfrFinInciso = artInfrFinInciso;
	}

	public String getArtInfrFinParrafo() {
		return artInfrFinParrafo;
	}

	public void setArtInfrFinParrafo(String artInfrFinParrafo) {
		this.artInfrFinParrafo = artInfrFinParrafo;
	}

	public String getArtInfrFinDescripcion() {
		return artInfrFinDescripcion;
	}

	public void setArtInfrFinDescripcion(String artInfrFinDescripcion) {
		this.artInfrFinDescripcion = artInfrFinDescripcion;
	}

	public Long getArtInfrFinSalario() {
		return artInfrFinSalario;
	}

	public void setArtInfrFinSalario(Long artInfrFinSalario) {
		this.artInfrFinSalario = artInfrFinSalario;
	}

	public Long getArtInfrFinAplicadoVeh() {
		return artInfrFinAplicadoVeh;
	}

	public void setArtInfrFinAplicadoVeh(Long artInfrFinAplicadoVeh) {
		this.artInfrFinAplicadoVeh = artInfrFinAplicadoVeh;
	}

	public String getArtInfrFinArrastro() {
		return artInfrFinArrastro;
	}

	public void setArtInfrFinArrastro(String artInfrFinArrastro) {
		this.artInfrFinArrastro = artInfrFinArrastro;
	}

	public Long getArtInfrFinPuntos() {
		return artInfrFinPuntos;
	}

	public void setArtInfrFinPuntos(Long artInfrFinPuntos) {
		this.artInfrFinPuntos = artInfrFinPuntos;
	}

	public Long getArtInfrFinCons() {
		return artInfrFinCons;
	}

	public void setArtInfrFinCons(Long artInfrFinCons) {
		this.artInfrFinCons = artInfrFinCons;
	}

	public Date getArtInfrFinFechaInicio() {
		return artInfrFinFechaInicio;
	}

	public void setArtInfrFinFechaInicio(Date artInfrFinFechaInicio) {
		this.artInfrFinFechaInicio = artInfrFinFechaInicio;
	}

	public Date getArtInfrFinFechaTermino() {
		return artInfrFinFechaTermino;
	}

	public void setArtInfrFinFechaTermino(Date artInfrFinFechaTermino) {
		this.artInfrFinFechaTermino = artInfrFinFechaTermino;
	}

	public Long getCreadoPor() {
		return creadoPor;
	}

	public void setCreadoPor(Long creadoPor) {
		this.creadoPor = creadoPor;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Long getModificadoPor() {
		return modificadoPor;
	}

	public void setModificadoPor(Long modificadoPor) {
		this.modificadoPor = modificadoPor;
	}

	public Date getUltimaModificacion() {
		return ultimaModificacion;
	}

	public void setUltimaModificacion(Date ultimaModificacion) {
		this.ultimaModificacion = ultimaModificacion;
	}

	public ArticuloDTO getArticulo() {
		return articulo;
	}

	public void setArticulo(ArticuloDTO articulo) {
		this.articulo = articulo;
	}
	
}
