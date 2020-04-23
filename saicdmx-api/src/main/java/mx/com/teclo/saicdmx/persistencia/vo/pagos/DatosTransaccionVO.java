package mx.com.teclo.saicdmx.persistencia.vo.pagos;

public class DatosTransaccionVO {
	private Long cajaID;
	private Long tranID;
	private String tranOrden;
	private String tranNumAutorizacion;
	private Double tranImporte;
	private String tranTarjeta;
	private String infracNum;
	private String banNombre;
	private String tranNombre;
	private String tranRespuesta;
	private String traNomAfiliacion;
	private String tranBanAfil;
	private String tranFecha;
	private String tranReferencia;
	private String tranLineaBanco;
	private String tranStatus;
	private String tipoOperacion;
	private String nbError;
	private String cdError;
	private String fechaValidacion;
	private boolean tieneVoucher;
	private boolean transaccionValidada;
	private boolean totalIgual;
	private boolean transaccionCancelada;
	private boolean transaccionDenegada;
	private boolean transaccionAprobada;
	
	
	public String getCdError() {
		return cdError;
	}
	public void setCdError(String cdError) {
		this.cdError = cdError;
	}
	public boolean isTransaccionAprobada() {
		return transaccionAprobada;
	}
	public void setTransaccionAprobada(boolean transaccionAprobada) {
		this.transaccionAprobada = transaccionAprobada;
	}
	public Long getCajaID() {
		return cajaID;
	}
	public void setCajaID(Long cajaID) {
		this.cajaID = cajaID;
	}
	public Long getTranID() {
		return tranID;
	}
	public void setTranID(Long tranID) {
		this.tranID = tranID;
	}
	public String getTranOrden() {
		return tranOrden;
	}
	public void setTranOrden(String tranOrden) {
		this.tranOrden = tranOrden;
	}
	public String getTranNumAutorizacion() {
		return tranNumAutorizacion;
	}
	public void setTranNumAutorizacion(String tranNumAutorizacion) {
		this.tranNumAutorizacion = tranNumAutorizacion;
	}
	public Double getTranImporte() {
		return tranImporte;
	}
	public void setTranImporte(Double tranImporte) {
		this.tranImporte = tranImporte;
	}
	public boolean isTieneVoucher() {
		return tieneVoucher;
	}
	public void setTieneVoucher(boolean tieneVoucher) {
		this.tieneVoucher = tieneVoucher;
	}
	public boolean isTransaccionValidada() {
		return transaccionValidada;
	}
	public void setTransaccionValidada(boolean transaccionValidada) {
		this.transaccionValidada = transaccionValidada;
	}
	public boolean isTotalIgual() {
		return totalIgual;
	}
	public void setTotalIgual(boolean totalIgual) {
		this.totalIgual = totalIgual;
	}
	public boolean isTransaccionCancelada() {
		return transaccionCancelada;
	}
	public void setTransaccionCancelada(boolean transaccionCancelada) {
		this.transaccionCancelada = transaccionCancelada;
	}
	public boolean isTransaccionDenegada() {
		return transaccionDenegada;
	}
	public void setTransaccionDenegada(boolean transaccionDenegada) {
		this.transaccionDenegada = transaccionDenegada;
	}
	public String getTranTarjeta() {
		return tranTarjeta;
	}
	public void setTranTarjeta(String tranTarjeta) {
		this.tranTarjeta = tranTarjeta;
	}
	public String getInfracNum() {
		return infracNum;
	}
	public void setInfracNum(String infracNum) {
		this.infracNum = infracNum;
	}
	public String getBanNombre() {
		return banNombre;
	}
	public void setBanNombre(String banNombre) {
		this.banNombre = banNombre;
	}
	public String getTranNombre() {
		return tranNombre;
	}
	public void setTranNombre(String tranNombre) {
		this.tranNombre = tranNombre;
	}
	public String getTranRespuesta() {
		return tranRespuesta;
	}
	public void setTranRespuesta(String tranRespuesta) {
		this.tranRespuesta = tranRespuesta;
	}
	public String getTraNomAfiliacion() {
		return traNomAfiliacion;
	}
	public void setTraNomAfiliacion(String traNomAfiliacion) {
		this.traNomAfiliacion = traNomAfiliacion;
	}
	public String getTranBanAfil() {
		return tranBanAfil;
	}
	public void setTranBanAfil(String tranBanAfil) {
		this.tranBanAfil = tranBanAfil;
	}
	public String getTranFecha() {
		return tranFecha;
	}
	public void setTranFecha(String tranFecha) {
		this.tranFecha = tranFecha;
	}
	public String getTranReferencia() {
		return tranReferencia;
	}
	public void setTranReferencia(String tranReferencia) {
		this.tranReferencia = tranReferencia;
	}
	public String getTranLineaBanco() {
		return tranLineaBanco;
	}
	public void setTranLineaBanco(String tranLineaBanco) {
		this.tranLineaBanco = tranLineaBanco;
	}
	public String getTranStatus() {
		return tranStatus;
	}
	public void setTranStatus(String tranStatus) {
		this.tranStatus = tranStatus;
	}
	public String getTipoOperacion() {
		return tipoOperacion;
	}
	public void setTipoOperacion(String tipoOperacion) {
		this.tipoOperacion = tipoOperacion;
	}
	public String getNbError() {
		return nbError;
	}
	public void setNbError(String nbError) {
		this.nbError = nbError;
	}
	public String getFechaValidacion() {
		return fechaValidacion;
	}
	public void setFechaValidacion(String fechaValidacion) {
		this.fechaValidacion = fechaValidacion;
	}

}
