package mx.com.teclo.saicdmx.persistencia.vo.controlsuministros;

import java.io.Serializable;

public class DevolucionesVO implements Serializable {

	private static final long serialVersionUID = 3555308119480100363L;
	
	private long id;
	private int ltipo_sum;
	private int lstAreaOpe;
	private int lstRegional;
	private int lstRecibe;
	private String txt_num_recibo;
	private String txt_fecha;
	private Long txt_folio_ini;
	private Long txt_folio_fin;
	private Long txt_tot_folios;
	private String txt_motivo;
	private int idUser;
	private String resultado;
	private String mensaje;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getLtipo_sum() {
		return ltipo_sum;
	}
	public void setLtipo_sum(int ltipo_sum) {
		this.ltipo_sum = ltipo_sum;
	}
	public int getLstAreaOpe() {
		return lstAreaOpe;
	}
	public void setLstAreaOpe(int lstAreaOpe) {
		this.lstAreaOpe = lstAreaOpe;
	}
	public int getLstRegional() {
		return lstRegional;
	}
	public void setLstRegional(int lstRegional) {
		this.lstRegional = lstRegional;
	}
	public int getLstRecibe() {
		return lstRecibe;
	}
	public void setLstRecibe(int lstRecibe) {
		this.lstRecibe = lstRecibe;
	}
	public String getTxt_num_recibo() {
		return txt_num_recibo;
	}
	public void setTxt_num_recibo(String txt_num_recibo) {
		this.txt_num_recibo = txt_num_recibo;
	}
	public String getTxt_fecha() {
		return txt_fecha;
	}
	public void setTxt_fecha(String txt_fecha) {
		this.txt_fecha = txt_fecha;
	}
	public Long getTxt_folio_ini() {
		return txt_folio_ini;
	}
	public void setTxt_folio_ini(Long txt_folio_ini) {
		this.txt_folio_ini = txt_folio_ini;
	}
	public Long getTxt_folio_fin() {
		return txt_folio_fin;
	}
	public void setTxt_folio_fin(Long txt_folio_fin) {
		this.txt_folio_fin = txt_folio_fin;
	}
	public Long getTxt_tot_folios() {
		return txt_tot_folios;
	}
	public void setTxt_tot_folios(Long txt_tot_folios) {
		this.txt_tot_folios = txt_tot_folios;
	}
	public String getTxt_motivo() {
		return txt_motivo;
	}
	public void setTxt_motivo(String txt_motivo) {
		this.txt_motivo = txt_motivo;
	}
	public int getIdUser() {
		return idUser;
	}
	public void setIdUser(int idUser) {
		this.idUser = idUser;
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
