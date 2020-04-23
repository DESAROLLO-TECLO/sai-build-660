package mx.com.teclo.saicdmx.persistencia.vo.pagos;

import java.io.Serializable;

public class RespuestaCentroPagosVO implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String operacionNumero;
	private String operacionRespuesta;
	private String referencia;
	private String numeroAutorizacion;
	private String importe;
	private String operacionFechaHora;
	private String operacionFecha;
	private String tarjetaNumero;
	private String tarjetaNombreUsuario;
	private boolean pagoAprobado;
	
	public String getOperacionNumero() {
		return operacionNumero;
	}
	public void setOperacionNumero(String operacionNumero) {
		this.operacionNumero = operacionNumero;
	}
	public String getOperacionRespuesta() {
		return operacionRespuesta;
	}
	public void setOperacionRespuesta(String operacionRespuesta) {
		this.operacionRespuesta = operacionRespuesta;
	}
	public String getReferencia() {
		return referencia;
	}
	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}
	public String getNumeroAutorizacion() {
		return numeroAutorizacion;
	}
	public void setNumeroAutorizacion(String numeroAutorizacion) {
		this.numeroAutorizacion = numeroAutorizacion;
	}
	public String getImporte() {
		return importe;
	}
	public void setImporte(String importe) {
		this.importe = importe;
	}
	public String getOperacionFechaHora() {
		return operacionFechaHora;
	}
	public void setOperacionFechaHora(String operacionFechaHora) {
		this.operacionFechaHora = operacionFechaHora;
	}
	public String getOperacionFecha() {
		return operacionFecha;
	}
	public void setOperacionFecha(String operacionFecha) {
		this.operacionFecha = operacionFecha;
	}
	public String getTarjetaNumero() {
		return tarjetaNumero;
	}
	public void setTarjetaNumero(String tarjetaNumero) {
		this.tarjetaNumero = tarjetaNumero;
	}
	public String getTarjetaNombreUsuario() {
		return tarjetaNombreUsuario;
	}
	public void setTarjetaNombreUsuario(String tarjetaNombreUsuario) {
		this.tarjetaNombreUsuario = tarjetaNombreUsuario;
	}
	public boolean isPagoAprobado() {
		return pagoAprobado;
	}
	public void setPagoAprobado(boolean pagoAprobado) {
		this.pagoAprobado = pagoAprobado;
	}
	
	
	
}
