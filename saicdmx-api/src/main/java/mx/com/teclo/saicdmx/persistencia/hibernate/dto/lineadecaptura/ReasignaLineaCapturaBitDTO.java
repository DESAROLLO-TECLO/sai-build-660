package mx.com.teclo.saicdmx.persistencia.hibernate.dto.lineadecaptura;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="TAI014B_LC_REASIGNA_WS")
public class ReasignaLineaCapturaBitDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5220780246632732212L;

	    
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "PKAI014B_LC_R", unique = true, nullable = false)
	private Long idLcReasignaWS;
	
	@Column(name = "TX_URL_WEBSERVICE")
	private String urlWebService;
	
	@Column(name = "NU_FOLIO")
	private String folio;

	@Column(name = "FH_INFRACCION")
	private Date fechaInfraccion;

	@Column(name = "FH_EMISION")
	private Date fechaEmision;
	              
	@Column(name = "NU_DIAS")
	private Integer numDias;
	
	@Column(name = "NU_SALARIO_MINIMO")
	private BigDecimal salarioMinimo;
	
	@Column(name = "NU_IMPORTE")
	private BigDecimal importe;
	
	@Column(name = "NU_DERECHOS")
	private Long derechos;
			
	@Column(name = "NU_APLICA_CONDONACION")
	private Integer aplicaCondonacion;
		
	@Column(name = "TX_CADENA_XML")
	private String cadenaXML;
		 
	@Column(name = "ST_ACTIVO")
	private Integer estatusActivo;
	 
	@Column(name = "ID_USR_CREACION")	
	private Long usuarioCreacion;
	
	@Column(name = "FH_CREACION")	
	private Date fechaCreacion;

	@Column(name = "ID_USR_MODIFICA")	
	private Long usuarioModificacion;

	@Column(name = "FH_MODIFICACION")	
	private Date fechaModificacion;

	public Long getIdLcReasignaWS() {
		return idLcReasignaWS;
	}

	public void setIdLcReasignaWS(Long idLcReasignaWS) {
		this.idLcReasignaWS = idLcReasignaWS;
	}

	public String getUrlWebService() {
		return urlWebService;
	}

	public void setUrlWebService(String urlWebService) {
		this.urlWebService = urlWebService;
	}

	public String getFolio() {
		return folio;
	}

	public void setFolio(String folio) {
		this.folio = folio;
	}

	public Date getFechaInfraccion() {
		return fechaInfraccion;
	}

	public void setFechaInfraccion(Date fechaInfraccion) {
		this.fechaInfraccion = fechaInfraccion;
	}

	public Date getFechaEmision() {
		return fechaEmision;
	}

	public void setFechaEmision(Date fechaEmision) {
		this.fechaEmision = fechaEmision;
	}

	public Integer getNumDias() {
		return numDias;
	}

	public void setNumDias(Integer numDias) {
		this.numDias = numDias;
	}

	public BigDecimal getSalarioMinimo() {
		return salarioMinimo;
	}

	public void setSalarioMinimo(BigDecimal salarioMinimo) {
		this.salarioMinimo = salarioMinimo;
	}

	public BigDecimal getImporte() {
		return importe;
	}

	public void setImporte(BigDecimal importe) {
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

	public Integer getEstatusActivo() {
		return estatusActivo;
	}

	public void setEstatusActivo(Integer estatusActivo) {
		this.estatusActivo = estatusActivo;
	}

	public Long getUsuarioCreacion() {
		return usuarioCreacion;
	}

	public void setUsuarioCreacion(Long usuarioCreacion) {
		this.usuarioCreacion = usuarioCreacion;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Long getUsuarioModificacion() {
		return usuarioModificacion;
	}

	public void setUsuarioModificacion(Long usuarioModificacion) {
		this.usuarioModificacion = usuarioModificacion;
	}

	public Date getFechaModificacion() {
		return fechaModificacion;
	}

	public void setFechaModificacion(Date fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}	

}
