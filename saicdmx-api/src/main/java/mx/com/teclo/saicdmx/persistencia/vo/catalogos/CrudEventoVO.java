package mx.com.teclo.saicdmx.persistencia.vo.catalogos;

public class CrudEventoVO extends BaseCrudVO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7793732242468938596L;
	
	private Long eventoId;
	private String eventoCodigo;
	private String eventoNombre;
	
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
}
