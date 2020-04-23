package mx.com.teclo.saicdmx.persistencia.hibernate.dto.garantias;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "V_GARANTIAS_SIN_PROCESAR")
public class GarantiaVwSinProcesarDTO implements Serializable {
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "infrac_num", unique = true, nullable = false)
	private String infracNum;
	@Column(name = "emp_placa")
	private String empPlaca;
	@Column(name = "emp_id")
	private Long empId;
	@Column(name = "garantia_tipo_id")
	private Long garantiaTipoId;
	@Column(name = "garantia_tipo")
	private String garantiaTipo;
	@Column(name = "placa")
	private String placa;
	@Column(name = "licencia")
	private String licencia;
	@Column(name = "tarjeta_circulacion")
	private String tarjetaCirculacion;

	/**
	 * @return the infracNum
	 */
	public String getInfracNum() {
		return infracNum;
	}

	/**
	 * @param infracNum
	 *            the infracNum to set
	 */
	public void setInfracNum(String infracNum) {
		this.infracNum = infracNum;
	}

	/**
	 * @return the empPlaca
	 */
	public String getEmpPlaca() {
		return empPlaca;
	}

	/**
	 * @param empPlaca
	 *            the empPlaca to set
	 */
	public void setEmpPlaca(String empPlaca) {
		this.empPlaca = empPlaca;
	}

	/**
	 * @return the empId
	 */
	public Long getEmpId() {
		return empId;
	}

	/**
	 * @param empId
	 *            the empId to set
	 */
	public void setEmpId(Long empId) {
		this.empId = empId;
	}

	/**
	 * @return the garantiaTipoId
	 */
	public Long getGarantiaTipoId() {
		return garantiaTipoId;
	}

	/**
	 * @param garantiaTipoId
	 *            the garantiaTipoId to set
	 */
	public void setGarantiaTipoId(Long garantiaTipoId) {
		this.garantiaTipoId = garantiaTipoId;
	}

	/**
	 * @return the garantiaTipo
	 */
	public String getGarantiaTipo() {
		return garantiaTipo;
	}

	/**
	 * @param garantiaTipo
	 *            the garantiaTipo to set
	 */
	public void setGarantiaTipo(String garantiaTipo) {
		this.garantiaTipo = garantiaTipo;
	}

	/**
	 * @return the placa
	 */
	public String getPlaca() {
		return placa;
	}

	/**
	 * @param placa
	 *            the placa to set
	 */
	public void setPlaca(String placa) {
		this.placa = placa;
	}

	/**
	 * @return the licencia
	 */
	public String getLicencia() {
		return licencia;
	}

	/**
	 * @param licencia
	 *            the licencia to set
	 */
	public void setLicencia(String licencia) {
		this.licencia = licencia;
	}

	/**
	 * @return the tarjetaCirculacion
	 */
	public String getTarjetaCirculacion() {
		return tarjetaCirculacion;
	}

	/**
	 * @param tarjetaCirculacion
	 *            the tarjetaCirculacion to set
	 */
	public void setTarjetaCirculacion(String tarjetaCirculacion) {
		this.tarjetaCirculacion = tarjetaCirculacion;
	}

}
