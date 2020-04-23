package mx.com.teclo.saicdmx.persistencia.vo.impugnacion;

public class VConsultaCanceladas {
	
	private String infracNum;
	private String vehiculoPlaca;
	private String fecha;
	private String infracCalle;
	private String motivacion;
	private String sancionDias;
	private String infracPagada;
	private String deposito;

	
	private String pagada;
	
	public String getInfracNum() {
		return infracNum;
	}
	public void setInfracNum(String infracNum) {
		this.infracNum = infracNum;
	}
	public String getVehiculoPlaca() {
		return vehiculoPlaca;
	}
	public void setVehiculoPlaca(String vehiculoPlaca) {
		this.vehiculoPlaca = vehiculoPlaca;
	}
	public String getInfracCalle() {
		return infracCalle;
	}
	public void setInfracCalle(String infracCalle) {
		this.infracCalle = infracCalle;
	}
	public String getSancionDias() {
		return sancionDias;
	}
	public void setSancionDias(String sancionDias) {
		this.sancionDias = sancionDias;
	}
	public String getInfracPagada() {
		return infracPagada;
	}
	public void setInfracPagada(String infracPagada) {
		this.infracPagada = infracPagada;
	}
	public String getDeposito() {
		return deposito;
	}
	public void setDeposito(String deposito) {
		this.deposito = deposito;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public String getMotivacion() {
		return motivacion;
	}
	public void setMotivacion(String motivacion) {
		this.motivacion = motivacion;
	}
	public String getPagada() {
		return pagada;
	}
	public void setPagada(String pagada) {
		this.pagada = pagada;
	}

	
}
