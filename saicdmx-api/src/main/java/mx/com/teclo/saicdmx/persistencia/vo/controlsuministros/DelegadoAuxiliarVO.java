package mx.com.teclo.saicdmx.persistencia.vo.controlsuministros;

import java.io.Serializable;

public class DelegadoAuxiliarVO implements Serializable{

	private static final long serialVersionUID = 3555308119480100363L;
	
	private Long id_registro;
	private String tipo;
	private String upc_nombre;
	private String oficial_placa;
	private String oficial_nombre;
	private Long reg_id;
	private Long upc_id;

	
	public Long getId_registro() {
		return id_registro;
	}
	public void setId_registro(Long id_registro) {
		this.id_registro = id_registro;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	public String getOficial_placa() {
		return oficial_placa;
	}
	public void setOficial_placa(String oficial_placa) {
		this.oficial_placa = oficial_placa;
	}
	public String getOficial_nombre() {
		return oficial_nombre;
	}
	public void setOficial_nombre(String oficial_nombre) {
		this.oficial_nombre = oficial_nombre;
	}
	public String getUpc_nombre() {
		return upc_nombre;
	}
	public void setUpc_nombre(String upc_nombre) {
		this.upc_nombre = upc_nombre;
	}
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}
	public Long getReg_id() {
		return reg_id;
	}
	public void setReg_id(Long reg_id) {
		this.reg_id = reg_id;
	}
	public Long getUpc_id() {
		return upc_id;
	}
	public void setUpc_id(Long upc_id) {
		this.upc_id = upc_id;
	}
	
	
}