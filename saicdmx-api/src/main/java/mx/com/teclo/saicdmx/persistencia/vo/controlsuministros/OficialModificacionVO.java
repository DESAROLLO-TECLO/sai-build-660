package mx.com.teclo.saicdmx.persistencia.vo.controlsuministros;

import java.io.Serializable;

public class OficialModificacionVO implements Serializable{

	private static final long serialVersionUID = 3555308119480100363L;

	private long id_registro;
	private String oficial_placa;
	private Long idUser;
	private String resultado;
	private String mensaje;

	public long getId_registro() {
		return id_registro;
	}
	public void setId_registro(long id_registro) {
		this.id_registro = id_registro;
	}
	public String getOficial_placa() {
		return oficial_placa;
	}
	public void setOficial_placa(String oficial_placa) {
		this.oficial_placa = oficial_placa;
	}
	public Long getIdUser() {
		return idUser;
	}
	public void setIdUser(Long idUser) {
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
