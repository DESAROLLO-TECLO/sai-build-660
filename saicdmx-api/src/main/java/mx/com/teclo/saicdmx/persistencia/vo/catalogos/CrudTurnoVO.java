package mx.com.teclo.saicdmx.persistencia.vo.catalogos;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class CrudTurnoVO extends BaseCrudVO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6304450070264059760L;
	
	private Long turnoId;
	private String turnoCod;
	private String turnoNombre;
	private String fechaInicio;
	private String fechaFin;
	private String horaInicio;
	private String horaFin;
	
	public Long getTurnoId() {
		return turnoId;
	}
	public void setTurnoId(Long turnoId) {
		this.turnoId = turnoId;
	}
	public String getTurnoCod() {
		return turnoCod;
	}
	public void setTurnoCod(String turnoCod) {
		this.turnoCod = turnoCod;
	}
	public String getTurnoNombre() {
		return turnoNombre;
	}
	public void setTurnoNombre(String turnoNombre) {
		this.turnoNombre = turnoNombre;
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
	public String getHoraInicio() {
		return horaInicio;
	}
	public void setHoraInicio(String horaInicio) {
		this.horaInicio = horaInicio;
	}
	public String getHoraFin() {
		return horaFin;
	}
	public void setHoraFin(String horaFin) {
		this.horaFin = horaFin;
	}
}
