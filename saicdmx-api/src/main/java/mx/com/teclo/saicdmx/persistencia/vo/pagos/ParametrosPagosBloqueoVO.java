package mx.com.teclo.saicdmx.persistencia.vo.pagos;

import java.io.Serializable;

public class ParametrosPagosBloqueoVO implements Serializable{
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4519989175149381376L;

	/**
	 * 
	 */
	
	
	private Integer id;
	private Integer stBloqueoPago;
	private Integer stActivo;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getStActivo() {
		return stActivo;
	}
	public void setStActivo(Integer stActivo) {
		this.stActivo = stActivo;
	}
	public Integer getStBloqueoPago() {
		return stBloqueoPago;
	}
	public void setStBloqueoPago(Integer stBloqueoPago) {
		this.stBloqueoPago = stBloqueoPago;
	}
	
	

}
