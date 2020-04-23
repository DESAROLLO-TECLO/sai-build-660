package mx.com.teclo.saicdmx.persistencia.hibernate.vo.catalogos;

import java.io.Serializable;

public class EventoVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1922819720178418175L;
	
	private Long eventoId;
	private String eventoCodigo;
	private String eventoNombre;
	private String eventoStatus;
	
	public Long getEventoId() {
		return eventoId;
	}
	public void setEventoId(Long eventoId) {
		this.eventoId = eventoId;
	}
	public String getEventoCodigo() {
		return eventoCodigo;
	}
	public void setEventoCodigo(String eventoCodigo) {
		this.eventoCodigo = eventoCodigo;
	}
	public String getEventoNombre() {
		return eventoNombre;
	}
	public void setEventoNombre(String eventoNombre) {
		this.eventoNombre = eventoNombre;
	}
	public String getEventoStatus() {
		return eventoStatus;
	}
	public void setEventoStatus(String eventoStatus) {
		this.eventoStatus = eventoStatus;
	}
	public String getStatusDesc() {
		return this.getEventoStatus().equals("A") ? "Activo" : "Cancelado";
	}
}
