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
@Table(name="SOPORTE_BITACORA")
public class SoporteBitacoraDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6934096314092113039L;
	
	@Id
	@Column(name = "CAMBIO_ID", unique = true, nullable = false)
	private Long cambioId;
	@Column(name = "COMPONENTE_ID")//, insertable = false, updatable = false)
	private Long componenteId;
	@Column(name = "CONCEPTO_ID")
	private Long conceptoId;
	@Column(name = "FECHA_CAMBIO")
	private Date fechaCambio;
	@Column(name = "MODIFICADO_POR")
	private Long modificadoPor;
	@Column(name = "VALOR_ORIGINAL")
	private String valorOriginal;
	@Column(name = "VALOR_FINAL")
	private String valorFinal;
	@Column(name = "AUTORIZADO_POR")
	private Long autorizadoPor;
	
	public Long getCambioId() {
		return cambioId;
	}
	public void setCambioId(Long cambioId) {
		this.cambioId = cambioId;
	}
	public Long getComponenteId() {
		return componenteId;
	}
	public void setComponenteId(Long componenteId) {
		this.componenteId = componenteId;
	}
	public Long getConceptoId() {
		return conceptoId;
	}
	public void setConceptoId(Long conceptoId) {
		this.conceptoId = conceptoId;
	}
	public Date getFechaCambio() {
		return fechaCambio;
	}
	public void setFechaCambio(Date fechaCambio) {
		this.fechaCambio = fechaCambio;
	}
	public Long getModificadoPor() {
		return modificadoPor;
	}
	public void setModificadoPor(Long modificadoPor) {
		this.modificadoPor = modificadoPor;
	}
	public String getValorOriginal() {
		return valorOriginal;
	}
	public void setValorOriginal(String valorOriginal) {
		this.valorOriginal = valorOriginal;
	}
	public String getValorFinal() {
		return valorFinal;
	}
	public void setValorFinal(String valorFinal) {
		this.valorFinal = valorFinal;
	}
	public Long getAutorizadoPor() {
		return autorizadoPor;
	}
	public void setAutorizadoPor(Long autorizadoPor) {
		this.autorizadoPor = autorizadoPor;
	}

	


}
