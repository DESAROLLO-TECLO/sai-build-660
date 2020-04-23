package mx.com.teclo.saicdmx.util.enums.pagos;

public enum EnumTransaccionesTipoRespuesta {
	APROBADA("APPROVED"),
    VENTA("VENTA"), 
    CANCELACION("CANCELACION"),
    DENEGDA("DENIED");
	
	private String tipoOperacion;
	
	public String getTipoInfraccionPago() {
		return tipoOperacion;
	}


	public void setTipoOperacion(String tipoOperacion) {
		this.tipoOperacion = tipoOperacion;
	}


	EnumTransaccionesTipoRespuesta(String tipoOperacion){
		this.setTipoOperacion(tipoOperacion);
	}
}
