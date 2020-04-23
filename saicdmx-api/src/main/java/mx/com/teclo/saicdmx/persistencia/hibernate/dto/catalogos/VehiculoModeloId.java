package mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class VehiculoModeloId implements Serializable
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6236784552009544865L;

	@Basic(optional = false)
	@Column(name = "VMAR_ID", nullable = false)
	private Long vMarId;
	
	@Basic(optional = false)
	@Column(name = "VMOD_ID", nullable = false)
	private Long vModId;

	
	public Long getvMarId() {
		return vMarId;
	}
	
	public void setvMarId(Long vMarId) {
		this.vMarId = vMarId;
	}
	/**
	 * @return the vModId
	 */
	public Long getvModId() {
		return vModId;
	}
	/**
	 * @param vModId the vModId to set
	 */
	public void setvModId(Long vModId) {
		this.vModId = vModId;
	}
}
