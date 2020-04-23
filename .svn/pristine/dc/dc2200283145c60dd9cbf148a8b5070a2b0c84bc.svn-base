package mx.com.teclo.saicdmx.persistencia.hibernate.vo.catalogos;

import java.io.Serializable;

public class ConceptoPagoVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8491386791148942805L;
	
	private Long conceptoId;
	private String conceptoCodigo;
	private String conceptoNombre;
	private Long conceptoValor;
	private Long conceptoDias;
	private Long conceptoDescuento;
	private String conceptoStatus;
	
	public Long getConceptoId() {
		return conceptoId;
	}
	public void setConceptoId(Long conceptoId) {
		this.conceptoId = conceptoId;
	}
	public String getConceptoCodigo() {
		return conceptoCodigo;
	}
	public void setConceptoCodigo(String conceptoCodigo) {
		this.conceptoCodigo = conceptoCodigo;
	}
	public String getConceptoNombre() {
		return conceptoNombre;
	}
	public void setConceptoNombre(String conceptoNombre) {
		this.conceptoNombre = conceptoNombre;
	}
	public Long getConceptoValor() {
		return conceptoValor;
	}
	public void setConceptoValor(Long conceptoValor) {
		this.conceptoValor = conceptoValor;
	}
	public Long getConceptoDias() {
		return conceptoDias;
	}
	public void setConceptoDias(Long conceptoDias) {
		this.conceptoDias = conceptoDias;
	}
	public Long getConceptoDescuento() {
		return conceptoDescuento;
	}
	public void setConceptoDescuento(Long conceptoDescuento) {
		this.conceptoDescuento = conceptoDescuento;
	}
	public String getConceptoStatus() {
		return conceptoStatus;
	}
	public void setConceptoStatus(String conceptoStatus) {
		this.conceptoStatus = conceptoStatus;
	}
	public String getStatusDesc() {
		return this.getConceptoStatus().equals("A") ? "Activo" : "Cancelado";
	}
}
