package mx.com.teclo.saicdmx.persistencia.vo.caja;

import java.io.Serializable;

public class VCajaExtDesactivarVO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7206047708862671291L;
	
	private Long cajaId;
	private String cajaCod;
	private String empPlaca;
	private String empNombre;
	private Long supervisorId;
	private String label;
	private String resultado;
	private String mensaje;
	
	public Long getCajaId() {
		return cajaId;
	}
	public void setCajaId(Long cajaId) {
		this.cajaId = cajaId;
	}
	public String getCajaCod() {
		return cajaCod;
	}
	public void setCajaCod(String cajaCod) {
		this.cajaCod = cajaCod;
	}
	public String getEmpPlaca() {
		return empPlaca;
	}
	public void setEmpPlaca(String empPlaca) {
		this.empPlaca = empPlaca;
	}
	public String getEmpNombre() {
		return empNombre;
	}
	public void setEmpNombre(String empNombre) {
		this.empNombre = empNombre;
	}
	public Long getSupervisorId() {
		return supervisorId;
	}
	public void setSupervisorId(Long supervisorId) {
		this.supervisorId = supervisorId;
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
	public void setLabel(String label) {
		this.label = label;
	}
	public String getLabel() {
		StringBuilder builder = new StringBuilder();
		builder.append("PLACA:");
		builder.append(this.empPlaca);
		builder.append(" ");
		builder.append("CAJA:");
		builder.append(this.cajaCod);
		builder.append(" ");
		builder.append(this.empNombre);
		return builder.toString();
	}
}
