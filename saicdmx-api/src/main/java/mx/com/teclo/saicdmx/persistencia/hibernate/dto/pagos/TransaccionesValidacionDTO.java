package mx.com.teclo.saicdmx.persistencia.hibernate.dto.pagos;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "TRANSACCIONES_VALIDACION")
public class TransaccionesValidacionDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "TRAN_VALIDA_ID", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long tranValidaId;
	
	@Column(name = "FECHA_VALIDACION")
	private Date fechaValidacion;
	
	@Column(name = "ACTIVO")
	private String Activo;
	
	@Column(name = "TRAN_ID")
	private Long tranId;
	
	@Column(name = "CAJA_ID")
	private Long cajaId;
	
	@Column(name = "EMP_ID")
	private Long empId;
	
	@Column(name = "MONTO_IGUAL_PAGO")
	private Integer montoPagoIgual;
	
	@Column(name = "MONTO_IGUAL_DET_PAG")
	private Integer montoDetPagoIgual;
	
	@Column(name = "MONTO_IGUAL_DET_CARG")
	private Integer montoDetCargosIgual;
	
	@Column(name = "TIPO_OPERACION")
	private String tipoOperacion;
	
	@Column(name = "TRAN_IMPORTE_ACTUALIZADO")
	private Integer tranImporteActualizado;
	
	@Column(name = "TRAN_NOMBRE_ACTUALIZADO")
	private Integer tranNombreActualizado;
	
	@Column(name = "TRAN_TARJETA_ACTUALIZADO")
	private Integer tranTarjetaActualizado;
	
	@Column(name = "TRAN_LINEA_BAN_ACTUALIZADO")
	private Integer tranLineaBancoActualizado;
	
	@Column(name = "TRAN_ORDEN_ACTUALIZADO")
	private Integer tranOrdenActualizado;
	
	@Column(name = "TRAN_RESPUESTA_ACTUALIZADO")
	private Integer tranRespuestaActualizado;
	
	@Column(name = "TRAN_NUM_AUTORIZA_ACTUALIZADO")
	private Integer numAutorizacionActualizado;
	
	public Long getTranValidaId() {
		return tranValidaId;
	}
	public void setTranValidaId(Long tranValidaId) {
		this.tranValidaId = tranValidaId;
	}
	public Date getFechaValidacion() {
		return fechaValidacion;
	}
	public void setFechaValidacion(Date fechaValidacion) {
		this.fechaValidacion = fechaValidacion;
	}
	public String getActivo() {
		return Activo;
	}
	public void setActivo(String activo) {
		Activo = activo;
	}
	public Long getTranId() {
		return tranId;
	}
	public void setTranId(Long tranId) {
		this.tranId = tranId;
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
	public Integer getMontoPagoIgual() {
		return montoPagoIgual;
	}
	public void setMontoPagoIgual(Integer montoPagoIgual) {
		this.montoPagoIgual = montoPagoIgual;
	}
	public Integer getMontoDetPagoIgual() {
		return montoDetPagoIgual;
	}
	public void setMontoDetPagoIgual(Integer montoDetPagoIgual) {
		this.montoDetPagoIgual = montoDetPagoIgual;
	}
	public Integer getMontoDetCargosIgual() {
		return montoDetCargosIgual;
	}
	public void setMontoDetCargosIgual(Integer montoDetCargosIgual) {
		this.montoDetCargosIgual = montoDetCargosIgual;
	}
	public String getTipoOperacion() {
		return tipoOperacion;
	}
	public void setTipoOperacion(String tipoOperacion) {
		this.tipoOperacion = tipoOperacion;
	}
	public Integer getTranImporteActualizado() {
		return tranImporteActualizado;
	}
	public void setTranImporteActualizado(Integer tranImporteActualizado) {
		this.tranImporteActualizado = tranImporteActualizado;
	}
	public Integer getTranNombreActualizado() {
		return tranNombreActualizado;
	}
	public void setTranNombreActualizado(Integer tranNombreActualizado) {
		this.tranNombreActualizado = tranNombreActualizado;
	}
	public Integer getTranTarjetaActualizado() {
		return tranTarjetaActualizado;
	}
	public void setTranTarjetaActualizado(Integer tranTarjetaActualizado) {
		this.tranTarjetaActualizado = tranTarjetaActualizado;
	}
	public Integer getTranLineaBancoActualizado() {
		return tranLineaBancoActualizado;
	}
	public void setTranLineaBancoActualizado(Integer tranLineaBancoActualizado) {
		this.tranLineaBancoActualizado = tranLineaBancoActualizado;
	}
	public Integer getTranOrdenActualizado() {
		return tranOrdenActualizado;
	}
	public void setTranOrdenActualizado(Integer tranOrdenActualizado) {
		this.tranOrdenActualizado = tranOrdenActualizado;
	}
	public Integer getTranRespuestaActualizado() {
		return tranRespuestaActualizado;
	}
	public void setTranRespuestaActualizado(Integer tranRespuestaActualizado) {
		this.tranRespuestaActualizado = tranRespuestaActualizado;
	}
	public Integer getNumAutorizacionActualizado() {
		return numAutorizacionActualizado;
	}
	public void setNumAutorizacionActualizado(Integer numAutorizacionActualizado) {
		this.numAutorizacionActualizado = numAutorizacionActualizado;
	}
}
