package mx.com.teclo.saicdmx.persistencia.hibernate.dto.pagos;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.CajaDTO;
@Entity
@Table(name = "PAGOS")
public class PagoDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private PagosPrimaryKeyDTO pagosId;
	
	@Column(name = "PAG_VALOR_TOTAL")
	private Long pagValorTotal;
	@Column(name = "PAG_TOTAL")
	private Long pagTotal;
	@Column(name = "TRAN_CAJA_ID")
	private Long tranCajaId;
	@Column(name = "TRAN_ID")
	private Long tranId;
	@Column(name = "PAG_CONCILIADO")
	private String pagConciliado;
	@Column(name = "PAG_FECHA")
	private Date pagFecha;
	@Column(name = "PAG_REFERENCIA")
	private String pagReferencia;
	@Column(name = "PAG_FECH_MOD")
	private Date pagFechMod;
	@Column(name = "CREADO_POR")
	private String creadoPor;
	@Column(name = "FECHA_CREACION")
	private Date fechaCreacion;
	@Column(name = "MODIFICADO_POR")
	private Long modificadoPor;
	@Column(name = "ULTIMA_MODIFICACION")
	private Date ultimaModificacion;
	@Column(name = "INFRAC_NUM")
	private String infracNum;
	@Column(name = "INGR_RESGUARDO")
	private String ingrResguardo;
	@Column(name = "INGR_CTRL_INT")
	private String ingrCtrlInt;
	@Column(name = "PAGO_ORIGEN")
	private String pagoOrigen;
	@Column(name = "INF_FECHA_ACTUALIZA")
	private Date infFechaActualiza;
	@Column(name = "INF_PLACA")
	private String infPlaca;
	@Column(name = "INF_FMOVE")
	private Date infFmove;
	@Column(name = "INF_HORA_PAGO")
	private Date infHoraPago;
	@Column(name = "INF_ID_PAGOS")
	private String inf_id_pagos;
	@Column(name = "PAG_STATUS")
	private String pagStatus;
	@Column(name = "AUTORIZACION_CANCELACION")
	private String autorizacionCancelacion;
	@Column(name = "FECHA_CANCELACION")
	private Date fechaCancelacion;
	@Column(name = "USUARIO_CANCELA")
	private Long usuarioCancela;
	@Column(name = "USUARIO_AUTORIZA")
	private Long usuarioAutoriza;
	
	
	public PagosPrimaryKeyDTO getPagosId() {
		return pagosId;
	}
	public void setPagosId(PagosPrimaryKeyDTO pagosId) {
		this.pagosId = pagosId;
	}
	public Long getPagValorTotal() {
		return pagValorTotal;
	}
	public void setPagValorTotal(Long pagValorTotal) {
		this.pagValorTotal = pagValorTotal;
	}
	public Long getPagTotal() {
		return pagTotal;
	}
	public void setPagTotal(Long pagTotal) {
		this.pagTotal = pagTotal;
	}
	public Long getTranCajaId() {
		return tranCajaId;
	}
	public void setTranCajaId(Long tranCajaId) {
		this.tranCajaId = tranCajaId;
	}
	public Long getTranId() {
		return tranId;
	}
	public void setTranId(Long tranId) {
		this.tranId = tranId;
	}
	public String getPagConciliado() {
		return pagConciliado;
	}
	public void setPagConciliado(String pagConciliado) {
		this.pagConciliado = pagConciliado;
	}
	public Date getPagFecha() {
		return pagFecha;
	}
	public void setPagFecha(Date pagFecha) {
		this.pagFecha = pagFecha;
	}
	public String getPagReferencia() {
		return pagReferencia;
	}
	public void setPagReferencia(String pagReferencia) {
		this.pagReferencia = pagReferencia;
	}
	public Date getPagFechMod() {
		return pagFechMod;
	}
	public void setPagFechMod(Date pagFechMod) {
		this.pagFechMod = pagFechMod;
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
	public String getInfracNum() {
		return infracNum;
	}
	public void setInfracNum(String infracNum) {
		this.infracNum = infracNum;
	}
	public String getIngrResguardo() {
		return ingrResguardo;
	}
	public void setIngrResguardo(String ingrResguardo) {
		this.ingrResguardo = ingrResguardo;
	}
	public String getIngrCtrlInt() {
		return ingrCtrlInt;
	}
	public void setIngrCtrlInt(String ingrCtrlInt) {
		this.ingrCtrlInt = ingrCtrlInt;
	}
	public String getPagoOrigen() {
		return pagoOrigen;
	}
	public void setPagoOrigen(String pagoOrigen) {
		this.pagoOrigen = pagoOrigen;
	}
	public Date getInfFechaActualiza() {
		return infFechaActualiza;
	}
	public void setInfFechaActualiza(Date infFechaActualiza) {
		this.infFechaActualiza = infFechaActualiza;
	}
	public String getInfPlaca() {
		return infPlaca;
	}
	public void setInfPlaca(String infPlaca) {
		this.infPlaca = infPlaca;
	}
	public Date getInfFmove() {
		return infFmove;
	}
	public void setInfFmove(Date infFmove) {
		this.infFmove = infFmove;
	}
	public Date getInfHoraPago() {
		return infHoraPago;
	}
	public void setInfHoraPago(Date infHoraPago) {
		this.infHoraPago = infHoraPago;
	}
	public String getInf_id_pagos() {
		return inf_id_pagos;
	}
	public void setInf_id_pagos(String inf_id_pagos) {
		this.inf_id_pagos = inf_id_pagos;
	}
	public String getPagStatus() {
		return pagStatus;
	}
	public void setPagStatus(String pagStatus) {
		this.pagStatus = pagStatus;
	}
	public String getAutorizacionCancelacion() {
		return autorizacionCancelacion;
	}
	public void setAutorizacionCancelacion(String autorizacionCancelacion) {
		this.autorizacionCancelacion = autorizacionCancelacion;
	}
	public Date getFechaCancelacion() {
		return fechaCancelacion;
	}
	public void setFechaCancelacion(Date fechaCancelacion) {
		this.fechaCancelacion = fechaCancelacion;
	}
	public Long getUsuarioCancela() {
		return usuarioCancela;
	}
	public void setUsuarioCancela(Long usuarioCancela) {
		this.usuarioCancela = usuarioCancela;
	}
	public Long getUsuarioAutoriza() {
		return usuarioAutoriza;
	}
	public void setUsuarioAutoriza(Long usuarioAutoriza) {
		this.usuarioAutoriza = usuarioAutoriza;
	}
	
	

}
