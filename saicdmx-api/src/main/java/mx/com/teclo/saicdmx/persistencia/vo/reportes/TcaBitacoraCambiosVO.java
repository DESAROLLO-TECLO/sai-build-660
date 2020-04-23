package mx.com.teclo.saicdmx.persistencia.vo.reportes;

import java.util.Date;

public class TcaBitacoraCambiosVO {
	
	private Long idCambio;
	private String nombreConponente;
	private String nombreConcepto;
	private String valorOriginal;
	private String valorFinal;
	private String registroAlterado;
	private String modificadoPor;
	private Date fechaModificacion;
	private String cheked;
	
	public String getCheked() {
		return cheked;
	}

	public void setCheked(String cheked) {
		this.cheked = cheked;
	}

	public Long getIdCambio() {
		return idCambio;
	}

	public void setIdCambio(Long idCambio) {
		this.idCambio = idCambio;
	}


	public Date getFechaModificacion() {
		return fechaModificacion;
	}

	public void setFechaModificacion(Date fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}

	public String getNombreConponente() {
		return nombreConponente;
	}
	
	public void setNombreConponente(String nombreConponente) {
		this.nombreConponente = nombreConponente;
	}
	public String getNombreConcepto() {
		return nombreConcepto;
	}
	public void setNombreConcepto(String nombreConcepto) {
		this.nombreConcepto = nombreConcepto;
	}
	public String getValorOriginal() {
		return valorOriginal;
	}
	public void setValorOriginal(String valorOriginal) {
		this.valorOriginal = valorOriginal;
	}
	public String getValorFinal() {
		return valorFinal;
	}
	public void setValorFinal(String valorFinal) {
		this.valorFinal = valorFinal;
	}
	public String getRegistroAlterado() {
		return registroAlterado;
	}
	public void setRegistroAlterado(String registroAlterado) {
		this.registroAlterado = registroAlterado;
	}
	public String getModificadoPor() {
		return modificadoPor;
	}
	public void setModificadoPor(String modificadoPor) {
		this.modificadoPor = modificadoPor;
	}
}
