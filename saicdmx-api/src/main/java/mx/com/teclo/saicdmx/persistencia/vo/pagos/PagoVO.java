package mx.com.teclo.saicdmx.persistencia.vo.pagos;

import java.io.Serializable;

public class PagoVO implements Serializable {

	private static final long serialVersionUID = 1L;
	private String tipoPago;
	private String cajaId;
	private String numCtrl;
	private String numInfrac;
	private String monto;
	private String descuento;
	private String actualizacion;
	private String recargos;
	private String arrastre;
	private String derechoPiso;
	private String adicionales;
	private String totalPago;
	private String lugarPago;
	private String entidad;
	/* Datos Transaccion Bancaria */
	private String referencia;
	private String transaccion;
	private String usuario;
	private String bancoTarjeta;
	private String numTarjeta;
	private String nomPropTarjeta;
	private String transRespuesta;
	private String numAutorizacion;
	private String nomAfil;
	private String banAfil;
	private String fechaTrans;
	private String refeTrans;
	private String hsResult;
	private String strTrack;
	private String xmlTrans;
	private String formaPago;
	private String auxiliar;
	private String salidaProceso;
	private String numeroOperacion;
	private String voucher;
	/* Fin datos Transaccion Bancaria */
	private String pagoId;
	private String candado;
	private String infracMontoActualizado;
	private String conceptoPagoInfraccion;
	private String conceptoPagoArrastre;
	private String conceptoPagoPiso;
	private String conceptoPagoCandado;

	/* Mensajes de respuesta del store */

	private String mensaje;
	private String resultado;
	private String resultadoExtra;

	public String getTipoPago() {
		return tipoPago;
	}

	public void setTipoPago(String tipoPago) {
		this.tipoPago = tipoPago;
	}

	public String getCajaId() {
		return cajaId;
	}

	public void setCajaId(String cajaId) {
		this.cajaId = cajaId;
	}

	public String getNumCtrl() {
		return numCtrl;
	}

	public void setNumCtrl(String numCtrl) {
		this.numCtrl = numCtrl;
	}

	public String getNumInfrac() {
		return numInfrac;
	}

	public void setNumInfrac(String numInfrac) {
		this.numInfrac = numInfrac;
	}

	public String getMonto() {
		return monto;
	}

	public void setMonto(String monto) {
		this.monto = monto;
	}

	public String getDescuento() {
		return descuento;
	}

	public void setDescuento(String descuento) {
		this.descuento = descuento;
	}

	public String getActualizacion() {
		return actualizacion;
	}

	public void setActualizacion(String actualizacion) {
		this.actualizacion = actualizacion;
	}

	public String getRecargos() {
		return recargos;
	}

	public void setRecargos(String recargos) {
		this.recargos = recargos;
	}

	public String getArrastre() {
		return arrastre;
	}

	public void setArrastre(String arrastre) {
		this.arrastre = arrastre;
	}

	public String getDerechoPiso() {
		return derechoPiso;
	}

	public void setDerechoPiso(String derechoPiso) {
		this.derechoPiso = derechoPiso;
	}

	public String getAdicionales() {
		return adicionales;
	}

	public void setAdicionales(String adicionales) {
		this.adicionales = adicionales;
	}

	public String getTotalPago() {
		return totalPago;
	}

	public void setTotalPago(String totalPago) {
		this.totalPago = totalPago;
	}

	public String getLugarPago() {
		return lugarPago;
	}

	public void setLugarPago(String lugarPago) {
		this.lugarPago = lugarPago;
	}

	public String getEntidad() {
		return entidad;
	}

	public void setEntidad(String entidad) {
		this.entidad = entidad;
	}

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public String getTransaccion() {
		return transaccion;
	}

