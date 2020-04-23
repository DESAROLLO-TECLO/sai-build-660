package mx.com.teclo.saicdmx.persistencia.vo.liberacionvehiculo;

import java.util.Date;

public class ProcesoDeSalidaVO {

	private Integer depositoId;
	private String nombreresguardo;
	private Long usuarioId;
	private String fechaIngreso;
	private String infracNum;
	private String statusIngr;
	
	private Integer resultado;
	private String mensaje;

	public Integer getDepositoId() {
		return depositoId;
	}

	public void setDepositoId(Integer depositoId) {
		this.depositoId = depositoId;
	}
	
	public String getNombreresguardo() {
		return nombreresguardo;
	}
	
	public void setNombreresguardo(String nombreresguardo) {
		this.nombreresguardo = nombreresguardo;
	}

	public Long getUsuarioId() {
		return usuarioId;
	}
	
	public void setUsuarioId(Long usuarioId) {
		this.usuarioId = usuarioId;
	}
	
	public Integer getResultado() {
		return resultado;
	}
	
	public void setResultado(Integer resultado) {
		this.resultado = resultado;
	}
	
	public String getMensaje() {
		return mensaje;
	}
	
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public String getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(String string) {
		this.fechaIngreso = string;
	}

	public String getInfracNum() {
		return infracNum;
	}

	public void setInfracNum(String infracNum) {
		this.infracNum = infracNum;
	}

	public String getStatusIngr() {
		return statusIngr;
	}

	public void setStatusIngr(String statusIngr) {
		this.statusIngr = statusIngr;
	}
	
	
}

