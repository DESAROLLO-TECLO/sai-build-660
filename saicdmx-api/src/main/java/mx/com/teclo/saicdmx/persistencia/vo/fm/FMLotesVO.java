package mx.com.teclo.saicdmx.persistencia.vo.fm;

import java.util.Date;

import javax.persistence.Column;

public class FMLotesVO {
	
	private Long id;
	private String nombre;
	private Date fechaEmision;
	private Integer estatusProcesoId;
	private Boolean enProceso;
	private Integer origenLote;
	private Integer idTipoDeteccion;
	private Boolean cancelado;
	private Long creadoPor;
	private Long modificadoPor;
	private Date ultimaModificacion;
	private Integer archivoComplementadas;
	private Integer archivoRechazadas;
	private Date fechaCreacion;
	private String anioSalarioMinimo;
	private String motivoCancelacion;
	private Integer archivoTipo;
	private Integer archivoTipoProceso;
	private Integer consecutivoArchivoSF;
	private Boolean libetado;
	private Date fechaLiberacion;
	private Boolean complementado;
	private Date fechaComplementado;
//	private Date fechaIniVencimientoLC;
//	private Date fechaFinVencimientoLC;
	private Date fechaImposicion;
	private Integer cantidadProcesados;
	private String infracNumInicial;
	private String infracNumFinal;
	private Integer cantidadCancelados;
	private Integer salariosMinimosId;
//	private Integer radarComplementadoUT;
	private Integer responsableProceso;
	private String tipoDeteccion;
	private String marca;
	private String origenPlaca;
	private String cantidadDetecciones;
	private Integer idTipoPersona;
	private Integer stLCaptura;
	private String tipoarchivo;
	private Integer stVCP;
	private Date fhIniPeriodo;
	private Date fhFinPeriodo;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Date getFechaEmision() {
		return fechaEmision;
	}
	public void setFechaEmision(Date fechaEmision) {
		this.fechaEmision = fechaEmision;
	}
	public Integer getEstatusProcesoId() {
		return estatusProcesoId;
	}
	public void setEstatusProcesoId(Integer estatusProcesoId) {
		this.estatusProcesoId = estatusProcesoId;
	}
	public Boolean getEnProceso() {
		return enProceso;
	}
	public void setEnProceso(Boolean enProceso) {
		this.enProceso = enProceso;
	}
	public Integer getOrigenLote() {
		return origenLote;
	}
	public void setOrigenLote(Integer origenLote) {
		this.origenLote = origenLote;
	}
	public Integer getIdTipoDeteccion() {
		return idTipoDeteccion;
	}
	public void setIdTipoDeteccion(Integer idTipoDeteccion) {
		this.idTipoDeteccion = idTipoDeteccion;
	}
	public Boolean getCancelado() {
		return cancelado;
	}
	public void setCancelado(Boolean cancelado) {
		this.cancelado = cancelado;
	}
	public Long getCreadoPor() {
		return creadoPor;
	}
	public void setCreadoPor(Long creadoPor) {
		this.creadoPor = creadoPor;
	}
	public Long getModificadoPor() {
		return modificadoPor;
	}
	public void setModificadoPor(Long modificadoPor) {
		this.modificadoPor = modificadoPor;
	}
	public Date getUltimaModificacion() {
		return ultimaModificacion;
	}
	public void setUltimaModificacion(Date ultimaModificacion) {
		this.ultimaModificacion = ultimaModificacion;
	}
	public Integer getArchivoComplementadas() {
		return archivoComplementadas;
	}
	public void setArchivoComplementadas(Integer archivoComplementadas) {
		this.archivoComplementadas = archivoComplementadas;
	}
	public Integer getArchivoRechazadas() {
		return archivoRechazadas;
	}
	public void setArchivoRechazadas(Integer archivoRechazadas) {
		this.archivoRechazadas = archivoRechazadas;
	}
	public Date getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	public String getAnioSalarioMinimo() {
		return anioSalarioMinimo;
	}
	public void setAnioSalarioMinimo(String anioSalarioMinimo) {
		this.anioSalarioMinimo = anioSalarioMinimo;
	}
	public String getMotivoCancelacion() {
		return motivoCancelacion;
	}
	public void setMotivoCancelacion(String motivoCancelacion) {
		this.motivoCancelacion = motivoCancelacion;
	}
	public Integer getArchivoTipo() {
		return archivoTipo;
	}
	public void setArchivoTipo(Integer archivoTipo) {
		this.archivoTipo = archivoTipo;
	}
	public Integer getArchivoTipoProceso() {
		return archivoTipoProceso;
	}
	public void setArchivoTipoProceso(Integer archivoTipoProceso) {
		this.archivoTipoProceso = archivoTipoProceso;
	}
	public Integer getConsecutivoArchivoSF() {
		return consecutivoArchivoSF;
	}
	public void setConsecutivoArchivoSF(Integer consecutivoArchivoSF) {
		this.consecutivoArchivoSF = consecutivoArchivoSF;
	}
	public Boolean getLibetado() {
		return libetado;
	}
	public void setLibetado(Boolean libetado) {
		this.libetado = libetado;
	}
	public Date getFechaLiberacion() {
		return fechaLiberacion;
	}
	public void setFechaLiberacion(Date fechaLiberacion) {
		this.fechaLiberacion = fechaLiberacion;
	}
	public Boolean getComplementado() {
		return complementado;
	}
	public void setComplementado(Boolean complementado) {
		this.complementado = complementado;
	}
	public Date getFechaComplementado() {
		return fechaComplementado;
	}
	public void setFechaComplementado(Date fechaComplementado) {
		this.fechaComplementado = fechaComplementado;
	}
//	public Date getFechaIniVencimientoLC() {
//		return fechaIniVencimientoLC;
//	}
//	public void setFechaIniVencimientoLC(Date fechaIniVencimientoLC) {
//		this.fechaIniVencimientoLC = fechaIniVencimientoLC;
//	}
//	public Date getFechaFinVencimientoLC() {
//		return fechaFinVencimientoLC;
//	}
//	public void setFechaFinVencimientoLC(Date fechaFinVencimientoLC) {
//		this.fechaFinVencimientoLC = fechaFinVencimientoLC;
//	}
	public Date getFechaImposicion() {
		return fechaImposicion;
	}
	public void setFechaImposicion(Date fechaImposicion) {
		this.fechaImposicion = fechaImposicion;
	}
	public Integer getCantidadProcesados() {
		return cantidadProcesados;
	}
	public void setCantidadProcesados(Integer cantidadProcesados) {
		this.cantidadProcesados = cantidadProcesados;
	}
	public String getInfracNumInicial() {
		return infracNumInicial;
	}
	public void setInfracNumInicial(String infracNumInicial) {
		this.infracNumInicial = infracNumInicial;
	}
	public String getInfracNumFinal() {
		return infracNumFinal;
	}
	public void setInfracNumFinal(String infracNumFinal) {
		this.infracNumFinal = infracNumFinal;
	}
	public Integer getCantidadCancelados() {
		return cantidadCancelados;
	}
	public void setCantidadCancelados(Integer cantidadCancelados) {
		this.cantidadCancelados = cantidadCancelados;
	}
	public Integer getSalariosMinimosId() {
		return salariosMinimosId;
	}
	public void setSalariosMinimosId(Integer salariosMinimosId) {
		this.salariosMinimosId = salariosMinimosId;
	}
//	public Integer getRadarComplementadoUT() {
//		return radarComplementadoUT;
//	}
//	public void setRadarComplementadoUT(Integer radarComplementadoUT) {
//		this.radarComplementadoUT = radarComplementadoUT;
//	}
	public Integer getResponsableProceso() {
		return responsableProceso;
	}
	public void setResponsableProceso(Integer responsableProceso) {
		this.responsableProceso = responsableProceso;
	}
	public String getTipoDeteccion() {
		return tipoDeteccion;
	}
	public void setTipoDeteccion(String tipoDeteccion) {
		this.tipoDeteccion = tipoDeteccion;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public String getOrigenPlaca() {
		return origenPlaca;
	}
	public void setOrigenPlaca(String origenPlaca) {
		this.origenPlaca = origenPlaca;
	}
	public String getCantidadDetecciones() {
		return cantidadDetecciones;
	}
	public void setCantidadDetecciones(String cantidadDetecciones) {
		this.cantidadDetecciones = cantidadDetecciones;
	}
	public Integer getIdTipoPersona() {
		return idTipoPersona;
	}
	public void setIdTipoPersona(Integer idTipoPersona) {
		this.idTipoPersona = idTipoPersona;
	}
	public Integer getStLCaptura() {
		return stLCaptura;
	}
	public void setStLCaptura(Integer stLCaptura) {
		this.stLCaptura = stLCaptura;
	}
	public String getTipoarchivo() {
		return tipoarchivo;
	}
	public void setTipoarchivo(String tipoarchivo) {
		this.tipoarchivo = tipoarchivo;
	}
	public Integer getStVCP() {
		return stVCP;
	}
	public void setStVCP(Integer stVCP) {
		this.stVCP = stVCP;
	}
	public Date getFhIniPeriodo() {
		return fhIniPeriodo;
	}
	public void setFhIniPeriodo(Date fhIniPeriodo) {
		this.fhIniPeriodo = fhIniPeriodo;
	}
	public Date getFhFinPeriodo() {
		return fhFinPeriodo;
	}
	public void setFhFinPeriodo(Date fhFinPeriodo) {
		this.fhFinPeriodo = fhFinPeriodo;
	}
}
