package mx.com.teclo.saicdmx.persistencia.vo.caja;

import java.io.Serializable;

public class VCajaSinCorteHistVO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6737814150796318303L;
	
	private String cajaCod;
	private String cajaId;
	private String empId;
	private String empNombreComp;
	private String empPlaca;
	private String empTipo;
	private String fecha;
	private String filtroFechaI;
	private String filtroFechaF;
	private String infracNum;
	private String pagoTotal;
	private String perfilId;
	private String perfilNombre;
	private String totalCount;

	public String getCajaCod() {
		return cajaCod;
	}
	public void setCajaCod(String cajaCod) {
		this.cajaCod = cajaCod;
	}
	public String getCajaId() {
		return cajaId;
	}
	public void setCajaId(String cajaId) {
		this.cajaId = cajaId;
	}
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public String getEmpNombreComp() {
		return empNombreComp;
	}
	public void setEmpNombreComp(String empNombreComp) {
		this.empNombreComp = empNombreComp;
	}
	public String getEmpPlaca() {
		return empPlaca;
	}
	public void setEmpPlaca(String empPlaca) {
		this.empPlaca = empPlaca;
	}
	public String getEmpTipo() {
		return empTipo;
	}
	public void setEmpTipo(String empTipo) {
		this.empTipo = empTipo;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public String getInfracNum() {
		return infracNum;
	}
	public void setInfracNum(String infracNum) {
		this.infracNum = infracNum;
	}
	public String getPagoTotal() {
		return pagoTotal;
	}
	public void setPagoTotal(String pagoTotal) {
		this.pagoTotal = pagoTotal;
	}
	public String getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(String totalCount) {
		this.totalCount = totalCount;
	}
	public String getPerfilId() {
		return perfilId;
	}
	public void setPerfilId(String perfilId) {
		this.perfilId = perfilId;
	}
	public String getPerfilNombre() {
		return perfilNombre;
	}
	public void setPerfilNombre(String perfilNombre) {
		this.perfilNombre = perfilNombre;
	}
	public String getFiltroFechaI() {
		return filtroFechaI;
	}
	public void setFiltroFechaI(String filtroFechaI) {
		this.filtroFechaI = filtroFechaI;
	}
	public String getFiltroFechaF() {
		return filtroFechaF;
	}
	public void setFiltroFechaF(String filtroFechaF) {
		this.filtroFechaF = filtroFechaF;
	}
}