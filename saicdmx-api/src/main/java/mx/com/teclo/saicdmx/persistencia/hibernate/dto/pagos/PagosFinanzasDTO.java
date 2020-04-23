package mx.com.teclo.saicdmx.persistencia.hibernate.dto.pagos;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "PAGOS_FINANZAS")
public class PagosFinanzasDTO implements Serializable {

	private static final long serialVersionUID = 1717583846356880200L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_pagos_finanzas")
	@SequenceGenerator(name = "seq_pagos_finanzas", sequenceName = "SEQ_PAGOS_FINANZAS", allocationSize=1)
	@Column(name = "PAGOS_FINANZAS_ID", nullable = false, unique = true)
	private Long pagosFinanzasId;
	@Column(name = "INFRAC_NUM")
	private String infracNum;
	@Column(name = "INFRAC_PLACA")
	private String infracPlaca;
	@Column(name = "PAGO_FECHA")
	private Date pagoFecha;
	@Column(name = "PAGO_MONTO")
	private BigDecimal pagoMonto;
	@Column(name = "CAJA", length = 10)
	private Long caja;
	@Column(name = "PARTIDA")
	private Long partida;
	@Column(name = "ATL")
	private String atl;
	@Column(name = "LINEA_CAPTURA")
	private String lineaCaptura;
	@Column(name = "INFRAC_ENCONTRADA")
	private Boolean infracEncontrada;
	@Column(name = "PROCESADO")
	private String procesado;
	@Column(name = "DUPLICADO")
	private Boolean duplicado;
	@Column(name = "FECHA_CREACION", insertable = false)
	private Date fechaCreacion;
	@Column(name = "ULTIMA_MODIFICACION", insertable = false)
	private Date ultimaModificacion;
	@Column(name = "FM_WS_RESULTADO")
	private String wsResultado;
	@Column(name = "FM_WS_ERROR")
	private String wsError;
	@Column(name = "VALIDO")
	private Boolean valido;
	
	@Column(name = "FECHA_CANCELACION")
	private Date fechaCancelacion;
	
	@Column(name = "MOTIVO_CANCELACION")
	private String motivoCancelacion;
	
	@Column(name = "ST_PAGO")
	private Integer estatusPago;
	
	@Column(name = "CONCEPTO")
	private Integer concepto;
	
	@Column(name = "PROCESO_ID")
	private Integer procesoId;
		
	@Column(name = "MONTO_SISTEMA")
	private BigDecimal montoSistema;
	
	/**
	 * @return the wsError
	 */
	public String getWsError() {
		return wsError;
	}

	/**
	 * @param wsError the wsError to set
	 */
	public void setWsError(String wsError) {
		this.wsError = wsError;
	}

	/**
	 * @return the wsResultado
	 */
	public String getWsResultado() {
		return wsResultado;
	}

	/**
	 * @param wsResultado the wsResultado to set
	 */
	public void setWsResultado(String wsResultado) {
		this.wsResultado = wsResultado;
	}

	/**
	 * @return the infracEncontrada
	 */
	public Boolean getInfracEncontrada() {
		return infracEncontrada;
	}

	/**
	 * @param infracEncontrada the infracEncontrada to set
	 */
	public void setInfracEncontrada(Boolean infracEncontrada) {
		this.infracEncontrada = infracEncontrada;
	}

	/**
	 * @return the pagosFinanzasId
	 */
	public Long getPagosFinanzasId() {
		return pagosFinanzasId;
	}

	/**
	 * @param pagosFinanzasId
	 *            the pagosFinanzasId to set
	 */
	public void setPagosFinanzasId(Long pagosFinanzasId) {
		this.pagosFinanzasId = pagosFinanzasId;
	}

	/**
	 * @return the infracNum
	 */
	public String getInfracNum() {
		return infracNum;
	}

	/**
	 * @param infracNum
	 *            the infracNum to set
	 */
	public void setInfracNum(String infracNum) {
		this.infracNum = infracNum;
	}

	/**
	 * @return the infracPlaca
	 */
	public String getInfracPlaca() {
		return infracPlaca;
	}

	/**
	 * @param infracPlaca
	 *            the infracPlaca to set
	 */
	public void setInfracPlaca(String infracPlaca) {
		this.infracPlaca = infracPlaca;
	}

	/**
	 * @return the pagoFecha
	 */
	public Date getPagoFecha() {
		return pagoFecha;
	}

	/**
	 * @param pagoFecha
	 *            the pagoFecha to set
	 */
	public void setPagoFecha(Date pagoFecha) {
		this.pagoFecha = pagoFecha;
	}

