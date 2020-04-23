package mx.com.teclo.saicdmx.persistencia.hibernate.vo.catalogos;

import java.io.Serializable;

public class ConcesionariaVO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1875807024359146744L;
	
	private Long concesionariaId;
	private String concesionariaCodigo;
	private String concesionariaPrefijo;
	private String concesionariaNombre;
	private String concesionariaStatus;
	
	public Long getConcesionariaId() {
		return concesionariaId;
	}
	public void setConcesionariaId(Long concesionariaId) {
		this.concesionariaId = concesionariaId;
	}
	public String getConcesionariaCodigo() {
		return concesionariaCodigo;
	}
	public void setConcesionariaCodigo(String concesionariaCodigo) {
		this.concesionariaCodigo = concesionariaCodigo;
	}
	public String getConcesionariaPrefijo() {
		return concesionariaPrefijo;
	}
	public void setConcesionariaPrefijo(String concesionariaPrefijo) {
		this.concesionariaPrefijo = concesionariaPrefijo;
	}
	public String getConcesionariaNombre() {
		return concesionariaNombre;
	}
	public void setConcesionariaNombre(String concesionariaNombre) {
		this.concesionariaNombre = concesionariaNombre;
	}
	public String getConcesionariaStatus() {
		return concesionariaStatus;
	}
	public void setConcesionariaStatus(String concesionariaStatus) {
		this.concesionariaStatus = concesionariaStatus;
	}
	public String getStatusDesc() {
		return this.getConcesionariaStatus().equals("A") ? "Activo" : "Cancelado";
	}
}
