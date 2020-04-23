package mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class DepositosEmpleadosId implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3102255459233497908L;
	
	@Basic(optional = false)
	@Column(name = "EMP_ID", nullable = false)
	private Long empId;
	
	@Basic(optional = false)
	@Column(name = "DEP_ID", nullable = false)
	private Long depId;

	public Long getEmpId() {
		return empId;
	}

	public void setEmpId(Long empId) {
		this.empId = empId;
	}

	public Long getDepId() {
		return depId;
	}

	public void setDepId(Long depId) {
		this.depId = depId;
	}
	
}
