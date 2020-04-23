package mx.com.teclo.saicdmx.persistencia.hibernate.vo.catalogos;

import java.io.Serializable;

public class EstatusInfraccionVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9059974807366211917L;
	
	private Long estatusId;
	private String estatusCodigo;
	private String estatusNombre;
	private String status;
	
	public Long getEstatusId() {
		return estatusId;
	}
	public void setEstatusId(Long estatusId) {
		this.estatusId = estatusId;
	}
	public String getEstatusCodigo() {
		return estatusCodigo;
	}
	public void setEstatusCodigo(String estatusCodigo) {
		this.estatusCodigo = estatusCodigo;
	}
	public String getEstatusNombre() {
		return estatusNombre;
	}
	public void setEstatusNombre(String estatusNombre) {
		this.estatusNombre = estatusNombre;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getStatusDesc() {
		return this.getStatus().equals("A") ? "Activo" : "Cancelado";
	}
}
