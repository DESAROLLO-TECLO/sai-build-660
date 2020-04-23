package mx.com.teclo.saicdmx.util.comun;

public class ComparaUtils {
	
	/**
	 * Inicializa un string cuando es null <br> de lo contrario retorna el valor del string original
	 * @param string
	 * @return	string
	 */
	public static String cadenaNvl(String string){
		string = string == null ? " " : string; 
		return string;
	}
	
	public static String LongNvl(Integer valorLong){
		String string = valorLong == null ? " " : valorLong.toString(); 
		return string;
	}
	public static String LongNvl(Long valorLong){
		String string = valorLong == null ? " " : valorLong.toString(); 
		return string;
	}
	
	public static Boolean comparaCadenas(String string1,String string2){
		return cadenaNvl(string1).equals(cadenaNvl(string2));
	}
	
	public static Boolean comparaCadenasLength(String string1,String string2){
		return cadenaNvl(string1).length() == cadenaNvl(string2).length();
	}
	
	public static Boolean comparaCadenas(String string1,Integer string2){
		return cadenaNvl(string1).equals(LongNvl(string2));
	}
	
	public static Boolean comparaCadenas(Long string1,Long string2){
		return LongNvl(string1).equals(LongNvl(string2));
	}
	

	
	
}
