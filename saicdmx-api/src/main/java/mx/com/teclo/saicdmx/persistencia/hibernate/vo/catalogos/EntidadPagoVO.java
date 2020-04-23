package mx.com.teclo.saicdmx.persistencia.hibernate.vo.catalogos;

import java.io.Serializable;

public class EntidadPagoVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7310098476214257291L;
	
	private Long entidadId;
	private String entidadCodigo;
	private String entidadNombre;
	private String entidadStatus;
	
	public Long getEntidadId() {
		return entidadId;
	}
	public void setEntidadId(Long entidadId) {
		this.entidadId = entidadId;
	}
	public String getEntidadCodigo() {
		return entidadCodigo;
	}
	public void setEntidadCodigo(String entidadCodigo) {
		this.entidadCodigo = entidadCodigo;
	}
	public String getEntidadNombre() {
		return entidadNombre;
	}
	public void setEntidadNombre(String entidadNombre) {
		this.entidadNombre = entidadNombre;
	}
	public String getEntidadStatus() {
		return entidadStatus;
	}
	public void setEntidadStatus(String entidadStatus) {
		this.entidadStatus = entidadStatus;
	}
	public String getStatusDesc() {
		return this.getEntidadStatus().equals("A") ? "Activo" : "Cancelado";
	}
}
