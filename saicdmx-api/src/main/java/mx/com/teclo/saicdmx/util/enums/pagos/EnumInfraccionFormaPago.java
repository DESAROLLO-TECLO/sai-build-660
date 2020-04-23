package mx.com.teclo.saicdmx.util.enums.pagos;

public enum EnumInfraccionFormaPago {

	EFECTIVO(1), TARJETA_CREDITO(2),TARJETA_DEBITO(3), DOCUMENTO(99) ;

	private EnumInfraccionFormaPago(Integer formaPago) {
		this.formaPago = formaPago;
	}

	private Integer formaPago;

	public Integer getFormaPago() {
		return formaPago;
	}

	public void setFormaPago(Integer formaPago) {
		this.formaPago = formaPago;
	}

	 
	 

}
