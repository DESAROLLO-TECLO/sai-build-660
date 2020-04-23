package mx.com.teclo.saicdmx.persistencia.hibernate.vo.catalogos;

import java.io.Serializable;

public class GarantiasCatDocumentosVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3632781372860398079L;
	
	private Long garantiaCatDocumentoId;
	private String garantiaCatNombre;
	private Integer garantiaCatActivo;
	
	public Long getGarantiaCatDocumentoId() {
		return garantiaCatDocumentoId;
	}
	public void setGarantiaCatDocumentoId(Long garantiaCatDocumentoId) {
		this.garantiaCatDocumentoId = garantiaCatDocumentoId;
	}
	public String getGarantiaCatNombre() {
		return garantiaCatNombre;
	}
	public void setGarantiaCatNombre(String garantiaCatNombre) {
		this.garantiaCatNombre = garantiaCatNombre;
	}
	public Integer getGarantiaCatActivo() {
		return garantiaCatActivo;
	}
	public void setGarantiaCatActivo(Integer garantiaCatActivo) {
		this.garantiaCatActivo = garantiaCatActivo;
	}
	
}
