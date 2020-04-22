package mx.com.teclo.saicdmx.util.comun;

import java.util.ArrayList;
import java.util.List;


public class BitComponente {

		private long componente;
		private String parametro;
		
		public BitComponente(long componente, String parametro){
			this.componente = componente;
			this.parametro = parametro;
		}
		
		public BitComponente(){
			this(0, "");
		}
		
		public long getComponente()
		{return componente;}
		
		public String getParametro()
		{return parametro;}
		
		static public List<String> convertToList(List<BitComponente> lista)
		{
			List<String> list = new ArrayList<String>();
			for(BitComponente comp : lista)
			{
				list.add(comp.getParametro());
			}
			return list;
		}
		
		static public BitComponente findByParametro(List<BitComponente> lista, String parametro)
		{
			for(BitComponente comp : lista)
			{
				if(comp.getParametro().equals(parametro))
					return comp;
			}
			return null;
		}
	}

