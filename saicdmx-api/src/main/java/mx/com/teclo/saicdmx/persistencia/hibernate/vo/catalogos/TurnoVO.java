package mx.com.teclo.saicdmx.persistencia.hibernate.vo.catalogos;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class TurnoVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8938819523307155581L;
	
	private Long turnoId;
	private String turnoCod;
	private String turnoNombre;
	private String turnoInicio;
	private String turnoFin;
	private String turnoStatus;	
	
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
	public String getTurnoInicio() {
		return turnoInicio;
	}
	public void setTurnoInicio(String turnoInicio) {
		this.turnoInicio = turnoInicio;
	}
	public String getTurnoFin() {
		return turnoFin;
	}
	public void setTurnoFin(String turnoFin) {
		this.turnoFin = turnoFin;
	}
	public String getTurnoStatus() {
		return turnoStatus;
	}
	public void setTurnoStatus(String turnoStatus) {
		this.turnoStatus = turnoStatus;
	}		
	public String getFechaFin() {
		if (this.turnoFin != null) {
			String fecha = this.turnoFin.split(" ")[0];
			String arrayFecha[] = fecha.split("-");
			fecha = arrayFecha[2] + "/" + arrayFecha[1] + "/" + arrayFecha[0]; 
			return fecha;
		}
		return "";
	}	
	public String getFechaInicio() {	
		if (this.turnoInicio != null) {
			String fecha = this.turnoInicio.split(" ")[0];
			String arrayFecha[] = fecha.split("-");
			fecha = arrayFecha[2] + "/" + arrayFecha[1] + "/" + arrayFecha[0]; 
			return fecha;
		}
		return "";
	}	
	public String getHoraInicio() {
		if (this.turnoInicio != null) {
			String hora = this.turnoInicio.split(" ")[1];
			hora = hora.replace(".0", "");
			hora = hora.split(":")[0] + ":" + hora.split(":")[1];
			return hora;
		}
		return "";
	}
	public String getHoraFin() {
		if (this.turnoFin != null) {
			String hora = this.turnoFin.split(" ")[1];
			hora = hora.replace(".0", "");
			hora = hora.split(":")[0] + ":" + hora.split(":")[1]; 
			return hora;
		}		
		return "";
	}
	public String getStatusDesc() {
		return this.getTurnoStatus().equals("A") ? "Activo" : "Cancelado";
	}
}
