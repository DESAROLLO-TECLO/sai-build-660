package mx.com.teclo.saicdmx.persistencia.vo.articulos;

import java.io.Serializable;
import java.util.Date;

public class SancionArticuloVO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8615664969939326094L;

	private SancionArtIdVO sancionId;
	private String artArrastre;
	private String artInmov;
	private String sanStatus;
	private Long creadoPor;
	private Date fechaCreacion;
	private Long modPor;
	private Date ultimaMod;
	private ArticuloVO articuloInfraccion;
	private ArticuloVO articuloSancion;
	
	public SancionArtIdVO getSancionId() {
		return sancionId;
	}
	public void setSancionId(SancionArtIdVO sancionId) {
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
	public ArticuloVO getArticuloInfraccion() {
		return articuloInfraccion;
	}
	public void setArticuloInfraccion(ArticuloVO articuloInfraccion) {
		this.articuloInfraccion = articuloInfraccion;
	}
	public ArticuloVO getArticuloSancion() {
		return articuloSancion;
	}
	public void setArticuloSancion(ArticuloVO articuloSancion) {
		this.articuloSancion = articuloSancion;
	}
}
