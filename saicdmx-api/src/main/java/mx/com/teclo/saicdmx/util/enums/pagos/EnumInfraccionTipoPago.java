package mx.com.teclo.saicdmx.util.enums.pagos;

public enum EnumInfraccionTipoPago {

	CON_INFRACCION(1), SIN_INFRACCION(2);

	private EnumInfraccionTipoPago(Integer tipoPago) {
		this.tipoPago = tipoPago;
	}

	private Integer tipoPago;

	public Integer getTipoPago() {
		return tipoPago;
	}

	public void setTipoPago(Integer tipoPago) {
		this.tipoPago = tipoPago;
	}

}
