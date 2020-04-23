package mx.com.teclo.saicdmx.persistencia.hibernate.dto.cajas;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "V_CAJA_EXT_DESACTIVAR")
public class VCajaExtDesactivarDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8078866112507864510L;
	
	@Id
	@Column(name = "CAJA_ID", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long cajaId;
	
	@Column(name = "CAJA")
	private String cajaCod;
	
	@Column(name = "EMP_PLACA")
	private String empPlaca;
	
	@Column(name = "EMPLEADO_NOMBRE")
	private String empNombre;
	
	public Long getCajaId() {
		return cajaId;
	}
	public void setCajaId(Long cajaId) {
		this.cajaId = cajaId;
	}
	public String getCajaCod() {
		return cajaCod;
	}
	public void setCajaCod(String cajaCod) {
		this.cajaCod = cajaCod;
	}
	public String getEmpPlaca() {
		return empPlaca;
	}
	public void setEmpPlaca(String empPlaca) {
		this.empPlaca = empPlaca;
	}
	public String getEmpNombre() {
		return empNombre;
	}
	public void setEmpNombre(String empNombre) {
		this.empNombre = empNombre;
	}
}
