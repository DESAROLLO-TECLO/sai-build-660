package mx.com.teclo.saicdmx.persistencia.vo.radares;

public class RadarDeteccionesCentroReparto {
	private Long idDeteccion; 
	private String placa;
	private String fecha;
	private String hora;
	private String nombre;
	private String domicilio;
	private String codigoPostal;
	private Long centroReparto;
	private String municipio;
	private String entidadFederativa;
	
	
	public Long getIdDeteccion() {
		return idDeteccion;
	}
	public void setIdDeteccion(Long idDeteccion) {
		this.idDeteccion = idDeteccion;
	}
	public String getPlaca() {
		return placa;
	}
	public void setPlaca(String placa) {
		this.placa = placa;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public String getHora() {
		return hora;
	}
	public void setHora(String hora) {
		this.hora = hora;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDomicilio() {
		return domicilio;
	}
	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}
	public String getCodigoPostal() {
		return codigoPostal;
	}
	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}
	public Long getCentroReparto() {
		return centroReparto;
	}
	public void setCentroReparto(Long centroReparto) {
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
