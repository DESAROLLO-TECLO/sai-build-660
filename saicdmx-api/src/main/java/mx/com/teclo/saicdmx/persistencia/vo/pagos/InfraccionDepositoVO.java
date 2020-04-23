package mx.com.teclo.saicdmx.persistencia.vo.pagos;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonAutoDetect
@JsonIgnoreProperties(ignoreUnknown = true)
public class InfraccionDepositoVO implements Serializable {

	private static final long serialVersionUID = 5654431815517164175L;

	private String infraccion;
	private String placa;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss ", timezone = "America/Mexico_City")
	private Date fecha;
	private String nci;
	private String status;
	private String marca;
	private String modelo;
	private String color;
	private String infractor;
	private String depId;
	private String deposito;
	private String impresa;
	private String documento;
	private String valorBusqueda;
	private String tipoBusqueda;
	private boolean pagoConMonto;
	private boolean pagoTieneVoucher;

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

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getNci() {
		return nci;
	}

	public void setNci(String nci) {
		this.nci = nci;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public String getDepId() {
		return depId;
	}

	public void setDepId(String depId) {
		this.depId = depId;
	}

	public String getDeposito() {
		return deposito;
	}

	public void setDeposito(String deposito) {
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

	public String getValorBusqueda() {
		return valorBusqueda;
	}

	public void setValorBusqueda(String valorBusqueda) {
		this.valorBusqueda = valorBusqueda;
	}

	public String getTipoBusqueda() {
		return tipoBusqueda;
	}

	public void setTipoBusqueda(String tipoBusqueda) {
		this.tipoBusqueda = tipoBusqueda;
	}

	public boolean isPagoConMonto() {
		return pagoConMonto;
	}

	public void setPagoConMonto(boolean pagoConMonto) {
		this.pagoConMonto = pagoConMonto;
	}

	public boolean isPagoTieneVoucher() {
		return pagoTieneVoucher;
	}

	public void setPagoTieneVoucher(boolean pagoTieneVoucher) {
		this.pagoTieneVoucher = pagoTieneVoucher;
	}

}
