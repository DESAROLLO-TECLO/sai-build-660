package mx.com.teclo.saicdmx.persistencia.vo.fm;

public class FMDeteccionesFechasVO {
	/**trae las fechas enque hay deteccciones separando por año y por mes **/
	
	private String fechaFormat;
	private String fechaValue;
	private String mes;
	private String anio;
	
	public String getFechaFormat() {
		return fechaFormat;
	}
	public void setFechaFormat(String fechaFormat) {
		this.fechaFormat = fechaFormat;
	}
	public String getFechaValue() {
		return fechaValue;
	}
	public void setFechaValue(String fechaValue) {
		this.fechaValue = fechaValue;
	}
	public String getMes() {
		return mes;
	}
	public void setMes(String mes) {
		this.mes = mes;
	}
	public String getAnio() {
		return anio;
	}
	public void setAnio(String anio) {
		this.anio = anio;
	}


}
