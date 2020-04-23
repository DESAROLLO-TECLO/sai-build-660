package mx.com.teclo.saicdmx.persistencia.hibernate.vo.catalogos;

import java.io.Serializable;

public class ProgramaVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8982290499708216373L;
	
	private Long programaId;
	private String programaCodigo;
	private String programaNombre;
	private String programaStatus;
	
	public Long getProgramaId() {
		return programaId;
	}
	public void setProgramaId(Long programaId) {
		this.programaId = programaId;
	}
	public String getProgramaCodigo() {
		return programaCodigo;
	}
	public void setProgramaCodigo(String programaCodigo) {
		this.programaCodigo = programaCodigo;
	}
	public String getProgramaNombre() {
		return programaNombre;
	}
	public void setProgramaNombre(String programaNombre) {
		this.programaNombre = programaNombre;
	}
	public String getProgramaStatus() {
		return programaStatus;
	}
	public void setProgramaStatus(String programaStatus) {
		this.programaStatus = programaStatus;
	}
	public String getStatusDesc() {
		return this.getProgramaStatus().equals("A") ? "Activo" : "Cancelado";
	}
}
