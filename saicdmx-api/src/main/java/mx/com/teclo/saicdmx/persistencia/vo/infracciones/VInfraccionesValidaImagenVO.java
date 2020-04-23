package mx.com.teclo.saicdmx.persistencia.vo.infracciones;

public class VInfraccionesValidaImagenVO {
	private String numeroInfraccion;
	private String estatus;
	private String fechaModificacion;
	private String modificadoPor;
	
	public String getNumeroInfraccion() {
		return numeroInfraccion;
	}
	public void setNumeroInfraccion(String numeroInfraccion) {
		this.numeroInfraccion = numeroInfraccion;
	}
	public String getEstatus() {
		return estatus;
	}
	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}
	public String getFechaModificacion() {
		return fechaModificacion;
	}
	public void setFechaModificacion(String fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}
	public String getModificadoPor() {
		return modificadoPor;
	}
	public void setModificadoPor(String modificadoPor) {
		this.modificadoPor = modificadoPor;
	}
	@Override
	public String toString() {
		return "VInfraccionesValidaImagenVO [numeroInfraccion=" + numeroInfraccion + ", estatus=" + estatus
				+ ", fechaModificacion=" + fechaModificacion + ", modificadoPor=" + modificadoPor + "]";
	}
	
}
