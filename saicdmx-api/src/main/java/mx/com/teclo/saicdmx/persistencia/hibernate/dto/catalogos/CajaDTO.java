package mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import mx.com.teclo.saicdmx.persistencia.hibernate.dto.empleados.EmpleadosDTO;

@Entity
@Table(name="CAJAS")
public class CajaDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4732962904110022069L;

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "CAJA_ID", unique = true, nullable = false)
	private Long cajaId;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumns({
        @JoinColumn(name="DEP_ID", referencedColumnName="DEP_ID"),
        @JoinColumn(name="EMP_ID", referencedColumnName="EMP_ID")
    })
	private DepositosEmpleadosDTO depEmp;

	public DepositosEmpleadosDTO getDepEmp() {
		return depEmp;
	}

	public void setDepEmp(DepositosEmpleadosDTO depEmp) {
		this.depEmp = depEmp;
	}

	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name="EMP_ID", referencedColumnName="EMP_ID", insertable=false, updatable=false)
	private EmpleadosDTO empleado;
	
	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name="DEP_ID", referencedColumnName="DEP_ID", insertable=false, updatable=false)
	private DepositosDTO deposito;
	
	@Column(name = "CAJA_COD")
	private String cajaCod;
	@Column(name = "CAJA_NOMBRE")
	private String cajaNombre;
	@Column(name = "CAJA_NUM_PAGO")
	private Long cajaNumPago;
	@Column(name = "CAJA_NUM_TRAN")
	private Long cajaNumTran;
	@Column(name = "CAJA_NUM_CORTE")
	private Long cajaNumCorte;
	@Column(name = "CAJA_STATUS")
	private String cajaStatus;
	@Column(name = "CREADO_POR")
	private Long creadoPor;
	@Column(name = "FECHA_CREACION")
	private Date fechaCreacion;
	@Column(name = "MODIFICADO_POR")
	private Long modificadoPor;
	@Column(name = "ULTIMA_MODIFICACION")
	private Date ultimaModificacion;
	@Column(name = "AUTORIZADA_P_COBRO")
	private Long autorizadaPCobro;

	/**
	 * @return the cajaId
	 */
	public Long getCajaId() {
		return cajaId;
	}

	/**
	 * @param cajaId the cajaId to set
	 */
	public void setCajaId(Long cajaId) {
		this.cajaId = cajaId;
	}

	public EmpleadosDTO getEmpleado() {
		return empleado;
	}

	public void setEmpleado(EmpleadosDTO empleado) {
		this.empleado = empleado;
	}

	public DepositosDTO getDeposito() {
		return deposito;
	}

	public void setDeposito(DepositosDTO deposito) {
		this.deposito = deposito;
	}

	/**
	 * @return the cajaCod
	 */
	public String getCajaCod() {
		return cajaCod;
	}

	/**
	 * @param cajaCod the cajaCod to set
	 */
	public void setCajaCod(String cajaCod) {
		this.cajaCod = cajaCod;
	}

	/**
	 * @return the cajaNombre
	 */
	public String getCajaNombre() {
		return cajaNombre;
	}

	/**
	 * @param cajaNombre the cajaNombre to set
	 */
	public void setCajaNombre(String cajaNombre) {
		this.cajaNombre = cajaNombre;
	}

	/**
	 * @return the cajaNumPago
	 */
	public Long getCajaNumPago() {
		return cajaNumPago;
	}

	/**
	 * @param cajaNumPago the cajaNumPago to set
	 */
	public void setCajaNumPago(Long cajaNumPago) {
		this.cajaNumPago = cajaNumPago;
	}

	/**
	 * @return the cajaNumTran
	 */
	public Long getCajaNumTran() {
		return cajaNumTran;
	}

	/**
	 * @param cajaNumTran the cajaNumTran to set
	 */
	public void setCajaNumTran(Long cajaNumTran) {
		this.cajaNumTran = cajaNumTran;
	}

	/**
	 * @return the cajaNumCorte
	 */
	public Long getCajaNumCorte() {
		return cajaNumCorte;
	}

	/**
	 * @param cajaNumCorte the cajaNumCorte to set
	 */
	public void setCajaNumCorte(Long cajaNumCorte) {
		this.cajaNumCorte = cajaNumCorte;
	}

	/**
	 * @return the cajaStatus
	 */
	public String getCajaStatus() {
		return cajaStatus;
	}

	/**
	 * @param cajaStatus the cajaStatus to set
	 */
	public void setCajaStatus(String cajaStatus) {
		this.cajaStatus = cajaStatus;
	}

	/**
	 * @return the creadoPor
	 */
	public Long getCreadoPor() {
		return creadoPor;
	}

	/**
	 * @param creadoPor the creadoPor to set
	 */
	public void setCreadoPor(Long creadoPor) {
		this.creadoPor = creadoPor;
	}

	/**
	 * @return the fechaCreacion
	 */
	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	/**
	 * @param fechaCreacion the fechaCreacion to set
	 */
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	/**
	 * @return the modificadoPor
	 */
	public Long getModificadoPor() {
		return modificadoPor;
	}

	/**
	 * @param modificadoPor the modificadoPor to set
	 */
	public void setModificadoPor(Long modificadoPor) {
		this.modificadoPor = modificadoPor;
	}

	/**
	 * @return the ultimaModificacion
	 */
	public Date getUltimaModificacion() {
		return ultimaModificacion;
	}

	/**
	 * @param ultimaModificacion the ultimaModificacion to set
	 */
	public void setUltimaModificacion(Date ultimaModificacion) {
		this.ultimaModificacion = ultimaModificacion;
	}

	/**
	 * @return the autorizadaPCobro
	 */
	public Long getAutorizadaPCobro() {
		return autorizadaPCobro;
	}

	/**
	 * @param autorizadaPCobro the autorizadaPCobro to set
	 */
	public void setAutorizadaPCobro(Long autorizadaPCobro) {
		this.autorizadaPCobro = autorizadaPCobro;
	}
}
