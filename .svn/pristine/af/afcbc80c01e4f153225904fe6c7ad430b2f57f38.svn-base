package mx.com.teclo.saicdmx.util.enumerados;

import java.util.HashMap;
import java.util.Map;

public enum ArchivoTipoProcesoEnum {
	PROCESO_COMPLETO(1),
	PROCESO_FORANEO(2);
	
	private Integer constante;
	private static Map<Integer, ArchivoTipoProcesoEnum> dictionary;
	
	static {
		  dictionary = new HashMap<Integer, ArchivoTipoProcesoEnum>();
		  for(ArchivoTipoProcesoEnum state : values()) {
		      dictionary.put(state.constante, state);
		  }
		}
	
	ArchivoTipoProcesoEnum(Integer constante){
		this.constante = constante;
	}
	
	/**
	 * @return the constante
	 */
	public Integer getConstante() {
		return constante;
	}
	
	/**
	 * @return the constante
	 */
	public Long getLongConstante() {
		return Long.parseLong(constante.toString());
	}
	
	/**
	 * @param constante
	 *            the constante to set
	 */
	public void setLongConstante(Integer constante) {
		this.constante = constante;
	}
	
	public static ArchivoTipoProcesoEnum fromInteger(Integer fromValue) {
		ArchivoTipoProcesoEnum var = dictionary.get(fromValue);
		  if(var == null) {
		      return null;
		  }
		  return var;
		}
}
