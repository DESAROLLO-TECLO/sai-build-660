package mx.com.teclo.saicdmx.util.enums.pagos;

public enum EnumTipoPagoCtroPagosValida {

	WEB_COMPLETO("WEB_COMPLETO"),
	WEB_INCOMPLETO("WEB_INCOMPLETO"),
	HH_COMPLETO("HH_COMPLETO"), 
	HH_INCOMPLETO("HH_INCOMPLETO");

	private String tipoPago;

	public String getTipoPago() {
		return tipoPago;
	}

	public void setTipoPago(String tipoPago) {
		this.tipoPago = tipoPago;
	}

	EnumTipoPagoCtroPagosValida(String tipoPago) {
		this.setTipoPago(tipoPago);
	}

}
