package mx.com.teclo.saicdmx.persistencia.vo.logs;

/**
 * Guarda informaci�n de resultados de StoreProcedure.
 *
 * @author TMM Logistic S.A.
 * @see www.tmm.com.mx
 */
public class ResultadoProcedureVO {
 
	
	private String tipoOperacion;
	private Long   logId;
	private Long   perfilId;
	private String logNombre;
	private String logDescripcion;
	private String logRuta;
	private String logExtensiones;
	private Long   usuarioId;
	private String mensaje;
	private String resultadoPrincipal;
	private String resultadoExtra;

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getResultadoExtra() {
        return resultadoExtra;
    }

    public void setResultadoExtra(String resultadoExtra) {
        this.resultadoExtra = resultadoExtra;
    }

    public String getResultadoPrincipal() {
        return resultadoPrincipal;
    }

    public void setResultadoPrincipal(String resultadoPrincipal) {
        this.resultadoPrincipal = resultadoPrincipal;
    }

	public String getTipoOperacion() {
		return tipoOperacion;
	}

	public void setTipoOperacion(String tipoOperacion) {
		this.tipoOperacion = tipoOperacion;
	}

	public Long getLogId() {
		return logId;
	}

	public void setLogId(Long logId) {
		this.logId = logId;
	}

	public Long getPerfilId() {
		return perfilId;
	}

	public void setPerfilId(Long perfilId) {
		this.perfilId = perfilId;
	}

	public String getLogNombre() {
		return logNombre;
	}

	public void setLogNombre(String logNombre) {
		this.logNombre = logNombre;
	}

	public String getLogDescripcion() {
		return logDescripcion;
	}

	public void setLogDescripcion(String logDescripcion) {
		this.logDescripcion = logDescripcion;
	}

	public String getLogRuta() {
		return logRuta;
	}

	public void setLogRuta(String logRuta) {
		this.logRuta = logRuta;
	}

	public String getLogExtensiones() {
		return logExtensiones;
	}

	public void setLogExtensiones(String logExtensiones) {
		this.logExtensiones = logExtensiones;
	}

	public Long getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(Long usuarioId) {
		this.usuarioId = usuarioId;
	}
     
   
}
