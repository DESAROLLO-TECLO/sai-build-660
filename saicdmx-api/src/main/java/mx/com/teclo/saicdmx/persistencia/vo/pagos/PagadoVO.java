package mx.com.teclo.saicdmx.persistencia.vo.pagos;

import java.io.Serializable;

public class PagadoVO implements Serializable {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String infracNum;
	private String infracNumCtrl;
	private String infracPlaca;
	private String infracFecha;
	private String infracImpresa;
	private String infracDocumento;
	private String vehiculoMarca;
	private String vehiculoModelo;
	private String vehiculoColor;
	private String infracEstatus;
	private String infractorNombre;
	private String depositoId;
	private String deposito;
	private boolean pagoConMonto;
	private boolean pagoTieneVoucher;
	private String pagoFecha;
	private String busquedaTipo;
	private String busquedaValor;
	private String tipoPago;

	public String getInfracNum() {
		return infracNum;
	}

	public void setInfracNum(String infracNum) {
		this.infracNum = infracNum;
	}

	public String getInfracNumCtrl() {
		return infracNumCtrl;
	}

	public void setInfracNumCtrl(String infracNumCtrl) {
		this.infracNumCtrl = infracNumCtrl;
	}

	public String getInfracPlaca() {
		return infracPlaca;
	}

	public void setInfracPlaca(String infracPlaca) {
		this.infracPlaca = infracPlaca;
	}

	public String getInfracFecha() {
		return infracFecha;
	}

	public void setInfracFecha(String infracFecha) {
		this.infracFecha = infracFecha;
	}

	public String getInfracImpresa() {
		return infracImpresa;
	}

	public void setInfracImpresa(String infracImpresa) {
		this.infracImpresa = infracImpresa;
	}

	public String getInfracDocumento() {
		return infracDocumento;
	}

	public void setInfracDocumento(String infracDocumento) {
		this.infracDocumento = infracDocumento;
	}

	public String getVehiculoMarca() {
		return vehiculoMarca;
	}

	public void setVehiculoMarca(String vehiculoMarca) {
		this.vehiculoMarca = vehiculoMarca;
	}

	public String getVehiculoModelo() {
		return vehiculoModelo;
	}

	public void setVehiculoModelo(String vehiculoModelo) {
		this.vehiculoModelo = vehiculoModelo;
	}

	public String getVehiculoColor() {
		return vehiculoColor;
	}

	public void setVehiculoColor(String vehiculoColor) {
		this.vehiculoColor = vehiculoColor;
	}

	public String getInfracEstatus() {
		return infracEstatus;
	}

	public void setInfracEstatus(String infracEstatus) {
		this.infracEstatus = infracEstatus;
	}

	public String getInfractorNombre() {
		return infractorNombre;
	}

	public void setInfractorNombre(String infractorNombre) {
		this.infractorNombre = infractorNombre;
	}

	public String getDepositoId() {
		return depositoId;
	}

	public void setDepositoId(String depositoId) {
		this.depositoId = depositoId;
	}

	public String getDeposito() {
		return deposito;
	}

	public void setDeposito(String deposito) {
		this.deposito = deposito;
	}

	public boolean isPagoConMonto() {
		return pagoConMonto;
	}

	public void setPagoConMonto(boolean pagoConMonto) {
		this.pagoConMonto = pagoConMonto;
	}

	public boolean isPagoTieneVoucher() {
		return pagoTieneVoucher;
	}

	public void setPagoTieneVoucher(boolean pagoTieneVoucher) {
		this.pagoTieneVoucher = pagoTieneVoucher;
	}

	public String getPagoFecha() {
		return pagoFecha;
	}

	public void setPagoFecha(String pagoFecha) {
		this.pagoFecha = pagoFecha;
	}

	public String getBusquedaTipo() {
		return busquedaTipo;
	}

	public void setBusquedaTipo(String busquedaTipo) {
		this.busquedaTipo = busquedaTipo;
	}

	public String getBusquedaValor() {
		return busquedaValor;
	}

	public void setBusquedaValor(String busquedaValor) {
		this.busquedaValor = busquedaValor;
	}
	public String getTipoPago(){
		return tipoPago;
	}
	public void setTipoPago(String tipoPago){
		this.tipoPago = tipoPago;
	}

}
