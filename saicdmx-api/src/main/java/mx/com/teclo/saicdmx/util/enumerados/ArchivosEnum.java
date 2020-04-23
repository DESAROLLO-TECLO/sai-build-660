package mx.com.teclo.saicdmx.util.enumerados;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author UnitisDes0416
 *
 */
public enum ArchivosEnum {

	ENTRADA("ENTRADA//"), 
	SALIDA("SALIDA/"),
	REGLAMENTO_FECHA_DISTRITO_FEDERAL("15/12/2015"),
	NODO_OPCIONAL(" "),
	FOTO_MUTLTA("FOTO-MULTA");
	
	private String constante;
	private static Map<String, ArchivosEnum> dictionary;
	
	static {
		  dictionary = new HashMap<String, ArchivosEnum>();
		  for(ArchivosEnum state : values()) {
		      dictionary.put(state.constante, state);
		  }
		}
	
	ArchivosEnum(String constante){
		this.constante = constante;
	}
	
	/**
	 * @return the message
	 */
	public String getConstante() {
		return constante;
	}
	
	/**
	 * @return the message
	 */
	public Integer getIntegerConstante(){
		return Integer.parseInt(constante);
	}

	/**
	 * @param message
	 *            the message to set
	 */
	public void setConstante(String constante) {
		this.constante = constante;
	}
	
	public static ArchivosEnum fromString(String fromValue) {
		ArchivosEnum var = dictionary.get(fromValue);
		  if(var == null) {
		      return null;
		  }
		  return var;
		}
}
