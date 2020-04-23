package mx.com.teclo.saicdmx.persistencia.vo.pagos;

import java.io.Serializable;

public class TotalesCentroPagosVO implements Serializable {

	private static final long serialVersionUID = -935072235354314865L;

	private Integer cantidad;
	private String conceptoPago;


	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public String getConceptoPago() {
		return conceptoPago;
	}

	public void setConceptoPago(String conceptoPago) {
		this.conceptoPago = conceptoPago;
	}

}
