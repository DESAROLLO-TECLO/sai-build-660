package mx.com.teclo.saicdmx.persistencia.vo.bloqueohh;

import java.io.Serializable;
import java.util.Date;

public class BloqueohhRegistroVO implements Serializable {

 	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long oficialId;
 	private String nombreOficial;
	private String placaOficial;
	private Long registroId;
 	private String numeroSeriehh;
  	private BloqueohhCatTipoBloqueoVO bloqueohhCatTipoBloqueo = new BloqueohhCatTipoBloqueoVO();
 	private String estatusBloqueo;
 	private Date fechaBloqueo;
 	private Date fechaDesbloqueo;
 	
 	
	public Long getOficialId() {
		return oficialId;
	}
	public void setOficialId(Long oficialId) {
		this.oficialId = oficialId;
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
	public Long getRegistroId() {
		return registroId;
	}
	public void setRegistroId(Long registroId) {
		this.registroId = registroId;
	}
	public String getNumeroSeriehh() {
		return numeroSeriehh;
	}
	public void setNumeroSeriehh(String numeroSeriehh) {
		this.numeroSeriehh = numeroSeriehh;
	}
	 
	public BloqueohhCatTipoBloqueoVO getBloqueohhCatTipoBloqueo() {
		return bloqueohhCatTipoBloqueo;
	}
	public void setBloqueohhCatTipoBloqueo(BloqueohhCatTipoBloqueoVO bloqueohhCatTipoBloqueo) {
		this.bloqueohhCatTipoBloqueo = bloqueohhCatTipoBloqueo;
	}
	 
	public String getEstatusBloqueo() {
		return estatusBloqueo;
	}
	public void setEstatusBloqueo(String estatusBloqueo) {
		this.estatusBloqueo = estatusBloqueo;
	}
	public Date getFechaBloqueo() {
		return fechaBloqueo;
	}
	public void setFechaBloqueo(Date fechaBloqueo) {
		this.fechaBloqueo = fechaBloqueo;
	}
	public Date getFechaDesbloqueo() {
		return fechaDesbloqueo;
	}
	public void setFechaDesbloqueo(Date fechaDesbloqueo) {
		this.fechaDesbloqueo = fechaDesbloqueo;
	}
 
	 
	
	 
   
}
