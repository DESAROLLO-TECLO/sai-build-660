package mx.com.teclo.saicdmx.persistencia.vo.catalogos;

public class CrudCategoriaVO extends BaseCrudVO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8487452198232534126L;
	
	private Long categoriaId;
	private String categoriaCodigo;
	private String categoriaDesc;
	
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
}
