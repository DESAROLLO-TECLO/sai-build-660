package mx.com.teclo.saicdmx.persistencia.vo.garantias;

public class GarantiaPorPagarVO {
	
	private Long garantiaId;
	private String garantiaDocumentoId;
	private String fechaCreacionGarantia;
	private String documentoFolio;
	private String recibida;
	private String infracNum;
	private String fechaCreacionInfraccion;
	private String estatusCancelacion;
	
	public Long getGarantiaId() {
		return garantiaId;
	}
	public void setGarantiaId(Long garantiaId) {
		this.garantiaId = garantiaId;
	}
	public String getGarantiaDocumentoId() {
		return garantiaDocumentoId;
	}
	public void setGarantiaDocumentoId(String garantiaDocumentoId) {
		this.garantiaDocumentoId = garantiaDocumentoId;
	}
	public String getFechaCreacionGarantia() {
		return fechaCreacionGarantia;
	}
	public void setFechaCreacionGarantia(String fechaCreacionGarantia) {
		this.fechaCreacionGarantia = fechaCreacionGarantia;
	}
	public String getDocumentoFolio() {
		return documentoFolio;
	}
	public void setDocumentoFolio(String documentoFolio) {
		this.documentoFolio = documentoFolio;
	}
	public String getRecibida() {
		return recibida;
	}
	public void setRecibida(String recibida) {
		this.recibida = recibida;
	}
	public String getInfracNum() {
		return infracNum;
	}
	public void setInfracNum(String infracNum) {
		this.infracNum = infracNum;
	}
	public String getFechaCreacionInfraccion() {
		return fechaCreacionInfraccion;
	}
	public void setFechaCreacionInfraccion(String fechaCreacionInfraccion) {
		this.fechaCreacionInfraccion = fechaCreacionInfraccion;
	}
	public String getEstatusCancelacion() {
		return estatusCancelacion;
	}
	public void setEstatusCancelacion(String estatusCancelacion) {
		this.estatusCancelacion = estatusCancelacion;
	}
	
}