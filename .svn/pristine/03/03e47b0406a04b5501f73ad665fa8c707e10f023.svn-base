package mx.com.teclo.saicdmx.util.enumerados;

public enum RadarTipoArchivo {
	RADAR_SSP(1), CARRIL_CONFINADO(2), FOTOMULTA(3), RADARES_CONCESIONADOS(4), FOTOMULTA_FORANEA(6);

	private Integer procesoId;

	private RadarTipoArchivo(Integer procesoId) {
		this.procesoId = procesoId;

	}

	/**
	 * @return the procesoId
	 */
	public Integer getProcesoId() {
		return procesoId;
	}

	/**
	 * @param procesoId
	 *            the procesoId to set
	 */
	public void setProcesoId(Integer procesoId) {
		this.procesoId = procesoId;
	}
	
	static RadarTipoArchivo getArchivoTipo(Integer x) {
		for (RadarTipoArchivo currentType : RadarTipoArchivo.values()) {
	      if (x.equals(currentType.getProcesoId())) {
	        return currentType;
	      }
	    }
	    return null;
	  }
}
