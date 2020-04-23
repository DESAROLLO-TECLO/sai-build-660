package mx.com.teclo.saicdmx.persistencia.hibernate.dto.articulos;

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
@Table(name="SANCIONES_ARTICULOS")
public class SancionesArticulosDTO implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private SancionesArtId sancionId;
	
	@Column(name = "ART_ARRASTRE")
	private String artArrastre;
	
	@Column(name = "ART_INMOVILIZACION")
	private String artInmov;
	
	@Column(name = "SAN_STATUS")
	private String sanStatus;
	
	@Column(name = "CREADO_POR")
	private Long creadoPor;
	
	@Column(name = "FECHA_CREACION")
	private Date fechaCreacion;
	
	@Column(name = "MODIFICADO_POR")
	private Long modPor;
	
	@Column(name = "ULTIMA_MODIFICACION")
	private Date ultimaMod;
	
	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name="ART_ID", referencedColumnName="ART_ID", insertable=false, updatable=false)
	private ArticuloDTO articuloInfraccion;
	
	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name="ART_SANCION", referencedColumnName="ART_ID", insertable=false, updatable=false)
	private ArticuloDTO articuloSancion;

	public SancionesArtId getSancionId() {
		return sancionId;
	}

	public void setSancionId(SancionesArtId sancionId) {
		this.sancionId = sancionId;
	}

	public String getArtArrastre() {
		return artArrastre;
	}

	public void setArtArrastre(String artArrastre) {
		this.artArrastre = artArrastre;
	}

	public String getArtInmov() {
		return artInmov;
	}

	public void setArtInmov(String artInmov) {
		this.artInmov = artInmov;
	}

	public String getSanStatus() {
		return sanStatus;
	}

	public void setSanStatus(String sanStatus) {
		this.sanStatus = sanStatus;
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

	public Long getModPor() {
		return modPor;
	}

	public void setModPor(Long modPor) {
		this.modPor = modPor;
	}

	public Date getUltimaMod() {
		return ultimaMod;
	}

	public void setUltimaMod(Date ultimaMod) {
		this.ultimaMod = ultimaMod;
	}

	public ArticuloDTO getArticuloInfraccion() {
		return articuloInfraccion;
	}

	public void setArticuloInfraccion(ArticuloDTO articuloInfraccion) {
		this.articuloInfraccion = articuloInfraccion;
	}

	public ArticuloDTO getArticuloSancion() {
		return articuloSancion;
	}

	public void setArticuloSancion(ArticuloDTO articuloSancion) {
		this.articuloSancion = articuloSancion;
	}
	
}
