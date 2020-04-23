package mx.com.teclo.saicdmx.persistencia.vo.garantias;

import java.io.Serializable;

public class VSSPGarantiaConsGralFVO implements Serializable {
	
	private static final long serialVersionUID = 3555308119480100363L;
	
	private Long garantiaId;
	private String infraccionFolio;
	private Integer documentoTipoId;
	private String documentoNombre;
	private String documentoFolio;
	private Integer procesoId;
	private String procesoNombre;
	private Long empleadoId;
	private String empleadoNombre;
	private String empleadoPlaca;
	private String deposito;
	private String fechaInfraccion;
	private Long idLote;
	private Integer numero;
	
	public Long getGarantiaId() {
		return garantiaId;
	}
	public void setGarantiaId(Long garantiaId) {
		this.garantiaId = garantiaId;
	}
	public String getInfraccionFolio() {
		return infraccionFolio;
	}
	public void setInfraccionFolio(String infraccionFolio) {
		this.infraccionFolio = infraccionFolio;
	}
	public Integer getDocumentoTipoId() {
		return documentoTipoId;
	}
	public void setDocumentoTipoId(Integer documentoTipoId) {
		this.documentoTipoId = documentoTipoId;
	}
	public String getDocumentoNombre() {
		return documentoNombre;
	}
	public void setDocumentoNombre(String documentoNombre) {
		this.documentoNombre = documentoNombre;
	}
	public String getDocumentoFolio() {
		return documentoFolio;
	}
	public void setDocumentoFolio(String documentoFolio) {
		this.documentoFolio = documentoFolio;
	}
	public Integer getProcesoId() {
		return procesoId;
	}
	public void setProcesoId(Integer procesoId) {
		this.procesoId = procesoId;
	}
	public String getProcesoNombre() {
		return procesoNombre;
	}
	public void setProcesoNombre(String procesoNombre) {
		this.procesoNombre = procesoNombre;
	}
	public Long getEmpleadoId() {
		return empleadoId;
	}
	public void setEmpleadoId(Long empleadoId) {
		this.empleadoId = empleadoId;
	}
	public String getEmpleadoNombre() {
		return empleadoNombre;
	}
	public void setEmpleadoNombre(String empleadoNombre) {
		this.empleadoNombre = empleadoNombre;
	}
	public String getEmpleadoPlaca() {
		return empleadoPlaca;
	}
	public void setEmpleadoPlaca(String empleadoPlaca) {
		this.empleadoPlaca = empleadoPlaca;
	}
	public String getDeposito() {
		return deposito;
	}
	public void setDeposito(String deposito) {
		this.deposito = deposito;
	}
	public String getFechaInfraccion() {
		return fechaInfraccion;
	}
	public void setFechaInfraccion(String fechaInfraccion) {
		this.fechaInfraccion = fechaInfraccion;
	}
	public Long getIdLote() {
		return idLote;
	}
	public void setIdLote(Long idLote) {
		this.idLote = idLote;
	}
	public Integer getNumero() {
		return numero;
	}
	public void setNumero(Integer numero) {
		this.numero = numero;
	}
	
	

}