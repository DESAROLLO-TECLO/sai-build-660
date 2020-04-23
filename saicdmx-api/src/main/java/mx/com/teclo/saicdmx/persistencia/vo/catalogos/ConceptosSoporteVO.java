package mx.com.teclo.saicdmx.persistencia.vo.catalogos;

public class ConceptosSoporteVO {
	private Long conceptoId; 
	private String conceptoNombre;
	private String descripcion;
	
	/**
	 * @return the conceptoId
	 */
	public Long getConceptoId() {
		return conceptoId;
	}
	/**
	 * @param conceptoId the conceptoId to set
	 */
	public void setConceptoId(Long conceptoId) {
		this.conceptoId = conceptoId;
	}
	/**
	 * @return the conceptoNombre
	 */
	public String getConceptoNombre() {
		return conceptoNombre;
	}
	/**
	 * @param conceptoNombre the conceptoNombre to set
	 */
	public void setConceptoNombre(String conceptoNombre) {
		this.conceptoNombre = conceptoNombre;
	}
	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}
	/**
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}
