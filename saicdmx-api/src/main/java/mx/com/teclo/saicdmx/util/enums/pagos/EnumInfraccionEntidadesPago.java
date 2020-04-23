package mx.com.teclo.saicdmx.util.enums.pagos;

public enum EnumInfraccionEntidadesPago {

	EFE(1), TARCRE(2), TARDEB(3), INTERNET(4),BANCO(5), SETRAVI(6) ,TES(7);

	private EnumInfraccionEntidadesPago(Integer entidadPago) {
		this.entidadPago = entidadPago;
	}

	private Integer entidadPago;

	public Integer getEntidadPago() {
		return entidadPago;
	}

	public void setEntidadPago(Integer entidadPago) {
		this.entidadPago = entidadPago;
	}

	 

}
