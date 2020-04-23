package mx.com.teclo.saicdmx.persistencia.vo.remisionadeposito;

import java.io.Serializable;

public class ListCausaIngresoVO  implements Serializable{

	private static final long serialVersionUID = 3555308119480100363L;
	
	private String causa_cod;
	private String causa_nombre;
	
	public String getCausa_cod() {
		return causa_cod;
	}
	public void setCausa_cod(String causa_cod) {
		this.causa_cod = causa_cod;
	}
	public String getCausa_nombre() {
		return causa_nombre;
	}
	public void setCausa_nombre(String causa_nombre) {
		this.causa_nombre = causa_nombre;
	}
	
}
