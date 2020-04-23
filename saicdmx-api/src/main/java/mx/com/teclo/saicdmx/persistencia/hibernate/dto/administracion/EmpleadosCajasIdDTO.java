package mx.com.teclo.saicdmx.persistencia.hibernate.dto.administracion;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class EmpleadosCajasIdDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 858232078463909367L;

	@Basic(optional = false)
	@Column(name = "CAJA_ID", nullable = false, precision = 11, scale = 0)
	private Long cajaId;

	@Basic(optional = false)
	@Column(name = "EMP_ID", nullable = false, precision = 11, scale = 0)
	private Long empId;

	public EmpleadosCajasIdDTO() {
	}

	public EmpleadosCajasIdDTO(Long cajaId, Long empId) {
		this.cajaId = cajaId;
		this.empId = empId;
	}

	public Long getCajaId() {
		return cajaId;
	}

	public void setCajaId(Long cajaId) {
		this.cajaId = cajaId;
	}

	public Long getEmpId() {
		return empId;
	}

	public void setEmpId(Long empId) {
		this.empId = empId;
	}

}
