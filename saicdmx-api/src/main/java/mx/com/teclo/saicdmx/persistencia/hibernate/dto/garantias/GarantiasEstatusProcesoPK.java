package mx.com.teclo.saicdmx.persistencia.hibernate.dto.garantias;

import java.io.Serializable;

public class GarantiasEstatusProcesoPK implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6699188723149744445L;

	private Long garantiaId;

    private Integer procesoId;

	/**
	 * @return the garantiaId
	 */
	public Long getGarantiaId() {
		return garantiaId;
	}

	/**
	 * @param garantiaId the garantiaId to set
	 */
	public void setGarantiaId(Long garantiaId) {
		this.garantiaId = garantiaId;
	}

	/**
	 * @return the procesoId
	 */
	public Integer getProcesoId() {
		return procesoId;
	}

	/**
	 * @param procesoId the procesoId to set
	 */
	public void setProcesoId(Integer procesoId) {
		this.procesoId = procesoId;
	}
}
