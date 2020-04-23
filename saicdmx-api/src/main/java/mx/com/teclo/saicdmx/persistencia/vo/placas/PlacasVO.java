package mx.com.teclo.saicdmx.persistencia.vo.placas;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class PlacasVO {
	
	private Long placaId;
	private String placaCodigo;
	private String placaStatus;
	@JsonIgnore
	private Long creadoPor;
	private Date fechaCreacion;
	@JsonIgnore
	private Long modificadoPor;
	private Date ultimaModificacion;
	private String observaciones;
	@JsonIgnore
	private String creadoPorS;
	@JsonIgnore
	private String modificadoPorS;
	
	
	public Long getPlacaId() {
		return placaId;
	}
	public void setPlacaId(Long placaId) {
		this.placaId = placaId;
	}
	public String getPlacaCodigo() {
		return placaCodigo;
	}
	public void setPlacaCodigo(String placaCodigo) {
		this.placaCodigo = placaCodigo;
	}
	public String getPlacaStatus() {
		return placaStatus;
	}
	public void setPlacaStatus(String placaStatus) {
		this.placaStatus = placaStatus;
	}
	public Long getCreadoPor() {
		return creadoPor;
	}
	public void setCreadoPor(Long creadoPor) {
		this.creadoPor = creadoPor;
	}
	public Date getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	public Long getModificadoPor() {
		return modificadoPor;
	}
	public void setModificadoPor(Long modificadoPor) {
		this.modificadoPor = modificadoPor;
	}
	public Date getUltimaModificacion() {
		return ultimaModificacion;
	}
	public void setUltimaModificacion(Date ultimaModificacion) {
		this.ultimaModificacion = ultimaModificacion;
	}
	public String getObservaciones() {
		return observaciones;
	}
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	public String getCreadoPorS() {
		return creadoPorS;
	}
	public void setCreadoPorS(String creadoPorS) {
		this.creadoPorS = creadoPorS;
	}
	public String getModificadoPorS() {
		return modificadoPorS;
	}
	public void setModificadoPorS(String modificadoPorS) {
		this.modificadoPorS = modificadoPorS;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((placaCodigo == null) ? 0 : placaCodigo.hashCode());
		result = prime * result + ((placaStatus == null) ? 0 : placaStatus.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PlacasVO other = (PlacasVO) obj;
		if (placaCodigo == null) {
			if (other.placaCodigo != null)
				return false;
		} else if (!placaCodigo.equals(other.placaCodigo))
			return false;
		if (placaStatus == null) {
			if (other.placaStatus != null)
				return false;
		} else if (!placaStatus.equals(other.placaStatus))
			return false;
		return true;
	}
	
	

}
