package mx.com.teclo.saicdmx.persistencia.hibernate.dto.parteinformativo;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ParteInformativoBoletaInfracsPK implements Serializable{
	private static final long serialVersionUID = 8115808029671993671L;

	@Basic(optional = false)
	@Column(name = "PI_ID")
	private Long piId;
	
	@Column(name = "INFRAC_NUM", length=11)
	private String infracNum;

	/**
	 * @return the piId
	 */
	public Long getPiId() {
		return piId;
	}

	/**
	 * @param piId the piId to set
	 */
	public void setPiId(Long piId) {
		this.piId = piId;
	}

	/**
	 * @return the infracNum
	 */
	public String getInfracNum() {
		return infracNum;
	}

	/**
	 * @param infracNum the infracNum to set
	 */
	public void setInfracNum(String infracNum) {
		this.infracNum = infracNum;
	}
}
