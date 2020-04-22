package mx.com.teclo.saicdmx.util.comun;

public class LetraCapitalUtil {

	public String getLetraCapitalPalabra(String palabra) {
		return (palabra.charAt(0)+"").toUpperCase() + palabra.substring(1).toLowerCase();
	}
	
	public String covertirNombreMayusYMinus(String texto, Boolean esApellido)
	{

    String textoConvertido="";
			if(texto!=null)
				{
			if(!esApellido)
				{
				String arrayNombre[]=texto.split(" ");
	        	if(arrayNombre.length!=1)
	        		{
	        		for (int x=0;x<arrayNombre.length;x++)
	            	{
	        			arrayNombre[x]=arrayNombre[x].toUpperCase().charAt(0) + arrayNombre[x].toLowerCase().substring(1, arrayNombre[x].length());
	        		}
	        		
	                for(int i = 0; i<arrayNombre.length; i++) {
	                	textoConvertido = textoConvertido + arrayNombre[i] + " ";
	                }
	        		}
	        	else
	        		{
	        		textoConvertido=arrayNombre[0].toUpperCase().charAt(0) + arrayNombre[0].toLowerCase().substring(1, arrayNombre[0].length());
	        		
	        		}
				}
			else
				{
			String arrayApellido[]=texto.split(" ");
			
	        	if(arrayApellido.length!=1)
	        		{
	        		for (int x=0;x<arrayApellido.length;x++)
	            	{
	        			if(x+1!=arrayApellido.length)
	        			arrayApellido[x]=arrayApellido[x].toLowerCase();
	        		  else
	        		  arrayApellido[x]=arrayApellido[x].toUpperCase().charAt(0) + arrayApellido[x].toLowerCase().substring(1, arrayApellido[x].length());
	    	        	
	        		}
	        		for(int i = 0; i<arrayApellido.length; i++) {
	                	textoConvertido = textoConvertido + arrayApellido[i] + " ";
	        	
	        		}
	        		}
	        	else
	        		{
	        		textoConvertido=arrayApellido[0].toUpperCase().charAt(0) + arrayApellido[0].toLowerCase().substring(1, arrayApellido[0].length());
    	        	
	        		}
				
				}
				}
		
        	
          return textoConvertido;
			
		}
}

