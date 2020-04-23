package mx.com.teclo.saicdmx.persistencia.vo.caja;

public class FnRenglonesCorteInfraccionVO {
	
	private Double t_RENGLON;
	private String t_INFRACCION;
	private Double t_MONTO;
	private Double t_SUMA_MONTOS;
	private Double t_PARTIDAS;
	private String t_STATUS_TXT;
	private String numCaja;
	private String cajaId;
	
	public Double getT_RENGLON() {
		return t_RENGLON;
	}
	public void setT_RENGLON(Double t_RENGLON) {
		this.t_RENGLON = t_RENGLON;
	}
	public String getT_INFRACCION() {
		return t_INFRACCION;
	}
	public void setT_INFRACCION(String t_INFRACCION) {
		this.t_INFRACCION = t_INFRACCION;
	}
	public Double getT_MONTO() {
		return t_MONTO;
	}
	public void setT_MONTO(Double t_MONTO) {
		this.t_MONTO = t_MONTO;
	}
	public Double getT_SUMA_MONTOS() {
		return t_SUMA_MONTOS;
	}
	public void setT_SUMA_MONTOS(Double t_SUMA_MONTOS) {
		this.t_SUMA_MONTOS = t_SUMA_MONTOS;
	}
	public Double getT_PARTIDAS() {
		return t_PARTIDAS;
	}
	public void setT_PARTIDAS(Double t_PARTIDAS) {
		this.t_PARTIDAS = t_PARTIDAS;
	}
	public String getT_STATUS_TXT() {
		return t_STATUS_TXT;
	}
	public void setT_STATUS_TXT(String t_STATUS_TXT) {
		this.t_STATUS_TXT = t_STATUS_TXT;
	}
	public String getNumCaja() {
		return numCaja;
	}
	public void setNumCaja(String numCaja) {
		this.numCaja = numCaja;
	}
	public String getCajaId() {
		return cajaId;
	}
	public void setCajaId(String cajaId) {
		this.cajaId = cajaId;
	}
}
