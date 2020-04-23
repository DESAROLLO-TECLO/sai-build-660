package mx.com.teclo.saicdmx.persistencia.vo.lineadecaptura;
/**
 * 
 * @author José Castillo
 *
 */
public class BitReasignacionLineaCapturaVO {
	
	private Long	ID_LC_REASIGNA_WS;
	//----------------------------
	//Parametros Peticion
	//----------------------------
	private String	urlWebservice;
	private String	folio;
	private String	fechaInfraccion;
	private String	fechaEmision;
	private Long	dias;
	private Double	salarioMinimo;
	private Double	importe;
	private Long	derechos;
	private Integer	aplicaCondonacion;
	private String	cadenaXML;
	
	//----------------------------
	//Parametros Respuesta
	//----------------------------
	private String	folioRes;
	private String	fechaInfraccionRes;
	private Double	salarioMinimoRes;
	private Long	diasRes;
	private Double	importeRes;
	private Double	descuentoRes;
	private Double	recargosRes;
	private Double	totalRes;
	private String	vigenciaRes;
	private String	lineacapturaRes;
	private String	lineacapturaCBRes;
	private Long	errorRes;
	private String	errorDescripcionRes;
	private String	cadenaXMLRes;
	
	private Long 	idUsuario;
	private Integer	idOrigen;
	
	public Long getPKAI014B_LC_R() {
		return ID_LC_REASIGNA_WS;
	}
	public void setPKAI014B_LC_R(Long pKAI014B_LC_R) {
		this.ID_LC_REASIGNA_WS = pKAI014B_LC_R;
	}
	public String getUrlWebservice() {
		return urlWebservice;
	}
	public void setUrlWebservice(String urlWebservice) {
		this.urlWebservice = urlWebservice;
	}
	public String getFolio() {
		return folio;
	}
	public void setFolio(String folio) {
		this.folio = folio;
	}
	public String getFechaInfraccion() {
		return fechaInfraccion;
	}
	public void setFechaInfraccion(String fechaInfraccion) {
		this.fechaInfraccion = fechaInfraccion;
	}
	public String getFechaEmision() {
		return fechaEmision;
	}
	public void setFechaEmision(String fechaEmision) {
		this.fechaEmision = fechaEmision;
	}
	public Long getDias() {
		return dias;
	}
	public void setDias(Long dias) {
		this.dias = dias;
	}
	public Double getSalarioMinimo() {
		return salarioMinimo;
	}
	public void setSalarioMinimo(Double salarioMinimo) {
		this.salarioMinimo = salarioMinimo;
	}
	public Double getImporte() {
		return importe;
	}
	public void setImporte(Double importe) {
		this.importe = importe;
	}
	public Long getDerechos() {
		return derechos;
	}
	public void setDerechos(Long derechos) {
		this.derechos = derechos;
	}
	public Integer getAplicaCondonacion() {
		return aplicaCondonacion;
	}
	public void setAplicaCondonacion(Integer aplicaCondonacion) {
		this.aplicaCondonacion = aplicaCondonacion;
	}
	public String getCadenaXML() {
		return cadenaXML;
	}
	public void setCadenaXML(String cadenaXML) {
		this.cadenaXML = cadenaXML;
	}
	public String getFolioRes() {
		return folioRes;
	}
	public void setFolioRes(String folioRes) {
		this.folioRes = folioRes;
	}
	public String getFechaInfraccionRes() {
		return fechaInfraccionRes;
	}
	public void setFechaInfraccionRes(String fechaInfraccionRes) {
		this.fechaInfraccionRes = fechaInfraccionRes;
	}
	public Double getSalarioMinimoRes() {
		return salarioMinimoRes;
	}
	public void setSalarioMinimoRes(Double salarioMinimoRes) {
		this.salarioMinimoRes = salarioMinimoRes;
	}
	public Long getDiasRes() {
		return diasRes;
	}
	public void setDiasRes(Long diasRes) {
		this.diasRes = diasRes;
	}
	public Double getImporteRes() {
		return importeRes;
	}
	public void setImporteRes(Double importeRes) {
		this.importeRes = importeRes;
	}
	public Double getDescuentoRes() {
		return descuentoRes;
	}
	public void setDescuentoRes(Double descuentoRes) {
		this.descuentoRes = descuentoRes;
	}
	public Double getRecargosRes() {
		return recargosRes;
	}
	public void setRecargosRes(Double recargosRes) {
		this.recargosRes = recargosRes;
	}
	public Double getTotalRes() {
		return totalRes;
	}
	public void setTotalRes(Double totalRes) {
		this.totalRes = totalRes;
	}
	public String getVigenciaRes() {
		return vigenciaRes;
	}
	public void setVigenciaRes(String vigenciaRes) {
		this.vigenciaRes = vigenciaRes;
	}
	public String getLineacapturaRes() {
		return lineacapturaRes;
	}
	public void setLineacapturaRes(String lineacapturaRes) {
		this.lineacapturaRes = lineacapturaRes;
	}
	public String getLineacapturaCBRes() {
		return lineacapturaCBRes;
	}
	public void setLineacapturaCBRes(String lineacapturaCBRes) {
		this.lineacapturaCBRes = lineacapturaCBRes;
	}
	public Long getErrorRes() {
		return errorRes;
	}
	public void setErrorRes(Long errorRes) {
		this.errorRes = errorRes;
	}
	public String getErrorDescripcionRes() {
		return errorDescripcionRes;
	}
	public void setErrorDescripcionRes(String errorDescripcionRes) {
		this.errorDescripcionRes = errorDescripcionRes;
	}
	public String getCadenaXMLRes() {
		return cadenaXMLRes;
	}
	public void setCadenaXMLRes(String cadenaXMLRes) {
		this.cadenaXMLRes = cadenaXMLRes;
	}
	public Long getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}
	public Integer getIdOrigen() {
		return idOrigen;
	}
	public void setIdOrigen(Integer idOrigen) {
		this.idOrigen = idOrigen;
	}
}
