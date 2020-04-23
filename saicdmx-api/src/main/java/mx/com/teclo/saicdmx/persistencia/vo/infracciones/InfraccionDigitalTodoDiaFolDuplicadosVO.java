package mx.com.teclo.saicdmx.persistencia.vo.infracciones;

import java.io.Serializable;
import java.util.Date;

public class InfraccionDigitalTodoDiaFolDuplicadosVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7698439790304108617L;
	
	private String folio;
	private String placa;
	private String articulo;
	private String fraccion;
	private String parrafo;
	private String inciso;
	private Date fechaHora;
	private Long lote;
	private String status;
	private Date fechaCreacion;
	
	public String getFolio() {
		return folio;
	}
	public void setFolio(String folio) {
		this.folio = folio;
	}
	public String getPlaca() {
		return placa;
	}
	public void setPlaca(String placa) {
		this.placa = placa;
	}
	public String getArticulo() {
		return articulo;
	}
	public void setArticulo(String articulo) {
		this.articulo = articulo;
	}
	public String getFraccion() {
		return fraccion;
	}
	public void setFraccion(String fraccion) {
		this.fraccion = fraccion;
	}
	public String getInciso() {
		return inciso;
	}
	public void setInciso(String inciso) {
		this.inciso = inciso;
	}
	public String getParrafo() {
		return parrafo;
	}
	public void setParrafo(String parrafo) {
		this.parrafo = parrafo;
	}
	public Date getFechaHora() {
		return fechaHora;
	}
	public void setFechaHora(Date fechaHora) {
		this.fechaHora = fechaHora;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Long getLote() {
		return lote;
	}
	public void setLote(Long lote) {
		this.lote = lote;
	}
	public Date getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
}
