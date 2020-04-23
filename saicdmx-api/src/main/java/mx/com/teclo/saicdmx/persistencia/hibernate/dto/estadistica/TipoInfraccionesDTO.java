package mx.com.teclo.saicdmx.persistencia.hibernate.dto.estadistica;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="TIPO_INFRACCIONES")
public class TipoInfraccionesDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6229107138864475678L;
    
	@Id
    @GeneratedValue
	@Column(name = "ID_DELEGACION", unique = true, nullable = false)
	private String Id_Delegacion;  
	@Column(name = "NB_DELEGACION")
	private String Nb_Delegacion;  
	
	public String getId_Delegacion() {
		return Id_Delegacion;
	}
	public void setId_Delegacion(String id_Delegacion) {
		Id_Delegacion = id_Delegacion;
	}
	public String getNb_Delegacion() {
		return Nb_Delegacion;
	}
	public void setNb_Delegacion(String nb_Delegacion) {
		Nb_Delegacion = nb_Delegacion;
	}	
	
}
