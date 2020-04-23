package mx.com.teclo.saicdmx.persistencia.vo.fm;

public class FMConsultaDeteccionesVO {
	private String TOTAL;
	private String RADAR;
	private String MARCA;
	private String TIPO;
	private String Historico;
	private String ID;
	private int TotalActuales;
	private int TotalHistoricos;
		
	
	public String getTOTAL() {
		return TOTAL;
	}
	public void setTOTAL(String tOTAL) {
		this.TOTAL = tOTAL;
	}
	public String getRADAR() {
		return RADAR;
	}
	public void setRADAR(String rADAR) {
		this.RADAR = rADAR;
	}
	public String getMARCA() {
		return MARCA;
	}
	public void setMARCA(String mARCA) {
		this.MARCA = mARCA;
	}
	public String getTIPO() {
		return TIPO;
	}
	public void setTIPO(String tIPO) {
		this.TIPO = tIPO;
	}
	
	public int getTotalActuales() {
		return TotalActuales;
	}
	public void setTotalActuales(int totalActuales) {
		TotalActuales = totalActuales;
	}
	public int getTotalHistoricos() {
		return TotalHistoricos;
	}
	public void setTotalHistoricos(int totalHistoricos) {
		TotalHistoricos = totalHistoricos;
	}
	public String getHistorico() {
		return Historico;
	}
	public void setHistorico(String historico) {
		Historico = historico;
	}
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	

}
