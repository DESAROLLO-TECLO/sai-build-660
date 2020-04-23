package mx.com.teclo.saicdmx.persistencia.vo.controlsuministros;

import java.io.Serializable;

public class ControlAlmacenVO implements Serializable {

	private static final long serialVersionUID = 3555308119480100363L;
	private long id;
	private String txt_num_remision;
	private String txt_recibe;
	private Long txt_del_folio;
	private Long txt_al_folio;
	private Long txt_total;
	//@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy", timezone="America/Mexico_City")
	private String txt_fecha;
	private Long userID;
	private Long ltipo_sum;
	private String resultado;
	private String mensaje;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTxt_num_remision() {
		return txt_num_remision;
	}
	public void setTxt_num_remision(String txt_num_remision) {
		this.txt_num_remision = txt_num_remision;
	}
	public String getTxt_recibe() {
		return txt_recibe;
	}
	public void setTxt_recibe(String txt_recibe) {
		this.txt_recibe = txt_recibe;
	}
	public Long getTxt_del_folio() {
		return txt_del_folio;
	}
	public void setTxt_del_folio(Long txt_del_folio) {
		this.txt_del_folio = txt_del_folio;
	}
	public Long getTxt_al_folio() {
		return txt_al_folio;
	}
	public void setTxt_al_folio(Long txt_al_folio) {
		this.txt_al_folio = txt_al_folio;
	}
	public Long getTxt_total() {
		return txt_total;
	}
	public void setTxt_total(Long txt_total) {
		this.txt_total = txt_total;
	}
	public String getTxt_fecha() {
		return txt_fecha;
	}
	public void setTxt_fecha(String txt_fecha) {
		this.txt_fecha = txt_fecha;
	}
	public Long getUserID() {
		return userID;
	}
	public void setUserID(Long userID) {
		this.userID = userID;
	}
	public Long getLtipo_sum() {
		return ltipo_sum;
	}
	public void setLtipo_sum(Long ltipo_sum) {
		this.ltipo_sum = ltipo_sum;
	}
	public String getResultado() {
		return resultado;
	}
	public void setResultado(String resultado) {
		this.resultado = resultado;
	}
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	
	
   
}
