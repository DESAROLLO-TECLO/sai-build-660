package mx.com.teclo.saicdmx.util.enums;

public enum EnumGarantiasProceso {

	CREADA(0), REVISADA(1), PAGADA(2), ENTREGADA(3), CANCELADA(4);

	private EnumGarantiasProceso(Integer procesoId) {
		this.procesoID = procesoId;
	}

	private Integer procesoID;

	/**
	 * @return the procesoID
	 */
	public Integer getProcesoID() {
		return procesoID;
	}

	/**
	 * @param procesoID
	 *            the procesoID to set
	 */
	public void setProcesoID(Integer procesoID) {
		this.procesoID = procesoID;
	}

}
