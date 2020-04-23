package mx.com.teclo.saicdmx.persistencia.vo.controlsuministros;

import java.io.Serializable;

public class OficialNuevoVO implements Serializable{

	private static final long serialVersionUID = 3555308119480100363L;
	
	private long reg_id;
	private long upc_id;
	private String oficial_placa;
	private int idUser;
	private String resultado;
	private String mensaje;
	
	public long getReg_id() {
		return reg_id;
	}
	public void setReg_id(long reg_id) {
		this.reg_id = reg_id;
	}
	public long getUpc_id() {
		return upc_id;
	}
	public void setUpc_id(long upc_id) {
		this.upc_id = upc_id;
	}
	public String getOficial_placa() {
		return oficial_placa;
	}
	public void setOficial_placa(String oficial_placa) {
		this.oficial_placa = oficial_placa;
	}
	public int getIdUser() {
		return idUser;
	}
	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}
	public String getResultado() {
		return resultado;
	}
	public void setResultado(String resultado) {
		this.resultado = resultado;
	}
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	
}
