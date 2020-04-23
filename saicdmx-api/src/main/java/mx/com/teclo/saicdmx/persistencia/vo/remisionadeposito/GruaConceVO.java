package mx.com.teclo.saicdmx.persistencia.vo.remisionadeposito;

import java.io.Serializable;

public class GruaConceVO implements Serializable{

		  private static final long serialVersionUID = 3555308119480100363L;
			
			private int grua_id;
			private String grua_cod;
			private String conse_prefijo;
		
			private String conseCod;	
			private Long conseArrastre;	
			private String conseNombre;	
			private String conseStatus;	
			private Long conseId;
			private Long modificadoPor;
			
			
			
			public int getGrua_id() {
				return grua_id;
			}
			public void setGrua_id(int grua_id) {
				this.grua_id = grua_id;
			}
			public String getGrua_cod() {
				return grua_cod;
			}
			public void setGrua_cod(String grua_cod) {
				this.grua_cod = grua_cod;
			}
			public String getConse_prefijo() {
				return conse_prefijo;
			}
			public void setConse_prefijo(String conse_prefijo) {
				this.conse_prefijo = conse_prefijo;
			}
			public String getConseCod() {
				return conseCod;
			}
			public void setConseCod(String conseCod) {
				this.conseCod = conseCod;
			}
			public Long getConseArrastre() {
				return conseArrastre;
			}
			public void setConseArrastre(Long conseArrastre) {
				this.conseArrastre = conseArrastre;
			}
			public String getConseNombre() {
				return conseNombre;
			}
			public void setConseNombre(String conseNombre) {
				this.conseNombre = conseNombre;
			}
			public String getConseStatus() {
				return conseStatus;
			}
			public void setConseStatus(String conseStatus) {
				this.conseStatus = conseStatus;
			}
			public Long getConseId() {
				return conseId;
			}
			public void setConseId(Long conseId) {
				this.conseId = conseId;
			}
			public Long getModificadoPor() {
				return modificadoPor;
			}
			public void setModificadoPor(Long modificadoPor) {
				this.modificadoPor = modificadoPor;
			}
			
			
				
}
