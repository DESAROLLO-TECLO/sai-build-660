package mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="V_REMISION_BUSQUEDA")
public class VRemBusqDTO {
	
	@Id
	@Column(name = "INFRACCION", unique = true, nullable = false)
	private String infraccion;
	@Column(name = "PLACA")
	private String placa;
	@Column(name = "FECHA")
	private String fecha;
	@Column(name = "NCI", nullable = false)
	private String nci;
	@Column(name = "STATUS")
	private String status;
	@Column(name = "MARCA")
	private String marca;
	@Column(name = "MODELO")
	private String modelo;
	@Column(name = "COLOR")
	private String color;
	@Column(name = "INFRACTOR")
	private String infractor;
	@Column(name = "DEPOSITO")
	private String deposito;
	@Column(name = "IMPRESA")
	private String impresa;
	/**
	 * @return the infraccion
	 */
	public String getInfraccion() {
		return infraccion;
	}
	/**
	 * @param infraccion the infraccion to set
	 */
	public void setInfraccion(String infraccion) {
		this.infraccion = infraccion;
	}
	/**
	 * @return the placa
	 */
	public String getPlaca() {
		return placa;
	}
	/**
	 * @param placa the placa to set
	 */
	public void setPlaca(String placa) {
		this.placa = placa;
	}
	/**
	 * @return the fecha
	 */
	public String getFecha() {
		return fecha;
	}
	/**
	 * @param fecha the fecha to set
	 */
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	/**
	 * @return the nci
	 */
	public String getNci() {
		return nci;
	}
	/**
	 * @param nci the nci to set
	 */
	public void setNci(String nci) {
		this.nci = nci;
	}
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * @return the marca
	 */
	public String getMarca() {
		return marca;
	}
	/**
	 * @param marca the marca to set
	 */
	public void setMarca(String marca) {
		this.marca = marca;
	}
	/**
	 * @return the modelo
	 */
	public String getModelo() {
		return modelo;
	}
	/**
	 * @param modelo the modelo to set
	 */
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	/**
	 * @return the color
	 */
	public String getColor() {
		return color;
	}
	/**
	 * @param color the color to set
	 */
	public void setColor(String color) {
		this.color = color;
	}
	/**
	 * @return the infractor
	 */
	public String getInfractor() {
		return infractor;
	}
	/**
	 * @param infractor the infractor to set
	 */
	public void setInfractor(String infractor) {
		this.infractor = infractor;
	}
	/**
	 * @return the deposito
	 */
	public String getDeposito() {
		return deposito;
	}
	/**
	 * @param deposito the deposito to set
	 */
	public void setDeposito(String deposito) {
		this.deposito = deposito;
	}
	/**
	 * @return the impresa
	 */
	public String getImpresa() {
		return impresa;
	}
	/**
	 * @param impresa the impresa to set
	 */
	public void setImpresa(String impresa) {
		this.impresa = impresa;
	}
}
