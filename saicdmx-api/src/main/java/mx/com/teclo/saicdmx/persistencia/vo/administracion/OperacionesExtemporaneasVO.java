package mx.com.teclo.saicdmx.persistencia.vo.administracion;

import java.io.Serializable;

public class OperacionesExtemporaneasVO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 390701294119976418L;
	
	private Long cajaId;
	private Long supervisorId;
	private String fecha;
	private String resultado;
	private String mensaje;
	private String cajaCod;
	
	public Long getCajaId() {
		return cajaId;
	}
	public void setCajaId(Long cajaId) {
		this.cajaId = cajaId;
	}
	public Long getSupervisorId() {
		return supervisorId;
	}
	public void setSupervisorId(Long supervisorId) {
		this.supervisorId = supervisorId;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public String getResultado() {
		return resultado;
	}
	public void setResultado(String resultado) {
		this.resultado = resultado;
	}
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	public String getCajaCod() {
		return cajaCod;
	}
	public void setCajaCod(String cajaCod) {
		this.cajaCod = cajaCod;
	}
}
