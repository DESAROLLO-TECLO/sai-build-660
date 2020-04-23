package mx.com.teclo.saicdmx.persistencia.vo.catalogos;

public class CrudConceptoPagoVO extends BaseCrudVO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7965471893843356885L;
	
	private Long conceptoId;
	private String conceptoCodigo;
	private String conceptoNombre;
	private Long conceptoValor;
	private Long conceptoDias;
	private Long conceptoDescuento;
	
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
}
