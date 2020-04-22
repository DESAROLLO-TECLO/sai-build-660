package mx.com.teclo.saicdmx.util.comun;

import java.lang.reflect.Method;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class ComparaObjetoUtils {

	@SuppressWarnings({ "finally", "rawtypes", "unchecked" })
	public static List<Method> compararObjetos(Object obj1, Object obj2, Class persistentClass, List<String> filtro){
		if (obj1.getClass() == persistentClass && obj2.getClass() == persistentClass){
			
			//persistentClass picdVO2 = (persistentClass) obj2;
			
			List<Method> lista = new ArrayList<Method>(); 
			
			Iterator<String> flt =filtro.iterator();
			
			try{
				while(flt.hasNext()){
					String param = flt.next();
					if(persistentClass.getMethod(param) != null){
						Method met1 = obj1.getClass().getMethod(param);
						Method met2 = obj2.getClass().getMethod(param);
						
						Object r1 = met1.invoke(obj1);
						Object r2 = met2.invoke(obj2);
						
						if(r1 != null){
							if(r1.getClass() == Date.class)
							{Date a1 = (Date)r1; if(a1.compareTo((Date) r2) != 0){lista.add(met2);};}
							else if(r1.getClass() == String.class)
							{if(!ComparaUtils.comparaCadenas((String)r1,(String) r2)){lista.add(met2);}}
							else{
								if(!r1.equals(r2 != null ? r2 : ""))
									lista.add(met2);
							}
						}else{
							if(r2.getClass() == Date.class)
							{Date a2 = (Date)r2; if(a2.compareTo((Date) r1) != 0){lista.add(met2);};}
							else if(r2.getClass() == String.class)
							{if(!ComparaUtils.comparaCadenas((String)r2, (String)r1)){lista.add(met2);}}
							else{
								if(!r2.equals(r1 != null ? r1 : ""))
									lista.add(met2);
							}
						}
					}else{
						return null;
					}
				}
				
				//obj1 = Class.forName(persistentClass.getName()).cast(obj1);
			}
			catch(NoSuchMethodException nsme)
			{
				nsme.printStackTrace();
			}finally{
				return lista;
			}
		}
		return null;
	}
}
