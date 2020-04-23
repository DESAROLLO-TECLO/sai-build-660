package mx.com.teclo.saicdmx.persistencia.hibernate.vo.catalogos;

import java.io.Serializable;

public class CategoriaVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7186702936128870157L;

	private Long categoriaId;
	private String categoriaCodigo;
	private String categoriaDesc;
	private String categoriaStatus;
	
	public Long getCategoriaId() {
		return categoriaId;
	}
	public void setCategoriaId(Long categoriaId) {
		this.categoriaId = categoriaId;
	}
	public String getCategoriaCodigo() {
		return categoriaCodigo;
	}
	public void setCategoriaCodigo(String categoriaCodigo) {
		this.categoriaCodigo = categoriaCodigo;
	}
	public String getCategoriaDesc() {
		return categoriaDesc;
	}
	public void setCategoriaDesc(String categoriaDesc) {
		this.categoriaDesc = categoriaDesc;
	}
	public String getCategoriaStatus() {
		return categoriaStatus;
	}
	public void setCategoriaStatus(String categoriaStatus) {
		this.categoriaStatus = categoriaStatus;
	}
	public String getStatusDesc() {
		return this.getCategoriaStatus().equals("A") ? "Activo" : "Cancelado";
	}
}
