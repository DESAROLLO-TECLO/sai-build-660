package mx.com.teclo.saicdmx.persistencia.vo.pagos;

public class ImagenExpedienteVO {
	private Long idArchivo;
	private String nombreArchivo;
	private String nombreCatDocumento;
	private String tipoCatDocumento;
	private String extensionArchivo;
	private boolean existeEnBD = false;
	private String localPath;
	private byte[] bdPath;
	
	public Long getIdArchivo() {
		return idArchivo;
	}
	public void setIdArchivo(Long idArchivo) {
		this.idArchivo = idArchivo;
	}
	
	public String getNombreArchivo() {
		return nombreArchivo;
	}
	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}
	
	public String getNombreCatDocumento() {
		return nombreCatDocumento;
	}
	public void setNombreCatDocumento(String nombreCatDocumento) {
		this.nombreCatDocumento = nombreCatDocumento;
	}
	
	public String getTipoCatDocumento() {
		return tipoCatDocumento;
	}
	public void setTipoCatDocumento(String tipoCatDocumento) {
		this.tipoCatDocumento = tipoCatDocumento;
	}
	
	public String getExtensionArchivo() {
		return extensionArchivo;
	}
	public void setExtensionArchivo(String extensionArchivo) {
		this.extensionArchivo = extensionArchivo;
	}
	
	public boolean isExisteEnBD() {
		return existeEnBD;
	}
	public void setExisteEnBD(boolean existeEnBD) {
		this.existeEnBD = existeEnBD;
	}
	public String getLocalPath() {
		return localPath;
	}
	public void setLocalPath(String localPath) {
		this.localPath = localPath;
	}
	public byte[] getBdPath() {
		return bdPath;
	}
	public void setBdPath(byte[] bdPath) {
		this.bdPath = bdPath;
	}
}
