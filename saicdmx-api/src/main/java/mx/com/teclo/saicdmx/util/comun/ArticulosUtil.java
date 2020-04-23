package mx.com.teclo.saicdmx.util.comun;

public class ArticulosUtil {

	public static String formatFraccion(String fraccion){
		switch (fraccion){
		case "1": return "I";
		case "2": return "II";
		case "3": return "II";
		case "4": return "IV";
		case "5": return "V";
		case "6": return "VI";
		case "7": return "VI";
		case "8": return "VIII";
		case "9": return "IX";
		case "10": return "X";
		case "11": return "XI";
		case "12": return "XII";
		case "13": return "XIII";
		case "14": return "XIV";
		case "15": return "XV";
		case "16": return "XVI";
		case "17": return "XVII";
		case "18": return "XVIII";
		case "19": return "XIX";		
		  default: return "-----";
		}
	}

	public static String formatParrafo(String parrafo){
		switch (parrafo){
		case "1": return "PRIMERO";
		case "2": return "SEGUNDO";
		case "3": return "TERCER";		
		case "P": return "PENULTIMO";
		case "U": return "ULTIMO";			
		 default: return "-----";
		}
	}
	
	public static String formatParrafo(int numero){
		String Unidad[] = {"", "primero", "segundo", "tercero",
	            "cuarto", "quinto", "sexto", "septimo", "octavo", "noveno"};
	        String Decena[] = {"", "decimo", "vigesimo", "trigesimo",
	            "cuadragesimo", "quincuagesimo", "sexagesimo", "septuagesimo",
	            "octogesimo", "nonagesimo"};
	        String Centena[] = {"", "centesimo", "ducentesimo", "tricentesimo",
	            " cuadringentesimo", " quingentesimo", " sexcentesimo",
	            " septingentesimo", " octingentesimo", " noningentesimo"};        
	        int N = numero;
	        int u = N % 10;
	        int d = (N / 10) % 10;
	        int c = N / 100;
	        if (N >= 100) {
	            return Centena[c] + " " + Decena[d] + " " + Unidad[u];
	        } else {
	            if (N >= 10) {
	                return Decena[d] + " " + Unidad[u];
	            } else {
	                return Unidad[N];
	            }
	        }
	}
	
	public static String formatInciso(String inciso){
		switch (inciso){
		case "0": return "-----";				
		 default: return inciso;
		}
	}
	
}
