package mx.com.teclo.saicdmx.persistencia.vo.garantias;

public class CatalogoDocumentosGarantiaVO {
	private Integer documentoId;
	private String nombre;
	private Boolean activo;
	private Integer contadorDoc = new Integer(0);
	
	public Integer getDocumentoId() {
		return documentoId;
	}
	public void setDocumentoId(Integer documentoId) {
		this.documentoId = documentoId;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public Boolean getActivo() {
		return activo;
	}
	public void setActivo(Boolean activo) {
		this.activo = activo;
	}
	public Integer getContadorDoc() {
		return contadorDoc;
	}
	public void setContadorDoc(Integer contadorDoc) {
		this.contadorDoc = contadorDoc;
	}
	
	

}
