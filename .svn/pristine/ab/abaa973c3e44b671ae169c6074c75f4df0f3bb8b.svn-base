package mx.com.teclo.saicdmx.persistencia.hibernate.dto.articulos;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class SancionesArtId implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2682501376873115958L;
		
	@Basic(optional = false)
	@Column(name = "ART_ID", nullable = false)
	private Long articuloId;
	
	@Basic(optional = false)
	@Column(name = "ART_SANCION", nullable = false)
	private Long artSancionId;

	public Long getArticuloId() {
		return articuloId;
	}

	public void setArticuloId(Long articuloId) {
		this.articuloId = articuloId;
	}

	public Long getArtSancionId() {
		return artSancionId;
	}

	public void setArtSancionId(Long artSancionId) {
		this.artSancionId = artSancionId;
	}
	
}
