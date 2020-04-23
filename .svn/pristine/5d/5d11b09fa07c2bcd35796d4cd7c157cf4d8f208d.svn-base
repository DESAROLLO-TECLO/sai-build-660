package mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import mx.com.teclo.saicdmx.persistencia.hibernate.dto.empleados.EmpleadosDTO;

@Entity
@Table(name="DEPOSITOS_EMPLEADOS")
public class DepositosEmpleadosDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4590117995616530231L;
	
	@EmbeddedId
	private DepositosEmpleadosId depId;
	
	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name="EMP_ID", referencedColumnName="EMP_ID", insertable=false, updatable=false)
	private EmpleadosDTO empleado;
	
	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name="DEP_ID", referencedColumnName="DEP_ID", insertable=false, updatable=false)
	private DepositosDTO depositos;
	
	@Column(name = "DEP_EMP_STATUS")
	private String depEmpStatus;
	
	@Column(name = "CREADO_POR")
	private Long creadoPor;
	
	@Column(name = "FECHA_CREACION")
	private Date fechaCreacion;
	
	@Column(name = "MODIFICADO_POR")
	private Long modificadoPor;
	
	@Column(name = "ULTIMA_MODIFICACION")
	private Date ultimaMod;

	public DepositosEmpleadosId getDepId() {
		return depId;
	}

	public void setDepId(DepositosEmpleadosId depId) {
		this.depId = depId;
	}

	public EmpleadosDTO getEmpleado() {
		return empleado;
	}

	public void setEmpleado(EmpleadosDTO empleado) {
		this.empleado = empleado;
	}

	public DepositosDTO getDepositos() {
		return depositos;
	}

	public void setDepositos(DepositosDTO depositos) {
		this.depositos = depositos;
	}

	public String getDepEmpStatus() {
		return depEmpStatus;
	}

	public void setDepEmpStatus(String depEmpStatus) {
		this.depEmpStatus = depEmpStatus;
	}

	public Long getCreadoPor() {
		return creadoPor;
	}

	public void setCreadoPor(Long creadoPor) {
		this.creadoPor = creadoPor;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Long getModificadoPor() {
		return modificadoPor;
	}

	public void setModificadoPor(Long modificadoPor) {
		this.modificadoPor = modificadoPor;
	}

	public Date getUltimaMod() {
		return ultimaMod;
	}

	public void setUltimaMod(Date ultimaMod) {
		this.ultimaMod = ultimaMod;
	}
	
}