	/**
	 * @return the pagoMonto
	 */
	public BigDecimal getPagoMonto() {
		return pagoMonto;
	}

	/**
	 * @param pagoMonto
	 *            the pagoMonto to set
	 */
	public void setPagoMonto(BigDecimal pagoMonto) {
		this.pagoMonto = pagoMonto;
	}

	/**
	 * @return the caja
	 */
	public Long getCaja() {
		return caja;
	}

	/**
	 * @param caja
	 *            the caja to set
	 */
	public void setCaja(Long caja) {
		this.caja = caja;
	}

	/**
	 * @return the partida
	 */
	public Long getPartida() {
		return partida;
	}

	/**
	 * @param partida
	 *            the partida to set
	 */
	public void setPartida(Long partida) {
		this.partida = partida;
	}

	/**
	 * @return the atl
	 */
	public String getAtl() {
		return atl;
	}

	/**
	 * @param atl
	 *            the atl to set
	 */
	public void setAtl(String atl) {
		this.atl = atl;
	}

	/**
	 * @return the lineaCaptura
	 */
	public String getLineaCaptura() {
		return lineaCaptura;
	}

	/**
	 * @param lineaCaptura
	 *            the lineaCaptura to set
	 */
	public void setLineaCaptura(String lineaCaptura) {
		this.lineaCaptura = lineaCaptura;
	}

	/**
	 * @return the fechaCreacion
	 */
	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	/**
	 * @param fechaCreacion
	 *            the fechaCreacion to set
	 */
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	/**
	 * @return the ultimaModificacion
	 */
	public Date getUltimaModificacion() {
		return ultimaModificacion;
	}

	/**
	 * @param ultimaModificacion
	 *            the ultimaModificacion to set
	 */
	public void setUltimaModificacion(Date ultimaModificacion) {
		this.ultimaModificacion = ultimaModificacion;
	}

	/**
	 * @return the valido
	 */
	public Boolean getValido() {
		return valido;
	}

	/**
	 * @param valido
	 *            the valido to set
	 */
	public void setValido(Boolean valido) {
		this.valido = valido;
	}

	/**
	 * @return the procesado
	 */
	public String getProcesado() {
		return procesado;
	}

	/**
	 * @param procesado
	 *            the procesado to set
	 */
	public void setProcesado(String procesado) {
		this.procesado = procesado;
	}

	/**
	 * @return the duplicado
	 */
	public Boolean getDuplicado() {
		return duplicado;
	}

	/**
	 * @param duplicado the duplicado to set
	 */
	public void setDuplicado(Boolean duplicado) {
		this.duplicado = duplicado;
	}

	/**
	 * @return the fechaCancelacion
	 */
	public Date getFechaCancelacion() {
		return fechaCancelacion;
	}

	/**
	 * @param fechaCancelacion the fechaCancelacion to set
	 */
	public void setFechaCancelacion(Date fechaCancelacion) {
		this.fechaCancelacion = fechaCancelacion;
	}

	/**
	 * @return the motivoCancelacion
	 */
	public String getMotivoCancelacion() {
		return motivoCancelacion;
	}

	/**
	 * @param motivoCancelacion the motivoCancelacion to set
	 */
	public void setMotivoCancelacion(String motivoCancelacion) {
		this.motivoCancelacion = motivoCancelacion;
	}

	/**
	 * @return the estatusPago
	 */
	public Integer getEstatusPago() {
		return estatusPago;
	}

	/**
	 * @param estatusPago the estatusPago to set
	 */
	public void setEstatusPago(Integer estatusPago) {
		this.estatusPago = estatusPago;
	}

	/**
	 * @return the concepto
	 */
	public Integer getConcepto() {
		return concepto;
	}

	/**
	 * @param concepto the concepto to set
	 */
	public void setConcepto(Integer concepto) {
		this.concepto = concepto;
	}

	/**
	 * @return the procesoId
	 */
	public Integer getProcesoId() {
		return procesoId;
	}

	/**
	 * @param procesoId the procesoId to set
	 */
	public void setProcesoId(Integer procesoId) {
		this.procesoId = procesoId;
	}

	/**
	 * @return the montoSistema
	 */
	public BigDecimal getMontoSistema() {
		return montoSistema;
	}

	/**
	 * @param montoSistema the montoSistema to set
	 */
	public void setMontoSistema(BigDecimal montoSistema) {
		this.montoSistema = montoSistema;
	}

	
}
