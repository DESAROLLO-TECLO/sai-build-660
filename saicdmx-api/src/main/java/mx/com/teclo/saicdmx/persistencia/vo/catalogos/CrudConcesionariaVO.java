package mx.com.teclo.saicdmx.persistencia.vo.catalogos;

public class CrudConcesionariaVO extends BaseCrudVO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5454555924056488065L;
	
	private Long concesionariaId;
	private String concesionariaCodigo;
	private String concesionariaPrefijo;
	private String concesionariaNombre;
	
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
}
