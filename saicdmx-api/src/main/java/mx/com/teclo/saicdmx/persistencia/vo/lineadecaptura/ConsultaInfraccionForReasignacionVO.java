package mx.com.teclo.saicdmx.persistencia.vo.lineadecaptura;

import java.math.BigDecimal;

/**
 * 
 * @author Jesus Gutierrez
 *
 */
public class ConsultaInfraccionForReasignacionVO {
	
	private String folio;
	private String fechaInfraccion;
	private String fechaEmision;;
	private Long dias;
	private Double salarioMinimo;
	private Double importe;
	private Long derechos;
	
	private Integer aplicaCondonacion;
	private Integer totalReasignaciones;
	private Integer porcentajeCondonacion;
	private Integer porcentajeDescuento1;
	private Integer porcentajeDescuento2;
	private String  cadenaXML;
	private Long 	idUsuario;
	private String  proxiURL;
	/*private String usuario;
	private String password;
	private BigDecimal clave;*/
	
	
	/**
	 * @return the folio
	 */
	public String getFolio() {
		return folio;
	}
	/**
	 * @param folio the folio to set
	 */
	public void setFolio(String folio) {
		this.folio = folio;
	}
	/**
	 * @return the fechaInfraccion
	 */
	public String getFechaInfraccion() {
		return fechaInfraccion;
	}
	/**
	 * @param fechaInfraccion the fechaInfraccion to set
	 */
	public void setFechaInfraccion(String fechaInfraccion) {
		this.fechaInfraccion = fechaInfraccion;
	}
	/**
	 * @return the fechaEmision
	 */
	public String getFechaEmision() {
		return fechaEmision;
	}
	/**
	 * @param fechaEmision the fechaEmision to set
	 */
	public void setFechaEmision(String fechaEmision) {
		this.fechaEmision = fechaEmision;
	}
	/**
	 * @return the dias
	 */
	public Long getDias() {
		return dias;
	}
	/**
	 * @param dias the dias to set
	 */
	public void setDias(Long dias) {
		this.dias = dias;
	}
	/**
	 * @return the salarioMinimo
	 */
	public Double getSalarioMinimo() {
		return salarioMinimo;
	}
	/**
	 * @param salarioMinimo the salarioMinimo to set
	 */
	public void setSalarioMinimo(Double salarioMinimo) {
		this.salarioMinimo = salarioMinimo;
	}
	/**
	 * @return the importe
	 */
	public Double getImporte() {
		return importe;
	}
	/**
	 * @param importe the importe to set
	 */
	public void setImporte(Double importe) {
		this.importe = importe;
	}
	/**
	 * @return the derechos
	 */
	public Long getDerechos() {
		return derechos;
	}
	/**
	 * @param derechos the derechos to set
	 */
	public void setDerechos(Long derechos) {
		this.derechos = derechos;
	}
	/**
	 * @return the aplicaCondonacion
	 */
	public Integer getAplicaCondonacion() {
		return aplicaCondonacion;
	}
	/**
	 * @param aplicaCondonacion the aplicaCondonacion to set
	 */
	public void setAplicaCondonacion(Integer aplicaCondonacion) {
		this.aplicaCondonacion = aplicaCondonacion;
	}
	/**
	 * @return the totalReasignaciones
	 */
	public Integer getTotalReasignaciones() {
		return totalReasignaciones;
	}
	/**
	 * @param totalReasignaciones the totalReasignaciones to set
	 */
	public void setTotalReasignaciones(Integer totalReasignaciones) {
		this.totalReasignaciones = totalReasignaciones;
	}
	/**
	 * @return the porcentajeCondonacion
	 */
	public Integer getPorcentajeCondonacion() {
		return porcentajeCondonacion;
	}
	/**
	 * @param porcentajeCondonacion the porcentajeCondonacion to set
	 */
	public void setPorcentajeCondonacion(Integer porcentajeCondonacion) {
		this.porcentajeCondonacion = porcentajeCondonacion;
	}
	/**
	 * @return the porcentajeDescuento1
	 */
	public Integer getPorcentajeDescuento1() {
		return porcentajeDescuento1;
	}
	/**
	 * @param porcentajeDescuento1 the porcentajeDescuento1 to set
	 */
	public void setPorcentajeDescuento1(Integer porcentajeDescuento1) {
		this.porcentajeDescuento1 = porcentajeDescuento1;
	}
	/**
	 * @return the porcentajeDescuento2
	 */
	public Integer getPorcentajeDescuento2() {
		return porcentajeDescuento2;
	}
	/**
	 * @param porcentajeDescuento2 the porcentajeDescuento2 to set
	 */
	public void setPorcentajeDescuento2(Integer porcentajeDescuento2) {
		this.porcentajeDescuento2 = porcentajeDescuento2;
	}
	public String getCadenaXML() {
		return cadenaXML;
	}
	public void setCadenaXML(String cadenaXML) {
		this.cadenaXML = cadenaXML;
	}
	public Long getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}
	public String getProxiURL() {
		return proxiURL;
	}
	public void setProxiURL(String proxiURL) {
		this.proxiURL = proxiURL;
	}

	/**
	 * @return the usuario
	 
	public String getUsuario() {
		return usuario;
	}*/

	/**
	 * @param usuario the usuario to set
	 
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}*/

	/**
	 * @return the password
	 
	public String getPassword() {
		return password;
	}*/

	/**
	 * @param password the password to set
	 
	public void setPassword(String password) {
		this.password = password;
	}*/

	/**
	 * @return the clave
	 
	public BigDecimal getClave() {
		return clave;
	}*/

	/**
	 * @param clave the clave to set
	 
	public void setClave(BigDecimal clave) {
		this.clave = clave;
	}*/
}