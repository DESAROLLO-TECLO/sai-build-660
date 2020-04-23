package mx.com.teclo.saicdmx.persistencia.vo.infracciones;



public class InfraccionesEstadisticasVO {
	
	private  String mes;
	private Integer infrac_creadas;
	private Integer infrac_pagadas;
	private String fechaInicio;
	private String fechaFin;
//	private  String infracConPlaca;
//	private  Integer  infracFolio;
	
	
	
	public Integer getInfrac_creadas() {
		return infrac_creadas;
	}
	public String getMes() {
		return mes;
	}
	public void setMes(String mes) {
		this.mes = mes;
	}
	public void setInfrac_creadas(Integer infrac_credas) {
		this.infrac_creadas = infrac_credas;
	}
	public Integer getInfrac_pagadas() {
		return infrac_pagadas;
	}
	public void setInfrac_pagadas(Integer infrac_pagadas) {
		this.infrac_pagadas = infrac_pagadas;
	}
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
}
