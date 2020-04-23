package mx.com.teclo.saicdmx.persistencia.vo.empleados;

import java.io.Serializable;
import java.util.Date;

import mx.com.teclo.saicdmx.persistencia.hibernate.vo.catalogos.AgrupamientosVO;
import mx.com.teclo.saicdmx.persistencia.hibernate.vo.catalogos.SectorVO;
import mx.com.teclo.saicdmx.persistencia.hibernate.vo.catalogos.TipoEmpleadoVO;


public class EmpleadosSinPsswdVO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1729254665276583965L;
	
	private Long empId;
	private String empCod;
	private String empPlaca;
	private TipoEmpleadoVO empTip;
	private String empRFC;
	private AgrupamientosVO agrupamiento;
	private SectorVO sector;
	private String empApePaterno;
	private String empApeMaterno;
	private String empNombre;
	private String empStatus;
	public Long getEmpId() {
		return empId;
	}
	public void setEmpId(Long empId) {
		this.empId = empId;
	}
	public String getEmpCod() {
		return empCod;
	}
	public void setEmpCod(String empCod) {
		this.empCod = empCod;
	}
	public String getEmpPlaca() {
		return empPlaca;
	}
	public void setEmpPlaca(String empPlaca) {
		this.empPlaca = empPlaca;
	}
	public TipoEmpleadoVO getEmpTip() {
		return empTip;
	}
	public void setEmpTip(TipoEmpleadoVO empTip) {
		this.empTip = empTip;
	}
	public String getEmpRFC() {
		return empRFC;
	}
	public void setEmpRFC(String empRFC) {
		this.empRFC = empRFC;
	}
	public AgrupamientosVO getAgrupamiento() {
		return agrupamiento;
	}
	public void setAgrupamiento(AgrupamientosVO agrupamiento) {
		this.agrupamiento = agrupamiento;
	}
	public SectorVO getSector() {
		return sector;
	}
	public void setSector(SectorVO sector) {
		this.sector = sector;
	}
	public String getEmpApePaterno() {
		return empApePaterno;
	}
	public void setEmpApePaterno(String empApePaterno) {
		this.empApePaterno = empApePaterno;
	}
	public String getEmpApeMaterno() {
		return empApeMaterno;
	}
	public void setEmpApeMaterno(String empApeMaterno) {
		this.empApeMaterno = empApeMaterno;
	}
	public String getEmpNombre() {
		return empNombre;
	}
	public void setEmpNombre(String empNombre) {
		this.empNombre = empNombre;
	}
	public String getEmpStatus() {
		return empStatus;
	}
	public void setEmpStatus(String empStatus) {
		this.empStatus = empStatus;
	}
}