	public void setTransaccion(String transaccion) {
		this.transaccion = transaccion;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getBancoTarjeta() {
		return bancoTarjeta;
	}

	public void setBancoTarjeta(String bancoTarjeta) {
		this.bancoTarjeta = bancoTarjeta;
	}

	public String getNumTarjeta() {
		return numTarjeta;
	}

	public void setNumTarjeta(String numTarjeta) {
		this.numTarjeta = numTarjeta;
	}

	public String getNomPropTarjeta() {
		return nomPropTarjeta;
	}

	public void setNomPropTarjeta(String nomPropTarjeta) {
		this.nomPropTarjeta = nomPropTarjeta;
	}

	public String getTransRespuesta() {
		return transRespuesta;
	}

	public void setTransRespuesta(String transRespuesta) {
		this.transRespuesta = transRespuesta;
	}

	public String getNumAutorizacion() {
		return numAutorizacion;
	}

	public void setNumAutorizacion(String numAutorizacion) {
		this.numAutorizacion = numAutorizacion;
	}

	public String getNomAfil() {
		return nomAfil;
	}

	public void setNomAfil(String nomAfil) {
		this.nomAfil = nomAfil;
	}

	public String getBanAfil() {
		return banAfil;
	}

	public void setBanAfil(String banAfil) {
		this.banAfil = banAfil;
	}

	public String getFechaTrans() {
		return fechaTrans;
	}

	public void setFechaTrans(String fechaTrans) {
		this.fechaTrans = fechaTrans;
	}

	public String getRefeTrans() {
		return refeTrans;
	}

	public void setRefeTrans(String refeTrans) {
		this.refeTrans = refeTrans;
	}

	public String getHsResult() {
		return hsResult;
	}

	public void setHsResult(String hsResult) {
		this.hsResult = hsResult;
	}

	public String getStrTrack() {
		return strTrack;
	}

	public void setStrTrack(String strTrack) {
		this.strTrack = strTrack;
	}

	public String getXmlTrans() {
		return xmlTrans;
	}

	public void setXmlTrans(String xmlTrans) {
		this.xmlTrans = xmlTrans;
	}

	public String getFormaPago() {
		return formaPago;
	}

	public void setFormaPago(String formaPago) {
		this.formaPago = formaPago;
	}

	public String getAuxiliar() {
		return auxiliar;
	}

	public void setAuxiliar(String auxiliar) {
		this.auxiliar = auxiliar;
	}

	public String getSalidaProceso() {
		return salidaProceso;
	}

	public void setSalidaProceso(String salidaProceso) {
		this.salidaProceso = salidaProceso;
	}

	public String getNumeroOperacion() {
		return numeroOperacion;
	}

	public void setNumeroOperacion(String numeroOperacion) {
		this.numeroOperacion = numeroOperacion;
	}

	public String getVoucher() {
		return voucher;
	}

	public void setVoucher(String voucher) {
		this.voucher = voucher;
	}

	public String getPagoId() {
		return pagoId;
	}

	public void setPagoId(String pagoId) {
		this.pagoId = pagoId;
	}

	public String getCandado() {
		return candado;
	}

	public void setCandado(String candado) {
		this.candado = candado;
	}

	public String getInfracMontoActualizado() {
		return infracMontoActualizado;
	}

	public void setInfracMontoActualizado(String infracMontoActualizado) {
		this.infracMontoActualizado = infracMontoActualizado;
	}

	public String getConceptoPagoInfraccion() {
		return conceptoPagoInfraccion;
	}

	public void setConceptoPagoInfraccion(String conceptoPagoInfraccion) {
		this.conceptoPagoInfraccion = conceptoPagoInfraccion;
	}

	public String getConceptoPagoArrastre() {
		return conceptoPagoArrastre;
	}

	public void setConceptoPagoArrastre(String conceptoPagoArrastre) {
		this.conceptoPagoArrastre = conceptoPagoArrastre;
	}

	public String getConceptoPagoPiso() {
		return conceptoPagoPiso;
	}

	public void setConceptoPagoPiso(String conceptoPagoPiso) {
		this.conceptoPagoPiso = conceptoPagoPiso;
	}

	public String getConceptoPagoCandado() {
		return conceptoPagoCandado;
	}

	public void setConceptoPagoCandado(String conceptoPagoCandado) {
		this.conceptoPagoCandado = conceptoPagoCandado;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public String getResultado() {
		return resultado;
	}

	public void setResultado(String resultado) {
		this.resultado = resultado;
	}

	public String getResultadoExtra() {
		return resultadoExtra;
	}

	public void setResultadoExtra(String resultadoExtra) {
		this.resultadoExtra = resultadoExtra;
	}

}
