package mx.com.teclo.saicdmx.persistencia.vo.impugnacion;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonAutoDetect
@JsonIgnoreProperties(ignoreUnknown = true)
public class ImpugnacionAltaVO {
	
	
	private String impugnacionOficioJur;	
	private String impugnacionJuicio;
	private String actorApellidoPaterno; 	
	private String actorApellidoMaterno;	
	private String actorNombre;	
	private String fechaRecepcion;
	private Long   empleadoId;
	private String impugnacionConstanciaCump;
	private String referencia;
	private String resultado;
	private String mensaje;
	
	public String getImpugnacionOficioJur() {
		return impugnacionOficioJur;
	}
	public void setImpugnacionOficioJur(String impugnacionOficioJur) {
		this.impugnacionOficioJur = impugnacionOficioJur;
	}
	public String getImpugnacionJuicio() {
		return impugnacionJuicio;
	}
	public void setImpugnacionJuicio(String impugnacionJuicio) {
		this.impugnacionJuicio = impugnacionJuicio;
	}
	public String getActorApellidoPaterno() {
		return actorApellidoPaterno;
	}
	public void setActorApellidoPaterno(String actorApellidoPaterno) {
		this.actorApellidoPaterno = actorApellidoPaterno;
	}
	public String getActorApellidoMaterno() {
		return actorApellidoMaterno;
	}
	public void setActorApellidoMaterno(String actorApellidoMaterno) {
		this.actorApellidoMaterno = actorApellidoMaterno;
	}
	public String getActorNombre() {
		return actorNombre;
	}
	public void setActorNombre(String actorNombre) {
		this.actorNombre = actorNombre;
	}
	public String getFechaRecepcion() {
		return fechaRecepcion;
	}
	public void setFechaRecepcion(String fechaRecepcion) {
		this.fechaRecepcion = fechaRecepcion;
	}
	public Long getEmpleadoId() {
		return empleadoId;
	}
	public void setEmpleadoId(Long empleadoId) {
		this.empleadoId = empleadoId;
	}
	public String getImpugnacionConstanciaCump() {
		return impugnacionConstanciaCump;
	}
	public void setImpugnacionConstanciaCump(String impugnacionConstanciaCump) {
		this.impugnacionConstanciaCump = impugnacionConstanciaCump;
	}
	public String getReferencia() {
		return referencia;
	}
	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}
	public String getResultado() {
		return resultado;
	}
	public void setResultado(String resultado) {
		this.resultado = resultado;
	}
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	

}
