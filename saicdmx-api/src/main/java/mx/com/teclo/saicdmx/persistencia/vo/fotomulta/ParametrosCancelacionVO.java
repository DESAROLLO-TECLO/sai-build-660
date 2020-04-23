package mx.com.teclo.saicdmx.persistencia.vo.fotomulta;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonAutoDetect
@JsonIgnoreProperties(ignoreUnknown = true)
public class ParametrosCancelacionVO {

	private String fecha;
	private int tipoRadar;
	private int origenPlaca;
	private String motivoCancelacion;
	private Long modificadoPor;
	private Date fechaInicio;
	private Date fechaFin;
	private String fechaInicioString;
	private String fechaFinString;
	private int motivoId;

	public String getFechaInicioString() {
		return fechaInicioString;
	}

	public void setFechaInicioString(String fechaInicioString) {
		this.fechaInicioString = fechaInicioString;
	}

	public String getFechaFinString() {
		return fechaFinString;
	}

	public void setFechaFinString(String fechaFinString) {
		this.fechaFinString = fechaFinString;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public int getTipoRadar() {
		return tipoRadar;
	}

	public void setTipoRadar(int tipoRadar) {
		this.tipoRadar = tipoRadar;
	}

	public int getOrigenPlaca() {
		return origenPlaca;
	}

	public void setOrigenPlaca(int origenPlaca) {
		this.origenPlaca = origenPlaca;
	}

	public String getMotivoCancelacion() {
		return motivoCancelacion;
	}

	public void setMotivoCancelacion(String motivoCancelacion) {
		this.motivoCancelacion = motivoCancelacion;
	}

	public Long getModificadoPor() {
		return modificadoPor;
	}

	public void setModificadoPor(Long modificadoPor) {
		this.modificadoPor = modificadoPor;
	}

	/**
	 * @return the fechaInicio
	 */
	public Date getFechaInicio() {
		return fechaInicio;
	}

	/**
	 * @param fechaInicio
	 *            the fechaInicio to set
	 */
	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	/**
	 * @return the fechaFin
	 */
	public Date getFechaFin() {
		return fechaFin;
	}

	/**
	 * @param fechaFin
	 *            the fechaFin to set
	 */
	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public int getMotivoId() {
		return motivoId;
	}

	public void setMotivoId(int motivoId) {
		this.motivoId = motivoId;
	}
}
