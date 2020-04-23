package mx.com.teclo.saicdmx.persistencia.vo.bloqueohh;

import java.io.Serializable;

public class BloqueohhReporteVO implements Serializable {

 	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
 	private String numeroSeriehh;
 	private String nombreOficial;
 	private String placaOficial;
 	private String tipoBloqueo;
  	private String estatusBloqueo;
 	private String fechaBloqueo;
 	private String fechaDesbloqueo;
 	
	public String getNumeroSeriehh() {
		return numeroSeriehh;
	}
	public void setNumeroSeriehh(String numeroSeriehh) {
		this.numeroSeriehh = numeroSeriehh;
	}
	public String getNombreOficial() {
		return nombreOficial;
	}
	public void setNombreOficial(String nombreOficial) {
		this.nombreOficial = nombreOficial;
	}
	public String getPlacaOficial() {
		return placaOficial;
	}
	public void setPlacaOficial(String placaOficial) {
		this.placaOficial = placaOficial;
	}
	public String getTipoBloqueo() {
		return tipoBloqueo;
	}
	public void setTipoBloqueo(String tipoBloqueo) {
		this.tipoBloqueo = tipoBloqueo;
	}
	public String getEstatusBloqueo() {
		return estatusBloqueo;
	}
	public void setEstatusBloqueo(String estatusBloqueo) {
		this.estatusBloqueo = estatusBloqueo;
	}
	public String getFechaBloqueo() {
		return fechaBloqueo;
	}
	public void setFechaBloqueo(String fechaBloqueo) {
		this.fechaBloqueo = fechaBloqueo;
	}
	public String getFechaDesbloqueo() {
		return fechaDesbloqueo;
	}
	public void setFechaDesbloqueo(String fechaDesbloqueo) {
		this.fechaDesbloqueo = fechaDesbloqueo;
	}
 
}
