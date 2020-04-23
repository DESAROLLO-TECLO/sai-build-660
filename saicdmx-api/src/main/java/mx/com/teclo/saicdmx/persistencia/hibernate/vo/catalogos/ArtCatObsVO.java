package mx.com.teclo.saicdmx.persistencia.hibernate.vo.catalogos;

public class ArtCatObsVO {
	
	private Integer idObservacion;
	
	private Long artId;
	
	private String consecutivo;
	
	private String descripcion;
	
	private Integer valido;

	/**
	 * @return the idObservacion
	 */
	public Integer getIdObservacion() {
		return idObservacion;
	}

	/**
	 * @param idObservacion the idObservacion to set
	 */
	public void setIdObservacion(Integer idObservacion) {
		this.idObservacion = idObservacion;
	}

	/**
	 * @return the artId
	 */
	public Long getArtId() {
		return artId;
	}

	/**
	 * @param artId the artId to set
	 */
	public void setArtId(Long artId) {
		this.artId = artId;
	}

	/**
	 * @return the consecutivo
	 */
	public String getConsecutivo() {
		return consecutivo;
	}

	/**
	 * @param consecutivo the consecutivo to set
	 */
	public void setConsecutivo(String consecutivo) {
		this.consecutivo = consecutivo;
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

	/**
	 * @return the valido
	 */
	public Integer getValido() {
		return valido;
	}

	/**
	 * @param valido the valido to set
	 */
	public void setValido(Integer valido) {
		this.valido = valido;
	}
}
