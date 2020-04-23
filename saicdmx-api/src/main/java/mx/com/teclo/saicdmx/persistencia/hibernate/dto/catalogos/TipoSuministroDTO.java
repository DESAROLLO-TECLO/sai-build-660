package mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ctrol_rollos_tipo_sum")
public class TipoSuministroDTO implements Serializable {
	
	private static final long serialVersionUID = -936077548484000666L;
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "ID_TIPO", unique = true, nullable = false)
	private Long id_tipo;
	@Column(name = "DESCRIPCION")
	private String descripcion;
	
	public Long getId_tipo() {
		return id_tipo;
	}
	public void setId_tipo(Long id_tipo) {
		this.id_tipo = id_tipo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
}
