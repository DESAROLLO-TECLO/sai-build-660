package mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class UTId implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7625795277395688084L;
	
	@Basic(optional = false)
	@Column(name = "SEC_ID", nullable = false)
	private Long secId;
	
	@Basic(optional = false)
	@Column(name = "UT_ID", nullable = false)
	private Long utId;

	

	public Long getSecId() {
		return secId;
	}

	public void setSecId(Long secId) {
		this.secId = secId;
	}

	/**
	 * @return the utId
	 */
	public Long getUtId() {
		return utId;
	}

	/**
	 * @param utId the utId to set
	 */
	public void setUtId(Long utId) {
		this.utId = utId;
	}
}