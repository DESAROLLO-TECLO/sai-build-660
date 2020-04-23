package mx.com.teclo.saicdmx.persistencia.vo.fm;

import java.util.List;

public class DeteccionBatchFinanzasVO {
	
	private Long archivoId;
	private String fileName;
	private java.sql.Date fechaEmision;
	private Integer estatusProcesoId;
	private String estatusProceso;
	private Integer enProceso;
	private java.sql.Date ultimaModificacion;
	private Long modificadoPorId;
	private String anioEjercicio;
	private Integer archivoTipo;
	private Integer archivoTipoProceso;
	private List<String[]> bitacoraProceso;
	
	public Long getArchivoId() {
		return archivoId;
	}
	public void setArchivoId(Long archivoId) {
		this.archivoId = archivoId;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public java.sql.Date getFechaEmision() {
		return fechaEmision;
	}
	public void setFechaEmision(java.sql.Date fechaEmision) {
		this.fechaEmision = fechaEmision;
	}
	public Integer getEstatusProcesoId() {
		return estatusProcesoId;
	}
	public void setEstatusProcesoId(Integer estatusProcesoId) {
		this.estatusProcesoId = estatusProcesoId;
	}
	public String getEstatusProceso() {
		return estatusProceso;
	}
	public void setEstatusProceso(String estatusProceso) {
		this.estatusProceso = estatusProceso;
	}
	public Integer getEnProceso() {
		return enProceso;
	}
	public void setEnProceso(Integer enProceso) {
		this.enProceso = enProceso;
	}
	public java.sql.Date getUltimaModificacion() {
		return ultimaModificacion;
	}
	public void setUltimaModificacion(java.sql.Date ultimaModificacion) {
		this.ultimaModificacion = ultimaModificacion;
	}
	public Long getModificadoPorId() {
		return modificadoPorId;
	}
	public void setModificadoPorId(Long modificadoPorId) {
		this.modificadoPorId = modificadoPorId;
	}
	public String getAnioEjercicio() {
		return anioEjercicio;
	}
	public void setAnioEjercicio(String anioEjercicio) {
		this.anioEjercicio = anioEjercicio;
	}
	public Integer getArchivoTipo() {
		return archivoTipo;
	}
	public void setArchivoTipo(Integer archivoTipo) {
		this.archivoTipo = archivoTipo;
	}
	public Integer getArchivoTipoProceso() {
		return archivoTipoProceso;
	}
	public void setArchivoTipoProceso(Integer archivoTipoProceso) {
		this.archivoTipoProceso = archivoTipoProceso;
	}
	public List<String[]> getBitacoraProceso() {
		return bitacoraProceso;
	}
	public void setBitacoraProceso(List<String[]> bitacoraProceso) {
		this.bitacoraProceso = bitacoraProceso;
	}
}
