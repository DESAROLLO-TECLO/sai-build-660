package mx.com.teclo.saicdmx.persistencia.vo.remisionadeposito;

import java.io.Serializable;

public class ListOperativoVO  implements Serializable{

	private static final long serialVersionUID = 3555308119480100363L;
	
	private String oper_cod;
	private String oper_nombre;
	
	public String getOper_cod() {
		return oper_cod;
	}
	public void setOper_cod(String oper_cod) {
		this.oper_cod = oper_cod;
	}
	public String getOper_nombre() {
		return oper_nombre;
	}
	public void setOper_nombre(String oper_nombre) {
		this.oper_nombre = oper_nombre;
	}
	
}
