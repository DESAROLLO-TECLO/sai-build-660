package mx.com.teclo.saicdmx.util.enums.pagos;

public enum EnumInfraccionPago {

	PLACA("PREIMPRESA"),
	IMPRESA("IMPRESA"),
    INFRACCION("INFRACCION"), 
    NCI("NCI") ;
	
	private String tipoInfraccionPago;
	
	public String getTipoInfraccionPago() {
		return tipoInfraccionPago;
	}


	public void setTipoInfraccionPago(String tipoInfraccionPago) {
		this.tipoInfraccionPago = tipoInfraccionPago;
	}


	EnumInfraccionPago(String tipoInfraccionPago){
		this.setTipoInfraccionPago(tipoInfraccionPago);
	}

	 
}
