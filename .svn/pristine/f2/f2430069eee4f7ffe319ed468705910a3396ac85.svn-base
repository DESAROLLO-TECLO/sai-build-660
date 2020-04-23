package mx.com.teclo.saicdmx.persistencia.hibernate.vo.catalogos;

import java.io.Serializable;

public class DenominacionVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2562803842154223290L;
	
	private Long denominacionId;
	private String denominacionCodigo;
	private String denominacionNombre;
	private Long denominacionValor;
	private String denominacionStatus;
	private String denominacionTipo;
	
	public Long getDenominacionId() {
		return denominacionId;
	}
	public void setDenominacionId(Long denominacionId) {
		this.denominacionId = denominacionId;
	}
	public String getDenominacionCodigo() {
		return denominacionCodigo;
	}
	public void setDenominacionCodigo(String denominacionCodigo) {
		this.denominacionCodigo = denominacionCodigo;
	}
	public String getDenominacionNombre() {
		return denominacionNombre;
	}
	public void setDenominacionNombre(String denominacionNombre) {
		this.denominacionNombre = denominacionNombre;
	}
	public Long getDenominacionValor() {
		return denominacionValor;
	}
	public void setDenominacionValor(Long denominacionValor) {
		this.denominacionValor = denominacionValor;
	}
	public String getDenominacionStatus() {
		return denominacionStatus;
	}
	public void setDenominacionStatus(String denominacionStatus) {
		this.denominacionStatus = denominacionStatus;
	}
	public String getDenominacionTipo() {
		return denominacionTipo;
	}
	public void setDenominacionTipo(String denominacionTipo) {
		this.denominacionTipo = denominacionTipo;
	}
	public String getStatusDesc() {
		return this.getDenominacionStatus().equals("A") ? "Activo" : "Cancelado";
	}
}
