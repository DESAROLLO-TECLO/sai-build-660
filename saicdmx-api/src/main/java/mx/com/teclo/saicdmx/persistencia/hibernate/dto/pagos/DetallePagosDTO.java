package mx.com.teclo.saicdmx.persistencia.hibernate.dto.pagos;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "DETALLE_PAGO")
public class DetallePagosDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private DetallePagosPrimarykey primaryKeyDetPag;
	
	@Column(name = "PAG_CANTIDAD")
	private Double pagCantidad;
	@Column(name = "PAG_LOCAL")
	private String pagLocal;
	@Column(name = "PAG_DOCTO_REF")
	private String pagDocReferen;
	@Column(name = "PAG_CONCILIADO")
	private String pagConciliado;
	@Column(name = "ENT_ID")
	private Long entId;
	@Column(name = "PAG_VALOR")
	private Double pagValor;
	@Column(name = "DET_PAGO_STATUS")
	private String detPagStatus;
	@Column(name = "CREADO_POR")
	private String creadoPor;
	@Column(name = "FECHA_CREACION")
	private Date fechaCreacion;
	@Column(name = "MODIFICADO_POR")
	private Long modificadoPor;
	@Column(name = "ULTIMA_MODIFICACION")
	private Date ultimaModificacion;
	
	
	
	public Double getPagCantidad() {
		return pagCantidad;
	}
	public void setPagCantidad(Double pagCantidad) {
		this.pagCantidad = pagCantidad;
	}
	public DetallePagosPrimarykey getPrimaryKeyDetPag() {
		return primaryKeyDetPag;
	}
	public void setPrimaryKeyDetPag(DetallePagosPrimarykey primaryKeyDetPag) {
		this.primaryKeyDetPag = primaryKeyDetPag;
	}
	public String getPagLocal() {
		return pagLocal;
	}
	public void setPagLocal(String pagLocal) {
		this.pagLocal = pagLocal;
	}
	public String getPagDocReferen() {
		return pagDocReferen;
	}
	public void setPagDocReferen(String pagDocReferen) {
		this.pagDocReferen = pagDocReferen;
	}
	public String getPagConciliado() {
		return pagConciliado;
	}
	public void setPagConciliado(String pagConciliado) {
		this.pagConciliado = pagConciliado;
	}
	public Long getEntId() {
		return entId;
	}
	public void setEntId(Long entId) {
		this.entId = entId;
	}
	public Double getPagValor() {
		return pagValor;
	}
	public void setPagValor(Double pagValor) {
		this.pagValor = pagValor;
	}
	public String getDetPagStatus() {
		return detPagStatus;
	}
	public void setDetPagStatus(String detPagStatus) {
		this.detPagStatus = detPagStatus;
	}
	public String getCreadoPor() {
		return creadoPor;
	}
	public void setCreadoPor(String creadoPor) {
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
