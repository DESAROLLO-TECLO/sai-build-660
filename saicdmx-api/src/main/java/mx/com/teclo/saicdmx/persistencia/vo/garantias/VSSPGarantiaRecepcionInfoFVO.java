package mx.com.teclo.saicdmx.persistencia.vo.garantias;

import java.io.Serializable;


public class VSSPGarantiaRecepcionInfoFVO implements Serializable  {
	
 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long garantiaId;
	private String infraccNum;
	private String documentoFolio;
	private String documentoNombre;
	
	public Long getGarantiaId() {
		return garantiaId;
	}
	public void setGarantiaId(Long garantiaId) {
		this.garantiaId = garantiaId;
	}
	public String getInfraccNum() {
		return infraccNum;
	}
	public void setInfraccNum(String infraccNum) {
		this.infraccNum = infraccNum;
	}
	public String getDocumentoFolio() {
		return documentoFolio;
	}
	public void setDocumentoFolio(String documentoFolio) {
		this.documentoFolio = documentoFolio;
	}
	public String getDocumentoNombre() {
		return documentoNombre;
	}
	public void setDocumentoNombre(String documentoNombre) {
		this.documentoNombre = documentoNombre;
	}

	//	PRUEBA DE VALORES QUE VAN EN NULL 
//	private Integer infraccFolio;
//	private Integer documentoId;
//	private String nombre;
//	
//	private String infraccionFolio;
//	private Integer documentoTipoId;
//	
//	private Integer procesoId;
//	private String procesoNombre;
//	private Long empleadoId;
//	private String empleadoNombre;
//	private String empleadoPlaca;
//	private String deposito;
//	private String fechaInfraccion;
//	
//	private InfraccionDTO infraccionDTO;
//	private GarantiaDocumentoDTO garantiaDocumentoDTO;
//	
//	

	
	

}