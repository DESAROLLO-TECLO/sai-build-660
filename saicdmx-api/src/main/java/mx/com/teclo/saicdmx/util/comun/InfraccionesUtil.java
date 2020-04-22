package mx.com.teclo.saicdmx.util.comun;

public class InfraccionesUtil {
	
	public static String generaNumeroDeControl(String numeroInfraccion,String placaVehiculo){		
		int sizePlaca = 7;
		int addCeros = 0;
		String ceros = "";
		
		if(placaVehiculo!=null){			
			addCeros = sizePlaca - placaVehiculo.length();
			for(int i = 1; i<= addCeros; i++){
				ceros+="0";
			}
		}
		
		 return numeroInfraccion+ceros+placaVehiculo;
	}
	
	public static Long decodificaTipoLicencia(String tipo){
		switch(tipo){
		case "A": return Long.valueOf(1);
		case "B": return Long.valueOf(2);
		case "C": return Long.valueOf(3);
		case "D": return Long.valueOf(4);
		case "E": return Long.valueOf(5);
		 default: return Long.valueOf(99);
		}
	}
	

}
