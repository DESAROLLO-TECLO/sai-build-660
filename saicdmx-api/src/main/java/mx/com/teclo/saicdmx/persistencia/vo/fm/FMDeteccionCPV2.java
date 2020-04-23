package mx.com.teclo.saicdmx.persistencia.vo.fm;

import java.util.List;

public class FMDeteccionCPV2 {
	private Long idDeteccion;
	private String codigoPostal;
	private String centroReparto;
	private String municipio;
	private String entidadFederativa;
	private List<FMDeteccionCP> detecciones;
	
	public List<FMDeteccionCP> getDetecciones() {
		return detecciones;
	}
	public void setDetecciones(List<FMDeteccionCP> detecciones) {
		this.detecciones = detecciones;
	}
	public Long getIdDeteccion() {
		return idDeteccion;
	}
	public void setIdDeteccion(Long idDeteccion) {
		this.idDeteccion = idDeteccion;
	}
	public String getCodigoPostal() {
		return codigoPostal;
	}
	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}
	public String getCentroReparto() {
		return centroReparto;
	}
	public void setCentroReparto(String centroReparto) {
		this.centroReparto = centroReparto;
	}
	public String getMunicipio() {
		return municipio;
	}
	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}
	public String getEntidadFederativa() {
		return entidadFederativa;
	}
	public void setEntidadFederativa(String entidadFederativa) {
		this.entidadFederativa = entidadFederativa;
	}
	
}
