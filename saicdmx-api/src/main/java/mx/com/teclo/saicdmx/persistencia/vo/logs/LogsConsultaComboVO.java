package mx.com.teclo.saicdmx.persistencia.vo.logs;

public class LogsConsultaComboVO {
	
	 private Long logId;
	 private String logNombre;
	 private String logDescripcion;
	 private String logEstatus;
	 private String rutaArchivos;
	
	
	public Long getLogId() {
		return logId;
	}
	public void setLogId(Long logId) {
		this.logId = logId;
	}
	public String getLogNombre() {
		return logNombre;
	}
	public void setLogNombre(String logNombre) {
		this.logNombre = logNombre;
	}
	public String getLogDescripcion() {
		return logDescripcion;
	}
	public void setLogDescripcion(String logDescripcion) {
		this.logDescripcion = logDescripcion;
	}	
	public String getRutaArchivos() {
		return rutaArchivos;
	}
	public void setRutaArchivos(String rutaArchivos) {
		this.rutaArchivos = rutaArchivos;
	}
	public String getLogEstatus() {
		return logEstatus;
	}
	public void setLogEstatus(String logEstatus) {
		this.logEstatus = logEstatus;
	}
	

	 

}
