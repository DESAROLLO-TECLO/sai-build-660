package mx.com.teclo.saicdmx.persistencia.vo.caja;

import java.io.Serializable;

public class VBuscarCorteCaja implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1682841479060732779L;
	
	private String empId;
	private String cajaId;
	private String corteId;
	
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public String getCajaId() {
		return cajaId;
	}
	public void setCajaId(String cajaId) {
		this.cajaId = cajaId;
	}
	public String getCorteId() {
		return corteId;
	}
	public void setCorteId(String corteId) {
		this.corteId = corteId;
	}
	
	

}
