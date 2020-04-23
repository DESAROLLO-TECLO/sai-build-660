package mx.com.teclo.saicdmx.persistencia.vo.logs;

public class LogsBusquedaPorIdVO {
	
	 private Long logId;
	 private String logNombre;
	 private String logDescripcion;
	 private String logEstatus;
	 private String rutaArchivo;
	 private String tipoExtensiones;
	
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
	public String getLogEstatus() {
		return logEstatus;
	}
	public void setLogEstatus(String logEstatus) {
		this.logEstatus = logEstatus;
	}	
	public String getRutaArchivo() {
		return rutaArchivo;
	}
	public void setRutaArchivo(String rutaArchivo) {
		this.rutaArchivo = rutaArchivo;
	}
	public String getTipoExtensiones() {
		return tipoExtensiones;
	}
	public void setTipoExtensiones(String tipoExtensiones) {
		this.tipoExtensiones = tipoExtensiones;
	}
	   

	 

}
