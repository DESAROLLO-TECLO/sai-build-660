package mx.com.teclo.saicdmx.persistencia.vo.logs;

import java.util.Calendar;

/**
 * Almacena informacion de un objeto de tipo log.
 *
 * @author TMM Logistic S.A.
 * @see www.tmm.com.mx
 */
public class LogsVO implements Comparable{

    private Long logId;
    private String logNombre;
    private String logDescripcion;
    private String logTipoArchivos;
    private boolean logEstatus;
    private String ultimaFechaModificacion;
    private Calendar fechaModficacion;
    private String rutaArchivo;
    private Long creadoPor;
    private Long perfilId;
    private String tipoOperacion;
    private String mensaje;
    private String resultadoPrincipal;
    
    public LogsVO() {
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

    public String getUltimaFechaModificacion() {
        return ultimaFechaModificacion;
    }

    public void setUltimaFechaModificacion(String ultimaFechaModificacion) {
        this.ultimaFechaModificacion = ultimaFechaModificacion;
    }

    public String getRutaArchivo() {
        return rutaArchivo;
    }

    public void setRutaArchivo(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
    }

    public Calendar getFechaModficacion() {
        return fechaModficacion;
    }

    public void setFechaModficacion(Calendar fechaModficacion) {
        this.fechaModficacion = fechaModficacion;
    }

    public boolean isLogEstatus() {
        return logEstatus;
    }

    public void setLogEstatus(boolean logEstatus) {
        this.logEstatus = logEstatus;
    }

    public String getLogTipoArchivos() {
        return logTipoArchivos;
    }

    public void setLogTipoArchivos(String logTipoArchivos) {
        this.logTipoArchivos = logTipoArchivos;
    }

   
    
    public Long getLogId() {
		return logId;
	}

	public void setLogId(Long logId) {
		this.logId = logId;
	}

	public Long getCreadoPor() {
		return creadoPor;
	}

	public void setCreadoPor(Long creadoPor) {
		this.creadoPor = creadoPor;
	}

	public Long getPerfilId() {
		return perfilId;
	}

	public void setPerfilId(Long perfilId) {
		this.perfilId = perfilId;
	}

	@Override
    public int compareTo(Object object) {
        LogsVO log = (LogsVO) object;
        return this.fechaModficacion.compareTo(log.fechaModficacion);
    }

	public String getTipoOperacion() {
		return tipoOperacion;
	}

	public void setTipoOperacion(String tipoOperacion) {
		this.tipoOperacion = tipoOperacion;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public String getResultadoPrincipal() {
		return resultadoPrincipal;
	}

	public void setResultadoPrincipal(String resultadoPrincipal) {
		this.resultadoPrincipal = resultadoPrincipal;
	}
}
