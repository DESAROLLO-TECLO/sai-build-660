package mx.com.teclo.saicdmx.persistencia.hibernate.vo.catalogos;

import java.io.Serializable;

import mx.com.teclo.saicdmx.persistencia.vo.empleados.EmpleadosVO;

public class DepositosEmpleadosVO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3191730410299816502L;
	
	private DepositosEmpleadosVOId depId;
	private EmpleadosVO empleado;
	private DepositosVO depositos;
	private String depEmpStatus;
	
	public DepositosEmpleadosVOId getDepId() {
		return depId;
	}
	public void setDepId(DepositosEmpleadosVOId depId) {
		this.depId = depId;
	}
	public EmpleadosVO getEmpleado() {
		return empleado;
	}
	public void setEmpleado(EmpleadosVO empleado) {
		this.empleado = empleado;
	}
	public DepositosVO getDepositos() {
		return depositos;
	}
	public void setDepositos(DepositosVO depositos) {
		this.depositos = depositos;
	}
	public String getDepEmpStatus() {
		return depEmpStatus;
	}
	public void setDepEmpStatus(String depEmpStatus) {
		this.depEmpStatus = depEmpStatus;
	}
	
}
