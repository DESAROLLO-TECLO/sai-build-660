package mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="CONCEPTO_PAGOS")
public class ConceptoPagoDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4695306875786674256L;
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "CONCEP_ID", unique = true, nullable = false)
	private Long conceptoId;
	
	@Column(name = "PERFIL_ID_PAGO")
	private Long perfilIdPago;
	
	@Column(name = "CONCEP_COD")
	private String conceptoCodigo;
	
	@Column(name = "CONCEP_NOMBRE")
	private String conceptoNombre;
	
	@Column(name = "CONCEP_VALOR")
	private Long conceptoValor;
	
	@Column(name = "CONCEP_DIAS")
	private Long conceptoDias;
	
	@Column(name = "CONCEP_DESCUENTO")
	private Long conceptoDescuento;
	
	@Column(name = "CONCEP_STATUS")
	private String conceptoStatus;
	
	@Column(name = "CREADO_POR")
	private Long creadoPor;
	
	@Column(name = "FECHA_CREACION")
	private Date fechaCreacion;
	
	@Column(name = "MODIFICADO_POR")
	private Long modificadoPor;
	
	@Column(name = "ULTIMA_MODIFICACION")
	private Date ultimaModificacion;
	
	public Long getConceptoId() {
		return conceptoId;
	}
	public void setConceptoId(Long conceptoId) {
		this.conceptoId = conceptoId;
	}
	public Long getPerfilIdPago() {
		return perfilIdPago;
	}
	public void setPerfilIdPago(Long perfilIdPago) {
		this.perfilIdPago = perfilIdPago;
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
	public Long getCreadoPor() {
		return creadoPor;
	}
	public void setCreadoPor(Long creadoPor) {
		this.creadoPor = creadoPor;
	}
	public Date getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	public Long getModificadoPor() {
		return modificadoPor;
	}
	public void setModificadoPor(Long modificadoPor) {
		this.modificadoPor = modificadoPor;
	}
	public Date getUltimaModificacion() {
		return ultimaModificacion;
	}
	public void setUltimaModificacion(Date ultimaModificacion) {
		this.ultimaModificacion = ultimaModificacion;
	}
}
