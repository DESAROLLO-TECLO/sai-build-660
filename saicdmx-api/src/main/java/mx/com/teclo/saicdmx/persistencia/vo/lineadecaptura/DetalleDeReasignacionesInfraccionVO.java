package mx.com.teclo.saicdmx.persistencia.vo.lineadecaptura;

import java.math.BigDecimal;

/***
 * 
 * @author Jesus Gutierrez
 *
 */
public class DetalleDeReasignacionesInfraccionVO {

	private String folioInfraccion;
	private String estatus;
	private String lineaCaptura;
	private String fechaReasigna;
	private String vigencia;
	private BigDecimal importe;
	private BigDecimal recargos;
	private BigDecimal descuento;
	private BigDecimal total;
	private String placaEmpleado;
	private String nombreEmpleado;
	private String placaVehiculo;
	
	
	/**
	 * @return the folioInfraccion
	 */
	public String getFolioInfraccion() {
		return folioInfraccion;
	}
	/**
	 * @param folioInfraccion the folioInfraccion to set
	 */
	public void setFolioInfraccion(String folioInfraccion) {
		this.folioInfraccion = folioInfraccion;
	}
	/**
	 * @return the estatus
	 */
	public String getEstatus() {
		return estatus;
	}
	/**
	 * @param estatus the estatus to set
	 */
	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}
	/**
	 * @return the lineaCaptura
	 */
	public String getLineaCaptura() {
		return lineaCaptura;
	}
	/**
	 * @param lineaCaptura the lineaCaptura to set
	 */
	public void setLineaCaptura(String lineaCaptura) {
		this.lineaCaptura = lineaCaptura;
	}
	/**
	 * @return the fechaReasigna
	 */
	public String getFechaReasigna() {
		return fechaReasigna;
	}
	/**
	 * @param fechaReasigna the fechaReasigna to set
	 */
	public void setFechaReasigna(String fechaReasigna) {
		this.fechaReasigna = fechaReasigna;
	}
	/**
	 * @return the vigencia
	 */
	public String getVigencia() {
		return vigencia;
	}
	/**
	 * @param vigencia the vigencia to set
	 */
	public void setVigencia(String vigencia) {
		this.vigencia = vigencia;
	}
	/**
	 * @return the importe
	 */
	public BigDecimal getImporte() {
		return importe;
	}
	/**
	 * @param importe the importe to set
	 */
	public void setImporte(BigDecimal importe) {
		this.importe = importe;
	}
	/**
	 * @return the recargos
	 */
	public BigDecimal getRecargos() {
		return recargos;
	}
	/**
	 * @param recargos the recargos to set
	 */
	public void setRecargos(BigDecimal recargos) {
		this.recargos = recargos;
	}
	/**
	 * @return the descuento
	 */
	public BigDecimal getDescuento() {
		return descuento;
	}
	/**
	 * @param descuento the descuento to set
	 */
	public void setDescuento(BigDecimal descuento) {
		this.descuento = descuento;
	}
	/**
	 * @return the total
	 */
	public BigDecimal getTotal() {
		return total;
	}
	/**
	 * @param total the total to set
	 */
	public void setTotal(BigDecimal total) {
		this.total = total;
	}
	/**
	 * @return the placaEmpleado
	 */
	public String getPlacaEmpleado() {
		return placaEmpleado;
	}
	/**
	 * @param placaEmpleado the placaEmpleado to set
	 */
	public void setPlacaEmpleado(String placaEmpleado) {
		this.placaEmpleado = placaEmpleado;
	}
	/**
	 * @return the nombreEmpleado
	 */
	public String getNombreEmpleado() {
		return nombreEmpleado;
	}
	/**
	 * @param nombreEmpleado the nombreEmpleado to set
	 */
	public void setNombreEmpleado(String nombreEmpleado) {
		this.nombreEmpleado = nombreEmpleado;
	}
	
	/**
	 * @return the placaVehiculo
	 */
	public String getPlacaVehiculo() {
		return placaVehiculo;
	}
	/**
	 * @param placaVehiculo the placaVehiculo to set
	 */
	public void setPlacaVehiculo(String placaVehiculo) {
		this.placaVehiculo = placaVehiculo;
	}
	public Boolean getEstatusFlag(){
		Boolean flag = false;
		if(this.estatus != null){
			if(this.estatus.equalsIgnoreCase("ok")){
				flag = true;
			}
		}
		
		return flag;
	}

}
