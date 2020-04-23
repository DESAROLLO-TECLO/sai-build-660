package mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "V_REMISION_CONSULTA_REMITIDAS")
public class VRemisionConsultaRemitidasDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "INFRACCION")
	String infraccion;
	@Column(name = "PLACA")
	String placa;
	@Column(name = "FECHA")
	String fecha;
	@Column(name = "NCI")
	String nci;
	@Column(name = "STATUS")
	String estatus;
	@Column(name = "MARCA")
	String marca;
	@Column(name = "MODELO")
	String modelo;
	@Column(name = "COLOR")
	String color;
	@Column(name = "INFRACTOR")
	String infractor;
	@Column(name = "DEPOSITO")
	Long deposito;
	@Column(name = "IMPRESA")
	String impresa;
	@Column(name = "DOCUMENTO")
	String documento;
	@Column(name = "INFO_INGRESO")
	String informacionIngreso;
	
	
	
	public String getInfraccion() {
		return infraccion;
	}
	public void setInfraccion(String infraccion) {
		this.infraccion = infraccion;
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
	public String getNci() {
		return nci;
	}
	public void setNci(String nci) {
		this.nci = nci;
	}
	public String getEstatus() {
		return estatus;
	}
	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getInfractor() {
		return infractor;
	}
	public void setInfractor(String infractor) {
		this.infractor = infractor;
	}
	public Long getDeposito() {
		return deposito;
	}
	public void setDeposito(Long deposito) {
		this.deposito = deposito;
	}
	public String getImpresa() {
		return impresa;
	}
	public void setImpresa(String impresa) {
		this.impresa = impresa;
	}
	public String getDocumento() {
		return documento;
	}
	public void setDocumento(String documento) {
		this.documento = documento;
	}
	public String getInformacionIngreso() {
		return informacionIngreso;
	}
	public void setInformacionIngreso(String informacionIngreso) {
		this.informacionIngreso = informacionIngreso;
	}
	
	

}
