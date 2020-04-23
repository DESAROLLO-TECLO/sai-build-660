package mx.com.teclo.saicdmx.persistencia.vo.reportes;

import java.util.List;

public class ParametrosBusquedaReporteInfraccionesArticuloVO {
	
	private String fechaInicio;
	private String fechaFin;
	private List<String> id;
	
	
	public String getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public String getFechaFin() {
		return fechaFin;
	}
	public void setFechaFin(String fechaFin) {
		this.fechaFin = fechaFin;
	}
	public List<String> getId() {
		return id;
	}
	public void setId(List<String> id) {
		this.id = id;
	}
	

}
