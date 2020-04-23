package mx.com.teclo.saicdmx.persistencia.hibernate.dto.pagos;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "DETALLE_CARGOS")
public class DetalleCargosDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private DetallePagosPrimarykey primaryKeyDetCarg;
	
	
	@Column(name = "PAG_CANTIDAD")
	private Double pagCantidad;
	
	@Column(name = "PAG_DESCUENTO")
	private Double pagDescuento;
	
	@Column(name = "PAG_ACTUALIZACION")
	private Double pagActualizacion;
	
	@Column(name = "PAG_RECARGO")
	private Double pagRecargo;
	
	@Column(name = "PAG_TOTAL")
	private Double pagTotal;
	
	@Column(name = "PAG_CONCILIADO")
	private String pagConciliado;
	
	@Column(name = "PAG_SIN_COBRO")
	private String pagSinCobro;
	
	@Column(name = "CONCEP_ID")
	private Long consepId;
	
	@Column(name = "PAG_VALOR")
	private Double pagValor;
	
	@Column(name = "INFRAC_NUM")
	private String infracNum;
	
	@Column(name = "INGR_CTRL_INT")
	private String numControlInterno;
	
	@Column(name = "INGR_RESGUARDO")
	private String ingrResguardo;
	
	@Column(name = "DET_PAGO_STATUS")
	private String detPagoStatus;
	
	@Column(name = "CREADO_POR")
	private Long creadoPor;
	
	@Column(name = "FECHA_CREACION")
	private Date fehaCreacion;
	
	@Column(name = "MODIFICADO_POR")
	private Long modificadoPor;
	
	@Column(name = "ULTIMA_MODIFICACION")
	private Date ultimaModificacion;
	
	@Column(name = "ENT_ID")
	private Long entId;
	
	@Column(name = "PAG_DOCTO_REF")
	private String pagDocRef;
	
	public Double getPagCantidad() {
		return pagCantidad;
	}
	public void setPagCantidad(Double pagCantidad) {
		this.pagCantidad = pagCantidad;
	}
	public DetallePagosPrimarykey getPrimaryKeyDetCarg() {
		return primaryKeyDetCarg;
	}
	public void setPrimaryKeyDetCarg(DetallePagosPrimarykey primaryKeyDetCarg) {
		this.primaryKeyDetCarg = primaryKeyDetCarg;
	}
	public Double getPagDescuento() {
		return pagDescuento;
	}
	public void setPagDescuento(Double pagDescuento) {
		this.pagDescuento = pagDescuento;
	}
	public Double getPagActualizacion() {
		return pagActualizacion;
	}
	public void setPagActualizacion(Double pagActualizacion) {
		this.pagActualizacion = pagActualizacion;
	}
	public Double getPagRecargo() {
		return pagRecargo;
	}
	public void setPagRecargo(Double pagRecargo) {
		this.pagRecargo = pagRecargo;
	}
	public Double getPagTotal() {
		return pagTotal;
	}
	public void setPagTotal(Double pagTotal) {
		this.pagTotal = pagTotal;
	}
	public String getPagConciliado() {
		return pagConciliado;
	}
	public void setPagConciliado(String pagConciliado) {
		this.pagConciliado = pagConciliado;
	}
	public String getPagSinCobro() {
		return pagSinCobro;
	}
	public void setPagSinCobro(String pagSinCobro) {
		this.pagSinCobro = pagSinCobro;
	}
	public Long getConsepId() {
		return consepId;
	}
	public void setConsepId(Long consepId) {
		this.consepId = consepId;
	}
	public Double getPagValor() {
		return pagValor;
	}
	public void setPagValor(Double pagValor) {
		this.pagValor = pagValor;
	}
	public String getInfracNum() {
		return infracNum;
	}
	public void setInfracNum(String infracNum) {
		this.infracNum = infracNum;
	}
	public String getNumControlInterno() {
		return numControlInterno;
	}
	public void setNumControlInterno(String numControlInterno) {
		this.numControlInterno = numControlInterno;
	}
	public String getIngrResguardo() {
		return ingrResguardo;
	}
	public void setIngrResguardo(String ingrResguardo) {
		this.ingrResguardo = ingrResguardo;
	}
	public String getDetPagoStatus() {
		return detPagoStatus;
	}
	public void setDetPagoStatus(String detPagoStatus) {
		this.detPagoStatus = detPagoStatus;
	}
	public Long getCreadoPor() {
		return creadoPor;
	}
	public void setCreadoPor(Long creadoPor) {
		this.creadoPor = creadoPor;
	}
	public Date getFehaCreacion() {
		return fehaCreacion;
	}
	public void setFehaCreacion(Date fehaCreacion) {
		this.fehaCreacion = fehaCreacion;
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
	public Long getEntId() {
		return entId;
	}
	public void setEntId(Long entId) {
		this.entId = entId;
	}
	public String getPagDocRef() {
		return pagDocRef;
	}
	public void setPagDocRef(String pagDocRef) {
		this.pagDocRef = pagDocRef;
	}

}
