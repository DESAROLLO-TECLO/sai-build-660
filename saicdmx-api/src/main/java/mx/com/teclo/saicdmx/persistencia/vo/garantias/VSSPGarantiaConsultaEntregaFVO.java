package mx.com.teclo.saicdmx.persistencia.vo.garantias;

import java.util.Date;

import mx.com.teclo.saicdmx.persistencia.hibernate.dto.garantias.GarantiaDocumentoDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.infracciones.InfraccionDTO;

public class VSSPGarantiaConsultaEntregaFVO {
	
	
	
	private Long garantiaId;
	private InfraccionDTO infraccionDTO;
	private String documentoFolio;
	private String fechaCreacion;
	private String fechaPago;
	private Boolean recibida;
	private GarantiaDocumentoDTO garantiaDocumentoDTO;
	
	
	
	public String getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(String fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	public Boolean getRecibida() {
		return recibida;
	}
	public void setRecibida(Boolean recibida) {
		this.recibida = recibida;
	}
	public GarantiaDocumentoDTO getGarantiaDocumentoDTO() {
		return garantiaDocumentoDTO;
	}
	public void setGarantiaDocumentoDTO(GarantiaDocumentoDTO garantiaDocumentoDTO) {
		this.garantiaDocumentoDTO = garantiaDocumentoDTO;
	}
	public InfraccionDTO getInfraccionDTO() {
		return infraccionDTO;
	}
	public void setInfraccionDTO(InfraccionDTO infraccionDTO) {
		this.infraccionDTO = infraccionDTO;
	}
	public String getDocumentoFolio() {
		return documentoFolio;
	}
	public void setDocumentoFolio(String documentoFolio) {
		this.documentoFolio = documentoFolio;
	}
	
	public Long getGarantiaId() {
		return garantiaId;
	}
	public void setGarantiaId(Long garantiaId) {
		this.garantiaId = garantiaId;
	}
	
	public String getFechaPago() {
		return fechaPago;
	}
	public void setFechaPago(String fechaPago) {
		this.fechaPago = fechaPago;
	}
	
	
	

}
