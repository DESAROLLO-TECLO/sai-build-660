package mx.com.teclo.saicdmx.persistencia.vo.fotomulta;

public class FotoMultaReporteSSPVO {
	private Long prevalidadorId;
	private String nombre;
	private String totalValidaciones;
	private String totalAceptadas;
	private String totalRechazadas;
	private String porcentaje;
	private String placa;
	/**
	 * @return the prevalidadorId
	 */
	public Long getPrevalidadorId() {
		return prevalidadorId;
	}
	/**
	 * @param prevalidadorId the prevalidadorId to set
	 */
	public void setPrevalidadorId(Long prevalidadorId) {
		this.prevalidadorId = prevalidadorId;
	}
	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}
	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	/**
	 * @return the totalValidaciones
	 */
	public String getTotalValidaciones() {
		return totalValidaciones;
	}
	/**
	 * @param totalValidaciones the totalValidaciones to set
	 */
	public void setTotalValidaciones(String totalValidaciones) {
		this.totalValidaciones = totalValidaciones;
	}
	/**
	 * @return the totalAceptadas
	 */
	public String getTotalAceptadas() {
		return totalAceptadas;
	}
	/**
	 * @param totalAceptadas the totalAceptadas to set
	 */
	public void setTotalAceptadas(String totalAceptadas) {
		this.totalAceptadas = totalAceptadas;
	}
	/**
	 * @return the totalRechazadas
	 */
	public String getTotalRechazadas() {
		return totalRechazadas;
	}
	/**
	 * @param totalRechazadas the totalRechazadas to set
	 */
	public void setTotalRechazadas(String totalRechazadas) {
		this.totalRechazadas = totalRechazadas;
	}
	/**
	 * @return the porcentaje
	 */
	public String getPorcentaje() {
		return porcentaje;
	}
	/**
	 * @param porcentaje the porcentaje to set
	 */
	public void setPorcentaje(String porcentaje) {
		this.porcentaje = porcentaje;
	}
	/**
	 * @return the placa
	 */
	public String getPlaca() {
		return placa;
	}
	/**
	 * @param placa the placa to set
	 */
	public void setPlaca(String placa) {
		this.placa = placa;
	}
}
