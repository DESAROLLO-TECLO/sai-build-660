package mx.com.teclo.saicdmx.persistencia.hibernate.vo.catalogos;

import java.io.Serializable;

public class TipoSuministroVO implements Serializable{

	private static final long serialVersionUID = 3555308119480100363L;
	
	private Long id_tipo;
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
