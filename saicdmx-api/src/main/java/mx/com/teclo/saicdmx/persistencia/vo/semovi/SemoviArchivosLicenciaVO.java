package mx.com.teclo.saicdmx.persistencia.vo.semovi;

public class SemoviArchivosLicenciaVO {
	
	private Long archivolicenciaId;
	private String nombreArchivo;
	private String fechaArchivo;
	private Long tipoArchivo;
	private Long creadoPor;
	private String fechaCreacion;
	private String descripcion;
	private String codigo;
	private Long numeroRegistros;
	
	public Long getArchivolicenciaId() {
		return archivolicenciaId;
	}
	public void setArchivolicenciaId(Long archivolicenciaId) {
		this.archivolicenciaId = archivolicenciaId;
	}
	public String getNombreArchivo() {
		return nombreArchivo;
	}
	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}
	public String getFechaArchivo() {
		return fechaArchivo;
	}
	public void setFechaArchivo(String fechaArchivo) {
		this.fechaArchivo = fechaArchivo;
	}
	public Long getTipoArchivo() {
		return tipoArchivo;
	}
	public void setTipoArchivo(Long tipoArchivo) {
		this.tipoArchivo = tipoArchivo;
	}
	public Long getCreadoPor() {
		return creadoPor;
	}
	public void setCreadoPor(Long creadoPor) {
		this.creadoPor = creadoPor;
	}
	public String getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(String fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public Long getNumeroRegistros() {
		return numeroRegistros;
	}
	public void setNumeroRegistros(Long numeroRegistros) {
		this.numeroRegistros = numeroRegistros;
	}
	

}
