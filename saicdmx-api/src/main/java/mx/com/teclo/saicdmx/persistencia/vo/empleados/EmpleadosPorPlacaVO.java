package mx.com.teclo.saicdmx.persistencia.vo.empleados;

import java.io.Serializable;

import mx.com.teclo.saicdmx.persistencia.hibernate.vo.catalogos.AgrupamientosVO;
import mx.com.teclo.saicdmx.persistencia.hibernate.vo.catalogos.SectorVO;

public class EmpleadosPorPlacaVO implements Serializable{
	private static final long serialVersionUID = -3803964519566686438L;

	private Long empId;
	private String empApePaterno;
	private String empApeMaterno;
	private String empNombre;
	private String empPlaca;
	private AgrupamientosVO agrupamiento;
	private SectorVO sector;
	
	/**
	 * @return the empId
	 */
	public Long getEmpId() {
		return empId;
	}
	/**
	 * @param empId the empId to set
	 */
	public void setEmpId(Long empId) {
		this.empId = empId;
	}
	/**
	 * @return the empApePaterno
	 */
	public String getEmpApePaterno() {
		return empApePaterno;
	}
	/**
	 * @param empApePaterno the empApePaterno to set
	 */
	public void setEmpApePaterno(String empApePaterno) {
		this.empApePaterno = empApePaterno;
	}
	/**
	 * @return the empApeMaterno
	 */
	public String getEmpApeMaterno() {
		return empApeMaterno;
	}
	/**
	 * @param empApeMaterno the empApeMaterno to set
	 */
	public void setEmpApeMaterno(String empApeMaterno) {
		this.empApeMaterno = empApeMaterno;
	}
	/**
	 * @return the empNombre
	 */
	public String getEmpNombre() {
		return empNombre;
	}
	/**
	 * @param empNombre the empNombre to set
	 */
	public void setEmpNombre(String empNombre) {
		this.empNombre = empNombre;
	}
	/**
	 * @return the agrupamiento
	 */
	public AgrupamientosVO getAgrupamiento() {
		return agrupamiento;
	}
	/**
	 * @param agrupamiento the agrupamiento to set
	 */
	public void setAgrupamiento(AgrupamientosVO agrupamiento) {
		this.agrupamiento = agrupamiento;
	}
	/**
	 * @return the sector
	 */
	public SectorVO getSector() {
		return sector;
	}
	/**
	 * @param sector the sector to set
	 */
	public void setSector(SectorVO sector) {
		this.sector = sector;
	}
	/**
	 * @return the empPlaca
	 */
	public String getEmpPlaca() {
		return empPlaca;
	}
	/**
	 * @param empPlaca the empPlaca to set
	 */
	public void setEmpPlaca(String empPlaca) {
		this.empPlaca = empPlaca;
	}
}
