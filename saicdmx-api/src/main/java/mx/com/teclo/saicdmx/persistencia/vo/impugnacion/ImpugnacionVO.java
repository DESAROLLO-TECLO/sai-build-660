package mx.com.teclo.saicdmx.persistencia.vo.impugnacion;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonAutoDetect
@JsonIgnoreProperties(ignoreUnknown = true)
public class ImpugnacionVO implements Serializable{
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 5654431815517164175L;
	
	private Long impugnacionId;
	private String impugnacionOficioJur;	
	private String impugnacionJuicio;
	private String  actorApellidoPaterno; 	
	private String  actorApellidoMaterno;	
	private String   actorNombre;	
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy HH:mm:ss ", timezone="America/Mexico_City")
	private Date fechaRecepcion;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy HH:mm:ss ", timezone="America/Mexico_City")
	private Date fechaCreacion;
	private String impugnacionConstanciaCump;
	private String referenciaSeg;
	private Long creadoPor;
	private Long modificadorPor;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy HH:mm:ss ", timezone="America/Mexico_City")
	private Date ultimaModificacion;
	private String referencia;
	private String resultado;
	private String mensaje;

	public Long getImpugnacionId() {
		return impugnacionId;
	}

	public void setImpugnacionId(Long impugnacionId) {
		this.impugnacionId = impugnacionId;
	}

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

	public Date getFechaRecepcion() {
		return fechaRecepcion;
	}

	public void setFechaRecepcion(Date fechaRecepcion) {
		this.fechaRecepcion = fechaRecepcion;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public String getImpugnacionConstanciaCump() {
		return impugnacionConstanciaCump;
	}

	public void setImpugnacionConstanciaCump(String impugnacionConstanciaCump) {
		this.impugnacionConstanciaCump = impugnacionConstanciaCump;
	}

	public String getReferenciaSeg() {
		return referenciaSeg;
	}

	public void setReferenciaSeg(String referenciaSeg) {
		this.referenciaSeg = referenciaSeg;
	}

	public Long getCreadoPor() {
		return creadoPor;
	}

	public void setCreadoPor(Long creadoPor) {
		this.creadoPor = creadoPor;
	}

	public Long getModificadorPor() {
		return modificadorPor;
	}

	public void setModificadorPor(Long modificadorPor) {
		this.modificadorPor = modificadorPor;
	}

	public Date getUltimaModificacion() {
		return ultimaModificacion;
	}

	public void setUltimaModificacion(Date ultimaModificacion) {
		this.ultimaModificacion = ultimaModificacion;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
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

}
