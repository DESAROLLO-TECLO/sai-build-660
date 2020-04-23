package mx.com.teclo.saicdmx.persistencia.vo.fotomulta;

public class DeteccionesResultadoVO {
	private Integer luzroja;
	private Integer bosch;
	private Integer laser;
	
	private Integer mes;
	private Integer anio;
	private String fechaCreacion;
	
	/**
	 * @return the luzroja
	 */
	public Integer getLuzroja() {
		return luzroja;
	}
	/**
	 * @param luzroja the luzroja to set
	 */
	public void setLuzroja(Integer luzroja) {
		this.luzroja = luzroja;
	}
	/**
	 * @return the bosch
	 */
	public Integer getBosch() {
		return bosch;
	}
	/**
	 * @param bosch the bosch to set
	 */
	public void setBosch(Integer bosch) {
		this.bosch = bosch;
	}
	/**
	 * @return the laser
	 */
	public Integer getLaser() {
		return laser;
	}
	/**
	 * @param laser the laser to set
	 */
	public void setLaser(Integer laser) {
		this.laser = laser;
	}
	/**
	 * @return the fechaCreacion
	 */
	public String getFechaCreacion() {
		return fechaCreacion;
	}
	/**
	 * @param fechaCreacion the fechaCreacion to set
	 */
	public void setFechaCreacion(String fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	/**
	 * @return the mes
	 */
	public Integer getMes() {
		return mes;
	}
	/**
	 * @param mes the mes to set
	 */
	public void setMes(Integer mes) {
		this.mes = mes;
	}
	/**
	 * @return the anio
	 */
	public Integer getAnio() {
		return anio;
	}
	/**
	 * @param anio the anio to set
	 */
	public void setAnio(Integer anio) {
		this.anio = anio;
	}
}
