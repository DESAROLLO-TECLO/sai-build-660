package mx.com.teclo.saicdmx.util.enumerados;

public enum CodigoPerfilesEnum {

	SSP_ADMINISTRADOR("SSPADMIN"), TCL_ADMINISTRADOR("TCLADMIN"), TCL_OPERACION("TCLOPERA"), TCL_SOPORTE_ESPECIAL("TCLSOESP"),
	TCL_CONTACT_CENTER("TCLCC"), TCL_SUPERVISOR("TCLSPV"), CAJERO("CAJERO"), HANDHELD("HANDHELD"), RADARES("RADARES"), RADARES_ADMIN("RADARESADMIN");
	
	
	
	
	private String codigo;
	
	private CodigoPerfilesEnum(String codigo) {
		this.setCodigo(codigo);
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
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
