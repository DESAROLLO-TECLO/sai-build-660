package mx.com.teclo.saicdmx.persistencia.vo.remisionadeposito;

import java.io.Serializable;

public class ArticuloSancionVO implements Serializable{

	  private static final long serialVersionUID = 3555308119480100363L;
		
		private int art_id;
		private int sancion_art_id;
		private String vorigen;
		private String art_numero;
		private String art_fraccion;
		private String art_parrafo;
		private String art_inciso;
		private String art_motivacion;
		private String art_dias;
		
		public int getArt_id() {
			return art_id;
		}
		public void setArt_id(int art_id) {
			this.art_id = art_id;
		}
		public int getSancion_art_id() {
			return sancion_art_id;
		}
		public void setSancion_art_id(int sancion_art_id) {
			this.sancion_art_id = sancion_art_id;
		}
		public String getVorigen() {
			return vorigen;
		}
		public void setVorigen(String vorigen) {
			this.vorigen = vorigen;
		}
		public String getArt_numero() {
			return art_numero;
		}
		public void setArt_numero(String art_numero) {
			this.art_numero = art_numero;
		}
		public String getArt_fraccion() {
			return art_fraccion;
		}
		public void setArt_fraccion(String art_fraccion) {
			this.art_fraccion = art_fraccion;
		}
		public String getArt_parrafo() {
			return art_parrafo;
		}
		public void setArt_parrafo(String art_parrafo) {
			this.art_parrafo = art_parrafo;
		}
		public String getArt_inciso() {
			return art_inciso;
		}
		public void setArt_inciso(String art_inciso) {
			this.art_inciso = art_inciso;
		}
		public String getArt_motivacion() {
			return art_motivacion;
		}
		public void setArt_motivacion(String art_motivacion) {
			this.art_motivacion = art_motivacion;
		}
		/**
		 * @return the art_dias
		 */
		public String getArt_dias() {
			return art_dias;
		}
		/**
		 * @param art_dias the art_dias to set
		 */
		public void setArt_dias(String art_dias) {
			this.art_dias = art_dias;
		}

		
}
