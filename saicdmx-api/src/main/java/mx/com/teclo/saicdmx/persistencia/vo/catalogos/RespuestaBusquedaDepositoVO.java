package mx.com.teclo.saicdmx.persistencia.vo.catalogos;

import java.io.Serializable;

public class RespuestaBusquedaDepositoVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6178035707887683235L;
	
	private Long depositoId;
	private Long regionId;
	private String depositoCodigo;
	private Long estadoId;
	private String depositoNombre;
	private Long zonaId;
	private String zonaNombre;
	private Long delegacionId;
	private String delegacionNombre;
	private String depositoDireccion;
	private String depositoColonia;
	private String depositoStatus;
	private String depositoTelefono;
	private Integer depositoCapacidad;
	private Integer depositoSuperficie;
	
	public Long getDepositoId() {
		return depositoId;
	}
	public void setDepositoId(Long depositoId) {
		this.depositoId = depositoId;
	}
	public Long getRegionId() {
		return regionId;
	}
	public void setRegionId(Long regionId) {
		this.regionId = regionId;
	}
	public String getDepositoCodigo() {
		return depositoCodigo;
	}
	public void setDepositoCodigo(String depositoCodigo) {
		this.depositoCodigo = depositoCodigo;
	}
	public Long getEstadoId() {
		return estadoId;
	}
	public void setEstadoId(Long estadoId) {
		this.estadoId = estadoId;
	}
	public String getDepositoNombre() {
		return depositoNombre;
	}
	public void setDepositoNombre(String depositoNombre) {
		this.depositoNombre = depositoNombre;
	}
	public Long getZonaId() {
		return zonaId;
	}
	public void setZonaId(Long zonaId) {
		this.zonaId = zonaId;
	}
	public String getZonaNombre() {
		return zonaNombre;
	}
	public void setZonaNombre(String zonaNombre) {
		this.zonaNombre = zonaNombre;
	}
	public Long getDelegacionId() {
		return delegacionId;
	}
	public void setDelegacionId(Long delegacionId) {
		this.delegacionId = delegacionId;
	}
	public String getDelegacionNombre() {
		return delegacionNombre;
	}
	public void setDelegacionNombre(String delegacionNombre) {
		this.delegacionNombre = delegacionNombre;
	}
	public String getDepositoDireccion() {
		return depositoDireccion;
	}
	public void setDepositoDireccion(String depositoDireccion) {
		this.depositoDireccion = depositoDireccion;
	}
	public String getDepositoColonia() {
		return depositoColonia;
	}
	public void setDepositoColonia(String depositoColonia) {
		this.depositoColonia = depositoColonia;
	}
	public String getDepositoTelefono() {
		return depositoTelefono;
	}
	public void setDepositoTelefono(String depositoTelefono) {
		this.depositoTelefono = depositoTelefono;
	}
	public Integer getDepositoCapacidad() {
		if (depositoCapacidad == null) {
			return 0;
		}
		return depositoCapacidad;
	}
	public void setDepositoCapacidad(Integer depositoCapacidad) {
		this.depositoCapacidad = depositoCapacidad;
	}
	public Integer getDepositoSuperficie() {
		if (depositoSuperficie == null) {
			return 0;
		}
		return depositoSuperficie;
	}
	public void setDepositoSuperficie(Integer depositoSuperficie) {
		this.depositoSuperficie = depositoSuperficie;
	}
	public String getDepositoStatus() {
		return depositoStatus;
	}
	public void setDepositoStatus(String depositoStatus) {
		this.depositoStatus = depositoStatus;
	}
	public String getStatusDesc() {
		return this.getDepositoStatus().equals("A") ? "Activo" : "Cancelado";
	}
}
