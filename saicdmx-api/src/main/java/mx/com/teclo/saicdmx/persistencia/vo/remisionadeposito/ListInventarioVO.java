package mx.com.teclo.saicdmx.persistencia.vo.remisionadeposito;

import java.io.Serializable;

public class ListInventarioVO implements Serializable{

	private static final long serialVersionUID = 3555308119480100363L;

	private String comp_id;
	private String comp_cod;
	private String comp_nombre;
	
	public String getComp_id() {
		return comp_id;
	}
	public void setComp_id(String comp_id) {
		this.comp_id = comp_id;
	}
	public String getComp_cod() {
		return comp_cod;
	}
	public void setComp_cod(String comp_cod) {
		this.comp_cod = comp_cod;
	}
	public String getComp_nombre() {
		return comp_nombre;
	}
	public void setComp_nombre(String comp_nombre) {
		this.comp_nombre = comp_nombre;
	}
	
}
