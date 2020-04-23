package mx.com.teclo.saicdmx.persistencia.hibernate.vo.catalogos;

import java.io.Serializable;

import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.RegionDTO;

public class DepositosVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8171029361359652534L;
	
	private Long depId;
	private String depCod;
	private String depNombre;
	private ZonasVO zonaDTO;
	private String depDireccion;
	private String depColonia;
	private String depTelefono;
	private EstadoVO estado;
	private DelegacionVO delegacion;
	private RegionDTO region;
	private Long depSuperficie;
	private Long depCapacidad;
	private String depEstatus;
	private String depUsuario;
	
	public Long getDepId() {
		return depId;
	}
	public void setDepId(Long depId) {
		this.depId = depId;
	}
	public String getDepCod() {
		return depCod;
	}
	public void setDepCod(String depCod) {
		this.depCod = depCod;
	}
	public String getDepNombre() {
		return depNombre;
	}
	public void setDepNombre(String depNombre) {
		this.depNombre = depNombre;
	}
	public ZonasVO getZonaDTO() {
		return zonaDTO;
	}
	public void setZonaDTO(ZonasVO zonaDTO) {
		this.zonaDTO = zonaDTO;
	}
	public String getDepDireccion() {
		return depDireccion;
	}
	public void setDepDireccion(String depDireccion) {
		this.depDireccion = depDireccion;
	}
	public String getDepColonia() {
		return depColonia;
	}
	public void setDepColonia(String depColonia) {
		this.depColonia = depColonia;
	}
	public String getDepTelefono() {
		return depTelefono;
	}
	public void setDepTelefono(String depTelefono) {
		this.depTelefono = depTelefono;
	}
	public EstadoVO getEstado() {
		return estado;
	}
	public void setEstado(EstadoVO estado) {
		this.estado = estado;
	}
	public DelegacionVO getDelegacion() {
		return delegacion;
	}
	public void setDelegacion(DelegacionVO delegacion) {
		this.delegacion = delegacion;
	}
	public RegionDTO getRegion() {
		return region;
	}
	public void setRegion(RegionDTO region) {
		this.region = region;
	}
	public Long getDepSuperficie() {
		return depSuperficie;
	}
	public void setDepSuperficie(Long depSuperficie) {
		this.depSuperficie = depSuperficie;
	}
	public Long getDepCapacidad() {
		return depCapacidad;
	}
	public void setDepCapacidad(Long depCapacidad) {
		this.depCapacidad = depCapacidad;
	}
	public String getDepEstatus() {
		return depEstatus;
	}
	public void setDepEstatus(String depEstatus) {
		this.depEstatus = depEstatus;
	}
	public String getDepUsuario() {
		return depUsuario;
	}
	public void setDepUsuario(String depUsuario) {
		this.depUsuario = depUsuario;
	}
	public String getStatusDesc() {
		return this.getDepEstatus().equals("A") ? "Activo" : "Cancelado";
	}
}
