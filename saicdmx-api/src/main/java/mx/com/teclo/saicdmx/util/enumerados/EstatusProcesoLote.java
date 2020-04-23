package mx.com.teclo.saicdmx.util.enumerados;

public enum EstatusProcesoLote {

	TODOS(0), LISTO_PARA_COMPLEMENTAR(1), COMPLEMENTADO(29), LISTO_PARA_LIBERAR(30), LIBERANDO(31), LIBERADO(32), CANCELADO(33);
	

	private Integer estatusProceso;
	
	private EstatusProcesoLote(Integer estatusProceso) {
		this.setEstatusProceso(estatusProceso);
	}

	public Integer getEstatusProceso() {
		return estatusProceso;
	}

	public void setEstatusProceso(Integer estatusProceso) {
		this.estatusProceso = estatusProceso;
	}
	
	
	public static EstatusProcesoLote getArchivoEstatusProceso(Integer x) {
		for (EstatusProcesoLote currentType : EstatusProcesoLote.values()) {
	      if (x.equals(currentType.getEstatusProceso())) {
	        return currentType;
	      }
	    }
	    return null;
	  }

}
