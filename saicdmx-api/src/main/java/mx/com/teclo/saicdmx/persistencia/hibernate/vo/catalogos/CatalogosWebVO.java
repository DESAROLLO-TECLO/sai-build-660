package mx.com.teclo.saicdmx.persistencia.hibernate.vo.catalogos;

import java.io.Serializable;

public class CatalogosWebVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7774284789618189355L;
	
	private Long catalogoId;
	private String catalogoDesc;
	
	public Long getCatalogoId() {
		return catalogoId;
	}
	public void setCatalogoId(Long catalogoId) {
		this.catalogoId = catalogoId;
	}
	public String getCatalogoDesc() {
		return catalogoDesc;
	}
	public void setCatalogoDesc(String catalogoDesc) {
		this.catalogoDesc = catalogoDesc;
	}
}
