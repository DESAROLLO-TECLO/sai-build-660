package mx.com.teclo.saicdmx.persistencia.vo.fm;

import java.util.Date;

public class FMBitProcesoVO {
	private String estatusNombre;
	private Long estatusProcesoId;
	private Date fechaProceso;
	
	public String getEstatusNombre() {
		return estatusNombre;
	}
	public void setEstatusNombre(String estatusNombre) {
		this.estatusNombre = estatusNombre;
	}
	public Long getEstatusProcesoId() {
		return estatusProcesoId;
	}
	public void setEstatusProcesoId(Long estatusProcesoId) {
		this.estatusProcesoId = estatusProcesoId;
	}
	public Date getFechaProceso() {
		return fechaProceso;
	}
	public void setFechaProceso(Date fechaProceso) {
		this.fechaProceso = fechaProceso;
	}
}
