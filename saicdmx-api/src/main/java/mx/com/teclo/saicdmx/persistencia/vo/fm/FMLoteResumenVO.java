package mx.com.teclo.saicdmx.persistencia.vo.fm;

import java.util.Date;

public class FMLoteResumenVO {
	
	private Long archivoId;
	private String fechaEmision;
	private Integer archivoTotal;
	private String estatusProceso;
	
	public Long getArchivoId() {
		return archivoId;
	}
	public void setArchivoId(Long archivoId) {
		this.archivoId = archivoId;
	}
	public String getFechaEmision() {
		return fechaEmision;
	}
	public void setFechaEmision(String fechaEmision) {
		this.fechaEmision = fechaEmision;
	}
	public Integer getArchivoTotal() {
		return archivoTotal;
	}
	public void setArchivoTotal(Integer archivoTotal) {
		this.archivoTotal = archivoTotal;
	}
	public String getEstatusProceso() {
		return estatusProceso;
	}
	public void setEstatusProceso(String estatusProceso) {
		this.estatusProceso = estatusProceso;
	}
}
