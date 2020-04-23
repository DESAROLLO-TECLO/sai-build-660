package mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class RegionId implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1393915580726230862L;
	
	@Basic(optional = false)
	@Column(name = "EDO_ID", nullable = false)
	private Long edoId;
	
	@Basic(optional = false)
	@Column(name = "REG_ID", nullable = false)
	private Long regId;

	public Long getEdoId() {
		return edoId;
	}

	public void setEdoId(Long edoId) {
		this.edoId = edoId;
	}

	public Long getRegId() {
		return regId;
	}

	public void setRegId(Long regId) {
		this.regId = regId;
	}

}
