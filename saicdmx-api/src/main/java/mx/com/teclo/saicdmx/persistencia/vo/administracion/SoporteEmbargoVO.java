package mx.com.teclo.saicdmx.persistencia.vo.administracion;

public class SoporteEmbargoVO {
	private String infracControl;
	private String infraccion;
	private String fechaHora;
	
	/**
	 * @return the infracControl
	 */
	public String getInfracControl() {
		return infracControl;
	}
	/**
	 * @param infracControl the infracControl to set
	 */
	public void setInfracControl(String infracControl) {
		this.infracControl = infracControl;
	}
	/**
	 * @return the infraccion
	 */
	public String getInfraccion() {
		return infraccion;
	}
	/**
	 * @param infraccion the infraccion to set
	 */
	public void setInfraccion(String infraccion) {
		this.infraccion = infraccion;
	}
	/**
	 * @return the fechaHora
	 */
	public String getFechaHora() {
		return fechaHora;
	}
	/**
	 * @param fechaHora the fechaHora to set
	 */
	public void setFechaHora(String fechaHora) {
		this.fechaHora = fechaHora;
	}
}
