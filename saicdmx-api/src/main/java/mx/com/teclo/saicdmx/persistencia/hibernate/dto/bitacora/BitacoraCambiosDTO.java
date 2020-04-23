package mx.com.teclo.saicdmx.persistencia.hibernate.dto.bitacora;


import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="BITACORA_CAMBIOS")
public class BitacoraCambiosDTO implements Serializable 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6934096314092113039L;

	@Id
	@SequenceGenerator(name = "entity1Seq", sequenceName="BITACORA_CAMBIOS_SEQ", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "entity1Seq")
	@Column(name = "CAMBIO_ID", unique = true, nullable = false)
	private Long cambioId;
	@Column(name = "COMPONENTE_ID")//, insertable = false, updatable = false)
	private Long componenteId;
	@Column(name = "CONCEPTO_ID")
	private Long conceptoId;
	@Column(name = "VALOR_ORIGINAL")
	private String valorOriginal;
	@Column(name = "VALOR_FINAL")
	private String valorFinal;
	@Column(name = "FECHA_CAMBIO")
	private Date fechaCambio;
	@Column(name = "MODIFICADO_POR")
	private Long modificadoPor;
	@Column(name = "ID_REGISTRO")
	private Long idRegistro;
	@Column(name = "ORIGEN")
	private String origen;
	
	/**
	 * @return the cambioId
	 */
	public Long getCambioId() {
		return cambioId;
	}
	/**
	 * @param cambioId the cambioId to set
	 */
	public void setCambioId(Long cambioId) {
		this.cambioId = cambioId;
	}
	/**
	 * @return the componenteId
	 */
	public Long getComponenteId() {
		return componenteId;
	}
	/**
	 * @param componenteId the componenteId to set
	 */
	public void setComponenteId(Long componenteId) {
		this.componenteId = componenteId;
	}
	/**
	 * @return the conceptoId
	 */
	public Long getConceptoId() {
		return conceptoId;
	}
	/**
	 * @param conceptoId the conceptoId to set
	 */
	public void setConceptoId(Long conceptoId) {
		this.conceptoId = conceptoId;
	}
	/**
	 * @return the valorOriginal
	 */
	public String getValorOriginal() {
		return valorOriginal;
	}
	/**
	 * @param valorOriginal the valorOriginal to set
	 */
	public void setValorOriginal(String valorOriginal) {
		this.valorOriginal = valorOriginal;
	}
	/**
	 * @return the valorFinal
	 */
	public String getValorFinal() {
		return valorFinal;
	}
	/**
	 * @param valorFinal the valorFinal to set
	 */
	public void setValorFinal(String valorFinal) {
		this.valorFinal = valorFinal;
	}
	/**
	 * @return the fechaCambio
	 */
	public Date getFechaCambio() {
		return fechaCambio;
	}
	/**
	 * @param fechaCambio the fechaCambio to set
	 */
	public void setFechaCambio(Date fechaCambio) {
		this.fechaCambio = fechaCambio;
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
	 * @return the idRegistro
	 */
	public Long getIdRegistro() {
		return idRegistro;
	}
	/**
	 * @param idRegistro the idRegistro to set
	 */
	public void setIdRegistro(Long idRegistro) {
		this.idRegistro = idRegistro;
	}
	/**
	 * @return the origen
	 */
	public String getOrigen() {
		return origen;
	}
	/**
	 * @param origen the origen to set
	 */
	public void setOrigen(String origen) {
		this.origen = origen;
	}
	
	
	@Override
	public String toString()
	{
		StringBuilder bitacoraCambiosDTO = new StringBuilder("");
		
		bitacoraCambiosDTO.append("\n\t cambioId: "		+ cambioId);
		bitacoraCambiosDTO.append("\n\t componenteId: "	+ componenteId);
		bitacoraCambiosDTO.append("\n\t conceptoId: " 	+ conceptoId);
		bitacoraCambiosDTO.append("\n\t valorOriginal: " + valorOriginal);
		bitacoraCambiosDTO.append("\n\t valorFinal: " 	+ valorFinal);
		bitacoraCambiosDTO.append("\n\t fechaCambio: "	+ fechaCambio);		
		bitacoraCambiosDTO.append("\n\t modificadoPor: " + modificadoPor);
		bitacoraCambiosDTO.append("\n\t idRegistro: " 	+ idRegistro);
		bitacoraCambiosDTO.append("\n\t origen: "		+ origen);
		
		
		return bitacoraCambiosDTO.toString();
	}
	
}

